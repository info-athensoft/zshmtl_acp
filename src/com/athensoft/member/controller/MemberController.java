package com.athensoft.member.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.athensoft.member.entity.Member;
import com.athensoft.member.entity.MemberStatus;
import com.athensoft.member.service.MemberService;

import com.athensoft.util.time.DatetimeHelper;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	private static final Logger logger = Logger.getLogger(MemberController.class);
	
	private static final String ACTION_EDIT = "管理";
	private static final String ACTION_DELETE = "删除";
	
	private MemberService memberService;
	
	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("/member_list.html")
	public String gotoMemberList(){
		return "member/member_list";
	}
	
	@RequestMapping(value="/memberListData",produces="application/json")
	@ResponseBody
	public Map<String,Object> getDataMemberList(){
		logger.info("entering /member/memberListData");
		
		ModelAndView mav = new ModelAndView();
		
		//data
		List<Member> listMembers = memberService.getAllMembers();
		logger.info("Length of member entries: "+ listMembers.size());
		
		String[][] data = getData(listMembers, ACTION_EDIT);
		
		Map<String, Object> model = mav.getModel();
		
		model.put("draw", new Integer(1));
		model.put("recordsTotal", new Integer(5));
		model.put("recordsFiltered", new Integer(5));
		model.put("data", data);
		model.put("customActionStatus","OK");
		model.put("customActionMessage","Data loaded");
		
		logger.info("leaving /member/memberListData");
		return model;
	}
	
	@RequestMapping(value="/searchFilterData",produces="application/json")
	@ResponseBody
	public Map<String,Object> getDataSearchMembersByFilter(@RequestParam String itemJSONString){
		logger.info("entering /member/searchFilterData");
		
		//get parameters
		JSONObject jobj= new JSONObject(itemJSONString);
		
		String where1 = jobj.getString("acctName").trim();
		String where2 = jobj.getString("name1").trim();
		String where3 = jobj.getString("name2").trim();
		int where4 = jobj.getInt("gender");
		String where5 = jobj.getString("phone1").trim();
		String where6 = jobj.getString("phone2").trim();
		String where7 = jobj.getString("wechat").trim();
		String where8 = jobj.getString("email").trim();
		int where9 = jobj.getInt("memberStatus");
		
		/* construct query string */
		StringBuffer queryString = new StringBuffer();
		queryString.append(where1.length()==0?" ":" and acct_name like '%"+where1+"%' ");
		queryString.append(where2.length()==0?" ":" and name1 like '%"+where2+"%' ");
		queryString.append(where3.length()==0?" ":" and name2 like '%"+where3+"%' ");
		queryString.append(where4==0?" ":" and gender = "+where4+" ");
		queryString.append(where5.length()==0?" ":" and phone1 like '%"+where5+"%' ");
		queryString.append(where6.length()==0?" ":" and phone2 like '%"+where6+"%' ");
		queryString.append(where7.length()==0?" ":" and wechat like '%"+where7+"%' ");
		queryString.append(where8.length()==0?" ":" and email like '%"+where8+"%' ");
		queryString.append(where9==0?" ":" and member_status = "+where9+" ");
		logger.info("QueryString = "+ queryString.toString());
		
		List<Member> listMember = memberService.getMembersByFilter(queryString.toString());
		logger.info("Length of member entries = "+ listMember.size());
		
		//data
		String[][] data = getData(listMember, ACTION_EDIT);
		
		//
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = mav.getModel();
		
		model.put("draw", new Integer(1));
		model.put("recordsTotal", new Integer(5));
		model.put("recordsFiltered", new Integer(5));
		model.put("data", data);
		model.put("customActionStatus","OK");
		model.put("customActionMessage","OK");
		
		logger.info("leaving /member/searchFilterData");
		return model;
	}
	
	@RequestMapping(value="/member_edit.html")
	public ModelAndView gotoMemberEdit(@RequestParam String acctName){
		logger.info("entering /member/memberEdit");
		
		//logic
		Member member = memberService.getMemberByAcctName(acctName);
		
		logger.info("current member profile:"+member.toString());
		
		ModelAndView mav = new ModelAndView();
		
		//data
		Map<String, Object> model = mav.getModel();
		model.put("memberObject", member);
			
		//view
		String viewName = "member/member_edit";
		mav.setViewName(viewName);
		
		logger.info("leaving /member/memberEdit");
		return mav;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String,Object> updateMember(@RequestParam String itemJSONString) {
		logger.info("entering /member/update");
		
		//set model
//      Map<String, Object> model = mav.getModel();
        JSONObject jsonObj= new JSONObject(itemJSONString);
   
        Member member = new Member();
        member.setAcctName(jsonObj.getString("acctName"));
        member.setName1(jsonObj.getString("name1"));
        member.setName2(jsonObj.getString("name2"));
        member.setGender(jsonObj.getInt("gender"));
        member.setNationality(jsonObj.getString("nationality"));
        member.setPhone1(jsonObj.getString("phone1"));
        member.setPhone2(jsonObj.getString("phone2"));
        member.setWechat(jsonObj.getString("wechat"));
        member.setEmail(jsonObj.getString("email"));
        member.setDegree(jsonObj.getString("degree"));
        member.setOccupation(jsonObj.getString("occupation"));
        member.setDob(jsonObj.getString("dob"));
        member.setPobProvince(jsonObj.getString("pobProvince"));
        member.setPobCity(jsonObj.getString("pobCity"));
        member.setHomeAddress(jsonObj.getString("homeAddress"));
        member.setPostalcode(jsonObj.getString("postalcode"));
        member.setMemberLevel(jsonObj.getInt("memberLevel"));
        int intMemberStatus = jsonObj.getInt("memberStatus");
        member.setMemberStatus(intMemberStatus);
        
        switch(intMemberStatus){
	        case MemberStatus.APPLIED:
	        	member.setMemberApplyDate(new Date());
	        	break;
	        case MemberStatus.APPROVED:
	        	member.setMemberApprovedDate(new Date());
	        	break;
	        case MemberStatus.ACTIVE:
	        	member.setMemberActiveDate(new Date());
	        	member.setMemberInactiveDate(DatetimeHelper.addOneYear(new Date()));
	        	break;
	        case MemberStatus.INACTIVE:
	        	member.setMemberInactiveDate(new Date());
	        	break;
	        case MemberStatus.BANNED:
	        	member.setMemberBannedDate(new Date());
	        	break;
	        case MemberStatus.PENDING:
	        	member.setMemberPendingDate(new Date());
	        	break;
	        default:
	        	break;
        }
       
        
//        System.out.println("memberActiveDate="+jsonObj.getString("memberActiveDate"));
//        
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		Date tmpDate = null;
//		try {
//			tmpDate = dateFormat.parse(jsonObj.getString("memberActiveDate"));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//        member.setMemberActiveDate(tmpDate);	//FIXME
        logger.info("member = "+member);
          
		/* business logic*/
        //long itemId = itemService.createItem(ic); 

        memberService.updateMember(member);
		
        /* initial settings */
		ModelAndView mav = new ModelAndView();
        
		/* assemble model and view */
        //String viewName = "member/member_edit";
		//mav.setViewName(viewName);		
		Map<String,Object> model = mav.getModel();
		model.put("updateState", "ok");
		
		logger.info("leaving /member/update");
		return model;		
	}
	
	
	@RequestMapping(value="/updateGroup",produces="application/json")
	@ResponseBody
	public Map<String,Object> updateMemberGroup(
			@RequestParam String memberArray,
			@RequestParam int memberStatus
			) {
		
		logger.info("entering /member/updateGroup");
		
		/* initial settings */
		ModelAndView mav = new ModelAndView();
		
		//set model
        Map<String, Object> model = mav.getModel();
   
        List<Member> memberList = new ArrayList<Member>();
        String[] members = memberArray.split(",");
        int memberLength = members.length;
        
        for(int i=0; i<memberLength; i++){
        	 Member member = new Member();
        	 member.setAcctName(members[i]);
        	 member.setMemberStatus(memberStatus);
        	 memberList.add(member);
             member = null;
        }
        
        logger.info("memberList size="+memberList.size());
        logger.info("memberList ="+memberList.toString());
        
		/* business logic*/
        memberService.updateMemberGroup(memberList);
        
		
		/* assemble model and view */
		logger.info("leaving /member/updateGroup");
		return model;		
	}
	
	
	private String[][] getData(List<Member> list, String actionName){
		int entryLength = list.size();
		final int COLUMN_NUM = 11;
		String[][] data = new String[entryLength][COLUMN_NUM];
		
		String field0 = "";	//check box
		String field1 = "";	//acctName
		String field2 = "";	//name1
		String field3 = "";	//name2
		String field4 = "";	//gender
		String field5 = "";	//phone1
		String field6 = "";	//phone2
		String field7 = "";	//wechat
		String field8 = "";	//email
		String field9 = "";	//memberStatus
		String field10 = "";	//action
		
		for(int i=0; i<entryLength ; i++){			
			field0 = "<input type='checkbox' name='id[]' value="+list.get(i).getAcctName()+">";
			field1 = list.get(i).getAcctName()+"";
			field2 = list.get(i).getName1();
			field3 = list.get(i).getName2();
			field4 = getGender(list.get(i).getGender());
			field5 = list.get(i).getPhone1().trim();
			field6 = list.get(i).getPhone2().trim();
			field7 = list.get(i).getWechat().trim();
			field8 = list.get(i).getEmail().trim();
			
			int intMemberStatus = list.get(i).getMemberStatus();
			String[] memberStatusPair = getMemberStatusPair(intMemberStatus);
			String memberStatusKey = memberStatusPair[0];
			String memberStatus = memberStatusPair[1];
			field9 = "<span class='label label-sm label-"+memberStatusKey+"'>"+memberStatus+"</span>";
			field10 = "<a href='/acp/member/"+getAction(actionName)+"?acctName="+field1+"' class='btn btn-xs default btn-editable'><i class='fa fa-pencil'></i> "+actionName+"</a>";
			
			//logger.info("field8="+field8);
			
			data[i][0] = field0;
			data[i][1] = field1;
			data[i][2] = field2;
			data[i][3] = field3;
			data[i][4] = field4;
			data[i][5] = field5;
			data[i][6] = field6;
			data[i][7] = field7;
			data[i][8] = field8;
			data[i][9] = field9;
			data[i][10] = field10;
		}
		
		return data;
	}
	
	private String getGender(int gender){
		String strGender = "";
		if(1==gender){
			strGender = "男";
		}else if(2==gender){
			strGender = "女";
		}else{
			strGender = "未知";
		}
		return strGender;
	}
	
	private String getAction(String actionName){
		String action = "";
		switch(actionName){
		case ACTION_EDIT:
			action = "member_edit.html";
			break;
		case ACTION_DELETE:
			action = "deleteMember";
			break;
		}
		return action;
	}
	
	private String[] getMemberStatusPair(int intStatus){
		String[] statusPair = new String[2];
		
		String status = "";
		String statusKey = "";
		switch(intStatus){
			case MemberStatus.APPLIED: 
//				status = "Applied";
				status = "已申请";
				statusKey = "warning";
				break;
			case MemberStatus.APPROVED: 
//				status = "Approved";
				status = "已通过预审";
				statusKey = "info";
				break;
			case MemberStatus.ACTIVE: 
//				status = "Active";
				status = "已激活";
				statusKey = "success";
				break;
			case MemberStatus.INACTIVE: 
//				status = "Inactive";
				status = "未激活";
				statusKey = "default";
				break;
			case MemberStatus.PENDING: 
//				status = "Pending";
				status = "审核中";
				statusKey = "danger";
				break;
			case MemberStatus.BANNED: 
//				status = "Banned";
				status = "已禁止";
				statusKey = "danger";
				break;
			default: 
				break;
		}
		
		statusPair[0]=statusKey;
		statusPair[1]=status;
		
		
		return statusPair;
	}
}

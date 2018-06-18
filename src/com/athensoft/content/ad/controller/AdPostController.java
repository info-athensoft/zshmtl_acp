package com.athensoft.content.ad.controller;


import java.text.SimpleDateFormat;
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

import com.athensoft.content.ad.entity.AdPost;
import com.athensoft.content.ad.entity.AdPostStatus;
import com.athensoft.content.ad.entity.AdPostType;
import com.athensoft.content.ad.service.AdPostService;
import com.athensoft.content.event.controller.NewsAcpController;
import com.athensoft.content.event.entity.Event;
import com.athensoft.content.event.entity.News;
import com.athensoft.util.id.UUIDHelper;


@Controller
@RequestMapping("/ad")
public class AdPostController {
	private static final Logger logger = Logger.getLogger(NewsAcpController.class);
	
	private static final String ACTION_EDIT = "Edit";
	private static final String ACTION_DELETE = "Delete";
	
	@Autowired
	private  AdPostService adPostService;
	
	
	@RequestMapping(value="/adpost_create.html")
	public String gotoAdPostCreate(){
		
		String viewName = "ad/adpost_create";
		
		return viewName;
	}
	
	
	@RequestMapping(value="/adpost_list.html")
	public String gotoAdPostList(){
		
		String viewName = "ad/adpost_list";
		
		return viewName;
	}
	
	
	@RequestMapping(value="/adpost/edit.html")
	public ModelAndView gotoAdPostEdit(@RequestParam String adUUID){
		logger.info("entering... /adpost/edit.html");
		
		ModelAndView mav = new ModelAndView();
		
		//view
		String viewName = "ad/adpost_edit";
		mav.setViewName(viewName);
		
		//data
		Map<String, Object> model = mav.getModel();
		
		//data - news
		AdPost adpost = adPostService.getAdPostByAdUUID(adUUID);	
		model.put("adPostObject", adpost);
		
		//data - media
		//List<EventMedia> listEventMedia = eventMediaService.getEventMediaByEventUUID(eventUUID);
		//logger.info("Length of EventReview entries: "+ listEventMedia.size());
		//model.put("eventMediaList", listEventMedia);
		
		logger.info("leaving... /adpost/edit.html");
		return mav;
	}
	
	
	@RequestMapping(value="/adPostListData",produces="application/json")
	@ResponseBody
	public Map<String,Object> getDataAdPostList(){
		logger.info("entering /ad/adPostListData");
		
		ModelAndView mav = new ModelAndView();
		
		//data
		List<AdPost> listAdPost = adPostService.getAdPostList();
		logger.info("Length of adpost entries: "+ listAdPost.size());
		
		String[][] data = getData(listAdPost, ACTION_EDIT);
		
		Map<String, Object> model = mav.getModel();
		
		model.put("draw", new Integer(1));
		model.put("recordsTotal", new Integer(5));
		model.put("recordsFiltered", new Integer(5));
		model.put("data", data);
		model.put("customActionStatus","OK");
		model.put("customActionMessage","Data loaded");
		
		logger.info("leaving /ad/adPostListData");
		return model;
	}
	
	
	@RequestMapping(value="/adpost/searchFilterData",produces="application/json")
	@ResponseBody
	public Map<String, Object> getDataSearchNewsByFilter(@RequestParam String itemJSONString){
		logger.info("entering /adpost/searchFilterData");
		
		
		JSONObject jobj= new JSONObject(itemJSONString);
		
		String where1 = jobj.getString("adUUID");
		String where2 = jobj.getString("adTitle");
		String where3 = jobj.getString("acctName");
		int where4 = jobj.getInt("adType");
		
		/* where5a, where5b */
		String strCreateDatetimeFrom = jobj.getString("createDatetimeFrom").trim();
		String strCreateDatetimeTo = jobj.getString("createDatetimeTo").trim();
		
		if(strCreateDatetimeFrom==null){
			strCreateDatetimeFrom = "";
		}
		if(strCreateDatetimeTo==null){
			strCreateDatetimeTo = "";
		}
		String where5a = strCreateDatetimeFrom;
		String where5b = strCreateDatetimeTo;
		
		logger.info("strCreateDatetimeFrom="+strCreateDatetimeFrom+"##");
		logger.info("strCreateDatetimeTo="+strCreateDatetimeTo+"##");
		
		/* where6a, where6b */
		String strPostDatetimeFrom = jobj.getString("postDatetimeFrom").trim();
		String strPostDatetimeTo = jobj.getString("postDatetimeTo").trim();
		
		if(strPostDatetimeFrom==null){
			strPostDatetimeFrom = "";
		}
		if(strPostDatetimeTo==null){
			strPostDatetimeTo = "";
		}
		String where6a = strPostDatetimeFrom;
		String where6b = strPostDatetimeTo;
		
		logger.info("strPostDatetimeFrom="+strPostDatetimeFrom+"##");
		logger.info("strPostDatetimeTo="+strPostDatetimeTo+"##");
		
		/* where7a, where7b */
		String strExpireDatetimeFrom = jobj.getString("expireDatetimeFrom").trim();
		String strExpireDatetimeTo = jobj.getString("expireDatetimeTo").trim();
		
		if(strExpireDatetimeFrom==null){
			strExpireDatetimeFrom = "";
		}
		if(strExpireDatetimeTo==null){
			strExpireDatetimeTo = "";
		}
		String where7a = strExpireDatetimeFrom;
		String where7b = strExpireDatetimeTo;
		
		logger.info("strExpireDatetimeFrom="+strExpireDatetimeFrom+"##");
		logger.info("strExpireDatetimeTo="+strExpireDatetimeTo+"##");
		
		/* where8a, where8b */
		String strModifyDatetimeFrom = jobj.getString("modifyDatetimeFrom").trim();
		String strModifyDatetimeTo = jobj.getString("modifyDatetimeTo").trim();
		
		if(strModifyDatetimeFrom==null){
			strModifyDatetimeFrom = "";
		}
		if(strModifyDatetimeTo==null){
			strModifyDatetimeTo = "";
		}
		String where8a = strModifyDatetimeFrom;
		String where8b = strModifyDatetimeTo;
		
		logger.info("strExpireDatetimeFrom="+strExpireDatetimeFrom+"##");
		logger.info("strExpireDatetimeTo="+strExpireDatetimeTo+"##");
		
				
		int where9 = jobj.getInt("adStatus");
		
		/* construct query string */
		StringBuffer queryString = new StringBuffer();
		queryString.append(where1.length()==0?" ":" and ad_uuid like '%"+where1+"%' ");
		queryString.append(where2.length()==0?" ":" and title like '%"+where2+"%' ");
		queryString.append(where3.length()==0?" ":" and acct_name like '%"+where3+"%' ");
		queryString.append(where4==0?" ":" and ad_type = "+where4+" ");
		
		String queryString_where5 = "";
		if(strCreateDatetimeFrom.equals("")&&strCreateDatetimeTo.equals("")){
			queryString_where5 = " ";
		}else if(!strCreateDatetimeFrom.equals("")&&strCreateDatetimeTo.equals("")){
			/* select * from event_news where date(post_datetime) >= adddate('2017-01-12', -1); */
			queryString_where5 = " and date(create_date) >= '"+where5a+"' ";
		}else if(strCreateDatetimeFrom.equals("")&&!strCreateDatetimeTo.equals("")){
			/* select * from event_news where date(post_datetime) <= '2017-02-05'; */
			queryString_where5 = " and date(create_date) <= '"+where5b+"' ";
		}else if(!strCreateDatetimeFrom.equals("")&&!strCreateDatetimeTo.equals("")){
			/* select * from event_news where date(post_datetime) between adddate('2017-01-12', -1) and '2017-02-05'; */
			int dateFlag = strCreateDatetimeFrom.compareTo(strCreateDatetimeTo);
			if(dateFlag<0){
				queryString_where5 = " and (date(create_date) between adddate('"+where5a+"', -1) and '"+where5b+"' ) ";
			}else if(dateFlag==0){
				queryString_where5 = " and (date(create_date) =  '"+where5a+"' ) ";
			}else{
				queryString_where5 = " and (date(create_date) between adddate('"+where5b+"', -1) and '"+where5a+"' ) ";
			}
			
		}else{
			logger.info("ERROR: create_datetime not valid date range");
		}
		queryString.append(queryString_where5);
		
		
		String queryString_where6 = "";
		if(strPostDatetimeFrom.equals("")&&strPostDatetimeTo.equals("")){
			queryString_where6 = " ";
		}else if(!strPostDatetimeFrom.equals("")&&strPostDatetimeTo.equals("")){
			queryString_where6 = " and date(post_date) >= '"+where6a+"' ";
		}else if(strPostDatetimeFrom.equals("")&&!strPostDatetimeTo.equals("")){
			queryString_where6 = " and date(post_date) <= '"+where6b+"' ";
		}else if(!strPostDatetimeFrom.equals("")&&!strPostDatetimeTo.equals("")){
			int dateFlag = strPostDatetimeFrom.compareTo(strPostDatetimeTo);
			if(dateFlag<0){
				queryString_where6 = " and (date(post_date) between adddate('"+where6a+"', -1) and '"+where6b+"' ) ";
			}else if(dateFlag==0){
				queryString_where6 = " and (date(post_date) =  '"+where6a+"' ) ";
			}else{
				queryString_where6 = " and (date(post_date) between adddate('"+where6b+"', -1) and '"+where6a+"' ) ";
			}
			
		}else{
			logger.info("ERROR: post_date not valid date range");
		}
		queryString.append(queryString_where6);
		
		
		String queryString_where7 = "";
		if(strExpireDatetimeFrom.equals("")&&strExpireDatetimeTo.equals("")){
			queryString_where7 = " ";
		}else if(!strExpireDatetimeFrom.equals("")&&strExpireDatetimeTo.equals("")){
			queryString_where7 = " and date(expire_date) >= '"+where7a+"' ";
		}else if(strExpireDatetimeFrom.equals("")&&!strExpireDatetimeTo.equals("")){
			queryString_where7 = " and date(expire_date) <= '"+where7b+"' ";
		}else if(!strExpireDatetimeFrom.equals("")&&!strExpireDatetimeTo.equals("")){
			int dateFlag = strExpireDatetimeFrom.compareTo(strExpireDatetimeTo);
			if(dateFlag<0){
				queryString_where7 = " and (date(expire_date) between adddate('"+where7a+"', -1) and '"+where7b+"' ) ";
			}else if(dateFlag==0){
				queryString_where7 = " and (date(expire_date) =  '"+where7a+"' ) ";
			}else{
				queryString_where7 = " and (date(expire_date) between adddate('"+where7b+"', -1) and '"+where7a+"' ) ";
			}
			
		}else{
			logger.info("ERROR: expire_date not valid date range");
		}
		queryString.append(queryString_where7);
		
		
		
		String queryString_where8 = "";
		if(strModifyDatetimeFrom.equals("")&&strModifyDatetimeTo.equals("")){
			queryString_where8 = " ";
		}else if(!strModifyDatetimeFrom.equals("")&&strModifyDatetimeTo.equals("")){
			queryString_where8 = " and date(modify_date) >= '"+where8a+"' ";
		}else if(strModifyDatetimeFrom.equals("")&&!strModifyDatetimeTo.equals("")){
			queryString_where8 = " and date(modify_date) <= '"+where8b+"' ";
		}else if(!strModifyDatetimeFrom.equals("")&&!strModifyDatetimeTo.equals("")){
			int dateFlag = strModifyDatetimeFrom.compareTo(strModifyDatetimeTo);
			if(dateFlag<0){
				queryString_where8 = " and (date(modify_date) between adddate('"+where8a+"', -1) and '"+where8b+"' ) ";
			}else if(dateFlag==0){
				queryString_where8 = " and (date(modify_date) =  '"+where8a+"' ) ";
			}else{
				queryString_where8 = " and (date(modify_date) between adddate('"+where8b+"', -1) and '"+where8a+"' ) ";
			}
			
		}else{
			logger.info("ERROR: modify_date not valid date range");
		}
		queryString.append(queryString_where8);
		
				
		queryString.append(where9==0?" ":" and ad_status = "+where9+" ");
		logger.info("QueryString = "+ queryString.toString());
		
		List<AdPost> listAdPost = adPostService.getAdPostByFilter(queryString.toString());
		logger.info("Length of listAdPost entries = "+ listAdPost.size());
		
		
		String[][] data = getData(listAdPost, ACTION_EDIT);
		
		ModelAndView mav = new ModelAndView();
		
		//data
		Map<String, Object> model = mav.getModel();
		
		model.put("draw", new Integer(1));
		model.put("recordsTotal", new Integer(5));
		model.put("recordsFiltered", new Integer(5));
		model.put("data", data);
		model.put("customActionStatus","OK");
		model.put("customActionMessage","OK");
		
		logger.info("leaving /adpost/searchFilterData");
		
		return model;
	}
	
	
	
	@RequestMapping(value="/adpost/create",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> createAdPost(@RequestParam String itemJSONString) {
		
		logger.info("entering /ad/adpost/create");
		
		/* initial settings */
		ModelAndView mav = new ModelAndView();
		
		//set model
        JSONObject jsonObj= new JSONObject(itemJSONString);
        
        AdPost adpost = new AdPost();
        adpost.setAdType(jsonObj.getInt("adType"));
        adpost.setAcctName(jsonObj.getString("acctName"));
        adpost.setAdUUID(UUIDHelper.getUUID());
        adpost.setAdTitle(jsonObj.getString("adTitle"));
        adpost.setAdText(jsonObj.getString("adText"));
        adpost.setAdImage("image");						//TODO
        adpost.setAdLink("link");						//TODO
        adpost.setAdUrl("url");							//TODO
        adpost.setCreateDate(new Date());				//TODO
        adpost.setPostDate(new Date());					//TODO
        adpost.setExpireDate(new Date());				//TODO
        adpost.setModifyDate(new Date());				//TODO
        //missing author
        adpost.setAdStatus(jsonObj.getInt("adpostStatus"));
          
        logger.info(adpost);
          
		/* business logic*/
        adPostService.createAdPost(adpost);
		
		/* assemble model and view */
//      model.put("news", news);
        Map<String,Object> model = mav.getModel();
        
        logger.info("exiting... /ad/adpost/create");
		return model;		
	}
	
	@RequestMapping(value="/adpost/update",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateAdPost(@RequestParam String itemJSONString) {
		
		logger.info("entering /ad/adpost/update");
		
		/* initial settings */
		ModelAndView mav = new ModelAndView();
		
		//set model
        JSONObject jsonObj= new JSONObject(itemJSONString);
        
        AdPost adpost = new AdPost();
        adpost.setAdType(jsonObj.getInt("adType"));
        adpost.setAcctName(jsonObj.getString("acctName"));
        adpost.setAdUUID(jsonObj.getString("adUUID"));
        adpost.setAdTitle(jsonObj.getString("adTitle"));
        adpost.setAdText(jsonObj.getString("adText"));
        adpost.setAdImage(jsonObj.getString("adImage"));						//TODO
        adpost.setAdLink(jsonObj.getString("adLink"));						//TODO
        adpost.setAdUrl(jsonObj.getString("adUrl"));							//TODO
        adpost.setAdStatus(jsonObj.getInt("adpostStatus"));
        //adpost.setCreateDate(new Date());				//TODO
        adpost.setPostDate(new Date());					//TODO
        adpost.setExpireDate(new Date());				//TODO
        adpost.setModifyDate(new Date());				//TODO
        //missing author
          
        logger.info(adpost);
          
		/* business logic*/
        adPostService.updateAdPost(adpost);
		
		/* assemble model and view */
//      model.put("news", news);
        Map<String,Object> model = mav.getModel();
        
        logger.info("exiting... /ad/adpost/update");
		return model;		
	}
	
	
	@RequestMapping(value="/adpost/updateGroup",produces="application/json")
	@ResponseBody
	public Map<String,Object> updateAdPostGroup(
			@RequestParam String adUUIDArray,
			@RequestParam int adStatus
			) {
		
		logger.info("entering /adpost/updateGroup");
		
		/* initial settings */
		ModelAndView mav = new ModelAndView();
		
		//set model
        Map<String, Object> model = mav.getModel();
   
        List<AdPost> adpostList = new ArrayList<AdPost>();
        String[] adUUIDs = adUUIDArray.split(",");
        int adUUIDLength = adUUIDs.length;
        
        for(int i=0; i<adUUIDLength; i++){
        	 AdPost adpost = new AdPost();
        	 adpost.setAdUUID(adUUIDs[i]);
        	 adpost.setAdStatus(adStatus);
             adpostList.add(adpost);
             adpost = null;
        }
        
        logger.info("adpostList size="+adpostList.size());
        logger.info("adpostList ="+adpostList.toString());
        
		/* business logic*/
        adPostService.updateAdPostGroup(adpostList);
        
		
		/* assemble model and view */
		logger.info("leaving /adpost/updateGroup");
		return model;		
	}
	
	
	
	private String[][] getData(List<AdPost> listObj, String actionName){
		int entryLength = listObj.size();
//		final int COLUMN_NUM = 14;
		final int COLUMN_NUM = 11;
		String[][] data = new String[entryLength][COLUMN_NUM];
		
		String field0 = "";	//check box
		String field1 = "";	//global id, adPostId
		String field2 = "";	//adText -> adTitle
		String field3 = "";	//adImage
		String field4 = "";	//adLink
		String field5 = "";	//adUrl
		
		String field6 = "";	//adType
		String field7 = "";	//adOwnerId
		
		String field8 = "";	//create date
		String field9 = ""; //post date
		String field10 = ""; //expire date
		String field11 = ""; //modify date
		
		String field12 = "";	//adStatus
		String field13 = "";	//action
		
		for(int i=0; i<entryLength ; i++){			
//			field0 = "<input type='checkbox' name='id[]' value="+listObj.get(i).getGlobalId()+">";
			field0 = "<input type='checkbox' name='id[]' value="+listObj.get(i).getAdUUID()+">";
			field1 = listObj.get(i).getAdUUID()+"";
//			field2 = listObj.get(i).getAdText();
			field2 = listObj.get(i).getAdTitle();
//			field3 = listObj.get(i).getAdImage();//.substring(0,15);		//TODO
//			field4 = listObj.get(i).getAdLink();//.substring(0,15);			//TODO
//			field5 = listObj.get(i).getAdUrl();//.substring(0,15);			//TODO
			
//			field6 = listObj.get(i).getAdOwnerId()+"";
			field6 = listObj.get(i).getAcctName();
			
			String strObjectClass = (listObj.get(i).getAdType())+"";
			field7 = getObjectClass(strObjectClass);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			field8 = sdf.format(listObj.get(i).getCreateDate())+"";
			field9 = sdf.format(listObj.get(i).getPostDate())+"";
			field10 = sdf.format(listObj.get(i).getExpireDate())+"";
			field11 = sdf.format(listObj.get(i).getModifyDate())+"";
			
			
			
			int intAdStatus = listObj.get(i).getAdStatus();
			String[] adPostStatusPair = getStatusPair(intAdStatus);
			String adPostStatusKey = adPostStatusPair[0];
			String adStatus = adPostStatusPair[1];
			field12 = "<span class='label label-sm label-"+adPostStatusKey+"'>"+adStatus+"</span>";
			field13 = "<a href='/acp/ad/adpost/"+getAction(actionName)+"?adUUID="+field1+"' class='btn btn-xs default btn-editable'><i class='fa fa-pencil'></i> "+actionName+"</a>";
			
			//logger.info("field8="+field8);
			
//			data[i][0] = field0;
//			data[i][1] = field1;
//			data[i][2] = field2;
//			data[i][3] = field3;
//			data[i][4] = field4;
//			data[i][5] = field5;
//			data[i][6] = field6;
//			data[i][7] = field7;
//			data[i][8] = field8;
//			data[i][9] = field9;
//			data[i][10] = field10;
//			data[i][11] = field11;
//			data[i][12] = field12;
//			data[i][13] = field13;
			
			data[i][0] = field0;
			data[i][1] = field1;
			data[i][2] = field2;
			
			data[i][3] = field6;
			data[i][4] = field7;
			data[i][5] = field8;
			data[i][6] = field9;
			data[i][7] = field10;
			data[i][8] = field11;
			data[i][9] = field12;
			data[i][10] = field13;
		}
		
		return data;
	}


	private String getObjectClass(String strObjectClass){
		
		int intObjectClass = Integer.parseInt(strObjectClass);
		String objectClass = "";
		switch(intObjectClass){
			case AdPostType.TEXT_BASED:
				objectClass = "文本广告";
				break;
			case AdPostType.IMAGE_BASED:
				objectClass = "图片广告";
				break;
			case AdPostType.LINK_BASED:
				objectClass = "链接广告";
				break;
			default: 
				objectClass = "未知类型";
				break;
		}
		
		return objectClass;
	}
	
	private String[] getStatusPair(int intEventStatus){
		String[] statusPair = new String[2];
		
		String status = "";
		String statusKey = "";
		switch(intEventStatus){
			case AdPostStatus.PUBLISHED: 
				status = "已发布";
				statusKey = "success";
				break;
			case AdPostStatus.WAIT_TO_POST: 
				status = "待发布";
				statusKey = "warning";
				break;
			case AdPostStatus.DELETED: 
				status = "已删除";
				statusKey = "default";
				break;
			case AdPostStatus.OUT_OF_DATE: 
				status = "已过期";
				statusKey = "info";
				break;
			case AdPostStatus.SUSPENDED: 
				status = "审查中";
				statusKey = "danger";
				break;
			default: 
				break;
		}
		
		statusPair[0]=statusKey;
		statusPair[1]=status;
		
		
		return statusPair;
	}
	
	private String getAction(String actionName){
		String action = "";
		switch(actionName){
		case ACTION_EDIT:
			action = "edit.html";	//adPost Edit
			break;
		case ACTION_DELETE:
			action = "delete.html";	//adPostDelete
			break;
		}
		return action;
	}
	
	
	
}

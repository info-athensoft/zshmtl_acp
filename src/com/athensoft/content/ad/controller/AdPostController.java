package com.athensoft.content.ad.controller;


import java.text.SimpleDateFormat;
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
import com.athensoft.content.ad.entity.AdPostType;
import com.athensoft.content.ad.service.AdPostService;
import com.athensoft.content.event.controller.NewsAcpController;
import com.athensoft.content.event.entity.EventMedia;
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
			field0 = "<input type='checkbox' name='id[]' value="+listObj.get(i).getGlobalId()+">";
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
			case News.PUBLISHED: 
				status = "已发布";
				statusKey = "success";
				break;
			case News.WAIT_TO_POST: 
				status = "待发布";
				statusKey = "warning";
				break;
			case News.DELETED: 
				status = "已删除";
				statusKey = "default";
				break;
			case News.OUT_OF_DATE: 
				status = "已过期";
				statusKey = "info";
				break;
			case News.SUSPENDED: 
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

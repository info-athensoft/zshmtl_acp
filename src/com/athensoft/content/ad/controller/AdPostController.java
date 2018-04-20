package com.athensoft.content.ad.controller;


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.athensoft.content.ad.entity.AdPost;
import com.athensoft.content.ad.entity.AdPostType;
import com.athensoft.content.ad.service.AdPostService;
import com.athensoft.content.event.controller.NewsAcpController;
import com.athensoft.content.event.entity.News;


@Controller
@RequestMapping("/ad")
public class AdPostController {
	private static final Logger logger = Logger.getLogger(NewsAcpController.class);
	
	private static final String ACTION_EDIT = "Edit";
	private static final String ACTION_DELETE = "Delete";
	
	@Autowired
	private  AdPostService adPostService;
	
	@RequestMapping(value="/adpost_list.html")
	public String gotoAdPostList(){
		
		String viewName = "ad/adpost_list";
		
		return viewName;
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
	
	
	private String[][] getData(List<AdPost> listObj, String actionName){
		int entryLength = listObj.size();
		final int COLUMN_NUM = 14;
		String[][] data = new String[entryLength][COLUMN_NUM];
		
		String field0 = "";	//check box
		String field1 = "";	//global id, adPostId
		String field2 = "";	//adText
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
			field1 = listObj.get(i).getGlobalId()+"";
			field2 = listObj.get(i).getAdText();
			field3 = listObj.get(i).getAdImage().substring(0,15);		//TODO
			field4 = listObj.get(i).getAdLink().substring(0,15);		//TODO
			field5 = listObj.get(i).getAdUrl().substring(0,15);			//TODO
			
			field6 = listObj.get(i).getAdOwnerId()+"";
			
			String strObjectClass = (listObj.get(i).getAdType())+"";
			field7 = getObjectClass(strObjectClass);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			field8 = sdf.format(listObj.get(i).getCreateDate())+"";
			field9 = sdf.format(listObj.get(i).getPostDate())+"";
			field10 = sdf.format(listObj.get(i).getExpireDate())+"";
			field11 = sdf.format(listObj.get(i).getModifyDate())+"";
			
			
			
			int intAdStatus = listObj.get(i).getAdStatus();
			String[] adPostStatusPair = getEventStatusPair(intAdStatus);
			String adPostStatusKey = adPostStatusPair[0];
			String adStatus = adPostStatusPair[1];
			field12 = "<span class='label label-sm label-"+adPostStatusKey+"'>"+adStatus+"</span>";
			field13 = "<a href='/acp/ad/"+getAction(actionName)+"?adPostId="+field1+"' class='btn btn-xs default btn-editable'><i class='fa fa-pencil'></i> "+actionName+"</a>";
			
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
			data[i][11] = field11;
			data[i][12] = field12;
			data[i][13] = field13;
		}
		
		return data;
	}


	private String getObjectClass(String strObjectClass){
		
		int intObjectClass = Integer.parseInt(strObjectClass);
		String objectClass = "";
		switch(intObjectClass){
			case AdPostType.TEXT_BASED:
				objectClass = "Text-based";
				break;
			case AdPostType.IMAGE_BASED:
				objectClass = "Image-based";
				break;
			case AdPostType.LINK_BASED:
				objectClass = "Video-based";
				break;
			default: 
				objectClass = "Unknown";
				break;
		}
		
		return objectClass;
	}
	
	private String[] getEventStatusPair(int intEventStatus){
		String[] eventStatusPair = new String[2];
		
		String eventStatus = "";
		String eventStatusKey = "";
		switch(intEventStatus){
			case News.PUBLISHED: 
				eventStatus = "Published";
				eventStatusKey = "success";
				break;
			case News.WAIT_TO_POST: 
				eventStatus = "Wait to post";
				eventStatusKey = "warning";
				break;
			case News.DELETED: 
				eventStatus = "Deleted";
				eventStatusKey = "default";
				break;
			case News.OUT_OF_DATE: 
				eventStatus = "Out of date";
				eventStatusKey = "info";
				break;
			case News.SUSPENDED: 
				eventStatus = "Suspended";
				eventStatusKey = "danger";
				break;
			default: 
				break;
		}
		
		eventStatusPair[0]=eventStatusKey;
		eventStatusPair[1]=eventStatus;
		
		
		return eventStatusPair;
	}
	
	private String getAction(String actionName){
		String action = "";
		switch(actionName){
		case ACTION_EDIT:
			action = "adPostEdit";
			break;
		case ACTION_DELETE:
			action = "adPostDelete";
			break;
		}
		return action;
	}
	
	
	
}

package com.athensoft.content.ad.controller;

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

import com.athensoft.content.ad.entity.AdAction;
import com.athensoft.content.ad.entity.AdPost;
import com.athensoft.content.ad.service.AdPostService;
import com.athensoft.util.id.UUIDHelper;


@Controller
@RequestMapping("/ad/adpost")
public class AdPostController {
	private static final Logger logger = Logger.getLogger(AdPostController.class);
	
	@Autowired
	private  AdPostService adPostService;
	
	@RequestMapping(value="/list.html")
	public String gotoAdPostList(){
		
		String viewName = "ad/adpost_list";
		
		return viewName;
	}
	
	@RequestMapping(value="/create.html")
	public String gotoAdPostCreate(){
		
		String viewName = "ad/adpost_create";
		
		return viewName;
	}
	
	@RequestMapping(value="/edit.html")
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
	
	
	@RequestMapping(value="/list",produces="application/json")
	@ResponseBody
	public Map<String,Object> getDataAdPostList(){
		logger.info("entering... /ad/adpost/list");
		
		ModelAndView mav = new ModelAndView();
		
		//data
		List<AdPost> listAdPost = adPostService.getAdPostList();
		logger.info("Length of adpost entries: "+ listAdPost.size());
		
		String[][] data = adPostService.getData(listAdPost, AdAction.EDIT);
		
		Map<String, Object> model = mav.getModel();
		
		model.put("draw", new Integer(1));
		model.put("recordsTotal", new Integer(5));
		model.put("recordsFiltered", new Integer(5));
		model.put("data", data);
		model.put("customActionStatus","OK");
		model.put("customActionMessage","Data loaded");
		
		logger.info("leaving... /ad/adpost/list");
		return model;
	}
	
	
	@RequestMapping(value="/searchbyfilter",produces="application/json")
	@ResponseBody
	public Map<String, Object> getDataSearchByFilter(@RequestParam String itemJSONString){
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
		
		
		String[][] data = adPostService.getData(listAdPost, AdAction.EDIT);
		
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
	
	
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
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
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
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
        Map<String,Object> model = mav.getModel();
        
        logger.info("exiting... /ad/adpost/update");
		return model;		
	}
	
	
	@RequestMapping(value="/updateGroup",produces="application/json")
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
        	 adpost.setModifyDate(new Date());
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
	
}

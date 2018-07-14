package com.athensoft.content.ad.controller;

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
import com.athensoft.content.ad.entity.AdRecommend;
import com.athensoft.content.ad.service.AdPostService;
import com.athensoft.content.ad.service.AdRecommendService;


@Controller
@RequestMapping("/ad/adrcmd")
public class AdRecommendController {
	private static final Logger logger = Logger.getLogger(AdRecommendController.class);
	
	@Autowired
	private  AdRecommendService adRecommendService;
	
	@Autowired
	private  AdPostService adPostService;
	
	@RequestMapping(value="/list.html")
	public String gotoAdRecommendList(){
		logger.info("entering... /ad/adrcmd/list.html");
		String viewName = "ad/adrecommend_list";
		
		logger.info("exit... /ad/adrcmd/list.html");
		return viewName;
	}
	
	@RequestMapping(value="/list",produces="application/json")
	@ResponseBody
	public Map<String,Object> getDataAdRecommendList(){
		logger.info("entering... /ad/adrcmd/list");

		//data
		List<AdRecommend> listAdRecommend = adRecommendService.getAll();
		logger.info("Length of listAdRecommend entries: "+ listAdRecommend.size());
		
		String[][] data = adRecommendService.getData(listAdRecommend, AdAction.EDIT);
		
		//
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = mav.getModel();
		
		model.put("draw", new Integer(1));
		model.put("recordsTotal", new Integer(5));
		model.put("recordsFiltered", new Integer(5));
		model.put("data", data);
		model.put("customActionStatus","OK");
		model.put("customActionMessage","Data loaded");
		
		logger.info("leaving... /ad/adrcmd/list");
		return model;
	}
	
	@RequestMapping(value="/create.html")
	public String gotoAdRecommendCreate(){
		
		String viewName = "ad/adrecommend_create";
		
		return viewName;
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> createAdRecommend(@RequestParam String itemJSONString) {
		
		logger.info("entering /ad/adrcmd/create");
		//set model
        JSONObject jsonObj= new JSONObject(itemJSONString);
        
        AdRecommend adrcmd = new AdRecommend();
        adrcmd.setAdUUID(jsonObj.getString("adUUID"));
        adrcmd.setPageId(jsonObj.getInt("pageId"));
        adrcmd.setPageName(jsonObj.getString("pageName"));
        adrcmd.setRcmdRank(jsonObj.getInt("rcmdRank"));
        adrcmd.setRcmdStatus(jsonObj.getInt("rcmdStatus"));
        
        adrcmd.setRcmdScore(0.0);	//default
          
        logger.info(adrcmd);
          
		/* business logic*/
        adRecommendService.createAdRecommend(adrcmd);
		
        
        /* initial settings */
		ModelAndView mav = new ModelAndView();
		
		/* assemble model and view */
        Map<String,Object> model = mav.getModel();
        model.put("adRecommend", adrcmd);
        
        logger.info("exiting... /ad/adrcmd/create");
		return model;		
	}
	
	@RequestMapping(value="/edit.html")
	public ModelAndView gotoAdRecommendEdit(@RequestParam int globalId){
		logger.info("entering /ad/adrcmd/edit.html");
		
		AdRecommend adrcmd = new AdRecommend();
		adrcmd = adRecommendService.getAdRecommendByGlobalId(globalId);
		
		logger.info("adrcmd = "+adrcmd.toString());
		
		 /* initial settings */
		ModelAndView mav = new ModelAndView();
		
		/* assemble model and view */
        Map<String,Object> model = mav.getModel();
        model.put("adRecommend", adrcmd);
        
        String viewName = "ad/adrecommend_edit";
        mav.setViewName(viewName);
        
        logger.info("exiting... /ad/adrcmd/edit.html");
		return mav;
	}
}

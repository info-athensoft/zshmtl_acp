package com.athensoft.content.ad.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.athensoft.content.ad.entity.AdAction;
import com.athensoft.content.ad.entity.AdRecommend;
import com.athensoft.content.ad.service.AdRecommendService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/ad/adrcmd")
@Log4j
public class AdRecommendController {

	@Autowired
	private AdRecommendService adRecommendService;

	@RequestMapping(value = "/list.html")
	public String gotoListAdRecommend() {
		String viewName = "ad/adrecommend_list";
		return viewName;
	}

	@RequestMapping(value = "/create.html")
	public String gotoCreateAdRecommend() {
		String viewName = "ad/adrecommend_create";
		return viewName;
	}

	@RequestMapping(value = "/edit.html")
	public ModelAndView gotoEditAdRecommend(@RequestParam int globalId) {
		log.info("entering /ad/adrcmd/edit.html");
	
		AdRecommend adrcmd = new AdRecommend();
		adrcmd = adRecommendService.getAdRecommendByGlobalId(globalId);
		log.info("adrcmd = " + adrcmd.toString());
	
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = mav.getModel();
		model.put("adRecommend", adrcmd);
	
		String viewName = "ad/adrecommend_edit";
		mav.setViewName(viewName);
	
		log.info("exiting... /ad/adrcmd/edit.html");
		return mav;
	}

	@RequestMapping(value = "/delete.html")
	public ModelAndView gotoDeleteAdRecommend(@RequestParam int globalId) {
		log.info("entering /ad/adrcmd/delete.html");
	
		AdRecommend adrcmd = new AdRecommend();
		adrcmd = adRecommendService.getAdRecommendByGlobalId(globalId);
		log.info("adrcmd = " + adrcmd.toString());
	
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = mav.getModel();
		model.put("adRecommend", adrcmd);
	
		String viewName = "ad/adrecommend_delete";
		mav.setViewName(viewName);
	
		log.info("exiting... /ad/adrcmd/delete.html");
		return mav;
	}

	@RequestMapping(value = "/list", produces = "application/json")
	@ResponseBody
	public Map<String, Object> getDataListAdRecommend() {
		log.info("entering... /ad/adrcmd/list");

		// data
		List<AdRecommend> listAdRecommend = adRecommendService.getAll();
		log.info("Length of listAdRecommend entries: " + listAdRecommend.size());

		String[] actions = { AdAction.VIEW, AdAction.EDIT, AdAction.DELETE };
		String[][] data = adRecommendService.getData(listAdRecommend, actions);

		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = mav.getModel();
		model.put("draw", new Integer(1));
		model.put("recordsTotal", new Integer(5));
		model.put("recordsFiltered", new Integer(5));
		model.put("data", data);
		model.put("customActionStatus", "OK");
		model.put("customActionMessage", "Data loaded");

		log.info("leaving... /ad/adrcmd/list");
		return model;
	}

	@RequestMapping(value = "/search", produces = "application/json")
	@ResponseBody
	public Map<String, Object> getDataSearchAdRecommend(@RequestParam String jsonObjString) {
		log.info("entering... /ad/adrcmd/search");
	
		JSONObject jobj = new JSONObject(jsonObjString);
	
		String where1 = jobj.getString("adUUID");
		int where2 = jobj.getInt("pageId");
		String where3 = jobj.getString("pageName");
		int where4 = jobj.getInt("rcmdRank"); // display seq no.
		int where5 = jobj.getInt("rcmdStatus"); // show or off
	
		/* construct query string */
		StringBuffer queryString = new StringBuffer();
		queryString.append(where1.length() == 0 ? " " : " and ad_uuid like '%" + where1 + "%' ");
		queryString.append(where2 == 0 ? " " : " and page_id = " + where2 + " ");
		queryString.append(where3.length() == 0 ? " " : " and page_name like '%" + where3 + "%' ");
		queryString.append(where4 == 0 ? " " : " and rcmd_rank = " + where4 + " ");
		queryString.append(where5 == 0 ? " " : " and rcmd_status = " + where5 + " ");
	
		log.info("QueryString = " + queryString.toString());
	
		List<AdRecommend> listAdRecommend = adRecommendService.getAdRecommendByFilter(queryString.toString());
		if (null != listAdRecommend) {
			log.info("Length of listAdRecommend entries = " + listAdRecommend.size());
		} else {
			listAdRecommend = new ArrayList<AdRecommend>();
		}
	
		String[] actions = { AdAction.VIEW, AdAction.EDIT, AdAction.DELETE };
		String[][] data = adRecommendService.getData(listAdRecommend, actions);
	
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = mav.getModel();
		model.put("draw", new Integer(1));
		model.put("recordsTotal", new Integer(5));
		model.put("recordsFiltered", new Integer(5));
		model.put("data", data);
		model.put("customActionStatus", "OK");
		model.put("customActionMessage", "OK");
	
		log.info("leaving... /ad/adrcmd/search");
		return model;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createAdRecommend(@RequestParam String jsonObjString) {
		log.info("entering /ad/adrcmd/create");

		JSONObject jsonObj = new JSONObject(jsonObjString);

		AdRecommend adrcmd = new AdRecommend();
		adrcmd.setAdUUID(jsonObj.getString("adUUID"));
		adrcmd.setPageId(jsonObj.getInt("pageId"));
		adrcmd.setPageName(jsonObj.getString("pageName"));
		adrcmd.setRcmdRank(jsonObj.getInt("rcmdRank"));
		adrcmd.setRcmdStatus(jsonObj.getInt("rcmdStatus"));
		adrcmd.setRcmdScore(0.0); // default

		log.info(adrcmd);

		/* business logic */
		adRecommendService.createAdRecommend(adrcmd);

		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = mav.getModel();
		model.put("adRecommend", adrcmd);

		log.info("exiting... /ad/adrcmd/create");
		return model;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public void updateAdRecommend(@RequestBody AdRecommend adrcmd) {
		log.info("entering... /ad/adrcmd/update");
		adRecommendService.updateAdRecommend(adrcmd);
		log.info("exiting... /ad/adrcmd/update");
		return;
	}
	
	@RequestMapping(value = "/updategroup", method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void updateGroupAdPost(@RequestBody AdRecommendGroup adRecommendGroup) {
		log.info("entering... /ad/adrcmd/updategroup");
		
		List<String> adUUIDs = adRecommendGroup.getAdUUIDArray();
		int adStatus = adRecommendGroup.getAdStatus();
		
		int adUUIDLength = adUUIDs.size();		
		List<AdRecommend> adRecommendList = new ArrayList<AdRecommend>();

		for (int i = 0; i < adUUIDLength; i++) {
			AdRecommend x = new AdRecommend();
			x.setAdUUID(adUUIDs.get(i));
			x.setRcmdStatus(adStatus);
			adRecommendList.add(x);
			x = null;
		}

		log.info("adRecommendList size=" + adRecommendList == null ? "NULL" : adRecommendList.size());

		/* business logic */
		adRecommendService.updateGroupAdRecommend(adRecommendList);


		log.info("leaving... /ad/adrcmd/updategroup");
		return ;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public void deleteAdRecommend(@RequestBody AdRecommend adrcmd) {
		log.info("entering... /ad/adrcmd/delete");
		adRecommendService.deleteAdRecommend(adrcmd);
		log.info("exiting... /ad/adrcmd/delete");
		return;
	}
	
	public static class AdRecommendGroup{
		private List<String> adUUIDArray;
		private int adStatus;
		
		public List<String> getAdUUIDArray() {
			return adUUIDArray;
		}
		public void setAdUUIDArray(List<String> adUUIDArray) {
			this.adUUIDArray = adUUIDArray;
		}
		public int getAdStatus() {
			return adStatus;
		}
		public void setAdStatus(int adStatus) {
			this.adStatus = adStatus;
		}
		@Override
		public String toString() {
			return "AdPostGroup [adUUIDArray=" + adUUIDArray + ", adStatus=" + adStatus + "]";
		}
	}
}

package com.athensoft.content.ad.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.athensoft.content.ad.entity.AdAction;
import com.athensoft.content.ad.entity.AdRequest;
import com.athensoft.content.ad.service.AdRequestService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/ad/adrequest")
@Log4j
public class AdRequestController {

	@Autowired
	private AdRequestService adRequestService;

	@RequestMapping(value = "/list", produces = "application/json")
	@ResponseBody
	public Map<String, Object> getDataListAdRequest() {
		log.info("entering... /ad/adrequest/list");

		// data
		List<AdRequest> listAdRequest = adRequestService.getAllAdRequest();
		log.info("Length of adrequest entries: " + listAdRequest == null ? "NULL" : listAdRequest.size());

		String[][] data = adRequestService.getData(listAdRequest, AdAction.EDIT);

		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = mav.getModel();
		model.put("draw", new Integer(1));
		model.put("recordsTotal", new Integer(5));
		model.put("recordsFiltered", new Integer(5));
		model.put("data", data);
		model.put("customActionStatus", "OK");
		model.put("customActionMessage", "Data loaded");

		log.info("leaving... /ad/adrequest/list");
		return model;
	}

}

package com.athensoft.content.ad.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/ad")
public class AdRecommendController {
	private static final Logger logger = Logger.getLogger(AdRecommendController.class);
	
	@RequestMapping(value="/adrecommend_list.html")
	public String gotoAdPostList(){
		
		String viewName = "ad/adrecommend_list";
		
		return viewName;
	}
}

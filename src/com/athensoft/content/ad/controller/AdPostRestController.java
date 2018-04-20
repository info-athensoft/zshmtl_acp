package com.athensoft.content.ad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.athensoft.content.ad.entity.AdPost;
import com.athensoft.content.ad.service.AdPostService;

@Controller
@RequestMapping("/ad")
public class AdPostRestController {
	
	@Autowired
	private  AdPostService adPostService; 
	
	@RequestMapping(value="/adposts", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<AdPost> getDataAdPostList(){
		return adPostService.getAdPostList();
	}
	
}

package com.athensoft.ecomm.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping("/")
	public String gotoHome(){
		return "index";
	}
	
	@RequestMapping("/index")
	public String gotoHome2(){
		return "index";
	}
	
	@RequestMapping("/test")
	public String gotoTest(){
		return "test";
	}
	
	
}

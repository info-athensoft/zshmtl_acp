package com.athensoft.global.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/support")
public class SupportController {
	
	@RequestMapping("/contactus.html")
	public String gotoContactus(){
		return "support/contactus";
	}
}

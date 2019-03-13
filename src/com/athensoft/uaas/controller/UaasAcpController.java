package com.athensoft.uaas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UaasAcpController {

	@RequestMapping("/login")
	public String gotoLogin(){
		return "uaas/login_soft";
	}
	
	@RequestMapping("/logout")
	public String gotoLogout(){
		return "uaas/login_soft";
	}
}

package com.athensoft.global.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The global controller
 * @author Athens
 *
 */
@Controller
public class TestController {
	
	/**
	 * go to home page
	 * @return
	 */
	@RequestMapping("/test")
	public String gotoHome(){
		return "events/ecommerce_products";
	}
	
	
}

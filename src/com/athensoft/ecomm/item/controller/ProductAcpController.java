package com.athensoft.ecomm.item.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductAcpController {
	
	@RequestMapping(value="/item/productEdit")
	public String gotoProductEdit(){
		String viewName = "item/item_product_edit";
		return viewName;
	}
}

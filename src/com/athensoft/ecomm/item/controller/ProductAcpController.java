package com.athensoft.ecomm.item.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductAcpController {
	
	@RequestMapping(value="/item/productList")
	public String gotoProductList(){
		String viewName = "item/item_products";
		return viewName;
	}
	
	@RequestMapping(value="/item/productEdit")
	public String gotoProductEdit(){
		String viewName = "item/item_product_edit";
		return viewName;
	}
	
	@RequestMapping(value="/item/categoryList")
	public String gotoCategoryList(){
		String viewName = "item/item_categories";
		return viewName;
	}
	
	@RequestMapping(value="/item/categoryEdit")
	public String gotoCategoryEdit(){
		String viewName = "item/item_category_edit";
		return viewName;
	}
}

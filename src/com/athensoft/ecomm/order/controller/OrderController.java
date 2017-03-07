package com.athensoft.ecomm.order.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.athensoft.ecomm.order.entity.Order;
import com.athensoft.ecomm.order.service.OrderService;

@Controller
public class OrderController {
	
	private OrderService orderService;
	
	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@RequestMapping(value="/ecomm/orders")
	public String gotoOrders(){
		String viewName = "order/ecommerce_orders";
		return viewName;
	}
	
	@RequestMapping("/ecomm/ordersview")
	public String gotoOrdersView(){
		String viewName = "order/ecommerce_orders_view";
		return viewName;
	}
	
	
	@RequestMapping(value="/orders")
	public ModelAndView getAllOrders(){
		ModelAndView mav = new ModelAndView();
		
		List<Order> orders = orderService.getAllOrders();
		System.out.println("size of orders:\t"+orders.size());
		
		
		Map<String,Object> data = mav.getModel();
		data.put("orderList", orders);		
		mav.setViewName("order/ecommerce_orders");
		return mav;
	}
	
	
	/*
	@RequestMapping(value="/orders",method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public List<Order> getAllOrders(){
		
		List<Order> orders = orderService.getAllOrders();
		System.out.println("size of orders:\t"+orders.size());
		return orders;
	}
	
	@RequestMapping(value="/orders",method=RequestMethod.GET,produces="application/json")
	@ResponseBody
	public List<OrderHead> getAllOrders2(){
		
		List<Order> orders = orderService.getAllOrders();
		System.out.println("size of orders:\t"+orders.size());
		
		List<OrderHead> orderheads = new ArrayList<OrderHead>();
		for(Order order:orders){
			OrderHead oh = new OrderHead();
			oh.setOrderId(order.getOrderId());
			oh.setOrderNo(order.getOrderNo());
			oh.setOrderDateTime(order.getOrderDateTime());
			oh.setGrandTotalAmount(order.getGrandTotalAmount());
			oh.setOrderStatus(order.getOrderStatus());
			oh.setPaymentMethod(order.getPaymentMethod());
			orderheads.add(oh);
		}
		return orderheads;
	}
	*/
	
	
	@RequestMapping(value="/orders/{orderId}")
	public ModelAndView getOrderDetail(@PathVariable long orderId){
		ModelAndView mav = new ModelAndView();
		
		Order order = orderService.getOrderByOrderId(orderId);
		System.out.println("getOrderDetail()\n"+order.toString());
		
		
		Map<String,Object> data = mav.getModel();
		data.put("order", order);		
		mav.setViewName("ecommerce_orders_view");
		return mav;
	}
	
	
	
	
}

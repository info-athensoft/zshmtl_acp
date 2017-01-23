package com.athensoft.content.event.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.athensoft.content.event.entity.Event;
import com.athensoft.content.event.entity.TestNews;
import com.athensoft.content.event.service.NewsService;

@Controller
public class NewsAcpController {
	
	private NewsService newsService;
	
	@Autowired
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	
	
	@RequestMapping(value="/content/eventsNewsList")
	public String gotoNewsList(){
		String viewName = "events/event_news_list";
		return viewName;
	}
	
	@RequestMapping(value="/content/eventsNewsListData",produces="application/json")
	@ResponseBody
	public Map<String,Object> getDataNewsList(){
		ModelAndView mav = new ModelAndView();
		
		//String viewName = "events/news";
		//mav.setViewName(viewName);
		
		//data
		List<Event> listNews = newsService.getAllNews();
		
		List<TestNews> listTestNews = new ArrayList<TestNews>();
		
		
		for(Event news : listNews){
			TestNews tn = new TestNews();
			tn.setAuthor(news.getAuthor());
			tn.setEventClass(news.getEventClass());
			tn.setEventStatus(news.getEventStatus());
			tn.setEventUUID(news.getEventUUID());
			tn.setGlobalId(news.getGlobalId());
			tn.setPostDatetime(news.getPostDatetime());
			tn.setTitle(news.getTitle());
			tn.setViewNum(news.getViewNum());
			listTestNews.add(tn);
		}
		
		
		
		/*
		for(Event news : listNews){
			String eventUUID = news.getEventUUID();
			List<EventMedia> listEventMedia = eventMediaService.getEventMediaByEventUUID(eventUUID);
			news.setListEventMedia(listEventMedia);
			news.setPrimaryEventMedia();
			
			List<EventTag> listEventTag = eventTagService.getEventTagByEventUUID(eventUUID);
			news.setListEventTag(listEventTag);
		}*/
		
		Map<String, Object> data = mav.getModel();
		data.put("listNews", listTestNews);
//		data.put("listNews", listNews);
		
		System.out.println("/content/eventsNewsListData");
		
		return data;
	}
	
	
	

	@RequestMapping(value="/content/eventsNewsEdit")
	public String gotoNewsEdit(){
		String viewName = "events/event_news_edit";
		return viewName;
	}

	/*
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
	*/
	
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
	
	/*
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
	
	*/
	
	
}

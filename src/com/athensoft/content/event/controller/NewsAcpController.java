package com.athensoft.content.event.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.athensoft.content.event.entity.Event;
import com.athensoft.content.event.service.NewsService;

@Controller
public class NewsAcpController {
	
	private static final Logger logger = Logger.getLogger(NewsAcpController.class);
	
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
		logger.info("Length of news entries: "+ listNews.size());
		
		
		//DatatableObject datatableObj= new DatatableObject();
		int entryLength = listNews.size();
		String[][] data = new String[entryLength][9];
		
//		datatableObj.setCustomActionStatus("OK");   
//		datatableObj.setCustomActionMessage("");
//		datatableObj.setDraw(1);
//		datatableObj.setRecordsTotal(3);
//		datatableObj.setRecordsFiltered(3);
		//datatableObj.setiTotalDisplayRecords("100");
		//datatableObj.setiTotalRecords("100");

		
		String field1 = "";
		String field8 = "";
		String field9 = "";
		
		//List<String> dataArray = new ArrayList<String>();
		
		//String[] deo = new DataEntryObject();
		
		for(int i=0; i<entryLength ; i++){
			
			field1 = "<input type='checkbox' name='id[]' value="+listNews.get(i).getGlobalId()+">";
			data[i][0] = (field1);
			data[i][1]=(listNews.get(i).getAuthor());
			data[i][2]=(listNews.get(i).getEventClass());
			data[i][3]=(listNews.get(i).getEventStatus()+"");
			//data.add(news.getEventUUID());
			data[i][4]=(listNews.get(i).getGlobalId()+"");
			data[i][5]=(listNews.get(i).getPostDatetime()+"");
			data[i][6]=(listNews.get(i).getTitle());
			
			String eventStatus = listNews.get(i).getEventStatus()+"";
			eventStatus = "Published";
			String eventStatusKey = "success";
			
			field8 = "<span class='label label-sm label-"+eventStatusKey+"'>"+eventStatus+"</span>";
			data[i][7]=(field8);
			
			field9 = "<a href='ecommerce_products_edit.html' class='btn btn-xs default btn-editable'><i class='fa fa-pencil'></i> Edit</a>";
			data[i][8]=(field9);
			
			//data.add(deo);
			//deo = new DataEntryObject(); 
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
		
		Map<String, Object> data1 = mav.getModel();
		//data.put("listNews", listTestNews);
		
//		data.put("listNews", listNews);
		data1.put("draw", new Integer(1));
		data1.put("recordsTotal", new Integer(3));
		data1.put("recordsFiltered", new Integer(3));
		data1.put("data", data);
		data1.put("customActionStatus","OK");
		data1.put("customActionMessage","OK");
		
		
		logger.debug("/content/eventsNewsListData");
		
		return data1;
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

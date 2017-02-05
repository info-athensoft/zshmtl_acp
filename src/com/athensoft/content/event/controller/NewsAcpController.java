package com.athensoft.content.event.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.athensoft.content.event.entity.Event;
import com.athensoft.content.event.entity.News;
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
		logger.info("entering /content/eventsNewsListData");
		
		ModelAndView mav = new ModelAndView();
		
		//data
		List<Event> listNews = newsService.getAllNews();
		logger.info("Length of news entries: "+ listNews.size());
		
		int entryLength = listNews.size();
		final int COLUMN_NUM = 9;
		String[][] data = new String[entryLength][COLUMN_NUM];
		
		String field0 = "";
		String field1 = "";
		String field2 = "";
		String field3 = "";
		String field4 = "";
		String field5 = "";
		String field6 = "";
		String field7 = "";
		String field8 = "";
		
		for(int i=0; i<entryLength ; i++){			
			field0 = "<input type='checkbox' name='id[]' value="+listNews.get(i).getEventUUID()+">";
			field1 = listNews.get(i).getEventUUID()+"";
			field2 = listNews.get(i).getTitle();
			field3 = listNews.get(i).getAuthor();
			field4 = listNews.get(i).getEventClass();
			field5 = listNews.get(i).getPostDatetime()+"";
			field6 = listNews.get(i).getViewNum()+"";
			String eventStatus = listNews.get(i).getEventStatus()+"";
			eventStatus = "Published";
			String eventStatusKey = "success";
			field7 = "<span class='label label-sm label-"+eventStatusKey+"'>"+eventStatus+"</span>";
			field8 = "<a href='/acp/content/eventsNewsEdit?eventUUID="+field1+"' class='btn btn-xs default btn-editable'><i class='fa fa-pencil'></i> Edit</a>";
			
			logger.info("field8="+field8);
			
			data[i][0] = field0;
			data[i][1] = field1;
			data[i][2] = field2;
			data[i][3] = field3;
			data[i][4] = field4;
			data[i][5] = field5;
			data[i][6] = field6;
			data[i][7] = field7;
			data[i][8] = field8;
		}
		
		Map<String, Object> data1 = mav.getModel();
		
		data1.put("draw", new Integer(1));
		data1.put("recordsTotal", new Integer(5));
		data1.put("recordsFiltered", new Integer(5));
		data1.put("data", data);
		data1.put("customActionStatus","OK");
		data1.put("customActionMessage","OK");
		
		logger.info("leaving /content/eventsNewsListData");
		return data1;
	}
	
	
	

	@RequestMapping(value="/content/eventsNewsEdit")
	public ModelAndView gotoNewsEdit(@RequestParam String eventUUID){
		logger.info("entering /content/eventsNewsEdit");
		
		ModelAndView mav = new ModelAndView();
		
		//view
		String viewName = "events/event_news_edit";
		mav.setViewName(viewName);
		
		//data
		Map<String, Object> model = mav.getModel();
		
		News news = newsService.getNewsByEventUUID(eventUUID);	
			
		model.put("newsObject", news);
		
		logger.info("leaving /content/eventsNewsEdit");
		return mav;
	}
	
	@RequestMapping(value="/content/eventsNewsCreate")
	public String gotoNewsCreate(){
		String viewName = "events/event_news_create";
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
	
	@RequestMapping(value="/content/createNews",method=RequestMethod.POST)
	public ModelAndView createNews(@RequestParam String itemJSONString) {
		
		logger.info("/content/createNews");
		
		/* initial settings */
		ModelAndView mav = new ModelAndView();
		
		//set model
        Map<String, Object> model = mav.getModel();
        JSONObject ic_job= new JSONObject(itemJSONString);
   
        
        /*
         * @RequestParam String eventUUID,
	@RequestParam String title,
	@RequestParam String author,
	@RequestParam String postDatetime,
	@RequestParam int viewNum,
	@RequestParam String descShort,
	@RequestParam String descLong,
	@RequestParam String eventClass,
	@RequestParam int eventStatus
         * 
         */
          News news = new News();
          //news.setGlobalId(ic_job.getLong("globalId"));
          news.setEventUUID(ic_job.getString("eventUUID"));
          news.setTitle(ic_job.getString("title"));
          news.setAuthor(ic_job.getString("author"));
          
//          news.setPostDatetime(new Date(ic_job.getString("postDatetime")));
          news.setPostDatetime(new Date());
          news.setViewNum(ic_job.getInt("viewNum"));
          news.setDescShort(ic_job.getString("descShort"));
          news.setDescLong(ic_job.getString("descLong"));
          news.setEventClass(ic_job.getString("eventClass"));
          news.setEventStatus(ic_job.getInt("eventStatus"));
          
          logger.info(news);
          
		/* business logic*/
        //long itemId = itemService.createItem(ic); 

          newsService.createNews(news);
		
		/* assemble model and view */
//        model.put("news", news);
         String viewName = "events/event_news_list";
		mav.setViewName(viewName);		
		
		return mav;		
	}
	
	@RequestMapping(value="/content/updateNews",method=RequestMethod.POST)
	public ModelAndView updateNews(@RequestParam String itemJSONString) {
		
		logger.info("entering /content/updateNews");
		
		/* initial settings */
		ModelAndView mav = new ModelAndView();
		
		//set model
        Map<String, Object> model = mav.getModel();
        JSONObject ic_job= new JSONObject(itemJSONString);
   
        News news = new News();
//          news.setGlobalId(ic_job.getLong("globalId"));
          news.setEventUUID(ic_job.getString("eventUUID"));
          news.setTitle(ic_job.getString("title"));
          news.setAuthor(ic_job.getString("author"));
          
//          news.setPostDatetime(new Date(ic_job.getString("postDatetime")));
          news.setPostDatetime(new Date());
          news.setViewNum(ic_job.getInt("viewNum"));
          news.setDescShort(ic_job.getString("descShort"));
          news.setDescLong(ic_job.getString("descLong"));
          news.setEventClass(ic_job.getString("eventClass"));
          news.setEventStatus(ic_job.getInt("eventStatus"));
          
          logger.info(news);
          
		/* business logic*/
        //long itemId = itemService.createItem(ic); 

          newsService.updateNews(news);
		
		/* assemble model and view */
//        model.put("news", news);
        String viewName = "events/event_news_list";
		mav.setViewName(viewName);		
		
		logger.info("leaving /content/updateNews");
		return mav;		
	}
}

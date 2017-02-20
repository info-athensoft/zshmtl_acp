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
import com.athensoft.content.event.entity.EventMedia;
import com.athensoft.content.event.entity.EventReview;
import com.athensoft.content.event.entity.News;
import com.athensoft.content.event.service.EventMediaService;
import com.athensoft.content.event.service.EventReviewService;
import com.athensoft.content.event.service.NewsService;

@Controller
public class NewsAcpController {
	
	private static final Logger logger = Logger.getLogger(NewsAcpController.class);
	
	private NewsService newsService;
	
	@Autowired
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	
	private EventReviewService eventReviewService;
		
	@Autowired
	public void setEventReviewService(EventReviewService eventReviewService) {
		this.eventReviewService = eventReviewService;
	}
	
	private EventMediaService eventMediaService;
	
	@Autowired
	public void setEventMediaService(EventMediaService eventMediaService) {
		this.eventMediaService = eventMediaService;
	}
	
	@RequestMapping(value="/content/eventsNewsList")
	public String gotoNewsList(){
		String viewName = "events/event_news_list";
		return viewName;
	}
	
	@RequestMapping(value="/content/eventsNewsCreate")
	public String gotoNewsCreate(){
		String viewName = "events/event_news_create";
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
			int intEventStatus = listNews.get(i).getEventStatus();
			String eventStatus = "";
			String eventStatusKey = "";
			switch(intEventStatus){
				case News.PUBLISHED: 
					eventStatus = "Published";
					eventStatusKey = "success";
					break;
				case News.NOT_PUBLISHED: 
					eventStatus = "Not published";
					eventStatusKey = "info";
					break;
				case News.DELETED: 
					eventStatus = "Deleted";
					eventStatusKey = "warning";
					break;
				default: 
					break;
			}
			field7 = "<span class='label label-sm label-"+eventStatusKey+"'>"+eventStatus+"</span>";
			field8 = "<a href='/acp/content/eventsNewsEdit?eventUUID="+field1+"' class='btn btn-xs default btn-editable'><i class='fa fa-pencil'></i> Edit</a>";
			
			//logger.info("field8="+field8);
			
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
		
		Map<String, Object> model = mav.getModel();
		
		model.put("draw", new Integer(1));
		model.put("recordsTotal", new Integer(5));
		model.put("recordsFiltered", new Integer(5));
		model.put("data", data);
		model.put("customActionStatus","OK");
		model.put("customActionMessage","OK");
		
		logger.info("leaving /content/eventsNewsListData");
		return model;
	}
	
	
	@RequestMapping(value="/content/eventsNewsSearchFilterData",produces="application/json")
	@ResponseBody
	public Map<String, Object> getDataSearchByFilter(@RequestParam String itemJSONString){
		logger.info("entering /content/eventsNewsSearchFilterData");
		
		ModelAndView mav = new ModelAndView();
		
		//data
		Map<String, Object> model = mav.getModel();
		JSONObject jobj= new JSONObject(itemJSONString);
		
		String where1 = jobj.getString("eventUUID").trim();
		String where2 = jobj.getString("title").trim();
		String where3 = jobj.getString("author").trim();
		int where4 = jobj.getInt("eventClass");
		
		/* where5a, where5b */
		String strPostDatetimeFrom = jobj.getString("postDatetimeFrom").trim();
		String strPostDatetimeTo = jobj.getString("postDatetimeTo").trim();
		
		if(strPostDatetimeFrom==null){
			strPostDatetimeFrom = "";
		}
		if(strPostDatetimeTo==null){
			strPostDatetimeTo = "";
		}
		String where5a = strPostDatetimeFrom;
		String where5b = strPostDatetimeTo;
		
		System.out.println("strViewNumFrom="+strPostDatetimeFrom+"##");
		System.out.println("strViewNumTo="+strPostDatetimeTo+"##");
		
		/* where6a, where6b */
		String strViewNumFrom = jobj.getString("viewNumFrom").trim();
		String strViewNumTo = jobj.getString("viewNumTo").trim();
		int where6a = 0;
		int where6b = 0;
		
//		System.out.println("strViewNumFrom="+strViewNumFrom+"##");
//		System.out.println("strViewNumTo="+strViewNumTo+"##");
		
		if(strViewNumFrom==null){
			strViewNumFrom = "";
		}
		if(strViewNumTo==null){
			strViewNumTo = "";
		}
		
		if(!strViewNumFrom.equals("")){
			where6a = Integer.parseInt(strViewNumFrom);
		}
		if(!strViewNumTo.equals("")){
			where6b = Integer.parseInt(strViewNumTo);
		}
		
		int where7 = jobj.getInt("eventStatus");
		
		/* construct query string */
		StringBuffer queryString = new StringBuffer();
		queryString.append(where1.length()==0?" ":" and event_uuid like '%"+where1+"%' ");
		queryString.append(where2.length()==0?" ":" and title like '%"+where2+"%' ");
		queryString.append(where3.length()==0?" ":" and author like '%"+where3+"%' ");
		queryString.append(where4==0?" ":" and event_class = "+where4+" ");
		
		
		
		
		String queryString_where5 = "";
		if(strPostDatetimeFrom.equals("")&&strPostDatetimeTo.equals("")){
			queryString_where5 = " ";
		}else if(!strPostDatetimeFrom.equals("")&&strViewNumTo.equals("")){
			/* select * from event_news where date(post_datetime) >= adddate('2017-01-12', -1); */
			queryString_where5 = " and date(post_datetime) >= '"+where5a+"' ";
		}else if(strPostDatetimeFrom.equals("")&&!strPostDatetimeTo.equals("")){
			/* select * from event_news where date(post_datetime) <= '2017-02-05'; */
			queryString_where5 = " and date(post_datetime) <= '"+where5b+"' ";
		}else if(!strPostDatetimeFrom.equals("")&&!strPostDatetimeTo.equals("")){
			/*
			select * from event_news where date(post_datetime) between adddate('2017-01-12', -1) and '2017-02-05';
			*/
			queryString_where5 = " and (date(post_datetime) between adddate('"+where5a+"', -1) and '"+where5b+"' ) ";
		}
		queryString.append(queryString_where5);
		
		
		String queryString_where6 = "";
		if(strViewNumFrom.equals("")&&strViewNumTo.equals("")){
			queryString_where6 = " ";
		}else if(!strViewNumFrom.equals("")&&strViewNumTo.equals("")){
			queryString_where6 = " and view_num >= "+where6a;
		}else if(strViewNumFrom.equals("")&&!strViewNumTo.equals("")){
			queryString_where6 = " and view_num <= "+where6b;
		}else if(!strViewNumFrom.equals("")&&!strViewNumTo.equals("")){
			if(where6a<=where6b){
				queryString_where6 = " and (view_num between "+where6a+" and "+where6b+" ) ";
			}else{
				queryString_where6 = " and (view_num between "+where6b+" and "+where6a+" ) ";
			}
			
		}
		queryString.append(queryString_where6);
		
		
		
		
		
		
		
		queryString.append(where7==0?" ":" and event_status = "+where7+" ");
		
		logger.info("QueryString = "+ queryString.toString());
		
		List<Event> listNews = newsService.getNewsByFilter(queryString.toString());
		logger.info("Length of news entries = "+ listNews.size());
		
		
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
			int intEventStatus = listNews.get(i).getEventStatus();
			String eventStatus = "";
			String eventStatusKey = "";
			switch(intEventStatus){
				case News.PUBLISHED: 
					eventStatus = "Published";
					eventStatusKey = "success";
					break;
				case News.NOT_PUBLISHED: 
					eventStatus = "Not published";
					eventStatusKey = "info";
					break;
				case News.DELETED: 
					eventStatus = "Deleted";
					eventStatusKey = "warning";
					break;
				default: 
					break;
			}
			
			
			field7 = "<span class='label label-sm label-"+eventStatusKey+"'>"+eventStatus+"</span>";
			field8 = "<a href='/acp/content/eventsNewsEdit?eventUUID="+field1+"' class='btn btn-xs default btn-editable'><i class='fa fa-pencil'></i> Edit</a>";
			
			//logger.info("field8="+field8);
			
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
		
		
		
		model.put("draw", new Integer(1));
		model.put("recordsTotal", new Integer(5));
		model.put("recordsFiltered", new Integer(5));
		model.put("data", data);
		model.put("customActionStatus","OK");
		model.put("customActionMessage","OK");
		
		logger.info("leaving /content/eventsNewsSearchFilterData");
		
		return model;
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
		
		//data - news
		News news = newsService.getNewsByEventUUID(eventUUID);	
		model.put("newsObject", news);
		
		//data - media
		List<EventMedia> listEventMedia = eventMediaService.getEventMediaByEventUUID(eventUUID);
		logger.info("Length of EventReview entries: "+ listEventMedia.size());
		model.put("eventMediaList", listEventMedia);
		
		logger.info("leaving /content/eventsNewsEdit");
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
	
	@RequestMapping(value="/content/eventsNewsReviewListData",produces="application/json")
	@ResponseBody
	public Map<String,Object> getDataNewsReviewList(){
		logger.info("entering /content/eventsNewsReviewListData");
		
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = mav.getModel();
		
		//data
		List<EventReview> listEventReview = eventReviewService.getAllEventReview();
		logger.info("Length of EventReview entries: "+ listEventReview.size());
		
		int entryLength = listEventReview.size();
		final int COLUMN_NUM = 6;
		String[][] data = new String[entryLength][COLUMN_NUM];
		
		String field0 = "";
		String field1 = "";
		String field2 = "";
		String field3 = "";
		String field4 = "";
		String field5 = "";
	
		
		for(int i=0; i<entryLength ; i++){			
//			field0 = "<input type='checkbox' name='id[]' value="+listEventReview.get(i).getEventUUID()+">";
			field0 = listEventReview.get(i).getEventUUID()+"";
			field1 = listEventReview.get(i).getReviewDatetime()+"";
			field2 = listEventReview.get(i).getCustomerId()+"";
			field3 = listEventReview.get(i).getReviewContent();
			
			
			int intReviewStatus = listEventReview.get(i).getReviewStatus();
			String reviewStatus = "";
			String reviewStatusKey = "";
			switch(intReviewStatus){
				case EventReview.APPROVED: 
					reviewStatus = "Approved";
					reviewStatusKey = "success";
					break;
				case EventReview.PENDING: 
					reviewStatus = "Pending";
					reviewStatusKey = "info";
					break;
				case EventReview.REJECTED: 
					reviewStatus = "Rejected";
					reviewStatusKey = "warning";
					break;
				default: 
					break;
			}
			
			
			field4 = "<span class='label label-sm label-"+reviewStatusKey+"'>"+reviewStatus+"</span>";
			field5 = "<a href='/acp/content/eventsNewsEdit?eventUUID="+field1+"' class='btn btn-xs default btn-editable'><i class='fa fa-pencil'></i> Edit</a>";
			
			data[i][0] = field0;
			data[i][1] = field1;
			data[i][2] = field2;
			data[i][3] = field3;
			data[i][4] = field4;
			data[i][5] = field5;
		}
		
		model.put("draw", new Integer(1));
		model.put("recordsTotal", new Integer(5));
		model.put("recordsFiltered", new Integer(5));
		model.put("data", data);
		model.put("customActionStatus","OK");
		model.put("customActionMessage","OK");
		
		logger.info("leaving /content/eventsNewsReviewListData");
		return model;
	}
	
	@RequestMapping(value="/content/setCoverMedia")
	public ModelAndView setCoverMedia(@RequestParam long mediaId, @RequestParam String eventUUID){
		logger.info("entering /content/setCoverMedia");
		
		ModelAndView mav = new ModelAndView();
		
		//view
		String viewName = "events/event_news_edit";
		mav.setViewName(viewName);
		
		//data
		Map<String, Object> model = mav.getModel();
		
		//data - set cover primary state		
		EventMedia previousPrimaryMedia = eventMediaService.getPrimaryMediaByEventUUID(eventUUID);
		if (previousPrimaryMedia!=null){
			previousPrimaryMedia.setPrimaryMedia(false);
			eventMediaService.updateEventMedia(previousPrimaryMedia);
		}
		
		EventMedia media = eventMediaService.getEventMediaByMediaId(mediaId);
		media.setPrimaryMedia(true);
		eventMediaService.updateEventMedia(media);
		
//		model.put("newsObject", news);
		
		//data - media
		List<EventMedia> listEventMedia = eventMediaService.getEventMediaByEventUUID(eventUUID);
		logger.info("Length of EventReview entries: "+ listEventMedia.size());
		model.put("eventMediaList", listEventMedia);
		
		logger.info("leaving /content/setCoverMedia");
		return mav;
	}
}

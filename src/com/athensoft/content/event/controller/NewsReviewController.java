package com.athensoft.content.event.controller;

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

import com.athensoft.content.event.entity.EventReview;
import com.athensoft.content.event.service.EventReviewService;

/**
 * News Review Controller for ACP
 * @author Athens
 * @version 1.0
 */
@Controller
@RequestMapping("/events")
public class NewsReviewController {
	private static final Logger logger = Logger.getLogger(NewsReviewController.class);
	
	/**
	 * EventReview Service instance
	 */
	@Autowired
	private EventReviewService eventReviewService;
		
	
	/**
	 * get news review objects in JSON data form<p>
	 * WARNING: DO NOT GET ALL EVENTREVIEW OBJECT IN PRODUCT. JUST FOR TEST.
	 * @return data table of new review objects
	 */
	@RequestMapping(value="/eventsNewsReviewListAllData",produces="application/json")
	@ResponseBody
	public Map<String,Object> getDataNewsReviewList(){
		logger.info("entering /events/eventsNewsReviewListData");
		
		//data
		List<EventReview> listEventReview = eventReviewService.getAllEventReview();
		logger.info("Length of EventReview entries: "+ listEventReview==null?"NULL":listEventReview.size());
		
		String[][] data = getData(listEventReview);
		
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = mav.getModel();
		model.put("draw", new Integer(1));
		model.put("recordsTotal", new Integer(5));
		model.put("recordsFiltered", new Integer(5));
		model.put("data", data);
		model.put("customActionStatus","OK");
		model.put("customActionMessage","OK");
		
		logger.info("leaving /events/eventsNewsReviewListData");
		return model;
	}
		
	/**
	 * get news review objects in JSON data form<p>
	 * WARNING: DO NOT GET ALL EVENTREVIEW OBJECT IN PRODUCT. JUST FOR TEST.
	 * @return data table of new review objects
	 */
	@RequestMapping(value="/eventsNewsReviewListData",produces="application/json")
	@ResponseBody
	public Map<String,Object> getDataNewsReviewListByEventUUID(@RequestParam String eventUUID){
		logger.info("entering /events/eventsNewsReviewListData");
		
		//data
		List<EventReview> listEventReview = eventReviewService.getEventReviewByEventUUID(eventUUID);
		logger.info("Length of EventReview entries: "+ listEventReview==null?"NULL":listEventReview.size());
		
		String[][] data = getData(listEventReview);
		
		
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = mav.getModel();
		model.put("draw", new Integer(1));
		model.put("recordsTotal", new Integer(5));
		model.put("recordsFiltered", new Integer(5));
		model.put("data", data);
		model.put("customActionStatus","OK");
		model.put("customActionMessage","OK");
		
		logger.info("leaving /events/eventsNewsReviewListData");
		return model;
	}

	/**
	 * get news review objects in JSON data form, which comply with criteria
	 * the data for showing in datatable in front-end pages is contained in a 2-dimension array
	 * @param jsonObjString search criteria object in JSON format
	 * @return a map structure containing data rendered to views
	 */
	@RequestMapping(value="/newsReviewSearchFilterData",produces="application/json")
	@ResponseBody
	public Map<String, Object> getDataSearchNewsReviewByFilter(@RequestParam String jsonObjString){
		logger.info("entering /events/newsReviewSearchFilterData");
		
		
		JSONObject jobj= new JSONObject(jsonObjString);
		
		String where1 = jobj.getString("eventReviewNo").trim();
		
		/* where2a, where2b */
		String strEventReviewDateFrom = jobj.getString("eventReviewDateFrom").trim();
		String strEventReviewDateTo= jobj.getString("eventReviewDateTo").trim();
		
		if(strEventReviewDateFrom==null){
			strEventReviewDateFrom = "";
		}
		if(strEventReviewDateTo==null){
			strEventReviewDateTo = "";
		}
		String where2a = strEventReviewDateFrom;
		String where2b = strEventReviewDateTo;
		
		logger.info("strViewNumFrom="+strEventReviewDateFrom+"##");
		logger.info("strViewNumTo="+strEventReviewDateTo+"##");
		
		String where3 = jobj.getString("eventReviewCustomer").trim();
		String where4 = jobj.getString("eventReviewContent").trim();
		int where5 = jobj.getInt("eventReviewStatus");
		
		/* construct query string */
		StringBuffer queryString = new StringBuffer();
		queryString.append(where1.length()==0?" ":" and review_uuid like '%"+where1+"%' ");
		
		
		String queryString_where2 = "";
		if(strEventReviewDateFrom.equals("")&&strEventReviewDateTo.equals("")){
			queryString_where2 = " ";
		}else if(!strEventReviewDateFrom.equals("")&&strEventReviewDateTo.equals("")){
			/* select * from event_review where date(post_datetime) >= adddate('2017-01-12', -1); */
			queryString_where2 = " and date(review_datetime) >= '"+where2a+"' ";
		}else if(strEventReviewDateFrom.equals("")&&!strEventReviewDateTo.equals("")){
			/* select * from event_review where date(post_datetime) <= '2017-02-05'; */
			queryString_where2 = " and date(review_datetime) <= '"+where2b+"' ";
		}else if(!strEventReviewDateFrom.equals("")&&!strEventReviewDateTo.equals("")){
			/*
			select * from event_news where date(post_datetime) between adddate('2017-01-12', -1) and '2017-02-05';
			*/
			
			int dateFlag = strEventReviewDateFrom.compareTo(strEventReviewDateTo);
			if(dateFlag<0){
				queryString_where2 = " and (date(review_datetime) between adddate('"+where2a+"', -1) and '"+where2b+"' ) ";
			}else if(dateFlag==0){
				queryString_where2 = " and (date(review_datetime) =  '"+where2a+"' ) ";
			}else{
				queryString_where2 = " and (date(review_datetime) between adddate('"+where2b+"', -1) and '"+where2a+"' ) ";
			}
		}
		queryString.append(queryString_where2);
		
		queryString.append(where3.length()==0?" ":" and customer_id like '%"+where3+"%' ");
		queryString.append(where4.length()==0?" ":" and review_content like '%"+where4+"%' ");
				
		queryString.append(where5==0?" ":" and review_status = "+where5+" ");
		
		logger.info("QueryString = "+ queryString.toString());
		
//		List<Event> listNews = newsService.getNewsByFilter(queryString.toString());
//		logger.info("Length of news entries = "+ listNews.size());
		
		List<EventReview> listEventReview = eventReviewService.getEventReviewByFilter(queryString.toString());
		logger.info("Length of news review entries = "+ listEventReview.size());
		
		
		String[][] data = getData(listEventReview);
		
		
		ModelAndView mav = new ModelAndView();
		
		//data
		Map<String, Object> model = mav.getModel();
		model.put("draw", new Integer(1));
		model.put("recordsTotal", new Integer(5));
		model.put("recordsFiltered", new Integer(5));
		model.put("data", data);
		model.put("customActionStatus","OK");
		model.put("customActionMessage","OK");
		
		logger.info("leaving /events/newsReviewSearchFilterData");
		
		return model;
	}
	
		
	/**
	 * goto event-news review edit page with data for updating
	 * @param eventUUID the eventUUID of new object selected
	 * @return data of review objects of news
	 */
	@RequestMapping(value="/review/edit.html")
	public ModelAndView gotoEventReviewEdit(@RequestParam String reviewUUID){
		logger.info("entering... /events/review/edit.html");
				
		//data - review
		EventReview eventReview = eventReviewService.getEventReviewByReviewUUID(reviewUUID);
		logger.info("eventReview: "+ eventReview.toString());
		
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = mav.getModel();
		model.put("eventReview", eventReview);
		//view
		String viewName = "event/review_edit";
		mav.setViewName(viewName);
		
		logger.info("leaving... /events/review/edit.html");
		return mav;
	}




	/**
	 * update news review object
	 * @param jsonObjString news object to update in JSON format
	 * @return data and target view
	 */
	@RequestMapping(value="/review/update",method=RequestMethod.POST)
	@ResponseBody
	public void updateNewsReview(@RequestParam String jsonObjString) {
		logger.info("entering /events/review/update");
		
		/* initial settings */
		ModelAndView mav = new ModelAndView();
		
		//set model
//        Map<String, Object> model = mav.getModel();
        JSONObject ic_job= new JSONObject(jsonObjString);
   
        EventReview newsReview = new EventReview();
//        newsReview.setGlobalId(ic_job.getLong("globalId"));
//        newsReview.setEventUUID(ic_job.getString("eventUUID"));
        newsReview.setReviewUUID(ic_job.getString("reviewUUID"));
//        newsReview.setReviewContent(ic_job.getString("reviewContent"));
//        newsReview.setCustomerId(ic_job.getLong("customerId"));
//        newsReview.setReviewDatetime(new Date());
        newsReview.setReviewStatus(ic_job.getInt("reviewStatus"));
          
        logger.info("newsReview = "+newsReview);
          
		/* business logic*/
        //long itemId = itemService.createItem(ic); 

        eventReviewService.updateEventReviewStatus(newsReview);
		
		/* assemble model and view */
//      String viewName = "events/event_news_list";
//		mav.setViewName(viewName);		
		
		logger.info("leaving /events/review/update");
		return ;		
	}
	
	private String[][] getData(List<EventReview> listEventReview){
		int entryLength = listEventReview.size();
		final int COLUMN_NUM = 6;		//the number of column showing in the data-table at the front web page
		String[][] data = new String[entryLength][COLUMN_NUM];
		
		String field0 = "";	//review UUID, review no.
		String field1 = "";	//review datetime
		String field2 = "";	//review customer
		String field3 = "";	//review content
		String field4 = "";	//review status
		String field5 = "";	//action
		
		for(int i=0; i<entryLength ; i++){			
			field0 = listEventReview.get(i).getReviewUUID()+"";
			field1 = listEventReview.get(i).getReviewDatetime()+"";
//			field2 = listEventReview.get(i).getCustomerId()+"";
			field2 = listEventReview.get(i).getAcctName();
			field3 = listEventReview.get(i).getReviewContent();
			
			
			int intEventReviewStatus = listEventReview.get(i).getReviewStatus();
			String eventReviewStatus = "";
			String eventReviewStatusKey = "";
			switch(intEventReviewStatus){
				case EventReview.APPROVED: 
					eventReviewStatus = "��ͨ��";
					eventReviewStatusKey = "success";
					break;
				case EventReview.PENDING: 
					eventReviewStatus = "�����";
					eventReviewStatusKey = "warning";
					break;
				case EventReview.REJECTED: 
					eventReviewStatus = "�Ѿܾ�";
					eventReviewStatusKey = "danger";
					break;
				default: 
					eventReviewStatus = "δ֪";
					eventReviewStatusKey = "default";
					break;
			}
			
			field4 = "<span class='label label-sm label-"+eventReviewStatusKey+"'>"+eventReviewStatus+"</span>";
			field5 = "<a href='/acp/events/review/edit.html?reviewUUID="+field0+"' class='btn btn-xs default btn-editable'><i class='fa fa-pencil'></i> ����</a>";
			
			data[i][0] = field0;
			data[i][1] = field1;
			data[i][2] = field2;
			data[i][3] = field3;
			data[i][4] = field4;
			data[i][5] = field5;
		}
		
		return data;
	}
	
}
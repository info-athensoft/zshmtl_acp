package com.athensoft.content.event.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.athensoft.content.event.entity.EventReview;
import com.athensoft.content.event.service.EventReviewService;

import lombok.extern.log4j.Log4j;

/**
 * News Review Controller for ACP
 * 
 * @author Athens
 * @version 1.0
 */
@Controller
@RequestMapping("/events/review")
@Log4j
public class NewsReviewController {

	/**
	 * EventReview Service instance
	 */
	@Autowired
	private EventReviewService eventReviewService;

	/**
	 * get news review objects in JSON data form
	 * <p>
	 * WARNING: DO NOT GET ALL EVENTREVIEW OBJECT IN PRODUCT. JUST FOR TEST.
	 * 
	 * @return data table of new review objects
	 */
	@RequestMapping(value = "/list", produces = "application/json")
	@ResponseBody
	public Map<String, Object> getDataListNewsReview() {
		log.info("entering... /events/review/list");

		// data
		List<EventReview> listEventReview = eventReviewService.getAllEventReview();
		log.info("Length of EventReview entries: " + listEventReview == null ? "NULL" : listEventReview.size());

		String[][] data = getData(listEventReview);

		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = mav.getModel();
		model.put("draw", new Integer(1));
		model.put("recordsTotal", new Integer(5));
		model.put("recordsFiltered", new Integer(5));
		model.put("data", data);
		model.put("customActionStatus", "OK");
		model.put("customActionMessage", "OK");

		log.info("leaving /events/review/list");
		return model;
	}

	/**
	 * get news review objects in JSON data form
	 * <p>
	 * WARNING: DO NOT GET ALL EVENTREVIEW OBJECT IN PRODUCT. JUST FOR TEST.
	 * 
	 * @return data table of new review objects
	 */
	@RequestMapping(value = "/list/eventuuid", produces = "application/json")
	@ResponseBody
	public Map<String, Object> getDataListNewsReviewByEventUUID(@RequestParam String eventUUID) {
		log.info("entering /events/eventsNewsReviewListData");

		// data
		List<EventReview> listEventReview = eventReviewService.getEventReviewByEventUUID(eventUUID);
		log.info("Length of EventReview entries: " + listEventReview == null ? "NULL" : listEventReview.size());

		String[][] data = getData(listEventReview);

		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = mav.getModel();
		model.put("draw", new Integer(1));
		model.put("recordsTotal", new Integer(5));
		model.put("recordsFiltered", new Integer(5));
		model.put("data", data);
		model.put("customActionStatus", "OK");
		model.put("customActionMessage", "OK");

		log.info("leaving /events/eventsNewsReviewListData");
		return model;
	}

	/**
	 * get news review objects in JSON data form, which comply with criteria the
	 * data for showing in datatable in front-end pages is contained in a
	 * 2-dimension array
	 * 
	 * @param jsonObjString
	 *            search criteria object in JSON format
	 * @return a map structure containing data rendered to views
	 */
	@RequestMapping(value = "/search", produces = "application/json")
	@ResponseBody
	public Map<String, Object> getDataSearchNewsReviewByFilter(@RequestParam String jsonObjString) {
		log.info("entering... /events/review/search");

		JSONObject jobj = new JSONObject(jsonObjString);

		String where1 = jobj.getString("eventReviewNo").trim();

		/* where2a, where2b */
		String strEventReviewDateFrom = jobj.getString("eventReviewDateFrom").trim();
		String strEventReviewDateTo = jobj.getString("eventReviewDateTo").trim();

		if (strEventReviewDateFrom == null) {
			strEventReviewDateFrom = "";
		}
		if (strEventReviewDateTo == null) {
			strEventReviewDateTo = "";
		}
		String where2a = strEventReviewDateFrom;
		String where2b = strEventReviewDateTo;

		log.info("strViewNumFrom=" + strEventReviewDateFrom + "##");
		log.info("strViewNumTo=" + strEventReviewDateTo + "##");

		String where3 = jobj.getString("eventReviewCustomer").trim();
		String where4 = jobj.getString("eventReviewContent").trim();
		int where5 = jobj.getInt("eventReviewStatus");

		/* construct query string */
		StringBuffer queryString = new StringBuffer();
		queryString.append(where1.length() == 0 ? " " : " and review_uuid like '%" + where1 + "%' ");

		String queryString_where2 = "";
		if (strEventReviewDateFrom.equals("") && strEventReviewDateTo.equals("")) {
			queryString_where2 = " ";
		} else if (!strEventReviewDateFrom.equals("") && strEventReviewDateTo.equals("")) {
			/*
			 * select * from event_review where date(post_datetime) >=
			 * adddate('2017-01-12', -1);
			 */
			queryString_where2 = " and date(review_datetime) >= '" + where2a + "' ";
		} else if (strEventReviewDateFrom.equals("") && !strEventReviewDateTo.equals("")) {
			/*
			 * select * from event_review where date(post_datetime) <=
			 * '2017-02-05';
			 */
			queryString_where2 = " and date(review_datetime) <= '" + where2b + "' ";
		} else if (!strEventReviewDateFrom.equals("") && !strEventReviewDateTo.equals("")) {
			/*
			 * select * from event_news where date(post_datetime) between
			 * adddate('2017-01-12', -1) and '2017-02-05';
			 */

			int dateFlag = strEventReviewDateFrom.compareTo(strEventReviewDateTo);
			if (dateFlag < 0) {
				queryString_where2 = " and (date(review_datetime) between adddate('" + where2a + "', -1) and '"
						+ where2b + "' ) ";
			} else if (dateFlag == 0) {
				queryString_where2 = " and (date(review_datetime) =  '" + where2a + "' ) ";
			} else {
				queryString_where2 = " and (date(review_datetime) between adddate('" + where2b + "', -1) and '"
						+ where2a + "' ) ";
			}
		}
		queryString.append(queryString_where2);

		queryString.append(where3.length() == 0 ? " " : " and customer_id like '%" + where3 + "%' ");
		queryString.append(where4.length() == 0 ? " " : " and review_content like '%" + where4 + "%' ");

		queryString.append(where5 == 0 ? " " : " and review_status = " + where5 + " ");

		log.info("QueryString = " + queryString.toString());

		// List<Event> listNews =
		// newsService.getNewsByFilter(queryString.toString());
		// log.info("Length of news entries = "+ listNews.size());

		List<EventReview> listEventReview = eventReviewService.getEventReviewByFilter(queryString.toString());
		log.info("Length of news review entries = " + listEventReview.size());

		String[][] data = getData(listEventReview);

		ModelAndView mav = new ModelAndView();

		// data
		Map<String, Object> model = mav.getModel();
		model.put("draw", new Integer(1));
		model.put("recordsTotal", new Integer(5));
		model.put("recordsFiltered", new Integer(5));
		model.put("data", data);
		model.put("customActionStatus", "OK");
		model.put("customActionMessage", "OK");

		log.info("leaving... /events/review/search");

		return model;
	}

	/**
	 * goto event-news review edit page with data for updating
	 * 
	 * @param eventUUID
	 *            the eventUUID of new object selected
	 * @return data of review objects of news
	 */
	@RequestMapping(value = "/edit.html")
	public ModelAndView gotoEditEventReview(@RequestParam String reviewUUID) {
		log.info("entering... /events/review/edit.html");

		// data - review
		EventReview eventReview = eventReviewService.getEventReviewByReviewUUID(reviewUUID);
		log.info("eventReview: " + eventReview.toString());

		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = mav.getModel();
		model.put("eventReview", eventReview);
		// view
		String viewName = "event/review_edit";
		mav.setViewName(viewName);

		log.info("leaving... /events/review/edit.html");
		return mav;
	}

	/**
	 * update news review object
	 * 
	 * @param jsonObjString
	 *            news object to update in JSON format
	 * @return data and target view
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@ResponseBody
	public void updateNewsReview(@RequestBody ReviewDTO review) {
		log.info("entering /events/review/update");

//		JSONObject ic_job = new JSONObject(jsonObjString);

		EventReview newsReview = new EventReview();
		newsReview.setEventUUID(review.getEventUUID());
		newsReview.setReviewUUID(review.getReviewUUID());
		newsReview.setReviewStatus(review.getReviewStatus());

		log.info("newsReview = " + newsReview);

		/* business logic */
		eventReviewService.updateEventReviewStatus(newsReview);

		log.info("leaving /events/review/update");
		return;
	}

	private String[][] getData(List<EventReview> listEventReview) {
		int entryLength = listEventReview.size();
		final int COLUMN_NUM = 6; // the number of column showing in the
									// data-table at the front web page
		String[][] data = new String[entryLength][COLUMN_NUM];

		String field0 = ""; // review UUID, review no.
		String field1 = ""; // review datetime
		String field2 = ""; // review customer
		String field3 = ""; // review content
		String field4 = ""; // review status
		String field5 = ""; // action

		for (int i = 0; i < entryLength; i++) {
			field0 = listEventReview.get(i).getReviewUUID() + "";
			field1 = listEventReview.get(i).getReviewDatetime() + "";
			// field2 = listEventReview.get(i).getCustomerId()+"";
			field2 = listEventReview.get(i).getAcctName();
			field3 = listEventReview.get(i).getReviewContent();

			int intEventReviewStatus = listEventReview.get(i).getReviewStatus();
			String eventReviewStatus = "";
			String eventReviewStatusKey = "";
			switch (intEventReviewStatus) {
			case EventReview.APPROVED:
				eventReviewStatus = "已通过";
				eventReviewStatusKey = "success";
				break;
			case EventReview.PENDING:
				eventReviewStatus = "审查中";
				eventReviewStatusKey = "warning";
				break;
			case EventReview.REJECTED:
				eventReviewStatus = "已拒绝";
				eventReviewStatusKey = "danger";
				break;
			default:
				eventReviewStatus = "未知";
				eventReviewStatusKey = "default";
				break;
			}

			field4 = "<span class='label label-sm label-" + eventReviewStatusKey + "'>" + eventReviewStatus + "</span>";
			field5 = "<a href='/acp/events/review/edit.html?reviewUUID=" + field0
					+ "' class='btn btn-xs default btn-editable'><i class='fa fa-pencil'></i> 管理</a>";

			data[i][0] = field0;
			data[i][1] = field1;
			data[i][2] = field2;
			data[i][3] = field3;
			data[i][4] = field4;
			data[i][5] = field5;
		}

		return data;
	}
	
	public static class ReviewDTO {
		private String eventUUID;
		private String reviewUUID;
		private int reviewStatus;
		public String getEventUUID() {
			return eventUUID;
		}
		public void setEventUUID(String eventUUID) {
			this.eventUUID = eventUUID;
		}
		public String getReviewUUID() {
			return reviewUUID;
		}
		public void setReviewUUID(String reviewUUID) {
			this.reviewUUID = reviewUUID;
		}
		public int getReviewStatus() {
			return reviewStatus;
		}
		public void setReviewStatus(int reviewStatus) {
			this.reviewStatus = reviewStatus;
		}
		@Override
		public String toString() {
			return "ReviewDTO [eventUUID=" + eventUUID + ", reviewUUID=" + reviewUUID + ", reviewStatus=" + reviewStatus
					+ "]";
		}
	}

}

package com.athensoft.content.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.athensoft.content.event.dao.EventReviewDao;
import com.athensoft.content.event.entity.Event;
import com.athensoft.content.event.entity.EventReview;

/**
 * EventReview Service
 * @author Athens
 * @version 1.0
 */
@Service
public class EventReviewService {
	
	/**
	 * DAO of EventReview
	 */
	@Autowired
	@Qualifier("eventReviewDaoJDBCImpl")
	private EventReviewDao eventReviewDao;
	
	public void setEventReviewDao(EventReviewDao eventReviewDao) {
		this.eventReviewDao = eventReviewDao;
	}
	
	/**
	 * get an eventReview object by its globalId
	 * @param globalId the globalId of eventReview object
	 * @return the target eventReview object
	 */
	public EventReview getEventReviewById(long globalId){
		return (EventReview)eventReviewDao.findById(globalId);
	}
	
	/**
	 * get eventReview objects by its eventUUID
	 * @param eventUUID the eventUUID of eventReview object
	 * @return the list of eventReview objects
	 */
	public List<EventReview> getEventReviewByEventUUID(String eventUUID){
		return eventReviewDao.findByEventUUID(eventUUID);
	}
	
	/**
	 * get eventReview objects by its eventUUID
	 * @param eventUUID the eventUUID of eventReview object
	 * @return the list of eventReview objects
	 */
	public List<EventReview> getEventReviewByFilter(String queryString){
		return eventReviewDao.findByFilter(queryString);
	}
	
	/**
	 * get eventReview object by reviewUUID
	 * @param reviewUUID the reviewUUID of eventReview object
	 * @return the object of eventReview
	 */
	public EventReview getEventReviewByReviewUUID(String reviewUUID){
		return eventReviewDao.findByReviewUUID(reviewUUID);
	}
	
	/**
	 * get all eventReview objects
	 * @return the list of eventReview objects
	 */
	public List<EventReview> getAllEventReview() {
		return eventReviewDao.findAll();
	}
	
	
	/**
	 * get latest reviews of news for shortcut control panel, shows 8 entries by default
	 * @param queryString
	 * @return
	 */
	public List<EventReview> getLatestReview(){
		final int DEFAULT_COUNT = 8;
		return getLatestReview(DEFAULT_COUNT);
	}
	
	/**
	 * get latest reviews of news for shortcut control panel by specified number
	 * @param count
	 * @return
	 */
	public List<EventReview> getLatestReview(int count){
		String queryString = " ORDER BY review_datetime DESC LIMIT "+count;
		return eventReviewDao.findByFilter(queryString);
	}
	
	/**
	 * get total count of all news
	 * @return
	 */
	public long countAllReviews(){
		return eventReviewDao.count();
	}
	
	/**
	 * create eventReview and persist it
	 * @param review the eventReview object to persist
	 */
	public void createEventReview(EventReview review) {
		this.eventReviewDao.create(review);
	}
	
	/**
	 * update eventReview and persist it
	 * @param review the eventReview object to persist
	 */
	public void updateEventReview(EventReview review) {
		this.eventReviewDao.update(review);
	}
}

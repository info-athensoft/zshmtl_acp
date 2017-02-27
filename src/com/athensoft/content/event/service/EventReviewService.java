package com.athensoft.content.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.athensoft.content.event.dao.EventReviewDao;
import com.athensoft.content.event.entity.EventReview;
import com.athensoft.content.event.entity.News;

@Service
public class EventReviewService {
	
	@Autowired
	@Qualifier("eventReviewDaoJDBCImpl")
	private EventReviewDao eventReviewDao;
	
	public void setEventReviewDao(EventReviewDao eventReviewDao) {
		this.eventReviewDao = eventReviewDao;
	}
	
	public EventReview getEventReviewById(long globalId){
		return (EventReview)eventReviewDao.findById(globalId);
	}
	
	public List<EventReview> getEventReviewByEventUUID(String eventUUID){
		return eventReviewDao.findByEventUUID(eventUUID);
	}
	
	public List<EventReview> getAllEventReview() {
		return eventReviewDao.findAll();
	}
	
	public void createEventReview(EventReview review) {
		this.eventReviewDao.create(review);
	}
	
	public void updateEventReview(EventReview review) {
		this.eventReviewDao.update(review);
	}
}

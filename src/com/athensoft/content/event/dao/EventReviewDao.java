package com.athensoft.content.event.dao;

import java.util.List;

import com.athensoft.content.event.entity.EventReview;

public interface EventReviewDao {
	public List<EventReview> findAll();
	public List<EventReview> findByFilter(String queryString);
	public EventReview findById(long globalId);
	
	public EventReview findByReviewUUID(String reviewUUID);	
	
	public void create(EventReview review);
	public void delete();
	public void update(EventReview review);
	public List<EventReview> findByEventUUID(String eventUUID);
}

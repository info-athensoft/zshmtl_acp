package com.athensoft.content.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.athensoft.content.event.dao.EventMediaDao;
import com.athensoft.content.event.entity.EventMedia;

@Service
public class EventMediaService {
	@Autowired
	@Qualifier("eventMediaDaoJDBCImpl")
	private EventMediaDao eventMediaDao;

	public void setEventMediaDao(EventMediaDao eventMediaDao) {
		this.eventMediaDao = eventMediaDao;
	}
	
	public List<EventMedia> getEventMediaByEventUUID(String eventUUID){
		return eventMediaDao.findByEventUUID(eventUUID);
	}
	
	public EventMedia getEventMediaByMediaId(long mediaId) {
		return eventMediaDao.findById(mediaId);
	}
	
	public EventMedia getPrimaryMediaByEventUUID(String eventUUID) {
		return eventMediaDao.findPrimaryMediaByEventUUID(eventUUID);
	}
	
	public List<EventMedia> getAllEventMedia() {
		return eventMediaDao.findAll();
	}
	
	public void creatEventMedia(EventMedia media) {
		this.eventMediaDao.create(media);
	}
	
	public void updateEventMedia(EventMedia media) {
		this.eventMediaDao.update(media);
	}
	

}

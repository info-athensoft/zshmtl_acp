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
}

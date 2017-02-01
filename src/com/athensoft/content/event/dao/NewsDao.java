package com.athensoft.content.event.dao;

import java.util.List;

import com.athensoft.content.event.entity.Event;
import com.athensoft.content.event.entity.News;

public interface NewsDao {
	public List<Event> findAll();
	public Event findById(long globalId);
	public Event findByEventUUID(String eventUUID);
	
	public void create(News news);
	public void update();
	public void delete();
	
}

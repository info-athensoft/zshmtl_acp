package com.athensoft.content.event.dao;

import java.util.List;

import com.athensoft.content.event.entity.EventMedia;

public interface EventMediaDao {
	public List<EventMedia> findAll();
	public EventMedia findById(long mediaId);
	public EventMedia findPrimaryMediaByEventUUID(String eventUUID);
	public List<EventMedia> findByEventUUID(String eventUUID);
	public void create(EventMedia media);
	public void delete();
	public void update(EventMedia media);
}

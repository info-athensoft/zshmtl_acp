package com.athensoft.content.event.dao;

import java.util.List;

import com.athensoft.content.event.entity.EventMedia;

/**
 * A DAO interface of {@link EventMedia}
 * 
 * @author Athens
 * @version 1.0 
 */
public interface EventMediaDao {
	/**
	 * get all event media objects<p>
	 * WARNING: DO NOT USE THIS METHOD IN PRODUCT, JUST FOR TEST.
	 * @return	list of news objects
	 */
	public List<EventMedia> findAll();
	
	/**
	 * get event media object by its gloablId
	 * @param mediaId the globlaId of news media object
	 * @return an event media object
	 */
	public EventMedia findById(long mediaId);
	
	/**
	 * get event media object by its eventUUID
	 * @param eventUUID the eventUUID of news object
	 * @return an event media object
	 */
	public List<EventMedia> findByEventUUID(String eventUUID);
	
	/**
	 * get primary event media object by its eventUUID
	 * @param eventUUID the eventUUID of news object
	 * @return an event media object
	 */
	public EventMedia findPrimaryMediaByEventUUID(String eventUUID);
	
	/**
	 * create an event media object and persist it
	 * @param media an event media object
	 */
	public void create(EventMedia media);
	
	/**
	 * remove an event media object from data source<p>
	 * WARNING: THE METHOD HAS NOT COMPLETED.
	 */
	public void delete();
	
	/**
	 * update an event media object and persist it
	 * @param media an event media object
	 */
	public void update(EventMedia media);

	public void changeSortNumber(String mediaId, String eventUUID, String sortNumber);

	public void changeMediaName(String mediaId, String eventUUID, String mediaName);
}

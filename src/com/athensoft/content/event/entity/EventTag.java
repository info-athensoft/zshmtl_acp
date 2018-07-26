package com.athensoft.content.event.entity;

import lombok.Data;

/**
 * A tag object binding to an event object, which represents a keyword
 * 
 * @author Athens
 * @version 1.0 
 */
@Data
public class EventTag {
	
	/**
	 * the id of event tag
	 */
	private long tagId;
	
	/**
	 * event UUID, the unique id for event objects
	 */
	private String eventUUID;
	
	/**
	 * the name of tag
	 */
	private String tagName;
	
	/**
	 * the description of tag
	 */
	private String tagDesc;
	
	
	
	
}

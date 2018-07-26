package com.athensoft.content.event.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

/**
 * A high-level class of event, may have multiple subclasses, represents a message to publish
 * 
 * @author Athens
 * @version 1.0 
 */
@ToString
@Log4j
public class Event {
	
	
	/**
	 * global id without business sense
	 */
	@Setter
	@Getter
	private long 		globalId;
	
	/**
	 * event UUID, the unique id for event objects
	 */
	@Setter
	@Getter
	private String 		eventUUID;
	
	/**
	 * event title, could be shown at front end pages
	 */
	@Setter
	@Getter
	private String 		title;
	
	/**
	 * event author who composites the content of event
	 */
	@Setter
	@Getter
	private String 		author;
	
	/**
	 * the date-time when the event content is post
	 */
	@Setter
	@Getter
	private Date 		postDatetime;
	
	/**
	 * the number of views on the event, a statistics indicator
	 */
	@Setter
	@Getter
	private int 		viewNum;
	
	/**
	 * the short description, short content of event
	 */
	@Setter
	@Getter
	private String 		descShort;
	
	/**
	 * the long description, long content of event
	 */
	@Setter
	@Getter
	private String 		descLong;
	
	/**
	 * the class(category) of event
	 */
	@Setter
	@Getter
	private String 		eventClass;
	
	/**
	 * the status of event
	 */
	@Setter
	@Getter
	private int 		eventStatus;	
	
	/**
	 * the primary media object which is binding to the event
	 */
	@Getter
	private EventMedia 	primaryEventMedia;
	
	/**
	 * the list of event tag objects which are binding to the event
	 */
	@Setter
	@Getter
	private List<EventTag> listEventTag = new ArrayList<EventTag>();
	
	/**
	 * the list of event media objects which are binding to the event
	 */
	@Setter
	@Getter
	private List<EventMedia> listEventMedia = new ArrayList<EventMedia>();
	
	/**
	 * the list of event review objects which are binding to the event
	 */
	@Setter
	@Getter
	private List<EventReview> listEventReview = new ArrayList<EventReview>();
	
	
	public void setPrimaryEventMedia() {
		EventMedia em = null;
		int size = this.listEventMedia.size();
		if(size>0){
			for(int i=0; i<size; i++){
				EventMedia tmpEm = listEventMedia.get(i);
				if(tmpEm.isPrimaryMedia()){
					em = tmpEm;
					break;
				}
			}
		}else{
			log.debug("WARNING: "+this.getClass().getName()+" - no eventmedia object in the list");
			em = new EventMedia();
			em.setMediaName("default media");
			em.setMediaURL("event-default.png");
		}
		
		this.primaryEventMedia = em;
	}
}

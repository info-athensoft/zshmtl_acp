package com.athensoft.content.event.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * A media object binding to an event object, which contains such media as picture,audio,video
 * 
 * @author Athens
 * @version 1.0 
 */
@Setter
@Getter
@ToString
public class EventMedia {
	/**
	 * the global media id without business sense
	 */
	private long mediaId;
	
	/**
	 * event UUID, the unique id for event objects
	 */
	private String eventUUID;
	
	/**
	 * the URL string of media object
	 * pattern: /{eventUUID}/{mediaFileName}
	 */
	private String mediaURL;
	
	/**
	 * the media file name of media object
	 */
	private String mediaName;
	
	/**
	 * the label or title of the media object
	 */
	private String mediaLabel;
	
	/**
	 * the sort number of media object, ascending
	 */
	private int sortNumber;
	
	/**
	 * the flag of primary media which is taken as cover media
	 */
	private boolean primaryMedia;
	
	/**
	 * the type of media indicating the format of media file
	 * the type could be {jpg,png,video link,audio link,...}
	 */
	private int mediaType;
	
	/**
	 * the date-time when the media binding to the event content is post
	 */
	private Date postTimestamp;
	
	//private boolean flag;
		
	
}

package com.athensoft.content.event.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * A review object binding to an event object, which represents a review from web visitors
 * 
 * @author Athens
 * @version 1.0 
 */
@Setter
@Getter
@ToString
public class EventReview {
	
	public static final int UNKNOWN = 0;
	
	/**
	 * the status of review, constant value, APPROVED = 1
	 */
	public static final int APPROVED = 1;
	
	/**
	 * the status of review, constant value, PENDING = 2
	 */
	public static final int PENDING = 2;
	
	/**
	 * the status of review, constant value, REJECTED = 3
	 */
	public static final int REJECTED = 3;
	
	/**
	 * global id without business sense
	 */
	private long globalId;
	
	/**
	 * event UUID, the unique id for event objects
	 */
	private String eventUUID;
	
	/**
	 * review UUID, the unique id for review objects
	 */
	private String reviewUUID;
	
	/**
	 * the date-time when the review content is post
	 */
	private Date reviewDatetime;
	
	/**
	 * the id of customer who post the review
	 */
	private long customerId;
	
	/**
	 * 
	 */
	private String acctName;
	
	/**
	 * the content of the review
	 */
	private String reviewContent;
	
	/**
	 * the status of the review, refer to the constant value
	 * {@link EventReview#APPROVED},{@link EventReview#PENDING},{@link EventReview#REJECTED}
	 * @see EventReview#APPROVED 
	 * @see EventReview#PENDING
	 * @see EventReview#REJECTED
	 */
	private int reviewStatus;
	
	

}

package com.athensoft.content.event.entity;

import java.util.Date;

public class TestNews {
	
	//private int checked = 0;
	

	private long globalId;
	public long getGlobalId() {
		return globalId;
	}
	public void setGlobalId(long globalId) {
		this.globalId = globalId;
	}
	public String getEventUUID() {
		return eventUUID;
	}
	public void setEventUUID(String eventUUID) {
		this.eventUUID = eventUUID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getPostDatetime() {
		return postDatetime;
	}
	public void setPostDatetime(Date postDatetime) {
		this.postDatetime = postDatetime;
	}
	public String getEventClass() {
		return eventClass;
	}
	public void setEventClass(String eventClass) {
		this.eventClass = eventClass;
	}
	public int getEventStatus() {
		return eventStatus;
	}
	public void setEventStatus(int eventStatus) {
		this.eventStatus = eventStatus;
	}
	private String eventUUID;
	private String title;
	private String author;
	private Date postDatetime;
	//private int viewNum;
	//private String descShort;
	//private String descLong;
	private String eventClass;
	private int eventStatus;
	
	
	
	
}

package com.athensoft.content.event.entity;

import java.util.Date;

public class EventReview {
	public static final int APPROVED = 1;
	public static final int PENDING = 2;
	public static final int REJECTED = 3;
	private long globalId;
	private String eventUUID;
	private String reviewUUID;
	private Date reviewDatetime;
	private long customerId;
	private String reviewContent;
	private int reviewStatus;
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
	public String getReviewUUID() {
		return reviewUUID;
	}
	public void setReviewUUID(String reviewUUID) {
		this.reviewUUID = reviewUUID;
	}
	public Date getReviewDatetime() {
		return reviewDatetime;
	}
	public void setReviewDatetime(Date reviewDatetime) {
		this.reviewDatetime = reviewDatetime;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public int getReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(int reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
	@Override
	public String toString() {
		return "EventReview [globalId=" + globalId + ", eventUUID=" + eventUUID
				+ ", reviewUUID=" + reviewUUID + ", reviewDatetime="
				+ reviewDatetime + ", customerId=" + customerId
				+ ", reviewContent=" + reviewContent + ", reviewStatus="
				+ reviewStatus + "]";
	}

}

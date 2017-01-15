package com.athensoft.content.event.entity;

import java.util.Date;

public class EventMedia {
	private long mediaId;
	private String eventUUID;
	private String mediaURL;
	private String mediaName;
	private int sortNumber;
	private boolean isPrimaryMedia;
	private int mediaType;
	private Date postTimestamp;
	
	public long getMediaId() {
		return mediaId;
	}
	public void setMediaId(long mediaId) {
		this.mediaId = mediaId;
	}
	public String getEventUUID() {
		return eventUUID;
	}
	public void setEventUUID(String eventUUID) {
		this.eventUUID = eventUUID;
	}
	public String getMediaURL() {
		return mediaURL;
	}
	public void setMediaURL(String mediaURL) {
		this.mediaURL = mediaURL;
	}
	public String getMediaName() {
		return mediaName;
	}
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
	public int getSortNumber() {
		return sortNumber;
	}
	public void setSortNumber(int sortNumber) {
		this.sortNumber = sortNumber;
	}
	public boolean isPrimaryMedia() {
		return isPrimaryMedia;
	}
	public void setPrimaryMedia(boolean isPrimaryMedia) {
		this.isPrimaryMedia = isPrimaryMedia;
	}
	public int getMediaType() {
		return mediaType;
	}
	public void setMediaType(int mediaType) {
		this.mediaType = mediaType;
	}
	public Date getPostTimestamp() {
		return postTimestamp;
	}
	public void setPostTimestamp(Date postTimestamp) {
		this.postTimestamp = postTimestamp;
	}
	
	@Override
	public String toString() {
		return "EventMedia [mediaId=" + mediaId + ", eventUUID=" + eventUUID + ", mediaURL=" + mediaURL + ", mediaName="
				+ mediaName + ", sortNumber=" + sortNumber + ", isPrimaryMedia=" + isPrimaryMedia + ", mediaType="
				+ mediaType + ", postTimestamp=" + postTimestamp + "]";
	}
	
	
	
}

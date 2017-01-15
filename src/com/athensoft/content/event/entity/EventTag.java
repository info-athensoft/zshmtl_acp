package com.athensoft.content.event.entity;

public class EventTag {
	private long tagId;
	private String eventUUID;
	private String tagName;
	private String tagDesc;
	
	public long getTagId() {
		return tagId;
	}
	public void setTagId(long tagId) {
		this.tagId = tagId;
	}
	public String getEventUUID() {
		return eventUUID;
	}
	public void setEventUUID(String eventUUID) {
		this.eventUUID = eventUUID;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getTagDesc() {
		return tagDesc;
	}
	public void setTagDesc(String tagDesc) {
		this.tagDesc = tagDesc;
	}
	@Override
	public String toString() {
		return "EventTag [tagId=" + tagId + ", eventUUID=" + eventUUID + ", tagName=" + tagName + ", tagDesc=" + tagDesc
				+ "]";
	}
	
	
}

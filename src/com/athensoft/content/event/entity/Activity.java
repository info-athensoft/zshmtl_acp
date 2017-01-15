package com.athensoft.content.event.entity;

import java.util.Date;

public class Activity extends Event {
	private Date activityStartDate;
	private Date activityEndDate;
	private String activityLocation;
	
	
	public Date getActivityStartDate() {
		return activityStartDate;
	}
	public void setActivityStartDate(Date activityStartDate) {
		this.activityStartDate = activityStartDate;
	}
	public Date getActivityEndDate() {
		return activityEndDate;
	}
	public void setActivityEndDate(Date activityEndDate) {
		this.activityEndDate = activityEndDate;
	}
	public String getActivityLocation() {
		return activityLocation;
	}
	public void setActivityLocation(String activityLocation) {
		this.activityLocation = activityLocation;
	}
	@Override
	public String toString() {
		return "Activity [activityStartDate=" + activityStartDate + ", activityEndDate=" + activityEndDate
				+ ", activityLocation=" + activityLocation + ", toString()=" + super.toString() + "]";
	}
	
	
	
}

package com.athensoft.ecomm.order.entity;

public class Contact {
	private long contactorId;
	private String entityName;		//individual or business
	private String email;
	private String telNum;
	private String faxNum;
	
	
	public long getContactorId() {
		return contactorId;
	}
	public void setContactorId(long contactorId) {
		this.contactorId = contactorId;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelNum() {
		return telNum;
	}
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	public String getFaxNum() {
		return faxNum;
	}
	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
	}
	
	
	
}

package com.athensoft.content.ad.entity;

import java.util.Date;

public class AdRequest {
	private Long globalId;
	private String acctName;
	private Integer requestType;
	private String requestName;
	private Date requestDate;
	private String requestPhone;
	private String requestSubject;
	private String requestMessage;
	private Integer requestStatus;
	public Long getGlobalId() {
		return globalId;
	}
	public void setGlobalId(Long globalId) {
		this.globalId = globalId;
	}
	public String getAcctName() {
		return acctName;
	}
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}
	public Integer getRequestType() {
		return requestType;
	}
	public void setRequestType(Integer requestType) {
		this.requestType = requestType;
	}
	public String getRequestName() {
		return requestName;
	}
	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public String getRequestPhone() {
		return requestPhone;
	}
	public void setRequestPhone(String requestPhone) {
		this.requestPhone = requestPhone;
	}
	public String getRequestSubject() {
		return requestSubject;
	}
	public void setRequestSubject(String requestSubject) {
		this.requestSubject = requestSubject;
	}
	public String getRequestMessage() {
		return requestMessage;
	}
	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}
	public Integer getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(Integer requestStatus) {
		this.requestStatus = requestStatus;
	}
	@Override
	public String toString() {
		return "AdRequest [globalId=" + globalId + ", acctName=" + acctName + ", requestType=" + requestType
				+ ", requestName=" + requestName + ", requestDate=" + requestDate + ", requestPhone=" + requestPhone
				+ ", requestSubject=" + requestSubject + ", requestMessage=" + requestMessage + ", requestStatus="
				+ requestStatus + "]";
	}
	
}

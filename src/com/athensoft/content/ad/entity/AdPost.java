package com.athensoft.content.ad.entity;

import java.util.Date;

public class AdPost {
	private Long globalId;
	private String adUUID;
	private String adTitle;
	private String adText;
	private String adLink;
	private String adImage;
	private String adUrl;
	private Integer adType;
	private Long adOwnerId;
	private String acctName;
	private Integer adStatus;
	private Date createDate;
	private Date postDate;
	private Date expireDate;
	private Date modifyDate;
	
	public Integer getAdStatus() {
		return adStatus;
	}
	public void setAdStatus(Integer adStatus) {
		this.adStatus = adStatus;
	}
	
	public Long getGlobalId() {
		return globalId;
	}
	public void setGlobalId(Long globalId) {
		this.globalId = globalId;
	}
	public String getAdText() {
		return adText;
	}
	public void setAdText(String adText) {
		this.adText = adText;
	}
	public String getAdLink() {
		return adLink;
	}
	public void setAdLink(String adLink) {
		this.adLink = adLink;
	}
	public String getAdImage() {
		return adImage;
	}
	public void setAdImage(String adImage) {
		this.adImage = adImage;
	}
	public String getAdUrl() {
		return adUrl;
	}
	public void setAdUrl(String adUrl) {
		this.adUrl = adUrl;
	}
	public Integer getAdType() {
		return adType;
	}
	public void setAdType(Integer adType) {
		this.adType = adType;
	}
	public Long getAdOwnerId() {
		return adOwnerId;
	}
	public void setAdOwnerId(Long adOwnerId) {
		this.adOwnerId = adOwnerId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	@Override
	public String toString() {
		return "AdPost [globalId=" + globalId + ", adUUID=" + adUUID + ", adTitle=" + adTitle + ", adText=" + adText
				+ ", adLink=" + adLink + ", adImage=" + adImage + ", adUrl=" + adUrl + ", adType=" + adType
				+ ", adOwnerId=" + adOwnerId + ", acctName=" + acctName + ", adStatus=" + adStatus + ", createDate="
				+ createDate + ", postDate=" + postDate + ", expireDate=" + expireDate + ", modifyDate=" + modifyDate
				+ "]";
	}
	public String getAcctName() {
		return acctName;
	}
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}
	public String getAdTitle() {
		return adTitle;
	}
	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}
	public String getAdUUID() {
		return adUUID;
	}
	public void setAdUUID(String adUUID) {
		this.adUUID = adUUID;
	}
	
}

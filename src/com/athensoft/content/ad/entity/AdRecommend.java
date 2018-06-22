package com.athensoft.content.ad.entity;

public class AdRecommend {
	private int globalId;
	private int pageId;
	private String pageName;
	private double rcmdScore;
	private int rcmdRank;
	private int rcmdStatus;
	private String adUUID;
	
	public int getGlobalId() {
		return globalId;
	}
	public void setGlobalId(int globalId) {
		this.globalId = globalId;
	}
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public double getRcmdScore() {
		return rcmdScore;
	}
	public void setRcmdScore(double rcmdScore) {
		this.rcmdScore = rcmdScore;
	}
	public int getRcmdRank() {
		return rcmdRank;
	}
	
	public int getRcmdStatus() {
		return rcmdStatus;
	}
	
	public String getAdUUID() {
		return adUUID;
	}
	public void setAdUUID(String adUUID) {
		this.adUUID = adUUID;
	}
	@Override
	public String toString() {
		return "AdRecommend [globalId=" + globalId + ", pageId=" + pageId + ", pageName=" + pageName + ", rcmdScore="
				+ rcmdScore + ", rcmdRank=" + rcmdRank + ", rcmdStatus=" + rcmdStatus + ", adUUID=" + adUUID + "]";
	}
	public void setRcmdRank(int rcmdRank) {
		this.rcmdRank = rcmdRank;
	}
	public void setRcmdStatus(int rcmdStatus) {
		this.rcmdStatus = rcmdStatus;
	}
	
}


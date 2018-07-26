package com.athensoft.content.ad.entity;

import lombok.Data;

@Data
public class AdRecommend {
	private int globalId;
	private int pageId;
	private String pageName;
	private double rcmdScore;
	private int rcmdRank;
	private int rcmdStatus;
	private String adUUID;
}


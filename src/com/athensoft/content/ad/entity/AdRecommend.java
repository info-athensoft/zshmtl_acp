package com.athensoft.content.ad.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdRecommend {
	private int globalId;
	private int pageId;
	private String pageName;
	private double rcmdScore;
	private int rcmdRank;
	private int rcmdStatus;
	private String adUUID;
}


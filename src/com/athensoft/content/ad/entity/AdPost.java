package com.athensoft.content.ad.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
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
}

package com.athensoft.content.ad.entity;

import java.util.Date;

import lombok.Data;

@Data
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
}

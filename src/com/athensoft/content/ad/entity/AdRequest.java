package com.athensoft.content.ad.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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

package com.athensoft.member.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {
	private Long	globalId;
	private String 	acctName;		//user name
	private String	memberCode;
	private Long	memberId;
	private String 	name1;			//Chinese name
	private String 	name2;			//English name
	private	Integer	gender;
	private	String 	nationality;
	private	String 	phone1;			//telephone
	private	String 	phone2;			//cell phone
	private	String 	wechat;
	private	String 	email;
	private	String 	degree;
	private	String 	occupation;
	private	String 	dob;
	private	String 	pobProvince;
	private	String 	pobCity;
	private	String 	homeAddress;
	private	String 	postalcode;
	private String	hobbies;
	private Integer	memberStatus;
	private Integer	memberLevel;
	private Date 	memberApplyDate;
	private Date 	memberApprovedDate;
	private Date 	memberActiveDate;
	private Date 	memberInactiveDate;
	private Date 	memberPendingDate;
	private Date 	memberBannedDate;
}

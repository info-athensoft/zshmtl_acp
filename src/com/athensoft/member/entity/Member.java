package com.athensoft.member.entity;

import java.util.Date;

public class Member {
	private Long	globalId;
	private String 	acctName;	//user name
	private String	memberCode;
	private Long	memberId;
	private String 	name1;	//Chinese name
	private String 	name2;	//English name
	private	Integer	gender;
	private	String 	nationality;
	private	String 	phone1;			//telephone
	private	String 	phone2;			//cellphone
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
	
	public String getAcctName() {
		return acctName;
	}
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}
	
	public Long getGlobalId() {
		return globalId;
	}
	public void setGlobalId(Long globalId) {
		this.globalId = globalId;
	}
	public String getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPobProvince() {
		return pobProvince;
	}
	public void setPobProvince(String pobProvince) {
		this.pobProvince = pobProvince;
	}
	public String getPobCity() {
		return pobCity;
	}
	public void setPobCity(String pobCity) {
		this.pobCity = pobCity;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	public Integer getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(Integer memberStatus) {
		this.memberStatus = memberStatus;
	}
	public Date getMemberApplyDate() {
		return memberApplyDate;
	}
	public void setMemberApplyDate(Date memberApplyDate) {
		this.memberApplyDate = memberApplyDate;
	}
	public Date getMemberApprovedDate() {
		return memberApprovedDate;
	}
	public void setMemberApprovedDate(Date memberApprovedDate) {
		this.memberApprovedDate = memberApprovedDate;
	}
	public Date getMemberActiveDate() {
		return memberActiveDate;
	}
	public void setMemberActiveDate(Date memberActiveDate) {
		this.memberActiveDate = memberActiveDate;
	}
	public Date getMemberInactiveDate() {
		return memberInactiveDate;
	}
	public void setMemberInactiveDate(Date memberInactiveDate) {
		this.memberInactiveDate = memberInactiveDate;
	}
	public Date getMemberPendingDate() {
		return memberPendingDate;
	}
	public void setMemberPendingDate(Date memberPendingDate) {
		this.memberPendingDate = memberPendingDate;
	}
	public Date getMemberBannedDate() {
		return memberBannedDate;
	}
	public void setMemberBannedDate(Date memberBannedDate) {
		this.memberBannedDate = memberBannedDate;
	}
	public Integer getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
	}
	
	@Override
	public String toString() {
		return "Member [globalId=" + globalId + ", acctName=" + acctName + ", memberCode=" + memberCode + ", memberId="
				+ memberId + ", name1=" + name1 + ", name2=" + name2 + ", gender=" + gender + ", nationality="
				+ nationality + ", phone1=" + phone1 + ", phone2=" + phone2 + ", wechat=" + wechat + ", email=" + email
				+ ", degree=" + degree + ", occupation=" + occupation + ", dob=" + dob + ", pobProvince=" + pobProvince
				+ ", pobCity=" + pobCity + ", homeAddress=" + homeAddress + ", postalcode=" + postalcode + ", hobbies="
				+ hobbies + ", memberStatus=" + memberStatus + ", memberLevel=" + memberLevel + ", memberApplyDate="
				+ memberApplyDate + ", memberApprovedDate=" + memberApprovedDate + ", memberActiveDate="
				+ memberActiveDate + ", memberInactiveDate=" + memberInactiveDate + ", memberPendingDate="
				+ memberPendingDate + ", memberBannedDate=" + memberBannedDate + "]";
	}
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
}

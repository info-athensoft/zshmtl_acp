package com.athensoft.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.athensoft.member.dao.MemberDao;
import com.athensoft.member.entity.Member;
import com.athensoft.member.entity.MemberStatus;

@Service
public class MemberService {

	private MemberDao memberDao;
	
	@Autowired
	@Qualifier("memberDaoJdbcImpl")
	public void setMemberDao(MemberDao memberDao){
		this.memberDao = memberDao;
	}
	
	public List<Member> getAllMembers() {
		return memberDao.findAll();
	}
	
	public List<Member> getLatestApplyRequest() {
		final int DEFAULT_COUNT = 4;
		return getLatestApplyRequest(DEFAULT_COUNT);
	}
	
	public List<Member> getLatestApplyRequest(int count) {
		String queryString = " AND member_status = "+MemberStatus.APPLIED+" LIMIT "+count;
		return memberDao.findByFilter(queryString);
	}
	
	public List<Member> getLatestOtherRequest() {
		final int DEFAULT_COUNT = 4;
		return getLatestOtherRequest(DEFAULT_COUNT);
	}
	
	public List<Member> getLatestOtherRequest(int count) {
		String queryString = " AND member_status in ("+MemberStatus.PENDING+","+MemberStatus.INACTIVE+","+MemberStatus.APPROVED+")"+" LIMIT "+count;
		return memberDao.findByFilter(queryString);
	}
	

	public Member getMemberByAcctName(String acctName) {
		
		//TODO test
		System.out.println(memberDao.findByAcctName(acctName));
		
		return memberDao.findByAcctName(acctName);
	}
	
	public long countAllMembers(){
		return memberDao.count();
	}

	public void updateMember(Member member) {
		memberDao.update(member);
	}
	
	public List<Member> getMembersByFilter(String queryString){
		return memberDao.findByFilter(queryString);
	}
	
	public void updateMemberGroup(List<Member> memberList) {
		this.memberDao.updateBatch(memberList);
	}
	
}

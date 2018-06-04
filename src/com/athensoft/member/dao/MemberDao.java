package com.athensoft.member.dao;

import java.util.List;

import com.athensoft.member.entity.Member;

public interface MemberDao {
	public List<Member> findAll();
	
	public Member findByAcctName(String acctName);
	
	public int create(Member member);
	public int update(Member member);
	public int delete(Member member);
	
}

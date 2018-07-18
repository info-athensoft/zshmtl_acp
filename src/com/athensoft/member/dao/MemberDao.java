package com.athensoft.member.dao;

import java.util.List;

import com.athensoft.member.entity.Member;

public interface MemberDao {
	public List<Member> findAll();

	public List<Member> findByFilter(String queryString);

	public Member findByAcctName(String acctName);

	public Long count();

	public int create(Member member);

	public int update(Member member);

	public int delete(Member member);

	public int[] updateBatch(List<Member> memberList);

}

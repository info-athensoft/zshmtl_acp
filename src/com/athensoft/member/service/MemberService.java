package com.athensoft.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.athensoft.member.dao.MemberDao;
import com.athensoft.member.entity.Member;

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
	
}

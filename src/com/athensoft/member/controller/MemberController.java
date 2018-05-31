package com.athensoft.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@RequestMapping("/member_list.html")
	public String gotoMemberList(){
		return "member/member_list";
	}
	
}

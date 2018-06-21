package com.athensoft.global.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.athensoft.content.ad.service.AdPostService;
import com.athensoft.content.event.entity.Event;
import com.athensoft.content.event.entity.EventReview;
import com.athensoft.content.event.service.EventReviewService;
import com.athensoft.content.event.service.NewsService;
import com.athensoft.member.entity.Member;
import com.athensoft.member.service.MemberService;

/**
 * The global controller
 * @author Athens
 *
 */
@Controller
public class GlobalController {
	
	private NewsService newsService;
	
	@Autowired
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	
	private EventReviewService eventReviewService;
	
	@Autowired
	public void setEventReviewService(EventReviewService eventReviewService) {
		this.eventReviewService = eventReviewService;
	}
	
	private AdPostService adPostService;
	
	@Autowired
	public void setAdPostService(AdPostService adPostService) {
		this.adPostService = adPostService;
	}
	
	private MemberService memberService;
	
	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	/**
	 * go to home page
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView gotoHome(){
		return gotoHome2();
	}
	
	/**
	 * go to home page
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView gotoHome2(){
		
		//logic - dashboard
		long newsTotalCount = newsService.countAllNews();
		long reviewsTotalCount = eventReviewService.countAllReviews();
		long adpostTotalCount = adPostService.countAllAdpost();
		long memberTotalCount = memberService.countAllMembers();
		
		
		//member request
		List<Member> listMember = memberService.getLatestApplyRequest();
		List<Member> listMember2 = memberService.getLatestOtherRequest();
		
		//latest news
		List<Event> listNews = newsService.getLatestNews();
		
		//latest reviews of news
		List<EventReview> listReviews = eventReviewService.getLatestReview(20);
		
		ModelAndView mav = new ModelAndView();
		
		//data
		Map<String,Object> model = mav.getModel();
		model.put("newsTotalCount", newsTotalCount);
		model.put("reviewsTotalCount", reviewsTotalCount);
		model.put("adpostTotalCount", adpostTotalCount);
		model.put("memberTotalCount", memberTotalCount);
		
		model.put("listMemberRequest", listMember);
		model.put("listMemberOtherRequest", listMember2);
		
		model.put("listNews", listNews);
		
		model.put("listReviews", listReviews);
		
		String viewName = "index";
		mav.setViewName(viewName);
		
		return mav;
	}
	
}

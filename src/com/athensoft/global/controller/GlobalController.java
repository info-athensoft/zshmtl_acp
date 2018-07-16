package com.athensoft.global.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.athensoft.content.ad.entity.AdRequest;
import com.athensoft.content.ad.entity.AdRequestType;
import com.athensoft.content.ad.service.AdPostService;
import com.athensoft.content.ad.service.AdRequestService;
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
	
	@Autowired
	private NewsService newsService;
	
//	@Autowired
//	public void setNewsService(NewsService newsService) {
//		this.newsService = newsService;
//	}
	
	@Autowired
	private EventReviewService eventReviewService;
	
//	@Autowired
//	public void setEventReviewService(EventReviewService eventReviewService) {
//		this.eventReviewService = eventReviewService;
//	}
	
	@Autowired
	private AdPostService adPostService;
	
	@Autowired
	private AdRequestService adRequestService;
	
//	@Autowired
//	public void setAdPostService(AdPostService adPostService) {
//		this.adPostService = adPostService;
//	}
	
	@Autowired
	private MemberService memberService;
	
//	@Autowired
//	public void setMemberService(MemberService memberService) {
//		this.memberService = memberService;
//	}
	
	
	
	/**
	 * go to home page
	 * @return
	 */
	@RequestMapping("/")
	public String gotoHome(){
		return "uaas/login_soft";
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
		
		//ad request
		List<AdRequest> listAdRequest = adRequestService.getLastestApplyRequest();
		List<AdRequest> listAdRequest2 = adRequestService.getLastestOtherRequest();
		
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
		
		model.put("listAdRequest", listAdRequest);
		model.put("listAdOtherRequest", listAdRequest2);
		
		model.put("listMemberRequest", listMember);
		model.put("listMemberOtherRequest", listMember2);
		
		model.put("listNews", listNews);
		
		model.put("listReviews", listReviews);
		
		String viewName = "index";
		mav.setViewName(viewName);
		
		return mav;
	}
	
}

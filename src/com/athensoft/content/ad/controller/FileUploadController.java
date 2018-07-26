package com.athensoft.content.ad.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.athensoft.content.ad.service.FileUploadService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/resource/")
@Log4j
public class FileUploadController {

	//image business type
//	private final String BIZ_LOGO = "BIZ_LOGO";
//	private final String BOOTH_BANNER = "BOOTH_BANNER";
//	private final String BOOTH_COVER = "BOOTH_COVER";
//	private final String BOOTH_SLIDER = "BOOTH_SLIDER";
	
	private final String USER_PROFILE = "USER_PROFILE";
	private final String USER_AD = "USER_AD";
	
	
	@Autowired
	private FileUploadService fileUploadService;
	
	/**
	 * upload files and then create corresponding records
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/image/upload/{imageBizType}", produces = "application/json")
	@ResponseBody
	public Map<String, Object> imageUpload(HttpServletRequest request, @PathVariable final String imageBizType) {
		log.info("entering... /resource/image/upload/"+imageBizType);
		
		String fileUrlFull = "";
		switch(imageBizType){
			case USER_PROFILE :
				fileUrlFull = fileUploadService.uploadImageUserProfile(request);
				break;
				
			case USER_AD :
				fileUrlFull = fileUploadService.uploadImageUserAd(request);
				break;
				
			
				
			default:
				break;
		}
		
		
		
		/* assemble data and view */
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = mav.getModel();
		
		/* set data */
		model.put("url", fileUrlFull);
// 		model.put("jsonrpc", "2.0");
// 		model.put("result", "OK");
// 		model.put("id", "id");
// 		model.put("url", "url");
		
		log.info("exiting... /resource/image/upload/"+imageBizType);
		return model;
	}


}

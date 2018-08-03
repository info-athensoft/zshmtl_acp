package com.athensoft.tag.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.athensoft.content.event.controller.NewsController.NewsGroup;
import com.athensoft.tag.service.TagService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/tag")
@Log4j
public class TagController {
	
	@Autowired
	private TagService tagService;
	
	@RequestMapping(value="/save", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public void saveTag(@RequestBody TagGroup tagGroup){
		log.info("entering... /tag/save");
		
		String tagNameArray = tagGroup.getTagNameArray();
//		tagService.addTag(tag);
		
		System.out.println(">>>>>>>>>>>>>>>>>>"+tagNameArray);
		
		log.info("exiting... /tag/save");
		return ;
	}
	
	private static class TagGroup{
		private String tagNameArray;

		public String getTagNameArray() {
			return tagNameArray;
		}

		public void setTagNameArray(String tagNameArray) {
			this.tagNameArray = tagNameArray;
		}

		@Override
		public String toString() {
			return "TagGroup [tagNameArray=" + tagNameArray + "]";
		}
		
	}
}

package com.athensoft.content.event.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.athensoft.tag.entity.Tag;
import com.athensoft.tag.service.TagService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/event/tag")
public class NewsTagController {
	
	@Autowired
	private TagService tagService;
	
	
	@RequestMapping(value="/save", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public void saveTag(@RequestBody TagGroup tagGroup){
		log.info("entering... /event/tag/save");
		
		String tagNames = tagGroup.getTagNameArray().trim();
		
		String[] tagNameArray = tagNames.split(",");
		
		List<Tag> tagList = new ArrayList<Tag>();
		for(int i=0; i<tagNameArray.length; i++){
			Tag tag = new Tag();
			tag.setTagName(tagNameArray[i]);
			tagList.add(tag);
			tag = null;
		}
		
		tagService.saveNewsTags(tagList, tagGroup.getObjectId());
		
		//System.out.println(">>>>>>>>>>>>>>>>>>"+tagList.toString());
		
		log.info("exiting... /event/tag/save");
		return ;
	}
	
	private static class TagGroup{
		private String tagNameArray;
		private long objectId;

		public String getTagNameArray() {
			return tagNameArray;
		}

		public void setTagNameArray(String tagNameArray) {
			this.tagNameArray = tagNameArray;
		}

		public long getObjectId() {
			return objectId;
		}

		public void setObjectId(long objectId) {
			this.objectId = objectId;
		}

		@Override
		public String toString() {
			return "TagGroup [tagNameArray=" + tagNameArray + ", objectId=" + objectId + "]";
		}
	}
	
}

package com.athensoft.tag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.athensoft.tag.dao.TagDao;
import com.athensoft.tag.entity.Tag;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class TagService {
	@Autowired
	@Qualifier("tagDaoJdbcImpl")
	private TagDao tagDao;
	
	public List<Tag> getAllTag(){
		return tagDao.findAll();
	}
	
	public void addTag(Tag tag){
		tagDao.create(tag);
	}
	
	public void saveTags(List<Tag> tagList){
		int[] s = tagDao.saveBatch(tagList);
		//Arrays.asList(s).stream().forEach(System.out::println);
		for(int i=0 ; i<s.length; i++){
			System.out.println(s[i]);
		}
	}
	
	public List<Tag> getAffectedTags(List<Tag> tagList, int[] affectedRows){
		if(tagList.size()!=affectedRows.length) return null;
		log.info("WARNING: TagList.size must equals to length of affectedRows");
		for(int i=0; i<tagList.size(); i++){
			if(affectedRows[i]==0){
				tagList.remove(i);
			}
		}		
		return tagList;
	}
	
	
}

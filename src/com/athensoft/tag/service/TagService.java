package com.athensoft.tag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.athensoft.tag.dao.TagDao;
import com.athensoft.tag.entity.Tag;

@Service
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
}

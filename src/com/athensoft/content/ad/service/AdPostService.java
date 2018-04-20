package com.athensoft.content.ad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.athensoft.content.ad.dao.AdPostDao;
import com.athensoft.content.ad.entity.AdPost;

@Service
public class AdPostService {
	
	@Autowired
	@Qualifier("adPostDaoJdbcImpl")
	private AdPostDao adPostDao; 
	
	public List<AdPost> getAdPostList(){
		return adPostDao.findAll();
	}
}

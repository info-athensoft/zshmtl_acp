package com.athensoft.content.ad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.athensoft.content.ad.dao.AdPostDao;
import com.athensoft.content.ad.entity.AdPost;
import com.athensoft.content.event.entity.News;

@Service
public class AdPostService {
	
	@Autowired
	@Qualifier("adPostDaoJdbcImpl")
	private AdPostDao adPostDao; 
	
	public List<AdPost> getAdPostList(){
		return adPostDao.findAll();
	}
	
	public AdPost getAdPostByAdUUID(String adUUID){
		return adPostDao.findByUUID(adUUID);
	}
	
	public List<AdPost> getAdPostByFilter(String queryString){
		return adPostDao.findByFilter(queryString);
	}
	
	public void createAdPost(AdPost adpost){
		adPostDao.create(adpost);
	}
	
	public void updateAdPost(AdPost adpost){
		adPostDao.update(adpost);
	}
	
	public void updateAdPostGroup(List<AdPost> adPostList) {
		this.adPostDao.updateBatch(adPostList);
	}
	
	public long countAllAdpost(){
		return adPostDao.count();
	}
	
}

package com.athensoft.content.ad.dao;

import java.util.List;

import com.athensoft.content.ad.entity.AdPost;

public interface AdPostDao {
	public List<AdPost> findAll();
	
	public List<AdPost> findByFilter(String queryString);
	
	public AdPost findById(Long globalId);
	
	public AdPost findByUUID(String adUUID);
	
	public long count();
	
	public int create(AdPost adPostObj);
	
	public int update(AdPost adPostObj);
	
	public int delete(AdPost adPostObj);
	
	public int delete(Long globalId);
	
	public void updateBatch(List<AdPost> adPostList);
}

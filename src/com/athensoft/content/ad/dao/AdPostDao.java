package com.athensoft.content.ad.dao;

import java.util.List;

import com.athensoft.content.ad.entity.AdPost;

public interface AdPostDao {
	public List<AdPost> findAll();
	
	public AdPost findById(Long globalId);
	
	public int create(AdPost adPostObj);
	
	public int update(AdPost adPostObj);
	
	public int delete(AdPost adPostObj);
	
	public int delete(Long globalId);
}

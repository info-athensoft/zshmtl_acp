package com.athensoft.content.ad.dao;

import java.util.List;

import com.athensoft.content.ad.entity.AdRecommend;

public interface AdRecommendDao {
	public List<AdRecommend> findAll();
	
	public List<AdRecommend> findByFilter(String queryString);
	
	public List<AdRecommend> findByPageId(int pageId);
	
	public int create(AdRecommend adRecommend);
	
	public void update(AdRecommend adRecommend);
	
}

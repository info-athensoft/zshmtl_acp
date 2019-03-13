package com.athensoft.content.ad.dao;

import java.util.List;

import com.athensoft.content.ad.entity.AdRecommend;

public interface AdRecommendDao {
	public List<AdRecommend> findAll();
	
	public List<AdRecommend> findByFilter(String queryString);
	
	public List<AdRecommend> findByPageId(int pageId);
	
	public AdRecommend findById(int globalId);
	
	public int create(AdRecommend adRecommend);
	
	public int update(AdRecommend adRecommend);
	
	public int[] updateBatch(List<AdRecommend> adRecommendList);
	
	public int delete(AdRecommend adRecommend);
	
}

package com.athensoft.content.ad.dao;

import java.util.List;

import com.athensoft.content.ad.entity.AdRequest;

public interface AdRequestDao {
	public List<AdRequest> findAll();
	
	public List<AdRequest> findByQuery(String queryString);
	
	public List<AdRequest> findByStatus(int requestStatus);
	
	public List<AdRequest> findByAcctName(String acctName);
	
	public int create(AdRequest adRequest);
	
	public int update(AdRequest adRequest);
	
	public int updateStatus(AdRequest adRequest);
	
	public int delete(long globalId);
}

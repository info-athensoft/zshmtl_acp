package com.athensoft.tag.dao;

import java.util.List;

import com.athensoft.tag.entity.Tag;

public interface TagDao {
	public List<Tag> findAll();
	
	public List<Tag> findByQuery(final String queryString);
	
	public int create(Tag tag);
	
	public int update(Tag tag);
	
	public int[] saveBatch(List<Tag> tagList);
	
	public int[] saveBatch(Tag[] tagArray);
	
	public int delete(Tag tag);
	
	public int delete(long tagId);
}

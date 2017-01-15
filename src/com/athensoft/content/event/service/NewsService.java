package com.athensoft.content.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.athensoft.content.event.dao.NewsDao;
import com.athensoft.content.event.entity.Event;
import com.athensoft.content.event.entity.News;

@Service
public class NewsService {
	
	@Autowired
	@Qualifier("newsDaoJDBCImpl")
	private NewsDao newsDao;

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}
	
	public News getNewsById(long globalId){
		return (News)newsDao.findById(globalId);
	}
	
	public News getNewsByEventUUID(String eventUUID){
		return (News)newsDao.findByEventUUID(eventUUID);
	}
	
	public List<Event> getAllNews(){
		return newsDao.findAll();
	}
}

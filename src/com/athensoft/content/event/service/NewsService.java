package com.athensoft.content.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.athensoft.content.event.dao.NewsDao;
import com.athensoft.content.event.entity.Event;
import com.athensoft.content.event.entity.News;

/**
 * News Service
 * @author Athens
 * @version 1.0
 */

@Service
public class NewsService {
	
	/**
	 * DAO of News
	 */
	@Autowired
	@Qualifier("newsDaoJdbcImpl")
	private NewsDao newsDao;

	
	/**
	 * get a news object by its globalId
	 * @param globalId the globalId of news object
	 * @return the target News object
	 */
	public News getNewsById(long globalId){
		return (News)newsDao.findById(globalId);
	}
	
	/**
	 * get a news object by its eventUUID
	 * @param eventUUID the eventUUID of news object
	 * @return the target News object
	 */
	public News getNewsByEventUUID(String eventUUID){
		return (News)newsDao.findByEventUUID(eventUUID);
	}
	
	/**
	 * get news objects by search criteria
	 * @param queryString search criteria for qualified news objects
	 * @return list of news object
	 */
	public List<Event> getNewsByFilter(String queryString){
		return newsDao.findByFilter(queryString);
	}
	
	/**
	 * get latest news for shortcut control panel, shows 8 entries by default
	 * @param queryString
	 * @return
	 */
	public List<Event> getLatestNews(){
		final int DEFAULT_COUNT = 8;
		return getLatestNews(DEFAULT_COUNT);
	}
	
	/**
	 * get latest news for shortcut control panel by specified number
	 * @param count
	 * @return
	 */
	public List<Event> getLatestNews(int count){
		String queryString = " ORDER BY post_datetime DESC LIMIT "+count;
		return newsDao.findByFilter(queryString);
	}

	/**
	 * get all news objects<p>
	 * WARNING: DO NOT USE THIS METHOD IN PRODUCT. JUST FOR TEST.
	 * @return list of news objects
	 */
	public List<Event> getAllNews(){
		return newsDao.findAll();
	}
	
	/**
	 * get total count of all news
	 * @return
	 */
	public long countAllNews(){
		return newsDao.count();
	}
	
	/**
	 * create a news object and persist it
	 * @param news a news object to persist
	 */
	public void createNews(News news) {
		this.newsDao.create(news);
		return;
	}

	/**
	 * update a news object and persist it
	 * @param news a news object to persist
	 */
	public void updateNews(News news) {
		this.newsDao.update(news);
	}
	
	/**
	 * update news objects and persist them in batch
	 * @param newsList
	 */
	public void updateNewsGroup(List<News> newsList) {
		this.newsDao.updateBatch(newsList);
	}

	/**
	 * @param eventUUID
	 */
	public void markNewsStatusDeleted(String eventUUID) {
		this.newsDao.markNewsStatusDeleted(eventUUID);
	}
	
	public void deleteNews(News news) {
		this.newsDao.delete(news);
	}
	
	/**
	 * delete news objects and persist them in batch
	 * @param newsList
	 */
	public void deleteNewsGroup(List<News> newsList) {
		this.newsDao.deleteBatch(newsList);
	}
}

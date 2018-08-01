package com.athensoft.content.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.athensoft.content.event.dao.EventMediaDao;
import com.athensoft.content.event.dao.EventReviewDao;
import com.athensoft.content.event.dao.NewsDao;
import com.athensoft.content.event.entity.Event;
import com.athensoft.content.event.entity.EventMedia;
import com.athensoft.content.event.entity.EventReview;
import com.athensoft.content.event.entity.EventStatus;
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
	
	@Autowired
	@Qualifier("eventMediaDaoJdbcImpl")
	private EventMediaDao eventMediaDao;
	
	@Autowired
	@Qualifier("eventReviewDaoJdbcImpl")
	private EventReviewDao eventReviewDao;

	
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
	
	public List<Event> getAllMarkedDeletedNews(){
		String queryString = " AND event_status ="+ EventStatus.DELETED;
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
		this.newsDao.markDeleted(eventUUID);
	}
	
	@Transactional
	public void deleteNews(News newsDTO) {
		this.newsDao.delete(newsDTO);
		EventMedia eventMediaDTO = new EventMedia();
		eventMediaDTO.setEventUUID(newsDTO.getEventUUID());
		this.eventMediaDao.delete(eventMediaDTO);
		EventReview eventReviewDTO = new EventReview();
		eventReviewDTO.setEventUUID(newsDTO.getEventUUID());
		this.eventReviewDao.delete(eventReviewDTO);
	}
	
	/**
	 * delete news objects and persist them in batch
	 * @param newsList
	 */
	public void deleteNewsGroup(List<News> newsDTOList) {
		this.newsDao.deleteBatch(newsDTOList);
	}
}

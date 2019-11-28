package com.athensoft.content.event.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.athensoft.base.dao.BaseDaoJdbcImpl;
import com.athensoft.content.event.entity.Event;
import com.athensoft.content.event.entity.EventStatus;
import com.athensoft.content.event.entity.News;

@Component
@Qualifier("newsDaoJDBCImpl")
public class NewsDaoJdbcImpl extends BaseDaoJdbcImpl implements NewsDao {
	private static final Logger logger = Logger.getLogger(NewsDaoJdbcImpl.class);

	private static final String TABLE = "event_news";

	@Override
	public List<Event> findAll() {
		String sql = "select * from event_news";

		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		List<Event> x = new ArrayList<Event>();
		try {
			x = jdbc.query(sql, paramSource, new NewsRowMapper());
		} catch (EmptyResultDataAccessException ex) {
			x = null;
		}
		return x;
	}

	@Override
	public List<Event> findByFilter(String queryString) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" SELECT * from " + TABLE);
		sbf.append(" WHERE 1=1 ");
		sbf.append(queryString);
		String sql = sbf.toString();

		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		List<Event> x = new ArrayList<Event>();
		try {
			x = jdbc.query(sql, paramSource, new NewsRowMapper());
		} catch (EmptyResultDataAccessException ex) {
			x = null;
		}
		return x;
	}

	@Override
	public Event findById(long globalId) {
		String sql = "select * from " + TABLE + " where global_id =:global_id";

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("global_id", globalId);

		Event x = null;
		try {
			x = jdbc.queryForObject(sql, paramSource, new NewsRowMapper());
		} catch (EmptyResultDataAccessException ex) {
			x = null;
		}
		return x;
	}

	@Override
	public Event findByEventUUID(String eventUUID) {
		String sql = "select * from " + TABLE + " where event_uuid =:event_uuid";

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("event_uuid", eventUUID);

		Event x = null;
		try {
			x = jdbc.queryForObject(sql, paramSource, new NewsRowMapper());
		} catch (EmptyResultDataAccessException ex) {
			x = null;
		}
		return x;
	}

	@Override
	public Long count() {
		String sql = "SELECT COUNT(*) from " + TABLE;
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		return (Long) jdbc.queryForObject(sql, paramSource, Long.class);
	}

	@Override
	public int create(News news) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("INSERT INTO ").append(TABLE);
		sbf.append(" (");
		sbf.append(" event_uuid,");
		sbf.append(" title,");
		sbf.append(" author,");
		sbf.append(" post_date,");
		sbf.append(" create_date,");
		sbf.append(" modify_date,");
		sbf.append(" delete_date,");
		sbf.append(" view_num,");
		sbf.append(" desc_short,");
		sbf.append(" desc_long,");
		sbf.append(" event_class,");
		sbf.append(" event_status");
		sbf.append(" ) VALUES(");
		sbf.append(" :event_uuid,");
		sbf.append(" :title,");
		sbf.append(" :author,");
		sbf.append(" :post_date,");
		sbf.append(" :create_date,");
		sbf.append(" :modify_date,");
		sbf.append(" :delete_date,");
		sbf.append(" :view_num,");
		sbf.append(" :desc_short,");
		sbf.append(" :desc_long,");
		sbf.append(" :event_class,");
		sbf.append(" :event_status");
		sbf.append(")");
		
		String sql = sbf.toString();

		// final int USER_ACCOUNT_STATUS = 0; //1: registered and active, 0:
		// in-activated, 2: locked, pending
		// final Date dateCreate = new Date();
		// final Date dateLastModified = dateCreate;
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		// paramSource.addValue("global_id", news.getGlobalId());
		paramSource.addValue("event_uuid", news.getEventUUID());
		paramSource.addValue("title", news.getTitle());
		paramSource.addValue("author", news.getAuthor());
		paramSource.addValue("post_date", news.getPostDate());
		paramSource.addValue("create_date", news.getCreateDate());
		paramSource.addValue("modify_date", news.getModifyDate());
		paramSource.addValue("delete_date", news.getDeleteDate());
		paramSource.addValue("view_num", news.getViewNum());
		paramSource.addValue("desc_short", news.getDescShort());
		paramSource.addValue("desc_long", news.getDescLong());
		paramSource.addValue("event_class", news.getEventClass());
		paramSource.addValue("event_status", news.getEventStatus());

		KeyHolder keyholder = new GeneratedKeyHolder();

		return jdbc.update(sql, paramSource, keyholder);
	}

	@Override
	public int update(News news) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("UPDATE " + TABLE + " ");
		sbf.append("SET ");
		sbf.append("title = :title, ");
		sbf.append("author = :author, ");
		sbf.append("post_date = :post_date, ");
		sbf.append("modify_date = :modify_date, ");
		sbf.append("delete_date = :delete_date,");
		sbf.append("view_num = :view_num, ");
		sbf.append("desc_short = :desc_short, ");
		sbf.append("desc_long = :desc_long, ");
		sbf.append("event_class = :event_class, ");
		sbf.append("event_status = :event_status ");
		sbf.append("WHERE ");
		sbf.append("event_uuid = :event_uuid");

		String sql = sbf.toString();
		logger.info(sql);
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("event_uuid", news.getEventUUID());
		paramSource.addValue("title", news.getTitle());
		paramSource.addValue("author", news.getAuthor());
		paramSource.addValue("post_date", news.getPostDate());
		paramSource.addValue("modify_date", news.getModifyDate());
		paramSource.addValue("delete_date", news.getDeleteDate());
		paramSource.addValue("view_num", news.getViewNum());
		paramSource.addValue("desc_short", news.getDescShort());
		paramSource.addValue("desc_long", news.getDescLong());
		paramSource.addValue("event_class", news.getEventClass());
		paramSource.addValue("event_status", news.getEventStatus());

		return jdbc.update(sql, paramSource);

	}

	@Override
	public int[] updateBatch(final List<News> newsList) {
		//String sql = "UPDATE event_news SET event_status=:eventStatus WHERE event_uuid =:eventUUID";
		
		StringBuffer sbf = new StringBuffer();
		sbf.append("UPDATE ").append(TABLE);
		sbf.append(" SET ");
		sbf.append(" event_status = :eventStatus, ");
		sbf.append(" post_date = :postDate, ");
		sbf.append(" modify_date = :modifyDate, ");
		sbf.append(" delete_date = :deleteDate ");
		sbf.append(" WHERE event_uuid =:eventUUID ");
		String sql = sbf.toString();

		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (News x : newsList) {
			parameters.add(new BeanPropertySqlParameterSource(x));
		}

		return jdbc.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	}

	@Override
	public int markDeleted(String eventUUID) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("UPDATE ").append(TABLE);
		sbf.append(" SET ");
		sbf.append(" event_status = :event_status ");
		sbf.append(" WHERE ");
		sbf.append(" event_uuid = :event_uuid");

		String sql = sbf.toString();

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("event_uuid", eventUUID);
		paramSource.addValue("event_status", EventStatus.DELETED);

		return jdbc.update(sql, paramSource);
	}

	@Override
	public int delete(News newsDTO) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("DELETE FROM ").append(TABLE);
		sbf.append(" WHERE ");
		sbf.append(" event_uuid =:eventUUID");
		//String sql = "delete from event_news where event_uuid =:eventUUID";
		
		String sql = sbf.toString();
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("eventUUID", newsDTO.getEventUUID());

		return jdbc.update(sql, paramSource);
	}

	@Override
	public int[] deleteBatch(final List<News> newsList) {
		//String sql = "delete from event_news where event_uuid =:eventUUID";
		
		StringBuffer sbf = new StringBuffer();
		sbf.append("DELETE FROM ").append(TABLE);
		sbf.append(" WHERE ");
		sbf.append(" event_uuid =:eventUUID ");
		
		String sql = sbf.toString();

		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (News x : newsList) {
			parameters.add(new BeanPropertySqlParameterSource(x));
		}

		return jdbc.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	}

	private static class NewsRowMapper implements RowMapper<Event> {
		public Event mapRow(ResultSet rs, int rowNumber) throws SQLException {
			Event x = new News();
			x.setGlobalId(rs.getLong("global_id"));
			x.setEventUUID(rs.getString("event_uuid"));
			x.setTitle(rs.getString("title"));
			x.setAuthor(rs.getString("author"));
			x.setViewNum(rs.getInt("view_num"));
			x.setDescShort(rs.getString("desc_short"));
			x.setDescLong(rs.getString("desc_long"));
			x.setEventClass(rs.getString("event_class"));
			x.setEventStatus(rs.getInt("event_status"));
			
			Timestamp pd = rs.getTimestamp("post_date");
			x.setPostDate(pd == null ? null : new Date(pd.getTime()));
			
			Timestamp cd = rs.getTimestamp("create_date");
			x.setCreateDate(cd == null ? null : new Date(cd.getTime()));
			
			Timestamp md = rs.getTimestamp("modify_date");
			x.setModifyDate(md == null ? null : new Date(md.getTime()));
			
			Timestamp dd = rs.getTimestamp("delete_date");
			x.setDeleteDate(dd == null ? null : new Date(dd.getTime()));

			return x;
		}
	}

	@Override
	public List<Map<String, Integer>> getViewNumStats() {
		// TODO Auto-generated method stub
		return null;
	}

}

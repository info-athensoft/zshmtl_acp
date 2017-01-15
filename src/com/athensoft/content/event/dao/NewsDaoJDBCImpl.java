package com.athensoft.content.event.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.athensoft.content.event.entity.Event;
import com.athensoft.content.event.entity.News;

@Component
@Qualifier("newsDaoJDBCImpl")
public class NewsDaoJDBCImpl implements NewsDao {

	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public List<Event> findAll() {
		String sql = "select * from event_news";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
//		paramSource.addValue("global_id", globalId);
		List<Event> x = new ArrayList<Event>();
		try{
			x = jdbc.query(sql, paramSource, new NewsRowMapper());
		}catch(EmptyResultDataAccessException ex){
			x = null;
		}
		return x;
	}

	@Override
	public Event findById(long globalId) {
		String sql = "select * from event_news where global_id =:global_id";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("global_id", globalId);
		Event x = null;
		try{
			x = jdbc.queryForObject(sql, paramSource, new NewsRowMapper());
		}catch(EmptyResultDataAccessException ex){
			x = null;
		}
		return x;
	}

	@Override
	public Event findByEventUUID(String eventUUID) {
		String sql = "select * from event_news where event_uuid =:event_uuid";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("event_uuid", eventUUID);
		Event x = null;
		try{
			x = jdbc.queryForObject(sql, paramSource, new NewsRowMapper());
		}catch(EmptyResultDataAccessException ex){
			x = null;
		}
		return x;
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}
	
	private static class NewsRowMapper implements RowMapper<Event>{
		public Event mapRow(ResultSet rs, int rowNumber) throws SQLException {
			Event x = new News();
			x.setGlobalId(rs.getLong("global_id"));
			x.setEventUUID(rs.getString("event_uuid"));
			x.setTitle(rs.getString("title"));
			x.setAuthor(rs.getString("author"));
			x.setViewNum(rs.getInt("view_num"));
			x.setDescShort(rs.getString("desc_short"));
			x.setDescLong(rs.getString("desc_long"));
			
				Timestamp ts = rs.getTimestamp("post_datetime");			
			x.setPostDatetime(new Date(ts.getTime()));
			
			x.setEventClass(rs.getString("event_class"));
			x.setEventStatus(rs.getInt("event_status"));
			
            return x;
		}		
	}

}

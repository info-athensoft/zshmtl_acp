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

import com.athensoft.content.event.entity.EventReview;

@Component
@Qualifier("eventReviewDaoJDBCImpl")
public class EventReviewDaoJDBCImpl implements EventReviewDao{
	
private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<EventReview> findAll() {
		String sql = "select * from event_review";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
//		paramSource.addValue("global_id", globalId);
		List<EventReview> x = new ArrayList<EventReview>();
		try{
			x = jdbc.query(sql, paramSource, new EventReviewRowMapper());
		}catch(EmptyResultDataAccessException ex){
			x = null;
		}
		return x;
	}

	@Override
	public List<EventReview> findByFilter(String queryString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventReview findById(long globalId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventReview findByReviewUUID(String reviewUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(EventReview review) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(EventReview review) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EventReview> findByEventUUID(String eventUUID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static class EventReviewRowMapper implements RowMapper<EventReview>{
		public EventReview mapRow(ResultSet rs, int rowNumber) throws SQLException {
			EventReview x = new EventReview();
			x.setGlobalId(rs.getLong("global_id"));
			x.setEventUUID(rs.getString("event_uuid"));
			x.setReviewUUID(rs.getString("review_uuid"));
			x.setCustomerId(rs.getLong("customer_id"));
			x.setReviewContent(rs.getString("review_content"));

			Timestamp ts = rs.getTimestamp("review_datetime");			
			x.setReviewDatetime(new Date(ts.getTime()));

			x.setReviewStatus(rs.getInt("review_status"));
			
            return x;
		}		
	}

}

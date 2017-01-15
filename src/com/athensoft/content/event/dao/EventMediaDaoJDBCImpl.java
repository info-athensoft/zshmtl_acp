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

import com.athensoft.content.event.entity.EventMedia;


@Component
@Qualifier("eventMediaDaoJDBCImpl")
public class EventMediaDaoJDBCImpl implements EventMediaDao {

	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public List<EventMedia> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventMedia findById(long globalId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EventMedia> findByEventUUID(String eventUUID) {
		String sql = "select * from event_media where event_uuid=:event_uuid";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("event_uuid", eventUUID);
		List<EventMedia> x = new ArrayList<EventMedia>();
		try{
			x = jdbc.query(sql, paramSource, new EventMediaRowMapper());
		}catch(EmptyResultDataAccessException ex){
			x = null;
		}
		return x;
	}

	
	private static class EventMediaRowMapper implements RowMapper<EventMedia>{
		public EventMedia mapRow(ResultSet rs, int rowNumber) throws SQLException {
			EventMedia x = new EventMedia();
			x.setMediaId(rs.getLong("media_id"));
			x.setEventUUID(rs.getString("event_uuid"));
			x.setMediaURL(rs.getString("media_url"));
			x.setMediaName(rs.getString("media_name"));
			x.setSortNumber(rs.getInt("sort_number"));
				int intIsPrimaryMedia = rs.getInt("sort_number");
				boolean isPrimaryMedia = intIsPrimaryMedia==1?true:false;
			x.setPrimaryMedia(isPrimaryMedia);
			x.setMediaType(rs.getInt("media_type"));
				Timestamp ts = rs.getTimestamp("post_timestamp");			
			x.setPostTimestamp(new Date(ts.getTime()));
            return x;
		}		
	}
}

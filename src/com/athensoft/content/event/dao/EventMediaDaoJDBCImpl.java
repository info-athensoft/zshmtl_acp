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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
		String sql = "select * from event_media";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		List<EventMedia> x = new ArrayList<EventMedia>();		
		try{
			x = jdbc.query(sql, paramSource, new EventMediaRowMapper());
		}catch(EmptyResultDataAccessException ex){
			x = null;
		}
		return x;
	}

	@Override
	public EventMedia findById(long medialId) {
		String sql = "select * from event_media where media_id=:media_id";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("media_id", medialId);
		EventMedia x = new EventMedia();
		try{
			x = jdbc.queryForObject(sql, paramSource, new EventMediaRowMapper());
		}catch(EmptyResultDataAccessException ex){
			x = null;
		}
		return x;
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
	
	@Override
	public void create(EventMedia media) {
		
	}

	
	private static class EventMediaRowMapper implements RowMapper<EventMedia>{
		public EventMedia mapRow(ResultSet rs, int rowNumber) throws SQLException {
			EventMedia x = new EventMedia();
			x.setMediaId(rs.getLong("media_id"));
			x.setEventUUID(rs.getString("event_uuid"));
			x.setMediaURL(rs.getString("media_url"));
			x.setMediaName(rs.getString("media_name"));
			x.setSortNumber(rs.getInt("sort_number"));
//				int intIsPrimaryMedia = rs.getInt("sort_number");
//				boolean isPrimaryMedia = intIsPrimaryMedia==1?true:false;
			x.setPrimaryMedia(rs.getBoolean("is_primary_media"));
			x.setMediaType(rs.getInt("media_type"));
				Timestamp ts = rs.getTimestamp("post_timestamp");			
			x.setPostTimestamp(new Date(ts.getTime()));
            return x;
		}		
	}


	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(EventMedia media) {
		final String TABLE1 = "event_media";
		
		StringBuffer sbf = new StringBuffer();
		sbf.append("update "+TABLE1+" ");
		sbf.append("set ");
		sbf.append("is_primary_media = :is_primary_media ");
		sbf.append("where ");
		sbf.append("media_id = :media_id");
				
				/*+ "(,author,post_datetime,view_num,desc_short,desc_long,event_class,event_status) ");*/
		
		String sql = sbf.toString();
		
//		final Date dateCreate 			= new Date();
//		final Date dateLastModified 	= dateCreate;
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
//		paramSource.addValue("global_id", news.getGlobalId());
		paramSource.addValue("is_primary_media", media.isPrimaryMedia()?1:0);
		paramSource.addValue("media_id",media.getMediaId());
		
		KeyHolder keyholder = new GeneratedKeyHolder();
		jdbc.update(sql, paramSource, keyholder);
		return;
		
	}

	@Override
	public EventMedia findPrimaryMediaByEventUUID(String eventUUID) {
		//select the eventmedia which is already the primary media 
		String sql = "select * from event_media where event_uuid=:event_uuid and is_primary_media = 1";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("event_uuid", eventUUID);
		EventMedia x = new EventMedia();
		try{
			x = jdbc.queryForObject(sql, paramSource, new EventMediaRowMapper());
		}catch(EmptyResultDataAccessException ex){
			x = null;
		}
		return x;
	}
}

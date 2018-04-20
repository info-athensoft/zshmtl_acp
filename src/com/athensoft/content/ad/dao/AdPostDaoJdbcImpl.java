package com.athensoft.content.ad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.athensoft.content.ad.entity.AdPost;

@Repository
@Qualifier("adPostDaoJdbcImpl")
public class AdPostDaoJdbcImpl implements AdPostDao {
	
	private final String TABLE = "ad_post";
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public List<AdPost> findAll() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("SELECT ");
		sbf.append("global_id, ");
		sbf.append("ad_text, ");
		sbf.append("ad_image, ");
		sbf.append("ad_link, ");
		sbf.append("ad_url, ");
		sbf.append("ad_type, ");
		sbf.append("ad_status, ");
		sbf.append("ad_owner_id, ");
		sbf.append("create_date, ");
		sbf.append("post_date, ");
		sbf.append("expire_date, ");
		sbf.append("modify_date ");
		sbf.append(" FROM "+TABLE);
		String sql = sbf.toString();
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		return jdbc.query(sql,paramSource,new AdPostRowMapper());
	}

	@Override
	public AdPost findById(Long globalId) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("SELECT ");
		sbf.append("global_id, ");
		sbf.append("ad_text, ");
		sbf.append("ad_image, ");
		sbf.append("ad_link, ");
		sbf.append("ad_url, ");
		sbf.append("ad_type, ");
		sbf.append("ad_status, ");
		sbf.append("ad_owner_id, ");
		sbf.append("create_date, ");
		sbf.append("post_date, ");
		sbf.append("expire_date, ");
		sbf.append("modify_date ");
		sbf.append(" FROM "+TABLE);
		sbf.append(" WHERE global_id = :global_id");
		String sql = sbf.toString();
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("global_id", globalId);
		AdPost obj = null;
		try{
			obj = jdbc.queryForObject(sql,paramSource,new AdPostRowMapper());
		}catch(Exception ex){
			obj = null;
		}
		return obj;
	}

	@Override
	public int create(AdPost adPostObj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(AdPost adPostObj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(AdPost adPostObj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long globalId) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	private static class AdPostRowMapper implements RowMapper<AdPost>{
		public AdPost mapRow(ResultSet rs, int rowNumber) throws SQLException {
			AdPost x = new AdPost();
			x.setGlobalId(rs.getLong("global_id"));
			x.setAdText(rs.getString("ad_text"));
			x.setAdLink(rs.getString("ad_link"));
			x.setAdImage(rs.getString("ad_image"));
			x.setAdUrl(rs.getString("ad_url"));
			x.setAdType(rs.getInt("ad_type"));
			x.setAdOwnerId(rs.getLong("ad_owner_id"));
			x.setAdStatus(rs.getInt("ad_status"));
			
				Timestamp cd = rs.getTimestamp("create_date");			
			x.setCreateDate(new Date(cd.getTime()));
				cd = null;
			
				Timestamp pd = rs.getTimestamp("post_date");			
			x.setPostDate(new Date(pd.getTime()));
				pd = null;
				
				Timestamp ed = rs.getTimestamp("expire_date");			
			x.setExpireDate(new Date(ed.getTime()));
				ed = null;
			
				Timestamp md = rs.getTimestamp("modify_date");			
			x.setModifyDate(new Date(md.getTime()));
				md = null;
				
	        return x;
		}		
	}
}

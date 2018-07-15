package com.athensoft.content.ad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.athensoft.content.ad.entity.AdRecommend;

@Repository
@Qualifier("adRecommendDaoJdbcImpl")
public class AdRecommendDaoJdbcImpl implements AdRecommendDao{
	
	private final String TABLE = "ad_recommend";
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public List<AdRecommend> findAll() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("SELECT ");
		sbf.append("global_id, ");
		sbf.append("ad_uuid, ");
		sbf.append("page_id, ");
		sbf.append("page_name, ");
		sbf.append("rcmd_score, ");
		sbf.append("rcmd_rank, ");
		sbf.append("rcmd_status ");
		sbf.append(" FROM "+TABLE);
		sbf.append(" ORDER BY page_id");
		String sql = sbf.toString();
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		return jdbc.query(sql,paramSource,new AdRecommendRowMapper());
	}

	@Override
	public List<AdRecommend> findByPageId(int pageId) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("SELECT ");
		sbf.append("global_id, ");
		sbf.append("ad_uuid, ");
		sbf.append("page_id, ");
		sbf.append("page_name, ");
		sbf.append("rcmd_score, ");
		sbf.append("rcmd_rank, ");
		sbf.append("rcmd_status ");
		sbf.append(" FROM "+TABLE);
		sbf.append(" WHERE page_id=:page_id");
		String sql = sbf.toString();
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("page_id", pageId);
		return jdbc.query(sql,paramSource,new AdRecommendRowMapper());
	}

	@Override
	public AdRecommend findById(int globalId) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("SELECT ");
		sbf.append("global_id, ");
		sbf.append("ad_uuid, ");
		sbf.append("page_id, ");
		sbf.append("page_name, ");
		sbf.append("rcmd_score, ");
		sbf.append("rcmd_rank, ");
		sbf.append("rcmd_status ");
		sbf.append(" FROM "+TABLE);
		sbf.append(" WHERE global_id = :globalId");
		String sql = sbf.toString();
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("globalId", globalId);
		AdRecommend x = new AdRecommend();
		try{
			x= jdbc.queryForObject(sql,paramSource,new AdRecommendRowMapper());
		}catch(Exception ex){
			x = null;
		}
		return x;
	}

	@Override
	public int create(AdRecommend x) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" INSERT INTO ").append(TABLE);
		sbf.append(" (");
		sbf.append(" ad_uuid,");
		sbf.append(" page_id, ");
		sbf.append(" page_name, ");
		sbf.append(" rcmd_score, ");
		sbf.append(" rcmd_rank, ");
		sbf.append(" rcmd_status ");
		sbf.append(" ) VALUES (");
		sbf.append(" :ad_uuid,");
		sbf.append(" :page_id, ");
		sbf.append(" :page_name, ");
		sbf.append(" :rcmd_score, ");
		sbf.append(" :rcmd_rank, ");
		sbf.append(" :rcmd_status ");
		sbf.append(" )");
		
		String sql = sbf.toString();
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		paramSource.addValue("ad_uuid", x.getAdUUID());
		paramSource.addValue("page_id", x.getPageId());
		paramSource.addValue("page_name", x.getPageName());
		paramSource.addValue("rcmd_score", x.getRcmdScore());
		paramSource.addValue("rcmd_rank", x.getRcmdRank());
		paramSource.addValue("rcmd_status", x.getRcmdStatus());
		
		int res = 0;
		try{
			res = jdbc.update(sql, paramSource);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return res;
		
	}

	@Override
	public void update(AdRecommend adRecommend) {
		// TODO Auto-generated method stub
		
	}
	
	private static class AdRecommendRowMapper implements RowMapper<AdRecommend>{
		public AdRecommend mapRow(ResultSet rs, int rowNumber) throws SQLException {
			AdRecommend x = new AdRecommend();
			x.setGlobalId(rs.getInt("global_id"));
			x.setAdUUID(rs.getString("ad_uuid"));
			x.setPageId(rs.getInt("page_id"));
			x.setPageName(rs.getString("page_name"));
			x.setRcmdRank(rs.getInt("rcmd_rank"));
			x.setRcmdScore(rs.getDouble("rcmd_score"));
			x.setRcmdStatus(rs.getInt("rcmd_status"));
	        return x;
		}		
	}

	@Override
	public List<AdRecommend> findByFilter(String queryString) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("SELECT ");
		sbf.append("global_id, ");
		sbf.append("ad_uuid, ");
		sbf.append("page_id, ");
		sbf.append("page_name, ");
		sbf.append("rcmd_score, ");
		sbf.append("rcmd_rank, ");
		sbf.append("rcmd_status ");
		sbf.append(" FROM "+TABLE);
		sbf.append(" WHERE 1=1 ");
		sbf.append(queryString);
		String sql = sbf.toString();
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		List<AdRecommend> x = new ArrayList<AdRecommend>();
		try{
			x = jdbc.query(sql,paramSource,new AdRecommendRowMapper());
		}catch(Exception ex){
			x = null;
		}
		return x;
	}

}

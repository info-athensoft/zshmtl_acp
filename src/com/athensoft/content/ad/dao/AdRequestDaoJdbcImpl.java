package com.athensoft.content.ad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.athensoft.base.dao.BaseDaoJdbcImpl;
import com.athensoft.content.ad.entity.AdRequest;

@Repository
@Qualifier("adRequestDaoJdbcImpl")
public class AdRequestDaoJdbcImpl extends BaseDaoJdbcImpl implements AdRequestDao{

	private static String TABLE = "ad_request";
	
	@Override
	public List<AdRequest> findAll() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("SELECT ");
		sbf.append("global_id,");
		sbf.append("acct_name,");
		sbf.append("request_name,");
		sbf.append("request_phone,");
		sbf.append("request_subject,");
		sbf.append("request_msg,");
		sbf.append("request_date,");
		sbf.append("request_type,");
		sbf.append("request_status");
		sbf.append(" FROM ").append(TABLE);
		
		String sql = sbf.toString();
		List<AdRequest> x = new ArrayList<AdRequest>();
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		try{
			x = jdbc.query(sql, paramSource, new AdRequestRowMapper());
		}catch(Exception ex){
			x = null;
		}
		
		return x;
	}

	@Override
	public List<AdRequest> findByQuery(String queryString) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("SELECT ");
		sbf.append("global_id,");
		sbf.append("acct_name,");
		sbf.append("request_name,");
		sbf.append("request_phone,");
		sbf.append("request_subject,");
		sbf.append("request_msg,");
		sbf.append("request_date,");
		sbf.append("request_type,");
		sbf.append("request_status");
		sbf.append(" FROM ").append(TABLE);
		sbf.append(" WHERE 1=1 ").append(queryString);
		
		String sql = sbf.toString();
		List<AdRequest> x = new ArrayList<AdRequest>();
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		try{
			x = jdbc.query(sql, paramSource, new AdRequestRowMapper());
		}catch(Exception ex){
			x = null;
		}
		
		return x;
	}

	@Override
	public List<AdRequest> findByStatus(int requestStatus) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("SELECT ");
		sbf.append("global_id,");
		sbf.append("acct_name,");
		sbf.append("request_name,");
		sbf.append("request_phone,");
		sbf.append("request_subject,");
		sbf.append("request_msg,");
		sbf.append("request_date,");
		sbf.append("request_type,");
		sbf.append("request_status");
		sbf.append(" FROM ").append(TABLE);
		sbf.append(" WHERE 1=1 ");
		sbf.append(" AND request_status =:request_status");
		
		String sql = sbf.toString();
		List<AdRequest> x = new ArrayList<AdRequest>();
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("request_status", new Integer(requestStatus));
		
		try{
			x = jdbc.query(sql, paramSource, new AdRequestRowMapper());
		}catch(Exception ex){
			x = null;
		}
		
		return x;
	}

	@Override
	public List<AdRequest> findByAcctName(String acctName) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("SELECT ");
		sbf.append("global_id,");
		sbf.append("acct_name,");
		sbf.append("request_name,");
		sbf.append("request_phone,");
		sbf.append("request_subject,");
		sbf.append("request_msg,");
		sbf.append("request_date,");
		sbf.append("request_type,");
		sbf.append("request_status");
		sbf.append(" FROM ").append(TABLE);
		sbf.append(" WHERE 1=1 ");
		sbf.append(" AND acct_name =:acct_name");
		
		String sql = sbf.toString();
		List<AdRequest> x = new ArrayList<AdRequest>();
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("acct_name", acctName);
		
		try{
			x = jdbc.query(sql, paramSource, new AdRequestRowMapper());
		}catch(Exception ex){
			x = null;
		}
		
		return x;
	}

	@Override
	public int create(AdRequest x) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" INSERT INTO ").append(TABLE);
		sbf.append(" (");
		sbf.append("acct_name,");
		sbf.append("request_name,");
		sbf.append("request_phone,");
		sbf.append("request_subject,");
		sbf.append("request_msg,");
		sbf.append("request_date,");
		sbf.append("request_type,");
		sbf.append("request_status");
		sbf.append(" ) VALUES (");
		sbf.append(":acct_name,");
		sbf.append(":request_name,");
		sbf.append(":request_phone,");
		sbf.append(":request_subject,");
		sbf.append(":request_msg,");
		sbf.append(":request_date,");
		sbf.append(":request_type,");
		sbf.append(":request_status");
		sbf.append(" )");
		
		String sql = sbf.toString();
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		paramSource.addValue("acct_name", x.getAcctName());
		paramSource.addValue("request_phone", x.getRequestPhone());
		paramSource.addValue("request_subject", x.getRequestSubject());
		paramSource.addValue("request_msg", x.getRequestMessage());
		paramSource.addValue("request_type", x.getRequestType());
		paramSource.addValue("request_status", x.getRequestStatus());
		paramSource.addValue("request_date", x.getRequestDate());
		
		int res = 0;
		try{
			res = jdbc.update(sql, paramSource);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int update(AdRequest x) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" UPDATE ").append(TABLE);
		sbf.append(" SET acct_name=:acct_name,");
		sbf.append("  request_subject=:request_subject,");
		sbf.append("  request_phone=:request_phone,");
		sbf.append("  request_msg=:request_msg,");
		sbf.append("  request_type=:request_type,");
		sbf.append("  request_status=:request_status,");
		sbf.append("  request_date=:request_date ");
		sbf.append(" WHERE global_id=:global_id");
		
		String sql = sbf.toString();
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		paramSource.addValue("acct_name", x.getAcctName());
		paramSource.addValue("request_phone", x.getRequestPhone());
		paramSource.addValue("request_subject", x.getRequestSubject());
		paramSource.addValue("request_msg", x.getRequestMessage());
		paramSource.addValue("request_type", x.getRequestType());
		paramSource.addValue("request_status", x.getRequestStatus());
		paramSource.addValue("request_date", x.getRequestDate());
		paramSource.addValue("global_id", x.getGlobalId());
		
		try{
			jdbc.update(sql, paramSource);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int updateStatus(AdRequest x) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" UPDATE ").append(TABLE);
		sbf.append(" SET ");
		sbf.append(" request_status=:request_status");
		sbf.append(" WHERE global_id=:global_id");
		
		String sql = sbf.toString();
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		paramSource.addValue("request_status", x.getRequestStatus());
		paramSource.addValue("global_id", x.getGlobalId());
		
		try{
			jdbc.update(sql, paramSource);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int delete(long globalId) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" DELETE FROM ").append(TABLE);
		sbf.append(" WHERE global_id=:global_id");
		
		String sql = sbf.toString();
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		paramSource.addValue("global_id", globalId);
		
		try{
			jdbc.update(sql, paramSource);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return 0;
	}
	
	private static class AdRequestRowMapper implements RowMapper<AdRequest>{
		@Override
		public AdRequest mapRow(ResultSet rs, int rowNumber) throws SQLException {
			AdRequest x = new AdRequest();
			x.setGlobalId(rs.getLong("global_id"));
			x.setAcctName(rs.getString("acct_name"));
			x.setRequestName(rs.getString("request_name"));
			x.setRequestSubject(rs.getString("request_subject"));
			x.setRequestMessage(rs.getString("request_msg"));
			x.setRequestPhone(rs.getString("request_phone"));
			x.setRequestType(rs.getInt("request_type"));
			x.setRequestStatus(rs.getInt("request_status"));
				Timestamp rd = rs.getTimestamp("request_date");			
			x.setRequestDate(new Date(rd.getTime()));
				rd = null;
	        return x;
		}
	}

	@Override
	public List<AdRequest> findByType(int requestType) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("SELECT ");
		sbf.append("global_id,");
		sbf.append("acct_name,");
		sbf.append("request_name,");
		sbf.append("request_phone,");
		sbf.append("request_subject,");
		sbf.append("request_msg,");
		sbf.append("request_date,");
		sbf.append("request_type,");
		sbf.append("request_status");
		sbf.append(" FROM ").append(TABLE);
		sbf.append(" WHERE 1=1 ");
		sbf.append(" AND request_type =:request_type");
		
		String sql = sbf.toString();
		List<AdRequest> x = new ArrayList<AdRequest>();
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("request_type", new Integer(requestType));
		
		try{
			x = jdbc.query(sql, paramSource, new AdRequestRowMapper());
		}catch(Exception ex){
			x = null;
		}
		
		return x;
	}
	
}

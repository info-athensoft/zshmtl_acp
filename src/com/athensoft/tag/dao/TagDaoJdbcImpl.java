package com.athensoft.tag.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.athensoft.base.dao.BaseDaoJdbcImpl;
import com.athensoft.tag.entity.Tag;

@Repository
@Qualifier("tagDaoJdbcImpl")
public class TagDaoJdbcImpl extends BaseDaoJdbcImpl implements TagDao {

	private static final String TABLE = "tag";
	
	@Override
	public List<Tag> findAll() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("SELECT ");
		sbf.append(" tag_id,");
		sbf.append(" tag_name,");
		sbf.append(" tag_num,");
		sbf.append(" tag_score");
		sbf.append(" FROM ");
		sbf.append(TABLE);
		String sql = sbf.toString();
		
		List<Tag> x = null;
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		try{
			x = jdbc.query(sql, paramSource, new TagRowMapper());
			if(null==x){
				x = new ArrayList<Tag>();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return x;
	}

	@Override
	public List<Tag> findByQuery(final String queryString) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("SELECT ");
		sbf.append(" tag_id,");
		sbf.append(" tag_name,");
		sbf.append(" tag_num,");
		sbf.append(" tag_score");
		sbf.append(" FROM ");
		sbf.append(TABLE);
		sbf.append(" WHERE 1=1 ");
		sbf.append(queryString);
		
		String sql = sbf.toString();
		
		List<Tag> x = null;
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		try{
			x = jdbc.query(sql, paramSource, new TagRowMapper());
			if(null==x){
				x = new ArrayList<Tag>();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return x;
	}

	@Override
	public int create(Tag tag) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("INSERT INTO ");
		sbf.append(TABLE);
		sbf.append(" ( ");
		sbf.append(" tag_name,");
		sbf.append(" tag_num,");
		sbf.append(" tag_score");
		sbf.append(" ) VALUES (");
		sbf.append(" :tag_name,");
		sbf.append(" :tag_num,");
		sbf.append(" :tag_score");
		sbf.append(" )");
		String sql = sbf.toString();
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("tag_name", tag.getTagName());
		paramSource.addValue("tag_num", tag.getTagNum());
		paramSource.addValue("tag_score", tag.getTagScore());
		
		return jdbc.update(sql, paramSource);
	}

	@Override
	public int update(Tag tag) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("UPDATE ");
		sbf.append(TABLE);
		sbf.append(" SET ");
		sbf.append(" tag_name=:tag_name,");
		sbf.append(" tag_num=:tag_num,");
		sbf.append(" tag_score=:tag_score");
		sbf.append(" WHERE tag_id=:tag_id ");
		String sql = sbf.toString();
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("tag_id", tag.getTagId());
		paramSource.addValue("tag_name", tag.getTagName());
		paramSource.addValue("tag_num", tag.getTagNum());
		paramSource.addValue("tag_score", tag.getTagScore());
		
		return jdbc.update(sql, paramSource);
	}

	@Override
	public int delete(Tag tag) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long tagId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int[] saveBatch(List<Tag> tagList) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("INSERT IGNORE INTO ").append(TABLE);
		sbf.append(" ( ");
		sbf.append(" tag_name ");
		sbf.append(" ) VALUES (");
		sbf.append(" :tagName ");
		sbf.append(" ) ");
		String sql = sbf.toString();

		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Tag x : tagList) {
			parameters.add(new BeanPropertySqlParameterSource(x));
		}
		
		return jdbc.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	}

	@Override
	public int[] saveBatch(Tag[] tagArray) {
		return new int[1];
	}

	private static class TagRowMapper implements RowMapper<Tag> {
		public Tag mapRow(ResultSet rs, int rowNumber) throws SQLException {
			Tag x = new Tag();
			x.setTagId(rs.getLong("tag_id"));
			x.setTagName(rs.getString("tag_name"));
			x.setTagNum(rs.getLong("tag_num"));
			x.setTagScore(rs.getDouble("tag_score"));
			return x;
		}
	}

}

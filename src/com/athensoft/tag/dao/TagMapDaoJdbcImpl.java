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
import org.springframework.stereotype.Repository;

import com.athensoft.base.dao.BaseDaoJdbcImpl;
import com.athensoft.base.entity.Module;
import com.athensoft.tag.dto.TagMapDTO;
import com.athensoft.tag.entity.Tag;
import com.athensoft.tag.entity.TagMap;

import lombok.extern.log4j.Log4j;

@Repository
@Qualifier("tagMapDaoJdbcImpl")
@Log4j
public class TagMapDaoJdbcImpl extends BaseDaoJdbcImpl implements TagMapDao{
	
	private static final String TABLE = "tag_map";
	
	@Override
	public List<TagMap> findByObjectId(int moduleId, long objectId) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("SELECT ");
		sbf.append(" global_id, ");
		sbf.append(" module_id, ");
		sbf.append(" tag_id, ");
		sbf.append(" object_id, ");
		sbf.append(" tag_name, ");
		sbf.append(" object_code ");
		sbf.append(" FROM ");
		sbf.append(getTableAndViewName(moduleId));
		sbf.append(" WHERE ");
		sbf.append(" object_id=:object_id");
		String sql = sbf.toString();
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("object_id", objectId);
		
		return jdbc.query(sql, paramSource, new TagMapRowMapper());
	}
	
	@Override
	public List<TagMap> findAllByModule(int moduleId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int create(TagMapDTO tagMapDTO) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("INSERT IGNORE INTO ");
		sbf.append(TABLE);
		sbf.append(" (");
		sbf.append(" module_id,");
		sbf.append(" tag_id,");
		sbf.append(" object_id");
		sbf.append(" ) VALUES(");
		sbf.append(" :module_id,");
		sbf.append(" :tag_id,");
		sbf.append(" :object_id");
		sbf.append(" )");
		
		String sql =  sbf.toString();
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("tag_id", tagMapDTO.getTagId());
		paramSource.addValue("object_id", tagMapDTO.getObjectId());
		paramSource.addValue("module_id", tagMapDTO.getModuleId());
		
		return jdbc.update(sql, paramSource);
	}


	@Override
	public int[] createBatch(List<TagMapDTO> tagMapDTOList) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("INSERT IGNORE INTO ");
		sbf.append(TABLE);
		sbf.append(" (");
		sbf.append(" module_id,");
		sbf.append(" tag_id,");
		sbf.append(" object_id");
		sbf.append(" ) VALUES(");
		sbf.append(" :moduleId,");
		sbf.append(" :tagId,");
		sbf.append(" :objectId");
		sbf.append(" )");
		String sql = sbf.toString();

		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (TagMapDTO x : tagMapDTOList) {
			parameters.add(new BeanPropertySqlParameterSource(x));
		}
		
		return jdbc.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	}


	@Override
	public int deleteByObjectId(int moduleId, long objectId) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("DELETE FROM ");
		sbf.append(TABLE);
		sbf.append(" WHERE 1=1 ");
		sbf.append(" AND module_id=:module_id ");
		sbf.append(" AND object_id=:object_id ");
		String sql = sbf.toString();
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("object_id", objectId);
		paramSource.addValue("module_id", moduleId);
		
		return jdbc.update(sql, paramSource);
	}


	private static class TagMapRowMapper implements RowMapper<TagMap> {
		public TagMap mapRow(ResultSet rs, int rowNumber) throws SQLException {
			TagMap x = new TagMap();
			x.setGlobalId(rs.getLong("global_id"));
			x.setModuleId(rs.getInt("module_id"));
			x.setTagId(rs.getLong("tag_id"));
			x.setObjectId(rs.getLong("object_id"));
			x.setTagName(rs.getString("tag_name"));
			x.setObjectCode(rs.getString("object_code"));
			return x;
		}
	}
	
	
	private String getTableAndViewName(final int moduleId){
		String TableAndViewName = "";
		switch(moduleId){
			case Module.NEWS:
				TableAndViewName = "VIEW_NEWS_TAG";
				break;
			case Module.AD:
				TableAndViewName = "VIEW_AD_TAG";
				break;
			default:
				log.info("WARNING: NO SUCH TABLE OR VIEW, PLEASE SPECIFY");
				break;
		}
		return TableAndViewName;
	}

}

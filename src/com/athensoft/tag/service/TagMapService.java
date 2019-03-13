package com.athensoft.tag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.athensoft.tag.dao.TagMapDao;
import com.athensoft.tag.entity.TagMap;

@Service
public class TagMapService {

	@Autowired
	@Qualifier("tagMapDaoJdbcImpl")
	private TagMapDao tagMapDao;
	
	public String getTagNames(int moduleId, long objectId){
		List<TagMap> tagMapList = tagMapDao.findByObjectId(moduleId, objectId);
		
		if(tagMapList==null||tagMapList.size()==0){
			return "";
		}
		
		String tagNames = "";
		StringBuffer sbf = new StringBuffer();
		for(TagMap tm : tagMapList){
			sbf.append(tm.getTagName());
			sbf.append(",");
		}
		tagNames = sbf.toString();
		return tagNames.substring(0,tagNames.length()-1);
	}
	
	public List<TagMap> getTagsByObjectId(int moduleId, long objectId){
		return tagMapDao.findByObjectId(moduleId, objectId);
	}
}

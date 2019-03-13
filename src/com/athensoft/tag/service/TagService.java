package com.athensoft.tag.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.athensoft.base.entity.Module;
import com.athensoft.tag.dao.TagDao;
import com.athensoft.tag.dao.TagMapDao;
import com.athensoft.tag.dto.TagMapDTO;
import com.athensoft.tag.entity.Tag;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class TagService {
	@Autowired
	@Qualifier("tagDaoJdbcImpl")
	private TagDao tagDao;
	
	@Autowired
	@Qualifier("tagMapDaoJdbcImpl")
	private TagMapDao tagMapDao;
	
	public List<Tag> getAllTag(){
		return tagDao.findAll();
	}
	
	public void addTag(Tag tag){
		tagDao.create(tag);
	}
	
	@Transactional
	public void saveNewsTags(List<Tag> tagList, final long objectId){
		int[] s = tagDao.saveBatch(tagList);
//		for(int i=0 ; i<s.length; i++){
//			System.out.println(s[i]);
//		}
		
		//tagList = getAffectedTags(tagList, s);
		
		if(tagList==null || tagList.size()==0) return;
		
		String queryString = " AND tag_name IN ("+getTagNames(tagList)+")";
		List<Tag> tagListForId = tagDao.findByQuery(queryString);
		
		List<TagMapDTO> tagMapDTOList = new ArrayList<TagMapDTO>();
		for(int i=0; i<tagList.size(); i++){
			TagMapDTO tm = new TagMapDTO();
			tm.setModuleId(Module.NEWS);
			tm.setTagId(tagListForId.get(i).getTagId());
			tm.setObjectId(objectId);
			tagMapDTOList.add(tm);
			tm=null;
		}
		
		//System.out.println(">>>>>>>>>>>tagMapDTOList length="+tagMapDTOList.size());
		
		tagMapDao.deleteByObjectId(Module.NEWS, objectId);
		tagMapDao.createBatch(tagMapDTOList);
		
	}
	
	public List<Tag> getAffectedTags(List<Tag> tagList, int[] affectedRows){
		if(tagList.size()!=affectedRows.length) {
			log.info("WARNING: TagList.size must equals to length of affectedRows");
			return null;
		}
		
		for(int i=0; i<tagList.size(); i++){
			if(affectedRows[i]==0){
				tagList.remove(i);
			}
		}		
		return tagList;
	}
	
	private String getTagNames(List<Tag> tagList){
		if(tagList==null || tagList.size()==0) return "";
		StringBuffer tagNames = new StringBuffer();
		for(Tag t : tagList){
			tagNames.append("'");
			tagNames.append(t.getTagName());
			tagNames.append("',");
		}
		String s = tagNames.toString();
		return s.substring(0,s.length()-1);
	}
	
	
}

package com.athensoft.content.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.athensoft.tag.dao.TagMapDao;
import com.athensoft.tag.dto.TagMapDTO;

@Service
public class EventTagService {
	
	@Autowired
	@Qualifier("tagMapDaoJdbcImpl")
	private TagMapDao tagMapDao;
	
	public void saveNewsTags(List<TagMapDTO> tagMapDTOList){
		tagMapDao.createBatch(tagMapDTOList);
	}
}

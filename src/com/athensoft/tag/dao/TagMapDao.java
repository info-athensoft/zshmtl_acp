package com.athensoft.tag.dao;

import java.util.List;

import com.athensoft.tag.dto.TagMapDTO;
import com.athensoft.tag.entity.TagMap;

public interface TagMapDao {
	public List<TagMap> findByObjectId(int moduleId, long objectId);
	
	public List<TagMap> findAllByModule(int moduleId);
	
	public int create(TagMapDTO tagMapDTO);
	
	public int[] createBatch(List<TagMapDTO> tagMapDTOList);
	
	public int deleteByObjectId(int moduleId, long objectId);
}

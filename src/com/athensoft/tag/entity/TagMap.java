package com.athensoft.tag.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TagMap {
	private long globalId;
	private int moduleId;
	private long tagId;
	private long objectId;
	
	//from view
	private String objectCode;
	private String tagName;
}

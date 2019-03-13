package com.athensoft.tag.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Tag {
	private long tagId;
	private String tagName;
	private long tagNum;
	private double tagScore;
}

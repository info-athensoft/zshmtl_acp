package com.athensoft.content.event.entity;

public class News extends Event {

	
	public final static int UNKNOWN = 0;
	public final static int PUBLISHED = 1;
	public final static int NOT_PUBLISHED = 2;
	public final static int DELETED = 3;
	
	
	@Override
	public String toString() {
		return "News [toString()=" + super.toString() + "]";
	}
	
	
}

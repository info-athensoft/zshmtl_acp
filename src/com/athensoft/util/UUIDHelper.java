package com.athensoft.util;

import java.util.UUID;

/**
 * UUID utilities
 * @author Athens
 *
 */
public class UUIDHelper {
	
	public static String getUUID(){
		final String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}
	
	public static String getUUID4(){
		//TODO
		return null;
	}
	
	public static void main(String[] args){
		System.out.println(getUUID());
		System.out.println(getUUID());
		System.out.println(getUUID());
	}
}

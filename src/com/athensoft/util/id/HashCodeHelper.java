package com.athensoft.util.id;

public class HashCodeHelper {
	
	public static long getHashCodeForString(String s){
		return s.hashCode();
	}
	
	public static void main(String[] args){
		
		System.out.println(getHashCodeForString("ad"));
		System.out.println(getHashCodeForString("event"));
		System.out.println(getHashCodeForString("news"));
		System.out.println(getHashCodeForString("uaas"));
		System.out.println(getHashCodeForString("member"));
		System.out.println(getHashCodeForString("tag"));
		
		for(int i=0; i<3; i++){
			System.out.println(getHashCodeForString("java基础教程我去你吗个鬼卡说打飞"));
		}
	
		for(int i=0; i<3; i++){
			System.out.println(getHashCodeForString("Aa"));
		}
	
		for(int i=0; i<3; i++){
			System.out.println(getHashCodeForString("BB"));
		}	
	}
}

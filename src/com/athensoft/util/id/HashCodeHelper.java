package com.athensoft.util.id;

public class HashCodeHelper {
	
	public static long getHashCodeForString(String s){
		return s.hashCode();
	}
	
	public static void main(String[] args){
		for(int i=0; i<5; i++){
			System.out.println(getHashCodeForString("java基础教程我去你吗个鬼卡说打飞机阿斯蒂芬爱抚阿斯蒂芬饭卡时代峻峰阿斯蒂芬 奥斯卡打飞机啊手动阀发啊大声道法师地方"));
		}
	
		for(int i=0; i<5; i++){
			System.out.println(getHashCodeForString("Aa"));
		}
	
		for(int i=0; i<5; i++){
			System.out.println(getHashCodeForString("BB"));
		}	
	}
}

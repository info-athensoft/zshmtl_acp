package com.athensoft.util.id;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * UUID utilities
 * @author Athens
 *
 */
public class UUIDHelper {
	
	/**
	 * generate a UUID without '-' symbol
	 * @return
	 */
	public static String getUUID(){
		final String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}
	
	private static final AtomicLong TS = new AtomicLong();
	
	public static long getUniqueLongId() {      
        /*UUID idOne = UUID.randomUUID();
        String str=""+idOne;        
        long uid=str.hashCode();
        String filterStr=""+uid;
        str=filterStr.replaceAll("-", "");
        return Long.parseLong(str);*/
		long micros = System.currentTimeMillis() * 1000;
	    for ( ; ; ) {
	        long value = TS.get();
	        if (micros <= value)
	            micros = value + 1;
	        if (TS.compareAndSet(value, micros))
	            return micros;
	    }
    }
	
	public static long getUniqueLongIdUUID() {      
       UUID idOne = UUID.randomUUID();
        String str=""+idOne;        
        long uid=str.hashCode();
        String filterStr=""+uid;
        str=filterStr.replaceAll("-", "");
        return Long.parseLong(str);
		
    }
	
	/**
	 * generate a UUID of generation 4
	 * @return
	 */
	public static String getUUID4(){
		//TODO
		return null;
	}
	
	public static void main(String[] args){
//		System.out.println(getUUID());
//		System.out.println(getUUID());
//		System.out.println(getUUID());
//		System.out.println(getUniqueLongId());
//		System.out.println(getUniqueLongId());
		for(int i=0; i<20; i++)
			System.out.println(getUniqueLongIdUUID());
		
		
		
	}
}

package com.athensoft.util.time;

import java.util.Calendar;
import java.util.Date;

public class DatetimeHelper {
	public static void main(String[] args){
		Date date = new Date();
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);//设置起时间
	    cal.add(Calendar.YEAR, 1);//增加一年
	    //cd.add(Calendar.DATE, n);//增加一天  
	   //cd.add(Calendar.DATE, -10);//减10天  
	    //cd.add(Calendar.MONTH, n);//增加一个月   
	    System.out.println("输出::"+cal.getTime());
	}
	
	
	/**
	 * change year by offset, if positive then add years by offset number, otherwise subtract years
	 * @param orginalDate
	 * @param offset
	 * @return
	 */
	
	public static Date shiftYear(Date orginalDate, int offset){
		Calendar cal = Calendar.getInstance();
	    cal.setTime(orginalDate);		//set start time
	    cal.add(Calendar.YEAR, offset);	
	    return cal.getTime();
	}
	
	/**
	 * add one calendar year
	 * @param orginalDate
	 * @return
	 */
	public static Date addOneYear(Date orginalDate){
		return shiftYear(orginalDate, 1);
	}
	
	
	
	
}

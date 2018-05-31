package com.athensoft.util.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLHelper {
	
	private static final String REGX_SITE_URL_NO_PROTOCOL = "([\\w|-]+\\.)+[\\w|-]+";
	
	public static String getSiteUrl(String srcURL){
		Pattern pattern = Pattern.compile(REGX_SITE_URL_NO_PROTOCOL);
		Matcher matcher = pattern.matcher(srcURL);
		String result = "";
		if (matcher.find()) {
			result=matcher.group();
		}
		return result;
	}

	public static void main(String[] args) {
//		String s = "https://www.athensoft.com/index?lang=zh_CN";
		String s = "apach.org/tomcat";
		System.out.println(getSiteUrl(s));
	}
}

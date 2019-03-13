package com.athensoft.util.sql;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class OrderbyClause {
	private StringBuffer orderbySQLSbf = new StringBuffer();
	
	public String toSQL(){
		return this.orderbySQLSbf.toString();
	}
		
	public OrderbyClause orderBy(Map<String,String> orderSequeceMap){
		Set<Entry<String,String>> orderSequeceSet = orderSequeceMap.entrySet();
		Iterator<Entry<String,String>> it = orderSequeceSet.iterator();
		StringBuffer sbf = new StringBuffer();
		sbf.append(" ORDER BY");
		while(it.hasNext()){
			Entry<String,String> entry = it.next();
			if(entry.getValue().trim().equals("DESC")){
				sbf.append(" ").append(entry.getKey()).append(" ").append(entry.getValue()).append(",");
			}else{
				sbf.append(" ").append(entry.getKey()).append(",");
			}
		}
		sbf = sbf.deleteCharAt(sbf.length()-1);
		this.orderbySQLSbf = sbf;
		return this;
	}
	
	public OrderbyClause orderBy(List<String> orderSequeceList){
		StringBuffer sbf = new StringBuffer();
		sbf.append(" ORDER BY");
		for(String fieldName : orderSequeceList){
			sbf.append(" ").append(fieldName).append(",");
		}
		sbf = sbf.deleteCharAt(sbf.length()-1);
		this.orderbySQLSbf = sbf;
		return this;
	}
}

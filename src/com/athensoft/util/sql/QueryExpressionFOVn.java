package com.athensoft.util.sql;

import java.util.ArrayList;
import java.util.List;

public class QueryExpressionFOVn extends QueryExpression{
	private List<String> valueNameList = new ArrayList<String>();
	private List<Object> valueList = new ArrayList<Object>();
	private List<Class<?>> valueTypeList = new ArrayList<Class<?>>();
	
	public List<String> getValueNameList() {
		return valueNameList;
	}
	public void setValueNameList(List<String> valueNameList) {
		this.valueNameList = valueNameList;
	}
	public List<Object> getValueList() {
		return valueList;
	}
	public void setValueList(List<Object> valueList) {
		this.valueList = valueList;
	}
	public List<Class<?>> getValueTypeList() {
		return valueTypeList;
	}
	public void setValueTypeList(List<Class<?>> valueTypeList) {
		this.valueTypeList = valueTypeList;
	}
	
}

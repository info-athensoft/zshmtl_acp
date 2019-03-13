package com.athensoft.util.sql;

public class QueryExpression {
	protected String fieldName;
	protected String operator;
	
	public String toExprString(){
		return "";
	}
	
	public void printValuePair(){
	}
	
	@SuppressWarnings("unchecked")
	public <T> T castValue(Object valueObject, Class<T> type) {
		return (T)valueObject;
	}
	
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
}

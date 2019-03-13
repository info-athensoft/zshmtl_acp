package com.athensoft.util.sql;

public class QueryExpressionFOV1 extends QueryExpression{
	
	private String valueName;
	private Object value;
	private Class<?> valueType;
	
	public String toExprString(){
		return this.getFieldName()+this.getOperator()+":"+this.getValueName();
	}
	
	public void printValuePair(){
		System.out.println("\t"+this.getValueName()+"->"+this.getValue());
	}
	

	public String getValueName() {
		return valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Class<?> getValueType() {
		return valueType;
	}

	public void setValueType(Class<?> valueType) {
		this.valueType = valueType;
	}
	
}

package com.athensoft.util.sql;

public class WhereClause {
	
	private StringBuffer whereSQLSbf = new StringBuffer();
	
	
	public WhereClause and(QueryExpression qe){
		this.whereSQLSbf.append(" AND "+qe.toExprString());
		return this;
	}
	
	public WhereClause or(QueryExpression qe){
		this.whereSQLSbf.append(" OR "+qe.toExprString());
		return this;
	}
	
	public WhereClause brace(WhereClause wc){
		StringBuffer sbf = new StringBuffer();
		sbf.append("( ");
		sbf.append(this.whereSQLSbf);
		sbf.append(" )");
		this.whereSQLSbf = sbf;
		return this;
	}
	
	public String toSQL(){
		return this.whereSQLSbf.toString();
	}


}

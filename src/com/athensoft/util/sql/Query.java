package com.athensoft.util.sql;

public class Query {
	private WhereClause wc;
	private OrderbyClause oc;
	
	public Query(WhereClause wc, OrderbyClause oc){
		this.wc = wc;
		this.oc = oc;
	}
	
	public Query(WhereClause wc){
		this.wc = wc;
	}
	
	
	public String getWhereSQL(){
		return this.wc.toSQL();
	}
	
	public String getOrderbySQL(){
		return this.oc.toSQL();
	}
	
	public WhereClause getWc() {
		return wc;
	}
	public void setWc(WhereClause wc) {
		this.wc = wc;
	}
	public OrderbyClause getOc() {
		return oc;
	}
	public void setOc(OrderbyClause oc) {
		this.oc = oc;
	}
	
	
}

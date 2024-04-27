package com.qst.financial.sql;

public enum C {

	EQ,NE,LIKE,DA,DAEQ,IXAO,IXAOEQ,IN;
	
	public static String getSqlWhere(C c){
		switch (c) {
		case EQ:
			return "=";
		case NE:
			return "<>";
		case LIKE:
			return "like";	
		case DA:
			return ">";	
		case DAEQ:
			return ">=";	
		case IXAO:
			return "<";	
		case IXAOEQ:
			return "<=";	
		case IN:
			return "in";	
		default:
			return "=";	
		}
	}
}

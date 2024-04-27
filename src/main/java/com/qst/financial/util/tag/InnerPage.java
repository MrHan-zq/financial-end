package com.qst.financial.util.tag;

import java.io.Serializable;

public class InnerPage implements Serializable{
	private static final long serialVersionUID = 1L;
	private String limit;
	private Object date;
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public Object getDate() {
		return date;
	}
	public void setDate(Object date) {
		this.date = date;
	}
}

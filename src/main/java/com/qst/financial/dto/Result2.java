package com.qst.financial.dto;

import java.io.Serializable;

public class Result2  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private Object date;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getDate() {
		return date;
	}
	public void setDate(Object date) {
		this.date = date;
	}
}

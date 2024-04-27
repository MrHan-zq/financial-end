package com.qst.financial.dto;

import java.io.Serializable;

public class Result  implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer type;
	private String dw;
	private Object data;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getDw() {
		return dw;
	}
	public void setDw(String dw) {
		this.dw = dw;
	}
}

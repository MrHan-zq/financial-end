package com.qst.financial.core;


/**
 * POJO字段封装类
 * @author qst
 * @email 
 */
public class Pram {

	private String file;
	
	private Object value;

	public Pram(){}
	public Pram(String file, Object value){
		this.file = file;
		this.value = value;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	

}

package com.qst.financial.dto;

import java.io.Serializable;

public class Urmtopers implements Serializable{
	private static final long serialVersionUID = 1L;
	private String address;
	private String pros;
	private String city;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPros() {
		return pros;
	}
	public void setPros(String pros) {
		this.pros = pros;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}

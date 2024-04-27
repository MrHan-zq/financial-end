package com.qst.financial.dto;

import java.io.Serializable;

/**
 * app验证码
 * @author qst
 *
 */
public class VerificationCode implements Serializable{
	private static final long serialVersionUID = 1L;
	private String jwt;
	private String code;
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}

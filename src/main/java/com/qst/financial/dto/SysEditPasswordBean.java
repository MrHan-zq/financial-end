package com.qst.financial.dto;

/**
 * @author qst
 * 
 * @类说明：
 */
public class SysEditPasswordBean {

	private String oldpass;
	private String newpass;
	private String cnewpass;

	public String getOldpass() {
		return oldpass;
	}

	public void setOldpass(String oldpass) {
		this.oldpass = oldpass;
	}

	public String getNewpass() {
		return newpass;
	}

	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}

	public String getCnewpass() {
		return cnewpass;
	}

	public void setCnewpass(String cnewpass) {
		this.cnewpass = cnewpass;
	}
}

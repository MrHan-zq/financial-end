package com.qst.financial.dto;

/**
 * @author qst
 * 
 * @类说明：
 */
public class SysDeptBean {

	private Long id;
	private String deptCode;
	private String deptName;
	private String remark;
	private String sysMenuIds;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSysMenuIds() {
		return sysMenuIds;
	}

	public void setSysMenuIds(String sysMenuIds) {
		this.sysMenuIds = sysMenuIds;
	}

}

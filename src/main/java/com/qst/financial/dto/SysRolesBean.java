package com.qst.financial.dto;

/**
 * @author qst
 * 
 * @类说明：
 */
public class SysRolesBean {

	private Long id;
	private String roleCode;
	private String roleName;
	private String remark;

	private String sysMenuIds;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

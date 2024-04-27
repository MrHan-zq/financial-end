package com.qst.financial.util.common;

import java.util.ArrayList;
import java.util.List;

public class TreeObject {

	private Integer id;

	private Integer parentId;

	private String name;

	private String parentName;

	private String resKey;

	private String resUrl;

	private Integer level;

	private String type;

	private String description;

	private String icon;

	private Integer ishide;

	private String btn;

	private String permissionsStatus = "1";// 用于查询角色的权限状态0:有权限1:无权限
	
	private String permissionsStatus2 = "1";// 用于查询用户的权限状态0:有权限1:无权限

	private List<TreeObject> children = new ArrayList<TreeObject>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public List<TreeObject> getChildren() {
		return children;
	}

	public void setChildren(List<TreeObject> children) {
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getResKey() {
		return resKey;
	}

	public void setResKey(String resKey) {
		this.resKey = resKey;
	}

	public String getResUrl() {
		return resUrl;
	}

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIshide() {
		return ishide;
	}

	public void setIshide(Integer ishide) {
		this.ishide = ishide;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getBtn() {
		return btn;
	}

	public void setBtn(String btn) {
		this.btn = btn;
	}

	public String getPermissionsStatus() {
		return permissionsStatus;
	}

	public void setPermissionsStatus(String permissionsStatus) {
		this.permissionsStatus = permissionsStatus;
	}

	public String getPermissionsStatus2() {
		return permissionsStatus2;
	}

	public void setPermissionsStatus2(String permissionsStatus2) {
		this.permissionsStatus2 = permissionsStatus2;
	}
	
}

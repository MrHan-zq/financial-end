package com.qst.financial.dto;

import java.io.Serializable;

/**
 * @author qst
 * 
 * @类说明：
 */
public class SysDictionaryDetailBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;// 名称
	private String remark;// 备注
	private String value;// 值
	private String busDictionaryId;// 关联id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getBusDictionaryId() {
		return busDictionaryId;
	}

	public void setBusDictionaryId(String busDictionaryId) {
		this.busDictionaryId = busDictionaryId;
	}

}

package com.qst.financial.dto;

/**
 * @author qst
 * 
 * @类说明：
 */
public class SysParametersBean {

	private Long id;

	private String cnName;// 参数中文名称
	private String flag;// 标志位:''0'':不可编辑;''1'':可编辑
	private String name;// 字典名称
	private String remark;// 备注
	private String value;// 字典值

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

}

package com.qst.financial.dto;

import java.util.List;

/**
 * @author qst
 * 
 * @类说明：
 */
public class SysDictionaryBean {

	private String id;
	private String cnName;
	private String enName;
	private String flag;
	private String remark;

	private List<SysDictionaryDetailBean> listDictionaryDetailModel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<SysDictionaryDetailBean> getListDictionaryDetailModel() {
		return listDictionaryDetailModel;
	}

	public void setListDictionaryDetailModel(
			List<SysDictionaryDetailBean> listDictionaryDetailModel) {
		this.listDictionaryDetailModel = listDictionaryDetailModel;
	}

}

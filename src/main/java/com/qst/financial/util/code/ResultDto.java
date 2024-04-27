package com.qst.financial.util.code;

import java.util.List;

public class ResultDto<T> {
	private List<T> data;
	private PageUtil pageUtil;
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public PageUtil getPageUtil() {
		return pageUtil;
	}
	public void setPageUtil(PageUtil pageUtil) {
		this.pageUtil = pageUtil;
	}
	
}

package com.qst.financial.util.tag;

import java.io.Serializable;
import java.util.List;

public class PagerKani<T> implements Serializable{
	private static final long serialVersionUID = -2613705016796991725L;
	private Integer curPage;
	private Integer totalPage;
	private Integer pageSize = 10;// 后续系统参数配置
	private Integer totalCount = 0;
	private List<T> listData;
	public Integer getCurPage() {
		return curPage;
	}
	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getListData() {
		return listData;
	}
	public void setListData(List<T> listData) {
		this.listData = listData;
	}
}

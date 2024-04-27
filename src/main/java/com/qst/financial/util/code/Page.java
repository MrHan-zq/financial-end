package com.qst.financial.util.code;

public class Page<T> {
	private T data;
	private PageUtil pageUtil;
	private String limit;
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public PageUtil getPageUtil() {
		return pageUtil;
	}
	public void setPageUtil(PageUtil pageUtil) {
		this.pageUtil = pageUtil;
	}
	public String getLimit() {
		return pageUtil.getLimit();
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	/*public static void main(String[] args) {
		Page<SysDeptBean> page=new Page<SysDeptBean>();
		PageUtil pageUtil=new PageUtil();
		pageUtil.setPageId(2);
		pageUtil.setPageCount(10);
		pageUtil.setRowCount(20);
		SysDeptBean sys=new SysDeptBean();
		sys.setDeptCode("sss");
		page.setData(sys);
		page.setPageUtil(pageUtil);
		System.out.println(page.getLimit());
	}*/
}

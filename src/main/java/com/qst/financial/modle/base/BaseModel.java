package com.qst.financial.modle.base;

import com.qst.financial.util.tag.PageUtil;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 
 * @author yj
 * 
 * @类说明：系统分页工具model
 */
public class BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private PageUtil pageUtil = new PageUtil();

	public PageUtil getPageUtil() {
		return pageUtil;
	}

	public void setPageUtil(PageUtil pageUtil) {
		this.pageUtil = pageUtil;
	}

	// 创建时间
	private Timestamp createTime = null;
	// 修改时间
	private Timestamp updateTime = null;
	// 创建用户
	private String createUser;
	// 修改用户
	private String updateUser;

    private String limit;// 分页
    
    public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	
	public Timestamp getCreateTime() throws Exception {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() throws Exception {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

}
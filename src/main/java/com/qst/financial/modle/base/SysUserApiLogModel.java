package com.qst.financial.modle.base;

import java.util.Date;

public class SysUserApiLogModel {
	
    private Long id;

    private Long userId;
    
    private String userName;

    private String accountName;

    private String apiName;

    private String apiUriPath;

    private Integer status;

    private String errMsg;

    private String reqIp;

    private Date createTime;

    private String reqParams;

    private String userAgentInfo;

    private String classMethodName;

    private String servAddr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName == null ? null : apiName.trim();
    }

    public String getApiUriPath() {
        return apiUriPath;
    }

    public void setApiUriPath(String apiUriPath) {
        this.apiUriPath = apiUriPath == null ? null : apiUriPath.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg == null ? null : errMsg.trim();
    }

    public String getReqIp() {
        return reqIp;
    }

    public void setReqIp(String reqIp) {
        this.reqIp = reqIp == null ? null : reqIp.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReqParams() {
        return reqParams;
    }

    public void setReqParams(String reqParams) {
        this.reqParams = reqParams == null ? null : reqParams.trim();
    }

    public String getUserAgentInfo() {
        return userAgentInfo;
    }

    public void setUserAgentInfo(String userAgentInfo) {
        this.userAgentInfo = userAgentInfo == null ? null : userAgentInfo.trim();
    }

    public String getClassMethodName() {
        return classMethodName;
    }

    public void setClassMethodName(String classMethodName) {
        this.classMethodName = classMethodName == null ? null : classMethodName.trim();
    }

    public String getServAddr() {
        return servAddr;
    }

    public void setServAddr(String servAddr) {
        this.servAddr = servAddr == null ? null : servAddr.trim();
    }
}
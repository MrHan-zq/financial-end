package com.qst.financial.modle.base;

import java.io.Serializable;
import java.util.Date;

public class SysUserModel implements Serializable{

	private static final long serialVersionUID = -4643023575516109235L;

	private Long id;

    private String userName;

    private String accountName;

    private String pwd;

    private Integer locked;

    private Integer accountType;

    private Long createUserId;

    private String description;

    private Date createTime;
    private Long accountPortType;
    private Integer type;
    private String chrildType;
    private Long orgId;
    private String url;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public Long getAccountPortType() {
		return accountPortType;
	}

	public void setAccountPortType(Long accountPortType) {
		this.accountPortType = accountPortType;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getChrildType() {
		return chrildType;
	}

	public void setChrildType(String chrildType) {
		this.chrildType = chrildType;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
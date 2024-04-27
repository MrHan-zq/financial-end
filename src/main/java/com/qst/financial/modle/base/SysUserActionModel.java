package com.qst.financial.modle.base;

import java.io.Serializable;

public class SysUserActionModel implements Serializable {
	
	private static final long serialVersionUID = 6016336119049967634L;

	private Integer resId;

    private Integer userId;

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
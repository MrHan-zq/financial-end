package com.qst.financial.util;

public enum PortTypeEnum {

    //金蝶
	KINGDEE(1L),
    //待定
	ASSETS_AND_LIABILITIES(2L),
	//待定
	KMYE(3L),
	//待定
	DETAIL(4L);
    Long code;

    PortTypeEnum(Long code) {
        this.code = code;
    }

    public Long getCode() {
        return code;
    }
}

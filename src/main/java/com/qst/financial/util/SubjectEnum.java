package com.qst.financial.util;

//报表类别枚举
public enum SubjectEnum {

    //资产负债表
	T_BASI_ASSETS_AND_LIABILITIES(1L),
    //现金流表
	T_BASI_CASH_FLOW(2L),
	//利润表
	T_BASI_PROFIT(3L),
	//明细账
	T_DETAIL(4L),
	//科目余额表
	T_KMYE(5L);
    Long code;

    SubjectEnum(Long code) {
        this.code = code;
    }

    public Long getCode() {
        return code;
    }
}

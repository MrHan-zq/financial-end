package com.qst.financial.util;

//报表类别枚举
public enum SubjectEnum {

    //资产负债表
	t_basi_assets_and_liabilities(1L),
    //现金流表
	t_basi_cash_flow(2L),
	//利润表
	t_basi_profit(3L),
	//明细账
	T_DETAIL(4L),
	//科目余额表
	t_kmye(5L);
    Long code;

    SubjectEnum(Long code) {
        this.code = code;
    }

    public Long getCode() {
        return code;
    }
}

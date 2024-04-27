package com.qst.financial.modle.subject;

public enum BiliRule {	
	YBLFX_A("ZCFZ_T-ZCFZ_BL",null),
	YBLFX_B("ZCFZ_T/ZCFZ_BL","ZCFZ_BL"),
	//YBLFX_C("/ZCFZ_BL","ZCFZ_BL"),
	YBLFX_D("(ZCFZ_A + ZCFZ_D)/ZCFZ_BL","ZCFZ_BL"),
	//YBLFX_E("XJLL-Y/",""),
	YBLFX_F("ZCFZ_AN/ZCFZ_BY","ZCFZ_BY"),
	YBLFX_G("ZCFZ_BY/ZCFZ_CM","ZCFZ_CM"),
	YBLFX_H("ZCFZ_AN/ZCFZ_CM","ZCFZ_CM"),
	YBLFX_I("ZCFZ_BX/(ZCFZ_BX + ZCFZ_CM)","ZCFZ_BX + ZCFZ_CM"),
	YBLFX_J("LR_AD/LR_H","LR_H"),
	YBLFX_K("XJLL_Y/LR_H","LR_H"),
	YBLFX_M("XJLL_Y/ZCFZ_BY","ZCFZ_BY"),
	YBLFX_L("((ZCFZ_G/MONTHS)/LR_A)*DAYS","LR_A"),
	YBLFX_N("((ZCFZ_P/MONTHS)/LR_F)*DAYS","LR_F"),
	YBLFX_O("((ZCFZ_AV/MONTHS)/LR_F)*DAYS","LR_F"),
	YBLFX_P("(ZCFZ_T/MONTHS)/LR_A*DAYS","LR_A"),
	YBLFX_Q("(ZCFZ_BZ/MONTHS)/LR_A*DAYS","LR_A"),
	YBLFX_R("(ZCFZ_AM/MONTHS)/LR_A*DAYS","LR_A"),
	YBLFX_S("(ZCFZ_AN/MONTHS)/LR_A*DAYS","LR_A"),
	YBLFX_T("LR_AF/LR_A","LR_A"),
	YBLFX_W("LR_AF/ZCFZ_AN","ZCFZ_AN"),
	YBLFX_Y("ZCFZ_CM/ZCFZ_AN","ZCFZ_AN");
	
	private String cal ;
	private String jnull ;
	
	private BiliRule(String cal , String jnull){
		this.cal = cal ;
		this.jnull = jnull ;
	}

	public String getCal() {
		return cal;
	}

	public void setCal(String cal) {
		this.cal = cal;
	}

	public String getJnull() {
		return jnull;
	}

	public void setJnull(String jnull) {
		this.jnull = jnull;
	}
	
}

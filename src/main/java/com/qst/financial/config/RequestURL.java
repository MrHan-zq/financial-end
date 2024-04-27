package com.qst.financial.config;


public class RequestURL {
	public static String USERLOGIN="v1/user/login";
	public static String NOTICE="v1/user/notice";
	public static String LOANLIST="v1/loan/list";
	public static String LOANDETAIL="v1/loan/detail";
	public static String LOANSUBMIT="v1/loan/submit";
	public static String LOANGETNEXTSETTING="v1/loan/getNextSetting";
	public static String INDIVIDUALLIST="v1/customer/individual/list";
	public static String INDIVIDUALDETAIL="v1/customer/individual/detail";
	public static String ENTERPRISELIST="v1/customer/enterprise/list";
	public static String ENTERPRISEDETAIL="v1/customer/enterprise/detail";
	public static String AFTERLIST="v1/loan/after/investment/list";
	public static String ADDLOANDETIAL="v1/loan/after/follow/add";
	
	public static String AFTERLISTENTER="v1/loan/after/detail";
	public static String AFTERALL="v1/loan/after/list";
	public static String getRequestURL() throws Exception{
		return PropertyUtils.get("reauestUrl");
	}
}

package com.qst.financial.util;


public enum NeedCaluateModeEum {
	
	S1("生产成本",1,"5001","2","LR_SCCB"),
	S2("生产成本-直接材料",3,"5001.01.01","2","LR_SCZJCL"),
	S3("生产成本-直接人工",3,"5001.01.02","2","LR_SCRGCB"),
	S4("制造费用-工资",2,"5101.06","2","LR_SCRGCB"),
	S5("制造费用-社保",2,"5101.19","2","LR_SCRGCB"),
	S6("制造费用-住房公积金",2,"5101.23","2","LR_SCRGCB"),
	S7("制造费用-折旧费",2,"5101.01","2","LR_ZCZJ"),
	S8("制造费用-水费",2,"5101.04","2","LR_ZCSDF"),
	S9("制造费用-电费",2,"5101.10","2","LR_ZCSDF"),
	S10("制造费用",1,"5101","2","LR_ZCFY"),
	S11("销售费用-业务招待费",2,"6601.10","2","LR_XSYWZDF"),
	S12("销售费用-广告费",2,null,"2","LR_XSGGF"),
	S13("销售费用-促销费",2,null,"2","LR_XSCXF"),
	S14("销售费用-推广费",2,"6601.17","2","LR_XSTGF"),
	S15("销售费用-设计费",2,"6601.20","2","LR_XSSJF"),
	
	S101("利润表最近一年营业收入",0,"LR-A","1","ZCFZ-YYSR"),
	S102("利润表最近一年主营业务成本累计",0,"LR-F","1","ZCFZ-YYCB"),
	S103("资产负债表最近一年应收账款累计",0,"LR-B","1","ZCFZ-ZJYNG"),
	S104("资产负债表最近一年应付账款",0,"LR-B","1","ZCFZ-ZJYNAV")
	
	;
	
	
	private String subjectName ;
	private int subjectLev ; 
	private String subjectCode ;
	private String reprotType ;
	private String codeNo ; 
	
	private NeedCaluateModeEum (String subjectName , int subjectLev , String subjectCode , String reprotType , String codeNo){
		this.subjectName = subjectName ;
		this.subjectLev = subjectLev ;
		this.subjectCode = subjectCode ;
		this.reprotType = reprotType ;
		this.codeNo = codeNo ;
	}
	
	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}
	
	public String getCodeNo() {
		return codeNo;
	}
	
	public void setReprotType(String reprotType) {
		this.reprotType = reprotType;
	}
	
	public String getReprotType() {
		return reprotType;
	}
	
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	public String getSubjectCode() {
		return subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getSubjectLev() {
		return subjectLev;
	}

	public void setSubjectLev(int subjectLev) {
		this.subjectLev = subjectLev;
	}
	
	
}

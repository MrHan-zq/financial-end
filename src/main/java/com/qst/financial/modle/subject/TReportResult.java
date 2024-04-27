package com.qst.financial.modle.subject;

import com.qst.financial.core.TableName;
import com.qst.financial.modle.base.PoModel;

import java.io.Serializable;
import java.math.BigDecimal;

@TableName(name="T_REPORT_RESULT")
public class TReportResult extends PoModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String ryear;
	private String rmonth;
	private String ryearAndMonth;
	private String codeNo;
	private BigDecimal dataValue;
	private String orgId;
	
	public TReportResult(String ryear, String rmonth, String ryearAndMonth,
			String codeNo, BigDecimal dataValue, String orgId) {
		super();
		this.ryear = ryear;
		this.rmonth = rmonth;
		this.ryearAndMonth = ryearAndMonth;
		this.codeNo = codeNo;
		this.dataValue = dataValue;
		this.orgId = orgId;
	}
	public TReportResult() {
	}
	public String getRyear() {
		return ryear;
	}
	public void setRyear(String ryear) {
		this.ryear = ryear;
	}
	public String getRmonth() {
		return rmonth;
	}
	public void setRmonth(String rmonth) {
		this.rmonth = rmonth;
	}
	public String getRyearAndMonth() {
		return ryearAndMonth;
	}
	public void setRyearAndMonth(String ryearAndMonth) {
		this.ryearAndMonth = ryearAndMonth;
	}
	public String getCodeNo() {
		return codeNo;
	}
	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}
	public BigDecimal getDataValue() {
		return dataValue;
	}
	public void setDataValue(BigDecimal dataValue) {
		this.dataValue = dataValue;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "TReportResult [ryear=" + ryear + ", rmonth=" + rmonth
				+ ", ryearAndMonth=" + ryearAndMonth + ", codeNo=" + codeNo
				+ ", dataValue=" + dataValue + ", orgId=" + orgId + "]";
	}
}

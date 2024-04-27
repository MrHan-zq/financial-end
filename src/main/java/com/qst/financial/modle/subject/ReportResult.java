package com.qst.financial.modle.subject;

import com.qst.financial.core.TableName;
import com.qst.financial.modle.base.PoModel;

import java.io.Serializable;

/**
 * 
 * @author yj
 * 结果实体类
 */
@TableName(name = "T_REPORT_RESULT")
public class ReportResult extends PoModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private String ryear;
	private String rmonth;
	private String ryearAndMonth;
	private String codeNo;
	private String dataValue;
	private String orgId;
	private String dataType;
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
	public String getDataValue() {
		return dataValue;
	}
	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
}

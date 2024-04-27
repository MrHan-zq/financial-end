package com.qst.financial.modle.subject;

import com.qst.financial.core.TableName;
import com.qst.financial.modle.base.PoModel;

import java.io.Serializable;

@TableName(name="T_CODE_MAPPER")
public class TCodeMapperBean extends PoModel implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private String basiField;
	private String codeNo;
	public String getBasiField() {
		return basiField;
	}
	public void setBasiField(String basiField) {
		this.basiField = basiField;
	}
	public String getCodeNo() {
		return codeNo;
	}
	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}
}

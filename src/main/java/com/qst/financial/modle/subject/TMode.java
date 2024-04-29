package com.qst.financial.modle.subject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qst.financial.core.TableName;
import com.qst.financial.modle.base.PoModel;

import java.io.Serializable;

/**
 * 文字计算表
 * @author chenHao
 */
@TableName(name = "t_mode") 
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TMode extends PoModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id ;
	
    private String reportType;

    private String reportConten;

    private String modeCondition;

    private String modeContent;

    private String modeValues;

    private String remark;

    private String conditionValues;
    
    private String modeArea ;
    private Integer px ;
    
    public void setModeArea(String modeArea) {
		this.modeArea = modeArea;
	}
    
    public String getModeArea() {
		return modeArea;
	}
    
    public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
    
    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType == null ? null : reportType.trim();
    }

    public String getReportConten() {
        return reportConten;
    }

    public void setReportConten(String reportConten) {
        this.reportConten = reportConten == null ? null : reportConten.trim();
    }

    public String getModeCondition() {
        return modeCondition;
    }

    public void setModeCondition(String modeCondition) {
        this.modeCondition = modeCondition == null ? null : modeCondition.trim();
    }

    public String getModeContent() {
        return modeContent;
    }

    public void setModeContent(String modeContent) {
        this.modeContent = modeContent == null ? null : modeContent.trim();
    }

    public String getModeValues() {
        return modeValues;
    }

    public void setModeValues(String modeValues) {
        this.modeValues = modeValues == null ? null : modeValues.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getConditionValues() {
        return conditionValues;
    }

    public void setConditionValues(String conditionValues) {
        this.conditionValues = conditionValues == null ? null : conditionValues.trim();
    }

	public Integer getPx() {
		return px;
	}

	public void setPx(Integer px) {
		this.px = px;
	}
}
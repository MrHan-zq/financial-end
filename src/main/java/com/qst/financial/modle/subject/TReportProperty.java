package com.qst.financial.modle.subject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qst.financial.core.TableName;
import com.qst.financial.modle.base.PoModel;

import java.io.Serializable;

@TableName(name="t_report_property")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TReportProperty  extends PoModel implements Serializable{
	
    private String proName;

    private String proCode;

    private Integer proColumn;

    private Integer proRow;

    private Integer proMoneyRow;

    private Integer proEnd;

    private Integer proRowOrColumn;

    private Integer proClass;

    private Integer proSubject;
    
    private Integer proPublic;

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName == null ? null : proName.trim();
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode == null ? null : proCode.trim();
    }

    public Integer getProColumn() {
        return proColumn;
    }

    public void setProColumn(Integer proColumn) {
        this.proColumn = proColumn;
    }

    public Integer getProRow() {
        return proRow;
    }

    public void setProRow(Integer proRow) {
        this.proRow = proRow;
    }

    public Integer getProMoneyRow() {
        return proMoneyRow;
    }

    public void setProMoneyRow(Integer proMoneyRow) {
        this.proMoneyRow = proMoneyRow;
    }

    public Integer getProEnd() {
        return proEnd;
    }

    public void setProEnd(Integer proEnd) {
        this.proEnd = proEnd;
    }

    public Integer getProRowOrColumn() {
        return proRowOrColumn;
    }

    public void setProRowOrColumn(Integer proRowOrColumn) {
        this.proRowOrColumn = proRowOrColumn;
    }

    public Integer getProClass() {
        return proClass;
    }

    public void setProClass(Integer proClass) {
        this.proClass = proClass;
    }

    public Integer getProSubject() {
        return proSubject;
    }

    public void setProSubject(Integer proSubject) {
        this.proSubject = proSubject;
    }
    
    public Integer getProPublic() {
        return proPublic;
    }

    public void setProPublic(Integer proPublic) {
        this.proPublic = proPublic;
    }
}
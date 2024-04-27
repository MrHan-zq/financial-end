package com.qst.financial.modle.subject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qst.financial.core.TableName;
import com.qst.financial.modle.base.PoModel;

import java.util.Date;

/**
 * 导入成功文件名表
 * @author chenHao
 */
@TableName(name = "t_report_name") 
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TReportName extends PoModel{
	private static final long serialVersionUID = 1L;

    private Date impTime;

    private String name;

    public Date getImpTime() {
        return impTime;
    }

    public void setImpTime(Date impTime) {
        this.impTime = impTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}
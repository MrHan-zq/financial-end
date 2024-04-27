package com.qst.financial.modle.subject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qst.financial.core.TableName;
import com.qst.financial.modle.base.PoModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 科目余额表
 * @author chenHao
 */
@TableName(name = "T_KMYE") 
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TKmye extends PoModel implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date impTime;

    private String impUser;

    private String batchId;

    private String orgId;

    private String kjyear;

    private String kjmoth;

    private String kjyearMoth;

    private String subjectCode;		//科目代码

    private String subjectName;			//科目名称

    private String hbzl;				//币别

    private BigDecimal qcjfye;			//期初借余额

    private BigDecimal qcdfye;			//期初贷方余额

    private BigDecimal bqjffse;				//本期借方发生额

    private BigDecimal bqdffse;				//本期贷方发生额

    private BigDecimal bnjflj;				//本年借方累计

    private BigDecimal bndflj;				//本年贷方累计

    private BigDecimal qmjfye;				//期末借方余额

    private BigDecimal qmdfye;				//期末贷方余额

    private String ztzb;

    private String fx;

    private Long accountPortType;
    
    private String formatFlag;
    
	public Long getAccountPortType() {
		return accountPortType;
	}

	public void setAccountPortType(Long accountPortType) {
		this.accountPortType = accountPortType;
	}
	
    public Date getImpTime() {
        return impTime;
    }

    public void setImpTime(Date impTime) {
        this.impTime = impTime;
    }

    public String getImpUser() {
        return impUser;
    }

    public void setImpUser(String impUser) {
        this.impUser = impUser == null ? null : impUser.trim();
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getKjyear() {
        return kjyear;
    }

    public void setKjyear(String kjyear) {
        this.kjyear = kjyear == null ? null : kjyear.trim();
    }

    public String getKjmoth() {
        return kjmoth;
    }

    public void setKjmoth(String kjmoth) {
        this.kjmoth = kjmoth == null ? null : kjmoth.trim();
    }

    public String getKjyearMoth() {
        return kjyearMoth;
    }

    public void setKjyearMoth(String kjyearMoth) {
        this.kjyearMoth = kjyearMoth == null ? null : kjyearMoth.trim();
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode == null ? null : subjectCode.trim();
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    public String getHbzl() {
        return hbzl;
    }

    public void setHbzl(String hbzl) {
        this.hbzl = hbzl == null ? null : hbzl.trim();
    }

    public BigDecimal getQcjfye() {
        return qcjfye;
    }

    public void setQcjfye(BigDecimal qcjfye) {
        this.qcjfye = qcjfye;
    }

    public BigDecimal getQcdfye() {
        return qcdfye;
    }

    public void setQcdfye(BigDecimal qcdfye) {
        this.qcdfye = qcdfye;
    }

    public BigDecimal getBqjffse() {
        return bqjffse;
    }

    public void setBqjffse(BigDecimal bqjffse) {
        this.bqjffse = bqjffse;
    }

    public BigDecimal getBqdffse() {
        return bqdffse;
    }

    public void setBqdffse(BigDecimal bqdffse) {
        this.bqdffse = bqdffse;
    }

    public BigDecimal getBnjflj() {
        return bnjflj;
    }

    public void setBnjflj(BigDecimal bnjflj) {
        this.bnjflj = bnjflj;
    }

    public BigDecimal getBndflj() {
        return bndflj;
    }

    public void setBndflj(BigDecimal bndflj) {
        this.bndflj = bndflj;
    }

    public BigDecimal getQmjfye() {
        return qmjfye;
    }

    public void setQmjfye(BigDecimal qmjfye) {
        this.qmjfye = qmjfye;
    }

    public BigDecimal getQmdfye() {
        return qmdfye;
    }

    public void setQmdfye(BigDecimal qmdfye) {
        this.qmdfye = qmdfye;
    }

    public String getZtzb() {
        return ztzb;
    }

    public void setZtzb(String ztzb) {
        this.ztzb = ztzb == null ? null : ztzb.trim();
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx == null ? null : fx.trim();
    }

	public String getFormatFlag() {
		return formatFlag;
	}

	public void setFormatFlag(String formatFlag) {
		this.formatFlag = formatFlag;
	}

}
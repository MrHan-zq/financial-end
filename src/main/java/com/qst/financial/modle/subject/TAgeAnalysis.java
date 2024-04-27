package com.qst.financial.modle.subject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qst.financial.core.TableName;
import com.qst.financial.modle.base.PoModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账款分析表
 * @author chenHao
 */
@TableName(name = "t_age_analysis") 
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TAgeAnalysis extends PoModel {
	private static final long serialVersionUID = 1L;


	 private String projectCode;

	    private String projectName;

	    private BigDecimal balance;

	    private BigDecimal creditPeriod;

	    private BigDecimal creditLine;

	    private BigDecimal notDue1To30Days;

	    private BigDecimal notDue31To90Days;

	    private BigDecimal notDue91To180Days;

	    private BigDecimal notDue181Days;

	    private BigDecimal due1To30Days;

	    private BigDecimal due31To90Days;

	    private BigDecimal due91To180Days;

	    private BigDecimal due181To360Days;

	    private BigDecimal due361To720Days;

	    private BigDecimal due721To1080Days;

	    private BigDecimal due1081To1440Days;

	    private BigDecimal due1441Days;

	    private BigDecimal dueToday;

	    private Integer type;

	    private Date impTime;

	    private String kjyear;

	    private String kjmoth;

	    private String kjyearMoth;

	    private String orgId;

	    private Long accountPortType;

	    private String formatFlag;

	    private String impUser;

	    private BigDecimal kcsljb;

	    private BigDecimal kcslcy;

	    private BigDecimal djjb;

	    private BigDecimal djcy;

	    private BigDecimal due0To90DaysNmJb;

	    private BigDecimal due0To90DaysNmCy;

	    private BigDecimal due91To180DaysNmJb;

	    private BigDecimal due91To180DaysNmCy;

	    private BigDecimal due181To360DaysNmJb;

	    private BigDecimal due181To360DaysNmCy;

	    private BigDecimal due361To720DaysNmJb;

	    private BigDecimal due361To720DaysNmCy;

	    private BigDecimal due721To1080DaysNmJb;

	    private BigDecimal due721To1080DaysNmCy;

	    private BigDecimal due1081DaysNmJb;

	    private BigDecimal due1081DaysNmCy;
	    
	    private BigDecimal notDue721To1080Days;
	    
	    private BigDecimal notDue1081Days;


	    
    public BigDecimal getNotDue721To1080Days() {
		return notDue721To1080Days;
	}

	public void setNotDue721To1080Days(BigDecimal notDue721To1080Days) {
		this.notDue721To1080Days = notDue721To1080Days;
	}

	public BigDecimal getNotDue1081Days() {
		return notDue1081Days;
	}

	public void setNotDue1081Days(BigDecimal notDue1081Days) {
		this.notDue1081Days = notDue1081Days;
	}

	public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getCreditPeriod() {
        return creditPeriod;
    }

    public void setCreditPeriod(BigDecimal creditPeriod) {
        this.creditPeriod = creditPeriod;
    }

    public BigDecimal getCreditLine() {
        return creditLine;
    }

    public void setCreditLine(BigDecimal creditLine) {
        this.creditLine = creditLine;
    }

    public BigDecimal getNotDue1To30Days() {
        return notDue1To30Days;
    }

    public void setNotDue1To30Days(BigDecimal notDue1To30Days) {
        this.notDue1To30Days = notDue1To30Days;
    }

    public BigDecimal getNotDue31To90Days() {
        return notDue31To90Days;
    }

    public void setNotDue31To90Days(BigDecimal notDue31To90Days) {
        this.notDue31To90Days = notDue31To90Days;
    }

    public BigDecimal getNotDue91To180Days() {
        return notDue91To180Days;
    }

    public void setNotDue91To180Days(BigDecimal notDue91To180Days) {
        this.notDue91To180Days = notDue91To180Days;
    }

    public BigDecimal getNotDue181Days() {
        return notDue181Days;
    }

    public void setNotDue181Days(BigDecimal notDue181Days) {
        this.notDue181Days = notDue181Days;
    }

    public BigDecimal getDue1To30Days() {
        return due1To30Days;
    }

    public void setDue1To30Days(BigDecimal due1To30Days) {
        this.due1To30Days = due1To30Days;
    }

    public BigDecimal getDue31To90Days() {
        return due31To90Days;
    }

    public void setDue31To90Days(BigDecimal due31To90Days) {
        this.due31To90Days = due31To90Days;
    }

    public BigDecimal getDue91To180Days() {
        return due91To180Days;
    }

    public void setDue91To180Days(BigDecimal due91To180Days) {
        this.due91To180Days = due91To180Days;
    }

    public BigDecimal getDue181To360Days() {
        return due181To360Days;
    }

    public void setDue181To360Days(BigDecimal due181To360Days) {
        this.due181To360Days = due181To360Days;
    }

    public BigDecimal getDue361To720Days() {
        return due361To720Days;
    }

    public void setDue361To720Days(BigDecimal due361To720Days) {
        this.due361To720Days = due361To720Days;
    }

    public BigDecimal getDue721To1080Days() {
        return due721To1080Days;
    }

    public void setDue721To1080Days(BigDecimal due721To1080Days) {
        this.due721To1080Days = due721To1080Days;
    }

    public BigDecimal getDue1081To1440Days() {
        return due1081To1440Days;
    }

    public void setDue1081To1440Days(BigDecimal due1081To1440Days) {
        this.due1081To1440Days = due1081To1440Days;
    }

    public BigDecimal getDue1441Days() {
        return due1441Days;
    }

    public void setDue1441Days(BigDecimal due1441Days) {
        this.due1441Days = due1441Days;
    }

    public BigDecimal getDueToday() {
        return dueToday;
    }

    public void setDueToday(BigDecimal dueToday) {
        this.dueToday = dueToday;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getImpTime() {
        return impTime;
    }

    public void setImpTime(Date impTime) {
        this.impTime = impTime;
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

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public Long getAccountPortType() {
        return accountPortType;
    }

    public void setAccountPortType(Long accountPortType) {
        this.accountPortType = accountPortType;
    }

    public String getFormatFlag() {
        return formatFlag;
    }

    public void setFormatFlag(String formatFlag) {
        this.formatFlag = formatFlag == null ? null : formatFlag.trim();
    }

    public String getImpUser() {
        return impUser;
    }

    public void setImpUser(String impUser) {
        this.impUser = impUser == null ? null : impUser.trim();
    }

	public BigDecimal getKcsljb() {
		return kcsljb;
	}

	public void setKcsljb(BigDecimal kcsljb) {
		this.kcsljb = kcsljb;
	}

	public BigDecimal getKcslcy() {
		return kcslcy;
	}

	public void setKcslcy(BigDecimal kcslcy) {
		this.kcslcy = kcslcy;
	}

	public BigDecimal getDjjb() {
		return djjb;
	}

	public void setDjjb(BigDecimal djjb) {
		this.djjb = djjb;
	}

	public BigDecimal getDjcy() {
		return djcy;
	}

	public void setDjcy(BigDecimal djcy) {
		this.djcy = djcy;
	}

	public BigDecimal getDue0To90DaysNmJb() {
		return due0To90DaysNmJb;
	}

	public void setDue0To90DaysNmJb(BigDecimal due0To90DaysNmJb) {
		this.due0To90DaysNmJb = due0To90DaysNmJb;
	}

	public BigDecimal getDue0To90DaysNmCy() {
		return due0To90DaysNmCy;
	}

	public void setDue0To90DaysNmCy(BigDecimal due0To90DaysNmCy) {
		this.due0To90DaysNmCy = due0To90DaysNmCy;
	}

	public BigDecimal getDue91To180DaysNmJb() {
		return due91To180DaysNmJb;
	}

	public void setDue91To180DaysNmJb(BigDecimal due91To180DaysNmJb) {
		this.due91To180DaysNmJb = due91To180DaysNmJb;
	}

	public BigDecimal getDue91To180DaysNmCy() {
		return due91To180DaysNmCy;
	}

	public void setDue91To180DaysNmCy(BigDecimal due91To180DaysNmCy) {
		this.due91To180DaysNmCy = due91To180DaysNmCy;
	}

	public BigDecimal getDue181To360DaysNmJb() {
		return due181To360DaysNmJb;
	}

	public void setDue181To360DaysNmJb(BigDecimal due181To360DaysNmJb) {
		this.due181To360DaysNmJb = due181To360DaysNmJb;
	}

	public BigDecimal getDue181To360DaysNmCy() {
		return due181To360DaysNmCy;
	}

	public void setDue181To360DaysNmCy(BigDecimal due181To360DaysNmCy) {
		this.due181To360DaysNmCy = due181To360DaysNmCy;
	}

	public BigDecimal getDue361To720DaysNmJb() {
		return due361To720DaysNmJb;
	}

	public void setDue361To720DaysNmJb(BigDecimal due361To720DaysNmJb) {
		this.due361To720DaysNmJb = due361To720DaysNmJb;
	}

	public BigDecimal getDue361To720DaysNmCy() {
		return due361To720DaysNmCy;
	}

	public void setDue361To720DaysNmCy(BigDecimal due361To720DaysNmCy) {
		this.due361To720DaysNmCy = due361To720DaysNmCy;
	}

	public BigDecimal getDue721To1080DaysNmJb() {
		return due721To1080DaysNmJb;
	}

	public void setDue721To1080DaysNmJb(BigDecimal due721To1080DaysNmJb) {
		this.due721To1080DaysNmJb = due721To1080DaysNmJb;
	}

	public BigDecimal getDue721To1080DaysNmCy() {
		return due721To1080DaysNmCy;
	}

	public void setDue721To1080DaysNmCy(BigDecimal due721To1080DaysNmCy) {
		this.due721To1080DaysNmCy = due721To1080DaysNmCy;
	}

	public BigDecimal getDue1081DaysNmJb() {
		return due1081DaysNmJb;
	}

	public void setDue1081DaysNmJb(BigDecimal due1081DaysNmJb) {
		this.due1081DaysNmJb = due1081DaysNmJb;
	}

	public BigDecimal getDue1081DaysNmCy() {
		return due1081DaysNmCy;
	}

	public void setDue1081DaysNmCy(BigDecimal due1081DaysNmCy) {
		this.due1081DaysNmCy = due1081DaysNmCy;
	}
    
    
}
package com.qst.financial.modle.base;

import com.qst.financial.core.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author yj
 * 机构管理
 */
@TableName(name="T_ORG_INFO")
public class OrgInfo extends PoModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orgName;				//机构名称
	private String shortName;			//简称
	private String orgRemark;						//机构简介
	
	private String businessNum;				//营业号
	private String legalerName;			//法人
	private String paidCapital;			//实缴资本
	private String confusingCapital;			//认缴资本
	private String registeredAddr;				//注册地址
	private String industry;						//所属行业
	private String isList;					//是否上市(0：是；1：否)
	private String orgProperty;				//性质ORG_PROPERTY
	private String resPersonName;				//负责人
	private String orgTotalPer;				//公司人数
	private String resPersonTel;						//负责人电话
	private Date registeredDate;					//注册时间
	private String orgAddr;						//公司地址
	private String shares;						//股票代码
	private String custType;				//客户类型
	private String reportLimit;				//报表标准
	private String parentId;
	private String url;
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getOrgRemark() {
		return orgRemark;
	}
	public void setOrgRemark(String orgRemark) {
		this.orgRemark = orgRemark;
	}
	public String getBusinessNum() {
		return businessNum;
	}
	public void setBusinessNum(String businessNum) {
		this.businessNum = businessNum;
	}
	public String getLegalerName() {
		return legalerName;
	}
	public void setLegalerName(String legalerName) {
		this.legalerName = legalerName;
	}
	public String getPaidCapital() {
		return paidCapital;
	}
	public void setPaidCapital(String paidCapital) {
		this.paidCapital = paidCapital;
	}
	public String getConfusingCapital() {
		return confusingCapital;
	}
	public void setConfusingCapital(String confusingCapital) {
		this.confusingCapital = confusingCapital;
	}
	public String getRegisteredAddr() {
		return registeredAddr;
	}
	public void setRegisteredAddr(String registeredAddr) {
		this.registeredAddr = registeredAddr;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getIsList() {
		return isList;
	}
	public void setIsList(String isList) {
		this.isList = isList;
	}
	public String getOrgProperty() {
		return orgProperty;
	}
	public void setOrgProperty(String orgProperty) {
		this.orgProperty = orgProperty;
	}
	public String getResPersonName() {
		return resPersonName;
	}
	public void setResPersonName(String resPersonName) {
		this.resPersonName = resPersonName;
	}
	public String getOrgTotalPer() {
		return orgTotalPer;
	}
	public void setOrgTotalPer(String orgTotalPer) {
		this.orgTotalPer = orgTotalPer;
	}
	public String getResPersonTel() {
		return resPersonTel;
	}
	public void setResPersonTel(String resPersonTel) {
		this.resPersonTel = resPersonTel;
	}
	public Date getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}
	public String getOrgAddr() {
		return orgAddr;
	}
	public void setOrgAddr(String orgAddr) {
		this.orgAddr = orgAddr;
	}
	public String getShares() {
		return shares;
	}
	public void setShares(String shares) {
		this.shares = shares;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getReportLimit() {
		return reportLimit;
	}
	public void setReportLimit(String reportLimit) {
		this.reportLimit = reportLimit;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}

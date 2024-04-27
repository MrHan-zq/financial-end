package com.qst.financial.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 拼接返回数据
 * @author yj
 *
 */
public class ResultDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String code;        //编码
	private String codeName;	//编码名称
	private BigDecimal bigSumMoney=new BigDecimal("0.00");
	private String sumMoney="0.00";      //金额总数
	private String tbSumMoneyStr="0.00";
	private BigDecimal tbSumMoney=new BigDecimal("0.00");      //同比金额总数
	private BigDecimal hbSumMoney=new BigDecimal("0.00");      //环比金额总数
	private String onRise="0.00";  		//同比上涨或下降
	private String linkRise="0.00"; 		//环比上涨或下降
	private BigDecimal bl=new BigDecimal("0.00");              //比例
	private String yearMoth;        //年月   如果查询总数，可为空
	private Integer year;             //年份    如果查询总数，可为空
	private Integer moth;             //月份    如果查询总数，可为空
	private Integer isCurrent;     //资产(0流动资产，1非流动资产)
	private Integer isDetails;     //是否有详情(0,没有，1有)
	private String dataType;     //app类别
	private Integer listArr ;  //页面显示顺序
	private String useArea ;  //区域划分
	private String survey;        //概况
	private String warn;            //预警
	private String reportType;            //类型
	private String clzz;
	private String dw;                  //单位
	private String remark;
	private String km1;
	private String km2;
	private String km3;
	private String parentId;
	public String getKm3() {
		return km3;
	}

	public void setKm3(String km3) {
		this.km3 = km3;
	}

	public String getKm1() {
		return km1;
	}

	public void setKm1(String km1) {
		this.km1 = km1;
	}

	public String getKm2() {
		return km2;
	}

	public void setKm2(String km2) {
		this.km2 = km2;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTbSumMoneyStr() {
		return tbSumMoneyStr;
	}

	public void setTbSumMoneyStr(String tbSumMoneyStr) {
		this.tbSumMoneyStr = tbSumMoneyStr;
	}

	public BigDecimal getBigSumMoney() {
		return bigSumMoney;
	}

	public void setBigSumMoney(BigDecimal bigSumMoney) {
		this.bigSumMoney = bigSumMoney;
	}

	public BigDecimal getHbSumMoney() {
		return hbSumMoney;
	}

	public void setHbSumMoney(BigDecimal hbSumMoney) {
		this.hbSumMoney = hbSumMoney;
	}

	public BigDecimal getTbSumMoney() {
		return tbSumMoney;
	}

	public void setTbSumMoney(BigDecimal tbSumMoney) {
		this.tbSumMoney = tbSumMoney;
	}

	public void setUseArea(String useArea) {
		this.useArea = useArea;
	}
	
	public String getUseArea() {
		return useArea;
	}
	
	public void setYear(Integer year) {
		this.year = year;
	}
	
	public Integer getYear() {
		return year;
	}
	
	public void setListArr(Integer listArr) {
		this.listArr = listArr;
	}
	
	public Integer getListArr() {
		return listArr;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getSumMoney() {
		return sumMoney;
	}
	public void setSumMoney(String sumMoney) {
		this.sumMoney = sumMoney;
	}
	public String getOnRise() {
		return onRise;
	}
	public void setOnRise(String onRise) {
		this.onRise = onRise;
	}
	public String getLinkRise() {
		return linkRise;
	}
	public void setLinkRise(String linkRise) {
		this.linkRise = linkRise;
	}
	public String getYearMoth() {
		return yearMoth;
	}
	public void setYearMoth(String yearMoth) {
		this.yearMoth = yearMoth;
	}
	public Integer getMoth() {
		return moth;
	}
	public void setMoth(Integer moth) {
		this.moth = moth;
	}
	public Integer getIsCurrent() {
		return isCurrent;
	}
	public void setIsCurrent(Integer isCurrent) {
		this.isCurrent = isCurrent;
	}
	public Integer getIsDetails() {
		return isDetails;
	}
	public void setIsDetails(Integer isDetails) {
		this.isDetails = isDetails;
	}
	
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	public String getDataType() {
		return dataType;
	}

	public String getSurvey() {
		return survey;
	}

	public void setSurvey(String survey) {
		this.survey = survey;
	}

	public String getWarn() {
		return warn;
	}

	public void setWarn(String warn) {
		this.warn = warn;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public BigDecimal getBl() {
		return bl;
	}

	public void setBl(BigDecimal bl) {
		this.bl = bl;
	}

	public String getClzz() {
		return clzz;
	}

	public void setClzz(String clzz) {
		this.clzz = clzz;
	}

	public String getDw() {
		return dw;
	}

	public void setDw(String dw) {
		this.dw = dw;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
}

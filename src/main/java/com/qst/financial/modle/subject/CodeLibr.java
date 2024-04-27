package com.qst.financial.modle.subject;

import com.qst.financial.core.TableName;
import com.qst.financial.modle.base.PoModel;

import java.io.Serializable;

/**
 * t_code_libr表
 * @author why
 *
 */
@TableName(name = "T_CODE_LIBR") 
public class CodeLibr extends PoModel implements Serializable{
	private static final long serialVersionUID = 1L;

	    private String codeName;

	    private String codeNo;

	    private String reportType;

	    private String useArea;

	    private String useAreaRemark;

	    private String dataType;

	    private String dataTypeDesc;

	    private String isCurrent;

	    private String isDetails;

	    private String survey;

	    private String warn;

	    private String parentId;

	    private String clzz;

	    private String basiField;
	    private String km1;
	    private String km2;
	    private String km3;
	    private String remark;
	    
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

		public String getKm3() {
			return km3;
		}

		public void setKm3(String km3) {
			this.km3 = km3;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getBasiField() {
			return basiField;
		}

		public void setBasiField(String basiField) {
			this.basiField = basiField;
		}

		public String getCodeName() {
	        return codeName;
	    }

	    public void setCodeName(String codeName) {
	        this.codeName = codeName == null ? null : codeName.trim();
	    }

	    public String getCodeNo() {
	        return codeNo;
	    }

	    public void setCodeNo(String codeNo) {
	        this.codeNo = codeNo == null ? null : codeNo.trim();
	    }

	    public String getReportType() {
	        return reportType;
	    }

	    public void setReportType(String reportType) {
	        this.reportType = reportType == null ? null : reportType.trim();
	    }

	    public String getUseArea() {
	        return useArea;
	    }

	    public void setUseArea(String useArea) {
	        this.useArea = useArea == null ? null : useArea.trim();
	    }

	    public String getUseAreaRemark() {
	        return useAreaRemark;
	    }

	    public void setUseAreaRemark(String useAreaRemark) {
	        this.useAreaRemark = useAreaRemark == null ? null : useAreaRemark.trim();
	    }

	    public String getDataType() {
	        return dataType;
	    }

	    public void setDataType(String dataType) {
	        this.dataType = dataType == null ? null : dataType.trim();
	    }

	    public String getDataTypeDesc() {
	        return dataTypeDesc;
	    }

	    public void setDataTypeDesc(String dataTypeDesc) {
	        this.dataTypeDesc = dataTypeDesc == null ? null : dataTypeDesc.trim();
	    }

	    public String getIsCurrent() {
	        return isCurrent;
	    }

	    public void setIsCurrent(String isCurrent) {
	        this.isCurrent = isCurrent == null ? null : isCurrent.trim();
	    }

	    public String getIsDetails() {
	        return isDetails;
	    }

	    public void setIsDetails(String isDetails) {
	        this.isDetails = isDetails == null ? null : isDetails.trim();
	    }

	    public String getSurvey() {
	        return survey;
	    }

	    public void setSurvey(String survey) {
	        this.survey = survey == null ? null : survey.trim();
	    }

	    public String getWarn() {
	        return warn;
	    }

	    public void setWarn(String warn) {
	        this.warn = warn == null ? null : warn.trim();
	    }

	    public String getParentId() {
	        return parentId;
	    }

	    public void setParentId(String parentId) {
	        this.parentId = parentId == null ? null : parentId.trim();
	    }

	    public String getClzz() {
	        return clzz;
	    }

	    public void setClzz(String clzz) {
	        this.clzz = clzz == null ? null : clzz.trim();
	    }
}

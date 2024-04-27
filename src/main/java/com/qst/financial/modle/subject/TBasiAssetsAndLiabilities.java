package com.qst.financial.modle.subject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qst.financial.core.TableName;
import com.qst.financial.modle.base.PoModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 资产负债表
 * @author chenHao
 */
@TableName(name = "T_BASI_ASSETS_AND_LIABILITIES") 
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TBasiAssetsAndLiabilities extends PoModel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String isSure;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date impTime;

    private String impUser;

    private String batchId;

    private String orgId;

    private String kjyear;

    private String kjmoth;

    private String kjyearMoth;

    private String jedw;

    private BigDecimal ncLdzcHbzj;								//期初货币资金

    private BigDecimal ncLdzcDqtc;					

    private BigDecimal ncLdzcYspj;					

    private BigDecimal ncLdzcYsfl;					

    private BigDecimal ncLdzcYslx;						

    private BigDecimal ncLdzcYsgl;						

    private BigDecimal ncLdzcYszk;				

    private BigDecimal ncLdzcQtysk;							

    private BigDecimal ncLdzcYfzk;

    private BigDecimal ncLdzcYsbtk;

    private BigDecimal ncLdzcCh;					

    private BigDecimal ncLdzcDtfy;					

    private BigDecimal ncLdzcYnndqcqzqtz;

    private BigDecimal ncLdzcQtldzc;

    private BigDecimal ncLdzcHj;

    private BigDecimal ncCqtcCqgqtz;

    private BigDecimal ncCqtcCqzqtc;

    private BigDecimal ncCqtzHj;

    private BigDecimal ncGdzcYj;

    private BigDecimal ncGdzcLjzj;

    private BigDecimal ncGdzcJz;

    private BigDecimal ncGdzcJzzb;

    private BigDecimal ncGdzcJe;

    private BigDecimal ncGdzcGcwz;

    private BigDecimal ncGdzcZjgc;

    private BigDecimal ncGdzcQl;

    private BigDecimal ncGdzcHj;

    private BigDecimal ncWxzcJe;

    private BigDecimal ncWxzcCqdtfy;

    private BigDecimal ncWxzcQtcqzc;

    private BigDecimal ncWxzcHj;

    private BigDecimal ncDysxDyckjx;

    private BigDecimal ncDysxZczj;

    private BigDecimal ncLdfzDqjk;

    private BigDecimal ncLdfzYfpj;

    private BigDecimal ncLdfzYfzk;

    private BigDecimal ncLdfzYszk;

    private BigDecimal ncLdfzYfgz;

    private BigDecimal ncLdfzYfflf;

    private BigDecimal ncLdfzYfgl;

    private BigDecimal ncLdfzYjsj;

    private BigDecimal ncLdfzQtyfk;

    private BigDecimal ncLdfzYtfy;

    private BigDecimal ncLdfzYjfz;

    private BigDecimal ncLdfzYnndqcqfz;

    private BigDecimal ncLdfzQtldfz;

    private BigDecimal ncLdfzLdfzhj;

    private BigDecimal ncFldfzCqjk;

    private BigDecimal ncFldfzYfzq;

    private BigDecimal ncFldfzCqyfk;

    private BigDecimal ncFldfzZxyfk;

    private BigDecimal ncFldfzQtcqfz;

    private BigDecimal ncFldfzCqfzhj;

    private BigDecimal ncDysxDyskhx;

    private BigDecimal ncDysxFzhj;

    private BigDecimal ncGdqySszb;

    private BigDecimal ncGdqyYghtz;

    private BigDecimal ncGdqySszbje;

    private BigDecimal ncGdqyZbgj;

    private BigDecimal ncGdqyYygj;

    private BigDecimal ncGdqyFdgyj;

    private BigDecimal ncGdqyWfplr;

    private BigDecimal ncGdqySyzqyhj;

    private BigDecimal ncGdqyFzhsyzqy;

    private BigDecimal qmLdzcHbzj;					//期末货币资金

    private BigDecimal qmLdzcDqtc;

    private BigDecimal qmLdzcYspj;

    private BigDecimal qmLdzcYsfl;

    private BigDecimal qmLdzcYslx;

    private BigDecimal qmLdzcYsgl;

    private BigDecimal qmLdzcYszk;

    private BigDecimal qmLdzcQtysk;

    private BigDecimal qmLdzcYfzk;

    private BigDecimal qmLdzcYsbtk;

    private BigDecimal qmLdzcCh;

    private BigDecimal qmLdzcDtfy;

    private BigDecimal qmLdzcYnndqcqzqtz;

    private BigDecimal qmLdzcQtldzc;

    private BigDecimal qmLdzcHj;

    private BigDecimal qmCqtcCqgqtz;

    private BigDecimal qmCqtcCqzqtc;

    private BigDecimal qmCqtzHj;

    private BigDecimal qmGdzcYj;

    private BigDecimal qmGdzcLjzj;

    private BigDecimal qmGdzcJz;

    private BigDecimal qmGdzcJzzb;

    private BigDecimal qmGdzcJe;

    private BigDecimal qmGdzcGcwz;

    private BigDecimal qmGdzcZjgc;

    private BigDecimal qmGdzcQl;

    private BigDecimal qmGdzcHj;

    private BigDecimal qmWxzcJe;

    private BigDecimal qmWxzcCqdtfy;

    private BigDecimal qmWxzcQtcqzc;

    private BigDecimal qmWxzcHj;

    private BigDecimal qmDysxDyckjx;

    private BigDecimal qmDysxZczj;

    private BigDecimal qmLdfzDqjk;

    private BigDecimal qmLdfzYfpj;

    private BigDecimal qmLdfzYfzk;

    private BigDecimal qmLdfzYszk;

    private BigDecimal qmLdfzYfgz;

    private BigDecimal qmLdfzYfflf;

    private BigDecimal qmLdfzYfgl;

    private BigDecimal qmLdfzYjsj;

    private BigDecimal qmLdfzQtyfk;

    private BigDecimal qmLdfzYtfy;

    private BigDecimal qmLdfzYjfz;

    private BigDecimal qmLdfzYnndqcqfz;

    private BigDecimal qmLdfzQtldfz;

    private BigDecimal qmLdfzLdfzhj;

    private BigDecimal qmFldfzCqjk;

    private BigDecimal qmFldfzYfzq;

    private BigDecimal qmFldfzCqyfk;

    private BigDecimal qmFldfzZxyfk;

    private BigDecimal qmFldfzQtcqfz;

    private BigDecimal qmFldfzCqfzhj;

    private BigDecimal qmDysxDyskhx;

    private BigDecimal qmDysxFzhj;

    private BigDecimal qmGdqySszb;

    private BigDecimal qmGdqyYghtz;

    private BigDecimal qmGdqySszbje;

    private BigDecimal qmGdqyZbgj;

    private BigDecimal qmGdqyYygj;

    private BigDecimal qmGdqyFdgyj;

    private BigDecimal qmGdqyWfplr;

    private BigDecimal qmGdqySyzqyhj;

    private BigDecimal qmGdqyFzhsyzqy;

    private BigDecimal ncLdzcJyxjrzc;

    private BigDecimal qmLdzcJyxjrzc;					//交易性金融资产

    private BigDecimal ncFldzcKgcsjrzc;

    private BigDecimal qmFldzcKgcsjrzc;

    private BigDecimal ncFldzcCyzdqtz;

    private BigDecimal qmFldzcCyzdqtz;

    private BigDecimal ncFldzcCqysk;

    private BigDecimal qmFldzcCqysk;

    private BigDecimal ncFldzcCqgqtz;

    private BigDecimal qmFldzcCqgqtz;

    private BigDecimal qmFldzcTzxfdc;

    private BigDecimal ncFldzcTzxfdc;

    private BigDecimal ncFldzcGdzc;

    private BigDecimal qmFldzcGdzc;

    private BigDecimal ncFldzcZjgc;

    private BigDecimal qmFldzcZjgc;

    private BigDecimal ncFldzcGcwz;

    private BigDecimal qmFldzcGcwz;

    private BigDecimal ncFldzcGdzcql;

    private BigDecimal qmFldzcGdzcql;

    private BigDecimal ncFldzcScxswzc;

    private BigDecimal qmFldzcScxswzc;

    private BigDecimal ncFldzcYqzc;

    private BigDecimal qmFldzcYqzc;

    private BigDecimal ncFldzcWxzc;

    private BigDecimal qmFldzcWxzc;

    private BigDecimal ncFldzcKfzc;

    private BigDecimal qmFldzcKfzc;

    private BigDecimal ncFldzcSy;

    private BigDecimal qmFldzcSy;

    private BigDecimal ncFldzcCqdtfy;

    private BigDecimal qmFldzcCqdtfy;

    private BigDecimal ncFldzcDysdszc;

    private BigDecimal qmFldzcDysdszc;

    private BigDecimal ncFldzcQtfldzc;

    private BigDecimal qmFldzcQtfldzc;

    private BigDecimal ncFldzcFldzchj;

    private BigDecimal qmFldzcFldzchj;

    private BigDecimal ncZczj;

    private BigDecimal qmZczj;

    private BigDecimal ncLdfzJyxjrfz;

    private BigDecimal qmLdfzJyxjrfz;

    private BigDecimal ncLdfzYflx;

    private BigDecimal qmLdfzYflx;

    private BigDecimal ncFldfzYjfz;

    private BigDecimal qmFldfzYjfz;

    private BigDecimal ncGdqyKcg;

    private BigDecimal qmGdqyKcg;

    private Long accountPortType;

    private String formatFlag;

    private BigDecimal qmYbfxzb;

    private BigDecimal ncYbfxzb;

    private BigDecimal qmGsymgsqyhj;

    private BigDecimal ncGsymgsqyhj;

    private BigDecimal qmSsgdqy;

    private BigDecimal ncSsgdqy;

    private BigDecimal qmYsjrzc;

    private BigDecimal qmHfwzydszc;

    private BigDecimal qmYxg;

    private BigDecimal qmCydsfz;

    private BigDecimal qmYxz;

    private BigDecimal qmDysy;

    private BigDecimal qmQtqygj;

    private BigDecimal qmQzyxg;

    private BigDecimal qmZcyxz;

    private BigDecimal qmZbgj;

    private BigDecimal qmQtzhsy;

    private BigDecimal qmJyxjrfz;

    private BigDecimal qmYsjrfz;

    private BigDecimal ncYgyjzjlqqbd;

    private BigDecimal qmYgyjzjlqqbd;

    private BigDecimal ncYsjrzc;

    private BigDecimal ncCydsdzc;

    private BigDecimal qmCydsdzc;

    private BigDecimal ncYgyjzjljrfz;

    private BigDecimal qmYgyjzjljrfz;

    private BigDecimal ncYsjrfz;

    private BigDecimal ncCydsdfz;

    private BigDecimal qmCydsdfz;

    private BigDecimal ncQzyxg;

    private BigDecimal ncYxz;

    private BigDecimal ncQtqygj;


    public String getIsSure() {
        return isSure;
    }

    public void setIsSure(String isSure) {
        this.isSure = isSure == null ? null : isSure.trim();
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

    public String getJedw() {
        return jedw;
    }

    public void setJedw(String jedw) {
        this.jedw = jedw == null ? null : jedw.trim();
    }

    public BigDecimal getNcLdzcHbzj() {
        return ncLdzcHbzj;
    }

    public void setNcLdzcHbzj(BigDecimal ncLdzcHbzj) {
        this.ncLdzcHbzj = ncLdzcHbzj;
    }

    public BigDecimal getNcLdzcDqtc() {
        return ncLdzcDqtc;
    }

    public void setNcLdzcDqtc(BigDecimal ncLdzcDqtc) {
        this.ncLdzcDqtc = ncLdzcDqtc;
    }

    public BigDecimal getNcLdzcYspj() {
        return ncLdzcYspj;
    }

    public void setNcLdzcYspj(BigDecimal ncLdzcYspj) {
        this.ncLdzcYspj = ncLdzcYspj;
    }

    public BigDecimal getNcLdzcYsfl() {
        return ncLdzcYsfl;
    }

    public void setNcLdzcYsfl(BigDecimal ncLdzcYsfl) {
        this.ncLdzcYsfl = ncLdzcYsfl;
    }

    public BigDecimal getNcLdzcYslx() {
        return ncLdzcYslx;
    }

    public void setNcLdzcYslx(BigDecimal ncLdzcYslx) {
        this.ncLdzcYslx = ncLdzcYslx;
    }

    public BigDecimal getNcLdzcYsgl() {
        return ncLdzcYsgl;
    }

    public void setNcLdzcYsgl(BigDecimal ncLdzcYsgl) {
        this.ncLdzcYsgl = ncLdzcYsgl;
    }

    public BigDecimal getNcLdzcYszk() {
        return ncLdzcYszk;
    }

    public void setNcLdzcYszk(BigDecimal ncLdzcYszk) {
        this.ncLdzcYszk = ncLdzcYszk;
    }

    public BigDecimal getNcLdzcQtysk() {
        return ncLdzcQtysk;
    }

    public void setNcLdzcQtysk(BigDecimal ncLdzcQtysk) {
        this.ncLdzcQtysk = ncLdzcQtysk;
    }

    public BigDecimal getNcLdzcYfzk() {
        return ncLdzcYfzk;
    }

    public void setNcLdzcYfzk(BigDecimal ncLdzcYfzk) {
        this.ncLdzcYfzk = ncLdzcYfzk;
    }

    public BigDecimal getNcLdzcYsbtk() {
        return ncLdzcYsbtk;
    }

    public void setNcLdzcYsbtk(BigDecimal ncLdzcYsbtk) {
        this.ncLdzcYsbtk = ncLdzcYsbtk;
    }

    public BigDecimal getNcLdzcCh() {
        return ncLdzcCh;
    }

    public void setNcLdzcCh(BigDecimal ncLdzcCh) {
        this.ncLdzcCh = ncLdzcCh;
    }

    public BigDecimal getNcLdzcDtfy() {
        return ncLdzcDtfy;
    }

    public void setNcLdzcDtfy(BigDecimal ncLdzcDtfy) {
        this.ncLdzcDtfy = ncLdzcDtfy;
    }

    public BigDecimal getNcLdzcYnndqcqzqtz() {
        return ncLdzcYnndqcqzqtz;
    }

    public void setNcLdzcYnndqcqzqtz(BigDecimal ncLdzcYnndqcqzqtz) {
        this.ncLdzcYnndqcqzqtz = ncLdzcYnndqcqzqtz;
    }

    public BigDecimal getNcLdzcQtldzc() {
        return ncLdzcQtldzc;
    }

    public void setNcLdzcQtldzc(BigDecimal ncLdzcQtldzc) {
        this.ncLdzcQtldzc = ncLdzcQtldzc;
    }

    public BigDecimal getNcLdzcHj() {
        return ncLdzcHj;
    }

    public void setNcLdzcHj(BigDecimal ncLdzcHj) {
        this.ncLdzcHj = ncLdzcHj;
    }

    public BigDecimal getNcCqtcCqgqtz() {
        return ncCqtcCqgqtz;
    }

    public void setNcCqtcCqgqtz(BigDecimal ncCqtcCqgqtz) {
        this.ncCqtcCqgqtz = ncCqtcCqgqtz;
    }

    public BigDecimal getNcCqtcCqzqtc() {
        return ncCqtcCqzqtc;
    }

    public void setNcCqtcCqzqtc(BigDecimal ncCqtcCqzqtc) {
        this.ncCqtcCqzqtc = ncCqtcCqzqtc;
    }

    public BigDecimal getNcCqtzHj() {
        return ncCqtzHj;
    }

    public void setNcCqtzHj(BigDecimal ncCqtzHj) {
        this.ncCqtzHj = ncCqtzHj;
    }

    public BigDecimal getNcGdzcYj() {
        return ncGdzcYj;
    }

    public void setNcGdzcYj(BigDecimal ncGdzcYj) {
        this.ncGdzcYj = ncGdzcYj;
    }

    public BigDecimal getNcGdzcLjzj() {
        return ncGdzcLjzj;
    }

    public void setNcGdzcLjzj(BigDecimal ncGdzcLjzj) {
        this.ncGdzcLjzj = ncGdzcLjzj;
    }

    public BigDecimal getNcGdzcJz() {
        return ncGdzcJz;
    }

    public void setNcGdzcJz(BigDecimal ncGdzcJz) {
        this.ncGdzcJz = ncGdzcJz;
    }

    public BigDecimal getNcGdzcJzzb() {
        return ncGdzcJzzb;
    }

    public void setNcGdzcJzzb(BigDecimal ncGdzcJzzb) {
        this.ncGdzcJzzb = ncGdzcJzzb;
    }

    public BigDecimal getNcGdzcJe() {
        return ncGdzcJe;
    }

    public void setNcGdzcJe(BigDecimal ncGdzcJe) {
        this.ncGdzcJe = ncGdzcJe;
    }

    public BigDecimal getNcGdzcGcwz() {
        return ncGdzcGcwz;
    }

    public void setNcGdzcGcwz(BigDecimal ncGdzcGcwz) {
        this.ncGdzcGcwz = ncGdzcGcwz;
    }

    public BigDecimal getNcGdzcZjgc() {
        return ncGdzcZjgc;
    }

    public void setNcGdzcZjgc(BigDecimal ncGdzcZjgc) {
        this.ncGdzcZjgc = ncGdzcZjgc;
    }

    public BigDecimal getNcGdzcQl() {
        return ncGdzcQl;
    }

    public void setNcGdzcQl(BigDecimal ncGdzcQl) {
        this.ncGdzcQl = ncGdzcQl;
    }

    public BigDecimal getNcGdzcHj() {
        return ncGdzcHj;
    }

    public void setNcGdzcHj(BigDecimal ncGdzcHj) {
        this.ncGdzcHj = ncGdzcHj;
    }

    public BigDecimal getNcWxzcJe() {
        return ncWxzcJe;
    }

    public void setNcWxzcJe(BigDecimal ncWxzcJe) {
        this.ncWxzcJe = ncWxzcJe;
    }

    public BigDecimal getNcWxzcCqdtfy() {
        return ncWxzcCqdtfy;
    }

    public void setNcWxzcCqdtfy(BigDecimal ncWxzcCqdtfy) {
        this.ncWxzcCqdtfy = ncWxzcCqdtfy;
    }

    public BigDecimal getNcWxzcQtcqzc() {
        return ncWxzcQtcqzc;
    }

    public void setNcWxzcQtcqzc(BigDecimal ncWxzcQtcqzc) {
        this.ncWxzcQtcqzc = ncWxzcQtcqzc;
    }

    public BigDecimal getNcWxzcHj() {
        return ncWxzcHj;
    }

    public void setNcWxzcHj(BigDecimal ncWxzcHj) {
        this.ncWxzcHj = ncWxzcHj;
    }

    public BigDecimal getNcDysxDyckjx() {
        return ncDysxDyckjx;
    }

    public void setNcDysxDyckjx(BigDecimal ncDysxDyckjx) {
        this.ncDysxDyckjx = ncDysxDyckjx;
    }

    public BigDecimal getNcDysxZczj() {
        return ncDysxZczj;
    }

    public void setNcDysxZczj(BigDecimal ncDysxZczj) {
        this.ncDysxZczj = ncDysxZczj;
    }

    public BigDecimal getNcLdfzDqjk() {
        return ncLdfzDqjk;
    }

    public void setNcLdfzDqjk(BigDecimal ncLdfzDqjk) {
        this.ncLdfzDqjk = ncLdfzDqjk;
    }

    public BigDecimal getNcLdfzYfpj() {
        return ncLdfzYfpj;
    }

    public void setNcLdfzYfpj(BigDecimal ncLdfzYfpj) {
        this.ncLdfzYfpj = ncLdfzYfpj;
    }

    public BigDecimal getNcLdfzYfzk() {
        return ncLdfzYfzk;
    }

    public void setNcLdfzYfzk(BigDecimal ncLdfzYfzk) {
        this.ncLdfzYfzk = ncLdfzYfzk;
    }

    public BigDecimal getNcLdfzYszk() {
        return ncLdfzYszk;
    }

    public void setNcLdfzYszk(BigDecimal ncLdfzYszk) {
        this.ncLdfzYszk = ncLdfzYszk;
    }

    public BigDecimal getNcLdfzYfgz() {
        return ncLdfzYfgz;
    }

    public void setNcLdfzYfgz(BigDecimal ncLdfzYfgz) {
        this.ncLdfzYfgz = ncLdfzYfgz;
    }

    public BigDecimal getNcLdfzYfflf() {
        return ncLdfzYfflf;
    }

    public void setNcLdfzYfflf(BigDecimal ncLdfzYfflf) {
        this.ncLdfzYfflf = ncLdfzYfflf;
    }

    public BigDecimal getNcLdfzYfgl() {
        return ncLdfzYfgl;
    }

    public void setNcLdfzYfgl(BigDecimal ncLdfzYfgl) {
        this.ncLdfzYfgl = ncLdfzYfgl;
    }

    public BigDecimal getNcLdfzYjsj() {
        return ncLdfzYjsj;
    }

    public void setNcLdfzYjsj(BigDecimal ncLdfzYjsj) {
        this.ncLdfzYjsj = ncLdfzYjsj;
    }

    public BigDecimal getNcLdfzQtyfk() {
        return ncLdfzQtyfk;
    }

    public void setNcLdfzQtyfk(BigDecimal ncLdfzQtyfk) {
        this.ncLdfzQtyfk = ncLdfzQtyfk;
    }

    public BigDecimal getNcLdfzYtfy() {
        return ncLdfzYtfy;
    }

    public void setNcLdfzYtfy(BigDecimal ncLdfzYtfy) {
        this.ncLdfzYtfy = ncLdfzYtfy;
    }

    public BigDecimal getNcLdfzYjfz() {
        return ncLdfzYjfz;
    }

    public void setNcLdfzYjfz(BigDecimal ncLdfzYjfz) {
        this.ncLdfzYjfz = ncLdfzYjfz;
    }

    public BigDecimal getNcLdfzYnndqcqfz() {
        return ncLdfzYnndqcqfz;
    }

    public void setNcLdfzYnndqcqfz(BigDecimal ncLdfzYnndqcqfz) {
        this.ncLdfzYnndqcqfz = ncLdfzYnndqcqfz;
    }

    public BigDecimal getNcLdfzQtldfz() {
        return ncLdfzQtldfz;
    }

    public void setNcLdfzQtldfz(BigDecimal ncLdfzQtldfz) {
        this.ncLdfzQtldfz = ncLdfzQtldfz;
    }

    public BigDecimal getNcLdfzLdfzhj() {
        return ncLdfzLdfzhj;
    }

    public void setNcLdfzLdfzhj(BigDecimal ncLdfzLdfzhj) {
        this.ncLdfzLdfzhj = ncLdfzLdfzhj;
    }

    public BigDecimal getNcFldfzCqjk() {
        return ncFldfzCqjk;
    }

    public void setNcFldfzCqjk(BigDecimal ncFldfzCqjk) {
        this.ncFldfzCqjk = ncFldfzCqjk;
    }

    public BigDecimal getNcFldfzYfzq() {
        return ncFldfzYfzq;
    }

    public void setNcFldfzYfzq(BigDecimal ncFldfzYfzq) {
        this.ncFldfzYfzq = ncFldfzYfzq;
    }

    public BigDecimal getNcFldfzCqyfk() {
        return ncFldfzCqyfk;
    }

    public void setNcFldfzCqyfk(BigDecimal ncFldfzCqyfk) {
        this.ncFldfzCqyfk = ncFldfzCqyfk;
    }

    public BigDecimal getNcFldfzZxyfk() {
        return ncFldfzZxyfk;
    }

    public void setNcFldfzZxyfk(BigDecimal ncFldfzZxyfk) {
        this.ncFldfzZxyfk = ncFldfzZxyfk;
    }

    public BigDecimal getNcFldfzQtcqfz() {
        return ncFldfzQtcqfz;
    }

    public void setNcFldfzQtcqfz(BigDecimal ncFldfzQtcqfz) {
        this.ncFldfzQtcqfz = ncFldfzQtcqfz;
    }

    public BigDecimal getNcFldfzCqfzhj() {
        return ncFldfzCqfzhj;
    }

    public void setNcFldfzCqfzhj(BigDecimal ncFldfzCqfzhj) {
        this.ncFldfzCqfzhj = ncFldfzCqfzhj;
    }

    public BigDecimal getNcDysxDyskhx() {
        return ncDysxDyskhx;
    }

    public void setNcDysxDyskhx(BigDecimal ncDysxDyskhx) {
        this.ncDysxDyskhx = ncDysxDyskhx;
    }

    public BigDecimal getNcDysxFzhj() {
        return ncDysxFzhj;
    }

    public void setNcDysxFzhj(BigDecimal ncDysxFzhj) {
        this.ncDysxFzhj = ncDysxFzhj;
    }

    public BigDecimal getNcGdqySszb() {
        return ncGdqySszb;
    }

    public void setNcGdqySszb(BigDecimal ncGdqySszb) {
        this.ncGdqySszb = ncGdqySszb;
    }

    public BigDecimal getNcGdqyYghtz() {
        return ncGdqyYghtz;
    }

    public void setNcGdqyYghtz(BigDecimal ncGdqyYghtz) {
        this.ncGdqyYghtz = ncGdqyYghtz;
    }

    public BigDecimal getNcGdqySszbje() {
        return ncGdqySszbje;
    }

    public void setNcGdqySszbje(BigDecimal ncGdqySszbje) {
        this.ncGdqySszbje = ncGdqySszbje;
    }

    public BigDecimal getNcGdqyZbgj() {
        return ncGdqyZbgj;
    }

    public void setNcGdqyZbgj(BigDecimal ncGdqyZbgj) {
        this.ncGdqyZbgj = ncGdqyZbgj;
    }

    public BigDecimal getNcGdqyYygj() {
        return ncGdqyYygj;
    }

    public void setNcGdqyYygj(BigDecimal ncGdqyYygj) {
        this.ncGdqyYygj = ncGdqyYygj;
    }

    public BigDecimal getNcGdqyFdgyj() {
        return ncGdqyFdgyj;
    }

    public void setNcGdqyFdgyj(BigDecimal ncGdqyFdgyj) {
        this.ncGdqyFdgyj = ncGdqyFdgyj;
    }

    public BigDecimal getNcGdqyWfplr() {
        return ncGdqyWfplr;
    }

    public void setNcGdqyWfplr(BigDecimal ncGdqyWfplr) {
        this.ncGdqyWfplr = ncGdqyWfplr;
    }

    public BigDecimal getNcGdqySyzqyhj() {
        return ncGdqySyzqyhj;
    }

    public void setNcGdqySyzqyhj(BigDecimal ncGdqySyzqyhj) {
        this.ncGdqySyzqyhj = ncGdqySyzqyhj;
    }

    public BigDecimal getNcGdqyFzhsyzqy() {
        return ncGdqyFzhsyzqy;
    }

    public void setNcGdqyFzhsyzqy(BigDecimal ncGdqyFzhsyzqy) {
        this.ncGdqyFzhsyzqy = ncGdqyFzhsyzqy;
    }

    public BigDecimal getQmLdzcHbzj() {
        return qmLdzcHbzj;
    }

    public void setQmLdzcHbzj(BigDecimal qmLdzcHbzj) {
        this.qmLdzcHbzj = qmLdzcHbzj;
    }

    public BigDecimal getQmLdzcDqtc() {
        return qmLdzcDqtc;
    }

    public void setQmLdzcDqtc(BigDecimal qmLdzcDqtc) {
        this.qmLdzcDqtc = qmLdzcDqtc;
    }

    public BigDecimal getQmLdzcYspj() {
        return qmLdzcYspj;
    }

    public void setQmLdzcYspj(BigDecimal qmLdzcYspj) {
        this.qmLdzcYspj = qmLdzcYspj;
    }

    public BigDecimal getQmLdzcYsfl() {
        return qmLdzcYsfl;
    }

    public void setQmLdzcYsfl(BigDecimal qmLdzcYsfl) {
        this.qmLdzcYsfl = qmLdzcYsfl;
    }

    public BigDecimal getQmLdzcYslx() {
        return qmLdzcYslx;
    }

    public void setQmLdzcYslx(BigDecimal qmLdzcYslx) {
        this.qmLdzcYslx = qmLdzcYslx;
    }

    public BigDecimal getQmLdzcYsgl() {
        return qmLdzcYsgl;
    }

    public void setQmLdzcYsgl(BigDecimal qmLdzcYsgl) {
        this.qmLdzcYsgl = qmLdzcYsgl;
    }

    public BigDecimal getQmLdzcYszk() {
        return qmLdzcYszk;
    }

    public void setQmLdzcYszk(BigDecimal qmLdzcYszk) {
        this.qmLdzcYszk = qmLdzcYszk;
    }

    public BigDecimal getQmLdzcQtysk() {
        return qmLdzcQtysk;
    }

    public void setQmLdzcQtysk(BigDecimal qmLdzcQtysk) {
        this.qmLdzcQtysk = qmLdzcQtysk;
    }

    public BigDecimal getQmLdzcYfzk() {
        return qmLdzcYfzk;
    }

    public void setQmLdzcYfzk(BigDecimal qmLdzcYfzk) {
        this.qmLdzcYfzk = qmLdzcYfzk;
    }

    public BigDecimal getQmLdzcYsbtk() {
        return qmLdzcYsbtk;
    }

    public void setQmLdzcYsbtk(BigDecimal qmLdzcYsbtk) {
        this.qmLdzcYsbtk = qmLdzcYsbtk;
    }

    public BigDecimal getQmLdzcCh() {
        return qmLdzcCh;
    }

    public void setQmLdzcCh(BigDecimal qmLdzcCh) {
        this.qmLdzcCh = qmLdzcCh;
    }

    public BigDecimal getQmLdzcDtfy() {
        return qmLdzcDtfy;
    }

    public void setQmLdzcDtfy(BigDecimal qmLdzcDtfy) {
        this.qmLdzcDtfy = qmLdzcDtfy;
    }

    public BigDecimal getQmLdzcYnndqcqzqtz() {
        return qmLdzcYnndqcqzqtz;
    }

    public void setQmLdzcYnndqcqzqtz(BigDecimal qmLdzcYnndqcqzqtz) {
        this.qmLdzcYnndqcqzqtz = qmLdzcYnndqcqzqtz;
    }

    public BigDecimal getQmLdzcQtldzc() {
        return qmLdzcQtldzc;
    }

    public void setQmLdzcQtldzc(BigDecimal qmLdzcQtldzc) {
        this.qmLdzcQtldzc = qmLdzcQtldzc;
    }

    public BigDecimal getQmLdzcHj() {
        return qmLdzcHj;
    }

    public void setQmLdzcHj(BigDecimal qmLdzcHj) {
        this.qmLdzcHj = qmLdzcHj;
    }

    public BigDecimal getQmCqtcCqgqtz() {
        return qmCqtcCqgqtz;
    }

    public void setQmCqtcCqgqtz(BigDecimal qmCqtcCqgqtz) {
        this.qmCqtcCqgqtz = qmCqtcCqgqtz;
    }

    public BigDecimal getQmCqtcCqzqtc() {
        return qmCqtcCqzqtc;
    }

    public void setQmCqtcCqzqtc(BigDecimal qmCqtcCqzqtc) {
        this.qmCqtcCqzqtc = qmCqtcCqzqtc;
    }

    public BigDecimal getQmCqtzHj() {
        return qmCqtzHj;
    }

    public void setQmCqtzHj(BigDecimal qmCqtzHj) {
        this.qmCqtzHj = qmCqtzHj;
    }

    public BigDecimal getQmGdzcYj() {
        return qmGdzcYj;
    }

    public void setQmGdzcYj(BigDecimal qmGdzcYj) {
        this.qmGdzcYj = qmGdzcYj;
    }

    public BigDecimal getQmGdzcLjzj() {
        return qmGdzcLjzj;
    }

    public void setQmGdzcLjzj(BigDecimal qmGdzcLjzj) {
        this.qmGdzcLjzj = qmGdzcLjzj;
    }

    public BigDecimal getQmGdzcJz() {
        return qmGdzcJz;
    }

    public void setQmGdzcJz(BigDecimal qmGdzcJz) {
        this.qmGdzcJz = qmGdzcJz;
    }

    public BigDecimal getQmGdzcJzzb() {
        return qmGdzcJzzb;
    }

    public void setQmGdzcJzzb(BigDecimal qmGdzcJzzb) {
        this.qmGdzcJzzb = qmGdzcJzzb;
    }

    public BigDecimal getQmGdzcJe() {
        return qmGdzcJe;
    }

    public void setQmGdzcJe(BigDecimal qmGdzcJe) {
        this.qmGdzcJe = qmGdzcJe;
    }

    public BigDecimal getQmGdzcGcwz() {
        return qmGdzcGcwz;
    }

    public void setQmGdzcGcwz(BigDecimal qmGdzcGcwz) {
        this.qmGdzcGcwz = qmGdzcGcwz;
    }

    public BigDecimal getQmGdzcZjgc() {
        return qmGdzcZjgc;
    }

    public void setQmGdzcZjgc(BigDecimal qmGdzcZjgc) {
        this.qmGdzcZjgc = qmGdzcZjgc;
    }

    public BigDecimal getQmGdzcQl() {
        return qmGdzcQl;
    }

    public void setQmGdzcQl(BigDecimal qmGdzcQl) {
        this.qmGdzcQl = qmGdzcQl;
    }

    public BigDecimal getQmGdzcHj() {
        return qmGdzcHj;
    }

    public void setQmGdzcHj(BigDecimal qmGdzcHj) {
        this.qmGdzcHj = qmGdzcHj;
    }

    public BigDecimal getQmWxzcJe() {
        return qmWxzcJe;
    }

    public void setQmWxzcJe(BigDecimal qmWxzcJe) {
        this.qmWxzcJe = qmWxzcJe;
    }

    public BigDecimal getQmWxzcCqdtfy() {
        return qmWxzcCqdtfy;
    }

    public void setQmWxzcCqdtfy(BigDecimal qmWxzcCqdtfy) {
        this.qmWxzcCqdtfy = qmWxzcCqdtfy;
    }

    public BigDecimal getQmWxzcQtcqzc() {
        return qmWxzcQtcqzc;
    }

    public void setQmWxzcQtcqzc(BigDecimal qmWxzcQtcqzc) {
        this.qmWxzcQtcqzc = qmWxzcQtcqzc;
    }

    public BigDecimal getQmWxzcHj() {
        return qmWxzcHj;
    }

    public void setQmWxzcHj(BigDecimal qmWxzcHj) {
        this.qmWxzcHj = qmWxzcHj;
    }

    public BigDecimal getQmDysxDyckjx() {
        return qmDysxDyckjx;
    }

    public void setQmDysxDyckjx(BigDecimal qmDysxDyckjx) {
        this.qmDysxDyckjx = qmDysxDyckjx;
    }

    public BigDecimal getQmDysxZczj() {
        return qmDysxZczj;
    }

    public void setQmDysxZczj(BigDecimal qmDysxZczj) {
        this.qmDysxZczj = qmDysxZczj;
    }

    public BigDecimal getQmLdfzDqjk() {
        return qmLdfzDqjk;
    }

    public void setQmLdfzDqjk(BigDecimal qmLdfzDqjk) {
        this.qmLdfzDqjk = qmLdfzDqjk;
    }

    public BigDecimal getQmLdfzYfpj() {
        return qmLdfzYfpj;
    }

    public void setQmLdfzYfpj(BigDecimal qmLdfzYfpj) {
        this.qmLdfzYfpj = qmLdfzYfpj;
    }

    public BigDecimal getQmLdfzYfzk() {
        return qmLdfzYfzk;
    }

    public void setQmLdfzYfzk(BigDecimal qmLdfzYfzk) {
        this.qmLdfzYfzk = qmLdfzYfzk;
    }

    public BigDecimal getQmLdfzYszk() {
        return qmLdfzYszk;
    }

    public void setQmLdfzYszk(BigDecimal qmLdfzYszk) {
        this.qmLdfzYszk = qmLdfzYszk;
    }

    public BigDecimal getQmLdfzYfgz() {
        return qmLdfzYfgz;
    }

    public void setQmLdfzYfgz(BigDecimal qmLdfzYfgz) {
        this.qmLdfzYfgz = qmLdfzYfgz;
    }

    public BigDecimal getQmLdfzYfflf() {
        return qmLdfzYfflf;
    }

    public void setQmLdfzYfflf(BigDecimal qmLdfzYfflf) {
        this.qmLdfzYfflf = qmLdfzYfflf;
    }

    public BigDecimal getQmLdfzYfgl() {
        return qmLdfzYfgl;
    }

    public void setQmLdfzYfgl(BigDecimal qmLdfzYfgl) {
        this.qmLdfzYfgl = qmLdfzYfgl;
    }

    public BigDecimal getQmLdfzYjsj() {
        return qmLdfzYjsj;
    }

    public void setQmLdfzYjsj(BigDecimal qmLdfzYjsj) {
        this.qmLdfzYjsj = qmLdfzYjsj;
    }

    public BigDecimal getQmLdfzQtyfk() {
        return qmLdfzQtyfk;
    }

    public void setQmLdfzQtyfk(BigDecimal qmLdfzQtyfk) {
        this.qmLdfzQtyfk = qmLdfzQtyfk;
    }

    public BigDecimal getQmLdfzYtfy() {
        return qmLdfzYtfy;
    }

    public void setQmLdfzYtfy(BigDecimal qmLdfzYtfy) {
        this.qmLdfzYtfy = qmLdfzYtfy;
    }

    public BigDecimal getQmLdfzYjfz() {
        return qmLdfzYjfz;
    }

    public void setQmLdfzYjfz(BigDecimal qmLdfzYjfz) {
        this.qmLdfzYjfz = qmLdfzYjfz;
    }

    public BigDecimal getQmLdfzYnndqcqfz() {
        return qmLdfzYnndqcqfz;
    }

    public void setQmLdfzYnndqcqfz(BigDecimal qmLdfzYnndqcqfz) {
        this.qmLdfzYnndqcqfz = qmLdfzYnndqcqfz;
    }

    public BigDecimal getQmLdfzQtldfz() {
        return qmLdfzQtldfz;
    }

    public void setQmLdfzQtldfz(BigDecimal qmLdfzQtldfz) {
        this.qmLdfzQtldfz = qmLdfzQtldfz;
    }

    public BigDecimal getQmLdfzLdfzhj() {
        return qmLdfzLdfzhj;
    }

    public void setQmLdfzLdfzhj(BigDecimal qmLdfzLdfzhj) {
        this.qmLdfzLdfzhj = qmLdfzLdfzhj;
    }

    public BigDecimal getQmFldfzCqjk() {
        return qmFldfzCqjk;
    }

    public void setQmFldfzCqjk(BigDecimal qmFldfzCqjk) {
        this.qmFldfzCqjk = qmFldfzCqjk;
    }

    public BigDecimal getQmFldfzYfzq() {
        return qmFldfzYfzq;
    }

    public void setQmFldfzYfzq(BigDecimal qmFldfzYfzq) {
        this.qmFldfzYfzq = qmFldfzYfzq;
    }

    public BigDecimal getQmFldfzCqyfk() {
        return qmFldfzCqyfk;
    }

    public void setQmFldfzCqyfk(BigDecimal qmFldfzCqyfk) {
        this.qmFldfzCqyfk = qmFldfzCqyfk;
    }

    public BigDecimal getQmFldfzZxyfk() {
        return qmFldfzZxyfk;
    }

    public void setQmFldfzZxyfk(BigDecimal qmFldfzZxyfk) {
        this.qmFldfzZxyfk = qmFldfzZxyfk;
    }

    public BigDecimal getQmFldfzQtcqfz() {
        return qmFldfzQtcqfz;
    }

    public void setQmFldfzQtcqfz(BigDecimal qmFldfzQtcqfz) {
        this.qmFldfzQtcqfz = qmFldfzQtcqfz;
    }

    public BigDecimal getQmFldfzCqfzhj() {
        return qmFldfzCqfzhj;
    }

    public void setQmFldfzCqfzhj(BigDecimal qmFldfzCqfzhj) {
        this.qmFldfzCqfzhj = qmFldfzCqfzhj;
    }

    public BigDecimal getQmDysxDyskhx() {
        return qmDysxDyskhx;
    }

    public void setQmDysxDyskhx(BigDecimal qmDysxDyskhx) {
        this.qmDysxDyskhx = qmDysxDyskhx;
    }

    public BigDecimal getQmDysxFzhj() {
        return qmDysxFzhj;
    }

    public void setQmDysxFzhj(BigDecimal qmDysxFzhj) {
        this.qmDysxFzhj = qmDysxFzhj;
    }

    public BigDecimal getQmGdqySszb() {
        return qmGdqySszb;
    }

    public void setQmGdqySszb(BigDecimal qmGdqySszb) {
        this.qmGdqySszb = qmGdqySszb;
    }

    public BigDecimal getQmGdqyYghtz() {
        return qmGdqyYghtz;
    }

    public void setQmGdqyYghtz(BigDecimal qmGdqyYghtz) {
        this.qmGdqyYghtz = qmGdqyYghtz;
    }

    public BigDecimal getQmGdqySszbje() {
        return qmGdqySszbje;
    }

    public void setQmGdqySszbje(BigDecimal qmGdqySszbje) {
        this.qmGdqySszbje = qmGdqySszbje;
    }

    public BigDecimal getQmGdqyZbgj() {
        return qmGdqyZbgj;
    }

    public void setQmGdqyZbgj(BigDecimal qmGdqyZbgj) {
        this.qmGdqyZbgj = qmGdqyZbgj;
    }

    public BigDecimal getQmGdqyYygj() {
        return qmGdqyYygj;
    }

    public void setQmGdqyYygj(BigDecimal qmGdqyYygj) {
        this.qmGdqyYygj = qmGdqyYygj;
    }

    public BigDecimal getQmGdqyFdgyj() {
        return qmGdqyFdgyj;
    }

    public void setQmGdqyFdgyj(BigDecimal qmGdqyFdgyj) {
        this.qmGdqyFdgyj = qmGdqyFdgyj;
    }

    public BigDecimal getQmGdqyWfplr() {
        return qmGdqyWfplr;
    }

    public void setQmGdqyWfplr(BigDecimal qmGdqyWfplr) {
        this.qmGdqyWfplr = qmGdqyWfplr;
    }

    public BigDecimal getQmGdqySyzqyhj() {
        return qmGdqySyzqyhj;
    }

    public void setQmGdqySyzqyhj(BigDecimal qmGdqySyzqyhj) {
        this.qmGdqySyzqyhj = qmGdqySyzqyhj;
    }

    public BigDecimal getQmGdqyFzhsyzqy() {
        return qmGdqyFzhsyzqy;
    }

    public void setQmGdqyFzhsyzqy(BigDecimal qmGdqyFzhsyzqy) {
        this.qmGdqyFzhsyzqy = qmGdqyFzhsyzqy;
    }

    public BigDecimal getNcLdzcJyxjrzc() {
        return ncLdzcJyxjrzc;
    }

    public void setNcLdzcJyxjrzc(BigDecimal ncLdzcJyxjrzc) {
        this.ncLdzcJyxjrzc = ncLdzcJyxjrzc;
    }

    public BigDecimal getQmLdzcJyxjrzc() {
        return qmLdzcJyxjrzc;
    }

    public void setQmLdzcJyxjrzc(BigDecimal qmLdzcJyxjrzc) {
        this.qmLdzcJyxjrzc = qmLdzcJyxjrzc;
    }

    public BigDecimal getNcFldzcKgcsjrzc() {
        return ncFldzcKgcsjrzc;
    }

    public void setNcFldzcKgcsjrzc(BigDecimal ncFldzcKgcsjrzc) {
        this.ncFldzcKgcsjrzc = ncFldzcKgcsjrzc;
    }

    public BigDecimal getQmFldzcKgcsjrzc() {
        return qmFldzcKgcsjrzc;
    }

    public void setQmFldzcKgcsjrzc(BigDecimal qmFldzcKgcsjrzc) {
        this.qmFldzcKgcsjrzc = qmFldzcKgcsjrzc;
    }

    public BigDecimal getNcFldzcCyzdqtz() {
        return ncFldzcCyzdqtz;
    }

    public void setNcFldzcCyzdqtz(BigDecimal ncFldzcCyzdqtz) {
        this.ncFldzcCyzdqtz = ncFldzcCyzdqtz;
    }

    public BigDecimal getQmFldzcCyzdqtz() {
        return qmFldzcCyzdqtz;
    }

    public void setQmFldzcCyzdqtz(BigDecimal qmFldzcCyzdqtz) {
        this.qmFldzcCyzdqtz = qmFldzcCyzdqtz;
    }

    public BigDecimal getNcFldzcCqysk() {
        return ncFldzcCqysk;
    }

    public void setNcFldzcCqysk(BigDecimal ncFldzcCqysk) {
        this.ncFldzcCqysk = ncFldzcCqysk;
    }

    public BigDecimal getQmFldzcCqysk() {
        return qmFldzcCqysk;
    }

    public void setQmFldzcCqysk(BigDecimal qmFldzcCqysk) {
        this.qmFldzcCqysk = qmFldzcCqysk;
    }

    public BigDecimal getNcFldzcCqgqtz() {
        return ncFldzcCqgqtz;
    }

    public void setNcFldzcCqgqtz(BigDecimal ncFldzcCqgqtz) {
        this.ncFldzcCqgqtz = ncFldzcCqgqtz;
    }

    public BigDecimal getQmFldzcCqgqtz() {
        return qmFldzcCqgqtz;
    }

    public void setQmFldzcCqgqtz(BigDecimal qmFldzcCqgqtz) {
        this.qmFldzcCqgqtz = qmFldzcCqgqtz;
    }

    public BigDecimal getQmFldzcTzxfdc() {
        return qmFldzcTzxfdc;
    }

    public void setQmFldzcTzxfdc(BigDecimal qmFldzcTzxfdc) {
        this.qmFldzcTzxfdc = qmFldzcTzxfdc;
    }

    public BigDecimal getNcFldzcTzxfdc() {
        return ncFldzcTzxfdc;
    }

    public void setNcFldzcTzxfdc(BigDecimal ncFldzcTzxfdc) {
        this.ncFldzcTzxfdc = ncFldzcTzxfdc;
    }

    public BigDecimal getNcFldzcGdzc() {
        return ncFldzcGdzc;
    }

    public void setNcFldzcGdzc(BigDecimal ncFldzcGdzc) {
        this.ncFldzcGdzc = ncFldzcGdzc;
    }

    public BigDecimal getQmFldzcGdzc() {
        return qmFldzcGdzc;
    }

    public void setQmFldzcGdzc(BigDecimal qmFldzcGdzc) {
        this.qmFldzcGdzc = qmFldzcGdzc;
    }

    public BigDecimal getNcFldzcZjgc() {
        return ncFldzcZjgc;
    }

    public void setNcFldzcZjgc(BigDecimal ncFldzcZjgc) {
        this.ncFldzcZjgc = ncFldzcZjgc;
    }

    public BigDecimal getQmFldzcZjgc() {
        return qmFldzcZjgc;
    }

    public void setQmFldzcZjgc(BigDecimal qmFldzcZjgc) {
        this.qmFldzcZjgc = qmFldzcZjgc;
    }

    public BigDecimal getNcFldzcGcwz() {
        return ncFldzcGcwz;
    }

    public void setNcFldzcGcwz(BigDecimal ncFldzcGcwz) {
        this.ncFldzcGcwz = ncFldzcGcwz;
    }

    public BigDecimal getQmFldzcGcwz() {
        return qmFldzcGcwz;
    }

    public void setQmFldzcGcwz(BigDecimal qmFldzcGcwz) {
        this.qmFldzcGcwz = qmFldzcGcwz;
    }

    public BigDecimal getNcFldzcGdzcql() {
        return ncFldzcGdzcql;
    }

    public void setNcFldzcGdzcql(BigDecimal ncFldzcGdzcql) {
        this.ncFldzcGdzcql = ncFldzcGdzcql;
    }

    public BigDecimal getQmFldzcGdzcql() {
        return qmFldzcGdzcql;
    }

    public void setQmFldzcGdzcql(BigDecimal qmFldzcGdzcql) {
        this.qmFldzcGdzcql = qmFldzcGdzcql;
    }

    public BigDecimal getNcFldzcScxswzc() {
        return ncFldzcScxswzc;
    }

    public void setNcFldzcScxswzc(BigDecimal ncFldzcScxswzc) {
        this.ncFldzcScxswzc = ncFldzcScxswzc;
    }

    public BigDecimal getQmFldzcScxswzc() {
        return qmFldzcScxswzc;
    }

    public void setQmFldzcScxswzc(BigDecimal qmFldzcScxswzc) {
        this.qmFldzcScxswzc = qmFldzcScxswzc;
    }

    public BigDecimal getNcFldzcYqzc() {
        return ncFldzcYqzc;
    }

    public void setNcFldzcYqzc(BigDecimal ncFldzcYqzc) {
        this.ncFldzcYqzc = ncFldzcYqzc;
    }

    public BigDecimal getQmFldzcYqzc() {
        return qmFldzcYqzc;
    }

    public void setQmFldzcYqzc(BigDecimal qmFldzcYqzc) {
        this.qmFldzcYqzc = qmFldzcYqzc;
    }

    public BigDecimal getNcFldzcWxzc() {
        return ncFldzcWxzc;
    }

    public void setNcFldzcWxzc(BigDecimal ncFldzcWxzc) {
        this.ncFldzcWxzc = ncFldzcWxzc;
    }

    public BigDecimal getQmFldzcWxzc() {
        return qmFldzcWxzc;
    }

    public void setQmFldzcWxzc(BigDecimal qmFldzcWxzc) {
        this.qmFldzcWxzc = qmFldzcWxzc;
    }

    public BigDecimal getNcFldzcKfzc() {
        return ncFldzcKfzc;
    }

    public void setNcFldzcKfzc(BigDecimal ncFldzcKfzc) {
        this.ncFldzcKfzc = ncFldzcKfzc;
    }

    public BigDecimal getQmFldzcKfzc() {
        return qmFldzcKfzc;
    }

    public void setQmFldzcKfzc(BigDecimal qmFldzcKfzc) {
        this.qmFldzcKfzc = qmFldzcKfzc;
    }

    public BigDecimal getNcFldzcSy() {
        return ncFldzcSy;
    }

    public void setNcFldzcSy(BigDecimal ncFldzcSy) {
        this.ncFldzcSy = ncFldzcSy;
    }

    public BigDecimal getQmFldzcSy() {
        return qmFldzcSy;
    }

    public void setQmFldzcSy(BigDecimal qmFldzcSy) {
        this.qmFldzcSy = qmFldzcSy;
    }

    public BigDecimal getNcFldzcCqdtfy() {
        return ncFldzcCqdtfy;
    }

    public void setNcFldzcCqdtfy(BigDecimal ncFldzcCqdtfy) {
        this.ncFldzcCqdtfy = ncFldzcCqdtfy;
    }

    public BigDecimal getQmFldzcCqdtfy() {
        return qmFldzcCqdtfy;
    }

    public void setQmFldzcCqdtfy(BigDecimal qmFldzcCqdtfy) {
        this.qmFldzcCqdtfy = qmFldzcCqdtfy;
    }

    public BigDecimal getNcFldzcDysdszc() {
        return ncFldzcDysdszc;
    }

    public void setNcFldzcDysdszc(BigDecimal ncFldzcDysdszc) {
        this.ncFldzcDysdszc = ncFldzcDysdszc;
    }

    public BigDecimal getQmFldzcDysdszc() {
        return qmFldzcDysdszc;
    }

    public void setQmFldzcDysdszc(BigDecimal qmFldzcDysdszc) {
        this.qmFldzcDysdszc = qmFldzcDysdszc;
    }

    public BigDecimal getNcFldzcQtfldzc() {
        return ncFldzcQtfldzc;
    }

    public void setNcFldzcQtfldzc(BigDecimal ncFldzcQtfldzc) {
        this.ncFldzcQtfldzc = ncFldzcQtfldzc;
    }

    public BigDecimal getQmFldzcQtfldzc() {
        return qmFldzcQtfldzc;
    }

    public void setQmFldzcQtfldzc(BigDecimal qmFldzcQtfldzc) {
        this.qmFldzcQtfldzc = qmFldzcQtfldzc;
    }

    public BigDecimal getNcFldzcFldzchj() {
        return ncFldzcFldzchj;
    }

    public void setNcFldzcFldzchj(BigDecimal ncFldzcFldzchj) {
        this.ncFldzcFldzchj = ncFldzcFldzchj;
    }

    public BigDecimal getQmFldzcFldzchj() {
        return qmFldzcFldzchj;
    }

    public void setQmFldzcFldzchj(BigDecimal qmFldzcFldzchj) {
        this.qmFldzcFldzchj = qmFldzcFldzchj;
    }

    public BigDecimal getNcZczj() {
        return ncZczj;
    }

    public void setNcZczj(BigDecimal ncZczj) {
        this.ncZczj = ncZczj;
    }

    public BigDecimal getQmZczj() {
        return qmZczj;
    }

    public void setQmZczj(BigDecimal qmZczj) {
        this.qmZczj = qmZczj;
    }

    public BigDecimal getNcLdfzJyxjrfz() {
        return ncLdfzJyxjrfz;
    }

    public void setNcLdfzJyxjrfz(BigDecimal ncLdfzJyxjrfz) {
        this.ncLdfzJyxjrfz = ncLdfzJyxjrfz;
    }

    public BigDecimal getQmLdfzJyxjrfz() {
        return qmLdfzJyxjrfz;
    }

    public void setQmLdfzJyxjrfz(BigDecimal qmLdfzJyxjrfz) {
        this.qmLdfzJyxjrfz = qmLdfzJyxjrfz;
    }

    public BigDecimal getNcLdfzYflx() {
        return ncLdfzYflx;
    }

    public void setNcLdfzYflx(BigDecimal ncLdfzYflx) {
        this.ncLdfzYflx = ncLdfzYflx;
    }

    public BigDecimal getQmLdfzYflx() {
        return qmLdfzYflx;
    }

    public void setQmLdfzYflx(BigDecimal qmLdfzYflx) {
        this.qmLdfzYflx = qmLdfzYflx;
    }

    public BigDecimal getNcFldfzYjfz() {
        return ncFldfzYjfz;
    }

    public void setNcFldfzYjfz(BigDecimal ncFldfzYjfz) {
        this.ncFldfzYjfz = ncFldfzYjfz;
    }

    public BigDecimal getQmFldfzYjfz() {
        return qmFldfzYjfz;
    }

    public void setQmFldfzYjfz(BigDecimal qmFldfzYjfz) {
        this.qmFldfzYjfz = qmFldfzYjfz;
    }

    public BigDecimal getNcGdqyKcg() {
        return ncGdqyKcg;
    }

    public void setNcGdqyKcg(BigDecimal ncGdqyKcg) {
        this.ncGdqyKcg = ncGdqyKcg;
    }

    public BigDecimal getQmGdqyKcg() {
        return qmGdqyKcg;
    }

    public void setQmGdqyKcg(BigDecimal qmGdqyKcg) {
        this.qmGdqyKcg = qmGdqyKcg;
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

    public BigDecimal getQmYbfxzb() {
        return qmYbfxzb;
    }

    public void setQmYbfxzb(BigDecimal qmYbfxzb) {
        this.qmYbfxzb = qmYbfxzb;
    }

    public BigDecimal getNcYbfxzb() {
        return ncYbfxzb;
    }

    public void setNcYbfxzb(BigDecimal ncYbfxzb) {
        this.ncYbfxzb = ncYbfxzb;
    }

    public BigDecimal getQmGsymgsqyhj() {
        return qmGsymgsqyhj;
    }

    public void setQmGsymgsqyhj(BigDecimal qmGsymgsqyhj) {
        this.qmGsymgsqyhj = qmGsymgsqyhj;
    }

    public BigDecimal getNcGsymgsqyhj() {
        return ncGsymgsqyhj;
    }

    public void setNcGsymgsqyhj(BigDecimal ncGsymgsqyhj) {
        this.ncGsymgsqyhj = ncGsymgsqyhj;
    }

    public BigDecimal getQmSsgdqy() {
        return qmSsgdqy;
    }

    public void setQmSsgdqy(BigDecimal qmSsgdqy) {
        this.qmSsgdqy = qmSsgdqy;
    }

    public BigDecimal getNcSsgdqy() {
        return ncSsgdqy;
    }

    public void setNcSsgdqy(BigDecimal ncSsgdqy) {
        this.ncSsgdqy = ncSsgdqy;
    }

    public BigDecimal getQmYsjrzc() {
        return qmYsjrzc;
    }

    public void setQmYsjrzc(BigDecimal qmYsjrzc) {
        this.qmYsjrzc = qmYsjrzc;
    }

    public BigDecimal getQmHfwzydszc() {
        return qmHfwzydszc;
    }

    public void setQmHfwzydszc(BigDecimal qmHfwzydszc) {
        this.qmHfwzydszc = qmHfwzydszc;
    }

    public BigDecimal getQmYxg() {
        return qmYxg;
    }

    public void setQmYxg(BigDecimal qmYxg) {
        this.qmYxg = qmYxg;
    }

    public BigDecimal getQmCydsfz() {
        return qmCydsfz;
    }

    public void setQmCydsfz(BigDecimal qmCydsfz) {
        this.qmCydsfz = qmCydsfz;
    }

    public BigDecimal getQmYxz() {
        return qmYxz;
    }

    public void setQmYxz(BigDecimal qmYxz) {
        this.qmYxz = qmYxz;
    }

    public BigDecimal getQmDysy() {
        return qmDysy;
    }

    public void setQmDysy(BigDecimal qmDysy) {
        this.qmDysy = qmDysy;
    }

    public BigDecimal getQmQtqygj() {
        return qmQtqygj;
    }

    public void setQmQtqygj(BigDecimal qmQtqygj) {
        this.qmQtqygj = qmQtqygj;
    }

    public BigDecimal getQmQzyxg() {
        return qmQzyxg;
    }

    public void setQmQzyxg(BigDecimal qmQzyxg) {
        this.qmQzyxg = qmQzyxg;
    }

    public BigDecimal getQmZcyxz() {
        return qmZcyxz;
    }

    public void setQmZcyxz(BigDecimal qmZcyxz) {
        this.qmZcyxz = qmZcyxz;
    }

    public BigDecimal getQmZbgj() {
        return qmZbgj;
    }

    public void setQmZbgj(BigDecimal qmZbgj) {
        this.qmZbgj = qmZbgj;
    }

    public BigDecimal getQmQtzhsy() {
        return qmQtzhsy;
    }

    public void setQmQtzhsy(BigDecimal qmQtzhsy) {
        this.qmQtzhsy = qmQtzhsy;
    }

    public BigDecimal getQmJyxjrfz() {
        return qmJyxjrfz;
    }

    public void setQmJyxjrfz(BigDecimal qmJyxjrfz) {
        this.qmJyxjrfz = qmJyxjrfz;
    }

    public BigDecimal getQmYsjrfz() {
        return qmYsjrfz;
    }

    public void setQmYsjrfz(BigDecimal qmYsjrfz) {
        this.qmYsjrfz = qmYsjrfz;
    }

    public BigDecimal getNcYgyjzjlqqbd() {
        return ncYgyjzjlqqbd;
    }

    public void setNcYgyjzjlqqbd(BigDecimal ncYgyjzjlqqbd) {
        this.ncYgyjzjlqqbd = ncYgyjzjlqqbd;
    }

    public BigDecimal getQmYgyjzjlqqbd() {
        return qmYgyjzjlqqbd;
    }

    public void setQmYgyjzjlqqbd(BigDecimal qmYgyjzjlqqbd) {
        this.qmYgyjzjlqqbd = qmYgyjzjlqqbd;
    }

    public BigDecimal getNcYsjrzc() {
        return ncYsjrzc;
    }

    public void setNcYsjrzc(BigDecimal ncYsjrzc) {
        this.ncYsjrzc = ncYsjrzc;
    }

    public BigDecimal getNcCydsdzc() {
        return ncCydsdzc;
    }

    public void setNcCydsdzc(BigDecimal ncCydsdzc) {
        this.ncCydsdzc = ncCydsdzc;
    }

    public BigDecimal getQmCydsdzc() {
        return qmCydsdzc;
    }

    public void setQmCydsdzc(BigDecimal qmCydsdzc) {
        this.qmCydsdzc = qmCydsdzc;
    }

    public BigDecimal getNcYgyjzjljrfz() {
        return ncYgyjzjljrfz;
    }

    public void setNcYgyjzjljrfz(BigDecimal ncYgyjzjljrfz) {
        this.ncYgyjzjljrfz = ncYgyjzjljrfz;
    }

    public BigDecimal getQmYgyjzjljrfz() {
        return qmYgyjzjljrfz;
    }

    public void setQmYgyjzjljrfz(BigDecimal qmYgyjzjljrfz) {
        this.qmYgyjzjljrfz = qmYgyjzjljrfz;
    }

    public BigDecimal getNcYsjrfz() {
        return ncYsjrfz;
    }

    public void setNcYsjrfz(BigDecimal ncYsjrfz) {
        this.ncYsjrfz = ncYsjrfz;
    }

    public BigDecimal getNcCydsdfz() {
        return ncCydsdfz;
    }

    public void setNcCydsdfz(BigDecimal ncCydsdfz) {
        this.ncCydsdfz = ncCydsdfz;
    }

    public BigDecimal getQmCydsdfz() {
        return qmCydsdfz;
    }

    public void setQmCydsdfz(BigDecimal qmCydsdfz) {
        this.qmCydsdfz = qmCydsdfz;
    }

    public BigDecimal getNcQzyxg() {
        return ncQzyxg;
    }

    public void setNcQzyxg(BigDecimal ncQzyxg) {
        this.ncQzyxg = ncQzyxg;
    }

    public BigDecimal getNcYxz() {
        return ncYxz;
    }

    public void setNcYxz(BigDecimal ncYxz) {
        this.ncYxz = ncYxz;
    }

    public BigDecimal getNcQtqygj() {
        return ncQtqygj;
    }

    public void setNcQtqygj(BigDecimal ncQtqygj) {
        this.ncQtqygj = ncQtqygj;
    }
}
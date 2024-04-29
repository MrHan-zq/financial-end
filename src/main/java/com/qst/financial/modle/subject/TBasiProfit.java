package com.qst.financial.modle.subject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qst.financial.core.TableName;
import com.qst.financial.modle.base.PoModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 利润表
 * @author chenHao
 */
@TableName(name = "t_basi_profit") 
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TBasiProfit extends PoModel implements Serializable{

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date impTime;

    private String impUser;

    private String batchId;

    private String orgId;

    private String kjyear;

    private String kjmoth;

    private String kjyearMoth;

    private String jedw;

    private BigDecimal byZyywsr;					//主营业务收入

    private BigDecimal byZyywcb;						//减：主营业务成本

    private BigDecimal byZyywsjjfj;						//主营业务税金及附加

    private BigDecimal byZyywlr;					//主营业务利润（亏损以“－”号填列）

    private BigDecimal byQtywlr;						//加：其他业务利润（亏损以“－”号填列）

    private BigDecimal byYyfy;							//减： 营业费用

    private BigDecimal byGlfy;						//管理费用

    private BigDecimal byCwfy;						//财务费用

    private BigDecimal byYylr;				//营业利润（亏损以“－”号填列）

    private BigDecimal byTzsy;						//加：投资收益（损失以“－”号填列）

    private BigDecimal byBtsr;					//补贴收入

    private BigDecimal byYywsr;					//营业外收入

    private BigDecimal byYywzc;					//减：营业外支出

    private BigDecimal byLrze;					//利润总额（亏损总额以“－”号填列）

    private BigDecimal bySds;					//减：所得税

    private BigDecimal byJlr;					//净利润（净亏损以“－”号填列）

    private BigDecimal byCsclbm;					//出售、处理部门或被投资单位所得收益

    private BigDecimal byZrzhfsdss;				//自然灾害发生的损失

    private BigDecimal byKjzcbgzj;					//会计政策变更增加（或减少）净利润

    private BigDecimal byKjgjbgzj;				//会计估计变更增加（或减少）净利润

    private BigDecimal byZwczss;				//债务重组损失

    private BigDecimal byOthers;				//其他

    private BigDecimal bnZyywsr;

    private BigDecimal bnZyywcb;

    private BigDecimal bnZyywsjjfj;

    private BigDecimal bnZyywlr;

    private BigDecimal bnQtywlr;

    private BigDecimal bnYyfy;

    private BigDecimal bnGlfy;

    private BigDecimal bnCwfy;

    private BigDecimal bnYylr;

    private BigDecimal bnTzsy;

    private BigDecimal bnBtsr;

    private BigDecimal bnYywsr;

    private BigDecimal bnYywzc;

    private BigDecimal bnLrze;

    private BigDecimal bnSds;

    private BigDecimal bnJlr;

    private BigDecimal bnCsclbm;

    private BigDecimal bnZrzhfsdss;

    private BigDecimal bnKjzcbgzj;

    private BigDecimal bnKjgjbgzj;

    private BigDecimal bnZwczss;

    private BigDecimal bnOthers;

    private BigDecimal byXsfy;

    private BigDecimal bnXsfy;

    private BigDecimal byZcjzss;

    private BigDecimal bnZcjzss;

    private BigDecimal byGyjzbdsy;

    private BigDecimal bnGyjzbdsy;

    private BigDecimal byDlyqyhhyqydtzsy;

    private BigDecimal bnDlyqyhhyqydtzsy;

    private BigDecimal byFldzcczss;

    private BigDecimal bnFldzcczss;

    private BigDecimal byMgsy;

    private BigDecimal bnMgsy;

    private BigDecimal byJbmgsy;

    private BigDecimal bnJbmgsy;

    private BigDecimal byXsmgsy;

    private BigDecimal bnXsmgsy;

    private Long accountPortType;

    private String formatFlag;

    private BigDecimal byYyzcb;

    private BigDecimal bnYyzcb;

    private BigDecimal byGsymgsjlr;

    private BigDecimal bnGsymgsjlr;

    private BigDecimal bySsgdsy;

    private BigDecimal bnSsgdsy;

    private BigDecimal byYysr;

    private BigDecimal byJzcjzss;

    private BigDecimal byLyqytz;

    private BigDecimal byHysy;

    private BigDecimal byLqtsy;

    private BigDecimal byZcczsy;

    private BigDecimal bnZcczsy;

    private BigDecimal byQtsy;

    private BigDecimal bnQtsy;

    private BigDecimal byBhbfjlrbd;

    private BigDecimal bnBhbfjlrbd;

    private BigDecimal byCsclbmsy;

    private BigDecimal bnCsclbmsy;

    private BigDecimal byZrzhfsss;

    private BigDecimal bnZrzhfsss;

    private BigDecimal byKjzcbgzjjlr;

    private BigDecimal bnKjzcbgzjjlr;

    private BigDecimal byKjgjbgzjjlr;

    private BigDecimal bnKjgjbgzjjlr;

    private BigDecimal byQt;

    private BigDecimal bnQt;

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

    public BigDecimal getByZyywsr() {
        return byZyywsr;
    }

    public void setByZyywsr(BigDecimal byZyywsr) {
        this.byZyywsr = byZyywsr;
    }

    public BigDecimal getByZyywcb() {
        return byZyywcb;
    }

    public void setByZyywcb(BigDecimal byZyywcb) {
        this.byZyywcb = byZyywcb;
    }

    public BigDecimal getByZyywsjjfj() {
        return byZyywsjjfj;
    }

    public void setByZyywsjjfj(BigDecimal byZyywsjjfj) {
        this.byZyywsjjfj = byZyywsjjfj;
    }

    public BigDecimal getByZyywlr() {
        return byZyywlr;
    }

    public void setByZyywlr(BigDecimal byZyywlr) {
        this.byZyywlr = byZyywlr;
    }

    public BigDecimal getByQtywlr() {
        return byQtywlr;
    }

    public void setByQtywlr(BigDecimal byQtywlr) {
        this.byQtywlr = byQtywlr;
    }

    public BigDecimal getByYyfy() {
        return byYyfy;
    }

    public void setByYyfy(BigDecimal byYyfy) {
        this.byYyfy = byYyfy;
    }

    public BigDecimal getByGlfy() {
        return byGlfy;
    }

    public void setByGlfy(BigDecimal byGlfy) {
        this.byGlfy = byGlfy;
    }

    public BigDecimal getByCwfy() {
        return byCwfy;
    }

    public void setByCwfy(BigDecimal byCwfy) {
        this.byCwfy = byCwfy;
    }

    public BigDecimal getByYylr() {
        return byYylr;
    }

    public void setByYylr(BigDecimal byYylr) {
        this.byYylr = byYylr;
    }

    public BigDecimal getByTzsy() {
        return byTzsy;
    }

    public void setByTzsy(BigDecimal byTzsy) {
        this.byTzsy = byTzsy;
    }

    public BigDecimal getByBtsr() {
        return byBtsr;
    }

    public void setByBtsr(BigDecimal byBtsr) {
        this.byBtsr = byBtsr;
    }

    public BigDecimal getByYywsr() {
        return byYywsr;
    }

    public void setByYywsr(BigDecimal byYywsr) {
        this.byYywsr = byYywsr;
    }

    public BigDecimal getByYywzc() {
        return byYywzc;
    }

    public void setByYywzc(BigDecimal byYywzc) {
        this.byYywzc = byYywzc;
    }

    public BigDecimal getByLrze() {
        return byLrze;
    }

    public void setByLrze(BigDecimal byLrze) {
        this.byLrze = byLrze;
    }

    public BigDecimal getBySds() {
        return bySds;
    }

    public void setBySds(BigDecimal bySds) {
        this.bySds = bySds;
    }

    public BigDecimal getByJlr() {
        return byJlr;
    }

    public void setByJlr(BigDecimal byJlr) {
        this.byJlr = byJlr;
    }

    public BigDecimal getByCsclbm() {
        return byCsclbm;
    }

    public void setByCsclbm(BigDecimal byCsclbm) {
        this.byCsclbm = byCsclbm;
    }

    public BigDecimal getByZrzhfsdss() {
        return byZrzhfsdss;
    }

    public void setByZrzhfsdss(BigDecimal byZrzhfsdss) {
        this.byZrzhfsdss = byZrzhfsdss;
    }

    public BigDecimal getByKjzcbgzj() {
        return byKjzcbgzj;
    }

    public void setByKjzcbgzj(BigDecimal byKjzcbgzj) {
        this.byKjzcbgzj = byKjzcbgzj;
    }

    public BigDecimal getByKjgjbgzj() {
        return byKjgjbgzj;
    }

    public void setByKjgjbgzj(BigDecimal byKjgjbgzj) {
        this.byKjgjbgzj = byKjgjbgzj;
    }

    public BigDecimal getByZwczss() {
        return byZwczss;
    }

    public void setByZwczss(BigDecimal byZwczss) {
        this.byZwczss = byZwczss;
    }

    public BigDecimal getByOthers() {
        return byOthers;
    }

    public void setByOthers(BigDecimal byOthers) {
        this.byOthers = byOthers;
    }

    public BigDecimal getBnZyywsr() {
        return bnZyywsr;
    }

    public void setBnZyywsr(BigDecimal bnZyywsr) {
        this.bnZyywsr = bnZyywsr;
    }

    public BigDecimal getBnZyywcb() {
        return bnZyywcb;
    }

    public void setBnZyywcb(BigDecimal bnZyywcb) {
        this.bnZyywcb = bnZyywcb;
    }

    public BigDecimal getBnZyywsjjfj() {
        return bnZyywsjjfj;
    }

    public void setBnZyywsjjfj(BigDecimal bnZyywsjjfj) {
        this.bnZyywsjjfj = bnZyywsjjfj;
    }

    public BigDecimal getBnZyywlr() {
        return bnZyywlr;
    }

    public void setBnZyywlr(BigDecimal bnZyywlr) {
        this.bnZyywlr = bnZyywlr;
    }

    public BigDecimal getBnQtywlr() {
        return bnQtywlr;
    }

    public void setBnQtywlr(BigDecimal bnQtywlr) {
        this.bnQtywlr = bnQtywlr;
    }

    public BigDecimal getBnYyfy() {
        return bnYyfy;
    }

    public void setBnYyfy(BigDecimal bnYyfy) {
        this.bnYyfy = bnYyfy;
    }

    public BigDecimal getBnGlfy() {
        return bnGlfy;
    }

    public void setBnGlfy(BigDecimal bnGlfy) {
        this.bnGlfy = bnGlfy;
    }

    public BigDecimal getBnCwfy() {
        return bnCwfy;
    }

    public void setBnCwfy(BigDecimal bnCwfy) {
        this.bnCwfy = bnCwfy;
    }

    public BigDecimal getBnYylr() {
        return bnYylr;
    }

    public void setBnYylr(BigDecimal bnYylr) {
        this.bnYylr = bnYylr;
    }

    public BigDecimal getBnTzsy() {
        return bnTzsy;
    }

    public void setBnTzsy(BigDecimal bnTzsy) {
        this.bnTzsy = bnTzsy;
    }

    public BigDecimal getBnBtsr() {
        return bnBtsr;
    }

    public void setBnBtsr(BigDecimal bnBtsr) {
        this.bnBtsr = bnBtsr;
    }

    public BigDecimal getBnYywsr() {
        return bnYywsr;
    }

    public void setBnYywsr(BigDecimal bnYywsr) {
        this.bnYywsr = bnYywsr;
    }

    public BigDecimal getBnYywzc() {
        return bnYywzc;
    }

    public void setBnYywzc(BigDecimal bnYywzc) {
        this.bnYywzc = bnYywzc;
    }

    public BigDecimal getBnLrze() {
        return bnLrze;
    }

    public void setBnLrze(BigDecimal bnLrze) {
        this.bnLrze = bnLrze;
    }

    public BigDecimal getBnSds() {
        return bnSds;
    }

    public void setBnSds(BigDecimal bnSds) {
        this.bnSds = bnSds;
    }

    public BigDecimal getBnJlr() {
        return bnJlr;
    }

    public void setBnJlr(BigDecimal bnJlr) {
        this.bnJlr = bnJlr;
    }

    public BigDecimal getBnCsclbm() {
        return bnCsclbm;
    }

    public void setBnCsclbm(BigDecimal bnCsclbm) {
        this.bnCsclbm = bnCsclbm;
    }

    public BigDecimal getBnZrzhfsdss() {
        return bnZrzhfsdss;
    }

    public void setBnZrzhfsdss(BigDecimal bnZrzhfsdss) {
        this.bnZrzhfsdss = bnZrzhfsdss;
    }

    public BigDecimal getBnKjzcbgzj() {
        return bnKjzcbgzj;
    }

    public void setBnKjzcbgzj(BigDecimal bnKjzcbgzj) {
        this.bnKjzcbgzj = bnKjzcbgzj;
    }

    public BigDecimal getBnKjgjbgzj() {
        return bnKjgjbgzj;
    }

    public void setBnKjgjbgzj(BigDecimal bnKjgjbgzj) {
        this.bnKjgjbgzj = bnKjgjbgzj;
    }

    public BigDecimal getBnZwczss() {
        return bnZwczss;
    }

    public void setBnZwczss(BigDecimal bnZwczss) {
        this.bnZwczss = bnZwczss;
    }

    public BigDecimal getBnOthers() {
        return bnOthers;
    }

    public void setBnOthers(BigDecimal bnOthers) {
        this.bnOthers = bnOthers;
    }

    public BigDecimal getByXsfy() {
        return byXsfy;
    }

    public void setByXsfy(BigDecimal byXsfy) {
        this.byXsfy = byXsfy;
    }

    public BigDecimal getBnXsfy() {
        return bnXsfy;
    }

    public void setBnXsfy(BigDecimal bnXsfy) {
        this.bnXsfy = bnXsfy;
    }

    public BigDecimal getByZcjzss() {
        return byZcjzss;
    }

    public void setByZcjzss(BigDecimal byZcjzss) {
        this.byZcjzss = byZcjzss;
    }

    public BigDecimal getBnZcjzss() {
        return bnZcjzss;
    }

    public void setBnZcjzss(BigDecimal bnZcjzss) {
        this.bnZcjzss = bnZcjzss;
    }

    public BigDecimal getByGyjzbdsy() {
        return byGyjzbdsy;
    }

    public void setByGyjzbdsy(BigDecimal byGyjzbdsy) {
        this.byGyjzbdsy = byGyjzbdsy;
    }

    public BigDecimal getBnGyjzbdsy() {
        return bnGyjzbdsy;
    }

    public void setBnGyjzbdsy(BigDecimal bnGyjzbdsy) {
        this.bnGyjzbdsy = bnGyjzbdsy;
    }

    public BigDecimal getByDlyqyhhyqydtzsy() {
        return byDlyqyhhyqydtzsy;
    }

    public void setByDlyqyhhyqydtzsy(BigDecimal byDlyqyhhyqydtzsy) {
        this.byDlyqyhhyqydtzsy = byDlyqyhhyqydtzsy;
    }

    public BigDecimal getBnDlyqyhhyqydtzsy() {
        return bnDlyqyhhyqydtzsy;
    }

    public void setBnDlyqyhhyqydtzsy(BigDecimal bnDlyqyhhyqydtzsy) {
        this.bnDlyqyhhyqydtzsy = bnDlyqyhhyqydtzsy;
    }

    public BigDecimal getByFldzcczss() {
        return byFldzcczss;
    }

    public void setByFldzcczss(BigDecimal byFldzcczss) {
        this.byFldzcczss = byFldzcczss;
    }

    public BigDecimal getBnFldzcczss() {
        return bnFldzcczss;
    }

    public void setBnFldzcczss(BigDecimal bnFldzcczss) {
        this.bnFldzcczss = bnFldzcczss;
    }

    public BigDecimal getByMgsy() {
        return byMgsy;
    }

    public void setByMgsy(BigDecimal byMgsy) {
        this.byMgsy = byMgsy;
    }

    public BigDecimal getBnMgsy() {
        return bnMgsy;
    }

    public void setBnMgsy(BigDecimal bnMgsy) {
        this.bnMgsy = bnMgsy;
    }

    public BigDecimal getByJbmgsy() {
        return byJbmgsy;
    }

    public void setByJbmgsy(BigDecimal byJbmgsy) {
        this.byJbmgsy = byJbmgsy;
    }

    public BigDecimal getBnJbmgsy() {
        return bnJbmgsy;
    }

    public void setBnJbmgsy(BigDecimal bnJbmgsy) {
        this.bnJbmgsy = bnJbmgsy;
    }

    public BigDecimal getByXsmgsy() {
        return byXsmgsy;
    }

    public void setByXsmgsy(BigDecimal byXsmgsy) {
        this.byXsmgsy = byXsmgsy;
    }

    public BigDecimal getBnXsmgsy() {
        return bnXsmgsy;
    }

    public void setBnXsmgsy(BigDecimal bnXsmgsy) {
        this.bnXsmgsy = bnXsmgsy;
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

    public BigDecimal getByYyzcb() {
        return byYyzcb;
    }

    public void setByYyzcb(BigDecimal byYyzcb) {
        this.byYyzcb = byYyzcb;
    }

    public BigDecimal getBnYyzcb() {
        return bnYyzcb;
    }

    public void setBnYyzcb(BigDecimal bnYyzcb) {
        this.bnYyzcb = bnYyzcb;
    }

    public BigDecimal getByGsymgsjlr() {
        return byGsymgsjlr;
    }

    public void setByGsymgsjlr(BigDecimal byGsymgsjlr) {
        this.byGsymgsjlr = byGsymgsjlr;
    }

    public BigDecimal getBnGsymgsjlr() {
        return bnGsymgsjlr;
    }

    public void setBnGsymgsjlr(BigDecimal bnGsymgsjlr) {
        this.bnGsymgsjlr = bnGsymgsjlr;
    }

    public BigDecimal getBySsgdsy() {
        return bySsgdsy;
    }

    public void setBySsgdsy(BigDecimal bySsgdsy) {
        this.bySsgdsy = bySsgdsy;
    }

    public BigDecimal getBnSsgdsy() {
        return bnSsgdsy;
    }

    public void setBnSsgdsy(BigDecimal bnSsgdsy) {
        this.bnSsgdsy = bnSsgdsy;
    }

    public BigDecimal getByYysr() {
        return byYysr;
    }

    public void setByYysr(BigDecimal byYysr) {
        this.byYysr = byYysr;
    }

    public BigDecimal getByJzcjzss() {
        return byJzcjzss;
    }

    public void setByJzcjzss(BigDecimal byJzcjzss) {
        this.byJzcjzss = byJzcjzss;
    }

    public BigDecimal getByLyqytz() {
        return byLyqytz;
    }

    public void setByLyqytz(BigDecimal byLyqytz) {
        this.byLyqytz = byLyqytz;
    }

    public BigDecimal getByHysy() {
        return byHysy;
    }

    public void setByHysy(BigDecimal byHysy) {
        this.byHysy = byHysy;
    }

    public BigDecimal getByLqtsy() {
        return byLqtsy;
    }

    public void setByLqtsy(BigDecimal byLqtsy) {
        this.byLqtsy = byLqtsy;
    }

    public BigDecimal getByZcczsy() {
        return byZcczsy;
    }

    public void setByZcczsy(BigDecimal byZcczsy) {
        this.byZcczsy = byZcczsy;
    }

    public BigDecimal getBnZcczsy() {
        return bnZcczsy;
    }

    public void setBnZcczsy(BigDecimal bnZcczsy) {
        this.bnZcczsy = bnZcczsy;
    }

    public BigDecimal getByQtsy() {
        return byQtsy;
    }

    public void setByQtsy(BigDecimal byQtsy) {
        this.byQtsy = byQtsy;
    }

    public BigDecimal getBnQtsy() {
        return bnQtsy;
    }

    public void setBnQtsy(BigDecimal bnQtsy) {
        this.bnQtsy = bnQtsy;
    }

    public BigDecimal getByBhbfjlrbd() {
        return byBhbfjlrbd;
    }

    public void setByBhbfjlrbd(BigDecimal byBhbfjlrbd) {
        this.byBhbfjlrbd = byBhbfjlrbd;
    }

    public BigDecimal getBnBhbfjlrbd() {
        return bnBhbfjlrbd;
    }

    public void setBnBhbfjlrbd(BigDecimal bnBhbfjlrbd) {
        this.bnBhbfjlrbd = bnBhbfjlrbd;
    }

    public BigDecimal getByCsclbmsy() {
        return byCsclbmsy;
    }

    public void setByCsclbmsy(BigDecimal byCsclbmsy) {
        this.byCsclbmsy = byCsclbmsy;
    }

    public BigDecimal getBnCsclbmsy() {
        return bnCsclbmsy;
    }

    public void setBnCsclbmsy(BigDecimal bnCsclbmsy) {
        this.bnCsclbmsy = bnCsclbmsy;
    }

    public BigDecimal getByZrzhfsss() {
        return byZrzhfsss;
    }

    public void setByZrzhfsss(BigDecimal byZrzhfsss) {
        this.byZrzhfsss = byZrzhfsss;
    }

    public BigDecimal getBnZrzhfsss() {
        return bnZrzhfsss;
    }

    public void setBnZrzhfsss(BigDecimal bnZrzhfsss) {
        this.bnZrzhfsss = bnZrzhfsss;
    }

    public BigDecimal getByKjzcbgzjjlr() {
        return byKjzcbgzjjlr;
    }

    public void setByKjzcbgzjjlr(BigDecimal byKjzcbgzjjlr) {
        this.byKjzcbgzjjlr = byKjzcbgzjjlr;
    }

    public BigDecimal getBnKjzcbgzjjlr() {
        return bnKjzcbgzjjlr;
    }

    public void setBnKjzcbgzjjlr(BigDecimal bnKjzcbgzjjlr) {
        this.bnKjzcbgzjjlr = bnKjzcbgzjjlr;
    }

    public BigDecimal getByKjgjbgzjjlr() {
        return byKjgjbgzjjlr;
    }

    public void setByKjgjbgzjjlr(BigDecimal byKjgjbgzjjlr) {
        this.byKjgjbgzjjlr = byKjgjbgzjjlr;
    }

    public BigDecimal getBnKjgjbgzjjlr() {
        return bnKjgjbgzjjlr;
    }

    public void setBnKjgjbgzjjlr(BigDecimal bnKjgjbgzjjlr) {
        this.bnKjgjbgzjjlr = bnKjgjbgzjjlr;
    }

    public BigDecimal getByQt() {
        return byQt;
    }

    public void setByQt(BigDecimal byQt) {
        this.byQt = byQt;
    }

    public BigDecimal getBnQt() {
        return bnQt;
    }

    public void setBnQt(BigDecimal bnQt) {
        this.bnQt = bnQt;
    }
}
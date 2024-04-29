package com.qst.financial.modle.subject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qst.financial.core.TableName;
import com.qst.financial.modle.base.PoModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 现金流量表
 * @author chenHao
 */
@TableName(name = "t_basi_cash_flow") 
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TBasiCashFlow extends PoModel implements Serializable{

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date impTime;

    private String impUser;

    private String batchId;

    private String orgId;

    private String kjyear;

    private String kjmoth;

    private String kjyearMoth;

    private String jedw;

    private BigDecimal jyhdSddxj;

    private BigDecimal jyhdSddsffh;

    private BigDecimal jyhdSddqtygdxj;

    private BigDecimal jyhdXjlrxj;

    private BigDecimal jyhdGjzfdxj;

    private BigDecimal jyhdZfgzgdxj;

    private BigDecimal jyhdZfdgxsf;

    private BigDecimal jyhdZfdqtdxj;

    private BigDecimal jyhdXjlcxj;

    private BigDecimal jyhdJyhdcsdje;

    private BigDecimal tzhdShtzssdxj;

    private BigDecimal tzhdQdsyssdxj;

    private BigDecimal tzhdSshdxj;

    private BigDecimal tzhdSddqtygdxj;

    private BigDecimal tzhdXjlrxj;

    private BigDecimal tzhdSzfdxj;

    private BigDecimal tzhdTzszfdxj;

    private BigDecimal tzhdZfqtdxj;

    private BigDecimal tzhdXjlcxj;

    private BigDecimal tzhdTzhdscsdxjllje;

    private BigDecimal czhdXstzssddxj;

    private BigDecimal czhdJkssddxj;

    private BigDecimal czhdSdqtygdxj;

    private BigDecimal czhdXjlrxj;

    private BigDecimal czhdChzwszfdxj;

    private BigDecimal czhdFpglszfdxj;

    private BigDecimal czhdZfqtygdxj;

    private BigDecimal czhdXjlcxj;

    private BigDecimal czhdZchdxjllje;

    private BigDecimal czhdHlbdxjdyx;

    private BigDecimal czhdXjjxjdwjjze;

    private BigDecimal jlrjyhdJlr;

    private BigDecimal jlrjyhdJtzcjzzb;

    private BigDecimal jlrjyhdGdzczj;

    private BigDecimal jlrjyhdWxzctx;

    private BigDecimal jlrjyhdCqdtfytx;

    private BigDecimal jlrjyhdDtfyjs;

    private BigDecimal jlrjyhdYtfyzj;

    private BigDecimal jlrjyhdCqzcdss;

    private BigDecimal jlrjyhdGdzcbfss;

    private BigDecimal jlrjyhdCwfy;

    private BigDecimal jlrjyhdTzss;

    private BigDecimal jlrjyhdDysdx;

    private BigDecimal jlrjyhdChdjs;

    private BigDecimal jlrjyhdJyxysxmjs;

    private BigDecimal jlrjyhdJyxyfxmzj;

    private BigDecimal jlrjyhdOhters;

    private BigDecimal jlrjyhdJyhdcsxjllje;

    private BigDecimal bsjxjZwzwzb;

    private BigDecimal bsjxjYnndqzq;

    private BigDecimal bsjxjRzzrgdzc;

    private BigDecimal xjjzjeXjqmye;

    private BigDecimal xjjzjeXjdqcye;

    private BigDecimal xjjzjeXjdjwdqmye;

    private BigDecimal xjjzjeXjdjwdqcye;

    private BigDecimal xjjzjeXjdjwjzje;

    private Long accountPortType;

    private String formatFlag;

    private BigDecimal bqJyhdSddxj;

    private BigDecimal bqJyhdSddsffh;

    private BigDecimal bqJyhdSddqtygdxj;

    private BigDecimal bqJyhdXjlrxj;

    private BigDecimal bqJyhdGjzfdxj;

    private BigDecimal bqJyhdZfgzgdxj;

    private BigDecimal bqJyhdZfdgxsf;

    private BigDecimal bqJyhdZfdqtdxj;

    private BigDecimal bqJyhdXjlcxj;

    private BigDecimal bqJyhdJyhdcsdje;

    private BigDecimal bqTzhdShtzssdxj;

    private BigDecimal bqTzhdQdsyssdxj;

    private BigDecimal bqTzhdSshdxj;

    private BigDecimal bqTzhdSddqtygdxj;

    private BigDecimal bqTzhdXjlrxj;

    private BigDecimal bqTzhdSzfdxj;

    private BigDecimal bqTzhdTzszfdxj;

    private BigDecimal bqTzhdZfqtdxj;

    private BigDecimal bqTzhdXjlcxj;

    private BigDecimal bqTzhdTzhdscsdxjllje;

    private BigDecimal bqCzhdXstzssddxj;

    private BigDecimal bqCzhdJkssddxj;

    private BigDecimal bqCzhdSdqtygdxj;

    private BigDecimal bqCzhdXjlrxj;

    private BigDecimal bqCzhdChzwszfdxj;

    private BigDecimal bqCzhdFpglszfdxj;

    private BigDecimal bqCzhdZfqtygdxj;

    private BigDecimal bqCzhdXjlcxj;

    private BigDecimal bqCzhdZchdxjllje;

    private BigDecimal bqCzhdHlbdxjdyx;

    private BigDecimal bqCzhdXjjxjdwjjze;

    private BigDecimal bqJlrjyhdJlr;

    private BigDecimal bqJlrjyhdJtzcjzzb;

    private BigDecimal bqJlrjyhdGdzczj;

    private BigDecimal bqJlrjyhdWxzctx;

    private BigDecimal bqJlrjyhdCqdtfytx;

    private BigDecimal bqJlrjyhdDtfyjs;

    private BigDecimal bqJlrjyhdYtfyzj;

    private BigDecimal bqJlrjyhdCqzcdss;

    private BigDecimal bqJlrjyhdGdzcbfss;

    private BigDecimal bqJlrjyhdCwfy;

    private BigDecimal bqJlrjyhdTzss;

    private BigDecimal bqJlrjyhdDysdx;

    private BigDecimal bqJlrjyhdChdjs;

    private BigDecimal bqJlrjyhdJyxysxmjs;

    private BigDecimal bqJlrjyhdJyxyfxmzj;

    private BigDecimal bqJlrjyhdOhters;

    private BigDecimal bqJlrjyhdJyhdcsxjllje;

    private BigDecimal bqBsjxjZwzwzb;

    private BigDecimal bqBsjxjYnndqzq;

    private BigDecimal bqBsjxjRzzrgdzc;

    private BigDecimal bqXjjzjeXjqmye;

    private BigDecimal bqXjjzjeXjdqcye;

    private BigDecimal bqXjjzjeXjdjwdqmye;

    private BigDecimal bqXjjzjeXjdjwdqcye;

    private BigDecimal bqXjjzjeXjdjwjzje;

    private BigDecimal jyhdXssptglwsddxj;

    private BigDecimal bqJyhdXssptglwsddxj;

    private BigDecimal bqTzhdCzwxhqtshdxjje;

    private BigDecimal tzhdCzwxhqtshdxjje;

    private BigDecimal tzhdCzzgsjqtzfdxjje;

    private BigDecimal bqTzhdCzzgsjqtzfdxjje;

    private BigDecimal bqTzhdGjgdwxhqtzfdxj;

    private BigDecimal tzhdGjgdwxhqtzfdxj;

    private BigDecimal tzhdQdzgsjqtzfdxjje;

    private BigDecimal bqTzhdQdzgsjqtzfdxjje;

    private BigDecimal czhdZgszfgllr;

    private BigDecimal bqCzhdZgszfgllr;

    private BigDecimal czhdCzhdcsdxjllje;

    private BigDecimal bqCzhdCzhdcsdxjllje;

    private BigDecimal xjjxjdjwjzje;

    private BigDecimal bqXjjxjdjwjzje;

    private BigDecimal qcxjjxjdjwye;

    private BigDecimal bqQcxjjxjdjwye;

    private BigDecimal bqQmxjjxjdjwye;

    private BigDecimal qmxjjxjdjwye;

    private BigDecimal bqCzhdZgsxssddxj;

    private BigDecimal czhdZgsxssddxj;

    private BigDecimal bqZgsxjje;

    private BigDecimal bqQdzgsxjje;

    private BigDecimal bqJlr;

    private BigDecimal bqJtdzcjz;

    private BigDecimal bqGdzczj;

    private BigDecimal bqWxzctx;

    private BigDecimal bqCqdtfytx;

    private BigDecimal bqDtfyjs;

    private BigDecimal bqYtfyzj;

    private BigDecimal bqCzgdzcwxzc;

    private BigDecimal bqGdzcbfss;

    private BigDecimal bqCwfy;

    private BigDecimal bqTzss;

    private BigDecimal bqDyskdx;

    private BigDecimal bqChdjs;

    private BigDecimal bqJyxysxmdjs;

    private BigDecimal bqJyxyfxmdzj;

    private BigDecimal bqQt;

    private BigDecimal bqJyhdcsdxjllje;

    private BigDecimal bqBsjxjsz;

    private BigDecimal bqZwzwzb;

    private BigDecimal bqYnndqdkzhgszj;

    private BigDecimal bqRzzrgdzc;

    private BigDecimal bqXjjxjdjwjzjqk;

    private BigDecimal bqXjdqmye;

    private BigDecimal bqJxjdqcye;

    private BigDecimal bqJxjdjwdqmye;

    private BigDecimal bqJxjdjwdqcye;

    private BigDecimal bqXjjxjdjwjzje2;

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

    public BigDecimal getJyhdSddxj() {
        return jyhdSddxj;
    }

    public void setJyhdSddxj(BigDecimal jyhdSddxj) {
        this.jyhdSddxj = jyhdSddxj;
    }

    public BigDecimal getJyhdSddsffh() {
        return jyhdSddsffh;
    }

    public void setJyhdSddsffh(BigDecimal jyhdSddsffh) {
        this.jyhdSddsffh = jyhdSddsffh;
    }

    public BigDecimal getJyhdSddqtygdxj() {
        return jyhdSddqtygdxj;
    }

    public void setJyhdSddqtygdxj(BigDecimal jyhdSddqtygdxj) {
        this.jyhdSddqtygdxj = jyhdSddqtygdxj;
    }

    public BigDecimal getJyhdXjlrxj() {
        return jyhdXjlrxj;
    }

    public void setJyhdXjlrxj(BigDecimal jyhdXjlrxj) {
        this.jyhdXjlrxj = jyhdXjlrxj;
    }

    public BigDecimal getJyhdGjzfdxj() {
        return jyhdGjzfdxj;
    }

    public void setJyhdGjzfdxj(BigDecimal jyhdGjzfdxj) {
        this.jyhdGjzfdxj = jyhdGjzfdxj;
    }

    public BigDecimal getJyhdZfgzgdxj() {
        return jyhdZfgzgdxj;
    }

    public void setJyhdZfgzgdxj(BigDecimal jyhdZfgzgdxj) {
        this.jyhdZfgzgdxj = jyhdZfgzgdxj;
    }

    public BigDecimal getJyhdZfdgxsf() {
        return jyhdZfdgxsf;
    }

    public void setJyhdZfdgxsf(BigDecimal jyhdZfdgxsf) {
        this.jyhdZfdgxsf = jyhdZfdgxsf;
    }

    public BigDecimal getJyhdZfdqtdxj() {
        return jyhdZfdqtdxj;
    }

    public void setJyhdZfdqtdxj(BigDecimal jyhdZfdqtdxj) {
        this.jyhdZfdqtdxj = jyhdZfdqtdxj;
    }

    public BigDecimal getJyhdXjlcxj() {
        return jyhdXjlcxj;
    }

    public void setJyhdXjlcxj(BigDecimal jyhdXjlcxj) {
        this.jyhdXjlcxj = jyhdXjlcxj;
    }

    public BigDecimal getJyhdJyhdcsdje() {
        return jyhdJyhdcsdje;
    }

    public void setJyhdJyhdcsdje(BigDecimal jyhdJyhdcsdje) {
        this.jyhdJyhdcsdje = jyhdJyhdcsdje;
    }

    public BigDecimal getTzhdShtzssdxj() {
        return tzhdShtzssdxj;
    }

    public void setTzhdShtzssdxj(BigDecimal tzhdShtzssdxj) {
        this.tzhdShtzssdxj = tzhdShtzssdxj;
    }

    public BigDecimal getTzhdQdsyssdxj() {
        return tzhdQdsyssdxj;
    }

    public void setTzhdQdsyssdxj(BigDecimal tzhdQdsyssdxj) {
        this.tzhdQdsyssdxj = tzhdQdsyssdxj;
    }

    public BigDecimal getTzhdSshdxj() {
        return tzhdSshdxj;
    }

    public void setTzhdSshdxj(BigDecimal tzhdSshdxj) {
        this.tzhdSshdxj = tzhdSshdxj;
    }

    public BigDecimal getTzhdSddqtygdxj() {
        return tzhdSddqtygdxj;
    }

    public void setTzhdSddqtygdxj(BigDecimal tzhdSddqtygdxj) {
        this.tzhdSddqtygdxj = tzhdSddqtygdxj;
    }

    public BigDecimal getTzhdXjlrxj() {
        return tzhdXjlrxj;
    }

    public void setTzhdXjlrxj(BigDecimal tzhdXjlrxj) {
        this.tzhdXjlrxj = tzhdXjlrxj;
    }

    public BigDecimal getTzhdSzfdxj() {
        return tzhdSzfdxj;
    }

    public void setTzhdSzfdxj(BigDecimal tzhdSzfdxj) {
        this.tzhdSzfdxj = tzhdSzfdxj;
    }

    public BigDecimal getTzhdTzszfdxj() {
        return tzhdTzszfdxj;
    }

    public void setTzhdTzszfdxj(BigDecimal tzhdTzszfdxj) {
        this.tzhdTzszfdxj = tzhdTzszfdxj;
    }

    public BigDecimal getTzhdZfqtdxj() {
        return tzhdZfqtdxj;
    }

    public void setTzhdZfqtdxj(BigDecimal tzhdZfqtdxj) {
        this.tzhdZfqtdxj = tzhdZfqtdxj;
    }

    public BigDecimal getTzhdXjlcxj() {
        return tzhdXjlcxj;
    }

    public void setTzhdXjlcxj(BigDecimal tzhdXjlcxj) {
        this.tzhdXjlcxj = tzhdXjlcxj;
    }

    public BigDecimal getTzhdTzhdscsdxjllje() {
        return tzhdTzhdscsdxjllje;
    }

    public void setTzhdTzhdscsdxjllje(BigDecimal tzhdTzhdscsdxjllje) {
        this.tzhdTzhdscsdxjllje = tzhdTzhdscsdxjllje;
    }

    public BigDecimal getCzhdXstzssddxj() {
        return czhdXstzssddxj;
    }

    public void setCzhdXstzssddxj(BigDecimal czhdXstzssddxj) {
        this.czhdXstzssddxj = czhdXstzssddxj;
    }

    public BigDecimal getCzhdJkssddxj() {
        return czhdJkssddxj;
    }

    public void setCzhdJkssddxj(BigDecimal czhdJkssddxj) {
        this.czhdJkssddxj = czhdJkssddxj;
    }

    public BigDecimal getCzhdSdqtygdxj() {
        return czhdSdqtygdxj;
    }

    public void setCzhdSdqtygdxj(BigDecimal czhdSdqtygdxj) {
        this.czhdSdqtygdxj = czhdSdqtygdxj;
    }

    public BigDecimal getCzhdXjlrxj() {
        return czhdXjlrxj;
    }

    public void setCzhdXjlrxj(BigDecimal czhdXjlrxj) {
        this.czhdXjlrxj = czhdXjlrxj;
    }

    public BigDecimal getCzhdChzwszfdxj() {
        return czhdChzwszfdxj;
    }

    public void setCzhdChzwszfdxj(BigDecimal czhdChzwszfdxj) {
        this.czhdChzwszfdxj = czhdChzwszfdxj;
    }

    public BigDecimal getCzhdFpglszfdxj() {
        return czhdFpglszfdxj;
    }

    public void setCzhdFpglszfdxj(BigDecimal czhdFpglszfdxj) {
        this.czhdFpglszfdxj = czhdFpglszfdxj;
    }

    public BigDecimal getCzhdZfqtygdxj() {
        return czhdZfqtygdxj;
    }

    public void setCzhdZfqtygdxj(BigDecimal czhdZfqtygdxj) {
        this.czhdZfqtygdxj = czhdZfqtygdxj;
    }

    public BigDecimal getCzhdXjlcxj() {
        return czhdXjlcxj;
    }

    public void setCzhdXjlcxj(BigDecimal czhdXjlcxj) {
        this.czhdXjlcxj = czhdXjlcxj;
    }

    public BigDecimal getCzhdZchdxjllje() {
        return czhdZchdxjllje;
    }

    public void setCzhdZchdxjllje(BigDecimal czhdZchdxjllje) {
        this.czhdZchdxjllje = czhdZchdxjllje;
    }

    public BigDecimal getCzhdHlbdxjdyx() {
        return czhdHlbdxjdyx;
    }

    public void setCzhdHlbdxjdyx(BigDecimal czhdHlbdxjdyx) {
        this.czhdHlbdxjdyx = czhdHlbdxjdyx;
    }

    public BigDecimal getCzhdXjjxjdwjjze() {
        return czhdXjjxjdwjjze;
    }

    public void setCzhdXjjxjdwjjze(BigDecimal czhdXjjxjdwjjze) {
        this.czhdXjjxjdwjjze = czhdXjjxjdwjjze;
    }

    public BigDecimal getJlrjyhdJlr() {
        return jlrjyhdJlr;
    }

    public void setJlrjyhdJlr(BigDecimal jlrjyhdJlr) {
        this.jlrjyhdJlr = jlrjyhdJlr;
    }

    public BigDecimal getJlrjyhdJtzcjzzb() {
        return jlrjyhdJtzcjzzb;
    }

    public void setJlrjyhdJtzcjzzb(BigDecimal jlrjyhdJtzcjzzb) {
        this.jlrjyhdJtzcjzzb = jlrjyhdJtzcjzzb;
    }

    public BigDecimal getJlrjyhdGdzczj() {
        return jlrjyhdGdzczj;
    }

    public void setJlrjyhdGdzczj(BigDecimal jlrjyhdGdzczj) {
        this.jlrjyhdGdzczj = jlrjyhdGdzczj;
    }

    public BigDecimal getJlrjyhdWxzctx() {
        return jlrjyhdWxzctx;
    }

    public void setJlrjyhdWxzctx(BigDecimal jlrjyhdWxzctx) {
        this.jlrjyhdWxzctx = jlrjyhdWxzctx;
    }

    public BigDecimal getJlrjyhdCqdtfytx() {
        return jlrjyhdCqdtfytx;
    }

    public void setJlrjyhdCqdtfytx(BigDecimal jlrjyhdCqdtfytx) {
        this.jlrjyhdCqdtfytx = jlrjyhdCqdtfytx;
    }

    public BigDecimal getJlrjyhdDtfyjs() {
        return jlrjyhdDtfyjs;
    }

    public void setJlrjyhdDtfyjs(BigDecimal jlrjyhdDtfyjs) {
        this.jlrjyhdDtfyjs = jlrjyhdDtfyjs;
    }

    public BigDecimal getJlrjyhdYtfyzj() {
        return jlrjyhdYtfyzj;
    }

    public void setJlrjyhdYtfyzj(BigDecimal jlrjyhdYtfyzj) {
        this.jlrjyhdYtfyzj = jlrjyhdYtfyzj;
    }

    public BigDecimal getJlrjyhdCqzcdss() {
        return jlrjyhdCqzcdss;
    }

    public void setJlrjyhdCqzcdss(BigDecimal jlrjyhdCqzcdss) {
        this.jlrjyhdCqzcdss = jlrjyhdCqzcdss;
    }

    public BigDecimal getJlrjyhdGdzcbfss() {
        return jlrjyhdGdzcbfss;
    }

    public void setJlrjyhdGdzcbfss(BigDecimal jlrjyhdGdzcbfss) {
        this.jlrjyhdGdzcbfss = jlrjyhdGdzcbfss;
    }

    public BigDecimal getJlrjyhdCwfy() {
        return jlrjyhdCwfy;
    }

    public void setJlrjyhdCwfy(BigDecimal jlrjyhdCwfy) {
        this.jlrjyhdCwfy = jlrjyhdCwfy;
    }

    public BigDecimal getJlrjyhdTzss() {
        return jlrjyhdTzss;
    }

    public void setJlrjyhdTzss(BigDecimal jlrjyhdTzss) {
        this.jlrjyhdTzss = jlrjyhdTzss;
    }

    public BigDecimal getJlrjyhdDysdx() {
        return jlrjyhdDysdx;
    }

    public void setJlrjyhdDysdx(BigDecimal jlrjyhdDysdx) {
        this.jlrjyhdDysdx = jlrjyhdDysdx;
    }

    public BigDecimal getJlrjyhdChdjs() {
        return jlrjyhdChdjs;
    }

    public void setJlrjyhdChdjs(BigDecimal jlrjyhdChdjs) {
        this.jlrjyhdChdjs = jlrjyhdChdjs;
    }

    public BigDecimal getJlrjyhdJyxysxmjs() {
        return jlrjyhdJyxysxmjs;
    }

    public void setJlrjyhdJyxysxmjs(BigDecimal jlrjyhdJyxysxmjs) {
        this.jlrjyhdJyxysxmjs = jlrjyhdJyxysxmjs;
    }

    public BigDecimal getJlrjyhdJyxyfxmzj() {
        return jlrjyhdJyxyfxmzj;
    }

    public void setJlrjyhdJyxyfxmzj(BigDecimal jlrjyhdJyxyfxmzj) {
        this.jlrjyhdJyxyfxmzj = jlrjyhdJyxyfxmzj;
    }

    public BigDecimal getJlrjyhdOhters() {
        return jlrjyhdOhters;
    }

    public void setJlrjyhdOhters(BigDecimal jlrjyhdOhters) {
        this.jlrjyhdOhters = jlrjyhdOhters;
    }

    public BigDecimal getJlrjyhdJyhdcsxjllje() {
        return jlrjyhdJyhdcsxjllje;
    }

    public void setJlrjyhdJyhdcsxjllje(BigDecimal jlrjyhdJyhdcsxjllje) {
        this.jlrjyhdJyhdcsxjllje = jlrjyhdJyhdcsxjllje;
    }

    public BigDecimal getBsjxjZwzwzb() {
        return bsjxjZwzwzb;
    }

    public void setBsjxjZwzwzb(BigDecimal bsjxjZwzwzb) {
        this.bsjxjZwzwzb = bsjxjZwzwzb;
    }

    public BigDecimal getBsjxjYnndqzq() {
        return bsjxjYnndqzq;
    }

    public void setBsjxjYnndqzq(BigDecimal bsjxjYnndqzq) {
        this.bsjxjYnndqzq = bsjxjYnndqzq;
    }

    public BigDecimal getBsjxjRzzrgdzc() {
        return bsjxjRzzrgdzc;
    }

    public void setBsjxjRzzrgdzc(BigDecimal bsjxjRzzrgdzc) {
        this.bsjxjRzzrgdzc = bsjxjRzzrgdzc;
    }

    public BigDecimal getXjjzjeXjqmye() {
        return xjjzjeXjqmye;
    }

    public void setXjjzjeXjqmye(BigDecimal xjjzjeXjqmye) {
        this.xjjzjeXjqmye = xjjzjeXjqmye;
    }

    public BigDecimal getXjjzjeXjdqcye() {
        return xjjzjeXjdqcye;
    }

    public void setXjjzjeXjdqcye(BigDecimal xjjzjeXjdqcye) {
        this.xjjzjeXjdqcye = xjjzjeXjdqcye;
    }

    public BigDecimal getXjjzjeXjdjwdqmye() {
        return xjjzjeXjdjwdqmye;
    }

    public void setXjjzjeXjdjwdqmye(BigDecimal xjjzjeXjdjwdqmye) {
        this.xjjzjeXjdjwdqmye = xjjzjeXjdjwdqmye;
    }

    public BigDecimal getXjjzjeXjdjwdqcye() {
        return xjjzjeXjdjwdqcye;
    }

    public void setXjjzjeXjdjwdqcye(BigDecimal xjjzjeXjdjwdqcye) {
        this.xjjzjeXjdjwdqcye = xjjzjeXjdjwdqcye;
    }

    public BigDecimal getXjjzjeXjdjwjzje() {
        return xjjzjeXjdjwjzje;
    }

    public void setXjjzjeXjdjwjzje(BigDecimal xjjzjeXjdjwjzje) {
        this.xjjzjeXjdjwjzje = xjjzjeXjdjwjzje;
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

    public BigDecimal getBqJyhdSddxj() {
        return bqJyhdSddxj;
    }

    public void setBqJyhdSddxj(BigDecimal bqJyhdSddxj) {
        this.bqJyhdSddxj = bqJyhdSddxj;
    }

    public BigDecimal getBqJyhdSddsffh() {
        return bqJyhdSddsffh;
    }

    public void setBqJyhdSddsffh(BigDecimal bqJyhdSddsffh) {
        this.bqJyhdSddsffh = bqJyhdSddsffh;
    }

    public BigDecimal getBqJyhdSddqtygdxj() {
        return bqJyhdSddqtygdxj;
    }

    public void setBqJyhdSddqtygdxj(BigDecimal bqJyhdSddqtygdxj) {
        this.bqJyhdSddqtygdxj = bqJyhdSddqtygdxj;
    }

    public BigDecimal getBqJyhdXjlrxj() {
        return bqJyhdXjlrxj;
    }

    public void setBqJyhdXjlrxj(BigDecimal bqJyhdXjlrxj) {
        this.bqJyhdXjlrxj = bqJyhdXjlrxj;
    }

    public BigDecimal getBqJyhdGjzfdxj() {
        return bqJyhdGjzfdxj;
    }

    public void setBqJyhdGjzfdxj(BigDecimal bqJyhdGjzfdxj) {
        this.bqJyhdGjzfdxj = bqJyhdGjzfdxj;
    }

    public BigDecimal getBqJyhdZfgzgdxj() {
        return bqJyhdZfgzgdxj;
    }

    public void setBqJyhdZfgzgdxj(BigDecimal bqJyhdZfgzgdxj) {
        this.bqJyhdZfgzgdxj = bqJyhdZfgzgdxj;
    }

    public BigDecimal getBqJyhdZfdgxsf() {
        return bqJyhdZfdgxsf;
    }

    public void setBqJyhdZfdgxsf(BigDecimal bqJyhdZfdgxsf) {
        this.bqJyhdZfdgxsf = bqJyhdZfdgxsf;
    }

    public BigDecimal getBqJyhdZfdqtdxj() {
        return bqJyhdZfdqtdxj;
    }

    public void setBqJyhdZfdqtdxj(BigDecimal bqJyhdZfdqtdxj) {
        this.bqJyhdZfdqtdxj = bqJyhdZfdqtdxj;
    }

    public BigDecimal getBqJyhdXjlcxj() {
        return bqJyhdXjlcxj;
    }

    public void setBqJyhdXjlcxj(BigDecimal bqJyhdXjlcxj) {
        this.bqJyhdXjlcxj = bqJyhdXjlcxj;
    }

    public BigDecimal getBqJyhdJyhdcsdje() {
        return bqJyhdJyhdcsdje;
    }

    public void setBqJyhdJyhdcsdje(BigDecimal bqJyhdJyhdcsdje) {
        this.bqJyhdJyhdcsdje = bqJyhdJyhdcsdje;
    }

    public BigDecimal getBqTzhdShtzssdxj() {
        return bqTzhdShtzssdxj;
    }

    public void setBqTzhdShtzssdxj(BigDecimal bqTzhdShtzssdxj) {
        this.bqTzhdShtzssdxj = bqTzhdShtzssdxj;
    }

    public BigDecimal getBqTzhdQdsyssdxj() {
        return bqTzhdQdsyssdxj;
    }

    public void setBqTzhdQdsyssdxj(BigDecimal bqTzhdQdsyssdxj) {
        this.bqTzhdQdsyssdxj = bqTzhdQdsyssdxj;
    }

    public BigDecimal getBqTzhdSshdxj() {
        return bqTzhdSshdxj;
    }

    public void setBqTzhdSshdxj(BigDecimal bqTzhdSshdxj) {
        this.bqTzhdSshdxj = bqTzhdSshdxj;
    }

    public BigDecimal getBqTzhdSddqtygdxj() {
        return bqTzhdSddqtygdxj;
    }

    public void setBqTzhdSddqtygdxj(BigDecimal bqTzhdSddqtygdxj) {
        this.bqTzhdSddqtygdxj = bqTzhdSddqtygdxj;
    }

    public BigDecimal getBqTzhdXjlrxj() {
        return bqTzhdXjlrxj;
    }

    public void setBqTzhdXjlrxj(BigDecimal bqTzhdXjlrxj) {
        this.bqTzhdXjlrxj = bqTzhdXjlrxj;
    }

    public BigDecimal getBqTzhdSzfdxj() {
        return bqTzhdSzfdxj;
    }

    public void setBqTzhdSzfdxj(BigDecimal bqTzhdSzfdxj) {
        this.bqTzhdSzfdxj = bqTzhdSzfdxj;
    }

    public BigDecimal getBqTzhdTzszfdxj() {
        return bqTzhdTzszfdxj;
    }

    public void setBqTzhdTzszfdxj(BigDecimal bqTzhdTzszfdxj) {
        this.bqTzhdTzszfdxj = bqTzhdTzszfdxj;
    }

    public BigDecimal getBqTzhdZfqtdxj() {
        return bqTzhdZfqtdxj;
    }

    public void setBqTzhdZfqtdxj(BigDecimal bqTzhdZfqtdxj) {
        this.bqTzhdZfqtdxj = bqTzhdZfqtdxj;
    }

    public BigDecimal getBqTzhdXjlcxj() {
        return bqTzhdXjlcxj;
    }

    public void setBqTzhdXjlcxj(BigDecimal bqTzhdXjlcxj) {
        this.bqTzhdXjlcxj = bqTzhdXjlcxj;
    }

    public BigDecimal getBqTzhdTzhdscsdxjllje() {
        return bqTzhdTzhdscsdxjllje;
    }

    public void setBqTzhdTzhdscsdxjllje(BigDecimal bqTzhdTzhdscsdxjllje) {
        this.bqTzhdTzhdscsdxjllje = bqTzhdTzhdscsdxjllje;
    }

    public BigDecimal getBqCzhdXstzssddxj() {
        return bqCzhdXstzssddxj;
    }

    public void setBqCzhdXstzssddxj(BigDecimal bqCzhdXstzssddxj) {
        this.bqCzhdXstzssddxj = bqCzhdXstzssddxj;
    }

    public BigDecimal getBqCzhdJkssddxj() {
        return bqCzhdJkssddxj;
    }

    public void setBqCzhdJkssddxj(BigDecimal bqCzhdJkssddxj) {
        this.bqCzhdJkssddxj = bqCzhdJkssddxj;
    }

    public BigDecimal getBqCzhdSdqtygdxj() {
        return bqCzhdSdqtygdxj;
    }

    public void setBqCzhdSdqtygdxj(BigDecimal bqCzhdSdqtygdxj) {
        this.bqCzhdSdqtygdxj = bqCzhdSdqtygdxj;
    }

    public BigDecimal getBqCzhdXjlrxj() {
        return bqCzhdXjlrxj;
    }

    public void setBqCzhdXjlrxj(BigDecimal bqCzhdXjlrxj) {
        this.bqCzhdXjlrxj = bqCzhdXjlrxj;
    }

    public BigDecimal getBqCzhdChzwszfdxj() {
        return bqCzhdChzwszfdxj;
    }

    public void setBqCzhdChzwszfdxj(BigDecimal bqCzhdChzwszfdxj) {
        this.bqCzhdChzwszfdxj = bqCzhdChzwszfdxj;
    }

    public BigDecimal getBqCzhdFpglszfdxj() {
        return bqCzhdFpglszfdxj;
    }

    public void setBqCzhdFpglszfdxj(BigDecimal bqCzhdFpglszfdxj) {
        this.bqCzhdFpglszfdxj = bqCzhdFpglszfdxj;
    }

    public BigDecimal getBqCzhdZfqtygdxj() {
        return bqCzhdZfqtygdxj;
    }

    public void setBqCzhdZfqtygdxj(BigDecimal bqCzhdZfqtygdxj) {
        this.bqCzhdZfqtygdxj = bqCzhdZfqtygdxj;
    }

    public BigDecimal getBqCzhdXjlcxj() {
        return bqCzhdXjlcxj;
    }

    public void setBqCzhdXjlcxj(BigDecimal bqCzhdXjlcxj) {
        this.bqCzhdXjlcxj = bqCzhdXjlcxj;
    }

    public BigDecimal getBqCzhdZchdxjllje() {
        return bqCzhdZchdxjllje;
    }

    public void setBqCzhdZchdxjllje(BigDecimal bqCzhdZchdxjllje) {
        this.bqCzhdZchdxjllje = bqCzhdZchdxjllje;
    }

    public BigDecimal getBqCzhdHlbdxjdyx() {
        return bqCzhdHlbdxjdyx;
    }

    public void setBqCzhdHlbdxjdyx(BigDecimal bqCzhdHlbdxjdyx) {
        this.bqCzhdHlbdxjdyx = bqCzhdHlbdxjdyx;
    }

    public BigDecimal getBqCzhdXjjxjdwjjze() {
        return bqCzhdXjjxjdwjjze;
    }

    public void setBqCzhdXjjxjdwjjze(BigDecimal bqCzhdXjjxjdwjjze) {
        this.bqCzhdXjjxjdwjjze = bqCzhdXjjxjdwjjze;
    }

    public BigDecimal getBqJlrjyhdJlr() {
        return bqJlrjyhdJlr;
    }

    public void setBqJlrjyhdJlr(BigDecimal bqJlrjyhdJlr) {
        this.bqJlrjyhdJlr = bqJlrjyhdJlr;
    }

    public BigDecimal getBqJlrjyhdJtzcjzzb() {
        return bqJlrjyhdJtzcjzzb;
    }

    public void setBqJlrjyhdJtzcjzzb(BigDecimal bqJlrjyhdJtzcjzzb) {
        this.bqJlrjyhdJtzcjzzb = bqJlrjyhdJtzcjzzb;
    }

    public BigDecimal getBqJlrjyhdGdzczj() {
        return bqJlrjyhdGdzczj;
    }

    public void setBqJlrjyhdGdzczj(BigDecimal bqJlrjyhdGdzczj) {
        this.bqJlrjyhdGdzczj = bqJlrjyhdGdzczj;
    }

    public BigDecimal getBqJlrjyhdWxzctx() {
        return bqJlrjyhdWxzctx;
    }

    public void setBqJlrjyhdWxzctx(BigDecimal bqJlrjyhdWxzctx) {
        this.bqJlrjyhdWxzctx = bqJlrjyhdWxzctx;
    }

    public BigDecimal getBqJlrjyhdCqdtfytx() {
        return bqJlrjyhdCqdtfytx;
    }

    public void setBqJlrjyhdCqdtfytx(BigDecimal bqJlrjyhdCqdtfytx) {
        this.bqJlrjyhdCqdtfytx = bqJlrjyhdCqdtfytx;
    }

    public BigDecimal getBqJlrjyhdDtfyjs() {
        return bqJlrjyhdDtfyjs;
    }

    public void setBqJlrjyhdDtfyjs(BigDecimal bqJlrjyhdDtfyjs) {
        this.bqJlrjyhdDtfyjs = bqJlrjyhdDtfyjs;
    }

    public BigDecimal getBqJlrjyhdYtfyzj() {
        return bqJlrjyhdYtfyzj;
    }

    public void setBqJlrjyhdYtfyzj(BigDecimal bqJlrjyhdYtfyzj) {
        this.bqJlrjyhdYtfyzj = bqJlrjyhdYtfyzj;
    }

    public BigDecimal getBqJlrjyhdCqzcdss() {
        return bqJlrjyhdCqzcdss;
    }

    public void setBqJlrjyhdCqzcdss(BigDecimal bqJlrjyhdCqzcdss) {
        this.bqJlrjyhdCqzcdss = bqJlrjyhdCqzcdss;
    }

    public BigDecimal getBqJlrjyhdGdzcbfss() {
        return bqJlrjyhdGdzcbfss;
    }

    public void setBqJlrjyhdGdzcbfss(BigDecimal bqJlrjyhdGdzcbfss) {
        this.bqJlrjyhdGdzcbfss = bqJlrjyhdGdzcbfss;
    }

    public BigDecimal getBqJlrjyhdCwfy() {
        return bqJlrjyhdCwfy;
    }

    public void setBqJlrjyhdCwfy(BigDecimal bqJlrjyhdCwfy) {
        this.bqJlrjyhdCwfy = bqJlrjyhdCwfy;
    }

    public BigDecimal getBqJlrjyhdTzss() {
        return bqJlrjyhdTzss;
    }

    public void setBqJlrjyhdTzss(BigDecimal bqJlrjyhdTzss) {
        this.bqJlrjyhdTzss = bqJlrjyhdTzss;
    }

    public BigDecimal getBqJlrjyhdDysdx() {
        return bqJlrjyhdDysdx;
    }

    public void setBqJlrjyhdDysdx(BigDecimal bqJlrjyhdDysdx) {
        this.bqJlrjyhdDysdx = bqJlrjyhdDysdx;
    }

    public BigDecimal getBqJlrjyhdChdjs() {
        return bqJlrjyhdChdjs;
    }

    public void setBqJlrjyhdChdjs(BigDecimal bqJlrjyhdChdjs) {
        this.bqJlrjyhdChdjs = bqJlrjyhdChdjs;
    }

    public BigDecimal getBqJlrjyhdJyxysxmjs() {
        return bqJlrjyhdJyxysxmjs;
    }

    public void setBqJlrjyhdJyxysxmjs(BigDecimal bqJlrjyhdJyxysxmjs) {
        this.bqJlrjyhdJyxysxmjs = bqJlrjyhdJyxysxmjs;
    }

    public BigDecimal getBqJlrjyhdJyxyfxmzj() {
        return bqJlrjyhdJyxyfxmzj;
    }

    public void setBqJlrjyhdJyxyfxmzj(BigDecimal bqJlrjyhdJyxyfxmzj) {
        this.bqJlrjyhdJyxyfxmzj = bqJlrjyhdJyxyfxmzj;
    }

    public BigDecimal getBqJlrjyhdOhters() {
        return bqJlrjyhdOhters;
    }

    public void setBqJlrjyhdOhters(BigDecimal bqJlrjyhdOhters) {
        this.bqJlrjyhdOhters = bqJlrjyhdOhters;
    }

    public BigDecimal getBqJlrjyhdJyhdcsxjllje() {
        return bqJlrjyhdJyhdcsxjllje;
    }

    public void setBqJlrjyhdJyhdcsxjllje(BigDecimal bqJlrjyhdJyhdcsxjllje) {
        this.bqJlrjyhdJyhdcsxjllje = bqJlrjyhdJyhdcsxjllje;
    }

    public BigDecimal getBqBsjxjZwzwzb() {
        return bqBsjxjZwzwzb;
    }

    public void setBqBsjxjZwzwzb(BigDecimal bqBsjxjZwzwzb) {
        this.bqBsjxjZwzwzb = bqBsjxjZwzwzb;
    }

    public BigDecimal getBqBsjxjYnndqzq() {
        return bqBsjxjYnndqzq;
    }

    public void setBqBsjxjYnndqzq(BigDecimal bqBsjxjYnndqzq) {
        this.bqBsjxjYnndqzq = bqBsjxjYnndqzq;
    }

    public BigDecimal getBqBsjxjRzzrgdzc() {
        return bqBsjxjRzzrgdzc;
    }

    public void setBqBsjxjRzzrgdzc(BigDecimal bqBsjxjRzzrgdzc) {
        this.bqBsjxjRzzrgdzc = bqBsjxjRzzrgdzc;
    }

    public BigDecimal getBqXjjzjeXjqmye() {
        return bqXjjzjeXjqmye;
    }

    public void setBqXjjzjeXjqmye(BigDecimal bqXjjzjeXjqmye) {
        this.bqXjjzjeXjqmye = bqXjjzjeXjqmye;
    }

    public BigDecimal getBqXjjzjeXjdqcye() {
        return bqXjjzjeXjdqcye;
    }

    public void setBqXjjzjeXjdqcye(BigDecimal bqXjjzjeXjdqcye) {
        this.bqXjjzjeXjdqcye = bqXjjzjeXjdqcye;
    }

    public BigDecimal getBqXjjzjeXjdjwdqmye() {
        return bqXjjzjeXjdjwdqmye;
    }

    public void setBqXjjzjeXjdjwdqmye(BigDecimal bqXjjzjeXjdjwdqmye) {
        this.bqXjjzjeXjdjwdqmye = bqXjjzjeXjdjwdqmye;
    }

    public BigDecimal getBqXjjzjeXjdjwdqcye() {
        return bqXjjzjeXjdjwdqcye;
    }

    public void setBqXjjzjeXjdjwdqcye(BigDecimal bqXjjzjeXjdjwdqcye) {
        this.bqXjjzjeXjdjwdqcye = bqXjjzjeXjdjwdqcye;
    }

    public BigDecimal getBqXjjzjeXjdjwjzje() {
        return bqXjjzjeXjdjwjzje;
    }

    public void setBqXjjzjeXjdjwjzje(BigDecimal bqXjjzjeXjdjwjzje) {
        this.bqXjjzjeXjdjwjzje = bqXjjzjeXjdjwjzje;
    }

    public BigDecimal getJyhdXssptglwsddxj() {
        return jyhdXssptglwsddxj;
    }

    public void setJyhdXssptglwsddxj(BigDecimal jyhdXssptglwsddxj) {
        this.jyhdXssptglwsddxj = jyhdXssptglwsddxj;
    }

    public BigDecimal getBqJyhdXssptglwsddxj() {
        return bqJyhdXssptglwsddxj;
    }

    public void setBqJyhdXssptglwsddxj(BigDecimal bqJyhdXssptglwsddxj) {
        this.bqJyhdXssptglwsddxj = bqJyhdXssptglwsddxj;
    }

    public BigDecimal getBqTzhdCzwxhqtshdxjje() {
        return bqTzhdCzwxhqtshdxjje;
    }

    public void setBqTzhdCzwxhqtshdxjje(BigDecimal bqTzhdCzwxhqtshdxjje) {
        this.bqTzhdCzwxhqtshdxjje = bqTzhdCzwxhqtshdxjje;
    }

    public BigDecimal getTzhdCzwxhqtshdxjje() {
        return tzhdCzwxhqtshdxjje;
    }

    public void setTzhdCzwxhqtshdxjje(BigDecimal tzhdCzwxhqtshdxjje) {
        this.tzhdCzwxhqtshdxjje = tzhdCzwxhqtshdxjje;
    }

    public BigDecimal getTzhdCzzgsjqtzfdxjje() {
        return tzhdCzzgsjqtzfdxjje;
    }

    public void setTzhdCzzgsjqtzfdxjje(BigDecimal tzhdCzzgsjqtzfdxjje) {
        this.tzhdCzzgsjqtzfdxjje = tzhdCzzgsjqtzfdxjje;
    }

    public BigDecimal getBqTzhdCzzgsjqtzfdxjje() {
        return bqTzhdCzzgsjqtzfdxjje;
    }

    public void setBqTzhdCzzgsjqtzfdxjje(BigDecimal bqTzhdCzzgsjqtzfdxjje) {
        this.bqTzhdCzzgsjqtzfdxjje = bqTzhdCzzgsjqtzfdxjje;
    }

    public BigDecimal getBqTzhdGjgdwxhqtzfdxj() {
        return bqTzhdGjgdwxhqtzfdxj;
    }

    public void setBqTzhdGjgdwxhqtzfdxj(BigDecimal bqTzhdGjgdwxhqtzfdxj) {
        this.bqTzhdGjgdwxhqtzfdxj = bqTzhdGjgdwxhqtzfdxj;
    }

    public BigDecimal getTzhdGjgdwxhqtzfdxj() {
        return tzhdGjgdwxhqtzfdxj;
    }

    public void setTzhdGjgdwxhqtzfdxj(BigDecimal tzhdGjgdwxhqtzfdxj) {
        this.tzhdGjgdwxhqtzfdxj = tzhdGjgdwxhqtzfdxj;
    }

    public BigDecimal getTzhdQdzgsjqtzfdxjje() {
        return tzhdQdzgsjqtzfdxjje;
    }

    public void setTzhdQdzgsjqtzfdxjje(BigDecimal tzhdQdzgsjqtzfdxjje) {
        this.tzhdQdzgsjqtzfdxjje = tzhdQdzgsjqtzfdxjje;
    }

    public BigDecimal getBqTzhdQdzgsjqtzfdxjje() {
        return bqTzhdQdzgsjqtzfdxjje;
    }

    public void setBqTzhdQdzgsjqtzfdxjje(BigDecimal bqTzhdQdzgsjqtzfdxjje) {
        this.bqTzhdQdzgsjqtzfdxjje = bqTzhdQdzgsjqtzfdxjje;
    }

    public BigDecimal getCzhdZgszfgllr() {
        return czhdZgszfgllr;
    }

    public void setCzhdZgszfgllr(BigDecimal czhdZgszfgllr) {
        this.czhdZgszfgllr = czhdZgszfgllr;
    }

    public BigDecimal getBqCzhdZgszfgllr() {
        return bqCzhdZgszfgllr;
    }

    public void setBqCzhdZgszfgllr(BigDecimal bqCzhdZgszfgllr) {
        this.bqCzhdZgszfgllr = bqCzhdZgszfgllr;
    }

    public BigDecimal getCzhdCzhdcsdxjllje() {
        return czhdCzhdcsdxjllje;
    }

    public void setCzhdCzhdcsdxjllje(BigDecimal czhdCzhdcsdxjllje) {
        this.czhdCzhdcsdxjllje = czhdCzhdcsdxjllje;
    }

    public BigDecimal getBqCzhdCzhdcsdxjllje() {
        return bqCzhdCzhdcsdxjllje;
    }

    public void setBqCzhdCzhdcsdxjllje(BigDecimal bqCzhdCzhdcsdxjllje) {
        this.bqCzhdCzhdcsdxjllje = bqCzhdCzhdcsdxjllje;
    }

    public BigDecimal getXjjxjdjwjzje() {
        return xjjxjdjwjzje;
    }

    public void setXjjxjdjwjzje(BigDecimal xjjxjdjwjzje) {
        this.xjjxjdjwjzje = xjjxjdjwjzje;
    }

    public BigDecimal getBqXjjxjdjwjzje() {
        return bqXjjxjdjwjzje;
    }

    public void setBqXjjxjdjwjzje(BigDecimal bqXjjxjdjwjzje) {
        this.bqXjjxjdjwjzje = bqXjjxjdjwjzje;
    }

    public BigDecimal getQcxjjxjdjwye() {
        return qcxjjxjdjwye;
    }

    public void setQcxjjxjdjwye(BigDecimal qcxjjxjdjwye) {
        this.qcxjjxjdjwye = qcxjjxjdjwye;
    }

    public BigDecimal getBqQcxjjxjdjwye() {
        return bqQcxjjxjdjwye;
    }

    public void setBqQcxjjxjdjwye(BigDecimal bqQcxjjxjdjwye) {
        this.bqQcxjjxjdjwye = bqQcxjjxjdjwye;
    }

    public BigDecimal getBqQmxjjxjdjwye() {
        return bqQmxjjxjdjwye;
    }

    public void setBqQmxjjxjdjwye(BigDecimal bqQmxjjxjdjwye) {
        this.bqQmxjjxjdjwye = bqQmxjjxjdjwye;
    }

    public BigDecimal getQmxjjxjdjwye() {
        return qmxjjxjdjwye;
    }

    public void setQmxjjxjdjwye(BigDecimal qmxjjxjdjwye) {
        this.qmxjjxjdjwye = qmxjjxjdjwye;
    }

    public BigDecimal getBqCzhdZgsxssddxj() {
        return bqCzhdZgsxssddxj;
    }

    public void setBqCzhdZgsxssddxj(BigDecimal bqCzhdZgsxssddxj) {
        this.bqCzhdZgsxssddxj = bqCzhdZgsxssddxj;
    }

    public BigDecimal getCzhdZgsxssddxj() {
        return czhdZgsxssddxj;
    }

    public void setCzhdZgsxssddxj(BigDecimal czhdZgsxssddxj) {
        this.czhdZgsxssddxj = czhdZgsxssddxj;
    }

    public BigDecimal getBqZgsxjje() {
        return bqZgsxjje;
    }

    public void setBqZgsxjje(BigDecimal bqZgsxjje) {
        this.bqZgsxjje = bqZgsxjje;
    }

    public BigDecimal getBqQdzgsxjje() {
        return bqQdzgsxjje;
    }

    public void setBqQdzgsxjje(BigDecimal bqQdzgsxjje) {
        this.bqQdzgsxjje = bqQdzgsxjje;
    }

    public BigDecimal getBqJlr() {
        return bqJlr;
    }

    public void setBqJlr(BigDecimal bqJlr) {
        this.bqJlr = bqJlr;
    }

    public BigDecimal getBqJtdzcjz() {
        return bqJtdzcjz;
    }

    public void setBqJtdzcjz(BigDecimal bqJtdzcjz) {
        this.bqJtdzcjz = bqJtdzcjz;
    }

    public BigDecimal getBqGdzczj() {
        return bqGdzczj;
    }

    public void setBqGdzczj(BigDecimal bqGdzczj) {
        this.bqGdzczj = bqGdzczj;
    }

    public BigDecimal getBqWxzctx() {
        return bqWxzctx;
    }

    public void setBqWxzctx(BigDecimal bqWxzctx) {
        this.bqWxzctx = bqWxzctx;
    }

    public BigDecimal getBqCqdtfytx() {
        return bqCqdtfytx;
    }

    public void setBqCqdtfytx(BigDecimal bqCqdtfytx) {
        this.bqCqdtfytx = bqCqdtfytx;
    }

    public BigDecimal getBqDtfyjs() {
        return bqDtfyjs;
    }

    public void setBqDtfyjs(BigDecimal bqDtfyjs) {
        this.bqDtfyjs = bqDtfyjs;
    }

    public BigDecimal getBqYtfyzj() {
        return bqYtfyzj;
    }

    public void setBqYtfyzj(BigDecimal bqYtfyzj) {
        this.bqYtfyzj = bqYtfyzj;
    }

    public BigDecimal getBqCzgdzcwxzc() {
        return bqCzgdzcwxzc;
    }

    public void setBqCzgdzcwxzc(BigDecimal bqCzgdzcwxzc) {
        this.bqCzgdzcwxzc = bqCzgdzcwxzc;
    }

    public BigDecimal getBqGdzcbfss() {
        return bqGdzcbfss;
    }

    public void setBqGdzcbfss(BigDecimal bqGdzcbfss) {
        this.bqGdzcbfss = bqGdzcbfss;
    }

    public BigDecimal getBqCwfy() {
        return bqCwfy;
    }

    public void setBqCwfy(BigDecimal bqCwfy) {
        this.bqCwfy = bqCwfy;
    }

    public BigDecimal getBqTzss() {
        return bqTzss;
    }

    public void setBqTzss(BigDecimal bqTzss) {
        this.bqTzss = bqTzss;
    }

    public BigDecimal getBqDyskdx() {
        return bqDyskdx;
    }

    public void setBqDyskdx(BigDecimal bqDyskdx) {
        this.bqDyskdx = bqDyskdx;
    }

    public BigDecimal getBqChdjs() {
        return bqChdjs;
    }

    public void setBqChdjs(BigDecimal bqChdjs) {
        this.bqChdjs = bqChdjs;
    }

    public BigDecimal getBqJyxysxmdjs() {
        return bqJyxysxmdjs;
    }

    public void setBqJyxysxmdjs(BigDecimal bqJyxysxmdjs) {
        this.bqJyxysxmdjs = bqJyxysxmdjs;
    }

    public BigDecimal getBqJyxyfxmdzj() {
        return bqJyxyfxmdzj;
    }

    public void setBqJyxyfxmdzj(BigDecimal bqJyxyfxmdzj) {
        this.bqJyxyfxmdzj = bqJyxyfxmdzj;
    }

    public BigDecimal getBqQt() {
        return bqQt;
    }

    public void setBqQt(BigDecimal bqQt) {
        this.bqQt = bqQt;
    }

    public BigDecimal getBqJyhdcsdxjllje() {
        return bqJyhdcsdxjllje;
    }

    public void setBqJyhdcsdxjllje(BigDecimal bqJyhdcsdxjllje) {
        this.bqJyhdcsdxjllje = bqJyhdcsdxjllje;
    }

    public BigDecimal getBqBsjxjsz() {
        return bqBsjxjsz;
    }

    public void setBqBsjxjsz(BigDecimal bqBsjxjsz) {
        this.bqBsjxjsz = bqBsjxjsz;
    }

    public BigDecimal getBqZwzwzb() {
        return bqZwzwzb;
    }

    public void setBqZwzwzb(BigDecimal bqZwzwzb) {
        this.bqZwzwzb = bqZwzwzb;
    }

    public BigDecimal getBqYnndqdkzhgszj() {
        return bqYnndqdkzhgszj;
    }

    public void setBqYnndqdkzhgszj(BigDecimal bqYnndqdkzhgszj) {
        this.bqYnndqdkzhgszj = bqYnndqdkzhgszj;
    }

    public BigDecimal getBqRzzrgdzc() {
        return bqRzzrgdzc;
    }

    public void setBqRzzrgdzc(BigDecimal bqRzzrgdzc) {
        this.bqRzzrgdzc = bqRzzrgdzc;
    }

    public BigDecimal getBqXjjxjdjwjzjqk() {
        return bqXjjxjdjwjzjqk;
    }

    public void setBqXjjxjdjwjzjqk(BigDecimal bqXjjxjdjwjzjqk) {
        this.bqXjjxjdjwjzjqk = bqXjjxjdjwjzjqk;
    }

    public BigDecimal getBqXjdqmye() {
        return bqXjdqmye;
    }

    public void setBqXjdqmye(BigDecimal bqXjdqmye) {
        this.bqXjdqmye = bqXjdqmye;
    }

    public BigDecimal getBqJxjdqcye() {
        return bqJxjdqcye;
    }

    public void setBqJxjdqcye(BigDecimal bqJxjdqcye) {
        this.bqJxjdqcye = bqJxjdqcye;
    }

    public BigDecimal getBqJxjdjwdqmye() {
        return bqJxjdjwdqmye;
    }

    public void setBqJxjdjwdqmye(BigDecimal bqJxjdjwdqmye) {
        this.bqJxjdjwdqmye = bqJxjdjwdqmye;
    }

    public BigDecimal getBqJxjdjwdqcye() {
        return bqJxjdjwdqcye;
    }

    public void setBqJxjdjwdqcye(BigDecimal bqJxjdjwdqcye) {
        this.bqJxjdjwdqcye = bqJxjdjwdqcye;
    }

    public BigDecimal getBqXjjxjdjwjzje2() {
        return bqXjjxjdjwjzje2;
    }

    public void setBqXjjxjdjwjzje2(BigDecimal bqXjjxjdjwjzje2) {
        this.bqXjjxjdjwjzje2 = bqXjjxjdjwjzje2;
    }
}
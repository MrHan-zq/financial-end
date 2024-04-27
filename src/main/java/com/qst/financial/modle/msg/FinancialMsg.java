package com.qst.financial.modle.msg;

import com.qst.financial.core.TableName;
import com.qst.financial.modle.base.PoModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author GaryChen
 *资讯信息
 */
@TableName(name="T_MESSAGE_INFO")
public class FinancialMsg extends PoModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String arryQue ;
	private String title ;
	private String content ;
	private BigDecimal viewNum ;
	private String author ;
	private Date upDate ;
	private String upPerson ;
	private String upPersonTel ;
	private Date createTime ;
	private String createUser ;
	private Date updateTime ;
	private String updateUser ;
	private Integer clzz;
	public void setArryQue(String arryQue) {
		this.arryQue = arryQue;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public BigDecimal getViewNum() {
		return viewNum;
	}
	public void setViewNum(BigDecimal viewNum) {
		this.viewNum = viewNum;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getUpDate() {
		return upDate;
	}
	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}
	public String getUpPerson() {
		return upPerson;
	}
	public void setUpPerson(String upPerson) {
		this.upPerson = upPerson;
	}
	public String getUpPersonTel() {
		return upPersonTel;
	}
	public void setUpPersonTel(String upPersonTel) {
		this.upPersonTel = upPersonTel;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Integer getClzz() {
		return clzz;
	}
	public void setClzz(Integer clzz) {
		this.clzz = clzz;
	}
	public String getArryQue() {
		return arryQue;
	}
	@Override
	public String toString() {
		return "FinancialMsg [arryQue=" + arryQue + ", title=" + title + ", content=" + content + ", viewNum=" + viewNum
				+ ", author=" + author + ", upDate=" + upDate + ", upPerson=" + upPerson + ", upPersonTel="
				+ upPersonTel + ", createTime=" + createTime + ", createUser=" + createUser + ", updateTime="
				+ updateTime + ", updateUser=" + updateUser + ", clzz=" + clzz + "]";
	} 
	

}

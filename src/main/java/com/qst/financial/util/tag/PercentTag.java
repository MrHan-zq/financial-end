package com.qst.financial.util.tag;

import javax.servlet.jsp.tagext.TagSupport;


/** 
 * @author qst
 * 类说明 
 */
public class PercentTag extends TagSupport {
	private double totalMoney;
	private double investMoney;
	public double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public double getInvestMoney() {
		return investMoney;
	}
	public void setInvestMoney(double investMoney) {
		this.investMoney = investMoney;
	}
}
 
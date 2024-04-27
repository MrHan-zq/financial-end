package com.qst.financial.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 投资预测详情
 * @author yj
 *
 */
public class Details implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<String> titles;
	private List<BigDecimal[]> contents;
	public List<String> getTitles() {
		return titles;
	}
	public void setTitles(List<String> titles) {
		this.titles = titles;
	}
	public List<BigDecimal[]> getContents() {
		return contents;
	}
	public void setContents(List<BigDecimal[]> contents) {
		this.contents = contents;
	}
	
}

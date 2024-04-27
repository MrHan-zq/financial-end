package com.qst.financial.dto;

import java.io.Serializable;
import java.util.List;

public class ResultByMdyRuleDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private ResultDto title ;
	private List<ResultDto> contents ;
	public ResultDto getTitle() {
		return title;
	}
	public void setTitle(ResultDto title) {
		this.title = title;
	}
	public List<ResultDto> getContents() {
		return contents;
	}
	public void setContents(List<ResultDto> contents) {
		this.contents = contents;
	}
	
	
}

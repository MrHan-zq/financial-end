package com.qst.financial.service.subject;

import com.qst.financial.modle.subject.CodeLibr;
import com.qst.financial.service.base.BaseService;
import com.qst.financial.util.tag.InnerPage;

import java.util.List;
import java.util.Map;

public interface CodeLibrService extends BaseService<CodeLibr,Integer>{/*
	
	public List<CodeLibr> listPage(WherePrams where, String limit);
	
	public int add(CodeLibr codeLibr);
	
	public int update(CodeLibr codeLibr);
	
	public int del(WherePrams where);
	
	public long count(WherePrams where);
	
*/
	public void deleteCodeLibr(String codeLibrId) throws Exception;
	
	public void deleteCodeMapper(String codeNo) throws Exception;
	
	public List<CodeLibr> selectCodeMapperList(InnerPage innerPage);           //分頁
	public void getReportDivine(Map<String, Object> map);
	
}

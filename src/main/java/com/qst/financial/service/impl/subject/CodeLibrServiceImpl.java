package com.qst.financial.service.impl.subject;

import com.qst.financial.dao.mapper.subject.CodeLibrMapper;
import com.qst.financial.dao.mapper.subject.TCodeMapperBeanMapper;
import com.qst.financial.modle.subject.CodeLibr;
import com.qst.financial.service.impl.base.BaseServiceImpl;
import com.qst.financial.service.subject.CodeLibrService;
import com.qst.financial.util.tag.InnerPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CodeLibrServiceImpl extends BaseServiceImpl<CodeLibr, Integer> implements CodeLibrService {
	@Autowired
	private CodeLibrMapper codeLibrMapper;
	
	@Autowired
	private TCodeMapperBeanMapper tCodeMapperBeanMapper;
	/*
	@Override
	public List<CodeLibr> listPage(WherePrams where, String limit) {
		return codeLibrMapper.listPage(CodeLibr.class, where, limit);
	}

	@Override
	public int add(CodeLibr codeLibr) {
		return codeLibrMapper.add(codeLibr);
	}

	@Override
	public int update(CodeLibr codeLibr) {
		return codeLibrMapper.update(codeLibr);
	}

	@Override
	public int del(WherePrams where) {
		return codeLibrMapper.del(CodeLibr.class, where);
	}
	
	@Override
	public long count(WherePrams where) {
		return codeLibrMapper.count(CodeLibr.class, where);
	}


*/
	
	@Override
	public void deleteCodeLibr(String codeLibrId) throws Exception {
		String[] codeLibrIdArr = codeLibrId.split(",");
		List<String> codeLibrIdList = new ArrayList<String>();
		for (String string : codeLibrIdArr) {
			codeLibrIdList.add(string);
		}
		codeLibrMapper.deleteByPrimaryKeyBatch(codeLibrIdList);
	}
	
	// 分页查询
	@Override
	public List<CodeLibr> selectCodeMapperList(InnerPage innerPage) {
		return codeLibrMapper.selectCodeMapperList(innerPage);
	}

	@Override
	public void deleteCodeMapper(String codeNo) throws Exception {
		String[] codeLibrIdArr = codeNo.split(",");
		List<String> codeLibrIdList = new ArrayList<String>();
		for (String string : codeLibrIdArr) {
			codeLibrIdList.add(string);
		}
		tCodeMapperBeanMapper.deleteByPrimaryKeyBatch(codeLibrIdList);
	}
	@Override
	public void getReportDivine(Map<String,Object> map){
		codeLibrMapper.getReportDivine(map);
	}
}




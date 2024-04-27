package com.qst.financial.service.impl.subject;


import com.qst.financial.dao.mapper.subject.TKmyeMapper;
import com.qst.financial.modle.subject.TKmye;
import com.qst.financial.service.impl.base.BaseServiceImpl;
import com.qst.financial.service.subject.TKmyeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TKmyeServiceImpl extends BaseServiceImpl<TKmye, Integer> implements TKmyeService {

	@Autowired
	private TKmyeMapper tKmyeMapper;
	
	@Override
	public int deleteByYearMonth(String yearMonth,Long accountPortType,Long orgId) {
		return tKmyeMapper.deleteByYearMonth(yearMonth,accountPortType,orgId);
	}
}




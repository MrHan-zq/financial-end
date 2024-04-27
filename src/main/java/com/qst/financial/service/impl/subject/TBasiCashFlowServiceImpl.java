package com.qst.financial.service.impl.subject;


import com.qst.financial.dao.mapper.subject.TBasiCashFlowMapper;
import com.qst.financial.modle.subject.TBasiCashFlow;
import com.qst.financial.service.impl.base.BaseServiceImpl;
import com.qst.financial.service.subject.TBasiCashFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TBasiCashFlowServiceImpl extends BaseServiceImpl<TBasiCashFlow, Integer> implements TBasiCashFlowService {

	@Autowired
	private TBasiCashFlowMapper tBasiCashFlowMapper;
	
	@Override
	public int deleteByYearMonth(String yearMonth,Long accountPortType,Long orgId) {
		return tBasiCashFlowMapper.deleteByYearMonth(yearMonth,accountPortType,orgId);
	}

}




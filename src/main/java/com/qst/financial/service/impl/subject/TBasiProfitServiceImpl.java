package com.qst.financial.service.impl.subject;


import com.qst.financial.dao.mapper.subject.TBasiProfitMapper;
import com.qst.financial.modle.subject.TBasiProfit;
import com.qst.financial.service.impl.base.BaseServiceImpl;
import com.qst.financial.service.subject.TBasiProfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TBasiProfitServiceImpl extends BaseServiceImpl<TBasiProfit, Integer> implements TBasiProfitService {

	@Autowired
	private TBasiProfitMapper tBasiProfitMapper;
	
	@Override
	public int deleteByYearMonth(String yearMonth,Long accountPortType,Long orgId) {
		return tBasiProfitMapper.deleteByYearMonth(yearMonth,accountPortType,orgId);
	}

}




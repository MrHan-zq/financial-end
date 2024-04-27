package com.qst.financial.service.subject;

import com.qst.financial.modle.subject.TBasiCashFlow;
import com.qst.financial.service.base.BaseService;


public interface TBasiCashFlowService extends BaseService<TBasiCashFlow, Integer>{

	/**
	 * 删除本月数据
	 */
	public int deleteByYearMonth(String yearMonth, Long accountPortType, Long orgId);
    
}

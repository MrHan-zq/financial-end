package com.qst.financial.service.subject;

import com.qst.financial.modle.subject.TReportProperty;
import com.qst.financial.service.base.BaseService;

public interface TReportPropertyService  extends BaseService<TReportProperty, Integer>{
	
	public void deletetTReportProperty(String msgId) throws Exception;
}

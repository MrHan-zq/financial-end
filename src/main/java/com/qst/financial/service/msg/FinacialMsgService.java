package com.qst.financial.service.msg;

import com.qst.financial.modle.msg.FinancialMsg;
import com.qst.financial.service.base.BaseService;

public interface FinacialMsgService extends BaseService<FinancialMsg, Integer>{
	
	public void deletetMsg(String msgId) throws Exception;
}

package com.qst.financial.service.impl.msg;

import com.qst.financial.dao.mapper.subject.FinancialMsgMapper;
import com.qst.financial.modle.msg.FinancialMsg;
import com.qst.financial.service.impl.base.BaseServiceImpl;
import com.qst.financial.service.msg.FinacialMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FinancialMsgServiceImpl extends BaseServiceImpl<FinancialMsg, Integer> implements FinacialMsgService {

	@Autowired
	private FinancialMsgMapper msgMapper;
	@Override
	public void deletetMsg(String msgId) throws Exception {
		String[] tModeIdArr = msgId.split(",");
		List<String> tModeIdList = new ArrayList<String>();
		for (String string : tModeIdArr) {
			tModeIdList.add(string);
		}
		msgMapper.deleteByPrimaryKeyBatch(tModeIdList);
	}

}

package com.qst.financial.service.impl.subject;

import com.qst.financial.dao.mapper.subject.TReportPropertyMapper;
import com.qst.financial.modle.subject.TReportProperty;
import com.qst.financial.service.impl.base.BaseServiceImpl;
import com.qst.financial.service.subject.TReportPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TReportPropertyServiceImpl extends BaseServiceImpl<TReportProperty, Integer> implements TReportPropertyService {

	@Autowired
	private TReportPropertyMapper msgMapper;
	@Override
	public void deletetTReportProperty(String msgId) throws Exception {
		String[] tModeIdArr = msgId.split(",");
		List<String> tModeIdList = new ArrayList<String>();
		for (String string : tModeIdArr) {
			tModeIdList.add(string);
		}
		msgMapper.deleteByPrimaryKeyBatch(tModeIdList);
	}

}

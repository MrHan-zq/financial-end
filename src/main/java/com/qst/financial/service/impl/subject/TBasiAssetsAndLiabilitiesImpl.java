package com.qst.financial.service.impl.subject;


import com.qst.financial.dao.mapper.subject.TBasiAssetsAndLiabilitiesMapper;
import com.qst.financial.modle.subject.TBasiAssetsAndLiabilities;
import com.qst.financial.service.impl.base.BaseServiceImpl;
import com.qst.financial.service.subject.TBasiAssetsAndLiabilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TBasiAssetsAndLiabilitiesImpl extends BaseServiceImpl<TBasiAssetsAndLiabilities, Integer> implements TBasiAssetsAndLiabilitiesService {

	@Autowired
	private TBasiAssetsAndLiabilitiesMapper tBasiAssetsAndLiabilitiesMapper;
	
	@Override
	public int deleteByYearMonth(String yearMonth,Long accountPortType,Long orgId) {
		return tBasiAssetsAndLiabilitiesMapper.deleteByYearMonth(yearMonth,accountPortType,orgId);
	}

}




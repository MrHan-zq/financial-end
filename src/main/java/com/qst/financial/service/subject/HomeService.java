package com.qst.financial.service.subject;

import com.qst.financial.dto.Result;

import java.util.List;
import java.util.Map;

public interface HomeService {

	public Map<String, Object> getHomes(String startTime, String endTime, String orgId) throws Exception;

	public List<Result> getDetail(String clzz, String startTime, String endTime, String orgId);

}

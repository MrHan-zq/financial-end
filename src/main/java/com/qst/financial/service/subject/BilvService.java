package com.qst.financial.service.subject;

import com.qst.financial.dto.ResultDto;

import java.text.ParseException;
import java.util.Map;

public interface BilvService {

	public Map<String, Object> getBilv(String startTime, String endTime, String orgId) throws ParseException;

	public ResultDto getBilvResult(String startTime, String endTime, String orgId, String useArea) throws ParseException;

}

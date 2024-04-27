package com.qst.financial.service.subject;

import com.qst.financial.modle.subject.TMode;
import com.qst.financial.service.base.BaseService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public interface TModeService extends BaseService<TMode, Integer>{
	public void deletetMode(String tModeId) throws Exception;
	
	public List<String> getContent(String startTime, String endTime, String orgId, String reportType, String modeArea)throws Exception ;

	/**
	 * 加入计算的预警变量作为条件，提升查询效率
	 * @param startTime
	 * @param endTime
	 * @param orgId
	 * @param reportType
	 * @param modeArea
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<String> getContent(String startTime, String endTime, String orgId, String reportType, String modeArea, Map<String, BigDecimal> map)throws Exception ;

	public Map<String,BigDecimal> getDbMsg(String startTime, String endTime, String orgId)throws Exception ;

	public Map<String, String> getDbBlvMsg(String startTime, String endTime, String orgId) throws Exception;

	public List<String> getContent2(String startTime, String endTime, String orgId, String reportType, String modeArea, String Key)
			throws Exception;

}

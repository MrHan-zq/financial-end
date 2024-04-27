package com.qst.financial.service.subject;

import com.qst.financial.dto.ResultDto;
import com.qst.financial.modle.subject.TAgeAnalysis;
import com.qst.financial.service.base.BaseService;

import java.util.List;
import java.util.Map;


public interface TAgeAnalysisService extends BaseService<TAgeAnalysis, Integer>{
	/**
	 * 批量插入
	public int insertTKmyeList(List <TKmye> record) ;
    */
	 public List<ResultDto> selectSumTop(Map<String, Object> map);
	    public List<ResultDto> selectSum(Map<String, Object> map);
	    public List<ResultDto> allSum(Map<String, Object> map);
	    public List<TAgeAnalysis> selectSumAll(Map<String, Object> map);
	    public List<TAgeAnalysis> selectSumAllByQJ(Map<String, Object> map);
	    public List<ResultDto> top5(Map<String, Object> map);
	    public List<ResultDto> allSumQJ(Map<String, Object> map);
	    public Map<String,Object> selectSumAll2(Map<String, Object> map);
		/**
		 * 删除本月数据
		 */
		public int deleteByYearMonth(String yearMonth, Long accountPortType, Integer type, Long orgId);
		public List<TAgeAnalysis> selectSumAllByType(Map<String, Object> map);
}

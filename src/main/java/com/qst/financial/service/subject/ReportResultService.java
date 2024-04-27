package com.qst.financial.service.subject;

import com.qst.financial.dto.ResultDto;
import com.qst.financial.modle.subject.ReportResult;
import com.qst.financial.service.base.BaseService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface ReportResultService extends BaseService<ReportResult,String>{
	public BigDecimal selectTBSum(Map<String, Object> map);
	public BigDecimal selectValueSum(Map<String, Object> map);
	public ResultDto selectValueSumResultDto(Map<String, Object> map);
	public List<ResultDto> selectValueMonthResultDto(Map<String, Object> map);
	public ResultDto selectAllValueSum(Map<String, Object> map);
	public List<ResultDto> selectValueSumByMoth(Map<String, Object> map);
	public List<ResultDto> selectValueSumDetail(Map<String, Object> map);
	public ResultDto selectValueSumDetailAllTotal(Map<String, Object> map);
	public List<ResultDto> selectValueSumDetailAll(Map<String, Object> map);
	public List<ResultDto> selectResultKmTop5Sum(Map<String, Object> map);
	/**
	 * 获取利润表首页信息
	 * @param mapmccc
	 * @return
	 */
	//public List<ResultDto> selectProfitMainMsg(String startTime , String endTime , String orgId , String reportType)  throws Exception;

	/**
	 * 获取明细信息
	 * @param mapmccc
	 * @return
	 */
	//public List<ResultDto> selectDetailMsg(String startTime , String endTime , String orgId , String reportType) throws Exception;

	/**
	 * 通过codeNo获取从开始时间道结束时间内每个月的明细信息
	 * @param startTime
	 * @param endTime
	 * @param orgId
	 * @param codeNos
	 * @param reportType
	 * @param getDataType   1:查本期数据;2:查本期及其环比数据;3:查本期及其同比数据;4:查本期及其同比和环比数据
	 * @return
	 * @throws Exception
	 */
	//public List<ResultDto> selectReportDataByCodeNo(String startTime , String endTime , String orgId ,List<String> codeNos, String reportType , int getDataType) throws Exception;

	/**
	 * 获取预警报表参数
	 * @return
	 */
	public Map<String,BigDecimal> getModeParams(String startTime, String endTime, String orgId, String reportType)throws Exception ;
	public List<ResultDto> selectValueYearResultDto(Map<String, Object> map);
	public ResultDto selectExpectValueSumDetailAllTotal(Map<String, Object> map);
	public List<ResultDto> selectExpectValueSumDetailAll(Map<String, Object> map);

	public List<ResultDto> getQueReport(String startTime, String endTime, String orgId)throws Exception;
	public Map<String, BigDecimal> getDbMsgMap(String startTime, String endTime, String orgId) throws Exception;
	public List<ResultDto> selectValueSumDetailAllts(Map<String, Object> map);
	public List<ResultDto> selectValueSumDetailAllBlv(Map<String, Object> map);
	public List<ResultDto> selectResultKm(Map<String, Object> map);
	public List<ResultDto> selectResultKmTo5(Map<String, Object> map);
	public List<ResultDto> selectResultKm2(Map<String, Object> map);
	public Map<String, BigDecimal> getModeParams2(String startTime, String endTime, String orgId, String reportType, String modeArea)
			throws Exception;
	public List<ResultDto> selectResultKmByYear(Map<String, Object> map);
	public List<ResultDto> selectResultSale(Map<String, Object> map);
	public List<ResultDto> selectResultSale2(Map<String, Object> map);
	public List<ResultDto> selectResultSaleByYear(Map<String, Object> map);
	public Map<String, BigDecimal> getOthen(Map<String, Object> map, String code, Map<String, BigDecimal> rmap, String type);
	public Map<String, BigDecimal> getTeshu(String startTime, String endTime, String orgId, Map<String, BigDecimal> rmap)
			throws ParseException;
	public List<ResultDto> selectRate(Map<String, Object> map);
	public List<ResultDto> selectRate2(Map<String, Object> map);
	public List<ResultDto> selectValueSumByName(Map<String, Object> map);
	public Map<String, BigDecimal> getTeshu2(String startTime, String endTime, String orgId, Map<String, BigDecimal> rmap)
			throws ParseException;
	public Map<String, BigDecimal> getTeshu3(String startTime, String endTime, String orgId, Map<String, BigDecimal> rmap)
			throws ParseException;
}

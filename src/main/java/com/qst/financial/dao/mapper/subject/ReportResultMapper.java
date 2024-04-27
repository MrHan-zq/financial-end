package com.qst.financial.dao.mapper.subject;

import com.qst.financial.dao.mapper.base.BaseMapper;
import com.qst.financial.dto.ResultDto;
import com.qst.financial.modle.subject.ReportResult;
import com.qst.financial.modle.subject.TKmye;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface ReportResultMapper extends BaseMapper<ReportResult,String>{
	public BigDecimal selectTBSum(Map<String, Object> map);
	public BigDecimal selectValueSum(Map<String, Object> map);
	public ResultDto selectAllValueSum(Map<String, Object> map);
	public List<ResultDto> selectValueSumByMoth(Map<String, Object> map);
	public List<ResultDto> selectValueSumByMoth2(Map<String, Object> map);
	public List<ResultDto> selectValueSumByYear(Map<String, Object> map);
	public List<ResultDto> selectValueSumDetail(Map<String, Object> map);
	public List<ResultDto> selectExpectValueSumDetail(Map<String, Object> map);
	public List<ResultDto> selectProfitMainMsg(Map<String, Object> map) ;
	public List<ResultDto> getProfitMainTB(Map<String, Object> map) ;

	public List<ResultDto> getReportDetailMsg(Map<String, Object> map) ;
	public List<TKmye> getReportDetailBySubCodes(Map<String, Object> map) ;
	public List<ResultDto> getZJYNCountMsg(Map<String, Object> map) ;
	public BigDecimal selectOne(Map<String, Object> map) ;

	public List<ResultDto> getReportDetailByCodeNoList(Map<String, Object> map) ;
	public BigDecimal selectExpectValueSum(Map<String, Object> map);
	public List<ResultDto> getThreeReportDetailMsg(Map<String, Object> map) ;
	public List<ResultDto> selectResultKm(Map<String, Object> map);
	public List<ResultDto> selectResultSale(Map<String, Object> map);
	public List<ResultDto> selectResultKmByYear(Map<String, Object> map);
	public List<ResultDto> selectResultSaleByYear(Map<String, Object> map);
	public List<ResultDto> selectResultKmTo5(Map<String, Object> map);
	public List<ResultDto> selectResultKmTop5Sum(Map<String, Object> map);
	public ResultDto selectResultSaleSum(Map<String, Object> map);
	public List<ResultDto> selectStockAge(Map<String, Object> map);
	public List<ResultDto> selectRate(Map<String, Object> map);
	public List<ResultDto> selectValueSumByName(Map<String, Object> map);
	
}

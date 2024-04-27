package com.qst.financial.dao.mapper.subject;

import com.qst.financial.dao.mapper.base.BaseMapper;
import com.qst.financial.dto.ResultDto;
import com.qst.financial.modle.subject.ReportResult;
import com.qst.financial.modle.subject.TAgeAnalysis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 账龄相关
 * @author yj
 *
 */
@Mapper
public interface TAgeAnalysisMapper extends BaseMapper<ReportResult,String>{
    int deleteByPrimaryKey(String id);

    int insert(TAgeAnalysis record);

    int insertSelective(TAgeAnalysis record);

    TAgeAnalysis selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TAgeAnalysis record);

    int updateByPrimaryKey(TAgeAnalysis record);
    public List<ResultDto> selectSumTop(Map<String, Object> map);
    public List<ResultDto> selectSum(Map<String, Object> map);
    public List<TAgeAnalysis> selectSumAll(Map<String, Object> map);
    public List<TAgeAnalysis> selectSumAllByQJ(Map<String, Object> map);
    public List<TAgeAnalysis> selectSumAllByType(Map<String, Object> map);
    public List<TAgeAnalysis> selectSumTops(Map<String, Object> map);
    public List<TAgeAnalysis> selectSumTops2(Map<String, Object> map);
    public Integer selectCount(Map<String, Object> map);
    public int deleteByYearMonth(@Param("yearMonth") String yearMonth, @Param("accountPortType") Long accountPortType, @Param("type") Integer type, @Param("orgId") Long orgId);
}
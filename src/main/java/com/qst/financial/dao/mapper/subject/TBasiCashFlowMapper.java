package com.qst.financial.dao.mapper.subject;

import com.qst.financial.dao.mapper.base.BaseMapper;
import com.qst.financial.modle.subject.TBasiCashFlow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TBasiCashFlowMapper extends BaseMapper<TBasiCashFlow, Integer>{
	public int deleteByPrimaryKey(String id);

	public int insert(TBasiCashFlow record);

	public int insertSelective(TBasiCashFlow record);

	public TBasiCashFlow selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(TBasiCashFlow record);

	public int updateByPrimaryKey(TBasiCashFlow record);
	
	public int deleteByYearMonth(@Param("yearMonth") String yearMonth, @Param("accountPortType") Long accountPortType, @Param("orgId") Long orgId);

    public  int updateFormatFlagByPrimaryKey(
            @Param("id") String id,
            @Param("formatFlag") String formatFlag
    );
}
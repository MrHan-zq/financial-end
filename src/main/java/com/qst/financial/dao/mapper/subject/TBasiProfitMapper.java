package com.qst.financial.dao.mapper.subject;

import com.qst.financial.dao.mapper.base.BaseMapper;
import com.qst.financial.modle.subject.TBasiProfit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TBasiProfitMapper extends BaseMapper<TBasiProfit, Integer>{
	public int deleteByPrimaryKey(String id);

	public int insert(TBasiProfit record);

	public int insertSelective(TBasiProfit record);

	public TBasiProfit selectByPrimaryKey(String id);

	public  int updateByPrimaryKeySelective(TBasiProfit record);
	
	public  int updateFormatFlagByPrimaryKey(
            @Param("id") String id,
            @Param("formatFlag") String formatFlag
    );

	public int updateByPrimaryKey(TBasiProfit record);

	public int deleteByYearMonth(@Param("yearMonth") String yearMonth, @Param("accountPortType") Long accountPortType, @Param("orgId") Long orgId);
}
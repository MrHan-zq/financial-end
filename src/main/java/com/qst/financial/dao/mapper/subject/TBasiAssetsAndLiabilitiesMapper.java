package com.qst.financial.dao.mapper.subject;

import com.qst.financial.dao.mapper.base.BaseMapper;
import com.qst.financial.modle.subject.TBasiAssetsAndLiabilities;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TBasiAssetsAndLiabilitiesMapper extends BaseMapper<TBasiAssetsAndLiabilities, Integer>{
	public int deleteByPrimaryKey(String id);

	public int insert(TBasiAssetsAndLiabilities record);

	public int insertSelective(TBasiAssetsAndLiabilities record);

	public TBasiAssetsAndLiabilities selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(TBasiAssetsAndLiabilities record);

	public int updateByPrimaryKey(TBasiAssetsAndLiabilities record);
	
	public  int updateFormatFlagByPrimaryKey(
            @Param("id") String id,
            @Param("formatFlag") String formatFlag
    );

	public int deleteByYearMonth(@Param("yearMonth") String yearMonth, @Param("accountPortType") Long accountPortType, @Param("orgId") Long orgId);
}
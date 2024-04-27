package com.qst.financial.dao.mapper.subject;

import com.qst.financial.modle.subject.TKmye;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TKmyeMapper {
	public int deleteByPrimaryKey(String id);

    public int insert(TKmye record);

    public  int insertSelective(TKmye record);

    public TKmye selectByPrimaryKey(String id);

    public int updateByPrimaryKeySelective(TKmye record);

    public int updateByPrimaryKey(TKmye record);
    
    public  int updateFormatFlagByPrimaryKey(
            @Param("id") String id,
            @Param("formatFlag") String formatFlag
    );

	public int deleteByYearMonth(@Param("yearMonth") String yearMonth, @Param("accountPortType") Long accountPortType, @Param("orgId") Long orgId);

	public int insertTKmyeList(List<TKmye> record);
}
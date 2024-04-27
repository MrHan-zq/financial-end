package com.qst.financial.dao.mapper.subject;

import com.qst.financial.modle.subject.TReportProperty;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TReportPropertyMapper {
	public int deleteByPrimaryKey(Long id);

	public int insert(TReportProperty record);

	public int insertSelective(TReportProperty record);

	public TReportProperty selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(TReportProperty record);

	public int updateByPrimaryKey(TReportProperty record);
	
    public int deleteByPrimaryKeyBatch(List<String> id);
}
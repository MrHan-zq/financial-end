package com.qst.financial.dao.mapper.subject;

import com.qst.financial.dao.mapper.base.BaseMapper;
import com.qst.financial.modle.subject.TMode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TModeMapper  extends BaseMapper<TMode, Integer>{
	public int deleteByPrimaryKey(Long id);

	public int insert(TMode record);

	public int insertSelective(TMode record);

	public TMode selectByPrimaryKey(Long id);

	public  int updateByPrimaryKeySelective(TMode record);

	public int updateByPrimaryKey(TMode record);
	
    public  int deleteByPrimaryKeyBatch(List<String> id);

	public List<TMode> getTModeInfo(Map<String, Object> params) ;
}
package com.qst.financial.dao.mapper.base;

import com.qst.financial.modle.base.SysDictionaryDetailModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysDictionaryDetailMapper {
	
	public int deleteByPrimaryKey(Long id);

	public int insert(SysDictionaryDetailModel record);

	public int insertSelective(SysDictionaryDetailModel record);

	public SysDictionaryDetailModel selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(SysDictionaryDetailModel record);

	public int updateByPrimaryKey(SysDictionaryDetailModel record);
    
	public int insertBatchDictionaryDetail(List<SysDictionaryDetailModel> List);
    
	public  List<SysDictionaryDetailModel> selectByDictionaryId(Long dictionaryId);
	public  List<SysDictionaryDetailModel> selectByDic(Map<String, String> map);
	public int deleteByDictionaryId(Long dictionaryId);
    
	public int deleteByDictionaryIdBatch(List<String> dictionaryIds);
}
package com.qst.financial.dao.mapper.base;

import com.qst.financial.modle.base.SysDictionaryModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysDictionaryMapper {
	
    public int deleteByPrimaryKey(Long id);

    public int insert(SysDictionaryModel record);

    public int insertSelective(SysDictionaryModel record);

    public SysDictionaryModel selectByPrimaryKey(Long id);

    public  int updateByPrimaryKeySelective(SysDictionaryModel record);

    public int updateByPrimaryKey(SysDictionaryModel record);
    
    public List<Map<String, Object>> selectDictionaryList(Map<String, String> map);
    
    public int selectDictionaryCount(Map<String, String> map);
    
    public  int deleteByPrimaryKeyBatch(List<String> id);
    
    public SysDictionaryModel selectByCnNameOrEnName(SysDictionaryModel record);
    
    public SysDictionaryModel selectByCnNameOrEnNameAndNotIncludedSelf(SysDictionaryModel record);
    
    //查询id
    public  long selectByCnName(String CnName);
}
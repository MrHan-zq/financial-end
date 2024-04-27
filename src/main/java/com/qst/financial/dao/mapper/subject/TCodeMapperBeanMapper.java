package com.qst.financial.dao.mapper.subject;

import com.qst.financial.modle.subject.TCodeMapperBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TCodeMapperBeanMapper {
    int insert(TCodeMapperBean record);

    int insertSelective(TCodeMapperBean record);
    
	TCodeMapperBean selectByCodeNo(String codeNo);
	
    int update(TCodeMapperBean record);
    
	int delete(String codeNo);
	
    int deleteByPrimaryKeyBatch(List<String> id);
}
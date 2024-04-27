package com.qst.financial.dao.mapper.subject;

import com.qst.financial.modle.subject.CodeLibr;
import com.qst.financial.util.tag.InnerPage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CodeLibrMapper {
	
	public int deleteByPrimaryKey(Integer id);

	public int insert(CodeLibr record);

    public int insertSelective(CodeLibr record);

    public CodeLibr selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(CodeLibr record);

    public int updateByPrimaryKey(CodeLibr record);
    
    public  int deleteByPrimaryKeyBatch(List<String> id);
    
    public List<CodeLibr> selectCodeMapperList(InnerPage innerPage);                 // 分页查询
	
	public List<CodeLibr> selectCodeNoList();
	public void getReportDivine(Map<String, Object> map);
	
}

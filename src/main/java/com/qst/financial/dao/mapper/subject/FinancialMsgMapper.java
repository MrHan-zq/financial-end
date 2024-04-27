package com.qst.financial.dao.mapper.subject;

import com.qst.financial.modle.msg.FinancialMsg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FinancialMsgMapper {
	public int deleteByPrimaryKey(Long id);

    public int insert(FinancialMsg record);

    public int insertSelective(FinancialMsg record);

    public FinancialMsg selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(FinancialMsg record);

    public int updateByPrimaryKeyWithBLOBs(FinancialMsg record);

    public int updateByPrimaryKey(FinancialMsg record);
    
    public int deleteByPrimaryKeyBatch(List<String> id);
}
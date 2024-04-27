package com.qst.financial.dao.mapper.base;

import com.qst.financial.modle.base.SysRoleActionModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleActionMapper {
	
	public int deleteByPrimaryKey(Long id);

	public int insert(SysRoleActionModel record);

	public int insertSelective(SysRoleActionModel record);

	public SysRoleActionModel selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(SysRoleActionModel record);

	public int updateByPrimaryKey(SysRoleActionModel record);
    
	public int insertBatchRoleAction(List<SysRoleActionModel> list);
    
	public List<SysRoleActionModel> selectByRoleId(Long roleId);
    
	public int deleteByRoleId(Long roleId);
    
	public int deleteByRoleIdBatch(List<String> roleId);
}
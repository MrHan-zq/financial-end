package com.qst.financial.dao.mapper.base;

import com.qst.financial.modle.base.SysUserRoleModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserRoleMapper {

	public int deleteByPrimaryKey(Long id);

	public int insert(SysUserRoleModel record);

	public int insertSelective(SysUserRoleModel record);

	public SysUserRoleModel selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(SysUserRoleModel record);

	public int updateByPrimaryKey(SysUserRoleModel record);
	
	public SysUserRoleModel selectByUserId(Long userId);
	
	public SysUserRoleModel selectByUserIdAndRoleId(SysUserRoleModel record);
	
	public int deleteByUserId(Long userId);
	
	public int deleteByUserIdBatch(List<String> userId);
	
}
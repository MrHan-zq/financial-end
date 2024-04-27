package com.qst.financial.dao.mapper.base;

import com.qst.financial.modle.base.SysRoleModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysRoleMapper {
	
	public int deleteByPrimaryKey(Long id);

	public int insert(SysRoleModel record);

	public int insertSelective(SysRoleModel record);

	public SysRoleModel selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(SysRoleModel record);

	public int updateByPrimaryKey(SysRoleModel record);
    
	public SysRoleModel selectByName(String name);
	
	public List<Map<String, Object>> selectRoleList(Map<String, String> map);
	
	public int selectRoleCount(Map<String, String> map);
	
	public SysRoleModel selectByNameAndNotIncludedSelf(SysRoleModel record);
	
	public int deleteByPrimaryKeyBatch(List<String> roleId);
	
	public List<SysRoleModel> selectRoleAll();
}
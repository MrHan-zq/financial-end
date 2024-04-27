package com.qst.financial.dao.mapper.base;

import com.qst.financial.modle.base.SysResourcesModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysResourcesMapper {
	
	public int deleteByPrimaryKey(Integer id);

	public int insert(SysResourcesModel record);

	public int insertSelective(SysResourcesModel record);

	public SysResourcesModel selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(SysResourcesModel record);

	public int updateByPrimaryKey(SysResourcesModel record);

	public List<SysResourcesModel> selectUserMenu(String userId);
	
	public List<SysResourcesModel> selectRootUserMenu();
	
	public List<SysResourcesModel> selectUserMenuButton(Map<String, String> map);

	public List<SysResourcesModel> selectRootUserMenuButton(Map<String, String> map);
	
	public List<SysResourcesModel> selectMenuAll();
	
	public List<SysResourcesModel> selectByParentId(Integer parentId);
	
	public int deleteByPrimaryKeyBatch(List<String> id);
	
	public int deleteByParentId(Integer parentId);
}
package com.qst.financial.service.base;

import com.qst.financial.modle.base.SysResourcesModel;
import com.qst.financial.util.common.TreeObject;

import java.util.List;
import java.util.Map;


public interface SysResourcesService {

	public List<TreeObject> getUserMenuActionAuthority(String userId) throws Exception;
	
	public List<TreeObject> getRootUserMenuActionAuthority() throws Exception;
	
	public List<TreeObject> getMenuAllActionAuthority() throws Exception;
	
	public void deleteMenuAndmenuSonAndButton(String id) throws Exception;
	public List<SysResourcesModel> selectUserMenuButton(Map<String, String> map);
	public List<SysResourcesModel> selectRootUserMenuButton(Map<String, String> map);

	public SysResourcesModel selectByPrimaryKey(Integer id);
}

package com.qst.financial.service.base;

import com.qst.financial.modle.base.SysRoleModel;
import com.qst.financial.util.common.TreeObject;

import java.util.List;

public interface SysRoleManageService {

	public void addRoleAndRoleAction(SysRoleModel record, String[] resourcesId) throws Exception;

	public void updateRoleAndRoleAction(SysRoleModel record, String[] resourcesId) throws Exception;
	
	public List<TreeObject> getRoleHaveResources(String roleId);
	
	public void deleteRoleAndRoleAction(String roleId) throws Exception;
	
}

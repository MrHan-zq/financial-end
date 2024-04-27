package com.qst.financial.service.base;

import com.qst.financial.modle.base.SysUserModel;
import com.qst.financial.util.common.TreeObject;

import java.util.List;

public interface SysUserService {
	
	public void addAdministratorAndUserRole(SysUserModel userModel, String roleId) throws Exception;
	
	public void updateAdministratorAndUserRole(SysUserModel userModel, String roleId) throws Exception;
	
	public void deleteAdministratorAndUserRole(String userIds);
	
	public void addAdministratorUserAction(String userId, String[] resourcesId) throws Exception;
	
	/**
	 * 获得角色与用户拥有的权限
	 *
	 */
	public List<TreeObject> getRoleAndUserActionHaveResources(String userId);

	public List<SysUserModel> selectSysUserByList(SysUserModel sysUser);
	
}

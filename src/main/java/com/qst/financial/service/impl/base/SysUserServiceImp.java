package com.qst.financial.service.impl.base;

import com.qst.financial.dao.mapper.base.*;
import com.qst.financial.modle.base.*;
import com.qst.financial.service.base.SysUserService;
import com.qst.financial.util.common.Common;
import com.qst.financial.util.common.Tree2Util;
import com.qst.financial.util.common.TreeObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("sysUserService")
public class SysUserServiceImp implements SysUserService {
	
	@Autowired
    SysUserMapper sysUserMapper;
	
	@Autowired
	SysUserRoleMapper sysUserRoleMapper;
	
	@Autowired
	SysUserActionMapper sysUserActionMapper;
	
	@Autowired
    SysResourcesMapper sysResourcesMapper;
	
	@Autowired
    SysRoleActionMapper sysRoleActionMapper;
	
	//@SystemServiceLog(description = "新增后台用户与分配的角色")
	@Override
	public void addAdministratorAndUserRole(SysUserModel userModel , String roleId) throws Exception {
		sysUserMapper.insertSelective(userModel);
		SysUserRoleModel sysUserRoleModel = new SysUserRoleModel();
		sysUserRoleModel.setUserId(userModel.getId());
		sysUserRoleModel.setRoleId(Long.parseLong(roleId));
		sysUserRoleMapper.insertSelective(sysUserRoleModel);
	}

	//@SystemServiceLog(description = "编辑后台用户与分配的角色")
	@Override
	public void updateAdministratorAndUserRole(SysUserModel userModel, String roleId) throws Exception{
		sysUserMapper.updateByPrimaryKeySelective(userModel);
		SysUserRoleModel sysUserRoleModel = new SysUserRoleModel();
		sysUserRoleModel.setUserId(userModel.getId());
		sysUserRoleModel.setRoleId(Long.parseLong(roleId));
		SysUserRoleModel sysUserRoleFind = sysUserRoleMapper.selectByUserIdAndRoleId(sysUserRoleModel);
		if (Common.isEmpty(sysUserRoleFind)) {
			sysUserRoleMapper.deleteByUserId(userModel.getId());
			sysUserRoleMapper.insertSelective(sysUserRoleModel);
		}
	}
	
	//@SystemServiceLog(description = "删除后台用户与分配的角色")
	@Override
	public void deleteAdministratorAndUserRole(String userIds) {
		try {
			String[] userIdArr = userIds.split(",");
			List<String> userIdList = new ArrayList<String>();
			for (String string : userIdArr) {
				userIdList.add(string);
			}
			sysUserRoleMapper.deleteByUserIdBatch(userIdList);
			sysUserMapper.deleteByPrimaryKeyBatch(userIdList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	//@SystemServiceLog(description = "新增后台用户角色以外的角色")
	@Override
	public void addAdministratorUserAction(String userId, String[] resourcesId) throws Exception {
		List<SysUserActionModel> resourcesIdList = new ArrayList<SysUserActionModel>();
		for (String string : resourcesId) {
			if("0".equals(string) || "-1".equals(string)){
				
			}
			else
			{
				SysUserActionModel model = new SysUserActionModel();
				model.setResId(Integer.parseInt(string));
				model.setUserId(Integer.parseInt(userId));
				resourcesIdList.add(model);
			}
		}
		sysUserActionMapper.deleteByUserId(Long.parseLong(userId));
		if(resourcesIdList.size() > 0)
		{
			sysUserActionMapper.insertBatchUserAction(resourcesIdList);
		}
	
	}

	//@SystemServiceLog(description = "获取用户角色与角色以外的权限")
	@Override
	public List<TreeObject> getRoleAndUserActionHaveResources(String userId) {
		// 查询所有菜单
		List<SysResourcesModel> resourcesList = sysResourcesMapper.selectMenuAll();
		// 查询用户的角色
		SysUserRoleModel userRoleModel = sysUserRoleMapper.selectByUserId(Long.parseLong(userId));
		// 查询用户拥有的权限
		List<SysUserActionModel> userActionList = sysUserActionMapper.selectByUserId(Long.parseLong(userId));
		
		List<TreeObject> list = new ArrayList<TreeObject>();
		if(!Common.isEmpty(userRoleModel)){
			// 查询角色拥有的权限
			List<SysRoleActionModel> roleActionList = sysRoleActionMapper.selectByRoleId(userRoleModel.getRoleId());
			for (SysResourcesModel resources : resourcesList) {
				TreeObject ts = new TreeObject();
				ts.setId(resources.getId());
				ts.setParentId(resources.getParentId());
				ts.setName(resources.getName());
				ts.setResKey(resources.getResKey());
				ts.setResUrl(resources.getResUrl());
				ts.setLevel(resources.getOrderNo());
				ts.setType(resources.getType());
				ts.setDescription(resources.getDescription());
				ts.setIcon(resources.getIcon());
				ts.setIshide(resources.getIsHide());
				ts.setBtn(resources.getBtn());
				for (SysRoleActionModel roleAction : roleActionList) {
					if(resources.getId().intValue() == roleAction.getResId().intValue()){
						ts.setPermissionsStatus("0");
						break;
					}
				}
				for (SysUserActionModel userAction : userActionList) {
					if(resources.getId().intValue() == userAction.getResId().intValue()){
						ts.setPermissionsStatus2("0");
						break;
					}
				}
				list.add(ts);
			}
		}
		else
		{
			for (SysResourcesModel resources : resourcesList) {
				TreeObject ts = new TreeObject();
				ts.setId(resources.getId());
				ts.setParentId(resources.getParentId());
				ts.setName(resources.getName());
				ts.setResKey(resources.getResKey());
				ts.setResUrl(resources.getResUrl());
				ts.setLevel(resources.getOrderNo());
				ts.setType(resources.getType());
				ts.setDescription(resources.getDescription());
				ts.setIcon(resources.getIcon());
				ts.setIshide(resources.getIsHide());
				ts.setBtn(resources.getBtn());
				for (SysUserActionModel userAction : userActionList) {
					if(resources.getId().intValue() == userAction.getResId().intValue()){
						ts.setPermissionsStatus2("0");
						break;
					}
				}
				list.add(ts);
			}
		}
		
		Tree2Util treeUtil = new Tree2Util();
		List<TreeObject> menuInitList = treeUtil.getChildTreeObjects(list, 0);
		return menuInitList;
	}
	@Override
	public List<SysUserModel> selectSysUserByList(SysUserModel sysUser){
		return sysUserMapper.selectSysUserByList(sysUser);
	}
}

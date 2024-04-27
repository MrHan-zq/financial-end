package com.qst.financial.service.impl.base;

import com.qst.financial.dao.mapper.base.SysResourcesMapper;
import com.qst.financial.dao.mapper.base.SysRoleActionMapper;
import com.qst.financial.dao.mapper.base.SysRoleMapper;
import com.qst.financial.modle.base.SysResourcesModel;
import com.qst.financial.modle.base.SysRoleActionModel;
import com.qst.financial.modle.base.SysRoleModel;
import com.qst.financial.service.base.SysResourcesService;
import com.qst.financial.service.base.SysRoleManageService;
import com.qst.financial.util.common.Tree2Util;
import com.qst.financial.util.common.TreeObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("sysRoleManageService")
public class SysRoleManageServiceImpl implements SysRoleManageService {

	@Autowired
	SysRoleMapper sysRoleMapper;
	
	@Autowired
	SysRoleActionMapper sysRoleActionMapper;
	
	@Autowired
    SysResourcesService sysResourcesService;
	
	@Autowired
    SysResourcesMapper sysResourcesMapper;
	
	//@SystemServiceLog(description = "新增角色与角色的权限")
	@Override
	public void addRoleAndRoleAction(SysRoleModel record, String[] resourcesId) throws Exception {
		sysRoleMapper.insertSelective(record);
		List<SysRoleActionModel> list = new ArrayList<SysRoleActionModel>();
		for (String string : resourcesId) {
			SysRoleActionModel model = new SysRoleActionModel();
			model.setRoleId(record.getId());
			model.setResId(Long.parseLong(string));
			list.add(model);
		}
		sysRoleActionMapper.insertBatchRoleAction(list);
	
	}

	//@SystemServiceLog(description = "编辑角色与角色的权限")
	@Override
	public void updateRoleAndRoleAction(SysRoleModel record, String[] resourcesId) throws Exception {
		sysRoleActionMapper.deleteByRoleId(record.getId());
		sysRoleMapper.updateByPrimaryKeySelective(record);
		List<SysRoleActionModel> list = new ArrayList<SysRoleActionModel>();
		for (String string : resourcesId) {
			SysRoleActionModel model = new SysRoleActionModel();
			model.setRoleId(record.getId());
			model.setResId(Long.parseLong(string));
			list.add(model);
		}
		sysRoleActionMapper.insertBatchRoleAction(list);
	}

	//@SystemServiceLog(description = "获取角色拥有的权限")
	@Override
	public List<TreeObject> getRoleHaveResources(String roleId) {
		List<SysResourcesModel> resourcesList = sysResourcesMapper.selectMenuAll();
		List<SysRoleActionModel> roleActionList = sysRoleActionMapper.selectByRoleId(Long.valueOf(roleId));
		List<TreeObject> list = new ArrayList<TreeObject>();
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
			list.add(ts);
		}
		Tree2Util treeUtil = new Tree2Util();
		List<TreeObject> menuInitList = treeUtil.getChildTreeObjects(list, 0);
		return menuInitList;
	}

	//@SystemServiceLog(description = "删除角色与角色的权限")
	@Override
	public void deleteRoleAndRoleAction(String roleId) throws Exception {
		String[] roleIdArr = roleId.split(",");
		List<String> roleIdList = new ArrayList<String>();
		for (String string : roleIdArr) {
			roleIdList.add(string);
		}
		sysRoleActionMapper.deleteByRoleIdBatch(roleIdList);
		sysRoleMapper.deleteByPrimaryKeyBatch(roleIdList);
	}


}

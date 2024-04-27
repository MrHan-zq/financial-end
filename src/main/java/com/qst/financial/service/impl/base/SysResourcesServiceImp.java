package com.qst.financial.service.impl.base;

import com.qst.financial.dao.mapper.base.SysResourcesMapper;
import com.qst.financial.modle.base.SysResourcesModel;
import com.qst.financial.service.base.SysResourcesService;
import com.qst.financial.util.common.Tree2Util;
import com.qst.financial.util.common.TreeObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("sysResourcesService")
public class SysResourcesServiceImp implements SysResourcesService {

	@Autowired
	private SysResourcesMapper sysResourcesMapper;
	
	//@SystemServiceLog(description = "获取用户菜单操作权限")
	@Override
	public List<TreeObject> getUserMenuActionAuthority(String userId) throws Exception {
		List<SysResourcesModel> resourcesList = sysResourcesMapper.selectUserMenu(userId);
		List<TreeObject> list = new ArrayList<TreeObject>();
		for (SysResourcesModel sysResourcesModel : resourcesList) {
			TreeObject ts = new TreeObject();
			ts.setId(sysResourcesModel.getId());
			ts.setParentId(sysResourcesModel.getParentId());
			ts.setName(sysResourcesModel.getName());
			ts.setResKey(sysResourcesModel.getResKey());
			ts.setResUrl(sysResourcesModel.getResUrl());
			ts.setLevel(sysResourcesModel.getOrderNo());
			ts.setType(sysResourcesModel.getType());
			ts.setDescription(sysResourcesModel.getDescription());
			ts.setIcon(sysResourcesModel.getIcon());
			ts.setIshide(sysResourcesModel.getIsHide());
			ts.setBtn(sysResourcesModel.getBtn());
			list.add(ts);
		}
		
		Tree2Util treeUtil = new Tree2Util();
		List<TreeObject> menuInitList = treeUtil.getChildTreeObjects(list, 0);
		return menuInitList;
	}

	//@SystemServiceLog(description = "获取Root用户菜单操作权限")
	@Override
	public List<TreeObject> getRootUserMenuActionAuthority() throws Exception {
		List<SysResourcesModel> resourcesList = sysResourcesMapper.selectRootUserMenu();
		List<TreeObject> list = new ArrayList<TreeObject>();
		for (SysResourcesModel sysResourcesModel : resourcesList) {
			TreeObject ts = new TreeObject();
			ts.setId(sysResourcesModel.getId());
			ts.setParentId(sysResourcesModel.getParentId());
			ts.setName(sysResourcesModel.getName());
			ts.setResKey(sysResourcesModel.getResKey());
			ts.setResUrl(sysResourcesModel.getResUrl());
			ts.setLevel(sysResourcesModel.getOrderNo());
			ts.setType(sysResourcesModel.getType());
			ts.setDescription(sysResourcesModel.getDescription());
			ts.setIcon(sysResourcesModel.getIcon());
			ts.setIshide(sysResourcesModel.getIsHide());
			ts.setBtn(sysResourcesModel.getBtn());
			list.add(ts);
		}
		
		Tree2Util treeUtil = new Tree2Util();
		List<TreeObject> menuInitList = treeUtil.getChildTreeObjects(list, 0);
		return menuInitList;
	}

	//@SystemServiceLog(description = "获取所有菜单操作权限")
	@Override
	public List<TreeObject> getMenuAllActionAuthority() throws Exception {
		List<SysResourcesModel> resourcesList = sysResourcesMapper.selectMenuAll();
		List<TreeObject> list = new ArrayList<TreeObject>();
		for (SysResourcesModel sysResourcesModel : resourcesList) {
			TreeObject ts = new TreeObject();
			ts.setId(sysResourcesModel.getId());
			ts.setParentId(sysResourcesModel.getParentId());
			ts.setName(sysResourcesModel.getName());
			ts.setResKey(sysResourcesModel.getResKey());
			ts.setResUrl(sysResourcesModel.getResUrl());
			ts.setLevel(sysResourcesModel.getOrderNo());
			ts.setType(sysResourcesModel.getType());
			ts.setDescription(sysResourcesModel.getDescription());
			ts.setIcon(sysResourcesModel.getIcon());
			ts.setIshide(sysResourcesModel.getIsHide());
			ts.setBtn(sysResourcesModel.getBtn());
			list.add(ts);
		}
		
		Tree2Util treeUtil = new Tree2Util();
		List<TreeObject> menuInitList = treeUtil.getChildTreeObjects(list, 0);
		return menuInitList;
	}

	//@SystemServiceLog(description = "删除根菜单的所有子菜单;子菜单的所有按钮")
	@Override
	public void deleteMenuAndmenuSonAndButton(String id) throws Exception {
		//删除按钮
		List<SysResourcesModel> resourcesList = sysResourcesMapper.selectByParentId(Integer.parseInt(id));
		if (null != resourcesList && resourcesList.size() > 0) {
			SysResourcesModel model = new SysResourcesModel();
			for (int i = 0; i < resourcesList.size(); i++) {
				model = resourcesList.get(i);
				sysResourcesMapper.deleteByParentId(model.getId());
			}
		}
		//删除子菜单
		sysResourcesMapper.deleteByParentId(Integer.parseInt(id));
		//删除根菜单
    	sysResourcesMapper.deleteByPrimaryKey(Integer.parseInt(id));
	}
	@Override
	public List<SysResourcesModel> selectUserMenuButton(Map<String,String> map){
		return sysResourcesMapper.selectUserMenuButton(map);
	}
	@Override
	public List<SysResourcesModel> selectRootUserMenuButton(Map<String,String> map){
		return sysResourcesMapper.selectRootUserMenuButton(map);
	}
	@Override
	public SysResourcesModel selectByPrimaryKey(Integer id){
		return sysResourcesMapper.selectByPrimaryKey(id);
	}
}

package com.qst.financial.controller.base;

import com.qst.financial.dao.mapper.base.SysResourcesMapper;
import com.qst.financial.dao.mapper.base.SysRoleMapper;
import com.qst.financial.modle.base.SysResourcesModel;
import com.qst.financial.modle.base.SysRoleModel;
import com.qst.financial.modle.base.SysUserModel;
import com.qst.financial.service.base.SysResourcesService;
import com.qst.financial.service.base.SysRoleManageService;
import com.qst.financial.util.common.Common;
import com.qst.financial.util.common.TreeObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;


@Controller
@RequestMapping("/admin/pc/roleManage")
public class RoleManageController extends BaseController {
	
	@Autowired
   	private SysResourcesMapper sysResourcesMapper;
	
	@Autowired
   	private SysResourcesService sysResourcesService;
	
	@Autowired
   	private SysRoleMapper sysRoleMapper;
	
	@Autowired
   	private SysRoleManageService sysRoleManageService;

	/**
     * 角色管理首页
     */
	//@SystemControllerLog(description = "角色管理")
    @RequestMapping(value = "/roleManageIndex", method = RequestMethod.GET)
    public String roleManageIndex(String fatherId, String parentId) throws Exception
    {
        if (!Common.isEmpty(fatherId) && !Common.isEmpty(parentId))
        {
            HttpSession session = getSession();
            session.setAttribute("fatherMenuId", fatherId);
            session.setAttribute("menuId", parentId);
        }
    
        return "admin/jsp/role/roleManageIndex";
    }
    
    /**
     * 角色列表与按钮列表
     */
    @RequestMapping(value = "/roleListAndButtonList", method = RequestMethod.GET)
    public String roleListAndButtonList(HttpServletRequest request, Model model)
    {
        try
        {
        	String parentId = session.getAttribute("menuId").toString();
    		SysUserModel systemUser = getSessionUser();
    		Map<String,String> map = new HashMap<String, String>();
    		map.put("parent_id", parentId);
    		List<SysResourcesModel> buttonList = new ArrayList<SysResourcesModel>();
    		if ("1".equals(systemUser.getAccountType().toString())) {
    			map.put("user_id", systemUser.getId().toString());
    			buttonList = sysResourcesMapper.selectUserMenuButton(map);
    		} else {
    			buttonList = sysResourcesMapper.selectRootUserMenuButton(map);
    		}
            model.addAttribute("buttonList", buttonList);
            
            String pageNow = getPara("pageNow");
            String pageSize = getPara("pageSize");
            Map<String,String> mapRole = new HashMap<String, String>();
            int count = sysRoleMapper.selectRoleCount(mapRole);
			initPage(request, Integer.parseInt(pageNow), Integer.parseInt(pageSize), count);
			String limit = getLimit(Integer.parseInt(pageNow), Integer.parseInt(pageSize));
			mapRole.put("limit", limit);
            List<Map<String, Object>> roleList = sysRoleMapper.selectRoleList(mapRole);
            model.addAttribute("roleList", roleList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return "admin/jsp/role/roleListAndButtonList";
    }
	
    /**
     * 角色列表
     */
    @RequestMapping(value = "/roleList", method = RequestMethod.GET)
    public String roleList(HttpServletRequest request, Model model)
    {
        try
        {
            String pageNow = getPara("pageNow");
            String pageSize = getPara("pageSize");
            String userName = getPara("userName");
            if(Common.isEmpty(pageNow)){
            	pageNow = "1";
            }
            if(Common.isEmpty(pageSize)){
            	pageSize = "10";
            }
            Map<String,String> mapRole = new HashMap<String, String>();
            mapRole.put("userId", getSessionUserid().toString());
            if(Common.isNotEmpty(userName)){
            	mapRole.put("userName", userName);
            }
            int count = sysRoleMapper.selectRoleCount(mapRole);
			initPage(request, Integer.parseInt(pageNow), Integer.parseInt(pageSize), count);
			String limit = getLimit(Integer.parseInt(pageNow), count);
			mapRole.put("limit", limit);
            List<Map<String, Object>> roleList = sysRoleMapper.selectRoleList(mapRole);
            model.addAttribute("roleList", roleList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return "admin/jsp/role/roleList";
    }
    
    /**
     * 角色新增页面
     */
    @RequestMapping(value = "/roleAddPage", method = RequestMethod.GET)
    public String roleAddPage(Model model)
    {
        try
        {
        	 List<TreeObject> treeObjectList = sysResourcesService.getMenuAllActionAuthority();
             model.addAttribute("menuList", treeObjectList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return "admin/jsp/role/roleAddPage";
    }
    
    /**
     * 角色新增名称验证
     */
    @RequestMapping(value = "/roleAddNameValidate", method = RequestMethod.GET)
    @ResponseBody
    public boolean roleAddNameValidate()
    {
        try
        {
			String roleName = getPara("roleName");
			SysRoleModel roleModel = sysRoleMapper.selectByName(roleName);
			if (Common.isEmpty(roleModel)) {
				return true;
			} else {
				return false;
			}
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * 角色保存
     */
    //@SystemControllerLog(description = "角色保存")
    @RequestMapping(value = "/roleSave", method = RequestMethod.POST)
    public String roleSave(@RequestParam("resourcesId") String[] resourcesId, Model model) throws Exception
    {
    	String roleName = getPara("roleName");
    	String roleDescription = getPara("roleDescription");
    	
    	SysRoleModel roleModel = new SysRoleModel();
    	roleModel.setName(roleName);
    	roleModel.setDescription(roleDescription);
    	roleModel.setCreateTime(new Date());
    	roleModel.setUserId(getSessionUserid());
    	
    	sysRoleManageService.addRoleAndRoleAction(roleModel, resourcesId);
    
		return "redirect:roleManageIndex";
    }
    
    /**
     * 角色编辑页面
     */
    @RequestMapping(value = "/roleEditPage", method = RequestMethod.GET)
    public String roleEditPage(Model model)
    {
        try
        {
        	String roleId = getPara("roleId");
        	SysRoleModel roleModel = sysRoleMapper.selectByPrimaryKey(Long.valueOf(roleId));
        	List<TreeObject> treeObjectList = sysRoleManageService.getRoleHaveResources(roleId);
        	model.addAttribute("roleModel", roleModel);
        	model.addAttribute("menuList", treeObjectList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return "admin/jsp/role/roleEditPage";
    }
    
    /**
     * 角色更新名称验证
     */
    @RequestMapping(value = "/roleUpdateNameValidate", method = RequestMethod.GET)
    @ResponseBody
    public boolean roleUpdateNameValidate()
    {
        try
        {
        	String roleId = getPara("roleId");
			String roleName = getPara("roleName");
			SysRoleModel roleParameter = new SysRoleModel();
			roleParameter.setId(Long.parseLong(roleId));
			roleParameter.setName(roleName);
			SysRoleModel roleModel = sysRoleMapper.selectByNameAndNotIncludedSelf(roleParameter);
			if (Common.isEmpty(roleModel)) {
				return true;
			} else {
				return false;
			}
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * 角色更新保存
     */
    //@SystemControllerLog(description = "角色更新保存")
    @RequestMapping(value = "/roleUpdateSave", method = RequestMethod.POST)
    public String roleUpdateSave(@RequestParam("resourcesId") String[] resourcesId, Model model) throws Exception
    {
    	String roleId = getPara("roleId");
    	String roleName = getPara("roleName");
    	String roleDescription = getPara("roleDescription");
    	
    	SysRoleModel roleModel = new SysRoleModel();
    	roleModel.setId(Long.parseLong(roleId));
    	roleModel.setName(roleName);
    	roleModel.setDescription(roleDescription);
    	
    	sysRoleManageService.updateRoleAndRoleAction(roleModel, resourcesId);
    
		return "redirect:roleManageIndex";
    }
    
    /**
     * 角色删除
     */
    //@SystemControllerLog(description = "角色删除")
    @RequestMapping(value = "/roleDelete", method = RequestMethod.POST)
    public String roleDelete()
    {
        try
        {
        	String roleId = getPara("roleId");
        	if(Common.isNotEmpty(roleId)){
        		sysRoleManageService.deleteRoleAndRoleAction(roleId);
        	}
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return "redirect:roleManageIndex";
    }
}

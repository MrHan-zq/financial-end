package com.qst.financial.controller.base;

import com.qst.financial.dao.mapper.base.SysResourcesMapper;
import com.qst.financial.modle.base.SysResourcesModel;
import com.qst.financial.modle.base.SysUserModel;
import com.qst.financial.service.base.SysResourcesService;
import com.qst.financial.util.common.Common;
import com.qst.financial.util.common.CommonConstant;
import com.qst.financial.util.common.TreeObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import com.qst.financial.redis.RedisService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/pc/resources")
public class MenuController extends BaseController {

	@Autowired
   	private SysResourcesService sysResourcesService;
	
	@Autowired
   	private SysResourcesMapper sysResourcesMapper;
	@Autowired
	private RedisService redisService;
	/**
     * 菜单管理首页
     */
	//@SystemControllerLog(description = "菜单管理")
    @RequestMapping(value = "/menuManageIndex", method = RequestMethod.GET)
    public String menuManageIndex(String fatherId, String parentId) throws Exception
    {
        if (!Common.isEmpty(fatherId) && !Common.isEmpty(parentId))
        {
            HttpSession session = getSession();
            session.setAttribute("fatherMenuId", fatherId);
            session.setAttribute("menuId", parentId);
        }
        return "admin/jsp/menu/menuIndex";
    }
    
    /**
     * 菜单列表
     */
    @RequestMapping(value = "/menuList", method = RequestMethod.GET)
    public String menuList(Model model)
    {
        try
        {
        	String parentId = session.getAttribute("menuId").toString();
        	Object bean = redisService.getAttrAsObject(request, CommonConstant.SESSION_USER);
    		SysUserModel systemUser = (SysUserModel) bean;
    		Map<String,String> map = new HashMap<String, String>();
    		map.put("parent_id", parentId);
    		List<SysResourcesModel> buttonList = new ArrayList<SysResourcesModel>();
    		if ("1".equals(systemUser.getAccountType().toString())) {
    			map.put("user_id", systemUser.getId().toString());
    			buttonList = sysResourcesMapper.selectUserMenuButton(map);
    		} else {
    			buttonList = sysResourcesMapper.selectRootUserMenuButton(map);
    		}
            List<TreeObject> treeObjectList = sysResourcesService.getMenuAllActionAuthority();
            model.addAttribute("menuList", treeObjectList);
            model.addAttribute("buttonList", buttonList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
            return "admin/jsp/menu/menuList";
    }
    
    /**
     * 所有根菜单
     */
    @RequestMapping(value = "/menuRootList", method = RequestMethod.GET)
    public String menuRootList(Model model)
    {
        try
        {
        	List<SysResourcesModel> menuRootList = sysResourcesMapper.selectByParentId(0);
            model.addAttribute("menuRootList", menuRootList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "admin/jsp/menu/menuRootList";
    }
    
    /**
     * 根菜单的所有子菜单
     */
    @RequestMapping(value = "/menuRootSonList", method = RequestMethod.GET)
    public String menuRootSonList(Model model)
    {
        try
        {
            String rootMenuId = getPara("rootMenuId");
            List<SysResourcesModel> menuRootList = sysResourcesMapper.selectByParentId(Integer.parseInt(rootMenuId));
            model.addAttribute("sonMenuList", menuRootList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "admin/jsp/menu/menuRootSonList";
    }
    
    /**
     * 保存菜单
     */
    //@SystemControllerLog(description = "新增菜单")
    @RequestMapping(value = "/menuRootSave", method = RequestMethod.POST)
    public String menuRootSave() throws Exception
    {
        String menuType = getPara("menuType");
        String name = getPara("name");
        String resKey = getPara("resKey");
        String resUrl = getPara("resUrl");
        String icon = getPara("icon");
        String description = getPara("description");
        
        SysResourcesModel resourcesModel = new SysResourcesModel();
        resourcesModel.setType(menuType);
        resourcesModel.setName(name);
        resourcesModel.setResKey(resKey);
        resourcesModel.setResUrl(resUrl);
        resourcesModel.setIcon(icon.trim());
        resourcesModel.setDescription(description);
        resourcesModel.setParentId(0);
        if("1".equals(menuType))
        {
            String parentId = getPara("parentId");
            resourcesModel.setParentId(Integer.parseInt(parentId));
        }
        
        if("2".equals(menuType))
        {
            String sonId = getPara("sonId");
            if(Common.isEmpty(sonId))
            {
                String parentId = getPara("parentId");
                resourcesModel.setParentId(Integer.parseInt(parentId));
            }
            else
            {
                resourcesModel.setParentId(Integer.parseInt(sonId));
            }
            String btn = getPara("btn");
            resourcesModel.setBtn(btn);
        }
        
        String level = getPara("level");
        if(Common.isEmpty(level))
        {
        	
            resourcesModel.setOrderNo(1);
        }
        else
        {
			resourcesModel.setOrderNo(Integer.parseInt(level));
        }
        
        sysResourcesMapper.insertSelective(resourcesModel);
        
        Object bean = redisService.getAttrAsObject(request, CommonConstant.SESSION_USER);
		SysUserModel systemUser = (SysUserModel) bean;
    	List<TreeObject> menuInitList = new ArrayList<TreeObject>();
		if ("1".equals(systemUser.getAccountType().toString())) {
			menuInitList = sysResourcesService.getUserMenuActionAuthority(systemUser.getId().toString());
		} else {
			menuInitList = sysResourcesService.getRootUserMenuActionAuthority();
		}

		session.setAttribute("menuInitList", menuInitList);
    
        return "redirect:menuManageIndex";
    }
    
    /**
     * 打开编辑菜单
     */
    @ResponseBody
    @RequestMapping(value = "/menuEdit", method = RequestMethod.POST)
    public SysResourcesModel menuEdit()
    {
    	SysResourcesModel resFormMap = new SysResourcesModel();
        try
        {
            String resourceId = getPara("resourceId");
            resFormMap = sysResourcesMapper.selectByPrimaryKey(Integer.parseInt(resourceId));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return resFormMap;
    }
    
    /**
     * 修改菜单
     */
    //@SystemControllerLog(description = "修改菜单")
    @RequestMapping(value = "/menuUpdate", method = RequestMethod.POST)
    public String menuUpdate() throws Exception
    {
        String name = getPara("name");
        String resKey = getPara("resKey");
        String resUrl = getPara("resUrl");
        String btn = getPara("btn");
        String icon = getPara("icon");
        String level = getPara("level");
        String description = getPara("description");
        String resourcesId = getPara("resourcesId");
        
        SysResourcesModel resourcesModel = new SysResourcesModel();
        resourcesModel.setName(name);
        resourcesModel.setResKey(resKey);
        resourcesModel.setResUrl(resUrl);
        resourcesModel.setBtn(btn);
        resourcesModel.setIcon(icon.trim());
        resourcesModel.setOrderNo(Integer.parseInt(level));
        resourcesModel.setDescription(description);
        resourcesModel.setId(Integer.parseInt(resourcesId));
        sysResourcesMapper.updateByPrimaryKeySelective(resourcesModel);
        
        Object bean = redisService.getAttrAsObject(request, CommonConstant.SESSION_USER);
		SysUserModel systemUser = (SysUserModel) bean;
    	List<TreeObject> menuInitList = new ArrayList<TreeObject>();
		if ("1".equals(systemUser.getAccountType().toString())) {
			menuInitList = sysResourcesService.getUserMenuActionAuthority(systemUser.getId().toString());
		} else {
			menuInitList = sysResourcesService.getRootUserMenuActionAuthority();
		}

		session.setAttribute("menuInitList", menuInitList);
    
        return "redirect:menuManageIndex";
    }
    
    /**
     * 删除菜单
     */
   // @SystemControllerLog(description = "删除菜单")
    @RequestMapping(value = "/menuDelete", method = RequestMethod.POST)
    public String menuDelete() throws Exception
    {
        String deleteRootMenu = getPara("deleteRootMenu");
        String ids = getPara("ids");
        //删除根菜单操作
        if("1".equals(deleteRootMenu))
        {
        	sysResourcesService.deleteMenuAndmenuSonAndButton(ids);
        }
        if("0".equals(deleteRootMenu))
        {
            //如果是子菜单，该子菜单下级还有按钮，则删除按钮
            List<SysResourcesModel> resourcesList = sysResourcesMapper.selectByParentId(Integer.parseInt(ids));
            if (null != resourcesList && resourcesList.size() > 0) {
                SysResourcesModel model = new SysResourcesModel();
                for (int i = 0; i < resourcesList.size(); i++) {
                    model = resourcesList.get(i);
                    sysResourcesMapper.deleteByParentId(model.getId());
                }
            }
            //删除子菜单
        	sysResourcesMapper.deleteByPrimaryKey(Integer.parseInt(ids));
        }
        
        Object bean = redisService.getAttrAsObject(request, CommonConstant.SESSION_USER);
		SysUserModel systemUser = (SysUserModel) bean;
    	List<TreeObject> menuInitList = new ArrayList<TreeObject>();
		if ("1".equals(systemUser.getAccountType().toString())) {
			menuInitList = sysResourcesService.getUserMenuActionAuthority(systemUser.getId().toString());
		} else {
			menuInitList = sysResourcesService.getRootUserMenuActionAuthority();
		}

		session.setAttribute("menuInitList", menuInitList);
    
		return "redirect:menuManageIndex";
    }
	
}

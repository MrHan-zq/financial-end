package com.qst.financial.controller.base;

import com.qst.financial.dao.mapper.base.SysResourcesMapper;
import com.qst.financial.dao.mapper.base.SysRoleMapper;
import com.qst.financial.dao.mapper.base.SysUserMapper;
import com.qst.financial.dao.mapper.base.SysUserRoleMapper;
import com.qst.financial.modle.base.*;
import com.qst.financial.redis.RedisService;
import com.qst.financial.service.base.OrgInfoService;
import com.qst.financial.service.base.SysRoleManageService;
import com.qst.financial.service.base.SysUserService;
import com.qst.financial.util.common.Common;
import com.qst.financial.util.common.CommonConstant;
import com.qst.financial.util.common.MethodUtil;
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
@RequestMapping("/admin/pc/administratorManage")
public class AdministratorManageController extends BaseController {
	@Autowired
	private OrgInfoService orgInfoService;
	@Autowired
   	private SysResourcesMapper sysResourcesMapper;
	
	@Autowired
   	private SysUserMapper sysUserMapper;
	
	@Autowired
   	private SysRoleMapper sysRoleMapper;
	
	@Autowired
   	private  SysUserService sysUserService;
	
	@Autowired
   	private SysUserRoleMapper sysUserRoleMapper;
	
	@Autowired
   	private SysRoleManageService sysRoleManageService;
	@Autowired
	private RedisService redisService;
	
	/**
     * 后台用户管理首页
     */
	//@SystemControllerLog(description = "后台用户管理")
    @RequestMapping(value = "/administratorIndex", method = RequestMethod.GET)
    public String administratorIndex(String fatherId, String parentId) throws Exception
    {
    	if (!Common.isEmpty(fatherId) && !Common.isEmpty(parentId))
        {
            HttpSession session = getSession();
            session.setAttribute("fatherMenuId", fatherId);
            session.setAttribute("menuId", parentId);
        }
        return "admin/jsp/administrator/administratorIndex";
    }
    
    /**
     * 后台用户列表与按钮列表
     */
    @RequestMapping(value = "/administratorListAndButtonList", method = RequestMethod.GET)
    public String administratorListAndButtonList(HttpServletRequest request, Model model)
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
            Map<String,String> sysUserMap = new HashMap<String, String>();
            SysUserModel users=(SysUserModel) redisService.getAttrAsObject(request, CommonConstant.SESSION_USER);
			int type=users.getType();
			if(type==1){
				sysUserMap.put("orgId", users.getOrgId().toString());
	        }
           
            sysUserMap.put("userId", getSessionUserid().toString());
            int count = sysUserMapper.selectSysUserCount(sysUserMap);
			initPage(request, Integer.parseInt(pageNow), Integer.parseInt(pageSize), count);
			String limit = getLimit(Integer.parseInt(pageNow), Integer.parseInt(pageSize));
			sysUserMap.put("limit", limit);
            List<Map<String, Object>> administratorList = sysUserMapper.selectSysUserList(sysUserMap);
            model.addAttribute("administratorList", administratorList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return "admin/jsp/administrator/administratorListAndButtonList";
    }
	
    /**
     * 后台用户列表
     */
    @RequestMapping(value = "/administratorList", method = RequestMethod.POST)
    public String administratorList(Model model)
    {
        try
        {
        	String pageNow = getPara("pageNow");
            String pageSize = getPara("pageSize");
            String userName = getPara("userName");
            String accountName = getPara("accountName");
            if(Common.isEmpty(pageNow)){
            	pageNow = "1";
            }
            if(Common.isEmpty(pageSize)){
            	pageSize = "10";
            }
            Map<String,String> sysUserMap = new HashMap<String, String>();
            sysUserMap.put("userId", getSessionUserid().toString());
            if(Common.isNotEmpty(userName)){
            	sysUserMap.put("userName", userName);
            }
            if(Common.isNotEmpty(accountName)){
            	sysUserMap.put("accountName", accountName);
            }
            int count = sysUserMapper.selectSysUserCount(sysUserMap);
            initPage(request, Integer.parseInt(pageNow), Integer.parseInt(pageSize), count);
			String limit = getLimit(Integer.parseInt(pageNow), count);
			sysUserMap.put("limit", limit);
            List<Map<String, Object>> administratorList = sysUserMapper.selectSysUserList(sysUserMap);
            model.addAttribute("administratorList", administratorList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return "admin/jsp/administrator/administratorList";
    }
    
    /**
     * 后台用户新增页面
     */
    @RequestMapping(value = "/administratorAddPage", method = RequestMethod.GET)
    public String administratorAddPage(Model model)
    {
        try
        {
        	List<OrgInfo> orgList=orgInfoService.list(null);
            model.addAttribute("orgList", orgList);
        	List<SysRoleModel> roleList = sysRoleMapper.selectRoleAll();
        	model.addAttribute("roleList", roleList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return "admin/jsp/administrator/administratorAddPage";
    }
    
    /**
     * 后台用户新增账号验证
     */
    @RequestMapping(value = "/administratorAddAccountNameValidate", method = RequestMethod.GET)
    @ResponseBody
    public boolean administratorAddAccountNameValidate()
    {
        try
        {
        	String accountName = getPara("accountName");
			SysUserModel userModel = sysUserMapper.selectByAccountName(accountName);
			if (Common.isEmpty(userModel)) {
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
     * 后台用户保存
     */
   // @SystemControllerLog(description = "后台用户保存")
    @RequestMapping(value = "/administratorSave", method = RequestMethod.POST)
    public String administratorSave() throws Exception
    {
    	String userName = getPara("userName");
    	String accountName = getPara("accountName");
    	String pwd = getPara("pwd");
    	String roleId = getPara("roleId");
    	String description = getPara("description");
    	String orgId = getPara("orgId");
    	
		SysUserModel userModel = sysUserMapper.selectByAccountName(accountName);
		if (Common.isEmpty(userModel)) {
			SysUserModel userAdd = new SysUserModel();
			userAdd.setUserName(userName);
			userAdd.setAccountName(accountName);
			String md5password = MethodUtil.getMD5(pwd, "UTF-8", 0);
			userAdd.setPwd(md5password);
			userAdd.setDescription(description);
			userAdd.setCreateTime(new Date());
			userAdd.setCreateUserId(getSessionUserid());
			userAdd.setLocked(0);
			userAdd.setAccountType(1);
			String accountPortType = "4";
			userAdd.setAccountPortType(Long.parseLong(accountPortType));
			if(orgId!=null && orgId.length()>0){
				userAdd.setOrgId(Long.parseLong(orgId));
			}
			sysUserService.addAdministratorAndUserRole(userAdd, roleId);
		}
    
		return "redirect:administratorIndex";
    }
    
    /**
     * 后台用户编辑页面
     */
    @RequestMapping(value = "/administratorEditPage", method = RequestMethod.GET)
    public String administratorEditPage(Model model)
    {
        try
        {
        	String systemUserId = getPara("systemUserId");
        	SysUserModel sysUserModel = sysUserMapper.selectByPrimaryKey(Long.parseLong(systemUserId));
        	List<SysRoleModel> roleList = sysRoleMapper.selectRoleAll();
        	SysUserRoleModel sysUserRoleModel = sysUserRoleMapper.selectByUserId(Long.parseLong(systemUserId));
        	List<OrgInfo> orgList=orgInfoService.list(null);
            model.addAttribute("orgList", orgList);
        	model.addAttribute("administratorInfo", sysUserModel);
        	model.addAttribute("roleList", roleList);
        	model.addAttribute("userRoleInfo", sysUserRoleModel);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return "admin/jsp/administrator/administratorEditPage";
    }
    
    /**
     * 后台用户更新账号验证
     */
    @RequestMapping(value = "/administratorUpdateAccountNameValidate", method = RequestMethod.GET)
    @ResponseBody
    public boolean administratorUpdateAccountNameValidate()
    {
        try
        {
        	String systemUserId = getPara("systemUserId");
        	String accountName = getPara("accountName");
        	SysUserModel userParameter = new SysUserModel();
        	userParameter.setId(Long.parseLong(systemUserId));
        	userParameter.setAccountName(accountName);
			SysUserModel userModel = sysUserMapper.selectByAccountNameAndNotIncludedSelf(userParameter);
			if (Common.isEmpty(userModel)) {
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
     * 后台用户更新保存
     */
   // @SystemControllerLog(description = "后台用户更新保存")
    @RequestMapping(value = "/administratorUpdateSave", method = RequestMethod.POST)
    public String administratorUpdateSave() throws Exception
    {
    	String systemUserId = getPara("systemUserId");
    	String userName = getPara("userName");
    	String accountName = getPara("accountName");
    	String locked = getPara("locked");
    	String pwd = getPara("pwd");
    	String roleId = getPara("roleId");
    	String description = getPara("description");
    	String orgId = getPara("orgId");
    	
    	SysUserModel userParameter = new SysUserModel();
    	userParameter.setId(Long.parseLong(systemUserId));
    	userParameter.setAccountName(accountName);
    	
		SysUserModel userModel = sysUserMapper.selectByAccountNameAndNotIncludedSelf(userParameter);
		if (Common.isEmpty(userModel)) {
			SysUserModel userAdd = new SysUserModel();
			userAdd.setId(Long.parseLong(systemUserId));
			userAdd.setUserName(userName);
			userAdd.setAccountName(accountName);
			if(Common.isNotEmpty(pwd)){
				String md5password = MethodUtil.getMD5(pwd, "UTF-8", 0);
				userAdd.setPwd(md5password);
			}
			userAdd.setDescription(description);
			userAdd.setLocked(Integer.parseInt(locked));
			if(orgId!=null && orgId.length()>0){
				userAdd.setOrgId(Long.parseLong(orgId));
			}
			sysUserService.updateAdministratorAndUserRole(userAdd, roleId);
		}
    
		return "redirect:administratorIndex";
    }
    
    /**
     * 后台用户删除
     */
   // @SystemControllerLog(description = "后台用户删除")
    @RequestMapping(value = "/administratorDelete", method = RequestMethod.POST)
    public String administratorDelete()
    {
        try
        {
        	String systemUserIds = getPara("systemUserIds");
        	sysUserService.deleteAdministratorAndUserRole(systemUserIds);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return "redirect:administratorIndex";
    }
    /**
     * 后台用户初始化
     */
   // @SystemControllerLog(description = "后台用户初始化)
    @RequestMapping(value = "/chushihua", method = RequestMethod.POST)
    public String chushihua()
    {
        try
        {
        	String systemUserIds = getPara("systemUserIds");
        	SysUserModel sysUserModel = sysUserMapper.selectByPrimaryKey(Long.parseLong(systemUserIds));
        	String pwd="123456";
        	if(Common.isNotEmpty(pwd)){
				String md5password = MethodUtil.getMD5(pwd, "UTF-8", 0);
				sysUserModel.setPwd(md5password);
			}
        	int result = sysUserMapper.updateByPrimaryKeySelective(sysUserModel);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return "redirect:administratorIndex";
    }
    /**
     * 后台用户分配权限页面
     */
    @RequestMapping(value = "/administratorPermissionsPage", method = RequestMethod.POST)
    public String administratorPermissionsPage(Model model)
    {
        try
        {
        	String systemUserId = getPara("systemUserId");
        	SysUserRoleModel sysUserRoleModel = sysUserRoleMapper.selectByUserId(Long.parseLong(systemUserId));
			if (!Common.isEmpty(sysUserRoleModel)) {
				SysUserModel sysUserModel = sysUserMapper.selectByPrimaryKey(Long.parseLong(systemUserId));
	        	SysRoleModel roleModel = sysRoleMapper.selectByPrimaryKey(sysUserRoleModel.getRoleId());
	        	List<TreeObject> treeObjectList = sysUserService.getRoleAndUserActionHaveResources(systemUserId);
	        	
	        	model.addAttribute("userModel", sysUserModel);
	        	model.addAttribute("roleModel", roleModel);
	        	model.addAttribute("menuList", treeObjectList);
        	}
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "admin/jsp/administrator/administratorPermissionsPage";
    }
    
    /**
     * 后台用户分配权限保存
     */
    //@SystemControllerLog(description = "后台用户分配权限保存")
    @RequestMapping(value = "/administratorPermissionsSave", method = RequestMethod.POST)
    public String administratorPermissionsSave(@RequestParam("resourcesId") String[] resourcesId) throws Exception
    {
    	if (!Common.isEmpty(resourcesId) && resourcesId.length > 0) 
    	{
    		String systemUserId = getPara("systemUserId");
        	sysUserService.addAdministratorUserAction(systemUserId, resourcesId);
    	}
        return "redirect:administratorIndex";
    }
    
    /**
     * 后台用户信息页面
     */
    @RequestMapping(value = "/administratorInfoPage", method = RequestMethod.GET)
    public String administratorInfoPage(Model model) throws Exception
    {
    	SysUserModel sysUserModel = getSessionUser();
    	SysUserRoleModel sysUserRoleModel = sysUserRoleMapper.selectByUserId(sysUserModel.getId());
		if (!Common.isEmpty(sysUserRoleModel)) {
        	SysRoleModel roleModel = sysRoleMapper.selectByPrimaryKey(sysUserRoleModel.getRoleId());
        	model.addAttribute("roleModel", roleModel);
    	}
		else
		{
			SysRoleModel roleModel = new SysRoleModel();
			roleModel.setName("未分配");
			model.addAttribute("roleModel", roleModel);
		}
    	model.addAttribute("administratorInfo", sysUserModel);
        return "admin/jsp/administrator/administratorInfoPage";
    }
    
    /**
     * 后台用户信息更新
     */
    //@SystemControllerLog(description = "后台用户信息更新")
    @RequestMapping(value = "/administratorInfoUpdate", method = RequestMethod.POST)
    @ResponseBody
    public String administratorInfoUpdate()
    {
    	try {
    		SysUserModel sysUserModel = getSessionUser();
        	String userName = getPara("userName");
        	String oldPwd = getPara("oldPwd");
        	String pwd = getPara("pwd");
        	String password = getPara("password");
        	String description = getPara("description");
        	
        	if(Common.isEmpty(userName))
        	{
        		return "userNameIsNull";
        	}
        	
        	if(Common.isNotEmpty(oldPwd)){
        		SysUserModel userModel = sysUserMapper.selectByPrimaryKey(sysUserModel.getId());
        		String md5oldPwd = MethodUtil.getMD5(oldPwd, "UTF-8", 0);
        		if(md5oldPwd.equals(userModel.getPwd())){
        			if(Common.isNotEmpty(pwd) && Common.isNotEmpty(password)){
        				if(pwd.equals(password))
        				{
        					String md5password = MethodUtil.getMD5(pwd, "UTF-8", 0);
            				userModel.setPwd(md5password);
            				userModel.setUserName(userName);
            				userModel.setDescription(description);
            				int result = sysUserMapper.updateByPrimaryKeySelective(userModel);
            				if(result > 0)
            				{
            					sysUserModel.setPwd(md5password);
            					sysUserModel.setUserName(userName);
            					sysUserModel.setDescription(description);
            					session.setAttribute("PCUserToken", sysUserModel);
            					redisService.setAttr(request, CommonConstant.SESSION_USER, sysUserModel);
            					return "success";
            				}
            				else
            				{
            					return "fail";
            				}
        				}
        				else
        				{
        					//俩次密码不正确
            				return "pwdOrPasswordIsErr";
        				}
        			}
        			else
        			{
        				//新密码确认新密码为空
        				return "pwdOrPasswordIsNull";
        			}
        		}
        		else
        		{
        			//原密码不正确
        			return "oldPwdIsErr";
        		}
        	}
        	else
        	{
        		sysUserModel.setUserName(userName);
        		sysUserModel.setDescription(description);
        		int result = sysUserMapper.updateByPrimaryKeySelective(sysUserModel);
				if(result > 0)
				{
	        		session.setAttribute("PCUserToken", sysUserModel);
	        		//SessionUtilRedis.setAttr(request, CommonConstant.SESSION_USER, sysUserModel);
	        		redisService.setAttr(request, CommonConstant.SESSION_USER, sysUserModel);
					return "success";
				}
				else
				{
					return "fail";
				}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "fail";
    }
    
}

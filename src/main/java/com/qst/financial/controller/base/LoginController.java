package com.qst.financial.controller.base;

import com.qst.financial.dao.mapper.base.SysUserMapper;
import com.qst.financial.modle.base.SysUserModel;
import com.qst.financial.redis.RedisService;
import com.qst.financial.service.base.SysResourcesService;
import com.qst.financial.util.common.Common;
import com.qst.financial.util.common.CommonConstant;
import com.qst.financial.util.common.MethodUtil;
import com.qst.financial.util.common.TreeObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/pc")
public class LoginController extends BaseController {
    
    private  static  final Logger log = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
	private SysUserMapper sysUserModelMapper;
    
    @Autowired
   	private SysResourcesService sysResourcesService;
    @Autowired
    private RedisService redisService;
    /**
     * 去登录页面
     */
    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public String toLogin(Model model, HttpSession session, HttpServletRequest request) throws Exception {
    	 return "admin/jsp/login";
    }
    /**
     * 登陆
     */
    //@SystemControllerLog(description = "登陆")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, HttpSession session, HttpServletRequest request) throws Exception {
		String accountName = request.getParameter("accountName");
        String password = request.getParameter("password");
        if(Common.isNotEmpty(password) && Common.isNotEmpty(accountName))
        {
        	log.info("账号:" + accountName + "登陆");
        	SysUserModel sysUserModel = sysUserModelMapper.selectByAccountName(accountName);
        	if(Common.isEmpty(sysUserModel))
            {
                model.addAttribute("message", "账号不存在");
                return "admin/jsp/login";
            }
            else
            {
				String md5password = MethodUtil.getMD5(password, "UTF-8", 0);
                String pwd = sysUserModel.getPwd();
                if (pwd.equals(md5password))
                {
                	if(sysUserModel.getLocked().intValue() == 1){
                		model.addAttribute("message", "您的账号已经被锁定,请联系管理员");
                        return "admin/jsp/login";
                	}
                	else
                	{
                		//String sessionId=SessionUtilRedis.generateKeyPrefix(request,CommonConstant.SESSION_USER);
                		//SessionUtilRedis.setAttr(request, CommonConstant.SESSION_USER, sysUserModel);
                		redisService.setAttr(request,CommonConstant.SESSION_USER, sysUserModel);
                        session.setAttribute("PCUserToken", sysUserModel);
//                        return "redirect:index";
                        return "redirect:/client/zcfz/table";
                	}
                }
                else
                {
                    model.addAttribute("message", "账号密码不正确");
                    return "admin/jsp/login";
                }
            }
        }
        else
        {
            model.addAttribute("message", "请输入账号与密码");
            return "admin/jsp/login";
        }
	}
    
    /**
     * 首页
     */
    //@SystemControllerLog(description = "登陆成功访问首页")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpSession session) throws Exception {
    	Object bean = redisService.getAttrAsObject(request, CommonConstant.SESSION_USER);
		SysUserModel systemUser = (SysUserModel) bean;
    	List<TreeObject> menuInitList = new ArrayList<TreeObject>();
    	//获取菜单
		if ("1".equals(systemUser.getAccountType().toString())) {
		    //普通权限
			menuInitList = sysResourcesService.getUserMenuActionAuthority(systemUser.getId().toString());
		} else {
		    //最高管理员root
			menuInitList = sysResourcesService.getRootUserMenuActionAuthority();
		}

		session.setAttribute("menuInitList", menuInitList);
		return "admin/jsp/index";
    }
    
    /**
     * 退出
     */
    //@SystemControllerLog(description = "退出")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) throws Exception {
    	//SessionUtilRedis.removeAttr(request, CommonConstant.SESSION_USER);
    	redisService.removeAttr(request, CommonConstant.SESSION_USER);
    	session.removeAttribute("PCUserToken");
    	model.addAttribute("message", "您已安全退出");
        return "admin/jsp/login";
    }
    
    /**
     * 登陆页面
     */
    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String loginPage() throws Exception {
    	
        return "admin/jsp/login";
    }
    
}

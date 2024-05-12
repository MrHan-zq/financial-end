package com.qst.financial.controller.base;

import com.qst.financial.controller.api.TBasiAssetsAndLiabilitiesApiController;
import com.qst.financial.dao.mapper.base.SysUserMapper;
import com.qst.financial.modle.base.SysUserModel;
import com.qst.financial.redis.RedisService;
import com.qst.financial.service.base.SysResourcesService;
import com.qst.financial.util.common.Common;
import com.qst.financial.util.common.CommonConstant;
import com.qst.financial.util.common.MethodUtil;
import com.qst.financial.util.common.TreeObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/client")
public class ClientController extends BaseController {

    @Autowired
    private SysUserMapper sysUserModelMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private SysResourcesService sysResourcesService;

    @Autowired
    private TBasiAssetsAndLiabilitiesApiController tBasiAssetsAndLiabilitiesApiController;

    @GetMapping(value = "/to/login")
    public String clientIndex() {
        return "client/client_login";
    }

    @PostMapping(value = "/login")
    public String login(Model model, HttpSession session, HttpServletRequest request) throws Exception {
        String accountName = request.getParameter("accountName");
        String password = request.getParameter("password");
        if (Common.isNotEmpty(password) && Common.isNotEmpty(accountName)) {
            log.info("账号:" + accountName + "登陆");
            SysUserModel sysUserModel = sysUserModelMapper.selectByAccountName(accountName);
            if (Common.isEmpty(sysUserModel)) {
                model.addAttribute("message", "账号不存在");
                return "client/client_login";
            } else {
                String md5password = MethodUtil.getMD5(password, "UTF-8", 0);
                String pwd = sysUserModel.getPwd();
                if (pwd.equals(md5password)) {
                    if (sysUserModel.getLocked().intValue() == 1) {
                        model.addAttribute("message", "您的账号已经被锁定,请联系管理员");
                        return "client/client_login";
                    } else {
                        //String sessionId=SessionUtilRedis.generateKeyPrefix(request,CommonConstant.SESSION_USER);
                        //SessionUtilRedis.setAttr(request, CommonConstant.SESSION_USER, sysUserModel);
                        redisService.setAttr(request, CommonConstant.SESSION_USER, sysUserModel);
                        session.setAttribute("PCUserToken", sysUserModel);
                        return "redirect:/client/zcfz/table";
                    }
                } else {
                    model.addAttribute("message", "账号密码不正确");
                    return "client/client_login";
                }
            }
        } else {
            model.addAttribute("message", "请输入账号与密码");
            return "client/client_login";
        }
    }

//    @RequestMapping(value = "/clientIndex", method = RequestMethod.GET)
//    public String index(HttpSession session) throws Exception {
//        Object bean = redisService.getAttrAsObject(request, CommonConstant.SESSION_USER);
//        SysUserModel systemUser = (SysUserModel) bean;
//        List<TreeObject> menuInitList = new ArrayList<TreeObject>();
//        //获取菜单
//        if ("1".equals(systemUser.getAccountType().toString())) {
//            //普通权限
//            menuInitList = sysResourcesService.getUserMenuActionAuthority(systemUser.getId().toString());
//        } else {
//            //最高管理员root
//            menuInitList = sysResourcesService.getRootUserMenuActionAuthority();
//        }
//
//        session.setAttribute("menuInitList", menuInitList);
//        return "client/client_index";
//    }

    @RequestMapping(value = "/zcfz/table", method = RequestMethod.GET)
    public String zcfzTable(){
        return "client/zcfz_table";
    }

    @RequestMapping(value = "/zcfz/lr/table", method = RequestMethod.GET)
    public String lrTable(){
        return "client/lr_table";
    }

    @RequestMapping(value = "/zcfz/xjl/table", method = RequestMethod.GET)
    public String xjlTable(){
        return "client/xjl_table";
    }

    @RequestMapping(value = "/zcfz/table/list", method = RequestMethod.GET)
    public String zcfzListTable(){
        tBasiAssetsAndLiabilitiesApiController
        return "client/zcfz_table_list";
    }

    @RequestMapping(value = "/zcfz/lr/table/list", method = RequestMethod.GET)
    public String lrListTable(){
        return "client/lr_table_list";
    }

    @RequestMapping(value = "/zcfz/xjl/table/list", method = RequestMethod.GET)
    public String xjlListTable(){
        return "client/xjl_table_list";
    }
}

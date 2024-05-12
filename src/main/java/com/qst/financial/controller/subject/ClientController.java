package com.qst.financial.controller.subject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.qst.financial.controller.api.*;
import com.qst.financial.controller.base.BaseController;
import com.qst.financial.dao.mapper.base.SysUserMapper;
import com.qst.financial.modle.base.SysUserModel;
import com.qst.financial.redis.RedisService;
import com.qst.financial.service.base.SysResourcesService;
import com.qst.financial.util.common.Common;
import com.qst.financial.util.common.CommonConstant;
import com.qst.financial.util.common.DateUtil;
import com.qst.financial.util.common.MethodUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

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

    @Autowired
    private CashFlowApiController cashFlowApiController;

    @Autowired
    private ProfitApiController profitApiController;

    @Autowired
    private TModeApiController tModeApiController;

    @Autowired
    private DetailController detailController;

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
    public String zcfzTable() {
        return "client/zcfz_table";
    }

    @RequestMapping(value = "/lr/table", method = RequestMethod.GET)
    public String lrTable() {
        return "client/lr_table";
    }

    @RequestMapping(value = "/xjl/table", method = RequestMethod.GET)
    public String xjlTable() {
        return "client/xjl_table";
    }

    @RequestMapping(value = "/zcfz/table/fx", method = RequestMethod.GET)
    public String zcfzTableFx(String startTime, String endTime, HttpServletResponse response, HttpServletRequest request, Model model) {
        SysUserModel user = getSessionUser();
        long orgId = Objects.isNull(user.getOrgId()) ? 8 : user.getOrgId();
        startTime = StringUtils.isBlank(startTime) ? DateUtil.dateToString(new Date()) : startTime;
        endTime = StringUtils.isBlank(endTime) ? DateUtil.dateToString(new Date()) : endTime;
        String totalTbasiAssets = tBasiAssetsAndLiabilitiesApiController.getTotalTbasiAssets(Long.toString(orgId), startTime.substring(0, 7), endTime.substring(0, 7), "1", response, request);
        log.info("totalTbasiAssets={}", totalTbasiAssets);
        JSONObject jsonObject1 = JSON.parseObject(totalTbasiAssets);
        Map map1 = new HashMap<>();
        if (jsonObject1.get("resultCode").toString().equals("200")) {
            JSONObject data = jsonObject1.getJSONObject("data");
            if (data != null) {
                map1 = JSON.parseObject(JSON.toJSONString(data), Map.class);
            }
        }
        Map<String, String> map = getContentMsgsShouye(startTime.substring(0, 7), endTime.substring(0, 7), Long.toString(orgId), "1");
        model.addAttribute("info", map);
        model.addAttribute("data", map1);
        return "client/zcfz_table_fx";
    }


    @RequestMapping(value = "/lr/table/fx", method = RequestMethod.GET)
    public String lrTableFx(String startTime, String endTime, HttpServletResponse response, HttpServletRequest request, Model model) {
        SysUserModel user = getSessionUser();
        long orgId = Objects.isNull(user.getOrgId()) ? 8 : user.getOrgId();
        startTime = StringUtils.isBlank(startTime) ? DateUtil.dateToString(new Date()) : startTime;
        endTime = StringUtils.isBlank(endTime) ? DateUtil.dateToString(new Date()) : endTime;
        String profitMainMsg = profitApiController.getProfitMainMsg(startTime.substring(0, 7), endTime.substring(0, 7), Long.toString(orgId), response, request);
        log.info("profitMainMsg={}", profitMainMsg);
        JSONObject jsonObject1 = JSON.parseObject(profitMainMsg);
        Map map1 = new HashMap<>();
        if (jsonObject1.get("resultCode").toString().equals("200")) {
            JSONObject data = jsonObject1.getJSONObject("data");
            if (data != null) {
                map1 = JSON.parseObject(JSON.toJSONString(data), Map.class);
            }
        }
        Map<String, String> map = getContentMsgsShouye(startTime.substring(0, 7), endTime.substring(0, 7), Long.toString(orgId), "1");
        model.addAttribute("info", map);
        model.addAttribute("data", map1);
        return "client/lr_table_fx";
    }

    @RequestMapping(value = "/xjl/table/fx", method = RequestMethod.GET)
    public String xjlTableFx(String startTime, String endTime, HttpServletResponse response, HttpServletRequest request, Model model) {
        SysUserModel user = getSessionUser();
        long orgId = Objects.isNull(user.getOrgId()) ? 8 : user.getOrgId();
        startTime = StringUtils.isBlank(startTime) ? DateUtil.dateToString(new Date()) : startTime;
        endTime = StringUtils.isBlank(endTime) ? DateUtil.dateToString(new Date()) : endTime;
        String cashFlowMainMsg = cashFlowApiController.getCashFlowMainMsg(Long.toString(orgId), startTime.substring(0, 7), endTime.substring(0, 7));
        log.info("cashFlowMainMsg={}", cashFlowMainMsg);
        JSONObject jsonObject1 = JSON.parseObject(cashFlowMainMsg);
        Map map1 = new HashMap<>();
        if (jsonObject1.get("resultCode").toString().equals("200")) {
            JSONObject data = jsonObject1.getJSONObject("data");
            if (data != null) {
                map1 = JSON.parseObject(JSON.toJSONString(data), Map.class);
            }
        }
        Map<String, String> map = getContentMsgsShouye(startTime.substring(0, 7), endTime.substring(0, 7), Long.toString(orgId), "1");
        model.addAttribute("info", map);
        model.addAttribute("data", map1);
        return "client/xjl_table_fx";
    }

    @RequestMapping(value = "/bl/table/fx", method = RequestMethod.GET)
    public String blTableFx() {
        return "client/bl_table_fx";
    }

    @RequestMapping(value = "/bl/table/fx/list", method = RequestMethod.GET)
    public String blTableFxList(String startTime, String endTime, HttpServletResponse response, HttpServletRequest request, Model model) {
        SysUserModel user = getSessionUser();
        long orgId = Objects.isNull(user.getOrgId()) ? 8 : user.getOrgId();
        String bilv = detailController.bilv(Long.toString(orgId), startTime.substring(0, 7), endTime.substring(0, 7));
        log.info("bilv={}", bilv);
        JSONObject jsonObject = JSON.parseObject(bilv);
        List<Map> list = Lists.newArrayList();
        if (jsonObject.get("resultCode").toString().equals("200")) {
            JSONObject data = jsonObject.getJSONObject("data");
            if (data != null) {
                List<Map> list1 = Optional.ofNullable(data.getJSONObject("cqcznl")).map(fz -> fz.getJSONArray("xq"))
                        .map(e -> JSONArray.parseArray(JSON.toJSONString(e), Map.class)).orElse(new ArrayList<>());
                List<Map> list2 = Optional.ofNullable(data.getJSONObject("yynl")).map(fz -> fz.getJSONArray("xq"))
                        .map(e -> JSONArray.parseArray(JSON.toJSONString(e), Map.class)).orElse(new ArrayList<>());
                List<Map> list3 = Optional.ofNullable(data.getJSONObject("dqcznl")).map(fz -> fz.getJSONArray("xq"))
                        .map(e -> JSONArray.parseArray(JSON.toJSONString(e), Map.class)).orElse(new ArrayList<>());
                List<Map> list4 = Optional.ofNullable(data.getJSONObject("ylnl")).map(fz -> fz.getJSONArray("xq"))
                        .map(e -> JSONArray.parseArray(JSON.toJSONString(e), Map.class)).orElse(new ArrayList<>());
                list.addAll(list1);
                list.addAll(list2);
                list.addAll(list3);
                list.addAll(list4);
            }
        }
        model.addAttribute("dataList", list);
        return "client/bl_table_fx_list";
    }
    @RequestMapping(value = "/zcfz/table/list", method = RequestMethod.GET)
    public String zcfzListTable(String startTime, String endTime, HttpServletResponse response, HttpServletRequest request, Model model) {
        SysUserModel user = getSessionUser();
        long orgId = Objects.isNull(user.getOrgId()) ? 8 : user.getOrgId();
        String reslut = tBasiAssetsAndLiabilitiesApiController.getDetailTbasiAssets(Long.toString(orgId), startTime.substring(0, 7), endTime.substring(0, 7), null, response, request);
        log.info("reslut={}", reslut);
        JSONObject jsonObject = JSON.parseObject(reslut);
        List<Map> list = Lists.newArrayList();
        if (jsonObject.get("resultCode").toString().equals("200")) {
            JSONObject data = jsonObject.getJSONObject("data");
            if (data != null) {
                List<Map> list1 = Optional.ofNullable(data.getJSONObject("fz")).map(fz -> fz.getJSONObject("gdfz").getJSONArray("xq"))
                        .map(e -> JSONArray.parseArray(JSON.toJSONString(e), Map.class)).orElse(new ArrayList<>());
                List<Map> list2 = Optional.ofNullable(data.getJSONObject("fz")).map(fz -> fz.getJSONObject("ldfz").getJSONArray("xq"))
                        .map(e -> JSONArray.parseArray(JSON.toJSONString(e), Map.class)).orElse(new ArrayList<>());
                List<Map> list3 = Optional.ofNullable(data.getJSONObject("qy")).map(fz -> fz.getJSONObject("qy").getJSONArray("xq"))
                        .map(e -> JSONArray.parseArray(JSON.toJSONString(e), Map.class)).orElse(new ArrayList<>());
                List<Map> list4 = Optional.ofNullable(data.getJSONObject("zc")).map(fz -> fz.getJSONObject("ldzc").getJSONArray("xq"))
                        .map(e -> JSONArray.parseArray(JSON.toJSONString(e), Map.class)).orElse(new ArrayList<>());
                List<Map> list5 = Optional.ofNullable(data.getJSONObject("zc")).map(fz -> fz.getJSONObject("gdzc").getJSONArray("xq"))
                        .map(e -> JSONArray.parseArray(JSON.toJSONString(e), Map.class)).orElse(new ArrayList<>());
                Map map = Optional.ofNullable(data.getJSONObject("fzandqy")).map(fz -> fz.getJSONObject("total"))
                        .map(e -> JSON.parseObject(JSON.toJSONString(e), Map.class)).orElse(new HashMap<>());
                list.addAll(list1);
                list.addAll(list2);
                list.addAll(list3);
                list.addAll(list4);
                list.addAll(list5);
                list.add(map);
            }
        }
        model.addAttribute("dataList", list);
        log.info("dataList={}", JSON.toJSONString(list));
        return "client/zcfz_table_list";
    }

    @RequestMapping(value = "/lr/table/list", method = RequestMethod.GET)
    public String lrListTable(String startTime, String endTime, HttpServletResponse response, HttpServletRequest request, Model model) {
        SysUserModel user = getSessionUser();
        long orgId = Objects.isNull(user.getOrgId()) ? 8 : user.getOrgId();
        String result = profitApiController.getProfitDetailMsg(startTime.substring(0, 7), endTime.substring(0, 7), Long.toString(orgId), response, request);
        log.info("result={}", result);
        JSONObject jsonObject = JSON.parseObject(result);
        List<Map> list = Lists.newArrayList();
        if (jsonObject.get("resultCode").toString().equals("200")) {
            JSONObject data = jsonObject.getJSONObject("data");
            if (data != null) {
                List<Map> list1 = Optional.ofNullable(data.getJSONObject("jlr")).map(fz -> fz.getJSONArray("xq"))
                        .map(e -> JSONArray.parseArray(JSON.toJSONString(e), Map.class)).orElse(new ArrayList<>());
                List<Map> list2 = Optional.ofNullable(data.getJSONObject("lrze")).map(fz -> fz.getJSONArray("xq"))
                        .map(e -> JSONArray.parseArray(JSON.toJSONString(e), Map.class)).orElse(new ArrayList<>());
                List<Map> list3 = Optional.ofNullable(data.getJSONObject("yysr")).map(fz -> fz.getJSONArray("xq"))
                        .map(e -> JSONArray.parseArray(JSON.toJSONString(e), Map.class)).orElse(new ArrayList<>());
                List<Map> list4 = Optional.ofNullable(data.getJSONObject("yycb")).map(fz -> fz.getJSONArray("xq"))
                        .map(e -> JSONArray.parseArray(JSON.toJSONString(e), Map.class)).orElse(new ArrayList<>());
                List<Map> list5 = Optional.ofNullable(data.getJSONObject("yylr")).map(fz -> fz.getJSONArray("xq"))
                        .map(e -> JSONArray.parseArray(JSON.toJSONString(e), Map.class)).orElse(new ArrayList<>());
                list.addAll(list1);
                list.addAll(list2);
                list.addAll(list3);
                list.addAll(list4);
                list.addAll(list5);
            }
        }
        model.addAttribute("dataList", list);
        return "client/lr_table_list";
    }

    @RequestMapping(value = "/xjl/table/list", method = RequestMethod.GET)
    public String xjlListTable(String startTime, String endTime, HttpServletResponse response, HttpServletRequest request, Model model) {
        SysUserModel user = getSessionUser();
        long orgId = Objects.isNull(user.getOrgId()) ? 8 : user.getOrgId();
        String detailMsg = cashFlowApiController.getCashFlowDetailMsg(startTime.substring(0, 7), endTime.substring(0, 7), null, Long.toString(orgId), response, request);
        log.info("detailMsg={}", detailMsg);
        JSONObject jsonObject = JSON.parseObject(detailMsg);
        List<Map> list = Lists.newArrayList();
        if (jsonObject.get("resultCode").toString().equals("200")) {
            JSONObject data = jsonObject.getJSONObject("data");
            if (data != null) {
                Map map1 = JSON.parseObject(JSON.toJSONString(data.getJSONObject("hlv")), Map.class);
                Map map2 = JSON.parseObject(JSON.toJSONString(data.getJSONObject("xjjdjw")), Map.class);
                Map map3 = JSON.parseObject(JSON.toJSONString(data.getJSONObject("qcxianj")), Map.class);
                Map map4 = JSON.parseObject(JSON.toJSONString(data.getJSONObject("qmxjdjw")), Map.class);
                List<Map> list1 = Optional.ofNullable(data.getJSONObject("tz")).map(fz -> fz.getJSONObject("tzhdlr").getJSONArray("xq"))
                        .map(e -> JSONArray.parseArray(JSON.toJSONString(e), Map.class)).orElse(new ArrayList<>());
                List<Map> list2 = Optional.ofNullable(data.getJSONObject("tz")).map(fz -> fz.getJSONObject("tzhdlc").getJSONArray("xq"))
                        .map(e -> JSONArray.parseArray(JSON.toJSONString(e), Map.class)).orElse(new ArrayList<>());
                List<Map> list3 = Optional.ofNullable(data.getJSONObject("tz")).map(fz -> fz.getJSONArray("alltotal"))
                        .map(e -> JSONArray.parseArray(JSON.toJSONString(e), Map.class)).orElse(new ArrayList<>());
                List<Map> list4 = Optional.ofNullable(data.getJSONObject("cz")).map(fz -> fz.getJSONObject("czhdlr").getJSONArray("xq"))
                        .map(e -> JSONArray.parseArray(JSON.toJSONString(e), Map.class)).orElse(new ArrayList<>());
                List<Map> list5 = Optional.ofNullable(data.getJSONObject("cz")).map(fz -> fz.getJSONArray("alltotal"))
                        .map(e -> JSONArray.parseArray(JSON.toJSONString(e), Map.class)).orElse(new ArrayList<>());
                List<Map> list9 = Optional.ofNullable(data.getJSONObject("cz")).map(fz -> fz.getJSONObject("czhdlc").getJSONArray("xq"))
                        .map(e -> JSONArray.parseArray(JSON.toJSONString(e), Map.class)).orElse(new ArrayList<>());

                List<Map> list6 = Optional.ofNullable(data.getJSONObject("jy")).map(fz -> fz.getJSONObject("jyhdlc").getJSONArray("xq"))
                        .map(e -> JSONArray.parseArray(JSON.toJSONString(e), Map.class)).orElse(new ArrayList<>());
                List<Map> list7 = Optional.ofNullable(data.getJSONObject("jy")).map(fz -> fz.getJSONObject("jyhdlr").getJSONArray("xq"))
                        .map(e -> JSONArray.parseArray(JSON.toJSONString(e), Map.class)).orElse(new ArrayList<>());
                List<Map> list8 = Optional.ofNullable(data.getJSONObject("jy")).map(fz -> fz.getJSONArray("alltotal"))
                        .map(e -> JSONArray.parseArray(JSON.toJSONString(e), Map.class)).orElse(new ArrayList<>());
                list.addAll(list1);
                list.addAll(list2);
                list.addAll(list3);
                list.addAll(list4);
                list.addAll(list5);
                list.addAll(list6);
                list.addAll(list7);
                list.addAll(list8);
                list.addAll(list9);
                list.add(map1);
                list.add(map2);
                list.add(map3);
                list.add(map4);
            }
        }
        model.addAttribute("dataList", list);
        log.info("dataList={}", JSON.toJSONString(list));
        return "client/xjl_table_list";
    }

    private Map<String, String> getContentMsgsShouye(String startTime, String endTime, String orgId, String type) {
        Map<String, String> map = new HashMap<>();
        String result = tModeApiController.getContentMsgsShouye(startTime,endTime,orgId,type);
        log.info("result={}", result);
        JSONObject jsonObject = JSON.parseObject(result);
        if (jsonObject.get("resultCode").toString().equals("200")) {
            JSONObject data = jsonObject.getJSONObject("data");
            if (data != null) {
                String gy = Optional.ofNullable(data.getJSONArray("gy")).map(e -> JSONArray.parseArray(JSON.toJSONString(e), String.class))
                        .map(e -> CollectionUtils.isEmpty(e) ? "" : e.get(0)).orElse(null);
                String yj = Optional.ofNullable(data.getJSONArray("yj")).map(e -> JSONArray.parseArray(JSON.toJSONString(e), String.class))
                        .map(e -> CollectionUtils.isEmpty(e) ? "" : e.get(0)).orElse(null);
                map.put("gy", gy);
                map.put("yj", yj);
            }
        }
        return map;
    }
}

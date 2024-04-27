package com.qst.financial.controller.api;

import com.qst.financial.annotation.AppControllerLog;
import com.qst.financial.controller.base.BaseController;
import com.qst.financial.dao.mapper.base.SysUserMapper;
import com.qst.financial.modle.base.SysUserModel;
import com.qst.financial.util.common.Common;
import com.qst.financial.util.common.DateUtil;
import com.qst.financial.util.common.MethodUtil;
import com.qst.financial.util.common.ResultCode;
import com.qst.financial.util.json.ResultObj;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import com.qst.financial.redis.RedisService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录接口
 * @author yj
 *
 */
@Controller
@RequestMapping("/api/login")
public class LonginController extends BaseController {
	private static final Logger log=Logger.getLogger(LonginController.class);
	@Autowired
	private SysUserMapper sysUserModelMapper;
	@Autowired
	private RedisService redisService;
	@AppControllerLog(description = "用户登录")
	@ApiOperation(value = "用户登录{参数内容类型:application/json}", notes = "用户名密码", response = ResultObj.class, httpMethod = "POST")
	@RequestMapping(value="/login.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
    public String login(@ApiParam(required = true, value = "userName(用户名),passWord(密码)") String userName,String passWord, 
    		HttpServletRequest request) throws Exception {
        if(Common.isNotEmpty(userName) && Common.isNotEmpty(passWord))
        {
        	log.info("账号:" + userName + "登陆");
        	SysUserModel sysUserModel = sysUserModelMapper.selectByAccountName(userName);
        	if(Common.isEmpty(sysUserModel))
            {
        		ResultObj robj = new ResultObj(ResultCode.RESULT_NULL, "账号密码不存在");
    			return robj.toString();
            }
            else
            {
				String md5password = passWord;
                String pwd = sysUserModel.getPwd();
                if (pwd.equals(md5password))
                {
                	if(sysUserModel.getLocked().intValue() == 1){
                		ResultObj robj = new ResultObj(ResultCode.RESULT_NULL, "账号已被锁，请联系管理员！");
            			return robj.toString();
                	}
                	else
                	{
                		Date dt = new Date();
    					String strDt = DateUtil.dateFormatToString(dt, "yyyyMMddHHmmss");
    					StringBuffer sb = new StringBuffer(strDt + "-");
    					sb.append(String.valueOf(sysUserModel.getId()));
    					String tokens = MethodUtil.getJwtToken(sb.toString());
    					redisService.setAttr(request,tokens, sysUserModel.getId());
    					Map<String, Object> map = new HashMap<String, Object>();
    	    			map.put("tokens", tokens);
    	    			map.put("sysUserModel", sysUserModel);
    	    			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "登陆成功", map);
    	    			return robj.toString();
                	}
                }
                else
                {
                	ResultObj robj = new ResultObj(ResultCode.RESULT_NULL, "账号密码不正确！");
        			return robj.toString();
                }
            }
        }
        else
        {
        	ResultObj robj = new ResultObj(ResultCode.RESULT_NULL, "账号或密码为空！");
			return robj.toString();
        }
	}
}

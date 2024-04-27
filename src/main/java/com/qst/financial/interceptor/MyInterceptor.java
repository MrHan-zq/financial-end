package com.qst.financial.interceptor;

import com.qst.financial.redis.RedisService;
import com.qst.financial.util.common.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.List;


//@Configuration
//Component
public class MyInterceptor implements HandlerInterceptor{
	private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
	private List<String> excludeUrls;
	@Autowired
	private RedisService redisService;
	//private RedisServiceImpl redisService = (RedisServiceImpl) SpringContextUtil.getBean("RedisServiceImpl");
	//RedisService redisService = ApplicationContext.getBean(RedisService.class);  
	//private static RedisServiceImpl redisutil = (RedisServiceImpl) ContextLoader.getCurrentWebApplicationContext().getBean("RedisServiceImpl");
	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}
	/**
	 * 完成页面的render后调用
	 */
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception exception)
			throws Exception {

	}

	/**
	 * 在调用controller具体方法后拦截
	 */
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object object,
			ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 在调用controller具体方法前拦截
	 */
	@SuppressWarnings({ "rawtypes" })
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object object) throws Exception {
		
			String path = request.getContextPath();
			String temp = request.getServerPort() == 80 ? "" : ":"
					+ request.getServerPort();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + temp + path ;
			String requestUri = request.getRequestURI();
			String contextPath = request.getContextPath();
			String url = requestUri.substring(contextPath.length());
			logger.info("url:"+basePath+requestUri);
			// 获得所有请求参数名
			Enumeration params = request.getParameterNames();
			while (params.hasMoreElements()) {
				// 得到参数名
				String name = params.nextElement().toString();
				// 得到参数对应值
				String[] value = request.getParameterValues(name);
				for (int i = 0; i < value.length; i++) {
					String param = value[i];
					value[i] = praseHtml(param);
					if(param != null && (param.indexOf("<")>0||param.indexOf("<")==0) && (param.indexOf(">")>0||param.indexOf(">")==0)){
						 response.sendRedirect(basePath+"/error/404.jsp");
					}
				}
			}
			
			//redisService.set("sss", "sssa");
			//System.out.println("======================"+redisService.getStr("sss"));
		    if (handleFormRepeat(request, object)) {
		    	//重复提交操作 代码 此处写
				logger.info("表单重复提交.........");
			}
		    //共享按摩椅如果输入ip进入首页
		    /*if(url.equals("/error")){
		    	response.sendRedirect(response.encodeURL(basePath+"/page/admin/jsp/indexs/index.jsp"));
		    	return false;
		    }*/
		    
		    Object users=redisService.getAttrAsObject(request, CommonConstant.SESSION_USER);
		    logger.info("===================");
		    //一般登录情况
			if (users == null) {
				logger.debug("拦截"+url + "用户session失效，强制退出");
				//获取原地址，登录成功后跳回
				String referer = "";
				if(request.getHeader("referer") != null && !request.getHeader("referer").equals("")){
					referer = request.getHeader("referer").toString();
							
					request.setAttribute("referer", request.getHeader("referer").toString());
				}
						 
				if (request.getHeader("x-requested-with") != null&& request.getHeader("x-requested-with").equals("XMLHttpRequest")) { // ajax请求
					response.setHeader("sessionstatus", "timeout");
					response.setHeader("referer", referer);
				}else{
					// request.getRequestDispatcher("/page/common/login.jsp").forward(request, response);
					response.sendRedirect(response.encodeURL(basePath+"/page/admin/jsp/login.jsp"));
	
				}			
				return false;
				
		    }
			return true;
	}


	/**
	 * HTML标签转换
	 * 
	 * @param html
	 * @return
	 */
	protected static String praseHtml(String html) {
		html = html.replaceAll("<", "&lt;");
		html = html.replaceAll(">", "&gt;");
		html = html.replaceAll("\"", "&quot;");
		return html;
	}
	
	private boolean isRepeatSubmit(HttpServletRequest request) {
		//获取页面中的token
		 String clinetToken = (String) request.getParameter("tokenName");
		 //获取session中的token
//        String serverToken = (String) request.getSession(false).getAttribute("token");
       // String serverToken = (String)SessionUtil.getAttr(request, "token");
        if (null == null) {
            return true;
        }
        if (clinetToken == null) {
            return true;
        }
        if (!false) {
            return true;
        }
        return false;
    }
	
	
	private boolean handleFormRepeat(HttpServletRequest request,Object object){
		boolean flag = false;
		try {
			if (object != null) {
			HandlerMethod handlerMethod = (HandlerMethod) object;
			Method method = handlerMethod.getMethod();
			//AvoidDuplicateSubmission annotation = method.getAnnotation(AvoidDuplicateSubmission.class);
			if (null != null) {
				//boolean needValidateToken = annotation.needValidateToken();
				if (true) {
					if (isRepeatSubmit(request)) {
						// 重复提交操作。。。
						logger.info("【表单重复提交】");
						flag =  true;
					}
//					request.getSession(false).removeAttribute("token");
				}
			}
		}else {
			flag = false;
		}
		} catch (Exception e) {
			logger.info(e.toString());
			flag = false;
		}
		return flag;
	}
	
	@SuppressWarnings("unused")
	private void writer(HttpServletResponse response, String str) {
		try {
			// 设置页面不缓存
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter out = null;
			out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

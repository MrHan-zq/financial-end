package com.qst.financial.util.power.spring;

import org.apache.log4j.Logger;
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


/**
 * 
 * @author qst
 * 
 * @类说明：权限校验拦截器
 */
public class SecurityInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(SecurityInterceptor.class);

//	@Autowired(required = false)
//	private IRedisUtil redisUtil;

	private List<String> excludeUrls;

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
			
			String requestUri = request.getRequestURI();
			String contextPath = request.getContextPath();
			String url = requestUri.substring(contextPath.length());
		

			if (excludeUrls.contains(url)) {
				return true;
		    }else{
		    	if (handleFormRepeat(request, object)) {
		    		//重复提交操作 代码 此处写
					logger.info("表单重复提交.........");
				}
		    	//Object users= request.getSession().getAttribute(CommonConstant.SESSION_USER);
		    	//Object bean = SessionUtilRedis.getAttrAsObject(request, CommonConstant.SESSION_USER);
		    	Object bean =null;
		    	logger.info("===================");
		    	//一般登录情况
				if (bean == null) {
					    logger.debug("拦截"+url + "用户session失效，强制退出");
					    //获取原地址，登录成功后跳回
					    String referer = "";
						if(request.getHeader("referer") != null 
								 && !request.getHeader("referer").equals("")){
							
							referer = request.getHeader("referer").toString();
							
						    request.setAttribute("referer", request.getHeader("referer").toString());
						}
						 
						if (request.getHeader("x-requested-with") != null
						&& request.getHeader("x-requested-with").equals(
						"XMLHttpRequest")) { // ajax请求
						     response.setHeader("sessionstatus", "timeout");
						     response.setHeader("referer", referer);
						}else{
							// request.getRequestDispatcher("/page/common/login.jsp").forward(request, response);
							response.sendRedirect(response.encodeURL(basePath+"/page/admin/jsp/login.jsp"));
	
						}			
					    return false;
				}
				
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


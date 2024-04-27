package com.qst.financial.util.listener;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author qst
 * 
 * @类说明：过滤未登录用户直接通过走jsp页面访问资源
 */
public class JspPathFilter implements Filter {
	
	private final static Logger log = Logger.getLogger(JspPathFilter.class);
	
	@SuppressWarnings("unused")
	private FilterConfig filterConfig = null;

	public void init(FilterConfig config) throws ServletException {
		this.filterConfig = config;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException,
			IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getServletPath();
		String igpath = path.substring(path.lastIndexOf("/") + 1);
		//if (!igpath.equals("login.jsp")) {
			request.setAttribute("url", path);
			request.getRequestDispatcher("page/admin/login").forward(request, response);
		//}
	}

	public void destroy() {
		this.filterConfig = null;
	}
}

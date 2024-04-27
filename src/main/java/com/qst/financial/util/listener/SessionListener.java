package com.qst.financial.util.listener;

import com.qst.financial.dto.SysUserBean;
import com.qst.financial.util.common.CommonConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Collections;
import java.util.Map;



/**
 * 
 * @author qst
 * 
 * @类说明：当session过期时将用户从session列表中删除
 */
public class SessionListener implements HttpSessionListener {
	private static Logger log = Logger.getLogger(SessionListener.class);

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * 当session过期时将用户从session列表中删除
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void sessionDestroyed(HttpSessionEvent hse) {
		log.debug("SessionListener 将session列表中session过期用户删除启动");
		ServletContext sc = hse.getSession().getServletContext();
		Map<String, String> onlineUser = Collections.emptyMap();
		onlineUser = (Map<String, String>) sc.getAttribute(CommonConstant.SERVLETCONTEXT_ONLINE_USER);
		if (onlineUser == null) {
			return;
		}
		log.debug("SessionListener onlineUser() remove before:" + onlineUser);
		Object obj = hse.getSession().getAttribute(CommonConstant.SESSION_USER);
		if (obj == null) {
			return;
		}
		SysUserBean userBean = (SysUserBean) obj;
		log.debug("SessionListener onlineUser() userBean userName:" + userBean.getUserName());
		if (onlineUser.containsKey(userBean.getUserName())) {
			if (hse.getSession().getId().equals(onlineUser.get(userBean.getUserName()))) {
				onlineUser.remove(userBean.getUserName());
				log.debug("SessionListener 将session列表中session过期用户删除，用户为:" + onlineUser);
			}
		}
		sc.setAttribute(CommonConstant.SERVLETCONTEXT_ONLINE_USER, onlineUser);
		log.debug("SessionListener 将session列表中session过期用户删除结束");
	}
}

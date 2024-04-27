package com.qst.financial.controller.base;


import com.qst.financial.modle.base.SysResourcesModel;
import com.qst.financial.modle.base.SysUserModel;
import com.qst.financial.redis.RedisService;
import com.qst.financial.service.base.SysResourcesService;
import com.qst.financial.util.common.Common;
import com.qst.financial.util.common.CommonConstant;
import com.qst.financial.util.tag.PageUtil;
import com.qst.financial.util.tag.Pager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

//import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


/**
 * 
 * @内容
 * @FileName BaseController.java
 * @author yj
 * 
 */
public class BaseController {
	protected final Logger log = Logger.getLogger(BaseController.class);
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	@Autowired 
	private RedisService redisService;
	@Autowired
	private SysResourcesService sysResourcesService;
	/**
	 * 获取分页参数
	 * @param curPage 当前页数
	 * @param rowCount 数据总条数
	 * @return
	 */
	protected String getLimit(Integer curPage, Integer rowCount){
		PageUtil page = new PageUtil();
		page.setPageId(curPage);//当前页数
		page.setRowCount(rowCount);//数据总条数
		page.splitPageInstance();//生成实例
		return page.getLimit();//返回limit 
	}
	
	
	/**
	 * 初始化分页相关信息
	 * @param map
	 * @param curPage
	 * @param pageSize
	 * @param totalCount
	 */
	protected void initPage(Map<String, Object> map, Integer curPage,
			Integer pageSize, Integer totalCount) {
		if (null == pageSize || pageSize.equals("")) {
			pageSize = 10; // 后续配置获取
		}
		if (pageSize > 20) {
			pageSize = 20;
		}
		Integer totalPage = (totalCount + pageSize - 1) / pageSize;
		if (null == curPage) {
			curPage = 1;
		} else if (curPage > totalPage) {
			curPage = totalPage;
		}
		map.put("startIndex", Pager.getStartIndex(curPage, pageSize));
		map.put("totalPage", totalPage);
		map.put("pageSize", pageSize);
		map.put("totalCount", totalCount);
		map.put("curPage", curPage);
	}
	
	
	/**
	 * 初始化分页相关信息
	 * @param request
	 * @param curPage
	 * @param pageSize
	 * @param totalCount
	 */
	protected void initPage(HttpServletRequest request, Integer curPage,
			Integer pageSize, Integer totalCount) {
		if (null == pageSize || pageSize.equals("")) {
			pageSize = 10; // 后续配置获取
		}
		if (pageSize > 20) {
			pageSize = 20;
		}
		Integer totalPage = (totalCount + pageSize - 1) / pageSize;
		if (null == curPage) {
			curPage = 1;
		} else if (curPage > totalPage) {
			curPage = totalPage;
		}
		request.setAttribute("startIndex", Pager.getStartIndex(curPage, pageSize));
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("curPage", curPage);
	}

	/**
	 * 将相关数据放入model
	 * @param model
	 * @param list
	 * @param map
	 */
	@SuppressWarnings("rawtypes")
	protected void initResult(Model model, List list,
			Map<String, Object> map) {
		model.addAttribute("list", list);
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry m = (Map.Entry) it.next();
			model.addAttribute(m.getKey().toString(), m.getValue());
		}
	}
	
	/**
	 * 将相关数据放入model
	 * @param request
	 * @param list
	 * @param map
	 */
	@SuppressWarnings("rawtypes")
	protected void initResult(HttpServletRequest request, List list,
			Map<String, Object> map) {
		request.setAttribute("list", list);
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry m = (Map.Entry) it.next();
			request.setAttribute(m.getKey().toString(), m.getValue());
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void writeListData(List dataList, int totalCount,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		JSONArray jsonArray = JSONArray.fromObject(dataList);
		JSONObject json = new JSONObject();
		json.put("total", totalCount);
		json.put("data", jsonArray.toString());
		response.getWriter().write(json.toString());
	}
	
	/**
	 * 返回数据到前台
	 * @param dataList
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public void writeListData(List dataList, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		JSONArray jsonArray = JSONArray.fromObject(dataList);
		
		response.getWriter().write(jsonArray.toString());
	}
	/**
	 * 请求分发，每个请求都会进行初始化操作
	 * 用于进行参数的设置
	 * @param request
	 * @param response
	 */
	@ModelAttribute
	protected void inItParam(HttpServletRequest request,HttpServletResponse response){
		this.request = request;  
        this.response = response;  
        this.session = request.getSession(); 
	}
	/**
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected Map<String, String> getRequestParamMap(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String, String> remap = new HashMap<String, String>();
		try {
			Enumeration eretion = request.getParameterNames();
			while (eretion.hasMoreElements()) {
				String elem = (String) eretion.nextElement();
				String result = request.getParameter(elem);
				remap.put(elem, result);
			}
		} catch (Exception e) {
			// TODO: handle exception
			remap = new HashMap<String, String>();
			log.error("getRequestParamMap have exception:" + e);
		}
		return remap;
	}
	public void writeResult(boolean success, String message,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		JSONObject json = new JSONObject();
		json.put("success", success);
		json.put("msg", message);
		response.getWriter().write(json.toString());
	}	
	
	/**
	 * 获取HttpSession
	 */
	public HttpSession getSession() {
		HttpSession session = null;
		try {
			session = getRequest().getSession();
		} catch (Exception e) {
		}
		return session;
	}
	
	/**
	 * 获取HttpServletRequest
	 */
	public HttpServletRequest getRequest()
    {
        ServletRequestAttributes attrs = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }
	public HttpServletResponse getResponse()
    {
        ServletRequestAttributes attrs = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        return attrs.getResponse();
    }
	/**
     * 获取页面传递的某一个参数值
     */
    public String getPara(String key)
    {
        HttpServletRequest request =
            ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getParameter(key);
    }
    
	/**
	 * 获取Session用户Id
	 */
    public Long getSessionUserid()
    {
    	try
    	{
    		Object bean = redisService.getAttrAsObject(request, CommonConstant.SESSION_USER);
        	if(Common.isEmpty(bean))
        	{
        		return null;
        	}
    		SysUserModel systemUser = (SysUserModel) bean;
    		return systemUser.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
	 * 获取Session用户
	 */
    public SysUserModel getSessionUser()
    {
    	try 
    	{
    		Object bean = redisService.getAttrAsObject(request, CommonConstant.SESSION_USER);
        	if(Common.isEmpty(bean))
        	{
        		return null;
        	}
    		SysUserModel systemUser = (SysUserModel) bean;
    		return systemUser;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    public void getButtonList(String fatherId, String parentId){
    	SysUserModel systemUser = getSessionUser();
		Map<String,String> map = new HashMap<String, String>();
		map.put("parent_id", parentId);
		List<SysResourcesModel> buttonList = new ArrayList<SysResourcesModel>();
		if ("1".equals(systemUser.getAccountType().toString())) {
			map.put("user_id", systemUser.getId().toString());
			buttonList = sysResourcesService.selectUserMenuButton(map);
		} else {
			buttonList = sysResourcesService.selectRootUserMenuButton(map);
		}
		Map<String, String> maps=new HashMap<String,String>();
    	SysResourcesModel fatherModel=sysResourcesService.selectByPrimaryKey(Integer.parseInt(fatherId));
    	SysResourcesModel parentModel=sysResourcesService.selectByPrimaryKey(Integer.parseInt(parentId));
    	maps.put("fatherName", fatherModel.getName());
    	maps.put("parentName", parentModel.getName());
    	getSession().setAttribute("map", maps);
    	getSession().setAttribute("buttonList", buttonList);
    	getSession().setAttribute("fatherMenuId", fatherId);
    	getSession().setAttribute("menuId", parentId);
    	
    }
    /**
     * 返回得到的权限
     */
    public String getPower(){
    	SysUserModel sysUserModel=(SysUserModel) redisService.getAttrAsObject(request, CommonConstant.SESSION_USER);
    	int type=sysUserModel.getType();
    	String childType=sysUserModel.getChrildType();
    	if(type==0){             //所有权限
    		return null;
    	}else if(type==1){
    		return sysUserModel.getId()+"";
    	}else if(type==2){
    		return childType;
    	}
    	return null;
    }
   
}

package com.qst.financial.controller.subject;

import com.qst.financial.controller.base.BaseController;
import com.qst.financial.dao.mapper.base.SysResourcesMapper;
import com.qst.financial.dao.mapper.subject.TModeMapper;
import com.qst.financial.modle.base.SysResourcesModel;
import com.qst.financial.modle.base.SysUserModel;
import com.qst.financial.modle.subject.TMode;
import com.qst.financial.service.subject.TModeService;
import com.qst.financial.sql.C;
import com.qst.financial.sql.Method;
import com.qst.financial.sql.WherePrams;
import com.qst.financial.util.common.Common;
import com.qst.financial.util.tag.InnerPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 算法模型Controller
 */
@Controller
@RequestMapping("/mode")
public class ModeController extends BaseController{
	private Logger log=LoggerFactory.getLogger(ModeController.class);
	@Autowired
	private TModeService tModeService;
	@Autowired
   	private SysResourcesMapper sysResourcesMapper;
	@Autowired
	private TModeMapper tModeMapper;
	@RequestMapping(value = "/tModeIndex", method = RequestMethod.GET)
	public String modeIndex(String fatherId, String parentId,Model model,String pages) throws Exception{
		if (!Common.isEmpty(fatherId) && !Common.isEmpty(parentId))
        {
            HttpSession session = getSession();
            session.setAttribute("fatherMenuId", fatherId);
            session.setAttribute("menuId", parentId);
        }
	    return "admin/jsp/mode/tModeIndex";
	}
	
	/**
     * 文字配置列表与按钮列表
     */
    @RequestMapping(value = "/tModeListAndButtonList", method = RequestMethod.GET)
    public String systemDictionaryListAndButtonList(HttpServletRequest request, Model model)
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
            String pageNow="";
            if(session.getAttribute("tpage")!=null){
            	pageNow = session.getAttribute("tpage").toString();
            	session.setAttribute("tpage", null);
            }else{
            	pageNow = getPara("pageNow");
            }
            String pageSize = getPara("pageSize");
            if(Common.isEmpty(pageNow)){
            	pageNow = "1";
            }
            if(Common.isEmpty(pageSize)){
            	pageSize = "10";
            }
            Method method=new  Method();
            WherePrams wherePrams=method.where("1", C.EQ, 1);
            InnerPage innerPage=new InnerPage();
			TMode subject=new TMode();
            innerPage.setDate(subject);
            int count=(int) tModeService.count(wherePrams);
            initPage(request, Integer.parseInt(pageNow), Integer.parseInt(pageSize), count);
            String limit = getLimit(Integer.parseInt(pageNow), count);
        	List<TMode> tModeList=tModeService.listPage(wherePrams, limit);
			model.addAttribute("tModeList", tModeList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return "admin/jsp/mode/tModeListAndButtonList";
    }
    
	@RequestMapping(value = "/tModeList", method = RequestMethod.GET)
    public String modeList(HttpServletRequest request, Model model,String reportType){
		String veiw="admin/jsp/mode/tModeList";
		SysUserModel user=getSessionUser();
		try {
			String pageNow = getPara("pageNow");
            String pageSize = getPara("pageSize");
            if(Common.isEmpty(pageNow)){
            	pageNow = "1";
            }
            if(Common.isEmpty(pageSize)){
            	pageSize = "10";
            }
            Method method=new  Method();
            WherePrams wherePrams=method.where("1", C.EQ, 1);
            if(!Common.isEmpty(reportType)){
            	wherePrams.and("reportType", C.LIKE, reportType);
            	wherePrams.or("reportConten",  C.LIKE, reportType);
            	wherePrams.or("modeArea",  C.LIKE, reportType);
            }
            InnerPage innerPage=new InnerPage();
			TMode subject=new TMode();
            innerPage.setDate(subject);
            int count=(int) tModeService.count(wherePrams);
            initPage(request, Integer.parseInt(pageNow), Integer.parseInt(pageSize), count);
            String limit = getLimit(Integer.parseInt(pageNow), count);
        	List<TMode> tModeList=tModeService.listPage(wherePrams, limit);
			model.addAttribute("tModeList", tModeList);
		} catch (Exception e) {
			log.info("=====" + e.getMessage());
		}
		return veiw;
    }
	
	 /**
     * 文字配置增页面
     */
    @RequestMapping(value = "/addTMode", method = RequestMethod.GET)
    public String addTMode(Model model){
    	String pages = getPara("pages");
    	model.addAttribute("pages", pages);
		return "admin/jsp/mode/tModeAddPage";
    }
	
	@RequestMapping(value = "/tModeSave")
    public String  tModeSave(HttpServletRequest request, Model model,String reportType,String reportConten,String modeCondition,String modeContent,
    		String modeValues,String remark,String conditionValues,String modeArea,String pages,String px){
		int flag = 0;
		try{
			TMode subject=new TMode();
            //if(!Common.isEmpty(reportType) && !Common.isEmpty(modeCondition) && !Common.isEmpty(modeContent)&& !Common.isEmpty(modeValues) && !Common.isEmpty(conditionValues)){
            	subject.setReportType(reportType);
        		subject.setReportConten(reportConten);
            	subject.setModeCondition(modeCondition);
                subject.setModeContent(modeContent);
                subject.setModeValues(modeValues);
                subject.setRemark(remark);
                subject.setModeArea(modeArea);
                if(px!=null && px.length()>0){
                	subject.setPx(Integer.parseInt(px));
                }
                flag=tModeService.addLocal(subject);
           // }
        }catch (Exception e){
        	log.info("====="+e.getMessage());
        }
		HttpSession session = getSession();
        session.setAttribute("tpage", pages);
		return "redirect:tModeIndex";
    }
	
	/**
     * 编辑页面
     */
    @RequestMapping(value = "/tModeEditPage", method = RequestMethod.GET)
    public String tModeEditPage(Model model)
    {
        try{
        	String tModeId = getPara("tModeId");
        	TMode tMode = tModeMapper.selectByPrimaryKey(Long.parseLong(tModeId));
        	String pages = getPara("pages");
        	model.addAttribute("pages", pages);
        	model.addAttribute("tMode", tMode);
        }
        catch (Exception e){
            e.printStackTrace();
        }
		return "admin/jsp/mode/tModeEditPage";
    }
    
    /**
     * 更新保存
     */
    @RequestMapping(value = "/tModeUpdateSave", method = RequestMethod.POST)
    @ResponseBody
    public int   tModeUpdateSave(String tModeId,String reportType,String reportConten,String modeCondition,String modeContent,
    	String modeValues,String remark	,String conditionValues,String modeArea,String pages,String px) throws Exception{
    	/*String tModeId = getPara("tModeId");
    	String reportType = getPara("reportType");
    	String reportConten = getPara("reportConten");
    	String modeCondition = getPara("modeCondition");
    	String modeContent = getPara("modeContent");
    	String modeValues = getPara("modeValues");
    	String remark = getPara("remark");
    	String conditionValues = getPara("conditionValues");
    	String modeArea = getPara("modeArea");*/
    	TMode tMode = new TMode();
    	tMode.setId(tModeId);
    	tMode.setReportType(reportType);
    	tMode.setReportConten(reportConten);
    	tMode.setModeCondition(modeCondition);
    	tMode.setModeContent(modeContent);
    	tMode.setModeValues(modeValues);
    	tMode.setRemark(remark);
    	tMode.setConditionValues(conditionValues);
    	tMode.setModeArea(modeArea);
    	if(px!=null && px.length()>0){
    		 tMode.setPx(Integer.parseInt(px));
         }
    	int flag=tModeMapper.updateByPrimaryKeySelective(tMode);
    	HttpSession session = getSession();
        session.setAttribute("tpage", pages);
    	return flag;
    }
    
    
    /**
     * 删除
     */
    @RequestMapping(value = "/tModeDelete", method = RequestMethod.POST)
    public String tModeDelete() {
        try{
        	String tModeId = getPara("tModeId");
        	if(Common.isNotEmpty(tModeId)){
        		tModeService.deletetMode(tModeId);
        	}
        }
        catch (Exception e){
            e.printStackTrace();
        }
    	return "redirect:tModeIndex";
    }
    
}




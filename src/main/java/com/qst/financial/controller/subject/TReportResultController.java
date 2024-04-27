package com.qst.financial.controller.subject;

import com.qst.financial.controller.base.BaseController;
import com.qst.financial.dao.mapper.base.SysResourcesMapper;
import com.qst.financial.modle.base.SysResourcesModel;
import com.qst.financial.modle.base.SysUserModel;
import com.qst.financial.modle.subject.CodeLibr;
import com.qst.financial.modle.subject.TReportResult;
import com.qst.financial.service.subject.TReportResultService;
import com.qst.financial.sql.C;
import com.qst.financial.sql.Method;
import com.qst.financial.sql.WherePrams;
import com.qst.financial.util.common.Common;
import com.qst.financial.util.tag.InnerPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/report/result")
public class TReportResultController extends BaseController{

	private Logger log=LoggerFactory.getLogger(TReportResultController.class);
	
	@Autowired
   	private SysResourcesMapper sysResourcesMapper;

	@Autowired
	private TReportResultService tReportResultService;
	
	@Value("${cbs.Ip}")
	private String ip;

	@Value("${cbs.tempPath}")
	private String tempPath;

	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String msgIndex(String fatherId, String parentId,Model model) throws Exception{
		if (!Common.isEmpty(fatherId) && !Common.isEmpty(parentId))
        {
            HttpSession session = getSession();
            session.setAttribute("fatherMenuId", fatherId);
            session.setAttribute("menuId", parentId);
        }
	    return "admin/jsp/report/tReportResultIndex";
	}
	
    @RequestMapping(value = "/buttonList", method = RequestMethod.GET)
    public String buttonList(HttpServletRequest request, Model model)
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
            
            String pageNow = getPara("pageNow");
            String pageSize = getPara("pageSize");
            if(Common.isEmpty(pageNow)){
            	pageNow = "1";
            }
            if(Common.isEmpty(pageSize)){
            	pageSize = "10";
            }
            InnerPage innerPage = new InnerPage();
            TReportResult msgBean = new TReportResult();
            Method method = new  Method();
            WherePrams wherePrams = method.where("1", C.EQ, 1);

            innerPage.setDate(msgBean);
            int count=(int) tReportResultService.count(wherePrams);
            initPage(request, Integer.parseInt(pageNow), Integer.parseInt(pageSize), count);
            String limit = getLimit(Integer.parseInt(pageNow), count);
            List<TReportResult> tReportResultList = tReportResultService.listPage(wherePrams, limit);
			model.addAttribute("tReportResultList", tReportResultList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return "admin/jsp/report/tReportResultListAndButtonList";
    }
    
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String msgList(HttpServletRequest request, Model model,String reportType){
		String veiw="admin/jsp/report/tReportResultList";
		try {
			 String pageNow = getPara("pageNow");
			 String pageSize = getPara("pageSize");
			 if(Common.isEmpty(pageNow)){
			 	pageNow = "1";
			 }
			 if(Common.isEmpty(pageSize)){
			 	pageSize = "10";
			 }
			 CodeLibr subject=new CodeLibr();
			 subject.setReportType(reportType);
			 InnerPage innerPage = new InnerPage();
			 innerPage.setDate(subject);
			 Map<String,String> map = new HashMap<>();
			 map.put("reportType",reportType);
			 int count=(int) tReportResultService.selectAllCountByType(map);
		     initPage(request, Integer.parseInt(pageNow), Integer.parseInt(pageSize), count);
		     String limit = getLimit(Integer.parseInt(pageNow), count);
		     innerPage.setLimit(limit);
		     List<TReportResult> tReportResultList = tReportResultService.selectAllByType(innerPage);
		     model.addAttribute("tReportResultList", tReportResultList);
		} catch (Exception e) {
			log.info("=====" + e.getMessage());
		}
		return veiw;
    }

    
}

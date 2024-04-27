package com.qst.financial.controller.base;

import com.qst.financial.modle.base.OrgInfo;
import com.qst.financial.modle.base.SysDictionaryDetailModel;
import com.qst.financial.service.base.OrgInfoService;
import com.qst.financial.service.base.SystemDictionaryService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/orgInfo")
public class OrgInfoController extends BaseController {
	private final static Logger log=LoggerFactory.getLogger(OrgInfoController.class);
	@Autowired
	private SystemDictionaryService systemDictionaryService;
	@Autowired
	private OrgInfoService orgInfoService;
	@RequestMapping(value = "/orgInfoIndex", method = RequestMethod.GET)
	public String subjectIndex(String fatherId, String parentId,Model model) throws Exception{
	    if (!Common.isEmpty(fatherId) && !Common.isEmpty(parentId)){
	    	try
	        {
	    		getButtonList(fatherId, parentId);
	        }
	        catch (Exception e)
	        {
	            log.info("====="+e.getMessage());
	            return "admin/jsp/error/400";
	        }
	    }
	    return "admin/jsp/orgInfo/orgInfoIndex";
	}
	@RequestMapping(value = "/orgInfoList", method = RequestMethod.GET)
    public String subjectList(HttpServletRequest request, Model model,String orgName,String legalerName,String isList,String resPersonTel){
		try
        {
            String pageNow = getPara("pageNow");
            String pageSize = getPara("pageSize");
            if(Common.isEmpty(pageNow)){
            	pageNow = "1";
            }
            if(Common.isEmpty(pageSize)){
            	pageSize = "10";
            }
            Map<String,String> mapRole = new HashMap<String, String>();
            InnerPage innerPage=new InnerPage();
            Method method=new Method();
            WherePrams wherePrams=method.where("1", C.EQ, 1);
            if(!Common.isEmpty(orgName)){
            	wherePrams.and("orgName", C.LIKE, orgName);
            }
            if(!Common.isEmpty(legalerName)){
            	wherePrams.and("legalerName", C.LIKE, legalerName);
            }
            if(!Common.isEmpty(isList)){
            	wherePrams.and("isList", C.EQ, isList);
            }
            if(!Common.isEmpty(resPersonTel)){
            	wherePrams.and("resPersonTel", C.LIKE, resPersonTel);
            }
            int count=(int) orgInfoService.count(wherePrams);
            initPage(request, Integer.parseInt(pageNow), Integer.parseInt(pageSize), count);
            String limit = getLimit(Integer.parseInt(pageNow), count);
            List<OrgInfo> orgInfoList=orgInfoService.listPage(wherePrams, limit);
			model.addAttribute("orgInfoList", orgInfoList);
        }
        catch (Exception e)
        {
        	 log.info("====="+e.getMessage());
        }
		return "admin/jsp/orgInfo/orgInfoList";
    }
	/**
	 * 新增公司
	 * @param model
	 * @return
	 */
    @RequestMapping(value = "/orgAddPage", method = RequestMethod.GET)
    public String orgAddPage(Model model)
    {
        try
        {
        	Map<String, String> map=new HashMap<String, String>();
        	map.put("enName", "proClass");
        	List<SysDictionaryDetailModel> disList=systemDictionaryService.selectByDic(map);
        	List<OrgInfo> orgList=orgInfoService.list(null);
            model.addAttribute("disList", disList);
            model.addAttribute("orgList", orgList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return "admin/jsp/orgInfo/orgInfoAddPage";
    }
    @RequestMapping(value = "/orgSave", method = RequestMethod.POST)
    public String orgSave(HttpServletRequest request, Model model) throws Exception
    {
    	OrgInfo org=new OrgInfo();
    	String orgName=request.getParameter("orgName");
    	org.setOrgName(orgName);
    	String shortName=request.getParameter("shortName");
    	org.setShortName(shortName);
    	String businessNum=request.getParameter("businessNum");
    	org.setBusinessNum(businessNum);
    	String legalerName=request.getParameter("legalerName");
    	org.setLegalerName(legalerName);
    	String paidCapital=request.getParameter("paidCapital");
    	org.setPaidCapital(paidCapital);
    	String confusingCapital=request.getParameter("confusingCapital");
    	org.setConfusingCapital(confusingCapital);
    	String registeredAddr=request.getParameter("registeredAddr");
    	org.setRegisteredAddr(registeredAddr);
    	String industry=request.getParameter("industry");
    	org.setIndustry(industry);
    	String isList=request.getParameter("isList");
    	org.setIsList(isList);
    	String orgProperty=request.getParameter("orgProperty");
    	org.setOrgProperty(orgProperty);
    	String resPersonName=request.getParameter("resPersonName");
    	org.setResPersonName(resPersonName);
    	String resPersonTel=request.getParameter("resPersonTel");
    	org.setResPersonTel(resPersonTel);
    	String reportLimit=request.getParameter("reportLimit");
    	org.setReportLimit(reportLimit);
    	String parentId=request.getParameter("parentId");
    	org.setParentId(parentId);
    	String orgRemark=request.getParameter("orgRemark");
    	org.setOrgRemark(orgRemark);
    	orgInfoService.addLocal(org);
		return "redirect:orgInfoIndex";
    }
    @RequestMapping(value = "/orgEditPage", method = RequestMethod.GET)
    public String orgEditPage(HttpServletRequest request,Model model)
    {
        try
        {
        	Map<String, String> map=new HashMap<String, String>();
        	map.put("enName", "proClass");
        	String orgId=request.getParameter("orgId");
        	WherePrams wherePrams= Method.where("id", C.EQ, orgId);
        	List<OrgInfo> orgInfoList=orgInfoService.list(wherePrams);
        	List<SysDictionaryDetailModel> disList=systemDictionaryService.selectByDic(map);
            model.addAttribute("disList", disList);
        	if(orgInfoList!=null && orgInfoList.size()>0){
        		OrgInfo orgInfo=orgInfoList.get(0);
        		model.addAttribute("orgInfo", orgInfo);
        	}
        	/*Map<String, String> map=new HashMap<String, String>();
        	map.put("enName", "proClass");
        	List<SysDictionaryDetailModel> disList=systemDictionaryService.selectByDic(map);
        	List<OrgInfo> orgList=orgInfoService.list(null);
            model.addAttribute("disList", disList);
            model.addAttribute("orgList", orgList);*/
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return "admin/jsp/orgInfo/orgInfoEditPage";
    }
    @RequestMapping(value = "/orgEdit", method = RequestMethod.POST)
    public String orgEdit(HttpServletRequest request, Model model) throws Exception
    {
    	OrgInfo org=new OrgInfo();
    	String id=request.getParameter("id");
    	org.setId(id);
    	String orgName=request.getParameter("orgName");
    	org.setOrgName(orgName);
    	String shortName=request.getParameter("shortName");
    	org.setShortName(shortName);
    	String businessNum=request.getParameter("businessNum");
    	org.setBusinessNum(businessNum);
    	String legalerName=request.getParameter("legalerName");
    	org.setLegalerName(legalerName);
    	String paidCapital=request.getParameter("paidCapital");
    	org.setPaidCapital(paidCapital);
    	String confusingCapital=request.getParameter("confusingCapital");
    	org.setConfusingCapital(confusingCapital);
    	String registeredAddr=request.getParameter("registeredAddr");
    	org.setRegisteredAddr(registeredAddr);
    	String industry=request.getParameter("industry");
    	org.setIndustry(industry);
    	String isList=request.getParameter("isList");
    	org.setIsList(isList);
    	String orgProperty=request.getParameter("orgProperty");
    	org.setOrgProperty(orgProperty);
    	String resPersonName=request.getParameter("resPersonName");
    	org.setResPersonName(resPersonName);
    	String resPersonTel=request.getParameter("resPersonTel");
    	org.setResPersonTel(resPersonTel);
    	String reportLimit=request.getParameter("reportLimit");
    	org.setReportLimit(reportLimit);
    	/*String parentId=request.getParameter("parentId");
    	org.setParentId(parentId);*/
    	String orgRemark=request.getParameter("orgRemark");
    	org.setOrgRemark(orgRemark);
    	orgInfoService.updateLocal(org);
		return "redirect:orgInfoIndex";
    }
}








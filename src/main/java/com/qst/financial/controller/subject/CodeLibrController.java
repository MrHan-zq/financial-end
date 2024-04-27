package com.qst.financial.controller.subject;

import com.qst.financial.controller.base.BaseController;
import com.qst.financial.dao.mapper.subject.CodeLibrMapper;
import com.qst.financial.dao.mapper.subject.TCodeMapperBeanMapper;
import com.qst.financial.modle.base.SysUserModel;
import com.qst.financial.modle.subject.CodeLibr;
import com.qst.financial.modle.subject.TCodeMapperBean;
import com.qst.financial.service.subject.CodeLibrService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编码字典Controller
 */
@Controller
@RequestMapping("/codeLibr")
public class CodeLibrController extends BaseController{
	private Logger log=LoggerFactory.getLogger(CodeLibrController.class);
	@Autowired
	private CodeLibrService codeLibrService;
	@Autowired
	private CodeLibrMapper codeLibrMapper;

	@Autowired
	private TCodeMapperBeanMapper tCodeMapperBeanMapper;

	/**
	 * 编码字典初期画面
	 */
	@RequestMapping(value = "/codeLibrIndex", method = RequestMethod.GET)
	public String subjectIndex(String fatherId, String parentId,Model model) throws Exception{

		if (!Common.isEmpty(fatherId) && !Common.isEmpty(parentId)) {
			try {
				getButtonList(fatherId, parentId);
			} catch (Exception e) {
				log.info("=====" + e.getMessage());
				return "admin/jsp/error/400";
			}
		}
	    return "admin/jsp/codeLibr/codeLibrIndex";
	}
	/**
	 * 编码字典得到数据List
	 */
	@RequestMapping(value = "/codeLibrListAndButtonList", method = RequestMethod.GET)
    public String codeLibrListAndButtonList(HttpServletRequest request, Model model,
    		String code_name,String code_no,String report_type,
    		String use_area,
    		String data_type_desc
    		){
		
		String veiw="admin/jsp/codeLibr/codeLibrListAndButtonList";//字典表
		SysUserModel user=getSessionUser();

		String impUser=this.getPower();
		try {
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
            WherePrams wherePrams=Method.where("1", C.EQ, 1);
            if(!Common.isEmpty(code_name)){
            	wherePrams.and("codeName", C.LIKE, code_name);
            }
            if(!Common.isEmpty(code_no)){
            	wherePrams.and("codeNo", C.LIKE, code_no);
            }
            if(!Common.isEmpty(use_area)){
            	wherePrams.and("reportType", C.EQ, use_area);
            }
            InnerPage innerPage=new InnerPage();
			CodeLibr subject=new CodeLibr();
	        innerPage.setDate(subject);
	        int count=(int) codeLibrService.count(wherePrams);
	        initPage(request, Integer.parseInt(pageNow), Integer.parseInt(pageSize), count);
	        String limit = getLimit(Integer.parseInt(pageNow), count);
	        List<CodeLibr> codeLibrList=codeLibrService.listPage(wherePrams, limit);
			model.addAttribute("codeLibrList", codeLibrList);
		} catch (Exception e) {
			log.info("=====" + e.getMessage());
		}
		return veiw;
	}
	/**
	 * 编码字典翻页，查询，得到list
	 */
	@RequestMapping(value = "/codeLibrList", method = RequestMethod.GET)
    public String subjectList(HttpServletRequest request, Model model,
    		String code_name,String code_no,String report_type,
    		String use_area,
    		String data_type_desc
    		){
		
		String veiw="admin/jsp/codeLibr/codeLibrList";//字典表
		SysUserModel user=getSessionUser();

		String impUser=this.getPower();
		try {
			String pageNow = getPara("pageNow");
            String pageSize = getPara("pageSize");
            if(Common.isEmpty(pageNow)){
            	pageNow = "1";
            }
            if(Common.isEmpty(pageSize)){
            	pageSize = "10";
            }
            WherePrams wherePrams=Method.where("1", C.EQ, 1);
            if(!Common.isEmpty(code_name)){
            	wherePrams.and("codeName", C.LIKE, code_name);
            }
            if(!Common.isEmpty(code_no)){
            	wherePrams.and("codeNo", C.LIKE, code_no);
            }
            if(!Common.isEmpty(use_area)){
            	wherePrams.and("reportType", C.EQ, use_area);
            }
            InnerPage innerPage=new InnerPage();
			CodeLibr subject=new CodeLibr();
	        innerPage.setDate(subject);
	        int count=(int) codeLibrService.count(wherePrams);
	        initPage(request, Integer.parseInt(pageNow), Integer.parseInt(pageSize), count);
	        String limit = getLimit(Integer.parseInt(pageNow), count);
	        List<CodeLibr> codeLibrList=codeLibrService.listPage(wherePrams, limit);
			model.addAttribute("codeLibrList", codeLibrList);
		} catch (Exception e) {
			log.info("=====" + e.getMessage());
		}
		return veiw;
	}
	
	/**
     * 编码字典点击【新增】按钮初始化，文字配置增页面
     */
    @RequestMapping(value = "/addCodeLibr", method = RequestMethod.GET)
    public String addTMode(Model model){
    	String pages = getPara("pages");
    	model.addAttribute("pages", pages);
		return "admin/jsp/codeLibr/codeLibrAddPage";
    }
	/**
	 * 编码字典新增
	 */
	@RequestMapping(value = "/codeLibrSave")
    public String  codeLibrSave(HttpServletRequest request, Model model,String id,String codeName,String codeNo,String reportType,String useArea,
    		String useAreaRemark,String dataType,String dataTypeDesc,String isCurrent,
    		String isDetails,String survey,String warn,String clzz,String km1,String km2,String km3,String remark,String pages){
		int flag = 0;
		try{
			CodeLibr subject=new CodeLibr();
			subject.setId(id);
            subject.setCodeName(codeName);
            subject.setCodeNo(codeNo);
            subject.setReportType(reportType);
            subject.setUseArea(useArea);
            subject.setUseAreaRemark(useAreaRemark);
            subject.setDataType(dataType);
            subject.setDataTypeDesc(dataTypeDesc);
            subject.setIsCurrent(isCurrent);
            subject.setIsDetails(isDetails);
            subject.setSurvey(survey);
            subject.setWarn(warn);
            subject.setClzz(clzz);
            subject.setKm1(km1);
            subject.setKm2(km2);
            subject.setKm3(km3);
            subject.setRemark(remark);
            flag=codeLibrService.addLocal(subject);
        }catch (Exception e){
        	log.info("====="+e.getMessage());
        }
		HttpSession session = getSession();
        session.setAttribute("tpage", pages);
		return "redirect:codeLibrIndex";
    }
	
	/**
     * 编码字典编辑页面
     */
    @RequestMapping(value = "/codeLibrEditPage", method = RequestMethod.GET)
    public String codeLibrEditPage(Model model)
    {
        try{
        	String codeLibrId = getPara("codeLibrId");
        	CodeLibr tMode = codeLibrMapper.selectByPrimaryKey(Integer.parseInt(codeLibrId));
        	String pages = getPara("pages");
        	model.addAttribute("pages", pages);
        	model.addAttribute("codeLibr", tMode);
        }
        catch (Exception e){
            e.printStackTrace();
        }
		return "admin/jsp/codeLibr/codeLibrEditPage";
    }
    
    /**
     * 编码字典更新保存
     */
    @RequestMapping(value = "/codeLibrUpdateSave", method = RequestMethod.POST)
    @ResponseBody
    public int   codeLibrUpdateSave(String id,String codeName,String codeNo,String reportType,String useArea,
    		String useAreaRemark,String dataType,String dataTypeDesc,String isCurrent,String isDetails,
    		String survey,String warn,String clzz,String km1,String km2,String km3,String remark,String pages) throws Exception{
    	CodeLibr tMode = new CodeLibr();
    	tMode.setId(id);
    	tMode.setCodeName(codeName);
    	tMode.setCodeNo(codeNo);
    	tMode.setReportType(reportType);
    	tMode.setUseArea(useArea);
    	tMode.setUseAreaRemark(useAreaRemark);
    	tMode.setDataType(dataType);
    	tMode.setDataTypeDesc(dataTypeDesc);
    	tMode.setIsCurrent(isCurrent);
    	tMode.setIsDetails(isDetails);
    	tMode.setSurvey(survey);
    	tMode.setWarn(warn);
    	tMode.setClzz(clzz);
    	tMode.setKm1(km1);
    	tMode.setKm2(km2);
    	tMode.setKm3(km3);
    	tMode.setRemark(remark);
    	HttpSession session = getSession();
        session.setAttribute("tpage", pages);
    	//int flag=codeLibrMapper.updateByPrimaryKeySelective(tMode);
    	int flag=codeLibrMapper.updateByPrimaryKey(tMode);
    	//int flag=codeLibrService.update(tMode);
    	return flag;
    }
    
    
    /**
     * 编码字典删除
     */
    @RequestMapping(value = "/codeLibrDelete", method = RequestMethod.POST)
    public String codeLibrDelete() {
        try{
        	String codeLibrId = getPara("codeLibrId");
        	if(Common.isNotEmpty(codeLibrId)){
        		codeLibrService.deleteCodeLibr(codeLibrId);
        	}
        }
        catch (Exception e){
            e.printStackTrace();
        }
    	return "redirect:codeLibrIndex";
    }

	/**
	 * codeMapper初期画面
	 */
    @RequestMapping(value = "/codeMapperIndex", method = RequestMethod.GET)
	public String codeMapperIndex(String fatherId, String parentId,Model model) throws Exception{

		if (!Common.isEmpty(fatherId) && !Common.isEmpty(parentId)) {
			try {
				getButtonList(fatherId, parentId);
			} catch (Exception e) {
				log.info("=====" + e.getMessage());
				return "admin/jsp/error/400";
			}
		}
	    return "admin/jsp/codeLibr/codeMapperIndex";
	}
	/**
	 * codeMapper获取数据list
	 */
	@RequestMapping(value = "/codeMapperListAndButtonList", method = RequestMethod.GET)
    public String codeMapperListAndButtonList(HttpServletRequest request, Model model,String codeName,String codeNo){
		
		String veiw="admin/jsp/codeLibr/codeMapperListAndButtonList";
		try {
			String pageNow = getPara("pageNow");
            String pageSize = getPara("pageSize");
            if(Common.isEmpty(pageNow)){
            	pageNow = "1";
            }
            if(Common.isEmpty(pageSize)){
            	pageSize = "10";
            }
            WherePrams wherePrams=Method.where("1", C.EQ, 1);
            if(!Common.isEmpty(codeName)){
            	wherePrams.and("codeName", C.LIKE, codeName);
            }
            if(!Common.isEmpty(codeNo)){
            	wherePrams.and("codeNo", C.LIKE, codeNo);
            }
            Map<String,String> mapRole = new HashMap<String, String>();
            InnerPage innerPage=new InnerPage();
			CodeLibr subject=new CodeLibr();
			subject.setCodeName(codeName);
			subject.setCodeNo(codeNo);
	        innerPage.setDate(subject);
	        int count=(int) codeLibrService.count(wherePrams);
	        initPage(request, Integer.parseInt(pageNow), Integer.parseInt(pageSize), count);
	        String limit = getLimit(Integer.parseInt(pageNow), count);
	        innerPage.setLimit(limit);
			mapRole.put("limit", limit);
			List<CodeLibr> codeMapperList=codeLibrService.selectCodeMapperList(innerPage);
			model.addAttribute("codeMapperList", codeMapperList);		            
		} catch (Exception e) {
			log.info("=====" + e.getMessage());
		}
		return veiw;
	}
	/**
	 * codeMapper翻页查询删除时获取list
	 */
	@RequestMapping(value = "/codeMapperList", method = RequestMethod.GET)
    public String codeMapperList(HttpServletRequest request, Model model,String codeName,String codeNo){
		
		String veiw="admin/jsp/codeLibr/codeMapperList";
		try {
			String pageNow = getPara("pageNow");
            String pageSize = getPara("pageSize");
            if(Common.isEmpty(pageNow)){
            	pageNow = "1";
            }
            if(Common.isEmpty(pageSize)){
            	pageSize = "10";
            }
            WherePrams wherePrams=Method.where("1", C.EQ, 1);
            if(!Common.isEmpty(codeName)){
            	wherePrams.and("codeName", C.LIKE, codeName);
            }
            if(!Common.isEmpty(codeNo)){
            	wherePrams.and("codeNo", C.LIKE, codeNo);
            }
            Map<String,String> mapRole = new HashMap<String, String>();
            InnerPage innerPage=new InnerPage();
			CodeLibr subject=new CodeLibr();
			subject.setCodeName(codeName);
			subject.setCodeNo(codeNo);
	        innerPage.setDate(subject);
	        int count=(int) codeLibrService.count(wherePrams);
	        initPage(request, Integer.parseInt(pageNow), Integer.parseInt(pageSize), count);
	        String limit = getLimit(Integer.parseInt(pageNow), count);
	        innerPage.setLimit(limit);
			mapRole.put("limit", limit);
			List<CodeLibr> codeMapperList=codeLibrService.selectCodeMapperList(innerPage);
			model.addAttribute("codeMapperList", codeMapperList);		     
		} catch (Exception e) {
			log.info("=====" + e.getMessage());
		}
		return veiw;
	}
	
	/**
     * CodeMapper文字配置增页面
     */
    @RequestMapping(value = "/addCodeMapper", method = RequestMethod.GET)
    public String addCodeMapper(Model model){
    	List<CodeLibr> codeNoList=codeLibrMapper.selectCodeNoList();
        model.addAttribute("codeNoList", codeNoList);
		return "admin/jsp/codeLibr/codeMapperAddPage";
    }
	/**
	 * CodeMapper增加
	 */
	@RequestMapping(value = "/codeMapperSave")
    public String  codeMapperSave(HttpServletRequest request, Model model,String basiField,String codeNo){
		int flag = 0;
		try{
			TCodeMapperBean subject=new TCodeMapperBean();
            subject.setBasiField(basiField);
            subject.setCodeNo(codeNo);
            flag=tCodeMapperBeanMapper.insert(subject);
        }catch (Exception e){
        	log.info("====="+e.getMessage());
        }
		return "redirect:codeMapperIndex";
    }
	
	/**
     * CodeMapper编辑页面
     */
    @RequestMapping(value = "/codeMapperEditPage", method = RequestMethod.GET)
    public String codeMapperEditPage(Model model)
    {
        try{
        	String codeNo = getPara("codeNo");
        	TCodeMapperBean tMode = tCodeMapperBeanMapper.selectByCodeNo(codeNo);
        	model.addAttribute("tCodeMapperBean", tMode);
        }
        catch (Exception e){
            e.printStackTrace();
        }
		return "admin/jsp/codeLibr/codeMapperEditPage";
    }
    
    /**
     * CodeMapper更新保存
     */
    @RequestMapping(value = "/codeMapperUpdateSave", method = RequestMethod.POST)
    @ResponseBody
    public int   codeLibrUpdateSave(String basiField,String codeNo) throws Exception{
    	TCodeMapperBean tMode = new TCodeMapperBean();
    	tMode.setBasiField(basiField);
    	tMode.setCodeNo(codeNo);
    	int flag=tCodeMapperBeanMapper.update(tMode);
    	return flag;
    }
    
    
    /**
     * CodeMapper删除
     */
    @RequestMapping(value = "/codeMapperDelete", method = RequestMethod.POST)
    public String codeMapperDelete() {
        try{
        	String codeNo = getPara("codeNo");
        	if(Common.isNotEmpty(codeNo)){
        		tCodeMapperBeanMapper.delete(codeNo);
        		codeLibrService.deleteCodeLibr(codeNo);
        	}
        }
        catch (Exception e){
            e.printStackTrace();
        }
    	return "redirect:codeMapperIndex";
    }
	
}




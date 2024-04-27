package com.qst.financial.controller.subject;

import com.qst.financial.controller.base.BaseController;
import com.qst.financial.modle.base.PoModel;
import com.qst.financial.modle.base.SysUserModel;
import com.qst.financial.modle.subject.*;
import com.qst.financial.service.subject.*;
import com.qst.financial.sql.C;
import com.qst.financial.sql.Method;
import com.qst.financial.sql.WherePrams;
import com.qst.financial.util.ModelUtil;
import com.qst.financial.util.POIUtil;
import com.qst.financial.util.ReportUtil;
import com.qst.financial.util.common.Common;
import com.qst.financial.util.tag.InnerPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报表Controller
 */
@Controller
@RequestMapping("/report")
public class FinancialReportController<T extends PoModel> extends BaseController {
	@Autowired
	private TBasiProfitService tBasiProfitService;
	@Autowired
	private TBasiAssetsAndLiabilitiesService tBasiAssetsAndLiabilitiesService;

	@Autowired
	private TKmyeService tKmyeService;
	@Autowired
	private TReportPropertyService tReportPropertyService;
	@Autowired
	private TBasiCashFlowService tBasiCashFlowService;
	@Autowired
	private TAgeAnalysisService tAgeAnalysisService;
	@Autowired
	private TReportNameService tReportNameService;

	private ModelUtil modelUtil=new ModelUtil();

	@RequestMapping(value = "/tBasiProfitIndex", method = RequestMethod.GET)
	public String subjectIndex(String fatherId, String parentId) throws Exception {
		if (!Common.isEmpty(fatherId) && !Common.isEmpty(parentId)) {
			try {
				getButtonList(fatherId, parentId);
			} catch (Exception e) {
				log.info("=====" + e.getMessage());
				return "admin/jsp/error/400";
			}
		}
		return "admin/jsp/report/tBasiProfitIndex";
	}

	@RequestMapping(value = "/tBasiAssetsAndLiabilitiesIndex", method = RequestMethod.GET)
	public String tBasiAssetsAndLiabilitiesIndex(String fatherId, String parentId) throws Exception {
		if (!Common.isEmpty(fatherId) && !Common.isEmpty(parentId)) {
			try {
				getButtonList(fatherId, parentId);
			} catch (Exception e) {
				log.info("=====" + e.getMessage());
				return "admin/jsp/error/400";
			}
		}
		return "admin/jsp/report/tBasiAssetsAndLiabilitiesIndex";
	}

	@RequestMapping(value = "/tKmyeIndex", method = RequestMethod.GET)
	public String tKmyeIndex(String fatherId, String parentId) throws Exception {
		if (!Common.isEmpty(fatherId) && !Common.isEmpty(parentId)) {
			try {
				getButtonList(fatherId, parentId);
			} catch (Exception e) {
				log.info("=====" + e.getMessage());
				return "admin/jsp/error/400";
			}
		}
		return "admin/jsp/report/tKmyeIndex";
	}

	@RequestMapping(value = "/tBasiCashFlowIndex", method = RequestMethod.GET)
	public String tBasiCashFlowIndex(String fatherId, String parentId) throws Exception {
		if (!Common.isEmpty(fatherId) && !Common.isEmpty(parentId)) {
			try {
				getButtonList(fatherId, parentId);
			} catch (Exception e) {
				log.info("=====" + e.getMessage());
				return "admin/jsp/error/400";
			}
		}
		return "admin/jsp/report/tBasiCashFlowIndex";
	}

	@RequestMapping(value = "/paymentTAgeAnalysisIndex", method = RequestMethod.GET)
	public String paymentTAgeAnalysisIndex(String fatherId, String parentId) throws Exception {
		if (!Common.isEmpty(fatherId) && !Common.isEmpty(parentId)) {
			try {
				getButtonList(fatherId, parentId);
			} catch (Exception e) {
				log.info("=====" + e.getMessage());
				return "admin/jsp/error/400";
			}
		}
		return "admin/jsp/report/paymentTAgeAnalysisIndex";
	}
	@RequestMapping(value = "/receiveTAgeAnalysisIndex", method = RequestMethod.GET)
	public String receiveTAgeAnalysisIndex(String fatherId, String parentId) throws Exception {
		if (!Common.isEmpty(fatherId) && !Common.isEmpty(parentId)) {
			try {
				getButtonList(fatherId, parentId);
			} catch (Exception e) {
				log.info("=====" + e.getMessage());
				return "admin/jsp/error/400";
			}
		}
		return "admin/jsp/report/receiveTAgeAnalysisIndex";
	}

	@RequestMapping(value = "/otherTAgeAnalysisIndex", method = RequestMethod.GET)
	public String otherTAgeAnalysisIndex(String fatherId, String parentId) throws Exception {
		if (!Common.isEmpty(fatherId) && !Common.isEmpty(parentId)) {
			try {
				getButtonList(fatherId, parentId);
			} catch (Exception e) {
				log.info("=====" + e.getMessage());
				return "admin/jsp/error/400";
			}
		}
		return "admin/jsp/report/otherTAgeAnalysisIndex";
	}

	@RequestMapping(value = "/planTAgeAnalysisIndex", method = RequestMethod.GET)
	public String planTAgeAnalysisIndex(String fatherId, String parentId) throws Exception {
		if (!Common.isEmpty(fatherId) && !Common.isEmpty(parentId)) {
			try {
				getButtonList(fatherId, parentId);
			} catch (Exception e) {
				log.info("=====" + e.getMessage());
				return "admin/jsp/error/400";
			}
		}
		return "admin/jsp/report/planTAgeAnalysisIndex";
	}

	@RequestMapping(value = "/mustTAgeAnalysisIndex", method = RequestMethod.GET)
	public String mustTAgeAnalysisIndex(String fatherId, String parentId) throws Exception {
		if (!Common.isEmpty(fatherId) && !Common.isEmpty(parentId)) {
			try {
				getButtonList(fatherId, parentId);
			} catch (Exception e) {
				log.info("=====" + e.getMessage());
				return "admin/jsp/error/400";
			}
		}
		return "admin/jsp/report/mustTAgeAnalysisIndex";
	}

	@RequestMapping(value = "/bulkImportIndex", method = RequestMethod.GET)
	public String bulkImportIndex(String fatherId, String parentId) throws Exception {
		if (!Common.isEmpty(fatherId) && !Common.isEmpty(parentId)) {
			try {
				getButtonList(fatherId, parentId);
			} catch (Exception e) {
				log.info("=====" + e.getMessage());
				return "admin/jsp/error/400";
			}
		}
		return "admin/jsp/report/bulkImportIndex";
	}

	/**
	 * 利润表查询
	 */
	@RequestMapping(value = "/tBasiProfitList", method = RequestMethod.GET)
	public String tBasiProfitList(HttpServletRequest request, Model model, String kjyearMoth) {
		String veiw="admin/jsp/report/tBasiProfitList";//利润表
		SysUserModel user=getSessionUser();
		Long portType = (long) 0;
		if(user !=null && null != user.getAccountPortType() && !"".equals(user.getAccountPortType())){
			portType=user.getAccountPortType();
		}
		Long orgId=null;
		Integer type=user.getType();
		if(type==null){
			type=1;
		}
		if(type.equals(1)){
			if(user.getOrgId()!=null && !"".equals(user.getOrgId())){
				orgId=user.getOrgId();
			}
		}
		//String impUser=this.getPower();
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
			if(!Common.isEmpty(orgId)){
				wherePrams.and("orgId", C.EQ, orgId);
			}
			if(!Common.isEmpty(kjyearMoth)){
				wherePrams.and("kjyearMoth", C.EQ, kjyearMoth);
			}
			if(!Common.isEmpty(portType)){
				wherePrams.and("accountPortType", C.EQ, portType);//财务系统类别
			}
			InnerPage innerPage=new InnerPage();
			TBasiProfit subject=new TBasiProfit();
			innerPage.setDate(subject);
			int count=0;
            /*if(!Common.isEmpty(orgId)){
            	wherePrams.and("orgId", C.EQ, orgId);
            }*/
			count=(int) tBasiProfitService.count(wherePrams);
			initPage(request, Integer.parseInt(pageNow), Integer.parseInt(pageSize), count);
			String limit = getLimit(Integer.parseInt(pageNow), count);
			List<TBasiProfit> tBasiProfitList=tBasiProfitService.listPage(wherePrams, limit);
			model.addAttribute("tBasiProfitList", tBasiProfitList);
		} catch (Exception e) {
			log.info("=====" + e.getMessage());
		}
		return veiw;
	}

	/**
	 * 资产负债表查询
	 */
	@RequestMapping(value = "/tBasiAssetsAndLiabilitiesList", method = RequestMethod.GET)
	public String tBasiAssetsAndLiabilitiesList(HttpServletRequest request, Model model, String kjyearMoth) {
		String veiw="admin/jsp/report/tBasiAssetsAndLiabilitiesList";
		SysUserModel user=getSessionUser();
		Long portType = (long) 0;
		if(user !=null && null != user.getAccountPortType() && !"".equals(user.getAccountPortType())){
			portType=user.getAccountPortType();
		}
		Long orgId=null;
		Integer type=user.getType();
		if(type==null){
			type=1;
		}
		if(type.equals(1)){
			if(user.getOrgId()!=null && !"".equals(user.getOrgId())){
				orgId=user.getOrgId();
			}
		}
		//String impUser=this.getPower();
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
			if(!Common.isEmpty(orgId)){
				wherePrams.and("orgId", C.EQ, orgId);
			}
			if(!Common.isEmpty(kjyearMoth)){
				wherePrams.and("kjyearMoth", C.EQ, kjyearMoth);
			}
			if(!Common.isEmpty(portType)){
				wherePrams.and("accountPortType", C.EQ, portType);//财务系统类别
			}
			InnerPage innerPage=new InnerPage();
			TBasiAssetsAndLiabilities subject=new TBasiAssetsAndLiabilities();
			innerPage.setDate(subject);
			int count=0;
           /* if(!Common.isEmpty(orgId)){
            	wherePrams.and("orgId", C.EQ, orgId);
            }*/
			count=(int)  tBasiAssetsAndLiabilitiesService.count(wherePrams);
			initPage(request, Integer.parseInt(pageNow), Integer.parseInt(pageSize), count);
			String limit = getLimit(Integer.parseInt(pageNow), count);
			List<TBasiAssetsAndLiabilities> tBasiAssetsAndLiabilitiesList=tBasiAssetsAndLiabilitiesService.listPage(wherePrams, limit);
           /* if(!Common.isEmpty(orgId)){
            	tBasiAssetsAndLiabilitiesList=tBasiAssetsAndLiabilitiesService.listPage(wherePrams, limit);
            }*/
			model.addAttribute("tBasiAssetsAndLiabilitiesList", tBasiAssetsAndLiabilitiesList);
		} catch (Exception e) {
			log.info("=====" + e.getMessage());
		}
		return veiw;
	}

	/**
	 * 批量导入查询
	 */
	@RequestMapping(value = "/bulkList", method = RequestMethod.GET)
	public String bulkList(HttpServletRequest request, Model model , String name) {
		String veiw="admin/jsp/report/bulkImportList";
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

			if(!Common.isEmpty(name)){
				wherePrams.and("name", C.LIKE, name);
			}

			InnerPage innerPage=new InnerPage();
			TReportName reportName=new TReportName();
			innerPage.setDate(reportName);
			int count=0;
			count=(int) tReportNameService.count(wherePrams);
			initPage(request, Integer.parseInt(pageNow), Integer.parseInt(pageSize), count);
			String limit = getLimit(Integer.parseInt(pageNow), count);
			List<TReportName> bulkList=tReportNameService.listPage(wherePrams, limit);;
			model.addAttribute("bulkList", bulkList);
		} catch (Exception e) {
			log.info("=====" + e.getMessage());
		}
		return veiw;
	}

	/**
	 * 其他应付账龄分析(其他应收账龄分析)表查询
	 */
	@RequestMapping(value = "/otherTAgeAnalysisList", method = RequestMethod.GET)
	public String otherTAgeAnalysisList(HttpServletRequest request, Model model , String kjyearMoth,String importType) {
		String veiw="admin/jsp/report/otherTAgeAnalysisList";
		SysUserModel user=getSessionUser();
		Long portType = (long) 0;
		if(user !=null && null != user.getAccountPortType() && !"".equals(user.getAccountPortType())){
			portType=user.getAccountPortType();
		}
		Long orgId=null;
		Integer type=user.getType();
		if(type==null){
			type=1;
		}
		if(type.equals(1)){
			if(user.getOrgId()!=null && !"".equals(user.getOrgId())){
				orgId=user.getOrgId();
			}
		}
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
			if(!Common.isEmpty(orgId)){
				wherePrams.and("orgId", C.EQ, orgId);
			}
			if(!Common.isEmpty(kjyearMoth)){
				wherePrams.and("kjyearMoth",  C.EQ, kjyearMoth);
			}
			if(!Common.isEmpty(importType)){
				wherePrams.and("type",  C.EQ, importType);
			}
			if(!Common.isEmpty(portType)){
				wherePrams.and("accountPortType", C.EQ, portType);//财务系统类别
			}
			InnerPage innerPage=new InnerPage();
			TAgeAnalysis subject=new TAgeAnalysis();
			innerPage.setDate(subject);
			int count=0;
			count=(int) tAgeAnalysisService.count(wherePrams);
			initPage(request, Integer.parseInt(pageNow), Integer.parseInt(pageSize), count);
			String limit = getLimit(Integer.parseInt(pageNow), count);
			List<TAgeAnalysis> otherTAgeAnalysisList=tAgeAnalysisService.listPage(wherePrams, limit);;
			model.addAttribute("otherTAgeAnalysisList", otherTAgeAnalysisList);
		} catch (Exception e) {
			log.info("=====" + e.getMessage());
		}
		return veiw;
	}

	/**
	 * 科目余额表查询
	 */
	@RequestMapping(value = "/tKmyeList", method = RequestMethod.GET)
	public String tKmyeList(HttpServletRequest request, Model model , String kjyearMoth, String subjectCode) {
		String veiw="admin/jsp/report/tKmyeList";
		SysUserModel user=getSessionUser();
		Long portType = (long) 0;
		if(user !=null && null != user.getAccountPortType() && !"".equals(user.getAccountPortType())){
			portType=user.getAccountPortType();
		}
		Long orgId=null;
		Integer type=user.getType();
		if(type==null){
			type=1;
		}
		if(type.equals(1)){
			if(user.getOrgId()!=null && !"".equals(user.getOrgId())){
				orgId=user.getOrgId();
			}
		}
		//String impUser=this.getPower();
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
			if(!Common.isEmpty(orgId)){
				wherePrams.and("orgId", C.EQ, orgId);
			}
			if(!Common.isEmpty(kjyearMoth)){
				wherePrams.and("kjyearMoth",  C.EQ, kjyearMoth);
			}
			if(!Common.isEmpty(subjectCode)){
				wherePrams.and("subjectCode", C.LIKE, subjectCode);
			}
			if(!Common.isEmpty(portType)){
				wherePrams.and("accountPortType", C.EQ, portType);//财务系统类别
			}
			InnerPage innerPage=new InnerPage();
			TKmye subject=new TKmye();
			innerPage.setDate(subject);
			int count=0;
          /*  if(!Common.isEmpty(orgId)){
            	wherePrams.and("orgId", C.EQ, orgId);
            }*/
			count=(int) tKmyeService.count(wherePrams);
			initPage(request, Integer.parseInt(pageNow), Integer.parseInt(pageSize), count);
			String limit = getLimit(Integer.parseInt(pageNow), count);
			List<TKmye> tKmyeList=tKmyeService.listPage(wherePrams, limit);;
            /*if(!Common.isEmpty(orgId)){
            	tKmyeList=tKmyeService.listPage(wherePrams, limit);
            }*/
			model.addAttribute("tKmyeList", tKmyeList);
		} catch (Exception e) {
			log.info("=====" + e.getMessage());
		}
		return veiw;
	}

	/**
	 * 现金流量表查询
	 */
	@RequestMapping(value = "/tBasiCashFlowList", method = RequestMethod.GET)
	public String tBasiCashFlowList(HttpServletRequest request, Model model, String kjyearMoth) {
		String veiw="admin/jsp/report/tBasiCashFlowList";//现金流量表
		SysUserModel user=getSessionUser();
		Long portType = (long) 0;
		if(user !=null && null != user.getAccountPortType() && !"".equals(user.getAccountPortType())){
			portType=user.getAccountPortType();
		}
		Long orgId=null;
		Integer type=user.getType();
		if(type==null){
			type=1;
		}
		if(type.equals(1)){
			if(user.getOrgId()!=null && !"".equals(user.getOrgId())){
				orgId=user.getOrgId();
			}
		}
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
			if(!Common.isEmpty(orgId)){
				wherePrams.and("orgId", C.EQ, orgId);
			}
			if(!Common.isEmpty(kjyearMoth)){
				wherePrams.and("kjyearMoth", C.EQ, kjyearMoth);
			}
			if(!Common.isEmpty(portType)){
				wherePrams.and("accountPortType", C.EQ, portType);//财务系统类别
			}
			InnerPage innerPage=new InnerPage();
			TBasiCashFlow subject=new TBasiCashFlow();
			innerPage.setDate(subject);
			int count=0;
            /*if(!Common.isEmpty(orgId)){
            	wherePrams.and("orgId", C.EQ, orgId);
            }*/
			count=(int) tBasiCashFlowService.count(wherePrams);
			initPage(request, Integer.parseInt(pageNow), Integer.parseInt(pageSize), count);
			String limit = getLimit(Integer.parseInt(pageNow), count);
			List<TBasiCashFlow> tBasiCashFlowList=tBasiCashFlowService.listPage(wherePrams, limit);
            /*if(!Common.isEmpty(orgId)){
            	tBasiCashFlowList=tBasiCashFlowService.listPage(wherePrams, limit);
            }*/
			model.addAttribute("tBasiCashFlowList", tBasiCashFlowList);
		} catch (Exception e) {
			log.info("=====" + e.getMessage());
		}
		return veiw;
	}

	/**
	 * 应付账龄分析查询
	 */
	@RequestMapping(value = "/paymentTAgeAnalysisList", method = RequestMethod.GET)
	public String paymentTAgeAnalysisList(HttpServletRequest request, Model model , String projectCode) {
		String veiw="admin/jsp/report/paymentTAgeAnalysisList";
		SysUserModel user=getSessionUser();
		Long portType = (long) 0;
		if(user !=null && null != user.getAccountPortType() && !"".equals(user.getAccountPortType())){
			portType=user.getAccountPortType();
		}
		Long orgId=null;
		Integer type=user.getType();
		if(type==null){
			type=1;
		}
		if(type.equals(1)){
			if(user.getOrgId()!=null && !"".equals(user.getOrgId())){
				orgId=user.getOrgId();
			}
		}
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
			wherePrams.and("type", C.EQ, 1);
			if(!Common.isEmpty(orgId)){
				wherePrams.and("orgId", C.EQ, orgId);
			}
			if(!Common.isEmpty(projectCode)){
				wherePrams.and("projectCode", C.LIKE, projectCode);
			}
			if(!Common.isEmpty(portType)){
				wherePrams.and("accountPortType", C.EQ, portType);//财务系统类别
			}
			InnerPage innerPage=new InnerPage();
			TAgeAnalysis subject=new TAgeAnalysis();
			innerPage.setDate(subject);
			int count=0;
			count=(int) tAgeAnalysisService.count(wherePrams);
			initPage(request, Integer.parseInt(pageNow), Integer.parseInt(pageSize), count);
			String limit = getLimit(Integer.parseInt(pageNow), count);
			List<TAgeAnalysis> paymentTAgeAnalysisList=tAgeAnalysisService.listPage(wherePrams, limit);;
			model.addAttribute("paymentTAgeAnalysisList", paymentTAgeAnalysisList);
		} catch (Exception e) {
			log.info("=====" + e.getMessage());
		}
		return veiw;
	}

	/**
	 * 应收账龄分析查询
	 */
	@RequestMapping(value = "/receiveTAgeAnalysisList", method = RequestMethod.GET)
	public String receiveTAgeAnalysisList(HttpServletRequest request, Model model , String projectCode) {
		String veiw="admin/jsp/report/receiveTAgeAnalysisList";
		SysUserModel user=getSessionUser();
		Long portType = (long) 0;
		if(user !=null && null != user.getAccountPortType() && !"".equals(user.getAccountPortType())){
			portType=user.getAccountPortType();
		}
		Long orgId=null;
		Integer type=user.getType();
		if(type==null){
			type=1;
		}
		if(type.equals(1)){
			if(user.getOrgId()!=null && !"".equals(user.getOrgId())){
				orgId=user.getOrgId();
			}
		}
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
			wherePrams.and("type", C.EQ, 2);
			if(!Common.isEmpty(orgId)){
				wherePrams.and("orgId", C.EQ, orgId);
			}
			if(!Common.isEmpty(projectCode)){
				wherePrams.and("projectCode", C.LIKE, projectCode);
			}
			if(!Common.isEmpty(portType)){
				wherePrams.and("accountPortType", C.EQ, portType);//财务系统类别
			}
			InnerPage innerPage=new InnerPage();
			TAgeAnalysis subject=new TAgeAnalysis();
			innerPage.setDate(subject);
			int count=0;
			count=(int) tAgeAnalysisService.count(wherePrams);
			initPage(request, Integer.parseInt(pageNow), Integer.parseInt(pageSize), count);
			String limit = getLimit(Integer.parseInt(pageNow), count);
			List<TAgeAnalysis> receiveTAgeAnalysisList=tAgeAnalysisService.listPage(wherePrams, limit);
			model.addAttribute("receiveTAgeAnalysisList", receiveTAgeAnalysisList);
		} catch (Exception e) {
			log.info("=====" + e.getMessage());
		}
		return veiw;
	}

	/**
	 * 利润表导入(单个导入)
	 * @param excelFile
	 */
	@RequestMapping(value = "/importTBasiProfit", method = RequestMethod.POST)
	@ResponseBody
	public int importTBasiProfit(@RequestParam(value = "excelFile") MultipartFile excelFile,String proSubject) {
		int flag = 0;
		try {
			List<List> listArray_ = POIUtil.readExcel(excelFile);

			List<Object[]> list=listArray_.get(0);
			SysUserModel user=getSessionUser();
			if(user.getOrgId()==null || "".equals(user.getOrgId())){
				flag=-1;
				return flag;
			}
			Long portType = (long) 0;
			if(user !=null && null != user.getAccountPortType() && !"".equals(user.getAccountPortType())){
				portType=user.getAccountPortType();
			}
			Method method=new  Method();
			WherePrams wherePrams=method.where("1", C.EQ, 1);
			if(!Common.isEmpty(portType)){
				wherePrams.and("proClass", C.EQ, portType);
			}
			wherePrams.and("proPublic", C.EQ, 0);//不是公用的字段
			wherePrams.and("proSubject", C.EQ, proSubject);//利润表
			List<TReportProperty> tReportPropertyList=tReportPropertyService.list(wherePrams);
			Boolean manyRow=false;
			Integer beginRow=null;
			Integer endRow=null;
			for(int i = 0;i <= tReportPropertyList.size()-1;i++){
				if(tReportPropertyList.get(i).getProMoneyRow()==1){
					manyRow=true;//判断是否有多行
					beginRow=tReportPropertyList.get(i).getProRow();
					endRow=tReportPropertyList.get(i).getProEnd();
					break;
				}
			}
			List<Map<String, Object>> listMap = ReportUtil.getHashMapValue(list,tReportPropertyList,manyRow,beginRow,endRow,user);

			String fileName = excelFile.getOriginalFilename().split("\\.")[0];
			String yearMoth =fileName.substring(fileName.length()-6,fileName.length());
			listMap.get(0).put("KJYEAR_MOTH", yearMoth);
			listMap.get(0).put("KJYEAR", yearMoth.substring(0, 4));
			listMap.get(0).put("KJMOTH", yearMoth.substring(4,6));

			List<TBasiProfit> listModel = modelUtil.getModel(TBasiProfit.class, listMap);
			tBasiProfitService.deleteByYearMonth(yearMoth,portType,user.getOrgId());
			flag=tBasiProfitService.addLocal(listModel.get(0));
		} catch (Exception e) {
			log.info("读取excel文件失败");
		}
		return flag;
	}

	/**
	 * 利润表导入(批量导入)
	 * @param excelFile
	 */
	@RequestMapping(value = "/importTBasiProfitBulk", method = RequestMethod.POST)
	@ResponseBody
	public Map importTBasiProfitBulk(@RequestParam(value = "excelFile") MultipartFile excelFile,String proSubject) {
		String flag = null;
		try {
			List<List> listArray = POIUtil.readExcel(excelFile);
			List<Object[]> list=listArray.get(0);
			SysUserModel user=getSessionUser();
			if(user.getOrgId()==null || "".equals(user.getOrgId())){
				flag="-1";
			}else{
				Long portType = (long) 0;
				if(user !=null && null != user.getAccountPortType() && !"".equals(user.getAccountPortType())){
					portType=user.getAccountPortType();
				}
				Method method=new  Method();
				WherePrams wherePrams=method.where("1", C.EQ, 1);
				if(!Common.isEmpty(portType)){
					wherePrams.and("proClass", C.EQ, portType);
				}
				wherePrams.and("proPublic", C.EQ, 0);//不是公用的字段
				wherePrams.and("proSubject", C.EQ, proSubject);//科目余额表
				List<TReportProperty> tReportPropertyList=tReportPropertyService.list(wherePrams);
				Boolean manyRow=false;
				Integer beginRow=null;
				Integer endRow=null;
				for(int i = 0;i <= tReportPropertyList.size()-1;i++){
					if(tReportPropertyList.get(i).getProMoneyRow()==1){
						manyRow=true;//判断是否有多行
						beginRow=tReportPropertyList.get(i).getProRow();
						endRow=tReportPropertyList.get(i).getProEnd();
						break;
					}
				}
				//将excel数据（list）根据tReportPropertyList（标志读取list的几行几列）读取到listMap中
				List<Map<String, Object>> listMap = ReportUtil.getHashMapValue(list,tReportPropertyList,manyRow,beginRow,endRow,user);
				String fileName = excelFile.getOriginalFilename().split("\\.")[0];
				String yearMoth =fileName.substring(fileName.length()-6,fileName.length());
				String year =yearMoth.substring(0,4);
				String moth =yearMoth.substring(4,6);
				for(int i = 0;i <= listMap.size()-1;i++){
					listMap.get(i).put("KJYEAR_MOTH", yearMoth);
					listMap.get(i).put("KJYEAR", year);
					listMap.get(i).put("KJMOTH", moth);
				}
				Long orgId=user.getOrgId();
				tBasiProfitService.deleteByYearMonth((String)listMap.get(0).get("KJYEAR_MOTH"),portType,orgId);
				List<TBasiProfit> listModel = modelUtil.getModel(TBasiProfit.class, listMap);
				tBasiProfitService.addLocal(listModel.get(0));
				TReportName name=new TReportName();
				name.setName(fileName);
				tReportNameService.addLocal(name);
				flag=fileName;
			}
		} catch (Exception e) {
			log.info("读取excel文件失败");
		}
		Map<String,String> result = new HashMap<String,String>();
		result.put("flag", flag);
		return result;
	}

	/**
	 * 资产负债表导入(批量导入)
	 * @param excelFile
	 */
	@RequestMapping(value = "/importTBasiAssetsAndLiabilitiesBulk", method = RequestMethod.POST)
	@ResponseBody
	public Map importTBasiAssetsAndLiabilitiesBulk(@RequestParam(value = "excelFile") MultipartFile excelFile,String proSubject) {
		String flag = null;
		try {
			List<List> listArray = POIUtil.readExcel(excelFile);
			List<Object[]> list=listArray.get(0);
			SysUserModel user=getSessionUser();
			if(user.getOrgId()==null || "".equals(user.getOrgId())){
				flag="-1";
			}else{
				Long portType = (long) 0;
				if(user !=null && null != user.getAccountPortType() && !"".equals(user.getAccountPortType())){
					portType=user.getAccountPortType();
				}
				Method method=new  Method();
				WherePrams wherePrams=method.where("1", C.EQ, 1);
				if(!Common.isEmpty(portType)){
					wherePrams.and("proClass", C.EQ, portType);
				}
				wherePrams.and("proPublic", C.EQ, 0);//不是公用的字段
				wherePrams.and("proSubject", C.EQ, proSubject);//科目余额表
				List<TReportProperty> tReportPropertyList=tReportPropertyService.list(wherePrams);
				Boolean manyRow=false;
				Integer beginRow=null;
				Integer endRow=null;
				for(int i = 0;i <= tReportPropertyList.size()-1;i++){
					if(tReportPropertyList.get(i).getProMoneyRow()==1){
						manyRow=true;//判断是否有多行
						beginRow=tReportPropertyList.get(i).getProRow();
						endRow=tReportPropertyList.get(i).getProEnd();
						break;
					}
				}
				//将excel数据（list）根据tReportPropertyList（标志读取list的几行几列）读取到listMap中
				List<Map<String, Object>> listMap = ReportUtil.getHashMapValue(list,tReportPropertyList,manyRow,beginRow,endRow,user);
				String fileName = excelFile.getOriginalFilename().split("\\.")[0];
				String yearMoth =fileName.substring(fileName.length()-6,fileName.length());
				String year =yearMoth.substring(0,4);
				String moth =yearMoth.substring(4,6);
				for(int i = 0;i <= listMap.size()-1;i++){
					listMap.get(i).put("KJYEAR_MOTH", yearMoth);
					listMap.get(i).put("KJYEAR", year);
					listMap.get(i).put("KJMOTH", moth);
				}
				Long orgId=user.getOrgId();
				tBasiAssetsAndLiabilitiesService.deleteByYearMonth((String)listMap.get(0).get("KJYEAR_MOTH"),user.getAccountPortType(),orgId);
				List<TBasiAssetsAndLiabilities> listModel = modelUtil.getModel(TBasiAssetsAndLiabilities.class, listMap);
				tBasiAssetsAndLiabilitiesService.addLocal(listModel.get(0));
				TReportName name=new TReportName();
				name.setName(fileName);
				tReportNameService.addLocal(name);
				flag=fileName;

			}
		} catch (Exception e) {
			log.info("读取excel文件失败");
		}
		Map<String,String> result = new HashMap<String,String>();
		result.put("flag", flag);
		return result;
	}


	/**
	 * 资产负债表导入(单个导入)
	 * @param excelFile
	 */
	@RequestMapping(value = "/importTBasiAssetsAndLiabilities", method = RequestMethod.POST)
	@ResponseBody
	public int importTBasiAssetsAndLiabilities(@RequestParam(value = "excelFile") MultipartFile excelFile,String proSubject) {
		int flag = 0;
		try {
			List<List> listArray_ = POIUtil.readExcel(excelFile);

			List<Object[]> list=listArray_.get(0);
			SysUserModel user=getSessionUser();
			if(user.getOrgId()==null || "".equals(user.getOrgId())){
				flag=-1;
				return flag;
			}
			Long portType = (long) 0;
			if(user !=null && null != user.getAccountPortType() && !"".equals(user.getAccountPortType())){
				portType=user.getAccountPortType();
			}
			Method method=new  Method();
			WherePrams wherePrams=method.where("1", C.EQ, 1);
			if(!Common.isEmpty(portType)){
				wherePrams.and("proClass", C.EQ, portType);
			}
			wherePrams.and("proPublic", C.EQ, 0);//不是公用的字段
			wherePrams.and("proSubject", C.EQ, proSubject);//资产负债表
			List<TReportProperty> tReportPropertyList=tReportPropertyService.list(wherePrams);
			Boolean manyRow=false;
			Integer beginRow=null;
			Integer endRow=null;
			for(int i = 0;i <= tReportPropertyList.size()-1;i++){
				if(tReportPropertyList.get(i).getProMoneyRow()==1){
					manyRow=true;//判断是否有多行
					beginRow=tReportPropertyList.get(i).getProRow();
					endRow=tReportPropertyList.get(i).getProEnd();
					break;
				}
			}
			List<Map<String, Object>> listMap = ReportUtil.getHashMapValue(list,tReportPropertyList,manyRow,beginRow,endRow,user);

			String fileName = excelFile.getOriginalFilename().split("\\.")[0];
			String yearMoth =fileName.substring(fileName.length()-6,fileName.length());
			listMap.get(0).put("KJYEAR", yearMoth.substring(0, 4));
			listMap.get(0).put("KJMOTH", yearMoth.substring(5));
			listMap.get(0).put("KJYEAR_MOTH", yearMoth);

			tBasiAssetsAndLiabilitiesService.deleteByYearMonth((String)listMap.get(0).get("KJYEAR_MOTH"),user.getAccountPortType(),user.getOrgId());
			List<TBasiAssetsAndLiabilities> listModel = modelUtil.getModel(TBasiAssetsAndLiabilities.class, listMap);
			flag=tBasiAssetsAndLiabilitiesService.addLocal(listModel.get(0));
		} catch (Exception e) {
			log.info("读取excel文件失败");
		}
		return flag;
	}

	/**
	 * 科目余额导入(批量导入)
	 * @param excelFile
	 */
	@RequestMapping(value = "/importTKmyeBulk", method = RequestMethod.POST)
	@ResponseBody
	public Map importTKmyeBulk(@RequestParam(value = "excelFile") MultipartFile excelFile,String proSubject) {
		String flag = null;
		try {
			List<List> listArray = POIUtil.readExcel(excelFile);
			List<Object[]> list=listArray.get(0);
			SysUserModel user=getSessionUser();
			if(user.getOrgId()==null || "".equals(user.getOrgId())){
				flag="-1";
			}else{
				Long portType = (long) 0;
				if(user !=null && null != user.getAccountPortType() && !"".equals(user.getAccountPortType())){
					portType=user.getAccountPortType();
				}
				Method method=new  Method();
				WherePrams wherePrams=method.where("1", C.EQ, 1);
				if(!Common.isEmpty(portType)){
					wherePrams.and("proClass", C.EQ, portType);
				}
				wherePrams.and("proPublic", C.EQ, 0);//不是公用的字段
				wherePrams.and("proSubject", C.EQ, proSubject);//科目余额表
				List<TReportProperty> tReportPropertyList=tReportPropertyService.list(wherePrams);
				Boolean manyRow=false;
				Integer beginRow=null;
				Integer endRow=null;
				for(int i = 0;i <= tReportPropertyList.size()-1;i++){
					if(tReportPropertyList.get(i).getProMoneyRow()==1){
						manyRow=true;//判断是否有多行
						beginRow=tReportPropertyList.get(i).getProRow();
						endRow=tReportPropertyList.get(i).getProEnd();
						break;
					}
				}
				List<Map<String, Object>> listMap = ReportUtil.getHashMapValue(list,tReportPropertyList,manyRow,beginRow,endRow,user);
				String fileName = excelFile.getOriginalFilename().split("\\.")[0];
				String yearMoth =fileName.substring(fileName.length()-6,fileName.length());
				String year =yearMoth.substring(0,4);
				String moth =yearMoth.substring(4,6);
				for(int i = 0;i <= listMap.size()-1;i++){
					listMap.get(i).put("KJYEAR_MOTH", yearMoth);
					listMap.get(i).put("KJYEAR", year);
					listMap.get(i).put("KJMOTH", moth);
				}
				Long orgId=user.getOrgId();
				tKmyeService.deleteByYearMonth((String)listMap.get(0).get("KJYEAR_MOTH"),user.getAccountPortType(),orgId);
				Map<String,Object> endMap = modelUtil.getModelList(TKmye.class, listMap);
				List<Integer>  errorList = (List<Integer>) endMap.get("errorList");
				if(errorList !=null && !errorList.isEmpty() && errorList.size()>0){
					flag=fileName+": 第 "+errorList.toString()+" 行数据不正确！";
				}else{
					List<TKmye> listModel = (List<TKmye>) endMap.get("model");
					for(int i=0;i<listModel.size();i++){
						tKmyeService.addLocal(listModel.get(i));
					}
					//Integer result=tKmyeService.insertTKmyeList(listModel);
					TReportName name=new TReportName();
					name.setName(fileName);
					tReportNameService.addLocal(name);
					//flag=result.toString();
					flag=fileName;
				}
			}
		} catch (Exception e) {
			log.info("读取excel文件失败");
		}
		Map<String,String> result = new HashMap<String,String>();
		result.put("flag", flag);
		return result;
	}

	/**
	 * 账龄分析导入(批量导入)
	 * @param excelFile
	 */
	@RequestMapping(value = "/importTAgeAnalysisBulk", method = RequestMethod.POST)
	@ResponseBody
	public Map importTAgeAnalysisBulk(@RequestParam(value = "excelFile") MultipartFile excelFile,String proSubject, String type) {
		String flag = null;
		try {
			List<List> listArray = POIUtil.readExcel(excelFile);
			SysUserModel user=getSessionUser();
			if(user.getOrgId()==null || "".equals(user.getOrgId())){
				flag="-1";
			}else{
				List<Object[]> list=listArray.get(0);
				Long portType = (long) 0;
				if(user !=null && null != user.getAccountPortType() && !"".equals(user.getAccountPortType())){
					portType=user.getAccountPortType();
				}
				Method method=new  Method();
				WherePrams wherePrams=method.where("1", C.EQ, 1);
				if(!Common.isEmpty(portType)){
					wherePrams.and("proClass", C.EQ, portType);
				}
				wherePrams.and("proPublic", C.EQ, 0);//不是公用的字段
				wherePrams.and("proSubject", C.EQ, proSubject);//账龄分析表
				List<TReportProperty> tReportPropertyList=tReportPropertyService.list(wherePrams);
				Boolean manyRow=false;
				Integer beginRow=null;
				Integer endRow=null;
				for(int i = 0;i <= tReportPropertyList.size()-1;i++){
					if(tReportPropertyList.get(i).getProMoneyRow()==1){
						manyRow=true;//判断是否有多行
						beginRow=tReportPropertyList.get(i).getProRow();
						endRow=tReportPropertyList.get(i).getProEnd();
						break;
					}
				}

				List<Map<String, Object>> listMap = ReportUtil.getHashMapValue(list,tReportPropertyList,manyRow,beginRow,endRow,user);
				String fileName = excelFile.getOriginalFilename().split("\\.")[0];
				String yearMoth =fileName.substring(fileName.length()-6,fileName.length());
				String year =yearMoth.substring(0,4);
				String moth =yearMoth.substring(4,6);
				for(int i = 0;i <= listMap.size()-1;i++){
					listMap.get(i).put("KJYEAR_MOTH", yearMoth);
					listMap.get(i).put("KJYEAR", year);
					listMap.get(i).put("KJMOTH", moth);
				}
				List<TAgeAnalysis> listModel = modelUtil.getModel(TAgeAnalysis.class, listMap);
				System.out.println(fileName);
				Long orgId=user.getOrgId();
				tAgeAnalysisService.deleteByYearMonth(yearMoth,portType,Integer.valueOf(type),user.getOrgId());
				for(int i=0;i<listModel.size();i++){
					if(listModel.get(i).getProjectName()!=null && !listModel.get(i).getProjectName().equals("")){
						listModel.get(i).setType(Integer.valueOf(type));
						tAgeAnalysisService.addLocal(listModel.get(i));
					}
				}
				TReportName name=new TReportName();
				name.setName(fileName);
				tReportNameService.addLocal(name);
				flag=fileName;
			}

		} catch (Exception e) {
			log.info("读取excel文件失败");
		}
		Map<String,String> result = new HashMap<String,String>();
		result.put("flag", flag);
		return result;
	}

	/**
	 * 现金流量表导入(单个导入)
	 * @param excelFile
	 */
	@RequestMapping(value = "/importTBasiCashFlow", method = RequestMethod.POST)
	@ResponseBody
	public int importTBasiCashFlow(@RequestParam(value = "excelFile") MultipartFile excelFile,String proSubject) {
		int flag = 0;
		try {
			List<List> listArray_ = POIUtil.readExcel(excelFile);

			List<Object[]> list=listArray_.get(0);
			SysUserModel user=getSessionUser();
			if(user.getOrgId()==null || "".equals(user.getOrgId())){
				flag=-1;
				return flag;
			}
			Long portType = (long) 0;
			if(user !=null && null != user.getAccountPortType() && !"".equals(user.getAccountPortType())){
				portType=user.getAccountPortType();
			}
			Method method=new  Method();
			WherePrams wherePrams=method.where("1", C.EQ, 1);
			if(!Common.isEmpty(portType)){
				wherePrams.and("proClass", C.EQ, portType);
			}
			wherePrams.and("proPublic", C.EQ, 0);//不是公用的字段
			wherePrams.and("proSubject", C.EQ, proSubject);//现金流量表
			List<TReportProperty> tReportPropertyList=tReportPropertyService.list(wherePrams);
			Boolean manyRow=false;
			Integer beginRow=null;
			Integer endRow=null;
			for(int i = 0;i <= tReportPropertyList.size()-1;i++){
				if(tReportPropertyList.get(i).getProMoneyRow()==1){
					manyRow=true;//判断是否有多行
					beginRow=tReportPropertyList.get(i).getProRow();
					endRow=tReportPropertyList.get(i).getProEnd();
					break;
				}
			}
			List<Map<String, Object>> listMap = ReportUtil.getHashMapValue(list,tReportPropertyList,manyRow,beginRow,endRow,user);
			String fileName = excelFile.getOriginalFilename().split("\\.")[0];
			String yearMoth =fileName.substring(fileName.length()-6,fileName.length());
			listMap.get(0).put("KJYEAR_MOTH", yearMoth);
			listMap.get(0).put("KJYEAR", yearMoth.substring(0, 4));
			listMap.get(0).put("KJMOTH", yearMoth.substring(5));

			Long orgId=user.getOrgId();
			tBasiCashFlowService.deleteByYearMonth((String)listMap.get(0).get("KJYEAR_MOTH"),portType,orgId);
			List<TBasiCashFlow> listModel = modelUtil.getModel(TBasiCashFlow.class, listMap);
			flag=tBasiCashFlowService.addLocal(listModel.get(0));

		} catch (Exception e) {
			log.info("读取excel文件失败");
		}
		return flag;
	}

	/**
	 * 现金流量表导入(批量导入)
	 * @param excelFile
	 */
	@RequestMapping(value = "/importTBasiCashFlowBulk", method = RequestMethod.POST)
	@ResponseBody
	public Map importTBasiCashFlowBulk(@RequestParam(value = "excelFile") MultipartFile excelFile,String proSubject) {
		String flag = null;
		try {
			List<List> listArray = POIUtil.readExcel(excelFile);
			List<Object[]> list=listArray.get(0);
			SysUserModel user=getSessionUser();
			if(user.getOrgId()==null || "".equals(user.getOrgId())){
				flag="-1";
			}else{
				Long portType = (long) 0;
				if(user !=null && null != user.getAccountPortType() && !"".equals(user.getAccountPortType())){
					portType=user.getAccountPortType();
				}
				Method method=new  Method();
				WherePrams wherePrams=method.where("1", C.EQ, 1);
				if(!Common.isEmpty(portType)){
					wherePrams.and("proClass", C.EQ, portType);
				}
				wherePrams.and("proPublic", C.EQ, 0);//不是公用的字段
				wherePrams.and("proSubject", C.EQ, proSubject);//科目余额表
				List<TReportProperty> tReportPropertyList=tReportPropertyService.list(wherePrams);
				Boolean manyRow=false;
				Integer beginRow=null;
				Integer endRow=null;
				for(int i = 0;i <= tReportPropertyList.size()-1;i++){
					if(tReportPropertyList.get(i).getProMoneyRow()==1){
						manyRow=true;//判断是否有多行
						beginRow=tReportPropertyList.get(i).getProRow();
						endRow=tReportPropertyList.get(i).getProEnd();
						break;
					}
				}
				//将excel数据（list）根据tReportPropertyList（标志读取list的几行几列）读取到listMap中
				List<Map<String, Object>> listMap = ReportUtil.getHashMapValue(list,tReportPropertyList,manyRow,beginRow,endRow,user);
				String fileName = excelFile.getOriginalFilename().split("\\.")[0];
				String yearMoth =fileName.substring(fileName.length()-6,fileName.length());
				String year =yearMoth.substring(0,4);
				String moth =yearMoth.substring(4,6);
				for(int i = 0;i <= listMap.size()-1;i++){
					listMap.get(i).put("KJYEAR_MOTH", yearMoth);
					listMap.get(i).put("KJYEAR", year);
					listMap.get(i).put("KJMOTH", moth);
				}
				Long orgId=user.getOrgId();
				tBasiCashFlowService.deleteByYearMonth((String)listMap.get(0).get("KJYEAR_MOTH"),portType,orgId);
				List<TBasiCashFlow> listModel = modelUtil.getModel(TBasiCashFlow.class, listMap);
				tBasiCashFlowService.addLocal(listModel.get(0));
				TReportName name=new TReportName();
				name.setName(fileName);
				tReportNameService.addLocal(name);
				flag=fileName;
			}
		} catch (Exception e) {
			log.info("读取excel文件失败");
		}
		Map<String,String> result = new HashMap<String,String>();
		result.put("flag", flag);
		return result;
	}

}

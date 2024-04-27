package com.qst.financial.controller.subject;

import com.qst.financial.controller.base.BaseController;
import com.qst.financial.modle.base.SysUserModel;
import com.qst.financial.modle.subject.TBasiAssetsAndLiabilities;
import com.qst.financial.modle.subject.TBasiCashFlow;
import com.qst.financial.modle.subject.TBasiProfit;
import com.qst.financial.service.subject.*;
import com.qst.financial.sql.C;
import com.qst.financial.sql.Method;
import com.qst.financial.sql.WherePrams;
import com.qst.financial.util.common.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据转换
 * @author why
 *
 */
@Controller
@RequestMapping("/dataFormat")
public class FinancialDataFormatController extends BaseController{
	private Logger log=LoggerFactory.getLogger(FinancialDataFormatController.class);
	@Autowired
	private TReportResultService tReportResultService;
	@Autowired
	private TBasiProfitService tBasiProfitService;
	@Autowired
	private TBasiCashFlowService tBasiCashFlowService;
	@Autowired
	private TBasiAssetsAndLiabilitiesService tBasiAssetsAndLiabilitiesService;
	@Autowired
	private CodeLibrService codeLibrService;
    
    Map<String,Object> map = new HashMap<String,Object>();
	
	/**
	 * 手动全量数据转换
	 * @param org_id ：过滤导入公司
	 * @return
	 */
	@RequestMapping(value = "/dataFormatAll", method = { RequestMethod.POST})
	@ResponseBody
    public Object dataFormatAll(String org_id){
		SysUserModel sysUserModel= this.getSessionUser();
		if((null==org_id || org_id.equals("")) && sysUserModel!=null){
			org_id=sysUserModel.getOrgId().toString();
		}
		//boolean r=true;
		WherePrams wherePrams=Method.where("1", C.EQ, 1);
        if(!Common.isEmpty(org_id)){
        	wherePrams.and("orgId", C.EQ, org_id);
        }
        wherePrams.and("formatFlag", C.EQ, "N");
		try
        {
            //全表全部操作
			List<TBasiAssetsAndLiabilities> tBasiAssetsAndLiabilitiesLst=tBasiAssetsAndLiabilitiesService.list(wherePrams);
			if(tBasiAssetsAndLiabilitiesLst!=null && tBasiAssetsAndLiabilitiesLst.size()>0){
				tReportResultService.comFormatData(tBasiAssetsAndLiabilitiesLst, org_id);
			}
			List<TBasiProfit> tBasiProfitList=tBasiProfitService.list(wherePrams);
			if(tBasiProfitList!=null && tBasiProfitList.size()>0){
				tReportResultService.comFormatData(tBasiProfitList, org_id);
			}

			List<TBasiCashFlow> tBasiCashFlow=  tBasiCashFlowService.list(wherePrams);
			if(tBasiCashFlow!=null && tBasiCashFlow.size()>0){
				tReportResultService.comFormatData(tBasiCashFlow, org_id);
			}
			//损益预测数据转换
			HashMap<String,Object> rmap=new HashMap<String,Object>();
			rmap.put("orgId", org_id);
			codeLibrService.getReportDivine(rmap);
			map.put("msg", "success");
        }
        catch (Exception e)
        {
        	log.info("====="+e.getMessage());
        	map.put("msg", "failed");
        }
		return map;
    }

}

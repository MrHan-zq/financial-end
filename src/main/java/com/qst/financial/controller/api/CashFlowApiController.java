package com.qst.financial.controller.api;


import com.qst.financial.annotation.AppControllerLog;
import com.qst.financial.dto.ResultDto;
import com.qst.financial.modle.subject.TBasiCashFlow;
import com.qst.financial.service.subject.ReportResultService;
import com.qst.financial.service.subject.TBasiCashFlowService;
import com.qst.financial.sql.C;
import com.qst.financial.sql.Method;
import com.qst.financial.sql.WherePrams;
import com.qst.financial.util.common.DateUtil;
import com.qst.financial.util.common.ResultCode;
import com.qst.financial.util.json.ResultObj;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 
 * @author GaryChen
 *现金流表 app接口
 */
@Controller
@RequestMapping("/api/cashFlow")
public class CashFlowApiController {
	private static final Logger log=Logger.getLogger(CashFlowApiController.class);
	
	/**
	 * 报表类型--利润表
	 * useArea:  #配置全量数据转换后根据code取得数据
	 *   xjll:
	 *     jyje: 42 #经营净额
	 *     tzje: 43 #投资净额
	 *     czje: 37 #筹资净额
	 *     xjze: 39 #现金增额
	 *     qmye: 41 #期末余额
	 */
	public static final String CASHFLOW_REPORT_TYPE = "3" ;
	
	@Autowired 
	private ReportResultService reportResultService ;
	@Autowired
	private TBasiCashFlowService tBasiCashFlowService;

	@Value("${useArea.xjll.jyje}")
	String jyje;	//经营净额
	@Value("${useArea.xjll.tzje}")
	String tzje; //投资净额
	@Value("${useArea.xjll.czje}")
	String czje; //筹资净额
	@Value("${useArea.xjll.xjze}")
	String xjze; //现金增额
	@Value("${useArea.xjll.qmye}")
	String qmye; //期末余额

	@AppControllerLog(description = "现金流首页，新修版")
	@ApiOperation(value = "现金流首页，新修版{参数内容类型:application/json}", notes = "输入开始时间与结束时间", response = ResultObj.class, httpMethod = "POST")
	@RequestMapping(value="/getCashFlowMainMsg.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody 
	public String getCashFlowMainMsg(@ApiParam(required = true, value = "orgId(用户id),startTime(开始时间),endTime(结束时间ssss)")
		String orgId,String startTime,String endTime){
		try {
			if(startTime==null || startTime.length()==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NULL, "开始时间不能为空");
				return robj.toString();
			}
			if(endTime==null || endTime.length()==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NULL, "结束时间不能为空");
				return robj.toString();
			}
			if(startTime.length()<8) 			startTime=startTime+"-01";
			if(endTime.length()<8)			endTime=endTime+"-01";
			Map<String,Object> rmap = new HashMap<>();
			Map<String,Object> map = new HashMap<>();
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("orgId", orgId);
			map.put("reportType", 3);
			//经营活动产生的现金流量净额
			map.put("useArea", jyje);
			//map.put("useArea", "01");
			ResultDto jyje=reportResultService.selectValueSumDetailAllTotal(map);
			jyje.setCode("jyje");
			jyje.setCodeName("经营活动产生的现金流量净额");
			//投资活动产生的现金流量净额
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("useArea", tzje);
			//map.put("useArea", "02"); 
			ResultDto tzje=reportResultService.selectValueSumDetailAllTotal(map);
			tzje.setCode("tzje");
			tzje.setCodeName("投资活动产生的现金流量净额");
			//筹资活动产生的现金流量净额
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("useArea", czje);
			ResultDto czje=reportResultService.selectValueSumDetailAllTotal(map);
			czje.setCode("czje");
			czje.setCodeName("筹资活动产生的现金流量净额");
			//现金及现金等价物净增额
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("useArea", xjze);
			ResultDto xjze=reportResultService.selectValueSumDetailAllTotal(map);
			xjze.setCode("xjze");
			xjze.setCodeName("现金及现金等价物净增额");
			//期末现金及现金等价物余额
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("useArea",qmye );
			ResultDto qmdjw=reportResultService.selectValueSumDetailAllTotal(map);
			map.put("endTimes", null);
			qmdjw.setCode("qmdjw");
			qmdjw.setCodeName("期末现金及现金等价物余额");
			rmap.put("jyje", jyje);
			rmap.put("tzje", tzje);
			rmap.put("czje", czje);
			rmap.put("xjze", xjze);
			rmap.put("qmdjw", qmdjw);
			String limit =" limit 0,1";
		    WherePrams wherePrams= Method.where("orgId", C.EQ, orgId).orderBy(" KJYEAR_MOTH desc");
        	List<TBasiCashFlow> tBasiCashFlowList=tBasiCashFlowService.listPage(wherePrams, limit);
        	String dt="";
        	if(tBasiCashFlowList!=null && tBasiCashFlowList.size()>0){
        		TBasiCashFlow tb=tBasiCashFlowList.get(0);
        		if(tb.getImpTime()!=null){
        			dt=tb.getKjyearMoth();
        		}
        	}//拼接一个查询
        	rmap.put("times", dt);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",rmap);
			return robj.toString();
		} catch (Exception e) {
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "getsmscode.api:",e.getMessage());
			return robj.toString();
		}
	}

	@AppControllerLog(description = "现金流表圆柱展示")
	@ApiOperation(value = "现金流表圆柱展示{参数内容类型:application/json}", notes = "输入开始时间与结束时间", response = ResultObj.class, httpMethod = "POST")
	@RequestMapping(value="/getCylinder.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String getCylinder(@ApiParam(required = true, value = "orgId(用户id),startTime(开始时间),endTime(结束时间ssss)")
									  String orgId,String startTime,String endTime,String useArea){
		try {
			if(startTime==null || startTime.length()==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NULL, "开始时间不能为空");
				return robj.toString();
			}
			if(endTime==null || endTime.length()==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NULL, "结束时间不能为空");
				return robj.toString();
			}
			if(useArea==null || useArea.length()==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NULL, "类别不能为空");
				return robj.toString();
			}
			startTime=startTime+"-01";
			endTime=endTime+"-01";
			List<Map<String,Object>> rlist=new ArrayList<>();
			Map<String,Object> remap = new HashMap<>();
			String[] useAreas=useArea.split(",");
			for(int i=0;i<useAreas.length;i++){
				Map<String,Object> rmap = new HashMap<>();
				Map<String,Object> map = new HashMap<>();
				/*Date ed=DateUtil.stringToDate(endTime, "yyyy-MM");
				Calendar calen = Calendar.getInstance();
				calen.setTime(ed);
				int year=calen.get(Calendar.YEAR);*/
				map.put("orgId", orgId);
				//得到结果
				map.put("startTime", startTime);
				map.put("endTime", endTime);
				map.put("useArea",  useAreas[i]);
				List<ResultDto> zcLists=reportResultService.selectValueSumDetailAll(map);
				map.put("startTime", DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(startTime,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4), "yyyy-MM-dd"));
				map.put("endTime", DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4), "yyyy-MM-dd"));
				List<ResultDto> zcUpLists=reportResultService.selectValueSumDetailAll(map);
				rmap.put("useArea", useAreas[i]);
				rmap.put("cylinder", zcLists);
				rmap.put("cylinderUp", zcUpLists);
				rlist.add(rmap);
			}
			remap.put("resultCy", rlist);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",remap);
			return robj.toString();
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "getsmscode.api:",e.getMessage());
			return robj.toString();
		}
	}


	@AppControllerLog(description = "统一首页查询线条展示")
	@ApiOperation(value = "统一首页查询线条展示{参数内容类型:application/json}", notes = "输入开始时间与结束时间", response = ResultObj.class, httpMethod = "POST")
	@RequestMapping(value="/getLine.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String getLine(@ApiParam(required = true, value = "orgId(用户id),startTime(开始时间),endTime(结束时间)")
								  String orgId,String startTime,String endTime,String useArea){
		try {
			if(startTime==null || startTime.length()==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NULL, "开始时间不能为空");
				return robj.toString();
			}
			if(endTime==null || endTime.length()==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NULL, "结束时间不能为空");
				return robj.toString();
			}
			if(useArea==null || useArea.length()==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NULL, "类别不能为空");
				return robj.toString();
			}

			//startTime=startTime+"-01";
			endTime=endTime+"-01";
			startTime=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd", -11, 3), "yyyy-MM-dd");
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Map<String,Object> rmap = new HashMap<>();
			Map<String,Object> map = new HashMap<>();
			Date st=DateUtil.stringToDate(startTime, "yyyy-MM-dd");
			Date ed=DateUtil.stringToDate(endTime, "yyyy-MM-dd");
			String startDt=DateUtil.dateToString(DateUtil.addTime(st,"yyyy-MM-dd",-1,4), "yyyy-MM-dd");
			String endDt=DateUtil.dateToString(DateUtil.addTime(ed,"yyyy-MM-dd",-1,4), "yyyy-MM-dd");
			map.put("orgId", orgId);
			//得到结果
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("useArea", useArea);
			List<ResultDto> zcLists=reportResultService.selectValueMonthResultDto(map);
			List<Date> dtList=DateUtil.getDatesBetweenTwoDate(startTime,endTime);
			List<ResultDto> zcList=new ArrayList<ResultDto>();
			map.put("startTime", startDt);
			map.put("endTime", endDt);
			List<ResultDto> zcUpLists=reportResultService.selectValueMonthResultDto(map);
			List<Date> dtUpList=DateUtil.getDatesBetweenTwoDate(startDt,endDt);
			List<ResultDto> zcUpList=new ArrayList<ResultDto>();

			if(dtList!=null){
				for(int i=0;i<dtList.size();i++){
					Date des=dtList.get(i);
					String strDes=DateUtil.dateFormatToString(des, "yyyy-MM");
					int zcpd=0;
					if(zcLists!=null && zcLists.size()>0){
						for(ResultDto zc : zcLists){
							if(strDes.equals(zc.getYearMoth())){
								zcList.add(zc);
								zcpd=1;
							}
						}
					}
					if(zcpd==0){
						ResultDto zcr=new ResultDto();
						Calendar calen = Calendar.getInstance();
						calen.setTime(des);
						zcr.setMoth(calen.get(Calendar.MONTH)+1);
						zcr.setYear(calen.get(Calendar.YEAR));
						zcr.setYearMoth(DateUtil.dateFormatToString(des, "yyyy-MM"));
						zcList.add(zcr);
					}
				}

			}
			if(dtUpList!=null){
				for(int i=0;i<dtUpList.size();i++){
					Date des=dtList.get(i);
					String strDes=DateUtil.dateFormatToString(des, "yyyy-MM");
					int zcpd=0;
					if(zcUpLists!=null && zcUpLists.size()>0){
						for(ResultDto zc : zcUpLists){
							if(strDes.equals(zc.getYearMoth())){
								zcUpList.add(zc);
								zcpd=1;
							}
						}
					}
					if(zcpd==0){
						ResultDto zcr=new ResultDto();
						Calendar calen = Calendar.getInstance();
						calen.setTime(des);
						zcr.setMoth(calen.get(Calendar.MONTH)+1);
						zcr.setYear(calen.get(Calendar.YEAR));
						zcr.setYearMoth(DateUtil.dateFormatToString(des, "yyyy-MM"));
						zcUpList.add(zcr);
					}


				}

			}
			rmap.put("line", zcList);
			rmap.put("lineUp", zcUpList);
			rmap.put("useArea", useArea);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",rmap);
			return robj.toString();
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "getsmscode.api:",e.getMessage());
			return robj.toString();
		}
	}


	@AppControllerLog(description = "现金流表明细查询")
	@ApiOperation(value = "现金流表明细信息{参数内容类型:application/json}", notes = "输入开始时间&结束时间&公司id", response = ResultObj.class, httpMethod = "POST")
	@RequestMapping(value="/getCashFlowDetailMsg.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String getCashFlowDetailMsg(String startTime , String endTime ,String type, String orgId , HttpServletResponse response,HttpServletRequest request){
		try {
			/*ResultObj reObj = new ResultObj();
			//检查条件参数是否为空
			if(checkCondition(reObj, startTime, endTime, orgId)){
				return reObj ;
			}
			List<ResultDto> curs =reportResultService.selectDetailMsg(startTime,endTime,orgId,CASHFLOW_REPORT_TYPE);
			reObj.setResultCode(ResultCode.STATE_SUCCESS);
			reObj.setMsg("查询成功!");
			if(curs==null || curs.size()==0){
				reObj.setData(null);
			}else{
				reObj.setData(DataChangeUtils.getDatas(curs, DataChangeUtils.DATA_CHANGE_TYPE_BY_CODE));
			}
			//reObj.setData(DataChangeUtils.getDataByMdyRule(curs,DataChangeUtils.CASH_FLOW_REPORT_TYPE));
			return reObj ;*/
			if(type==null || type.length()==0){
				type="3";
			}
			Map<String,Object> rmap = new HashMap<>();
			Map<String,Object> rmap1 = new HashMap<>();
			Map<String,Object> rmap2 = new HashMap<>();
			Map<String,Object> remap = new HashMap<>();
			Map<String,Object> map = new HashMap<>();
			startTime=startTime+"-01";
			endTime=endTime+"-01";
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("orgId", orgId);
			map.put("reportType", 3);
			map.put("useArea", 31);
			//经营活动流入
			ResultDto jyhdlrTotal=reportResultService.selectValueSumDetailAllTotal(map);
			//map.remove("likeUseAreas");
			map.put("useArea", null);
			Map<String,Object> jyhdlr = new HashMap<>();
			if(jyhdlrTotal!=null){
				jyhdlr.put("total", jyhdlrTotal);
				map.put("likeUseAreas", 31);
				map.put("startTime", startTime);
				map.put("endTime", endTime);
				map.put("useArea", null);
				//map.put("useArea", "10");
				List<ResultDto> xq=reportResultService.selectValueSumDetailAll(map);
				jyhdlr.put("xq", xq);
				rmap.put("jyhdlr", jyhdlr);
				map.put("likeUseAreas", null);
			}else{
				jyhdlr.put("total", null);
				jyhdlr.put("xq", null);
				rmap.put("jyhdlr", jyhdlr);
			}

			//经营活动流出
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("useArea", 32);
			ResultDto jyhdlcTotal=reportResultService.selectValueSumDetailAllTotal(map);
			map.remove("likeUseAreas");
			map.put("useArea", null);
			Map<String,Object> jyhdlc = new HashMap<>();
			if(jyhdlcTotal!=null){
				jyhdlc.put("total", jyhdlcTotal);
				//map.put("useArea", "10");
				map.put("likeUseAreas", 32);
				map.put("useArea", null);
				map.put("startTime", startTime);
				map.put("endTime", endTime);
				List<ResultDto> xq=reportResultService.selectValueSumDetailAll(map);
				jyhdlc.put("xq", xq);
				rmap.put("jyhdlc", jyhdlc);
				map.put("likeUseAreas", null);
			}else{
				jyhdlc.put("total", null);
				jyhdlc.put("xq", null);
				rmap.put("jyhdlc", jyhdlc);
			}
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("useArea", 42);                   //经营活动产生的现金流量净额
			//Map<String, Object> jyhdcsMap=new HashMap<String, Object>();
			List<ResultDto> jyhdcs=reportResultService.selectValueSumDetailAll(map);
			/*if(jyhdcs!=null && jyhdcs.size()>0){
				jyhdcsMap.put("total", jyhdcs.get(0));
				remap.put("jyhdcs", jyhdcsMap);
			}else{
				remap.put("jyhdcs", null);
			}*/
			//ResultDto jy=Dates.getResultDto(jyhdlrTotal, jyhdlcTotal);
			rmap.put("alltotal", jyhdcs);
			remap.put("jy", rmap);
			//投资活动流入
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("useArea", 33);
			ResultDto tzhdlrTotal=reportResultService.selectValueSumDetailAllTotal(map);
			map.remove("likeUseAreas");
			map.put("useArea", null);
			Map<String,Object> tzhdlr = new HashMap<>();
			if(tzhdlrTotal!=null){
				tzhdlr.put("total", tzhdlrTotal);
				//map.put("useArea", "10");
				map.put("likeUseAreas", 33);
				map.put("useArea", null);
				map.put("startTime", startTime);
				map.put("endTime", endTime);
				List<ResultDto> xq=reportResultService.selectValueSumDetailAll(map);
				tzhdlr.put("xq", xq);
				rmap1.put("tzhdlr", tzhdlr);
				map.put("likeUseAreas", null);
			}else{
				tzhdlr.put("total", null);
				tzhdlr.put("xq", null);
				rmap1.put("tzhdlr", tzhdlr);
			}

			//投资活动流出
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("useArea", 34);
			ResultDto tzhdlcTotal=reportResultService.selectValueSumDetailAllTotal(map);
			map.remove("likeUseAreas");
			map.put("useArea", null);
			Map<String,Object> tzhdlc = new HashMap<>();
			if(tzhdlcTotal!=null){
				tzhdlc.put("total", tzhdlcTotal);
				//map.put("useArea", "10");
				map.put("likeUseAreas", 34);
				map.put("useArea", null);
				map.put("startTime", startTime);
				map.put("endTime", endTime);
				List<ResultDto> xq=reportResultService.selectValueSumDetailAll(map);
				tzhdlc.put("xq", xq);
				rmap1.put("tzhdlc", tzhdlc);
				map.put("likeUseAreas", null);
			}else{
				tzhdlc.put("total", null);
				tzhdlc.put("xq", null);
				rmap1.put("tzhdlc", tzhdlc);
			}
			//ResultDto tz=Dates.getResultDto(tzhdlrTotal, tzhdlcTotal);
			map.put("useArea", 43);         //投资活动产生的现金流量净额
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			List<ResultDto> tzhdcs=reportResultService.selectValueSumDetailAll(map);
			/*if(tzhdcs!=null && tzhdcs.size()>0){
				remap.put("tzhdcs", tzhdcs.get(0));
			}else{
				remap.put("tzhdcs", null);
			}
			*/
			rmap1.put("alltotal", tzhdcs);
			remap.put("tz", rmap1);
			//筹资活动流入
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("useArea", 35);
			ResultDto czhdlrTotal=reportResultService.selectValueSumDetailAllTotal(map);
			map.remove("likeUseAreas");
			map.put("useArea", null);
			Map<String,Object> czhdlr = new HashMap<>();
			if(czhdlrTotal!=null){
				czhdlr.put("total", czhdlrTotal);
				//map.put("useArea", "10");
				map.put("likeUseAreas", 35);
				map.put("useArea", null);
				map.put("startTime", startTime);
				map.put("endTime", endTime);
				List<ResultDto> xq=reportResultService.selectValueSumDetailAll(map);
				czhdlr.put("xq", xq);
				rmap2.put("czhdlr", czhdlr);
				map.put("likeUseAreas", null);
			}else{
				czhdlr.put("total", null);
				czhdlr.put("xq", null);
				rmap2.put("czhdlr", czhdlr);
			}

			//筹资活动流出
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("useArea", 36);
			ResultDto czhdlcTotal=reportResultService.selectValueSumDetailAllTotal(map);
			map.remove("likeUseAreas");
			map.put("useArea", null);
			Map<String,Object> czhdlc = new HashMap<>();
			if(czhdlcTotal!=null){
				czhdlc.put("total", czhdlcTotal);
				map.put("likeUseAreas", 36);
				map.put("useArea", null);
				//map.put("useArea", "10");
				map.put("startTime", startTime);
				map.put("endTime", endTime);
				List<ResultDto> xq=reportResultService.selectValueSumDetailAll(map);
				czhdlc.put("xq", xq);
				rmap2.put("czhdlc", czhdlc);
				map.put("likeUseAreas", null);
			}else{
				czhdlc.put("total", null);
				czhdlc.put("xq", null);
				rmap2.put("czhdlc", czhdlc);
			}
			//ResultDto cz=Dates.getResultDto(tzhdlrTotal, tzhdlcTotal);
			map.put("useArea", 37);      //筹资活动产生的现金流量净额
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			List<ResultDto> czhdcs=reportResultService.selectValueSumDetailAll(map);
			/*if(czhdcs!=null && czhdcs.size()>0){
				remap.put("czhdcs", czhdcs.get(0));
			}else{
				remap.put("czhdcs", null);
			}*/
			rmap2.put("alltotal", czhdcs);
			remap.put("cz", rmap2);


			/*map.put("useArea", 43);         //投资活动产生的现金流量净额
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			List<ResultDto> tzhdcs=reportResultService.selectValueSumDetailAll(map);
			if(tzhdcs!=null && tzhdcs.size()>0){
				remap.put("tzhdcs", tzhdcs.get(0));
			}else{
				remap.put("tzhdcs", null);
			}
			*/
			/*map.put("useArea", 37);      //筹资活动产生的现金流量净额
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			List<ResultDto> czhdcs=reportResultService.selectValueSumDetailAll(map);
			if(czhdcs!=null && czhdcs.size()>0){
				remap.put("czhdcs", czhdcs.get(0));
			}else{
				remap.put("czhdcs", null);
			}*/

			map.put("useArea", 38);           //汇率变动对现金及现金等价物的影响
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			List<ResultDto> hlv=reportResultService.selectValueSumDetailAll(map);
			remap.put("hlv", hlv);
			if(hlv!=null && hlv.size()>0){
				remap.put("hlv", hlv.get(0));
			}else{
				remap.put("hlv", null);
			}
			//现金及现金等价物净增加额
			map.put("useArea", 39);
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			List<ResultDto> xjjdjw=reportResultService.selectValueSumDetailAll(map);
			if(xjjdjw!=null && xjjdjw.size()>0){
				remap.put("xjjdjw", xjjdjw.get(0));
			}else{
				remap.put("xjjdjw", null);
			}

			map.put("useArea", 40);              //期初现金及现金等价物余额
			map.put("startTime", null);
			map.put("endTime", null);
			//String st=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(startTime,"yyyy-MM-dd"),"yyyy-MM-dd",-1,3), "yyyy-MM-dd");
			map.put("endTimes", startTime);
			List<ResultDto> qcxianj=reportResultService.selectValueSumDetailAll(map);
			if(qcxianj!=null && qcxianj.size()>0){
				remap.put("qcxianj", qcxianj.get(0));
			}else{
				remap.put("qcxianj", null);
			}
			map.put("endTimes", null);

			map.put("useArea", 41);             //期末现金及现金等价物余额
			//map.put("startTime", startTime);
			//map.put("endTime", endTime);
			map.put("endTimes", endTime);
			List<ResultDto> qmxjdjw=reportResultService.selectValueSumDetailAll(map);
			if(qmxjdjw!=null && qmxjdjw.size()>0){
				remap.put("qmxjdjw", qmxjdjw.get(0));
			}else{
				remap.put("qmxjdjw", null);
			}
			map.put("endTimes", null);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",remap);
			return robj.toString();
		}catch (Exception e) {
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "getProfitMainMsg.api:",e.getMessage());
			return robj.toString();
		}
	}

}

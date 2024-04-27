package com.qst.financial.controller.api;


import com.qst.financial.annotation.AppControllerLog;
import com.qst.financial.dto.Result;
import com.qst.financial.dto.ResultDto;
import com.qst.financial.modle.subject.TBasiProfit;
import com.qst.financial.service.subject.*;
import com.qst.financial.sql.C;
import com.qst.financial.sql.Method;
import com.qst.financial.sql.WherePrams;
import com.qst.financial.util.LineUtile;
import com.qst.financial.util.RNames;
import com.qst.financial.util.common.DateUtil;
import com.qst.financial.util.common.ResultCode;
import com.qst.financial.util.json.ResultObj;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 所有明细相关
 * @author yj
 *
 */
@Controller
@RequestMapping("/api/detail")
public class DetailController {

	@Autowired
	private HomeService homeService;
	@Autowired
	private BilvService bilvService;

	@Autowired
	private ReportResultService reportResultService;

	@Autowired
	private TBasiProfitService tBasiProfitService;

	@Autowired
	private TModeService tModeService;

	@Autowired
	private  DetailService detailService;

	@AppControllerLog(description = "比率分析")
	@ApiOperation(value = "比率分析{参数内容类型:application/json}", notes = "输入开始时间与结束时间", response = ResultObj.class, httpMethod = "POST")
	@RequestMapping(value="/bilv.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String bilv(@ApiParam(required = true, value = "orgId(用户id),startTime(开始时间),endTime(结束时间ssss)")
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
			Map<String, Object> rmap=bilvService.getBilv(startTime, endTime, orgId);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",rmap);
			return robj.toString();
		}catch (Exception e) {
			e.printStackTrace();
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "getsmscode.api:",e.getMessage());
			return robj.toString();
		}

	}
	@AppControllerLog(description = "明细查询线条展示与资金总额")
	@ApiOperation(value = "明细查询线条展示与资金总额{参数内容类型:application/json}", notes = "输入开始时间与结束时间", response = ResultObj.class, httpMethod = "POST")
	@RequestMapping(value="/getLineAndMoney.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String getLineAndMoney(@ApiParam(required = true, value = "orgId(用户id),startTime(开始时间),endTime(结束时间ssss)")
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
			String strDt= DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime, "yyyy"), "yyyy", -11, 3), "yyyy");
			endTime=endTime+"-01";
			startTime=startTime+"-01";
			String dws="";
			String startTimes=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd", -11, 3), "yyyy-MM-dd");
			Map<String,Object> rmap = new HashMap<>();
			Map<String,Object> rmap2 = new HashMap<>();
			Map<String,Object> rmap3 = new HashMap<>();
			Map<String,Object> rmap4 = new HashMap<>();
			Map<String,Object> map = new HashMap<>();
			Map<String,Object> maps = new HashMap<>();
			Date st=DateUtil.stringToDate(startTimes, "yyyy-MM-dd");
			Date ed=DateUtil.stringToDate(endTime, "yyyy-MM-dd");
			String startDt=DateUtil.dateToString(DateUtil.addTime(st,"yyyy-MM-dd",-1,4), "yyyy-MM-dd");
			String endDt=DateUtil.dateToString(DateUtil.addTime(ed,"yyyy-MM-dd",-1,4), "yyyy-MM-dd");
			maps.put("useArea", useArea);
			List<ResultDto> resultDtoList=reportResultService.selectValueSumDetail(maps);
			if(resultDtoList==null || resultDtoList.size()==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NULL, "参数错误");
				return robj.toString();
			}
			ResultDto resultDto=resultDtoList.get(0);
			String survey="";
			String warn="";
			String reportType="";
			String clzz="";
			String remark="";
			String name="";
			String km1="";
			String km2="";
			String km3="";
			String parentId;
			survey =resultDto.getSurvey();
			warn=resultDto.getWarn();
			reportType=resultDto.getReportType();
			clzz=resultDto.getClzz();
			remark=resultDto.getRemark();
			name=resultDto.getCodeName();
			km1=resultDto.getKm1();
			km2=resultDto.getKm2();
			km3=resultDto.getKm3();
			parentId=resultDto.getParentId();
			map.put("orgId", orgId);
			//map.put("startTimes", startTime);
			//得到结果
//			if(reportType.equals("1")){            //如果是资产负债
//				map.put("endTimes", endTime);
//			}else{
//				if(useArea.equals("41")){
//					map.put("endTimes", endTime);
//				}else if(useArea.equals("40")){
//					map.put("endTimes", startTime);
//				}else{
					map.put("startTime", startTime);
					map.put("endTime", endTime);
//				}
//			}
			map.put("useArea", useArea);
			ResultDto zcs=reportResultService.selectValueSumResultDto(map);//可以替换
//			String str=useArea.substring(0,2);
			String money="0";
//			if(str.equals("bl")){
//				ResultDto bl=bilvService.getBilvResult(startTime, endTime, orgId, useArea);
//				if(bl!=null){
//					if(bl.getBigSumMoney()!=null){
//						money=bl.getBigSumMoney().toString();
//					}
//				}
//			}else{
//				if(zcs!=null){
					money=zcs.getSumMoney();                //得到总金额
//				}
//			}
			Map<String,Object> map2 = new HashMap<>();
			map2.put("endTime", endTime);
			Date st2=DateUtil.addTime(DateUtil.stringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd", -11, 3);
			String strSt2=DateUtil.dateToString(st2,"yyyy-MM-dd");
			map2.put("startTime", strSt2);
			map2.put("orgId", orgId);
			map2.put("useArea", useArea);
			List<ResultDto> zcLists=reportResultService.selectValueMonthResultDto(map2);
			//String str=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd", -11, 3), "yyyy-MM-dd");
			List<Date> dtList=DateUtil.getDatesBetweenTwoDate(DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd", -11, 3), "yyyy-MM-dd"),endTime);
			List<ResultDto> zcList=new ArrayList<ResultDto>();
			if(reportType.equals("1")){            //如果是资产负债
				map.put("endTimes", endDt);
			}else{
				map.put("startTime", startDt);
				map.put("endTime", endDt);
			}
				dws="万元";
				zcList= LineUtile.resultLine(dtList, zcLists);
			String limit =" limit 0,1";
			WherePrams wherePrams= Method.where("orgId", C.EQ, orgId).orderBy(" KJYEAR_MOTH desc");
			List<TBasiProfit> tBasiProfitList=tBasiProfitService.listPage(wherePrams, limit);
			String dt="";
			if(tBasiProfitList!=null && tBasiProfitList.size()>0){
				TBasiProfit tb=tBasiProfitList.get(0);
				if(tb.getImpTime()!=null){
					dt=tb.getKjyearMoth();
				}
			}
			List<Result> list=new ArrayList<Result>();
			Result r1=new Result();
			r1.setType(1);
			r1.setDw(dws);
			String name2=resultDto.getCodeName();
			name2= RNames.rname(name2);
			rmap.put("title", name2);
			rmap.put("content", money);
			rmap.put("times", dt);
			r1.setData(rmap);
			list.add(r1);
			if(survey!=null && survey.length()>0){
				List<String> reList =new ArrayList<String>();


						reList = tModeService.getContent(startTime, endTime, orgId, reportType,survey);


				if(reList==null || reList.size()==0){
					reList.add("无概要。");
				}
				Result r4=new Result();
				r4.setType(3);
				rmap4.put("title", "概况");
				rmap4.put("content", reList);
				r4.setData(rmap4);
				list.add(r4);
			}
			if(warn!=null && warn.length()>0){

					List<String> reList =new ArrayList<String>();

							reList = tModeService.getContent(startTime, endTime, orgId, reportType,warn);

					if(reList==null || reList.size()==0){
						reList.add("无预警。");
					}
					Result r3=new Result();
					r3.setType(3);
					rmap3.put("title", "预警");
					rmap3.put("content", reList);
					r3.setData(rmap3);
					list.add(r3);
//				}

			}

			Result r2=new Result();
			r2.setType(2);
			r2.setDw(dws);
			String names=resultDto.getCodeName()+"变动";
			rmap2.put("title", names);
			rmap2.put("content", zcList);
			r2.setData(rmap2);
			list.add(r2);
			//增加其他图形
				list.addAll(detailService.getKmDetail(reportType,clzz, startTime, endTime, orgId,name,km1,km2,km3,useArea));


			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",list);
			return robj.toString();
		}catch (Exception e) {
			e.printStackTrace();
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "getsmscode.api:",e.getMessage());
			return robj.toString();
		}
	}
	@AppControllerLog(description = "首页")
	@ApiOperation(value = "首页{参数内容类型:application/json}", notes = "输入开始时间与结束时间", response = ResultObj.class, httpMethod = "POST")
	@RequestMapping(value="/homePage.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody 
	public String homePage(@ApiParam(required = true, value = "orgId(用户id),startTime(开始时间),endTime(结束时间ssss)") 
		String orgId,String startTime,String endTime,String type){
		try {
			if(startTime==null || startTime.length()==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NULL, "开始时间不能为空");
				return robj.toString();
			}
			if(endTime==null || endTime.length()==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NULL, "结束时间不能为空");
				return robj.toString();
			}
			if(type==null || type.length()==0){
				type="3";
			}
			HashMap<String, Object> rmap=(HashMap<String, Object>) homeService.getHomes(startTime, endTime, orgId);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",rmap);
			return robj.toString();
		}catch (Exception e) {
			e.printStackTrace();
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "getsmscode.api:",e.getMessage());
			return robj.toString();
		}
	}

}

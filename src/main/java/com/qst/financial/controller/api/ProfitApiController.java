package com.qst.financial.controller.api;

import com.qst.financial.annotation.AppControllerLog;
import com.qst.financial.dto.ResultDto;
import com.qst.financial.modle.subject.TBasiProfit;
import com.qst.financial.service.subject.ReportResultService;
import com.qst.financial.service.subject.TBasiProfitService;
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
 *利润表 app接口
 */
@Controller
@RequestMapping("/api/profit")
public class ProfitApiController {
	private static final Logger log=Logger.getLogger(ProfitApiController.class);
	
	/**
	 * 报表类型--利润表
	 */
	public static final String PROFIT_REPORT_TYPE = "2" ;
	
	@Autowired 
	private ReportResultService reportResultService ;
	@Autowired
	private TBasiProfitService tBasiProfitService;

	@Value("${useArea.lr.yysy}")
	String useArea_yysy;  //营业收入
	@Value("${useArea.lr.yycb}")
	String useArea_yycb; //营业成本
	@Value("${useArea.lr.yylr}")
	String useArea_yylr; //营业利润
	@Value("${useArea.lr.lrze}")
	String useArea_lrze; //利润总额
	@Value("${useArea.lr.jlr}")
	String useArea_jlr; //净利润


	
	@AppControllerLog(description = "利润表明细查询,新修版")
	@ApiOperation(value = "利润表明细信息,新修版{参数内容类型:application/json}", notes = "输入开始时间&结束时间&公司id", response = ResultObj.class, httpMethod = "POST")
	@RequestMapping(value="/getProfitDetailMsg.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody 
	public String getProfitDetailMsg(String startTime , String endTime , String orgId , HttpServletResponse response,HttpServletRequest request){
		try{
			if(startTime==null || startTime.length()==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NULL, "开始时间不能为空");
				return robj.toString();
			}
			if(endTime==null || endTime.length()==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NULL, "结束时间不能为空");
				return robj.toString();
			}

			startTime=startTime+"-01";
			endTime=endTime+"-01";
			Map<String,Object> rmap = new HashMap<>();
			Map<String,Object> map = new HashMap<>();
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("orgId", orgId);
			map.put("reportType", 2);
			//营业收入
			map.put("useArea", useArea_yysy);
			Map<String,Object> yysr = new HashMap<>();
			ResultDto yysrTotal=reportResultService.selectValueSumDetailAllTotal(map);
			yysrTotal.setUseArea(useArea_yysy);
			yysrTotal.setCodeName("营业收入");
			yysrTotal.setIsDetails(1);
			if(yysrTotal!=null){
				yysr.put("total", yysrTotal);
				//map.put("useArea", "10"); 
				map.put("useArea", null); 
				map.put("likeUseAreas",useArea_yysy);
				map.put("startTime", startTime);
				map.put("endTime", endTime);
				List<ResultDto> xq=reportResultService.selectValueSumDetailAll(map);
				yysr.put("xq", xq);
				rmap.put("yysr", yysr);
				map.put("likeUseAreas", null);
			}else{
				yysr.put("total", null);
				yysr.put("xq", null);
				rmap.put("yysr", yysr);
			}
		
			//营业成本
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("useArea", useArea_yycb);
			Map<String,Object> yycb = new HashMap<>();
			ResultDto yycbTotal=reportResultService.selectValueSumDetailAllTotal(map);
			yycbTotal.setUseArea(useArea_yycb);
			yycbTotal.setCodeName("营业成本");
			yycbTotal.setIsDetails(1);
			if(yycbTotal!=null){
				yycb.put("total", yycbTotal);
				//map.put("useArea", "11"); 
				map.put("useArea", null); 
				map.put("likeUseAreas", useArea_yycb);
				map.put("startTime", startTime);
				map.put("endTime", endTime);
				List<ResultDto> xq=reportResultService.selectValueSumDetailAll(map);
				yycb.put("xq", xq);
				map.put("useArea", null);
				rmap.put("yycb", yycb);
				map.put("likeUseAreas", null);
			}else{
				yycb.put("total", null);
				yycb.put("xq", null);
				rmap.put("yycb", yycb);
			}
		
			//营业利润
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			//map.put("dataType", "12"); 
			map.put("useArea", useArea_yylr);
			Map<String,Object> yylr = new HashMap<>();
			//List<ResultDto> ldfzTotal=reportResultService.selectValueSumDetailAll(map);
			ResultDto yylrTotal=reportResultService.selectValueSumDetailAllTotal(map);
			yylrTotal.setUseArea(useArea_yylr);
			yylrTotal.setCodeName("营业利润");
			yylrTotal.setIsDetails(1);
			if(yylrTotal!=null){
				yylr.put("total", yylrTotal);
				map.put("useArea", null); 
				map.put("likeUseAreas", useArea_yylr);
				//map.put("useArea", "10"); 
				map.put("startTime", startTime);
				map.put("endTime", endTime);
				List<ResultDto> xq=reportResultService.selectValueSumDetailAll(map);
				yylr.put("xq", xq);
				rmap.put("yylr", yylr);
				map.put("likeUseAreas", null);
			}else{
				yylr.put("total", null);
				yylr.put("xq", null);
				rmap.put("yylr", yylr);
			}
		
			//利润总额
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			//map.put("dataType", "13"); 
			map.put("useArea", useArea_lrze);
			Map<String,Object> lrze = new HashMap<>();
			ResultDto lrzeTotal=reportResultService.selectValueSumDetailAllTotal(map);
			lrzeTotal.setCodeName("利润总额");
			lrzeTotal.setUseArea(useArea_lrze);
			lrzeTotal.setIsDetails(1);
			//List<ResultDto> gdfzTotal=reportResultService.selectValueSumDetailAll(map);
			if(lrzeTotal!=null){
				lrze.put("total", lrzeTotal);
				map.put("useArea", null); 
				map.put("likeUseAreas", useArea_lrze);
				map.put("startTime", startTime);
				map.put("endTime", endTime);
				List<ResultDto> xq=reportResultService.selectValueSumDetailAll(map);
				lrze.put("xq", xq);
				rmap.put("lrze", lrze);
				map.put("likeUseAreas", null);
			}else{
				lrze.put("total", null);
				lrze.put("xq", null);
				rmap.put("lrze", lrze);
			}
			
			//净利润
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			//map.put("dataType", "13"); 
			map.put("useArea", useArea_jlr);
			Map<String,Object> jlr = new HashMap<>();
			ResultDto jlrTotal=reportResultService.selectValueSumDetailAllTotal(map);
			jlrTotal.setUseArea(useArea_jlr);
			jlrTotal.setCodeName("净利润");
			jlrTotal.setIsDetails(1);
			//List<ResultDto> gdfzTotal=reportResultService.selectValueSumDetailAll(map);
			if(jlrTotal!=null){
				jlr.put("total", jlrTotal);
				map.put("useArea", null); 
				map.put("likeUseAreas", useArea_jlr);
				map.put("startTime", startTime);
				map.put("endTime", endTime);
				List<ResultDto> xq=reportResultService.selectValueSumDetailAll(map);
				jlr.put("xq", xq);
				rmap.put("jlr", jlr);
				map.put("likeUseAreas", null);
			}else{
				jlr.put("total", null);
				jlr.put("xq", null);
				rmap.put("jlr", jlr);
			}
			
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",rmap);
			return robj.toString();
		} catch (Exception e) {
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
	@AppControllerLog(description = "利润表首页查询")
	@ApiOperation(value = "利润表首页信息{参数内容类型:application/json}", notes = "输入开始时间&结束时间&公司id", response = ResultObj.class, httpMethod = "POST")
	@RequestMapping(value="/getProfitMainMsg.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String getProfitMainMsg(String startTime , String endTime , String orgId , HttpServletResponse response,HttpServletRequest request){
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
			ResultObj reObj = new ResultObj();

			Map<String,Object> map = new HashMap<String,Object>() ;
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("orgId", orgId);

			Map<String,Object> rmap = new HashMap<>();
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("orgId", orgId);
			//营业收入
			map.put("useArea", useArea_yysy);
			//map.put("useArea", "01");
			ResultDto yysr=reportResultService.selectValueSumDetailAllTotal(map);
			yysr.setCode("yysr");
			yysr.setCodeName("经营活动产生的现金流量净额");
			//营业成本
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("useArea", useArea_yycb);
			//map.put("useArea", "02");
			ResultDto yycb=reportResultService.selectValueSumDetailAllTotal(map);
			yycb.setCode("tzje");
			yycb.setCodeName("投资活动产生的现金流量净额");
			//营业利润
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("useArea", useArea_yylr);
			ResultDto yylr=reportResultService.selectValueSumDetailAllTotal(map);
			yylr.setCode("yylr");
			yylr.setCodeName("筹资活动产生的现金流量净额");
			//利润总额
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("useArea", useArea_lrze);
			ResultDto lrze=reportResultService.selectValueSumDetailAllTotal(map);
			lrze.setCode("lrze");
			lrze.setCodeName("现金及现金等价物净增额");
			//净利润
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("useArea",useArea_jlr);
			ResultDto jlr=reportResultService.selectValueSumDetailAllTotal(map);
			map.put("endTimes", null);
			jlr.setCode("jlr");
			jlr.setCodeName("期末现金及现金等价物余额");
			rmap.put("yysr", yysr);
			rmap.put("yycb", yycb);
			rmap.put("yylr", yylr);
			rmap.put("lrze", lrze);
			rmap.put("jlr", jlr);
			String limit =" limit 0,1";
			WherePrams wherePrams= Method.where("orgId", C.EQ, orgId).orderBy(" KJYEAR_MOTH desc");
			List<TBasiProfit> tBasiProfitList=tBasiProfitService.listPage(wherePrams, limit);
			String dt="";
			if(tBasiProfitList!=null && tBasiProfitList.size()>0){
				TBasiProfit tb=tBasiProfitList.get(0);
				if(tb.getImpTime()!=null){
					dt=tb.getKjyearMoth();
				}
			}//拼接一个查询
			rmap.put("times", dt);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",rmap);
			return robj.toString();
		}catch (Exception e) {
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "getProfitMainMsg.api:",e.getMessage());
			return robj.toString();
		}
	}

	

}

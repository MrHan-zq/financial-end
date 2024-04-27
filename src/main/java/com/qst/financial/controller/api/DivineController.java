package com.qst.financial.controller.api;


import com.qst.financial.dto.FundResunlt;
import com.qst.financial.dto.ResultDto;
import com.qst.financial.dto.ResultMessage;
import com.qst.financial.service.subject.ReportResultService;
import com.qst.financial.util.common.ResultCode;
import com.qst.financial.util.json.ResultObj;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预测相关
 * @author yj
 *
 */
@Controller
@RequestMapping("/api/divine")
public class DivineController {
	/**
	 * 预测1展示
	 */
	private static final Logger log=Logger.getLogger(DivineController.class);
	@Autowired 
	private ReportResultService reportResultService ;
	@ApiOperation(value = "预测{参数内容类型:application/json}", notes = "输入开始时间与结束时间", response = ResultObj.class, httpMethod = "POST")
	@RequestMapping(value="/getDivine.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody 
	public String getDivine(@ApiParam(required = true, value = "orgId(用户id),startTime(开始时间),endTime(结束时间ssss)") 
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
			Map<String,Object> map = new HashMap<>();
			map.put("orgId", orgId);
			int sYear=Integer.parseInt(startTime);
			int eYear=Integer.parseInt(endTime);
			int count=eYear-sYear+1;
			List<Map<String,Object>> rList=new ArrayList<Map<String,Object>>();
			//Map<String,Object> remap2 = new HashMap<>();
			for(int i=0;i<count;i++){
				Map<String,Object> remap = new HashMap<>();
				Map<String,Object> rmap = new HashMap<>();
				int year=sYear+i;
				remap.put("key", year);
				map.put("year", year);
				map.put("isW", "1");
				//营业收入
				map.put("useArea", "21"); 
				Map<String,Object> yysr = new HashMap<>();
				ResultDto yysrTotal=reportResultService.selectExpectValueSumDetailAllTotal(map);
				if(yysrTotal!=null){
					yysr.put("total", yysrTotal);
					//map.put("useArea", "10"); 
					map.put("useArea", null); 
					map.put("likeUseAreas", "21");
					List<ResultDto> xq=reportResultService.selectExpectValueSumDetailAll(map);
					yysr.put("xq", xq);
					rmap.put("yysr", yysr);
					map.put("likeUseAreas", null);
				}else{
					yysr.put("total", null);
					yysr.put("xq", null);
					rmap.put("yysr", yysr);
				}
			
				//营业成本
				/*map.put("likeUseAreas", null);
				map.put("useArea", "22"); 
				Map<String,Object> yycb = new HashMap<>();
				ResultDto yycbTotal=reportResultService.selectValueSumDetailAllTotal2(map);
				if(yycbTotal!=null){
					yycb.put("total", yycbTotal);
					//map.put("useArea", "11"); 
					map.put("useArea", null); 
					map.put("likeUseAreas", "22");
					List<ResultDto> xq=reportResultService.selectValueSumDetailAll2(map);
					yycb.put("xq", xq);
					//map.put("useArea", "02"); 
					rmap.put("yycb", yycb);
				}else{
					yycb.put("total", null);
					yycb.put("xq", null);
					rmap.put("yycb", yycb);
				}*/
			
				//营业利润
			/*	map.put("startTime", startTime);
				map.put("endTime", endTime);*/
				//map.put("dataType", "12"); 
				map.put("likeUseAreas", null);
				map.put("useArea", "23"); 
				Map<String,Object> yylr = new HashMap<>();
				//List<ResultDto> ldfzTotal=reportResultService.selectValueSumDetailAll(map);
				ResultDto yylrTotal=reportResultService.selectExpectValueSumDetailAllTotal(map);
				if(yylrTotal!=null){
					yylr.put("total", yylrTotal);
					map.put("useArea", null); 
					map.put("likeUseAreas", "23");
					List<ResultDto> xq=reportResultService.selectExpectValueSumDetailAll(map);
					yylr.put("xq", xq);
					rmap.put("yylr", yylr);
					map.put("likeUseAreas", null);
				}else{
					yylr.put("total", null);
					yylr.put("xq", null);
					rmap.put("yylr", yylr);
				}
			
				//利润总额
				//map.put("dataType", "13"); 
				map.put("likeUseAreas", null);
				map.put("useArea", "24"); 
				Map<String,Object> lrze = new HashMap<>();
				ResultDto lrzeTotal=reportResultService.selectExpectValueSumDetailAllTotal(map);
				//List<ResultDto> gdfzTotal=reportResultService.selectValueSumDetailAll(map);
				if(lrzeTotal!=null){
					lrze.put("total", lrzeTotal);
					map.put("useArea", null); 
					map.put("likeUseAreas", "24");
					List<ResultDto> xq=reportResultService.selectExpectValueSumDetailAll(map);
					lrze.put("xq", xq);
					rmap.put("lrze", lrze);
				}else{
					lrze.put("total", null);
					lrze.put("xq", null);
					rmap.put("lrze", lrze);
				}
				
				//净利润
				map.put("likeUseAreas", null);
				map.put("useArea", "25"); 
				Map<String,Object> jlr = new HashMap<>();
				ResultDto jlrTotal=reportResultService.selectExpectValueSumDetailAllTotal(map);
				//List<ResultDto> gdfzTotal=reportResultService.selectValueSumDetailAll(map);
				if(jlrTotal!=null){
					jlr.put("total", jlrTotal);
					map.put("useArea", null); 
					map.put("likeUseAreas", "25");
					List<ResultDto> xq=reportResultService.selectExpectValueSumDetailAll(map);
					jlr.put("xq", xq);
					rmap.put("jlr", jlr);
					map.put("likeUseAreas", null);
				}else{
					jlr.put("total", null);
					jlr.put("xq", null);
					rmap.put("jlr", jlr);
				}
				remap.put("content", rmap);
				rList.add(remap);
			}
			//remap2.put("sm", "");
			//rList.add(remap2);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",rList);
			return robj.toString();
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "getsmscode.api:",e.getMessage());
			return robj.toString();
		}
	}


	@ApiOperation(value = "按单元格的公式进行计算,得结果{参数内容类型:application/json}", notes = "输入开始时间与结束时间", response = ResultObj.class, httpMethod = "POST")
	@RequestMapping(value="/autoComp.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody //tax 支出金额（含税） pay 支出金额（不含税）zcje 增值税率 fundsHand 自由资产 onelcome 预计第一年收入  ssys 收入增值税率
	public String autoComp(String tax,String zcje,String pay,String fundsHand,String oneIncome,String srsy){
		try {
			Map<String, Object> autoComp= FundResunlt.autoComp(tax, zcje, pay, fundsHand, oneIncome, srsy);
			if(autoComp==null || autoComp.size()==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NULL, "参数填写不完整!");
				return robj.toString();
			}
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",autoComp);
			return robj.toString();
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "getsmscode.api:",e.getMessage());
			return robj.toString();
		}
	}


	@ApiOperation(value = "投资预测{参数内容类型:application/json}", notes = "输入开始时间与结束时间", response = ResultObj.class, httpMethod = "POST")
	@RequestMapping(value="/getTzDivine.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String getTzDivine(String startTime,String endTime,String tax,String pay,String fundsHand,
							  String oneIncome,String incomeGrowth,String yycb,String xsfy,String glfy,String cwfy,String zjsr,String ycmqs,
							  String srsy,String yycbzz,String zcje,String fjsl,String zzsfh,String sds,String jkll,String tzl,String allYear,String startYear,
							  HttpServletResponse response,HttpServletRequest request){
		try {
			startTime=(Integer.parseInt(startTime)-1)+"";
			HashMap<String, Object> rmap=new HashMap<String, Object>();
			HashMap<String, Object> rmaps=(HashMap<String, Object>) ResultMessage.getMessage(startTime, endTime, tax, pay, "0", "0", "0", fundsHand, "0",
					oneIncome, "0", "0", incomeGrowth, yycb, xsfy, glfy, cwfy, zjsr, ycmqs, srsy, yycbzz, zcje, fjsl, zzsfh, sds, jkll,
					tzl, allYear, startYear);								//信息判断
			int code=(int) rmaps.get("code");
			if(code==1){
				ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, rmaps.get("message").toString());
				return robj.toString();
			}
			Map<String, Object> autoComp=FundResunlt.autoComp(tax, zcje, pay, fundsHand, oneIncome, srsy);
			String qpay=autoComp.get("qpay").toString();
			String loan=autoComp.get("loan").toString();
			String oneNoIncome=autoComp.get("oneNoIncome").toString();
			String funds=autoComp.get("funds").toString();
			String dpay=autoComp.get("dpay").toString();
			String limits=autoComp.get("limits").toString();
			int st=Integer.parseInt(startTime);
			int allYearInt=(Integer.parseInt(allYear))+1;
			int[] year=new int[allYearInt];
			for(int i=0;i<allYearInt;i++){
				//year[i]=st+i;
				year[i]=i;
			}
			Map<String, Object> rsmaps=new HashMap<String, Object>();
			Map<String, Object> years=new HashMap<String, Object>();			//年月
			years.put("total", year);
			years.put("xq", null);
			rsmaps.put("year", years);
			rmap.put("years", rsmaps);
			//利润表&增值税
			HashMap<String, Object> profit=(HashMap<String, Object>) FundResunlt.profit(startTime, endTime, tax, pay, dpay, qpay, funds, fundsHand, loan,
					oneIncome, oneNoIncome, limits, incomeGrowth, yycb, xsfy, glfy, cwfy, zjsr, ycmqs, srsy, yycbzz, zcje, fjsl, zzsfh, sds, jkll,
					tzl, allYear, startYear);
			//资金来源中的借款的还本付息
			HashMap<String, Object> sourcesFunds=(HashMap<String, Object>) FundResunlt.sourcesFunds(loan, jkll, allYear, startTime);
			//投入资金的现金流测算
			HashMap<String, Object> trzj=(HashMap<String, Object>) FundResunlt.cashFlow(profit, sourcesFunds, allYearInt, fundsHand, zjsr, tzl);
			rmap.put("trzj", trzj);
			//全资本金投入的现金流测算
			HashMap<String, Object> qzbj=(HashMap<String, Object>) FundResunlt.capital(profit, sourcesFunds, allYearInt, fundsHand, tax, zjsr,sds, tzl);
			rmap.put("qzbj", qzbj);
			HashMap<String, Object> tr=(HashMap<String, Object>) FundResunlt.cashFlow2(profit, sourcesFunds, allYearInt, fundsHand, zjsr, tzl);
			HashMap<String, Object> qz=(HashMap<String, Object>) FundResunlt.capital2(profit, sourcesFunds, allYearInt, fundsHand, tax, zjsr,sds, tzl);
			//考虑通货膨胀IRR(全资本金投入的现金流测算)
			HashMap<String, Object> maps=(HashMap<String, Object>) FundResunlt.result(tr, qz, allYearInt);
			rmap.put("result", maps);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",rmap);
			return robj.toString();
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "getsmscode.api:",e.getMessage());
			return robj.toString();
		}
	}



}








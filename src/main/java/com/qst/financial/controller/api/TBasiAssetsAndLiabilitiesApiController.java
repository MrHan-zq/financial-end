package com.qst.financial.controller.api;


import com.qst.financial.annotation.AppControllerLog;
import com.qst.financial.dto.ResultDto;
import com.qst.financial.modle.subject.TBasiAssetsAndLiabilities;
import com.qst.financial.service.subject.ReportResultService;
import com.qst.financial.service.subject.TBasiAssetsAndLiabilitiesService;
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
 * @author yj
 *资产负债表app接口相关，app相关接口与swagger展示
 */
@Controller
@RequestMapping("/api/tBasiAssetsAndLiabilities")
public class TBasiAssetsAndLiabilitiesApiController {
	private static final Logger log=Logger.getLogger(TBasiAssetsAndLiabilitiesApiController.class);
	@Autowired
	private ReportResultService reportResultService ;
	@Autowired 
	private TBasiAssetsAndLiabilitiesService tBasiAssetsAndLiabilitiesService ;
	@Value("${useArea.zzc}")//总资产
	String zzc_code;
	@Value("${useArea.zfz}")//总负债
	String zfz_code;
	@Value("${useArea.zqy}")//总权益
	String zqy_code;
	/**
	 * 资产负债首页查询
	 * @param startTime
	 * @param endTine
	 * @param response
	 * @param request
	 * @return
	 */
	@AppControllerLog(description = "资产负债首页查询")
	@ApiOperation(value = "资产负债表信息{参数内容类型:application/json}", notes = "输入开始时间与结束时间", response = ResultObj.class, httpMethod = "POST")
	@RequestMapping(value="/getTotalTbasiAssets.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody 
	public String getTotalTbasiAssets(@ApiParam(required = true, value = "orgId(用户id),startTime(开始时间),endTime(结束时间ssss)") 
		String orgId,String startTime,String endTime,String type,HttpServletResponse response,HttpServletRequest request){
		try {
			if(startTime==null || startTime.length()==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NULL, "开始时间不能为空");
				return robj.toString();
			}
			if(endTime==null || endTime.length()==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NULL, "结束时间不能为空");
				return robj.toString();
			}
			log.debug("===========type:"+type);
			System.out.println("===========type:"+type);
			if(type==null || type.length()==0){
				type="3";
			}
			startTime=startTime+"-01";
			endTime=endTime+"-01";
			Map<String,Object> rmap = new HashMap<>();
			Map<String,Object> map = new HashMap<>();
			//map.put("startTime", startTime);
			//map.put("endTime", endTime);
			map.put("endTimes", endTime);
			map.put("orgId", orgId);
			map.put("reportType", 1);
			map.put("type", type);
			//总资产
			/*zzclist.add("10");
			zzclist.add("11");*/
			//map.put("useAreas", zzclist);
			map.put("useArea", zzc_code);
			ResultDto zzc=reportResultService.selectValueSumResultDto(map);
			zzc.setCode("zzc");
			zzc.setCodeName("资产");
			//总负债
			/*map.put("startTime", startTime);
			map.put("endTime", endTime);*/
			map.put("endTimes", endTime);
			/*fzlist.add("12");
			fzlist.add("13");*/
			//map.put("useAreas", fzlist);
			map.put("useArea", zfz_code);
			ResultDto fz=reportResultService.selectValueSumResultDto(map);
			fz.setCode("fz");
			fz.setCodeName("负债");
			//权益
			/*map.put("startTime", startTime);
			map.put("endTime", endTime);*/
			map.put("endTimes", endTime);
			map.put("useArea", zqy_code);
			ResultDto qy=reportResultService.selectValueSumResultDto(map);
			qy.setCode("qy");
			qy.setCodeName("权益");
			rmap.put("zzc", zzc);
			rmap.put("fz", fz);
			rmap.put("qy", qy);
			
			String limit =" limit 0,1";
			WherePrams wherePrams= Method.where("orgId", C.EQ, orgId).orderBy(" KJYEAR_MOTH desc");
        	List<TBasiAssetsAndLiabilities> lists=tBasiAssetsAndLiabilitiesService.listPage(wherePrams, limit);
        	String dt="";
        	if(lists!=null && lists.size()>0){
        		TBasiAssetsAndLiabilities tb=lists.get(0);
        		if(tb.getImpTime()!=null){
        			dt=tb.getKjyearMoth();
        		}
        	}
        	rmap.put("times", dt);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",rmap);
			return robj.toString();
		} catch (Exception e) {
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "getsmscode.api:",e.getMessage());
			return robj.toString();
		}
	}
	/**
	 * 资产负债首页查询线性条
	 * @param startTime
	 * @param endTine
	 * @param response
	 * @param request
	 * @return
	 */
	@AppControllerLog(description = "资产负债首页查询线条展示")
	@ApiOperation(value = "资产负债首页查询线条展示{参数内容类型:application/json}", notes = "输入开始时间与结束时间", response = ResultObj.class, httpMethod = "POST")
	@RequestMapping(value="/getLine.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody 
	public String getLine(@ApiParam(required = true, value = "orgId(用户id),startTime(开始时间),endTime(结束时间ssss)") 
		String orgId,String startTime,String endTime,String type,HttpServletResponse response,HttpServletRequest request){
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
			//startTime=startTime+"-01";
			endTime=endTime+"-01";
			startTime= DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd", -11, 3), "yyyy-MM-dd");
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
			Map<String,Object> rmap = new HashMap<>();
			Map<String,Object> map = new HashMap<>();
			Date st=DateUtil.stringToDate(startTime, "yyyy-MM-dd");
			Date ed=DateUtil.stringToDate(endTime, "yyyy-MM-dd");
			String startDt=DateUtil.dateToString(DateUtil.addTime(st,"yyyy-MM",-1,4), "yyyy-MM-dd");
			String endDt=DateUtil.dateToString(DateUtil.addTime(ed,"yyyy-MM",-1,4), "yyyy-MM-dd");
			map.put("type", type);
			map.put("orgId", orgId);
			//总资产
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			/*map.put("endTimes", endTime);*/
			map.put("useArea", "01");  
			List<ResultDto> zcLists=reportResultService.selectValueMonthResultDto(map);
			List<Date> dtList=DateUtil.getDatesBetweenTwoDate(startTime,endTime);
			List<ResultDto> zcList=new ArrayList<ResultDto>();
			map.put("startTime", startDt);
			map.put("endTime", endDt);
			/*List<ResultDto> zcUpLists=reportResultService.selectValueMonthResultDto(map);
			List<Date> dtUpList=DateUtil.getDatesBetweenTwoDate(startDt,endDt);
			List<ResultDto> zcUpList=new ArrayList<ResultDto>();*/
			//总负债
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("useArea", "02");  
			List<ResultDto> fzLists=reportResultService.selectValueMonthResultDto(map);
			List<ResultDto> fzList=new ArrayList<ResultDto>();
			/*map.put("startTime", startDt);
			map.put("endTime", endDt);
			List<ResultDto> fzUpLists=reportResultService.selectValueMonthResultDto(map);
			List<ResultDto> fzUpList=new ArrayList<ResultDto>();*/
			//总权益
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("useArea", "14");  //03
			List<ResultDto> qyLists=reportResultService.selectValueMonthResultDto(map);
			List<ResultDto> qyList=new ArrayList<ResultDto>();
			/*map.put("startTime", startDt);
			map.put("endTime", endDt);
			List<ResultDto> qyUpLists=reportResultService.selectValueMonthResultDto(map);
			List<ResultDto> qyUpList=new ArrayList<ResultDto>();*/
			if(dtList!=null){
				for(int i=0;i<dtList.size();i++){
					Date des=dtList.get(i);
					String strDes=DateUtil.dateFormatToString(des, "yyyy-MM");
					int zcpd=0;
					int fzpd=0;
					int qxpd=0;
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
						zcr.setYearMoth(DateUtil.dateFormatToString(des, "yyyy-MM-dd"));
						zcList.add(zcr);
					}
					if(fzLists!=null && fzLists.size()>0){
						for(ResultDto fz : fzLists){
							if(strDes.equals(fz.getYearMoth())){
								fzList.add(fz);
								fzpd=1;
							}
						}
					}
					if(fzpd==0){
						ResultDto fzr=new ResultDto();
						Calendar calen = Calendar.getInstance();
						calen.setTime(des);
						fzr.setMoth(calen.get(Calendar.MONTH)+1);
						fzr.setYear(calen.get(Calendar.YEAR));
						fzr.setYearMoth(DateUtil.dateFormatToString(des, "yyyy-MM-dd"));
						fzList.add(fzr);
					}
					if(qyLists!=null && qyLists.size()>0){
						for(ResultDto qx : qyLists){
							if(strDes.equals(qx.getYearMoth())){
								qyList.add(qx);
								qxpd=1;
							}
						}
					}
					if(qxpd==0){
						ResultDto qyr=new ResultDto();
						Calendar calen = Calendar.getInstance();
						calen.setTime(des);
						qyr.setMoth(calen.get(Calendar.MONTH)+1);
						qyr.setYear(calen.get(Calendar.YEAR));
						qyr.setYearMoth(DateUtil.dateFormatToString(des, "yyyy-MM-dd"));
						qyList.add(qyr);
					}
					
				}
				
			}
			/*if(dtUpList!=null){
				for(int i=0;i<dtUpList.size();i++){
					Date des=dtList.get(i);
					String strDes=DateUtil.dateFormatToString(des, "yyyy-MM");
					int zcpd=0;
					int fzpd=0;
					int qxpd=0;
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
						zcr.setYearMoth(DateUtil.dateFormatToString(des, "yyyy-MM-dd"));
						zcUpList.add(zcr);
					}
					if(fzUpLists!=null && fzUpLists.size()>0){
						for(ResultDto fz : fzUpLists){
							if(strDes.equals(fz.getYearMoth())){
								fzUpList.add(fz);
								fzpd=1;
							}
						}
					}
					if(fzpd==0){
						ResultDto fzr=new ResultDto();
						Calendar calen = Calendar.getInstance();
						calen.setTime(des);
						fzr.setMoth(calen.get(Calendar.MONTH)+1);
						fzr.setYear(calen.get(Calendar.YEAR));
						fzr.setYearMoth(DateUtil.dateFormatToString(des, "yyyy-MM-dd"));
						fzUpList.add(fzr);
					}
					if(qyUpLists!=null && qyUpLists.size()>0){
						for(ResultDto qx : qyUpLists){
							if(strDes.equals(qx.getYearMoth())){
								qyUpList.add(qx);
								qxpd=1;
							}
						}
					}
					if(qxpd==0){
						ResultDto qyr=new ResultDto();
						Calendar calen = Calendar.getInstance();
						calen.setTime(des);
						qyr.setMoth(calen.get(Calendar.MONTH)+1);
						qyr.setYear(calen.get(Calendar.YEAR));
						qyr.setYearMoth(DateUtil.dateFormatToString(des, "yyyy-MM-dd"));
						qyUpList.add(qyr);
					}
					
				}
				
			}*/
			rmap.put("zzc", zcList);
			rmap.put("fz", fzList);
			rmap.put("qy", qyList);
			/*rmap.put("zzcUp", zcUpList);
			rmap.put("fzUp", fzUpList);
			rmap.put("qyUp", qyUpList);*/
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",rmap);
			return robj.toString();
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "getsmscode.api:",e.getMessage());
			return robj.toString();
		}
	}
	@AppControllerLog(description = "资产负债点击进入详情")
	@ApiOperation(value = "资产负债表信息{参数内容类型:application/json}", notes = "输入开始时间与结束时间", response = ResultObj.class, httpMethod = "POST")
	@RequestMapping(value="/getDetailTbasiAssets.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody 
	public String getDetailTbasiAssets(@ApiParam(required = true, value = "orgId(用户id),startTime(开始时间),endTime(结束时间ssss)") 
	String orgId,String startTime,String endTime,String type,HttpServletResponse response,HttpServletRequest request){
		try {
			if(type==null || type.length()==0){
				type="3";
			}
			if(startTime==null || startTime.length()==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NULL, "开始时间不能为空");
				return robj.toString();
			}
			if(endTime==null || endTime.length()==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NULL, "输入时间空间类型");
				return robj.toString();
			}
			Map<String,Object> remap = new HashMap<>();
			Map<String,Object> rmap = new HashMap<>();
			Map<String,Object> rmap2 = new HashMap<>();
			Map<String,Object> rmap3 = new HashMap<>();
			Map<String,Object> rmap4 = new HashMap<>();
			Map<String,Object> map = new HashMap<>();
			startTime=startTime+"-01";
			endTime=endTime+"-01";
			/*map.put("startTime", startTime);
			map.put("endTime", endTime);*/
			map.put("type", type);
			map.put("endTimes", endTime);
			map.put("startTimes", startTime);
			map.put("orgId", orgId);
			map.put("reportType", 1);
			//流动资产
			map.put("useArea", "10"); 
			Map<String,Object> ldzc = new HashMap<>();
			ResultDto ldzcTotal=reportResultService.selectValueSumDetailAllTotal(map);
			if(ldzcTotal!=null){
				map.put("endTimes", endTime);
				ldzc.put("total", ldzcTotal);
				//map.put("useArea", "10"); 
				map.put("useArea", null); 
				map.put("likeUseAreas", 10);
				List<ResultDto> xq=reportResultService.selectValueSumDetailAll(map);
				ldzc.put("xq", xq);
				rmap.put("ldzc", ldzc);
			}else{
				ldzc.put("total", null);
				ldzc.put("xq", null);
				rmap.put("ldzc", ldzc);
			}
			
			//固定资产
			/*map.put("startTime", startTime);
			map.put("endTime", endTime);*/
			map.put("endTimes", endTime);
			map.put("likeUseAreas", null);
			map.put("useArea", "11"); 
			Map<String,Object> gdzc = new HashMap<>();
			ResultDto gdzcTotal=reportResultService.selectValueSumDetailAllTotal(map);
			if(gdzcTotal!=null){
				map.put("endTimes", endTime);
				gdzc.put("total", gdzcTotal);
				//map.put("useArea", "11"); 
				map.put("useArea", null); 
				map.put("likeUseAreas", 11);
				List<ResultDto> xq=reportResultService.selectValueSumDetailAll(map);
				gdzc.put("xq", xq);
				map.put("useArea", "02"); 
				rmap.put("gdzc", gdzc);
				map.put("likeUseAreas", null);
			}else{
				gdzc.put("total", null);
				gdzc.put("xq", null);
				rmap.put("gdzc", gdzc);
			}
			//ResultDto allTotal=new ResultDto();
			//List<String> zc=new ArrayList<String>();
			//zc.add("10");
			//zc.add("11");
			map.remove("useArea");
			//map.put("useAreas", zc); 
			map.put("useArea", "01"); 
			map.put("endTimes", endTime);
			//List<ResultDto> allTotalList=reportResultService.selectValueSumDetailAll(map);
			ResultDto allTotal=reportResultService.selectValueSumDetailAllTotal(map);
			rmap.put("total", allTotal);
			remap.put("zc", rmap);
			//remap.put("zcdtial", rmap);
			
			//流动负债
			/*map.put("startTime", startTime);
			map.put("endTime", endTime);*/
			map.remove("useAreas");
			map.put("endTimes", endTime);
			//map.put("dataType", "12"); 
			map.put("likeUseAreas", null);
			map.put("useArea", "12"); 
			Map<String,Object> ldfz = new HashMap<>();
			//List<ResultDto> ldfzTotal=reportResultService.selectValueSumDetailAll(map);
			ResultDto ldfzTotal=reportResultService.selectValueSumDetailAllTotal(map);
			if(ldfzTotal!=null){
				ldfz.put("total", ldfzTotal);
				map.put("useArea", null); 
				map.put("likeUseAreas", 12);
				map.put("endTimes", endTime);
				//map.put("useArea", "10"); 
				List<ResultDto> xq=reportResultService.selectValueSumDetailAll(map);
				ldfz.put("xq", xq);
				rmap2.put("ldfz", ldfz);
				map.put("likeUseAreas", null);
			}else{
				ldfz.put("total", null);
				ldfz.put("xq", null);
				rmap2.put("ldfz", ldfz);
			}
			
			//固定负债
			/*map.put("startTime", startTime);
			map.put("endTime", endTime);*/
			map.put("endTimes", endTime);
			//map.put("dataType", "13"); 
			map.put("useArea", "13"); 
			Map<String,Object> gdfz = new HashMap<>();
			ResultDto gdfzTotal=reportResultService.selectValueSumDetailAllTotal(map);
			//List<ResultDto> gdfzTotal=reportResultService.selectValueSumDetailAll(map);
			if(gdfzTotal!=null){
				gdfz.put("total", gdfzTotal);
				map.put("useArea", null); 
				map.put("likeUseAreas", 13);
				map.put("endTimes", endTime);
				List<ResultDto> xq=reportResultService.selectValueSumDetailAll(map);
				gdfz.put("xq", xq);
				rmap2.put("gdfz", gdfz);
				map.put("likeUseAreas", null);
			}else{
				gdfz.put("total", null);
				gdfz.put("xq", null);
				rmap2.put("gdfz", gdfz);
			}
			//map.put("useArea", "02"); 
			//List<String> fz=new ArrayList<String>();
			//fz.add("12");
			//fz.add("13");
			map.remove("useArea");
			//map.put("useAreas", fz); 
			map.put("useArea", "02"); 
			map.put("endTimes", endTime);
			//List<ResultDto> allTotalList=reportResultService.selectValueSumDetailAll(map);
			ResultDto allTotals=reportResultService.selectValueSumDetailAllTotal(map);
			//List<ResultDto> allTotalList2s=reportResultService.selectValueSumDetailAll(map);
			/*if(allTotals!=null){
				allTotal2=allTotalList2s.get(0);
				gdfz.put("allTotal", allTotal2);
			}*/
			rmap2.put("total", allTotals);
			remap.put("fz", rmap2);
			map.remove("useAreas");
			//
			/*map.put("startTime", startTime);
			map.put("endTime", endTime);*/
			map.put("endTimes", endTime);
			map.put("likeUseAreas", null);
			map.put("useArea", "14"); 
			Map<String,Object> qy = new HashMap<>();
			List<ResultDto> qyTotal=reportResultService.selectValueSumDetailAll(map);
			if(qyTotal!=null && qyTotal.size()>0){
				qy.put("total", qyTotal.get(0));
				map.put("useArea", null); 
				map.put("likeUseAreas", 14);
				map.put("endTimes", endTime);
				List<ResultDto> xq=reportResultService.selectValueSumDetailAll(map);
				qy.put("xq", xq);
				rmap3.put("qy", qy);
				map.put("likeUseAreas", null);
			}else{
				qy.put("total", null);
				qy.put("xq", null);
				rmap3.put("qy", qy);
			}		
			remap.put("qy", rmap3);
			map.remove("useArea");
			map.remove("useAreas");
			//map.put("useAreas", fz); 
			map.put("useArea", "03"); 
			map.put("endTimes", endTime);
			//List<ResultDto> allTotalList=reportResultService.selectValueSumDetailAll(map);
			ResultDto alls=reportResultService.selectValueSumDetailAllTotal(map);
			Map<String,Object> fzandqy = new HashMap<>();
			fzandqy.put("total", alls);
			fzandqy.put("xq", null);
			rmap4.put("fzandqy", fzandqy);
			remap.put("fzandqy", fzandqy);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",remap);
			return robj.toString();
		} catch (Exception e) {
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "getsmscode.api:",e.getMessage());
			return robj.toString();
		}
	}
}

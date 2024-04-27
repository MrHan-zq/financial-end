package com.qst.financial.service.impl.subject;

import com.qst.financial.dto.Result;
import com.qst.financial.dto.ResultDto;
import com.qst.financial.service.subject.HomeService;
import com.qst.financial.service.subject.ReportResultService;
import com.qst.financial.service.subject.TAgeAnalysisService;
import com.qst.financial.service.subject.TModeService;
import com.qst.financial.util.common.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.qst.financial.redis.RedisService;
import java.util.*;

@Service
public class HomeServiceImpl implements HomeService {
	@Autowired
	private ReportResultService reportResultService;
	@Autowired
	private TModeService tModeService;
	@Autowired
	private TAgeAnalysisService tAgeAnalysisService;
	@Autowired
	private RedisService redisService;

	@Value("${mode.zcfz.home}")
	private String zcfz_home;
	@Value("${mode.lr.home}")
	private String lr_home;
	@Value("${mode.xjll.home}")
	private String xjll_home;
	@Override
	public Map<String,Object> getHomes(String startTime,String endTime,String orgId) throws Exception{
		if(startTime.length()<8)startTime=startTime+"-01";
		if(endTime.length()<8)endTime=endTime+"-01";
		Map<String,Object> rmap = new HashMap<>();

		List<String> fzList = tModeService.getContent(startTime, endTime, orgId, "1",zcfz_home);
		rmap.put("fzList", fzList) ;              //负债概述
		List<String> lrList = tModeService.getContent(startTime, endTime, orgId, "2",lr_home);     //G2001
		rmap.put("lrList", lrList) ;				//利润概述
		List<String> xjList = tModeService.getContent(startTime, endTime, orgId, "3",xjll_home);
		rmap.put("xjList", xjList) ;			//现金概述
		//redisService.delObj(key);
		return rmap;
	}
	@Override
	public List<Result> getDetail(String clzz, String startTime, String endTime, String orgId){
		List<Result> list=new ArrayList<Result>();
		//饼状加树形4,饼状 5,树形 6
		if(clzz!=null && clzz.length()>0){
			//1应付，2应收
			String clzzs[]=clzz.split(",");
			int index=clzzs.length;
			for(int i=0;i<index;i++){
				if(!clzzs[i].equals("0")){
					Map<String,Object> ysf = new HashMap<>();
					ysf.put("startTime", startTime);
					ysf.put("endTime", endTime);
					ysf.put("orgId", orgId);
					if(clzzs[i].equals("1")){
						Date sts=DateUtil.stringToDate(startTime, "yyyy-MM-dd");
						Date ets=DateUtil.stringToDate(endTime, "yyyy-MM-dd");
						Calendar c1 = Calendar.getInstance(); 
						c1.setTime(sts);
						String ys1=(c1.get(Calendar.YEAR))+"";
						int msi1=c1.get(Calendar.MONTH)+1;
						String ms1=(c1.get(Calendar.MONTH)+1)+"";
						String strs="";
						if(msi1<10){
							strs=ys1+"0"+ms1;
						}else{
							strs=ys1+ms1;
						}
						
						Calendar c2 = Calendar.getInstance(); 
						c2.setTime(ets);
						String ys2=(c2.get(Calendar.YEAR))+"";
						int msi2=c2.get(Calendar.MONTH)+1;
						String ms2=(c2.get(Calendar.MONTH)+1)+"";
						String stre="";
						if(msi2<10){
							stre=ys2+"0"+ms2;
						}else{
							stre=ys2+ms2;
						}
						ysf.put("startTime", strs);
						ysf.put("endTime", stre);
						ysf.put("type", "5");
						ysf.put("isNotNull", "5");
						HashMap<String,Object> rmapz=new HashMap<String,Object>();
						Map<String,Object> tageList=new HashMap<String,Object>();
						List<ResultDto> ysfList=tAgeAnalysisService.allSum(ysf);
						if(ysfList!=null){
							List<String> ids=new ArrayList<>();
							for (ResultDto resultDto2 : ysfList) {
								ids.add(resultDto2.getCode());
							}
							ysf.put("ids", ids);
							tageList=tAgeAnalysisService.selectSumAll2(ysf);
						}
						Map<String, Object> mapsa=new HashMap<String, Object>();
						mapsa.put("tuyuan", ysfList);
						mapsa.put("hengxian", tageList);
						Result r7=new Result();
						r7.setType(4);               //供应商应付款
						rmapz.put("title", "前五大供应商应付账款");
						rmapz.put("content", mapsa);
						r7.setData(rmapz);
						if(ysfList!=null && ysfList.size()>.0){
							list.add(r7);
						}
						ysf.put("isNotNull", null);
						ysf.put("startTime", startTime);
						ysf.put("endTime", endTime);
					}else if(clzzs[i].equals("2")){
						Map<String,Object> emap=new HashMap<String,Object>();
						Date sts=DateUtil.stringToDate(startTime, "yyyy-MM-dd");
						Date ets=DateUtil.stringToDate(endTime, "yyyy-MM-dd");
						Calendar c1 = Calendar.getInstance(); 
						c1.setTime(sts);
						String ys1=(c1.get(Calendar.YEAR))+"";
						int msi1=c1.get(Calendar.MONTH)+1;
						String ms1=(c1.get(Calendar.MONTH)+1)+"";
						String strs="";
						if(msi1<10){
							strs=ys1+"0"+ms1;
						}else{
							strs=ys1+ms1;
						}
						
						Calendar c2 = Calendar.getInstance(); 
						c2.setTime(ets);
						String ys2=(c2.get(Calendar.YEAR))+"";
						int msi2=c2.get(Calendar.MONTH)+1;
						String ms2=(c2.get(Calendar.MONTH)+1)+"";
						String stre="";
						if(msi2<10){
							stre=ys2+"0"+ms2;
						}else{
							stre=ys2+ms2;
						}
						ysf.put("startTime", strs);
						ysf.put("endTime", stre);
						ysf.put("type", "6");
						ysf.put("isNotNull", "5");
						Map<String,Object> tageList=new HashMap<String,Object>();
						List<ResultDto> ysfList=tAgeAnalysisService.allSum(ysf);
						if(ysfList!=null){
							List<String> ids=new ArrayList<>();
							for (ResultDto resultDto2 : ysfList) {
								ids.add(resultDto2.getCode());
							}
							ysf.put("ids", ids);
							tageList=tAgeAnalysisService.selectSumAll2(ysf);
							//tageList=tAgeAnalysisService.selectSumAll(ysf);
						}
						ysf.put("isNotNull", null);
						Map<String, Object> mapsa=new HashMap<String, Object>();
						mapsa.put("tuyuan", ysfList);
						mapsa.put("hengxian", tageList);
						Result r7=new Result();
						r7.setType(4);               //供应商应付款
						emap.put("title", "前五大供应商应收款");
						emap.put("content", mapsa);
						r7.setData(emap);
						if(tageList!=null && tageList.size()>0){
							list.add(r7);
						}
						ysf.put("startTime", startTime);
						ysf.put("endTime", endTime);
					}else if(clzzs[i].equals("8")){
						Map<String,Object> emap=new HashMap<String,Object>();
						Date sts=DateUtil.stringToDate(startTime, "yyyy-MM-dd");
						Date ets=DateUtil.stringToDate(endTime, "yyyy-MM-dd");
						Calendar c1 = Calendar.getInstance(); 
						c1.setTime(sts);
						String ys1=(c1.get(Calendar.YEAR))+"";
						int msi1=c1.get(Calendar.MONTH)+1;
						String ms1=(c1.get(Calendar.MONTH)+1)+"";
						String strs="";
						if(msi1<10){
							strs=ys1+"0"+ms1;
						}else{
							strs=ys1+ms1;
						}
						
						Calendar c2 = Calendar.getInstance(); 
						c2.setTime(ets);
						String ys2=(c2.get(Calendar.YEAR))+"";
						int msi2=c2.get(Calendar.MONTH)+1;
						String ms2=(c2.get(Calendar.MONTH)+1)+"";
						String stre="";
						if(msi2<10){
							stre=ys2+"0"+ms2;
						}else{
							stre=ys2+ms2;
						}
						ysf.put("startTime", strs);
						ysf.put("endTime", stre);
						ysf.put("type", "3");
						ysf.put("isNotNull", "5");
						Map<String,Object> tageList=new HashMap<String,Object>();
						List<ResultDto> ysfList=tAgeAnalysisService.allSum(ysf);
						if(ysfList!=null){
							List<String> ids=new ArrayList<>();
							for (ResultDto resultDto2 : ysfList) {
								ids.add(resultDto2.getCode());
							}
							ysf.put("ids", ids);
							tageList=tAgeAnalysisService.selectSumAll2(ysf);
							//tageList=tAgeAnalysisService.selectSumAll(ysf);
						}
						ysf.put("isNotNull", null);
						Map<String, Object> mapsa=new HashMap<String, Object>();
						//mapsa.put("tuyuan", ysfList);
						mapsa.put("hengxian", tageList);
						Result r7=new Result();
						r7.setType(4);               //其他应付账龄分析
						emap.put("title", "前五大供应商其他应付账龄分析");
						emap.put("content", mapsa);
						r7.setData(emap);
						if(tageList!=null && tageList.size()>0){
							list.add(r7);
						}
						ysf.put("startTime", startTime);
						ysf.put("endTime", endTime);
					}else if(clzzs[i].equals("9")){
						Map<String,Object> emap=new HashMap<String,Object>();
						Date sts=DateUtil.stringToDate(startTime, "yyyy-MM-dd");
						Date ets=DateUtil.stringToDate(endTime, "yyyy-MM-dd");
						Calendar c1 = Calendar.getInstance(); 
						c1.setTime(sts);
						String ys1=(c1.get(Calendar.YEAR))+"";
						int msi1=c1.get(Calendar.MONTH)+1;
						String ms1=(c1.get(Calendar.MONTH)+1)+"";
						String strs="";
						if(msi1<10){
							strs=ys1+"0"+ms1;
						}else{
							strs=ys1+ms1;
						}
						
						Calendar c2 = Calendar.getInstance(); 
						c2.setTime(ets);
						String ys2=(c2.get(Calendar.YEAR))+"";
						int msi2=c2.get(Calendar.MONTH)+1;
						String ms2=(c2.get(Calendar.MONTH)+1)+"";
						String stre="";
						if(msi2<10){
							stre=ys2+"0"+ms2;
						}else{
							stre=ys2+ms2;
						}
						ysf.put("startTime", strs);
						ysf.put("endTime", stre);
						ysf.put("type", "4");
						ysf.put("isNotNull", "5");
						Map<String,Object> tageList=new HashMap<String,Object>();
						List<ResultDto> ysfList=tAgeAnalysisService.allSum(ysf);
						if(ysfList!=null){
							List<String> ids=new ArrayList<>();
							for (ResultDto resultDto2 : ysfList) {
								ids.add(resultDto2.getCode());
							}
							ysf.put("ids", ids);
							tageList=tAgeAnalysisService.selectSumAll2(ysf);
							//tageList=tAgeAnalysisService.selectSumAll(ysf);
						}
						ysf.put("isNotNull", null);
						Map<String, Object> mapsa=new HashMap<String, Object>();
						mapsa.put("tuyuan", ysfList);
						mapsa.put("hengxian", tageList);
						Result r7=new Result();
						r7.setType(4);               //其他应收账龄分析
						emap.put("title", "前五大供应商其他应收账龄分析");
						emap.put("content", mapsa);
						r7.setData(emap);
						if(tageList!=null && tageList.size()>0){
							list.add(r7);
						}
						ysf.put("startTime", startTime);
						ysf.put("endTime", endTime);
					}else if(clzzs[i].equals("10")){
						Map<String,Object> emap=new HashMap<String,Object>();
						Date sts=DateUtil.stringToDate(startTime, "yyyy-MM-dd");
						Date ets=DateUtil.stringToDate(endTime, "yyyy-MM-dd");
						Calendar c1 = Calendar.getInstance(); 
						c1.setTime(sts);
						String ys1=(c1.get(Calendar.YEAR))+"";
						int msi1=c1.get(Calendar.MONTH)+1;
						String ms1=(c1.get(Calendar.MONTH)+1)+"";
						String strs="";
						if(msi1<10){
							strs=ys1+"0"+ms1;
						}else{
							strs=ys1+ms1;
						}
						
						Calendar c2 = Calendar.getInstance(); 
						c2.setTime(ets);
						String ys2=(c2.get(Calendar.YEAR))+"";
						int msi2=c2.get(Calendar.MONTH)+1;
						String ms2=(c2.get(Calendar.MONTH)+1)+"";
						String stre="";
						if(msi2<10){
							stre=ys2+"0"+ms2;
						}else{
							stre=ys2+ms2;
						}
						ysf.put("startTime", strs);
						ysf.put("endTime", stre);
						ysf.put("type", "7");
						ysf.put("isNotNull", "5");
						Map<String,Object> tageList=new HashMap<String,Object>();
						List<ResultDto> ysfList=tAgeAnalysisService.allSum(ysf);
						if(ysfList!=null){
							List<String> ids=new ArrayList<>();
							for (ResultDto resultDto2 : ysfList) {
								ids.add(resultDto2.getCode());
							}
							ysf.put("ids", ids);
							tageList=tAgeAnalysisService.selectSumAll2(ysf);
							//tageList=tAgeAnalysisService.selectSumAll(ysf);
						}
						ysf.put("isNotNull", null);
						Map<String, Object> mapsa=new HashMap<String, Object>();
						mapsa.put("tuyuan", ysfList);
						mapsa.put("hengxian", tageList);
						Result r7=new Result();
						r7.setType(4);               //预付账款账龄分析
						emap.put("title", "前五大供应商预付账款账龄分析");
						emap.put("content", mapsa);
						r7.setData(emap);
						if(tageList!=null && tageList.size()>0){
							list.add(r7);
						}
						ysf.put("startTime", startTime);
						ysf.put("endTime", endTime);
					}else if(clzzs[i].equals("11")){
						Map<String,Object> emap=new HashMap<String,Object>();
						Date sts=DateUtil.stringToDate(startTime, "yyyy-MM-dd");
						Date ets=DateUtil.stringToDate(endTime, "yyyy-MM-dd");
						Calendar c1 = Calendar.getInstance(); 
						c1.setTime(sts);
						String ys1=(c1.get(Calendar.YEAR))+"";
						int msi1=c1.get(Calendar.MONTH)+1;
						String ms1=(c1.get(Calendar.MONTH)+1)+"";
						String strs="";
						if(msi1<10){
							strs=ys1+"0"+ms1;
						}else{
							strs=ys1+ms1;
						}
						
						Calendar c2 = Calendar.getInstance(); 
						c2.setTime(ets);
						String ys2=(c2.get(Calendar.YEAR))+"";
						int msi2=c2.get(Calendar.MONTH)+1;
						String ms2=(c2.get(Calendar.MONTH)+1)+"";
						String stre="";
						if(msi2<10){
							stre=ys2+"0"+ms2;
						}else{
							stre=ys2+ms2;
						}
						ysf.put("startTime", strs);
						ysf.put("endTime", stre);
						ysf.put("type", "8");
						ysf.put("isNotNull", "5");
						Map<String,Object> tageList=new HashMap<String,Object>();
						List<ResultDto> ysfList=tAgeAnalysisService.allSum(ysf);
						if(ysfList!=null){
							List<String> ids=new ArrayList<>();
							for (ResultDto resultDto2 : ysfList) {
								ids.add(resultDto2.getCode());
							}
							ysf.put("ids", ids);
							tageList=tAgeAnalysisService.selectSumAll2(ysf);
							//tageList=tAgeAnalysisService.selectSumAll(ysf);
						}
						ysf.put("isNotNull", null);
						Map<String, Object> mapsa=new HashMap<String, Object>();
						mapsa.put("tuyuan", ysfList);
						mapsa.put("hengxian", tageList);
						Result r7=new Result();
						r7.setType(4);               //预收账款账龄分析
						emap.put("title", "前五大供应商预收账款账龄分析");
						emap.put("content", mapsa);
						r7.setData(emap);
						if(tageList!=null && tageList.size()>0){
							list.add(r7);
						}
						ysf.put("startTime", startTime);
						ysf.put("endTime", endTime);
					}else if(clzzs[i].equals("3")){
						ysf.put("type", "5");
						Date sts=DateUtil.stringToDate(startTime, "yyyy-MM-dd");
						Date ets=DateUtil.stringToDate(endTime, "yyyy-MM-dd");
						Calendar c1 = Calendar.getInstance(); 
						c1.setTime(sts);
						String ys1=(c1.get(Calendar.YEAR))+"";
						int msi1=c1.get(Calendar.MONTH)+1;
						String ms1=(c1.get(Calendar.MONTH)+1)+"";
						String strs="";
						if(msi1<10){
							strs=ys1+"0"+ms1;
						}else{
							strs=ys1+ms1;
						}
						
						Calendar c2 = Calendar.getInstance(); 
						c2.setTime(ets);
						String ys2=(c2.get(Calendar.YEAR))+"";
						int msi2=c2.get(Calendar.MONTH)+1;
						String ms2=(c2.get(Calendar.MONTH)+1)+"";
						String stre="";
						if(msi2<10){
							stre=ys2+"0"+ms2;
						}else{
							stre=ys2+ms2;
						}
						ysf.put("startTime", strs);
						ysf.put("endTime", stre);
						Map<String,Object> emap=new HashMap<String,Object>();
						List<ResultDto> ysfList=tAgeAnalysisService.allSumQJ(ysf);
						Result r7=new Result();
						r7.setType(5);               
						emap.put("title", "各区间应付账龄占比");
						emap.put("content", ysfList);
						emap.put("yj", "");
						r7.setData(emap);
						if(ysfList!=null && ysfList.size()>0){
							list.add(r7);
						}
						ysf.put("startTime", startTime);
						ysf.put("endTime", endTime);
					}else if(clzzs[i].equals("4")){
						ysf.put("type", "1");
						Map<String,Object> emap=new HashMap<String,Object>();
						List<ResultDto> reList=tAgeAnalysisService.top5(ysf);
						Result r7=new Result();
						r7.setType(6);               
						emap.put("title", "前五大应付供应商对比");
						emap.put("content", reList);
						emap.put("yj", "");
						r7.setData(emap);
						if(reList!=null && reList.size()>0){
							list.add(r7);
						}
					}else if(clzzs[i].equals("5")){
						ysf.put("type", "6");
						Date sts=DateUtil.stringToDate(startTime, "yyyy-MM-dd");
						Date ets=DateUtil.stringToDate(endTime, "yyyy-MM-dd");
						Calendar c1 = Calendar.getInstance(); 
						c1.setTime(sts);
						String ys1=(c1.get(Calendar.YEAR))+"";
						int msi1=c1.get(Calendar.MONTH)+1;
						String ms1=(c1.get(Calendar.MONTH)+1)+"";
						String strs="";
						if(msi1<10){
							strs=ys1+"0"+ms1;
						}else{
							strs=ys1+ms1;
						}
						
						Calendar c2 = Calendar.getInstance(); 
						c2.setTime(ets);
						String ys2=(c2.get(Calendar.YEAR))+"";
						int msi2=c2.get(Calendar.MONTH)+1;
						String ms2=(c2.get(Calendar.MONTH)+1)+"";
						String stre="";
						if(msi2<10){
							stre=ys2+"0"+ms2;
						}else{
							stre=ys2+ms2;
						}
						ysf.put("startTime", strs);
						ysf.put("endTime", stre);
						Map<String,Object> emap=new HashMap<String,Object>();
						List<ResultDto> ysfList=tAgeAnalysisService.allSumQJ(ysf);
						Result r7=new Result();
						r7.setType(5);               
						emap.put("title", "各区间应收账龄占比");
						emap.put("content", ysfList);
						emap.put("yj", "");
						r7.setData(emap);
						if(ysfList!=null && ysfList.size()>0){
							list.add(r7);
						}
						ysf.put("startTime", startTime);
						ysf.put("endTime", endTime);
					}else if(clzzs[i].equals("6")){
						ysf.put("type", "2");
						Map<String,Object> emap=new HashMap<String,Object>();
						List<ResultDto> reList=tAgeAnalysisService.top5(ysf);
						Result r7=new Result();
						r7.setType(6);               
						emap.put("title", "前五大应收供应商对比");
						emap.put("content", reList);
						emap.put("yj", "");
						r7.setData(emap);
						if(reList!=null && reList.size()>0){
							list.add(r7);
						}
					}else if(clzzs[i].equals("7")){           //货币资金
						
					}
				}
			}
		}
		return list;
	}
}







package com.qst.financial.service.impl.subject;


import com.qst.financial.dao.mapper.subject.ReportResultMapper;
import com.qst.financial.dao.mapper.subject.TAgeAnalysisMapper;
import com.qst.financial.dto.Result2;
import com.qst.financial.dto.ResultDto;
import com.qst.financial.modle.subject.TAgeAnalysis;
import com.qst.financial.service.impl.base.BaseServiceImpl;
import com.qst.financial.service.subject.TAgeAnalysisService;
import com.qst.financial.util.common.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TAgeAnalysisServiceImpl extends BaseServiceImpl<TAgeAnalysis, Integer> implements TAgeAnalysisService {
	 @Autowired
	 private ReportResultMapper reportResultMapper;
	 @Autowired 
	 private TAgeAnalysisMapper tAgeAnalysisMapper;
	 public List<ResultDto> selectSumTop(Map<String,Object> map){
		return tAgeAnalysisMapper.selectSumTop(map);
	 }
	 public List<ResultDto> selectSum(Map<String,Object> map){
		return tAgeAnalysisMapper.selectSum(map);
	 }
	 public List<TAgeAnalysis> selectSumAll(Map<String,Object> map){
		 return tAgeAnalysisMapper.selectSumAll(map);
	 }
	@Override
	public int deleteByYearMonth(String yearMonth,Long accountPortType,Integer type,Long orgId) {
		return tAgeAnalysisMapper.deleteByYearMonth(yearMonth,accountPortType,type,orgId);
	}
	 public Map<String,Object> selectSumAll2(Map<String,Object> map){
		 Map<String,Object> maps=new HashMap<String,Object>();
		 String type=(String) map.get("type");
		 List<TAgeAnalysis> rList=new ArrayList<>();
		 if(type.equals("9") || type.equals("10")){
			 rList= tAgeAnalysisMapper.selectSumTops(map);
		 }else{
			 Integer count =tAgeAnalysisMapper.selectCount(map);
			 if(count==null || count.equals(0)){
				 return null;
			 }
			 rList= tAgeAnalysisMapper.selectSumAll(map);
		 }
		 List<String> keyList=new ArrayList<String>();
		// keyList.add("1个月内");
		 keyList.add("0到90天");
		 keyList.add("91到180天");
		 keyList.add("181到360天");
		 keyList.add("361到720天");
		 keyList.add("721到1080天");
		 keyList.add("1081以上");
		 //keyList.add("48个月以上");
		 maps.put("keyList", keyList);
		 List<Result2> rLists=new ArrayList<Result2>();
		 if(rList!=null && rList.size()>0){
			 for (TAgeAnalysis tAgeAnalysis : rList) {
				 if(tAgeAnalysis!=null){
					 Result2 r2=new Result2();
						// BigDecimal to30Days=new BigDecimal("0.00");
						 BigDecimal to90Days=new BigDecimal("0.00");
						 BigDecimal to180Days=new BigDecimal("0.00");
						 BigDecimal t181Days=new BigDecimal("0.00");
						 BigDecimal t720Days=new BigDecimal("0.00");
						 BigDecimal t1080Days=new BigDecimal("0.00");
						 BigDecimal t1440Days=new BigDecimal("0.00");
						 //BigDecimal t1441Days=new BigDecimal("0.00");
						 r2.setName(tAgeAnalysis.getProjectName());
						/* if(tAgeAnalysis.getNotDue1To30Days()!=null){
							 to30Days=to30Days.add(tAgeAnalysis.getNotDue1To30Days()); 
						 }
						 if(tAgeAnalysis.getDue1To30Days()!=null){
							 to30Days=to30Days.add(tAgeAnalysis.getDue1To30Days());
						 }*/
						 if(tAgeAnalysis.getDue1To30Days()!=null){
							 to90Days=to90Days.add(tAgeAnalysis.getDue1To30Days()); 
						 }
						 if(tAgeAnalysis.getDue31To90Days()!=null){
							 to90Days=to90Days.add(tAgeAnalysis.getDue31To90Days()); 
						 }
						 if(tAgeAnalysis.getDueToday()!=null){
							 to90Days=to90Days.add(tAgeAnalysis.getDueToday());
						 }
						 /*if(tAgeAnalysis.getNotDue91To180Days()!=null){
							 to180Days=to180Days.add(tAgeAnalysis.getNotDue91To180Days()); 
						 }*/
						 if(tAgeAnalysis.getDue91To180Days()!=null){
							 to180Days=to180Days.add(tAgeAnalysis.getDue91To180Days()); 
						 }
						/* if(tAgeAnalysis.getNotDue181Days()!=null){
							 t181Days=t181Days.add(tAgeAnalysis.getNotDue181Days()); 
						 }*/
						 if(tAgeAnalysis.getDue181To360Days()!=null){
							 t181Days=t181Days.add(tAgeAnalysis.getDue181To360Days()); 
						 }
						 
						 if(tAgeAnalysis.getDue361To720Days()!=null){
							 t720Days=t720Days.add(tAgeAnalysis.getDue361To720Days()); 
						 }
						 if(tAgeAnalysis.getDue721To1080Days()!=null){
							 t1080Days=t1080Days.add(tAgeAnalysis.getDue721To1080Days()); 
						 }
						 if(tAgeAnalysis.getDue1081To1440Days()!=null){
							 t1440Days=t1440Days.add(tAgeAnalysis.getDue1081To1440Days()); 
						 }
						/* if(tAgeAnalysis.getDue1441Days()!=null){
							 t1441Days=t181Days.add(tAgeAnalysis.getDue1441Days()); 
						 }*/
						 List<BigDecimal> lists=new ArrayList<BigDecimal>();
						 //lists.add(to30Days);
						 lists.add(to90Days.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP));
						 lists.add(to180Days.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP));
						 lists.add(t181Days.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP));
						 lists.add(t720Days.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP));
						 lists.add(t1080Days.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP));
						 lists.add(t1440Days.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP));
						 //lists.add(t1441Days);
						 r2.setDate(lists);
						 rLists.add(r2);
				 }
			 }
			 maps.put("date", rLists);
			 return maps;
		 }else{
			 return null;
		 }
	 }

	 public List<TAgeAnalysis> selectSumAllByQJ(Map<String,Object> map){
		 return tAgeAnalysisMapper.selectSumAllByQJ(map);
	 }
	 @Override
	 public List<TAgeAnalysis> selectSumAllByType(Map<String,Object> map){
		 return tAgeAnalysisMapper.selectSumAllByType(map);
	 }
	 public List<ResultDto> allSumQJ(Map<String,Object> map){
		/* Integer count =tAgeAnalysisMapper.selectCount(map);
		 if(count==null || count.equals(0)){
			 return null;
		 }*/
		 String type=(String) map.get("type");
		 List<TAgeAnalysis> taList=new ArrayList<TAgeAnalysis>();

			 taList=tAgeAnalysisMapper.selectSumAllByQJ(map);

		
		 BigDecimal to90Days=new BigDecimal("0.00");
		 BigDecimal to180Days=new BigDecimal("0.00");
		 BigDecimal t181Days=new BigDecimal("0.00");
		 BigDecimal t720Days=new BigDecimal("0.00");
		 BigDecimal t1080Days=new BigDecimal("0.00");
		 BigDecimal t1440Days=new BigDecimal("0.00");
		 List<ResultDto> rList=new ArrayList<ResultDto>();
		 if(taList!=null && taList.size()>0){
			 for (TAgeAnalysis tAgeAnalysis : taList) {
					

				 if(tAgeAnalysis!=null){
					 if(tAgeAnalysis.getDue1To30Days()!=null){
						 to90Days=to90Days.add(tAgeAnalysis.getDue1To30Days()); 
					 }
					 if(tAgeAnalysis.getDue31To90Days()!=null){
						 to90Days=to90Days.add(tAgeAnalysis.getDue31To90Days()); 
					 }
					 if(tAgeAnalysis.getDueToday()!=null){
						 to90Days=to90Days.add(tAgeAnalysis.getDueToday());
					 }

					 if(tAgeAnalysis.getDue91To180Days()!=null){
						 to180Days=to180Days.add(tAgeAnalysis.getDue91To180Days()); 
					 }

					 if(tAgeAnalysis.getDue181To360Days()!=null){
						 t181Days=t181Days.add(tAgeAnalysis.getDue181To360Days()); 
					 }
					 
					 if(tAgeAnalysis.getDue361To720Days()!=null){
						 t720Days=t720Days.add(tAgeAnalysis.getDue361To720Days()); 
					 }
					 if(tAgeAnalysis.getDue721To1080Days()!=null){
						 t1080Days=t1080Days.add(tAgeAnalysis.getDue721To1080Days()); 
					 }
					 if(tAgeAnalysis.getDue1081To1440Days()!=null){
						 t1440Days=t1440Days.add(tAgeAnalysis.getDue1081To1440Days()); 
					 }
				 }
			 } 
		 }
		 
		 BigDecimal sum=to90Days.add(to180Days).add(t181Days).add(t720Days).add(t1080Days).add(t1440Days);
		 if(sum.compareTo(BigDecimal.ZERO)==0){
			 return null;
		 }

		 ResultDto rt2=new ResultDto();
		 rt2.setCode("to90Days");
		 rt2.setCodeName("0到90天");
		 rt2.setBigSumMoney(to90Days);
		 rt2.setSumMoney(to90Days.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP).toString());
		 rList.add(rt2);
		 ResultDto rt3=new ResultDto();
		 rt3.setCode("to180Days");
		 rt3.setCodeName("91到180天");
		 rt3.setBigSumMoney(to180Days);
		 rt3.setSumMoney(to180Days.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP).toString());
		 rList.add(rt3);
		 ResultDto rt4=new ResultDto();
		 rt4.setCode("t181Days");
		 rt4.setCodeName("181到360天");
		 rt4.setBigSumMoney(t181Days);
		 rt4.setSumMoney(t181Days.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP).toString());
		 rList.add(rt4);
		 ResultDto rt5=new ResultDto();
		 rt5.setCode("t720Days");
		 rt5.setCodeName("361到720天");
		 rt5.setBigSumMoney(t720Days);
		 rt5.setSumMoney(t720Days.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP).toString());
		 rList.add(rt5);
		 ResultDto rt6=new ResultDto();
		 rt6.setCode("t1080Days");
		 rt6.setCodeName("721到1080天");
		 rt6.setBigSumMoney(t1080Days);
		 rt6.setSumMoney(t1080Days.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP).toString());
		 rList.add(rt6);
		 ResultDto rt7=new ResultDto();
		 rt7.setCode("t1440Days");
		 rt7.setCodeName("1081以上");
		 rt7.setBigSumMoney(t1440Days);
		 rt7.setSumMoney(t1440Days.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP).toString());
		 rList.add(rt7);

		 return rList;
	 }
	 public List<TAgeAnalysis> selectSumAllByQJ2(Map<String,Object> map){
		List<TAgeAnalysis> rlist= tAgeAnalysisMapper.selectSumAllByQJ(map);
		BigDecimal sums=new BigDecimal("0.00");
		for (TAgeAnalysis tAgeAnalysis : rlist) {
			
		}
		return rlist;
	 }
	 public List<ResultDto> allSum(Map<String,Object> map){
		 BigDecimal allSum=new BigDecimal("0.00");
		 List<ResultDto> allList=tAgeAnalysisMapper.selectSum(map);
		 if(allList!=null && allList.size()>0){
			 ResultDto rt=allList.get(0);
			 if(rt.getBigSumMoney()!=null){
				 allSum=rt.getBigSumMoney();
			 }
		 }
		 BigDecimal othenSum=new BigDecimal("0.00");
		 BigDecimal othen=new BigDecimal("0.00");
		 BigDecimal othenBl=new BigDecimal("0.00");
		 List<ResultDto> resultList=new ArrayList<ResultDto>();
		 if(allSum==null || allSum.compareTo(BigDecimal.ZERO)==0){
			 return null;
		 }else{
			 List<ResultDto> sumList=tAgeAnalysisMapper.selectSumTop(map);
			 for(ResultDto sums : sumList){
				 BigDecimal bl=(sums.getBigSumMoney().divide(allSum,4, RoundingMode.HALF_UP)).multiply(new BigDecimal("100"));
				 sums.setBl(bl);
				 sums.setSumMoney(sums.getBigSumMoney()==null ? "0":sums.getBigSumMoney().toString());
				 resultList.add(sums);
				 othenSum=othenSum.add(sums.getBigSumMoney());
			 }
		 }
		/* othen=allSum.subtract(othenSum);
		 othenBl= (othen.divide(allSum,4, RoundingMode.HALF_UP)).multiply(new BigDecimal("100"));
		 ResultDto rt=new ResultDto();
		 rt.setCode("othen");
		 rt.setCodeName("其他");
		 rt.setBigSumMoney(othen);
		 rt.setSumMoney(othen.toString());
		 rt.setBl(othenBl);
		 resultList.add(rt);*/
		 return resultList;
	 }
	 public List<ResultDto> top5(Map<String,Object> map){
		 List<ResultDto> sumList=tAgeAnalysisMapper.selectSumTop(map);
		 String startDt="";
		 String endDt="";
		 String endTimes="";
		 if(map.get("startTime")!=null && map.get("startTime").toString()!=""){
			startDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(map.get("startTime").toString(),"yyyy-MM-dd"),"yyyy-MM-dd",-1,4), "yyyy-MM-dd");
		 }
		 if(map.get("endTime")!=null && map.get("endTime").toString()!=""){
			endDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(map.get("endTime").toString(),"yyyy-MM-dd"),"yyyy-MM-dd",-1,4), "yyyy-MM-dd");
		 }
		 if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
			endTimes=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(map.get("endTimes").toString(),"yyyy-MM-dd"),"yyyy-MM-dd",-1,4), "yyyy-MM-dd");
		 }
		 map.put("startTime", startDt);
		 map.put("endTime", endDt);
		 map.put("startTimes", endTimes);
		 List<ResultDto> sumList2=tAgeAnalysisMapper.selectSumTop(map);
		 List<ResultDto> rList=new ArrayList<>();
		 for (ResultDto resultDto : sumList) {
			 for (ResultDto resultDto2 : sumList2) {
				 if(resultDto.getCode().equals(resultDto2.getCode())){
					 resultDto.setTbSumMoney(resultDto2.getBigSumMoney());
					 rList.add(resultDto);
				 }
					
			 }
		 }
		 return rList;
	 }
}




package com.qst.financial.service.impl.subject;


import com.qst.financial.dao.mapper.base.SysDictionaryDetailMapper;
import com.qst.financial.dao.mapper.base.SysDictionaryMapper;
import com.qst.financial.dao.mapper.subject.ReportResultMapper;
import com.qst.financial.dto.ResultDto;
import com.qst.financial.modle.base.SysDictionaryDetailModel;
import com.qst.financial.modle.base.SysDictionaryModel;
import com.qst.financial.modle.subject.ReportResult;
import com.qst.financial.modle.subject.TAgeAnalysis;
import com.qst.financial.modle.subject.TKmye;
import com.qst.financial.service.impl.base.BaseServiceImpl;
import com.qst.financial.service.subject.BilvService;
import com.qst.financial.service.subject.ReportResultService;
import com.qst.financial.service.subject.TAgeAnalysisService;
import com.qst.financial.util.Dates;
import com.qst.financial.util.NeedCaluateModeEum;
import com.qst.financial.util.common.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReportResultServiceImpl extends BaseServiceImpl<ReportResult,String> implements ReportResultService {
	private static final String REPORT_TYPE_LR = "2" ;
	private static final String REPORT_TYPE_ZCFZ = "1" ;
	private static final String REPORT_TYPE_XJLL = "3" ;
	@Autowired
	private TAgeAnalysisService tAgeAnalysisService;
	@Autowired
	private ReportResultMapper reportResultMapper;
	@Autowired
	private BilvService bilvService;
	@Autowired
	private SysDictionaryMapper sysDictionaryMapper;
	@Autowired
	private SysDictionaryDetailMapper sysDictionaryDetailMapper;
	/**
	 * 得到对应的总数，map中有开始时间，结束时间，类别，科目类别
	 */



	@Override
	public BigDecimal selectTBSum(Map<String,Object> map){
		return reportResultMapper.selectTBSum(map);
	}

	/**
	 * 得到对应的总数，map中有开始时间 not null，结束时间 not null，类别，科目类别   
	 */
	@Override
	public BigDecimal selectValueSum(Map<String, Object> map) {
		return reportResultMapper.selectValueSum(map);
	}
	@Override
	public Map<String, BigDecimal> getDbMsgMap(String startTime, String endTime, String orgId) throws Exception {
		//endTime=endTime+"-01";
		//startTime=startTime+"-01";
		Map<String, Object> map=new HashMap<String, Object>();
		Map<String, Object> map2=new HashMap<String, Object>();
		map.put("endTimes", endTime);
		map.put("reportType", "1");
		map.put("orgId", orgId);
		List<ResultDto> list= (List<ResultDto>) reportResultMapper.selectValueSumDetail(map);
		Map<String,BigDecimal> rMap = new HashMap<>() ;
		if(list!=null && list.size()>0){
			for (ResultDto resultDto : list) {
				String code = resultDto.getCode().replace("-", "_");
				rMap.put(code, resultDto.getBigSumMoney());
			}
		}
		map2.put("startTime", startTime);
		map2.put("endTime", endTime);
		map2.put("reportType", "2");
		map2.put("orgId", orgId);
		List<ResultDto> list2= (List<ResultDto>) reportResultMapper.selectValueSumDetail(map2);
		if(list2!=null && list2.size()>0){
			for (ResultDto resultDto : list2) {
				/*if(resultDto.getCode().equals("LR-AF")){
					int s=1;
					System.out.println(s);
				}*/
				String code = resultDto.getCode().replace("-", "_");
				rMap.put(code, resultDto.getBigSumMoney());
			}
		}
		return rMap;
	}
	@Override
	public ResultDto selectValueSumResultDto(Map<String, Object> map) {

		BigDecimal money=reportResultMapper.selectValueSum(map); 
		String startDt="";
		String endTime="";
		String endTimes="";
		if(map.get("startTime")!=null && map.get("startTime").toString()!=""){
			String st=map.get("startTime").toString();
			if(st.length()<8){
				st=st+"-01";
			}
			startDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(st,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4),"yyyy-MM-dd");
		}
		if(map.get("endTime")!=null && map.get("endTime").toString()!=""){
			String et=map.get("endTime").toString();
			if(et.length()<8){
				et=et+"-01";
			}
			endTime=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(et,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4),"yyyy-MM-dd");
		}
		if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
			String ets=map.get("endTimes").toString();
			if(ets.length()<8){
				ets=ets+"-01";
			}
			endTimes=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(ets,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4),"yyyy-MM-dd");
		}
		map.put("startTime", startDt);
		map.put("endTime", endTime);
		map.put("endTimes", endTimes);
		BigDecimal tbMoney=reportResultMapper.selectValueSum(map);
		/*if(tbMoney==null){
			tbMoney=new BigDecimal("0.00");
		}*/
		BigDecimal tb=new BigDecimal("0.00");
		String strTb="-";
		String sumMoney="0";
		if(money!=null){
			if(tbMoney!=null){
				int a=tbMoney.compareTo(BigDecimal.ZERO);
				if(a!=0){
					tb=((money.subtract(tbMoney)).multiply(new BigDecimal("100")).divide(tbMoney.abs(),2, RoundingMode.HALF_UP));
					strTb=tb.toString();
				}else{
					strTb="-";
				}
			}
		}else{
			money=new BigDecimal("0.00");
			strTb="-";
		}
		if(tbMoney==null){
			tbMoney=new BigDecimal("0");
		}
		if(map.get("flag")==null){
			money=money.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);              //金额单位转换成万元
			tbMoney=tbMoney.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);              //金额单位转换成万元
		}
		sumMoney=money.toString();
		ResultDto rd=new ResultDto();
		rd.setBigSumMoney(money);
		rd.setSumMoney(sumMoney);
		rd.setTbSumMoney(tbMoney);
		/*String type="3";
		Object types= map.get("type");*/
		rd.setOnRise(strTb);
		/*if(types!=null){
			type=types.toString();
		}
		if(!type.equals("3")){
			rd.setOnRise(strTb);
		}*/
		return rd;
	}




	@Override
	public List<ResultDto> selectValueYearResultDto(Map<String, Object> map) {
		List<ResultDto> rList=new ArrayList<>();
		List<ResultDto> rsList=reportResultMapper.selectValueSumByYear(map);
		int year=(int) map.get("time");
		year=year-1;
		map.put("time", year);
		List<ResultDto> tbrsList=reportResultMapper.selectValueSumByYear(map);
		if(rsList!=null && rsList.size()>0){
			for (ResultDto resultDto : rsList) {
				//int flag=1;
				BigDecimal money=resultDto.getBigSumMoney();
				BigDecimal tbMoney=new BigDecimal("0.00");
				//BigDecimal tb=new BigDecimal("0.00");
				String tb="";
				if(tbrsList!=null && tbrsList.size()>0){
					if(tbrsList!=null && tbrsList.size()>0){
						for (ResultDto tbresultDto : tbrsList) {
							int years=tbresultDto.getYear()+1;
							if(resultDto.getYear().equals(years+"")){
								if(resultDto.getBigSumMoney()!=null){
									tbMoney=resultDto.getBigSumMoney();
									if((tbMoney.compareTo(BigDecimal.ZERO))!=0 && money.compareTo(BigDecimal.ZERO)!=0){
										tb=((money.subtract(tbMoney)).multiply(new BigDecimal("100")).divide(tbMoney.abs(),2, RoundingMode.HALF_UP)).toString();
									}else if(tbMoney.compareTo(BigDecimal.ZERO)==0 && money.compareTo(BigDecimal.ZERO)==0){
										tb="-";
									}else if(tbMoney.compareTo(BigDecimal.ZERO)==0 && money.compareTo(BigDecimal.ZERO)!=0){
										tb="-";
									}else{
										tb=((money.subtract(tbMoney)).multiply(new BigDecimal("100")).divide(tbMoney.abs(),2, RoundingMode.HALF_UP)).toString();
									}
								}else{
									tb="-";
								}


							}
							//flag=2;
						}
					}
				}
				tbMoney=tbMoney.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP); 
				money=money.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);              //金额单位转换成万元
				/*if(flag==1){
					tb=new BigDecimal("100.00");
				}*/
				resultDto.setTbSumMoney(tbMoney);
				resultDto.setSumMoney(money.toString());
				resultDto.setOnRise(tb);
				resultDto.setBigSumMoney(money);
				rList.add(resultDto);
			}
		}else{
			return null;
		}
		return rList;
	}
	/**
	 * 只对于线性条有用
	 */
	@Override
	public List<ResultDto> selectValueMonthResultDto(Map<String, Object> map) {
		List<ResultDto> rList=new ArrayList<>();
		int monthDiff=0;
		try {
			if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
				monthDiff=1;
			}else{
				monthDiff = Dates.getDateTimes(map.get("startTime").toString(),map.get("endTime").toString())+1;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<ResultDto> rsList=reportResultMapper.selectValueSumByMoth(map);
		String startDt="";
		String endDt="";
		String endTimes="";
		String bzStartTime="";
		String bzEndTime="";
		String bzEndTimes="";
		if(map.get("startTime")!=null && map.get("startTime").toString()!=""){
			bzStartTime=map.get("startTime").toString();
			startDt= DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(map.get("startTime").toString(),"yyyy-MM-dd"),"yyyy-MM-dd",-1,4),"yyyy-MM-dd");
		}
		if(map.get("endTime")!=null && map.get("endTime").toString()!=""){
			bzEndTime=map.get("endTime").toString();
			endDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(map.get("endTime").toString(),"yyyy-MM-dd"),"yyyy-MM-dd",-1,4),"yyyy-MM-dd");
		}
		if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
			bzEndTimes=map.get("endTimes").toString();
			endTimes=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(map.get("endTimes").toString(),"yyyy-MM-dd"),"yyyy-MM-dd",-1,4),"yyyy-MM-dd");
		}
		map.put("startTime", startDt);
		map.put("endTime", endDt);
		map.put("endTimes", endTimes);
		List<ResultDto> tbrsList=reportResultMapper.selectValueSumByMoth(map);
		String hbsDate="";
		String hbeDt="";
		String hbeDts="";
		if(bzStartTime!=null && bzStartTime!=""){
			hbsDate=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(bzStartTime,"yyyy-MM-dd"),"yyyy-MM-dd",1*(-1),3), "yyyy-MM-dd");
		}
		if(bzEndTime!=null && bzEndTime!=""){
			hbeDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(bzEndTime,"yyyy-MM-dd"),"yyyy-MM-dd",1*(-1),3), "yyyy-MM-dd");
		}
		if(bzEndTimes!=null && bzEndTimes!=""){
			hbeDts=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(bzEndTimes,"yyyy-MM-dd"),"yyyy-MM-dd",1*(-1),3), "yyyy-MM-dd");
		}
		map.put("startTime", hbsDate);
		map.put("endTime", hbeDt);
		map.put("endTimes", hbeDts);
		List<ResultDto> hbrsList=reportResultMapper.selectValueSumByMoth(map);
		if(rsList!=null && rsList.size()>0){
			for (ResultDto resultDto : rsList) {
				BigDecimal money=resultDto.getBigSumMoney();

				BigDecimal tbMoney=new BigDecimal("0.00");
				BigDecimal hbMoney=new BigDecimal("0.00");
				//int flag=1;
				String tb="-";
				//BigDecimal hb=new BigDecimal("0.00");
				String hb="-";
				if(tbrsList!=null && tbrsList.size()>0){
					if(tbrsList!=null && tbrsList.size()>0){
						for (ResultDto tbresultDto : tbrsList) {
							String str=DateUtil.dateFormatToString(DateUtil.addTime(DateUtil.stringToDate(tbresultDto.getYearMoth(), "yyyy-MM"), "yyyy-MM", 1, 4), "yyyy-MM");
							if(resultDto.getYearMoth().equals(str)){
								if(tbresultDto.getBigSumMoney()!=null){
									tbMoney=tbresultDto.getBigSumMoney();
									if((tbMoney.compareTo(BigDecimal.ZERO))!=0 && money.compareTo(BigDecimal.ZERO)!=0){
										tb=((resultDto.getBigSumMoney().subtract(tbresultDto.getBigSumMoney())).multiply(new BigDecimal("100")).divide(tbMoney.abs(),2, RoundingMode.HALF_UP)).toString();
									}else if(tbMoney.compareTo(BigDecimal.ZERO)==0 && money.compareTo(BigDecimal.ZERO)==0){
										tb="-";
									}else if(tbMoney.compareTo(BigDecimal.ZERO)==0 && money.compareTo(BigDecimal.ZERO)!=0){
										tb="-";
									}else{
										tb="-100";
									}
								}else{
									tb="-";
								}

							}
							//flag=2;
						}
					}
				}
				if(hbrsList!=null && hbrsList.size()>0){
					if(hbrsList!=null && hbrsList.size()>0){
						for (ResultDto hbresultDto : hbrsList) {
							String str=DateUtil.dateFormatToString(DateUtil.addTime(DateUtil.stringToDate(hbresultDto.getYearMoth(), "yyyy-MM"), "yyyy-MM", 1,3), "yyyy-MM");
							if(resultDto.getYearMoth().equals(str)){
								if(hbresultDto.getBigSumMoney()!=null){
									hbMoney=hbresultDto.getBigSumMoney();
									if((hbMoney.compareTo(BigDecimal.ZERO))!=0 && money.compareTo(BigDecimal.ZERO)!=0){
										hb=((resultDto.getBigSumMoney().subtract(hbresultDto.getBigSumMoney())).multiply(new BigDecimal("100")).divide(hbMoney.abs(),2, RoundingMode.HALF_UP)).toString();
									}else if(hbMoney.compareTo(BigDecimal.ZERO)==0 && money.compareTo(BigDecimal.ZERO)==0){
										hb="-";
									}else if(hbMoney.compareTo(BigDecimal.ZERO)==0 && money.compareTo(BigDecimal.ZERO)!=0){
										hb="-";
									}else{
										hb="-100";
									}
								}else{
									hb="-";
								}

							}
							//flag=2;
						}
					}
				}
				if(map.get("flag")==null){
					hbMoney=hbMoney.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);  
					tbMoney=tbMoney.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);    
					money=money.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);              //金额单位转换成万元
				}
				/*if(flag==1){
					tb=new BigDecimal("100.00");
				}*/
				resultDto.setTbSumMoney(tbMoney);
				resultDto.setSumMoney(money.toString());
				resultDto.setOnRise(tb.toString());
				resultDto.setHbSumMoney(hbMoney);
				resultDto.setLinkRise(hb.toString());
				resultDto.setBigSumMoney(money);
				rList.add(resultDto);
			}
		}else{
			return null;
		}
		return rList;
	}
	/**
	 * 得到详情时候可根据这个查询
	 * @param map
	 * @return
	 */
	@Override
	public ResultDto selectAllValueSum(Map<String,Object> map){
		return reportResultMapper.selectAllValueSum(map);
	}
	/**
	 * 根据月份得到结果
	 */
	@Override
	public List<ResultDto> selectValueSumByMoth(Map<String,Object> map){
		return reportResultMapper.selectValueSumByMoth(map);
	}
	/**
	 * 点击获取资产余额表详情
	 */
	@Override
	public List<ResultDto> selectValueSumDetail(Map<String,Object> map){
		return reportResultMapper.selectValueSumDetail(map);
	}
	public ResultDto selectValueSumDetailAllTotal(Map<String,Object> map){
		ResultDto rd=new ResultDto();
		String startTime="";
		String endTime="";
		String sendTimes="";
		if(map.get("startTime")!=null && map.get("startTime").toString()!=""){
			startTime=map.get("startTime").toString();
		}
		if(map.get("endTime")!=null && map.get("endTime").toString()!=""){
			endTime=map.get("endTime").toString();
		}
		if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
			sendTimes=map.get("endTimes").toString();
		}
		int monthDiff=0;
		try {
			if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
				monthDiff= Dates.getDateTimes(map.get("startTimes").toString(),map.get("endTimes").toString())+1;
			}else{
				monthDiff = Dates.getDateTimes(map.get("startTime").toString(),map.get("endTime").toString())+1;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		/*String startTime=map.get("startTime").toString();
		String endTime=map.get("endTime").toString();*/
		//本期金额
		BigDecimal money=reportResultMapper.selectValueSum(map); 
		String startDt="";
		String endDt="";
		String endTimes="";
		if(map.get("startTime")!=null && map.get("startTime").toString()!=""){
			startDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(startTime,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4), "yyyy-MM-dd");
		}
		if(map.get("endTime")!=null && map.get("endTime").toString()!=""){
			endDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4), "yyyy-MM-dd");
		}
		if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
			endTimes=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(sendTimes,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4), "yyyy-MM-dd");
		}
		map.put("startTime", startDt);
		map.put("endTime", endDt);
		map.put("endTimes", endTimes);
		//上期金额
		BigDecimal tbMoney=reportResultMapper.selectValueSum(map);
		String hbsDate="";
		String hbeDt="";
		String hbeDts="";
		if(map.get("startTime")!=null && map.get("startTime").toString()!=""){
			hbsDate=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(startTime,"yyyy-MM-dd"),"yyyy-MM-dd",monthDiff*(-1),3), "yyyy-MM-dd");
		}
		if(map.get("endTime")!=null && map.get("endTime").toString()!=""){
			hbeDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime,"yyyy-MM-dd"),"yyyy-MM-dd",monthDiff*(-1),3), "yyyy-MM-dd");
		}
		if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
			hbeDts=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(sendTimes,"yyyy-MM-dd"),"yyyy-MM-dd",monthDiff*(-1),3), "yyyy-MM-dd");
		}
		map.put("startTime", hbsDate);
		map.put("endTime", hbeDt);
		map.put("endTimes", hbeDts);
		BigDecimal hbMoney=reportResultMapper.selectValueSum(map);
		if(tbMoney==null){
			tbMoney=new BigDecimal("0.00");
		}
		if(hbMoney==null){
			hbMoney=new BigDecimal("0.00");
		}
		/*rd.setCode(tbMoney.toString());
		rd.setCodeName(hbMoney.toString());*/
		//BigDecimal tb=new BigDecimal("0.00");
		String tb="";
		//BigDecimal hb=new BigDecimal("0.00");
		String hb="";
		if(money!=null){
			/*if(!tbMoney.equals(new BigDecimal("0.00"))){
				tb=((money.subtract(tbMoney)).divide(tbMoney,4, RoundingMode.HALF_UP)).multiply(new BigDecimal("100"));
			}else{
				tb=new BigDecimal("100.00");
			}*/ //计算环比   环比增长速度=（本期数－上期数）÷上期数×100%
			if(tbMoney.compareTo(BigDecimal.ZERO)==0 && money.compareTo(BigDecimal.ZERO)!=0){
				//tb=new BigDecimal("100.00");
				tb="-";
			}else if(tbMoney.compareTo(BigDecimal.ZERO)==0 && money.compareTo(BigDecimal.ZERO)==0){
				tb="-";
			}else{
				tb=((money.subtract(tbMoney)).multiply(new BigDecimal("100")).divide(tbMoney.abs(),2, RoundingMode.HALF_UP)).toString();
			}
			/*if(!tbMoney.equals(new BigDecimal("0.00"))){
				hb=((money.subtract(hbMoney)).divide(hbMoney,4, RoundingMode.HALF_UP)).multiply(new BigDecimal("100"));
			}else{
				hb=new BigDecimal("100.00");
			}*/
			if(hbMoney.compareTo(BigDecimal.ZERO)==0 && money.compareTo(BigDecimal.ZERO)!=0){
				//hb=new BigDecimal("100.00");
				hb="-";
			}else if(hbMoney.compareTo(BigDecimal.ZERO)==0 && money.compareTo(BigDecimal.ZERO)==0){
				hb="-";
			}else{
				hb=((money.subtract(hbMoney)).multiply(new BigDecimal("100")).divide(hbMoney.abs(),2, RoundingMode.HALF_UP)).toString();
			}
			money=money.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);   //金额单位转换成万元
		}else{
			money=new BigDecimal("0.00");
			tb="-";
			hb="-";
		}
		rd.setBigSumMoney(money);
		rd.setSumMoney(money.toString());
		rd.setTbSumMoney(tbMoney);
		rd.setHbSumMoney(hbMoney);
		rd.setOnRise(tb.toString());
		rd.setLinkRise(hb.toString());
		return rd;
	}
	@Override
	public List<ResultDto> selectValueSumDetailAll(Map<String,Object> map){
		/*String startTime=map.get("startTime").toString();
		String endTime=map.get("endTime").toString();*/
		String startTime="";
		String endTime="";
		String sendTimes="";
		if(map.get("startTime")!=null && map.get("startTime").toString()!=""){
			startTime=map.get("startTime").toString();
		}
		if(map.get("endTime")!=null && map.get("endTime").toString()!=""){
			endTime=map.get("endTime").toString();
		}
		if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
			sendTimes=map.get("endTimes").toString();
		}
		int monthDiff=0;
		try {
			/*if(map.get("reportType")!=null && map.get("reportType").toString()!="" && map.get("reportType").toString().equals("1")){
				if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
					//String st=map.get("startTimes").toString();
					//String et=map.get("endTimes").toString();
					monthDiff=1;;
				}else{
					monthDiff = Dates.getDateTimes(map.get("startTime").toString(),map.get("endTime").toString())+1;
				}
			}else{*/
				if(map.get("endTimes")!=null && map.get("endTimes").toString()!="" && map.get("startTimes")!=null && map.get("startTimes").toString()!=""){
					monthDiff = Dates.getDateTimes(map.get("startTimes").toString(),map.get("endTimes").toString())+1;
				}else if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
					monthDiff=1;
				}else{
					monthDiff = Dates.getDateTimes(map.get("startTime").toString(),map.get("endTime").toString())+1;
				}
			//}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<ResultDto> rList=new ArrayList<ResultDto>();
		List<ResultDto> sjList=reportResultMapper.selectValueSumDetail(map);
		String startDt="";
		String endDt="";
		String endTimes="";
		if(map.get("startTime")!=null && map.get("startTime").toString()!=""){
			startDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(startTime,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4), "yyyy-MM-dd");
		}
		if(map.get("endTime")!=null && map.get("endTime").toString()!=""){
			endDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4), "yyyy-MM-dd");
		}
		if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
			endTimes=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(sendTimes,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4), "yyyy-MM-dd");
		}
		map.put("startTime", startDt);
		map.put("endTime", endDt);
		map.put("endTimes", endTimes);
		List<ResultDto> tbList=reportResultMapper.selectValueSumDetail(map);
		//int monthDiff = (DateUtil.stringToDate(endTime).getYear() - DateUtil.stringToDate(startTime).getYear()) * 12 + (DateUtil.stringToDate(endTime).getMonth()- DateUtil.stringToDate(startTime).getMonth()) ;
		String hbsDate="";
		String hbeDt="";
		String hbeDts="";
		if(map.get("startTime")!=null && map.get("startTime").toString()!=""){
			hbsDate=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(startTime,"yyyy-MM-dd"),"yyyy-MM-dd",monthDiff*(-1),3), "yyyy-MM-dd");
		}
		if(map.get("endTime")!=null && map.get("endTime").toString()!=""){
			hbeDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime,"yyyy-MM-dd"),"yyyy-MM-dd",monthDiff*(-1),3), "yyyy-MM-dd");
		}
		if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
			hbeDts=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(sendTimes,"yyyy-MM-dd"),"yyyy-MM-dd",monthDiff*(-1),3), "yyyy-MM-dd");
		}
		map.put("startTime", hbsDate);
		map.put("endTime", hbeDt);
		map.put("endTimes", hbeDts);
		List<ResultDto> hbList=reportResultMapper.selectValueSumDetail(map);
		if(sjList!=null && sjList.size()>0){
			for (ResultDto resultDto : sjList) {
				//int flag=1;
				//BigDecimal tb=new BigDecimal("0.00");
				String tb="";
				BigDecimal tbSumMoney=new BigDecimal("0.00");
				BigDecimal hbSumMoney=new BigDecimal("0.00");
				if(tbList!=null && tbList.size()>0){
					for (ResultDto tbresultDto : tbList) {
						if(resultDto.getCode().equals(tbresultDto.getCode())){
							if(tbresultDto.getBigSumMoney()!=null){
								tbSumMoney=tbresultDto.getBigSumMoney();
								if(tbSumMoney.compareTo(BigDecimal.ZERO)==0 && resultDto.getBigSumMoney().compareTo(BigDecimal.ZERO)!=0){
									//tb=new BigDecimal("100.00");
									tb="-";
								}else if(tbSumMoney.compareTo(BigDecimal.ZERO)==0 && resultDto.getBigSumMoney().compareTo(BigDecimal.ZERO)==0){
									tb="-";
								}else{
									tb=((resultDto.getBigSumMoney().subtract(tbresultDto.getBigSumMoney())).multiply(new BigDecimal("100")).divide(tbSumMoney.abs(),2, RoundingMode.HALF_UP)).toString();
								}
							}
							else{
								tb="-";
							}

						}
						//flag=2;
					}

				}
				String hb="";
				if(hbList!=null && hbList.size()>0){
					for (ResultDto hbresultDto : hbList) {
						if(resultDto.getCode().equals(hbresultDto.getCode())){
							if(hbresultDto.getBigSumMoney()!=null){
								hbSumMoney=hbresultDto.getBigSumMoney();
								if(hbSumMoney.compareTo(BigDecimal.ZERO)==0 && resultDto.getBigSumMoney().compareTo(BigDecimal.ZERO)!=0){
									//hb=new BigDecimal("100.00");
									hb="-";
								}else if(hbSumMoney.compareTo(BigDecimal.ZERO)==0 && resultDto.getBigSumMoney().compareTo(BigDecimal.ZERO)==0){
									hb="-";
								}else{
									hb=((resultDto.getBigSumMoney().subtract(hbresultDto.getBigSumMoney())).multiply(new BigDecimal("100")).divide(hbSumMoney.abs(),2, RoundingMode.HALF_UP)).toString();
								}
							}else{
								hb="-";
							}

							//hb=((resultDto.getSumMoney().subtract(hbresultDto.getSumMoney())).divide(hbresultDto.getSumMoney(),4, RoundingMode.HALF_UP)).multiply(new BigDecimal("100"));
						}
						//flag=2;
					}

				}
				BigDecimal money=resultDto.getBigSumMoney();
				if(map.get("isZh")==null || map.get("isZh").toString().equals("0")){
					money=money.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);   //金额单位转换成万元
					tbSumMoney=tbSumMoney.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);   //金额单位转换成万元
					hbSumMoney=hbSumMoney.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);   //金额单位转换成万元
					if(map.get("isW")==null || map.get("isW").toString().equals("0")){
						resultDto.setSumMoney(money.toString());
					}else{
						resultDto.setSumMoney(money.toString()+"万");
					}
				}else{
					resultDto.setSumMoney(money.toString());
				}
				resultDto.setTbSumMoney(tbSumMoney);
				resultDto.setHbSumMoney(hbSumMoney);
				resultDto.setBigSumMoney(money);
				resultDto.setOnRise(tb);
				resultDto.setLinkRise(hb);
				rList.add(resultDto);
			}
		}else{
			return null;
		}

		return rList;
		//return reportResultMapper.selectValueSumDetail(map);
	}
	@Override
	public List<ResultDto> selectValueSumDetailAllBlv(Map<String,Object> map){
		/*String startTime=map.get("startTime").toString();
		String endTime=map.get("endTime").toString();*/
		String startTime="";
		String endTime="";
		String sendTimes="";
		if(map.get("startTime")!=null && map.get("startTime").toString()!=""){
			startTime=map.get("startTime").toString();
		}
		if(map.get("endTime")!=null && map.get("endTime").toString()!=""){
			endTime=map.get("endTime").toString();
		}
		if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
			sendTimes=map.get("endTimes").toString();
		}
		int monthDiff=0;
		try {
			if(map.get("reportType")!=null && map.get("reportType").toString()!="" && map.get("reportType").toString().equals("1")){
				if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
					String st=map.get("startTimes").toString();
					String et=map.get("endTimes").toString();
					monthDiff = Dates.getDateTimes(st,et)+1;
				}else{
					monthDiff = Dates.getDateTimes(map.get("startTime").toString(),map.get("endTime").toString())+1;
				}
			}else{
				if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
					monthDiff=1;
				}else{
					monthDiff = Dates.getDateTimes(map.get("startTime").toString(),map.get("endTime").toString())+1;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<ResultDto> rList=new ArrayList<ResultDto>();
		List<ResultDto> sjList=reportResultMapper.selectValueSumDetail(map);
		String startDt="";
		String endDt="";
		String endTimes="";
		if(map.get("startTime")!=null && map.get("startTime").toString()!=""){
			startDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(startTime,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4), "yyyy-MM-dd");
		}
		if(map.get("endTime")!=null && map.get("endTime").toString()!=""){
			endDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4), "yyyy-MM-dd");
		}
		if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
			endTimes=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(sendTimes,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4), "yyyy-MM-dd");
		}
		map.put("startTime", startDt);
		map.put("endTime", endDt);
		map.put("endTimes", endTimes);
		List<ResultDto> tbList=reportResultMapper.selectValueSumDetail(map);
		//int monthDiff = (DateUtil.stringToDate(endTime).getYear() - DateUtil.stringToDate(startTime).getYear()) * 12 + (DateUtil.stringToDate(endTime).getMonth()- DateUtil.stringToDate(startTime).getMonth()) ;
		String hbsDate="";
		String hbeDt="";
		String hbeDts="";
		if(map.get("startTime")!=null && map.get("startTime").toString()!=""){
			hbsDate=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(startTime,"yyyy-MM-dd"),"yyyy-MM-dd",monthDiff*(-1),3), "yyyy-MM-dd");
		}
		if(map.get("endTime")!=null && map.get("endTime").toString()!=""){
			hbeDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime,"yyyy-MM-dd"),"yyyy-MM-dd",monthDiff*(-1),3), "yyyy-MM-dd");
		}
		if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
			hbeDts=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(sendTimes,"yyyy-MM-dd"),"yyyy-MM-dd",monthDiff*(-1),3), "yyyy-MM-dd");
		}
		map.put("startTime", hbsDate);
		map.put("endTime", hbeDt);
		map.put("endTimes", hbeDts);
		List<ResultDto> hbList=reportResultMapper.selectValueSumDetail(map);
		if(sjList!=null && sjList.size()>0){
			for (ResultDto resultDto : sjList) {
				//int flag=1;
				//BigDecimal tb=new BigDecimal("0.00");
				String tb="";
				BigDecimal tbSumMoney=new BigDecimal("0.00");
				BigDecimal hbSumMoney=new BigDecimal("0.00");
				if(tbList!=null && tbList.size()>0){
					for (ResultDto tbresultDto : tbList) {
						if(resultDto.getCode().equals(tbresultDto.getCode())){
							if(tbresultDto.getBigSumMoney()!=null){
								tbSumMoney=tbresultDto.getBigSumMoney();
								if(tbSumMoney.compareTo(BigDecimal.ZERO)==0 && resultDto.getBigSumMoney().compareTo(BigDecimal.ZERO)!=0){
									//tb=new BigDecimal("100.00");
									tb="-";
								}else if(tbSumMoney.compareTo(BigDecimal.ZERO)==0 && resultDto.getBigSumMoney().compareTo(BigDecimal.ZERO)==0){
									tb="-";
								}else{
									tb=((resultDto.getBigSumMoney().subtract(tbresultDto.getBigSumMoney())).multiply(new BigDecimal("100")).divide(tbSumMoney.abs(),2, RoundingMode.HALF_UP)).toString();
								}
							}
							else{
								tb="-";
							}

						}
						//flag=2;
					}

				}
				/*if(flag==1){
					tb=new BigDecimal("100.00");
				}*/
				//int flag2=1;
				//BigDecimal hb=new BigDecimal("0.00");
				String hb="";
				if(hbList!=null && hbList.size()>0){
					for (ResultDto hbresultDto : hbList) {
						if(resultDto.getCode().equals(hbresultDto.getCode())){
							if(hbresultDto.getBigSumMoney()!=null){
								hbSumMoney=hbresultDto.getBigSumMoney();
								if(hbSumMoney.compareTo(BigDecimal.ZERO)==0 && resultDto.getBigSumMoney().compareTo(BigDecimal.ZERO)!=0){
									//hb=new BigDecimal("100.00");
									hb="-";
								}else if(hbSumMoney.compareTo(BigDecimal.ZERO)==0 && resultDto.getBigSumMoney().compareTo(BigDecimal.ZERO)==0){
									hb="-";
								}else{
									hb=((resultDto.getBigSumMoney().subtract(hbresultDto.getBigSumMoney())).multiply(new BigDecimal("100")).divide(hbSumMoney.abs(),2, RoundingMode.HALF_UP)).toString();
								}
							}else{
								hb="-";
							}

							//hb=((resultDto.getSumMoney().subtract(hbresultDto.getSumMoney())).divide(hbresultDto.getSumMoney(),4, RoundingMode.HALF_UP)).multiply(new BigDecimal("100"));
						}
						//flag=2;
					}

				}
				BigDecimal money=resultDto.getBigSumMoney();
				if(map.get("isZh")==null || map.get("isZh").toString().equals("0")){
					money=money.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);   //金额单位转换成万元
					tbSumMoney=tbSumMoney.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);   //金额单位转换成万元
					hbSumMoney=hbSumMoney.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);   //金额单位转换成万元
					resultDto.setSumMoney(money.toString()+"万");
				}else{
					resultDto.setSumMoney(money.toString());
				}
				/*if(flag2==1){
					hb=new BigDecimal("100.00");
				}*/
				resultDto.setTbSumMoney(tbSumMoney);
				resultDto.setHbSumMoney(hbSumMoney);
				resultDto.setBigSumMoney(money);
				
				resultDto.setOnRise(tb);
				resultDto.setLinkRise(hb);
				rList.add(resultDto);
			}
		}else{
			return null;
		}

		return rList;
		//return reportResultMapper.selectValueSumDetail(map);
	}
	@Override
	public List<ResultDto> selectValueSumDetailAllts(Map<String,Object> map){
		/*String startTime=map.get("startTime").toString();
		String endTime=map.get("endTime").toString();*/
		String startTime="";
		String endTime="";
		String sendTimes="";
		if(map.get("startTime")!=null && map.get("startTime").toString()!=""){
			startTime=map.get("startTime").toString();
		}
		if(map.get("endTime")!=null && map.get("endTime").toString()!=""){
			endTime=map.get("endTime").toString();
		}
		if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
			sendTimes=map.get("endTimes").toString();
		}
		int monthDiff=0;
		try {
			if(map.get("reportType")!=null && map.get("reportType").toString()!="" && map.get("reportType").toString().equals("1")){
				if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
					String st=map.get("startTimes").toString();
					String et=map.get("endTimes").toString();
					monthDiff = Dates.getDateTimes(st,et)+1;
				}else{
					monthDiff = Dates.getDateTimes(map.get("startTime").toString(),map.get("endTime").toString())+1;
				}
			}else{
				if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
					monthDiff=1;
				}else{
					monthDiff = Dates.getDateTimes(map.get("startTime").toString(),map.get("endTime").toString())+1;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<ResultDto> rList=new ArrayList<ResultDto>();
		List<ResultDto> sjList=reportResultMapper.selectValueSumDetail(map);
		String startDt="";
		String endDt="";
		String endTimes="";
		if(map.get("startTime")!=null && map.get("startTime").toString()!=""){
			startDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(startTime,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4), "yyyy-MM-dd");
		}
		if(map.get("endTime")!=null && map.get("endTime").toString()!=""){
			endDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4), "yyyy-MM-dd");
		}
		if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
			endTimes=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(sendTimes,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4), "yyyy-MM-dd");
		}
		map.put("startTime", startDt);
		map.put("endTime", endDt);
		map.put("endTimes", endTimes);
		List<ResultDto> tbList=reportResultMapper.selectValueSumDetail(map);
		//int monthDiff = (DateUtil.stringToDate(endTime).getYear() - DateUtil.stringToDate(startTime).getYear()) * 12 + (DateUtil.stringToDate(endTime).getMonth()- DateUtil.stringToDate(startTime).getMonth()) ;
		String hbsDate="";
		String hbeDt="";
		String hbeDts="";
		if(map.get("startTime")!=null && map.get("startTime").toString()!=""){
			hbsDate=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(startTime,"yyyy-MM-dd"),"yyyy-MM-dd",monthDiff*(-1),3), "yyyy-MM-dd");
		}
		if(map.get("endTime")!=null && map.get("endTime").toString()!=""){
			hbeDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime,"yyyy-MM-dd"),"yyyy-MM-dd",monthDiff*(-1),3), "yyyy-MM-dd");
		}
		if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
			hbeDts=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(sendTimes,"yyyy-MM-dd"),"yyyy-MM-dd",monthDiff*(-1),3), "yyyy-MM-dd");
		}
		map.put("startTime", hbsDate);
		map.put("endTime", hbeDt);
		map.put("endTimes", hbeDts);
		List<ResultDto> hbList=reportResultMapper.selectValueSumDetail(map);
		if(sjList!=null && sjList.size()>0){
			for (ResultDto resultDto : sjList) {
				//int flag=1;
				//BigDecimal tb=new BigDecimal("0.00");
				String tb="";
				BigDecimal tbSumMoney=new BigDecimal("0.00");
				BigDecimal hbSumMoney=new BigDecimal("0.00");
				if(tbList!=null && tbList.size()>0){
					for (ResultDto tbresultDto : tbList) {
						if(resultDto.getCode().equals(tbresultDto.getCode())){
							if(tbresultDto.getBigSumMoney()!=null){
								tbSumMoney=tbresultDto.getBigSumMoney();
								if(tbSumMoney.compareTo(BigDecimal.ZERO)==0 && resultDto.getBigSumMoney().compareTo(BigDecimal.ZERO)!=0){
									//tb=new BigDecimal("100.00");
									tb="-";
								}else if(tbSumMoney.compareTo(BigDecimal.ZERO)==0 && resultDto.getBigSumMoney().compareTo(BigDecimal.ZERO)==0){
									tb="-";
								}else{
									tb=((resultDto.getBigSumMoney().subtract(tbresultDto.getBigSumMoney())).multiply(new BigDecimal("100")).divide(tbSumMoney.abs(),2, RoundingMode.HALF_UP)).toString();
								}
							}
							else{
								tb="-";
							}

						}
						//flag=2;
					}

				}
				/*if(flag==1){
					tb=new BigDecimal("100.00");
				}*/
				//int flag2=1;
				//BigDecimal hb=new BigDecimal("0.00");
				String hb="";
				if(hbList!=null && hbList.size()>0){
					for (ResultDto hbresultDto : hbList) {
						if(resultDto.getCode().equals(hbresultDto.getCode())){
							if(hbresultDto.getBigSumMoney()!=null){
								hbSumMoney=hbresultDto.getBigSumMoney();
								if(hbSumMoney.compareTo(BigDecimal.ZERO)==0 && resultDto.getBigSumMoney().compareTo(BigDecimal.ZERO)!=0){
									//hb=new BigDecimal("100.00");
									hb="-";
								}else if(hbSumMoney.compareTo(BigDecimal.ZERO)==0 && resultDto.getBigSumMoney().compareTo(BigDecimal.ZERO)==0){
									hb="-";
								}else{
									hb=((resultDto.getBigSumMoney().subtract(hbresultDto.getBigSumMoney())).multiply(new BigDecimal("100")).divide(hbSumMoney.abs(),2, RoundingMode.HALF_UP)).toString();
								}
							}else{
								hb="-";
							}

							//hb=((resultDto.getSumMoney().subtract(hbresultDto.getSumMoney())).divide(hbresultDto.getSumMoney(),4, RoundingMode.HALF_UP)).multiply(new BigDecimal("100"));
						}
						//flag=2;
					}

				}
				BigDecimal money=resultDto.getBigSumMoney();
				money=money.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);   //金额单位转换成万元
				/*if(flag2==1){
					hb=new BigDecimal("100.00");
				}*/
				tbSumMoney=tbSumMoney.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);   //金额单位转换成万元
				hbSumMoney=hbSumMoney.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);   //金额单位转换成万元
				resultDto.setTbSumMoney(tbSumMoney);
				resultDto.setHbSumMoney(hbSumMoney);
				resultDto.setBigSumMoney(money);
				resultDto.setSumMoney(money.toString()+"天");
				resultDto.setOnRise(tb);
				resultDto.setLinkRise(hb);
				rList.add(resultDto);
			}
		}else{
			return null;
		}

		return rList;
		//return reportResultMapper.selectValueSumDetail(map);
	}
	/**
	 * 预测详情
	 */
	public List<ResultDto> selectExpectValueSumDetailAll(Map<String,Object> map){
		/*String startTime=map.get("startTime").toString();
		String endTime=map.get("endTime").toString();*/
		List<ResultDto> sjList=reportResultMapper.selectExpectValueSumDetail(map);
		List<ResultDto> sjList2=new ArrayList<ResultDto>();
		for(ResultDto r : sjList){
			BigDecimal money=r.getBigSumMoney();
			if(money==null){
				money=new BigDecimal("0");
			}
			money=money.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);
			r.setBigSumMoney(money);
			r.setSumMoney(money.toString());
			sjList2.add(r);
		}
		//money=money.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);
		return sjList2;
		//return reportResultMapper.selectValueSumDetail(map);
	}

	@Override
	public List<ResultDto> getQueReport(String startTime, String endTime, String orgId) throws Exception{
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM" );
		Date startM1 = sdf.parse(startTime);
		Date endM1 = sdf.parse(endTime);
		Date startM2 = sdf.parse(Integer.toString(Integer.parseInt(startTime.substring(0, 4)) - 1) + startTime.substring(4, 7)) ;
		Date endM2 = sdf.parse(Integer.toString(Integer.parseInt(endTime.substring(0, 4)) - 1) + endTime.substring(4, 7)) ;
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("startM1", startM1);
		map.put("endM1", endM1);
		map.put("startM2", startM2);
		map.put("endM2", endM2);
		map.put("orgId", orgId);
		List<ResultDto> allDatas = reportResultMapper.getThreeReportDetailMsg(map);
		List<ResultDto> retList = new ArrayList<>() ;
		String lastCodeNo = "";
		ResultDto rtBean = new ResultDto() ;
		BigDecimal amtSum = null ;
		BigDecimal tbSum = null;
		int len = allDatas.size() ;
		int i = 0 ;
		for(ResultDto bean : allDatas){
			String codeNo = bean.getCode() ;
			if(StringUtils.isEmpty(lastCodeNo)){
				lastCodeNo = codeNo ;
				rtBean.setCode(bean.getCode());
				rtBean.setCodeName(bean.getCodeName());
				rtBean.setReportType(bean.getReportType());
			}
			else if(!lastCodeNo.equals(codeNo)){
				rtBean.setBigSumMoney(amtSum);
				rtBean.setTbSumMoney(tbSum);
				retList.add(rtBean) ;
				lastCodeNo = codeNo ;
				amtSum = null ;
				tbSum = null ;
				rtBean = new ResultDto() ;
				rtBean.setCode(bean.getCode());
				rtBean.setCodeName(bean.getCodeName());
				rtBean.setReportType(bean.getReportType());
			}
			if(null != bean.getYearMoth() && compareTime(startM1, endM1, sdf, bean)){
				if(bean.getBigSumMoney() != null && amtSum != null){
					if(!REPORT_TYPE_ZCFZ.equals(bean.getReportType())){
						amtSum = amtSum.add(bean.getBigSumMoney()) ;
					}
					else if(endTime.equals(bean.getYearMoth())){
						amtSum = bean.getBigSumMoney();
					}
				}
				else{
					amtSum = bean.getBigSumMoney() ;
				}
			}
			if(null != bean.getYearMoth() && compareTime(startM2, endM2, sdf, bean)){
				if(bean.getBigSumMoney() != null && tbSum != null){
					if(!REPORT_TYPE_ZCFZ.equals(bean.getReportType())){
						tbSum = tbSum.add(bean.getBigSumMoney()) ;
					}
					else if(endTime.equals(bean.getYearMoth())){
						tbSum = bean.getBigSumMoney();
					}
				}
				else{
					tbSum = bean.getBigSumMoney() ;
				}
			}

			if(i == len - 1){
				rtBean.setBigSumMoney(amtSum);
				rtBean.setTbSumMoney(tbSum);
				retList.add(rtBean) ;
			}
		}
		return retList;
	}
	@Override
	public Map<String, BigDecimal> getModeParams2(String startTime, String endTime, String orgId, String reportType,String modeArea) throws Exception {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("reportType", reportType);
		if(reportType.equals("1")){
			map.put("endTimes", endTime);
		}else{
			map.put("startTime", startTime);
			map.put("endTime", endTime);
		}
		map.put("isZh", "1");
		HashMap<String, BigDecimal> rmap=new HashMap<String, BigDecimal>();
		List<ResultDto> allDatas=this.selectValueSumDetailAll(map);
		if(allDatas!=null && allDatas.size()>0){
			for (ResultDto resultDto : allDatas) {
				String codeNo=resultDto.getCode();
				String code = codeNo.replace("-", "_");
				rmap.put(code, resultDto.getBigSumMoney());
				rmap.put(code+"1", resultDto.getTbSumMoney());
				rmap.put(code+"2", resultDto.getHbSumMoney());
			}
		}
//		map.put("endTimes", null);
//		SysDictionaryModel dic=new SysDictionaryModel();
//		dic.setEnName("tsbmdy");
//		dic.setCnName("");
//		SysDictionaryModel ids=sysDictionaryMapper.selectByCnNameOrEnName(dic);
//		String startTimes="";
//		String endTimes="";
//		if(modeArea.equals("G100001")){
//			if(reportType.equals("1")){
//				startTimes=endTime;
//				endTimes=endTime;
//			}else{
//				startTimes=startTime;
//				endTimes=endTime;
//			}
//			//YBLFX-L应收账款周转天数
//			ResultDto bl4=bilvService.getBilvResult(startTimes, endTimes, orgId, "bl5201");             //YBLFX-L应收账款周转天数
//			if(bl4!=null){
//				if(bl4.getBigSumMoney()!=null){
//					rmap.put("YBLFX_L", bl4.getBigSumMoney());
//				}else{
//					rmap.put("YBLFX_L",new BigDecimal("0"));
//				}
//				if(bl4.getTbSumMoney()!=null){
//					rmap.put("YBLFX_L1", bl4.getTbSumMoney());
//				}else{
//					rmap.put("YBLFX_L1",new BigDecimal("0"));
//				}
//				if(bl4.getHbSumMoney()!=null){
//					rmap.put("YBLFX_L2", bl4.getHbSumMoney());
//				}else{
//					rmap.put("YBLFX_L2",new BigDecimal("0"));
//				}
//			}else{
//				rmap.put("YBLFX_Y",new BigDecimal("0"));
//				rmap.put("YBLFX_L1",new BigDecimal("0"));
//				rmap.put("YBLFX_L2",new BigDecimal("0"));
//			}
//			ResultDto bl2=bilvService.getBilvResult(startTime, endTime, orgId, "bl5203");             //YBLFX_O 应付账款周转天数
//			if(bl2!=null){
//				if(bl2.getBigSumMoney()!=null){
//					rmap.put("YBLFX_O", bl2.getBigSumMoney());
//				}else{
//					rmap.put("YBLFX_O",new BigDecimal("0"));
//				}
//				if(bl2.getTbSumMoney()!=null){
//					rmap.put("YBLFX_O1", bl2.getTbSumMoney());
//				}else{
//					rmap.put("YBLFX_O1",new BigDecimal("0"));
//				}
//				if(bl2.getHbSumMoney()!=null){
//					rmap.put("YBLFX_O2", bl2.getHbSumMoney());
//				}else{
//					rmap.put("YBLFX_O2",new BigDecimal("0"));
//				}
//			}else{
//				rmap.put("YBLFX_O",new BigDecimal("0"));
//				rmap.put("YBLFX_O1",new BigDecimal("0"));
//				rmap.put("YBLFX_O2",new BigDecimal("0"));
//			}
//			this.getTeshu2(startTimes, endTimes, orgId, rmap);
//		}
//		if(modeArea.equals("G10045")){
//			if(reportType.equals("1")){
//				startTimes=endTime;
//				endTimes=endTime;
//			}else{
//				startTimes=startTime;
//				endTimes=endTime;
//			}
//			this.getTeshu2(startTimes, endTimes, orgId, rmap);
//			return rmap;
//		}
//		if(modeArea.equals("G20030")){
//			if(reportType.equals("1")){
//				startTimes=endTime;
//				endTimes=endTime;
//			}else{
//				startTimes=startTime;
//				endTimes=endTime;
//			}
//			this.getTeshu3(startTimes, endTimes, orgId, rmap);
//			return rmap;
//		}
//		if(ids!=null){
//			String remark=ids.getRemark();
//			String s[]=remark.split(",");
//			if(reportType.equals("1")){
//				startTimes=endTime;
//				endTimes=endTime;
//			}else{
//				startTimes=startTime;
//				endTimes=endTime;
//			}
//			if(Arrays.asList(s).contains(modeArea)){
//				this.getTeshu(startTimes, endTimes, orgId, rmap);
//			};
//
//		}
		return rmap;
	}
	@Override
	public Map<String,BigDecimal> getTeshu(String startTime, String endTime, String orgId,Map<String,BigDecimal> rmap) throws ParseException{
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("startTime", Dates.getDecDate(startTime));
		map.put("endTime", Dates.getDecDate(endTime));
		map.put("projectName", "合计");
		map.put("orgId", orgId);
		
		List<TAgeAnalysis> rList=tAgeAnalysisService.selectSumAllByType(map);
		rmap.put("CHKL_A", new BigDecimal("0"));
		rmap.put("CHKL_B", new BigDecimal("0"));
		rmap.put("CHKL_C", new BigDecimal("0"));
		rmap.put("CHKL_D", new BigDecimal("0"));
		rmap.put("CHKL_E", new BigDecimal("0"));
		rmap.put("CHKL_F", new BigDecimal("0"));
		rmap.put("RM_A", new BigDecimal("0"));
		rmap.put("RM_B", new BigDecimal("0"));
		rmap.put("RM_C", new BigDecimal("0"));
		rmap.put("RM_D", new BigDecimal("0"));
		rmap.put("RM_E",new BigDecimal("0"));
		rmap.put("RM_F",new BigDecimal("0"));
		rmap.put("YSZKZL_A", new BigDecimal("0"));
		rmap.put("YSZKZL_B", new BigDecimal("0"));
		rmap.put("YSZKZL_C", new BigDecimal("0"));
		rmap.put("YSZKZL_D", new BigDecimal("0"));
		rmap.put("YSZKZL_E",new BigDecimal("0"));
		rmap.put("YSZKZL_F",new BigDecimal("0"));
		rmap.put("YFZKZL_A", new BigDecimal("0"));
		rmap.put("YFZKZL_B", new BigDecimal("0"));
		rmap.put("YFZKZL_C", new BigDecimal("0"));
		rmap.put("YFZKZL_D", new BigDecimal("0"));
		rmap.put("YFZKZL_E",new BigDecimal("0"));
		rmap.put("YFZKZL_F",new BigDecimal("0"));
		rmap.put("QTYFZL_A", new BigDecimal("0"));
		rmap.put("QTYFZL_B", new BigDecimal("0"));
		rmap.put("QTYFZL_C", new BigDecimal("0"));
		rmap.put("QTYFZL_D", new BigDecimal("0"));
		rmap.put("QTYFZL_E",new BigDecimal("0"));
		rmap.put("QTYFZL_F",new BigDecimal("0"));
		rmap.put("QTYSZL_A", new BigDecimal("0"));
		rmap.put("QTYSZL_B", new BigDecimal("0"));
		rmap.put("QTYSZL_C", new BigDecimal("0"));
		rmap.put("QTYSZL_D", new BigDecimal("0"));
		rmap.put("QTYSZL_E",new BigDecimal("0"));
		rmap.put("QTYSZL_F",new BigDecimal("0"));
		rmap.put("YFKXZL_A", new BigDecimal("0"));
		rmap.put("YFKXZL_B", new BigDecimal("0"));
		rmap.put("YFKXZL_C", new BigDecimal("0"));
		rmap.put("YFKXZL_D", new BigDecimal("0"));
		rmap.put("YFKXZL_E",new BigDecimal("0"));
		rmap.put("YFKXZL_F",new BigDecimal("0"));
		rmap.put("YSKXZL_A", new BigDecimal("0"));
		rmap.put("YSKXZL_B", new BigDecimal("0"));
		rmap.put("YSKXZL_C", new BigDecimal("0"));
		rmap.put("YSKXZL_D", new BigDecimal("0"));
		rmap.put("YSKXZL_E",new BigDecimal("0"));
		rmap.put("YSKXZL_F",new BigDecimal("0"));
		if(rList!=null && rList.size()>0){
			for (TAgeAnalysis tAgeAnalysis : rList) {
				if(tAgeAnalysis.getType().equals(9) || tAgeAnalysis.getType().equals("9")){
					BigDecimal to90=new BigDecimal("0");
					if(tAgeAnalysis.getDue1To30Days()!=null){
						to90.add(tAgeAnalysis.getDue1To30Days());
					}
					if(tAgeAnalysis.getDue31To90Days()!=null){
						to90.add(tAgeAnalysis.getDue31To90Days());
					}
					rmap.put("CHKL_A", to90);
					rmap.put("CHKL_B", tAgeAnalysis.getNotDue91To180Days()!=null ? tAgeAnalysis.getNotDue91To180Days() : new BigDecimal("0"));
					rmap.put("CHKL_C", tAgeAnalysis.getDue181To360Days()!=null ? tAgeAnalysis.getDue181To360Days() : new BigDecimal("0"));
					rmap.put("CHKL_D", tAgeAnalysis.getDue361To720Days()!=null ? tAgeAnalysis.getDue361To720Days() : new BigDecimal("0"));
					rmap.put("CHKL_E", tAgeAnalysis.getDue721To1080Days()!=null ? tAgeAnalysis.getDue721To1080Days() : new BigDecimal("0"));
					rmap.put("CHKL_F", tAgeAnalysis.getDue1081To1440Days()!=null ? tAgeAnalysis.getDue1081To1440Days() : new BigDecimal("0"));
				}else if(tAgeAnalysis.getType().equals(10) || tAgeAnalysis.getType().equals("10")){
					BigDecimal to90=new BigDecimal("0");
					if(tAgeAnalysis.getDue1To30Days()!=null){
						to90.add(tAgeAnalysis.getDue1To30Days());
					}
					if(tAgeAnalysis.getDue31To90Days()!=null){
						to90.add(tAgeAnalysis.getDue31To90Days());
					}
					rmap.put("RM_A", to90);
					rmap.put("RM_B", tAgeAnalysis.getNotDue91To180Days()!=null ? tAgeAnalysis.getNotDue91To180Days() : new BigDecimal("0"));
					rmap.put("RM_C", tAgeAnalysis.getDue181To360Days()!=null ? tAgeAnalysis.getDue181To360Days() : new BigDecimal("0"));
					rmap.put("RM_D", tAgeAnalysis.getDue361To720Days()!=null ? tAgeAnalysis.getDue361To720Days() : new BigDecimal("0"));
					rmap.put("RM_E", tAgeAnalysis.getDue721To1080Days()!=null ? tAgeAnalysis.getDue721To1080Days() : new BigDecimal("0"));
					rmap.put("RM_F", tAgeAnalysis.getDue1081To1440Days()!=null ? tAgeAnalysis.getDue1081To1440Days() : new BigDecimal("0"));
				}else if(tAgeAnalysis.getType().equals(6) || tAgeAnalysis.getType().equals("6")){
					BigDecimal to90=new BigDecimal("0");
					if(tAgeAnalysis.getDue1To30Days()!=null){
						to90.add(tAgeAnalysis.getDue1To30Days());
					}
					if(tAgeAnalysis.getDue31To90Days()!=null){
						to90.add(tAgeAnalysis.getDue31To90Days());
					}
					rmap.put("YSZKZL_A", to90);
					rmap.put("YSZKZL_B", tAgeAnalysis.getDue91To180Days()!=null ? tAgeAnalysis.getDue91To180Days() : new BigDecimal("0"));
					rmap.put("YSZKZL_C", tAgeAnalysis.getDue181To360Days()!=null ? tAgeAnalysis.getDue181To360Days() : new BigDecimal("0"));
					rmap.put("YSZKZL_D", tAgeAnalysis.getDue361To720Days()!=null ? tAgeAnalysis.getDue361To720Days() : new BigDecimal("0"));
					rmap.put("YSZKZL_E", tAgeAnalysis.getDue721To1080Days()!=null ? tAgeAnalysis.getDue721To1080Days() : new BigDecimal("0"));
					rmap.put("YSZKZL_F", tAgeAnalysis.getDue1081To1440Days()!=null ? tAgeAnalysis.getDue1081To1440Days() : new BigDecimal("0"));
				}else if(tAgeAnalysis.getType().equals(5) || tAgeAnalysis.getType().equals("5")){
					BigDecimal to90=new BigDecimal("0");
					if(tAgeAnalysis.getDue1To30Days()!=null){
						to90.add(tAgeAnalysis.getDue1To30Days());
					}
					if(tAgeAnalysis.getDue31To90Days()!=null){
						to90.add(tAgeAnalysis.getDue31To90Days());
					}
					rmap.put("YFZKZL_A", to90);
					rmap.put("YFZKZL_B", tAgeAnalysis.getDue91To180Days()!=null ? tAgeAnalysis.getDue91To180Days() : new BigDecimal("0"));
				 	rmap.put("YFZKZL_C", tAgeAnalysis.getDue181To360Days()!=null ? tAgeAnalysis.getDue181To360Days() : new BigDecimal("0"));
				 	rmap.put("YFZKZL_D", tAgeAnalysis.getDue361To720Days()!=null ? tAgeAnalysis.getDue361To720Days() : new BigDecimal("0"));
				 	rmap.put("YFZKZL_E", tAgeAnalysis.getDue721To1080Days()!=null ? tAgeAnalysis.getDue721To1080Days() : new BigDecimal("0"));
				 	rmap.put("YFZKZL_F", tAgeAnalysis.getDue1081To1440Days()!=null ? tAgeAnalysis.getDue1081To1440Days() : new BigDecimal("0"));
				}else if(tAgeAnalysis.getType().equals(3) || tAgeAnalysis.getType().equals("3")){
					BigDecimal to90=new BigDecimal("0");
					if(tAgeAnalysis.getDue1To30Days()!=null){
						to90.add(tAgeAnalysis.getDue1To30Days());
					}
					if(tAgeAnalysis.getDue31To90Days()!=null){
						to90.add(tAgeAnalysis.getDue31To90Days());
					}
					rmap.put("QTYFZL_A", to90);
					rmap.put("QTYFZL_B", tAgeAnalysis.getDue91To180Days()!=null ? tAgeAnalysis.getDue91To180Days() : new BigDecimal("0"));
					rmap.put("QTYFZL_C", tAgeAnalysis.getDue181To360Days()!=null ? tAgeAnalysis.getDue181To360Days() : new BigDecimal("0"));
					rmap.put("QTYFZL_D", tAgeAnalysis.getDue361To720Days()!=null ? tAgeAnalysis.getDue361To720Days() : new BigDecimal("0"));
					rmap.put("QTYFZL_E", tAgeAnalysis.getDue721To1080Days()!=null ? tAgeAnalysis.getDue721To1080Days() : new BigDecimal("0"));
					rmap.put("QTYFZL_F", tAgeAnalysis.getDue1081To1440Days()!=null ? tAgeAnalysis.getDue1081To1440Days() : new BigDecimal("0"));
				}else if(tAgeAnalysis.getType().equals(4) || tAgeAnalysis.getType().equals("4")){
					BigDecimal to90=new BigDecimal("0");
					if(tAgeAnalysis.getDue1To30Days()!=null){
						to90.add(tAgeAnalysis.getDue1To30Days());
					}
					if(tAgeAnalysis.getDue31To90Days()!=null){
						to90.add(tAgeAnalysis.getDue31To90Days());
					}
					rmap.put("QTYSZL_A", to90);
					rmap.put("QTYSZL_B", tAgeAnalysis.getDue91To180Days()!=null ? tAgeAnalysis.getDue91To180Days() : new BigDecimal("0"));
				 	rmap.put("QTYSZL_C", tAgeAnalysis.getDue181To360Days()!=null ? tAgeAnalysis.getDue181To360Days() : new BigDecimal("0"));
				 	rmap.put("QTYSZL_D", tAgeAnalysis.getDue361To720Days()!=null ? tAgeAnalysis.getDue361To720Days() : new BigDecimal("0"));
				 	rmap.put("QTYSZL_E", tAgeAnalysis.getDue721To1080Days()!=null ? tAgeAnalysis.getDue721To1080Days() : new BigDecimal("0"));
				 	rmap.put("QTYSZL_F", tAgeAnalysis.getDue1081To1440Days()!=null ? tAgeAnalysis.getDue1081To1440Days() : new BigDecimal("0"));
				}else if(tAgeAnalysis.getType().equals(7) ||tAgeAnalysis.getType().equals("7")){
					BigDecimal to90=new BigDecimal("0");
					if(tAgeAnalysis.getDue1To30Days()!=null){
						to90.add(tAgeAnalysis.getDue1To30Days());
					}
					if(tAgeAnalysis.getDue31To90Days()!=null){
						to90.add(tAgeAnalysis.getDue31To90Days());
					}
					rmap.put("YFKXZL_A", to90);
					rmap.put("YFKXZL_B", tAgeAnalysis.getDue91To180Days()!=null ? tAgeAnalysis.getDue91To180Days() : new BigDecimal("0"));
					rmap.put("YFKXZL_C", tAgeAnalysis.getDue181To360Days()!=null ? tAgeAnalysis.getDue181To360Days() : new BigDecimal("0"));
					rmap.put("YFKXZL_D", tAgeAnalysis.getDue361To720Days()!=null ? tAgeAnalysis.getDue361To720Days() : new BigDecimal("0"));
					rmap.put("YFKXZL_E", tAgeAnalysis.getDue721To1080Days()!=null ? tAgeAnalysis.getDue721To1080Days() : new BigDecimal("0"));
					rmap.put("YFKXZL_F", tAgeAnalysis.getDue1081To1440Days()!=null ? tAgeAnalysis.getDue1081To1440Days() : new BigDecimal("0"));
				}else if( tAgeAnalysis.getType().equals(8) || tAgeAnalysis.getType().equals("8")){
					BigDecimal to90=new BigDecimal("0");
					if(tAgeAnalysis.getDue1To30Days()!=null){
						to90.add(tAgeAnalysis.getDue1To30Days());
					}
					if(tAgeAnalysis.getDue31To90Days()!=null){
						to90.add(tAgeAnalysis.getDue31To90Days());
					}
					rmap.put("YSKXZL_A", to90);
					rmap.put("YSKXZL_B", tAgeAnalysis.getDue91To180Days()!=null ? tAgeAnalysis.getDue91To180Days() : new BigDecimal("0"));
					rmap.put("YSKXZL_C", tAgeAnalysis.getDue181To360Days()!=null ? tAgeAnalysis.getDue181To360Days() : new BigDecimal("0"));
					rmap.put("YSKXZL_D", tAgeAnalysis.getDue361To720Days()!=null ? tAgeAnalysis.getDue361To720Days() : new BigDecimal("0"));
					rmap.put("YSKXZL_E", tAgeAnalysis.getDue721To1080Days()!=null ? tAgeAnalysis.getDue721To1080Days() : new BigDecimal("0"));
					rmap.put("YSKXZL_F", tAgeAnalysis.getDue1081To1440Days()!=null ? tAgeAnalysis.getDue1081To1440Days() : new BigDecimal("0"));
				}
				
			}
		}
		
		ResultDto bl=bilvService.getBilvResult(startTime, endTime, orgId, "bl5202");       //存货周转天数
		if(bl!=null){
			if(bl.getBigSumMoney()!=null){
				rmap.put("YBLFX_N", bl.getBigSumMoney());
			}else{
				rmap.put("YBLFX_N",new BigDecimal("0"));
			}
			if(bl.getTbSumMoney()!=null){
				rmap.put("YBLFX_N1", bl.getTbSumMoney());
			}else{
				rmap.put("YBLFX_N1",new BigDecimal("0"));
			}
			if(bl.getHbSumMoney()!=null){
				rmap.put("YBLFX_N2", bl.getHbSumMoney());
			}else{
				rmap.put("YBLFX_N2",new BigDecimal("0"));
			}
		}else{
			rmap.put("YBLFX_N",new BigDecimal("0"));
			rmap.put("YBLFX_N1",new BigDecimal("0"));
			rmap.put("YBLFX_N2",new BigDecimal("0"));
		}
		ResultDto bl2=bilvService.getBilvResult(startTime, endTime, orgId, "bl5203");             //YBLFX_O 应付账款周转天数
		if(bl2!=null){
			if(bl2.getBigSumMoney()!=null){
				rmap.put("YBLFX_O", bl2.getBigSumMoney());
			}else{
				rmap.put("YBLFX_O",new BigDecimal("0"));
			}
			if(bl2.getTbSumMoney()!=null){
				rmap.put("YBLFX_O1", bl2.getTbSumMoney());
			}else{
				rmap.put("YBLFX_O1",new BigDecimal("0"));
			}
			if(bl2.getHbSumMoney()!=null){
				rmap.put("YBLFX_O2", bl2.getHbSumMoney());
			}else{
				rmap.put("YBLFX_O2",new BigDecimal("0"));
			}
		}else{
			rmap.put("YBLFX_O",new BigDecimal("0"));
			rmap.put("YBLFX_O1",new BigDecimal("0"));
			rmap.put("YBLFX_O2",new BigDecimal("0"));
		}
		
		ResultDto bl3=bilvService.getBilvResult(startTime, endTime, orgId, "bl5303");             //YBLFX-Y 权益净利润率
		if(bl3!=null){
			if(bl3.getBigSumMoney()!=null){
				rmap.put("YBLFX_Y", bl3.getBigSumMoney());
			}else{
				rmap.put("YBLFX_Y",new BigDecimal("0"));
			}
			if(bl3.getTbSumMoney()!=null){
				rmap.put("YBLFX_Y1", bl3.getTbSumMoney());
			}else{
				rmap.put("YBLFX_Y1",new BigDecimal("0"));
			}
			if(bl3.getHbSumMoney()!=null){
				rmap.put("YBLFX_Y2", bl3.getHbSumMoney());
			}else{
				rmap.put("YBLFX_Y2",new BigDecimal("0"));
			}
		}else{
			rmap.put("YBLFX_Y",new BigDecimal("0"));
			rmap.put("YBLFX_Y1",new BigDecimal("0"));
			rmap.put("YBLFX_Y2",new BigDecimal("0"));
		}
		//YBLFX-L应收账款周转天数
		ResultDto bl4=bilvService.getBilvResult(startTime, endTime, orgId, "bl5201");             //YBLFX-L应收账款周转天数
		if(bl4!=null){
			if(bl4.getBigSumMoney()!=null){
				rmap.put("YBLFX_L", bl4.getBigSumMoney());
			}else{
				rmap.put("YBLFX_L",new BigDecimal("0"));
			}
			if(bl4.getTbSumMoney()!=null){
				rmap.put("YBLFX_L1", bl4.getTbSumMoney());
			}else{
				rmap.put("YBLFX_L1",new BigDecimal("0"));
			}
			if(bl4.getHbSumMoney()!=null){
				rmap.put("YBLFX_L2", bl4.getHbSumMoney());
			}else{
				rmap.put("YBLFX_L2",new BigDecimal("0"));
			}
		}else{
			rmap.put("YBLFX_Y",new BigDecimal("0"));
			rmap.put("YBLFX_L1",new BigDecimal("0"));
			rmap.put("YBLFX_L2",new BigDecimal("0"));
		}
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("left", "km03");
		this.getOthen(map, "RGCB-A", rmap,"1");           //人工成本
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		this.getOthen(map, "SDCB-A", rmap,"1");            //水电
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		this.getOthen(map, "ZJCB-A", rmap,"1"); 			//折旧
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("left", "km24");
		map.put("type", "2");
		map.put("spmcLike", "合计");
		this.getOthen(map, "CLCB-A", rmap,"2");   
		map.put("type", "2");
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("left", "km23");
		this.getOthen(map, "CLCB-B", rmap,"2");   
		map.put("type", null);
		map.put("spmcLike", null);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("left", "km03");
		this.getOthen(map, "GLFY-RG", rmap,"1"); 			//管理费用-人工
		BigDecimal rg=rmap.get("GLFY_RG");
		BigDecimal rg1=rmap.get("GLFY_RG1");
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("left", "km03");
		this.getOthen(map, "GLFY-ZJ", rmap,"1"); 			//管理费用-折旧
		BigDecimal zj=rmap.get("GLFY_RG");
		BigDecimal zj1=rmap.get("GLFY_RG1");
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("left", "km03");
		this.getOthen(map, "GLFY-ZD", rmap,"1"); 			//管理费用-业务招待费
		BigDecimal yw=rmap.get("GLFY_RG");
		BigDecimal yw1=rmap.get("GLFY_RG1");
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("left", "km03");
		map.put("subjectCode", "6602");
		map.put("subNameLists", null);
		//this.getOthen(map, "GLFY-QT", rmap,"1"); 			//管理费用-其他
		BigDecimal qt=new BigDecimal("0");
		BigDecimal qt1=new BigDecimal("0");
		List<ResultDto> rLists=this.selectResultKm2(map);
		if(rLists!=null && rLists.size()>0){
			ResultDto r=rLists.get(0);
			BigDecimal zs=new BigDecimal("0");
			BigDecimal zs1=new BigDecimal("0");
			if(r.getBigSumMoney()!=null){
				zs=r.getBigSumMoney();
			}
			qt=zs.subtract(rg).subtract(zj).subtract(yw);
			if(r.getTbSumMoney()!=null){
				zs1=r.getTbSumMoney();
			}
			qt1=zs1.subtract(rg1).subtract(zj1).subtract(yw1);
		}
		map.put("subjectCode", null);
		rmap.put("GLFY_QT",qt);
		rmap.put("GLFY_QT1",qt1);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("left", "km03");
		this.getOthen(map, "XSFY-RG", rmap,"1"); 			//销售费用-人工
		BigDecimal xrg=rmap.get("XSFY_RG");
		BigDecimal xrg1=rmap.get("XSFY_RG1");
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("left", "km03");
		this.getOthen(map, "XSFY-CL", rmap,"1"); 			//销售费用-差旅费
		BigDecimal xcl=rmap.get("XSFY_CL");
		BigDecimal xcl1=rmap.get("XSFY_CL1");
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("left", "km03");
		this.getOthen(map, "XSFY-TG", rmap,"1"); 			//销售费用-市场推广费
		BigDecimal xtg=rmap.get("XSFY_TG");
		BigDecimal xtg1=rmap.get("XSFY_TG1");
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("left", "km03");
		this.getOthen(map, "XSFY-ZD", rmap,"1"); 			//销售费用-业务招待费
		BigDecimal xyw=rmap.get("XSFY_ZD");
		BigDecimal xyw1=rmap.get("XSFY_ZD1");
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("left", "km07");
		map.put("subjectCode", "6601");
		BigDecimal xqt=new BigDecimal("0");
		BigDecimal xqt1=new BigDecimal("0");
		List<ResultDto> xrLists=this.selectResultKm2(map);
		if(xrLists!=null && xrLists.size()>0){
			ResultDto r=xrLists.get(0);
			BigDecimal xzs=new BigDecimal("0");
			BigDecimal xzs1=new BigDecimal("0");
			if(r.getBigSumMoney()!=null){
				xzs=r.getBigSumMoney();
			}
			xqt=xzs.subtract(xrg).subtract(xcl).subtract(xtg).subtract(xyw);
			if(r.getTbSumMoney()!=null){
				xzs1=r.getTbSumMoney();
			}
			xqt1=xzs1.subtract(xrg1).subtract(xcl1).subtract(xtg1).subtract(xyw1);
		}
		rmap.put("XSFY_QT", xqt);
		rmap.put("XSFY_QT1", xqt1);
		map.put("subjectCode", null);
		//this.getOthen(map, "XSFY-QT", rmap,"1"); 			//销售费用其他
		
		
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("left", "km03");
		this.getOthen(map, "QTCB-A", rmap,"1"); 			//其他成本
		
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("left", "km23");
		map.put("type",1);
		map.put("spmc","合计");
		this.getOthen(map, "XSSL-A", rmap,"2"); 			//发出数量
		map.put("type",null);
		map.put("spmc",null);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("left", "km03");
		this.getOthen(map, "CLCB-C", rmap,"1"); 			//成本-直接材料
		Map<String, Object> smap=new HashMap<String, Object>();
		smap.put("subjectCodes", "1122");
		smap.put("noSubjectCode", "1122");
		smap.put("startTime", Dates.getDecDate(startTime));
		smap.put("endTime", Dates.getDecDate(endTime));
		smap.put("orgId", orgId);
		smap.put("left", "km07");
		List<ResultDto> rto5=reportResultMapper.selectResultKmTop5Sum(smap);
		if(rto5!=null && rto5.size()>0){
			ResultDto r5=rto5.get(0);
			if(r5!=null){
				if(r5.getBigSumMoney()!=null){
					rmap.put("YSQWD_C",r5.getBigSumMoney());
				}else{
					rmap.put("YSQWD_C",new BigDecimal("0"));
				}
			}else{
				rmap.put("YSQWD_C",new BigDecimal("0"));
			}
		}else{
			rmap.put("YSQWD_C",new BigDecimal("0"));               //应收账款 前五大客户期末余额合计
		}
		smap.put("subjectCodes", "2202");
		smap.put("noSubjectCode", "2202");
		smap.put("startTime", Dates.getDecDate(startTime));
		smap.put("endTime", Dates.getDecDate(endTime));
		smap.put("orgId", orgId);
		smap.put("left", "km08");
		List<ResultDto> rto6=reportResultMapper.selectResultKmTop5Sum(smap);
		if(rto6!=null){
			ResultDto r5=rto6.get(0);
			if(r5!=null){
				if(r5.getBigSumMoney()!=null){
					rmap.put("YFQWD_S",r5.getBigSumMoney());
				}else{
					rmap.put("YFQWD_S",new BigDecimal("0"));
				}
			}else{
				rmap.put("YFQWD_S",new BigDecimal("0"));
			}
			
		}else{
			rmap.put("YFQWD_S",new BigDecimal("0"));               //应付账款 前五大客户期末余额合计
		}
		smap.put("subjectCodes", null);
		smap.put("noSubjectCode", null);
		smap.put("startTime",null);
		smap.put("endTime", null);
		smap.put("orgId", null);
		smap.put("left", "km07");
		Map<String, Object> sbmap=new HashMap<String, Object>();
		sbmap.put("startTime", Dates.getDecDate(startTime));
		sbmap.put("endTime", Dates.getDecDate(endTime));
		sbmap.put("orgId", orgId);
		sbmap.put("left", "km26");
		sbmap.put("type", "1");
		ResultDto rto=reportResultMapper.selectResultSaleSum(smap);
		if(rto!=null){
			if(rto.getBigSumMoney()!=null){
				rmap.put("YCLKC_B",rto.getBigSumMoney());               //原材料库存期末余额
			}else{
				rmap.put("YCLKC_B",new BigDecimal("0"));
			}
		}else{
			rmap.put("YCLKC_B",new BigDecimal("0"));
		}
		sbmap.put("startTime", Dates.getDecDate(startTime));
		sbmap.put("endTime", Dates.getDecDate(endTime));
		sbmap.put("type", "2");
		ResultDto rto2=reportResultMapper.selectResultSaleSum(smap);
		if(rto2!=null){
			if(rto2.getBigSumMoney()!=null){
				rmap.put("YCLKC_B",rto2.getBigSumMoney());               //产成品库存期末余额
			}else{
				rmap.put("YCLKC_B",new BigDecimal("0"));
			}
		}else{
			rmap.put("YCLKC_B",new BigDecimal("0"));
		}
		sbmap.put("startTime", null);
		sbmap.put("endTime", null);
		sbmap.put("orgId", null);
		sbmap.put("left", null);
		/*rmap.put("RM_A",new BigDecimal("0"));
		rmap.put("RM_B",new BigDecimal("0"));
		rmap.put("RM_C",new BigDecimal("0"));
		rmap.put("RM_D",new BigDecimal("0"));
		rmap.put("RM_E",new BigDecimal("0"));
		rmap.put("RM_F",new BigDecimal("0"));
		rmap.put("FG_A",new BigDecimal("0"));
		rmap.put("FG_B",new BigDecimal("0"));
		rmap.put("FG_C",new BigDecimal("0"));
		rmap.put("FG_D",new BigDecimal("0"));
		rmap.put("FG_E",new BigDecimal("0"));
		rmap.put("FG_F",new BigDecimal("0"));*/
		return rmap;
	}
	@Override
	public Map<String,BigDecimal> getTeshu2(String startTime, String endTime, String orgId,Map<String,BigDecimal> rmap) throws ParseException{
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("startTime", Dates.getDecDate(startTime));
		map.put("endTime", Dates.getDecDate(endTime));
		map.put("projectName", "合计");
		map.put("orgId", orgId);
		//map.put("type", 9);
		List<TAgeAnalysis> rList=tAgeAnalysisService.selectSumAllByType(map);
		rmap.put("CHKL_A", new BigDecimal("0"));
		rmap.put("CHKL_B", new BigDecimal("0"));
		rmap.put("CHKL_C", new BigDecimal("0"));
		rmap.put("CHKL_D", new BigDecimal("0"));
		rmap.put("CHKL_E", new BigDecimal("0"));
		rmap.put("CHKL_F", new BigDecimal("0"));
		rmap.put("RM_A", new BigDecimal("0"));
		rmap.put("RM_B", new BigDecimal("0"));
		rmap.put("RM_C", new BigDecimal("0"));
		rmap.put("RM_D", new BigDecimal("0"));
		rmap.put("RM_E",new BigDecimal("0"));
		rmap.put("RM_F",new BigDecimal("0"));
		rmap.put("YSZKZL_A", new BigDecimal("0"));
		rmap.put("YSZKZL_B", new BigDecimal("0"));
		rmap.put("YSZKZL_C", new BigDecimal("0"));
		rmap.put("YSZKZL_D", new BigDecimal("0"));
		rmap.put("YSZKZL_E",new BigDecimal("0"));
		rmap.put("YSZKZL_F",new BigDecimal("0"));
		rmap.put("YFZKZL_A", new BigDecimal("0"));
		rmap.put("YFZKZL_B", new BigDecimal("0"));
		rmap.put("YFZKZL_C", new BigDecimal("0"));
		rmap.put("YFZKZL_D", new BigDecimal("0"));
		rmap.put("YFZKZL_E",new BigDecimal("0"));
		rmap.put("YFZKZL_F",new BigDecimal("0"));
		rmap.put("QTYFZL_A", new BigDecimal("0"));
		rmap.put("QTYFZL_B", new BigDecimal("0"));
		rmap.put("QTYFZL_C", new BigDecimal("0"));
		rmap.put("QTYFZL_D", new BigDecimal("0"));
		rmap.put("QTYFZL_E",new BigDecimal("0"));
		rmap.put("QTYFZL_F",new BigDecimal("0"));
		rmap.put("QTYSZL_A", new BigDecimal("0"));
		rmap.put("QTYSZL_B", new BigDecimal("0"));
		rmap.put("QTYSZL_C", new BigDecimal("0"));
		rmap.put("QTYSZL_D", new BigDecimal("0"));
		rmap.put("QTYSZL_E",new BigDecimal("0"));
		rmap.put("QTYSZL_F",new BigDecimal("0"));
		rmap.put("YFKXZL_A", new BigDecimal("0"));
		rmap.put("YFKXZL_B", new BigDecimal("0"));
		rmap.put("YFKXZL_C", new BigDecimal("0"));
		rmap.put("YFKXZL_D", new BigDecimal("0"));
		rmap.put("YFKXZL_E",new BigDecimal("0"));
		rmap.put("YFKXZL_F",new BigDecimal("0"));
		rmap.put("YSKXZL_A", new BigDecimal("0"));
		rmap.put("YSKXZL_B", new BigDecimal("0"));
		rmap.put("YSKXZL_C", new BigDecimal("0"));
		rmap.put("YSKXZL_D", new BigDecimal("0"));
		rmap.put("YSKXZL_E",new BigDecimal("0"));
		rmap.put("YSKXZL_F",new BigDecimal("0"));
		if(rList!=null && rList.size()>0){
			for (TAgeAnalysis tAgeAnalysis : rList) {
				if(tAgeAnalysis.getType().equals(9) || tAgeAnalysis.getType().equals("9")){
					BigDecimal to90=new BigDecimal("0");
					if(tAgeAnalysis.getDue1To30Days()!=null){
						to90.add(tAgeAnalysis.getDue1To30Days());
					}
					if(tAgeAnalysis.getDue31To90Days()!=null){
						to90.add(tAgeAnalysis.getDue31To90Days());
					}
					rmap.put("CHKL_A", to90);
					rmap.put("CHKL_B", tAgeAnalysis.getNotDue91To180Days()!=null ? tAgeAnalysis.getNotDue91To180Days() : new BigDecimal("0"));
					rmap.put("CHKL_C", tAgeAnalysis.getDue181To360Days()!=null ? tAgeAnalysis.getDue181To360Days() : new BigDecimal("0"));
					rmap.put("CHKL_D", tAgeAnalysis.getDue361To720Days()!=null ? tAgeAnalysis.getDue361To720Days() : new BigDecimal("0"));
					rmap.put("CHKL_E", tAgeAnalysis.getDue721To1080Days()!=null ? tAgeAnalysis.getDue721To1080Days() : new BigDecimal("0"));
					rmap.put("CHKL_F", tAgeAnalysis.getDue1081To1440Days()!=null ? tAgeAnalysis.getDue1081To1440Days() : new BigDecimal("0"));
				}else if(tAgeAnalysis.getType().equals(10) || tAgeAnalysis.getType().equals("10")){
					BigDecimal to90=new BigDecimal("0");
					if(tAgeAnalysis.getDue1To30Days()!=null){
						to90.add(tAgeAnalysis.getDue1To30Days());
					}
					if(tAgeAnalysis.getDue31To90Days()!=null){
						to90.add(tAgeAnalysis.getDue31To90Days());
					}
					rmap.put("RM_A", to90);
					rmap.put("RM_B", tAgeAnalysis.getNotDue91To180Days()!=null ? tAgeAnalysis.getNotDue91To180Days() : new BigDecimal("0"));
					rmap.put("RM_C", tAgeAnalysis.getDue181To360Days()!=null ? tAgeAnalysis.getDue181To360Days() : new BigDecimal("0"));
					rmap.put("RM_D", tAgeAnalysis.getDue361To720Days()!=null ? tAgeAnalysis.getDue361To720Days() : new BigDecimal("0"));
					rmap.put("RM_E", tAgeAnalysis.getDue721To1080Days()!=null ? tAgeAnalysis.getDue721To1080Days() : new BigDecimal("0"));
					rmap.put("RM_F", tAgeAnalysis.getDue1081To1440Days()!=null ? tAgeAnalysis.getDue1081To1440Days() : new BigDecimal("0"));
				}else if(tAgeAnalysis.getType().equals(6) || tAgeAnalysis.getType().equals("6")){
					BigDecimal to90=new BigDecimal("0");
					if(tAgeAnalysis.getDue1To30Days()!=null){
						to90.add(tAgeAnalysis.getDue1To30Days());
					}
					if(tAgeAnalysis.getDue31To90Days()!=null){
						to90.add(tAgeAnalysis.getDue31To90Days());
					}
					rmap.put("YSZKZL_A", to90);
					rmap.put("YSZKZL_B", tAgeAnalysis.getDue91To180Days()!=null ? tAgeAnalysis.getDue91To180Days() : new BigDecimal("0"));
					rmap.put("YSZKZL_C", tAgeAnalysis.getDue181To360Days()!=null ? tAgeAnalysis.getDue181To360Days() : new BigDecimal("0"));
					rmap.put("YSZKZL_D", tAgeAnalysis.getDue361To720Days()!=null ? tAgeAnalysis.getDue361To720Days() : new BigDecimal("0"));
					rmap.put("YSZKZL_E", tAgeAnalysis.getDue721To1080Days()!=null ? tAgeAnalysis.getDue721To1080Days() : new BigDecimal("0"));
					rmap.put("YSZKZL_F", tAgeAnalysis.getDue1081To1440Days()!=null ? tAgeAnalysis.getDue1081To1440Days() : new BigDecimal("0"));
				}else if(tAgeAnalysis.getType().equals(5) || tAgeAnalysis.getType().equals("5")){
					BigDecimal to90=new BigDecimal("0");
					if(tAgeAnalysis.getDue1To30Days()!=null){
						to90.add(tAgeAnalysis.getDue1To30Days());
					}
					if(tAgeAnalysis.getDue31To90Days()!=null){
						to90.add(tAgeAnalysis.getDue31To90Days());
					}
					rmap.put("YFZKZL_A", to90);
					rmap.put("YFZKZL_B", tAgeAnalysis.getDue91To180Days()!=null ? tAgeAnalysis.getDue91To180Days() : new BigDecimal("0"));
				 	rmap.put("YFZKZL_C", tAgeAnalysis.getDue181To360Days()!=null ? tAgeAnalysis.getDue181To360Days() : new BigDecimal("0"));
				 	rmap.put("YFZKZL_D", tAgeAnalysis.getDue361To720Days()!=null ? tAgeAnalysis.getDue361To720Days() : new BigDecimal("0"));
				 	rmap.put("YFZKZL_E", tAgeAnalysis.getDue721To1080Days()!=null ? tAgeAnalysis.getDue721To1080Days() : new BigDecimal("0"));
				 	rmap.put("YFZKZL_F", tAgeAnalysis.getDue1081To1440Days()!=null ? tAgeAnalysis.getDue1081To1440Days() : new BigDecimal("0"));
				}else if(tAgeAnalysis.getType().equals(3) || tAgeAnalysis.getType().equals("3")){
					BigDecimal to90=new BigDecimal("0");
					if(tAgeAnalysis.getDue1To30Days()!=null){
						to90.add(tAgeAnalysis.getDue1To30Days());
					}
					if(tAgeAnalysis.getDue31To90Days()!=null){
						to90.add(tAgeAnalysis.getDue31To90Days());
					}
					rmap.put("QTYFZL_A", to90);
					rmap.put("QTYFZL_B", tAgeAnalysis.getDue91To180Days()!=null ? tAgeAnalysis.getDue91To180Days() : new BigDecimal("0"));
					rmap.put("QTYFZL_C", tAgeAnalysis.getDue181To360Days()!=null ? tAgeAnalysis.getDue181To360Days() : new BigDecimal("0"));
					rmap.put("QTYFZL_D", tAgeAnalysis.getDue361To720Days()!=null ? tAgeAnalysis.getDue361To720Days() : new BigDecimal("0"));
					rmap.put("QTYFZL_E", tAgeAnalysis.getDue721To1080Days()!=null ? tAgeAnalysis.getDue721To1080Days() : new BigDecimal("0"));
					rmap.put("QTYFZL_F", tAgeAnalysis.getDue1081To1440Days()!=null ? tAgeAnalysis.getDue1081To1440Days() : new BigDecimal("0"));
				}else if(tAgeAnalysis.getType().equals(4) || tAgeAnalysis.getType().equals("4")){
					BigDecimal to90=new BigDecimal("0");
					if(tAgeAnalysis.getDue1To30Days()!=null){
						to90.add(tAgeAnalysis.getDue1To30Days());
					}
					if(tAgeAnalysis.getDue31To90Days()!=null){
						to90.add(tAgeAnalysis.getDue31To90Days());
					}
					rmap.put("QTYSZL_A", to90);
					rmap.put("QTYSZL_B", tAgeAnalysis.getDue91To180Days()!=null ? tAgeAnalysis.getDue91To180Days() : new BigDecimal("0"));
				 	rmap.put("QTYSZL_C", tAgeAnalysis.getDue181To360Days()!=null ? tAgeAnalysis.getDue181To360Days() : new BigDecimal("0"));
				 	rmap.put("QTYSZL_D", tAgeAnalysis.getDue361To720Days()!=null ? tAgeAnalysis.getDue361To720Days() : new BigDecimal("0"));
				 	rmap.put("QTYSZL_E", tAgeAnalysis.getDue721To1080Days()!=null ? tAgeAnalysis.getDue721To1080Days() : new BigDecimal("0"));
				 	rmap.put("QTYSZL_F", tAgeAnalysis.getDue1081To1440Days()!=null ? tAgeAnalysis.getDue1081To1440Days() : new BigDecimal("0"));
				}else if(tAgeAnalysis.getType().equals(7) ||tAgeAnalysis.getType().equals("7")){
					BigDecimal to90=new BigDecimal("0");
					if(tAgeAnalysis.getDue1To30Days()!=null){
						to90.add(tAgeAnalysis.getDue1To30Days());
					}
					if(tAgeAnalysis.getDue31To90Days()!=null){
						to90.add(tAgeAnalysis.getDue31To90Days());
					}
					rmap.put("YFKXZL_A", to90);
					rmap.put("YFKXZL_B", tAgeAnalysis.getDue91To180Days()!=null ? tAgeAnalysis.getDue91To180Days() : new BigDecimal("0"));
					rmap.put("YFKXZL_C", tAgeAnalysis.getDue181To360Days()!=null ? tAgeAnalysis.getDue181To360Days() : new BigDecimal("0"));
					rmap.put("YFKXZL_D", tAgeAnalysis.getDue361To720Days()!=null ? tAgeAnalysis.getDue361To720Days() : new BigDecimal("0"));
					rmap.put("YFKXZL_E", tAgeAnalysis.getDue721To1080Days()!=null ? tAgeAnalysis.getDue721To1080Days() : new BigDecimal("0"));
					rmap.put("YFKXZL_F", tAgeAnalysis.getDue1081To1440Days()!=null ? tAgeAnalysis.getDue1081To1440Days() : new BigDecimal("0"));
				}else if( tAgeAnalysis.getType().equals(8) || tAgeAnalysis.getType().equals("8")){
					BigDecimal to90=new BigDecimal("0");
					if(tAgeAnalysis.getDue1To30Days()!=null){
						to90.add(tAgeAnalysis.getDue1To30Days());
					}
					if(tAgeAnalysis.getDue31To90Days()!=null){
						to90.add(tAgeAnalysis.getDue31To90Days());
					}
					rmap.put("YSKXZL_A", to90);
					rmap.put("YSKXZL_B", tAgeAnalysis.getDue91To180Days()!=null ? tAgeAnalysis.getDue91To180Days() : new BigDecimal("0"));
					rmap.put("YSKXZL_C", tAgeAnalysis.getDue181To360Days()!=null ? tAgeAnalysis.getDue181To360Days() : new BigDecimal("0"));
					rmap.put("YSKXZL_D", tAgeAnalysis.getDue361To720Days()!=null ? tAgeAnalysis.getDue361To720Days() : new BigDecimal("0"));
					rmap.put("YSKXZL_E", tAgeAnalysis.getDue721To1080Days()!=null ? tAgeAnalysis.getDue721To1080Days() : new BigDecimal("0"));
					rmap.put("YSKXZL_F", tAgeAnalysis.getDue1081To1440Days()!=null ? tAgeAnalysis.getDue1081To1440Days() : new BigDecimal("0"));
				}
				
			}
		}
		
	
		Map<String, Object> smap=new HashMap<String, Object>();
		
		smap.put("subjectCodes", "2202");
		smap.put("noSubjectCode", "2202");
		smap.put("startTime", Dates.getDecDate(startTime));
		smap.put("endTime", Dates.getDecDate(endTime));
		smap.put("orgId", orgId);
		smap.put("left", "km08");
		List<ResultDto> rto6=reportResultMapper.selectResultKmTop5Sum(smap);
		if(rto6!=null){
			ResultDto r5=rto6.get(0);
			if(r5!=null){
				if(r5.getBigSumMoney()!=null){
					rmap.put("YFQWD_S",r5.getBigSumMoney());
				}else{
					rmap.put("YFQWD_S",new BigDecimal("0"));
				}
			}else{
				rmap.put("YFQWD_S",new BigDecimal("0"));
			}
			
		}else{
			rmap.put("YFQWD_S",new BigDecimal("0"));               //应付账款 前五大客户期末余额合计
		}
		smap.put("subjectCodes", null);
		smap.put("noSubjectCode", null);
		smap.put("startTime", null);
		smap.put("endTime", null);
		smap.put("orgId", null);
		smap.put("left", null);
		Map<String, Object> smap2=new HashMap<String, Object>();
		smap2.put("subjectCodes", "1122");
		smap2.put("noSubjectCode", "1122");
		smap2.put("startTime", Dates.getDecDate(startTime));
		smap2.put("endTime", Dates.getDecDate(endTime));
		smap2.put("orgId", orgId);
		smap2.put("left", "km07");
		List<ResultDto> rto5=reportResultMapper.selectResultKmTop5Sum(smap2);
		if(rto5!=null && rto5.size()>0){
			ResultDto r5=rto5.get(0);
			if(r5!=null){
				if(r5.getBigSumMoney()!=null){
					rmap.put("YSQWD_C",r5.getBigSumMoney());
				}else{
					rmap.put("YSQWD_C",new BigDecimal("0"));
				}
			}else{
				rmap.put("YSQWD_C",new BigDecimal("0"));
			}
		}else{
			rmap.put("YSQWD_C",new BigDecimal("0"));               //应收账款 前五大客户期末余额合计
		}
		smap2.put("subjectCodes", null);
		smap2.put("noSubjectCode", null);
		smap2.put("startTime", null);
		smap2.put("endTime", null);
		smap2.put("orgId", null);
		smap2.put("left", null);
		ResultDto bl=bilvService.getBilvResult(startTime, endTime, orgId, "bl5202");       //存货周转天数
		if(bl!=null){
			if(bl.getBigSumMoney()!=null){
				rmap.put("YBLFX_N", bl.getBigSumMoney());
			}else{
				rmap.put("YBLFX_N",new BigDecimal("0"));
			}
			if(bl.getTbSumMoney()!=null){
				rmap.put("YBLFX_N1", bl.getTbSumMoney());
			}else{
				rmap.put("YBLFX_N1",new BigDecimal("0"));
			}
			if(bl.getHbSumMoney()!=null){
				rmap.put("YBLFX_N2", bl.getHbSumMoney());
			}else{
				rmap.put("YBLFX_N2",new BigDecimal("0"));
			}
		}else{
			rmap.put("YBLFX_N",new BigDecimal("0"));
			rmap.put("YBLFX_N1",new BigDecimal("0"));
			rmap.put("YBLFX_N2",new BigDecimal("0"));
		}
		ResultDto bl2=bilvService.getBilvResult(startTime, endTime, orgId, "bl5203");             //YBLFX_O 应付账款周转天数
		if(bl2!=null){
			if(bl2.getBigSumMoney()!=null){
				rmap.put("YBLFX_O", bl2.getBigSumMoney());
			}else{
				rmap.put("YBLFX_O",new BigDecimal("0"));
			}
			if(bl2.getTbSumMoney()!=null){
				rmap.put("YBLFX_O1", bl2.getTbSumMoney());
			}else{
				rmap.put("YBLFX_O1",new BigDecimal("0"));
			}
			if(bl2.getHbSumMoney()!=null){
				rmap.put("YBLFX_O2", bl2.getHbSumMoney());
			}else{
				rmap.put("YBLFX_O2",new BigDecimal("0"));
			}
		}else{
			rmap.put("YBLFX_O",new BigDecimal("0"));
			rmap.put("YBLFX_O1",new BigDecimal("0"));
			rmap.put("YBLFX_O2",new BigDecimal("0"));
		}
		
		ResultDto bl3=bilvService.getBilvResult(startTime, endTime, orgId, "bl5303");             //YBLFX-Y 权益净利润率
		if(bl3!=null){
			if(bl3.getBigSumMoney()!=null){
				rmap.put("YBLFX_Y", bl3.getBigSumMoney());
			}else{
				rmap.put("YBLFX_Y",new BigDecimal("0"));
			}
			if(bl3.getTbSumMoney()!=null){
				rmap.put("YBLFX_Y1", bl3.getTbSumMoney());
			}else{
				rmap.put("YBLFX_Y1",new BigDecimal("0"));
			}
			if(bl3.getHbSumMoney()!=null){
				rmap.put("YBLFX_Y2", bl3.getHbSumMoney());
			}else{
				rmap.put("YBLFX_Y2",new BigDecimal("0"));
			}
		}else{
			rmap.put("YBLFX_Y",new BigDecimal("0"));
			rmap.put("YBLFX_Y1",new BigDecimal("0"));
			rmap.put("YBLFX_Y2",new BigDecimal("0"));
		}
		//YBLFX-L应收账款周转天数
		ResultDto bl4=bilvService.getBilvResult(startTime, endTime, orgId, "bl5201");             //YBLFX-L应收账款周转天数
		if(bl4!=null){
			if(bl4.getBigSumMoney()!=null){
				rmap.put("YBLFX_L", bl4.getBigSumMoney());
			}else{
				rmap.put("YBLFX_L",new BigDecimal("0"));
			}
			if(bl4.getTbSumMoney()!=null){
				rmap.put("YBLFX_L1", bl4.getTbSumMoney());
			}else{
				rmap.put("YBLFX_L1",new BigDecimal("0"));
			}
			if(bl4.getHbSumMoney()!=null){
				rmap.put("YBLFX_L2", bl4.getHbSumMoney());
			}else{
				rmap.put("YBLFX_L2",new BigDecimal("0"));
			}
		}else{
			rmap.put("YBLFX_Y",new BigDecimal("0"));
			rmap.put("YBLFX_L1",new BigDecimal("0"));
			rmap.put("YBLFX_L2",new BigDecimal("0"));
		}
		/*rmap.put("RM_A",new BigDecimal("0"));
		rmap.put("RM_B",new BigDecimal("0"));
		rmap.put("RM_C",new BigDecimal("0"));
		rmap.put("RM_D",new BigDecimal("0"));
		rmap.put("RM_E",new BigDecimal("0"));
		rmap.put("RM_F",new BigDecimal("0"));
		rmap.put("FG_A",new BigDecimal("0"));
		rmap.put("FG_B",new BigDecimal("0"));
		rmap.put("FG_C",new BigDecimal("0"));
		rmap.put("FG_D",new BigDecimal("0"));
		rmap.put("FG_E",new BigDecimal("0"));
		rmap.put("FG_F",new BigDecimal("0"));*/
		return rmap;
	}
	@Override
	public Map<String,BigDecimal> getTeshu3(String startTime, String endTime, String orgId,Map<String,BigDecimal> rmap) throws ParseException{
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("startTime", Dates.getDecDate(startTime));
		map.put("endTime", Dates.getDecDate(endTime));
		map.put("type", 9);
		////map.put("projectName", "合计");
		map.put("orgId", orgId);
		List<TAgeAnalysis> rList=tAgeAnalysisService.selectSumAllByQJ(map);
		
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("left", "km03");
		this.getOthen(map, "XSFY-RG", rmap,"1"); 			//销售费用-人工
		BigDecimal xrg=rmap.get("XSFY_RG");
		BigDecimal xrg1=rmap.get("XSFY_RG1");
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("left", "km03");
		this.getOthen(map, "XSFY-CL", rmap,"1"); 			//销售费用-差旅费
		BigDecimal xcl=rmap.get("XSFY_CL");
		BigDecimal xcl1=rmap.get("XSFY_CL1");
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("left", "km03");
		this.getOthen(map, "XSFY-TG", rmap,"1"); 			//销售费用-市场推广费
		BigDecimal xtg=rmap.get("XSFY_TG");
		BigDecimal xtg1=rmap.get("XSFY_TG1");
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("left", "km03");
		this.getOthen(map, "XSFY-ZD", rmap,"1"); 			//销售费用-业务招待费
		BigDecimal xyw=rmap.get("XSFY_ZD");
		BigDecimal xyw1=rmap.get("XSFY_ZD1");
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("left", "km07");
		map.put("subjectCode", "6601");
		BigDecimal xqt=new BigDecimal("0");
		BigDecimal xqt1=new BigDecimal("0");
		List<ResultDto> xrLists=this.selectResultKm2(map);
		if(xrLists!=null && xrLists.size()>0){
			ResultDto r=xrLists.get(0);
			BigDecimal xzs=new BigDecimal("0");
			BigDecimal xzs1=new BigDecimal("0");
			if(r.getBigSumMoney()!=null){
				xzs=r.getBigSumMoney();
			}
			xqt=xzs.subtract(xrg).subtract(xcl).subtract(xtg).subtract(xyw);
			if(r.getTbSumMoney()!=null){
				xzs1=r.getTbSumMoney();
			}
			xqt1=xzs1.subtract(xrg1).subtract(xcl1).subtract(xtg1).subtract(xyw1);
		}
		rmap.put("XSFY_QT", xqt);
		rmap.put("XSFY_QT1", xqt1);
		map.put("subjectCode", null);
		//this.getOthen(map, "XSFY-QT", rmap,"1"); 			//销售费用其他
		
		
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("left", "km07");
		this.getOthen(map, "QTCB-A", rmap,"1"); 			//其他成本
		
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("left", "km23");
		map.put("type",1);
		map.put("spmc","合计");
		this.getOthen(map, "XSSL-A", rmap,"2"); 			//发出数量
		map.put("type",null);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("left", "km03");
		this.getOthen(map, "CLCB-C", rmap,"1"); 			//成本-直接材料
		Map<String, Object> smap=new HashMap<String, Object>();
		smap.put("subjectCodes", "1122");
		smap.put("noSubjectCode", "1122");
		smap.put("startTime", startTime);
		smap.put("endTime", endTime);
		smap.put("orgId", orgId);
		smap.put("left", "km07");
		List<ResultDto> rto5=reportResultMapper.selectResultKmTop5Sum(smap);
		if(rto5!=null && rto5.size()>0){
			ResultDto r5=rto5.get(0);
			if(r5!=null){
				if(r5.getBigSumMoney()!=null){
					rmap.put("YSQWD_C",r5.getBigSumMoney());
				}else{
					rmap.put("YSQWD_C",new BigDecimal("0"));
				}
			}else{
				rmap.put("YSQWD_C",new BigDecimal("0"));
			}
		}else{
			rmap.put("YSQWD_C",new BigDecimal("0"));               //应收账款 前五大客户期末余额合计
		}
		smap.put("subjectCodes", "2202");
		smap.put("noSubjectCode", "2202");
		smap.put("startTime", Dates.getDecDate(startTime));
		smap.put("endTime", Dates.getDecDate(endTime));
		smap.put("orgId", orgId);
		smap.put("left", "km08");
		List<ResultDto> rto6=reportResultMapper.selectResultKmTop5Sum(smap);
		if(rto6!=null){
			ResultDto r5=rto6.get(0);
			if(r5!=null){
				if(r5.getBigSumMoney()!=null){
					rmap.put("YFQWD_S",r5.getBigSumMoney());
				}else{
					rmap.put("YFQWD_S",new BigDecimal("0"));
				}
			}else{
				rmap.put("YFQWD_S",new BigDecimal("0"));
			}
			
		}else{
			rmap.put("YFQWD_S",new BigDecimal("0"));               //应付账款 前五大客户期末余额合计
		}
		smap.put("subjectCodes", null);
		smap.put("noSubjectCode", null);
		smap.put("startTime", null);
		smap.put("endTime", null);
		smap.put("orgId", null);
		smap.put("left", null);
		Map<String, Object> sbmap=new HashMap<String, Object>();
		sbmap.put("startTime", Dates.getDecDate(startTime));
		sbmap.put("endTime", Dates.getDecDate(endTime));
		sbmap.put("orgId", orgId);
		sbmap.put("left", "km26");
		sbmap.put("type", "1");
		ResultDto rto=reportResultMapper.selectResultSaleSum(sbmap);
		if(rto!=null){
			if(rto.getBigSumMoney()!=null){
				rmap.put("YCLKC_B",rto.getBigSumMoney());               //原材料库存期末余额
			}else{
				rmap.put("YCLKC_B",new BigDecimal("0"));
			}
		}else{
			rmap.put("YCLKC_B",new BigDecimal("0"));
		}
		sbmap.put("startTime", Dates.getDecDate(startTime));
		sbmap.put("endTime", Dates.getDecDate(endTime));
		sbmap.put("type", "2");
		ResultDto rto2=reportResultMapper.selectResultSaleSum(sbmap);
		if(rto2!=null){
			if(rto2.getBigSumMoney()!=null){
				rmap.put("YCLKC_B",rto2.getBigSumMoney());               //产成品库存期末余额
			}else{
				rmap.put("YCLKC_B",new BigDecimal("0"));
			}
		}else{
			rmap.put("YCLKC_B",new BigDecimal("0"));
		}
		sbmap.put("startTime", null);
		sbmap.put("endTime", null);
		sbmap.put("orgId", null);
		sbmap.put("left", null);
		sbmap.put("type", null);
		/*rmap.put("RM_A",new BigDecimal("0"));
		rmap.put("RM_B",new BigDecimal("0"));
		rmap.put("RM_C",new BigDecimal("0"));
		rmap.put("RM_D",new BigDecimal("0"));
		rmap.put("RM_E",new BigDecimal("0"));
		rmap.put("RM_F",new BigDecimal("0"));
		rmap.put("FG_A",new BigDecimal("0"));
		rmap.put("FG_B",new BigDecimal("0"));
		rmap.put("FG_C",new BigDecimal("0"));
		rmap.put("FG_D",new BigDecimal("0"));
		rmap.put("FG_E",new BigDecimal("0"));
		rmap.put("FG_F",new BigDecimal("0"));*/
		return rmap;
	}
	@Override
	public  Map<String,BigDecimal> getOthen(Map<String,Object> map,String code,Map<String,BigDecimal> rmap,String type){
		Object startTime=map.get("startTime");
		Object endTime=map.get("endTime");
		Object endTimes=map.get("endTimes");
		
		List<String> kmList=new ArrayList<String>();
		if(code.equals("GLFY-QT")){
			List<String> rgs=new ArrayList<String>();
			List<String> rgs2=new ArrayList<String>();
			rgs2.add("GLFY-ZD");
			rgs2.add("GLFY-ZJ");
			rgs2.add("GLFY-RG");
			for(int i=0;i<rgs2.size();i++){
				SysDictionaryModel dic=new SysDictionaryModel();
				dic.setEnName(rgs2.get(i));
				dic.setCnName("");
				SysDictionaryModel ids=sysDictionaryMapper.selectByCnNameOrEnName(dic);
				List<SysDictionaryDetailModel> rgList=sysDictionaryDetailMapper.selectByDictionaryId(ids.getId());
				if(rgList!=null && rgList.size()>0){
					for (SysDictionaryDetailModel sysDictionaryDetailModel : rgList) {
						rgs.add(sysDictionaryDetailModel.getName());
					}
				}
			}
			map.put("notSubNameLists", rgs);
			kmList.add("6602");
		}else{
			SysDictionaryModel dic=new SysDictionaryModel();
			dic.setEnName(code);
			dic.setCnName("");
			SysDictionaryModel ids=sysDictionaryMapper.selectByCnNameOrEnName(dic);
			if(ids!=null){
				String cnName=ids.getCnName();
				int index=cnName.indexOf("@");
				if(index>0){
					String kmCode=cnName.substring(index+1);
					kmList=Arrays.asList(kmCode.split(";"));
				}
				List<SysDictionaryDetailModel> rgList=sysDictionaryDetailMapper.selectByDictionaryId(ids.getId());
				List<String> rgs=new ArrayList<String>();
				if(rgList!=null && rgList.size()>0){
					for (SysDictionaryDetailModel sysDictionaryDetailModel : rgList) {
						rgs.add(sysDictionaryDetailModel.getName());
					}
				}
				map.put("subNameLists", rgs);
				
			}
		}
		
		
		/*if(rgs!=null && rgs.size()>0){
			map.put("subNameLists", rgs);
		}else{
			map.put("subNameLists", null);
		}*/
		
		BigDecimal bq=new BigDecimal("0");
		BigDecimal sq=new BigDecimal("0");
		if(type.equals("1")){
			for(int i=0;i<kmList.size();i++){
				map.put("subjectCodes", kmList.get(i));
				map.put("noSubjectCode", kmList.get(i));
				if(startTime!=null){
					map.put("startTime",startTime);
				}
				if(endTime!=null){
					map.put("endTime",endTime);
				}
				if(endTimes!=null){
					map.put("endTimes",endTimes);
				}
				List<ResultDto> gz=this.selectResultKm2(map);
				if(gz!=null && gz.size()>0){
					for (ResultDto resultDto : gz) {
						bq=bq.add(resultDto.getBigSumMoney());
						if(resultDto.getTbSumMoney()!=null){
							sq=sq.add(resultDto.getTbSumMoney());
						}
					}
				}
			}
		}else if(type.equals("2")){
			if(startTime!=null){
				map.put("startTime",startTime);
			}
			if(endTime!=null){
				map.put("endTime",endTime);
			}
			if(endTimes!=null){
				map.put("endTimes",endTimes);
			}
			List<ResultDto> gz=selectResultSale2(map);
			if(gz!=null && gz.size()>0){
				for (ResultDto resultDto : gz) {
					bq=bq.add(resultDto.getBigSumMoney());
					sq=sq.add(resultDto.getTbSumMoney());
				}
			}
		}
		String codes = code.replace("-", "_");
		rmap.put(codes, bq);
		rmap.put(codes+"1", sq);
		map.put("subNameLists", null);
		map.put("noSubjectCode", null);
		map.put("subjectCodes", null);
		return rmap;
	}
	@Override
	public Map<String, BigDecimal> getModeParams(String startTime, String endTime, String orgId, String reportType) throws Exception {
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM" );
		Date startM1 = sdf.parse(startTime);
		Date endM1 = sdf.parse(endTime);
		Date startM2 = sdf.parse(Integer.toString(Integer.parseInt(startTime.substring(0, 4)) - 1) + startTime.substring(4, 7)) ;
		Date endM2 = sdf.parse(Integer.toString(Integer.parseInt(endTime.substring(0, 4)) - 1) + endTime.substring(4, 7)) ;
		Calendar endC = Calendar.getInstance() ;
		Calendar startC = Calendar.getInstance() ;
		endC.setTime(endM1);
		startC.setTime(startM1);
		int tmpMonth = endC.get(Calendar.MONTH) - startC.get(Calendar.MONTH) + (endC.get(Calendar.YEAR) - startC.get(Calendar.YEAR))*12 ;
		startC.add(Calendar.MONTH, -tmpMonth - 1);
		Date hbStartM1 = startC.getTime();
		Calendar hbEndC = Calendar.getInstance() ;
		hbEndC.setTime(startM1);
		hbEndC.add(Calendar.MONTH, -1);
		Date hbEndM1 = hbEndC.getTime() ;
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("startM1", hbStartM1);
		map.put("endM1", endM1);
		map.put("startM2", startM2);
		map.put("endM2", endM2);
		map.put("orgId", orgId);
		map.put("reportType",reportType);
		Map<String , BigDecimal> reMap = new HashMap<>() ;
		//三大基础表的常规指标
		getNormalModeParams(sdf, startM1, endM1, startM2, endM2, hbStartM1, hbEndM1, map, reMap);

		//利润表单独计算的指标
		if(null == reportType || REPORT_TYPE_LR.equals(reportType)){
			getLrSModeParams(REPORT_TYPE_LR, sdf, startM1, endM1, startM2, endM2, hbStartM1, hbEndM1, map, reMap);
		}

		//资产负债表单独计算的指标
		if(null == reportType || REPORT_TYPE_ZCFZ.equals(reportType)){
			getZCFZSModeParams(REPORT_TYPE_LR, startM1, endM1, map, reMap);
		}

		return reMap;
	}

	private void getZCFZSModeParams(String reportType, Date startM1, Date endM1, Map<String, Object> map,
			Map<String, BigDecimal> reMap) {
		Calendar countC = Calendar.getInstance() ;
		countC.setTime(startM1);
		countC.add(Calendar.MONTH, -12);
		Date sTime = countC.getTime() ;
		map.put("sTime", sTime);
		map.put("eTime", startM1) ;
		List<String> codeLists = new ArrayList<>() ;
		Map<String,String> codeMapper = new HashMap<>() ;
		for(NeedCaluateModeEum val : NeedCaluateModeEum.values()){
			if(val.getSubjectCode() != null && val.getReprotType().equals(reportType)){
				codeLists.add(val.getSubjectCode());
				codeMapper.put(val.getSubjectCode(), val.getCodeNo()) ;
			}
		}
		map.put("codeLists", codeLists);
		List<ResultDto> zjynCount = reportResultMapper.getZJYNCountMsg(map);
		for(ResultDto bean : zjynCount){
			String code = codeMapper.get(bean.getCode());
			if(!StringUtils.isEmpty(code)){
				reMap.put(code, bean.getBigSumMoney()) ;
			}
		}
		BigDecimal yysr = reMap.get("ZCFZ-YYSR") ;
		BigDecimal ng = reMap.get("ZCFZ-ZJYNG") ;
		BigDecimal yycb = reMap.get("ZCFZ-YYCB") ;
		BigDecimal nav = reMap.get("ZCFZ-ZJYNAV") ;
		if(yysr != null && ng != null && ng.compareTo(new BigDecimal(0)) != 0){
			BigDecimal zzy = new BigDecimal(365).divideToIntegralValue((yysr.divide(ng,2, RoundingMode.HALF_UP)));
			reMap.put("ZCFZ-YSZKZZY", zzy);
		}
		if(yycb != null && nav != null && nav.compareTo(new BigDecimal(0)) != 0){
			BigDecimal zzt = new BigDecimal(365).divideToIntegralValue((yycb.divide(nav,2, RoundingMode.HALF_UP)));
			reMap.put("ZCFZ-YSZKZZT", zzt);
		}

		map.clear();
		map.put("time", endM1);
		map.put("codeNo", "ZCFZ-A");
		BigDecimal hbzj = reportResultMapper.selectOne(map);
		if(null != hbzj){
			reMap.put("ZCFZ-ZHYYXJ", hbzj);
		}
	}

	private void getNormalModeParams(SimpleDateFormat sdf, Date startM1, Date endM1, Date startM2, Date endM2,
			Date hbStartM1, Date hbEndM1, Map<String, Object> map, Map<String, BigDecimal> reMap) throws Exception {
		List<ResultDto> allDatas = reportResultMapper.getReportDetailMsg(map);
		BigDecimal amt = new BigDecimal(0);
		BigDecimal hbAmt = new BigDecimal(0);
		BigDecimal tbAmt = new BigDecimal(0);
		int i = 0 ;
		String codeNo = null ;
		for(ResultDto bean : allDatas){
			String lastCodeNo = bean.getCode() ;
			if ( null == codeNo){
				codeNo = lastCodeNo ;
			}
			else if (!codeNo.equals(lastCodeNo)){
				String code = codeNo.replace("-", "_");
				reMap.put(code, amt.setScale(2,BigDecimal.ROUND_HALF_UP));
				reMap.put(code + "1", tbAmt.setScale(2,BigDecimal.ROUND_HALF_UP));
				reMap.put(code + "2", hbAmt.setScale(2,BigDecimal.ROUND_HALF_UP));
				codeNo = lastCodeNo ;
				amt = new BigDecimal(0);
				hbAmt = new BigDecimal(0);
				tbAmt = new BigDecimal(0);
			}
			if(compareTime(startM1,endM1,sdf, bean)){
				if(REPORT_TYPE_ZCFZ.equals(bean.getReportType())){
					if(null == amt){
						amt = bean.getBigSumMoney() ;
					}
					else if(bean.getBigSumMoney() != null && amt.compareTo(bean.getBigSumMoney()) < 0){
						amt = bean.getBigSumMoney() ;
					}
				}
				else{
					amt = amt.add(bean.getBigSumMoney()) ;
				}
			}
			if(compareTime(hbStartM1,hbEndM1,sdf, bean)){
				if(REPORT_TYPE_ZCFZ.equals(bean.getReportType())){
					if(null == hbAmt){
						hbAmt = bean.getBigSumMoney() ;
					}
					else if(bean.getBigSumMoney() != null && hbAmt.compareTo(bean.getBigSumMoney()) < 0){
						hbAmt = bean.getBigSumMoney() ;
					}
				}
				else{
					hbAmt = hbAmt.add(bean.getBigSumMoney()) ;
				}
			}
			if(compareTime(startM2,endM2,sdf, bean)){
				if(REPORT_TYPE_ZCFZ.equals(bean.getReportType())){
					if(null == tbAmt){
						tbAmt = bean.getBigSumMoney() ;
					}
					else if(bean.getBigSumMoney() != null && tbAmt.compareTo(bean.getBigSumMoney()) < 0){
						tbAmt = bean.getBigSumMoney() ;
					}
				}
				else{
					tbAmt = tbAmt.add(bean.getBigSumMoney()) ;
				}
			}
			if(i == allDatas.size() - 1){
				String code = codeNo.replace("-", "_");
				reMap.put(code, amt.setScale(2,BigDecimal.ROUND_HALF_UP));
				reMap.put(code + "1", tbAmt.setScale(2,BigDecimal.ROUND_HALF_UP));
				reMap.put(code + "2", hbAmt.setScale(2,BigDecimal.ROUND_HALF_UP));
			}
			i++ ;
		}
	}

	private void getLrSModeParams(String reportType, SimpleDateFormat sdf, Date startM1, Date endM1, Date startM2,
			Date endM2, Date hbStartM1, Date hbEndM1, Map<String, Object> map, Map<String, BigDecimal> reMap)
					throws Exception {
		BigDecimal amt;
		BigDecimal hbAmt;
		BigDecimal tbAmt;
		amt = new BigDecimal(0);
		hbAmt = new BigDecimal(0);
		tbAmt = new BigDecimal(0);
		List<String> subjectCodes = new ArrayList<>() ;
		Map<String,String> codeMapper = new HashMap<>() ;
		for(NeedCaluateModeEum val : NeedCaluateModeEum.values()){
			if(val.getSubjectCode() != null && val.getReprotType().equals(reportType)){
				subjectCodes.add(val.getSubjectCode());
				codeMapper.put(val.getSubjectCode(), val.getCodeNo()) ;
			}
		}
		if(subjectCodes!=null && subjectCodes.size()>0){
			map.put("subCodeLists", subjectCodes);
		}else{
			map.put("subCodeLists", null);
		}
		List<TKmye> list = reportResultMapper.getReportDetailBySubCodes(map);
		int m = 0 ;
		String subCode = null ;
		for(TKmye bean : list){
			String lastCodeNo = bean.getSubjectCode();
			if ( null == subCode){
				subCode = lastCodeNo ;
			}
			else if (!subCode.equals(lastCodeNo)){
				String code = codeMapper.get(subCode);
				if(StringUtils.isEmpty(code)){
					continue ;
				}
				if(reMap.containsKey(code)){
					amt = amt.add(reMap.get(code)) ;
				}
				reMap.put(code, amt.setScale(2,BigDecimal.ROUND_HALF_UP));
				reMap.put(code + "1", tbAmt.setScale(2,BigDecimal.ROUND_HALF_UP));
				reMap.put(code + "2", hbAmt.setScale(2,BigDecimal.ROUND_HALF_UP));
				subCode = lastCodeNo ;
				amt = new BigDecimal(0);
				hbAmt = new BigDecimal(0);
				tbAmt = new BigDecimal(0);
			}
			if(compareTime(startM1,endM1,sdf, bean)){
				amt = amt.add(bean.getBqdffse()) ;
			}
			if(compareTime(hbStartM1,hbEndM1,sdf, bean)){
				hbAmt = hbAmt.add(bean.getBqdffse());
			}
			if(compareTime(startM2,endM2,sdf, bean)){
				tbAmt = tbAmt.add(bean.getBqdffse());
			}
			if(m == list.size() - 1){
				String code = codeMapper.get(subCode);
				if(StringUtils.isEmpty(code)){
					continue ;
				}
				if(reMap.containsKey(code)){
					amt = amt.add(reMap.get(code)) ;
				}
				reMap.put(code, amt.setScale(2,BigDecimal.ROUND_HALF_UP));
				reMap.put(code + "1", tbAmt.setScale(2,BigDecimal.ROUND_HALF_UP));
				reMap.put(code + "2", hbAmt.setScale(2,BigDecimal.ROUND_HALF_UP));
			}
			m++ ;
		}
	}

	/**
	 * 获取利润表首页信息
	 * @param map
	 * @return
	 */
	/*@Override
	public List<ResultDto> selectProfitMainMsg(String startTime , String endTime , String orgId , String reportType) throws Exception{
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM" );
		Date startM1 = sdf.parse(startTime);
		Date endM1 = sdf.parse(endTime);
		Date startM2 = sdf.parse(Integer.toString(Integer.parseInt(startTime.substring(0, 4)) - 1) + startTime.substring(4, 7)) ;
		Date endM2 = sdf.parse(Integer.toString(Integer.parseInt(endTime.substring(0, 4)) - 1) + endTime.substring(4, 7)) ;
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("startM1", startM1);
		map.put("endM1", endM1);
		map.put("startM2", startM2);
		map.put("endM2", endM2);
		map.put("orgId", orgId);
		map.put("reportType",reportType);
		List<ResultDto> retList = new ArrayList<>() ;
		List<ResultDto> all = reportResultMapper.selectProfitMainMsg(map);
		dealResultData(sdf, startM1, endM1,startM2, endM2, retList, all);
		return retList ;
	}
	 */

	/**
	 * 获取明细信息
	 * @param mapmccc
	 * @return
	 */
	/*public List<ResultDto> selectDetailMsg(String startTime , String endTime , String orgId , String reportType)throws Exception {
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM" );
		Date startM1 = sdf.parse(startTime);
		Date endM1 = sdf.parse(endTime);
		Calendar endC = Calendar.getInstance() ;
		Calendar startC = Calendar.getInstance() ;
		endC.setTime(endM1);
		startC.setTime(startM1);
		int tmpMonth = endC.get(Calendar.MONTH) - startC.get(Calendar.MONTH) + (endC.get(Calendar.YEAR) - startC.get(Calendar.YEAR))*12 ;
		startC.add(Calendar.MONTH, -tmpMonth - 1);
		Date hbStartM1 = startC.getTime();
		Calendar hbStartC = Calendar.getInstance() ;
		hbStartC.setTime(startM1);
		hbStartC.add(Calendar.MONTH, -1);
		Date hbEndM1 = hbStartC.getTime() ;
		Date startM2 = sdf.parse(Integer.toString(Integer.parseInt(startTime.substring(0, 4)) - 1) + startTime.substring(4, 7)) ;
		Date endM2 = sdf.parse(Integer.toString(Integer.parseInt(endTime.substring(0, 4)) - 1) + endTime.substring(4, 7)) ;
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("startM1", hbStartM1);
		map.put("endM1", endM1);
		map.put("startM2", startM2);
		map.put("endM2", endM2);
		map.put("orgId", orgId);
		map.put("reportType",reportType);
		List<ResultDto> retList = new ArrayList<>() ;
		List<ResultDto> all = reportResultMapper.getReportDetailMsg(map);
		dealResultData(sdf, startM1, endM1, hbStartM1, hbEndM1, startM2, endM2, retList, all);
		return retList ;
	}*/

	/**
	 * 通过codeNo获取从开始时间道结束时间内每个月的明细信息
	 * @param startTime
	 * @param endTime
	 * @param orgId
	 * @param codeNos
	 * @param reportType
	 * @param getDataType   1:查本期数据;2:查本期及其环比数据;3:查本期及其同比数据;4:查本期及其同比和环比数据
	 * @return
	 * @throws Exception
	 */
	/*public List<ResultDto> selectReportDataByCodeNo(String startTime , String endTime , String orgId , List<String> codeNos, String reportType,int getDataType) throws Exception{
		int getHB = 2 ;
		int getTB = 3 ;
		int getTBAndHB = 4 ;
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM" );
		Date startM1 = sdf.parse(startTime);
		Date date1 = sdf.parse(startTime);
		Date endM1 = sdf.parse(endTime);
		if(getDataType == getHB || getDataType == getTBAndHB){
			//若要查询同比信息则需要重新设置查询的开始时间
			Calendar startC = Calendar.getInstance();
			startC.setTime(startM1);
			startC.add(Calendar.MONTH, -1);
			startM1 = startC.getTime() ;
		}
		Calendar startE = Calendar.getInstance() ;
		startE.setTime(endM1);
		startE.add(Calendar.MONTH, -1);
		Date hbEnd1 = startE.getTime() ;
		Date startM2 = sdf.parse(Integer.toString(Integer.parseInt(startTime.substring(0, 4)) - 1) + startTime.substring(4, 7)) ;
		Date endM2 = sdf.parse(Integer.toString(Integer.parseInt(endTime.substring(0, 4)) - 1) + endTime.substring(4, 7)) ;
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("startM1", startM1);
		map.put("endM1", endM1);
		if(getDataType == getTB || getDataType == getTBAndHB){
			//若要查询同比数据，则要添加同比查询时间
			map.put("startM2", startM2);
			map.put("endM2", endM2);
		}
		map.put("orgId", orgId);
		map.put("reportType",reportType);
		if(codeNos.size() == 1){
			map.put("codeNo", codeNos.get(0)) ;
			map.put("isList", false);
		}
		else{
			map.put("codeNoList", codeNos) ;
			map.put("isList", true);
		}
		List<ResultDto> all = reportResultMapper.getReportDetailByCodeNoList(map);
		List<ResultDto> retList = new ArrayList<>() ;
		Map<String, BigDecimal> tbMap = new HashMap<String, BigDecimal>();
		Map<String, BigDecimal> hbMap = new HashMap<String, BigDecimal>();
		String codeNo = null ;
		int i = 0 ;
		for(ResultDto bean : all){
			String lastCodeNo = bean.getCode() ;
			if(null == codeNo){
				codeNo = lastCodeNo ;
			}
			else{
				if(!codeNo.equals(lastCodeNo)){
					//切换codeno的时候计算同比和环比
					if(getDataType == getHB || getDataType == getTB || getDataType == getTBAndHB ){
						BigDecimal zero = new BigDecimal(0) ;
						for(ResultDto tmpBean : retList){
							if(codeNo.equals(tmpBean.getCode())){
								if(getDataType == getHB || getDataType == getTBAndHB){
									BigDecimal tmp = hbMap.get(getHbOrTbYearAndMonth(sdf, tmpBean,Calendar.MONTH,-1)) ;
									if(null != tmp && zero.compareTo(tmp) != 0 && null != tmpBean.getSumMoney() ){
										tmpBean.setLinkRise(getHBScal(tmpBean.getSumMoney(), tmp));
									}
								}
								if(getDataType == getTB || getDataType == getTBAndHB){
									BigDecimal tmp = tbMap.get(getHbOrTbYearAndMonth(sdf, tmpBean,Calendar.YEAR,-1)) ;
									if(null != tmp && zero.compareTo(tmp) != 0 && null != tmpBean.getSumMoney() ){
										tmpBean.setOnRise(getTBScal(tmpBean.getSumMoney(), tmp));
									}
								}
							}
						}
					}
					codeNo = lastCodeNo ;
					tbMap.clear();
					hbMap.clear();
				}
			}
			if(compareTime(date1,endM1,sdf, bean)){
				//获取本身查询数据
				retList.add(bean);
			}
			if((getDataType == getHB || getDataType == getTBAndHB) && compareTime(startM1,hbEnd1,sdf, bean)){
				//获取环比数据
				hbMap.put(bean.getYearMoth(), bean.getSumMoney());
			}
			if((getDataType == getTB || getDataType == getTBAndHB) && compareTime(startM2,endM2,sdf, bean)){
				//获取同比数据
				tbMap.put(bean.getYearMoth(), bean.getSumMoney());
			}
			if(i == all.size() - 1){
				//切换codeno的时候计算同比和环比--最后一次循环的
				if(getDataType == getHB || getDataType == getTB || getDataType == getTBAndHB ){
					BigDecimal zero = new BigDecimal(0) ;
					for(ResultDto tmpBean : retList){
						if(codeNo.equals(tmpBean.getCode())){
							if(getDataType == getHB || getDataType == getTBAndHB){
								BigDecimal tmp = hbMap.get(getHbOrTbYearAndMonth(sdf, tmpBean,Calendar.MONTH,-1)) ;
								if(null != tmp && zero.compareTo(tmp) != 0 && null != tmpBean.getSumMoney() ){
									tmpBean.setLinkRise(getHBScal(tmpBean.getSumMoney(), tmp));
								}
							}
							if(getDataType == getTB || getDataType == getTBAndHB){
								BigDecimal tmp = tbMap.get(getHbOrTbYearAndMonth(sdf, tmpBean,Calendar.YEAR,-1)) ;
								if(null != tmp && zero.compareTo(tmp) != 0 && null != tmpBean.getSumMoney() ){
									tmpBean.setOnRise(getTBScal(tmpBean.getSumMoney(), tmp));
								}
							}
						}
					}
				}
			}
			i ++ ;
		}
		return retList ;
	}*/

	/**
	 * 获取bean里面的年月对应的同比或者环比的年月
	 * @param sdf
	 * @param bean
	 * @param type
	 * @param value
	 * @return
	 * @throws ParseException
	 */
	private String getHbOrTbYearAndMonth(SimpleDateFormat sdf, ResultDto bean , int type , int value) throws ParseException {
		Date tmpDate = sdf.parse(bean.getYearMoth()) ;
		Calendar tmpC = Calendar.getInstance() ;
		tmpC.setTime(tmpDate);
		tmpC.add(type, value);
		tmpDate = tmpC.getTime() ;
		String str = sdf.format(tmpDate) ;
		return str;
	}

	/**
	 * 处理查询出的数据，获取当前数据及其同比数据
	 * @param sdf
	 * @param startM1
	 * @param endM1
	 * @param startM2
	 * @param endM2
	 * @param retList
	 * @param all
	 * @throws Exception
	 */
	/*private void dealResultData(SimpleDateFormat sdf, Date startM1, Date endM1, Date startM2, Date endM2, List<ResultDto> retList, List<ResultDto> all) throws Exception {
		dealResultData(sdf, startM1, endM1, null, null, startM2, endM2, retList, all);
	}*/


	/**
	 * 处理查询出的数据，获取当前数据及其同比和环比数据
	 * @param sdf
	 * @param startM1
	 * @param endM1
	 * @param hbStartM1
	 * @param hbEndM1
	 * @param startM2
	 * @param endM2
	 * @param retList
	 * @param all
	 * @throws Exception
	 */
	/*private void dealResultData(SimpleDateFormat sdf, Date startM1, Date endM1, Date hbStartM1, Date hbEndM1,
			Date startM2, Date endM2, List<ResultDto> retList, List<ResultDto> all) throws Exception {
		String codeNo = null ;
		ResultDto retBean = new ResultDto() ;
		BigDecimal tb = new BigDecimal(0);
		BigDecimal hb = new BigDecimal(0);
		BigDecimal zero = new BigDecimal(0) ;
		int m = 1 ;
		for(ResultDto bean : all){
			String lastCodeNo = bean.getCode() ;
			if(codeNo == null){
				codeNo = lastCodeNo ;
			}
			else if(!codeNo.equals(lastCodeNo)){
				if(tb.compareTo(zero) != 0){
					retBean.setOnRise(getTBScal(retBean.getSumMoney(), tb));
				}
				else{
					retBean.setOnRise(null);
				}
				if(hb.compareTo(zero) != 0){
					retBean.setLinkRise(getHBScal(retBean.getSumMoney(), hb));
				}
				else{
					retBean.setLinkRise(null);
				}
				retList.add(retBean);
				retBean = new ResultDto() ;
				tb = new BigDecimal(0);
				hb = new BigDecimal(0);
				codeNo = lastCodeNo ;
			}
			if(compareTime(startM1,endM1,sdf, bean)){
				//获取本身查询数据
				if(StringUtils.isEmpty(retBean.getCode())){
					retBean.setCode(lastCodeNo);
					retBean.setCodeName(bean.getCodeName());
					retBean.setListArr(m);
					retBean.setDataType(bean.getDataType());
					retBean.setUseArea(bean.getUseArea());
					m ++ ;
				}
				if(null != retBean.getSumMoney() && retBean.getSumMoney().compareTo(zero) > 0 ){
					retBean.setSumMoney(retBean.getSumMoney().add(bean.getSumMoney()));
				}
				else{
					retBean.setSumMoney(bean.getSumMoney());
				}
			}
			else{
				if(null != hbStartM1 && null != hbEndM1 && compareTime(hbStartM1,hbEndM1,sdf, bean)){
					//获取环比数据
					hb = hb.add(bean.getSumMoney());
				}
				if(null != startM2 && null != endM2 && compareTime(startM2,endM2,sdf, bean)){
					//获取同比数据
					tb = tb.add(bean.getSumMoney());
				}
			}
		}
		//最后一次循环数据
		if(tb.compareTo(zero) != 0){
			retBean.setOnRise(getTBScal(retBean.getSumMoney(), tb));
		}
		else{
			retBean.setOnRise(null);
		}
		if(hb.compareTo(zero) != 0){
			retBean.setLinkRise(getHBScal(retBean.getSumMoney(), hb));
		}
		else{
			retBean.setLinkRise(null);
		}
		retList.add(retBean);
	}*/

	/**
	 * 获取同比比例 --已*100   
	 * @param val
	 * @param lastVal
	 * @return
	 */
	private BigDecimal getTBScal(BigDecimal val , BigDecimal lastVal){
		return ((val.subtract(lastVal)).multiply(new BigDecimal(100)).divide(lastVal,2,RoundingMode.HALF_UP)) ;
	}

	/**
	 * 因为不了解公式所以同比环比用不同的方法
	 * 获取环比比例 --已*100   
	 * @param val  
	 * @param lastVal
	 * @return
	 */
	private BigDecimal getHBScal(BigDecimal val , BigDecimal lastVal){
		return ((val.subtract(lastVal)).multiply(new BigDecimal(100)).divide(lastVal,2,RoundingMode.HALF_UP)) ;
	}


	/**
	 * 比较年月  年份是用>=和<=   月份是>=和<=
	 * @param sy
	 * @param ey
	 * @param sm
	 * @param em
	 * @param bean
	 * @return
	 */
	private boolean compareTime(Date st , Date et,SimpleDateFormat sdf , ResultDto bean ) throws Exception{
		Date bDt = sdf.parse(bean.getYearMoth()) ;
		if(bDt.getTime() >= st.getTime() && bDt.getTime() <= et.getTime()){
			return true ;
		}
		return false ;
	}

	/**
	 * 比较年月  年份是用>=和<=   月份是>=和<=
	 * @param sy
	 * @param ey
	 * @param sm
	 * @param em
	 * @param bean
	 * @return
	 */
	private boolean compareTime(Date st , Date et,SimpleDateFormat sdf , TKmye bean ) throws Exception{
		Date bDt = sdf.parse(bean.getKjyearMoth()) ;
		if(bDt.getTime() >= st.getTime() && bDt.getTime() <= et.getTime() && bean.getBqdffse() != null){
			return true ;
		}
		return false ;
	}
	/**
	 * 预测得到总数
	 */
	public ResultDto selectExpectValueSumDetailAllTotal(Map<String,Object> map){
		ResultDto rd=new ResultDto();
		int year=(int) map.get("year");
		/*String startTime=map.get("startTime").toString();
		String endTime=map.get("endTime").toString();*/
		BigDecimal money=reportResultMapper.selectExpectValueSum(map);
		if(money==null){
			money=new BigDecimal("0");
		}
		money=money.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);
		rd.setSumMoney(money.toString());
		rd.setBigSumMoney(money);;
		rd.setYear(year);
		return rd;
	}
	@Override
	public List<ResultDto> selectResultKm(Map<String,Object> map){
		return reportResultMapper.selectResultKm(map);
	}
	@Override
	public List<ResultDto> selectResultSale(Map<String,Object> map){
		return reportResultMapper.selectResultSale(map);
	}
	@Override
	public List<ResultDto> selectResultKmByYear(Map<String,Object> map){
		List<ResultDto> rList=new ArrayList<>();
		String s="";
		String ed="";
		String eds="";
		if(map.get("startTime")!=null && map.get("startTime").toString()!=""){
			 s=map.get("startTime").toString();
			 map.put("startTime", Dates.getDecDate(map.get("startTime").toString()));
		}
		if(map.get("endTime")!=null && map.get("endTime").toString()!=""){
			ed=map.get("endTime").toString();
			map.put("endTime", Dates.getDecDate(map.get("endTime").toString()));
		}
		if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
			eds=map.get("endTimes").toString();
			map.put("endTimes", Dates.getDecDate(map.get("endTimes").toString()));
		}
		List<ResultDto> rsList=reportResultMapper.selectResultKmByYear(map);
		String startDt="";
		String endDt="";
		String endTimes="";
		String bzStartTime="";
		String bzEndTime="";
		String bzEndTimes="";
		if(map.get("startTime")!=null && map.get("startTime").toString()!=""){
			bzStartTime=s;
			if(s.length()<8){
				s=s+"-01";
			}
			startDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(s,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4),"yyyy-MM-dd");
			map.put("startTime",  Dates.getDecDate(startDt));
		}
		if(map.get("endTime")!=null && map.get("endTime").toString()!=""){
			bzEndTime=ed;
			if(ed.length()<8){
				ed=ed+"-01";
			}
			endDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(ed,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4),"yyyy-MM-dd");
			map.put("endTime",  Dates.getDecDate(endDt));
		}
		if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
			bzEndTimes=eds;
			if(eds.length()<8){
				eds=eds+"-01";
			}
			endTimes=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(eds,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4),"yyyy-MM-dd");
			map.put("endTimes",  Dates.getDecDate(endTimes));
		}
		List<ResultDto> tbrsList=reportResultMapper.selectResultKmByYear(map);
		String hbsDate="";
		String hbeDt="";
		String hbeDts="";
		if(bzStartTime!=null && bzStartTime!=""){
			if(bzStartTime.length()<8){
				bzStartTime=bzStartTime+"-01";
			}
			hbsDate=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(bzStartTime,"yyyy-MM-dd"),"yyyy-MM-dd",1*(-1),3), "yyyy-MM-dd");
			map.put("startTime", Dates.getDecDate(hbsDate));
		}
		if(bzEndTime!=null && bzEndTime!=""){
			if(bzEndTime.length()<8){
				bzEndTime=bzEndTime+"-01";
			}
			hbeDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(bzEndTime,"yyyy-MM-dd"),"yyyy-MM-dd",1*(-1),3), "yyyy-MM-dd");
			map.put("endTime", Dates.getDecDate(hbeDt));
		}
		if(bzEndTimes!=null && bzEndTimes!=""){
			if(bzEndTimes.length()<8){
				bzEndTimes=bzEndTimes+"-01";
			}
			hbeDts=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(bzEndTimes,"yyyy-MM-dd"),"yyyy-MM-dd",1*(-1),3), "yyyy-MM-dd");
			map.put("endTimes",  Dates.getDecDate(hbeDts));
		}
		List<ResultDto> hbrsList=reportResultMapper.selectResultKmByYear(map);
		if(rsList!=null && rsList.size()>0){
			for (ResultDto resultDto : rsList) {
				BigDecimal money=resultDto.getBigSumMoney();

				BigDecimal tbMoney=new BigDecimal("0.00");
				BigDecimal hbMoney=new BigDecimal("0.00");
				//int flag=1;
				String tb="-";
				//BigDecimal hb=new BigDecimal("0.00");
				String hb="-";
				if(tbrsList!=null && tbrsList.size()>0){
					if(tbrsList!=null && tbrsList.size()>0){
						for (ResultDto tbresultDto : tbrsList) {
							String str=DateUtil.dateFormatToString(DateUtil.addTime(DateUtil.stringToDate(tbresultDto.getYearMoth().substring(0, 4)+"-"+tbresultDto.getYearMoth().substring(4), "yyyy-MM"), "yyyy-MM", 1, 4), "yyyy-MM");
							str= Dates.getDecDate(str+"-01");
							String str2=resultDto.getYearMoth();
							if(str2.equals(str)){
								if(tbresultDto.getBigSumMoney()!=null){
									tbMoney=tbresultDto.getBigSumMoney();
									if((tbMoney.compareTo(BigDecimal.ZERO))!=0 && money.compareTo(BigDecimal.ZERO)!=0){
										tb=((resultDto.getBigSumMoney().subtract(tbresultDto.getBigSumMoney())).multiply(new BigDecimal("100")).divide(tbMoney.abs(),2, RoundingMode.HALF_UP)).toString();
									}else if(tbMoney.compareTo(BigDecimal.ZERO)==0 && money.compareTo(BigDecimal.ZERO)==0){
										tb="-";
									}else if(tbMoney.compareTo(BigDecimal.ZERO)==0 && money.compareTo(BigDecimal.ZERO)!=0){
										tb="-";
									}else{
										tb="-100";
									}
								}else{
									tb="-";
								}

							}
							//flag=2;
						}
					}
				}
				if(hbrsList!=null && hbrsList.size()>0){
					if(hbrsList!=null && hbrsList.size()>0){
						for (ResultDto hbresultDto : hbrsList) {
							String str=DateUtil.dateFormatToString(DateUtil.addTime(DateUtil.stringToDate(hbresultDto.getYearMoth().substring(0, 4)+"-"+hbresultDto.getYearMoth().substring(4), "yyyy-MM"), "yyyy-MM", 1,3), "yyyy-MM");
							str= Dates.getDecDate(str+"-01");
							String str2=resultDto.getYearMoth();
							if(str2.equals(str)){
								if(hbresultDto.getBigSumMoney()!=null){
									hbMoney=hbresultDto.getBigSumMoney();
									if((hbMoney.compareTo(BigDecimal.ZERO))!=0 && money.compareTo(BigDecimal.ZERO)!=0){
										hb=((resultDto.getBigSumMoney().subtract(hbresultDto.getBigSumMoney())).multiply(new BigDecimal("100")).divide(hbMoney.abs(),2, RoundingMode.HALF_UP)).toString();
									}else if(hbMoney.compareTo(BigDecimal.ZERO)==0 && money.compareTo(BigDecimal.ZERO)==0){
										hb="-";
									}else if(hbMoney.compareTo(BigDecimal.ZERO)==0 && money.compareTo(BigDecimal.ZERO)!=0){
										hb="-";
									}else{
										hb="-100";
									}
								}else{
									hb="-";
								}

							}
							//flag=2;
						}
					}
				}
				if(map.get("flag")==null){
					hbMoney=hbMoney.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);  
					tbMoney=tbMoney.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);    
					money=money.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);              //金额单位转换成万元
				}
				
				/*if(flag==1){
					tb=new BigDecimal("100.00");
				}*/
				resultDto.setTbSumMoney(tbMoney);
				resultDto.setSumMoney(money.toString());
				resultDto.setOnRise(tb.toString());
				resultDto.setHbSumMoney(hbMoney);
				resultDto.setLinkRise(hb.toString());
				resultDto.setBigSumMoney(money);
				rList.add(resultDto);
			}
		}else{
			return null;
		}
		return rList;
		//return reportResultMapper.selectResultKmByYear(map);
	}
	@Override
	public List<ResultDto> selectResultSaleByYear(Map<String,Object> map){
		List<ResultDto> rList=new ArrayList<>();
		String s="";
		String ed="";
		String eds="";
		if(map.get("startTime")!=null && map.get("startTime").toString()!=""){
			 s=map.get("startTime").toString();
			 map.put("startTime", Dates.getDecDate(map.get("startTime").toString()));
		}
		if(map.get("endTime")!=null && map.get("endTime").toString()!=""){
			ed=map.get("endTime").toString();
			map.put("endTime", Dates.getDecDate(map.get("endTime").toString()));
		}
		if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
			eds=map.get("endTimes").toString();
			map.put("endTimes", Dates.getDecDate(map.get("endTimes").toString()));
		}
		List<ResultDto> rsList=reportResultMapper.selectResultSaleByYear(map);
		String startDt="";
		String endDt="";
		String endTimes="";
		String bzStartTime="";
		String bzEndTime="";
		String bzEndTimes="";
		if(map.get("startTime")!=null && map.get("startTime").toString()!=""){
			bzStartTime=s;
			startDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(s,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4),"yyyy-MM-dd");
			map.put("startTime",  Dates.getDecDate(startDt));
		}
		if(map.get("endTime")!=null && map.get("endTime").toString()!=""){
			bzEndTime=ed;
			endDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(ed,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4),"yyyy-MM-dd");
			map.put("endTime",  Dates.getDecDate(endDt));
		}
		if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
			bzEndTimes=eds;
			endTimes=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(eds,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4),"yyyy-MM-dd");
			map.put("endTimes",  Dates.getDecDate(endTimes));
		}
		List<ResultDto> tbrsList=reportResultMapper.selectResultSaleByYear(map);
		String hbsDate="";
		String hbeDt="";
		String hbeDts="";
		if(bzStartTime!=null && bzStartTime!=""){
			hbsDate=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(bzStartTime,"yyyy-MM-dd"),"yyyy-MM-dd",1*(-1),3), "yyyy-MM-dd");
			map.put("startTime", Dates.getDecDate(hbsDate));
		}
		if(bzEndTime!=null && bzEndTime!=""){
			hbeDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(bzEndTime,"yyyy-MM-dd"),"yyyy-MM-dd",1*(-1),3), "yyyy-MM-dd");
			map.put("endTime", Dates.getDecDate(hbeDt));
		}
		if(bzEndTimes!=null && bzEndTimes!=""){
			hbeDts=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(bzEndTimes,"yyyy-MM-dd"),"yyyy-MM-dd",1*(-1),3), "yyyy-MM-dd");
			map.put("endTimes",  Dates.getDecDate(hbeDts));
		}
		List<ResultDto> hbrsList=reportResultMapper.selectResultSaleByYear(map);
		if(rsList!=null && rsList.size()>0){
			for (ResultDto resultDto : rsList) {
				BigDecimal money=resultDto.getBigSumMoney();

				BigDecimal tbMoney=new BigDecimal("0.00");
				BigDecimal hbMoney=new BigDecimal("0.00");
				//int flag=1;
				String tb="-";
				//BigDecimal hb=new BigDecimal("0.00");
				String hb="-";
				if(tbrsList!=null && tbrsList.size()>0){
					if(tbrsList!=null && tbrsList.size()>0){
						for (ResultDto tbresultDto : tbrsList) {
							String str=DateUtil.dateFormatToString(DateUtil.addTime(DateUtil.stringToDate(tbresultDto.getYearMoth().substring(0, 4)+"-"+tbresultDto.getYearMoth().substring(4), "yyyy-MM"), "yyyy-MM", 1, 4), "yyyy-MM");
							str= Dates.getDecDate(str+"-01");
							String str2=resultDto.getYearMoth();
							if(str2.equals(str)){
								if(tbresultDto.getBigSumMoney()!=null){
									tbMoney=tbresultDto.getBigSumMoney();
									if((tbMoney.compareTo(BigDecimal.ZERO))!=0 && money.compareTo(BigDecimal.ZERO)!=0){
										tb=((resultDto.getBigSumMoney().subtract(tbresultDto.getBigSumMoney())).multiply(new BigDecimal("100")).divide(tbMoney.abs(),2, RoundingMode.HALF_UP)).toString();
									}else if(tbMoney.compareTo(BigDecimal.ZERO)==0 && money.compareTo(BigDecimal.ZERO)==0){
										tb="-";
									}else if(tbMoney.compareTo(BigDecimal.ZERO)==0 && money.compareTo(BigDecimal.ZERO)!=0){
										tb="-";
									}else{
										tb="-100";
									}
								}else{
									tb="-";
								}

							}
							//flag=2;
						}
					}
				}
				if(hbrsList!=null && hbrsList.size()>0){
					if(hbrsList!=null && hbrsList.size()>0){
						for (ResultDto hbresultDto : hbrsList) {
							String str=DateUtil.dateFormatToString(DateUtil.addTime(DateUtil.stringToDate(hbresultDto.getYearMoth().substring(0, 4)+"-"+hbresultDto.getYearMoth().substring(4), "yyyy-MM"), "yyyy-MM", 1,3), "yyyy-MM");
							str= Dates.getDecDate(str+"-01");
							String str2=resultDto.getYearMoth();
							if(str2.equals(str)){
								if(hbresultDto.getBigSumMoney()!=null){
									hbMoney=hbresultDto.getBigSumMoney();
									if((hbMoney.compareTo(BigDecimal.ZERO))!=0 && money.compareTo(BigDecimal.ZERO)!=0){
										hb=((resultDto.getBigSumMoney().subtract(hbresultDto.getBigSumMoney())).multiply(new BigDecimal("100")).divide(hbMoney.abs(),2, RoundingMode.HALF_UP)).toString();
									}else if(hbMoney.compareTo(BigDecimal.ZERO)==0 && money.compareTo(BigDecimal.ZERO)==0){
										hb="-";
									}else if(hbMoney.compareTo(BigDecimal.ZERO)==0 && money.compareTo(BigDecimal.ZERO)!=0){
										hb="-";
									}else{
										hb="-100";
									}
								}else{
									hb="-";
								}

							}
							//flag=2;
						}
					}
				}
				if(map.get("flag")==null){
					hbMoney=hbMoney.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);  
					tbMoney=tbMoney.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);    
					money=money.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);              //金额单位转换成万元
				}
				/*if(flag==1){
					tb=new BigDecimal("100.00");
				}*/
				resultDto.setTbSumMoney(tbMoney);
				resultDto.setSumMoney(money.toString());
				resultDto.setOnRise(tb.toString());
				resultDto.setHbSumMoney(hbMoney);
				resultDto.setLinkRise(hb.toString());
				resultDto.setBigSumMoney(money);
				rList.add(resultDto);
			}
		}else{
			return null;
		}
		return rList;
		//return reportResultMapper.selectResultKmByYear(map);
	}
	@Override
	public List<ResultDto> selectResultKmTo5(Map<String,Object> map){
		return reportResultMapper.selectResultKmTo5(map);
	}
	@Override
	public List<ResultDto> selectResultKm2(Map<String, Object> map){
		Object startTime=map.get("startTime");
		Object endTime=map.get("endTime");
		Object endTimes=map.get("endTimes");
		String strs="";
		String stre="";
		String stres="";
		if(startTime!=null && startTime.toString().length()>6){
			strs= Dates.getDecDate(startTime.toString());
			map.put("startTime", strs);
		}else if(startTime!=null){
			map.put("startTime", startTime);
		}
		if(endTime!=null && endTime.toString().length()>6){
			stre= Dates.getDecDate(endTime.toString());
			map.put("endTime", stre);
		}else if(startTime!=null){
			map.put("endTime", endTime);
		}
		if(endTimes!=null && endTimes.toString().length()>6){
			stres= Dates.getDecDate(endTimes.toString());
			map.put("endTimes", stres);
		}else if(endTimes!=null){
			map.put("endTimes", endTimes);
		}
		List<ResultDto> rlist=new ArrayList<ResultDto>();
		List<ResultDto> list=reportResultMapper.selectResultKm(map);
		String startDt="";
		String endDt="";
		String endDt2="";
		if(startTime!=null ){
			startDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(startTime.toString(),"yyyy-MM-dd"),"yyyy-MM-dd",-1,4),"yyyy-MM-dd");
			map.put("startTime", Dates.getDecDate(startDt));
		}
		if(endTime!=null){
			endDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime.toString(),"yyyy-MM-dd"),"yyyy-MM-dd",-1,4),"yyyy-MM-dd");
			map.put("endTime", Dates.getDecDate(endDt));
		}
		if(endTimes!=null){
			endDt2=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTimes.toString(),"yyyy-MM-dd"),"yyyy-MM-dd",-1,4),"yyyy-MM-dd");
			map.put("endTimes", Dates.getDecDate(endDt2));
		}
		List<ResultDto> list2=reportResultMapper.selectResultKm(map);
		for (ResultDto resultDto : list) {
			for (ResultDto resultDto2 : list2) {
				if(resultDto.getCode()!=null && resultDto.getCode().length()>0){
					if((resultDto.getCode().equals(resultDto2.getCode())) || (resultDto.getCodeName().equals(resultDto2.getCodeName()))){
						resultDto.setTbSumMoney(resultDto2.getBigSumMoney());
						resultDto.setTbSumMoneyStr(resultDto2.getSumMoney());
					}
				}
			}
			rlist.add(resultDto);
		}
		return rlist;
	}
	@Override
	public List<ResultDto> selectResultSale2(Map<String, Object> map){
		Object startTime=map.get("startTime");
		Object endTime=map.get("endTime");
		Object endTimes=map.get("endTimes");
		String strs="";
		String stre="";
		String stres="";
		if(startTime!=null){
			strs= Dates.getDecDate(startTime.toString());
			map.put("startTime", strs);
		}
		if(endTime!=null){
			stre= Dates.getDecDate(endTime.toString());
			map.put("endTime", stre);
		}
		if(endTimes!=null){
			stres= Dates.getDecDate(endTimes.toString());
			map.put("endTimes", stres);
		}
		List<ResultDto> rlist=new ArrayList<ResultDto>();
		List<ResultDto> list=reportResultMapper.selectResultSale(map);
		String startDt="";
		String endDt="";
		String endDt2="";
		if(startTime!=null ){
			startDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(startTime.toString(),"yyyy-MM-dd"),"yyyy-MM-dd",-1,4),"yyyy-MM-dd");
			map.put("startTime", Dates.getDecDate(startDt));
		}
		if(endTime!=null){
			endDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime.toString(),"yyyy-MM-dd"),"yyyy-MM-dd",-1,4),"yyyy-MM-dd");
			map.put("endTime", Dates.getDecDate(endDt));
		}
		if(endTimes!=null){
			endDt2=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTimes.toString(),"yyyy-MM-dd"),"yyyy-MM-dd",-1,4),"yyyy-MM-dd");
			map.put("endTimes", Dates.getDecDate(endDt2));
		}
		List<ResultDto> list2=reportResultMapper.selectResultSale(map);
		for (ResultDto resultDto : list) {
			for (ResultDto resultDto2 : list2) {
				if(resultDto.getCodeName()!=null && resultDto2.getCodeName().length()>0){
					if(resultDto.getCodeName().equals(resultDto2.getCodeName())){
						resultDto.setTbSumMoney(resultDto2.getBigSumMoney());
						resultDto.setTbSumMoneyStr(resultDto2.getSumMoney());
						rlist.add(resultDto);
					}
				}
			}
		}
		return rlist;
	}
	public List<ResultDto> selectResultKmTop5Sum(Map<String,Object> map){
		return reportResultMapper.selectResultKmTop5Sum(map);
	}
	@Override
	public List<ResultDto> selectRate(Map<String,Object> map){
		return reportResultMapper.selectRate(map);
	}
	@Override
	public List<ResultDto> selectRate2(Map<String, Object> map) {
		List<ResultDto> rList=new ArrayList<>();
		int monthDiff=0;
		try {
			if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
				monthDiff=1;
			}else{
				monthDiff = Dates.getDateTimes(map.get("startTime").toString(),map.get("endTime").toString())+1;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<ResultDto> rsList=reportResultMapper.selectRate(map);
		String startDt="";
		String endDt="";
		String endTimes="";
		String bzStartTime="";
		String bzEndTime="";
		String bzEndTimes="";
		if(map.get("startTime")!=null && map.get("startTime").toString()!=""){
			bzStartTime=map.get("startTime").toString();
			startDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(map.get("startTime").toString(),"yyyy-MM-dd"),"yyyy-MM-dd",-1,4),"yyyy-MM-dd");
		}
		if(map.get("endTime")!=null && map.get("endTime").toString()!=""){
			bzEndTime=map.get("endTime").toString();
			endDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(map.get("endTime").toString(),"yyyy-MM-dd"),"yyyy-MM-dd",-1,4),"yyyy-MM-dd");
		}
		if(map.get("endTimes")!=null && map.get("endTimes").toString()!=""){
			bzEndTimes=map.get("endTimes").toString();
			endTimes=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(map.get("endTimes").toString(),"yyyy-MM-dd"),"yyyy-MM-dd",-1,4),"yyyy-MM-dd");
		}
		map.put("startTime", startDt);
		map.put("endTime", endDt);
		map.put("endTimes", endTimes);
		List<ResultDto> tbrsList=reportResultMapper.selectRate(map);
		String hbsDate="";
		String hbeDt="";
		String hbeDts="";
		if(bzStartTime!=null && bzStartTime!=""){
			hbsDate=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(bzStartTime,"yyyy-MM-dd"),"yyyy-MM-dd",1*(-1),3), "yyyy-MM-dd");
		}
		if(bzEndTime!=null && bzEndTime!=""){
			hbeDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(bzEndTime,"yyyy-MM-dd"),"yyyy-MM-dd",1*(-1),3), "yyyy-MM-dd");
		}
		if(bzEndTimes!=null && bzEndTimes!=""){
			hbeDts=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(bzEndTimes,"yyyy-MM-dd"),"yyyy-MM-dd",1*(-1),3), "yyyy-MM-dd");
		}
		map.put("startTime", hbsDate);
		map.put("endTime", hbeDt);
		map.put("endTimes", hbeDts);
		List<ResultDto> hbrsList=reportResultMapper.selectRate(map);
		if(rsList!=null && rsList.size()>0){
			for (ResultDto resultDto : rsList) {
				BigDecimal money=resultDto.getBigSumMoney();

				BigDecimal tbMoney=new BigDecimal("0.00");
				BigDecimal hbMoney=new BigDecimal("0.00");
				//int flag=1;
				String tb="-";
				//BigDecimal hb=new BigDecimal("0.00");
				String hb="-";
				if(tbrsList!=null && tbrsList.size()>0){
					if(tbrsList!=null && tbrsList.size()>0){
						for (ResultDto tbresultDto : tbrsList) {
							String str=DateUtil.dateFormatToString(DateUtil.addTime(DateUtil.stringToDate(tbresultDto.getYearMoth(), "yyyy-MM"), "yyyy-MM", 1, 4), "yyyy-MM");
							if(resultDto.getYearMoth().equals(str)){
								if(tbresultDto.getBigSumMoney()!=null){
									tbMoney=tbresultDto.getBigSumMoney();
									if((tbMoney.compareTo(BigDecimal.ZERO))!=0 && money.compareTo(BigDecimal.ZERO)!=0){
										tb=((resultDto.getBigSumMoney().subtract(tbresultDto.getBigSumMoney())).multiply(new BigDecimal("100")).divide(tbMoney.abs(),2, RoundingMode.HALF_UP)).toString();
									}else if(tbMoney.compareTo(BigDecimal.ZERO)==0 && money.compareTo(BigDecimal.ZERO)==0){
										tb="-";
									}else if(tbMoney.compareTo(BigDecimal.ZERO)==0 && money.compareTo(BigDecimal.ZERO)!=0){
										tb="-";
									}else{
										tb="-100";
									}
								}else{
									tb="-";
								}

							}
							//flag=2;
						}
					}
				}
				if(hbrsList!=null && hbrsList.size()>0){
					if(hbrsList!=null && hbrsList.size()>0){
						for (ResultDto hbresultDto : hbrsList) {
							String str=DateUtil.dateFormatToString(DateUtil.addTime(DateUtil.stringToDate(hbresultDto.getYearMoth(), "yyyy-MM"), "yyyy-MM", 1,3), "yyyy-MM");
							if(resultDto.getYearMoth().equals(str)){
								if(hbresultDto.getBigSumMoney()!=null){
									hbMoney=hbresultDto.getBigSumMoney();
									if((hbMoney.compareTo(BigDecimal.ZERO))!=0 && money.compareTo(BigDecimal.ZERO)!=0){
										hb=((resultDto.getBigSumMoney().subtract(hbresultDto.getBigSumMoney())).multiply(new BigDecimal("100")).divide(hbMoney.abs(),2, RoundingMode.HALF_UP)).toString();
									}else if(hbMoney.compareTo(BigDecimal.ZERO)==0 && money.compareTo(BigDecimal.ZERO)==0){
										hb="-";
									}else if(hbMoney.compareTo(BigDecimal.ZERO)==0 && money.compareTo(BigDecimal.ZERO)!=0){
										hb="-";
									}else{
										hb="-100";
									}
								}else{
									hb="-";
								}

							}
							//flag=2;
						}
					}
				}
				
				/*if(flag==1){
					tb=new BigDecimal("100.00");
				}*/
				resultDto.setTbSumMoney(tbMoney);
				resultDto.setSumMoney(money.toString());
				resultDto.setOnRise(tb.toString());
				resultDto.setHbSumMoney(hbMoney);
				resultDto.setLinkRise(hb.toString());
				resultDto.setBigSumMoney(money);
				rList.add(resultDto);
			}
		}else{
			return null;
		}
		return rList;
	}
	public List<ResultDto> selectValueSumByName(Map<String,Object> map){
		return reportResultMapper.selectValueSumByName(map);
	}
}

package com.qst.financial.service.impl.subject;

import com.qst.financial.dto.Result;
import com.qst.financial.dto.ResultDto;
import com.qst.financial.modle.subject.TBasiProfit;
import com.qst.financial.service.subject.*;
import com.qst.financial.sql.C;
import com.qst.financial.sql.Method;
import com.qst.financial.sql.WherePrams;
import com.qst.financial.util.Dates;
import com.qst.financial.util.LineUtile;
import com.qst.financial.util.MyCalendar;
import com.qst.financial.util.common.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.*;

@Service
public class DetailServiceImpl implements DetailService{
	@Autowired
	private ReportResultService reportResultService;
	@Autowired
	private TAgeAnalysisService tAgeAnalysisService;
	@Autowired
	private BilvService bilvService;
	@Autowired
	private TBasiProfitService tBasiProfitService;
	//取科目余额表
	@Override
	public List<Result> getKmDetail(String reportType, String clzz, String startTime, String endTime, String orgId, String name, String km1, String km2, String km3, String useArea){
		HashMap<String, Object> map=new HashMap<String, Object>();
		List<Result> list=new ArrayList<Result>();
		if(clzz!=null && clzz.length()>0) {
			String clzzs[] = clzz.split(";");
			int index = clzzs.length;
			String[] km1s = km1.split(";");
			//String[] km2s=km2.split(";");
			//String[] km3s=km3.split(";");
			int b = 0;
			//int s=0;
			//int bs=0;
			for (int i = 0; i < index; i++) {
				int bj = 0;
				String strBj = "";
				String str = clzzs[i];
				int indexStr = str.indexOf("_");
				int index2 = str.indexOf("-");
				int index3 = str.indexOf("@");
				String strIndex3 = "";
				if (index3 > 0) {
					strIndex3 = str.substring(index3 + 1);
				}
				if (index2 > 0 && indexStr > 0) {
					name = str.substring(index2 + 1, indexStr);
					if (indexStr > 0) {
						String left = str.substring(0, index2);
						map.put("left", left);
						String right = "";
						if (index3 > 0) {
							right = str.substring(indexStr + 1, index3);
						} else {
							right = str.substring(indexStr + 1);
						}
						String strs = Dates.getDecDate(startTime);
						String stre = Dates.getDecDate(endTime);

						if(right.equals("b")){                   //饼状  科目余额
							HashMap<String,Object> rmap1=new HashMap<String,Object>();
							Result r1=new Result();
							if(reportType.equals("1")){
								map.put("endTimes", stre);
							}else{
								map.put("startTime", strs);
								map.put("endTime", stre);
							}
							map.put("orgId", orgId);
							km1=km1s[b];
							List<ResultDto> resultLis3=new ArrayList<ResultDto>();
							List<ResultDto> resultList=new ArrayList<ResultDto>();
							//if(left.equals("km01")){
							if(km1!=null && km1.length()>0){
								String km[]=km1.split(",");
								List<String> lists=new ArrayList<String>();
								int leght=km.length;
								for(int is=0;is<leght;is++){
									List<ResultDto> resultLis2=new ArrayList<ResultDto>();
									int indexs=km[is].indexOf("%");
									if(indexs>0){
										String strIndex=km[is].substring(0, indexs);
										if(useArea.equals("1016")){
											List<String> lists2=new ArrayList<String>();
											lists2.add("1403");
											lists2.add("1406");
											lists2.add("1405");
											map.put("subCodeLists", lists2);
										}else{
											map.put("subjectCodes", strIndex);
										}
										resultLis2=reportResultService.selectResultKm(map);
										map.put("subCodeLists", null);
										String strMath=km[is].substring(indexs+1);
										if(resultLis2!=null && resultLis2.size()>0){
											if(strMath!=null && strMath.length()>0){
												int math=Integer.parseInt(strMath);
												int indexSize=strIndex.length()+math*2;
												int indexSize2=strIndex.length();
												for (ResultDto resultDto : resultLis2) {
													if(resultDto.getCode()!=null && resultDto.getCode().length()>0){
														String code=resultDto.getCode();
														if(code.indexOf(".0")>0){
															code=code.substring(0, code.indexOf(".0"));
														}
														int sizes=code.length();
														if(sizes>indexSize2 && sizes<=indexSize){
															if(resultDto.getBigSumMoney()!=null){
																BigDecimal bg=resultDto.getBigSumMoney();
																resultDto.setBigSumMoney(bg.divide(new BigDecimal("10000"), 2,RoundingMode.HALF_UP));
																resultDto.setSumMoney(bg.divide(new BigDecimal("10000"), 2,RoundingMode.HALF_UP).toString());
															}
															resultList.add(resultDto);
														}
													}
												}
											}else{
												for (ResultDto resultDto : resultLis2) {
													if(resultDto.getCode()!=null && resultDto.getCode().length()>0){
														if(resultDto.getBigSumMoney()!=null){
															BigDecimal bg=resultDto.getBigSumMoney();
															resultDto.setBigSumMoney(bg.divide(new BigDecimal("10000"), 2,RoundingMode.HALF_UP));
															resultDto.setSumMoney(bg.divide(new BigDecimal("10000"), 2,RoundingMode.HALF_UP).toString());
														}
														resultList.add(resultDto);
													}
												}
											}
										}
									}else{
										if(km[is]!=null && km[is].length()>1 && km[is].substring(1, 2).equals("RM")){
											bj=1;
											strBj= km[is].substring(0, 1);
											lists.add(km[is]);
										}else if(km[is]!=null && km[is].length()>1 && km[is].indexOf(".0")>0){
											strBj= km[is].substring(0, 1);
											//lists.add(km[is].substring(1));
											map.put("accountPortType", strBj);
											map.put("startTime", startTime);
											map.put("endTime", endTime);
											map.put("subCodeLists", null);
											map.put("groupBy", "1");
											resultLis3=reportResultService.selectResultKm2(map);
											map.put("groupBy", null);
										}else{
											lists.add(km[is]);
										}

									}
									map.put("subjectCodes", null);
									if(lists!=null && lists.size()>0){       //1016
										map.put("subCodeLists", lists);
										if(bj==1){
											map.put("type", strBj);
											resultLis3=reportResultService.selectResultSale(map);
										}else{
											resultLis3=reportResultService.selectResultKm(map);
										}
										map.put("type", null);

									}

								}

							}
							if(resultLis3!=null && resultLis3.size()>0){
								if(resultLis3!=null && resultLis3.size()>0){
									for (ResultDto resultDto : resultLis3) {
										if(resultDto.getBigSumMoney()!=null){
											BigDecimal bg=resultDto.getBigSumMoney();
											resultDto.setBigSumMoney(bg.divide(new BigDecimal("10000"), 2,RoundingMode.HALF_UP));
											resultDto.setSumMoney(bg.divide(new BigDecimal("10000"), 2,RoundingMode.HALF_UP).toString());
										}
										resultList.add(resultDto);
									}
								}
							}
							//}
							List<ResultDto> rList=new ArrayList<ResultDto>();
							int is=5;
							if(index3>0){
								is=Integer.parseInt(strIndex3);
							}
							int indexs=0;
							ResultDto resultDto2=new ResultDto();
							if(resultList!=null && resultList.size()>0){
								for (ResultDto resultDto : resultList) {
									if(indexs>=is){
										if(resultDto2.getBigSumMoney()!=null){
											BigDecimal sbig=resultDto2.getBigSumMoney();
											sbig=sbig.add(resultDto.getBigSumMoney());
											resultDto2.setBigSumMoney(sbig);
											resultDto2.setSumMoney(sbig.toString());
										}else{
											BigDecimal sbig=resultDto.getBigSumMoney();
											resultDto2.setBigSumMoney(sbig);
											resultDto2.setSumMoney(sbig.toString());
										}
									}else{
										rList.add(resultDto);
									}
									indexs=indexs+1;
								}
								if(indexs>=is){
									resultDto2.setCodeName("其他");
									rList.add(resultDto2);
								}
							}
							rmap1.put("title", name);
							rmap1.put("content", rList);
							//rmap1.put("yj", "11");
							r1.setType(5);
							r1.setData(rmap1);
							r1.setDw("万元");
							if(rList!=null && rList.size()>0){
								list.add(r1);
							}
							b=b+1;
							map.put("type", null);
							map.put("accountPortType", null);
							map.put("subjectCodes", null);
							map.put("subCodeLists", null);
							map.put("endTimes", null);
							map.put("endTime", null);
							map.put("startTime", null);
						}
						else if (right.equals("bb")) {   //饼状   三大表  饼状
							HashMap<String, Object> rmap1 = new HashMap<String, Object>();
							Result r1 = new Result();

							map.put("orgId", orgId);
							km1 = km1s[b];
							int is = 5;
							List<ResultDto> resultList = new ArrayList<ResultDto>();
							if (km1 != null && km1.length() > 0) {
								String km[] = km1.split(",");
								if (km[0] != null && km[0].length() > 1) {
									if (reportType.equals("1")) {
										map.put("endTimes", endTime);
									} else {
										map.put("startTime", startTime);
										map.put("endTime", endTime);
									}
									map.put("useArea", km[0]);
									resultList = reportResultService.selectValueMonthResultDto(map);
								} else if (km[0].length() == 1) {
									if (reportType.equals("1")) {
										map.put("endTimes", stre);
									} else {
										map.put("startTime", strs);
										map.put("endTime", stre);
									}
									map.put("type", km[0]);
									resultList = tAgeAnalysisService.allSumQJ(map);
									is = 1000;
								}
							}
							List<ResultDto> rList = new ArrayList<ResultDto>();
							if (index3 > 0) {
								is = Integer.parseInt(strIndex3);
							}
							int indexs = 0;
							ResultDto resultDto2 = new ResultDto();
							if (resultList != null && resultList.size() > 0) {
								for (ResultDto resultDto : resultList) {
									if (indexs >= is) {
										if (resultDto2.getBigSumMoney() != null) {
											BigDecimal sbig = resultDto2.getBigSumMoney();
											sbig = sbig.add(resultDto.getBigSumMoney());
											resultDto2.setBigSumMoney(sbig);
											resultDto2.setSumMoney(sbig.toString());
										} else {
											BigDecimal sbig = resultDto.getBigSumMoney();
											resultDto2.setBigSumMoney(sbig);
											resultDto2.setSumMoney(sbig.toString());
										}
									} else {
										rList.add(resultDto);
									}
									indexs = indexs + 1;
								}
								if (indexs >= is) {
									resultDto2.setCodeName("其他");
									rList.add(resultDto2);
								}
							}
							rmap1.put("title", name);
							rmap1.put("content", rList);
							//rmap1.put("yj", "11");
							r1.setType(5);
							r1.setData(rmap1);
							r1.setDw("万元");
							if (rList != null && rList.size() > 0) {
								list.add(r1);
							}
							b = b + 1;
							map.put("endTimes", null);
							map.put("endTime", null);
							map.put("useArea", null);
							map.put("startTime", null);
						}
					}
				}
			}
		}
		return list;
	}
	//账龄相关
	public static List<Result> ages(String reportType, String clzz, String startTime, String endTime, String orgId, String name, String km1, String km2, String km3){
		
		return null;
	}
	@Override
	//特殊的三个图形
	public List<Result> getOthenTx(String useArea, Map<String, Object> map) throws Exception{
		String startTime=map.get("startTime").toString();
		String endTime=map.get("endTime").toString();
		String orgId=map.get("orgId").toString(); 
		HashMap<String, Object> rmap=new HashMap<>();
		rmap.put("startTime", startTime);
		rmap.put("endTime", endTime);
		rmap.put("orgId", orgId);
		rmap.put("reportType", "2");
		List<ResultDto> modeValus =reportResultService.selectValueSumDetail(rmap);
		//营业利润+营业收入-营业外支出=利润总额
		//营业收入-营业成本+公允价值变动收益+投资收益+汇总收益+其他收益=营业利润                                 汇总收益目前没有了
		ResultDto yylr=new ResultDto();
		ResultDto yysr=new ResultDto();
		ResultDto yywzc=new ResultDto();
		ResultDto yywsr=new ResultDto();
		ResultDto yycb=new ResultDto();
		ResultDto gyjzbdsy=new ResultDto();
		ResultDto tzsy=new ResultDto();
		ResultDto hzsy=new ResultDto();
		ResultDto qtsy=new ResultDto();
		ResultDto lrze=new ResultDto();
		for (ResultDto resultDto : modeValus) {
			if(resultDto.getCode().equals("LR-Y")){			//营业利润
				yylr=resultDto;
			}else if(resultDto.getCode().equals("LR-A")){
				yysr=resultDto;
			}else if(resultDto.getCode().equals("LR-AB")){
				yywzc=resultDto;
			}else if(resultDto.getCode().equals("LR-F")){
				yycb=resultDto;
			}else if(resultDto.getCode().equals("LR-T")){		//公允价值变动收益
				gyjzbdsy=resultDto;
			}else if(resultDto.getCode().equals("LR-U")){			//投资收益
				tzsy=resultDto;
			}else if(resultDto.getCode().equals("LR-CC")){				//汇总收益
				hzsy=resultDto;
			}else if(resultDto.getCode().equals("LR-X")){				//其他收益
				qtsy=resultDto;
			}else if(resultDto.getCode().equals("LR-AD")){
				lrze=resultDto;
			}else if(resultDto.getCode().equals("LR-Z")){
				yywsr=resultDto;
			}
		}
		List<Result> list=new ArrayList<Result>();
		if(useArea.equals("23")){
			
			List<String> codelist=new ArrayList<String>();
			String b1=yysr.getBigSumMoney().divide(new BigDecimal("10000"),2, RoundingMode.HALF_UP).toString();
			String b2=yycb.getBigSumMoney().divide(new BigDecimal("10000"),2, RoundingMode.HALF_UP).toString();
			String b3=gyjzbdsy.getBigSumMoney().divide(new BigDecimal("10000"),2, RoundingMode.HALF_UP).toString();
			String b4=tzsy.getBigSumMoney().divide(new BigDecimal("10000"),2, RoundingMode.HALF_UP).toString();
			String b5=hzsy.getBigSumMoney().divide(new BigDecimal("10000"),2, RoundingMode.HALF_UP).toString();
			String b6=qtsy.getBigSumMoney().divide(new BigDecimal("10000"),2, RoundingMode.HALF_UP).toString();
			String b7=yylr.getBigSumMoney().divide(new BigDecimal("10000"),2, RoundingMode.HALF_UP).toString();
			String d1="100%";
			String d2="-";
			String d3="-";
			String d4="-";
			String d5="-";
			String d6="-";
			String d7="-";
			if((new BigDecimal(b1)).compareTo(BigDecimal.ZERO)!=0){
				d2=(((new BigDecimal(b2)).divide(new BigDecimal(b1),4,RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2,RoundingMode.HALF_UP)).toString()+"%";
				d3=(((new BigDecimal(b3)).divide(new BigDecimal(b1),4,RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2,RoundingMode.HALF_UP)).toString()+"%";
				d4=(((new BigDecimal(b4)).divide(new BigDecimal(b1),4,RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2,RoundingMode.HALF_UP)).toString()+"%";
				d5=(((new BigDecimal(b5)).divide(new BigDecimal(b1),4,RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2,RoundingMode.HALF_UP)).toString()+"%";
				d6=(((new BigDecimal(b6)).divide(new BigDecimal(b1),4,RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2,RoundingMode.HALF_UP)).toString()+"%";
				d7=(((new BigDecimal(b7)).divide(new BigDecimal(b1),4,RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2,RoundingMode.HALF_UP)).toString()+"%";
			}
			String[] a1={b1,"-","-","-","-","-","-"};
			String[] a2={"-",b2,"-","-","-","-","-"};
			String[] a3={"-","-",b3,"-","-","-","-"};
			String[] a4={"-","-","-",b4,"-","-","-"};
			String[] a5={"-","-","-","-",b5,"-","-"};
			String[] a6={"-","-","-","-","-",b6,"-"};
			String[] a7={"-","-","-","-","-","-",b7};
			String[] bfb={"100%","20%","20%","20%","20%","20%","20%"};
			codelist.add("营业总收入("+d1+")");
			codelist.add("-营业总成本("+d2+")");
			codelist.add("+公允价值变动收益("+d3+")");
			codelist.add("+投资收益("+d4+")");
			codelist.add("+汇总收益("+d5+")");
			codelist.add("+其他收益("+d6+")");
			codelist.add("=营业利润("+d7+")");
			String c1="0";
			BigDecimal bc2=(new BigDecimal(b1)).subtract(new BigDecimal(b2));
			String c2=bc2.toString();
			BigDecimal bc3=bc2.add(new BigDecimal(b3));
			String c3=bc3.toString();
			BigDecimal bc4=bc3.add(new BigDecimal(b4));
			String c4=bc4.toString();
			BigDecimal bc5=bc4.subtract(new BigDecimal(b5));
			String c5=bc5.toString();
			BigDecimal bc6=bc5.subtract(new BigDecimal(b6));
			String c6=bc6.toString();
			String c7="0";
			String[] total={c1,c2,c3,c4,c5,c6,c7};
			HashMap<String,Object> rmap1=new HashMap<String,Object>();
			Result r1=new Result();
			rmap1.put("title", "利润构成");
			rmap1.put("cs", codelist);
			rmap1.put("a1", a1);
			rmap1.put("a2", a2);
			rmap1.put("a3", a3);
			rmap1.put("a4", a4);
			rmap1.put("a5", a5);
			rmap1.put("a6", a6);
			rmap1.put("a7", a7);
			rmap1.put("sl", 7);
			rmap1.put("bfb", bfb);
			rmap1.put("total", total);
			r1.setType(10);
			r1.setData(rmap1);
			r1.setDw("万元");
			list.add(r1);
		}else if(useArea.equals("24")){
			
			List<ResultDto> rList=new ArrayList<ResultDto>();
			HashMap<String,Object> rmap1=new HashMap<String,Object>();
			rList.add(yysr);
			rList.add(yysr);
			rList.add(yywzc);
			Result r1=new Result();
			rmap1.put("title", "利润总额构成");
			List<String> codelist=new ArrayList<String>();
			String b1=yylr.getBigSumMoney().divide(new BigDecimal("10000"),2, RoundingMode.HALF_UP).toString();
			String b2=yywsr.getBigSumMoney().divide(new BigDecimal("10000"),2, RoundingMode.HALF_UP).toString();
			String b3=yywzc.getBigSumMoney().divide(new BigDecimal("10000"),2, RoundingMode.HALF_UP).toString();
			String b4=lrze.getBigSumMoney().divide(new BigDecimal("10000"),2, RoundingMode.HALF_UP).toString();
			String c1="0";
			BigDecimal bc2=(new BigDecimal(b1)).add(new BigDecimal(b2));
			String c2=bc2.toString();
			BigDecimal bc3=bc2.subtract(new BigDecimal(b3));
			String c3=bc3.toString();
			//BigDecimal bc4=bc3.add(new BigDecimal(b3));
			String d1="100%";
			String d2="-";
			String d3="-";
			String d4="-";
			if((new BigDecimal(b1)).compareTo(BigDecimal.ZERO)!=0){
				d2=(((new BigDecimal(b2)).divide(new BigDecimal(b1),4,RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2,RoundingMode.HALF_UP)).toString()+"%";
				d3=(((new BigDecimal(b3)).divide(new BigDecimal(b1),4,RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2,RoundingMode.HALF_UP)).toString()+"%";
				d4=(((new BigDecimal(b4)).divide(new BigDecimal(b1),4,RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2,RoundingMode.HALF_UP)).toString()+"%";
			}
			codelist.add("营业利润("+d1+")");
			codelist.add("+营业外收入("+d2+")");
			codelist.add("-营业外支出("+d3+")");
			codelist.add("=利润总额("+d4+")");
			String c4="0";
			String[] a1={b1,"-","-","-"};
			String[] a2={"-", b2, "-", "-"};
			String[] a3={ "-", "-",b3, "-"};
			String[] a4={"-", "-", "-", b4};
			String[] bfb={"100%","20%","20%","20%"};
			
			String[] total={c1,c2,c3,c4};
			rmap1.put("sl", 4);
			rmap1.put("cs", codelist);
			rmap1.put("a1", a1);
			rmap1.put("a2", a2);
			rmap1.put("a3", a3);
			rmap1.put("a4", a4);
			rmap1.put("bfb", bfb);
			rmap1.put("total", total);
			r1.setType(10);
			r1.setData(rmap1);
			r1.setDw("万元");
			list.add(r1);
		}else if(useArea.equals("10001")){
			
		}
		return list;
	}
	//营业收入需要计算的
	@Override
	public List<Result> getJisuan(String useArea, Map<String, Object> map) throws Exception{
		List<Result> list=new ArrayList<Result>();
		String endTime=map.get("endTime").toString();
		String startTime=map.get("startTime").toString();
		String orgId=map.get("orgId").toString();
		//各月毛利率比较
		HashMap<String,Object> rmap1=new HashMap<String,Object>();
		HashMap<String,Object> rmap2=new HashMap<String,Object>();
		HashMap<String,Object> rmap3=new HashMap<String,Object>();
		HashMap<String,Object> rmap4=new HashMap<String,Object>();
		HashMap<String,Object> rmap5=new HashMap<String,Object>();
		HashMap<String,Object> rmap6=new HashMap<String,Object>();
		HashMap<String,Object> rmap7=new HashMap<String,Object>();
		HashMap<String,Object> rmap8=new HashMap<String,Object>();
		Result r1=new Result();
		Date st2=DateUtil.addTime(DateUtil.stringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd", -11, 3);
		String strSt2=DateUtil.dateToString(st2,"yyyy-MM-dd");
		//String strs2=Dates.getDecDate(strSt2);
		//String stre2=Dates.getDecDate(endTime);
		map.put("startTime", strSt2);
		map.put("endTime", endTime);
		map.put("useArea", "21");
		List<ResultDto> resultList=new ArrayList<ResultDto>();
		resultList=reportResultService.selectValueMonthResultDto(map);
		map.put("startTime", strSt2);
		map.put("endTime", endTime);
		map.put("useArea", "2105");
		List<ResultDto> resultList2=new ArrayList<ResultDto>();
		resultList2=reportResultService.selectValueMonthResultDto(map);
		List<Date> dtList = new ArrayList<Date>();
		try {
			dtList = DateUtil.getDatesBetweenTwoDate(DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd", -11, 3), "yyyy-MM-dd"),endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<ResultDto> zcList= LineUtile.resultLine4(dtList, resultList,resultList2);
		r1.setType(2);
		rmap1.put("title", "各月毛利率比较");
		rmap1.put("content", zcList);           //zcList
		r1.setData(rmap1);
		r1.setDw("%");
		if(zcList!=null &&zcList.size()>0){
			list.add(r1);
		}
		/*Map<String,Object> map21=new HashMap<String,Object>();
		map21.put("orgId", orgId);
		map21.put("startTime", strSt2);
		map21.put("endTime", endTime);
		map21.put("left", "km21");
		List<ResultDto> rktList=reportResultService.selectResultSaleByYear(map21);
		List<ResultDto> zcList2=LineUtile.resultLine3(dtList, resultList2,rktList);
		Result r2=new Result();
		r2.setType(2);
		rmap2.put("title", "个月单位成本变动");
		rmap2.put("content", zcList2);           //zcList
		r2.setDate(rmap2);
		r2.setDw("万元");
		if(zcList!=null &&zcList.size()>0){
			list.add(r2);
		}*/
		Map<String,Object> map31=new HashMap<String,Object>();
		map31.put("orgId", orgId);
		map31.put("startTime", startTime);
		map31.put("endTime", endTime);
		map31.put("isNulls", "1");
		map31.put("bj", "1");
		map31.put("accountPortType", "4");
		map31.put("left", "km07");
		List<ResultDto> fztList=reportResultService.selectResultKmByYear(map31);
		Map<String,Object> map32=new HashMap<String,Object>();
		map32.put("isNulls", null);
		map32.put("bj", "0");
		map32.put("accountPortType", null);
		map32.put("startTime", startTime);
		map32.put("endTime", endTime);
		map32.put("subjectCode", "6401");
		map32.put("left", "km07");
		map32.put("orgId", orgId);
		List<ResultDto> kmyycbList=reportResultService.selectResultKmByYear(map32);
		List<ResultDto> kmList=new ArrayList<ResultDto>();
		if(kmyycbList!=null && kmyycbList.size()>0){
			if(fztList!=null && fztList.size()>0){
				for(ResultDto fz: fztList){
					ResultDto rz=new ResultDto();
					for(ResultDto km :kmyycbList){
						if(fz.getCodeName().equals(km.getCodeName())){
							rz.setCodeName(fz.getCodeName());
							BigDecimal s1=fz.getBigSumMoney();
							BigDecimal d1=km.getBigSumMoney();
							if(d1!=null & s1!=null && s1.compareTo(BigDecimal.ZERO)!=0){
								BigDecimal total=((s1.subtract(d1)).multiply(new BigDecimal("100"))).divide(s1, 2, RoundingMode.HALF_UP);
								rz.setBigSumMoney(total);
								rz.setSumMoney(total.toString());
							}else{
								rz.setSumMoney("-");
								rz.setBigSumMoney(new BigDecimal("0"));
							}
							BigDecimal ts1=fz.getTbSumMoney();
							BigDecimal td1=km.getTbSumMoney();
							if(td1!=null & ts1!=null && ts1.compareTo(BigDecimal.ZERO)!=0){
								BigDecimal total=((ts1.subtract(td1)).multiply(new BigDecimal("100"))).divide(ts1, 2, RoundingMode.HALF_UP);
								rz.setTbSumMoney(total);
								rz.setTbSumMoneyStr(total.toString());
							}else{
								rz.setTbSumMoneyStr("-");
								rz.setTbSumMoney(new BigDecimal("0"));
							}
							kmList.add(rz);
						}
					}
				}
			}
		}/*else{
			kmList.addAll(fztList);
		}*/
		int indexs=0;
		List<ResultDto> rList=new ArrayList<ResultDto>();
		ResultDto resultDto2=new ResultDto();
		if(kmList!=null ){
			for (ResultDto resultDto : kmList) {
				if(indexs>=10){
					if(resultDto2.getBigSumMoney()!=null){
						BigDecimal sbig=resultDto2.getBigSumMoney();
						sbig=sbig.add(resultDto.getBigSumMoney());
						resultDto2.setBigSumMoney(sbig);
						resultDto2.setSumMoney(sbig.toString());
					}else{
						BigDecimal sbig=resultDto.getBigSumMoney();
						resultDto2.setBigSumMoney(sbig);
						resultDto2.setSumMoney(sbig.toString());
					}
				}else{
					rList.add(resultDto);
				}
				indexs=indexs+1;
			}
			if(indexs>=10){
				resultDto2.setCodeName("其他");
				rList.add(resultDto2);
			}
		}
		Result r3=new Result();
		r3.setType(6);
		rmap3.put("title", "客户毛利率比较");
		//rmap3.put("yj", "11");
		rmap3.put("content", rList);           //zcList
		r3.setData(rmap2);
		r3.setDw("万元");
		if(rList!=null && rList.size()>0){
			list.add(r3);
		}
		
		
		Map<String,Object> map51=new HashMap<String,Object>();
		map51.put("orgId", orgId);
		map51.put("startTime", strSt2);
		map51.put("endTime", endTime);
		map51.put("useArea", "21");
		List<ResultDto> resultList51=reportResultService.selectValueMonthResultDto(map51);
		
		/*Map<String,Object> map52=new HashMap<String,Object>();
		map52.put("orgId", orgId); 
		map52.put("startTime", strSt2);
		map52.put("endTime", endTime);
		map52.put("left", "km23");
		map52.put("type", "1");
		List<ResultDto> rktList52=reportResultService.selectResultSaleByYear(map52);
		if(resultList51!=null && resultList51.size()>0){
			for (ResultDto rt1 : resultList51) {
				for (ResultDto rt2 : rktList52) {
					String strs=rt2.getYearMoth().substring(0, 4)+"-"+rt2.getYearMoth().substring(4);
					if(rt1.getYearMoth().equals(strs)){
						BigDecimal b2=rt2.getBigSumMoney();
						BigDecimal b1=rt1.getBigSumMoney();
						BigDecimal tb2=rt2.getTbSumMoney();
						BigDecimal hb2=rt2.getHbSumMoney();
						BigDecimal tb1=rt1.getTbSumMoney();
						BigDecimal hb1=rt1.getHbSumMoney();
						BigDecimal bs=new BigDecimal("0");
						BigDecimal tbs=new BigDecimal("0");
						BigDecimal hbs=new BigDecimal("0");
						if(b2!=null && b2.compareTo(BigDecimal.ZERO)!=0 && b1!=null){
							bs=b1.divide(b2,2,RoundingMode.HALF_UP);
						}
						if(tb2!=null && tb2.compareTo(BigDecimal.ZERO)!=0 && tb1!=null){
							tbs=tb1.divide(tb2,2,RoundingMode.HALF_UP);
						}
						if(hb2!=null && hb2.compareTo(BigDecimal.ZERO)!=0 && hb1!=null){
							hbs=hb1.divide(hb2,2,RoundingMode.HALF_UP);
						}
						rt1.setBigSumMoney(bs);
						rt1.setSumMoney(bs+"");
						if(tbs.compareTo(BigDecimal.ZERO)!=0){
							rt1.setTbSumMoney(((bs.subtract(tbs)).divide(tbs,4,RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP));
						}
						if(hbs.compareTo(BigDecimal.ZERO)!=0){
							rt1.setTbSumMoney(((bs.subtract(hbs)).divide(hbs,4,RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP));
						}
						rt1.setTbSumMoney(tbs);
						rt1.setHbSumMoney(hbs);
					}
				}
			}
		}
		List<ResultDto> zcList5=LineUtile.resultLine(dtList, resultList51);
		Result r5=new Result();
		r5.setType(2);
		rmap5.put("title", "各月销售单价变动");
		rmap5.put("content", zcList5);           //zcList
		r5.setDate(rmap5);
		r5.setDw("万元");
		list.add(r5);*/
		
		//产成品各月入库和销售单价变动
		/*List<ResultDto> rlist=new ArrayList<ResultDto>();
		for (ResultDto rt2 : rktList52) {
			for (ResultDto rt1 : rktList42) {
				if(rt2.getYearMoth().equals(rt1.getYearMoth())){
					ResultDto r=new ResultDto();
					BigDecimal b2=rt2.getBigSumMoney();
					BigDecimal b1=rt1.getBigSumMoney();
					BigDecimal tb2=rt2.getTbSumMoney();
					BigDecimal hb2=rt2.getHbSumMoney();
					BigDecimal tb1=rt1.getTbSumMoney();
					BigDecimal hb1=rt1.getHbSumMoney();
					BigDecimal bs=new BigDecimal("0");
					BigDecimal tbs=new BigDecimal("0");
					BigDecimal hbs=new BigDecimal("0");
					if(b1!=null && b1.compareTo(BigDecimal.ZERO)!=0 && b2!=null){
						bs=b2.divide(b1,2,RoundingMode.HALF_UP);
					}
					if(tb1!=null && tb1.compareTo(BigDecimal.ZERO)!=0 && tb2!=null){
						tbs=tb2.divide(tb1,2,RoundingMode.HALF_UP);
					}
					if(hb1!=null && hb1.compareTo(BigDecimal.ZERO)!=0 && hb2!=null){
						hbs=hb2.divide(hb1,2,RoundingMode.HALF_UP);
					}
					r.setBigSumMoney(bs);
					r.setSumMoney(bs+"");
					if(tbs.compareTo(BigDecimal.ZERO)!=0){
						r.setTbSumMoney(((bs.subtract(tbs)).divide(tbs,4,RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP));
					}
					if(hbs.compareTo(BigDecimal.ZERO)!=0){
						r.setTbSumMoney(((bs.subtract(hbs)).divide(hbs,4,RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP));
					}
					r.setTbSumMoney(tbs);
					r.setHbSumMoney(hbs);
					r.setYear(rt2.getYear());
					r.setMoth(rt2.getMoth());
					r.setYearMoth(rt2.getYearMoth());
					rlist.add(r);
				}
			}
		}
		List<ResultDto> zcList6=LineUtile.resultLine(dtList, rlist);
		Result r6=new Result();
		r6.setType(2);
		rmap6.put("title", "产成品各月入库和销售单价变动");
		rmap6.put("content", zcList6);           //zcList
		r6.setDate(rmap6);
		r6.setDw("万元");
		list.add(r6);*/
		
		//原材料各月入库单价变动
		/*Map<String,Object> map62=new HashMap<String,Object>();
		map62.put("orgId", orgId); 
		map62.put("startTime", strSt2);
		map62.put("endTime", endTime);
		map62.put("left", "km23");
		map62.put("type", "2");
		List<ResultDto> rktList62=reportResultService.selectResultSaleByYear(map62);
		Map<String,Object> map61=new HashMap<String,Object>();
		map61.put("orgId", orgId); 
		map61.put("startTime", strSt2);
		map61.put("endTime", endTime);
		map61.put("left", "km21");
		map61.put("type", "2");
		List<ResultDto> rktList61=reportResultService.selectResultSaleByYear(map61);
		List<ResultDto> rlist2=new ArrayList<ResultDto>();
		for (ResultDto rt2 : rktList62) {
			for (ResultDto rt1 : rktList61) {
				if(rt2.getYearMoth().equals(rt1.getYearMoth())){
					ResultDto r=new ResultDto();
					BigDecimal b2=rt2.getBigSumMoney();
					BigDecimal b1=rt1.getBigSumMoney();
					BigDecimal tb2=rt2.getTbSumMoney();
					BigDecimal hb2=rt2.getHbSumMoney();
					BigDecimal tb1=rt1.getTbSumMoney();
					BigDecimal hb1=rt1.getHbSumMoney();
					BigDecimal bs=new BigDecimal("0");
					BigDecimal tbs=new BigDecimal("0");
					BigDecimal hbs=new BigDecimal("0");
					if(b1!=null && b1.compareTo(BigDecimal.ZERO)!=0 && b2!=null){
						bs=b2.divide(b1,2,RoundingMode.HALF_UP);
					}
					if(tb1!=null && tb1.compareTo(BigDecimal.ZERO)!=0 && tb2!=null){
						tbs=tb2.divide(tb1,2,RoundingMode.HALF_UP);
					}
					if(hb1!=null && hb1.compareTo(BigDecimal.ZERO)!=0 && hb2!=null){
						hbs=hb2.divide(hb1,2,RoundingMode.HALF_UP);
					}
					r.setBigSumMoney(bs);
					r.setSumMoney(bs+"");
					if(tbs.compareTo(BigDecimal.ZERO)!=0){
						r.setTbSumMoney(((bs.subtract(tbs)).divide(tbs,4,RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP));
					}
					if(hbs.compareTo(BigDecimal.ZERO)!=0){
						r.setTbSumMoney(((bs.subtract(hbs)).divide(hbs,4,RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP));
					}
					r.setTbSumMoney(tbs);
					r.setHbSumMoney(hbs);
					r.setYear(rt2.getYear());
					r.setMoth(rt2.getMoth());
					r.setYearMoth(rt2.getYearMoth());
					rlist2.add(r);
				}
			}
		}
		List<ResultDto> zcList7=LineUtile.resultLine(dtList, rlist2);
		Result r7=new Result();
		r7.setType(2);
		rmap7.put("title", "原材料各月入库单价变动");
		rmap7.put("content", zcList7);           //zcList
		r7.setDate(rmap7);
		r7.setDw("万元");
		list.add(r7);*/
		
		/*各月长短期借款与利息支出对比取数方式：“长短期借款”取自“科目余额表”中每月“长期借款”与“短期借款”的期末贷方余额两数合计计算得出
		/“利息支出”取自“科目余额表”中“财务费用”下级科目“利息支出”中的本期借方发生额*/
		/*Result r8=new Result();
		Map<String,Object> map81=new HashMap<String,Object>();
		map81.put("orgId", orgId); 
		map81.put("startTime", strSt2);
		map81.put("endTime", endTime);
		map81.put("left", "km08");
		List<String> pList=new ArrayList<>();
		pList.add("2501");
		pList.add("2001");
		map81.put("subCodeLists", pList);
		List<ResultDto> zc1=reportResultService.selectResultKmByYear(map81);
		Map<String,Object> map82=new HashMap<String,Object>();
		map82.put("orgId", orgId); 
		map82.put("startTime", strSt2);
		map82.put("endTime", endTime);
		map82.put("left", "km05");
		map82.put("subjectCode", "660302");
		List<ResultDto> zc2=reportResultService.selectResultKmByYear(map82);
		List<ResultDto> zcList81=LineUtile.resultLine(dtList, zc1);
		List<ResultDto> zcList82=LineUtile.resultLine(dtList, zc2);
		Map<String,Object> name1Map=new HashMap<String,Object>();
		Map<String,Object> name2Map=new HashMap<String,Object>();
		List<String> dtList2 = new ArrayList<String>();
		List<BigDecimal> list1=new ArrayList<BigDecimal>();
		List<BigDecimal> list2=new ArrayList<BigDecimal>();
		for(ResultDto rs : zcList81){
			list1.add(rs.getBigSumMoney());
		}
		for(ResultDto rs2 : zcList82){
			list2.add(rs2.getBigSumMoney());
		}
		for(int i1=0;i1<12;i1++){
			dtList2.add(DateUtil.dateToString(dtList.get(i1), "yyyy-MM"));
		}
		name1Map.put("type", "line");
		name1Map.put("name","长短期借款");
		name1Map.put("data",list1);         //2501+2001
		name2Map.put("type", "line");
		name2Map.put("name","利息支出");       //660302
		name2Map.put("data",list2);
		List<Map<String,Object>> allMapList=new ArrayList<Map<String,Object>>();
		allMapList.add(name1Map);
		allMapList.add(name2Map);
		rmap8.put("title", "各月长短期借款与利息支出对比");
		rmap8.put("date", dtList2);
		rmap8.put("content", allMapList);    
		r8.setType(9);
		r8.setDate(rmap8);
		r8.setDw("万元");
		list.add(r8);*/
		return list;
	}
	@Override
	public List<Result> getYycb(Map<String, Object> map) throws Exception{
		List<Result> list=new ArrayList<Result>();
		String endTime=map.get("endTime").toString();
		String startTime=map.get("startTime").toString();
		String orgId=map.get("orgId").toString();
		Date st2=DateUtil.addTime(DateUtil.stringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd", -11, 3);
		String strSt2=DateUtil.dateToString(st2,"yyyy-MM-dd");
		List<Date> dtList = new ArrayList<Date>();
		try {
			dtList = DateUtil.getDatesBetweenTwoDate(DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd", -11, 3), "yyyy-MM-dd"),endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//各月毛利率比较
		HashMap<String,Object> rmap4=new HashMap<String,Object>();
		List<ResultDto> rList4=new ArrayList<ResultDto>();
		Map<String,Object> map41=new HashMap<String,Object>();
		map41.put("orgId", orgId);
		map41.put("startTime", strSt2);
		map41.put("endTime", endTime);
		map41.put("useArea", "2105");
		List<ResultDto> resultList41=reportResultService.selectValueMonthResultDto(map41);
		
		Map<String,Object> map42=new HashMap<String,Object>();
		map42.put("orgId", orgId); 
		map42.put("startTime", strSt2);
		map42.put("endTime", endTime);
		map42.put("left", "km23");        //km21
		map42.put("type", "1");
		map42.put("spmcLike", "小计");
		List<ResultDto> rktList42=reportResultService.selectResultSaleByYear(map42);
		if(resultList41!=null && resultList41.size()>0){
			for (ResultDto rt1 : resultList41) {
				if(rktList42!=null && rktList42.size()>0){
					for (ResultDto rt2 : rktList42) {
						String strs=rt2.getYearMoth().substring(0, 4)+"-"+rt2.getYearMoth().substring(4);
						if(rt1.getYearMoth().equals(strs)){
							if(rt2.getBigSumMoney()!=null && rt2.getBigSumMoney().compareTo(BigDecimal.ZERO)!=0){
								BigDecimal b2=rt2.getBigSumMoney();
								BigDecimal b1=rt1.getBigSumMoney();
								BigDecimal tb2=rt2.getTbSumMoney();
								BigDecimal hb2=rt2.getHbSumMoney();
								BigDecimal tb1=rt1.getTbSumMoney();
								BigDecimal hb1=rt1.getHbSumMoney();
								BigDecimal bs=new BigDecimal("0");
								BigDecimal tbs=new BigDecimal("0");
								BigDecimal hbs=new BigDecimal("0");
								if(b2!=null && b2.compareTo(BigDecimal.ZERO)!=0 && b1!=null){
									bs=b1.divide(b2,2,RoundingMode.HALF_UP);
								}
								if(tb2!=null && tb2.compareTo(BigDecimal.ZERO)!=0 && tb1!=null){
									tbs=tb1.divide(tb2,2,RoundingMode.HALF_UP);
								}
								if(hb2!=null && hb2.compareTo(BigDecimal.ZERO)!=0 && hb1!=null){
									hbs=hb1.divide(hb2,2,RoundingMode.HALF_UP);
								}
								rt1.setBigSumMoney(bs);
								rt1.setSumMoney(bs+"");
								if(tbs.compareTo(BigDecimal.ZERO)!=0){
									rt1.setOnRise(((bs.subtract(tbs)).divide(tbs,4,RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP).toString());
								}
								if(hbs.compareTo(BigDecimal.ZERO)!=0){
									rt1.setLinkRise(((bs.subtract(hbs)).divide(hbs,4,RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP).toString());
								}
								rt1.setTbSumMoney(tbs);
								rt1.setHbSumMoney(hbs);
							}
						}
					}
				
				}
			}
		}
		List<ResultDto> zcList4= LineUtile.resultLine(dtList, resultList41);
		Result r4=new Result();
		r4.setType(2);
		rmap4.put("title", "各月单位成本变动");
		//rmap4.put("yj", "11");
		rmap4.put("content", zcList4);           //zcList
		r4.setData(rmap4);
		r4.setDw("元");
		list.add(r4);
		return list;
	}
	@Override
	public List<Result> getCdqi(String useArea, Map<String, Object> map) throws Exception{
		Result r8=new Result();
		List<Result> list=new ArrayList<Result>();
		String endTime=map.get("endTime").toString();
		String startTime=map.get("startTime").toString();
		String orgId=map.get("orgId").toString();
		//各月毛利率比较
		HashMap<String,Object> rmap8=new HashMap<String,Object>();
		List<Date> dtList = new ArrayList<Date>();
		try {
			dtList = DateUtil.getDatesBetweenTwoDate(DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd", -11, 3), "yyyy-MM-dd"),endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Result r1=new Result();
		Date st2=DateUtil.addTime(DateUtil.stringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd", -11, 3);
		String strSt2=DateUtil.dateToString(st2,"yyyy-MM-dd");
		Map<String,Object> map81=new HashMap<String,Object>();
		map81.put("orgId", orgId); 
		map81.put("startTime", strSt2);
		map81.put("endTime", endTime);
		map81.put("left", "km08");
		List<String> pList=new ArrayList<>();
		pList.add("2501");
		pList.add("2001");
		map81.put("subCodeLists", pList);
		List<ResultDto> zc1=reportResultService.selectResultKmByYear(map81);
		Map<String,Object> map82=new HashMap<String,Object>();
		map82.put("orgId", orgId); 
		map82.put("startTime", strSt2);
		map82.put("endTime", endTime);
		map82.put("left", "km03");
		map82.put("subjectCode", "660302");
		List<ResultDto> zc2=reportResultService.selectResultKmByYear(map82);
		List<ResultDto> zcList81= LineUtile.resultLine(dtList, zc1);
		List<ResultDto> zcList82= LineUtile.resultLine(dtList, zc2);
		Map<String,Object> name1Map=new HashMap<String,Object>();
		Map<String,Object> name2Map=new HashMap<String,Object>();
		List<String> dtList2 = new ArrayList<String>();
		List<BigDecimal> list1=new ArrayList<BigDecimal>();
		List<BigDecimal> list2=new ArrayList<BigDecimal>();
		for(ResultDto rs : zcList81){
			list1.add(rs.getBigSumMoney());
		}
		for(ResultDto rs2 : zcList82){
			list2.add(rs2.getBigSumMoney());
		}
		for(int i1=0;i1<12;i1++){
			dtList2.add(DateUtil.dateToString(dtList.get(i1), "yyyy-MM"));
		}
		name1Map.put("type", "line");
		name1Map.put("name","长短期借款");
		name1Map.put("data",list1);         //2501+2001
		name2Map.put("type", "line");
		name2Map.put("name","利息支出");       //660302
		name2Map.put("data",list2);
		List<Map<String,Object>> allMapList=new ArrayList<Map<String,Object>>();
		allMapList.add(name1Map);
		allMapList.add(name2Map);
		rmap8.put("title", "各月长短期借款与利息支出对比（单位：万元）");
		rmap8.put("date", dtList2);
		rmap8.put("content", allMapList);    
		//rmap8.put("yj", "11");
		r8.setType(9);
		r8.setData(rmap8);
		r8.setDw("万元");
		list.add(r8);
		return list;
	}
	@Override
	public List<Result> Cd(Map<String, Object> map){
		String endTime=map.get("endTime").toString();
		//String startTime=map.get("startTime").toString();
		String st=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd", -11, 3), "yyyy-MM-dd");
		map.put("startTime", st);
		List<Result> list=new ArrayList<Result>();
		HashMap<String,Object> rmap1=new HashMap<String,Object>();
		Result r1=new Result();
		List<ResultDto> resultList=new ArrayList<ResultDto>();
		resultList=reportResultService.selectRate2(map);
		List<Date> dtList = new ArrayList<Date>();
		try {
			dtList = DateUtil.getDatesBetweenTwoDate(DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd", -11, 3), "yyyy-MM-dd"),endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<ResultDto> zcList= LineUtile.resultLine(dtList, resultList);
		r1.setType(2);
		rmap1.put("title", "1年内贷款利率变动走势");
		rmap1.put("content", zcList);           //zcList
		//rmap1.put("yj", "11");
		r1.setData(rmap1);
		r1.setDw("%");
		if(zcList!=null &&zcList.size()>0){
			list.add(r1);
		}
		return list;
	}
	@Override
	public List<Result> Cd2(Map<String, Object> map) throws Exception{
		Result r8=new Result();
		List<Result> list=new ArrayList<Result>();
		String endTime=map.get("endTime").toString();
		//各月毛利率比较
		HashMap<String,Object> rmap8=new HashMap<String,Object>();
		List<Date> dtList = new ArrayList<Date>();
		try {
			dtList = DateUtil.getDatesBetweenTwoDate(DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd", -11, 3), "yyyy-MM-dd"),endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date st2=DateUtil.addTime(DateUtil.stringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd", -11, 3);
		String strSt2=DateUtil.dateToString(st2,"yyyy-MM-dd");
		map.put("startTime", strSt2);
		map.put("type",1);
		List<ResultDto> zc1=reportResultService.selectRate(map);
		map.put("startTime", strSt2);
		map.put("endTime", endTime);
		map.put("type",2);
		List<ResultDto> zc2=reportResultService.selectRate(map);
		List<ResultDto> zcList81= LineUtile.resultLine(dtList, zc1);
		List<ResultDto> zcList82= LineUtile.resultLine(dtList, zc2);
		Map<String,Object> name1Map=new HashMap<String,Object>();
		Map<String,Object> name2Map=new HashMap<String,Object>();
		List<String> dtList2 = new ArrayList<String>();
		List<BigDecimal> list1=new ArrayList<BigDecimal>();
		List<BigDecimal> list2=new ArrayList<BigDecimal>();
		for(ResultDto rs : zcList81){
			list1.add(rs.getBigSumMoney());
		}
		for(ResultDto rs2 : zcList82){
			list2.add(rs2.getBigSumMoney());
		}
		for(int i1=0;i1<12;i1++){
			dtList2.add(DateUtil.dateToString(dtList.get(i1), "yyyy-MM"));
		}
		name1Map.put("type", "line");
		name1Map.put("name","1至5年的贷款利率");
		name1Map.put("data",list1);         //2501+2001
		name2Map.put("type", "line");
		name2Map.put("name","5年以上的贷款利率");       //660302
		name2Map.put("data",list2);
		List<Map<String,Object>> allMapList=new ArrayList<Map<String,Object>>();
		allMapList.add(name1Map);
		allMapList.add(name2Map);
		rmap8.put("title", "贷款利率变动走势");
		rmap8.put("date", dtList2);
		rmap8.put("content", allMapList);    
		//rmap8.put("yj", "11");
		r8.setType(9);
		r8.setData(rmap8);
		r8.setDw("%");
		list.add(r8);
		return list;
	}
	@Override
	public List<Result> Xsdj(Map<String, Object> map) throws Exception{
		Result r1=new Result();
		HashMap<String,Object> rmap1=new HashMap<String,Object>();
		List<Result> list=new ArrayList<Result>();
		String startTime=map.get("startTime").toString();
		String endTime=map.get("endTime").toString();
		List<Date> dtList1 = new ArrayList<Date>();
		List<String> dtList2 = new ArrayList<String>();
		try {
			dtList1 = DateUtil.getDatesBetweenTwoDate(DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd", -11, 3), "yyyy-MM-dd"),endTime);
			for(int i1=0;i1<12;i1++){
				dtList2.add(DateUtil.dateToString(dtList1.get(i1), "yyyy-MM"));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date st2=DateUtil.addTime(DateUtil.stringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd", -11, 3);
		String strSt2=DateUtil.dateToString(st2,"yyyy-MM-dd");
		map.put("startTime", strSt2);
		map.put("endTime", endTime);
		map.put("flag", "1");
		map.put("left", "km23");
		map.put("type", "1");
		map.put("isNull", "1");
		map.put("spmcLike", "合计");
		List<ResultDto> result1=reportResultService.selectResultSaleByYear(map);
		map.put("isNull", null);
		map.put("spmcLike", null);
		map.put("spmcLike", null);
		map.put("flag", "1");
		map.put("left", null);
		map.put("type", null);
		map.put("useArea", "21");  
		map.put("startTime", strSt2);
		map.put("endTime", endTime);
		List<ResultDto> result2=reportResultService.selectValueMonthResultDto(map);
		map.put("flag", null);
		List<ResultDto> resultList2= LineUtile.resultLine3(dtList1, result2, result1);
		r1.setType(2);
		rmap1.put("title", "各月销售单价变动");
		rmap1.put("content", resultList2);           //zcList
		//rmap1.put("yj", "11");
		r1.setData(rmap1);
		r1.setDw("元");
		if(resultList2!=null &&resultList2.size()>0){
			list.add(r1);
		}
		return list;
	}
	@Override
	public  List<Result> Hbyj(Map<String, Object> map) throws Exception{
		Result r1=new Result();
		r1.setType(9);
		List<Result> list=new ArrayList<Result>();
		Map<String, Object> rmap1=new HashMap<>();
		rmap1.put("title", "货币资金预警:单位（个月）");
		String endtimes=(String) map.get("endTime");
		String orgId=map.get("orgId").toString();
		String limit =" limit 0,1";
		WherePrams wherePrams=Method.where("orgId", C.EQ, orgId).orderBy(" KJYEAR_MOTH desc");
    	List<TBasiProfit> tBasiProfitList=tBasiProfitService.listPage(wherePrams, limit);
		String dt="";
    	if(tBasiProfitList!=null && tBasiProfitList.size()>0){
    		TBasiProfit tb=tBasiProfitList.get(0);
    		if(tb.getImpTime()!=null){
    			dt=tb.getKjyearMoth();
    		}
    	}else{
    		rmap1.put("content", null);           //zcList
			rmap1.put("yj", "根据周转期计算结果，系统中已存数据不足以支撑预测，无法计算显示相关图形进行分析，建议管理层检查数据准确性及完整性。");
			r1.setData(rmap1);
			list.add(r1);
			map.put("flag", null);
			return list;
    	}
    	if(dt.length()<7){
    		dt=dt.substring(0,4)+"-"+dt.substring(4);
    	}
    	
		WherePrams wherePrams2=Method.where("orgId", C.EQ, orgId).orderBy(" KJYEAR_MOTH asc");
    	List<TBasiProfit> tBasiProfitList2=tBasiProfitService.listPage(wherePrams2, limit);
		String dt2="";
    	if(tBasiProfitList2!=null && tBasiProfitList2.size()>0){
    		TBasiProfit tb=tBasiProfitList2.get(0);
    		if(tb.getImpTime()!=null){
    			dt2=tb.getKjyearMoth();
    		}
    	}else{
    		return null;
    	}
    	if(dt2.length()<7){
    		dt2=dt2.substring(0,4)+"-"+dt2.substring(4);
    	}
    	Date dstrs=DateUtil.stringToDate(dt,"yyyy-MM");
		//String startTime=map.get("startTime").toString();
		//String endTime=map.get("endTime").toString();
		String endTime=DateUtil.dateFormatToString(dstrs, "yyyy-MM")+"-01";
		int mathonTimes= MyCalendar.getMonthSpace(dt2+"-01", endTime);
		List<Date> dtList = new ArrayList<Date>();
		try {
			dtList = DateUtil.getDatesBetweenTwoDate(DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd", -5, 3), "yyyy-MM-dd"),endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Date> wdtList = new ArrayList<Date>();
		String endTimeResult=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd", 1, 3), "yyyy-MM-dd");
		try {
			wdtList = DateUtil.getDatesBetweenTwoDate(endTimeResult,DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTimeResult, "yyyy-MM-dd"), "yyyy-MM-dd", 5, 3), "yyyy-MM-dd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date st2=DateUtil.addTime(DateUtil.stringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd", -5, 3);
		String strSt2=DateUtil.dateToString(st2,"yyyy-MM-dd");
		Date st3=DateUtil.addTime(DateUtil.stringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd", -11, 3);
		String strSt3=DateUtil.dateToString(st3,"yyyy-MM-dd");
		map.put("startTime", endTime);
		map.put("startTime", endTime);
		map.put("left", "km07");
		List<String> lists2=new ArrayList<String>();
		lists2.add("1001");
		lists2.add("1002");
		map.put("subCodeLists", lists2);
		map.put("flag", "1");
		List<ResultDto> resultList=reportResultService.selectResultKmByYear(map);
		map.put("subCodeLists", null);
		BigDecimal a1=new BigDecimal("0");
		BigDecimal b1=new BigDecimal("0");
		if(resultList!=null && resultList.size()>0){
			a1=resultList.get(0).getBigSumMoney();
			b1=resultList.get(0).getTbSumMoney();
		}
		//应收账款周转天数         b
		ResultDto bl=bilvService.getBilvResult(strSt3, endTime, orgId, "bl5201");
		BigDecimal bmonths=bl.getBigSumMoney().divide(new BigDecimal("30"),0,RoundingMode.UP);
		int months=Integer.parseInt(bmonths.toString());
		if(mathonTimes<months){
			rmap1.put("content", null);           //zcList
			rmap1.put("yj", "根据周转期计算结果，系统中已存数据不足以支撑预测，无法计算显示相关图形进行分析，建议管理层检查数据准确性及完整性。");
			r1.setData(rmap1);
			list.add(r1);
			map.put("flag", null);
			return list;
		}
		String startDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime,"yyyy-MM-dd"),"yyyy-MM-dd",-months+1,3),"yyyy-MM-dd");
		String endDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(startDt,"yyyy-MM-dd"),"yyyy-MM-dd",5,3),"yyyy-MM-dd");
		map.put("startTime", startDt);
		map.put("endTime", endDt);
		List<Date> dtList2 = new ArrayList<Date>();
		try {
			dtList2 = DateUtil.getDatesBetweenTwoDate(startDt,endDt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		map.put("left", "km07");
		map.put("subjectCode", "1122");  
		ResultDto[] rt2=new ResultDto[12];
		List<ResultDto> resultList2=reportResultService.selectResultKmByYear(map);
		if(resultList2!=null && resultList2.size()>0){
			int flag=0;
			int bj=0;
			for(int i=0;i<dtList2.size();i++){
				for (ResultDto resultDto : resultList2) {
					String dtStr=DateUtil.dateFormatToString(dtList2.get(i), "yyyyMM");
					String rStr=resultDto.getYearMoth();
					if(dtStr.equals(rStr)){
						ResultDto rd=new ResultDto();
						rd.setBigSumMoney(resultDto.getBigSumMoney());
						rd.setSumMoney(resultDto.getSumMoney());
						rd.setTbSumMoney(resultDto.getTbSumMoney());
						rd.setYearMoth(DateUtil.dateFormatToString(dtList2.get(i), "yyyy-MM"));
						rt2[i]=rd;
						flag=flag+1;
						bj=i;
					}
					
				}
				if(bj!=i){
					ResultDto rd=new ResultDto();
					BigDecimal fb=new BigDecimal("0");
					BigDecimal tfb=new BigDecimal("0");
					for(int j=0;j<flag;j++){
						fb=fb.add(rt2[j].getBigSumMoney());
						tfb=tfb.add(rt2[j].getTbSumMoney());
					}
					String strFlag=flag+"";
					rd.setBigSumMoney(fb.divide(new BigDecimal(strFlag),2,RoundingMode.HALF_UP));
					rd.setSumMoney((fb.divide(new BigDecimal(strFlag),2,RoundingMode.HALF_UP)).toString());
					rd.setTbSumMoney(tfb.divide(new BigDecimal(strFlag),2,RoundingMode.HALF_UP));
					rd.setYearMoth(DateUtil.dateFormatToString(dtList2.get(i), "yyyy-MM"));
					rt2[i]=rd;
				}
			}
		}
		map.put("subjectCode", null);  
		map.put("left", null);
		//C
		ResultDto bl2=bilvService.getBilvResult(strSt3, endTime, orgId, "bl5203");
		BigDecimal bmonths2=bl2.getBigSumMoney().divide(new BigDecimal("30"),0,RoundingMode.UP);
		int months2=Integer.parseInt(bmonths2.toString());
		if(mathonTimes<months2){
			rmap1.put("content", null);           //zcList
			rmap1.put("yj", "根据周转期计算结果，系统中已存数据不足以支撑预测，无法计算显示相关图形进行分析，建议管理层检查数据准确性及完整性。");
			r1.setData(rmap1);
			list.add(r1);
			map.put("flag", null);
			return list;
		}
		String startDt2=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime,"yyyy-MM-dd"),"yyyy-MM-dd",-months2+1,3),"yyyy-MM-dd");
		String endDt2=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(startDt2,"yyyy-MM-dd"),"yyyy-MM-dd",5,3),"yyyy-MM-dd");
		List<Date> dtList3 = new ArrayList<Date>();
		try {
			dtList3 = DateUtil.getDatesBetweenTwoDate(startDt2,endDt2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		map.put("startTime", startDt2);
		map.put("endTime", endDt2);
		map.put("left", "km08");
		map.put("subjectCode", "2202");  
		List<ResultDto> resultList3=reportResultService.selectResultKmByYear(map);
		ResultDto[] rt3=new ResultDto[12];
		if(resultList3!=null && resultList3.size()>0){
			int flag=0;
			int bj=0;
			for(int i=0;i<dtList3.size();i++){
				for (ResultDto resultDto : resultList3) {
					String dtStr=DateUtil.dateFormatToString(dtList3.get(i), "yyyyMM");
					String rStr=resultDto.getYearMoth();
					if(dtStr.equals(rStr)){
						ResultDto rd=new ResultDto();
						rd.setBigSumMoney(resultDto.getBigSumMoney());
						rd.setSumMoney(resultDto.getSumMoney());
						rd.setTbSumMoney(resultDto.getTbSumMoney());
						rd.setYearMoth(DateUtil.dateFormatToString(dtList3.get(i), "yyyy-MM"));
						rt3[i]=rd;
						bj=i;
						flag=flag+1;
					}
				}
				if(bj!=i){
					ResultDto rd=new ResultDto();
					BigDecimal fb=new BigDecimal("0");
					BigDecimal tfb=new BigDecimal("0");
					for(int j=0;j<flag;j++){
						fb=fb.add(rt3[j].getBigSumMoney());
						tfb=tfb.add(rt3[j].getTbSumMoney());
					}
					String strFlag=flag+"";
					rd.setBigSumMoney(fb.divide(new BigDecimal(strFlag),2,RoundingMode.HALF_UP));
					rd.setSumMoney((fb.divide(new BigDecimal(strFlag),2,RoundingMode.HALF_UP)).toString());
					rd.setTbSumMoney(tfb.divide(new BigDecimal(strFlag),2,RoundingMode.HALF_UP));
					rd.setYearMoth(DateUtil.dateFormatToString(dtList3.get(i), "yyyy-MM"));
					rt3[i]=rd;
				}
			}
		}
 		map.put("subjectCode", null);  
		map.put("left", null);
		ResultDto[] rt4=new ResultDto[6];
		ResultDto[] rt5=new ResultDto[6];
		ResultDto[] rt6=new ResultDto[6];
		ResultDto[] rt7=new ResultDto[6];
		for(int i=0;i<dtList.size();i++){
			if(i==0){
				ResultDto r4=new ResultDto();    //D 5001      510103
				String st=DateUtil.dateToString(dtList.get(0),"yyyy-MM-dd");
				map.put("startTime", st);
				map.put("left", "km03");
				String et=DateUtil.dateToString(dtList.get(dtList.size()-1),"yyyy-MM-dd");
				map.put("endTime",et);
				map.put("subjectCode", "5001");  
				List<ResultDto> resultList41=reportResultService.selectResultKmByYear(map);
				map.put("subjectCode", null);  
				map.put("startTime", st);
				map.put("endTime",  et);
				map.put("subjectCodes", "5101"); 
				map.put("noSubjectCode", "5101");
				map.put("subjectNames", "折旧");
				List<ResultDto> resultList42=reportResultService.selectResultKmByYear(map);
				map.put("subjectCodes", null); 
				map.put("noSubjectCode", null);
				map.put("subjectNames", null);
				BigDecimal b=new BigDecimal("0");
				BigDecimal tb=new BigDecimal("0");
				/*if(resultList41!=null && resultList41.size()>0){
					for (ResultDto resultDto : resultList41) {
						if(resultList42!=null && resultList42.size()>0){
							for (ResultDto resultDto2: resultList42) {
								if(resultDto.getYearMoth().equals(resultDto2.getYearMoth())){
									b=b.add(resultDto.getBigSumMoney().subtract(resultDto2.getBigSumMoney()));
									tb=tb.add(resultDto.getTbSumMoney().subtract(resultDto2.getTbSumMoney()));
								}
							}
						}else{
							b=b.add(resultDto.getBigSumMoney());
							tb=tb.add(resultDto.getTbSumMoney());
						}
					}
				}*/
				if(resultList41!=null && resultList41.size()>0){
					for (ResultDto resultDto : resultList41) {
						b=b.add(resultDto.getBigSumMoney());
						tb=tb.add(resultDto.getTbSumMoney());
					}
				}
				if(resultList42!=null && resultList42.size()>0){
					for (ResultDto resultDto2: resultList42) {
						b=b.subtract(resultDto2.getBigSumMoney());
						tb=tb.subtract(resultDto2.getTbSumMoney());
					}
				}
				r4.setBigSumMoney(b.divide(new BigDecimal("6"),2,RoundingMode.HALF_UP));
				r4.setTbSumMoney(tb.divide(new BigDecimal("6"),2,RoundingMode.HALF_UP));
				rt4[i]=r4;
				map.put("subjectCode", null); 
				map.put("left", null); 
				map.put("startTime", null);
				map.put("endTime",  null);
				
				ResultDto r5=new ResultDto();      //E
				map.put("startTime", st);
				map.put("left", "km03");
				map.put("endTime", et);
				map.put("subjectCode", "6601");  
				List<ResultDto> resultList51=reportResultService.selectResultKmByYear(map);
				map.put("subjectCode", null);  
				map.put("startTime", st);
				map.put("endTime",  et);
				map.put("subjectCodes", "6601"); 
				map.put("noSubjectCode", "6601");
				map.put("subjectNames", "折旧"); 
				List<ResultDto> resultList52=reportResultService.selectResultKmByYear(map);
				map.put("subjectCodes", null); 
				map.put("noSubjectCode", null);
				map.put("subjectNames", null);
				BigDecimal b5=new BigDecimal("0");
				BigDecimal tb5=new BigDecimal("0");
				/*if(resultList51!=null && resultList51.size()>0){
					for (ResultDto resultDto : resultList51) {
						if(resultList52!=null && resultList52.size()>0){
							for (ResultDto resultDto2: resultList52) {
								if(resultDto.getYearMoth().equals(resultDto2.getYearMoth())){
									b5=b5.add(resultDto.getBigSumMoney().subtract(resultDto2.getBigSumMoney()));
									tb5=tb5.add(resultDto.getTbSumMoney().subtract(resultDto2.getTbSumMoney()));
								}
							}
						}else{
							b5=b5.add(resultDto.getBigSumMoney());
							tb5=tb5.add(resultDto.getTbSumMoney());
						}
					}
				}*/
				if(resultList51!=null && resultList51.size()>0){
					for (ResultDto resultDto : resultList51) {
						b5=b5.add(resultDto.getBigSumMoney());
						tb5=tb5.add(resultDto.getTbSumMoney());
						
					}
				}
				if(resultList52!=null && resultList52.size()>0){
					for (ResultDto resultDto2: resultList52) {
						b5=b5.subtract(resultDto2.getBigSumMoney());
						tb5=tb5.subtract(resultDto2.getTbSumMoney());
					}
				}
				r5.setBigSumMoney(b5.divide(new BigDecimal("6"),2,RoundingMode.HALF_UP));
				r5.setTbSumMoney(tb5.divide(new BigDecimal("6"),2,RoundingMode.HALF_UP));
				rt5[i]=r5;
				map.put("subjectCode", null); 
				map.put("left", null); 
				map.put("startTime", null);
				map.put("endTime",  null);
				
				ResultDto r6=new ResultDto(); //F
				map.put("startTime", st);
				map.put("left", "km03");
				map.put("endTime",  et);
				map.put("subjectCode", "6602");  
				List<ResultDto> resultList61=reportResultService.selectResultKmByYear(map);
				map.put("subjectCode", null);  
				map.put("startTime", st);
				map.put("endTime",  et);
				map.put("subjectCodes", "6601"); 
				map.put("noSubjectCode", "6601");
				map.put("subjectNames", "折旧"); 
				List<ResultDto> resultList62=reportResultService.selectResultKmByYear(map);
				map.put("subjectCodes", null); 
				map.put("noSubjectCode", null);
				map.put("subjectNames", null); 
				BigDecimal b6=new BigDecimal("0");
				BigDecimal tb6=new BigDecimal("0");
				/*if(resultList61!=null && resultList61.size()>0){
					for (ResultDto resultDto : resultList61) {
						if(resultList62!=null && resultList62.size()>0){
							for (ResultDto resultDto2: resultList62) {
								if(resultDto.getYearMoth().equals(resultDto2.getYearMoth())){
									b6=b6.add(resultDto.getBigSumMoney().subtract(resultDto2.getBigSumMoney()));
									tb6=tb6.add(resultDto.getTbSumMoney().subtract(resultDto2.getTbSumMoney()));
								}
							}
						}else{
							b6=b6.add(resultDto.getBigSumMoney());
							tb6=tb6.add(resultDto.getTbSumMoney());
						}
					}
				}*/
				if(resultList61!=null && resultList61.size()>0){
					for (ResultDto resultDto : resultList61) {
						b6=b6.add(resultDto.getBigSumMoney());
						tb6=tb6.add(resultDto.getTbSumMoney());
						
					}
				}
				if(resultList62!=null && resultList62.size()>0){
					for (ResultDto resultDto2: resultList62) {
						b6=b6.subtract(resultDto2.getBigSumMoney());
						tb6=tb6.subtract(resultDto2.getTbSumMoney());
					}
				}
				r6.setBigSumMoney(b6.divide(new BigDecimal("6"),2,RoundingMode.HALF_UP));
				r6.setTbSumMoney(tb6.divide(new BigDecimal("6"),2,RoundingMode.HALF_UP));
				rt6[i]=r6;
				map.put("subjectCode", null); 
				map.put("left", null); 
				map.put("startTime", null);
				map.put("endTime",  null);
				
				
				ResultDto r7=new ResultDto();
				map.put("startTime", st);
				map.put("left", "km03");
				map.put("endTime",  et);
				map.put("subjectCode", "6603");  
				List<ResultDto> resultList71=reportResultService.selectResultKmByYear(map);
			
				BigDecimal b7=new BigDecimal("0");
				BigDecimal tb7=new BigDecimal("0");
				/*if(resultList71!=null && resultList71.size()>0){
					for (ResultDto resultDto : resultList71) {
						b7=b7.add(resultDto.getBigSumMoney());
						tb7=tb7.add(resultDto.getTbSumMoney());
					}
				}*/
				if(resultList71!=null && resultList71.size()>0){
					for (ResultDto resultDto : resultList71) {
						b7=b7.add(resultDto.getBigSumMoney());
						tb7=tb7.add(resultDto.getTbSumMoney());
						
					}
				}
				r7.setBigSumMoney(b7.divide(new BigDecimal("6"),2,RoundingMode.HALF_UP));
				r7.setTbSumMoney(tb7.divide(new BigDecimal("6"),2,RoundingMode.HALF_UP));
				rt7[i]=r7;
			}else{
				rt4[i]=rt4[0];
				rt5[i]=rt5[0];
				rt6[i]=rt6[0];
				rt7[i]=rt7[0];
			}
		}
		BigDecimal d=new BigDecimal("0");
		BigDecimal e=new BigDecimal("0");
		BigDecimal f=new BigDecimal("0");
		BigDecimal g=new BigDecimal("0");
		
		BigDecimal td=new BigDecimal("0");
		BigDecimal te=new BigDecimal("0");
		BigDecimal tf=new BigDecimal("0");
		BigDecimal tg=new BigDecimal("0");
		for(int i=0;i<dtList.size();i++){
			BigDecimal sd=rt4[i].getBigSumMoney();
			BigDecimal se=rt5[i].getBigSumMoney();
			BigDecimal sf=rt6[i].getBigSumMoney();
			BigDecimal sg=rt7[i].getBigSumMoney();
			d=d.add(sd);
			e=e.add(se);
			f=f.add(sf);
			g=g.add(sg);
			
			BigDecimal tsd=rt4[i].getBigSumMoney();
			BigDecimal tse=rt5[i].getBigSumMoney();
			BigDecimal tsf=rt6[i].getBigSumMoney();
			BigDecimal tsg=rt7[i].getBigSumMoney();
			td=td.add(tsd);
			te=te.add(tse);
			tf=tf.add(tsf);
			tg=tg.add(tsg);
		}
		d=d.divide(new BigDecimal("6"),2,RoundingMode.HALF_UP);
		e=e.divide(new BigDecimal("6"),2,RoundingMode.HALF_UP);
		f=f.divide(new BigDecimal("6"),2,RoundingMode.HALF_UP);
		g=g.divide(new BigDecimal("6"),2,RoundingMode.HALF_UP);
		
		td=td.divide(new BigDecimal("6"),2,RoundingMode.HALF_UP);
		te=te.divide(new BigDecimal("6"),2,RoundingMode.HALF_UP);
		tf=tf.divide(new BigDecimal("6"),2,RoundingMode.HALF_UP);
		tg=tg.divide(new BigDecimal("6"),2,RoundingMode.HALF_UP);
		map.put("subjectCode", null); 
		map.put("left", null); 
		map.put("startTime", null);
		map.put("endTime",  null);
		map.put("subjectNames", null);
		List<ResultDto> rList=new ArrayList<ResultDto>();
		List<BigDecimal> rList2=new ArrayList<BigDecimal>();
		BigDecimal[] h=new BigDecimal[6];
		BigDecimal[] th=new BigDecimal[6];
		for(int i=0;i<wdtList.size();i++){
			BigDecimal rs2=new BigDecimal("0");
			BigDecimal rs3=new BigDecimal("0");
			BigDecimal rs4=new BigDecimal("0");
			BigDecimal rs5=new BigDecimal("0");
			BigDecimal rs6=new BigDecimal("0");
			BigDecimal rs7=new BigDecimal("0");
			if(rt2[i]!=null && rt2[i].getBigSumMoney()!=null){
				rs2=rt2[i].getBigSumMoney();
			}
			if(rt3[i]!=null && rt3[i].getBigSumMoney()!=null){
				rs3=rt3[i].getBigSumMoney();
			}
			rs4=d;
			rs5=e;
			rs6=f;
			rs7=g;
			BigDecimal trs2=new BigDecimal("0");
			BigDecimal trs3=new BigDecimal("0");
			BigDecimal trs4=new BigDecimal("0");
			BigDecimal trs5=new BigDecimal("0");
			BigDecimal trs6=new BigDecimal("0");
			BigDecimal trs7=new BigDecimal("0");
			if(rt2[i]!=null && rt2[i].getBigSumMoney()!=null){
				trs2=rt2[i].getTbSumMoney();
			}
			if(rt3[i]!=null && rt3[i].getBigSumMoney()!=null){
				trs3=rt3[i].getTbSumMoney();
			}
			if(rt4[i]!=null && rt4[i].getBigSumMoney()!=null){
				trs4=rt4[i].getTbSumMoney();
			}
			if(rt5[i]!=null && rt5[i].getBigSumMoney()!=null){
				trs5=rt5[i].getTbSumMoney();
			}
			if(rt6[i]!=null && rt6[i].getBigSumMoney()!=null){
				trs6=rt6[i].getTbSumMoney();
			}
			if(rt7[i]!=null && rt7[i].getBigSumMoney()!=null){
				trs7=rt7[i].getTbSumMoney();
			}
			ResultDto result=new ResultDto();
			if(i==0){
				h[i]=a1.add(rs2).subtract(rs3).subtract(rs4).subtract(rs5).subtract(rs6).subtract(rs7);
				th[i]=b1.add(trs2).subtract(trs3).subtract(trs4).subtract(trs5).subtract(trs6).subtract(trs7);
			}else{
				h[i]=h[i-1].add(rs2).subtract(rs3).subtract(rs4).subtract(rs5).subtract(rs6).subtract(rs7);
				th[i]=th[i-1].add(trs2).subtract(trs3).subtract(trs4).subtract(trs5).subtract(trs6).subtract(trs7);
			}
			BigDecimal bc=rs4.add(rs5).add(rs6).add(rs7);
			BigDecimal tbc=trs4.add(trs5).add(trs6).add(trs7);
			BigDecimal ri=new BigDecimal("0");
			BigDecimal tri=new BigDecimal("0");
			if(bc.compareTo(BigDecimal.ZERO)!=0){
				ri=h[i].multiply(new BigDecimal("0.8")).divide(bc,2,RoundingMode.HALF_UP);
			}
			if(tbc.compareTo(BigDecimal.ZERO)!=0){
				tri=th[i].multiply(new BigDecimal("0.8")).divide(tbc,2,RoundingMode.HALF_UP);
			}
			result.setYearMoth(DateUtil.dateFormatToString(wdtList.get(i), "yyyy-MM"));
			result.setSumMoney(ri.toString());
			result.setBigSumMoney(ri);
			result.setTbSumMoney(tri);
			result.setLinkRise("-");
			result.setOnRise("-");
			rList.add(result);
			rList2.add(ri);
		}
		//rmap1.put("content", rList);           //zcList
		Map<String,Object> name1Map=new HashMap<String,Object>();
		Map<String,Object> name2Map=new HashMap<String,Object>();
		name1Map.put("type", "line");
		name1Map.put("name","");
		name1Map.put("data",rList2);
		name2Map.put("type", "line");
		name2Map.put("name","");
		name2Map.put("data","");
		List<Map<String,Object>> allMapList=new ArrayList<Map<String,Object>>();
		allMapList.add(name1Map);
		allMapList.add(name2Map);
		List<String> strDtList = new ArrayList<String>();
		for(int i1=0;i1<6;i1++){
			strDtList.add(DateUtil.dateToString(wdtList.get(i1), "yyyy-MM"));
		}
		rmap1.put("date", strDtList);
		rmap1.put("content", allMapList);    
		int bj1=0;              //小于等于1
		int bj2=0;				//大于1小鱼等于4
		int bj3=0;             //大于4
		Map<String, Object> maps=new HashMap<String, Object>();
		maps.put("useArea", "1001");  
		maps.put("endTimes", endtimes);
		maps.put("orgId", orgId);
		maps.put("flag", "1");
		ResultDto zcs=reportResultService.selectValueSumResultDto(maps);
		maps.put("useArea", "10");  
		maps.put("endTimes", endtimes);
		maps.put("orgId", orgId);
		ResultDto zcs1=reportResultService.selectValueSumResultDto(maps);
		maps.put("useArea", "01");  
		maps.put("endTimes", endtimes);
		maps.put("orgId", orgId);
		ResultDto zcs2=reportResultService.selectValueSumResultDto(maps);
		BigDecimal jshb=new BigDecimal("0");
		BigDecimal hb=new BigDecimal("0");
		BigDecimal tbhb=new BigDecimal("0");
		BigDecimal zc=new BigDecimal("0");  //zcs1.getBigSumMoney();
		BigDecimal zzc=new BigDecimal("0");   //zcs2.getBigSumMoney();
		if(zcs.getBigSumMoney()!=null){
			hb=zcs.getBigSumMoney();
		}
		if(zcs.getTbSumMoney()!=null){
			tbhb=zcs.getTbSumMoney();
		}
		if(zcs1.getBigSumMoney()!=null){
			zc=zcs1.getBigSumMoney();
		}
		if(zcs2.getBigSumMoney()!=null){
			zzc=zcs2.getBigSumMoney();
		}
		List<Integer> zjxzList=new ArrayList<Integer>();
		List<Integer> zjjqList=new ArrayList<Integer>();
		int zjxz=-1;       //资金闲置
		int zjjq=-1;			//资金紧缺
		for(int i=0;i<6;i++){
			if(rList2.get(i).compareTo(new BigDecimal("1"))==-1 || rList2.get(i).compareTo(new BigDecimal("1"))==0){
				bj1=1;
			}
			if(rList2.get(i).compareTo(new BigDecimal("1"))==1 || rList2.get(i).compareTo(new BigDecimal("4"))==-1 ||rList2.get(i).compareTo(new BigDecimal("4"))==0){
				bj2=1;
			}
			if(rList2.get(i).compareTo(new BigDecimal("4"))==1){
				bj3=1;
				if(zjxz==-1){
					zjxz=i;
				}
				zjxzList.add(i);
			}
			if(rList2.get(i).compareTo(new BigDecimal("1"))==-1){
				if(zjjq==-1){
					zjjq=i;
				}
				zjjqList.add(i);
			}
		}
		jshb=hb.subtract(tbhb);
		if(bj1==0 && bj3==1){
			String str="与上期相比，本期货币资金";
			if(jshb.compareTo(BigDecimal.ZERO)==-1){
				str=str+"减少了Rmb"+jshb.abs().divide(new BigDecimal("10000"),2,RoundingMode.HALF_UP).abs()+"万元，";
			}else{
				str=str+"增加了Rmb"+jshb.divide(new BigDecimal("10000"),2,RoundingMode.HALF_UP).abs()+"万元，";
			}
			if(zc.compareTo(BigDecimal.ZERO)==0){
				str=str+"占流动资产的比率为-%，";
			}else{
				str=str+"占流动资产的比率为"+hb.divide(zc,4,RoundingMode.HALF_UP).multiply(new BigDecimal("100")).setScale(2,RoundingMode.HALF_UP)+"%，";
			}
			if(zzc.compareTo(BigDecimal.ZERO)==0){
				str=str+"占总资产比率为-%。";
			}else{
				str=str+"占总资产比率为"+hb.divide(zzc,4,RoundingMode.HALF_UP).multiply(new BigDecimal("100"))+"%。";
			}
			if(zjxz>-1){
				String zjxzStr="";
				for(int i=0;i<zjxzList.size();i++){
					if(zjxzStr==""){
						zjxzStr=strDtList.get(zjxzList.get(i));
					}else{
						zjxzStr=zjxzStr+","+strDtList.get(zjxzList.get(i));
					}
				}
				str=str+"未来6个月内，"+zjxzStr+"月将出现资金闲置情况。建议管理层做好资金规划，考虑将闲置资金进行投资增值。";
			}else{
				str=str+"未来6个月内，-月将出现资金闲置情况。建议管理层做好资金规划，考虑将闲置资金进行投资增值。";
			}
			rmap1.put("yj2", str);
		}
		if(bj1==1 && bj3==0){
			String str="与上期相比，本期货币资金";
			if(jshb.compareTo(BigDecimal.ZERO)==-1){
				str=str+"减少了Rmb"+jshb.divide(new BigDecimal("10000"),2,RoundingMode.HALF_UP).abs()+"万元，";
			}else{
				str=str+"增加了Rmb"+jshb.divide(new BigDecimal("10000"),2,RoundingMode.HALF_UP).abs()+"万元，";
			}
			if(zc.compareTo(BigDecimal.ZERO)==0){
				str=str+"占流动资产的比率为-%，，";
			}else{
				str=str+"占流动资产的比率为"+hb.divide(zc,4,RoundingMode.HALF_UP).multiply(new BigDecimal("100")).setScale(2,RoundingMode.HALF_UP)+"%，";
			}
			if(zzc.compareTo(BigDecimal.ZERO)==0){
				str=str+"占总资产比率为-%。";
			}else{
				str=str+"占总资产比率为"+hb.divide(zzc,4,RoundingMode.HALF_UP).multiply(new BigDecimal("100")).setScale(2,RoundingMode.HALF_UP)+"%。";
			}
			if(zjjq>-1){
				String zjjqStr="";
				for(int i=0;i<zjjqList.size();i++){
					if(zjjqStr==""){
						zjjqStr=strDtList.get(zjjqList.get(i));
					}else{
						zjjqStr=zjjqStr+","+strDtList.get(zjjqList.get(i));
					}
				}
				str=str+"未来6个月内，"+zjjqStr+"月将出现资金紧缺的情况。建议管理层提前做好融资或增资计划，结合资产负债率，在资产负债率不超过60%的情况下可考虑申请银行贷款，在资产负债率超过60%的情况下，建议考虑股权融资或股东增资或股东借款等形式进行资金补充，避免因资金紧缺导致资金链断裂而影响企业整体运营。";
			}else{
				str=str+"未来6个月内，-月将出现资金紧缺的情况。建议管理层提前做好融资或增资计划，结合资产负债率，在资产负债率不超过60%的情况下可考虑申请银行贷款，在资产负债率超过60%的情况下，建议考虑股权融资或股东增资或股东借款等形式进行资金补充，避免因资金紧缺导致资金链断裂而影响企业整体运营。";
			}
			rmap1.put("yj2", str);
			//rmap1.put("yj", "与上期相比，本期货币资金增加（减少）了Rmb***，占流动资产的比率为**%，占总资产比率为**%。根据资金预测，未来6个月内，**年*月，*月将出现资金紧缺的情况。建议管理层提前做好融资或增资计划，结合资产负债率，在资产负债率不超过60%的情况下可考虑申请银行贷款，在资产负债率超过60%的情况下，建议考虑股权融资或股东增资或股东借款等形式进行资金补充，避免因资金紧缺导致资金链断裂而影响企业整体运营。");
		}
		if(bj1==1 && bj3==1){
			String str="与上期相比，本期货币资金";
			if(jshb.compareTo(BigDecimal.ZERO)==-1){
				str=str+"减少了Rmb"+jshb.divide(new BigDecimal("10000"),2,RoundingMode.HALF_UP).abs()+"万元，";
			}else{
				str=str+"增加了Rmb"+jshb.divide(new BigDecimal("10000"),2,RoundingMode.HALF_UP).abs()+"万元，";
			}
			if(zc.compareTo(BigDecimal.ZERO)==0){
				str=str+"占流动资产的比率为-%，";
			}else{
				str=str+"占流动资产的比率为"+hb.divide(zc,4,RoundingMode.HALF_UP).multiply(new BigDecimal("100")).setScale(2,RoundingMode.HALF_UP)+"%，";
			}
			if(zzc.compareTo(BigDecimal.ZERO)==0){
				str=str+"占总资产比率为-%。";
			}else{
				str=str+"占总资产比率为"+hb.divide(zzc,4,RoundingMode.HALF_UP).multiply(new BigDecimal("100"))+"%。";
			}
			if(zjjq>-1){
				String zjjqStr="";
				for(int i=0;i<zjjqList.size();i++){
					if(zjjqStr==""){
						zjjqStr=strDtList.get(zjjqList.get(i));
					}else{
						zjjqStr=zjjqStr+","+strDtList.get(zjjqList.get(i));
					}
				}
				str=str+"未来6个月内，"+zjjqStr+"月将出现资金紧缺情况，*月将出现闲置情况，其余月份资金情况较为理想。建议管理层合理分配资金，避免资金在各期间出现分配使用不均的情况。可采取的措施包括：应收账款的回收管理，贷款的合理规划，生产资金的合理安排等。建议管理层使用本系统咨询服务中的一般企业咨询模块，聘请专业顾问为企业提供专业建议。";
			}else{
				str=str+"未来6个月内，-月将出现资金紧缺情况，*月将出现闲置情况，其余月份资金情况较为理想。建议管理层合理分配资金，避免资金在各期间出现分配使用不均的情况。可采取的措施包括：应收账款的回收管理，贷款的合理规划，生产资金的合理安排等。建议管理层使用本系统咨询服务中的一般企业咨询模块，聘请专业顾问为企业提供专业建议。";
			}
			rmap1.put("yj2", str);
			//rmap1.put("yj", "与上期相比，本期货币资金增加（减少）了Rmb***，占流动资产的比率为**%，占总资产比率为**%。根据资金预测，未来6个月内，**年**月，*月将出现资金紧缺情况，*月将出现闲置情况，其余月份资金情况较为理想。建议管理层合理分配资金，避免资金在各期间出现分配使用不均的情况。可采取的措施包括：应收账款的回收管理，贷款的合理规划，生产资金的合理安排等。建议管理层使用本系统咨询服务中的一般企业咨询模块，聘请专业顾问为企业提供专业建议。");
		}
		rmap1.put("yj", "0个月以下资金严重不足，0到1个月资金为紧张，1-3个月资金为适当，3-4个月资金为中等，4个月以上资金为闲置。");
		r1.setData(rmap1);
		r1.setDw("个月");
		list.add(r1);
		map.put("flag", null);
		return list;
	}
}










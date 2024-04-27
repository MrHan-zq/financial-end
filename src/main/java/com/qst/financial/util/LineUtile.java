package com.qst.financial.util;

import com.qst.financial.dto.ResultDto;
import com.qst.financial.util.common.DateUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LineUtile {
	public static List<ResultDto> resultLine(List<Date> dtList,List<ResultDto> zcLists){
		List<ResultDto> zcList=new ArrayList<ResultDto>();
			if(dtList!=null){
				for(int i=0;i<dtList.size();i++){
					Date des=dtList.get(i);
					String strDes=DateUtil.dateFormatToString(des, "yyyy-MM");
					String strDes2=strDes;
					int zcpd=0;
					if(zcLists!=null && zcLists.size()>0){
						for(ResultDto zc : zcLists){
							String str=zc.getYearMoth();
							if(str.length()<7){
								if(strDes.length()>=7){
									strDes=Dates.getDecDate(strDes+"-01");
								}
							}
							if(strDes.equals(zc.getYearMoth())){
								zc.setYearMoth(strDes2);
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
						zcr.setDw("万元");
						zcList.add(zcr);
					}
				}
				
			}
			return zcList;
	}
	public static List<ResultDto> resultLine2(List<Date> dtList,List<ResultDto> zcLists1,List<ResultDto> zcLists2){
		List<ResultDto> zcList=new ArrayList<ResultDto>();
			if(dtList!=null){
				for(int i=0;i<dtList.size();i++){
					Date des=dtList.get(i);
					String strDes=DateUtil.dateFormatToString(des, "yyyy-MM");
					String strDes2=strDes;
					int zcpd=0;
					if(zcLists1!=null && zcLists1.size()>0){
						for(ResultDto zc : zcLists1){
							for(ResultDto zc2 : zcLists2){
								String str=zc.getYearMoth();
								String str2=zc2.getYearMoth();
								if(str.equals(str2)){
									if(str.length()<7){
										if(strDes.length()>=7){
											strDes=Dates.getDecDate(strDes+"-01");
										}
									}
									if(strDes.equals(zc.getYearMoth())){
										zc.setYearMoth(strDes2);
										BigDecimal b1=new BigDecimal("0");
										BigDecimal b2=new BigDecimal("0");
										if(zc.getBigSumMoney()!=null){
											b1=zc.getBigSumMoney();
										}
										if(zc2.getBigSumMoney()!=null){
											b2=zc2.getBigSumMoney();
										}
										zc.setBigSumMoney(b1.subtract(b2));
										zc.setSumMoney(b1.subtract(b2).toString());
										BigDecimal tb=new BigDecimal("0");
										BigDecimal tb1=new BigDecimal("0");
										BigDecimal tb2=new BigDecimal("0");
										if(zc.getTbSumMoney()!=null){
											tb1=zc.getTbSumMoney();
										}
										if(zc2.getTbSumMoney()!=null){
											tb2=zc2.getTbSumMoney();
										}
										tb=tb1.subtract(tb2);
										zc.setTbSumMoney(tb);
										if(tb.compareTo(BigDecimal.ZERO)!=0){
											zc.setOnRise(((((b1.subtract(b2)).subtract(tb)).multiply(new BigDecimal("100"))).divide(tb.abs(),2,RoundingMode.HALF_UP)).toString());
										}else{
											zc.setOnRise("-");
										}
										BigDecimal hb=new BigDecimal("0");
										BigDecimal hb1=new BigDecimal("0");
										BigDecimal hb2=new BigDecimal("0");
										if(zc.getHbSumMoney()!=null){
											hb1=zc.getHbSumMoney();
										}
										if(zc2.getHbSumMoney()!=null){
											hb2=zc2.getHbSumMoney();
										}
										hb=hb1.subtract(hb2);
										zc.setHbSumMoney(hb);
										if(hb.compareTo(BigDecimal.ZERO)!=0){
											zc.setLinkRise(((((b1.subtract(b2)).subtract(hb)).multiply(new BigDecimal("100"))).divide(hb.abs(),2,RoundingMode.HALF_UP)).toString());
										}else{
											zc.setLinkRise("-");
										}
										zcList.add(zc);
										zcpd=1;
									}
								}
								
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
						zcr.setDw("万元");
						zcList.add(zcr);
					}
				}
				
			}
			return zcList;
	}
	public static List<ResultDto> resultLine3(List<Date> dtList,List<ResultDto> zcLists1,List<ResultDto> zcLists2){
		List<ResultDto> zcList=new ArrayList<ResultDto>();
			if(dtList!=null){
				for(int i=0;i<dtList.size();i++){
					Date des=dtList.get(i);
					String strDes=DateUtil.dateFormatToString(des, "yyyy-MM");
					String strDes2=strDes;
					int zcpd=0;
					if(zcLists1!=null && zcLists1.size()>0){
						for(ResultDto zc : zcLists1){
							if(zcLists2!=null && zcLists2.size()>0){
								for(ResultDto zc2 : zcLists2){
									String str=zc.getYearMoth();
									String str2=zc2.getYearMoth();
									if(str.length()>=7){
										str=Dates.getDecDate(str+"-01");
									}
									if(str2.length()>=7){
										str2=Dates.getDecDate(str2+"-01");
									}
									if(str.equals(str2)){
										if(str.length()<7){
											if(strDes.length()>=7){
												strDes=Dates.getDecDate(strDes+"-01");
											}
										}
										if(strDes.equals(str)){
											zc.setYearMoth(strDes2);
											BigDecimal b1=new BigDecimal("0");
											BigDecimal b2=new BigDecimal("0");
											if(zc.getBigSumMoney()!=null){
												b1=zc.getBigSumMoney();
											}
											if(zc2.getBigSumMoney()!=null){
												b2=zc2.getBigSumMoney();
											}
											BigDecimal sb1=new BigDecimal("0");
											if(b2.compareTo(BigDecimal.ZERO)!=0){
												sb1=b1.divide(b2,2,RoundingMode.HALF_UP);
											}
											zc.setBigSumMoney(sb1);
											zc.setSumMoney(sb1.toString());
											BigDecimal tb=new BigDecimal("0");
											BigDecimal tb1=new BigDecimal("0");
											BigDecimal tb2=new BigDecimal("0");
											if(zc.getTbSumMoney()!=null){
												tb1=zc.getTbSumMoney();
											}
											if(zc2.getTbSumMoney()!=null){
												tb2=zc2.getTbSumMoney();
											}
											if(tb2.compareTo(BigDecimal.ZERO)!=0){
												tb=tb1.divide(tb2,2,RoundingMode.HALF_UP);
												if(tb.compareTo(BigDecimal.ZERO)!=0){
													zc.setOnRise(((((sb1).subtract(tb)).multiply(new BigDecimal("100"))).divide(tb.abs(),2,RoundingMode.HALF_UP)).toString());
												}else{
													zc.setOnRise("-");
												}
												zc.setTbSumMoney(tb);
											}else{
												zc.setOnRise("-");
											}
											
											BigDecimal hb=new BigDecimal("0");
											BigDecimal hb1=new BigDecimal("0");
											BigDecimal hb2=new BigDecimal("0");
											if(zc.getHbSumMoney()!=null){
												hb1=zc.getHbSumMoney();
											}
											if(zc2.getHbSumMoney()!=null){
												hb2=zc2.getHbSumMoney();
											}
											if(hb2.compareTo(BigDecimal.ZERO)!=0){
												hb=hb1.divide(hb2,2,RoundingMode.HALF_UP);
												if(hb.compareTo(BigDecimal.ZERO)!=0){
													zc.setLinkRise(((((sb1).subtract(hb)).multiply(new BigDecimal("100"))).divide(hb.abs(),2,RoundingMode.HALF_UP)).toString());
												}else{
													zc.setLinkRise("-");
												}
												zc.setHbSumMoney(hb);
											}else{
												zc.setLinkRise("-");
											}
											zcList.add(zc);
											zcpd=1;
										}
									}
								}
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
						zcr.setDw("万元");
						zcList.add(zcr);
					}
				}
				
			}
			return zcList;
	}
	public static List<ResultDto> resultLine4(List<Date> dtList,List<ResultDto> zcLists1,List<ResultDto> zcLists2){
		List<ResultDto> zcList=new ArrayList<ResultDto>();
			if(dtList!=null){
				for(int i=0;i<dtList.size();i++){
					Date des=dtList.get(i);
					String strDes=DateUtil.dateFormatToString(des, "yyyy-MM");
					String strDes2=strDes;
					int zcpd=0;
					if(zcLists1!=null && zcLists1.size()>0){
						for(ResultDto zc : zcLists1){
							for(ResultDto zc2 : zcLists2){
								String str=zc.getYearMoth();
								String str2=zc2.getYearMoth();
								if(str.equals(str2)){
									if(str.length()<7){
										if(strDes.length()>=7){
											strDes=Dates.getDecDate(strDes+"-01");
										}
									}
									if(strDes.equals(zc.getYearMoth())){
										zc.setYearMoth(strDes2);
										BigDecimal b1=new BigDecimal("0");
										BigDecimal b2=new BigDecimal("0");
										BigDecimal sb1=new BigDecimal("0");
										if(zc.getBigSumMoney()!=null){
											b1=zc.getBigSumMoney();
										}
										if(zc2.getBigSumMoney()!=null){
											b2=zc2.getBigSumMoney();
										}
										if(b1.compareTo(BigDecimal.ZERO)!=0){
											sb1=b1.subtract(b2).divide(b1,2,RoundingMode.HALF_UP);
										}
										zc.setBigSumMoney(sb1.multiply(new BigDecimal("100")));
										zc.setSumMoney((sb1.multiply(new BigDecimal("100"))).toString());
										BigDecimal tb=new BigDecimal("0");
										BigDecimal tb1=new BigDecimal("0");
										BigDecimal tb2=new BigDecimal("0");
										if(zc.getTbSumMoney()!=null){
											tb1=zc.getTbSumMoney();
										}
										if(zc2.getTbSumMoney()!=null){
											tb2=zc2.getTbSumMoney();
										}
										if(tb1.compareTo(BigDecimal.ZERO)!=0){
											tb=tb1.subtract(tb2).divide(tb1,2,RoundingMode.HALF_UP);
										}
										zc.setTbSumMoney(tb.multiply(new BigDecimal("100")));
										if(tb.compareTo(BigDecimal.ZERO)!=0){
											zc.setOnRise(((((sb1).subtract(tb)).multiply(new BigDecimal("100"))).divide(tb.abs(),2,RoundingMode.HALF_UP)).toString());
										}else{
											zc.setOnRise("-");
										}
										BigDecimal hb=new BigDecimal("0");
										BigDecimal hb1=new BigDecimal("0");
										BigDecimal hb2=new BigDecimal("0");
										if(zc.getHbSumMoney()!=null){
											hb1=zc.getHbSumMoney();
										}
										if(zc2.getHbSumMoney()!=null){
											hb2=zc2.getHbSumMoney();
										}
										if(hb1.compareTo(BigDecimal.ZERO)!=0){
											hb=hb1.subtract(hb2).divide(hb1,2,RoundingMode.HALF_UP);
										}
										zc.setHbSumMoney(hb.multiply(new BigDecimal("100")));
										if(hb.compareTo(BigDecimal.ZERO)!=0){
											zc.setLinkRise(((((sb1).subtract(hb)).multiply(new BigDecimal("100"))).divide(hb.abs(),2,RoundingMode.HALF_UP)).toString());
										}else{
											zc.setLinkRise("-");
										}
										zcList.add(zc);
										zcpd=1;
									}
								}
								
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
						zcr.setDw("万元");
						zcList.add(zcr);
					}
				}
				
			}
			return zcList;
	}
}

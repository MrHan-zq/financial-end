package com.qst.financial.util;

import com.qst.financial.dto.ResultDto;
import com.qst.financial.util.common.DateUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TbAndHb {
	public static ResultDto getResultDto(BigDecimal a,BigDecimal tba,BigDecimal hba,BigDecimal b,BigDecimal tbb,BigDecimal hbb,String code,String name,String str,String useArea){
		ResultDto r=new ResultDto();
		r.setCode(code);
		r.setCodeName(name);
		if(b.compareTo(BigDecimal.ZERO)!=0){
			BigDecimal ldbl=new BigDecimal("0");
			r.setBigSumMoney(ldbl);
			if(str.equals("%")){
				ldbl=(a.divide(b,4,RoundingMode.HALF_UP).multiply(new BigDecimal("100"))).setScale(2, RoundingMode.HALF_UP);;
				r.setBigSumMoney(ldbl);
				r.setSumMoney(ldbl.toString()+str);
			}else{
				ldbl=a.divide(b,2,RoundingMode.HALF_UP);
				r.setBigSumMoney(ldbl);
				r.setSumMoney(ldbl.toString()+str);
			}
			if(tbb.compareTo(BigDecimal.ZERO)!=0){
				BigDecimal tbldbl=new BigDecimal("0");
				if(str.equals("%")){
					tbldbl=(tba.divide(tbb,4,RoundingMode.HALF_UP).multiply(new BigDecimal("100"))).setScale(2, RoundingMode.HALF_UP);
				}else{
					tbldbl=tba.divide(tbb,2,RoundingMode.HALF_UP);
				}
				if(tbldbl.compareTo(BigDecimal.ZERO)!=0){
					//r.setOnRise((((ldbl.subtract(tbldbl)).divide(tbldbl.abs(),4,RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP)).toString());
					r.setOnRise(tbldbl.toString()+str);
				}else{
					r.setOnRise("-");
				}
				r.setTbSumMoney(tbldbl);
			}
			if(hbb.compareTo(BigDecimal.ZERO)!=0){
				BigDecimal hbldbl=new BigDecimal("0");
				if(str.equals("%")){
					hbldbl=(hba.divide(hbb,4,RoundingMode.HALF_UP).multiply(new BigDecimal("100"))).setScale(2, RoundingMode.HALF_UP);;
				}else{
					hbldbl=hba.divide(hbb,2,RoundingMode.HALF_UP);
				}
				if(hbldbl.compareTo(BigDecimal.ZERO)!=0){
					//r.setLinkRise((((ldbl.subtract(hbldbl)).divide(hbldbl.abs(),4,RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP)).toString());
					r.setLinkRise(hbldbl.toString()+str);
				}else{
					r.setLinkRise("-");
				}
				r.setHbSumMoney(hbldbl);
			}
		}else{
			r.setSumMoney("-");
			r.setLinkRise("-");
			r.setOnRise("-");
		}
		r.setIsDetails(1);
		r.setUseArea(useArea);
		return r;
	}
	public static ResultDto getResultDto2(BigDecimal a,BigDecimal tba,BigDecimal hba,BigDecimal b,BigDecimal tbb,BigDecimal hbb,String code,String name,String str,int days,int tbday,int hbday,String useArea){
		ResultDto r=new ResultDto();
		r.setCode(code);
		r.setCodeName(name);
		if(b.compareTo(BigDecimal.ZERO)!=0){
			BigDecimal ldbl=new BigDecimal("0");
			r.setBigSumMoney(ldbl);
			if(str.equals("%")){
				ldbl=a.divide(b,4,RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
				r.setBigSumMoney(ldbl);
				r.setSumMoney(ldbl.toString()+str);
			}else{
				BigDecimal s=a.divide(b,8,RoundingMode.HALF_UP);
				if(s.compareTo(BigDecimal.ZERO)!=0){
					ldbl=(new BigDecimal(days)).divide(s,2,RoundingMode.HALF_UP);
				}
				r.setBigSumMoney(ldbl);
				r.setSumMoney(ldbl.toString()+str);
			}
			if(tbb.compareTo(BigDecimal.ZERO)!=0){
				BigDecimal tbldbl=new BigDecimal("0");
				if(str.equals("%")){
					tbldbl=tba.divide(tbb,4,RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
				}else{
					BigDecimal tbs=tba.divide(tbb,8,RoundingMode.HALF_UP);
					tbldbl=(new BigDecimal(tbday)).divide(tbs,2,RoundingMode.HALF_UP);
				}
				if(tbldbl.compareTo(BigDecimal.ZERO)!=0){
					//r.setOnRise((((ldbl.subtract(tbldbl)).divide(tbldbl.abs(),4,RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP)).toString());
					r.setOnRise(tbldbl.toString()+str);
				}else{
					r.setOnRise("-");
				}
				r.setTbSumMoney(tbldbl);
			}
			if(hbb.compareTo(BigDecimal.ZERO)!=0){
				BigDecimal hbldbl=new BigDecimal("0");
				if(str.equals("%")){
					hbldbl=hba.divide(hbb,4,RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
				}else{
					BigDecimal hbs=hba.divide(hbb,8,RoundingMode.HALF_UP);
					if(hbs.compareTo(BigDecimal.ZERO)!=0){
						hbldbl=(new BigDecimal(hbday)).divide(hbs,2,RoundingMode.HALF_UP);
					}
					
				}
				if(hbldbl.compareTo(BigDecimal.ZERO)!=0){
					//r.setLinkRise((((ldbl.subtract(hbldbl)).divide(hbldbl.abs(),4,RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP)).toString());
					r.setLinkRise(hbldbl.toString()+str);
				}else{
					r.setLinkRise("-");
				}
				r.setHbSumMoney(hbldbl);
			}
		}else{
			r.setSumMoney("-");
			r.setLinkRise("-");
			r.setOnRise("-");
		}
		
		r.setIsDetails(1);;
		r.setUseArea(useArea);
		return r;
	}
	public static int getDays(String startTime,String endTime){
		Date des=DateUtil.stringToDate(endTime,"yyyy-MM");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		Calendar calen = Calendar.getInstance();
		calen.setTime(des);
		calen.setTime(des);
		calen.add(Calendar.MONTH, 1);  
		calen.set(Calendar.DAY_OF_MONTH, 0);
		String lastDay=format.format(calen.getTime());
		int days = (int) ((DateUtil.stringToDate(lastDay,"yyyy-MM-dd").getTime()- DateUtil.stringToDate(startTime,"yyyy-MM-dd").getTime())/ (1000*3600*24))+1;
		return days;
	}
}






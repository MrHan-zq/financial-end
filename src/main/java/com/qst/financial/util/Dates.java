package com.qst.financial.util;

import com.qst.financial.dto.ResultDto;
import com.qst.financial.util.common.DateUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Dates {
	public static int getDateTimes(String str1,String str2) throws ParseException {
		try {
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		     Calendar bef = Calendar.getInstance();
		     Calendar aft = Calendar.getInstance();
		     bef.setTime(sdf.parse(str1));
		     aft.setTime(sdf.parse(str2));
		     int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
		     int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
		     //System.out.println(Math.abs(month + result)); 
		     return result+month;
		} catch (Exception e) {
			return 0;
			// TODO: handle exception
		}
       
    }
	public static ResultDto getResultDto(ResultDto jyhdlrTotal,ResultDto jyhdlcTotal){
		ResultDto jyrd=new ResultDto();
		BigDecimal jyMoney=jyhdlrTotal.getBigSumMoney().subtract(jyhdlcTotal.getBigSumMoney());
		BigDecimal tblr=jyhdlrTotal.getTbSumMoney();
		BigDecimal tblc=jyhdlcTotal.getTbSumMoney();
		BigDecimal jytbMoney=tblr.subtract(tblc);
		//BigDecimal jytb=new BigDecimal("0.00");
		String jytb="";
		//BigDecimal jyhb=new BigDecimal("0.00");
		String jyhb="";
		BigDecimal hblr=jyhdlrTotal.getHbSumMoney();
		BigDecimal hblc=jyhdlcTotal.getHbSumMoney();
		BigDecimal jyhbMoney=hblr.subtract(hblc);
		if(jytbMoney.compareTo(BigDecimal.ZERO)==0 && jyMoney.compareTo(BigDecimal.ZERO)!=0){
			jytb="-";
		}else if(jytbMoney.compareTo(BigDecimal.ZERO)==0 && jyMoney.compareTo(BigDecimal.ZERO)==0){
			jytb="-";
		}else{
			jytb=((jyMoney.subtract(jytbMoney)).divide(jytbMoney,4, RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).toString();
		}
		if(jyhbMoney.compareTo(BigDecimal.ZERO)==0 && jyMoney.compareTo(BigDecimal.ZERO)!=0){
			jyhb="-";
		}else if(jyhbMoney.compareTo(BigDecimal.ZERO)==0 && jyMoney.compareTo(BigDecimal.ZERO)==0){
			jyhb="-";
		}else{
			jyhb=((jyMoney.subtract(jyhbMoney)).divide(jyhbMoney,4, RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).toString();
		}
		jyrd.setSumMoney(jyMoney.toString());
		jyrd.setOnRise(jytb);
		jyrd.setLinkRise(jyhb.toString());
		return jyrd;
	}
	/*public static void main(String[] args) throws ParseException {
		System.out.println(Dates.getDateTimes("2010-12", "2011-08"));
	}*/
	public static String getDecDate(String time){
		if(time.length()<8){
			time=time+"-01";
		}
		Date sts=DateUtil.stringToDate(time, "yyyy-MM-dd");
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
		return strs;
	}
}

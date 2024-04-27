package com.qst.financial.util.common;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/*
 * author"yanjun
 * 类型：日期相关
 */
public class DateUtil {

	/**
	 * 将给定应用服务器日期按照给定格式化类型转换成字符串
	 * 
	 * @param date
	 *            -java日期对象
	 * @param format
	 *            -日期格式化类型
	 * @return String -返回转换后的字符串
	 */
	public static String dateFormatToString(Date date, String format) {
		if (MethodUtil.isEmpty(date))
			date = new Date();
		if (MethodUtil.isEmpty(format))
			format = CommonConstant.DATE_WITH_FORMAT;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 将给定应用服务器日期按照给定格式化类型转换成字符串
	 * 
	 * @param date
	 *            -java日期对象
	 * @param format
	 *            -日期格式化类型
	 * @return String -返回转换后的字符串
	 */
	public static String dateToString(Date date, String format) {
		if (MethodUtil.isEmpty(date))
			date = new Date();
		if (MethodUtil.isEmpty(format))
			format = CommonConstant.DATE_WITHMILLISECOND_FORMAT;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 将给定应用服务器日期按照默认格式化类型转换成字符串
	 * 
	 * @param date
	 *            -java日期对象
	 * @return String -返回转换后的字符串
	 */
	public static String dateToString(Date date) {
		return dateToString(date, CommonConstant.DATE_WITHMILLISECOND_FORMAT);
	}

	/**
	 * 将应用服务器当前日期按照给定格式化类型转换成字符串
	 * 
	 * @param format
	 *            -日期格式化类型
	 * @return String -返回转换后的字符串
	 */
	public static String dateToString(String format) {
		return dateToString(new Date(), format);
	}

	/**
	 * 将应用服务器日期按照默认格式化类型转换成字符串
	 * 
	 * @return String -返回转换后的字符串
	 */

	public static String dateToString() {
		return dateToString(new Date(),
				CommonConstant.DATE_WITHMILLISECOND_FORMAT);
	}

	/**
	 * 将字符串转换成日期 注意：一定要选用匹配的格式，否则不能解析，将返回null
	 * 
	 * @param strDate
	 *            - 日期
	 * @param format
	 *            - 格式
	 * @return Date -转换后的日期
	 */
	public static Date stringToDate(String strDate, String format) {
		if (MethodUtil.isEmpty(strDate))
			return null;
		if (MethodUtil.isEmpty(format))
			format = CommonConstant.DATE_SHORT_FORMAT;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(strDate);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 将字符串转换成日期，默认格式：yyyy-MM-dd
	 * 
	 * @param strDate
	 *            - 日期
	 * @return Date -转换后的日期
	 */
	public static Date stringToDate(String strDate) {
		if (MethodUtil.isEmpty(strDate))
			return null;
		return stringToDate(strDate, CommonConstant.DATE_SHORT_FORMAT);
	}

	/**
	 * 获取指定日期偏移delayDays后的日期
	 * 
	 * @param startDate
	 *            -开始日期
	 * @param delayDays
	 *            -延迟的天数
	 * @return Date -转换后的日期
	 */
	public static Date getDateAfterDays(Date startDate, int delayDays) {
		if (MethodUtil.isEmpty(startDate))
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.add(Calendar.DAY_OF_MONTH, delayDays);
		return c.getTime();
	}
	/**
	 * 获取指定日期偏移delay后的日期
	 * @param startDt 开始日期
	 * @param delay 延迟的分钟数
	 * @param type 类型
	 * @return
	 */
	public static Date getDateAfter(Date startDt, int delay,int type) {
		if (MethodUtil.isEmpty(startDt))
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(startDt);
		c.add(type, delay);
		return c.getTime();
	}

	/**
	 * 获取当前日期（没转化格式）
	 * 
	 * @return Date -转换后的日期
	 */
	public static Date getCurrentDate() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	/**
	 * 获取当前时间，精确到秒（没转化格式）
	 * 
	 * @return Date -转换后的日期
	 */
	public static Date getCurrentTime() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 设置开始日期时分秒格式 00:00:00
	 * 
	 * @param date
	 *            -日期
	 * @return Date -转换后的日期
	 */
	public static Date setSatrtDate(Date date) {
		if (MethodUtil.isEmpty(date))
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	/**
	 * 设置结束日期时分秒格式 23:59:59
	 * 
	 * @param date
	 *            -日期
	 * @return Date -转换后的日期
	 */
	public static Date setEndDate(Date date) {
		if (MethodUtil.isEmpty(date))
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}

	/**
	 * 获取当前年份-短
	 * 
	 * @return String -当前年份
	 */
	public static String getCurrentYear() {
		Calendar c = Calendar.getInstance();
		String year = Integer.toString(c.get(Calendar.YEAR));
		year = year.substring(2);
		return year;
	}

	/**
	 * 获取当前月份
	 * 
	 * @return String -当天月份
	 */
	public static String getCurrentMonth() {
		Calendar c = Calendar.getInstance();
		String month = Integer.toString(c.get(Calendar.MONTH) + 1);
		if (month.length() < 2)
			month = "0" + month;
		return month;
	}

	/**
	 * 获取当天日期
	 * 
	 * @return String -日期
	 */
	public static String getCurrentDay() {
		Calendar c = Calendar.getInstance();
		String date = Integer.toString(c.get(Calendar.DATE));
		if (date.length() < 2)
			date = "0" + date;
		return date;
	}

	/**
	 * 获取指定时间的前一天的指定类型日期
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String getBeforeDay(String date, String format) {
		if (MethodUtil.isEmpty(date))
			return null;
		if (MethodUtil.isEmpty(format))
			format = CommonConstant.DATE_SHORT_FORMAT;
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTime(stringToDate(date));
		c.add(Calendar.DATE, -1);
		SimpleDateFormat myFormatter = new SimpleDateFormat(format);
		return myFormatter.format(c.getTime());
	}

	/**
	 * 获取指定时间的前一天的默认类型日期
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String getBeforeDay(String date) {
		return getBeforeDay(date, CommonConstant.DATE_WITHMILLISECOND_FORMAT);
	}

	/**
	 * 获取指定时间的后一天的指定类型日期
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String getAfterDay(String date, String format) {
		if (MethodUtil.isEmpty(date))
			return null;
		if (MethodUtil.isEmpty(format))
			format = CommonConstant.DATE_SHORT_FORMAT;
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTime(stringToDate(date));
		c.add(Calendar.DATE, 1);
		SimpleDateFormat myFormatter = new SimpleDateFormat(format);
		return myFormatter.format(c.getTime());
	}

	/**
	 * 获取指定时间的后一天的默认类型日期
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String getAfterDay(String date) {
		return getAfterDay(date, CommonConstant.DATE_WITHMILLISECOND_FORMAT);
	}

	/**
	 * 获取指定时间前一天的最后时间的固定类型日期 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param String
	 * @return String
	 */
	public static String getBeforeDayLastTime(String date) {
		if (MethodUtil.isEmpty(date))
			return null;
		return dateToString(setEndDate(stringToDate(getBeforeDay(date))),
				CommonConstant.DATE_WITHSECOND_FORMAT);
	}

	/**
	 * 获取指定时间前一天的最后时间的固定类型日期 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param String
	 * @return Date
	 */
	public static Date getBeforeDayLastTime(Date date) {
		if (MethodUtil.isEmpty(date))
			return null;
		return setEndDate(stringToDate(getBeforeDay(dateToString(date,
				CommonConstant.DATE_WITHSECOND_FORMAT))));
	}

	/**
	 * 获取指定时间后一天的开始时间的固定类型日期 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param String
	 * @return String
	 */
	public static String getAfterDayFirstTime(String date) {
		if (MethodUtil.isEmpty(date))
			return null;
		return dateToString(setSatrtDate(stringToDate(getAfterDay(date))),
				CommonConstant.DATE_WITHSECOND_FORMAT);
	}

	/**
	 * 获取指定时间后一天的开始时间的固定类型日期 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param String
	 * @return Date
	 */
	public static Date getAfterDayFirstTime(Date date) {
		if (MethodUtil.isEmpty(date))
			return null;
		return setSatrtDate(stringToDate(getAfterDay(dateToString(date,
				CommonConstant.DATE_WITHSECOND_FORMAT))));
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 * 
	 * @param sdate
	 * @return String
	 */
	public static String getWeek(String date) {
		if (MethodUtil.isEmpty(date))
			return null;
		Date sdate = stringToDate(date, CommonConstant.DATE_SHORT_FORMAT);
		Calendar c = Calendar.getInstance();
		c.setTime(sdate);

		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	/**
	 * 两个时间之间的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static long getDays(String date1, String date2)
			throws ParseException {
		if (MethodUtil.isEmpty(date1))
			return 0;
		if (MethodUtil.isEmpty(date2))
			return 0;
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat(
				CommonConstant.DATE_SHORT_FORMAT);
		Date date = null;
		Date mydate = null;

		date = myFormatter.parse(date1);
		mydate = myFormatter.parse(date2);
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return (new BigDecimal(day).abs()).longValue();
	}

	/**
	 * 给定一个年份判断该年份是否为闰年
	 * 
	 * @param year
	 * @return false:不是闰年 true:闰年
	 */
	public static boolean isLeapYear(int year) {
		GregorianCalendar calendar = new GregorianCalendar();
		return calendar.isLeapYear(year);
	}

	/**
	 * 获取数据库时间
	 * 
	 * @return String
	 * @throws Exception 
	 * @throws AGPException
	 */
	/*public static Date getDBTime() throws Exception {
		return SystemResourceUtil.getInstance().getDBTime();
	}*/
	/**
	 * 根据制定日期求加减日期
	 * 
	 * @param datetime
	 * @param days
	 * @return
	 * @throws ParseException
	 */
	public static String getDayByOpera(String datetime, int day){
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat simple = new SimpleDateFormat(CommonConstant.DATE_SHORT_FORMAT);
			Date date = simple.parse(datetime);
			calendar.setTime(date);
			calendar.add(Calendar.DATE, day);// 把日期往后增加一天.整数往后推,负数往前移动
			String reStr = simple.format(calendar.getTime());
			return reStr;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	/**
	 * 获得当前时间 
	 */
	public static String getNowTime(){
		SimpleDateFormat format = new SimpleDateFormat(CommonConstant.DATE_WITHSECOND_FORMAT);
		Date date = new Date();
		return format.format(date);
	}
	/**
	 * 获得当月的最大天数
	 * @return
	 */
	public static int getMaxDays(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		c.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = c.get(Calendar.DATE);
		return maxDate;
	}
	/**
	 * 根据日期格式化为字符串
	 * @param date
	 * @return
	 */
	public static String formatByDate(Date date,String formatStr){
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		return format.format(date);
	}
	/**
	 * 获得yyyyMMdd格式时间
	 * @return
	 */
	public static String shortSimpleFormat(){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(date);
	}
	
	/**
	 * 方法描述: 获取当前时间，返回Timestamp对象
	 * @return
	 * @throws Exception
	 * @return Timestamp
	 */
	public static Timestamp getCurrentTimestamp() throws Exception {
		return new Timestamp(System.currentTimeMillis());
	}
	/**
	 * 日期格式化
	 */
	public static Date timeToTime(Date dt,String format){
		String strdt=DateUtil.dateFormatToString(new Date(), format);
		return DateUtil.stringToDate(strdt,format);
	}
	/**
	 * 
	 * @param dt
	 * @param format
	 * @param dates   增加减少数目
	 * @param dts			年月日表示
	 * @return
	 */
	public static Date addTime(Date dt,String format,int dates,int dts){                  
		SimpleDateFormat f=new SimpleDateFormat(format);
		Calendar calen = Calendar.getInstance();
		calen.setTime(dt);
		if(dts==1){
			calen.add(Calendar.DAY_OF_YEAR,dates);            //日
		}else if(dts==2){
			calen.add(Calendar.WEEK_OF_YEAR, dates);   													//星期
		}else if(dts==3){
			calen.add(Calendar.MONTH, dates);											//月
		}else if(dts==4){
			calen.add(Calendar.YEAR,dates);                //年
		}
		return calen.getTime();
	}
	public static String longToDtStr(long dt){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(dt);
		return sdf.format(date);
	}
	/*public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long time = Long.parseLong("20110920123045");
		Date date = new Date(time);
		System.out.println(sdf.format(date));
	}*/
	public static String getTimestamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
	 /** 
     * 根据开始时间和结束时间返回时间段内的时间集合 
     *  
     * @param beginDate 
     * @param endDate 
     * @return List 
	 * @throws ParseException 
     */  
    public static List<Date> getDatesBetweenTwoDate(String dBegin, String dEnd) throws ParseException {  
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
    	Date beginDate = sdf.parse(dBegin);  
        Date endDate = sdf.parse(dEnd);  
        List<Date> lDate = new ArrayList<Date>();  
        lDate.add(beginDate);// 把开始时间加入集合  
        Calendar cal = Calendar.getInstance();  
        // 使用给定的 Date 设置此 Calendar 的时间  
        cal.setTime(beginDate);  
        boolean bContinue = true;  
        while (bContinue) {  
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
            cal.add(Calendar.MONDAY, 1);  
            // 测试此日期是否在指定日期之后  
            if (endDate.after(cal.getTime())) {  
                lDate.add(cal.getTime());  
            } else {  
                break;  
            }  
        }  
        lDate.add(endDate);// 把结束时间加入集合  
        return lDate;  
    }  
}





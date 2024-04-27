package com.qst.financial.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyCalendar {
	public static int getMonthSpace(String date1, String date2)
            throws Exception {

        int result = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));
        int years=c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH)+1;
        result=years*12+result;
        return result;

    }
	/*public static void main(String[] args) throws Exception {
		MyCalendar.getMonthSpace("2016-12-01", "2017-2-01");
	}*/

}

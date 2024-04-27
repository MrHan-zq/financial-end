package com.qst.financial.util.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CityByPhone {
	public static Map<String, String> getCity(String phone) throws IOException {

		String strUrl = "http://www.ip138.com:8080/search.asp?mobile="+phone+"&action=mobile";
		URL url = new URL(strUrl);
		HttpURLConnection httpUrlCon = (HttpURLConnection) url.openConnection();
		InputStreamReader inRead = new InputStreamReader(
				httpUrlCon.getInputStream(), "GBK");
		BufferedReader bufRead = new BufferedReader(inRead);
		StringBuffer strBuf = new StringBuffer();
		StringBuffer strBufs = new StringBuffer();
		String line = "";
		while ((line = bufRead.readLine()) != null) {
			strBuf.append(line);

		}
		String s="";
		// System.out.println(strBuf.toString().indexOf("卡号归属地"));
		if(strBuf.toString().indexOf("卡号归属地")>0){
			s = strBuf.toString().substring(
				strBuf.toString().indexOf("卡号归属地"));
			/*strBufs.append(strBuf.toString().substring(
				strBuf.toString().indexOf("卡号归属地")));*/
		// System.out.println(s.substring(93,95));
			
		}
		String province = s.substring(93, 95);
		int start = s.indexOf("&nbsp;");
		// System.out.println(s.substring(start+6,start+8));
		String city = s.substring(start + 6, start + 8);
		Map<String, String> map = new HashMap<String, String>();
		map.put("city", city);
		map.put("province", province);
		return map;
		// System.out.println(strBufs.append(strBuf).substring(4681));
		/*
		 * for(int i=0;i<strBuf.length();i++){ int n=(int)strBuf.charAt(i);
		 * if(19968<=n && n<40623){ strBufs.append(line); } }
		 */
		// System.out.println(strBufs);
	}

	/*public static void main(String[] args) throws IOException {
		CityByPhone.getCity("13058127893");
		// System.out.println(str);
	}*/
}




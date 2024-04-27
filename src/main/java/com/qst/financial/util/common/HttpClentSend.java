package com.qst.financial.util.common;

import com.qst.financial.config.RequestURL;

import java.util.HashMap;
import java.util.Map;


/**
 * 传递第三方接口所有信息
 * @author qst
 *
 */
public class HttpClentSend {
	/**
	 * 向小贷发送信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static String batchSend(Map<String, String> map,String mappings) throws Exception{
		String auth=AuthToken.getSignData(map);
		String url= RequestURL.getRequestURL()+mappings;
		map.put("auth_token", auth);
		String json = HttpXmlClient.post(url, map);
		return json;
	}
	public static void main(String[] args) {
		Map<String, String> map=new HashMap<>();
		map.put("userName", "admin");
		map.put("password", "123456");
		try {
			String str=batchSend(map,RequestURL.USERLOGIN);
			System.out.println(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



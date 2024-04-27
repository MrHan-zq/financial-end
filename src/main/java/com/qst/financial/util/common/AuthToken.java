package com.qst.financial.util.common;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AuthToken {
	public static String getSignData(Map<String, String> params)throws Exception {
		StringBuffer content = new StringBuffer();
		// 按照key做排序
		List<String> keys = new ArrayList<>(params.keySet());
		Collections.sort(keys);
		for (int i = 0; i < keys.size(); i++) {
			String key = String.valueOf(keys.get(i));
			if ("auth_token".equals(key)) {
				continue;
			}
			String values =  params.get(key);          // 具体逻辑修改下面的路径进行拼接
			content.append(values);
		}
		return md5(content.toString());
	}
	public static String md5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6','7', '8', '9','a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
				return null;
		}
	}
}







package com.qst.financial.util.common;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * App token
 * @author qst
 *
 */
public class GenerativeToken {
	public static String getToken(){
		StringBuffer sb=new StringBuffer("");
        UUID uuid = UUID.randomUUID();  
        String str = uuid.toString();  
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
        sb.append(temp.toUpperCase());
        return sb.toString();  
	}
	/**
	 * 返回map  包含时间与key
	 * @param token
	 * @return
	 */
	public static Map<String, Object> getKeyId(String token){
		String descToken=MethodUtil.getDES(token,1);
		int index=descToken.indexOf("-");
		if(index>0){
			String dates=descToken.substring(0,index);
			String keys=descToken.substring(index+1, descToken.length());
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("dates", dates);
			map.put("keys", keys);
			return map;
		}
		return null;
	}
	/**
	 * 返回String   只是key值
	 * @param token
	 * @return
	 */
	public static String getKeyById(String token){
		String descToken=MethodUtil.getDES(token,1);
		int index=descToken.indexOf("-");
		if(index>0){
			//String dates=descToken.substring(0,index);
			String keys=descToken.substring(index+1, descToken.length());
			return keys;
		}
		return null;
	}
}



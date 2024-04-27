package com.qst.financial.util.common;

import com.qst.financial.util.code.VerifyCode;

import java.util.UUID;


public class MarkPrimaryKey {
	public static String getMarkKey() {  
		StringBuffer sb=new StringBuffer(VerifyCode.generateTextCode(4, 8, null));
        UUID uuid = UUID.randomUUID();  
        String str = uuid.toString();  
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
        sb.append(temp.toUpperCase());
        return sb.toString();  
    }  
}

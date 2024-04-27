package com.qst.financial.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 机器传值解析，存入map中
 * @author qst
 *
 */
public class CodeAnaly {
	//public static String arr[]={"code","gold","onLine","status","work"};
	public static String arr[]={"code","onLine","status","work"};
	/*
	 * 对每次传过来的值进行解析，code存放机器编码，gold投币总数，onLine线上支付，status按摩椅当前状态,work工作剩余时间
	 * 按摩椅识别号码：按摩椅识别代号都是由#开头和 12 位数字和字母组成。
	 * 按摩椅投币总数：按摩椅投币总数由#开头和 1-6 位数字组成。
	 * 按摩椅投币总数：按摩椅投币总数由#开头和 1-6 位数字组成。
	 * 按摩椅当前运行状态：idle，work#剩余时间，fault#故障代码
	 * 所有的数据结束都以换行结束。HEX 码为（0X0D,0X0A）。
	 */
	public static Map<String,String> getCode(String message){
		Map<String,String> map=new HashMap<String, String>();
		message=message.substring(1);
		int i=0;
		int index=message.indexOf("#");
		while(index>=0){
			map.put(arr[i], message.substring(0,index));
			message=message.substring(index+1);
			index=message.indexOf("#");
			i=i+1;
		}
		map.put(arr[i], message);
		return map;
	}
	/**
	 * 得到某一字符到某一字符串中出现个数
	 * @param str
	 * @param c
	 * @return
	 */
	public static int getCount(String str,char c){
		int count=0;
		for(int i=0;i<str.length();i++){
				if(str.charAt(i)==c){
					count++;
				}
		}
		return count;
	}
	/*public static void main(String[] args) {
		Map<String,String> map=CodeAnaly.getCode("~08d833f62d36#000324#000512#OK！");
		for(Map.Entry<String, String> entry : map.entrySet()){
			System.out.println("codes:"+entry.getKey()+"\t"+"values:"+entry.getValue());
		}
	}*/
}

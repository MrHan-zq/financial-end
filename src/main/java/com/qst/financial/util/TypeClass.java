package com.qst.financial.util;

public class TypeClass {
	public static String resultClass(int type,String childType){
		if(type==1){
			return null;
		}else if(type==2){
			return childType;
		}
		return null;
	}
}

package com.qst.financial.util;

public class RNames {
	public static String rname(String name2){
		if(name2.indexOf("其中：")>=0){
			name2=name2.substring(name2.indexOf("其中：")+3);
		}
		if(name2.indexOf("一、")>=0){
			name2=name2.substring(name2.indexOf("一、")+2);
		}
		if(name2.indexOf("三、")>=0){
			name2=name2.substring(name2.indexOf("三、")+2);
		}
		if(name2.indexOf("四、")>=0){
			name2=name2.substring(name2.indexOf("四、")+2);
		}
		if(name2.indexOf("五、")>=0){
			name2=name2.substring(name2.indexOf("五、")+2);
		}
		if(name2.indexOf("加：")>=0){
			name2=name2.substring(name2.indexOf("加：")+2);
		}
		if(name2.indexOf("减：")>=0){
			name2=name2.substring(name2.indexOf("减：")+2);
		}
		if(name2.indexOf("六、")>=0){
			name2=name2.substring(name2.indexOf("六、")+2);
		}
		if(name2.indexOf("（亏损以“－”号填列）")>=0){
			name2=name2.substring(0,name2.indexOf("（亏损以“－”号填列）"));
		}
		if(name2.indexOf("（亏损总额以“－”号填列）")>=0){
			name2=name2.substring(0,name2.indexOf("（亏损总额以“－”号填列）"));
		}
		if(name2.indexOf("（净亏损以“－”号填列）")>=0){
			name2=name2.substring(0,name2.indexOf("（净亏损以“－”号填列））"));
		}
		return name2;
	}
}

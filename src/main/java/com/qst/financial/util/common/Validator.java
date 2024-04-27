package com.qst.financial.util.common;

import java.util.regex.Pattern;


public class Validator {
	/**
	 * 验证是否为合法邮箱信息
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		String emailPattern = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
		return Pattern.matches(emailPattern, email);
	}
	
	/**
	 * 验证是否为标准用户名
	 * @param str
	 * @return
	 */
	public static boolean isNormalName(String str){
		String namePattern = "[\u4E00-\u9FA5A-Za-z]+";
		return Pattern.matches(namePattern, str);
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isValideUserName(String str) {
		String p="^[_a-zA-Z][_a-zA-Z\\d]{4,}";
		return Pattern.matches(p, str);
	}
	/**
	 * 验证密码
	 * @param str
	 * @return
	 */
	public static boolean isValidePassword(String str) {
		String p=".{6,}";
		return Pattern.matches(p, str);
	}
	
	
	/**
	 * 手机号码 移动：134[0-8],135,136,137,138,139,150,151,157,158,159,182,187,188、178、147,170,183
	 * 联通：130,131,132,147,152,155,156,185,186 电信：133,1349,153,180,189，176,145
	 */
	public static final String mobilePattern = "^1(3[0-9]|4[57]|5[0-35-9]|7[08]|8[0235-9]|47)\\d{8}$";
	/**
	 * 10 * 中国移动：China Mobile 11 *
	 * 134、135、136、137、138、139、147（无线上网卡）、150、151、152、157、158、159、182、183、187、188、178 
	 */
	public static final String cmPattern = "^((13[4-9])|(147)|(15[0-2,7-9])|(178)|(18[2-4,7-8]))\\d{8}|(1705)\\d{7}$";
	/**
	 * 中国联通号码：130、131、132、145（无线上网卡）、155、156、185（iPhone5上市后开放）、186、176（4G号段）、175（2015年9月10日正式启用，暂只对北京、上海和广东投放办理） 
	 */
	public static final String cuPattern = "^((13[0-2])|(145)|(15[5-6])|(176)|(18[5,6]))\\d{8}|(1709)\\d{7}$";
	/**
	 * 20 * 中国电信：133、153、180、181、189、177、173、149 
	 */
	public static final String ctPattern = "^((133)|(153)|(177)|(18[0,1,9])|(149))\\d{8}$";
	/**
	 * 25 * 大陆地区固话及小灵通 26 * 区号：010,020,021,022,023,024,025,027,028,029 27 *
	 * 号码：七位或八位 28
	 */

	public static boolean isMobileNum(String mobileNum) {
		
		if (Pattern.matches(mobilePattern, mobileNum)
				|| Pattern.matches(cmPattern, mobileNum)
				|| Pattern.matches(cuPattern, mobileNum)
				|| Pattern.matches(ctPattern, mobileNum)) {
			return true;
		}
		return false;
	
	}
	
	
	public enum MobileBrand{
		CHINA_MOBILE,CHINA_TELECOM,CHINA_UNICOM,UNKNOWN
	}
	
	public static MobileBrand detectBrand(String mobileNum){
		if(Pattern.matches(cmPattern, mobileNum)){
			return MobileBrand.CHINA_MOBILE;
		}else if(Pattern.matches(cuPattern, mobileNum)){
			return MobileBrand.CHINA_UNICOM;
		}else if(Pattern.matches(ctPattern, mobileNum)){
			return MobileBrand.CHINA_TELECOM;
		}else{
			return MobileBrand.UNKNOWN;
		}
	}
	
	/**
	 * 验证字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str){
		return str!=null && !str.trim().isEmpty() ? false:true;
	}
	
	 
	/*public static void main(String[] args){
		System.out.println(Validator.isMobileNum("17619491639"));
	}*/
}

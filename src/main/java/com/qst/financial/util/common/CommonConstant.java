package com.qst.financial.util.common;


import com.qst.financial.config.PropertyUtils;


/*
 * author:yanjun
 * 
 * 类型：全局变量
 */
public class CommonConstant {
	/**
	 * 验证码
	 */
	public static final String VERIFY_CODE = "yzm";
	/**
	 * 系统DES密钥相关常量
	 */
	//public static final String DES_KEY_DEFAULT = Key.getKey();

	/**
	 * 系统参数字典前缀
	 */
	public static final String SYS_DICTIONARY_PREFIX = "SYS_";

	/**
	 * 业务参数字典前缀
	 */
	public static final String BUS_DICTIONARY_PREFIX = "BUS_";

	/**
	 * 系统标志位
	 */
	// 表示不可编辑
	public static final Character FLAG_IMMUTABLE = '0';
	// 表示可编辑
	public static final Character FLAG_VARIABLE = '1';

	/**
	 * Session中保存的用户信息
	 */
	public static final String SESSION_USER = "user";
	
	public static final String SESSION_USER_TIMEOUT = "1800";
	public static final String FIELD_NAME_DEFAUL_VALUE = "_nullName_this_is_default";
	/**
	 * 用户登录session验证码(前端存储缓存)
	 */
	public static final String SESSION_USER_VERIFYCODE ="loginVerifyCode";

	/**
	 * servletcontext中存储的在线用户列表
	 */
	public static final String SERVLETCONTEXT_ONLINE_USER = "onlineUserList";

	/**
	 * 字符编码
	 */
	public static final String UTF8 = "UTF-8";

	/*
	 * 日志常量
	 */
	// 系统日志文件中Dao层发生异常时描述信息
	public static final String SYSTEM_LOG_DAO_MESSAGE = "Dao错误";
	// 系统日志文件中Service层发生异常时描述信息
	public static final String SYSTEM_LOG_SERVICE_MESSAGE = "Service错误";
	// 系统日志文件中Controller层发生异常时描述信息
	public static final String SYSTEM_LOG_CONTROLLER_MESSAGE = "Controller错误";
	// 系统日志文件中其他地方发生异常时描述信息
	public static final String SYSTEM_LOG_OTHER_MESSAGE = "其他错误";
	// 系统日志文件定位类文件描述符
	public static final String SYSTEM_LOG_CLASS_POSITION = "className:";
	// 系统日志文件定位类方法描述符
	public static final String SYSTEM_LOG_METHOD_POSITION = "methodName:";
	// 系统日志文件定位类行号描述符
	public static final String SYSTEM_LOG_LINENUMBER_POSITION = "lineNumber:";
	// 系统日志文件定位具体异常信息描述符
	public static final String SYSTEM_LOG_ERRORTYPE_POSITION = "errorDescription:";
	// 显示给用户的解决应用错误的方式描述信息
	public static final String SYSTEM_LOG_CONTACT_DESCRIPTION = "应用程序内部错误,请与技术人员联系!";
	// 系统日志文件前缀名称
	public static final String SYSTEM_LOG_PREFIX = "cheyue<xntz>";
	// 系统日志文件只记录拦截包发生的异常信息
	public static final String SYSTEM_LOG_INTERCEPTOR_PACKAGE = "com.xntz";
	// 定位Dao层发生异常常量
	public static final String SYSTEM_LOG_USER_DAO = "Dao";
	// 定位Service层发生异常常量
	public static final String SYSTEM_LOG_USER_SERVICE = "Service";
	// 定位Controller层发生异常常量
	public static final String SYSTEM_LOG_USER_CONTROLLER = "Controller";
	// 定位其他地方发生异常常量
	public static final String SYSTEM_LOG_USER_OTHER = "Other";

	/**
	 * 日期常量
	 */
	// 简单年月日日期格式
	public static final String DATE_SHORT_SIMPLE_FORMAT = "yyyyMMdd";
	// 年月日日期格式
	public static final String DATE_SHORT_FORMAT = "yyyy-MM-dd";
	// 中文年月日日期格式
	public static final String DATE_SHORT_CHN_FORMAT = "yyyy年MM月dd日";
	// 年月日时日期格式
	public static final String DATE_WITHHOUR_FORMAT = "yyyy-MM-dd HH";
	// 中文年月日时日期格式
	public static final String DATE_WITHHOUR_CHN_FORMAT = "yyyy年MM月dd日 HH";
	// 年月日时分日期格式
	public static final String DATE_WITHMINUTE_FORMAT = "yyyy-MM-dd HH:mm";
	// 中文年月日时分日期格式
	public static final String DATE_WITHMINUTE_CHN_FORMAT = "yyyy年MM月dd日 HH:mm";
	// 年月日时分秒日期格式
	public static final String DATE_WITHSECOND_FORMAT = "yyyy-MM-dd HH:mm:ss";
	// 中文年月日时分秒日期格式
	public static final String DATE_WITHSECOND_CHN_FORMAT = "yyyy年MM月dd日 HH:mm:ss";
	// 年月日时分秒毫秒日期格式
	public static final String DATE_WITHMILLISECOND_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
	// 中文年月日时分秒毫秒日期格式
	public static final String DATE_WITHMILLISECOND_CHN_FORMAT = "yyyy年MM月dd日 HH:mm:ss.S";
	// 年月日时分秒日期格式
	public static final String DATE_WITH_FORMAT = "yyyyMMddHHmmss";
	
	
	public static final Integer USER_DISABLE = 2;//用户禁用状态
	
	public static final Integer USER_ACT = 1 ; //用户启用状态

	public static final String CHANNER_ROOT_NODE = "1";// 渠道根节点
	
	public static String getRequestURL() throws Exception{
		return PropertyUtils.get("reauestUrl");
	}
	
	/**
	 * 短信内容签名
	 */

	
	
	/**
	 * jwt
	 */
	public static final int JWT_TTL_MIN=60*1000;
	public static final String JWT_ID = "jwt";
	public static final String JWT_SECRET = "7786df7fc3a34e26a61c034d5ec8245d";
	public static final int JWT_TTL = 60 * 60 * 1000; // millisecond
	public static final int JWT_REFRESH_INTERVAL = 55 * 60 * 1000; // millisecond
	public static final int JWT_REFRESH_TTL = 48 * 12 * 60 * 60 * 1000; // millisecond
	
	
}





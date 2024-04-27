package com.qst.financial.util.common;
public class ResultCode {
	public static final int RESULT_SUCCESS=200;//请求成功
	public static final int RESULT_NULL=201;//当前操作失败
	public static final int RESULT_EXCEPTION=202;//出现异常
	public static final int RESULT_PARAM_NULL=203;//请求参数为空
	public static final int RESULT_PARAM_ERROR = 204;//请求参数有误
	public static final int RESULT_DUPLICATE = 205;//请求重复
	public static final int RESULT_PHONE_ERROR=206;//电话号码有误
	public static final int RESULT_PHONE_NOT_REGISTER=207;//该号码未注册
	public static final int RESULT_PHONE_NOT_SET=208;//号码发送失败
	public static final int RESULT_PHONE_NOT_CODE=209;//验证码过期
	public static final int RESULT_PHONE_ERROR_CODE=210;//验证码错误
	public static final int RESULT_PAY_NOT_SET=211;//号码发送失败
	public static final int RESULT_NOT_SET=212;//支付码错误
	
	public static final int RESULT_PHONE_TIMES_CODE=301;         //短信超过次数
	public static final int RESULT_NO_VIP=302;              //用户不是vip
	public static final int RESULT_NO_PHONEID=303;          //用户id与手机不一致
	public static final int RESULT_NO_FANSNUMBER=304;    //用户提取粉丝数不够
	public static final int RESULT_NO_SING=305;      //超过签到次数
	public static final int RESULT_NO_OTHEN=306;    //不能互相推荐
	public static final int RESULT_HAVE_VIP=307;    //该用户是VIP
	public static final int RESULT_SY_VIP=308;       //已经试用试用
	
	public static final int STATE_SUCCESS=200;//请求成功
	public static final int STATE_NULL=201;//请求成功但值为空
	public static final int STATE_EXCEPTION=202;//出现异常
	
	/**
	 * tokens过期
	 */
	public static final int RESULT_TOKENS_EXPIRED = 405;
}

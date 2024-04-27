package com.qst.financial.util.common;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Key;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 
 * @author qst
 * 
 * @类说明：方法工具类，包含绝大多数公共方法
 */
public class MethodUtil {
	private static Log log = LogFactory.getLog(MethodUtil.class);

	public static final long EXPIRE = 1000 * 60 * 60 * 24*7;//过期时间（毫秒为单位，此处为7天）
	public static final String APP_SECRET = "nicaicaikanwoyongdesha";//秘钥（自己随便写）




	public static String getJwtToken(String userInfo){

		String JwtToken = Jwts.builder()
				.setHeaderParam("typ", "JWT")//jwet头信息
				.setHeaderParam("alg", "HS256")
				.setSubject("user_star")//分类（随便写）
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
				.claim("userInfo", userInfo)
				.signWith(SignatureAlgorithm.HS256, APP_SECRET)
				.compact();
		return JwtToken;
	}

	/**
	 * 判断token是否存在与有效
	 * @param jwtToken
	 * @return
	 */
	public static boolean checkToken(String jwtToken) {
		if(null!=jwtToken&&!"".equals(jwtToken)) return false;
		try {
			Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * 获取登录用户的IP地址
	 * 
	 * @param request
	 * @return String
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.equals("0:0:0:0:0:0:0:1")) {
			ip = "本地";
		}
		return ip;
	}

	/**
	 * MD5加密方法
	 * 
	 * @param str
	 * @param encoding
	 *            default UTF-8
	 * @param no_Lower_Upper
	 *            0,1,2 0：不区分大小写，1：小写，2：大写
	 * @return MD5Str
	 */
	public static String getMD5(String str, String encoding, int no_Lower_Upper) {
		if (null == encoding)
			encoding = "utf-8";
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(str.getBytes(encoding));
			for (int i = 0; i < array.length; i++) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (no_Lower_Upper == 0) {
			return sb.toString();
		}
		if (no_Lower_Upper == 1) {
			return sb.toString().toLowerCase();
		}
		if (no_Lower_Upper == 2) {
			return sb.toString().toUpperCase();
		}
		return null;
	}

	/**
	 * DES
	 * 
	 * @param arrBTmp
	 * @return
	 * @throws Exception
	 */
	private static Key getKey(byte[] arrBTmp) throws Exception {
		byte[] arrB = new byte[8];// 创建一个空的8位字节数组（默认值为0）
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) { // 将原始字节数组转换为8位
			arrB[i] = arrBTmp[i];
		}
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");// 生成密钥
		return key;
	}

	/**
	 * DES
	 * 
	 * @param strIn
	 * @return
	 * @throws Exception
	 */
	private static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes("UTF-8");
		int iLen = arrB.length;

		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	/**
	 * DES
	 * 
	 * @param arrB
	 * @return
	 * @throws Exception
	 */
	private static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}
	/*public static void main(String[] args) {
		String s=getDES("15916906463", 0);
		System.out.println(s);
		System.out.println(getDES("baeb48a04a70e864656d2ebadfe6f29a", 1));
		Object o = new Object();
		o = "2fc11faf2b0262f0";
		System.out.println("3333"+MethodUtil.getDES(o.toString(), 1)+"333333         "+getMD5("123456", null, 1));//2fc11faf2b0262f0
		
	}*/
	/**
	 * DES方法 0为加密,1为解密
	 * 
	 * @param deskey 密钥
	 * @param str 待加密字符串
	 * @param type
	 *            0为加密,1为解密
	 * @return DES Str
	 */
	public static String getDES(String str, int type) {
			return null;
	}

	/**
	 * 
	 * @return
	 */
	public static MethodUtil getInstance() {
		return new MethodUtil();
	}

	/**
	 * 获取随机数
	 * 
	 * @param min
	 *            最小数
	 * @param max
	 *            最大数
	 * @return int
	 */
	public static int getRandom(int min, int max) {
		return (int) (Math.random() * (max - min) + min);
	}

	/**
	 * 获取随机数从1开始,格式10000000-99999999
	 * 
	 * @param number 随机数长度
	 * @return
	 */
	public static int getRandom(int number) {
		int max = 9;
		int min = 1;
		for (int i = 1; i < number; i++) {
			min = min * 10;
			max = max * 10 + 9;
		}
		return getRandom(min, max);
	}

	/**
	 * 获取日期方法
	 * 
	 * @param type 0:yyyy-MM-dd HH:mm:ss 1:yyyyMMddHHmmss 2:yyyyMMdd
	 * @param formatStr
	 *            日期格式
	 * @return String
	 */
	public static String getDate(int type, String formatStr) {
		Date date = new Date();
		SimpleDateFormat sdf = null;
		if (null != formatStr) {
			sdf = new SimpleDateFormat(formatStr);
		} else if (type == 0) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else if (type == 1) {
			sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		} else if (type == 2) {
			sdf = new SimpleDateFormat("yyyyMMdd");
		}
		String str = sdf.format(date);
		return str;
	}

	/**
	 * 时间差
	 * 
	 * @param current_time
	 *            当前时间
	 * @param compare_time
	 *            比较时间
	 * @return long
	 */
	public static long getDateCompare(String current_time, String compare_time) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long time = 0;
		try {
			Date c_tiem = sf.parse(current_time);
			Date com_time = sf.parse(compare_time);
			long l = c_tiem.getTime() - com_time.getTime() > 0 ? c_tiem.getTime() - com_time.getTime() : com_time.getTime() - c_tiem.getTime();
			time = l / 1000; // 算出超时秒数
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 处理时间的加减运算 60*60 为一个小时 60*60*24 为一天
	 * 
	 * @param startTime
	 * @param endTime
	 * @param type
	 *            0为加 1为减
	 * @return Date long
	 */
	public static long getDateAdd(String startTime, String endTime, int type) {
		long time = 0l;
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime);
			Date addLong = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);
			switch (type) {
			case 0:
				time = (date.getTime() / 1000) + (addLong.getTime() / 1000);
				break;
			case 1:
				time = (date.getTime() / 1000) - (addLong.getTime() / 1000);
				break;
			default:
				time = (date.getTime() / 1000) + (addLong.getTime() / 1000);
				break;
			}
			date.setTime(time * 1000);
			time = date.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 一个月最大day
	 * 
	 * @param time
	 *            时间
	 * @return obj[0]=maxMonth; obj[1]=time
	 */
	public static Object[] getMaxMonth(String time) {
		Object[] obj = new Object[2];
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar a = Calendar.getInstance();
		a.setTime(date);
		a.set(Calendar.DATE, 1); // 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxMonth = a.get(Calendar.DATE);
		a.roll(Calendar.DATE, 1);
		time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(a.getTime());
		obj[0] = maxMonth;
		obj[1] = time;
		return obj;
	}

	/**
	 * 20位可用于UUID
	 * 
	 * @return String
	 */
	public static String getUid() {
		return new SimpleDateFormat("yyMMddHHmmss").format(new Date()) + getRandom(8);
	}

	/**
	 * 12位时间加上number位数
	 * 
	 * @param number
	 * @return Long
	 */
	public static Long getUid(int number) {
		//中间""符号不能去除
		return Long.parseLong(new Date().getTime()+""+ getRandom(number));
	}
	
	
	/**
	 * 结合当前用户uid的主键生成一个唯一的数据ID
	 * @param primarykey  (从session中取得用户的id)
	 * @return
	 */
	public static Long getIdWithPrimarykey(int primarykey) {
	
		return Long.parseLong(String.valueOf(new Date().getTime())+ primarykey);
	}
	
	

	/**
	 * 输出JSON
	 * 
	 * @param response
	 * @param type
	 *            0=成功 其他=失败
	 * @param msg
	 */
	public static void toJsonMsg(HttpServletResponse response, int type, String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", type);
		if (type == 0) {
			map.put("success", true);
			if (msg == null) {
				map.put("msg", "成功");
			} else {
				map.put("msg", msg);
			}
		} else {
			map.put("success", false);
			if (msg == null) {
				map.put("msg", "失败");
			} else {
				map.put("msg", msg);
			}
		}
		map.put("state", type);
		toJsonPrint(response, JSONObject.fromObject(map).toString());
	}

	/**
	 * 打印JSON
	 * 
	 * @param response
	 * @param str
	 */
	public static void toJsonPrint(HttpServletResponse response, String str) {
		// 不需要设置 避免IE9 出现下载
		// response.setContentType("application/json");text/html;charset=UTF-8
//		response.setContentType("application/json");
//		response.setContentType("text/x-json;charset=UTF-8");
		writer(response, str);
	}

	/**
	 * 打印
	 * 
	 * @param response
	 * @param str
	 */
	private static void writer(HttpServletResponse response, String str) {
		try {
			// 设置页面不缓存
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter out = null;
			out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 在页面输出脚本
	 * 
	 * @param response
	 * @param str
	 */
	public void toSript(HttpServletResponse response, String str) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script type=\"text/javascript\">");
		sb.append(str);
		sb.append("</script>");
		response.setContentType("text/html");
		writer(response, sb.toString());
	}
	
	/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 *      -参数对象
	 * @return boolean
	 * 		-true:表示对象为空;false:表示对象为非空
	 */
	public static boolean isEmpty(Object obj) {
		return obj == null || obj.toString().equalsIgnoreCase("null") || obj.toString().length() == 0;
	}

	/**
	 *Object 转换为int类型
	 */
	public static int converToInt(Object str, int des) {
		if (isEmpty(str)) {
			return des;
		}
		return Integer.parseInt(str.toString());
	}
	/**
	 * 对象转换为Double
	 * @param str
	 * @param des
	 * @return
	 */
	public static double converToDouble(Object str, double des){
		if (isEmpty(str)) {
			return des;
		}
		return Double.parseDouble(str.toString());
	}
	/**
	 * 将对象转换为boolean类型
	 * @param str
	 * @param des
	 * @return
	 */
	public static boolean converToBoolean(Object str, boolean des){
		if (isEmpty(str)) {
			return des;
		}
		return Boolean.parseBoolean(str.toString());
	}
	/**
	 *Object 转换为long类型
	 * @param str
	 * @param des
	 * @return
	 */
	public static long converToLong(Object str, long des) {
		if (isEmpty(str)) {
			return des;
		}
		return Long.parseLong(str.toString());
	}
	/**
	 * Object转换为String
	 * @param obj
	 * @return
	 */
	public static String convetToString(Object obj){
		if (isEmpty(obj)) {
			return "";
		}
		return obj.toString();
	}
	/**
	 * 获得手续费 金额以分来算 返回金额，以元来算
	 * @param money
	 * @param per
	 * @param max
	 * @return
	 */
	public static double getCostMoney(long money, int per, int max) {
		if (money == 0) {
			return 0.00;
		}
		if (per == 0) {
			return 0.00;
		}
		if (max == 0) {
			return 0.00;
		}
		if (per > max) {
			throw new ClassCastException("per cant greater max value");
		}
		double reval = money * per * 0.01 / max;
		return reval;
	}
	/**
	 * 检测是否不为空 不为空返回true
	 */
	public static boolean isNotNull(Object obj) {
		if (obj == null || obj.toString().trim().length() == 0
				|| obj.toString().trim().equals("null")) {
			return false;
		}
		return true;
	}
	/**
	 * List集合中对象复制
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void transFormEntity(List oldList,List newList,Class clas){
		try {
			int size = 0;
			if(oldList != null && (size = oldList.size()) >= 1){
				String className = clas.getName();
				Class myclass = Class.forName(className);
				for (int i = 0; i < size; i++) {
					Object obj = oldList.get(i);
					Object bean = myclass.newInstance();
					BeanUtils.copyProperties(obj, bean);
					newList.add(bean);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("MethodUtil transFormEntity have exception:"+e);
		}
	}
	
	
	/**
	 * 银行卡号码、身份证号码、手机号码、邮箱地址等信息处理
	 * 
	 * @param cardNo
	 * @param prefix
	 * @param suffix
	 * @return
	 */
	public static String getSecurityNum(String cardNo, int prefix, int suffix) {
		StringBuffer cardNoBuffer = new StringBuffer();
		int len = prefix + suffix;
		if (StringUtils.isNotBlank(cardNo) && cardNo.length() > len) {
			cardNoBuffer.append(cardNo.substring(0, prefix));
			for (int i = 0; i < cardNo.length() - len; i++) {
				cardNoBuffer.append("*");
			}
			cardNoBuffer.append(cardNo.substring(cardNo.length() - suffix,
					cardNo.length()));
		}
		return cardNoBuffer.toString();
	}
	public static String getUUID() {  
        UUID uuid = UUID.randomUUID();  
        String str = uuid.toString();  
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);  
        return temp.toUpperCase();  
    }  
	
	/**
	 * 是否包含表情
	 * 
	 * @param source
	 * @return
	 */
	public static boolean containsEmoji(String source) {
		int len = source.length();
		boolean isEmoji = false;
		for (int i = 0; i < len; i++) {
			char hs = source.charAt(i);
			if (0xd800 <= hs && hs <= 0xdbff) {
				if (source.length() > 1) {
					char ls = source.charAt(i + 1);
					int uc = ((hs - 0xd800) * 0x400) + (ls - 0xdc00) + 0x10000;
					if (0x1d000 <= uc && uc <= 0x1f77f) {
						return true;
					}
				}
			} else {
				// non surrogate
				if (0x2100 <= hs && hs <= 0x27ff && hs != 0x263b) {
					return true;
				} else if (0x2B05 <= hs && hs <= 0x2b07) {
					return true;
				} else if (0x2934 <= hs && hs <= 0x2935) {
					return true;
				} else if (0x3297 <= hs && hs <= 0x3299) {
					return true;
				} else if (hs == 0xa9 || hs == 0xae || hs == 0x303d
						|| hs == 0x3030 || hs == 0x2b55 || hs == 0x2b1c
						|| hs == 0x2b1b || hs == 0x2b50 || hs == 0x231a) {
					return true;
				}
				if (!isEmoji && source.length() > 1 && i < source.length() - 1) {
					char ls = source.charAt(i + 1);
					if (ls == 0x20e3) {
						return true;
					}
				}
			}
		}
		return isEmoji;
	}

	private static boolean isEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
				|| (codePoint == 0xD)
				|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
				|| ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
				|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}

	/**
	 * 过滤emoji或者其他非文字类型的字符
	 * 
	 * @param source
	 * @return
	 */
	public static String filterEmoji(String source) {
		if (StringUtils.isBlank(source)) {
			return source;
		}
		StringBuilder buf = null;
		int len = source.length();
		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);
			if (isEmojiCharacter(codePoint)) {
				if (buf == null) {
					buf = new StringBuilder(source.length());
				}
				buf.append(codePoint);
			}
		}
		if (buf == null) {
			return source;
		} else {
			if (buf.length() == len) {
				buf = null;
				return source;
			} else {
				return buf.toString();
			}
		}
	}
	
}

package com.qst.financial.util;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


public class SessionUtilRedis {
	protected static final Logger logger = Logger.getLogger(SessionUtilRedis.class);
	/*@Autowired
	private static RedisService redisService;*/
	//private static RedisServiceImpl redisutil = (RedisServiceImpl) ContextLoader.getCurrentWebApplicationContext().getBean("RedisServiceImpl");
	/**
	 * 生产redis key ： uky+key
	 * @param request
	 * @return
	 */
	/*public static String generateKeyPrefix(HttpServletRequest request,String key){
		String sessionId = CookieHelper.getCookieValue(request, "uky");
		if(sessionId == null || "".equals(sessionId)){
			logger.error("generateKeyPrefix sessionId is null");
			sessionId = request.getSession().getId().toUpperCase() ;       //+ getUUID()
		}
		StringBuffer sb = new StringBuffer(sessionId).append("-").append(key);
		return sb.toString();
	}
	
	public static void setAttr(HttpServletRequest request, String key,
			Object value) {
		String redisKey = generateKeyPrefix(request,key);
		if(redisKey == null ){
			logger.error("setAttr redisKey is null");
			return;
		}
		Long result = null;
		if(value instanceof String){
			result = redisService.set(redisKey,(String)value, 30*60);
		}else{
			result = redisService.set(redisKey,value,30*60);
		}
		logger.info("setAttr redisKey:"+redisKey+"---object:" + value + "---resutl:" +result);
	}
	
	

	public static Object getAttrAsObject(HttpServletRequest request, String key) {
		String redisKey = generateKeyPrefix(request,key);
		if(redisKey == null ){
			logger.error("getAttrAsObject redisKey is null");
			return null;
		}
		Object object = redisService.getObj(redisKey);
		logger.info("getAttrAsObject redisKey:"+redisKey+ "---object:" + object);
		//延长过期时间
		if(object != null && "user".equals(key)){
			if(object instanceof String){
				redisService.set(redisKey,(String)object, 30*60);
			}else{
				redisService.set(redisKey,object,30*60);
			}
		}
		return object;
	}*/
	/**
	 * 生成uuid
	 * @return
	 */
	public static String getUUID() {  
        UUID uuid = UUID.randomUUID();  
        String str = uuid.toString();  
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);  
        return temp.toUpperCase();  
    }

	public static void removeAttr(HttpServletRequest request, String sessionUser) {
		// TODO Auto-generated method stub
		
	}  
}

package com.qst.financial.redis;

import com.qst.financial.util.CookieHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Service("redisService")
public class RedisServiceImpl implements RedisService{
	protected static final Logger logger = Logger.getLogger(RedisServiceImpl.class);
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Resource(name = "stringRedisTemplate")
	private ValueOperations<String, String> valOpsStr;
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	@Resource(name = "redisTemplate")
	private ValueOperations<Object, Object> valOpsObj;
	@Value("${session.time}")
	private int time;
	/**
	 * 保存session
	 * @param request
	 * @param key
	 * @param value
	 */
	@Override
	public void setAttr(HttpServletRequest request, String key,
			Object value) {
		String redisKey = generateKeyPrefix(request,key);
		if(redisKey == null ){
			logger.error("setAttr redisKey is null");
			return;
		}
		Long result = null;
		if(value instanceof String){
			setObjByTime(redisKey, value,time);
			//result = set(redisKey,(String)value, 30*60);
		}else{
			setObjByTime(redisKey, value,time);
			//result = set(redisKey,value,30*60);
		}
		logger.info("setAttr redisKey:"+redisKey+"---object:" + value + "---resutl:" +result);
	}
	/*
	 * 得到session
	 */
	@Override
	public Object getAttrAsObject(HttpServletRequest request, String key) {
		String redisKey = generateKeyPrefix(request,key);
		if(redisKey == null ){
			logger.error("getAttrAsObject redisKey is null");
			return null;
		}
		Object object = getObj(redisKey);
		logger.info("getAttrAsObject redisKey:"+redisKey+ "---object:" + object);
		//延长过期时间
		if(object != null && "user".equals(key)){
			setObjByTime(redisKey, object,time);
		}
		return object;
	}
	@Override
	public void removeAttr(HttpServletRequest request, String key) {
		String redisKey = generateKeyPrefix(request,key);
		if(redisKey == null ){
			logger.error("getAttrAsObject redisKey is null");
		}else{
			delObj(key);
		}
		
	}
	/**设置有超时时间的KV *//*  
	@Override
	public Long set(String key, Object value, long seconds) {  
        return redisTemplate.execute(c -> {  
            c.set(key.getBytes(), ((String) value).getBytes());  
            c.expire(key.getBytes(), seconds);  
            return 1L;  
        });  
    }  
	*//** 
     *存入不会超时的KV 
     *//*  
	@Override
    public Long set(String key, String value) {  
        return redisTemplate.execute(c -> {  
            c.set(key.getBytes(), value.getBytes());  
            return 1L;  
        });  
    }  
    *//**判断redis数据库是否有对应的key*//*  
	@Override
    public boolean exist(String key){  
        return redisTemplate.execute(c->c.exists(key.getBytes()));  
    }  
    *//** 
     * redis数据库条数 
     *//*  
	@Override
    public Long dbSize() {  
        return redisTemplate.execute(c -> c.dbSize());  
    }  
    *//**获得redis数据库所有的key*//*  
	@Override
    public Set<String> keys(String pattern){  
        return redisTemplate.execute(c->c.keys(pattern.getBytes()).stream().map(this::getUTF).collect(Collectors.toSet()));  
    } 
    private String getUTF(byte[] data){  
        try {  
            return new String(data, "utf-8");  
        } catch (UnsupportedEncodingException e) {  
            return null;  
        }  
    }  */ 
	/**
     * 根据指定key获取String
     * @param key
     * @return
     */
    @Override
    public String getStr(String key){
        return valOpsStr.get(key);
    }

    /**
     * 设置Str缓存
     * @param key
     * @param val
     */
    @Override
    public void setStr(String key, String val){
        valOpsStr.set(key,val);
    }
    /**
     * 设置Str换成 带时间
     * @param key
     * @param val
     */
    @Override
    public void setStrByTime(String key, String val,long times){
        valOpsStr.set(key,val,times);
    }
    /**
     * 删除指定key
     * @param key
     */
    @Override
    public void del(String key){
        stringRedisTemplate.delete(key);
    }

    /**
     * 根据指定o获取Object
     * @param o
     * @return
     */
    @Override
    public Object getObj(Object o){
        return valOpsObj.get(o);
    }

    /**
     * 设置obj缓存
     * @param o1
     * @param o2
     */
    @Override
    public void setObj(Object o1, Object o2){
        valOpsObj.set(o1, o2);
    }
    @Override
    public void setObjByTime(Object o1, Object o2,int times){
    	 valOpsObj.set(o1, o2,times,TimeUnit.SECONDS);
    }
    /**
     * 删除Obj缓存
     * @param o
     */
    @Override
    public void delObj(Object o){
        redisTemplate.delete(o);
    }
    public static String generateKeyPrefix(HttpServletRequest request,String key){
		String sessionId = CookieHelper.getCookieValue(request, "uky");
		if(sessionId == null || "".equals(sessionId)){
			logger.error("generateKeyPrefix sessionId is null");
			sessionId = request.getSession().getId().toUpperCase() ;       //+ getUUID()
		}
		StringBuffer sb = new StringBuffer(sessionId).append("-").append(key);
		return sb.toString();
	}
}

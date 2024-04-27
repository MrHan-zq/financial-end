package com.qst.financial.redis;

import javax.servlet.http.HttpServletRequest;

public interface RedisService {
	/**
	 * 保存session
	 * @param request
	 * @param key
	 * @param value
	 */
	public void setAttr(HttpServletRequest request, String key,Object value);
	/*
	 * 得到session
	 */
	public Object getAttrAsObject(HttpServletRequest request, String key);
	/**设置有超时时间的KV */  
	/*public Long set(String key, Object value, long seconds);
	*//** 
     *存入不会超时的KV 
     *//*  
    public Long set(String key, String value);
    *//**判断redis数据库是否有对应的key*//*  
    public boolean exist(String key);
    *//** 
     * redis数据库条数 
     *//*  
    public Long dbSize();
    *//**获得redis数据库所有的key*//*  
    public Set<String> keys(String pattern);*/
	/**
     * 根据指定key获取String
     * @param key
     * @return
     */
    //public String getStr(String key);

    /**
     * 设置Str缓存
     * @param key
     * @param val
     */
    public void setStr(String key, String val);

    /**
     * 删除指定key
     * @param key
     */
    public void del(String key);

    /**
     * 根据指定o获取Object
     * @param o
     * @return
     */
    public Object getObj(Object o);

    /**
     * 设置obj缓存
     * @param o1
     * @param o2
     */
    public void setObj(Object o1, Object o2);

    /**
     * 删除Obj缓存
     * @param o
     */
    public void delObj(Object o);

	public void setStrByTime(String key, String val, long times);
	public void removeAttr(HttpServletRequest request, String key);
	public void setObjByTime(Object o1, Object o2, int times);
	public String getStr(String key);
}

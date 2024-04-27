package com.qst.financial.util;

import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

public class MyMap {
	private Map<String, String> map2 = new HashMap<String, String>();
	private Map<String, SocketChannel> map = new HashMap<String, SocketChannel>();
    private Object lock = new Object();
    private static class MyMapHeder{
    	public static MyMap myMap=new MyMap();
    }
    //private static MyMap instance = new MyMap();
    private MyMap(){}
    public static MyMap getMyMap() {
        /* if (instance == null) {
             instance = new MyMap();
         }
         return instance ;*/
    	return MyMapHeder.myMap;
    }
    public void put(String taskId, SocketChannel name) {
         synchronized (lock ) {
             map.put(taskId, name);
         }
    }
   
    public Map<String, SocketChannel> getMap() {
         return map ;
    }
    /**
     * 清除
     * @param key
     */
   public void removeMap(String key) {
        map.remove(key) ;
   }
   
   public void put2(String taskId, String name) {
        synchronized (lock ) {
            map2.put(taskId, name);
        }
   }
  
   public Map<String, String> getMap2() {
        return map2 ;
   }
   /**
    * 清除
    * @param key
    */
   public void removeMap2(String key) {
       map2.remove(key) ;
  }
}

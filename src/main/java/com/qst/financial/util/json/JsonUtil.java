package com.qst.financial.util.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class JsonUtil {
private static ObjectMapper objectMapper = new ObjectMapper();
	
/** 

 * javaBean,list,array convert to json string 

 */  
public static String obj2json(Object obj) throws Exception{  
    return objectMapper.writeValueAsString(obj);  
}  
  
/** 

 * json string convert to javaBean 

 */  
public static <T> T json2pojo(String jsonStr,Class<T> clazz) throws Exception{  
    return objectMapper.readValue(jsonStr, clazz);  
}  
  
/** 

 * json string convert to map 

 */  
@SuppressWarnings("unchecked")
public static <T> Map<String,Object> json2map(String jsonStr)throws Exception{  
    return objectMapper.readValue(jsonStr, Map.class);  
}  
  
/** 

 * json string convert to map with javaBean 

 */  
public static <T> Map<String,T> json2map(String jsonStr,Class<T> clazz)throws Exception{  
    Map<String,Map<String,Object>> map =  objectMapper.readValue(jsonStr, new TypeReference<Map<String,T>>() {  
    });  
    Map<String,T> result = new HashMap<String, T>();  
    for (Entry<String, Map<String,Object>> entry : map.entrySet()) {  
        result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));  
    }  
    return result;  
}  
  
/** 

 * json array string convert to list with javaBean 

 */  
public static <T> List<T> json2list(String jsonArrayStr,Class<T> clazz)throws Exception{  
    List<Map<String,Object>> list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {  
    });  
    List<T> result = new ArrayList<>();  
    for (Map<String, Object> map : list) {  
        result.add(map2pojo(map, clazz));  
    	
    }  
    return result;  
} 
public static <T> List<T> jsonlists(String jsonArrayStr,Class<T> clazz)throws Exception{  
    List<Map<String,Object>> list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {  
    });  
    List<T> result = new ArrayList<>();  
    for (Map<String, Object> map : list) {  
        result.add((T) convertSetMap(map, clazz));  
    	
    }  
    return result;  
} 
public static <T> List<Map<String,Object>> jsonlistsMap(String jsonArrayStr)throws Exception{  
    List<Map<String,Object>> list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {  
    });  
    return list;  
} 
public static <T> List<T> jsonlist(String jsonArrayStr,Class<T> clazz)throws Exception{  
    List<Map<String,Object>> list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {  
    });  
    List<T> result = new ArrayList<>();  
    for (Map<String, Object> map : list) {  
        result.add((T) convertMap(map, clazz));  
    	
    }  
    return result;  
}  
public static Object convertMap(Map map,Class type)  
        throws IntrospectionException, IllegalAccessException,  
        InstantiationException, InvocationTargetException {  
    BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性  

    Object obj = type.newInstance(); // 创建 JavaBean 对象  


    // 给 JavaBean 对象的属性赋值  

    PropertyDescriptor[] propertyDescriptors = beanInfo  
            .getPropertyDescriptors();  
    for (int i = 0; i < propertyDescriptors.length; i++) {  
        PropertyDescriptor descriptor = propertyDescriptors[i];  
        String propertyName = descriptor.getName();  
        if (map.containsKey(propertyName)) {  
            // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。  

            Object value = map.get(propertyName);  

            Object[] args = new Object[1];  
            args[0] = value;  

            descriptor.getWriteMethod().invoke(obj, args);  
        }  
    }  
    return obj;  
}    
/** 

 * map convert to javaBean 

 */  
public static <T> T map2pojo(Map<?,?> map,Class<T> clazz){  
    return objectMapper.convertValue(map, clazz);  
}  
public static Object convertMap2(Map map,Class type)  
        throws IntrospectionException, IllegalAccessException,  
        InstantiationException, InvocationTargetException {  
    BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性  

    Object obj = type.newInstance(); // 创建 JavaBean 对象  


    // 给 JavaBean 对象的属性赋值  

    PropertyDescriptor[] propertyDescriptors = beanInfo  
            .getPropertyDescriptors();  
    for (int i = 0; i < propertyDescriptors.length; i++) {  
        PropertyDescriptor descriptor = propertyDescriptors[i];  
        String propertyName = descriptor.getName();  
        if (map.containsKey(propertyName)) {  
            // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。  

            Object value = map.get(propertyName);  
            Object[] args = new Object[1];  
            args[0] = value;  
            if(!value.equals(null)){
            	descriptor.getWriteMethod().invoke(obj, args);
            }
        }  
    }  
    return obj;  
}   
public static Object convertMaps(Map map,Class type)  
        throws IntrospectionException, IllegalAccessException,  
        InstantiationException, InvocationTargetException {  
    BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性  

    Object obj = type.newInstance(); // 创建 JavaBean 对象  


    // 给 JavaBean 对象的属性赋值  

    PropertyDescriptor[] propertyDescriptors = beanInfo  
            .getPropertyDescriptors();  
    for (int i = 0; i < propertyDescriptors.length; i++) {  
        PropertyDescriptor descriptor = propertyDescriptors[i];  
        String propertyName = descriptor.getName();  
        if (map.containsKey(propertyName)) {  
            // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。  

            Object value = map.get(propertyName);  
            if(value!=null){
            	Object[] args = new Object[1];  
            	args[0] = value.toString();  
            	descriptor.getWriteMethod().invoke(obj, args);
            }
        }  
    }  
    return obj;  
}    
public static Object convertSetMap(Map map,Class type)  
        throws IntrospectionException, IllegalAccessException,  
        InstantiationException, InvocationTargetException {  
    BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性  

    Object obj = type.newInstance(); // 创建 JavaBean 对象  


    // 给 JavaBean 对象的属性赋值  

    PropertyDescriptor[] propertyDescriptors = beanInfo  
            .getPropertyDescriptors();  
    for (int i = 0; i < propertyDescriptors.length; i++) {  
        PropertyDescriptor descriptor = propertyDescriptors[i];  
        String propertyName = descriptor.getName(); 
        if (map.containsKey(propertyName) && !propertyName.equals("Pkorder")) {  
            // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。  

            Object value = map.get(propertyName); 
            if(value!=null){
            	if(!value.equals(null) && !value.equals("null") && value.toString()!="null"){
            		Field field = null;
            		try {
            			field = type.getDeclaredField(propertyName);
            		} catch (NoSuchFieldException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		} catch (SecurityException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
				 		field.setAccessible(true); 
				 		field.set(obj, value);
				 		/*String setMethod = "set"+propertyName;
            		Method m = null;
					try {
					m = type.getMethod(setMethod, value.getClass());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					m.invoke(obj, value);*/
            	}
            }
        }  
    }  
    return obj;  
}    
}

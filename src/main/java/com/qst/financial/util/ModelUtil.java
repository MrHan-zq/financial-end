package com.qst.financial.util;

import com.qst.financial.modle.base.PoModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@SuppressWarnings("all")
public class ModelUtil<T extends PoModel> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private Class<T> entityClass;
    private String tableName;

    public  List<T> getModel(Class c,List<Map<String, Object>> selectList) {
        List<T> list = new ArrayList<>();
        for (Map<String, Object> map : selectList) {
        	Map<String,Object> resultMap=handleResult(c,map, this.entityClass);
            T t = (T) resultMap.get("t");
            list.add(t);
        }

        return list;
    }

    public  Map<String,Object> getModelList(Class c,List<Map<String, Object>> selectList) {
    	Map<String,Object> endMap = new HashMap<String,Object>();
        List<T> list = new ArrayList<>();
        List<Integer> errorList = new ArrayList<>();
        Integer i=1;
        for (Map<String, Object> map : selectList) {
        	Map<String,Object> resultMap=handleResult(c,map, this.entityClass);
            T t = (T) resultMap.get("t");
            boolean isTrue= (boolean) resultMap.get("isTrue");
            if(isTrue){
            	list.add(t);
            }else{
            	errorList.add(i);
            }
            i++;
        }
        endMap.put("model", list);
        endMap.put("errorList", errorList);
        return endMap;
    }

	private Map<String,Object> handleResult(Class c,Map<String, Object> resultMap, Class<T> tClazz) {
    	//tableName = getTableName(c);
		Map<String,Object> map = new HashMap<String,Object>();
		boolean isTrue=true;
        if (null == resultMap) {
            return null;
        }
        T t = null;
        try {
            t = (T) c.newInstance();
        } catch (InstantiationException e) {
            logger.error("/********************************");
            logger.error("实例化Bean失败(" + this.entityClass + ")!"
                    + e.getMessage());
            logger.error("/********************************");
        } catch (IllegalAccessException e) {
            logger.error("/********************************");
            logger.error("实例化Bean失败(" + this.entityClass + ")!"
                    + e.getMessage());
            logger.error("/********************************");
        }
        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            String key = entry.getKey();
            key=key.toLowerCase();
            key=StringConverting.camelName(key);
            Serializable val = (Serializable) entry.getValue();
            try {
            	boolean setFile= setFileValue(t, key, val);
            	if(!setFile){
            		isTrue=false;
            		break;
            	}
            } catch (Exception e) {
                // TODO: handle exception
                logger.error("/********************************");
                logger.error("/实例化Bean失败(" + this.entityClass + ")不能赋值到字段(" + key + "):"
                        + e.getMessage());
                logger.error("/********************************");
            }
        }
        map.put("t", t);
        map.put("isTrue", isTrue);
        return map;
    }
    
	/**
	 * 将某个�?��?�过反射强制赋给实体�?
	 * @param PoModel
	 * @param fileName
	 * @param fileValue
	 * @return
	 */
	public static <T> boolean setFileValue(T t, String fileName, Object aval){
		Class<?> thisClass = t.getClass();
		fileName=fileName.replaceAll(" ", ""); 
		try {
			if ("id".equalsIgnoreCase(fileName)) {
				try {
					Class<?> clazz = thisClass.getSuperclass();
					Field field = clazz.getDeclaredField(fileName);
					String calssName = field.getType().getName();
					if (calssName.equals("int") || calssName.equals("java.lang.Integer")) {
						if (Integer.MAX_VALUE >  new Integer("" + aval)) {
							Integer val = new Integer("" + aval);
							Method method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1), field.getType());
							method.invoke(t, val);
							return true;
						}else{
							return false;
						}
					}else if(calssName.equals("long") || calssName.equals("java.lang.Long")){
						Long val = new Long("" + aval);
						Method method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1), field.getType());
						method.invoke(t, val);
						return true;
					}else if (aval instanceof String) {
						aval = new String("" + aval);
						Method method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1), field.getType());
						method.invoke(t, aval);
						return true;
					}else{
						aval = new String("" + aval);
						Method method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1), field.getType());
						method.invoke(t, aval);
						return true;
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
			if (null != aval) {
				try {
					fileName=fileName.replaceAll(" ", ""); 
					fileName.trim();
					System.out.println("****"+fileName+"****");
					Field fields = thisClass.getDeclaredField(fileName);
					Method method = null;
					if ("subjectCode".equalsIgnoreCase(fileName)) {
						aval = new String("" + aval);
						method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1), fields.getType());
					}else{
						if (aval instanceof String) {
							if(fields.getType()== BigDecimal.class){
								if(aval.equals("")||aval.equals(" ")){
									aval = new BigDecimal("0");
								}else{
									if(isNumericzidai(aval.toString())){
										aval = new BigDecimal("" + aval);
									}else {
										aval = null;
									}
									System.out.println(fileName+"++++++"+aval);
									
									System.out.println("@@@@"+aval);
								}
								method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1), fields.getType());
							}else{
								aval = new String("" + aval);
								method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1), fields.getType());
							}
							
						}else if(aval instanceof Integer){
							if(fields.getType()== String.class){
								aval = new String("" + aval);
								method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1),fields.getType());
							}else{
								aval = new Integer("" + aval);
								method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1),fields.getType());
							}
						}else if(aval instanceof Long){
							aval = new Long("" + aval);
							method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1), fields.getType());
						}else if(aval instanceof Double){
							aval = new Double("" + aval);
							method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1), fields.getType());
						}else if(aval instanceof Short){
							aval = new Short("" + aval);
							method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1), fields.getType());
						}else if(aval instanceof BigDecimal){
							if(fields.getType()== String.class){
								aval = new String("" + aval);
								method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1),fields.getType());
							}else{
								aval = new BigDecimal("" + aval);
								method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1), fields.getType());
							}	
						}else{
							method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1), fields.getType());
						}
					}
						
					
					method.invoke(t, aval);
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean isNumericzidai(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}

package com.qst.financial.util;

import com.qst.financial.util.common.MethodUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CloneClass {
	public static <T> T getClone(T a,T b){
		Class<?> aClass = a.getClass();
		Class<?> bClass = b.getClass();
		Field[] afs = aClass.getDeclaredFields();
		Field[] bfs = bClass.getDeclaredFields();
		try {
			for(int i = 0;i < afs.length;i++){
				for(int j=0;j<bfs.length;j++){
					Field f = afs[i];
					f.setAccessible(true);
					//System.out.println("i"+f.getName());
					if(afs[i].getName().equals(bfs[j].getName())){                          //���������������һ��
						//System.out.println("j:"+bfs[j].getName());
						//Field af = afs[i];
						Object aval=afs[i].get(a);                                       //�õ�ֵ
						//System.out.println(aval);
						setFileValue(b, afs[i].getName(), aval);
					}
				}
			}
		}catch(Exception e){
			e.getStackTrace();
		}
		return b;
	}
	public static <T> boolean setFileValue(T t, String fileName, Object aval){
		Class<?> thisClass = t.getClass();
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
							/*throw new AiyiIdTypeException("ID type is not a corresPoModelnding type at " + "set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1) + "\n"
									+ "the will give value type is " + fileValue.getClass().getName() + "\n" 
									+ "the filed type type is " + field.getType().getName());*/
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
					Field fields = thisClass.getDeclaredField(fileName);
					Method method = null;
					if (aval instanceof String) {
						aval = new String("" + aval);
						method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1), fields.getType());
					}else if(aval instanceof Integer){
						aval = new Integer("" + aval);
						method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1),fields.getType());
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
						aval = new BigDecimal("" + aval);
						method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1), fields.getType());
					}else{
						method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1), fields.getType());
					}
					method.invoke(t, aval);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 克隆最新方法
	 * @param a
	 * @param bClass
	 * @return
	 */
	public static <T> Object getClone(T a,Class<?> bClass){
		Class<T> aClass=(Class<T>) a.getClass();
		List<Field> aFields=getAllField(aClass);
		List<Field> bFields=getAllField(bClass);
		try {
			Object target=bClass.newInstance();
			for(int i=0;i<bFields.size();i++){
				Field targetField=bFields.get(i);
				targetField.setAccessible(true);
				String targetName=targetField.getName();
				for(int j=0;i<aFields.size();j++){
					Field sourceField=aFields.get(i);
					sourceField.setAccessible(true);
					if(targetName.equals(sourceField.getName())){
						Object sourceValue=sourceField.get(a);
						if(MethodUtil.isEmpty(sourceValue)){
							continue;
						}
						Method method = bClass.getMethod("set" + targetName.substring(0, 1).toUpperCase() + targetName.substring(1), sourceField.getType());
						setFileValue(target, method, sourceValue);
					}
				}
			}
			return target;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public static <T> boolean setFileValue(T t, Method method, Object aval){
		
		try {
			if (aval instanceof String) {
				aval = new String("" + aval);
			}else if(aval instanceof Integer){
				aval = new Integer("" + aval);
			}else if(aval instanceof Long){
				aval = new Long("" + aval);
			}else if(aval instanceof Double){
				aval = new Double("" + aval);
			}else if(aval instanceof Short){
				aval = new Short("" + aval);
			}else if(aval instanceof BigDecimal){
				aval = new BigDecimal("" + aval);
			}
			method.invoke(t, aval);
			return true;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static List<Field> getAllField(Class clazz){
		List<Field> fieldList=new ArrayList<>();
		if(clazz!=null){
			//当分类为null的时候说明到达了最上层父类
			fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
			//得到父类，然后赋予给自己
			List<Field> parentFieldList=getAllField(clazz.getSuperclass());
			fieldList.addAll(parentFieldList);
		}
		return fieldList;
	}
}







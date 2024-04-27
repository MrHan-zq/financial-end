package com.qst.financial.sql;

import com.qst.financial.core.FieldName;
import com.qst.financial.core.Pram;
import com.qst.financial.core.TableName;
import com.qst.financial.core.TempField;
import com.qst.financial.modle.base.PoModel;
import com.qst.financial.util.StringConverting;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SqlUtil<T extends PoModel> {
	
	
	


	/**
	 * @param t
	 * @param fieldName
	 * @return
	 */
	public Field getField(Class<?> t, String fieldName){
		Field[] fields = t.getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}
		return null;
	}
	
	/**
	 * ��ȡ��ѯsql���ֶ��б�
	 * @param PoModel
	 * @return
	 */
	public List<Pram> getPramListOfSelect(PoModel PoModel){
		List<Pram> list = new ArrayList<>();
		Class<? extends PoModel> thisClass = PoModel.getClass();
	    Field[] fields = thisClass.getDeclaredFields();
	    for(Field f : fields){
	    	try {
	    		if (!f.isAnnotationPresent(TempField.class)) {
	    			String fName = f.getName();
	    			//�ж��Ƿ���boolean����
	    			String get = "get";
	    			String fieldType = f.getGenericType().toString();
	    			if (fieldType.indexOf("boolean") != -1 || fieldType.indexOf("Boolean") != -1) {
	    				get = "is";
					}
	    			if (f.isAnnotationPresent(FieldName.class)) {
	    				String fieldName = f.getAnnotation(FieldName.class).name();
						Pram pram = new Pram(fieldName + " as " + fName, thisClass.getMethod(get + fName.substring(0, 1).toUpperCase() + fName.substring(1)).invoke(PoModel));
						list.add(pram);
	    			}else{
	    				String fieldName = toTableString(fName);
	    				Pram pram = new Pram(fieldName + " as " + fName, thisClass.getMethod(get + fName.substring(0, 1).toUpperCase() + fName.substring(1)).invoke(PoModel));
						list.add(pram);
					}
				}
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	    }
		return list;
	}
	
	/**
	 * 
	 * @param PoModel
	 * @return
	 */
	public String getTableName(PoModel PoModel){
		Class<? extends PoModel> c = PoModel.getClass();
		if(c.isAnnotationPresent(TableName.class)){
			return c.getAnnotation(TableName.class).name();
		}else{
			String className = PoModel.getClass().getSimpleName();
			String tName = toTableString(className);
			String PoModelName = tName.substring(tName.length() - 2, tName.length());
			if("PoModel".equals(PoModelName)){
				tName = tName.substring(0,tName.length() - 3);
			}
			return tName;
		}
		
	}
	
	public static<T extends PoModel> List<Pram> getPramListofStatic(PoModel PoModel){
		List<Pram> list = new ArrayList<>();
		Class<? extends PoModel> thisClass = PoModel.getClass();
		Class superClass = PoModel.getClass().getSuperclass();
		//Class userCla = (Class) PoModel.getClass();
		Field[] fs = thisClass.getDeclaredFields();
		Field[] superfs = superClass.getDeclaredFields();
		try {
			for(int j = 0;j < superfs.length;j++){
				Field superf = superfs[j];
				if(!superf.getName().equals("serialVersionUID")){
					superf.setAccessible(true); 		// 设置些属性是可以访问的            
					Object sval = superf.get(PoModel);	
					Pram spram = new Pram(StringConverting.underscoreName(superf.getName()), sval);
					list.add(spram);
				}
			}
			for(int i = 0;i < fs.length;i++){
				Field f = fs[i];
				if(!f.getName().equals("serialVersionUID")){
					f.setAccessible(true); 		// 设置些属性是可以访问的            
					Object val = f.get(PoModel);	
					Pram pram = new Pram(StringConverting.underscoreName(f.getName()), val);
					list.add(pram);
				}
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}
	
	/**
	 * ͨ��Class��ȡ���ɶ�ӦSql�ֶ�
	 * @param PoModel
	 * @return
	 */
	public List<Pram> getPramList(Class<T> PoModel){
		List<Pram> list = new ArrayList<>();
		Class<? extends PoModel> thisClass = PoModel;
	    Field[] fields = thisClass.getDeclaredFields();
	    	try {
	    		Object o = thisClass.newInstance();
	    		for(Field f : fields){
		    		if(!f.getName().equalsIgnoreCase("ID") && !f.isAnnotationPresent(TempField.class)){
		    			String fName = f.getName();
		    			
		    			//�ж��Ƿ���boolean����
		    			String getf = "get";
		    			String fieldType = f.getGenericType().toString();
		    			if (fieldType.indexOf("boolean") != -1 || fieldType.indexOf("Boolean") != -1) {
		    				getf = "is";
						}
		    			if (f.isAnnotationPresent(FieldName.class)) {
		    				String fieldName = f.getAnnotation(FieldName.class).name();
		    				Method get = thisClass.getMethod(getf + fName.substring(0, 1).toUpperCase() + fName.substring(1));
		    				Object getValue = get.invoke(o);
		    				Pram pram = new Pram(fieldName, getValue);
							list.add(pram);
		    			}else{
		    				String fieldName = toTableString(fName);
		    				Method get = thisClass.getMethod(getf + fName.substring(0, 1).toUpperCase() + fName.substring(1));
		    				Object getValue = get.invoke(o);
		    				Pram pram = new Pram(fieldName , getValue);
							list.add(pram);
						}
		    		}
	    		}
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
	}
	
	public List<Pram> getPramList(T PoModel){
		List<Pram> list = new ArrayList<>();
		Class<? extends PoModel> thisClass = PoModel.getClass();
	    Field[] fields = thisClass.getDeclaredFields();
	    	try {
	    		for(Field f : fields){
		    		if(!f.getName().equalsIgnoreCase("ID") && !f.isAnnotationPresent(TempField.class)){
		    			String fName = f.getName();
		    			
		    			//�ж��Ƿ���boolean����
		    			String getf = "get";
		    			String fieldType = f.getGenericType().toString();
		    			if (fieldType.indexOf("boolean") != -1 || fieldType.indexOf("Boolean") != -1) {
		    				getf = "is";
						}
		    			if (f.isAnnotationPresent(FieldName.class)) {
		    				String fieldName = f.getAnnotation(FieldName.class).name();
		    				Method get = thisClass.getMethod(getf + fName.substring(0, 1).toUpperCase() + fName.substring(1));
		    				Object getValue = get.invoke(PoModel);
		    				Pram pram = new Pram(fieldName, getValue);
							list.add(pram);
		    			}else{
		    				String fieldName = toTableString(fName);
		    				Method get = thisClass.getMethod(getf + fName.substring(0, 1).toUpperCase() + fName.substring(1));
		    				Object getValue = get.invoke(PoModel);
		    				Pram pram = new Pram(fieldName , getValue);
							list.add(pram);
						}
		    		}
	    		}
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
	}
	
	
	/**
	 * ͨ��Class��ȡ���ɶ�ӦSql��ѯ���ֶ�
	 * @param PoModel
	 * @return
	 */
	public List<Pram> getPramListOfSelect(Class<T> PoModel){
		List<Pram> list = new ArrayList<>();
		Class<? extends PoModel> thisClass = PoModel;
		//Class userCla = (Class) PoModel.getClass();
	    Field[] fields = thisClass.getDeclaredFields();
	    try {
	    	String ids="id";
	    	Pram idp=new Pram();
	    	idp.setFile(ids);
	    	list.add(idp);
	    	for(int i = 0;i < fields.length;i++){
	    		Field f = fields[i];
	    		if(!f.getName().equals("serialVersionUID")){
	    			Pram p=new Pram();
	    			f.setAccessible(true);
	    			//Object val = f.get(PoModel);
	    			String str=StringConverting.underscoreName(f.getName());
	    			p.setFile(str);
	    			list.add(p);
	    		}
	    	}
	    		/*Object o = thisClass.newInstance();
	    		for(Field f : fields){
	    			if (!f.isAnnotationPresent(TempField.class)) {
	    				String fName = f.getName();
	    				//�ж��Ƿ���boolean����
		    			String getf = "get";
		    			String fieldType = f.getGenericType().toString();
		    			if (fieldType.indexOf("boolean") != -1 || fieldType.indexOf("Boolean") != -1) {
		    				getf = "is";
						}
		    			if (f.isAnnotationPresent(FieldName.class)) {
		    				String fieldName = f.getAnnotation(FieldName.class).name();
		    				Method get = thisClass.getMethod(getf + fName.substring(0, 1).toUpperCase() + fName.substring(1));
		    				Object getValue = get.invoke(o);
		    				Pram pram = new Pram(fieldName + " as " + fName, getValue);
							list.add(pram);
		    			}else{
		    				String fieldName = toTableString(fName);
		    				Method get = thisClass.getMethod(getf + fName.substring(0, 1).toUpperCase() + fName.substring(1));
		    				Object getValue = get.invoke(o);
		    				Pram pram = new Pram(fieldName + " as " + fName, getValue);
							list.add(pram);
						}
					}
	    		}*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}
	
	/**
	 * ͨ��Class��ȡ���ɶ�ӦSql��ѯ���ֶ�
	 * @param PoModel
	 * @return
	 */
	public List<Pram> getPramListByBean(Class<T> PoModel){
		List<Pram> list = new ArrayList<>();
		Class<?> thisClass = PoModel;
	    Field[] fields = thisClass.getDeclaredFields();
	    try {
    		Object o = thisClass.newInstance();
    		for(Field f : fields){
	    		if(!f.getName().equalsIgnoreCase("ID") && !f.isAnnotationPresent(TempField.class)){
	    			
	    			String fName = f.getName();
	    			
	    			//�ж��Ƿ���boolean����
	    			String getf = "get";
	    			String fieldType = f.getGenericType().toString();
	    			if (fieldType.indexOf("boolean") != -1 || fieldType.indexOf("Boolean") != -1) {
	    				getf = "is";
					}
	    			if (f.isAnnotationPresent(FieldName.class)) {
	    				String fieldName = f.getAnnotation(FieldName.class).name();
	    				Method get = thisClass.getMethod(getf + fName.substring(0, 1).toUpperCase() + fName.substring(1));
	    				Object getValue = get.invoke(o);
	    				Pram pram = new Pram(fieldName + " as " + fName, getValue);
						list.add(pram);
	    			}else{
	    				String fieldName = toTableString(fName);
	    				Method get = thisClass.getMethod(getf + fName.substring(0, 1).toUpperCase() + fName.substring(1));
	    				Object getValue = get.invoke(o);
	    				Pram pram = new Pram(fieldName + " as " + fName, getValue);
						list.add(pram);
					}
	    			
	    		}
    		}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * ͨ��Class��ȡ���ɶ�ӦSql��ѯ���ֶ�
	 * @param PoModel
	 * @return
	 */
	public List<Pram> getPramListByBeanOfSelect(Class<T> PoModel){
		List<Pram> list = new ArrayList<>();
		Class<?> thisClass = PoModel;
	    Field[] fields = thisClass.getDeclaredFields();
	    try {
    		Object o = thisClass.newInstance();
    		for(Field f : fields){
    			if (!f.isAnnotationPresent(TempField.class)) {
    				String fName = f.getName();
    				//�ж��Ƿ���boolean����
	    			String getf = "get";
	    			String fieldType = f.getGenericType().toString();
	    			if (fieldType.indexOf("boolean") != -1 || fieldType.indexOf("Boolean") != -1) {
	    				getf = "is";
					}
	    			if (f.isAnnotationPresent(FieldName.class)) {
	    				String fieldName = f.getAnnotation(FieldName.class).name();
	    				Method get = thisClass.getMethod(getf + fName.substring(0, 1).toUpperCase() + fName.substring(1));
	    				Object getValue = get.invoke(o);
	    				Pram pram = new Pram(fieldName + " as " + fName, getValue);
						list.add(pram);
	    			}else{
	    				String fieldName = toTableString(fName);
	    				Method get = thisClass.getMethod(getf + fName.substring(0, 1).toUpperCase() + fName.substring(1));
	    				Object getValue = get.invoke(o);
	    				Pram pram = new Pram(fieldName + " as " + fName, getValue);
						list.add(pram);
					}
				}
    		}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * ��ȡSql�ֶ���
	 * @param PoModel
	 * @return
	 */
	public String getTableName(Class<T> PoModel){
		if(PoModel.isAnnotationPresent(TableName.class)){
			return PoModel.getAnnotation(TableName.class).name();
		}else{
			String tName = toTableString(PoModel.getSimpleName());
			String PoModelName = tName.substring(tName.length() - 2, tName.length());
			if("PoModel".equals(PoModelName)){
				tName = tName.substring(0,tName.length() - 3);
			}
			return tName;
		}
	}
	
	/**
	 * ��ȡSql�ֶ���
	 * @param PoModel
	 * @return
	 */
	public String getTableNameByBean(Class<T> PoModel){
		if(PoModel.isAnnotationPresent(TableName.class)){
			return PoModel.getAnnotation(TableName.class).name();
		}else{
			String tName = toTableString(PoModel.getSimpleName());
			if("PoModel".equals(tName.substring(tName.length() - 3, tName.length() - 1))){
				tName = tName.substring(0,tName.length() - 3);
			}
			return tName;
		}
	}
	
	/**
	 * 获取实体类中的某个�??
	 * @param PoModel
	 * @param fileName
	 * @return
	 */
	public static<T> Serializable getFileValue(Class<T> PoModel, String fileName){
		try {
			Method method = PoModel.getMethod("get" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1));
			Object o = PoModel.newInstance();
			Object invoke = method.invoke(o);
			return null == invoke ? null : (Serializable)invoke;
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 取字段名
	 * @param PoModel
	 * @param fileName
	 * @return
	 */
	public Serializable getFileValue(PoModel PoModel, String fileName){
		try {
			Class<? extends PoModel> cla = PoModel.getClass();
			Method method = cla.getMethod("get" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1));
			Object o = PoModel;
			Object invoke = method.invoke(o);
			return null == invoke ? null : (Serializable)invoke;
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	
	/**
	 * 将某个�?��?�过反射强制赋给实体�?
	 * @param t
	 * @param fileName
	 * @param aval
	 * @return
	 */
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
	 * �շ��ʶת�»��߱�ʶ
	 * @param text
	 * @return
	 */
	public String toTableString(String text){
		String tName = text.substring(0, 1).toLowerCase();
		for(int i = 1; i < text.length(); i++){
			if(!Character.isLowerCase(text.charAt(i))){
				tName += "_";
			}
			tName += text.substring(i, i + 1);
		}
		return tName.toLowerCase();
	}

	public String getTableNameByClazz(Class<? extends PoModel> PoModel) {
		// TODO Auto-generated method stub
		if(PoModel.isAnnotationPresent(TableName.class)){
			return PoModel.getAnnotation(TableName.class).name();
		}else{
			String tName = toTableString(PoModel.getSimpleName());
			if("PoModel".equals(tName.substring(tName.length() - 3, tName.length() - 1))){
				tName = tName.substring(0,tName.length() - 3);
			}
			return tName;
		}
	}
	
}

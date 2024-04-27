package com.qst.financial.util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

public class CalculateUtils {

	public static boolean strToLj(String str,Map<String, BigDecimal> params) throws ScriptException {  
		try{
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
            if(null != params && !params.isEmpty()){
                Iterator<String> keys = params.keySet().iterator() ;
                while(keys.hasNext()){
                    String key = keys.next() ;
                    if(str.contains(key)){
                        engine.put(key, params.get(key));
                    }
                }
            }
            Object result = engine.eval(str);
            if(null != result){
                return (boolean)result;
            }
            return false ;
        }catch (Exception e){
		    return false;
        }
	}  

	public static BigDecimal strToSs(String str,Map<String, BigDecimal> params) throws ScriptException {  
		ScriptEngineManager manager = new ScriptEngineManager();  
		ScriptEngine engine = manager.getEngineByName("js");
		if(null != params && !params.isEmpty()){
			Iterator<String> keys = params.keySet().iterator() ;
			while(keys.hasNext()){
				String key = keys.next() ;
				if(str.contains(key)){
					engine.put(key, params.get(key));
				}
			}
		}
		Object result = engine.eval(str); 
		if(null != result){
			if(result instanceof BigDecimal){
				return (BigDecimal)result ;
			}
			else if(result instanceof Double){
				return new BigDecimal((Double)result).setScale(2,BigDecimal.ROUND_HALF_UP) ;
			}
			else{
				return null ;
			}
		}
		return null ;
	}  

	/*public static void main(String[] args) throws ScriptException { 
		String str = "{(B-B1)+100}fdsaf{(B-B1)+100}dsafds" ;
		System.out.println(str.replace("{(B-B1)+100}", "100"));
	}  */
}

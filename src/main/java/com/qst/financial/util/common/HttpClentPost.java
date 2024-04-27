package com.qst.financial.util.common;

import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * post请求
 * @author qst
 *
 */
public class HttpClentPost {
	/**
	 * post传json
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String postJson(String url, Map<String, Object> params) {
		HttpPost method = new HttpPost(url);  
		CloseableHttpClient httpClient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();//设置请求和传输超时时间
		method.setConfig(requestConfig);
		if(params!=null){
			JSONObject jsonParam = new JSONObject();  
			Set<String> keySet = params.keySet();
			for(String key : keySet) {
				jsonParam.put(key, params.get(key));
			}
			StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题    
	        entity.setContentEncoding("UTF-8");    
	        entity.setContentType("application/json");    
	        method.setEntity(entity); 
		}
        String resData="";
		try {
			HttpResponse result = httpClient.execute(method);  
			resData = EntityUtils.toString(result.getEntity());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        return resData;
	}
}




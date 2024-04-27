package com.qst.financial.util.common;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;



/**
 * 
 * 验证码相关
 *
 */
public class HttpClientPhone {
	public static final Integer NUM = 6;
	private static final Logger log = Logger.getLogger(HttpClientPhone.class);
	/*
	 * 消息发送
	 */
	public static boolean sendSms(String msg,String phone) {
		try {
			if(phone==null || phone.length()==0){
				return false;
			}
			HttpClient httpclient=new HttpClient();
			httpclient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			Properties p=new Properties();
			String url=p.getProperty("url");
			String act=p.getProperty("act");
			String user=p.getProperty("user");
			String passwd=p.getProperty("passwd");
			PostMethod postmethod=new PostMethod(url);
			Date dt=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("");
			String sendtime=sdf.format(dt);
			NameValuePair[] date = { new NameValuePair("act",act),
				new NameValuePair("user",user),
				new NameValuePair("passwd",passwd),
				new NameValuePair("msg",msg),
				new NameValuePair("sendtime",sendtime),
				new NameValuePair("phone",phone.toString())};
			postmethod.setRequestBody(date);
			int responseCode=httpclient.executeMethod(postmethod);
			if(responseCode == HttpURLConnection.HTTP_OK){
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			log.error(e);
			return false;
		}
	}
	
	//产生四个随机数
	public static char[] getRandom()
	{
		//源始的内容
		String str = "0123456789";
		//存放随机数
		char[] rands = new char[NUM];
		Random  random   = new Random();
		for (int i=0;i<NUM;i++)
		{
			int index = random.nextInt(10);
			rands[i] = str.charAt(index);
		}
		return rands;
	}
	
	/*public static void main(String[] args) {
		List<String> list=new ArrayList<>();
		String phone="13058127893";
		//list.add("18676576551");
		HttpClientPhone.sendSms("您好",phone);
	}*/
}

package com.qst.financial.util.common;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Properties;



	/**
	 * 
	 * @param url 应用地址，类似于http://ip:port/msg/
	 * @param un 账号
	 * @param pw 密码
	 * @param phone 手机号码，多个号码使用","分割
	 * @param msg 短信内容
	 * @param rd 是否需要状态报告，需要1，不需要0
	 * @return 返回值定义参见HTTP协议文档
	 * @throws Exception
	 */
public class HttpSender {
	
	public static String batchSend(String phone, String msg,
		String rd, String ex) throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager());
		Properties p=new Properties();
		p.load(HttpSender.class.getResourceAsStream("/configs/sendSms.properties"));
		String url=p.getProperty("url");
		String un=p.getProperty("user");
		String pw=p.getProperty("passwd");
		/*String url="https://sms.253.com/msg/";
		String un="N3947165";
		String pw="lwUWIn3MKjc065";*/
		GetMethod method = new GetMethod();
		try {
			URI base = new URI(url, false);
			method.setURI(new URI(base, "send", false));
			method.setQueryString(new NameValuePair[] { 
				new NameValuePair("un", un),
				new NameValuePair("pw", pw), 
				new NameValuePair("phone", phone),
				new NameValuePair("rd", rd), 
				new NameValuePair("msg", msg),
				new NameValuePair("ex", ex), 
			});
			int result = client.executeMethod(method);
			if (result == HttpStatus.SC_OK) {
				InputStream in = method.getResponseBodyAsStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = in.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				return URLDecoder.decode(baos.toString(), "UTF-8");
			} else {
				throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
			}
		} finally {
			method.releaseConnection();
		}
	}
	
	/*public static void main(String[] args) {

		String url = "https://sms.253.com/msg/";// 应用地址
		String un = "N3947165";// 账号
		String pw = "lwUWIn3MKjc065";// 密码
		String phone = "13058127893";// 手机号码，多个号码使用","分割
		String msg = "【微商人脉宝】您好，你的验证码是123456";// 短信内容
		String rd = "1";// 是否需要状态报告，需要1，不需要0
		String ex = null;// 扩展码

		
		try {
			String returnString = HttpSender.batchSend( phone, msg, rd, ex);
			System.out.println(returnString);
			// TODO 处理返回值,参见HTTP协议文档
		} catch (Exception e) {
			// TODO 处理异常
			e.printStackTrace();
		}
	}	*/
	
	
}
	
	
	
	
	
	
	
package com.qst.financial.util.common;

import com.qst.financial.dto.Urmtopers;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class Ip138 {
	private final static Logger log = Logger.getLogger(Ip138.class);
	public static Urmtopers  getAddressByIP(String strIP) {
		try {
			Urmtopers urm=new Urmtopers();
			//String strIP = "";     
			URL url = new URL( "http://www.ip138.com/ips138.asp?ip="+strIP+"&action=2");  
			URLConnection conn = url.openConnection();    
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));  
			String line = null;      
			StringBuffer result = new StringBuffer();  
			while((line = reader.readLine()) != null){
				result.append(line);      
			}   
			reader.close(); 
			log.info("【总结果是："+result+"】");
			int s=(result.indexOf("本站数据："));
			log.info("============================"+s);
			String strIps=result.substring(result.indexOf("本站数据：")+6,result.indexOf("</li>"));
			log.info("【截取后是："+strIps+"】");
			String address="";
			String pro="";
			String city="";
			if(strIps.indexOf("省")>=0 && strIps.indexOf("省")!=strIps.indexOf("全省")+1)
			{
				address="中国";
				pro=strIps.substring(0,strIps.indexOf("省")+1);
				if(strIps.indexOf("市")>=0 && strIps.indexOf("市")==strIps.lastIndexOf("市")){
					city=strIps.substring(strIps.indexOf("省")+1,strIps.indexOf("市")+1);
				}else{
					String yy=strIps.substring(strIps.length()-1,strIps.length());
					if(yy.equals("信") || yy.equals("通") || yy.equals("动")){
						city=strIps.substring(strIps.indexOf("省")+1,strIps.length()-3);
					}else{
						city=strIps.substring(strIps.indexOf("省")+1);
					}
					int pdlengh=strIps.indexOf("CDM");
					if(pdlengh>=0){
					city=strIps.substring(strIps.indexOf("省")+1,pdlengh-1);
					}
					int qslegh=strIps.indexOf("全省");
					if(qslegh>=0 && pdlengh<0){
						city=strIps.substring(strIps.indexOf("省")+1,qslegh-1);
					}
					int dxlegh=strIps.indexOf("电信CDMA");
					if(dxlegh>=0  && qslegh<0){
						city=strIps.substring(strIps.indexOf("省")+1,dxlegh-1);
					}
				}
			}else {
				String firstStr=strIps.substring(0,2);
				address="中国";
				if(firstStr.equals("广西") || firstStr.equals("内蒙") || firstStr.equals("新疆") ||firstStr.equals("西藏")||  firstStr.equals("宁夏")){
					pro=strIps.substring(0,strIps.indexOf("区")+1);
					if(strIps.indexOf("市")>=0 && strIps.indexOf("市")==strIps.lastIndexOf("市")){
						city=strIps.substring(strIps.indexOf("区")+1,strIps.indexOf("市")+1);
					}else{
						String yy=strIps.substring(strIps.length()-1,strIps.length());
						int pdlengh=strIps.indexOf("CDM");
						int qslegh=strIps.indexOf("全省");
						if(yy.equals("信") || yy.equals("通") || yy.equals("动")|| yy.equals("全省")){
							city=strIps.substring(strIps.indexOf("区")+1,strIps.length()-3);
						}else{
							city=strIps.substring(strIps.indexOf("区")+1);
						}
						if(pdlengh>=0){
							city=strIps.substring(strIps.indexOf("区")+1,pdlengh-1);
						}
						if(qslegh>=0 && pdlengh<0){
							city=strIps.substring(strIps.indexOf("区")+1,qslegh-1);
						}
						int dxlegh=strIps.indexOf("电信CDMA");
						if(dxlegh>=0 && qslegh<0){
							city=strIps.substring(strIps.indexOf("区")+1,dxlegh-1);
						}
					}
				}else if(firstStr.equals("香港")){
					pro="香港特别行政区";
				}else if(firstStr.equals("澳门")){
					pro="澳门特别行政区";
				}else{
					int firstCity=strIps.indexOf("市");
					if(firstCity>0){
						pro=strIps.substring(0,firstCity);
						city=strIps.substring(0, firstCity+1);
					}else{
						address="未知";
					}
				}
			}
			urm.setAddress(address);
			urm.setPros(pro);
			urm.setCity(city);
		//	System.out.println(strIps);
			return urm;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	/*public static void main(String[] args) {
		Urmtopers urm=getAddressByIP("183.206.102.20");
		System.out.println(urm.getAddress()+":"+urm.getPros()+":"+urm.getCity());
	}*/
	
}



package com.qst.financial.util;

import com.qst.financial.modle.base.SysUserModel;
import com.qst.financial.modle.subject.*;

import java.io.IOException;
import java.util.*;

public class ReportUtil {
	
	/**
	 * 根据Excel数据和数据库表字段配置组合Map数据
	 * @param list Excel原始数据
	 * @param tReportPropertyList 数据库表字段配置
	 * @param manyRow 是否多行
	 * @param beginRow 开始行(多行)
	 * @param endRow 结束行(多行)
	 * @param user 登录用户信息
	 */
	public static List<Map<String, Object>> getHashMapValue(
            List<Object[]> list, List<TReportProperty> tReportPropertyList, Boolean manyRow, Integer beginRow, Integer endRow, SysUserModel user) throws IOException {
		list.removeAll(Collections.singleton(null)); 
        List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
		if(manyRow){//多行
			for(int i = beginRow;i <= list.size()-endRow-1;i++){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("ID", UUID.randomUUID().toString().replace("-", ""));
				map.put("IMP_USER", Long.toString(user.getId()));
				map.put("ORG_ID", Long.toString(user.getOrgId()));
				map.put("ACCOUNT_PORT_TYPE", user.getAccountPortType());
				for(int j = 0;j <= tReportPropertyList.size()-1;j++){
	            	String code=tReportPropertyList.get(j).getProCode();
	            	int row=tReportPropertyList.get(j).getProRow();
	            	int column=tReportPropertyList.get(j).getProColumn();
	            	System.out.println("i"+i);
	            	System.out.println(j);
	            	if(tReportPropertyList.get(j).getProMoneyRow()==1){
	            		if(list.get(i).length!=1){//此处判断余额表中最后多了一行为空的数据
	            			map.put(code, list.get(i)[column]==null ||"".equals(list.get(i)[column]) ? null : list.get(i)[column]);
	            		}
	            	}else {
	            		map.put(code, list.get(row)[column]);
	            	}
	            }
				listMap.add(map);
		    } 
		}else{//单行
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("ID", UUID.randomUUID().toString().replace("-", ""));
			map.put("IMP_USER", Long.toString(user.getId()));
			map.put("ORG_ID", Long.toString(user.getOrgId()));
			map.put("ACCOUNT_PORT_TYPE", user.getAccountPortType());
			for(int i = 0;i <= tReportPropertyList.size()-1;i++){
				String code=tReportPropertyList.get(i).getProCode();
				int row=tReportPropertyList.get(i).getProRow();
				int column=tReportPropertyList.get(i).getProColumn();
				String name=tReportPropertyList.get(i).getProName();
				String[] names=name.split("\\/");
				List<String> nameArr=new ArrayList();
				for(int a= 0;a <= names.length-1;a++){
					nameArr.add(names[a].trim());
				}
				for(int j = 0;j <= list.size()-1;j++){
					Object [] obj=list.get(j);
					for(int n = 0;n <= obj.length-1;n++){
						if(nameArr.contains(String.valueOf(obj[n]).trim())){
							if(code.equals("BQ_JYHD_JYHDCSDJE")) {
								System.out.print("code:"+code+"value:"+list.get(j)[column]+"j:"+j+"column:"+column);
							}
							map.put(code, list.get(j)[column]);
						}
					}
				}
			}
			listMap.add(map);
		}
		return listMap;
	}

}

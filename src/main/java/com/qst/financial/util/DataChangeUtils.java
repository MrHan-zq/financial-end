package com.qst.financial.util;

import com.qst.financial.dto.ResultByMdyRuleDto;
import com.qst.financial.dto.ResultDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataChangeUtils {
	public static final String DATA_CHANGE_TYPE_BY_CODE = "1" ;
	public static final String DATA_CHANGE_TYPE_BY_ARR = "2" ;
	public static final String CASH_FLOW_REPORT_TYPE = "3" ;
	
	public static Map<String, Object> getDatas(List<ResultDto> lists,String type){
		Map<String, Object> map = new HashMap<>() ;
		for(ResultDto bean : lists){
			if(DATA_CHANGE_TYPE_BY_CODE.equals(type)){
				map.put(bean.getCode(), bean);
			}
			else if(DATA_CHANGE_TYPE_BY_ARR.equals(type)){
				map.put(bean.getListArr() + "", bean);
			}
		}
		return map ;
	}
	
	public static List<ResultByMdyRuleDto> getDataByMdyRule(List<ResultDto> lists){
		List<ResultByMdyRuleDto> reList = new ArrayList<>() ;
		Map<String,List<ResultDto>> map = new HashMap<>() ;
		for(ResultDto bean : lists){
			String useArea = bean.getUseArea() ;
			if(useArea.length() == 2 && null == map.get(useArea)){
				ResultByMdyRuleDto res = new ResultByMdyRuleDto();
				res.setTitle(bean);
				reList.add(res);
			}
			else if(useArea.length() == 4 ){
				String tmpStr = useArea.substring(0, 2);
				if(null != map.get(tmpStr)){
					map.get(tmpStr).add(bean);
				}
				else{
					List<ResultDto> list = new ArrayList<>() ;
					list.add(bean);
					map.put(tmpStr, list) ;
				}
			}
		}
		for(ResultByMdyRuleDto bean : reList){
			bean.setContents(map.get(bean.getTitle().getUseArea()));
		}
		return reList ;
	}
	
	public static List<ResultByMdyRuleDto> getDataByMdyRule(List<ResultDto> lists,String reportType){
		List<ResultByMdyRuleDto> reList = new ArrayList<>() ;
		Map<String,List<ResultDto>> map = new HashMap<>() ;
		if(CASH_FLOW_REPORT_TYPE.equals(reportType)){
			ResultByMdyRuleDto res = new ResultByMdyRuleDto();
			ResultDto bean = new ResultDto();
			bean.setCode("XJLL-TMP1");
			bean.setUseArea("31");
			bean.setCodeName("一、经营活动产生的现金流量");
			res.setTitle(bean);
			ResultDto bean1 = new ResultDto();
			bean1.setCode("XJLL-TMP2");
			bean1.setUseArea("32");
			bean1.setCodeName("二、投资活动产生的现金流量");
			res.setTitle(bean1);
			ResultDto bean2 = new ResultDto();
			bean2.setCode("XJLL-TMP3");
			bean2.setUseArea("33");
			bean2.setCodeName("三、筹资活动产生的现金流量");
			res.setTitle(bean2);
			reList.add(res);
		}
		for(ResultDto bean : lists){
			String useArea = bean.getUseArea() ;
			if(useArea.length() == 2 && null == map.get(useArea)){
				ResultByMdyRuleDto res = new ResultByMdyRuleDto();
				res.setTitle(bean);
				reList.add(res);
			}
			else if(useArea.length() == 4 ){
				String tmpStr = useArea.substring(0, 2);
				if(null != map.get(tmpStr)){
					map.get(tmpStr).add(bean);
				}
				else{
					List<ResultDto> list = new ArrayList<>() ;
					list.add(bean);
					map.put(tmpStr, list) ;
				}
			}
		}
		for(ResultByMdyRuleDto bean : reList){
			bean.setContents(map.get(bean.getTitle().getUseArea()));
		}
		return reList ;
	}
	
}

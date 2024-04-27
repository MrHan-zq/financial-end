package com.qst.financial.remote;

import com.qst.financial.dto.SysDictionaryDetailBean;

import java.util.List;



public interface SystemResourceService {
	
	public void addResourceAsString(String key, String value);

	public boolean setResourceAsString(String key, String value);
	
	public boolean delResourceAsString(String key);
	
	public boolean setBusinessDictionary(String key,List<SysDictionaryDetailBean> list);
	
}

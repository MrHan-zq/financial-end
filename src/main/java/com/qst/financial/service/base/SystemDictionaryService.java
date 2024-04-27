package com.qst.financial.service.base;

import com.qst.financial.modle.base.SysDictionaryDetailModel;
import com.qst.financial.modle.base.SysDictionaryModel;

import java.util.List;
import java.util.Map;

	public interface SystemDictionaryService {

	public void addDictionaryAndDictionaryDetail(SysDictionaryModel dictionaryModel,
                                                 String[] detailName, String[] detailValue, String[] detailRemark) throws Exception;

	public void updateDictionaryAndDictionaryDetail(SysDictionaryModel dictionaryModel,
                                                    String[] detailName, String[] detailValue, String[] detailRemark) throws Exception;

	public void deleteDictionaryAndDictionaryDetail(String dictionaryId) throws Exception;
	public  List<SysDictionaryDetailModel> selectByDic(Map<String, String> map);
}

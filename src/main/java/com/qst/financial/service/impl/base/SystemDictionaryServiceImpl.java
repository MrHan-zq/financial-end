package com.qst.financial.service.impl.base;

import com.qst.financial.dao.mapper.base.SysDictionaryDetailMapper;
import com.qst.financial.dao.mapper.base.SysDictionaryMapper;
import com.qst.financial.modle.base.SysDictionaryDetailModel;
import com.qst.financial.modle.base.SysDictionaryModel;
import com.qst.financial.service.base.SystemDictionaryService;
import com.qst.financial.util.common.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SystemDictionaryServiceImpl implements SystemDictionaryService {

	@Autowired
    SysDictionaryMapper sysDictionaryMapper;
	
	@Autowired
    SysDictionaryDetailMapper sysDictionaryDetailMapper;
	public  List<SysDictionaryDetailModel> selectByDic(Map<String,String> map){
		return sysDictionaryDetailMapper.selectByDic(map);
	}
	//@SystemServiceLog(description = "新增系统字典与系统字典明细")
	@Override
	public void addDictionaryAndDictionaryDetail(SysDictionaryModel dictionaryModel,
                                                 String[] detailName, String[] detailValue, String[] detailRemark) throws Exception {
		
		List<SysDictionaryDetailModel> moldelList = new ArrayList<SysDictionaryDetailModel>();
		sysDictionaryMapper.insertSelective(dictionaryModel);

		for (int i = 0; i < detailName.length; i++) {
			SysDictionaryDetailModel detailModel = new SysDictionaryDetailModel();
			if(Common.isNotEmpty(detailName[i]))
			{
				detailModel.setDictionaryId(dictionaryModel.getId().toString());
				detailModel.setName(detailName[i]);
			}
			for (int j = 0; j < detailValue.length; j++) {
				if(i == j)
				{
					if(Common.isNotEmpty(detailValue[j]))
					{
						detailModel.setValue(detailValue[j]);
					}
					break;
				}
			}
			for (int j2 = 0; j2 < detailRemark.length; j2++) {
				if(i == j2)
				{
					if(Common.isNotEmpty(detailRemark[j2]))
					{
						detailModel.setRemark(detailRemark[j2]);
					}
					break;
				}
			}
			if(Common.isNotEmpty(detailName[i]))
			{
				moldelList.add(detailModel);
			}
		}
		if(moldelList.size() > 0)
		{
			sysDictionaryDetailMapper.insertBatchDictionaryDetail(moldelList);
		}
	}

	//@SystemServiceLog(description = "更新系统字典与系统字典明细")
	@Override
	public void updateDictionaryAndDictionaryDetail(
            SysDictionaryModel dictionaryModel, String[] detailName,
            String[] detailValue, String[] detailRemark) throws Exception {
		
		sysDictionaryMapper.updateByPrimaryKeySelective(dictionaryModel);
		sysDictionaryDetailMapper.deleteByDictionaryId(dictionaryModel.getId());
		
		List<SysDictionaryDetailModel> moldelList = new ArrayList<SysDictionaryDetailModel>();
		for (int i = 0; i < detailName.length; i++) {
			SysDictionaryDetailModel detailModel = new SysDictionaryDetailModel();
			if(Common.isNotEmpty(detailName[i]))
			{
				detailModel.setDictionaryId(dictionaryModel.getId().toString());
				detailModel.setName(detailName[i]);
			}
			for (int j = 0; j < detailValue.length; j++) {
				if(i == j)
				{
					if(Common.isNotEmpty(detailValue[j]))
					{
						detailModel.setValue(detailValue[j]);
					}
					break;
				}
			}
			for (int j2 = 0; j2 < detailRemark.length; j2++) {
				if(i == j2)
				{
					if(Common.isNotEmpty(detailRemark[j2]))
					{
						detailModel.setRemark(detailRemark[j2]);
					}
					break;
				}
			}
			if(Common.isNotEmpty(detailName[i]))
			{
				moldelList.add(detailModel);
			}
		}
		if(moldelList.size() > 0)
		{
			sysDictionaryDetailMapper.insertBatchDictionaryDetail(moldelList);
		}
	}

	//@SystemServiceLog(description = "删除系统字典与系统字典明细")
	@Override
	public void deleteDictionaryAndDictionaryDetail(String dictionaryId)
			throws Exception {
		String[] dictionaryIdArr = dictionaryId.split(",");
		List<String> dictionaryIdList = new ArrayList<String>();
		for (String string : dictionaryIdArr) {
			dictionaryIdList.add(string);
		}
		sysDictionaryDetailMapper.deleteByDictionaryIdBatch(dictionaryIdList);
		sysDictionaryMapper.deleteByPrimaryKeyBatch(dictionaryIdList);
	}

}

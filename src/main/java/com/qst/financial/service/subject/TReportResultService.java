package com.qst.financial.service.subject;

import com.qst.financial.modle.subject.TReportResult;
import com.qst.financial.service.base.BaseService;
import com.qst.financial.util.tag.InnerPage;

import java.util.List;
import java.util.Map;


public interface TReportResultService extends BaseService<TReportResult, Integer>{

	/**
	 * 通用数据转换
	 */
	public boolean comFormatData(List list, String org_id);

	/**
	 * 条件查询
	 */
	public List<TReportResult> selectAllByType(InnerPage innerPage);
	/**
	 * 条件查询数量
	 */
	public int selectAllCountByType(Map<String, String> map);
}

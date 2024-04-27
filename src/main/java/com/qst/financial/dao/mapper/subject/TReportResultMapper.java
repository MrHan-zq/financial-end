package com.qst.financial.dao.mapper.subject;

import com.qst.financial.modle.subject.TCodeMapperBean;
import com.qst.financial.modle.subject.TReportResult;
import com.qst.financial.util.tag.InnerPage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TReportResultMapper {
	
	public int insertTReportResultList(List<TReportResult> record);

	public List<TCodeMapperBean> selectALLCodeMapper();

	public void deleteData(Map<String, String> map) ;

	public List<TReportResult> selectAllByType(InnerPage innerPage);

	public int selectAllCountByType(Map<String, String> map);
	
}
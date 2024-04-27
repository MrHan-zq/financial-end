package com.qst.financial.service.msg;



import com.qst.financial.dto.ResultDto;
import com.qst.financial.modle.subject.ReportResult;
import com.qst.financial.service.base.BaseService;

import java.util.List;
import java.util.Map;

public interface ReportResultService extends BaseService<ReportResult,String> {

	public List<ResultDto> selectValueSumDetailAll(Map<String, Object> map);

}

package com.qst.financial.service.subject;

import com.qst.financial.dto.Result;

import java.util.List;
import java.util.Map;

public interface DetailService {

	public List<Result> getKmDetail(String reportType, String clzz, String startTime, String endTime, String orgId, String name,
                                    String km1, String km2, String km3, String useArea);

	public List<Result> getOthenTx(String useArea, Map<String, Object> map) throws Exception;

	public List<Result> getJisuan(String useArea, Map<String, Object> map) throws Exception;

	public List<Result> getCdqi(String useArea, Map<String, Object> map) throws Exception;

	public List<Result> Cd(Map<String, Object> map);



	public List<Result> Cd2(Map<String, Object> map) throws Exception;

	public List<Result> Xsdj(Map<String, Object> map) throws Exception;

	public List<Result> getYycb(Map<String, Object> map) throws Exception;

	public List<Result> Hbyj(Map<String, Object> map) throws Exception;

}

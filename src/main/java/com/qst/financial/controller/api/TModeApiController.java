package com.qst.financial.controller.api;


import com.qst.financial.annotation.AppControllerLog;
import com.qst.financial.dto.Tishi;
import com.qst.financial.service.subject.TModeService;
import com.qst.financial.util.common.ResultCode;
import com.qst.financial.util.json.ResultObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author GaryChen
 *模板 app接口
 */
@Api(description="模板相关接口")
@Controller
@RequestMapping("/api/tmode")
public class TModeApiController {
	private static final Logger log=Logger.getLogger(TModeApiController.class);

	@Autowired 
	private TModeService tModeService ;
	@Value("${mode.zcfz.gy}")
	String zcfz_gy;
	@Value("${mode.zcfz.yj}")
	String zcfz_yj;
	@Value("${mode.lr.gy}")
	String lr_gy;
	@Value("${mode.lr.yj}")
	String lr_yj;
	@Value("${mode.xjll.gy}")
	String xjll_gy;
	@Value("${mode.xjll.yj}")
	String xjll_yj;


	

	@AppControllerLog(description = "模板查询,首页的")
	@ApiOperation(value = "模板信息{参数内容类型:application/json}", notes = "输入开始时间&结束时间&公司id", response = ResultObj.class, httpMethod = "POST")
	@RequestMapping(value="/getContentMsgsShouye.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody 
	public String getContentMsgsShouye(String startTime , String endTime , String orgId ,String type ){
		try {
			if(startTime==null || startTime.length()==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NULL, "开始时间不能为空");
				return robj.toString();
			}
			if(endTime==null || endTime.length()==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NULL, "结束时间不能为空");
				return robj.toString();
			}
			if(type==null || type.length()==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NULL, "类别不能为空");
				return robj.toString();
			}
			Map<String, Object> map = new HashMap<>() ;
			if(type.equals("1")){           //资产负债
				List<String>  yjreList = tModeService.getContent(startTime, endTime, orgId, "1", zcfz_yj);//xxxxx starttime
				List<String>  ryjreList=new ArrayList<String>();
				if(yjreList==null || yjreList.size()==0){
					yjreList.add(Tishi.YJ);

				}
				map.put("yj", yjreList);

				
				List<String>  gyreList = tModeService.getContent(endTime, endTime, orgId, "1", zcfz_gy);
				if(gyreList==null || gyreList.size()==0){
					gyreList.add(Tishi.GY);
				}
				map.put("gy", gyreList);
			}else if(type.equals("2")){        //利润
				List<String>  yjreList = tModeService.getContent(startTime, endTime, orgId, "2", lr_yj);
				if(yjreList==null || yjreList.size()==0){
					yjreList.add(Tishi.YJ);
				}
				map.put("yj", yjreList);
				List<String>  gyreList = tModeService.getContent(startTime, endTime, orgId, "2", lr_gy);
				if(gyreList==null || gyreList.size()==0){
					gyreList.add(Tishi.GY);
				}
				map.put("gy", gyreList);
			}else if(type.equals("3")){        //现金流量
				//List<String>  yjreList = tModeService.getContent(startTime, endTime, orgId, "3", "30001");
				List<String>  yjreList = tModeService.getContent(startTime, endTime, orgId, "3", xjll_yj);
				if(yjreList==null || yjreList.size()==0){
					yjreList.add(Tishi.YJ);
				}
				map.put("yj", yjreList);
				List<String>  gyreList = tModeService.getContent(startTime, endTime, orgId, "3", xjll_gy);

				if(gyreList==null || gyreList.size()==0){
					yjreList.add(Tishi.GY);
				}
				map.put("gy", gyreList);
			}
			ResultObj robj = new ResultObj(ResultCode.STATE_SUCCESS, "success",map);
			return robj.toString();
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace(); 
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "getProfitMainMsg.api:",e.getMessage());
			return robj.toString();
		}
	}
	

}

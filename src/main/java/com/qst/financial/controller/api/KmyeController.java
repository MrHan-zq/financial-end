package com.qst.financial.controller.api;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qst.financial.annotation.AppControllerLog;
import com.qst.financial.modle.subject.TKmye;
import com.qst.financial.service.subject.TKmyeService;
import com.qst.financial.sql.C;
import com.qst.financial.sql.Method;
import com.qst.financial.sql.WherePrams;
import com.qst.financial.util.common.DateUtil;
import com.qst.financial.util.common.ResultCode;
import com.qst.financial.util.json.ResultObj;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;


/**
 * 科目余额表
 */
@Slf4j
@RestController
@RequestMapping("/api/kmye")
public class KmyeController {

    @Autowired
    private TKmyeService tKmyeService;

    @AppControllerLog(description = "科目余额表明细查询,新修版")
    @ApiOperation(value = "科目余额表明细信息,新修版{参数内容类型:application/json}", notes = "输入开始时间&结束时间&公司id", response = ResultObj.class, httpMethod = "POST")
    @RequestMapping(value = "/getProfitDetailMsg.api", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public ResultObj getProfitDetailMsg(String startTime, String endTime, String orgId) {
        try {
            if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)) {
                return new ResultObj(ResultCode.RESULT_EXCEPTION, "getsmscode.api:", "时间不能为空");
            }
            String startYear = startTime.substring(0, 4);
            String startMoth = startTime.substring(5, 7);
            String endYear = endTime.substring(0, 4);
            String endMoth = endTime.substring(5, 7);
            WherePrams wherePrams = Method.where("1", C.EQ, 1);
            wherePrams.and("orgId", C.EQ, orgId);
            wherePrams.and("kjyear", C.DAEQ, startYear);
            wherePrams.and("kjmoth", C.DAEQ, startMoth);
            wherePrams.and("kjyear", C.IXAOEQ, endYear);
            wherePrams.and("kjmoth", C.IXAOEQ, endMoth);
            List<TKmye> list = tKmyeService.list(wherePrams);
            //期初余额、 期末余额、 累计借方发生、累计贷方发生
            Map<String, String> bqMap = Maps.newHashMap();
            BigDecimal qcjfye=BigDecimal.ZERO;
            BigDecimal qmjfye=BigDecimal.ZERO;
            BigDecimal bnjflj=BigDecimal.ZERO;
            BigDecimal bndflj=BigDecimal.ZERO;
            for (TKmye e : list) {
                qcjfye=qcjfye.add(null==e.getQcjfye()?BigDecimal.ZERO:e.getQcjfye());
                qmjfye=qmjfye.add(null==e.getQmjfye()?BigDecimal.ZERO:e.getQmjfye());
                bnjflj=bnjflj.add(null==e.getBnjflj()?BigDecimal.ZERO:e.getBnjflj());
                bndflj=bndflj.add(null==e.getBndflj()?BigDecimal.ZERO:e.getBndflj());
            }
            bqMap.put("qcjfye", qcjfye.setScale(2, RoundingMode.HALF_UP).toString());
            bqMap.put("qmjfye", qmjfye.setScale(2, RoundingMode.HALF_UP).toString());
            bqMap.put("bnjflj", bnjflj.setScale(2, RoundingMode.HALF_UP).toString());
            bqMap.put("bndflj", bndflj.setScale(2, RoundingMode.HALF_UP).toString());
            return new ResultObj(ResultCode.RESULT_SUCCESS, "success", bqMap);
        } catch (Exception e) {
            log.error("科目余额表明细查询失败: ", e);
            return new ResultObj(ResultCode.RESULT_EXCEPTION, "getsmscode.api:", e.getMessage());
        }
    }

    @AppControllerLog(description = "统一首页查询线条展示")
    @ApiOperation(value = "统一首页查询线条展示{参数内容类型:application/json}", notes = "输入开始时间与结束时间", response = ResultObj.class, httpMethod = "POST")
    @RequestMapping(value = "/getLine.api", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultObj getLine(@ApiParam(required = true, value = "orgId(用户id),startTime(开始时间),endTime(结束时间)" +
            " useArea: 1=余额、 2=信用额度、 3=库存数量(基本)、4=单价(基本)")
                             String orgId, String startTime, String endTime, String useArea) {
        try {
            if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)) {
                return new ResultObj(ResultCode.RESULT_EXCEPTION, "getsmscode.api:", "时间不能为空");
            }

            String newEndTime = endTime + "-01";
            String newStartTime = DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(newEndTime, "yyyy-MM-dd"), "yyyy-MM-dd", -11, 3), "yyyy-MM-dd");
            List<Date> dtList = DateUtil.getDatesBetweenTwoDate(newStartTime, newEndTime);

            String startYear = newStartTime.substring(0, 4);
            String startMoth = newStartTime.substring(5, 7);
            String endYear = newEndTime.substring(0, 4);
            String endMoth = newEndTime.substring(5, 7);
            WherePrams wherePrams = Method.where("1", C.EQ, 1);
            wherePrams.and("orgId", C.EQ, orgId);
            wherePrams.and("kjyear", C.DAEQ, startYear);
            wherePrams.and("kjmoth", C.DAEQ, startMoth);
            wherePrams.and("kjyear", C.IXAOEQ, endYear);
            wherePrams.and("kjmoth", C.IXAOEQ, endMoth);
            //查询本期信息
            List<TKmye> tKmyes = tKmyeService.list(wherePrams);

            //查询上期信息 进行环比
            Date st = DateUtil.stringToDate(newStartTime, "yyyy-MM-dd");
            Date ed = DateUtil.stringToDate(newEndTime, "yyyy-MM-dd");
            String startDt = DateUtil.dateToString(DateUtil.addTime(st, "yyyy-MM-dd", -1, 4), "yyyy-MM-dd");
            String endDt = DateUtil.dateToString(DateUtil.addTime(ed, "yyyy-MM-dd", -1, 4), "yyyy-MM-dd");
            List<Date> dtUpList = DateUtil.getDatesBetweenTwoDate(startDt, endDt);

            String startYearTb = startDt.substring(0, 4);
            String startMothTb = startDt.substring(5, 7);
            String endYearTb = endDt.substring(0, 4);
            String endMothTb = endDt.substring(5, 7);
            WherePrams tbwherePrams = Method.where("1", C.EQ, 1);
            wherePrams.and("orgId", C.EQ, orgId);
            wherePrams.and("kjyear", C.DAEQ, startYearTb);
            wherePrams.and("kjmoth", C.DAEQ, startMothTb);
            wherePrams.and("kjyear", C.IXAOEQ, endYearTb);
            wherePrams.and("kjmoth", C.IXAOEQ, endMothTb);
            List<TKmye> tbAgeAnalysisList = tKmyeService.list(tbwherePrams);
            List<TKmye> bqList = Lists.newArrayList();
            List<TKmye> sqList = Lists.newArrayList();

            for (int i = 0; i < dtList.size(); i++) {
                Date des = dtList.get(i);
                String strDes = DateUtil.dateFormatToString(des, "yyyyMM");
                int zcpd = 0;
                if (tKmyes != null && tKmyes.size() > 0) {
                    for (TKmye zc : tKmyes) {
                        if (strDes.equals(zc.getKjyearMoth())) {
                            bqList.add(zc);
                            zcpd = 1;
                        }
                    }
                }
                if (zcpd == 0) {
                    TKmye zcr = new TKmye();
                    zcr.setKjmoth(strDes.substring(4));
                    zcr.setKjyear(strDes.substring(0, 4));
                    zcr.setKjyearMoth(strDes);
                    bqList.add(zcr);
                }
            }

            for (int i = 0; i < dtUpList.size(); i++) {
                Date des = dtUpList.get(i);
                String strDes = DateUtil.dateFormatToString(des, "yyyyMM");
                int zcpd = 0;
                if (tbAgeAnalysisList != null && tbAgeAnalysisList.size() > 0) {
                    for (TKmye zc : tbAgeAnalysisList) {
                        if (strDes.equals(zc.getKjyearMoth())) {
                            sqList.add(zc);
                            zcpd = 1;
                        }
                    }
                }
                if (zcpd == 0) {
                    TKmye zcr = new TKmye();
                    Calendar calen = Calendar.getInstance();
                    calen.setTime(des);
                    zcr.setKjmoth(strDes.substring(4));
                    zcr.setKjyear(strDes.substring(0, 4));
                    zcr.setKjyearMoth(strDes);
                    sqList.add(zcr);
                }
            }

            Map<String, Object> rmap = new HashMap<>();
            rmap.put("line", bqList);
            rmap.put("lineUp", sqList);
            return new ResultObj(ResultCode.RESULT_SUCCESS, "success", rmap);
        } catch (Exception e) {
            log.error("科目余额表查询失败: ", e);
            return new ResultObj(ResultCode.RESULT_EXCEPTION, "getsmscode.api:", e.getMessage());
        }
    }

    @AppControllerLog(description = "科目余额表")
    @ApiOperation(value = "科目余额表页信息{参数内容类型:application/json}", notes = "输入开始时间&结束时间&公司id", response = ResultObj.class, httpMethod = "POST")
    @RequestMapping(value = "/getProfitMainMsg.api", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultObj getProfitMainMsg(String startTime, String endTime, String orgId) {
        try {
            if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)) {
                return new ResultObj(ResultCode.RESULT_EXCEPTION, "getsmscode.api:", "时间不能为空");
            }
            String startYear = startTime.substring(0, 4);
            String startMoth = startTime.substring(5, 7);
            String endYear = endTime.substring(0, 4);
            String endMoth = endTime.substring(5, 7);
            WherePrams wherePrams = Method.where("1", C.EQ, 1);
            wherePrams.and("orgId", C.EQ, orgId);
            wherePrams.and("kjyear", C.DAEQ, startYear);
            wherePrams.and("kjmoth", C.DAEQ, startMoth);
            wherePrams.and("kjyear", C.IXAOEQ, endYear);
            wherePrams.and("kjmoth", C.IXAOEQ, endMoth);
            //查询本期信息
            List<TKmye> otherTAgeAnalysisList = tKmyeService.list(wherePrams);

            //查询上期信息 进行环比
            String startDt = "";
            String endDt = "";
            startDt = DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(startTime, "yyyy-MM-dd"), "yyyy-MM-dd", -1, 4), "yyyy-MM-dd");
            endDt = DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd", -1, 4), "yyyy-MM-dd");
            String startYearTb = startDt.substring(0, 4);
            String startMothTb = startDt.substring(5, 7);
            String endYearTb = endDt.substring(0, 4);
            String endMothTb = endDt.substring(5, 7);
            WherePrams tbwherePrams = Method.where("1", C.EQ, 1);
            wherePrams.and("orgId", C.EQ, orgId);
            wherePrams.and("kjyear", C.DAEQ, startYearTb);
            wherePrams.and("kjmoth", C.DAEQ, startMothTb);
            wherePrams.and("kjyear", C.IXAOEQ, endYearTb);
            wherePrams.and("kjmoth", C.IXAOEQ, endMothTb);
            wherePrams.and("type", C.EQ, 6);
            wherePrams.and("accountPortType", C.EQ, 2);//财务系统类别
            List<TKmye> tbAgeAnalysisList = tKmyeService.list(tbwherePrams);
            //展示字段：期初余额、 期末余额、 累计借方发生、累计贷方发生
            Map<String, BigDecimal> bqMap = Maps.newHashMap();
            Map<String, BigDecimal> sqMap = Maps.newHashMap();
            otherTAgeAnalysisList = null == otherTAgeAnalysisList ? Lists.newArrayList() : otherTAgeAnalysisList;
            tbAgeAnalysisList = null == tbAgeAnalysisList ? Lists.newArrayList() : tbAgeAnalysisList;

            for (TKmye e : otherTAgeAnalysisList) {
                if (Objects.nonNull(e.getQcjfye())) {
                    bqMap.put("qcjfye", Objects.isNull(bqMap.get("qcjfye")) ? e.getQcjfye() : bqMap.get("qcjfye").add(e.getQcjfye()));
                }
                if (Objects.nonNull(e.getQmjfye())) {
                    bqMap.put("qmjfye", Objects.isNull(bqMap.get("qmjfye")) ? e.getQmjfye() : bqMap.get("qmjfye").add(e.getQmjfye()));
                }
                if (Objects.nonNull(e.getBnjflj())) {
                    bqMap.put("bnjflj", Objects.isNull(bqMap.get("kcsljb")) ? e.getBnjflj() : bqMap.get("kcsljb").add(e.getBnjflj()));
                }
                if (Objects.nonNull(e.getBndflj())) {
                    bqMap.put("bndflj", Objects.isNull(bqMap.get("djjb")) ? e.getBndflj() : bqMap.get("djjb").add(e.getBndflj()));
                }
            }

            for (TKmye e : tbAgeAnalysisList) {
                if (Objects.nonNull(e.getQcjfye())) {
                    sqMap.put("qcjfye", Objects.isNull(sqMap.get("qcjfye")) ? e.getQcjfye() : sqMap.get("qcjfye").add(e.getQcjfye()));
                }
                if (Objects.nonNull(e.getQmjfye())) {
                    sqMap.put("qmjfye", Objects.isNull(sqMap.get("qmjfye")) ? e.getQmjfye() : sqMap.get("qmjfye").add(e.getQmjfye()));
                }
                if (Objects.nonNull(e.getBnjflj())) {
                    sqMap.put("bnjflj", Objects.isNull(sqMap.get("kcsljb")) ? e.getBnjflj() : sqMap.get("kcsljb").add(e.getBnjflj()));
                }
                if (Objects.nonNull(e.getBndflj())) {
                    sqMap.put("bndflj", Objects.isNull(sqMap.get("djjb")) ? e.getBndflj() : sqMap.get("djjb").add(e.getBndflj()));
                }
            }
            Map<String, Object> tempMap = Maps.newHashMap();
            String qcjfye;
            if (Objects.isNull(bqMap.get("qcjfye")) && Objects.isNull(sqMap.get("qcjfye"))) {
                qcjfye = "-";
            } else if (sqMap.get("qcjfye").compareTo(BigDecimal.ZERO) == 0 && bqMap.get("qcjfye").compareTo(BigDecimal.ZERO) != 0) {
                qcjfye = "-";
            } else if (sqMap.get("qcjfye").compareTo(BigDecimal.ZERO) == 0 && bqMap.get("qcjfye").compareTo(BigDecimal.ZERO) == 0) {
                qcjfye = "-";
            } else {
                qcjfye = ((bqMap.get("qcjfye").subtract(sqMap.get("qcjfye"))).multiply(new BigDecimal("100")).divide(sqMap.get("qcjfye").abs(), 2, RoundingMode.HALF_UP)).toString();
            }

            String qmjfye;
            if (Objects.isNull(bqMap.get("qmjfye")) || Objects.isNull(sqMap.get("qmjfye"))) {
                qmjfye = "-";
            } else if (sqMap.get("qmjfye").compareTo(BigDecimal.ZERO) == 0 && bqMap.get("qmjfye").compareTo(BigDecimal.ZERO) != 0) {
                qmjfye = "-";
            } else if (sqMap.get("qmjfye").compareTo(BigDecimal.ZERO) == 0 && bqMap.get("qmjfye").compareTo(BigDecimal.ZERO) == 0) {
                qmjfye = "-";
            } else {
                qmjfye = ((bqMap.get("qmjfye").subtract(sqMap.get("qmjfye"))).multiply(new BigDecimal("100")).divide(sqMap.get("qmjfye").abs(), 2, RoundingMode.HALF_UP)).toString();
            }

            String bnjflj;
            if (Objects.isNull(bqMap.get("bnjflj")) || Objects.isNull(sqMap.get("bnjflj"))) {
                bnjflj = "-";
            } else if (sqMap.get("bnjflj").compareTo(BigDecimal.ZERO) == 0 && bqMap.get("bnjflj").compareTo(BigDecimal.ZERO) != 0) {
                bnjflj = "-";
            } else if (sqMap.get("bnjflj").compareTo(BigDecimal.ZERO) == 0 && bqMap.get("bnjflj").compareTo(BigDecimal.ZERO) == 0) {
                bnjflj = "-";
            } else {
                bnjflj = ((bqMap.get("bnjflj").subtract(sqMap.get("bnjflj"))).multiply(new BigDecimal("100")).divide(sqMap.get("bnjflj").abs(), 2, RoundingMode.HALF_UP)).toString();
            }

            String bndflj;
            if (Objects.isNull(bqMap.get("bndflj")) || Objects.isNull(sqMap.get("bndflj"))) {
                bndflj = "-";
            } else if (sqMap.get("bndflj").compareTo(BigDecimal.ZERO) == 0 && bqMap.get("bndflj").compareTo(BigDecimal.ZERO) != 0) {
                bndflj = "-";
            } else if (sqMap.get("bndflj").compareTo(BigDecimal.ZERO) == 0 && bqMap.get("bndflj").compareTo(BigDecimal.ZERO) == 0) {
                bndflj = "-";
            } else {
                bndflj = ((bqMap.get("bndflj").subtract(sqMap.get("bndflj"))).multiply(new BigDecimal("100")).divide(sqMap.get("bndflj").abs(), 2, RoundingMode.HALF_UP)).toString();
            }
//            期初余额、 期末余额、 累计借方发生、累计贷方发生
            tempMap.put("qcjfyeRise", qcjfye);
            tempMap.put("qmjfyeRise", qmjfye);
            tempMap.put("bnjfljRise", bnjflj);
            tempMap.put("bndfljRise", bndflj);
            tempMap.put("qcjfye", bqMap.getOrDefault("qcjfye", new BigDecimal("0.00")));
            tempMap.put("qmjfye", bqMap.getOrDefault("qmjfye", new BigDecimal("0.00")));
            tempMap.put("bnjflj", bqMap.getOrDefault("bnjflj", new BigDecimal("0.00")));
            tempMap.put("bndflj", bqMap.getOrDefault("bndflj", new BigDecimal("0.00")));

            return new ResultObj(ResultCode.RESULT_SUCCESS, "success", tempMap);
        } catch (Exception e) {
            log.error("科目余额表查询失败: ", e);
            return new ResultObj(ResultCode.RESULT_EXCEPTION, "getsmscode.api:", e.getMessage());
        }
    }
}

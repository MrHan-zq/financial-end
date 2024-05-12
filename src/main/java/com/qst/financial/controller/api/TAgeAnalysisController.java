package com.qst.financial.controller.api;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qst.financial.annotation.AppControllerLog;
import com.qst.financial.modle.subject.TAgeAnalysis;
import com.qst.financial.service.subject.TAgeAnalysisService;
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
import java.util.stream.Collectors;

/**
 * 账龄分析表
 */
@Slf4j
@RestController
@RequestMapping("/api/analysis")
public class TAgeAnalysisController {

    @Autowired
    private TAgeAnalysisService tAgeAnalysisService;

    @AppControllerLog(description = "应收账龄分析表明细查询,新修版")
    @ApiOperation(value = "应收账龄分析表明细信息,新修版{参数内容类型:application/json}", notes = "输入开始时间&结束时间&公司id", response = ResultObj.class, httpMethod = "POST")
    @RequestMapping(value = "/getProfitDetailMsg.api", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public ResultObj getProfitDetailMsg(String startTime, String endTime, String orgId) {
        //展示字段： 余额、 信用额度、 库存数量(基本)、单价(基本)
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
            wherePrams.and("type", C.EQ, 6);
            wherePrams.and("accountPortType", C.EQ, 2);//财务系统类别
            List<TAgeAnalysis> otherTAgeAnalysisList = tAgeAnalysisService.list(wherePrams);
            Map<String, String> bqMap = Maps.newHashMap();
            BigDecimal balance=BigDecimal.ZERO;
            BigDecimal creditLine=BigDecimal.ZERO;
            BigDecimal kcsljb=BigDecimal.ZERO;
            BigDecimal djjb=BigDecimal.ZERO;
            for (TAgeAnalysis e : otherTAgeAnalysisList) {
                balance=balance.add(null == e.getBalance() ? BigDecimal.ZERO : e.getBalance());
                creditLine=creditLine.add(null == e.getCreditLine() ? BigDecimal.ZERO : e.getCreditLine());
                kcsljb=kcsljb.add(null == e.getKcsljb() ? BigDecimal.ZERO : e.getKcsljb());
                djjb=djjb.add(null == e.getDjjb() ? BigDecimal.ZERO : e.getDjjb());
            }
            bqMap.put("balance", balance.setScale(2, RoundingMode.HALF_UP).toString());
            bqMap.put("creditLine", creditLine.setScale(2, RoundingMode.HALF_UP).toString());
            bqMap.put("kcsljb", kcsljb.setScale(2, RoundingMode.HALF_UP).toString());
            bqMap.put("djjb", djjb.setScale(2, RoundingMode.HALF_UP).toString());
            return new ResultObj(ResultCode.RESULT_SUCCESS, "success", bqMap);
        } catch (Exception e) {
            log.error("应收账龄分析表明细查询失败: ", e);
            return new ResultObj(ResultCode.RESULT_EXCEPTION, "getsmscode.api:", e.getMessage());
        }
    }

    @AppControllerLog(description = "统一首页查询线条展示")
    @ApiOperation(value = "统一首页查询线条展示{参数内容类型:application/json}", notes = "输入开始时间与结束时间", response = ResultObj.class, httpMethod = "POST")
    @RequestMapping(value = "/getLine.api", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultObj getLine(@ApiParam(required = true, value = "orgId(用户id),startTime(开始时间),endTime(结束时间)")
                             String orgId, String startTime, String endTime) {
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
            wherePrams.and("type", C.EQ, 6);
            wherePrams.and("accountPortType", C.EQ, 2);//财务系统类别
            //查询本期信息
            List<TAgeAnalysis> otherTAgeAnalysisList = tAgeAnalysisService.list(wherePrams);

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
            wherePrams.and("type", C.EQ, 6);
            wherePrams.and("accountPortType", C.EQ, 2);//财务系统类别
            List<TAgeAnalysis> tbAgeAnalysisList = tAgeAnalysisService.list(tbwherePrams);
            List<TAgeAnalysis> bqList = Lists.newArrayList();
            List<TAgeAnalysis> sqList = Lists.newArrayList();

            for (int i = 0; i < dtList.size(); i++) {
                Date des = dtList.get(i);
                String strDes = DateUtil.dateFormatToString(des, "yyyyMM");
                int zcpd = 0;
                if (otherTAgeAnalysisList != null && otherTAgeAnalysisList.size() > 0) {
                    for (TAgeAnalysis zc : otherTAgeAnalysisList) {
                        if (strDes.equals(zc.getKjyearMoth())) {
                            bqList.add(zc);
                            zcpd = 1;
                        }
                    }
                }
                if (zcpd == 0) {
                    TAgeAnalysis zcr = new TAgeAnalysis();
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
                    for (TAgeAnalysis zc : tbAgeAnalysisList) {
                        if (strDes.equals(zc.getKjyearMoth())) {
                            sqList.add(zc);
                            zcpd = 1;
                        }
                    }
                }
                if (zcpd == 0) {
                    TAgeAnalysis zcr = new TAgeAnalysis();
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
            log.error("应收账龄分析表查询失败: ", e);
            return new ResultObj(ResultCode.RESULT_EXCEPTION, "getsmscode.api:", e.getMessage());
        }
    }

    @AppControllerLog(description = "应收账龄分析表")
    @ApiOperation(value = "应收账龄分析表页信息{参数内容类型:application/json}", notes = "输入开始时间&结束时间&公司id", response = ResultObj.class, httpMethod = "POST")
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
            wherePrams.and("type", C.EQ, 6);
            wherePrams.and("accountPortType", C.EQ, 2);//财务系统类别
            //查询本期信息
            List<TAgeAnalysis> otherTAgeAnalysisList = tAgeAnalysisService.list(wherePrams);

            //查询上期信息 进行环比
            String startDt = "";
            String endDt = "";
            startDt = DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(startTime, "yyyy-MM"), "yyyy-MM-dd", -1, 4), "yyyy-MM-dd");
            endDt = DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime, "yyyy-MM"), "yyyy-MM-dd", -1, 4), "yyyy-MM-dd");
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
            List<TAgeAnalysis> tbAgeAnalysisList = tAgeAnalysisService.list(tbwherePrams);

            //展示字段：余额、 信用额度、 库存数量(基本)、单价(基本)
            Map<String, BigDecimal> bqMap = Maps.newHashMap();
            Map<String, BigDecimal> sqMap = Maps.newHashMap();
            otherTAgeAnalysisList = null == otherTAgeAnalysisList ? Lists.newArrayList() : otherTAgeAnalysisList;
            tbAgeAnalysisList = null == tbAgeAnalysisList ? Lists.newArrayList() : tbAgeAnalysisList;

            for (TAgeAnalysis e : otherTAgeAnalysisList) {
                if (Objects.nonNull(e.getBalance())) {
                    bqMap.put("balance", Objects.isNull(bqMap.get("balance")) ? e.getBalance() : bqMap.get("balance").add(e.getBalance()));
                }
                if (Objects.nonNull(e.getCreditLine())) {
                    bqMap.put("creditLine", Objects.isNull(bqMap.get("creditLine")) ? e.getCreditLine() : bqMap.get("creditLine").add(e.getCreditLine()));
                }
                if (Objects.nonNull(e.getKcsljb())) {
                    bqMap.put("kcsljb", Objects.isNull(bqMap.get("kcsljb")) ? e.getKcsljb() : bqMap.get("kcsljb").add(e.getKcsljb()));
                }
                if (Objects.nonNull(e.getDjjb())) {
                    bqMap.put("djjb", Objects.isNull(bqMap.get("djjb")) ? e.getDjjb() : bqMap.get("djjb").add(e.getDjjb()));
                }
            }

            for (TAgeAnalysis e : tbAgeAnalysisList) {
                if (Objects.nonNull(e.getBalance())) {
                    sqMap.put("balance", Objects.isNull(sqMap.get("balance")) ? e.getBalance() : sqMap.get("balance").add(e.getBalance()));
                }
                if (Objects.nonNull(e.getCreditLine())) {
                    sqMap.put("creditLine", Objects.isNull(sqMap.get("creditLine")) ? e.getCreditLine() : sqMap.get("creditLine").add(e.getCreditLine()));
                }
                if (Objects.nonNull(e.getKcsljb())) {
                    sqMap.put("kcsljb", Objects.isNull(sqMap.get("kcsljb")) ? e.getKcsljb() : sqMap.get("kcsljb").add(e.getKcsljb()));
                }
                if (Objects.nonNull(e.getDjjb())) {
                    sqMap.put("djjb", Objects.isNull(sqMap.get("djjb")) ? e.getDjjb() : sqMap.get("djjb").add(e.getDjjb()));
                }
            }
            Map<String, Object> tempMap = Maps.newHashMap();
            String balance;
            String creditLine;
            String kcsljb;
            String djjb;
            if (Objects.isNull(bqMap.get("balance")) && Objects.isNull(sqMap.get("balance"))) {
                balance = "-";
            } else if (sqMap.get("balance").compareTo(BigDecimal.ZERO) == 0 && bqMap.get("balance").compareTo(BigDecimal.ZERO) != 0) {
                balance = "-";
            } else if (sqMap.get("balance").compareTo(BigDecimal.ZERO) == 0 && bqMap.get("balance").compareTo(BigDecimal.ZERO) == 0) {
                balance = "-";
            } else {
                balance = ((bqMap.get("balance").subtract(sqMap.get("balance"))).multiply(new BigDecimal("100")).divide(sqMap.get("balance").abs(), 2, RoundingMode.HALF_UP)).toString();
            }

            if (Objects.isNull(bqMap.get("creditLine")) || Objects.isNull(sqMap.get("creditLine"))) {
                creditLine = "-";
            } else if (sqMap.get("creditLine").compareTo(BigDecimal.ZERO) == 0 && bqMap.get("creditLine").compareTo(BigDecimal.ZERO) != 0) {
                creditLine = "-";
            } else if (sqMap.get("creditLine").compareTo(BigDecimal.ZERO) == 0 && bqMap.get("creditLine").compareTo(BigDecimal.ZERO) == 0) {
                creditLine = "-";
            } else {
                creditLine = ((bqMap.get("creditLine").subtract(sqMap.get("creditLine"))).multiply(new BigDecimal("100")).divide(sqMap.get("creditLine").abs(), 2, RoundingMode.HALF_UP)).toString();
            }

            if (Objects.isNull(bqMap.get("kcsljb")) || Objects.isNull(sqMap.get("kcsljb"))) {
                kcsljb = "-";
            } else if (sqMap.get("kcsljb").compareTo(BigDecimal.ZERO) == 0 && bqMap.get("kcsljb").compareTo(BigDecimal.ZERO) != 0) {
                kcsljb = "-";
            } else if (sqMap.get("kcsljb").compareTo(BigDecimal.ZERO) == 0 && bqMap.get("kcsljb").compareTo(BigDecimal.ZERO) == 0) {
                kcsljb = "-";
            } else {
                kcsljb = ((bqMap.get("kcsljb").subtract(sqMap.get("kcsljb"))).multiply(new BigDecimal("100")).divide(sqMap.get("kcsljb").abs(), 2, RoundingMode.HALF_UP)).toString();
            }

            if (Objects.isNull(bqMap.get("djjb")) || Objects.isNull(sqMap.get("djjb"))) {
                djjb = "-";
            } else if (sqMap.get("djjb").compareTo(BigDecimal.ZERO) == 0 && bqMap.get("djjb").compareTo(BigDecimal.ZERO) != 0) {
                djjb = "-";
            } else if (sqMap.get("djjb").compareTo(BigDecimal.ZERO) == 0 && bqMap.get("djjb").compareTo(BigDecimal.ZERO) == 0) {
                djjb = "-";
            } else {
                djjb = ((bqMap.get("djjb").subtract(sqMap.get("djjb"))).multiply(new BigDecimal("100")).divide(sqMap.get("djjb").abs(), 2, RoundingMode.HALF_UP)).toString();
            }
            tempMap.put("balanceRise", balance);
            tempMap.put("creditLineRise", creditLine);
            tempMap.put("kcsljbRise", kcsljb);
            tempMap.put("djjbRise", djjb);
            tempMap.put("balance", bqMap.getOrDefault("balance", new BigDecimal("0.00")));
            tempMap.put("creditLine", bqMap.getOrDefault("creditLine", new BigDecimal("0.00")));
            tempMap.put("kcsljb", bqMap.getOrDefault("kcsljb", new BigDecimal("0.00")));
            tempMap.put("djjb", bqMap.getOrDefault("djjb", new BigDecimal("0.00")));
            return new ResultObj(ResultCode.RESULT_SUCCESS, "success", tempMap);
        } catch (Exception e) {
            log.error("应收账龄分析表查询失败: ", e);
            return new ResultObj(ResultCode.RESULT_EXCEPTION, "getsmscode.api:", e.getMessage());
        }
    }
}

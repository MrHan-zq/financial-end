package com.qst.financial.dto;

import java.util.HashMap;
import java.util.Map;


public class ResultMessage {
	public static Map<String,Object> getMessage(String startTime,String endTime,String tax,String pay,String dpay,String qpay,String funds,String fundsHand,String loan,
			String oneIncome,String oneNoIncome,String limits,String incomeGrowth,String yycb,String xsfy,String glfy,String cwfy,String zjsr,String ycmqs,
			String srsy,String yycbzz,String zcje,String fjsl,String zzsfh,String sds,String jkll,String tzl,String allYear,String startYear){
		int code=0;
		Map<String, Object> map=new HashMap<String, Object>();
		if(startTime==null || startTime.length()==0){
			code=1;
			map.put("message", "开始时间不能为空");
			map.put("code", code);
			return map;
		}
		if(endTime==null || endTime.length()==0){
			code=1;
			map.put("message", "结束时间不能为空");
			map.put("code", code);
			return map;
		}
		if(tax==null || tax.length()==0){
			code=1;
			map.put("message", "支出金额（含税）不能为空");
			map.put("code", code);
			return map;
		}
		if(pay==null || pay.length()==0){
			code=1;
			map.put("message", "支出金额（不含税）不能为空");
			map.put("code", code);
			return map;
		}
		if(dpay==null || dpay.length()==0){
			code=1;
			map.put("message", "可抵扣增值税额不能为空");
			map.put("code", code);
			return map;
		}
		if(qpay==null || qpay.length()==0){
			code=1;
			map.put("message", "其他税额不能为空");
			map.put("code", code);
			return map;
		}
		if(funds==null || funds.length()==0){
			code=1;
			map.put("message", "资金来源不能为空");
			map.put("code", code);
			return map;
		}
		if(fundsHand==null || fundsHand.length()==0){
			code=1;
			map.put("message", "自有资金不能为空");
			map.put("code", code);
			return map;
		}
		if(loan==null || loan.length()==0){
			code=1;
			map.put("message", "借款不能为空");
			map.put("code", code);
			return map;
		}
		if(incomeGrowth==null || incomeGrowth.length()==0){
			code=1;
			map.put("message", "项目总年限不能为空");
			map.put("code", code);
			return map;
		}
		if(incomeGrowth==null || incomeGrowth.length()==0){
			code=1;
			map.put("message", "预计从第几年开始正式取得收益不能为空");
			map.put("code", code);
			return map;
		}
		if(oneIncome==null || oneIncome.length()==0){
			code=1;
			map.put("message", "预测第一年收入（含税）不能为空");
			map.put("code", code);
			return map;
		}
		if(oneNoIncome==null || oneNoIncome.length()==0){
			code=1;
			map.put("message", "预测第一年收入（不含税）不能为空");
			map.put("code", code);
			return map;
		}
		if(limits==null || limits.length()==0){
			code=1;
			map.put("message", "税额不能为空");
			map.put("code", code);
			return map;
		}
		if(incomeGrowth==null || incomeGrowth.length()==0){
			code=1;
			map.put("message", "收入增长率不能为空");
			map.put("code", code);
			return map;
		}
		if(yycb==null || yycb.length()==0){
			code=1;
			map.put("message", "营业成本收入比不能为空");
			map.put("code", code);
			return map;
		}
		if(xsfy==null || xsfy.length()==0){
			code=1;
			map.put("message", "销售费用收入比不能为空");
			map.put("code", code);
			return map;
		}
		if(glfy==null || glfy.length()==0){
			code=1;
			map.put("message", "管理费用收入比不能为空");
			map.put("code", code);
			return map;
		}
		if(cwfy==null || cwfy.length()==0){
			code=1;
			map.put("message", "财务费用收入比不能为空");
			map.put("code", code);
			return map;
		}
		if(zjsr==null || zjsr.length()==0){
			code=1;
			map.put("message", "折旧收入比不能为空");
			map.put("code", code);
			return map;
		}
		if(ycmqs==null || ycmqs.length()==0){
			code=1;
			map.put("message", "预测末期处置全部权益可回收或需支付的资金不能为空");
			map.put("code", code);
			return map;
		}
		if(srsy==null || srsy.length()==0){
			code=1;
			map.put("message", "收入适用的增值税税率不能为空");
			map.put("code", code);
			return map;
		}
		if(yycbzz==null || yycbzz.length()==0){
			code=1;
			map.put("message", "营业成本适用的增值税税率不能为空");
			map.put("code", code);
			return map;
		}
		if(zcje==null || zcje.length()==0){
			code=1;
			map.put("message", "支出金额适用的增值税税率不能为空");
			map.put("code", code);
			return map;
		}
		if(fjsl==null || fjsl.length()==0){
			code=1;
			map.put("message", "附加税率不能为空");
			map.put("code", code);
			return map;
		}
		if(zzsfh==null || zzsfh.length()==0){
			code=1;
			map.put("message", "增值税返还率不能为空");
			map.put("code", code);
			return map;
		}
		if(sds==null || sds.length()==0){
			code=1;
			map.put("message", "所得税率不能为空");
			map.put("code", code);
			return map;
		}
		if(jkll==null || jkll.length()==0){
			code=1;
			map.put("message", "借款利率不能为空");
			map.put("code", code);
			return map;
		}
		if(tzl==null || tzl.length()==0){
			code=1;
			map.put("message", "通胀率不能为空");
			map.put("code", code);
			return map;
		}
		/*int stTime=Integer.parseInt(startTime);
		int enTime=Integer.parseInt(endTime);
		int math=enTime-stTime;
		int allYearInt=Integer.parseInt(allYear);
		if(allYearInt!=math){
			code=1;
			map.put("message", "选择时间区域与项目总年限不一致");
			map.put("code", code);
			return map;
		}*/
		map.put("code", code);
		return map;
	}
}

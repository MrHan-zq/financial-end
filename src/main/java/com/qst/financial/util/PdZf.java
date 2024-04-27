package com.qst.financial.util;

import com.qst.financial.util.common.DateUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PdZf {
	//得到两个n-(n-1)正负数量
	public static Map<String, Integer> getPd(List<BigDecimal> list1,List<BigDecimal>  list2){
		int xt=0;
		int bt=0;
		int dy=0;
		for(int i=1;i<list1.size();i++){
			BigDecimal a=list1.get(i).subtract(list1.get(i-1));
			BigDecimal b=list2.get(i).subtract(list2.get(i-1));
			int as=a.compareTo(new BigDecimal("0"));
			int bs=b.compareTo(new BigDecimal("0"));
			if(as==-1 && bs==-1){
				xt=xt+1;
			}else if(as==1 && bs==1){
				xt=xt+1;
			}else{
				bt=bt+1;
			}
		}
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("xt", xt);
		map.put("bt", bt);
		return map;
	}
	//两个相减得到最大
	public static int getJdz(List<BigDecimal> list1,List<BigDecimal>  list2){
		int a=0;
		BigDecimal max=new BigDecimal("0");
		for(int i=0;i<list1.size();i++){
			BigDecimal abs=(list1.get(i).subtract(list2.get(i))).abs();
			if(max.compareTo(abs)==-1){
				a=i;
				max=abs;
			}
		}
		return a;
	}
	//得到递减后最大值的下标
	public static int getBJdz(List<BigDecimal>  list2){
		int a=0;
		BigDecimal max=new BigDecimal("0");
		for(int i=1;i<list2.size();i++){
			BigDecimal abs=(list2.get(i).subtract(list2.get(i-1))).abs();
			if(max.compareTo(abs)==-1){
				a=i;
				max=abs;
			}
		}
		return a;
	}
	//得到递减后的最大值
	public static BigDecimal getAJA(List<BigDecimal>  list1){
		BigDecimal max=new BigDecimal("0");
		for(int i=1;i<list1.size();i++){
			max=(list1.get(i).subtract(list1.get(i-1)));
			
		}
		return max;
	}
	
	//得到参数的值
	public static BigDecimal getAJA2(List<BigDecimal>  list1,int n){
		BigDecimal max=new BigDecimal("0");
		if(n>0){
			max=(list1.get(n).subtract(list1.get(n-1)));
		}else{
			max=list1.get(n);
		}
		return max;
	}
	public static String Pd(List<BigDecimal> list1,List<BigDecimal>  list2,List<Date> list){
		String str="";
		Map<String, Integer> getPd=PdZf.getPd(list1, list2);
		int xt=getPd.get("xt");
		int bt=getPd.get("bt");
		if(xt<bt){
			BigDecimal azdjzx=list1.get(list1.size()-1).subtract(list1.get(0));
			BigDecimal bzdjzx=list2.get(list1.size()-1).subtract(list2.get(0));
			if(azdjzx.compareTo(BigDecimal.ZERO)==1 && bzdjzx.compareTo(BigDecimal.ZERO)==-1){      //A
				str="应收账款与营业收入呈反趋势变化，应收账款与往月相比呈上升趋势，但营业收入呈下降趋势，企业资金回收能力减弱的同时收入不断减少，建议管理层结合货币资金中的资金预测，查看未来现金流是否存在压力，考虑是否需要进行债权或股权融资。同时，结合市场大环境的趋势，如果收入下降原因是市场萎缩导致，建议管理层考虑企业业务的转型或者开辟区别化产品，同时加快应收账款的回收，针对部分客户长期拖欠款项的情况，结合合同条款及违约责任等，考虑是否需要通过法律途径进行解决.";
			}
			
		}
		int abJdz=PdZf.getJdz(list1, list2);
		/*BigDecimal am=list1.get(list1.size()-1).subtract(list1.get(0));
		BigDecimal bm=list1.get(list2.size()-1).subtract(list2.get(0));*/
		BigDecimal aja=PdZf.getAJA2(list1,abJdz);
		BigDecimal bjb=PdZf.getAJA2(list2,abJdz);
		if(aja.compareTo(BigDecimal.ZERO)==1 && aja.compareTo(bjb)==1 ){    //C-->B
			//str=str+list.get(abJdz)+"月份应收账款与营业收入趋势差异为全年中差异最大月份，由于理想状态下，应收账款与营业收入应为同变化趋势，因此，针对*月份的异常情况，建议管理层查看本月应收账款付款忽然加快的原因。";   
			str=str+DateUtil.dateFormatToString(list.get(abJdz), "yyyy-MM")+"月份应收账款与营业收入趋势差异为全年中差异最大月份，由于理想状态下，应收账款与营业成本应为同变化趋势，因此，针对"+DateUtil.dateFormatToString(list.get(abJdz), "yyyy-MM")+"月份的异常情况，建议管理层查看本月应收账款付款忽然减慢的原因，考虑是否需要调整信用政策，避免同样情况在未来持续发生。";
		}
		if(aja.compareTo(BigDecimal.ZERO)==1 && aja.compareTo(bjb)==-1 ){    //D-->C
			//str=str+list.get(abJdz)+"月份营业收入异常升高，建议管理层结合存货模块的各项分析，查看营业收入异常升高的原因。针对异常升高的原因，考虑未来是否存在可复制性，是否可以靠同类措施或政策持续刺激收入增长。";     
			str=str+DateUtil.dateFormatToString(list.get(abJdz), "yyyy-MM")+"月份应收账款与营业收入趋势差异为全年中差异最大月份，由于理想状态下，应收账款与营业收入应为同变化趋势，因此，针对"+DateUtil.dateFormatToString(list.get(abJdz), "yyyy-MM")+"月份的异常情况，建议管理层查看本月应收账款付款忽然加快的原因。";
		}
		int bJdz=PdZf.getBJdz(list2);
		BigDecimal bjb2=PdZf.getAJA2(list2,bJdz);
		if(bjb2.compareTo(BigDecimal.ZERO)==1){			//E--->D
			//str=str+list.get(bJdz)+"月份营业收入异常下降，建议管理层结合存货模块的各项分析，查看营业收入异常下降的原因。针对异常下降的原因，考虑是否需要调整营销策略，避免未来出现同样的不良状况。";
			str=str+DateUtil.dateFormatToString(list.get(bJdz), "yyyy-MM")+"月份营业收入异常升高，建议管理层结合存货模块的各项分析，查看营业收入异常升高的原因。针对异常升高的原因，考虑未来是否存在可复制性，是否可以靠同类措施或政策持续刺激收入增长。";
		}
		if(bjb2.compareTo(BigDecimal.ZERO)==-1){  //F--->E
			//str=str+list.get(bJdz)+"待写。";                      //+F未写
			str=str+DateUtil.dateFormatToString(list.get(bJdz), "yyyy-MM")+"月份营业收入异常下降，建议管理层结合存货模块的各项分析，查看营业收入异常下降的原因。针对异常下降的原因，考虑是否需要调整营销策略，避免未来出现同样的不良状况。";
		}
		return str;
	}
	public static String Pd2(List<BigDecimal> list1,List<BigDecimal>  list2,List<Date> list){
		String str="";
		Map<String, Integer> getPd=PdZf.getPd(list1, list2);
		int xt=getPd.get("xt");
		int bt=getPd.get("bt");
		if(xt<bt){  //A
			BigDecimal azdjzx=list1.get(list1.size()-1).subtract(list1.get(0));
			//BigDecimal bzdjzx=list2.get(list1.size()-1).subtract(list2.get(0));
			if(azdjzx.compareTo(BigDecimal.ZERO)==-1){      //A
				str="应付账款与营业成本呈反趋势变化，应付账款与往月相比呈下降趋势，管理层需考虑应付账款周转期加快对未来现金流的影响，建议管理层结合货币资金部分的资金预测图，以及应收账款及应付账款部分的分析结果，分析未来现金流状况，考虑是否需要与客户以及供应商调整收付款条件。如无法调整付款条件，未来现金流可能出现紧张情况，需结合比率分析的结果，提前考虑银行债权融资的可能性，同时分析股权融资与债权融资的优劣势，择优选择对企业的最佳方案。";
			}
			if(azdjzx.compareTo(BigDecimal.ZERO)==1){      //A
				str="应付账款与营业成本呈反趋势变化，应付账款与往月相比呈上升趋势，管理层需查看应付账款周转期拉长的原因，并结合货币资金的资金预测以及应付账款的分析结果，判断企业是否存在资金周转问题。如资金周转问题不存在持续性，则无需做太大变动，如存在持续性，需考虑进行债权或股权融资，或由股东进行增资，以缓解资金压力。同时，管理层需关注购买合同的付款条约，以防逾期未支付带来的违约风险。";
			}
			
		}
		int abJdz=PdZf.getJdz(list1, list2);
		/*BigDecimal am=list1.get(list1.size()-1).subtract(list1.get(0));
		BigDecimal bm=list1.get(list2.size()-1).subtract(list2.get(0));*/
		BigDecimal aja=PdZf.getAJA2(list1,abJdz);
		BigDecimal bjb=PdZf.getAJA2(list2,abJdz);
		if(aja.compareTo(BigDecimal.ZERO)==1 && aja.compareTo(bjb)==1 ){    //C
			str=str+DateUtil.dateFormatToString(list.get(abJdz), "yyyy-MM")+"月份应付账款与营业成本趋势差异为全年中差异最大月份，由于理想状态下，应付账款与营业成本应为同变化趋势，因此，针对"+DateUtil.dateFormatToString(list.get(abJdz), "yyyy-MM")+"月份的异常情况，建议管理层查看本月应付账款付款忽然减慢的原因，并考虑该月份异常付款是否存在违约问题，考虑相应的法律风险。";
		}
		int bJdz=PdZf.getBJdz(list2);
		BigDecimal bjb2=PdZf.getAJA2(list2,bJdz);
		if(bjb2.compareTo(BigDecimal.ZERO)==1){			//E
			//str=str+list.get(bJdz)+"月份营业收入异常下降，建议管理层结合存货模块的各项分析，查看营业收入异常下降的原因。针对异常下降的原因，考虑是否需要调整营销策略，避免未来出现同样的不良状况。";
			str=str+DateUtil.dateFormatToString(list.get(bJdz), "yyyy-MM")+"月份营业成本异常升高，建议管理层结合存货中的“产成品各月入库和出库单价变动”图，分析营业成本异常升高的原因";
		}
		if(bjb2.compareTo(BigDecimal.ZERO)==-1){  //F-
			str=str+DateUtil.dateFormatToString(list.get(bJdz), "yyyy-MM")+"月份营业成本异常下降，建议管理层结合存货中的“产成品各月入库和出库单价变动”图，分析营业成本异常下降的原因。";
		}
		return str;
		
	}
}







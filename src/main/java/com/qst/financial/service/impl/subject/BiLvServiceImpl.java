package com.qst.financial.service.impl.subject;

import com.qst.financial.dto.ResultDto;
import com.qst.financial.service.subject.BilvService;
import com.qst.financial.service.subject.ReportResultService;
import com.qst.financial.util.Dates;
import com.qst.financial.util.TbAndHb;
import com.qst.financial.util.common.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BiLvServiceImpl implements BilvService {
	@Autowired
	private ReportResultService reportResultService ;
	@Override
	public Map<String,Object> getBilv(String startTime,String endTime,String orgId) throws ParseException{
		startTime=startTime+"-01";
		endTime=endTime+"-01";
		Map<String,Object> rmap = new HashMap<>();
		Map<String,Object> map = new HashMap<>();
		map.put("startTimes", startTime);
		map.put("endTimes", endTime);
		map.put("orgId", orgId);
		map.put("useArea", null);
		map.put("reportType", 1);
		map.put("isZh", "1");
		int days = TbAndHb.getDays(startTime, endTime);
		int tbdays= TbAndHb.getDays(DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(startTime,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4), "yyyy-MM-dd"), DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4), "yyyy-MM-dd"));
		int monthDiff = Dates.getDateTimes(startTime,endTime)+1;
		int hbdays= TbAndHb.getDays(DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(startTime,"yyyy-MM-dd"),"yyyy-MM-dd",monthDiff*(-1),3), "yyyy-MM-dd"), DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime,"yyyy-MM-dd"),"yyyy-MM-dd",monthDiff*(-1),3), "yyyy-MM-dd"));
		List<ResultDto> zcLists=reportResultService.selectValueSumDetailAll(map);
		//流动资产
		BigDecimal ldzc=new BigDecimal("0");
		BigDecimal tbldzc=new BigDecimal("0");
		BigDecimal hbldzc=new BigDecimal("0");
		//流动负债
		BigDecimal ldfz=new BigDecimal("0");
		BigDecimal tbldfz=new BigDecimal("0");
		BigDecimal hbldfz=new BigDecimal("0");
		//存货
		BigDecimal ch=new BigDecimal("0");
		BigDecimal tbch=new BigDecimal("0");
		BigDecimal hbch=new BigDecimal("0");
		//货币基金
		BigDecimal hbjj=new BigDecimal("0");
		BigDecimal tbhbjj=new BigDecimal("0");
		BigDecimal hbhbjj=new BigDecimal("0");
		//经营活动产生的现金流量净额
		BigDecimal jyhdcsxjll=new BigDecimal("0");
		BigDecimal tbjyhdcsxjll=new BigDecimal("0");
		BigDecimal hbjyhdcsxjll=new BigDecimal("0");
		//总资产
		BigDecimal zzc=new BigDecimal("0");
		BigDecimal tbzzc=new BigDecimal("0");
		BigDecimal hbzzc=new BigDecimal("0");
		//总负债
		BigDecimal zfz=new BigDecimal("0");
		BigDecimal tbzfz=new BigDecimal("0");
		BigDecimal hbzfz=new BigDecimal("0");
		//所有者权益
		BigDecimal syzqy=new BigDecimal("0");
		BigDecimal tbsyzqy=new BigDecimal("0");
		BigDecimal hbsyzqy=new BigDecimal("0");
		//ZCFZ-BX非流动负债
		BigDecimal fldfz=new BigDecimal("0");
		BigDecimal tbfldfz=new BigDecimal("0");
		BigDecimal hbfldfz=new BigDecimal("0");
		//非流动资产‘
		BigDecimal fldzc=new BigDecimal("0");
		BigDecimal tbfldzc=new BigDecimal("0");
		BigDecimal hbfldzc=new BigDecimal("0");
		//股东权益    =====>所有者权益合计
		BigDecimal gdqy=new BigDecimal("0");
		BigDecimal tbgdqy=new BigDecimal("0");
		BigDecimal hbgdqy=new BigDecimal("0");
		//应收账款
		BigDecimal yszk=new BigDecimal("0");
		BigDecimal tbyszk=new BigDecimal("0");
		BigDecimal hbyszk=new BigDecimal("0");
		//应付账款
		BigDecimal yfzk=new BigDecimal("0");
		BigDecimal tbyfzk=new BigDecimal("0");
		BigDecimal hbyfzk=new BigDecimal("0");
		//净利润
		BigDecimal jlr=new BigDecimal("0");
		BigDecimal tbjlr=new BigDecimal("0");
		BigDecimal hbjlr=new BigDecimal("0");
		//营业收入
		BigDecimal yysr=new BigDecimal("0");
		BigDecimal tbyysr=new BigDecimal("0");
		BigDecimal hbyysr=new BigDecimal("0");
		//营业成本
		BigDecimal yycb=new BigDecimal("0");
		BigDecimal tbyycb=new BigDecimal("0");
		BigDecimal hbyycb=new BigDecimal("0");
		//利息费用
		BigDecimal lxfy=new BigDecimal("0");
		BigDecimal tblxfy=new BigDecimal("0");
		BigDecimal hblxfy=new BigDecimal("0");
		//交易性金融资产===  以公允价值计量且其变动计入当期损益的金融资产
		BigDecimal jyxjrzc=new BigDecimal("0");
		BigDecimal tbjyxjrzc=new BigDecimal("0");
		BigDecimal hbjyxjrzc=new BigDecimal("0");
		//应收票据
		BigDecimal yspj=new BigDecimal("0");
		BigDecimal tbyspj=new BigDecimal("0");
		BigDecimal hbyspj=new BigDecimal("0");
		//应收利息 ZCFZ-L
		BigDecimal yslx=new BigDecimal("0");
		BigDecimal tbyslx=new BigDecimal("0");
		BigDecimal hbyslx=new BigDecimal("0");
		//应收股利ZCFZ-M
		BigDecimal ysgl=new BigDecimal("0");
		BigDecimal tbysgl=new BigDecimal("0");
		BigDecimal hbysgl=new BigDecimal("0");
		//其他应收款 ZCFZ-N
		BigDecimal qtysk=new BigDecimal("0");
		BigDecimal tbqtysk=new BigDecimal("0");
		BigDecimal hbqtysk=new BigDecimal("0");
		for (ResultDto r : zcLists) {
			BigDecimal bz=r.getBigSumMoney();
			BigDecimal tbbz=r.getTbSumMoney();
			BigDecimal hbbz=r.getHbSumMoney();
			if(r.getCode().equals("ZCFZ-F")){
				yspj=bz;
				tbyspj=tbbz;
				tbyspj=hbbz;
			}
			if(r.getCode().equals("ZCFZ-L")){
				yslx=bz;
				tbyslx=tbbz;
				hbyslx=hbbz;
			}
			if(r.getCode().equals("ZCFZ-M")){
				ysgl=bz;
				tbysgl=tbbz;
				hbysgl=hbbz;
			}
			if(r.getCode().equals("ZCFZ-N")){
				qtysk=bz;
				tbqtysk=tbbz;
				hbqtysk=hbbz;
			}
			if(r.getCode().equals("ZCFZ-T")){
				ldzc=bz;
				tbldzc=tbbz;
				hbldzc=hbbz;
			}
			if(r.getCode().equals("ZCFZ-BL")){
				ldfz=bz;
				tbldfz=tbbz;
				hbldfz=hbbz;
			}
			if(r.getCode().equals("ZCFZ-P")){
				ch=bz;
				tbch=tbbz;
				hbch=hbbz;
			}
			if(r.getCode().equals("ZCFZ-A")){
				hbjj=bz;
				tbhbjj=tbbz;
				hbhbjj=hbbz;
			}
			if(r.getCode().equals("ZCFZ-AN")){
				zzc=bz;
				tbzzc=tbbz;
				hbzzc=hbbz;
			}
			if(r.getCode().equals("ZCFZ-BY")){
				zfz=bz;
				tbzfz=tbbz;
				hbzfz=hbbz;
			}
			if(r.getCode().equals("ZCFZ-CM")){
				syzqy=bz;
				tbsyzqy=tbbz;
				hbsyzqy=hbbz;
			}
			if(r.getCode().equals("ZCFZ-BX")){
				fldfz=bz;
				tbfldfz=tbbz;
				hbfldfz=hbbz;
			}
			if(r.getCode().equals("ZCFZ-CM")){
				gdqy=bz;
				tbgdqy=tbbz;
				hbgdqy=hbbz;
			}
			if(r.getCode().equals("ZCFZ-G")){
				yszk=bz;
				tbyszk=tbbz;
				hbyszk=hbbz;
			}
			if(r.getCode().equals("ZCFZ-AV")){
				yfzk=bz;
				tbyfzk=tbbz;
				hbyfzk=hbbz;
			}
			if(r.getCode().equals("ZCFZ-AM")){
				fldzc=bz;
				tbfldzc=tbbz;
				hbfldzc=hbbz;
			}
			if(r.getCode().equals("ZCFZ-D")){
				jyxjrzc=bz;
				tbjyxjrzc=tbbz;
				hbjyxjrzc=hbbz;
			}
		}
		map.put("endTimes", null);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("orgId", orgId);
		map.put("useArea", null);
		map.put("reportType", 3);
		map.put("isZh", "1");
		List<ResultDto> zcLists2=reportResultService.selectValueSumDetailAll(map);
		for (ResultDto r : zcLists2) {
			BigDecimal bz=r.getBigSumMoney();
			BigDecimal tbbz=r.getTbSumMoney();
			BigDecimal hbbz=r.getHbSumMoney();
			if(r.getCode().equals("XJLL-Y")){
				jyhdcsxjll=bz;
				tbjyhdcsxjll=tbbz;
				hbjyhdcsxjll=hbbz;
			}
		}
		map.put("endTimes", null);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("orgId", orgId);
		map.put("useArea", null);
		map.put("reportType", 2);
		map.put("isZh", "1");
		List<ResultDto> zcLists3=reportResultService.selectValueSumDetailAll(map);
		for (ResultDto r : zcLists3) {
			BigDecimal bz=r.getBigSumMoney();
			BigDecimal tbbz=r.getTbSumMoney();
			BigDecimal hbbz=r.getHbSumMoney();
			if(r.getCode().equals("LR-AF")){
				jlr=bz;
				tbjlr=tbbz;
				hbjlr=hbbz;
			}
			if(r.getCode().equals("LR-A")){
				yysr=bz;
				tbyysr=tbbz;
				hbyysr=hbbz;
			}
			if(r.getCode().equals("LR-O")){
				lxfy=bz;
				tblxfy=tbbz;
				hblxfy=hbbz;
			}
			if(r.getCode().equals("LR-F")){
				yycb=bz;
				tbyycb=tbbz;
				hbyycb=hbbz;
			}

		}
		//=========短期偿债能力=========
		Map<String,Object> dqcznl = new HashMap<>();
		List<ResultDto> dqczList=new ArrayList<ResultDto>();
		ResultDto yyzbR=new ResultDto();
		BigDecimal yyzb=(ldzc.subtract(ldfz)).divide(new BigDecimal("10000"),2,RoundingMode.HALF_UP);
		BigDecimal tbyyzb=(tbldzc.subtract(tbldfz)).divide(new BigDecimal("10000"),2,RoundingMode.HALF_UP);
		BigDecimal hbyyzb=(hbldzc.subtract(hbldfz)).divide(new BigDecimal("10000"),2,RoundingMode.HALF_UP);
		yyzbR.setBigSumMoney(yyzb);
		yyzbR.setCode("YBLFX-A");
		yyzbR.setSumMoney(yyzb.toString()+"万");
		yyzbR.setIsDetails(1);
		yyzbR.setUseArea("bl5001");
		yyzbR.setCodeName("营运资本(万元)");
		if(tbyyzb.compareTo(BigDecimal.ZERO)!=0){
			//yyzbR.setOnRise((((yyzb.subtract(tbyyzb)).divide(tbyyzb.abs(),4,RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP)).toString());
			yyzbR.setOnRise(tbyyzb.toString());
		}else{
			yyzbR.setOnRise("-");
		}
		if(hbyyzb.compareTo(BigDecimal.ZERO)!=0){
			//yyzbR.setLinkRise((((yyzb.subtract(hbyyzb)).divide(hbyyzb.abs(),4,RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP)).toString());
			yyzbR.setLinkRise(hbyyzb.toString());
		}else{
			yyzbR.setLinkRise("-");
		}
		//流动比率
		dqczList.add(yyzbR);
		ResultDto ldblR=new ResultDto();
		ldblR= TbAndHb.getResultDto(ldzc, tbldzc, hbldzc, ldfz, tbldfz, hbldfz, "YBLFX-B", "流动比率", "","bl5002");
		dqczList.add(ldblR);
		//速动比率
		ResultDto sdblR=new ResultDto();
		/*BigDecimal sd=ldzc.subtract(ch);
		BigDecimal tbsd=tbldzc.subtract(tbch);
		BigDecimal hbsd=hbldzc.subtract(hbch);*/
		BigDecimal sd=hbjj.add(jyxjrzc).add(yspj).add(yszk).add(yslx).add(ysgl).add(qtysk);
		BigDecimal tbsd=tbhbjj.add(tbjyxjrzc).add(tbyspj).add(tbyszk).add(tbyslx).add(tbysgl).add(tbqtysk);
		BigDecimal hbsd=hbhbjj.add(hbjyxjrzc).add(hbyspj).add(hbyszk).add(hbyslx).add(hbysgl).add(hbqtysk);
		sdblR= TbAndHb.getResultDto(sd, tbsd, hbsd, ldfz, tbldfz, hbldfz, "YBLFX-C", "速动比率", "","bl5003");
		dqczList.add(sdblR);
		//现金比率hbjj
		ResultDto xjblR=new ResultDto();
		BigDecimal rxj=hbjj.add(jyxjrzc);
		BigDecimal tbrxj=tbhbjj.add(tbjyxjrzc);
		BigDecimal hbrxj=hbhbjj.add(hbjyxjrzc);
		xjblR= TbAndHb.getResultDto(rxj, tbrxj, hbrxj, ldfz, tbldfz, hbldfz, "YBLFX-D", "现金比率", "","bl5004");
		dqczList.add(xjblR);
		//现金流量比率
		ResultDto xjllblR=new ResultDto();
		xjllblR= TbAndHb.getResultDto(jyhdcsxjll, tbjyhdcsxjll, hbjyhdcsxjll, ldfz, tbldfz, hbldfz, "YBLFX-E", "现金流量比率", "","bl5005");
		dqczList.add(xjllblR);
		dqcznl.put("total", "短期偿债能力");
		dqcznl.put("xq", dqczList);
		rmap.put("dqcznl", dqcznl);
		//========长期偿债能力========
		Map<String,Object> cqcznl = new HashMap<>();
		List<ResultDto> cqcznlList=new ArrayList<ResultDto>();
		//资产负债率
		ResultDto zcfzlR=new ResultDto();
		zcfzlR= TbAndHb.getResultDto(zfz, tbzfz, hbzfz,zzc, tbzzc, hbzzc, "YBLFX-F", "资产负债率", "%","bl5101");
		cqcznlList.add(zcfzlR);
		//2、产权比率
		ResultDto cqblR=new ResultDto();
		cqblR= TbAndHb.getResultDto(zfz, tbzfz, hbzfz, syzqy, tbsyzqy, hbsyzqy, "YBLFX-G", "产权比率", "","bl5102");
		cqcznlList.add(cqblR);
		//3、权益乘数
		ResultDto qycsR=new ResultDto();
		qycsR= TbAndHb.getResultDto(zzc, tbzzc, hbzzc, syzqy, tbsyzqy, hbsyzqy, "YBLFX-H", "权益乘数", "","bl5103");
		cqcznlList.add(qycsR);
		//长期资本负债率
		ResultDto cqzbfzR=new ResultDto();
		BigDecimal s=fldfz.add(syzqy);
		BigDecimal tbs=tbfldfz.add(tbsyzqy);
		BigDecimal hbs=hbfldfz.add(hbsyzqy);
		cqzbfzR= TbAndHb.getResultDto(fldfz, tbfldfz, hbfldfz, s, tbs, hbs, "YBLFX-I", "长期资本负债率", "%","bl5104");
		cqcznlList.add(cqzbfzR);
		//7、现金流量债务比
		ResultDto xjllzwbR=new ResultDto();
		xjllzwbR= TbAndHb.getResultDto(jyhdcsxjll, tbjyhdcsxjll, hbjyhdcsxjll, zfz, tbzfz, hbzfz, "YBLFX-K", "现金流量债务比", "","bl5107");
		cqcznlList.add(xjllzwbR);
		cqcznl.put("total", "长期偿债能力");
		cqcznl.put("xq", cqcznlList);
		rmap.put("cqcznl", cqcznl);
		//=========营运能力============
		Map<String,Object> yynl = new HashMap<>();
		List<ResultDto> yynlList=new ArrayList<ResultDto>();
		//应收账款周转天数
		ResultDto yynlR=new ResultDto();
		yynlR= TbAndHb.getResultDto2(yysr, tbyysr, hbyysr, yszk, tbyszk, hbyszk, "YBLFX-L", "应收账款周转天数(天)", "", days,tbdays,hbdays,"bl5201");
		yynlList.add(yynlR);
		//存货周转天数
		ResultDto chzzR=new ResultDto();
		chzzR= TbAndHb.getResultDto2(yycb, tbyycb, hbyycb,ch, tbch, hbch,  "YBLFX-N", "存货周转天数(天)", "", days,tbdays,hbdays,"bl5202");
		yynlList.add(chzzR);
		//3、应付账款周转天数
		ResultDto yfzkzzR=new ResultDto();
		yfzkzzR= TbAndHb.getResultDto2(yycb, tbyycb, hbyycb,yfzk, tbyfzk, hbyfzk,  "YBLFX-O", "应付账款周转天数(天)", "", days,tbdays,hbdays,"bl5203");
		yynlList.add(yfzkzzR);
		//4、流动资产周转天数
		ResultDto ldzczzR=new ResultDto();
		ldzczzR= TbAndHb.getResultDto2(yysr, tbyysr, hbyysr,ldzc, tbldzc, hbldzc,  "YBLFX-P", "流动资产周转天数(天)", "", days,tbdays,hbdays,"bl5204");
		yynlList.add(ldzczzR);
		//5、营业资本周转天数
		ResultDto yyzbzzR=new ResultDto();
		BigDecimal yyzbzzs=ldzc.subtract(ldfz);
		BigDecimal tbyyzbzzs=tbldzc.subtract(tbldfz);
		BigDecimal hbyyzbzzs=hbldzc.subtract(hbldfz);
		yyzbzzR= TbAndHb.getResultDto2(yysr, tbyysr, hbyysr,yyzbzzs, tbyyzbzzs, hbyyzbzzs,  "YBLFX-Q", "营运资本周转天数(天)", "", days,tbdays,hbdays,"bl5205");
		yynlList.add(yyzbzzR);
		//6、非流动资产周转天数
		ResultDto fldzczzR=new ResultDto();
		fldzczzR= TbAndHb.getResultDto2(yysr, tbyysr, hbyysr,fldzc, tbfldzc, hbfldzc,  "YBLFX-R", "非流动资产周转天数(天)", "", days,tbdays,hbdays,"bl5206");
		yynlList.add(fldzczzR);
		//7、总资产周转天数
		ResultDto zzczzR=new ResultDto();
		zzczzR= TbAndHb.getResultDto2(yysr, tbyysr, hbyysr,zzc, tbzzc, hbzzc,  "YBLFX-S", "总资产周转天数(天)", "", days,tbdays,hbdays,"bl5207");
		yynlList.add(zzczzR);
		yynl.put("total", "营运能力");
		yynl.put("xq", yynlList);
		rmap.put("yynl", yynl);
		//===============盈利能力===========
		Map<String,Object> ylnl = new HashMap<>();
		List<ResultDto> ylnlList=new ArrayList<ResultDto>();
		//销售净利润率
		ResultDto xsjlR=new ResultDto();
		xsjlR= TbAndHb.getResultDto(jlr, tbjlr, hbjlr, yysr, tbyysr, hbyysr, "YBLFX-T", "销售净利润率", "%","bl5301");
		ylnlList.add(xsjlR);
		// (yysr-yycb)/yysr 营业收入利润率
		ResultDto yysrlrl=new ResultDto();
		yysrlrl.setCode("YBLFX-SR");
		yysrlrl.setCodeName("营业收入利润率");
		String multiply = yysr.compareTo(BigDecimal.ZERO)==0?"-":(yysr.subtract(yycb)).divide(yysr, 4, RoundingMode.CEILING).multiply(new BigDecimal("100"))+"%";
		yysrlrl.setSumMoney(multiply);
		yysrlrl.setUseArea("bl5304");
		yysrlrl.setOnRise("-");
		yysrlrl.setLinkRise("-");
        ylnlList.add(yysrlrl);
		// (yysr-yycb)/yycb 营业成本利润率
		ResultDto yycblrl=new ResultDto();
		yycblrl.setCode("YBLFX-CB");
		String multiply1 = yycb.compareTo(BigDecimal.ZERO)==0?"-":(yysr.subtract(yycb)).divide(yycb, 4, RoundingMode.CEILING).multiply(new BigDecimal("100"))+"%";
		yycblrl.setSumMoney(multiply1);
		yycblrl.setCodeName("营业成本利润率");
		yycblrl.setUseArea("bl5305");
		yycblrl.setOnRise("-");
		yycblrl.setLinkRise("-");
        ylnlList.add(yycblrl);
		//2、总资产净利润率
		ResultDto zzcjlrR=new ResultDto();
		zzcjlrR= TbAndHb.getResultDto(jlr, tbjlr, hbjlr, zzc, tbzzc, hbzzc, "YBLFX-W", "总资产净利润率", "%","bl5302");
		ylnlList.add(zzcjlrR);
		//3、权益净利润率
		ResultDto qyjlrR=new ResultDto();
		qyjlrR= TbAndHb.getResultDto(jlr, tbjlr, hbjlr, gdqy, tbgdqy, hbgdqy, "YBLFX-Y", "权益净利润率", "%","bl5303");
		ylnlList.add(qyjlrR);
		yynl.put("total", "盈利能力");
		ylnl.put("xq", ylnlList);
		rmap.put("ylnl", ylnl);
		return rmap;
	}
	@Override
	public ResultDto getBilvResult(String startTime, String endTime, String orgId, String useArea) throws ParseException{
		Map<String,Object> map = new HashMap<>();
		map.put("startTimes", startTime);
		map.put("endTimes", endTime);
		map.put("orgId", orgId);
		map.put("useArea", null);
		map.put("reportType", 1);
		map.put("isZh", "1");
		int days = TbAndHb.getDays(startTime, endTime);
		int tbdays= TbAndHb.getDays(DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(startTime,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4), "yyyy-MM-dd"), DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime,"yyyy-MM-dd"),"yyyy-MM-dd",-1,4), "yyyy-MM-dd"));
		int monthDiff = Dates.getDateTimes(startTime,endTime)+1;
		int hbdays= TbAndHb.getDays(DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(startTime,"yyyy-MM-dd"),"yyyy-MM-dd",monthDiff*(-1),3), "yyyy-MM-dd"), DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime,"yyyy-MM-dd"),"yyyy-MM-dd",monthDiff*(-1),3), "yyyy-MM-dd"));
		List<ResultDto> zcLists=reportResultService.selectValueSumDetailAll(map);
		//流动资产
		BigDecimal ldzc=new BigDecimal("0");
		BigDecimal tbldzc=new BigDecimal("0");
		BigDecimal hbldzc=new BigDecimal("0");
		//流动负债
		BigDecimal ldfz=new BigDecimal("0");
		BigDecimal tbldfz=new BigDecimal("0");
		BigDecimal hbldfz=new BigDecimal("0");
		//存货
		BigDecimal ch=new BigDecimal("0");
		BigDecimal tbch=new BigDecimal("0");
		BigDecimal hbch=new BigDecimal("0");
		//货币基金
		BigDecimal hbjj=new BigDecimal("0");
		BigDecimal tbhbjj=new BigDecimal("0");
		BigDecimal hbhbjj=new BigDecimal("0");
		//经营活动产生的现金流量净额
		BigDecimal jyhdcsxjll=new BigDecimal("0");
		BigDecimal tbjyhdcsxjll=new BigDecimal("0");
		BigDecimal hbjyhdcsxjll=new BigDecimal("0");
		//总资产
		BigDecimal zzc=new BigDecimal("0");
		BigDecimal tbzzc=new BigDecimal("0");
		BigDecimal hbzzc=new BigDecimal("0");
		//总负债
		BigDecimal zfz=new BigDecimal("0");
		BigDecimal tbzfz=new BigDecimal("0");
		BigDecimal hbzfz=new BigDecimal("0");
		//所有者权益
		BigDecimal syzqy=new BigDecimal("0");
		BigDecimal tbsyzqy=new BigDecimal("0");
		BigDecimal hbsyzqy=new BigDecimal("0");
		//ZCFZ-BX非流动负债
		BigDecimal fldfz=new BigDecimal("0");
		BigDecimal tbfldfz=new BigDecimal("0");
		BigDecimal hbfldfz=new BigDecimal("0");
		//非流动资产‘
		BigDecimal fldzc=new BigDecimal("0");
		BigDecimal tbfldzc=new BigDecimal("0");
		BigDecimal hbfldzc=new BigDecimal("0");
		//股东权益
		BigDecimal gdqy=new BigDecimal("0");
		BigDecimal tbgdqy=new BigDecimal("0");
		BigDecimal hbgdqy=new BigDecimal("0");
		//应收账款
		BigDecimal yszk=new BigDecimal("0");
		BigDecimal tbyszk=new BigDecimal("0");
		BigDecimal hbyszk=new BigDecimal("0");
		//应付账款
		BigDecimal yfzk=new BigDecimal("0");
		BigDecimal tbyfzk=new BigDecimal("0");
		BigDecimal hbyfzk=new BigDecimal("0");
		//净利润
		BigDecimal jlr=new BigDecimal("0");
		BigDecimal tbjlr=new BigDecimal("0");
		BigDecimal hbjlr=new BigDecimal("0");
		//营业收入
		BigDecimal yysr=new BigDecimal("0");
		BigDecimal tbyysr=new BigDecimal("0");
		BigDecimal hbyysr=new BigDecimal("0");
		//营业成本
		BigDecimal yycb=new BigDecimal("0");
		BigDecimal tbyycb=new BigDecimal("0");
		BigDecimal hbyycb=new BigDecimal("0");
		//利息费用
		BigDecimal lxfy=new BigDecimal("0");
		BigDecimal tblxfy=new BigDecimal("0");
		BigDecimal hblxfy=new BigDecimal("0");
		//交易性金融资产===  以公允价值计量且其变动计入当期损益的金融资产
		BigDecimal jyxjrzc=new BigDecimal("0");
		BigDecimal tbjyxjrzc=new BigDecimal("0");
		BigDecimal hbjyxjrzc=new BigDecimal("0");
		//应收票据
		BigDecimal yspj=new BigDecimal("0");
		BigDecimal tbyspj=new BigDecimal("0");
		BigDecimal hbyspj=new BigDecimal("0");
		//应收利息 ZCFZ-L
		BigDecimal yslx=new BigDecimal("0");
		BigDecimal tbyslx=new BigDecimal("0");
		BigDecimal hbyslx=new BigDecimal("0");
		//应收股利ZCFZ-M
		BigDecimal ysgl=new BigDecimal("0");
		BigDecimal tbysgl=new BigDecimal("0");
		BigDecimal hbysgl=new BigDecimal("0");
		//其他应收款 ZCFZ-N
		BigDecimal qtysk=new BigDecimal("0");
		BigDecimal tbqtysk=new BigDecimal("0");
		BigDecimal hbqtysk=new BigDecimal("0");
		for (ResultDto r : zcLists) {
			BigDecimal bz=r.getBigSumMoney();
			BigDecimal tbbz=r.getTbSumMoney();
			BigDecimal hbbz=r.getHbSumMoney();
			if(r.getCode().equals("ZCFZ-F")){
				yspj=bz;
				tbyspj=tbbz;
				tbyspj=hbbz;
			}
			if(r.getCode().equals("ZCFZ-L")){
				yslx=bz;
				tbyslx=tbbz;
				hbyslx=hbbz;
			}
			if(r.getCode().equals("ZCFZ-M")){
				ysgl=bz;
				tbysgl=tbbz;
				hbysgl=hbbz;
			}
			if(r.getCode().equals("ZCFZ-N")){
				qtysk=bz;
				tbqtysk=tbbz;
				hbqtysk=hbbz;
			}
			if(r.getCode().equals("ZCFZ-T")){
				ldzc=bz;
				tbldzc=tbbz;
				hbldzc=hbbz;
			}
			if(r.getCode().equals("ZCFZ-BL")){
				ldfz=bz;
				tbldfz=tbbz;
				hbldfz=hbbz;
			}
			if(r.getCode().equals("ZCFZ-P")){
				ch=bz;
				tbch=tbbz;
				hbch=hbbz;
			}
			if(r.getCode().equals("ZCFZ-A")){
				hbjj=bz;
				tbhbjj=tbbz;
				hbhbjj=hbbz;
			}
			if(r.getCode().equals("ZCFZ-AN")){
				zzc=bz;
				tbzzc=tbbz;
				hbzzc=hbbz;
			}
			if(r.getCode().equals("ZCFZ-BY")){
				zfz=bz;
				tbzfz=tbbz;
				hbzfz=hbbz;
			}
			if(r.getCode().equals("ZCFZ-CM")){
				syzqy=bz;
				tbsyzqy=tbbz;
				hbsyzqy=hbbz;
			}
			if(r.getCode().equals("ZCFZ-BX")){
				fldfz=bz;
				tbfldfz=tbbz;
				hbfldfz=hbbz;
			}
			if(r.getCode().equals("ZCFZ-CM")){
				gdqy=bz;
				tbgdqy=tbbz;
				hbgdqy=hbbz;
			}
			if(r.getCode().equals("ZCFZ-G")){
				yszk=bz;
				tbyszk=tbbz;
				hbyszk=hbbz;
			}
			if(r.getCode().equals("ZCFZ-AV")){
				yfzk=bz;
				tbyfzk=tbbz;
				hbyfzk=hbbz;
			}
			if(r.getCode().equals("ZCFZ-AM")){
				fldzc=bz;
				tbfldzc=tbbz;
				hbfldzc=hbbz;
			}
			if(r.getCode().equals("ZCFZ-D")){
				jyxjrzc=bz;
				tbjyxjrzc=tbbz;
				hbjyxjrzc=hbbz;
			}
		}
		map.put("endTimes", null);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("orgId", orgId);
		map.put("useArea", null);
		map.put("reportType", 3);
		map.put("isZh", "1");
		List<ResultDto> zcLists2=reportResultService.selectValueSumDetailAll(map);
		for (ResultDto r : zcLists2) {
			BigDecimal bz=r.getBigSumMoney();
			BigDecimal tbbz=r.getTbSumMoney();
			BigDecimal hbbz=r.getHbSumMoney();
			if(r.getCode().equals("XJLL-Y")){
				jyhdcsxjll=bz;
				tbjyhdcsxjll=tbbz;
				hbjyhdcsxjll=hbbz;
			}
		}
		map.put("endTimes", null);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("orgId", orgId);
		map.put("useArea", null);
		map.put("reportType", 2);
		map.put("isZh", "1");
		List<ResultDto> zcLists3=reportResultService.selectValueSumDetailAll(map);
		for (ResultDto r : zcLists3) {
			BigDecimal bz=r.getBigSumMoney();
			BigDecimal tbbz=r.getTbSumMoney();
			BigDecimal hbbz=r.getHbSumMoney();
			if(r.getCode().equals("LR-AF")){
				jlr=bz;
				tbjlr=tbbz;
				hbjlr=hbbz;
			}
			if(r.getCode().equals("LR-A")){
				yysr=bz;
				tbyysr=tbbz;
				hbyysr=hbbz;
			}
			if(r.getCode().equals("LR-O")){
				lxfy=bz;
				tblxfy=tbbz;
				hblxfy=hbbz;
			}
			if(r.getCode().equals("LR-F")){
				yycb=bz;
				tbyycb=tbbz;
				hbyycb=hbbz;
			}

		}
		//=========短期偿债能力=========
		ResultDto r=new ResultDto();
		BigDecimal yyzb=(ldzc.subtract(ldfz)).divide(new BigDecimal("10000"),2,RoundingMode.HALF_UP);
		BigDecimal tbyyzb=(tbldzc.subtract(tbldfz)).divide(new BigDecimal("10000"),2,RoundingMode.HALF_UP);
		BigDecimal hbyyzb=(hbldzc.subtract(hbldfz)).divide(new BigDecimal("10000"),2,RoundingMode.HALF_UP);
		if(useArea.equals("bl5001")){
			r.setBigSumMoney(yyzb);
			r.setCode("YBLFX-A");
			r.setSumMoney(yyzb.toString());
			r.setTbSumMoney(tbyyzb);
			r.setTbSumMoneyStr(tbyyzb.toString());
			r.setIsDetails(1);
			r.setUseArea("bl5001");
			r.setCodeName("营运资本");
			if(tbyyzb.compareTo(BigDecimal.ZERO)!=0){
				//yyzbR.setOnRise((((yyzb.subtract(tbyyzb)).divide(tbyyzb.abs(),4,RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP)).toString());
				r.setOnRise(tbyyzb.toString()+"万");
			}else{
				r.setOnRise("-");
			}
			if(hbyyzb.compareTo(BigDecimal.ZERO)!=0){
				//yyzbR.setLinkRise((((yyzb.subtract(hbyyzb)).divide(hbyyzb.abs(),4,RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP)).toString());
				r.setLinkRise(hbyyzb.toString()+"万");
			}else{
				r.setLinkRise("-");
			}
		}


		//流动比率
		if(useArea.equals("bl5002")){
			r= TbAndHb.getResultDto(ldzc, tbldzc, hbldzc, ldfz, tbldfz, hbldfz, "YBLFX-B", "流动比率", "","bl5002");
		}
		//速动比率
		if(useArea.equals("bl5003")){
			BigDecimal sd=hbjj.add(jyxjrzc).add(yspj).add(yszk).add(yslx).add(ysgl).add(qtysk);
			BigDecimal tbsd=tbhbjj.add(tbjyxjrzc).add(tbyspj).add(tbyszk).add(tbyslx).add(tbysgl).add(tbqtysk);
			BigDecimal hbsd=hbhbjj.add(hbjyxjrzc).add(hbyspj).add(hbyszk).add(hbyslx).add(hbysgl).add(hbqtysk);
			r= TbAndHb.getResultDto(sd, tbsd, hbsd, ldfz, tbldfz, hbldfz, "YBLFX-C", "速动比率", "","bl5003");
		}
		//现金比率hbjj
		if(useArea.equals("bl5004")){
			BigDecimal rxj=hbjj.add(jyxjrzc);
			BigDecimal tbrxj=tbhbjj.add(tbjyxjrzc);
			BigDecimal hbrxj=hbhbjj.add(hbjyxjrzc);
			r= TbAndHb.getResultDto(rxj, tbrxj, hbrxj, ldfz, tbldfz, hbldfz, "YBLFX-D", "现金比率", "","bl5004");
		}
		//现金流量比率
		if(useArea.equals("bl5005")){
			r= TbAndHb.getResultDto(jyhdcsxjll, tbjyhdcsxjll, hbjyhdcsxjll, ldfz, tbldfz, hbldfz, "YBLFX-E", "现金流量比率", "","bl5005");
		}
		//========长期偿债能力========
		//资产负债率
		if(useArea.equals("bl5101")){
			r= TbAndHb.getResultDto(zfz, tbzfz, hbzfz,zzc, tbzzc, hbzzc, "YBLFX-F", "资产负债率", "%","bl5101");
		}
		//2、产权比率
		if(useArea.equals("bl5102")){
			r= TbAndHb.getResultDto(zfz, tbzfz, hbzfz, syzqy, tbsyzqy, hbsyzqy, "YBLFX-G", "产权比率", "","bl5102");
		}
		//3、权益乘数
		if(useArea.equals("bl5103")){
			r= TbAndHb.getResultDto(zzc, tbzzc, hbzzc, syzqy, tbsyzqy, hbsyzqy, "YBLFX-H", "权益乘数", "","bl5103");
		}
		//长期资本负债率
		if(useArea.equals("bl5104")){
			BigDecimal s=fldfz.add(syzqy);
			BigDecimal tbs=tbfldfz.add(tbsyzqy);
			BigDecimal hbs=hbfldfz.add(hbsyzqy);
			r= TbAndHb.getResultDto(fldfz, tbfldfz, hbfldfz, s, tbs, hbs, "YBLFX-I", "长期资本负债率", "%","bl5104");
		}
		//7、现金流量债务比
		if(useArea.equals("bl5107")){
			r= TbAndHb.getResultDto(jyhdcsxjll, tbjyhdcsxjll, hbjyhdcsxjll, zfz, tbzfz, hbzfz, "YBLFX-K", "现金流量债务比", "","bl5107");
		}
		//=========营运能力============
		//应收账款周转天数
		if(useArea.equals("bl5201")){
			r= TbAndHb.getResultDto2(yysr, tbyysr, hbyysr, yszk, tbyszk, hbyszk, "YBLFX-L", "应收账款周转天数", "天", days,tbdays,hbdays,"bl5201");
		}
		//存货周转天数
		if(useArea.equals("bl5202")){
			r= TbAndHb.getResultDto2(yycb, tbyycb, hbyycb,ch, tbch, hbch,  "YBLFX-N", "存货周转天数", "天", days,tbdays,hbdays,"bl5202");
		}
		//3、应付账款周转天数
		if(useArea.equals("bl5203")){
			r= TbAndHb.getResultDto2(yycb, tbyycb, hbyycb,yfzk, tbyfzk, hbyfzk,  "YBLFX-O", "应付账款周转天数", "天", days,tbdays,hbdays,"bl5203");
		}
		//4、流动资产周转天数
		if(useArea.equals("bl5204")){
			r= TbAndHb.getResultDto2(yysr, tbyysr, hbyysr,ldzc, tbldzc, hbldzc,  "YBLFX-P", "流动资产周转天数", "天", days,tbdays,hbdays,"bl5204");
		}
		//5、营业资本周转天数
		if(useArea.equals("bl5205")){
			//ResultDto yyzbzzR=new ResultDto();
			BigDecimal yyzbzzs=ldzc.subtract(ldfz);
			BigDecimal tbyyzbzzs=tbldzc.subtract(tbldfz);
			BigDecimal hbyyzbzzs=hbldzc.subtract(hbldfz);
			r= TbAndHb.getResultDto2(yysr, tbyysr, hbyysr,yyzbzzs, tbyyzbzzs, hbyyzbzzs,  "YBLFX-Q", "营运资本周转天数", "天", days,tbdays,hbdays,"bl5205");
			//BigDecimal yyzbzzs=ldzc.subtract(ldfz);
			//BigDecimal tbyyzbzzs=tbldzc.subtract(tbldfz);
			//BigDecimal hbyyzbzzs=hbldzc.subtract(hbldfz);
			//r=TbAndHb.getResultDto2(yysr, tbyysr, hbyysr,yyzbzzs, tbyyzbzzs, hbyyzbzzs,  "YBLFX-Q", "营运资本周转天数", "天", days,tbdays,hbdays,"bl5205");
		}
		//6、非流动资产周转天数
		if(useArea.equals("bl5206")){
			r= TbAndHb.getResultDto2(yysr, tbyysr, hbyysr,fldzc, tbfldzc, hbfldzc,  "YBLFX-R", "非流动资产周转天数", "天", days,tbdays,hbdays,"bl5205");
		}
		//7、总资产周转天数
		if(useArea.equals("bl5207")){
			r= TbAndHb.getResultDto2(yysr, tbyysr, hbyysr,zzc, tbzzc, hbzzc,  "YBLFX-S", "总资产周转天数", "天", days,tbdays,hbdays,"bl5207");
		}
		//===============盈利能力===========
		//销售净利润率
		if(useArea.equals("bl5301")){
			r= TbAndHb.getResultDto(jlr, tbjlr, hbjlr, yysr, tbyysr, hbyysr, "YBLFX-T", "销售净利润率", "%","bl5301");
		}
		//2、总资产净利润率
		if(useArea.equals("bl5302")){
			r= TbAndHb.getResultDto(jlr, tbjlr, hbjlr, zzc, tbzzc, hbzzc, "YBLFX-W", "总资产净利润率", "%","bl5302");
		}
		//3、权益净利润率
		if(useArea.equals("bl5303")){
			r= TbAndHb.getResultDto(jlr, tbjlr, hbjlr, gdqy, tbgdqy, hbgdqy, "YBLFX-Y", "权益净利润率", "%","bl5303");
		}
		return r;
	}
}

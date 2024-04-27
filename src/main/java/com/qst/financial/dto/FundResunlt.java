package com.qst.financial.dto;

import com.qst.financial.util.IrrUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资金结果
 * @author yj
 *
 */
public class FundResunlt {
	/**
	 * 增值税
	 * @return
	 */
	public Map<String,Object>  addedValueTax(String startTime,String endTime,String tax,String pay,String dpay,String qpay,String funds,String fundsHand,String loan,
			String oneIncome,String oneNoIncome,String limits,String incomeGrowth,String yycb,String xsfy,String glfy,String cwfy,String zjsr,String ycmqs,
			String srsy,String yycbzz,String zcje,String fjsl,String zzsfh,String sds,String jkll,String tzl,String allYear,String startYear){
		int startYearInt=Integer.parseInt(startYear);
		int stTime=Integer.parseInt(startTime);
		int allYearInt=Integer.parseInt(allYear);
		//组合空寂
		int[] arr=new int[allYearInt];
		for(int i=0;i<allYearInt;i++){
			arr[i]=stTime;
			stTime=stTime+1;
		}
		BigDecimal bsrsy=new BigDecimal(srsy);
		BigDecimal boneNoIncome=new BigDecimal(oneNoIncome);
		BigDecimal bincomeGrowth=(new BigDecimal(incomeGrowth)).divide(new BigDecimal("100"));
		double dincomeGrowth=bincomeGrowth.doubleValue();
		//营业收入销项税额
		BigDecimal[] yysr=new BigDecimal[allYearInt];
		for(int i=0;i<allYearInt;i++){
			if(i<startYearInt){
				yysr[i]=new BigDecimal("0.00");
			}else{
				double r1=(double) Math.pow(1+dincomeGrowth, i-startYearInt);
				BigDecimal br1=new BigDecimal(r1);
				BigDecimal r=boneNoIncome.multiply(br1).multiply(bsrsy);
				yysr[i]=r.setScale(2, BigDecimal.ROUND_HALF_UP);
				//sryx[i]=;
			}
		}
		//增值税（销项）
		BigDecimal[] zzsx=new BigDecimal[allYearInt];
		for(int i=0;i<allYearInt;i++){
			zzsx[i]=yysr[i];
		}
		return null;
	}
	/**
	 * 资金来源中的借款的还本付息
	 */
	public static Map<String,Object>  sourcesFunds(String loan,String jkll,String allYear,String startTime){
		Map<String,Object> map=new HashMap<String,Object>();
		int allYearInt=(Integer.parseInt(allYear))+1;
		int stTime=Integer.parseInt(startTime);
		BigDecimal bjkll=new BigDecimal(jkll).divide(new BigDecimal("100"));
		//BigDecimal bjkll=new BigDecimal(jkll);
		BigDecimal ballYear=new BigDecimal(allYear);
		//组合空寂
		int[] arr=new int[allYearInt];
		//期初借款
		BigDecimal[] qcjk=new BigDecimal[allYearInt];
		qcjk[0]=new BigDecimal("0.00");
		//本年应计利息
		BigDecimal[] bnlx=new BigDecimal[allYearInt];
		bnlx[0]=new BigDecimal("0.00");
		//本期还本
		BigDecimal[] bqhk=new BigDecimal[allYearInt];
		bqhk[0]=new BigDecimal("0.00");
		//本年付息
		BigDecimal[] bqfx=new BigDecimal[allYearInt];
		bqfx[0]=new BigDecimal("0.00");
		//本期偿还本息
		BigDecimal[] bqchbx=new BigDecimal[allYearInt];
		bqchbx[0]=new BigDecimal("0.00");
		//累计偿还本息
		BigDecimal[] ljchbx=new BigDecimal[allYearInt];
		//期末借款
		BigDecimal[] qmjk=new BigDecimal[allYearInt];
		qmjk[0]=new BigDecimal("0.00");
		ljchbx[0]=new BigDecimal("0.00");
		BigDecimal bloan=new BigDecimal(loan);
		BigDecimal bt=new BigDecimal("0");             //利息累计
		BigDecimal lj=new BigDecimal("0");            //资本累计
		BigDecimal ljlx=new BigDecimal("0");           //累计本息
		BigDecimal pj=bloan.divide(ballYear, 2, RoundingMode.HALF_UP);  
		for(int i=1;i<allYearInt;i++){
			arr[i]=stTime;
			stTime=stTime+1;
			if(i==(allYearInt-1)){
				bqhk[i]=bloan.subtract(lj);
				qcjk[i]=bloan.subtract(lj);
				qmjk[i]=new BigDecimal("0");
				lj=lj.add(bqhk[i]);
				//bnlx[i]=qcjk[i].multiply(bjkll).divide(new BigDecimal("100"),2, RoundingMode.HALF_UP);
				bnlx[i]=(((qcjk[i].add(qmjk[i])).divide(new BigDecimal("2"))).multiply(bjkll)).setScale(2, BigDecimal.ROUND_HALF_UP);
				bqfx[i]=bnlx[i];
				bqchbx[i]=bnlx[i].add(bqhk[i]);
				ljlx=ljlx.add(bqchbx[i]);
				ljchbx[i]=ljlx;
				bt.add(bnlx[i]);
			}else{
				bqhk[i]=pj;
				qcjk[i]=bloan.subtract(lj);
				qmjk[i]=qcjk[i].subtract(bqhk[i]);
				lj=lj.add(bqhk[i]);
				//bnlx[i]=(qcjk[i].multiply(bjkll)).divide(new BigDecimal("100"),2, RoundingMode.HALF_UP);
				bnlx[i]=(((qcjk[i].add(qmjk[i])).divide(new BigDecimal("2"))).multiply(bjkll)).setScale(2, BigDecimal.ROUND_HALF_UP);
				bqfx[i]=bnlx[i];
				bqchbx[i]=bnlx[i].add(bqhk[i]);
				ljlx=ljlx.add(bqchbx[i]);
				ljchbx[i]=ljlx;
				bt.add(bnlx[i]);
				
			}
		}
		map.put("qcjk", qcjk);
		map.put("qmjk", qmjk);
		map.put("bnlx", bnlx);
		map.put("bqhk", bqhk);
		map.put("bqfx", bqfx);
		map.put("bqchbx", bqchbx);
		map.put("ljchbx", ljchbx);
		return map;
	}
	/*
	 * 利润表&增值税
	 * @return
	 */
	public static Map<String,Object>  profit(String startTime,String endTime,String tax,String pay,String dpay,String qpay,String funds,String fundsHand,String loan,
			String oneIncome,String oneNoIncome,String limits,String incomeGrowth,String yycb,String xsfy,String glfy,String cwfy,String zjsr,String ycmqs,
			String srsy,String yycbzz,String zcje,String fjsl,String zzsfh,String sds,String jkll,String tzl,String allYear,String startYear){
		int startYearInt=Integer.parseInt(startYear);
		int stTime=Integer.parseInt(startTime);
		//int enTime=Integer.parseInt(endTime);
		int allYearInt=Integer.parseInt(allYear)+1;
		//组合空寂
		int[] arr=new int[allYearInt];
		for(int i=0;i<allYearInt;i++){
			arr[i]=stTime;
			stTime=stTime+1;
		}
		Map<String, Object> sourcesMap=sourcesFunds(loan, jkll, allYear, startTime);
		BigDecimal[] bqfx= (BigDecimal[]) sourcesMap.get("bqfx");//本年付息
		//BigDecimal[] bqfx=(BigDecimal[]) sourcesMap.get("bqfx");
		BigDecimal bsrsy=new BigDecimal(srsy).divide(new BigDecimal("100"));
		BigDecimal bcwfy=new BigDecimal(cwfy).divide(new BigDecimal("100"));      //财务费用收入比	22
		BigDecimal byycb=new BigDecimal(yycb).divide(new BigDecimal("100"));       //营业成本收入比	19
		BigDecimal bxsfy=new BigDecimal(xsfy).divide(new BigDecimal("100"));			//销售费用收入比	20
		BigDecimal bglfy=new BigDecimal(glfy).divide(new BigDecimal("100"));             //管理费用收入比     21
		BigDecimal bsds=new BigDecimal(sds).divide(new BigDecimal("100"));                //所得税   32
		BigDecimal byycbzz=new BigDecimal(yycbzz).divide(new BigDecimal("100")); 			//营业成本适用的增值税税率     28
		BigDecimal bzzsfh=new BigDecimal(zzsfh).divide(new BigDecimal("100"));                //增值税返还率    31
		BigDecimal bfjsl=new BigDecimal(fjsl).divide(new BigDecimal("100"));                //附加税率    30
		//BigDecimal bstartYear=new BigDecimal(startYear);
		BigDecimal boneNoIncome=new BigDecimal(oneNoIncome);
		BigDecimal bdpay=new BigDecimal(dpay);
		BigDecimal bincomeGrowth=(new BigDecimal(incomeGrowth)).divide(new BigDecimal("100"));
		double dincomeGrowth=bincomeGrowth.doubleValue();
		//BigDecimal ones=new BigDecimal("1");
		//增值税（销项）
		BigDecimal[] zzsx=new BigDecimal[allYearInt];
		//营业收入销项税额
		BigDecimal[] yysr=new BigDecimal[allYearInt];
		//增值税（进项）
		BigDecimal[] zzsj=new BigDecimal[allYearInt];
		//产品成本
		BigDecimal[] cpcb=new BigDecimal[allYearInt];
		//期初投入可抵扣额
		BigDecimal[] cqtrkde=new BigDecimal[allYearInt];
		//销项减进项
		BigDecimal[] xxjjx=new BigDecimal[allYearInt];
		//上期留抵抵扣
		BigDecimal[] sqlqdk=new BigDecimal[allYearInt];
		//销项减进项加上期留抵抵扣
		BigDecimal[] xxjjxj=new BigDecimal[allYearInt];
		//当期应缴纳
		BigDecimal[] dqjn=new BigDecimal[allYearInt];
		//当期可返还
		BigDecimal[] dqkfh=new BigDecimal[allYearInt];
		//当期实际缴纳
		BigDecimal[] dqsjjl=new BigDecimal[allYearInt];
		for(int i=0;i<allYearInt;i++){
			if(i<startYearInt){
				yysr[i]=new BigDecimal("0.00");
			}else{
				//double r1=(double) Math.pow(1+dincomeGrowth, i-startYearInt);
				double r1=(double) Math.pow(1+dincomeGrowth, i-1);
				BigDecimal br1=new BigDecimal(r1);
				BigDecimal r=boneNoIncome.multiply(br1).multiply(bsrsy);
				yysr[i]=r.setScale(2, BigDecimal.ROUND_HALF_UP);    //营业收入销项税额
			//sryx[i]=;
			}
			if(i==0){
				cqtrkde[i]=bdpay;
			}else{
				cqtrkde[i]=new BigDecimal("0.00"); 		//期初投入可抵扣额
			} 
		}
		
		for(int i=0;i<allYearInt;i++){
			zzsx[i]=yysr[i];					//增值税,销项(增值税)
		}
		
		/**
		 * 利润表
		 */
		//收入
		BigDecimal[] sru=new BigDecimal[allYearInt];
		sru[0]=new BigDecimal("0.00");
		//产品销售收入
		BigDecimal[] cpsr=new BigDecimal[allYearInt];
		cpsr[0]=new BigDecimal("0.00");
		//总成本费用
		BigDecimal[] zcbfy=new BigDecimal[allYearInt];
		zcbfy[0]=new BigDecimal("0.00");
		//营业成本
		BigDecimal[] yycbll=new BigDecimal[allYearInt];
		yycbll[0]=new BigDecimal("0.00");
		//营业税金及附加
		BigDecimal[] yysjjfj=new BigDecimal[allYearInt];
		yysjjfj[0]=new BigDecimal("0.00");
		//销售费用
		BigDecimal[] xsfys=new BigDecimal[allYearInt];
		xsfys[0]=new BigDecimal("0.00");
		//管理费用
		BigDecimal[] glfys=new BigDecimal[allYearInt];
		glfys[0]=new BigDecimal("0.00");
		//财务费用           
		BigDecimal[] cwfeiy=new BigDecimal[allYearInt];
		cwfeiy[0]=new BigDecimal("0.00");
		//利润总额
		BigDecimal[] lrze=new BigDecimal[allYearInt];
		lrze[0]=new BigDecimal("0.00");
		//所得税费用
		BigDecimal[] sdss=new BigDecimal[allYearInt];
		sdss[0]=new BigDecimal("0.00");
		//净利润
		BigDecimal[] jlr=new BigDecimal[allYearInt];
		jlr[0]=new BigDecimal("0.00");
		for(int i=1;i<allYearInt;i++){
			if(i<startYearInt){
				cpsr[i]=new BigDecimal("0.00");
				yycbll[i]=new BigDecimal("0.00");
				xsfys[i]=(cpsr[i].multiply(bxsfy)).setScale(2, BigDecimal.ROUND_HALF_UP);
				glfys[i]=(cpsr[i].multiply(bglfy)).setScale(2, BigDecimal.ROUND_HALF_UP);
			}else{
				//double r1=(double) Math.pow(1+dincomeGrowth, i-startYearInt);
				double r1=(double) Math.pow(1+dincomeGrowth, i-1);
				BigDecimal br1=new BigDecimal(r1);
				BigDecimal r=boneNoIncome.multiply(br1);
				cpsr[i]=r.setScale(2, BigDecimal.ROUND_HALF_UP);		//产品销售收入
				yycbll[i]=(cpsr[i].multiply(byycb)).setScale(2, BigDecimal.ROUND_HALF_UP);		//营业成本
				xsfys[i]=(cpsr[i].multiply(bxsfy)).setScale(2, BigDecimal.ROUND_HALF_UP);		//销售费用
				glfys[i]=(cpsr[i].multiply(bglfy)).setScale(2, BigDecimal.ROUND_HALF_UP);				//管理费用
				//sryx[i]=;
			}
		}
		for(int i=0;i<allYearInt;i++){
			if(i<startYearInt){
				cpcb[i]=yycbll[i].multiply(byycbzz).setScale(2, BigDecimal.ROUND_HALF_UP);      // 产品成本 (增值税)
				zzsj[i]=cpcb[i].add(cqtrkde[i]);                                         //增值税.进项（增值税）
				xxjjx[i]=zzsx[i].subtract(zzsj[i]);											//销项减进项（增值税）
			}else{
				cpcb[i]=yycbll[i].multiply(byycbzz).setScale(2, BigDecimal.ROUND_HALF_UP);      // 产品成本 (增值税)
				zzsj[i]=cpcb[i].add(cqtrkde[i]);                                         //增值税.进项（增值税）
				xxjjx[i]=zzsx[i].subtract(zzsj[i]);											//销项减进项（增值税）
				//sryx[i]=;
			}
		}
		for(int i=0;i<allYearInt;i++){
			sru[i]=cpsr[i];
		} 
		for(int i=0;i<allYearInt;i++){
			if(i==0){
				xxjjxj[i]=xxjjx[i];					//销项减进项加上期留抵抵扣（增值税）
				sqlqdk[i]=new BigDecimal("0.00");		//上期留抵抵扣（增值税）
			}else{
				int r=xxjjxj[i-1].compareTo(BigDecimal.ZERO);                    //r==1  >0     r==-1   <0   r==0  0
				if(r==1){
					sqlqdk[i]=new BigDecimal("0.00");
				}else{
					sqlqdk[i]=xxjjxj[i-1];
				}
				xxjjxj[i]=xxjjx[i].add(sqlqdk[i]); 				
			}
			int r=xxjjxj[i].compareTo(BigDecimal.ZERO);       
			if(r==-1){
				dqjn[i]=new BigDecimal("0.00");     //当期应缴纳（增值税）
			}else{
				dqjn[i]=xxjjxj[i];
			}
			dqkfh[i]=dqjn[i].multiply(bzzsfh).setScale(2, BigDecimal.ROUND_HALF_UP);				//当期可返还（增值税）
			dqsjjl[i]=dqjn[i].subtract(dqkfh[i]);										//当期实际缴纳（增值税）
		}
		for(int i=1;i<allYearInt;i++){
			yysjjfj[i]=(dqjn[i-1].multiply(bfjsl)).setScale(2, BigDecimal.ROUND_HALF_UP);            //营业税金及附加(利润表)
			cwfeiy[i]=(cpsr[i].multiply(bcwfy)).setScale(2, BigDecimal.ROUND_HALF_UP); 				//财务费用     
			cwfeiy[i]=cwfeiy[i].add(bqfx[i]);
			zcbfy[i]=cwfeiy[i].add(yycbll[i]).add(yysjjfj[i]).add(xsfys[i]).add(glfys[i]);			//总成本费用
			lrze[i]=sru[i].subtract(zcbfy[i]);				//利润总额
			sdss[i]=lrze[i].multiply(bsds).setScale(2, BigDecimal.ROUND_HALF_UP);    //所得税费用
			jlr[i]=lrze[i].subtract(sdss[i]);
		}
		sourcesMap.put("zzsx", zzsx);		//增值税（销项）
		sourcesMap.put("yysr", yysr);			//营业收入销项税额
		sourcesMap.put("zzsj", zzsj);			//增值税（进项）
		sourcesMap.put("cpcb", cpcb);			//产品成本
		sourcesMap.put("cqtrkde", cqtrkde);			//期初投入可抵扣额
		sourcesMap.put("xxjjx", xxjjx);			//销项减进项
		sourcesMap.put("sqlqdk", sqlqdk);			//上期留抵抵扣
		sourcesMap.put("xxjjxj", xxjjxj);			//销项减进项加上期留抵抵扣
		sourcesMap.put("dqjn", dqjn);			//当期应缴纳
		sourcesMap.put("dqkfh", dqkfh);			//当期可返还
		sourcesMap.put("dqsjjl", dqsjjl);			//当期实际缴纳
		
		sourcesMap.put("sru", sru);				//收入
		sourcesMap.put("cpsr", cpsr);			//产品销售收入
		sourcesMap.put("zcbfy", zcbfy);			//总成本费用
		sourcesMap.put("yycbll", yycbll);			//营业成本
		sourcesMap.put("yysjjfj", yysjjfj);			//营业税金及附加
		sourcesMap.put("xsfys", xsfys);			//销售费用
		sourcesMap.put("glfys", glfys);			//管理费用
		sourcesMap.put("cwfeiy", cwfeiy);			//财务费用           
		sourcesMap.put("lrze", lrze);			//利润总额
		sourcesMap.put("sdss", sdss);			//所得税费用
		sourcesMap.put("jlr", jlr);			//净利润
		return sourcesMap;
	}
	/**
	 * 投入资金的现金流测算
	 * @param profit
	 * @param sourcesFunds
	 * @param allYearInt
	 * @param fundsHand
	 * @param zjsr
	 * @param tzl
	 * @return
	 */
	public static Map<String, Object> cashFlow(Map<String,Object> profit,Map<String,Object> sourcesFunds,int allYearInt,String fundsHand,String zjsr,String tzl){
		BigDecimal bfundsHand=new BigDecimal(fundsHand);
		BigDecimal bzjsr=new BigDecimal(zjsr).divide(new BigDecimal("100"));                //折旧收入比   23
		BigDecimal btzl=new BigDecimal(tzl).divide(new BigDecimal("100"));                //通货率   34
		double dtzl=btzl.doubleValue();
		Map<String, Object> rmap=new HashMap<String, Object>();
		//现金流入
		Map<String, Object> xjlr=new HashMap<String, Object>();
		//Map<String, Object> xq1=new HashMap<String, Object>();
		BigDecimal[] cpsr=(BigDecimal[]) profit.get("cpsr");
		//BigDecimal[] zzsx=new BigDecimal[allYearInt];
		BigDecimal[] zzsx=(BigDecimal[]) profit.get("zzsx");
		//xq1.put("cpsr", cpsr);             //产品销售收入
		//xq1.put("zzsx", zzsx);               //销项税额
		BigDecimal[] total1=new BigDecimal[allYearInt];
		for(int i=0;i<allYearInt;i++){
			total1[i]=cpsr[i].add(zzsx[i]);
		}
		List<String> r1TitleList=new ArrayList<String>();
		List<BigDecimal[]> r1ContentList=new ArrayList<BigDecimal[]>();
		r1TitleList.add("产品销售收入");
		r1TitleList.add("销项税额");
		//r1ContentList.add(cpsr);
		//r1ContentList.add(zzsx);
		for(int i=0;i<allYearInt;i++){
			BigDecimal[] jg1=new BigDecimal[2];
			jg1[0]=cpsr[i];
			jg1[1]=zzsx[i];
			r1ContentList.add(jg1);
		}
		Details d1=new Details();
		d1.setTitles(r1TitleList);
		d1.setContents(r1ContentList);
		//xjlr.put("xq", xq1);
		xjlr.put("details", d1);
		xjlr.put("total", total1);     //现金流入
		rmap.put("xjlr", xjlr);
		//现金流出
		BigDecimal[] zcbfy=(BigDecimal[]) profit.get("zcbfy");
		BigDecimal[] bqhk=(BigDecimal[]) sourcesFunds.get("bqhk");             //本期还本
		BigDecimal[] sdss=(BigDecimal[]) profit.get("sdss");       		//所得税费用            
		BigDecimal[] cpcb=(BigDecimal[]) profit.get("cpcb");				//产品成本
		BigDecimal[] dqsjjl=(BigDecimal[]) profit.get("dqsjjl");			//当期实际缴纳
		Map<String, Object> xjzc=new HashMap<String, Object>();			//现金流出
		//Map<String, Object> xq2=new HashMap<String, Object>();
		//期初支出金额
		BigDecimal[] cqzc=new BigDecimal[allYearInt];
		//总成本费用
		BigDecimal[] zcbfys=new BigDecimal[allYearInt];
		//进项税额及实际已纳增值税
		BigDecimal[] zzsejsjyl=new BigDecimal[allYearInt];
		BigDecimal[] total2=new BigDecimal[allYearInt];
		BigDecimal[] total3=new BigDecimal[allYearInt];
		for(int i=0;i<allYearInt;i++){
			if(i==0){
				cqzc[i]=bfundsHand;
				zcbfys[i]=new BigDecimal("0.00");
				zzsejsjyl[i]=new BigDecimal("0.00"); 		//进项税额及实际已纳增值税
			}else{
				cqzc[i]=new BigDecimal("0.00");
				zcbfys[i]=zcbfy[i].subtract(cpsr[i].multiply(bzjsr).setScale(2, BigDecimal.ROUND_HALF_UP));      //总成本费用
				zzsejsjyl[i]=cpcb[i].add(dqsjjl[i]); 		//进项税额及实际已纳增值税
			}
			total2[i]=zzsejsjyl[i].add(cqzc[i]).add(zcbfys[i]).add(bqhk[i]).add(sdss[i]);
			total3[i]=total1[i].subtract(total2[i]);
		}
		List<String> r2TitleList=new ArrayList<String>();
		List<BigDecimal[]> r2ContentList=new ArrayList<BigDecimal[]>();
		r2TitleList.add("期初支出金额");
		r2TitleList.add("总成本费用");
		r2TitleList.add("归还本金");
		r2TitleList.add("所得税费用");
		r2TitleList.add("进项税额及实际已纳增值税");
		//xq2.put("cqzc", cqzc);					//期初支出金额
		//xq2.put("zcbfys", zcbfys);			//总成本费用
		//xq2.put("ghbj", bqhk);             //归还本金
		//xq2.put("sdss", sdss);          //所得税费用
		//xq2.put("zzsejsjyl", zzsejsjyl); 				//进项税额及实际已纳增值税
		//r2ContentList.add(cqzc);
		//r2ContentList.add(zcbfys);
		//r2ContentList.add(bqhk);
		//r2ContentList.add(sdss);
		//r2ContentList.add(zzsejsjyl);
		for(int i=0;i<allYearInt;i++){
			BigDecimal[] jg2=new BigDecimal[5];
			jg2[0]=cqzc[i];
			jg2[1]=zcbfys[i];
			jg2[2]=bqhk[i];
			jg2[3]=sdss[i];
			jg2[4]=zzsejsjyl[i];
			r2ContentList.add(jg2);
		}
		Details d2=new Details();
		d2.setTitles(r2TitleList);
		d2.setContents(r2ContentList);
		xjzc.put("total", total2);
		xjzc.put("details", d2);
		rmap.put("xjzc", xjzc);					////现金流出
		//项目净现金流量
		Map<String, Object> xmjll=new HashMap<String, Object>();   //项目净现金流量
		//Map<String, Object> xq3=new HashMap<String, Object>();
		xmjll.put("total", total3);
		xmjll.put("details", null);
		rmap.put("xmjll", xmjll);	//项目净现金流量
		//累计净现金流量
		Map<String, Object> ljjxjll=new HashMap<String, Object>();   //累计净现金流量
		//Map<String, Object> xq4=new HashMap<String, Object>();
		BigDecimal[] total4=new BigDecimal[allYearInt];
		for(int i=0;i<allYearInt;i++){
			if(i==0){
				total4[i]=total3[i];
			}else{
				total4[i]=total3[i].add(total4[i-1]);
			}
		}
		ljjxjll.put("total", total4);
		ljjxjll.put("details", null);
		rmap.put("ljjxjll", ljjxjll);	//累计净现金流量
		
		//项目净现金流量（考虑通胀）
		Map<String, Object> xmjzjthbz=new HashMap<String, Object>();   //项目净现金流量（考虑通胀）
		//Map<String, Object> xq5=new HashMap<String, Object>();
		BigDecimal[] total5=new BigDecimal[allYearInt];
		for(int i=0;i<allYearInt;i++){
			double s=0;
			s=Math.pow((1+dtzl),i);
			total5[i]=total3[i].divide(new BigDecimal(s),2, RoundingMode.HALF_UP);
		}
		xmjzjthbz.put("total", total5);
		xmjzjthbz.put("details", null);
		rmap.put("xmjzjthbz", xmjzjthbz);         //累计净现金流量
		//累计净现金流量（考虑通胀）
		Map<String, Object> ljjxjllthbz=new HashMap<String, Object>();   //累计净现金流量（考虑通胀）
		//Map<String, Object> xq6=new HashMap<String, Object>();
		BigDecimal[] total6=new BigDecimal[allYearInt];
		
		for(int i=0;i<allYearInt;i++){
			double s2=0;
			s2=Math.pow((1+dtzl),i);
			if(i==0){
				total6[i]=total4[i].divide(new BigDecimal(s2),2, RoundingMode.HALF_UP);
			}else{
				total6[i]=total5[i].add(total6[i-1]);
			}
		}
		ljjxjllthbz.put("total", total6);
		ljjxjllthbz.put("details", null);
		rmap.put("ljjxjllthbz", ljjxjllthbz); 
		return rmap;
	}
	
	/**
	 * 投入资金的现金流测算
	 * @param profit
	 * @param sourcesFunds
	 * @param allYearInt
	 * @param fundsHand
	 * @param zjsr
	 * @param tzl
	 * @return
	 */
	public static Map<String, Object> cashFlow2(Map<String,Object> profit,Map<String,Object> sourcesFunds,int allYearInt,String fundsHand,String zjsr,String tzl){
		BigDecimal bfundsHand=new BigDecimal(fundsHand);
		BigDecimal bzjsr=new BigDecimal(zjsr).divide(new BigDecimal("100"));                //折旧收入比   23
		BigDecimal btzl=new BigDecimal(tzl).divide(new BigDecimal("100"));                //通货率   34
		double dtzl=btzl.doubleValue();
		Map<String, Object> rmap=new HashMap<String, Object>();
		//现金流入
		BigDecimal[] cpsr=(BigDecimal[]) profit.get("cpsr");
		BigDecimal[] zzsx=(BigDecimal[]) profit.get("zzsx");
		//xq1.put("cpsr", cpsr);             //产品销售收入
		//xq1.put("zzsx", zzsx);               //销项税额
		BigDecimal[] total1=new BigDecimal[allYearInt];
		for(int i=0;i<allYearInt;i++){
			total1[i]=cpsr[i].add(zzsx[i]);
		}
		//现金流出
		BigDecimal[] zcbfy=(BigDecimal[]) profit.get("zcbfy");
		BigDecimal[] bqhk=(BigDecimal[]) sourcesFunds.get("bqhk");             //本期还本
		BigDecimal[] sdss=(BigDecimal[]) profit.get("sdss");       		//所得税费用            
		BigDecimal[] cpcb=(BigDecimal[]) profit.get("cpcb");				//产品成本
		BigDecimal[] dqsjjl=(BigDecimal[]) profit.get("dqsjjl");			//当期实际缴纳
		//Map<String, Object> xq2=new HashMap<String, Object>();
		//期初支出金额
		BigDecimal[] cqzc=new BigDecimal[allYearInt];
		//总成本费用
		BigDecimal[] zcbfys=new BigDecimal[allYearInt];
		//进项税额及实际已纳增值税
		BigDecimal[] zzsejsjyl=new BigDecimal[allYearInt];
		BigDecimal[] total2=new BigDecimal[allYearInt];
		BigDecimal[] total3=new BigDecimal[allYearInt];
		for(int i=0;i<allYearInt;i++){
			if(i==0){
				cqzc[i]=bfundsHand;
				zcbfys[i]=new BigDecimal("0.00");
				zzsejsjyl[i]=new BigDecimal("0.00"); 		//进项税额及实际已纳增值税
			}else{
				cqzc[i]=new BigDecimal("0.00");
				zcbfys[i]=zcbfy[i].subtract(cpsr[i].multiply(bzjsr).setScale(2, BigDecimal.ROUND_HALF_UP));      //总成本费用
				zzsejsjyl[i]=cpcb[i].add(dqsjjl[i]); 		//进项税额及实际已纳增值税
			}
			total2[i]=zzsejsjyl[i].add(cqzc[i]).add(zcbfys[i]).add(bqhk[i]).add(sdss[i]);
			total3[i]=total1[i].subtract(total2[i]);
		}
		
		//项目净现金流量
		Map<String, Object> xmjll=new HashMap<String, Object>();   //项目净现金流量
		//Map<String, Object> xq3=new HashMap<String, Object>();
		xmjll.put("total", total3);
		
		BigDecimal[] total4=new BigDecimal[allYearInt];
		for(int i=0;i<allYearInt;i++){
			if(i==0){
				total4[i]=total3[i];
			}else{
				total4[i]=total3[i].add(total4[i-1]);
			}
		}
		
		BigDecimal[] total5=new BigDecimal[allYearInt];
		for(int i=0;i<allYearInt;i++){
			double s=0;
			s=Math.pow((1+dtzl),i);
			total5[i]=total3[i].divide(new BigDecimal(s),2, RoundingMode.HALF_UP);
		}
	
		BigDecimal[] total6=new BigDecimal[allYearInt];
		
		for(int i=0;i<allYearInt;i++){
			double s2=0;
			s2=Math.pow((1+dtzl),i);
			if(i==0){
				total6[i]=total4[i].divide(new BigDecimal(s2),2, RoundingMode.HALF_UP);
			}else{
				total6[i]=total5[i].add(total6[i-1]);
			}
		}
		rmap.put("total1", total1);
		rmap.put("total2", total2);
		rmap.put("total3", total3);
		rmap.put("total4", total4);
		rmap.put("total5", total5);
		rmap.put("total6", total6);
		return rmap;
	}
	
	
	/**
	 * 全资本金投入的现金流测算
	 * @param profit
	 * @param sourcesFunds
	 * @param allYearInt
	 * @param fundsHand
	 * @param zjsr
	 * @param tzl
	 * @return
	 */
	public static Map<String, Object> capital(Map<String,Object> profit,Map<String,Object> sourcesFunds,int allYearInt,String fundsHand,String tax,String zjsr,String sds,String tzl){
		Map<String, Object> rmap=new HashMap<String, Object>();
		//BigDecimal bfundsHand=new BigDecimal(fundsHand);
		BigDecimal btax=new BigDecimal(tax);
		BigDecimal bsds=new BigDecimal(sds).divide(new BigDecimal("100")); 
		BigDecimal bzjsr=new BigDecimal(zjsr).divide(new BigDecimal("100"));                //折旧收入比   23
		BigDecimal btzl=new BigDecimal(tzl).divide(new BigDecimal("100"));                //通货率   34
		double dtzl=btzl.doubleValue();
		//现金流入
		Map<String, Object> xjlr=new HashMap<String, Object>();
		//Map<String, Object> xq1=new HashMap<String, Object>();
		BigDecimal[] cpsr=(BigDecimal[]) profit.get("cpsr"); 			//产品收入
		BigDecimal[] zzsx=new BigDecimal[allYearInt];
		Object zzsxs=profit.get("zzsx");
		if(zzsxs!=null){
			zzsx=(BigDecimal[]) zzsxs;
		}
		List<String> r1TitleList=new ArrayList<String>();
		List<BigDecimal[]> r1ContentList=new ArrayList<BigDecimal[]>();
		r1TitleList.add("产品销售收入");
		r1TitleList.add("销项税额");
		//r1ContentList.add(cpsr);
		//r1ContentList.add(zzsx);
		Details d1=new Details();
		d1.setTitles(r1TitleList);
		//d1.setContents(r1ContentList);
		//xq1.put("cpsr", cpsr);             //产品销售收入
		//xq1.put("zzsx", zzsx);               //销项税额
		for(int i=0;i<allYearInt;i++){
			BigDecimal[] jg1=new BigDecimal[2];
			jg1[0]=cpsr[i];
			jg1[1]=zzsx[i];
			r1ContentList.add(jg1);
		}
		d1.setContents(r1ContentList);
		BigDecimal[] total1=new BigDecimal[allYearInt];
		for(int i=0;i<allYearInt;i++){
			total1[i]=cpsr[i].add(zzsx[i]);
		}
		xjlr.put("details", d1);
		//xjlr.put("xq", xq1);
		xjlr.put("total", total1);     //现金流入
		rmap.put("xjlr", xjlr);
		
		//现金流出
		BigDecimal[] zcbfy=(BigDecimal[]) profit.get("zcbfy");
		BigDecimal[] bqfx=(BigDecimal[]) sourcesFunds.get("bqfx");					//本年付息
		BigDecimal[] dqsjjl=(BigDecimal[]) profit.get("dqsjjl");				   //当期实缴
		BigDecimal[] lrze=(BigDecimal[]) profit.get("lrze");	                     //利润总额
		BigDecimal[] cpcb=(BigDecimal[]) profit.get("cpcb");						//产品成本
		
		Map<String, Object> xjzc=new HashMap<String, Object>();			//现金流出
		//Map<String, Object> xq2=new HashMap<String, Object>();
		//期初支出金额
		BigDecimal[] cqzc=new BigDecimal[allYearInt];
		//总成本费用
		BigDecimal[] zcbfys=new BigDecimal[allYearInt];
		//进项税额及实际已纳增值税
		BigDecimal[] zzsejsjyl=new BigDecimal[allYearInt];
		BigDecimal[] sdss=new BigDecimal[allYearInt];           //
		BigDecimal[] total2=new BigDecimal[allYearInt];
		BigDecimal[] total3=new BigDecimal[allYearInt];
		for(int i=0;i<allYearInt;i++){
			if(i==0){
				cqzc[i]=btax;
				zcbfys[i]=new BigDecimal("0.00");
				sdss[i]=new BigDecimal("0.00");
				zzsejsjyl[i]=new BigDecimal("0.00"); 		//进项税额及实际已纳增值税
			}else{
				cqzc[i]=new BigDecimal("0.00");
				BigDecimal mt=zcbfy[i].subtract(bqfx[i]);
				zcbfys[i]=(mt.subtract(cpsr[i].multiply(bzjsr)).setScale(2, BigDecimal.ROUND_HALF_UP));      //总成本费用
				sdss[i]=((lrze[i].add(bqfx[i])).multiply(bsds)).setScale(2, BigDecimal.ROUND_HALF_UP);
				zzsejsjyl[i]=cpcb[i].add(dqsjjl[i]); 		//进项税额及实际已纳增值税
			}
			total2[i]=zzsejsjyl[i].add(cqzc[i]).add(zcbfys[i]).add(sdss[i]);
			total3[i]=total1[i].subtract(total2[i]);
		}
		List<String> r2TitleList=new ArrayList<String>();
		List<BigDecimal[]> r2ContentList=new ArrayList<BigDecimal[]>();
		r2TitleList.add("期初支出金额");
		r2TitleList.add("总成本费用");
		r2TitleList.add("所得税费用");
		r2TitleList.add("进项税额及实际已纳增值税");
		//r2ContentList.add(cqzc);
		//r2ContentList.add(zcbfys);
		//r2ContentList.add(sdss);
		//r2ContentList.add(zzsejsjyl);
		for(int i=0;i<allYearInt;i++){
			BigDecimal[] jg2=new BigDecimal[4];
			jg2[0]=cqzc[i];
			jg2[1]=zcbfys[i];
			jg2[2]=sdss[i];
			jg2[3]=zzsejsjyl[i];
			r2ContentList.add(jg2);
		}
		Details d2=new Details();
		d2.setTitles(r2TitleList);
		d2.setContents(r2ContentList);
		//xq2.put("cqzc", cqzc);					//期初支出金额
		//xq2.put("zcbfys", zcbfys);			//总成本费用
		//xq2.put("sdss", sdss);          //所得税费用
		//xq2.put("zzsejsjyl", zzsejsjyl); 				//进项税额及实际已纳增值税
		xjzc.put("total", total2);
		xjzc.put("details", d2);
		rmap.put("xjzc", xjzc);					////现金流出
		//项目净现金流量
		Map<String, Object> xmjll=new HashMap<String, Object>();   //项目净现金流量
		//Map<String, Object> xq3=new HashMap<String, Object>();
		xmjll.put("total", total3);
		//xmjll.put("xq", xq3);
		xmjll.put("details", null);
		rmap.put("xmjll", xmjll);	//项目净现金流量
		//累计净现金流量
		Map<String, Object> ljjxjll=new HashMap<String, Object>();   //累计净现金流量
		//Map<String, Object> xq4=new HashMap<String, Object>();
		BigDecimal[] total4=new BigDecimal[allYearInt];
		for(int i=0;i<allYearInt;i++){
			if(i==0){
				total4[i]=total3[i];
			}else{
				total4[i]=total3[i].add(total4[i-1]);
			}
		}
		ljjxjll.put("total", total4);
		//ljjxjll.put("xq", xq4);
		ljjxjll.put("details", null);
		rmap.put("ljjxjll", ljjxjll);	//累计净现金流量
		
		//项目净现金流量（考虑通胀）
		Map<String, Object> xmjzjthbz=new HashMap<String, Object>();   //项目净现金流量（考虑通胀）
		//Map<String, Object> xq5=new HashMap<String, Object>();
		BigDecimal[] total5=new BigDecimal[allYearInt];
		for(int i=0;i<allYearInt;i++){
			double s=0;
			s=Math.pow((1+dtzl),i);
			total5[i]=total3[i].divide(new BigDecimal(s),2, RoundingMode.HALF_UP);
		}
		xmjzjthbz.put("total", total5);
		//xmjzjthbz.put("xq", xq5);
		xmjzjthbz.put("details", null);
		rmap.put("xmjzjthbz", xmjzjthbz);         //累计净现金流量
		//累计净现金流量（考虑通胀）
		Map<String, Object> ljjxjllthbz=new HashMap<String, Object>();   //累计净现金流量（考虑通胀）
		//Map<String, Object> xq6=new HashMap<String, Object>();
		BigDecimal[] total6=new BigDecimal[allYearInt];
				
		for(int i=0;i<allYearInt;i++){
			double s2=0;
			s2=Math.pow((1+dtzl),i);
			if(i==0){
				total6[i]=total4[i].divide(new BigDecimal(s2),2, RoundingMode.HALF_UP);
			}else{
				total6[i]=total5[i].add(total6[i-1]);
			}
		}
		ljjxjllthbz.put("total", total6);
		//ljjxjllthbz.put("xq", xq6);
		ljjxjllthbz.put("details", null);
		rmap.put("ljjxjllthbz", ljjxjllthbz); 
		return rmap;
	}
	/**
	 * 全资本金投入的现金流测算
	 * @param profit
	 * @param sourcesFunds
	 * @param allYearInt
	 * @param fundsHand
	 * @param zjsr
	 * @param tzl
	 * @return
	 */
	public static Map<String, Object> capital2(Map<String,Object> profit,Map<String,Object> sourcesFunds,int allYearInt,String fundsHand,String tax,String zjsr,String sds,String tzl){
		Map<String, Object> rmap=new HashMap<String, Object>();
		//BigDecimal bfundsHand=new BigDecimal(fundsHand);
		BigDecimal btax=new BigDecimal(tax);
		BigDecimal bsds=new BigDecimal(sds).divide(new BigDecimal("100")); 
		BigDecimal bzjsr=new BigDecimal(zjsr).divide(new BigDecimal("100"));                //折旧收入比   23
		BigDecimal btzl=new BigDecimal(tzl).divide(new BigDecimal("100"));                //通货率   34
		double dtzl=btzl.doubleValue();
		//现金流入
		BigDecimal[] cpsr=(BigDecimal[]) profit.get("cpsr"); 			//产品收入
		BigDecimal[] zzsx=new BigDecimal[allYearInt];
		Object zzsxs=profit.get("zzsx");
		if(zzsxs!=null){
			zzsx=(BigDecimal[]) zzsxs;
		}
		BigDecimal[] total1=new BigDecimal[allYearInt];
		for(int i=0;i<allYearInt;i++){
			total1[i]=cpsr[i].add(zzsx[i]);
		}
		
		//现金流出
		BigDecimal[] zcbfy=(BigDecimal[]) profit.get("zcbfy");
		BigDecimal[] bqfx=(BigDecimal[]) sourcesFunds.get("bqfx");					//本年付息
		BigDecimal[] dqsjjl=(BigDecimal[]) profit.get("dqsjjl");				   //当期实缴
		BigDecimal[] lrze=(BigDecimal[]) profit.get("lrze");	                     //利润总额
		BigDecimal[] cpcb=(BigDecimal[]) profit.get("cpcb");						//产品成本
		
		//Map<String, Object> xq2=new HashMap<String, Object>();
		//期初支出金额
		BigDecimal[] cqzc=new BigDecimal[allYearInt];
		//总成本费用
		BigDecimal[] zcbfys=new BigDecimal[allYearInt];
		//进项税额及实际已纳增值税
		BigDecimal[] zzsejsjyl=new BigDecimal[allYearInt];
		BigDecimal[] sdss=new BigDecimal[allYearInt];           //
		BigDecimal[] total2=new BigDecimal[allYearInt];
		BigDecimal[] total3=new BigDecimal[allYearInt];
		for(int i=0;i<allYearInt;i++){
			if(i==0){
				cqzc[i]=btax;
				zcbfys[i]=new BigDecimal("0.00");
				sdss[i]=new BigDecimal("0.00");
				zzsejsjyl[i]=new BigDecimal("0.00"); 		//进项税额及实际已纳增值税
			}else{
				cqzc[i]=new BigDecimal("0.00");
				BigDecimal mt=zcbfy[i].subtract(bqfx[i]);
				zcbfys[i]=(mt.subtract(cpsr[i].multiply(bzjsr)).setScale(2, BigDecimal.ROUND_HALF_UP));      //总成本费用
				sdss[i]=((lrze[i].add(bqfx[i])).multiply(bsds)).setScale(2, BigDecimal.ROUND_HALF_UP);
				zzsejsjyl[i]=cpcb[i].add(dqsjjl[i]); 		//进项税额及实际已纳增值税
			}
			total2[i]=zzsejsjyl[i].add(cqzc[i]).add(zcbfys[i]).add(sdss[i]);
			total3[i]=total1[i].subtract(total2[i]);
		}
		//累计净现金流量
		BigDecimal[] total4=new BigDecimal[allYearInt];
		for(int i=0;i<allYearInt;i++){
			if(i==0){
				total4[i]=total3[i];
			}else{
				total4[i]=total3[i].add(total4[i-1]);
			}
		}
		BigDecimal[] total5=new BigDecimal[allYearInt];
		for(int i=0;i<allYearInt;i++){
			double s=0;
			s=Math.pow((1+dtzl),i);
			total5[i]=total3[i].divide(new BigDecimal(s),2, RoundingMode.HALF_UP);
		}
		BigDecimal[] total6=new BigDecimal[allYearInt];
		for(int i=0;i<allYearInt;i++){
			double s2=0;
			s2=Math.pow((1+dtzl),i);
			if(i==0){
				total6[i]=total4[i].divide(new BigDecimal(s2),2, RoundingMode.HALF_UP);
			}else{
				total6[i]=total5[i].add(total6[i-1]);
			}
		}
		rmap.put("total1", total1);
		rmap.put("total2", total2);
		rmap.put("total3", total3);
		rmap.put("total4", total4);
		rmap.put("total5", total5);
		rmap.put("total6", total6);
		return rmap;
	}
	/**
	 * 按单元格的公式进行计算,得结果
	 * @param tax
	 * @param zcje
	 * @param pay
	 * @param fundsHand
	 * @param oneIncome
	 * @param srsy
	 * @return
	 */
	public static Map<String, Object> autoComp(String tax,String zcje,String pay,String fundsHand,String oneIncome,String srsy){
		Map<String, Object> rmap=new HashMap<String, Object>();
		if(tax==null || tax.length()==0){
			return null;
		}
		if(zcje==null || zcje.length()==0){
			return null;
		}
		if(pay==null || pay.length()==0){
			return null;
		}
		if(fundsHand==null || fundsHand.length()==0){
			return null;
		}
		if(oneIncome==null || oneIncome.length()==0){
			return null;
		}
		if(srsy==null || srsy.length()==0){
			return null;
		}
		BigDecimal btax=new BigDecimal(tax);
		BigDecimal bpay=new BigDecimal(pay);
		BigDecimal bfundsHand=new BigDecimal(fundsHand);
		BigDecimal boneIncome=new BigDecimal(oneIncome);
		BigDecimal bsrsy=new BigDecimal(srsy).divide(new BigDecimal("100"));   
		BigDecimal bzcje=new BigDecimal(zcje).divide(new BigDecimal("100"));   
		//BigDecimal t1=((new BigDecimal("1")).add(bzcje));           //.setScale(2, BigDecimal.ROUND_HALF_UP)
		//BigDecimal dpay=btax.divide(t1,2, RoundingMode.HALF_UP).multiply(bzcje).setScale(2, BigDecimal.ROUND_HALF_UP);					//可抵扣增值税额
		BigDecimal dpay=(bpay.multiply(bzcje)).setScale(2, BigDecimal.ROUND_HALF_UP);
		rmap.put("dpay", dpay);
		//其他税额
		BigDecimal qpay=(btax.subtract(bpay)).subtract(dpay);
		rmap.put("qpay", qpay); 				//其他税额
		//资金来源
		BigDecimal funds=btax;
		rmap.put("funds", funds); 			//资金来源
		//借款
		BigDecimal loan=btax.subtract(bfundsHand);
		rmap.put("loan", loan);				//借款
		//预测第一年收入（不含税）
		BigDecimal t2=(new BigDecimal("1")).add(bsrsy);
		BigDecimal oneNoIncome=boneIncome.divide(t2,2, RoundingMode.HALF_UP);
		rmap.put("oneNoIncome", oneNoIncome);				//预测第一年收入（不含税）
		//税额
		BigDecimal limits=(oneNoIncome.multiply(bsrsy)).setScale(2, BigDecimal.ROUND_HALF_UP);
		rmap.put("limits", limits);						//税额
		return rmap;
	}
	/**
	 * 结果展示
	 */
	public static Map<String, Object> result(HashMap<String, Object> tr,HashMap<String, Object> qz,int allYearInt){
		BigDecimal[] trtotal3=(BigDecimal[]) tr.get("total3");
		BigDecimal[] trtotal4=(BigDecimal[]) tr.get("total4");
		BigDecimal[] trtotal5=(BigDecimal[]) tr.get("total5");
		BigDecimal[] trtotal6=(BigDecimal[]) tr.get("total6");
		BigDecimal[] qztotal3=(BigDecimal[]) qz.get("total3");
		BigDecimal[] qztotal4=(BigDecimal[]) qz.get("total4");
		BigDecimal[] qztotal5=(BigDecimal[]) qz.get("total5");
		BigDecimal[] qztotal6=(BigDecimal[]) qz.get("total6");
		String trjt="FALSE";
		String trthjt="FALSE";
		String qzjt="FALSE";
		String qzthjt="FALSE";
		int flag=0;
		int flag2=0;
		int flag3=0;
		int flag4=0;
		double[] dtrtotal3=new double[allYearInt];
		double[] dtrtotal5=new double[allYearInt];
		double[] dqztotal3=new double[allYearInt];
		double[] dqztotal5=new double[allYearInt];
		for(int i=0;i<allYearInt;i++){
			int bz=i-1;
			if(flag==0){
				int a = trtotal4[i].compareTo(new BigDecimal("0"));
				if(a==1){
					if(i>0){
						trjt=(((trtotal3[i].subtract(trtotal4[i])).divide(trtotal3[i], 2, BigDecimal.ROUND_HALF_UP)).add(new BigDecimal(bz))).toString()+"年";
						flag=1;
					}
				}
			}
			if(flag2==0){
				int a = trtotal6[i].compareTo(new BigDecimal("0"));
				if(a==1){
					if(i>0){
						trthjt=(((trtotal5[i].subtract(trtotal6[i])).divide(trtotal5[i], 2, BigDecimal.ROUND_HALF_UP)).add(new BigDecimal(bz))).toString()+"年";
						flag2=1;
					}
				}
			}
			if(flag3==0){
				int a = qztotal4[i].compareTo(new BigDecimal("0"));
				if(a==1 && i>0){
					if(i>0){
						qzjt=(((qztotal3[i].subtract(qztotal4[i])).divide(qztotal3[i], 2, BigDecimal.ROUND_HALF_UP)).add(new BigDecimal(bz))).toString()+"年";
						flag3=1;
					}
				}
			}
			if(flag4==0){
				int a = qztotal6[i].compareTo(new BigDecimal("0"));
				if(a==1 && i>0){
					if(i>0){
						qzthjt=(((qztotal5[i].subtract(qztotal6[i])).divide(qztotal5[i], 2, BigDecimal.ROUND_HALF_UP)).add(new BigDecimal(bz))).toString()+"年";
						flag4=1;
					}
				}
			}
			dtrtotal3[i]=trtotal3[i].doubleValue();
			dtrtotal5[i]=trtotal5[i].doubleValue();
			
			dqztotal3[i]=qztotal3[i].doubleValue();
			dqztotal5[i]=qztotal5[i].doubleValue();
		}
		HashMap<String, Object> maps=new HashMap<String, Object>();
		double dtrret= IrrUtil.irr(dtrtotal3,0.00001d);
		double dtrthret= IrrUtil.irr(dtrtotal5,0.00001d);
		if(Double.isNaN(dtrret)){
			maps.put("trret", "#NUM!");			//不考虑通货膨胀IRR(投入资金的现金流测算)
		}else{
			BigDecimal trret = ((new BigDecimal(dtrret)).multiply(new BigDecimal("100"))).setScale(2, BigDecimal.ROUND_HALF_UP);
			maps.put("trret", trret+"%");
		}
		if(Double.isNaN(dtrthret)){
			maps.put("trthret", "#NUM!");			//不考虑通货膨胀IRR(投入资金的现金流测算)
		}else{
			BigDecimal trthret = ((new BigDecimal(dtrthret)).multiply(new BigDecimal("100"))).setScale(2, BigDecimal.ROUND_HALF_UP);
			maps.put("trthret", trthret+"%");
		}
		double dqzret= IrrUtil.irr(dqztotal3,0.00001d);
		double dqzthret= IrrUtil.irr(dqztotal5,0.00001d);
		if(Double.isNaN(dqzret)){
			maps.put("qzret", "#NUM!");			//不考虑通货膨胀IRR(投入资金的现金流测算)
		}else{
			BigDecimal qzret = ((new BigDecimal(dqzret)).multiply(new BigDecimal("100"))).setScale(2, BigDecimal.ROUND_HALF_UP);
			maps.put("qzret", qzret+"%");
		}
		if(Double.isNaN(dqzthret)){
			maps.put("qzthret", "#NUM!");			//不考虑通货膨胀IRR(投入资金的现金流测算)
		}else{
			BigDecimal qzthret = ((new BigDecimal(dqzthret)).multiply(new BigDecimal("100"))).setScale(2, BigDecimal.ROUND_HALF_UP);
			maps.put("qzthret", qzthret+"%");
		}
		
		
		maps.put("trjt", trjt);            //不考虑通货膨胀静态投资回收期(投入资金的现金流测算)
		maps.put("trthjt", trthjt);        //考虑静态投资回收期(投入资金的现金流测算)
		//maps.put("trret", trret+"%");			//不考虑通货膨胀IRR(投入资金的现金流测算)
		//maps.put("trthret", trthret+"%");      //考虑通货膨胀IRR(投入资金的现金流测算)
		maps.put("qzjt", qzjt);				//不考虑通货膨胀静态投资回收期(全资本金投入的现金流测算)
		maps.put("qzthjt", qzthjt);			//考虑静态投资回收期(全资本金投入的现金流测算)
		//maps.put("qzret", qzret+"%");			//不考虑考虑通货膨胀IRR(全资本金投入的现金流测算)
		//maps.put("qzthret", qzthret+"%");	
		return maps;
	}
}









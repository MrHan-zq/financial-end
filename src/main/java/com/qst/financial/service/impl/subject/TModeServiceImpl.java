package com.qst.financial.service.impl.subject;

import com.qst.financial.dao.mapper.subject.TModeMapper;
import com.qst.financial.modle.subject.TMode;
import com.qst.financial.service.impl.base.BaseServiceImpl;
import com.qst.financial.service.subject.ReportResultService;
import com.qst.financial.service.subject.TModeService;
import com.qst.financial.util.CalculateUtils;
import com.qst.financial.util.Dates;
import com.qst.financial.util.common.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qst.financial.redis.RedisService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TModeServiceImpl extends BaseServiceImpl<TMode, Integer> implements TModeService {
	private static final Logger log=Logger.getLogger(TModeServiceImpl.class);
	@Autowired
	private TModeMapper tModeMapper;

	@Autowired
	private ReportResultService reportResultService;
	@Autowired
	private RedisService redisService;
	@Override
	public List<String> getContent(String startTime, String endTime, String orgId, String reportType,String modeArea) throws Exception {
		Map<String, Object> params = new HashMap<>() ;
		String modeAreas=modeArea.substring(0,3);
		if(!modeAreas.equals("GBL")){
			params.put("reportType", reportType);
		}
		params.put("modeArea", modeArea);
		List<String> reList = new ArrayList<>() ;
		List<TMode> modes = tModeMapper.getTModeInfo(params);
		if(startTime.length()<8){
			startTime=startTime+"-01";
			endTime=endTime+"-01";
		}
		if(modeAreas.equals("GBL")){
			reportType="";
		}
		Map<String,BigDecimal> map = reportResultService.getModeParams2(startTime, endTime, orgId, reportType,modeArea);
		/*redisService.setObj("s", map);
		Map<String,BigDecimal> maps=(Map<String, BigDecimal>) redisService.getObj("s");*/
		//Map<String,BigDecimal> map = reportResultService.getModeParams(startTime, endTime, orgId, reportType);
		//供组合预警使用
		StringBuffer reStrB = new StringBuffer() ;
		for(TMode mode : modes){
			log.info("============"+mode.getId());
			String condition = mode.getModeCondition() ;
			/*log.info("管理费用人工:"+map.get("GLFY_RG")+";"+map.get("GLFY_RG1"));
			log.info("管理费用折旧:"+map.get("GLFY_ZJ")+";"+map.get("GLFY_ZJ1"));
			log.info("管理费用业务招待费:"+map.get("GLFY_ZD")+";"+map.get("GLFY_ZD1"));
			log.info("管理费用其他:"+map.get("GLFY_QT")+";"+map.get("GLFY_QT1"));
			log.info("LR_Q1:"+map.get("LR_Q1"));
			log.info("LR_Q:"+map.get("LR_Q"));*/
		  /*log.info("LR_A:"+map.get("LR_A"));
			log.info("LR_A1:"+map.get("LR_A1"));
			log.info("XSSL_A:"+map.get("XSSL_A"));
			log.info("XSSL_A1:"+map.get("XSSL_A1"));*/
			
			if(!StringUtils.isEmpty(condition)){
				boolean conditionsIsTrue  = false ;
				try {
					/*if(mode.getId().toString().equals("3439") || mode.getId().toString().equals("3491")){
						System.out.println("============"+mode.getId());
						conditionsIsTrue = CalculateUtils.strToLj(condition, map) ;
					}else{*/
						conditionsIsTrue = CalculateUtils.strToLj(condition, map) ;
					//}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("条件判断出现异常！可能是条件中的参数无值！ 条件：" + condition);
				}
				if(conditionsIsTrue){
					log.info("=========="+condition);
					if(!StringUtils.isEmpty(mode.getModeArea()) && mode.getModeArea().startsWith("G")){
						//组合预警条件计算
						boolean isTransNormal = true ;
						String modeValues = mode.getModeValues() ;
						String reStr = mode.getModeContent();
						if(StringUtils.isEmpty(modeValues) && !StringUtils.isEmpty(reStr)){
							reStrB.append(reStr);
						}
						else{
							String[] modeVals = modeValues.split(",");
							for(String modeVal : modeVals){
								modeVal = modeVal.replace("{", "") ;
								modeVal = modeVal.replace("}", "") ;
								BigDecimal value = null ;
								try {
									value = CalculateUtils.strToSs(modeVal, map);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("模板结果异常,可能模板内参数无值或者除0！ 模板：" + reStr);
									isTransNormal = false ;
								}
								if(null != value){
									reStr = reStr.replace("{" + modeVal + "}", value.toString()) ;
								}
							}
							if(isTransNormal){
								reStrB.append(reStr);
							}
						}
						
					}
					else{
						boolean isTransNormal = true ;
						String modeValues = mode.getModeValues() ;
						String reStr = mode.getModeContent();
						if(StringUtils.isEmpty(modeValues) && !StringUtils.isEmpty(reStr)){
							reList.add(reStr);
						}
						else{
							String[] modeVals = modeValues.split(",");
							for(String modeVal : modeVals){
								modeVal = modeVal.replace("{", "") ;
								modeVal = modeVal.replace("}", "") ;
								BigDecimal value = null ;
								try {
									value = CalculateUtils.strToSs(modeVal, map);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("模板结果异常,可能模板内参数无值或者除0！ 模板：" + reStr);
									isTransNormal = false ;
								}
								if(null != value){
									reStr = reStr.replace("{" + modeVal + "}", value.toString()) ;
								}
							}
							if(isTransNormal){
								reList.add(reStr);
							}
						}
					}
				}
			}
		}
		if(!StringUtils.isEmpty(reStrB.toString())){
			reList.add(reStrB.toString());
		}
		return reList;
	}
	@Override
	public List<String> getContent2(String startTime, String endTime, String orgId, String reportType,String modeArea,String key) throws Exception {
		Map<String, Object> params = new HashMap<>() ;
		String modeAreas=modeArea.substring(0,3);
		if(!modeAreas.equals("GBL")){
			params.put("reportType", reportType);
		}
		params.put("modeArea", modeArea);
		List<String> reList = new ArrayList<>() ;
		List<TMode> modes = tModeMapper.getTModeInfo(params);
		if(startTime.length()<8){
			startTime=startTime+"-01";
			endTime=endTime+"-01";
		}
		//if(modeAreas.equals("GBL")){
			reportType="";
		//}
		Map<String,BigDecimal> map =new HashMap<String,BigDecimal>();
		Map<String,BigDecimal> maps=(Map<String, BigDecimal>) redisService.getObj(key);
		if(maps!=null){
			map =(Map<String, BigDecimal>) redisService.getObj(key);
		}else{
			map = reportResultService.getModeParams2(startTime, endTime, orgId, reportType,modeArea);
			redisService.setObj(key,map);
		}
		
		/*redisService.setObj("s", map);
		Map<String,BigDecimal> maps=(Map<String, BigDecimal>) redisService.getObj("s");*/
		//Map<String,BigDecimal> map = reportResultService.getModeParams(startTime, endTime, orgId, reportType);
		//供组合预警使用
		StringBuffer reStrB = new StringBuffer() ;
		for(TMode mode : modes){
			String condition = mode.getModeCondition() ;
			if(!StringUtils.isEmpty(condition)){
				boolean conditionsIsTrue  = false ;
				try {
					//if(condition.equals("ZCFZ_AN>ZCFZ_AN1&&ZCFZ_G>ZCFZ_G1&&ZCFZ_G-ZCFZ_G1>ZCFZ_A-ZCFZ_A1&&ZCFZ_G-ZCFZ_G1>ZCFZ_D-ZCFZ_D1&&ZCFZ_G-ZCFZ_G1>ZCFZ_E-ZCFZ_E1&&ZCFZ_G-ZCFZ_G1>ZCFZ_F-ZCFZ_F1&&ZCFZ_G-ZCFZ_G1>ZCFZ_H-ZCFZ_H1&&ZCFZ_G-ZCFZ_G1>ZCFZ_L-ZCFZ_L1&&ZCFZ_G-ZCFZ_G1>ZCFZ_M-ZCFZ_M1&&ZCFZ_G-ZCFZ_G1>ZCFZ_N-ZCFZ_N1&&ZCFZ_G-ZCFZ_G1>ZCFZ_P-ZCFZ_P1&&ZCFZ_G-ZCFZ_G1>ZCFZ_Q-ZCFZ_Q1&&ZCFZ_G-ZCFZ_G1>ZCFZ_R-ZCFZ_R1&&ZCFZ_G-ZCFZ_G1>ZCFZ_S-ZCFZ_S1&&ZCFZ_G-ZCFZ_G1>ZCFZ_V-ZCFZ_V1&&ZCFZ_G-ZCFZ_G1>ZCFZ_W-ZCFZ_W1&&ZCFZ_G-ZCFZ_G1>ZCFZ_X-ZCFZ_X1&&ZCFZ_G-ZCFZ_G1>ZCFZ_Y-ZCFZ_Y1&&ZCFZ_G-ZCFZ_G1>ZCFZ_Z-ZCFZ_Z1&&ZCFZ_G-ZCFZ_G1>ZCFZ_AA-ZCFZ_AA1&&ZCFZ_G-ZCFZ_G1-ZCFZ_AB>ZCFZ_AB1&&ZCFZ_G-ZCFZ_G1>ZCFZ_AC-ZCFZ_AC1&&ZCFZ_G-ZCFZ_G1>ZCFZ_AD-ZCFZ_AD1&&ZCFZ_G-ZCFZ_G1>ZCFZ_AE-ZCFZ_AE1&&ZCFZ_G-ZCFZ_G1>ZCFZ_AF-ZCFZ_AF1&&ZCFZ_G-ZCFZ_G1>ZCFZ_AG-ZCFZ_AG1&&ZCFZ_G-ZCFZ_G1>ZCFZ_AH-ZCFZ_AH1&&ZCFZ_G-ZCFZ_G1>ZCFZ_AI-ZCFZ_AI1&&ZCFZ_G-ZCFZ_G1>ZCFZ_AJ-ZCFZ_AJ1&&ZCFZ_G-ZCFZ_G1>ZCFZ_AK-ZCFZ_AK1&&ZCFZ_G-ZCFZ_G1>ZCFZ_AL-ZCFZ_AL1")){
						//conditionsIsTrue = CalculateUtils.strToLj(condition, map) ;
					//}else{
						conditionsIsTrue = CalculateUtils.strToLj(condition, map) ;
					//}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("条件判断出现异常！可能是条件中的参数无值！ 条件：" + condition);
				}
				if(conditionsIsTrue){
					log.info("=========="+condition);
					if(!StringUtils.isEmpty(mode.getModeArea()) && mode.getModeArea().startsWith("G")){
						//组合预警条件计算
						boolean isTransNormal = true ;
						String modeValues = mode.getModeValues() ;
						String reStr = mode.getModeContent();
						if(StringUtils.isEmpty(modeValues) && !StringUtils.isEmpty(reStr)){
							reStrB.append(reStr);
						}
						else{
							String[] modeVals = modeValues.split(",");
							for(String modeVal : modeVals){
								modeVal = modeVal.replace("{", "") ;
								modeVal = modeVal.replace("}", "") ;
								BigDecimal value = null ;
								try {
									value = CalculateUtils.strToSs(modeVal, map);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("模板结果异常,可能模板内参数无值或者除0！ 模板：" + reStr);
									isTransNormal = false ;
								}
								if(null != value){
									reStr = reStr.replace("{" + modeVal + "}", value.toString()) ;
								}
							}
							if(isTransNormal){
								reStrB.append(reStr);
							}
						}
						
					}
					else{
						boolean isTransNormal = true ;
						String modeValues = mode.getModeValues() ;
						String reStr = mode.getModeContent();
						if(StringUtils.isEmpty(modeValues) && !StringUtils.isEmpty(reStr)){
							reList.add(reStr);
						}
						else{
							String[] modeVals = modeValues.split(",");
							for(String modeVal : modeVals){
								modeVal = modeVal.replace("{", "") ;
								modeVal = modeVal.replace("}", "") ;
								BigDecimal value = null ;
								try {
									value = CalculateUtils.strToSs(modeVal, map);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("模板结果异常,可能模板内参数无值或者除0！ 模板：" + reStr);
									isTransNormal = false ;
								}
								if(null != value){
									reStr = reStr.replace("{" + modeVal + "}", value.toString()) ;
								}
							}
							if(isTransNormal){
								reList.add(reStr);
							}
						}
					}
				}
			}
		}
		if(!StringUtils.isEmpty(reStrB.toString())){
			reList.add(reStrB.toString());
		}
		return reList;
	}
	@Override
	public List<String> getContent(String startTime, String endTime, String orgId, String reportType,String modeArea,Map<String,BigDecimal> map) throws Exception {
		Map<String, Object> params = new HashMap<>() ;
		params.put("reportType", reportType);
		params.put("modeArea", modeArea);
		List<String> reList = new ArrayList<>() ;
		List<TMode> modes = tModeMapper.getTModeInfo(params);
		//供组合预警使用
		StringBuffer reStrB = new StringBuffer() ;
		for(TMode mode : modes){
			String condition = mode.getModeCondition() ;
			if(!StringUtils.isEmpty(condition)){
				boolean conditionsIsTrue  = false ;
				try {
					conditionsIsTrue = CalculateUtils.strToLj(condition, map) ;
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("条件判断出现异常！可能是条件中的参数无值！ 条件：" + condition);
				}
				if(conditionsIsTrue){
					if(!StringUtils.isEmpty(mode.getModeArea()) && mode.getModeArea().startsWith("G")){
						//组合预警条件计算
						boolean isTransNormal = true ;
						String modeValues = mode.getModeValues() ;
						String reStr = mode.getModeContent();
						if(StringUtils.isEmpty(modeValues) && !StringUtils.isEmpty(reStr)){
							reStrB.append(reStr);
						}
						else{
							String[] modeVals = modeValues.split(",");
							for(String modeVal : modeVals){
								modeVal = modeVal.replace("{", "") ;
								modeVal = modeVal.replace("}", "") ;
								BigDecimal value = null ;
								try {
									value = CalculateUtils.strToSs(modeVal, map);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("模板结果异常,可能模板内参数无值或者除0！ 模板：" + reStr);
									isTransNormal = false ;
								}
								if(null != value){
									reStr = reStr.replace("{" + modeVal + "}", value.toString()) ;
								}
							}
							if(isTransNormal){
								reStrB.append(reStr);
							}
						}
						
					}
					else{
						boolean isTransNormal = true ;
						String modeValues = mode.getModeValues() ;
						String reStr = mode.getModeContent();
						if(StringUtils.isEmpty(modeValues) && !StringUtils.isEmpty(reStr)){
							reList.add(reStr);
						}
						else{
							String[] modeVals = modeValues.split(",");
							for(String modeVal : modeVals){
								modeVal = modeVal.replace("{", "") ;
								modeVal = modeVal.replace("}", "") ;
								BigDecimal value = null ;
								try {
									value = CalculateUtils.strToSs(modeVal, map);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("模板结果异常,可能模板内参数无值或者除0！ 模板：" + reStr);
									isTransNormal = false ;
								}
								if(null != value){
									reStr = reStr.replace("{" + modeVal + "}", value.toString()) ;
								}
							}
							if(isTransNormal){
								reList.add(reStr);
							}
						}
					}
				}
			}
		}
		if(!StringUtils.isEmpty(reStrB.toString())){
			reList.add(reStrB.toString());
		}
		return reList;
	}

	
	@Override
	public void deletetMode(String tModeId) throws Exception {
		// TODO Auto-generated method stub
		String[] tModeIdArr = tModeId.split(",");
		List<String> tModeIdList = new ArrayList<String>();
		for (String string : tModeIdArr) {
			tModeIdList.add(string);
		}
		tModeMapper.deleteByPrimaryKeyBatch(tModeIdList);
	}
	@Override
	public Map<String,String> getDbBlvMsg(String startTime, String endTime, String orgId) throws Exception{
		Map<String,String> maps=new HashMap<String,String>();
		Map<String,BigDecimal> map= this.getDbMsg(startTime, endTime, orgId);
		int monthDiff=0;
		try {
			monthDiff = Dates.getDateTimes(startTime,endTime)+1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String tbStartDt="";
		String tbEndDt="";
		String hbStartTime="";
		String hbEndTime="";
		//同比时间
		if(startTime!=null && startTime!=""){
			tbStartDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(startTime,"yyyy-MM"),"yyyy-MM",-1,4),"yyyy-MM");
		}
		if(endTime!=null && endTime!=""){
			tbEndDt=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime,"yyyy-MM"),"yyyy-MM",-1,4),"yyyy-MM");
		}
		Map<String,BigDecimal> tbMap= this.getDbMsg(tbStartDt, tbEndDt, orgId);
		//环比时间
		if(startTime!=null && startTime!=""){
			hbStartTime=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(startTime,"yyyy-MM"),"yyyy-MM",monthDiff*1*(-1),3), "yyyy-MM");
		}
		if(endTime!=null && endTime!=""){
			hbEndTime=DateUtil.dateToString(DateUtil.addTime(DateUtil.stringToDate(endTime,"yyyy-MM"),"yyyy-MM",monthDiff*1*(-1),3), "yyyy-MM");
		}
		Map<String,BigDecimal> hbMap= this.getDbMsg(hbStartTime, hbEndTime, orgId);
		for (Map.Entry<String,BigDecimal> entry : map.entrySet()) {  
		    //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
			
			for (Map.Entry<String,BigDecimal> tbEntry : tbMap.entrySet()) { 
				if(entry.getKey().equals(tbEntry.getKey()) && !entry.getKey().equals("DB_QYCS")&& !entry.getKey().equals("DB_ZZCS")){
					if(entry.getKey().equals("DB_ZZCJLR") || entry.getKey().equals("DB_XSJLR") || entry.getKey().equals("DB_QYJLL")){
						String tb="0";
						BigDecimal tbs=entry.getValue().subtract(tbEntry.getValue());
						if(tbs.compareTo(BigDecimal.ZERO)==-1){
							tb="↓"+tbs.abs();
						}else if(tbs.compareTo(BigDecimal.ZERO)==1){
							tb="↑"+tbs.abs();
						}else{
							tb="0";
						}
						maps.put("tb"+entry.getKey(), tb);
					}else{
						String tb="0";
						String keys="tb"+entry.getKey();
						BigDecimal tbs=new BigDecimal("0");
						if(entry.getValue()!=null && !entry.getValue().equals(null)){
							if(tbEntry.getValue()!=null){
								if(tbEntry.getValue().compareTo(BigDecimal.ZERO)!=0){
									tbs=(entry.getValue().subtract(tbEntry.getValue())).multiply(new BigDecimal("100")).divide((tbEntry.getValue()).abs(),2, RoundingMode.HALF_UP);
									//tb=((entry.getValue().subtract(tbEntry.getValue())).multiply(new BigDecimal("100")).divide((tbEntry.getValue()).abs(),2, RoundingMode.HALF_UP)).toString();
								}
							}
						}
						if(tbs.compareTo(BigDecimal.ZERO)==-1){
							tb="↓"+tbs.abs();
						}else if(tbs.compareTo(BigDecimal.ZERO)==1){
							tb="↑"+tbs.abs();
						}else{
							tb="0";
						}
						maps.put(keys, tb);
					}
				}
			}
			maps.put("tbDB_QYCS", null);
			maps.put("tbDB_ZZCS", null);
			for (Map.Entry<String,BigDecimal> hbEntry : hbMap.entrySet()) { 
				if(entry.getKey().equals(hbEntry.getKey()) && !entry.getKey().equals("DB_QYCS")&& !entry.getKey().equals("DB_ZZCS")){
					if(entry.getKey().equals("DB_ZZCJLR") || entry.getKey().equals("DB_XSJLR") || entry.getKey().equals("DB_QYJLL")){
						String hb="0";
						BigDecimal hbs=entry.getValue().subtract(hbEntry.getValue());
						if(hbs.compareTo(BigDecimal.ZERO)==-1){
							hb="↓"+hbs.abs();
						}else if(hbs.compareTo(BigDecimal.ZERO)==1){
							hb="↑"+hbs.abs();
						}else{
							hb="0";
						}
						maps.put("hb"+entry.getKey(), hb);
					}else{
						String keys="hb"+entry.getKey();
						String hb="0";
						BigDecimal hbs=new BigDecimal("0");
						if(entry.getValue()!=null && !entry.getValue().equals(null)){
							if(hbEntry.getValue()!=null ){
								if(hbEntry.getValue().compareTo(BigDecimal.ZERO)!=0){
									hbs=(entry.getValue().subtract(hbEntry.getValue())).multiply(new BigDecimal("100")).divide((hbEntry.getValue()).abs(),2, RoundingMode.HALF_UP);
								}
							}
						}
						if(hbs.compareTo(BigDecimal.ZERO)==-1){
							hb="↓"+hbs.abs();
						}else if(hbs.compareTo(BigDecimal.ZERO)==1){
							hb="↑"+hbs.abs();
						}else{
							hb="0";
						}
						maps.put(keys, hb);
					}
				}
			}
			if(entry.getValue()!=null && !entry.getValue().equals(null)){
				if(entry.getKey().equals("DB_QYCS") || entry.getKey().equals("DB_ZZCS")){
					maps.put(entry.getKey(), entry.getValue().toString());
				}else if(entry.getKey().equals("DB_ZZCJLR") || entry.getKey().equals("DB_XSJLR") || entry.getKey().equals("DB_QYJLL")){
					maps.put(entry.getKey(), entry.getValue()+"%");
				}else{
					maps.put(entry.getKey(), entry.getValue()+"万");
				}
			}else{
				if(entry.getKey().equals("DB_QYCS") || entry.getKey().equals("DB_QYJLL") || entry.getKey().equals("DB_XSJLR") || entry.getKey().equals("DB_ZZCJLR") || entry.getKey().equals("DB_ZZCS")){
					maps.put(entry.getKey(), "0");
				}else{
					maps.put(entry.getKey(), "0万");
				}
			}
		}
		maps.put("hbDB_QYCS", null);
		maps.put("hbDB_ZZCS", null);
		return maps;
	}
	
	@Override
	public Map<String, BigDecimal> getDbMsg(String startTime, String endTime, String orgId) throws Exception {
		//Map<String,BigDecimal> modeValus = reportResultService.getModeParams(startTime, endTime, orgId, null);
		//String start=startTime+"-01";
		//String end=endTime+"-01";
		Map<String,BigDecimal> modeValus =reportResultService.getDbMsgMap(startTime+"-01", endTime+"-01", orgId);
		Map<String,BigDecimal> resultMap = new HashMap<>() ;
		//可供出售的金融资产 money.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP)
		resultMap.put("DB_V", modeValus.get("ZCFZ_V")!=null ?  modeValus.get("ZCFZ_V").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//持有至到期的投资
		resultMap.put("DB_W ", modeValus.get("ZCFZ_W")!=null ?  modeValus.get("ZCFZ_W").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//长期应收款
		resultMap.put("DB_X", modeValus.get("ZCFZ_X")!=null ?  modeValus.get("ZCFZ_X").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//长期股权投资
		resultMap.put("DB_Y", modeValus.get("ZCFZ_Y")!=null ?  modeValus.get("ZCFZ_Y").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//投资性房地产
		resultMap.put("DB_Z", modeValus.get("ZCFZ_Z")!=null ?  modeValus.get("ZCFZ_Z").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//固定资产
		resultMap.put("DB_AA", modeValus.get("ZCFZ_AA")!=null ?  modeValus.get("ZCFZ_AA").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//在建工程
		resultMap.put("DB_AB", modeValus.get("ZCFZ_AB")!=null ?  modeValus.get("ZCFZ_AB").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//工程物资
		resultMap.put("DB_AC", modeValus.get("ZCFZ_AC")!=null ?  modeValus.get("ZCFZ_AC").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//固定资产清理
		resultMap.put("DB_AD", modeValus.get("ZCFZ_AD")!=null ?  modeValus.get("ZCFZ_AD").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//生产性生物资产
		resultMap.put("DB_AE", modeValus.get("ZCFZ_AE")!=null ?  modeValus.get("ZCFZ_AE").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//油气资产
		resultMap.put("DB_AF", modeValus.get("ZCFZ_AF")!=null ?  modeValus.get("ZCFZ_AF").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//无形资产
		resultMap.put("DB_AG", modeValus.get("ZCFZ_AG")!=null ?  modeValus.get("ZCFZ_AG").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//开发支出
		resultMap.put("DB_AH", modeValus.get("ZCFZ_AH")!=null ?  modeValus.get("ZCFZ_AH").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//商誉
		resultMap.put("DB_AI", modeValus.get("ZCFZ_AI")!=null ?  modeValus.get("ZCFZ_AI").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//长期待摊费用
		resultMap.put("DB_AJ", modeValus.get("ZCFZ_AJ")!=null ?  modeValus.get("ZCFZ_AJ").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//递延所得税资产
		resultMap.put("DB_AK", modeValus.get("ZCFZ_AK")!=null ?  modeValus.get("ZCFZ_AK").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//其他非流动资产
		resultMap.put("DB_AL", modeValus.get("ZCFZ_AL")!=null ?  modeValus.get("ZCFZ_AL").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//长期资产=可供出售金融资产+持有至到期的投资+长期应收款+长期股权投资+投资性房地产+固定资产+在建工程+工程物资+固定资产清理+生产性生物资产+油气资产+无形资产+开发指出+商誉+长期待摊费用+递延所得税资产+其他非流动资产
		resultMap.put("DB_AM", modeValus.get("ZCFZ_AM")!=null ?  modeValus.get("ZCFZ_AM").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//货币资金
		//resultMap.put("DB_A", modeValus.get("ZCFZ_A")!=null ?  modeValus.get("ZCFZ_A").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		BigDecimal xj=new BigDecimal("0");
		if(modeValus.get("ZCFZ_A")!=null){
			xj=xj.add(modeValus.get("ZCFZ_A"));
		}
		if(modeValus.get("ZCFZ_D")!=null){
			xj=xj.add(modeValus.get("ZCFZ_D"));
		}
		resultMap.put("DB_A",   xj.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP));
		//应收票据
		resultMap.put("DB_F", modeValus.get("ZCFZ_F")!=null ?  modeValus.get("ZCFZ_F").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//金融资产
		resultMap.put("DB_D", modeValus.get("ZCFZ_D")!=null ?  modeValus.get("ZCFZ_D").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//衍生金融资产
		resultMap.put("DB_E", modeValus.get("ZCFZ_E")!=null ?  modeValus.get("ZCFZ_E").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//应收账款
		resultMap.put("DB_G", modeValus.get("ZCFZ_G")!=null ?  modeValus.get("ZCFZ_G").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//预付款项
		resultMap.put("DB_H", modeValus.get("ZCFZ_H")!=null ?  modeValus.get("ZCFZ_H").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//应收利息
		resultMap.put("DB_L", modeValus.get("ZCFZ_L")!=null ?  modeValus.get("ZCFZ_L").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//应收股利
		resultMap.put("DB_M", modeValus.get("ZCFZ_M")!=null ?  modeValus.get("ZCFZ_M").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//其他应收款
		BigDecimal qt=new BigDecimal("0");
		if(modeValus.get("ZCFZ_F")!=null){
			qt=qt.add(modeValus.get("ZCFZ_F"));
		}
		if(modeValus.get("ZCFZ_H")!=null){
			qt=qt.add(modeValus.get("ZCFZ_H"));
		}
		if(modeValus.get("ZCFZ_L")!=null){
			qt=qt.add(modeValus.get("ZCFZ_L"));
		}
		if(modeValus.get("ZCFZ_M")!=null){
			qt=qt.add(modeValus.get("ZCFZ_M"));
		}
		if(modeValus.get("ZCFZ_N")!=null){
			qt=qt.add(modeValus.get("ZCFZ_N"));
		}
		if(modeValus.get("ZCFZ_R")!=null){
			qt=qt.add(modeValus.get("ZCFZ_R"));
		}
		if(modeValus.get("ZCFZ_S")!=null){
			qt=qt.add(modeValus.get("ZCFZ_S"));
		}
		resultMap.put("DB_N", qt.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP));
		//存货
		resultMap.put("DB_P", modeValus.get("ZCFZ_P")!=null ?  modeValus.get("ZCFZ_P").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//持有待售的资产
		resultMap.put("DB_Q", modeValus.get("ZCFZ_Q")!=null ?  modeValus.get("ZCFZ_Q").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//一年内到期的非流动资产
		resultMap.put("DB_R", modeValus.get("ZCFZ_R")!=null ?  modeValus.get("ZCFZ_R").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//其他流动资产
		resultMap.put("DB_S", modeValus.get("ZCFZ_S")!=null ?  modeValus.get("ZCFZ_S").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//流动资产=货币资金+应收票据+金融资产+衍生金融资产+应收账款+预付款项+应收利息+应收股利+其他应收款+存货+持有待售的资产+一年内到期的非流动资产+其他流动资产
		resultMap.put("DB_T", modeValus.get("ZCFZ_T")!=null ?  modeValus.get("ZCFZ_T").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//资产=长期资产+流动资产
		BigDecimal zc = new BigDecimal("0") ;
		if(null == modeValus.get("ZCFZ_AM")){
			zc = modeValus.get("ZCFZ_T") ;
		}
		else if(null == modeValus.get("ZCFZ_T")){
			zc = modeValus.get("ZCFZ_AM") ;
		}
		else {
			zc = modeValus.get("ZCFZ_AM").add(modeValus.get("ZCFZ_T")) ;
		}
		resultMap.put("DB_ZC", zc!=null ?  zc.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//资产周转次数=营业收入/资产
		BigDecimal zzcs = new BigDecimal("0") ;
		BigDecimal yysr = modeValus.get("LR_A");  //营业收入
		resultMap.put("DB_AYYSR", yysr!=null ?  yysr.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		if(null != zc && zc.compareTo(BigDecimal.ZERO) != 0 && null != yysr){
			zzcs = yysr.divide(zc,2, BigDecimal.ROUND_HALF_UP);
		}
		resultMap.put("DB_ZZCS", zzcs);
		//所得税=利润总额*所得税税率
		BigDecimal lrze = modeValus.get("LR_AD"); //利润总额
		/*BigDecimal sds = null ;
		if(null != lrze){
			sds = lrze.multiply(new BigDecimal(0.25)).setScale(2, BigDecimal.ROUND_HALF_UP) ;
		}
		resultMap.put("DB_SDS", sds);*/
		BigDecimal sds = modeValus.get("LR_AE");
		resultMap.put("DB_SDS", sds!=null ?  sds.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));    //不需要乘以所得税税率
		//税金及期间费用=税金及附加+销售费用+管理费用+财务费用
		BigDecimal ssjqjfy = null ;
		if(null != modeValus.get("LR_O")){
			if(null == ssjqjfy){
				ssjqjfy = new BigDecimal(0);
			}
			ssjqjfy = ssjqjfy.add(modeValus.get("LR_O")) ;
		}
		if(null != modeValus.get("LR_P")){
			if(null == ssjqjfy){
				ssjqjfy = new BigDecimal(0);
			}
			ssjqjfy = ssjqjfy.add(modeValus.get("LR_P")) ;
		}
		if(null != modeValus.get("LR_Q")){
			if(null == ssjqjfy){
				ssjqjfy = new BigDecimal(0);
			}
			ssjqjfy = ssjqjfy.add(modeValus.get("LR_Q")) ;
		}
		if(null != modeValus.get("LR_R")){
			if(null == ssjqjfy){
				ssjqjfy = new BigDecimal(0);
			}
			ssjqjfy = ssjqjfy.add(modeValus.get("LR_R")) ;
		}
		log.info("=============DB_SSJQJFY:"+ssjqjfy);
		resultMap.put("DB_SSJQJFY", ssjqjfy!=null ?  ssjqjfy.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		BigDecimal sjlr=modeValus.get("LR_AF");          //净利润  直接取
		log.info("============LR-AF,jlr:"+sjlr);
		resultMap.put("DB_SJLR", sjlr!=null ?  sjlr.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//成本费用=营业成本+税金及期间费用          营业收入-净利润（或营业成本+税费及期间费用+其他损益+营业外收支+所得税费用）
		BigDecimal cbfy = new BigDecimal("0") ;
		if(null != modeValus.get("LR_F") ){
			cbfy = cbfy.add(modeValus.get("LR_F"));
		}
		if(null != modeValus.get("LR_O") ){
			cbfy = cbfy.add(modeValus.get("LR_O"));
		}
		if(null != modeValus.get("LR_P")){
			cbfy = cbfy.add(modeValus.get("LR_P"));
		}
		if(null != modeValus.get("LR_Q") ){
			cbfy = cbfy.add(modeValus.get("LR_Q"));
		}
		if(null != modeValus.get("LR_R") ){
			cbfy = cbfy.add(modeValus.get("LR_R"));
		}
		/*if(null != modeValus.get("LR_F")){
			if(null == cbfy){
				cbfy = new BigDecimal(0);
			}
			cbfy = cbfy.add(modeValus.get("LR_F")) ;
		}*/
		//营业成本 
		resultMap.put("DB_YYCB", modeValus.get("LR_F")!=null ?  modeValus.get("LR_F").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		/*if(null != ssjqjfy){
			if(null == cbfy){
				cbfy = new BigDecimal(0);
			}
			cbfy = cbfy.add(ssjqjfy) ;
		}*/
		//成本费用
		resultMap.put("DB_CBFY", cbfy!=null ?  cbfy.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//净利润=营业收入-成本费用-其他损益-营业外支出-所得税
		/*BigDecimal sjlr = null ;
		if(null != modeValus.get("LR_A")){
			if(null == sjlr){
				sjlr = new BigDecimal(0);
			}
			sjlr = sjlr.add(modeValus.get("LR_A")) ;
		}
		if(null != cbfy){
			if(null == sjlr){
				sjlr = new BigDecimal(0);
			}
			sjlr = sjlr.subtract(cbfy) ;
		}
		if(null != modeValus.get("LR_X")){
			if(null == sjlr){
				sjlr = new BigDecimal(0);
			}
			sjlr = sjlr.subtract(modeValus.get("LR_X")) ;
		}
		if(null != modeValus.get("LR_AB")){
			if(null == sjlr){
				sjlr = new BigDecimal(0);
			}
			sjlr = sjlr.subtract(modeValus.get("LR_AB")) ;
		}
		if(null != modeValus.get("LR_AE")){
			if(null == sjlr){
				sjlr = new BigDecimal(0);
			}
			sjlr = sjlr.subtract(modeValus.get("LR_AE")) ;
		}*/
		//总资产
		BigDecimal zzc = modeValus.get("ZCFZ_AN") ;
		resultMap.put("DB_AN", zzc!=null ? zzc.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//营业外收支
		BigDecimal yywsz=new BigDecimal("0");
		if(null != modeValus.get("LR_Z") ){
			yywsz = yywsz.add(modeValus.get("LR_Z"));
		}
		if(null != modeValus.get("LR_AB") ){
			yywsz = yywsz.subtract(modeValus.get("LR_AB"));
		}
		resultMap.put("DB_YYWZCS",  yywsz.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP));
		//resultMap.put("DB_YYWZCS", modeValus.get("LR_AB")!=null ?  modeValus.get("LR_AB").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		// 其他损益  DB_QTSY
		BigDecimal qtsy=new BigDecimal("0");
		if(null != modeValus.get("LR_T") ){
			qtsy = qtsy.add(modeValus.get("LR_T"));
		}
		if(null != modeValus.get("LR_U") ){
			qtsy = qtsy.add(modeValus.get("LR_U"));
		}
		if(null != modeValus.get("LR_CC")){
			qtsy = qtsy.add(modeValus.get("LR_CC"));
		}
		if(null != modeValus.get("LR_X") ){
			qtsy = qtsy.add(modeValus.get("LR_X"));
		}
		if(null != modeValus.get("LR_S") ){
			qtsy = qtsy.subtract(modeValus.get("LR_S"));
		}
		resultMap.put("DB_QTSY", qtsy!=null ?  qtsy.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//销售净利率=净利润/营业收入
		//BigDecimal jlr = modeValus.get("LR_AF");  //净利润
		BigDecimal xsjlr = new BigDecimal("0") ;
		if(null != sjlr && null != yysr && yysr.compareTo(BigDecimal.ZERO) != 0){
			xsjlr = sjlr.divide(yysr,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP);
		}
		resultMap.put("DB_XSJLR", xsjlr);
		//总资产净利率=销售净利率*资产周转次数                    净利润/总资产
		BigDecimal zjlr = new BigDecimal("0") ;
		if(null != zzc && null != sjlr && zzc.compareTo(BigDecimal.ZERO) != 0){
			//zjlr = xsjlr.multiply(zzcs).setScale(2, BigDecimal.ROUND_HALF_UP);
			zjlr=sjlr.divide(zzc, 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP);
		}
		resultMap.put("DB_ZZCJLR",zjlr);
		//resultMap.put("DB_ZJLR", zjlr);DB_ZZCJLR
		//resultMap.put("DB_ZZCJLR",zjlr!=null ?  zjlr.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//短期借款
		resultMap.put("DB_AO", modeValus.get("ZCFZ_AO")!=null ?  modeValus.get("ZCFZ_AO").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//金融负债
		resultMap.put("DB_AS", modeValus.get("ZCFZ_AS")!=null ?  modeValus.get("ZCFZ_AS").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//衍生金融负债
		resultMap.put("DB_AT", modeValus.get("ZCFZ_AT")!=null ?  modeValus.get("ZCFZ_AT").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//应付票据
		resultMap.put("DB_AU", modeValus.get("ZCFZ_AU")!=null ?  modeValus.get("ZCFZ_AU").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//应付账款
		resultMap.put("DB_AV", modeValus.get("ZCFZ_AV")!=null ?  modeValus.get("ZCFZ_AV").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//预收款项
		resultMap.put("DB_AW", modeValus.get("ZCFZ_AW")!=null ?  modeValus.get("ZCFZ_AW").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//应付职工薪酬
		resultMap.put("DB_AZ", modeValus.get("ZCFZ_AZ")!=null ?  modeValus.get("ZCFZ_AZ").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//应交税费
		resultMap.put("DB_BA", modeValus.get("ZCFZ_BA")!=null ?  modeValus.get("ZCFZ_BA").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//应付利息
		resultMap.put("DB_BB", modeValus.get("ZCFZ_BB")!=null ?  modeValus.get("ZCFZ_BB").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//应付股利
		resultMap.put("DB_BC", modeValus.get("ZCFZ_BC")!=null ?  modeValus.get("ZCFZ_BC").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//其他应付款
		resultMap.put("DB_BD", modeValus.get("ZCFZ_BD")!=null ?  modeValus.get("ZCFZ_BD").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//持有待售的负债
		resultMap.put("DB_BI", modeValus.get("ZCFZ_BI")!=null ?  modeValus.get("ZCFZ_BI").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));resultMap.put("DB_V", modeValus.get("ZCFZ_W")!=null ?  modeValus.get("ZCFZ_W").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//一年内到期的非流动负债
		resultMap.put("DB_BJ", modeValus.get("ZCFZ_BJ")!=null ?  modeValus.get("ZCFZ_BJ").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//其他流动负债
		resultMap.put("DB_BK", modeValus.get("ZCFZ_BK")!=null ?  modeValus.get("ZCFZ_BK").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//短期负债=短期借款+金融负债+衍生金融负债+应付票据+应付账款+预收款项+应付职工薪酬+应交税费+应付利息+应付股利+其他应付款+持有待售的负债+一年内到期的非流动负债+其他流动负债
		BigDecimal dqfz = modeValus.get("ZCFZ_BL") ; //短期负债
		resultMap.put("DB_BL", dqfz!=null ?  dqfz.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//长期借款
		resultMap.put("DB_BM", modeValus.get("ZCFZ_BM")!=null ?  modeValus.get("ZCFZ_W").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//应付债券
		resultMap.put("DB_BN", modeValus.get("ZCFZ_BN")!=null ?  modeValus.get("ZCFZ_BN").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//长期应付款
		resultMap.put("DB_BQ", modeValus.get("ZCFZ_BQ")!=null ?  modeValus.get("ZCFZ_BQ").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//专项应付款
		resultMap.put("DB_BS", modeValus.get("ZCFZ_BS")!=null ?  modeValus.get("ZCFZ_BS").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//预计负债
		resultMap.put("DB_BT", modeValus.get("ZCFZ_BT")!=null ?  modeValus.get("ZCFZ_BT").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//递延收益
		resultMap.put("DB_BU", modeValus.get("ZCFZ_BU")!=null ?  modeValus.get("ZCFZ_BU").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//递延所得税负债
		resultMap.put("DB_BV", modeValus.get("ZCFZ_BV")!=null ?  modeValus.get("ZCFZ_BV").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//其他非流动负债
		resultMap.put("DB_BW", modeValus.get("ZCFZ_BW")!=null ?  modeValus.get("ZCFZ_BW").divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//长期负债=长期借款+应付债券+长期应付款+专项应付款+预计负债+递延收益+递延所得税负债+其他非流动负债
		BigDecimal cqfz = modeValus.get("ZCFZ_BX");
		resultMap.put("DB_BX", cqfz!=null ?  cqfz.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//负债=长期负债+短期负债
		BigDecimal zfz = null ;
		if(null == dqfz){
			zfz = cqfz ;
		}
		else if(null == cqfz){
			zfz = dqfz ;
		}
		else {
			zfz = dqfz.add(cqfz) ;
		}
		resultMap.put("DB_ZFZ", zfz!=null ?  zfz.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//所有者权益=资产-负债
		BigDecimal syzqy = null ;
		if(null == zzc && null != zfz){
			syzqy = BigDecimal.ZERO.subtract(zfz) ;
		}
		else if(null != zzc && null == zfz){
			syzqy = zzc ;
		}
		else if(null != zzc && null != zfz){
			syzqy = zzc.subtract(zfz) ;
		}
		resultMap.put("DB_SYZQY", syzqy!=null ?  syzqy.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP):new BigDecimal("0"));
		//权益乘数=资产/所有者权益
		BigDecimal qycs = new BigDecimal("0") ;
		if(null != zzc && null != syzqy && BigDecimal.ZERO.compareTo(syzqy) != 0){
			qycs = zzc.divide(syzqy,2,BigDecimal.ROUND_HALF_UP);
		}
		resultMap.put("DB_QYCS",qycs);
		BigDecimal s= modeValus.get("ZCFZ_CM");
		//权益净利率=总资产净利率*权益乘数                           净利润/权益总额     sjlr/
		BigDecimal qyjll = new BigDecimal("0") ;
		if(s!=null && s.compareTo(BigDecimal.ZERO)!=0){
			qyjll=sjlr.divide(s, 4, RoundingMode.HALF_UP);
		}
		/*if(null != zjlr && null != qycs ){
			qyjll = zjlr.multiply(qycs).setScale(2, BigDecimal.ROUND_HALF_UP) ;
		}*/
		resultMap.put("DB_QYJLL", qyjll.multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP));
		return resultMap;
	}
}




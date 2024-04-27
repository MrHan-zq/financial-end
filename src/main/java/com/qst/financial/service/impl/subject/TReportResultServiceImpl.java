package com.qst.financial.service.impl.subject;

import com.qst.financial.dao.mapper.subject.TBasiAssetsAndLiabilitiesMapper;
import com.qst.financial.dao.mapper.subject.TBasiCashFlowMapper;
import com.qst.financial.dao.mapper.subject.TBasiProfitMapper;
import com.qst.financial.dao.mapper.subject.TKmyeMapper;
import com.qst.financial.modle.subject.*;
import com.qst.financial.mq.JMSProducer;
import com.qst.financial.service.impl.base.BaseServiceImpl;
import com.qst.financial.service.subject.ReportResultService;
import com.qst.financial.service.subject.TReportResultService;
import com.qst.financial.util.CalculateUtils;
import com.qst.financial.util.common.Common;
import com.qst.financial.util.tag.InnerPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.jms.Queue;
import javax.jms.Topic;
import javax.script.ScriptException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TReportResultServiceImpl extends BaseServiceImpl<TReportResult, Integer> implements TReportResultService {
	private Logger log=LoggerFactory.getLogger(TReportResultServiceImpl.class);

	private static final String REPORT_TYPE_ZCFZ = "1" ;
	private static final String REPORT_TYPE_LR = "2" ;
	private static final String REPORT_TYPE_XJLL = "3" ;
	private static final String REPORT_TYPE_YBLFX = "5" ;

	@Autowired
	private com.qst.financial.dao.mapper.subject.TReportResultMapper TReportResultMapper;
	@Autowired
	private TBasiProfitMapper tBasiProfitMapper;
	@Autowired
	private TKmyeMapper tKmyeMapper;
	@Autowired
	private TBasiCashFlowMapper tBasiCashFlowMapper;
	@Autowired
	private TBasiAssetsAndLiabilitiesMapper tBasiAssetsAndLiabilitiesMapper;
	@Autowired
	private JMSProducer jmsProducer;
	@Autowired
	private Topic topic;
	@Autowired
	private Queue queue;
	@Autowired
	private ReportResultService reportResultService ;

	//定义锁对象
	private final ReentrantLock lock=new ReentrantLock();

	/**
	 * 通过基表的list获取结果表对应记录
	 * @param datas
	 * @param org_id
	 * @return
	 * @throws Exception
	 */
	public List<TReportResult> filedToCodeNo(List datas, String org_id)throws Exception{
		List<TReportResult> reList = new ArrayList<>() ;
		if(!Common.isEmpty(datas)){
			List<TCodeMapperBean> list = TReportResultMapper.selectALLCodeMapper();
			Map<String,String> map = new HashMap<>() ;
			for(TCodeMapperBean bean : list){
				map.put(bean.getBasiField().trim().toLowerCase().replace("_", ""), bean.getCodeNo()) ;
			}
			for(Object obj : datas){
				Class<?> cls = obj.getClass();
				Method[] methods = cls.getMethods() ;
				Method getYearMoth = cls.getDeclaredMethod("getKjyearMoth", null);
				Method getOrgId = cls.getDeclaredMethod("getOrgId", null);
				String yearMoth = (String)getYearMoth.invoke(obj, null);
				String year = null;
				String month = null;
				if(yearMoth.contains("-") && (yearMoth.trim().length() == 6 || yearMoth.trim().length() == 7)){
					String[] tmp = yearMoth.split("-") ;
					year = tmp[0];
					month = tmp[1] ;
					if(!StringUtils.isEmpty(year) && !StringUtils.isEmpty(month) && year.length() == 4 && month.length() == 1){
						yearMoth = year + "-0" + month ;
					}
				}
				else if(yearMoth.length() == 6 && !yearMoth.contains("-")){
					yearMoth = yearMoth.substring(0, 4) + "-" + yearMoth.substring(4, 6) ;
					String[] tmp = yearMoth.split("-") ;
					year = tmp[0];
					month = tmp[1] ;
				}
				else{
					throw new Exception("导入数据年月异常，请重新确认！");
				}

				String orgId = (String)getOrgId.invoke(obj, null);
				for(Method method : methods){
					String basiField = method.getName().toLowerCase().replace("get", "");
					if(!StringUtils.isEmpty(basiField) && !basiField.startsWith("set") ){
						String codeNo = map.get(basiField.trim());
						if(!StringUtils.isEmpty(codeNo)){
							Object amt = method.invoke(obj, null) ;
							if(null != amt){
								//BigDecimal batm=(BigDecimal)amt;
									TReportResult bean = new TReportResult() ;
									bean.setRyear(year);
									bean.setRmonth(month);
									bean.setRyearAndMonth(yearMoth);
									bean.setOrgId(orgId);
									bean.setId(UUID.randomUUID().toString().replace("-", ""));
									bean.setCodeNo(codeNo);
									bean.setDataValue(((BigDecimal)amt).setScale(2, RoundingMode.HALF_UP));
									reList.add(bean);
							}
						}
					}
				}
			}
		}
		return reList ;
	}

	//通用数据转换
	@Override
	public boolean comFormatData(List list, String org_id) {
		boolean r=true;
		//加锁
		lock.lock();
		try {
			if(!Common.isEmpty(list) && list.size()>0){
				//消除重复插入结果表
				deleteRdataByYearMonth(list);
				//结果数据封装
				List<TReportResult> tReportResultLst=new ArrayList<TReportResult>();
				tReportResultLst=this.filedToCodeNo(list, org_id);

				//批量插入结果表
				if(!Common.isEmpty(tReportResultLst) && tReportResultLst.size()>0){
					TReportResultMapper.insertTReportResultList(tReportResultLst);
				}
				
				insertCal(list);
				
				//修改转换状态
				changeStatus(list);
			}
		}catch (Exception e)
		{
			r=false;
			log.info("=====:"+e.getMessage());
			e.printStackTrace();
		}
		//使用finally块来保证释放锁
		finally{
			lock.unlock();
		}
		return r;
	}

	private void insertCal(List list) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
			Exception, ScriptException {
		List<TReportResult> ilist = new ArrayList<>() ;
		for(Object obj : list){
			Class<?> cls = obj.getClass();
			Method getYearMonth = cls.getDeclaredMethod("getKjyearMoth", null);
			Method getOrgId = cls.getDeclaredMethod("getOrgId", null);
			String yearMoth = (String)getYearMonth.invoke(obj, null);
			String orgId = (String)getOrgId.invoke(obj, null);
			yearMoth = checkYearMonthFomat(yearMoth);
			//批量插入比例分析结果
			Map<String,BigDecimal> map = reportResultService.getModeParams(yearMoth, yearMoth, orgId, null);
			for(BiliRule bean : BiliRule.values()){
				String jNullCode = bean.getJnull() ;
				BigDecimal jVal = null ;
				try{
					jVal = CalculateUtils.strToSs(jNullCode, map);
				}catch(Exception e){
					e.printStackTrace();
					continue ;
				}
				if(null == jNullCode || (null != map.get(jNullCode) && map.get(jNullCode).compareTo(new BigDecimal(0)) != 0 
						&& null != jVal && jVal.compareTo(new BigDecimal(0)) != 0)){
					//被除数判断不为0
					String codeNo = bean.toString().replace("_", "-");
					map.put("MONTHS", new BigDecimal(1));
					map.put("DAYS", new BigDecimal(31));
					BigDecimal val = null ;
					try{
						val = CalculateUtils.strToSs(bean.getCal(), map);
					}catch(Exception e){
						e.printStackTrace();
						continue ;
					}
					if(null == val){
						continue ;
					}
					
					String year = null;
					String month = null;
					if(yearMoth.contains("-") && (yearMoth.trim().length() == 6 || yearMoth.trim().length() == 7)){
						String[] tmp = yearMoth.split("-") ;
						year = tmp[0];
						month = tmp[1] ;
						if(!StringUtils.isEmpty(year) && !StringUtils.isEmpty(month) && year.length() == 4 && month.length() == 1){
							yearMoth = year + "-0" + month ;
						}
					}
					else if(yearMoth.length() == 6 && !yearMoth.contains("-")){
						yearMoth = yearMoth.substring(0, 4) + "-" + yearMoth.substring(4, 6) ;
					}
					else{
						throw new Exception("导入数据年月异常，请重新确认！");
					}
					TReportResult b = new TReportResult() ;
					b.setRyear(year);
					b.setRmonth(month);
					b.setRyearAndMonth(yearMoth);
					b.setOrgId(orgId);
					b.setId(UUID.randomUUID().toString().replace("-", ""));
					b.setCodeNo(codeNo);
					b.setDataValue(((BigDecimal)val).setScale(2, RoundingMode.HALF_UP));
					ilist.add(b);
				}
			}
		}
		if(!Common.isEmpty(ilist) && ilist.size()>0){
			TReportResultMapper.insertTReportResultList(ilist);
		}
	}

	private void changeStatus(List list) {
		for(Object obj : list){
			Class<?> cls = obj.getClass();
			String className=cls.getName();
			//利润表
			if(className.contains("TBasiProfit")){
				TBasiProfit tBasiProfit=(TBasiProfit)obj;
				tBasiProfit.setFormatFlag("Y");
				tBasiProfitMapper.updateFormatFlagByPrimaryKey(tBasiProfit.getId(), "Y");
				//资产负债表
			}else if(className.contains("TBasiAssetsAndLiabilities")){
				TBasiAssetsAndLiabilities tBasiAssetsAndLiabilities=(TBasiAssetsAndLiabilities)obj;
				tBasiAssetsAndLiabilitiesMapper.updateFormatFlagByPrimaryKey(tBasiAssetsAndLiabilities.getId(), "Y");
				//科目余额表
			}else if(className.contains("TKmye")){
				TKmye tKmye=(TKmye)obj;
				tKmyeMapper.updateFormatFlagByPrimaryKey(tKmye.getId(), "Y");
				//现金流量表
			}else if(className.contains("TBasiCashFlow")){
				TBasiCashFlow tBasiCashFlow=(TBasiCashFlow)obj;
				tBasiCashFlowMapper.updateFormatFlagByPrimaryKey(tBasiCashFlow.getId(), "Y");
			}

		}
	}

	private void deleteRdataByYearMonth(List list) {
		for(Object obj : list){
			Class<?> cls = obj.getClass();
			String className=cls.getName();
			Map<String,String> map = new HashMap<>() ;
			//利润表
			if(className.contains("TBasiProfit")){
				TBasiProfit tBasiProfit=(TBasiProfit)obj;
				String yearMoth = tBasiProfit.getKjyearMoth() ;
				yearMoth = checkYearMonthFomat(yearMoth);
				map.clear(); 
				map.put("orgId", tBasiProfit.getOrgId());
				map.put("yearMonth", yearMoth);
				map.put("reportType", REPORT_TYPE_LR);
				TReportResultMapper.deleteData(map);
				map.put("reportType",REPORT_TYPE_YBLFX);
				TReportResultMapper.deleteData(map);
				//资产负债表
			}else if(className.contains("TBasiAssetsAndLiabilities")){
				TBasiAssetsAndLiabilities tBasiAssetsAndLiabilities=(TBasiAssetsAndLiabilities)obj;
				String yearMoth = tBasiAssetsAndLiabilities.getKjyearMoth() ;
				yearMoth = checkYearMonthFomat(yearMoth);
				map.clear(); 
				map.put("orgId", tBasiAssetsAndLiabilities.getOrgId());
				map.put("yearMonth", yearMoth);
				map.put("reportType", REPORT_TYPE_ZCFZ);
				TReportResultMapper.deleteData(map);
				map.put("reportType",REPORT_TYPE_YBLFX);
				TReportResultMapper.deleteData(map);
				//现金流量表
			}else if(className.contains("TBasiCashFlow")){
				TBasiCashFlow tBasiCashFlow=(TBasiCashFlow)obj;
				String yearMoth = tBasiCashFlow.getKjyearMoth() ;
				yearMoth = checkYearMonthFomat(yearMoth);
				map.clear(); 
				map.put("orgId", tBasiCashFlow.getOrgId());
				map.put("yearMonth", yearMoth);
				map.put("reportType", REPORT_TYPE_XJLL);
				TReportResultMapper.deleteData(map);
				map.put("reportType",REPORT_TYPE_YBLFX);
				TReportResultMapper.deleteData(map);
			}
		}
	}

	private String checkYearMonthFomat(String yearMoth) {
		if(yearMoth.contains("-") && (yearMoth.trim().length() == 6 || yearMoth.trim().length() == 7)){
			String[] tmp = yearMoth.split("-") ;
			String year = tmp[0];
			String month = tmp[1] ;
			if(!StringUtils.isEmpty(year) && !StringUtils.isEmpty(month) && year.length() == 4 && month.length() == 1){
				yearMoth = year + "-0" + month ;
			}
		}
		else if(yearMoth.length() == 6 && !yearMoth.contains("-")){
			yearMoth = yearMoth.substring(0, 4) + "-" + yearMoth.substring(4, 6) ;
		}
		return yearMoth;
	}

	@Override
	public List<TReportResult> selectAllByType(InnerPage innerPage){
		return TReportResultMapper.selectAllByType(innerPage);
	}

	@Override
	public int selectAllCountByType(Map<String, String> map){
		return TReportResultMapper.selectAllCountByType(map);
	}

}




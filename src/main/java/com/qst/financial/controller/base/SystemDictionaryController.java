package com.qst.financial.controller.base;

import com.qst.financial.dao.mapper.base.SysDictionaryDetailMapper;
import com.qst.financial.dao.mapper.base.SysDictionaryMapper;
import com.qst.financial.dao.mapper.base.SysResourcesMapper;
import com.qst.financial.modle.base.SysDictionaryDetailModel;
import com.qst.financial.modle.base.SysDictionaryModel;
import com.qst.financial.modle.base.SysResourcesModel;
import com.qst.financial.modle.base.SysUserModel;
import com.qst.financial.service.base.SystemDictionaryService;
import com.qst.financial.util.common.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/admin/pc/systemDictionary")
public class SystemDictionaryController extends BaseController {
	
	@Autowired
   	private SysResourcesMapper sysResourcesMapper;
	
	@Autowired
   	private SysDictionaryMapper sysDictionaryMapper;
	
	@Autowired
   	private SysDictionaryDetailMapper sysDictionaryDetailMapper;
	
	@Autowired
   	private SystemDictionaryService systemDictionaryService;

	/**
     * 系统字典首页
     */
	//@SystemControllerLog(description = "系统字典")
    @RequestMapping(value = "/systemDictionaryIndex", method = RequestMethod.GET)
    public String systemDictionaryIndex(String fatherId, String parentId) throws Exception
    {
    	if (!Common.isEmpty(fatherId) && !Common.isEmpty(parentId))
        {
            HttpSession session = getSession();
            session.setAttribute("fatherMenuId", fatherId);
            session.setAttribute("menuId", parentId);
        }
        return "admin/jsp/systemDictionary/systemDictionaryIndex";
    }
	
	/**
     * 系统字典列表与按钮列表
     */
    @RequestMapping(value = "/systemDictionaryListAndButtonList", method = RequestMethod.GET)
    public String systemDictionaryListAndButtonList(HttpServletRequest request, Model model)
    {
        try
        {
        	String parentId = session.getAttribute("menuId").toString();
    		SysUserModel systemUser = getSessionUser();
    		Map<String,String> map = new HashMap<String, String>();
    		map.put("parent_id", parentId);
    		List<SysResourcesModel> buttonList = new ArrayList<SysResourcesModel>();
    		if ("1".equals(systemUser.getAccountType().toString())) {
    			map.put("user_id", systemUser.getId().toString());
    			buttonList = sysResourcesMapper.selectUserMenuButton(map);
    		} else {
    			buttonList = sysResourcesMapper.selectRootUserMenuButton(map);
    		}
            model.addAttribute("buttonList", buttonList);
            
            String pageNow = getPara("pageNow");
            String pageSize = getPara("pageSize");
            Map<String,String> dictionaryMap = new HashMap<String, String>();
            dictionaryMap.put("userId", getSessionUserid().toString());
            int count = sysDictionaryMapper.selectDictionaryCount(dictionaryMap);
			initPage(request, Integer.parseInt(pageNow), Integer.parseInt(pageSize), count);
			String limit = getLimit(Integer.parseInt(pageNow), Integer.parseInt(pageSize));
			dictionaryMap.put("limit", limit);
            List<Map<String, Object>> dictionaryList = sysDictionaryMapper.selectDictionaryList(dictionaryMap);
            model.addAttribute("dictionaryList", dictionaryList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return "admin/jsp/systemDictionary/systemDictionaryListAndButtonList";
    }
    
    /**
     * 系统字典列表
     */
    @RequestMapping(value = "/systemDictionaryList", method = RequestMethod.POST)
    public String systemDictionaryList(HttpServletRequest request, Model model)
    {
        try
        {
            String pageNow = getPara("pageNow");
            String pageSize = getPara("pageSize");
            String cnNameSearch = getPara("cnNameSearch");
            String enNameSearch = getPara("enNameSearch");
            if(Common.isEmpty(pageNow)){
            	pageNow = "1";
            }
            if(Common.isEmpty(pageSize)){
            	pageSize = "10";
            }
            Map<String,String> dictionaryMap = new HashMap<String, String>();
            if(Common.isNotEmpty(cnNameSearch))
            {
            	dictionaryMap.put("cnName", cnNameSearch);
            }
            if(Common.isNotEmpty(enNameSearch))
            {
            	dictionaryMap.put("enName", enNameSearch);
            }
            int count = sysDictionaryMapper.selectDictionaryCount(dictionaryMap);
			initPage(request, Integer.parseInt(pageNow), Integer.parseInt(pageSize), count);
			String limit = getLimit(Integer.parseInt(pageNow), count);
			dictionaryMap.put("limit", limit);
            List<Map<String, Object>> dictionaryList = sysDictionaryMapper.selectDictionaryList(dictionaryMap);
            model.addAttribute("dictionaryList", dictionaryList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return "admin/jsp/systemDictionary/systemDictionaryList";
    }
    
    /**
     * 系统字典新增页面
     */
    @RequestMapping(value = "/systemDictionaryAddPage", method = RequestMethod.GET)
    public String systemDictionaryAddPage()
    {
        try
        {
        	
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return "admin/jsp/systemDictionary/systemDictionaryAddPage";
    }
    
    /**
     * 系统字典新增验证字典中文名称与字典英文名称
     */
    @RequestMapping(value = "/systemDictionaryAddCnNameAndEnNameValidate", method = RequestMethod.GET)
    @ResponseBody
    public boolean systemDictionaryAddCnNameAndEnNameValidate()
    {
        try
        {
        	String cnName = getPara("cnName");
        	String enName = getPara("enName");
        	SysDictionaryModel model = new SysDictionaryModel();
        	model.setCnName(cnName);
        	model.setEnName(enName);
        	SysDictionaryModel dictionaryModel = sysDictionaryMapper.selectByCnNameOrEnName(model);
        	if(Common.isEmpty(dictionaryModel))
        	{
        		 return true;
        	}
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * 系统字典保存
     */
   // @SystemControllerLog(description = "系统字典保存")
    @RequestMapping(value = "/systemDictionarySave", method = RequestMethod.POST)
    public String systemDictionarySave(
    		@RequestParam("detailName") String[] detailName, 
    		@RequestParam("detailValue") String[] detailValue,
    		@RequestParam("detailRemark") String[] detailRemark, Model model) throws Exception
    {
    	String cnName = getPara("cnName");
    	String enName = getPara("enName");
    	String flag = getPara("flag");
    	String description = getPara("description");
    	
    	SysDictionaryModel dictionaryModel = new SysDictionaryModel();
    	dictionaryModel.setCnName(cnName);
    	dictionaryModel.setEnName(enName);
    	
    	SysDictionaryModel findModel = sysDictionaryMapper.selectByCnNameOrEnName(dictionaryModel);
    	if(Common.isEmpty(findModel))
    	{
    		if(Common.isEmpty(flag))
        	{
        		flag = "0";
        	}
        	dictionaryModel.setFlag(flag);
        	dictionaryModel.setRemark(description);
        	dictionaryModel.setCreateTime(new Date());
        	dictionaryModel.setCreateUserId(getSessionUserid());
        	
        	systemDictionaryService.addDictionaryAndDictionaryDetail(dictionaryModel, detailName, detailValue, detailRemark);
    	}
    	
		return "redirect:systemDictionaryIndex";
    }
	
    /**
     * 系统字典编辑页面
     */
    @RequestMapping(value = "/systemDictionaryEditPage", method = RequestMethod.GET)
    public String systemDictionaryEditPage(Model model)
    {
        try
        {
        	String dictionaryId = getPara("dictionaryId");
        	SysDictionaryModel dictionaryModel = sysDictionaryMapper.selectByPrimaryKey(Long.parseLong(dictionaryId));
        	List<SysDictionaryDetailModel> dictionaryDetaiList = sysDictionaryDetailMapper.selectByDictionaryId(dictionaryModel.getId());
        	model.addAttribute("dictionaryModel", dictionaryModel);
        	model.addAttribute("dictionaryDetaiList", dictionaryDetaiList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return "admin/jsp/systemDictionary/systemDictionaryEditPage";
    }
    
    /**
     * 系统字典更新验证字典中文名称与字典英文名称
     */
    @RequestMapping(value = "/systemDictionaryUpdateCnNameAndEnNameValidate", method = RequestMethod.GET)
    @ResponseBody
    public boolean systemDictionaryUpdateCnNameAndEnNameValidate()
    {
        try
        {
        	String dictionaryId = getPara("dictionaryId");
        	String cnName = getPara("cnName");
        	String enName = getPara("enName");
        	SysDictionaryModel model = new SysDictionaryModel();
        	model.setId(Long.parseLong(dictionaryId));
        	model.setCnName(cnName);
        	model.setEnName(enName);
        	SysDictionaryModel dictionaryModel = sysDictionaryMapper.selectByCnNameOrEnNameAndNotIncludedSelf(model);
        	if(Common.isEmpty(dictionaryModel))
        	{
        		 return true;
        	}
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * 系统字典更新保存
     */
    //@SystemControllerLog(description = "系统字典保存")
    @RequestMapping(value = "/systemDictionaryUpdateSave", method = RequestMethod.POST)
    public String systemDictionaryUpdateSave(
    		@RequestParam("detailName") String[] detailName, 
    		@RequestParam("detailValue") String[] detailValue,
    		@RequestParam("detailRemark") String[] detailRemark, Model model) throws Exception
    {
    	String dictionaryId = getPara("dictionaryId");
    	String cnName = getPara("cnName");
    	String enName = getPara("enName");
    	String flag = getPara("flag");
    	String description = getPara("description");
    	
    	SysDictionaryModel dictionaryModel = new SysDictionaryModel();
    	dictionaryModel.setId(Long.parseLong(dictionaryId));
    	dictionaryModel.setCnName(cnName);
    	dictionaryModel.setEnName(enName);
    	if(Common.isEmpty(flag))
    	{
    		flag = "0";
    	}
    	dictionaryModel.setFlag(flag);
    	dictionaryModel.setRemark(description);
    	dictionaryModel.setUpdateTime(new Date());
    	dictionaryModel.setUpdateUserId(getSessionUserid());
    	
    	SysDictionaryModel findModel = sysDictionaryMapper.selectByCnNameOrEnNameAndNotIncludedSelf(dictionaryModel);
    	if(Common.isEmpty(findModel))
    	{
    		systemDictionaryService.updateDictionaryAndDictionaryDetail(dictionaryModel, detailName, detailValue, detailRemark);
    	}
    	
		return "redirect:systemDictionaryIndex";
    }
    
    /**
     * 系统字典删除
     */
   //@SystemControllerLog(description = "系统字典与系统字典明细删除")
    @RequestMapping(value = "/systemDictionaryDelete", method = RequestMethod.POST)
    public String systemDictionaryDelete()
    {
        try
        {
        	String dictionaryId = getPara("dictionaryId");
        	if(Common.isNotEmpty(dictionaryId)){
        		systemDictionaryService.deleteDictionaryAndDictionaryDetail(dictionaryId);
        	}
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return "redirect:systemDictionaryIndex";
    }
    
    /**
     * 系统字典是否可编辑
     */
    @RequestMapping(value = "/systemDictionaryWhetherCanEdit", method = RequestMethod.GET)
    @ResponseBody
    public boolean systemDictionaryWhetherCanEdit()
    {
        try
        {
        	String dictionaryId = getPara("dictionaryId");
        	SysDictionaryModel dictionaryModel = sysDictionaryMapper.selectByPrimaryKey(Long.parseLong(dictionaryId));
        	if(dictionaryModel.getFlag().equals("1"))
        	{
        		 return true;
        	}
        	else
        	{
				return false;
			}
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
}

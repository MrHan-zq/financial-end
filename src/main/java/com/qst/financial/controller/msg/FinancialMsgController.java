package com.qst.financial.controller.msg;

import com.alibaba.fastjson.JSONObject;
import com.qst.financial.controller.base.BaseController;
import com.qst.financial.dao.mapper.base.SysResourcesMapper;
import com.qst.financial.dao.mapper.subject.FinancialMsgMapper;
import com.qst.financial.modle.base.SysResourcesModel;
import com.qst.financial.modle.base.SysUserModel;
import com.qst.financial.modle.msg.FinancialMsg;
import com.qst.financial.service.msg.FinacialMsgService;
import com.qst.financial.sql.C;
import com.qst.financial.sql.Method;
import com.qst.financial.sql.WherePrams;
import com.qst.financial.util.common.Common;
import com.qst.financial.util.tag.InnerPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资讯管理Controller
 */
@Controller
@RequestMapping("/msg")
public class FinancialMsgController extends BaseController{

	private Logger log=LoggerFactory.getLogger(FinancialMsgController.class);
	
	@Autowired
	private FinacialMsgService finacialMsgService;
	
	@Autowired
   	private SysResourcesMapper sysResourcesMapper;
	
	
	@Autowired
	private FinancialMsgMapper finacialMsgMapper;

	@Value("${cbs.Ip}")
	private String ip;

	@Value("${cbs.tempPath}")
	private String tempPath;

	@RequestMapping(value = "/msgIndex", method = RequestMethod.GET)
	public String msgIndex(String fatherId, String parentId,Model model) throws Exception{
		if (!Common.isEmpty(fatherId) && !Common.isEmpty(parentId))
        {
            HttpSession session = getSession();
            session.setAttribute("fatherMenuId", fatherId);
            session.setAttribute("menuId", parentId);
        }
	    return "admin/jsp/msg/msgIndex";
	}
	
    @RequestMapping(value = "/msgListAndButtonList", method = RequestMethod.GET)
    public String msgListAndButtonList(HttpServletRequest request, Model model,String title, String author)
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
            if(Common.isEmpty(pageNow)){
            	pageNow = "1";
            }
            if(Common.isEmpty(pageSize)){
            	pageSize = "10";
            }
            InnerPage innerPage = new InnerPage();
            FinancialMsg msgBean = new FinancialMsg();
            Method method = new  Method();
            WherePrams wherePrams = method.where("1", C.EQ, 1);
            if(!Common.isEmpty(title)){
            	wherePrams.and("title", C.LIKE, title);
            }
            if(!Common.isEmpty(author)){
            	wherePrams.and("author", C.LIKE, author);
            }
            innerPage.setDate(msgBean);
            int count=(int) finacialMsgService.count(wherePrams);
            initPage(request, Integer.parseInt(pageNow), Integer.parseInt(pageSize), count);
            String limit = getLimit(Integer.parseInt(pageNow), count);
            List<FinancialMsg> msgList = finacialMsgService.listPage(wherePrams, limit);
			model.addAttribute("msgList", msgList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return "admin/jsp/msg/msgListAndButtonList";
    }
    
	@RequestMapping(value = "/msgList", method = RequestMethod.GET)
    public String msgList(HttpServletRequest request, Model model,String title, String author){
		String veiw="admin/jsp/msg/msgList";
		try {
			 String pageNow = getPara("pageNow");
	            String pageSize = getPara("pageSize");
	            if(Common.isEmpty(pageNow)){
	            	pageNow = "1";
	            }
	            if(Common.isEmpty(pageSize)){
	            	pageSize = "10";
	            }
	            InnerPage innerPage = new InnerPage();
	            FinancialMsg msgBean = new FinancialMsg();
	            Method method = new  Method();
	            WherePrams wherePrams = method.where("1", C.EQ, 1);
	            if(!Common.isEmpty(title)){
	            	wherePrams.and("title", C.LIKE, title);
	            }
	            if(!Common.isEmpty(author)){
	            	wherePrams.and("author", C.LIKE, author);
	            }
	            innerPage.setDate(msgBean);
	            int count=(int) finacialMsgService.count(wherePrams);
	            initPage(request, Integer.parseInt(pageNow), Integer.parseInt(pageSize), count);
	            String limit = getLimit(Integer.parseInt(pageNow), count);
	            List<FinancialMsg> msgList = finacialMsgService.listPage(wherePrams, limit);
				model.addAttribute("msgList", msgList);
		} catch (Exception e) {
			log.info("=====" + e.getMessage());
		}
		return veiw;
    }
	
    @RequestMapping(value = "/addMsg", method = RequestMethod.GET)
    public String addMsg(){
		return "admin/jsp/msg/msgAddPage";
    }
	
	@RequestMapping(value = "/msgSave",method = RequestMethod.POST)
	@ResponseBody
    public int  msgSave(@RequestBody FinancialMsg msg){
		int flag = 0;
		try{
			 flag=finacialMsgService.addLocal(msg);
        }catch (Exception e){
        	log.info("====="+e.getMessage());
        }
		return flag;
    }
	
	/**
     * 编辑页面
     */
    @RequestMapping(value = "/msgEditPage", method = RequestMethod.GET)
    public String msgEditPage(Model model)
    {
        try{
        	String msgId = getPara("msgId");
        	FinancialMsg msg = finacialMsgMapper.selectByPrimaryKey(Long.parseLong(msgId));
        	model.addAttribute("msg", msg);
        }
        catch (Exception e){
            e.printStackTrace();
        }
		return "admin/jsp/msg/msgEditPage";
    }
    
    /**
     * 更新保存
     */
    @RequestMapping(value = "/msgUpdateSave", method = RequestMethod.POST)
	@ResponseBody
    public int  msgUpdateSave(@RequestBody FinancialMsg msg) throws Exception{
		int flag = 0;
		try{
			 flag=finacialMsgMapper.updateByPrimaryKeySelective(msg);
       }catch (Exception e){
       	log.info("====="+e.getMessage());
       }
		return flag;
    }
    
    @RequestMapping(value = "/msgDelete", method = RequestMethod.POST)
    public String msgDelete() {
        try{
        	String msgId = getPara("msgId");
        	if(Common.isNotEmpty(msgId)){
        		finacialMsgService.deletetMsg(msgId);
        	}
        }
        catch (Exception e){
            e.printStackTrace();
        }
    	return "redirect:msgIndex";
    }
    
	@RequestMapping(value="uploadImg",method=RequestMethod.POST)
	@ResponseBody
	public String uploadImg(@RequestParam MultipartFile file,HttpServletRequest request){
		String oldName=file.getOriginalFilename();
		String tempName = System.currentTimeMillis() + "";
		//以文件名命名的文件夹
		String tempFileDir = tempPath;
        File parentFileDir = new File(tempFileDir);
         //若不存在 就新建
        if (!parentFileDir.exists()) {
            parentFileDir.mkdirs();
        }
        //新文件名 获取当前名+文件后缀
   	 	String newName = tempName + oldName.substring(oldName.lastIndexOf("."));
   	 	//数据库img的src
   	 	String src=ip+"/images/"+newName;
   	 	try {
   	 		//存放文件  （文件名，文件）
			file.transferTo(new File(tempFileDir,newName));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	   	 Map<String,Object> map1 = new HashMap<String,Object>();
	     Map<String,Object> map2 = new HashMap<String,Object>();
	     map1.put("code",0);//0表示成功，1失败
	     map1.put("msg","上传成功");//提示消息
	     map1.put("data",map2);
	     map2.put("src",src);//图片url
	     map2.put("title",oldName);//图片名称，这个会显示在输入框里
	     String result = new JSONObject(map1).toString();
		 return result;
	}
}

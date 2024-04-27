package com.qst.financial.dao.mapper.base;

import com.qst.financial.modle.base.SysUserModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysUserMapper {
	
	public int deleteByPrimaryKey(Long id);

	public int insert(SysUserModel record);

	public  int insertSelective(SysUserModel record);

	public SysUserModel selectByPrimaryKey(Long id);

	public   int updateByPrimaryKeySelective(SysUserModel record);

	public   int updateByPrimaryKey(SysUserModel record);
    
	public SysUserModel selectByAccountName(String accountName);
    
	public List<Map<String, Object>> selectSysUserList(Map<String, String> map);
    
	public int selectSysUserCount(Map<String, String> map);
	
	public SysUserModel selectByAccountNameAndNotIncludedSelf(SysUserModel record);
	
	public int deleteByPrimaryKeyBatch(List<String> userId);
	
	public List<SysUserModel> selectSysUserByList(SysUserModel sysUser);
}
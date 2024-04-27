package com.qst.financial.dao.mapper.base;

import com.qst.financial.modle.base.SysUserActionModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserActionMapper {

	public int insert(SysUserActionModel record);

	public int insertSelective(SysUserActionModel record);

	public int deleteByUserId(Long userId);

	public int insertBatchUserAction(List<SysUserActionModel> list);

	public List<SysUserActionModel> selectByUserId(Long userId);
}
package com.qst.financial.dao.mapper.base;


import com.qst.financial.modle.base.PoModel;
import com.qst.financial.sql.WherePrams;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 公共数据库操作层
 * @author qst
 * @email 
 * @param <T> 实体PoModel类型
 * @param <PK> PoModel主键类型
 */
public interface BaseMapper <T extends PoModel, PK extends Serializable> {
	/**
     * 添加不为空的记录（只将不为空字段入库，效率高）
     * @param PoModel
     * @return 受改变的记录数
     */
    public int addLocal(T PoModel);
    public int addByMap(Class entityClass, Map<String, Object> map);
    /**
     * 记录添加（所有字段入库，效率中）
     * @param PoModel
     * @return
     */
    public int add(T PoModel);

    /**
     * 通过主键获取某个记录
     * @param id 主键
     * @return PoModel
     */
    public T get(Class c,PK id);

    /**
     * 通过主键获取某个字段的值
     * @param id
     * @param fileName
     * @return
     */
    public Serializable getField(Class c,PK id, String fileName);

    /**
     * 条件获取一条记录
     * @param t
     * @param 条件表达式
     * @return PoModel
     */
    public T get(Class c, WherePrams where);

    /**
     * 条件获取某个记录字段
     * @param where
     * @param fileName
     * @return
     */
    public Serializable getFile(Class c,WherePrams where, String fileName);

    /**
     * 条件查询列表
     * @param where 条件表达式
     * @return PoModel列表
     */
    public List<T> list(Class c,WherePrams where);

    /**
     * 查询某个字段列表
     * @param where 条件表达式
     * @param fileName 要查询的字段
     * @return
     */
    public Serializable[] listFile(Class c,WherePrams where, String fileName);

    /**
     * 查询某些字段
     * @param where 条件表达式
     * @param files 要查询的字段集
     * @return 查询的PoModel字段列表
     */
    public List<Map<String, Serializable>> listFiles(Class c,WherePrams where, String[] files);

    /**
     * 更新不为null的PoModel字段
     * @param PoModel
     * @return 受影响的行数
     */
    public int updateLocal(T PoModel);

    /**
     * 更新PoModel的所有字段
     * @param PoModel
     * @return 受影响的行数
     */
    public int update(T PoModel);

    /**
     * 条件更新不为null的字段
     * @param PoModel
     * @param 条件表达式
     * @return 受影响的行数
     */
    public int updateLocal(T PoModel, WherePrams where);

    /**
     * 条件更新所有字段
     * @param PoModel
     * @param 条件表达式
     * @return 受影响的行数
     */
    public int update(T PoModel, WherePrams where);

    /**
     * 删除某个记录
     * @param id 主键
     * @return 受影响的行数
     */
    public int del(Class c,PK id);

    /**
     * 条件删除某个记录
     * @param where 条件表达式
     * @return 受影响的行数
     */
    public int del(Class c,WherePrams where);

    /**
     * 自定义sql查询
     * @param PoModel 用于封装返回结果的Bean
     * @param sql 用于执行查询的Sql
     * @param args Sql占位付对应的参数
     * @return 结果集合
     */
    public List<Map<String, Object>> listBySql(Class c,String sql);

    /**
     * 执行自定义sql
     * @param sql 用于执行的Sql
     * @param args Sql占位付对应的参数
     * @return 受影响的行数
     */
    public int excuse(Class c,String sql);

    /**
     * 获取指定条件的记录数
     * @param where 条件表达式
     * @return 查询到的记录数
     */
    public long count(Class c,WherePrams where);

    /**
     * 获取对应表中的记录数
     * @return 表中的条数
     */
    public long size(Class c);

    /**
     * 是否存在字段相同的记录（ID以及不为空的字段除外）
     * @param PoModel 参照实体
     * @return
     */
    public boolean isExist(Class c,T PoModel);

    /**
     * 是否存在指定条件的记录
     * @param where 条件表达式
     * @return
     */
    public boolean isExist(Class c,WherePrams where);

    /**
     * 内查询
     * @param fileName 用于内查询的字段
     * @param values 字段的值
     * @return 查询到的结果集
     */
    public List<T> in(Class c,String fileName, Serializable[] values);

    /**
     * 获得下一个序列的值
     * @return
     */
    public long nextId(Class c);

	public List<T> listPage(Class c, WherePrams where, String limit);


}

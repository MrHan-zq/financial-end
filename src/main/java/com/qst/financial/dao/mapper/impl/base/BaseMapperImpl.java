package com.qst.financial.dao.mapper.impl.base;

import com.qst.financial.core.FieldName;
import com.qst.financial.core.GenericsUtils;
import com.qst.financial.core.Pram;
import com.qst.financial.dao.mapper.base.BaseMapper;
import com.qst.financial.modle.base.PoModel;
import com.qst.financial.sql.C;
import com.qst.financial.sql.Method;
import com.qst.financial.sql.SqlUtil;
import com.qst.financial.sql.WherePrams;
import com.qst.financial.util.StringConverting;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Repository
public class BaseMapperImpl<T extends PoModel, PK extends Serializable> implements BaseMapper<T, PK> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    private Class<T> entityClass;
    private Class<T> entityClasses;

    private String pkName;                  //主键字段

    private String idName;                  //对应id名称

    private String seq;                     //当前主键

    private String tableName;

    private List<Pram> sqlParms;


    //private List<Pram> selectSqlParms;

    private SqlUtil<T> sqlUtil;
    @SuppressWarnings("unchecked")
    public BaseMapperImpl() {
        super();
        this.sqlUtil = new SqlUtil<T>();

        this.entityClass = (Class<T>) GenericsUtils.getSuperClassGenricType(this.getClass());

        this.sqlParms = this.sqlUtil.getPramList(this.entityClass);

       // this.selectSqlParms = this.sqlUtil.getPramListOfSelect(this.entityClass);

        //this.tableName = this.sqlUtil.getTableName(this.entityClass);

        //习惯统一用‘id’做约束了，所以这里我给固定死了，不想固定的话可以修改这里
        this.pkName = "id";

        this.idName = "id";

        this.seq = "id";

    }

    /**
     * 只增加值不为空的
     */
    @Override
    public int addLocal(T po) {
        // TODO Auto-generated method stub
    	tableName = sqlUtil.getTableName(po);
        String sql = "insert into " + tableName + " (";
        String prams = "";
        String values = "";
       
        List<Pram> pramList = SqlUtil.getPramListofStatic(po);
        int j=0;
        for (int i = 0; i < pramList.size(); i++) {
            if (pramList.get(i).getValue() == null || pramList.get(i).getValue().equals("") || pramList.get(i).getValue().equals(null)) {
                continue;
            }else{
            	if(j>0){
            		if(i < pramList.size() ){
                        prams += ",";
                        values += ",";
                    }
            	}
            	prams += pramList.get(i).getFile();
                if (pramList.get(i).getValue() == null) {
                    values += "null";
                }else{
                    values += "'" + pramList.get(i).getValue() + "'";
                }
                j=j+1;
            }
        }
        sql += prams + ") values (" + values +");";
        //SqlUtil.setFileValue(po, "id", nextId());
        logger.debug(sql);
        return sqlSessionTemplate.insert("addLocal", sql);

    }
    @Override
    public int addByMap(Class c,Map<String,Object> map){
    	tableName = sqlUtil.getTableName(c);
        String sql = "insert into " + tableName + " (";
        String prams = "";
        String values = "";
        for (Map.Entry<String, Object> entry : map.entrySet()) {
        	if(prams==""){
        		prams=entry.getKey();
        		values=(String) entry.getValue();
        	}
        	prams=prams+","+entry.getKey();
        	values=values+","+entry.getValue();
        }
        sql += prams + ") values (" + values +");";
        logger.debug(sql);
    	return sqlSessionTemplate.insert("addByMap", sql);
    }
    @Override
    public int add(T po) {
        // TODO Auto-generated method stub
    	tableName = sqlUtil.getTableName(po);
        String sql = "insert into " + tableName + " (";
        String prams = "";
        String values = "";
        String strPo=po.toString();
        //entityClass = (Class<T>) GenericsUtils.getSuperClassGenricType(this.getClass());
        List<Pram> pramList = SqlUtil.getPramListofStatic(po);

        for (int i = 0; i < pramList.size(); i++) {
        	if(i>0){
        		if(i < pramList.size() ){
                    prams += ",";
                    values += ",";
                }
        	}
            prams += pramList.get(i).getFile();
            if (pramList.get(i).getValue() == null) {
                values += "null";
            }else{
                values += "'" + pramList.get(i).getValue() + "'";
            }

        }
        sql += prams + ") values (" + values +");";
        //SqlUtil.setFileValue(po, "id", nextId());
        return sqlSessionTemplate.insert("add", sql);
    }

    @Override
    public T get(Class c,PK id) {
        // TODO Auto-generated method stub
    	List<Pram> selectSqlParms=sqlUtil.getPramListOfSelect(c);
    	tableName = sqlUtil.getTableName(c);
        String sql = "select ";
        for (int i = 0; i < selectSqlParms.size(); i++) {
            sql += selectSqlParms.get(i).getFile();
            if(i < selectSqlParms.size() -1){
                sql += ",";
            }else{
                sql += " ";
            }
        }
        sql += " from " + tableName + " where id='" + id + "';";
        Map<String, Object> resultMap = sqlSessionTemplate.selectOne(
                    "getById", sql);

        return handleResult(c,resultMap, this.entityClass);
    }

    @Override
    public Serializable getField(Class c,PK id, String fileName) {
        // TODO Auto-generated method stub
    	tableName = sqlUtil.getTableName(c);
        String field = fileName;
        String tabField = "";
        Field f = sqlUtil.getField(this.entityClass, fileName);
        if (null == f) {
            logger.error("查询字段失败(无法找到" + this.entityClass + "中的" + fileName + "字段)");
        }
        FieldName annotation = f.getAnnotation(FieldName.class);
        if (null == annotation) {
            tabField = sqlUtil.toTableString(fileName) + " as " + fileName;
        }else{
            tabField = annotation.name() + " as " + fileName;
        }

        String sql = "select ";
        sql += tabField + " from " + tableName + " where id='" + id + "';";
        Map<String, Object> resultMap = sqlSessionTemplate.selectOne(
                "getFieldById", sql);
        return (Serializable) resultMap.get(fileName);
    }

    @Override
    public T get(Class c, WherePrams where) {
    	List<Pram> selectSqlParms=sqlUtil.getPramListOfSelect(c);
    	tableName = sqlUtil.getTableName(c);
        // TODO Auto-generated method stub
        String sql = "select ";
        for (int i = 0; i < selectSqlParms.size(); i++) {
            sql += selectSqlParms.get(i).getFile();
            if(i < selectSqlParms.size() -1){
                sql += ",";
            }else{
                sql += " ";
            }
        }
        sql += " from " + tableName + where.getWherePrams() + ";";

        Map<String, Object> resultMap = sqlSessionTemplate.selectOne(
                "getByParm", sql);

        return handleResult(c,resultMap, this.entityClass);
    }

    @Override
    public Serializable getFile(Class c,WherePrams where, String fileName) {
    	tableName = sqlUtil.getTableName(c);
        // TODO Auto-generated method stub
        String field = fileName;
        String tabField = "";
        Field f = sqlUtil.getField(this.entityClass, fileName);
        if (null == f) {
            logger.error("查询字段失败(无法找到" + this.entityClass + "中的" + fileName + "字段)");
        }
        FieldName annotation = f.getAnnotation(FieldName.class);
        if (null == annotation) {
            tabField = sqlUtil.toTableString(fileName) + " as " + fileName;
        }else{
            tabField = annotation.name() + " as " + fileName;
        }

        String sql = "select ";
        sql += tabField + " *from " + tableName + where.getWherePrams() + ";";
        Map<String, Object> resultMap = sqlSessionTemplate.selectOne(
                "getFieldByParm", sql);
        return (Serializable) resultMap.get(fileName);
    }
    /**
     * 统一分页
     */
    @Override
    public List<T> listPage(Class c,WherePrams where,String limit) {
    	List<Pram> selectSqlParms=sqlUtil.getPramListOfSelect(c);
    	tableName = sqlUtil.getTableName(c);
        String sql = "select ";
        for (int i = 0; i < selectSqlParms.size(); i++) {
            sql += selectSqlParms.get(i).getFile();
            if(i < selectSqlParms.size() -1){
                sql += ",";
            }else{
                sql += " ";
            }
        }
        
        if(where!=null){
        	sql += " from " + tableName + where.getWherePrams() +" "+limit+ ";";
        }else{
        	sql += " from " + tableName  +" "+limit+ ";";
        }
        List<Map<String, Object>> selectList = sqlSessionTemplate.selectList("selectList", sql);

        List<T> list = new ArrayList<>();
        for (Map<String, Object> map : selectList) {
            T t = handleResult(c,map, this.entityClass);
            list.add(t);
        }

        return list;
    }
    @Override
    public List<T> list(Class c,WherePrams where) {
        // TODO Auto-generated method stub
    	List<Pram> selectSqlParms=sqlUtil.getPramListOfSelect(c);
    	tableName = sqlUtil.getTableName(c);
        String sql = "select ";
        for (int i = 0; i < selectSqlParms.size(); i++) {
            sql += selectSqlParms.get(i).getFile();
            if(i < selectSqlParms.size() -1){
                sql += ",";
            }else{
                sql += " ";
            }
        }
        if(where!=null){
        	sql += " from " + tableName + where.getWherePrams() + ";";
        }else{
        	sql += " from " + tableName + ";";
        }
        List<Map<String, Object>> selectList = sqlSessionTemplate.selectList("selectList", sql);

        List<T> list = new ArrayList<>();
        for (Map<String, Object> map : selectList) {
            T t = handleResult(c,map, this.entityClass);
            list.add(t);
        }

        return list;

    }
    
    @Override
    public Serializable[] listFile(Class c,WherePrams where, String fileName) {
        // TODO Auto-generated method stub
    	tableName = sqlUtil.getTableName(c);
        String field = fileName;
        String tabField = "";
        Field f = sqlUtil.getField(this.entityClass, fileName);
        if (null == f) {
            logger.error("查询指定字段集失败(无法序列化" + this.entityClass + "中的" + fileName + "字段)");
        }
        FieldName annotation = f.getAnnotation(FieldName.class);
        if (null == annotation) {
            tabField = sqlUtil.toTableString(fileName) + " as " + fileName;
        }else{
            tabField = annotation.name() + " as " + fileName;
        }

        String sql = "select ";
        sql += tabField + " *from " + tableName + where.getWherePrams() + ";";
        List<Map<String, Object>> resultMap = sqlSessionTemplate.selectList("selectListField", sql);

        Serializable[] fields = new Serializable[resultMap.size()];

        for (int i = 0; i < resultMap.size(); i++) {
            if (null != resultMap.get(i)) {
                fields[i] =(Serializable) resultMap.get(i).get(fileName);
            }
        }

        return fields;
    }

    @Override
    public List<Map<String, Serializable>> listFiles(Class c,WherePrams where, String[] files) {
        // TODO Auto-generated method stub
    	tableName = sqlUtil.getTableName(c);
        String tabField = "";
        int index = 1;
        for (String field : files) {
            Field f = sqlUtil.getField(this.entityClass, field);
            if (null == f) {
                logger.error("查询指定字段集失败(无法序列化" + this.entityClass + "中的" + field + "字段)");
            }
            FieldName annotation = f.getAnnotation(FieldName.class);
            if (null == annotation) {
                tabField += sqlUtil.toTableString(field) + " as " + field;
            }else{
                tabField += annotation.name() + " as " + field;
            }
            if (index < files.length) {
                tabField += ",";
            }

            index ++;
        }

        String sql = "select ";
        sql += tabField + " *from " + tableName + where.getWherePrams() + ";";
        List<Map<String, Serializable>> resultMap = sqlSessionTemplate.selectList("selectListField", sql);

        return resultMap;
    }

    @Override
    public int updateLocal(T po) {
        // TODO Auto-generated method stub
    	tableName = sqlUtil.getTableName(po);
        Serializable id = sqlUtil.getFileValue(po, "id");

        if(null == id){
            return 0;
        }
        String pramsValues = "";
        String values = "";
        String sql = "update " + tableName + " set ";
        int j=0;
        //List<Pram> prams = sqlUtil.getPramList(po);
        List<Pram> pramList = SqlUtil.getPramListofStatic(po);
        for (int i = 0; i < pramList.size(); i++) {
            if (pramList.get(i).getValue() == null || pramList.get(i).getValue().equals("") || pramList.get(i).getValue().equals(null)) {
                continue;
            }else{
            	if(j>0){
            		if(i < pramList.size() ){
            			pramsValues=pramsValues+",";
                    }
            	}
            	pramsValues=pramsValues+pramList.get(i).getFile()+"=";
            	pramsValues += "'" + pramList.get(i).getValue() + "'";
            	/*prams += pramList.get(i).getFile();
                if (pramList.get(i).getValue() == null) {
                    values += "null";
                }else{
                    values += "'" + pramList.get(i).getValue() + "'";
                }*/
                j=j+1;
            }
        }
        /*for (int i = 0; i < prams.size(); i++) {
            if(null != prams.get(i).getValue()){
                sql += prams.get(i).getFile() + "=";
                Object value = prams.get(i).getValue();
                if (value instanceof byte[] ) {
                    sql += "'" + new String((byte[]) value) + "'";
                }else if(value instanceof String){
                    sql += "'" + value + "'";
                }else{
                    sql += value ;
                }

                //sql += prams.get(i).getFile() + "='" + prams.get(i).getValue() + "'";
                if (i < prams.size() -1) {
                    sql += ",";
                }
            }
        }*/
        sql =sql+ pramsValues+" where id='" + id +"';";

        return sqlSessionTemplate.update("updateLocal", sql);
    }

    @Override
    public int update(T po) {
        // TODO Auto-generated method stub
    	tableName = sqlUtil.getTableName(po);
        Serializable id = sqlUtil.getFileValue(po, "id");
        String pramsValues="";
        if(null == id){
            return 0;
        }
        String sql = "update " + tableName + " set ";

       // List<Pram> prams = sqlUtil.getPramList(po);
        List<Pram> prams = SqlUtil.getPramListofStatic(po);
        List<Pram> pramList = SqlUtil.getPramListofStatic(po);
        for (int i = 0; i < pramList.size(); i++) {
            if(i>0){
            	if(i < pramList.size() ){
            		pramsValues=pramsValues+",";
            	}
            }
            pramsValues=pramsValues+pramList.get(i).getFile()+"=";
            if (pramList.get(i).getValue() == null || pramList.get(i).getValue().equals("") || pramList.get(i).getValue().equals(null)) {
            	pramsValues+=null;
            }else{
            	pramsValues += "'" + pramList.get(i).getValue() + "'";
            }
            //pramsValues=pramsValues+pramList.get(i).getFile()+"="+"'"+pramList.get(i).getValue()+"'";
            	/*prams += pramList.get(i).getFile();
                if (pramList.get(i).getValue() == null) {
                    values += "null";
                }else{
                    values += "'" + pramList.get(i).getValue() + "'";
                }*/
        }
       /* for (int i = 0; i < prams.size(); i++) {
            if(null != prams.get(i).getValue()){
                sql += prams.get(i).getFile() + "=";
                Object value = prams.get(i).getValue();
                if (value instanceof byte[] ) {
                    sql += "'" + new String((byte[]) value) + "'";
                }else if(value instanceof String){
                    sql += "'" + value + "'";
                }else{
                    sql += value ;
                }
                //sql += prams.get(i).getFile() + "='" + prams.get(i).getValue() + "'";
                if (i < prams.size() -1) {
                    sql += ",";
                }
            }else{
                sql += prams.get(i).getFile() + "=null";
                if (i < prams.size() -1) {
                    sql += ",";
                }
            }
        }*/
        sql =sql+pramsValues+ " where id='" + id +"';";

        return sqlSessionTemplate.update("update", sql);
    }

    @Override
    public int updateLocal(T po, WherePrams where) {
        // TODO Auto-generated method stub
    	tableName = sqlUtil.getTableName(po);
        String sql = "update " + tableName + " set ";
        List<Pram> prams = sqlUtil.getPramList(po);
        for (int i = 0; i < prams.size(); i++) {
            if(null != prams.get(i).getValue()){
                sql += prams.get(i).getFile() + "=";
                Object value = prams.get(i).getValue();
                if (value instanceof byte[] ) {
                    sql += "'" + new String((byte[]) value) + "'";
                }else if(value instanceof String){
                    sql += "'" + value + "'";
                }else{
                    sql += value ;
                }
//              sql += prams.get(i).getFile() + "='" + prams.get(i).getValue() + "'";
                if (i < prams.size() -1) {
                    sql += ",";
                }
            }
        }
        sql += where.getWherePrams() +"';";

        return sqlSessionTemplate.update("updateLocalByPram", sql);

    }

    @Override
    public int update(T po, WherePrams where) {
        // TODO Auto-generated method stub
    	tableName = sqlUtil.getTableName(po);
        String sql = "update " + tableName + " set ";
        Object[] o = new Object[sqlParms.size()];
        for (int i = 0; i < sqlParms.size(); i++) {
            if(null != sqlParms.get(i).getValue()){
                sql += sqlParms.get(i).getFile() + "=" + sqlParms.get(i).getValue();
                if (i < sqlParms.size() -1) {
                    sql += ",";
                }
            }else{
                sql += sqlParms.get(i).getFile() + "=null";
                if (i < sqlParms.size() -1) {
                    sql += ",";
                }
            }
        }
        sql += where.getWherePrams() + "';";

        return sqlSessionTemplate.update("updateByPram", sql);

    }

    @Override
    public int del(Class c,PK id) {
        // TODO Auto-generated method stub
    	tableName = sqlUtil.getTableName(c);
        String sql = "delete from " + tableName + " where id=" + id;

        return sqlSessionTemplate.delete("deleteById", sql);
    }

    @Override
    public int del(Class c,WherePrams where) {
        // TODO Auto-generated method stub
    	Class clz = this.getClass();
    	tableName = sqlUtil.getTableName(clz);
        String sql = "delete from " + tableName + where.getWherePrams();

        return sqlSessionTemplate.delete("deleteByparm", sql);
    }

    @Override
    public List<Map<String, Object>> listBySql(Class c,String sql) {
        // TODO Auto-generated method stub
    	tableName = sqlUtil.getTableName(c);
        List<Map<String, Object>> selectList = sqlSessionTemplate.selectList("selectBySql", sql);
        return selectList;
    }

    @Override
    public int excuse(Class c,String sql) {
        // TODO Auto-generated method stub
        return sqlSessionTemplate.update("excuse", sql);
    }

    @Override
    public long count(Class c,WherePrams where) {
        // TODO Auto-generated method stub
    	tableName = sqlUtil.getTableName(c);
        String sql = "select count(*) from ";

        sql += tableName + where.getWherePrams();

        long count = sqlSessionTemplate.selectOne("selectCountByParm", sql);

        return count;
    }

    @Override
    public long size(Class c) {
        // TODO Auto-generated method stub
    	tableName = sqlUtil.getTableName(c);
        String sql = "select count(*) from " + tableName;
        long count = sqlSessionTemplate.selectOne("selectCount", sql);
        return count;
    }

    @Override
    public boolean isExist(Class c,T po) {
        // TODO Auto-generated method stub
    	
        WherePrams wherePrams = Method.createDefault();

        List<Pram> list = SqlUtil.getPramListofStatic(po);

        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                wherePrams = Method.where(list.get(i).getFile(), C.EQ, (Serializable)list.get(i).getValue());
            }else{
                wherePrams.and(list.get(i).getFile(), C.EQ, (Serializable)list.get(i).getValue());
            }
        }


        return count(c,wherePrams) > 0;
    }

    @Override
    public boolean isExist(Class c,WherePrams where) {
        // TODO Auto-generated method stub
        return count(c,where) > 0;
    }

    @Override
    public List<T> in(Class c,String fileName, Serializable[] values) {
        // TODO Auto-generated method stub
    	tableName = sqlUtil.getTableName(c);
    	List<Pram> selectSqlParms=sqlUtil.getPramListOfSelect(c);
        String sql = "select ";
        for (int i = 0; i < sqlParms.size(); i++) {
            sql += selectSqlParms.get(i).getFile();
            if(i < selectSqlParms.size() -1){
                sql += ",";
            }else{
                sql += " ";
            }
        }
        sql += " from " + tableName + " where " + fileName + " in ";
        String value = "(";
        for(int i = 0; i < values.length; i++){
            if(i < values.length -1){
                value += values[i] + ","; 
            }else{
                value += values[i] + ");"; 
            }
        }
        sql += value;

        List<Map<String, Object>> selectList = sqlSessionTemplate.selectList("selectIn", sql);

        List<T> list = new ArrayList<>();
        for (Map<String, Object> map : selectList) {
            T t = handleResult(c,map, this.entityClass);
            list.add(t);
        }

        return list;
    }

    private T handleResult(Class c,Map<String, Object> resultMap, Class<T> tClazz) {
    	tableName = sqlUtil.getTableName(c);
        if (null == resultMap) {
            return null;
        }
        T t = null;
        try {
            t = (T) c.newInstance();
        } catch (InstantiationException e) {
            logger.error("/********************************");
            logger.error("实例化Bean失败(" + this.entityClass + ")!"
                    + e.getMessage());
            logger.error("/********************************");
        } catch (IllegalAccessException e) {
            logger.error("/********************************");
            logger.error("实例化Bean失败(" + this.entityClass + ")!"
                    + e.getMessage());
            logger.error("/********************************");
        }
        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            String key = entry.getKey();
            key=key.toLowerCase();
            key= StringConverting.camelName(key);
            Serializable val = (Serializable) entry.getValue();
            try {
                SqlUtil.setFileValue(t, key, val);
            } catch (Exception e) {
                // TODO: handle exception
                logger.error("/********************************");
                logger.error("/实例化Bean失败(" + this.entityClass + ")不能赋值到字段(" + key + "):"
                        + e.getMessage());
                logger.error("/********************************");
            }
        }
        return t;
    }

    /**
     * 获取某表的下一个Id
     */
    public long nextId(Class c){
    	tableName = sqlUtil.getTableName(c);
        String sql = "SELECT auto_increment FROM information_schema.`TABLES` WHERE TABLE_NAME='" + tableName + "'";
        Long idVal = sqlSessionTemplate.selectOne("fetchSeqNextval", sql);
        if (null == idVal) {
            logger.error("/********************************");
            logger.error("/获取id失败，" + tableName + "表异常。请检查是否存在或者配为自增主键");
            logger.error("/********************************");
            return 0;
        }
        return idVal;

    }

}

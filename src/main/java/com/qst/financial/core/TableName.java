package com.qst.financial.core;

import java.lang.annotation.*;

/**
 * 标识一个实体类对应数据库表的表名
 * 拥有该注解的PO类执行增删改差的时候将优先使用该注解内的值
 * @author qst
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TableName {
	String name();
}

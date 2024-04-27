package com.qst.financial.mybatis;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;


/**
 * mybatis配置类
 * @author qst
 *
 */
@Configuration
@EnableTransactionManagement
public class MybatisConfig implements TransactionManagementConfigurer{
	private static Logger log=LoggerFactory.getLogger(MybatisConfig.class);
  
	@Value("${mybatis.typeAliasesPackage}")
	private String typeAliasesPackage;
	@Value("${mybatis.xmlResources}")
	private String xmlResources;
	@Resource(name = "dataSource")
    DataSource dataSource;
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean() {
			
	        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
	        bean.setDataSource(dataSource);
	        bean.setTypeAliasesPackage(typeAliasesPackage);
	        System.out.println(typeAliasesPackage);

	        //分页插件,插件无非是设置mybatis的拦截器
	        /*PageHelper pageHelper = new PageHelper();
	        Properties properties = new Properties();
	        properties.setProperty("reasonable", "true");
	        properties.setProperty("supportMethodsArguments", "true");
	        properties.setProperty("returnPageInfo", "check");
	        properties.setProperty("params", "count=countSql");
	        pageHelper.setProperties(properties);

	        //添加插件
	        bean.setPlugins(new Interceptor[]{pageHelper});*/

	        //添加XML目录
	        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	        try {
	            //设置xml扫描路径
	            bean.setMapperLocations(resolver.getResources(xmlResources));
	            log.info("===========================mybatis初始化成功");
	            return bean.getObject();
	        } catch (Exception e) {
	            throw new RuntimeException("sqlSessionFactory init fail",e);
	        }
	    }

	    /**
	     * 用于实际查询的sql工具,传统dao开发形式可以使用这个,基于mapper代理则不需要注入
	     * @param sqlSessionFactory
	     * @return
	     */
	    @Bean(name = "sqlSessionTemplate")
	    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
	        return new SqlSessionTemplate(sqlSessionFactory);
	    }
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		// TODO Auto-generated method stub
		return null;
	}
	
}



package com.qst.financial.database;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.sql.SQLException;

@Configuration
public class DataSourcesConfig {
	private static Logger log=LoggerFactory.getLogger(DataSourcesConfig.class);
	@Value("${jdbc.user}")  
    private String user;    
    @Value("${jdbc.password}")   
    private String password;
    @Value("${jdbc.driverClass}")  
    private String driverClass;    
    @Value("${jdbc.url}")   
    private String url; 
    @Value("${jdbc.maxActive}")  
    private Integer maxActive;    
    @Value("${jdbc.initialSize}")   
    private Integer initialSize;  
    @Value("${jdbc.minIdle}")  
    private Integer minIdle;    
    @Value("${jdbc.maxWai}")   
    private Integer maxWai;  
    @Value("${jdbc.timeBetweenEvictionRunsMillis}")  
    private Integer timeBetweenEvictionRunsMillis;    
    @Value("${jdbc.minEvictableIdleTimeMillis}")   
    private Integer minEvictableIdleTimeMillis;  
    @Value("${jdbc.validationQuery}")  
    private String validationQuery;    
    @Value("${jdbc.testWhileIdle}")   
    private boolean testWhileIdle;  
    @Value("${jdbc.testOnBorrow}")  
    private boolean testOnBorrow;    
    @Value("${jdbc.testOnReturn}")   
    private boolean testOnReturn;  
    @Value("${jdbc.poolPreparedStatements}")  
    private boolean poolPreparedStatements;    
    @Value("${jdbc.maxOpenPreparedStatements}")   
    private Integer maxOpenPreparedStatements;  
    @Value("${druid.druid}")   
    private String druid;  
    @Value("${druid.allow}")   
    private String allow;  
    @Value("${druid.deny}")   
    private String deny;  
    @Value("${druid.loginUsername}")   
    private String loginUsername;  
    @Value("${druid.loginPassword}")   
    private String loginPassword;  
    @Value("${druid.urlPatterns}")   
    private String urlPatterns;  
    @Value("${druid.initParameter}")   
    private String initParameter;  
	 /**
     * druid初始化
     * @return
     * @throws SQLException
     */
    @Primary //默认数据源
    @Bean(name = "dataSource",destroyMethod = "close")
    public DruidDataSource Construction() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        log.info("==========================user:"+user);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClass);
        //配置最大连接
        dataSource.setMaxActive(maxActive);
        //配置初始连接
        dataSource.setInitialSize(initialSize);
        //配置最小连接
        dataSource.setMinIdle(minIdle);
        //连接等待超时时间
        dataSource.setMaxWait(maxWai);
        //间隔多久进行检测,关闭空闲连接
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        //一个连接最小生存时间
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        //用来检测是否有效的sql
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        //打开PSCache,并指定每个连接的PSCache大小
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxOpenPreparedStatements(maxOpenPreparedStatements);
        //配置sql监控的filter
        //dataSource.setFilters("stat,wall,log4j");
        dataSource.setFilters("stat,log4j");
        try {
        	log.info("=====================数据库初始化成功");
            dataSource.init();
        } catch (SQLException e) {
            throw new RuntimeException("druid datasource init fail");
        }
        log.info("===========================初始化成功");
        return dataSource;
    }
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings(druid);
        reg.addInitParameter("allow", allow);
        reg.addInitParameter("deny",deny);
        reg.addInitParameter("loginUsername", loginUsername);
        reg.addInitParameter("loginPassword", loginPassword);
        return reg;
    }

    /**
     * druid监控过滤
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns(urlPatterns);
        filterRegistrationBean.addInitParameter("exclusions", initParameter);
        return filterRegistrationBean;
    }
}




package com.qst.financial.interceptor;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebMvcConfigurer extends WebMvcConfigurerAdapter{
	@Value("${interceptor.configs}")
	private String configs;
	@Value("${interceptor.excludeUrl}")
	private String excludeUrl;
	
	@Bean
    public HandlerInterceptor getMyInterceptor(){
        return new MyInterceptor();
    }
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(getMyInterceptor()).addPathPatterns(configs)
	    .excludePathPatterns("/MP_verify_zARiL3syFOvleDge.txt","/wxPay/**","/wx/**","/about","/static/**","/index/*","/page/html/index.html","/MP_verify_zARiL3syFOvleDge","/websocket","/configuration/security","/configuration/ui","/v2/api-docs","swagger-ui.html","/wxchat","/index","/admin/pc/login","/admin/pc/toLogin","/swagger-*","/report/ss","/api/*/*");

        super.addInterceptors(registry); 
    }
	/*@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/page/static/");
		super.addResourceHandlers(registry);
	}*/
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
          registry.addResourceHandler("/webjars/**")
                 .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
}

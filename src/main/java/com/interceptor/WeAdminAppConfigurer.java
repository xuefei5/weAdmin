package com.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @des    自定义应用配置
 * @author yangsheng
 *
 */
@Configuration
public class WeAdminAppConfigurer extends WebMvcConfigurerAdapter{
	
	/*将拦截器注入成bean*/
	@Bean
    public HandlerInterceptor getLoginInterceptor(){
        return (HandlerInterceptor) new LoginInterceptor();
    }
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		
		//拦截器可以配置多个，设置登录拦截，addPathPatterns 用于添加拦截规则，excludePathPatterns 用户排除拦截
		registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/**");
		//registry.addInterceptor(getLogWriteInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
        
    }
}

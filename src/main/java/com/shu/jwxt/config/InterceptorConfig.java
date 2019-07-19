package com.shu.jwxt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yang
 * @date 2019/7/19 13:16
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private final AccessInterceptor accessInterceptor;
//    private final LoginInterceptor loginInterceptor;

    public InterceptorConfig(AccessInterceptor accessInterceptor) {
        this.accessInterceptor = accessInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/register");
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/login");
    }
}

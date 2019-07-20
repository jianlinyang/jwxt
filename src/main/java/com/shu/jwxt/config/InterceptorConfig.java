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
    private final AdminInterceptor adminInterceptor;
    private final AccessInterceptor accessInterceptor;
    private final LoginInterceptor loginInterceptor;

    public InterceptorConfig(AccessInterceptor accessInterceptor, LoginInterceptor loginInterceptor, AdminInterceptor adminInterceptor) {
        this.accessInterceptor = accessInterceptor;
        this.loginInterceptor = loginInterceptor;
        this.adminInterceptor = adminInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessInterceptor).addPathPatterns("/**").excludePathPatterns("/login", "/register","/admin/**");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/login", "/register");
        registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin/login");
    }
}

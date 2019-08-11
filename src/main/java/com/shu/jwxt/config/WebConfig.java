package com.shu.jwxt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yang
 * @date 2019/7/19 13:16
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final AdminInterceptor adminInterceptor;
    private final AccessInterceptor accessInterceptor;
    private final LoginInterceptor loginInterceptor;

    public WebConfig(AccessInterceptor accessInterceptor, LoginInterceptor loginInterceptor, AdminInterceptor adminInterceptor) {
        this.accessInterceptor = accessInterceptor;
        this.loginInterceptor = loginInterceptor;
        this.adminInterceptor = adminInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessInterceptor).addPathPatterns("/**").excludePathPatterns("/login", "/register","/admin/**","/swagger-ui.html");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/login", "/register");
        registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin/login","/swagger-ui.html");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //添加映射路径
        registry.addMapping("/**")
                //放行哪些原始域
                .allowedOrigins("http://106.14.183.86:9001")
                //是否发送Cookie信息
                .allowCredentials(true)
                //放行哪些原始域(请求方式)
                .allowedMethods("GET","POST", "PUT", "DELETE")
                //放行哪些原始域(头部信息)
                .allowedHeaders("*")
                //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
                .exposedHeaders("access-control-allow-headers", "access-control-allow-methods","access-control-allow-origin",
                        "access-control-max-age",
                        "X-Frame-Options");
    }
}

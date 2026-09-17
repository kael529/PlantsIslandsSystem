package com.zhiyu.plantsislandssystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web配置类,用于配置Spring MVC的拦截器等Web相关功能
 * 实现WebMvcConfigurer接口,自定义Web配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * JWT拦截器,用于处理请求的JWT认证
     */
    @Autowired
    private JwtInterceptor jwtInterceptor;

    /**
     * 添加拦截器到Spring MVC的拦截器注册表中
     * @param registry 拦截器注册表,用于注册和配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加JWT拦截器,并配置拦截路径和排除路径
        registry.addInterceptor(jwtInterceptor)
                // 拦截所有请求路径
                .addPathPatterns("/**")
                // 排除不需要JWT认证的路径，包括用户登录、注册和测试相关接口
                .excludePathPatterns(
                        "/user/login",         // 用户登录接口
                        "/user/register",     // 用户注册接口
                        "/test/**",          // 所有测试相关接口
                        "/recognition/**"   // 放行植物识别接口

                );
    }
}

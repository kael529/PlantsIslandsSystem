package com.zhiyu.plantsislandssystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置
 * 允许前端（uni-app / Vue3）调用后端接口
 */

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")     //对所有接口生效
                .allowedOriginPatterns("*")      //允许所有域名（前端地址）访问后端
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")     //允许前端使用这些 HTTP 方法
                .allowedHeaders("*")       //允许前端携带任何请求头
                .allowCredentials(true)   //允许前端携带 Cookie 或认证信息
                .maxAge(3600);      //预检请求的结果缓存 3600 秒
    }
}

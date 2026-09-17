package com.zhiyu.plantsislandssystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate配置类
 * 用于配置和提供RestTemplate Bean实例
 * RestTemplate是Spring框架提供的用于执行HTTP请求的同步客户端工具
 */
@Configuration
public class RestTemplateConfig {

    /**
     * 创建并配置RestTemplate Bean
     * 该方法将RestTemplate实例注册为Spring容器中的一个Bean
     * 其他组件可以通过依赖注入来使用这个RestTemplate实例
     *
     * @return 配置好的RestTemplate实例
     */
    @Bean
    public RestTemplate restTemplate(){
        //创建SimpleClientHttpRequestFactory实例,用于配置HTTP请求的底层工厂
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        //设置连接超时时间为5000毫秒（5秒）
        factory.setConnectTimeout(5000);
        //设置读取超时时间为30000毫秒（30秒）
        factory.setReadTimeout(30000);
        //创建并返回一个配置好的RestTemplate实例
        return new RestTemplate(factory);
    }
}

package com.zhiyu.plantsislandssystem.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 密码工具类,提供密码加密和校验功能
 * 使用了Lombok的@RequiredArgsConstructor注解,自动生成final字段的构造器
 */

@Component
@RequiredArgsConstructor
public class PasswordUtils {

    private final PasswordEncoder passwordEncoder;

    //加密密码
    public String encode(String rawPassword){
        return passwordEncoder.encode(rawPassword);
    }

    //校验密码
    public boolean matches(String rawPassword, String encodePassword){
        return passwordEncoder.matches(rawPassword, encodePassword);
    }
}

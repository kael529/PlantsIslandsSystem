package com.zhiyu.plantsislandssystem.config;

import com.zhiyu.plantsislandssystem.entity.User;
import com.zhiyu.plantsislandssystem.mapper.UserMapper;
import com.zhiyu.plantsislandssystem.utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 项目启动时,自动初始化默认管理员账号
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserMapper userMapper;

    private final PasswordUtils passwordUtils;
    @Override
    public void run(String... args) throws Exception {
        //1.检查数据库中是否存在超级管理员
        User admin = userMapper.selectUserByNo("superAdmin");
        //2.不存在自动创建
        if (admin == null){
            User user = new User();
            user.setNo("superAdmin");
            user.setPhone("13800000000");
            user.setPassword(passwordUtils.encode("123456"));
            user.setNickname("超级管理员");
            user.setAvatar("");
            user.setGender(0);
            user.setRole(0);
            user.setStatus(1);
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            userMapper.insert(user);
            //打印日志
            log.info("✅️超级管理员创建成功!");
            log.info("账号: superAdmin");
            log.info("密码: 123456");
        }else {
            //存在直接跳过检查
            log.info("✅️超级管理员已存在!");
        }
    }
}

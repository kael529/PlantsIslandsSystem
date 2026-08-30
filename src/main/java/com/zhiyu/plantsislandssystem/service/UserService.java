package com.zhiyu.plantsislandssystem.service;

import com.zhiyu.plantsislandssystem.dto.LoginDTO;
import com.zhiyu.plantsislandssystem.dto.RegisterDTO;
import com.zhiyu.plantsislandssystem.entity.User;

public interface UserService {

    //用户注册
    void register(RegisterDTO registerDTO);

    //用户登录
    String login(LoginDTO loginDTO);

    //根据id查询用户
    User queryUserById(Long id);
}

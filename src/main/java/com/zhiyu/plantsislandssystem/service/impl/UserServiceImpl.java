package com.zhiyu.plantsislandssystem.service.impl;

import com.zhiyu.plantsislandssystem.common.BusinessException;
import com.zhiyu.plantsislandssystem.common.ResultCode;
import com.zhiyu.plantsislandssystem.dto.LoginDTO;
import com.zhiyu.plantsislandssystem.dto.RegisterDTO;
import com.zhiyu.plantsislandssystem.entity.User;
import com.zhiyu.plantsislandssystem.mapper.UserMapper;
import com.zhiyu.plantsislandssystem.service.UserService;
import com.zhiyu.plantsislandssystem.utils.JwtUtils;
import com.zhiyu.plantsislandssystem.utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final PasswordUtils passwordUtils;

    private final JwtUtils jwtUtils;

    //TODO:用户注册
    @Override
    public void register(RegisterDTO registerDTO) {
        //1.检查手机号是否注册
        int count = userMapper.countUserByPhone(registerDTO.getPhone());
        if (count > 0){
            throw new BusinessException(ResultCode.PHONE_ALREADY_EXISTS);
        }
        //2.检查密码两次是否一致
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())){
            throw new BusinessException(ResultCode.PASSWORD_ERROR, "两次输入的密码不一致!");
        }
        //3.加密密码
        String encodePassword = passwordUtils.encode(registerDTO.getPassword());
        //4.创建User
        User user = new User();
        user.setPhone(registerDTO.getPhone());
        user.setPassword(encodePassword);
        user.setAvatar("");
        user.setGender(0);
        user.setRole(2);
        user.setStatus(1);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        //5.生成唯一账号(重试机制)
        int maxRetries = 3;  // 最多重试 3 次
        for (int i = 0; i < maxRetries; i++) {
            String no = generateUserNo();  // 生成账号
            user.setNo(no);
            user.setNickname("花友" + no);  // 昵称也跟着更新
            try {
                userMapper.insert(user);
                return;  // 插入成功,直接返回
            } catch (DuplicateKeyException e) {
                // 账号冲突了,重试
                log.warn("账号 {} 已存在，重试第 {} 次", no, i + 1);
                if (i == maxRetries - 1) {
                    throw new BusinessException(ResultCode.ERROR, "系统繁忙,请稍后再试");
                }
            }
        }
    }

    //TODO:生成用户账号
    private String generateUserNo() {
        return "p" + System.currentTimeMillis() + (int) (Math.random() * 9000 + 1000);
    }

    //TODO：用户登录
    @Override
    public String login(LoginDTO loginDTO) {
        String loginKey = loginDTO.getLoginKey();
        //1.检查用户存不存在
        User user = null;
        if (loginKey.matches("^1[3-9]\\d{9}$")){
            user = userMapper.selectUserByPhone(loginKey);
        }else {
            user = userMapper.selectUserByNo(loginKey);
        }
        if (user == null){
            throw new BusinessException(ResultCode.USER_NOT_FOUND);
        }
        //2.检查账号或手机号能否使用
        if (user.getStatus() != 1){
            throw new BusinessException(ResultCode.ACCOUNT_DISABLED);
        }
        //3.检查密码对不对
        if (!passwordUtils.matches(loginDTO.getPassword(), user.getPassword())){
            throw new BusinessException(ResultCode.PASSWORD_ERROR);
        }
        //4.生成Token
        String token = jwtUtils.generateToken(user.getId());
        //5.返回Token
        return token;
    }

    //TODO:按id查询用户
    @Override
    public User queryUserById(Long id) {
        User user = userMapper.selectById(id);
        if (user == null){
            throw new BusinessException(ResultCode.USER_NOT_FOUND);
        }
        return user;
    }
}

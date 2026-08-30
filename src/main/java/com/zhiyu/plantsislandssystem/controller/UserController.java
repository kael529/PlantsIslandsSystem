package com.zhiyu.plantsislandssystem.controller;

import com.zhiyu.plantsislandssystem.common.Result;
import com.zhiyu.plantsislandssystem.dto.LoginDTO;
import com.zhiyu.plantsislandssystem.dto.RegisterDTO;
import com.zhiyu.plantsislandssystem.entity.User;
import com.zhiyu.plantsislandssystem.service.UserService;
import com.zhiyu.plantsislandssystem.utils.JwtUtils;
import com.zhiyu.plantsislandssystem.vo.LoginInfoVO;
import com.zhiyu.plantsislandssystem.vo.LoginVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final JwtUtils jwtUtils;

    //TODO:用户注册
    @PostMapping("/register")
    public Result<String> register(@Valid @RequestBody RegisterDTO registerDTO){
        userService.register(registerDTO);
        return Result.success("恭喜您,注册成功!");
    }

    //TODO:用户登录
    @PostMapping("/login")
    public Result<LoginVO> login(@Valid @RequestBody LoginDTO loginDTO){
        //1.获取Token
        String token = userService.login(loginDTO);
        //2.在Token里拿到userId
        Long userId = jwtUtils.getUserId(token);
        //3.查询用户信息
        User user = userService.queryUserById(userId);
        //4.组装用户信息
        LoginInfoVO userInfo = new LoginInfoVO();
        userInfo.setId(user.getId());
        userInfo.setNickname(user.getNickname());
        userInfo.setAvatar(user.getAvatar());
        userInfo.setPhone(user.getPhone());
        userInfo.setRole(user.getRole());
        //5.返回Token和用户信息
        return Result.success(new LoginVO(token, userInfo));
    }
}

package com.zhiyu.plantsislandssystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 接收前端登录请求
 */

@Data
public class LoginDTO {

    @NotBlank(message = "账号或手机号不能为空!")
    private String loginKey;

    @NotBlank(message = "密码不能为空!")
    private String password;
}

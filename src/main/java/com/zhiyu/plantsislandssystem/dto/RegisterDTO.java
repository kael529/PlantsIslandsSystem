package com.zhiyu.plantsislandssystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 接收前端注册请求
 */

@Data
public class RegisterDTO {

    @NotBlank(message = "手机号不能为空!")
    private String phone;

    @NotBlank(message = "密码不能为空!")
    private String password;

    @NotBlank(message = "请再次输入密码!")
    private String confirmPassword;
}

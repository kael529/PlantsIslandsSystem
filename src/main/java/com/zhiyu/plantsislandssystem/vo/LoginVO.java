package com.zhiyu.plantsislandssystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录响应 VO
 * 包含 token 和用户信息
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO {
    private String token;
    private LoginInfoVO loginInfoVO;
}

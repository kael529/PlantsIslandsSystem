package com.zhiyu.plantsislandssystem.vo;

import lombok.Data;

/**
 * 登录成功后返回的用户信息
 */

@Data
public class LoginInfoVO {
    private Long id;
    private String nickname;
    private String avatar;
    private String phone;
    private Integer role;
}

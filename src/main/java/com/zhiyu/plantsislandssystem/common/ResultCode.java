package com.zhiyu.plantsislandssystem.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 统一响应状态码枚举
 * 规范整个项目的返回码，避免硬编码
 */

@Getter
@AllArgsConstructor
public enum ResultCode {

    // 通用状态码
    SUCCESS(200, "成功"),
    ERROR(500, "系统异常,请稍后重试"),

    // 参数校验异常
    PARAM_ERROR(400, "参数错误"),
    PARAM_MISSING(4001, "缺少必要参数"),
    PARAM_TYPE_ERROR(4002, "参数类型错误"),

    // 认证授权异常
    UNAUTHORIZED(401, "未登录或登录已过期,请重新登录"),
    FORBIDDEN(403, "无权限访问,请联系管理员"),
    TOKEN_EXPIRED(4011, "登录已过期,请重新登录"),
    TOKEN_INVALID(4012, "无效的令牌"),

    // 业务异常
    USER_NOT_FOUND(1001, "用户不存在"),
    USER_ALREADY_EXISTS(1002, "用户已存在"),
    PHONE_ALREADY_EXISTS(1003, "手机号已被注册"),
    PASSWORD_ERROR(1004, "密码错误"),
    ACCOUNT_DISABLED(1005, "账号已被禁用"),

    PLANT_NOT_FOUND(2001, "植物不存在"),
    GARDEN_PLANT_NOT_FOUND(3001, "花园记录不存在"),

    POST_NOT_FOUND(4001, "帖子不存在"),
    COMMENT_NOT_FOUND(4002, "评论不存在"),

    GOODS_NOT_FOUND(5001, "商品不存在"),
    STOCK_INSUFFICIENT(5002, "库存不足"),
    ORDER_NOT_FOUND(5003, "订单不存在"),

    // 文件/上传异常
    FILE_UPLOAD_ERROR(6001, "文件上传失败"),
    FILE_TOO_LARGE(6002, "文件大小超过限制"),
    FILE_TYPE_ERROR(6003, "不支持的文件格式");

    private final Integer code;
    private final String message;
}
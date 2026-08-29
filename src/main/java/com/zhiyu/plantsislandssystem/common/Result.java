package com.zhiyu.plantsislandssystem.common;

import lombok.Data;

/**
 * 统一响应结果类
 * 所有 Controller 接口都返回这个格式
 */

@Data
public class Result<T> {

    private Integer code;
    private String message;
    private T data;

    /**
     * 私有构造方法，不对外暴露
     */
    private Result(Integer code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    //------响应成功------
    /**
     * 成功返回数据（使用默认 message）
     */
    public static <T> Result<T> success(T data){
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功不返回数据
     */
    public static <T> Result<T> success(){
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    /**
     * 成功返回自定义消息和数据
     */
    public static <T> Result<T> success(String message, T data){
        return new Result<>(ResultCode.SUCCESS.getCode(), message, data);
    }

    //------响应失败------
    /**
     * 失败（使用枚举中预定义的状态码和消息）
     */
    public static <T> Result<T> fail(ResultCode resultCode){
        return new Result<>(resultCode.getCode(), resultCode.getMessage(), null);
    }

    /**
     * 失败（使用枚举，但自定义消息）
     */
    public static <T> Result<T> fail(ResultCode resultCode, String message){
        return new Result<>(resultCode.getCode(), message, null);
    }

    /**
     * 失败（快速失败，直接用错误消息，code=500）
     */
    public static <T> Result<T> fail(String message){
        return new Result<>(ResultCode.ERROR.getCode(), message, null);
    }
}

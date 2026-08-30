package com.zhiyu.plantsislandssystem.common;

import lombok.Getter;

/**
 * 自定义业务异常
 * 用于在 Service层抛出,由全局异常处理器统一拦截
 */

@Getter
public class BusinessException extends RuntimeException {

    private final ResultCode resultCode;
    public BusinessException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public BusinessException(ResultCode resultCode, String message){
        super(message);
        this.resultCode = resultCode;
    }
}

package com.zhiyu.plantsislandssystem.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * 拦截所有异常，统一返回 Result 格式
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有未捕获的异常
     */
    //管理这种类型的异常
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e){
        //打印完整的异常堆栈到日志
        log.error("系统异常: ", e);
        return Result.fail(ResultCode.ERROR);
    }

    /**
     * 处理运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Result<Void> handleRuntimeException(RuntimeException e){
        log.error("运行时异常: ", e);
        return Result.fail(ResultCode.ERROR);
    }

    /**
     * 处理业务异常(自定义异常)
     */
    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusinessException(BusinessException e){
        log.warn("业务异常: {}", e.getMessage());
        return Result.fail(e.getResultCode());
    }
}

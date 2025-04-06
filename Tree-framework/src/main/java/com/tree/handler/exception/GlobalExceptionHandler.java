package com.tree.handler.exception;

import com.tree.domain.ResponseResult;
import com.tree.enums.AppHttpCodeEnum;
import com.tree.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.access.AccessDeniedException;

//@RestControllerAdvice 是 Spring 框架里的一个注解，
// 它把 @ControllerAdvice 和 @ResponseBody 两个注解的功能结合起来，
// 主要用于全局处理控制器里抛出的异常以及对控制器返回的数据进行统一处理。下面为你详细介绍：

//1. 全局异常处理
//借助 @RestControllerAdvice 注解的类能够定义多个异常处理方法，这些方法能够捕获控制器里抛出的特定异常，然后返回统一格式的错误响应，这有助于增强代码的可维护性和用户体验。
//
// 2. 数据统一处理
//可以在 @RestControllerAdvice 注解的类里定义方法，对控制器返回的数据进行统一处理，例如添加额外的信息、转换数据格式等。
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e){
        //打印异常信息
        log.error("出现了异常！ "+e.getMessage());
        //从异常对象中获取提示信息封装返回
        return ResponseResult.errorResult(e.getCode(),e.getMsg());
    }

    // 处理SpringSecurity的权限异常
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseResult handleAccessDeniedException(AccessDeniedException e) {
        return ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH.getCode(),e.getMessage());//枚举值是500
    }

    //其它异常交给这里处理
    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e){
        //打印异常信息
        log.error("出现了异常！ "+e.getMessage());
        //从异常对象中获取提示信息封装返回
        return
                ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),e.getMessage()
                );
    }
}

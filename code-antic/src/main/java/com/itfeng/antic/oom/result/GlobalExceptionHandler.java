package com.itfeng.antic.oom.result;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常拦截
 */
@RestControllerAdvice
//@RestControllerAdvice ，等同于@ControllerAdvice + @ResponseBody
//@RestControllerAdvice和@ExceptionHandler会捕获所有Rest接口的异常并封装成我们定义的HttpResult的结果集返回，
// 但是：处理不了拦截器里的异常
public class GlobalExceptionHandler {

    /**
     * 异常捕获
     *
     * @param e 捕获的异常
     * @return 封装的返回对象
     **/
    @ExceptionHandler(Exception.class)
    public HttpResult handlerException(Exception e) {
        ResultCodeEnum resultCodeEnum;
        // 自定义异常
        if (e instanceof TokenVerificationException) {
            resultCodeEnum = ResultCodeEnum.SERVER_ERROR;
            resultCodeEnum.setMessage(e.getMessage());
            //log.error("tokenVerificationException：{}", resultCodeEnum.getMessage());
        } else {
            // 其他异常，当我们定义了多个异常时，这里可以增加判断和记录
            resultCodeEnum = ResultCodeEnum.SERVER_ERROR;
            resultCodeEnum.setMessage(e.getMessage());
            // log.error("common exception:{}", JSON.toJSONString(e));
        }
        return HttpResult.failure(resultCodeEnum);
    }
}
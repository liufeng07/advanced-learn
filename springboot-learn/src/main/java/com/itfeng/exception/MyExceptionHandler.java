//package com.itfeng.exception;
//
//import com.conlin.common.api.vo.Result;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.io.IOException;
//
//@ControllerAdvice
//@ResponseBody
//@Slf4j
//@Order(value = 0)
//public class MyExceptionHandler {
//    @ExceptionHandler(AppException.class)
//    @ResponseBody
//    public Result parameterException(AppException ex)
//            throws IOException {
//        log.error("ExceptionHandler(AppException)===>" + ex.getCode() + ":" + ex.getMessage(), ex);
//        Result result = new Result();
////        result.error500("处理异常"+ex.getMessage());
//        result.error500(ex.getMessage());
//        result.setCode(new Integer(ex.getCode()));
//        return result;
//    }
//
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    @ResponseBody
//    public Result parameterException(HttpMessageNotReadableException ex)
//            throws IOException {
//        log.error("ExceptionHandler(HttpMessageNotReadableException)===>" + "入参格式不合法" +ex.getMessage(), ex);
//        Result result = new Result();
//        result.error500("入参格式不合法"+ex.getMessage());
//        return result;
//    }
//
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public Result parameterException(Exception ex)
//            throws IOException {
//        log.error("ExceptionHandler(Exception)===>" + ex.getMessage(), ex);
//        Result result = new Result();
//        result.error500("服务器异常"+ex.getMessage());
//        return result;
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody
//    public Result parameterException(MethodArgumentNotValidException ex)
//            throws IOException {
//        log.error("ExceptionHandler(Exception)===>" + ex.getMessage(), ex);
//        String msg = ex.getBindingResult().getFieldError().getDefaultMessage();
//        Result result = new Result();
//        result.error500("入参格式不合法:"+msg);
//        return result;
//    }
//}

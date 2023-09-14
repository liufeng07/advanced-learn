//package com.itfeng.aop;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.conlin.common.api.vo.Result;
//import com.conlin.common.exception.AppException;
//import com.conlin.common.util.DateUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
//
///**
//* @Description:    日志切面类
//* @Author:         xiewei
//* @CreateDate:     2019/7/17 11:45
//*/
//@Slf4j
//@Aspect
//@Component
//public class RequestAspect {
//    private long _ElapsedTime;
//    /**
//     * 切点
//     */
//    @Pointcut("@within(com.conlin.common.aop.annotation.RequestIntercept)")
//    public void xLogCut() {
//    }
//
//    /**
//     * 前置
//     */
//    @Before("xLogCut()")
//    public void xBefore(JoinPoint x) {
//        String methodName = x.getSignature().getName();
//        String classType = x.getTarget().getClass().getName();
//        String reqStr = "";
//        if (x.getArgs().length > 0){
//            //zzj 上传文件接口不记日志
//            if (!(x.getArgs()[0] instanceof MultipartFile)
//                    &&!(x.getArgs()[0] instanceof MultipartFile[])
//                    && !(x.getArgs()[0] instanceof StandardMultipartHttpServletRequest)){
//                reqStr = JSON.toJSONStringWithDateFormat(x.getArgs()[0],
//                        DateUtils.FORMAT_ONE,
//                        SerializerFeature.WriteDateUseDateFormat);
//            }
//        }
//        log.info("{}.{} 入参:{}",classType,methodName,reqStr);
//    }
//    /**
//     * 环绕
//     *
//     * @param x
//     * @return
//     * @throws Throwable
//     */
//    @Around("xLogCut()")
//    public Object xAround(ProceedingJoinPoint x) throws Throwable {
//        String methodName = x.getSignature().getName();
//        String classType = x.getTarget().getClass().getName();
//        log.info("==={}.{}=== 请求调用开始:",classType,methodName);
//        long _BngTime = System.currentTimeMillis();
//        Object o = x.proceed();
//        _ElapsedTime = System.currentTimeMillis() - _BngTime;
//        log.info("==={}.{}=== 请求调用结束,总耗时{}ms:",classType,methodName,_ElapsedTime);
//        return o;
//    }
//    /**
//     * 后置
//     *
//     * @param x
//     */
//    @AfterReturning(returning = "res", pointcut = ("xLogCut()"))
//    public void xAfter(JoinPoint x, Result res) {
//        String methodName = x.getSignature().getName();
//        String classType = x.getTarget().getClass().getName();
//        String resStr = JSON.toJSONStringWithDateFormat(res,
//                DateUtils.FORMAT_ONE,
//                SerializerFeature.WriteDateUseDateFormat);
//        log.info("{}.{} 出参:{}",classType,methodName,resStr);
//
//    }
//    /**
//     * 例外
//     *
//     * @param o
//     */
//    @AfterThrowing(pointcut="xLogCut()",throwing="e")
//    public void xException(JoinPoint o, AppException e) {
//        String methodName = o.getSignature().getName();
//        String classType = o.getTarget().getClass().getName();
//        log.error("{}.{} 异常:{}",classType,methodName,e.getMessage());
//    }
//}

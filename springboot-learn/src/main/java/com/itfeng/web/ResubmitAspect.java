//package com.itfeng.web;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.lang.reflect.Method;
//import java.util.concurrent.TimeUnit;
//
///**
// * 重复提交切面
// */
//@Component
//@Aspect
//@Slf4j
//public class ResubmitAspect {
//    /**
//     * redis目录分割符
//     */
//    private final String CATALOG_SEPARATOR = ":";
//
//    @Value("${spring.profiles.active}")
//    private String springProfilesActive;
//    @Value("${spring.application.name}")
//    private String springApplicationName;
//
//    @Resource
//    RedissonClient redissonClient;
//
//    /**
//     * 重复提交切点
//     */
//    @Pointcut("@annotation(xxx.aop.anno.Resubmit)")
//    public void resubmitPointcut() {
//    }
//
//    /**
//     * 执行拦截
//     *
//     * @param joinPoint 切点
//     * @return
//     */
//    @Around("resubmitPointcut()")
//    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        Signature signature = joinPoint.getSignature();
//        MethodSignature methodSignature = (MethodSignature) signature;
//        //获取这个上面的注解
//        Method method = methodSignature.getMethod();
//        Resubmit annotation = method.getAnnotation(Resubmit.class);
//        int delaySeconds = annotation.delaySeconds();
//
//        //获取第一个参数值
//        String redisKey = getLockKey(signature, annotation, methodSignature, joinPoint.getArgs());
//        RLock lock = redissonClient.getLock(redisKey);
//        try {
//            boolean lockFlag = true;
//            if (delaySeconds == 0) {
//                //这种表示一直在续约 默认有效期为30秒 之后每10秒更新一次为30秒，直到任务完成
//                lockFlag = lock.tryLock(0, -1, TimeUnit.SECONDS);
//            } else {
//                lockFlag = lock.tryLock(0, delaySeconds, TimeUnit.SECONDS);
//            }
//            if (!lockFlag) {
//                //return Response.error(ErrorCode.EXCEPTION.getCode(), "请勿重复操作～", null);
//            }
//            log.info("获取锁成功");
//            return joinPoint.proceed();
//        } catch (InterruptedException e) {
//            //获取redis锁导致的报错
//            log.error("ResubmitAspect/Exception:[{}]", e.getMessage());
//           // return Response.error(ErrorCode.EXCEPTION.getCode(), e.getMessage(), null);
//        } finally {
//            //如果设置了过期时间就让它自动过期
//            if (delaySeconds == 0) {
//                if (lock.isHeldByCurrentThread()) {
//                    // 解锁
//                    log.info("解锁成功 {}", redisKey);
//                    lock.unlock();
//                } else {
//                    log.info("获取锁失败 {}", redisKey);
//                }
//            }
//        }
//    }
//
//    /**
//     * key的生成策略（如何保证唯一很重要）存活时间，缓存策略（Redis还是本地Map）
//     * 获取唯一标识key
//     *
//     * @param signature
//     * @param annotation
//     * @param methodSignature
//     * @param args
//     * @return
//     */
//    private String getLockKey(Signature signature, Resubmit annotation, MethodSignature methodSignature, Object[] args) {
//        //获取包含方法参数的值
//        String keyField = annotation.uniqueKeyField();
//        String key = "";
//        if (StringUtils.isEmpty(keyField)) {
//            //如果没有传值，直接取第一个值，进行MD5加密作为key
//            Object firstArg = args[0];
//            key = MD5Util.getMD5(JSONObject.toJSONString(firstArg), "");
//        } else {
//            String[] parameterNames = methodSignature.getParameterNames();
//
//            for (int index = 0; index < parameterNames.length; index++) {
//                if (keyField.equals(parameterNames[index])) {
//                    Object obj = args[index];
//                    key = JSONObject.toJSONString(obj);
//                    break;
//                }
//            }
//            if (StringUtils.isEmpty(key)) {
//                throw new Exception(ErrorCode.EXCEPTION.getCode(), "请设置正确的Resubmit唯一key标识");
//            }
//        }
//        //获取类名称，方法名称
//        String methodName = signature.getName();
//        String simpleClassName = signature.getDeclaringType().getSimpleName();
//        //项目名称 + 环境编码 + 获取类名称 + 方法名称 + 唯一key
//        key = springApplicationName + CATALOG_SEPARATOR + springProfilesActive + CATALOG_SEPARATOR + simpleClassName + CATALOG_SEPARATOR + methodName + CATALOG_SEPARATOR + key;
//        return key;
//    }
//}

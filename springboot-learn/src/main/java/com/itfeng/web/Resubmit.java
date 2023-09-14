package com.itfeng.web;

import java.lang.annotation.*;

/**
 * 防重复提交拦截的注解
 * @author ggy
 * @date 2021/12/9
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Resubmit {
    /**
     * 默认提交的0-表示前一个请求执行完后面一个就可以继续请求
     * 如果是10 表示前一个请求执行开始10秒之后 后面一个就可以才能请求
     * @return
     */
    int delaySeconds() default 0;

    /**
     * 唯一key字段 取自请求参数上的字段字段，可以直接是字段值，比如userId
     * 如果不传取第一个参数md5 加密生成唯一key
     */
    String uniqueKeyField() default "";
}

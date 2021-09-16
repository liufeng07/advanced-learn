package com.itfeng.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author liuf
 * @date 2021年08月03日 9:02 上午
 *  springboot异步操作可以使用@EnableAsync和@Async两个注解，本质就是多线程和动态代理
 *  异步方法使用注解@Async ,返回值为void或者Future
 *  异步方法和调用方法一定要写在不同的类中,如果写在一个类中是没有效果的
 */
@Slf4j
@Service
public class TestThreadPoolService {
    @Async("taskExecutor")
    public void test() {
        log.info(Thread.currentThread().getName() + ".........开始");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(Thread.currentThread().getName() + ".........结束");
    }
}

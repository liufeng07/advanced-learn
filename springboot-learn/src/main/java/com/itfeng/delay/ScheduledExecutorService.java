package com.itfeng.delay;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author liuf
 * @date 2021年12月15日 4:23 下午
 */
public class ScheduledExecutorService {
    public static void main(String[] args) {
        System.out.println("程序启动时间：" + LocalDateTime.now());
        scheduledExecutorServiceTask();
    }

    /**
     * ScheduledExecutorService 实现固定频率一直循环执行任务
     */
    public static void scheduledExecutorServiceTask() {
        Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(
                new Runnable() {
                    @Override
                    public void run() {
                        // 执行任务的业务代码
                        System.out.println("执行任务" +
                                " ，执行时间：" + LocalDateTime.now());
                    }
                },
                2, // 初次执行间隔
                2, // 2s 执行一次
                TimeUnit.SECONDS);
    }
}

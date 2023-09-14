package com.itfeng.antic.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类
 */
public class AtomicTest {
    public static AtomicInteger count = new AtomicInteger(0);
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            service.execute(() -> {
                for (int j = 0; j < 1000; j++) {
                    count.getAndIncrement();
                }
            });
        }
        service.shutdown();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count = " + count);
    }
}

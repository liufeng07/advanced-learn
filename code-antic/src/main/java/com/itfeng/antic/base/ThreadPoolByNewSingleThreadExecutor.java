package com.itfeng.antic.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liuf
 * @date 2021年05月07日 1:40 下午
 *
 * 单线程化线程池的优点，即串形化执行所有任务，如果这个唯一的线程因异常而结束，
 * 那么会有一个新的线程来替代他，此线程池保证所有的任务顺序按照任务的提交顺序来执行。
 */
public class ThreadPoolByNewSingleThreadExecutor {
    public static void main(String[] args) {
        /**
         * 单线程化的线程池
         */
        //创建一个线程池
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        //创建10个线程
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    Thread.currentThread().setName("Thread i = " + index);
                    System.out.println(Thread.currentThread().getName() + " index = " + index);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.out.println("ssss");
                    }
                }
            });
        }
        singleThreadExecutor.shutdown();
        System.out.println("on the main thread...");

    }
}

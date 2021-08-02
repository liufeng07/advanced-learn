package com.itfeng.antic.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author liuf
 * @date 2021年07月09日 10:23 上午
 */
public class ConcurrentTest {
    //类共享变量==并发
    public Integer count = 0;

    public static void main(String[] args) {
        final ConcurrentTest demo = new ConcurrentTest();
        //初始化时具有10个线程的线程池，多线程对类变量count进行自增操作
        Executor executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100000; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    //System.out.println(Thread.currentThread().getName());
                    demo.count++; // i++不具备原子性 读取i的值，加1，再写会主内存是三步操作
                }
            });
        }
        //保证线程池执行完毕
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("final count value:" + demo.count);
    }
}

package com.itfeng.antic.base;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * CountDownLatch
 * 场景 上传excel文件读取等
 */
public class TestThreadPoolCountDownLatch {
    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程总数
    public static int threadTotal = 200;

    //共享变量
    public static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        // 定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 定义信号量 最大的线程数量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //参数count为计数值
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);


        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    //用于获取权限的acquire(),其底层实现与CountDownLatch.countdown()类似;
                    semaphore.acquire();
                    count++;
                    semaphore.release();
                    //用于释放权限的release()，其底层实现与acquire()是一个互逆的过程。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //将count值减1
                countDownLatch.countDown();
            });
        }
        //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("count:"+count);
    }
}

package com.itfeng.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author: lf
 * @creat: 2024/2/19 11:46
 * @describe:
 */
public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //缺点 get方法会阻塞主线程，也就是异步任务没完成会一直阻塞直到任务结束
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(() -> "三友");
        while (!future.isDone()) {
            //任务有没有完成，没有就继续循环判断

        }
        System.out.println(future.get());
        executorService.shutdown();
    }
}

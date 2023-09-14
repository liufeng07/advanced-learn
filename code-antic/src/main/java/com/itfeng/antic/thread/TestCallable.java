package com.itfeng.antic.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author liuf
 * @date 2022年01月11日 2:01 下午
 */
public class TestCallable implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"进入callable");
        int retValue = 10;
        return retValue;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(new TestCallable());
        Thread thread = new Thread(task,"线程A");
        thread.start();
        //线程运行结束，说明Callable接口的方法已经完成，此时我们就可以获取返回值
        //get()方法用来获取执行结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回
        System.out.println("Callable返回的结果是："+task.get());
    }
}

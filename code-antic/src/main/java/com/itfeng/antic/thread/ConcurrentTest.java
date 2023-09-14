package com.itfeng.antic.thread;

import com.itfeng.antic.oom.stackError.entity.count;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author liuf
 * @date 2021年07月09日 10:23 上午
 */
public class ConcurrentTest {
    //一般在系统启动或者执行到业务时完成初始化(一般都是全局的，不需要每次都new一个，如果每次都new高并发就有问题了)
//    创建一个指定工作线程数量的线程池。每当提交一个任务就创建一个工作线程，如果工作线程数量达到线程池初始的最大数，则将提交的任务存入到池队列中。
//    FixedThreadPool 是一个典型且优秀的线程池，它具有线程池提高程序效率和节省创建线程时所耗的开销的优点。但是，在线程池空闲时，即线程池中没有可运行任务时，它不会释放工作线程，还会占用一定的系统资源。
    private static final ExecutorService executor = Executors.newFixedThreadPool(10);
    //private static final Executor executor = Executors.newSingleThreadExecutor();
    //private static final Executor executor = Executors.newCachedThreadPool();
    //安排任务延迟执行
    //private static final Executor executor = Executors.newScheduledThreadPool(5);

    //类共享变量==并发
    public Integer count = 0;

    public static void main(String[] args) {
        final ConcurrentTest demo = new ConcurrentTest();
        //初始化时具有10个线程的线程池，多线程对类变量count进行自增操作
        for (int i = 0; i < 100000; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"执行开始");
                    demo.count++; // i++不具备原子性 读取i的值，加1，再写会主内存是三步操作
                    System.out.println(Thread.currentThread().getName() + "执行结束");
                }
            });
        }
        //保证线程池执行完毕 线程阻塞
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        System.out.println("final count value:" + demo.count);
    }
}

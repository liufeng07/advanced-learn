package com.itfeng.antic.thread;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author liuf
 * @date 2021年05月07日 2:08 下午
 * 结论：
 * 线程A和线程B存储在ThreadLocal中的变量互不干扰，线程A存储的变量只能由线程A访问，线程B存储的变量只能由线程B访问。\
 * 实际应用 数据库连接的管理类
 */
public class ThreadLocalTest {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    //    public static void main(String[] args) {
//        //创建第一个线程
//        Thread threadA = new Thread(() -> {
//            threadLocal.set("ThreadA：" + Thread.currentThread().getName());
//            System.out.println("线程A本地变量中的值为：" + threadLocal.get());
//            threadLocal.remove();
//            System.out.println("线程A删除本地变量后ThreadLocal中的值为：" + threadLocal.get());
//        });
//        //创建第二个线程
//        Thread threadB = new Thread(() -> {
//            threadLocal.set("ThreadB：" + Thread.currentThread().getName());
//            System.out.println("线程B本地变量中的值为：" + threadLocal.get());
//            System.out.println("线程B没有删除本地变量：" + threadLocal.get());
//        });
//        //启动线程A和线程B
//        threadA.start();
//        threadB.start();
//    }
    public static void main(String[] args) {
        //新建一个ThreadLocal
        ThreadLocal<String> local = new ThreadLocal<>();
        //新建一个随机数类
        Random random = new Random();

        IntStream.rangeClosed(0, 10).forEach(x -> System.out.println(x));
        //使用java8的Stream新建5个线程
        IntStream.range(0, 5).forEach(a -> new Thread(() -> {
            //为每一个线程设置相应的local值
            local.set(a + "  " + random.nextInt(10));
            System.out.println("线程和local值分别是  " + local.get());
            //可能会造成内存泄漏，使用完成之后最好调用remove。内存泄漏最终会导致内存溢出
            local.remove();
            //设置线程阻塞是为了能及时读取当前local的值
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());
    }

}
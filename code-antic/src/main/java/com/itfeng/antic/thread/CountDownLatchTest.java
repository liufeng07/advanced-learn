package com.itfeng.antic.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author liuf
 * @date 2022年01月11日 2:29 下午
 * 同步工具类定义
 * 一个或者一组线程在开始执行操作之前，必须等待其他线程执行完成才可以。举个例子，考试的时候等学生交完卷完成之后才能走
 *
 */
public class CountDownLatchTest {
    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) {
        System.out.println("全班同学开始考试：一共两个学生");
        new Thread(() -> {
            System.out.println("第一个学生交卷，countDownLatch减1");
            countDownLatch.countDown();
        }).start();
        new Thread(() -> {
            System.out.println("第二个学生交卷，countDownLatch减1");
            countDownLatch.countDown();
        }).start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("老师清点试卷，在此之前，只要一个学生没交，"
                + "countDownLatch不为0，不能离开考场");
    }
}

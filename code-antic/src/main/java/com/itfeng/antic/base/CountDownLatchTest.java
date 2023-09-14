package com.itfeng.antic.base;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 子线程执行完毕之后执行主线程
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);//传计数器的值
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                //返回[0,5)的值
                int time = random.nextInt(5);
                System.out.println(Thread.currentThread().getName() + " 在路上耗时" + time + "秒");
                try {
                    TimeUnit.SECONDS.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 到达⻋站了");
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println("⽼司机，发⻋");
    }
}

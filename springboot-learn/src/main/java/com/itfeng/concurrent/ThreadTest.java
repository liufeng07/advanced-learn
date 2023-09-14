package com.itfeng.concurrent;

/**
 * @author liuf
 * @date 2022年01月06日 4:19 下午
 */
public class ThreadTest {
    private int value = 0;

    public int getValue() {
        return value++;
    }
    //共享资源线程安全问题
    public static void main(String[] args) throws InterruptedException {
        final ThreadTest test = new ThreadTest();
        for (int i = 0; i < 100000; i++) {
            new Thread("th1") {
                @Override
                public void run() {
                    System.out.println(test.getValue() + " " + super.getName());
                }
            }.start();
        }
    }
}

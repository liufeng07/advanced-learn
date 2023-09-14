package com.itfeng.concurrent;

/**
 * @author liuf
 * @date 2022年01月06日 3:03 下午
 */
public class TestThread {
    public static void main(String[] args) {
        //10个线程
        for (int i = 0; i < 10; i++) {
            new Thread(()-> qdm()).start();
        }
       new Thread(()-> tyy()).start();
    }

    private static void qdm(){
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"敲代码");
        }
    }

    private static void tyy(){
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"听音乐");
        }
    }
}

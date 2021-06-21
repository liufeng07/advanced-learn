package com.itfeng.antic.base;

/**
 * @author liuf
 * @date 2021年05月07日 2:51 下午
 * 测试线程的可见性
 * 可见性就是说一个线程对共享变量的修改，另一个线程能够立刻看到。
 * 在CPU单核时代，操作系统上所有的线程都是运行在同一个CPU上，操作同一个CPU的缓存。一个线程对缓存的写，对另外一个线程
 * 一定可见。
 */
public class TestThread {
    private long count = 0; //对count的值累加1000次

    private void addCount() {
        for (int i = 0; i < 1000000; i++) {
            count++;
        }
    }

//    public long execute() throws InterruptedException {
//        //创建两个线程，执行count的累加操作
//        Thread threadA = new Thread(() -> {
//            addCount();
//        });
//        Thread threadB = new Thread(() -> {
//            addCount();
//        }); //启动线程
//        threadA.start();
//        threadA.start();
//        //等待线程执行结束
//        threadA.join();
//        threadB.join();
//        //返回结果
//        return count;
//    }
    //同一个线程中，连续调用2次
    public long execute() throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            //创建两个线程，执行count的累加操作
            Thread threadA = new Thread(() -> {
                addCount();
            });
            threadA.start();
            //等待线程执行结束
            threadA.join();
        }

        //返回结果
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        TestThread testThread = new TestThread();
        long count = testThread.execute();
        System.out.println(count);
    }
}
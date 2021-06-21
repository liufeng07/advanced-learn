package com.itfeng.antic.base;

/**
 * @author liuf
 * @date 2021年04月07日 9:03 上午
 * 验证Synchronized锁住的是对象
 * 主要有两种使用方法
 *  1.同步方法
 *  2.同步代码块
 * https://segmentfault.com/a/1190000023571783
 * 总结
 *  如果多线程同时访问同一类的 类锁（synchronized 修饰的静态方法）以及对象锁（synchronized 修饰的非静态方法/代码块形式）这两个方法执行是异步的，原因：类锁和对象锁是2中不同的锁。
 *  类锁对该类的所有对象都能起作用，而对象锁不能。
 *  synchronized锁的是对象。
 *  类锁其实通过对象锁实现的。因为当虚拟机加载一个类的时候，会会为这个类实例化一个 java.lang.Class 对象，当你锁住一个类的时候，其实锁住的是其对应的Class 对象。
 * 类锁:
 *      java类可能会有很多个对象，但是只有1个Class对象，也就是说类的不同实例之间共享该类的Class对象。Class对象其实也仅仅是1个java对象，只不过有点特殊而已。由于每个java对象都有1个互斥锁，而类的静态方法是需要Class对象。所以所谓的类锁，不过是Class对象的锁而已。获取类的Class对象有好几种，最简单的就是［类名.class］的方式
 */
public class TestSynchronized {

    //对象锁
    public synchronized void test() {
        System.out.println("test start");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test end");
    }
    static class MyThread extends Thread{
        @Override
        public void run() {
            TestSynchronized testSynchronized = new TestSynchronized();
            testSynchronized.test();
        }
    }

    static class MyThread2 extends Thread{
        public TestSynchronized testSynchronized;

        public MyThread2(TestSynchronized testSynchronized) {
            this.testSynchronized = testSynchronized;
        }

        @Override
        public void run() {
            testSynchronized.test();
        }
    }



    //开启三个线程并发测试
    public static void main(String[] args) {
        //现象：在MyThread中，每次都new一个新的TestSynchronized对象，可以看到代码块test虽然被加了synchonized但是还是并行执行的，初步结论：锁住的不是代码块
//        for (int i = 0; i < 3; i++) {
//            Thread myThread = new MyThread();
//            myThread.start();
//        }
        //现象：可以看到当他们共用一个对象的时候，synchonized起了作用，这块代码是串行执行的
        //结论：锁住的对象
        TestSynchronized testSynchronized = new TestSynchronized();
        for (int i = 0; i < 3; i++) {
            MyThread2 myThread2 = new MyThread2(testSynchronized);
            myThread2.start();
        }
    }
}

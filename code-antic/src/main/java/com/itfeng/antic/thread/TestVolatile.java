package com.itfeng.antic.thread;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuf
 * @date 2022年01月11日 1:46 下午
 * 验证volatile关键字可见行和有序性，但并不保证原子性。所以是非线程安全的
 * 使用原子类来保证原子性 底层基本上就是通过Unsafe和CAS机制来实现的
 */
public class TestVolatile {
    private static volatile int a = 0;
    //具体是使用了 CAS机制 即比较并替换，实现并发算法时常用到的一种技术。CAS操作包含三个操作数——内存位置、预期原值及新值。（难点Unsafe类 类似c语言指针一样操作内存空间的能力）
    //cas是一种乐观锁，而且是一种非阻塞的轻量级的乐观锁 缺点会导致aba问题，假设一个变量 A ，修改为 B之后又修改为 A，CAS 的机制是无法察觉的，但实际上已经被修改过了（可以使用版本号递增以及时间戳的机制）
    //执行CAS操作的时候，将内存位置的值与预期原值比较，如果相匹配，那么处理器会自动将该位置值更新为新值，否则，处理器不做任何操作。
    private static AtomicInteger atomicInteger = new AtomicInteger();
    //多线程使用boolean线程不安全，使用原子类
    //private static AtomicBoolean atomicBoolean = new AtomicBoolean();


    // a++操作 非原子操作 单线程没有问题，多线程就有问题了，因为可能一个线程对a进行了加1操作，还没来得及写入内存，其他的线程就读取了旧值。造成了线程的不安全现象
    // 1.从内存中读取a
    // 2.对a进行加1操作
    // 3.将a的值重新写入内存中
    public static void main(String[] args) {
        TestVolatile test = new TestVolatile();
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
//                        System.out.println(++a);
                        //顺序可能不一样，但最后的结果一定是50
                        System.out.println(atomicInteger.incrementAndGet());
                        Thread.sleep(500);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }
    }
}

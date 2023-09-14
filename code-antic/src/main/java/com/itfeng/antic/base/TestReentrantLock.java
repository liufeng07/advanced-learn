package com.itfeng.antic.base;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuf
 * @date 2021年04月07日 11:10 上午
 * 公平锁
 */
public class TestReentrantLock {
    private final Lock lock = new ReentrantLock();
    private int count;

    public void add(int n) {
        lock.lock();
        try {
            count += n;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        TestReentrantLock testReentrantLock = new TestReentrantLock();
        for (int i = 0; i < 10; i++) {
            testReentrantLock.add(10);
            System.out.println(testReentrantLock.count);
        }

    }
}

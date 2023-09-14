package com.itfeng.antic.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author liuf
 * @date 2022年01月11日 2:52 下午
 * 验证 使用阻塞队列实现生产者消费者模式
 */
public class BlockingQueueDataTest {
    //flag表示是否生产，默认生产
    private volatile boolean flag = true;
    //aInteger表示产品
    private AtomicInteger aInteger = new AtomicInteger();
    BlockingQueue<Object> queue = null;

    public BlockingQueueDataTest(BlockingQueue<Object> queue) {
        this.queue = queue;
    }

    public void produce() throws Exception {
        String data = null;
        boolean retValue;
        while (flag) {
            data = aInteger.incrementAndGet() + "";
            retValue = queue.offer(data, 2L, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName() + " 插入的结果是：" + retValue);
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + " 休息一会，马上回来");
    }

    public void consumer() throws Exception {
        Object result = null;
        while (flag) {
            result = queue.poll(2L, TimeUnit.SECONDS);
            if (result == null || ((String) result).equalsIgnoreCase("")) {
                flag = false;
            }

            System.out.println(Thread.currentThread().getName() + " 消费资源成功");
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public static void main(String[] args) {
        BlockingQueue<Object> queue = new ArrayBlockingQueue<>(1);
        BlockingQueueDataTest blockingQueueDataTest = new BlockingQueueDataTest(queue);
        IntStream.range(0, 1).forEach(a -> new Thread(() -> {
            try {
                blockingQueueDataTest.produce();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start());

        IntStream.range(0, 10).forEach(a -> new Thread(() -> {
            try {
                blockingQueueDataTest.consumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start());
        IntStream.range(0, 10).forEach(System.out::println);
    }


}

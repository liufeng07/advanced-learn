package com.itfeng.antic.forkjoinpool;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * @author: lf
 * @creat: 2023/11/14 10:00
 * @describe:
 */
public class MainTest {
    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 10000000).toArray();
        Instant start = Instant.now();
        //Calculator calculator = new ForLoopImpl(); //普通for循环案例
        //Calculator calculator = new ExecutorServiceCalculatorImpl(); // ExecutorService 线程池案例
        //Calculator calculator = new ForkJoinPoolImpl(); //ForkJoin 案例
        //long result = calculator.sumUp(numbers);

        long result = LongStream.rangeClosed(0, 10000000L).parallel().reduce(0, Long::sum); // jdk8并行流,底层还是Fork/Join框架只是任务拆分优化的较好
        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(start, end).toMillis() + "ms");
        System.out.println("结果为：" + result);
    }
}

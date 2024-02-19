package com.itfeng.antic.forkjoinpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author: lf
 * @creat: 2023/11/14 10:03
 * @describe: ExecutorService 多线程方式实现
 */
public class ExecutorServiceCalculatorImpl implements Calculator {
    private int parallism;
    private ExecutorService pool;

    public ExecutorServiceCalculatorImpl() {
        parallism = Runtime.getRuntime().availableProcessors(); // CPU的核心数 默认就用cpu核心数了
        pool = Executors.newFixedThreadPool(parallism);
    }

    //@Override
    //public long sumUp(long[] numbers) {
    //    List<Future<Long>> results = new ArrayList<>();
    //
    //    // 把任务分解为 n 份，交给 n 个线程处理   4核心 就等分成4份呗
    //    // 然后把每一份都扔个一个SumTask线程 进行处理
    //    int part = numbers.length / parallism;
    //    for (int i = 0; i < parallism; i++) {
    //        int from = i * part; //开始位置
    //        int to = (i == parallism - 1) ? numbers.length - 1 : (i + 1) * part - 1; //结束位置
    //        //扔给线程池计算
    //        results.add(pool.submit(new SumTask(numbers, from, to)));
    //    }
    //
    //    // 把每个线程的结果相加，得到最终结果 get()方法 是阻塞的
    //    // 优化方案：可以采用CompletableFuture来优化  JDK1.8的新特性
    //    long total = 0L;
    //    for (Future<Long> f : results) {
    //        try {
    //            total += f.get();
    //        } catch (Exception ignore) {
    //        }
    //    }
    //    return total;
    //}

    //使用jdk1.8 CompletableFuture优化
    @Override
    public long sumUp(long[] numbers) {
        List<CompletableFuture<Long>> futures = new ArrayList<>();

        int part = numbers.length / parallism;
        for (int i = 0; i < parallism; i++) {
            int from = i * part;
            int to = (i == parallism - 1) ? numbers.length - 1 : (i + 1) * part - 1;
            // Use CompletableFuture.supplyAsync to submit tasks to the pool
            CompletableFuture<Long> future = CompletableFuture.supplyAsync(() ->
                            new SumTask(numbers, from, to).call(),
                    pool);
            futures.add(future);
        }

        // Combine all CompletableFuture results using thenApply
        CompletableFuture<List<Long>> allOf = CompletableFuture.allOf(
                        futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()));

//        当我们调用 `CompletableFuture.allOf` 时，它会接受一个 `CompletableFuture` 数组作为参数，返回一个新的 `CompletableFuture<Void>`。这个新的 `CompletableFuture` 在传入的所有 `CompletableFuture` 完成时也会完成。
//
//`futures.toArray(new CompletableFuture[0])` 把 `futures` 这个 `CompletableFuture<Long>` 列表转换成一个 `CompletableFuture<Long>` 数组。传入空数组是为了确保数组的类型正确。
//
//        接下来是 `.thenApply(v -> ...)`，它是一个方法，用于对已完成的 `CompletableFuture` 的结果应用一个函数。在这里，这个函数接受一个 `Void` 类型（因为`CompletableFuture.allOf` 返回的是 `CompletableFuture<Void>`），并返回一个 `List<Long>`。
//
//`v -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList())` 是一个Lambda表达式，表示由 `thenApply` 应用的函数。
//
//        在 `futures.stream().map(CompletableFuture::join).collect(Collectors.toList())` 中：
//
//        - `futures.stream()` 将 `futures` 这个 `CompletableFuture<Long>` 列表转换为一个 `CompletableFuture<Long>` 流。
//        - `.map(CompletableFuture::join)` 对流中的每个 `CompletableFuture` 应用 `join` 方法，该方法等待 `CompletableFuture` 完成并返回其结果。
//        - `.collect(Collectors.toList())` 将结果收集到一个 `List<Long>` 中。
//
//        因此，整个表达式 `CompletableFuture<List<Long>> allOf = ...` 创建了一个新的 `CompletableFuture`，当 `futures` 列表中的所有单独的 `CompletableFuture` 完成时，这个新的 `CompletableFuture` 也会完成。然后，`thenApply` 部分将这些 `CompletableFuture` 的结果转换为一个 `Long` 值的列表。
        


        // Join the final CompletableFuture and sum the results
        long total = allOf.join().stream()
                .mapToLong(Long::longValue)
                .sum();

        // Shutdown the pool
        pool.shutdown();

        try {
            pool.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return total;
    }

    //处理计算任务的线程
    private static class SumTask implements Callable<Long> {
        private long[] numbers;
        private int from;
        private int to;

        public SumTask(long[] numbers, int from, int to) {
            this.numbers = numbers;
            this.from = from;
            this.to = to;
        }

        @Override
        public Long call() {
            long total = 0;
            for (int i = from; i <= to; i++) {
                total += numbers[i];
            }
            return total;
        }
    }
}

package com.itfeng.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: lf
 * @creat: 2023/11/13 17:00
 * @describe: 线程池并行任务
 */
public class ExecutorsTest {
    public static void main(String[] args) {
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // 创建任务列表
        List<Callable<String>> tasks = new ArrayList<>();

        // 添加并行查询任务
        tasks.add(() -> performQuery("Query 1"));
        tasks.add(() -> performQuery("Query 2"));
        tasks.add(() -> performQuery("Query 3"));

        try {
            // 执行并行查询
            List<Future<String>> results = executorService.invokeAll(tasks);

            // 处理查询结果
            for (Future<String> result : results) {
                System.out.println(result.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池
            executorService.shutdown();
        }
    }

    private static String performQuery(String query) {
        // 执行查询逻辑，返回查询结果
        return "Result of " + query;
    }
}

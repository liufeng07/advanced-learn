package com.itfeng.concurrent;

import java.util.concurrent.CompletableFuture;

/**
 * @author: lf
 * @creat: 2023/11/13 16:54
 * @describe: CompletableFuture 并行查询
 */
public class CompletableFutureTest2 {
    public static void main(String[] args) throws InterruptedException {
        // 并行执行查询
        CompletableFuture<String> query1 = CompletableFuture.supplyAsync(() -> performQuery("Query 1"));
        CompletableFuture<String> query2 = CompletableFuture.supplyAsync(() -> performQuery("Query 2"));
        CompletableFuture<String> query3 = CompletableFuture.supplyAsync(() -> performQuery("Query 3"));

        // 处理查询结果
        query1.thenAccept(System.out::println);
        query2.thenAccept(System.out::println);
        query3.thenAccept(System.out::println);

        Thread.sleep(1000);

        // 等待所有查询完成
        CompletableFuture.allOf(query1, query2, query3).join();
        System.out.println("全部执行完成");
    }

    private static String performQuery(String query) {
        // 执行查询逻辑，返回查询结果
        return "Result of " + query;
    }
}

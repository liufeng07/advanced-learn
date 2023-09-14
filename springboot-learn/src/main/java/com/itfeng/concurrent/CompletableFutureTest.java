package com.itfeng.concurrent;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;

import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * @author: lf
 * @creat: 2023/5/8 11:36
 * @describe:
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws InterruptedException {
        Map<String, Object> rpcDto = new LinkedHashMap<>();
        rpcDto.put("method", "30");
        List<Map<String, String>> params = new ArrayList<>();
        Map<String, String> param = new LinkedHashMap<>();
        param.put("deviceNumber", "12");
        params.add(param);
        rpcDto.put("params", params);

        Map<String, Boolean> res = new LinkedHashMap<>();

        List<CompletableFuture<Map<String, Integer>>> futures = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            CompletableFuture<Map<String, Integer>> future = CompletableFuture.supplyAsync(
                    () -> {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
                        stringIntegerHashMap.put("age",20);

                        return stringIntegerHashMap;
                    }
            );
            future.thenAccept(results -> results.keySet().forEach(resultkey -> {
                System.out.println("future accepted, setting res as true");
                res.put(resultkey, results.get(resultkey) == 0);
            }));
            futures.add(future);
        }
        CompletableFuture<Void> combinedFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        combinedFutures.thenAccept(r ->
                System.out.println("combinedFuture accepted")
        );

        combinedFutures.whenComplete((v, th) -> {
            System.out.println("all completed");
            System.out.println(v);
        }).join();


        System.out.println(res);

        System.out.println("watting...");
        Thread.sleep(10);

        System.out.println(res);
    }


}

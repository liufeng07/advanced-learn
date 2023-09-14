package com.itfeng.function;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * @author liuf
 * @date 2022年01月14日 3:01 下午
 * Predicate 使用案例
 */
public class PredicateDemo {
    public static void main(String[] args) {
        String[] arr = {"马尔扎哈,女", "马尔扎哈,男", "赵丽颖,女"};
        ArrayList<String> list1 = filter(arr, (String s) -> {
            return s.split(",")[1].equals("女");
        }, (String s) -> {
            return s.split(",")[0].length() >= 4;
        });
        for (String s : list1) {
            System.out.println(s);
        }
    }

    public static ArrayList<String> filter(String[] arr, Predicate<String> pre1, Predicate<String> pre2) {
        ArrayList<String> list = new ArrayList<>();
        for (String s : arr) {
            boolean b = pre1.and(pre2).test(s);
            if (b) {
                list.add(s);
            }
        }
        return list;
    }
}

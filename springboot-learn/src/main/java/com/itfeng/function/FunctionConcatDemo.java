package com.itfeng.function;

import java.util.function.Function;

/**
 * @author liuf
 * @date 2022年01月14日 2:58 下午
 * function 自定义函数拼接
 */
public class FunctionConcatDemo {
    public static void main(String[] args) {
        String s ="炸丽颖,12";
        int pinjie1 = pinjie(s, str -> str.split(",")[1], str -> Integer.parseInt(str), i -> i + 100);
        System.out.println(pinjie1);
    }
    public static int pinjie(String s, Function<String,String> fun1, Function<String,Integer> fun2, Function<Integer,Integer> fun3){
        return fun1.andThen(fun2).andThen(fun3).apply(s);
    }
}

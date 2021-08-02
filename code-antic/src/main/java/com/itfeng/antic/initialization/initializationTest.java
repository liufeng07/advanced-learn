package com.itfeng.antic.initialization;

/**
 * 类的初始化顺序
 */
public class initializationTest {
    private static String staticField = "静态变量";
    private String field = "普通变量";

    static {
        System.out.println(staticField);
        System.out.println("静态代码块");
    }

    {
        System.out.println(field);
        System.out.println("初始化块");
    }

    private initializationTest() {
        System.out.println("构造器");
    }

    public static void main(String[] args) {
        new initializationTest();
    }
}

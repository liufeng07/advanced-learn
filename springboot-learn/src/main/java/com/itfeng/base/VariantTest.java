package com.itfeng.base;

/**
 * 变量测试
 */
public class VariantTest {
    //静态变量
    public static int staticVar = 0;
    //实例变量
    public int instanceVar = 0;

    public VariantTest() {
        staticVar++;
        instanceVar++;
        System.out.println("staticVar =" + staticVar + ",instanceVar ="+instanceVar);
    }

    public static void main(String[] args) {
        VariantTest variantTest = new VariantTest();
        VariantTest variantTest2 = new VariantTest();
        VariantTest variantTest3 = new VariantTest();
    }
}

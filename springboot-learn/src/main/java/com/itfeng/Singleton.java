package com.itfeng;

/**
 * @author liuf
 * @date 2022年01月21日 11:50 上午
 */
public enum Singleton {

    INSTANCE;

    public void doSomething() {
        System.out.println("doSomething");
    }


    public static void main(String[] args) {
        Singleton.INSTANCE.doSomething();
    }
}

package com.itfeng.antic.design.proxy.static2;

/**
 * 静态代理
 * 在某些情况下，一个对象不合适或者不能直接引用另一个对象，
 * 而代理对象可以在客户端和目标对象之间起到中介的作用。
 */
public class TestMain {
    public static void main(String[] args) {
        Customer customer = new Customer();
        House house = new Proxy(customer);
        house.maifang();
    }
}

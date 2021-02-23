package com.itfeng.antic.design.strategy.payment;

public class TestMain {
    public static void main(String[] args) {
        Order order = new Order("1", "2323235655956", 325);
        System.out.println(order.pay(PayStrategy.ALI_PAY));
    }
}

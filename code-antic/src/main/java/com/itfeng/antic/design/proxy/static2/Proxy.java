package com.itfeng.antic.design.proxy.static2;

public class Proxy implements House {

    // 代理对象
    private Customer customer;

    public Proxy(Customer customer) {
        this.customer = customer;
    }


    @Override
    public void maifang() {
        System.out.println("我是中介,你买房开始交给我啦!");
        customer.maifang();
        System.out.println("我是中介,你买房结束啦...");
    }
}

package com.itfeng.antic.design.proxy.static2;

public class Customer implements House {
    @Override
    public void maifang() {
        System.out.println("我是买家,终于可以买房了");
    }
}

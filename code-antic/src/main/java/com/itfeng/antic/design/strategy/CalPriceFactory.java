package com.itfeng.antic.design.strategy;

/**
 * 使用一个标准的简单工厂来改进一下策略模式
 */
public class CalPriceFactory {
    private CalPriceFactory() {
    }

    //有个缺点（假如修改会员打折策略）。违背开闭原则，对修改关闭，对扩展开放

    //根据客户的总金额产生相应的策略
    public static CalPrice createCalPrice(Customer2 customer) {
        if (customer.getTotalAmount() > 3000) {//3000则改为金牌会员计算方式
            return new GoldVip();
        } else if (customer.getTotalAmount() > 2000) {//类似
            return new SuperVip();
        } else if (customer.getTotalAmount() > 1000) {//类似
            return new Vip();
        } else {
            return new Common();
        }
    }
}

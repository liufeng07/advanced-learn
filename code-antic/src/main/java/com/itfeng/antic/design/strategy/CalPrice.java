package com.itfeng.antic.design.strategy;

//计算价格策略的接口
public interface CalPrice {
    //根据原价返回一个最终的价格
    Double calPrice(Double originalPrice);
}

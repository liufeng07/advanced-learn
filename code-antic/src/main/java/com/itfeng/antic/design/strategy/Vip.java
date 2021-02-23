package com.itfeng.antic.design.strategy;

/**
 * 会员
 */
public class Vip implements CalPrice {
    @Override
    public Double calPrice(Double originalPrice) {
        return originalPrice * 0.8;
    }
}

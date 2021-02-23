package com.itfeng.antic.design.strategy;

/**
 * 黄金会员
 */
public class GoldVip implements CalPrice {
    @Override
    public Double calPrice(Double originalPrice) {
        return originalPrice * 0.5;
    }
}

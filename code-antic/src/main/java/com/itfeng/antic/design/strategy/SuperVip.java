package com.itfeng.antic.design.strategy;

/**
 * 超级会员
 */
public class SuperVip implements CalPrice {
    @Override
    public Double calPrice(Double originalPrice) {
        return originalPrice * 0.7;
    }
}

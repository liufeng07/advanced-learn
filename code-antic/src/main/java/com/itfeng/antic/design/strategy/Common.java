package com.itfeng.antic.design.strategy;

/**
 * 原价
 */
public class Common implements CalPrice {
    @Override
    public Double calPrice(Double originalPrice) {
        return originalPrice;
    }
}

package com.itfeng.antic.ifelse.ordinary;

import com.itfeng.antic.ifelse.PayService;

/**
 * 非框架实现
 */
public class ChannelRouterUtil {
    public static String result(int channel,int Amount){
        PayService payService = PayServiceFactory.getInstance().get(channel);
        if(payService == null){
            throw new IllegalArgumentException("暂未支持该渠道！！！");
        }
        return  payService.Order(Amount);
    }


    public static void main(String[] args) {
        String result = result(1, 1);
        System.out.println(result);
    }
}

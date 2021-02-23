package com.itfeng.antic.ifelse.ordinary;

import com.itfeng.antic.ifelse.PayService;

public class AliPayServiceImpl implements PayService {
    @Override
    public String Order(int Amount) {
        return "AliPay Order Success !!!" + Amount;
    }

    @Override
    public int getChannel() {
        return ChannelEnum.ALIPAY.getChannel();
    }
}

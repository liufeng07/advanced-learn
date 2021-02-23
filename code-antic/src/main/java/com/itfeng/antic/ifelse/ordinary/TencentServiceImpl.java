package com.itfeng.antic.ifelse.ordinary;

import com.itfeng.antic.ifelse.PayService;

public class TencentServiceImpl implements PayService {
    @Override
    public String Order(int Amount) {
        return "TENCENT Order Success!!!" + Amount;
    }

    @Override
    public int getChannel() {
        return ChannelEnum.TENCENT.getChannel();
    }
}

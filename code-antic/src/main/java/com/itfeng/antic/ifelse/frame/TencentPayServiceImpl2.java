package com.itfeng.antic.ifelse.frame;

import com.itfeng.antic.ifelse.ordinary.ChannelEnum;
import com.itfeng.antic.ifelse.PayService;
import org.springframework.stereotype.Service;

@Service
public class TencentPayServiceImpl2 implements PayService {
    @Override
    public String Order(int Amount) {
        return "TENCENT Order Success !!!" + Amount;
    }

    @Override
    public int getChannel() {
        return ChannelEnum.TENCENT.getChannel();
    }
}

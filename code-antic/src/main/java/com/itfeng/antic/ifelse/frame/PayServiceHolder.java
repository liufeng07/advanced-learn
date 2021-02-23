package com.itfeng.antic.ifelse.frame;

import com.itfeng.antic.ifelse.PayService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class PayServiceHolder {
    @Resource
    private Map<String, PayService> payServiceMap;


    //获取具体的策略
    PayService setService(Integer mode) {
        if (mode != null) {
            String serviceType = ChannelEnum2.getServiceTypeEnum(mode);
            PayService channelService = payServiceMap.get(serviceType);
            if (channelService != null) {
                return channelService;
            }
        }
        throw new RuntimeException("暂不支持该渠道 mode=" + mode);
    }
}

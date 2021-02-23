package com.itfeng.antic.ifelse.ordinary;

import com.itfeng.antic.ifelse.PayService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PayServiceFactory {
    private Map<Integer, PayService> map;

    // 该工厂管理所有的策略接口实现类  通过构造函数方式传入
    public PayServiceFactory() {
        List<PayService> payServices = new ArrayList<>();
        payServices.add(new AliPayServiceImpl());
        payServices.add(new TencentServiceImpl());
        map = new ConcurrentHashMap<>();
        // 把所有策略实现的集合List转为Map
        for (PayService payService : payServices) {
            map.put(payService.getChannel(), payService);
        }
    }

    //静态内部类单例
    public static class Holder {
        //静态变量 jvm保证实例化一次
        public static PayServiceFactory instance = new PayServiceFactory();
    }

    //在构造方法的时候 初始化需要的 PayServiceFactory
    public static PayServiceFactory getInstance() {
        return Holder.instance;
    }

    //根据channel 获取相应的策略实现类
    public PayService get(Integer channel) {
        return map.get(channel);
    }
}

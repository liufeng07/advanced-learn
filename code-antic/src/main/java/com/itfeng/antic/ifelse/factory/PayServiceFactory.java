package com.itfeng.antic.ifelse.factory;

import com.itfeng.antic.ifelse.PayService;
import com.itfeng.antic.ifelse.frame.ChannelEnum2;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * eg: spring中结合策略模式来使用简单工厂方法模式
 * <p>
 * 注意：
 * 从ApplicationContextAware获取ApplicationContext上下文的情况，
 * 仅仅适用于当前运行的代码和已启动的Spring代码处于同一个Spring上下文，
 * 否则获取到的ApplicationContext是空的。
 * 定时任务是没办法获取到项目所在Spring容器启动之后的ApplicationContext
 */
@Component
public class PayServiceFactory implements ApplicationContextAware {

    private static Map<String, PayService> serviceMap;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        serviceMap = applicationContext.getBeansOfType(PayService.class);
    }

    public PayService getService(Integer payType) {
        PayService payService = serviceMap.get(ChannelEnum2.getServiceTypeEnum(payType));
        if (payService == null) {
            throw new RuntimeException("不存在该支付方式");
        }
        return payService;
    }
}

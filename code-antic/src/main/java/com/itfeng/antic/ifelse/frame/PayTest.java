package com.itfeng.antic.ifelse.frame;

import com.itfeng.antic.ifelse.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PayTest {

    @Resource
    private PayServiceHolder payServiceHolder;

    @Test
    public void test() {
        PayService payService = payServiceHolder.setService(222);
        String order = payService.Order(1);
        System.out.println(order);
    }
}

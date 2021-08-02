package com.itfeng.controller;

import com.itfeng.utils.GenerateIdUtils;
import com.itfeng.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuf
 * @date 2021年08月02日 11:20 上午
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class MainTestController {
    @Autowired
    private GenerateIdUtils generateIdUtil;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping(  "/generateIds")
    public Object generateIds() {
        String s = generateIdUtil.generateId();
        System.out.println(s);
        return s;
    }
}

package com.itfeng.config;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:fcc
 * @Date:2021-03-10
 * @Time:15:55
 */
@Configuration
public class RedissonConfig {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private String port;
    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("redis://" + host + ":" + port + "");
        if (StringUtils.isNotBlank(password)){
            singleServerConfig.setPassword(password);
        }
//                .useClusterServers()
//                .setScanInterval(2000);
//                         .addNodeAddress("redis://10.0.29.30:6379", "redis://10.0.29.95:6379");
        return Redisson.create(config);
    }

}
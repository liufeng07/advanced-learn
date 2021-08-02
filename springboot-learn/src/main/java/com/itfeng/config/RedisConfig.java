package com.itfeng.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.time.Duration;

@Configuration
@EnableCaching
/**
 * <p>
 *  redis缓存配置
 *
 * @author linshaochuan
 * @since 2019-07-23
 */
@Slf4j
public class RedisConfig {
    private final static String sysName = "conlin";
    private final static String appName = "message";
    @Resource
    private LettuceConnectionFactory lettuceConnectionFactory;
    /**
     * @description 自定义的缓存key的生成策略 若想使用这个key
     *              只需要讲注解上keyGenerator的值设置为keyGenerator即可</br>
     * @return 自定义策略生成的key
     */
    @Bean
    public KeyGenerator keyGenerator() {
        //  系统名:应用名:业务含义:值
        return (target, method,  params)-> {
            StringBuffer sb = new StringBuffer();
            sb.append(sysName);
            sb.append(":");
            sb.append(appName);
            sb.append(":");
            sb.append("Login");
            sb.append(target.getClass().getSimpleName() + ".");
            sb.append(method.getName());
            sb.append(":");
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }
    /**
     * RedisTemplate配置
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        // 设置序列化
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
        // 配置redisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
//        RedisSerializer<?> stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(keySerializer());// key序列化
        redisTemplate.setValueSerializer(valueSerializer());// value序列化
        redisTemplate.setHashKeySerializer(keySerializer());// Hash key序列化
        redisTemplate.setHashValueSerializer(valueSerializer());// Hash value序列化
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 缓存配置管理器
     */
    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory factory) {
        // 以锁写入的方式创建RedisCacheWriter对象
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(factory);
        // 创建默认缓存配置对象
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                // 1小时缓存失效
                .entryTtl(Duration.ofHours(1))
                // 不缓存null值
                .disableCachingNullValues();
        RedisCacheManager redisCacheManager = RedisCacheManager.builder(factory)
                .cacheDefaults(config)
                .transactionAware()
                .build();
        log.info("自定义RedisCacheManager加载完成");
//        RedisCacheManager cacheManager = new RedisCacheManager(writer, config);
        return redisCacheManager;
    }


//    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);
//
//

    // key键序列化方式
    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }
    //
    // value值序列化方式
    private GenericJackson2JsonRedisSerializer valueSerializer(){
        return new GenericJackson2JsonRedisSerializer();
    }

    /**
     * 获取缓存的key值
     * @param busMean
     * @param value
     * @return
     */
    public static String getCacheKey(String busMean,String value){
        StringBuffer sb = new StringBuffer();
        sb.append(sysName);
        sb.append(":");
        sb.append(appName);
        sb.append(":");
        sb.append(busMean);
        sb.append(":");
        sb.append(value);
        return sb.toString();
    }
}

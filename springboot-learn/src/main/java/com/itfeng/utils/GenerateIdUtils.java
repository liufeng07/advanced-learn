package com.itfeng.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuf
 * @date 2021年08月02日 11:12 上午
 * <p>
 * 使用redis生成订单号
 * <p>
 * 思路：
 * 利用redis单线程实现自增不重复。
 * yyyMMddHHmmss(14位) + 5位自增数字（前面不够5位的补0），组成19位不重复的数字。
 * 把yyyMMddHHmmss作为key存到redis,设置过期时间为2s,在同一个key的时候，后5为从1自增，也就是说，同一秒内最大支持99999个请求，可以满足需求了。
 */
@Service
public class GenerateIdUtils {
    public static final String REDIS_KEY_PREFIX = "userId:orderId:";

    @Autowired
    public RedisUtil redisUtil;
    @Autowired
    public RedissonUtil redissonUtil;

    /**
     * 获取有过期时间的自增长ID
     *
     * @param key
     * @return
     */
    private long generate(String key) {
        String redisKey = REDIS_KEY_PREFIX + key;
        Long autoID = redisUtil.incr(redisKey, 1);
        // 查询 key 是否存在， 不存在返回 1 ，存在的话则自增加1
        if (autoID == 1) {
            // 设置key过期时间, 保证每天的流水号从1开始
            redisUtil.expire(redisKey, 2);//2秒过期
        }
        return autoID;
    }


    public String generateId() {
        // 生成id为当前日期（yyMMddHHmmss）+6位（从000000开始不足位数补0）
        LocalDateTime now = LocalDateTime.now();
        String idPrefix = getIdPrefix(now);// 生成yyyyMMddHHmmss
        //String id = idPrefix + String.format("%1$05d", generate(idPrefix));
        String id = idPrefix + StringUtils.leftPad(String.valueOf(generate(idPrefix)), 5, "0");
        return id;
    }

    public static String getIdPrefix(LocalDateTime now) {
        return now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    public Date getExpireAtTime(LocalDateTime now) {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = now.plusSeconds(20);
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());
        return date;
    }
}

package com.itfeng.utils;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author liuf
 * @date 2021年07月13日 2:08 下午
 */
@Slf4j
@Component
public class RedissonUtil {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 加锁
     */
    public boolean buildLock(String lockKey) {
        return buildLock(lockKey, 5L, 20L);
    }


    /**
     * 加锁
     */
    public boolean buildLock(String lockKey, long waitTime, long leaseTime) {
        boolean result = false;
        RLock lock = redissonClient.getLock(lockKey);
        try {
            // 第一个参数是等待时间，n毫秒内获取不到锁，则直接返回
            // 第二个参数 m毫秒后强制释放锁
            result = lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            String msg = String.format("LOCK FAILED: key=%s||tryLockTime=%s||lockExpiredTime=%s", lockKey, waitTime, leaseTime);
            log.error("尝试加锁失败：{}", msg, e);
        }
        return result;
    }

    /**
     * 解锁
     */
    public void releaseLock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            //isHeldByCurrentThread 判断当前线程是否持有此锁定
            if (lock != null && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        } catch (Exception e) {
            String msg = String.format("UNLOCK FAILED: key=%s", lockKey);
            log.error("尝试解锁失败:{}", msg, e);
        }
    }
}

package com.itfeng.cache;

import java.lang.ref.SoftReference;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author liuf
 * @date 2021年12月16日 10:19 上午
 * 自己实现的本地缓存
 */
public class MapCacheDemo {

    public static void main(String[] args) throws InterruptedException {
        MapCacheDemo mapCacheDemo = new MapCacheDemo();
        mapCacheDemo.add("1","hello world",10);
        Object o = mapCacheDemo.get("1");
        String o1 = (String) o;
        System.out.println(o1);

        Thread.sleep(6000);

        Object o2 = mapCacheDemo.get("1");
        String o3 = (String) o2;
        System.out.println(o3);
    }

    // 我使用了  ConcurrentHashMap，线程安全的要求。
    //我使用SoftReference <Object>  作为映射值，因为软引用可以保证在抛出OutOfMemory之前，如果缺少内存，将删除引用的对象。
    //在构造函数中，我创建了一个守护程序线程，每5秒扫描一次并清理过期的对象。
    private static final int CLEAN_UP_PERIOD_IN_SEC = 5;

    private final ConcurrentHashMap<String, SoftReference<CacheObject>> cache = new ConcurrentHashMap<>();

    public MapCacheDemo() {
        Thread cleanerThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(CLEAN_UP_PERIOD_IN_SEC * 1000);
                    cache.entrySet().removeIf(entry -> Optional.ofNullable(entry.getValue()).map(SoftReference::get).map(CacheObject::isExpired).orElse(false));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        cleanerThread.setDaemon(true);
        cleanerThread.start();
    }

    public void add(String key, Object value, long periodInMillis) {
        if (key == null) {
            return;
        }
        if (value == null) {
            cache.remove(key);
        } else {
            long expiryTime = System.currentTimeMillis() + periodInMillis;
            cache.put(key, new SoftReference<>(new CacheObject(value, expiryTime)));
        }
    }

    public void remove(String key) {
        cache.remove(key);
    }

    public Object get(String key) {
        return Optional.ofNullable(cache.get(key)).map(SoftReference::get).filter(cacheObject -> !cacheObject.isExpired()).map(CacheObject::getValue).orElse(null);
    }

    public void clear() {
        cache.clear();
    }

    public long size() {
        return cache.entrySet().stream().filter(entry -> Optional.ofNullable(entry.getValue()).map(SoftReference::get).map(cacheObject -> !cacheObject.isExpired()).orElse(false)).count();
    }

    // 缓存对象value
    private static class CacheObject {
        private Object value;
        private long expiryTime;

        private CacheObject(Object value, long expiryTime) {
            this.value = value;
            this.expiryTime = expiryTime;
        }

        boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}

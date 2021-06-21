package com.itfeng.antic.thread;

/**
 * @author liuf
 * @date 2021年06月01日 10:04 上午
 * 定义回调接口 便于接口的通用性，这里定义了泛型
 */
public interface TaskCallable<T> {
    T callable(T t);
}

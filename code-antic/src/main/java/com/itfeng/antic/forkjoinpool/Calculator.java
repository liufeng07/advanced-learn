package com.itfeng.antic.forkjoinpool;

/**
 * @author: lf
 * @creat: 2023/11/14 09:57
 * @describe:
 */
public interface Calculator {
    /**
     * 把传进来的所有numbers做求和处理
     * @param numbers
     * @return 总和
     */
    long sumUp(long[] numbers);
}

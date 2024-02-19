package com.itfeng.antic.forkjoinpool;

/**
 * @author: lf
 * @creat: 2023/11/14 09:57
 * @describe: 普通for循环实现
 */
public class ForLoopImpl implements Calculator{
    @Override
    public long sumUp(long[] numbers) {
        long total = 0;
        for (long number : numbers) {
            total+= number;
        }
        return total;
    }
}

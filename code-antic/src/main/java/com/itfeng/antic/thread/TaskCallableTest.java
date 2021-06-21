package com.itfeng.antic.thread;

/**
 * @author liuf
 * @date 2021年06月01日 10:12 上午
 * 测试回调 异步获取任务结果
 * 具体的方案是：定义一个回调接口，并在接口中定义接收任务结果数据的方法，具体逻辑在回调接口的实现类中完成。
 * 将回调接口与任务参数一同放进线程或线程池中运行，任务运行后调用接口方法，执行回调接口实现类中的逻辑来处理结果数据。
 */
public class TaskCallableTest {
    public static void main(String[] args) {
        //回调函数
        TaskCallable<TaskResult> taskCallable = new TaskHandler();
        TaskExecutor taskExecutor = new TaskExecutor(taskCallable, "测试回调任务");
        new Thread(taskExecutor).start();
    }
}

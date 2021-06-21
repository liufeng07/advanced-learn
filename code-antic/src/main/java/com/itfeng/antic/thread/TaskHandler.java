package com.itfeng.antic.thread;


/**
 * @author liuf
 * @date 2021年06月01日 10:07 上午
 * 回调函数的实现类主要用来对任务的返回结果进行相应的业务处理
 */
public class TaskHandler implements TaskCallable<TaskResult> {
    @Override
    public TaskResult callable(TaskResult taskResult) {
        //TODO: 拿到结果数据后进一步处理
        System.out.println(taskResult.toString());
        return taskResult;
    }
}

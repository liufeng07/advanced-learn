package com.itfeng.antic.thread;

/**
 * @author liuf
 * @date 2021年06月01日 10:09 上午
 * 任务的执行类是具体执行任务的类，实现Runnable接口，在此类中定义一个回调接口类型的成员变量和一个String类型的任务参数
 * （模拟任务的参数），并在构造方法中注入回调接口和任务参数。在run方法中执行任务，任务完成后将任务的结果数据封装成
 * TaskResult对象，调用回调接口的方法将TaskResult对象传递到回调方法中
 */
public class TaskExecutor implements Runnable {
    private TaskCallable<TaskResult> taskCallable;
    private String taskParameter;

    public TaskExecutor(TaskCallable<TaskResult> taskCallable, String taskParameter) {
        this.taskCallable = taskCallable;
        this.taskParameter = taskParameter;
    }

    @Override
    public void run() {
        //TODO 一系列业务逻辑,将结果数据封装成TaskResult对象并返回
        TaskResult result = new TaskResult();
        result.setTaskStatus(1);
        result.setTaskMessage(this.taskParameter);
        result.setTaskResult("异步回调成功");
        taskCallable.callable(result);
    }
}

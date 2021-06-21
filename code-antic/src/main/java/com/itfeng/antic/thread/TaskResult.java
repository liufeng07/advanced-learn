package com.itfeng.antic.thread;

/**
 * @author liuf
 * @date 2021年06月01日 10:06 上午
 * 任务执行结果
 */
public class TaskResult {
    private static final long serialVersionUID = 8678277072402730062L;
    /*** 任务状态 */
    private Integer taskStatus;
    /*** 任务消息 */
    private String taskMessage;
    /*** 任务结果数据 */
    private String taskResult;

    //省略getter和setter方法
    @Override
    public String toString() {
        return "TaskResult{" + "taskStatus=" + taskStatus + ", taskMessage='" + taskMessage + '\'' + ", taskResult='" + taskResult + '\'' + '}';
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskMessage() {
        return taskMessage;
    }

    public void setTaskMessage(String taskMessage) {
        this.taskMessage = taskMessage;
    }

    public String getTaskResult() {
        return taskResult;
    }

    public void setTaskResult(String taskResult) {
        this.taskResult = taskResult;
    }
}

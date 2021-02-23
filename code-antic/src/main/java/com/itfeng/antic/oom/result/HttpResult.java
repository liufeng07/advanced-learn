package com.itfeng.antic.oom.result;

import java.io.Serializable;

/**
 * 返回数据的包装类
 * 
 * 1、构造器私有，外部不可以直接创建；
 * 2、只可以调用统一返回类的静态方法返回对象；
 * 3、success 是一个Boolean 值，通过这个值，可以直接观察到该次请求是否成功；
 * 4、data 表示响应数据，用于请求成功后，返回客户端需要的数据
 * @param <T> 泛型类
 */
public class HttpResult<T> implements Serializable {

    /**
     * 是否响应成功
     */
    private Boolean success;
    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应数据
     */
    private T data;
    /**
     * 错误信息
     */
    private String message;

    // 构造器开始

    /**
     * 无参构造器(构造器私有，外部不可以直接创建)
     */
    private HttpResult() {
        this.code = 200;
        this.success = true;
    }

    /**
     * 有参构造器
     *
     * @param obj
     */
    private HttpResult(T obj) {
        this.code = 200;
        this.data = obj;
        this.success = true;
    }

    /**
     * 有参构造器
     *
     * @param resultCode
     */
    private HttpResult(ResultCodeEnum resultCode) {
        this.success = false;
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }
    // 构造器结束

    /**
     * 通用返回成功（没有返回结果）
     *
     * @param <T>
     * @return
     */
    public static HttpResult success() {
        return new HttpResult();
    }
    /**
     * 返回成功（有返回结果）
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> HttpResult<T> success(T data) {
        return new HttpResult<T>(data);
    }

    /**
     * 通用返回失败
     *
     * @param resultCode
     * @param <T>
     * @return
     */
    public static <T> HttpResult<T> failure(ResultCodeEnum resultCode) {
        return new HttpResult<T>(resultCode);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "success=" + success +
                ", code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }

    public static void main(String[] args) {
        //通用返回成功（没有返回结果
        HttpResult success = HttpResult.success();
        System.out.println(success.toString());
        //返回成功（有返回结果
        HttpResult<String> hello = HttpResult.success("hello");
        System.out.println(hello.toString());
        //通用返回失败
        HttpResult failure = HttpResult.failure(ResultCodeEnum.NOT_FOUND);
        System.out.println(failure.toString());
    }
}

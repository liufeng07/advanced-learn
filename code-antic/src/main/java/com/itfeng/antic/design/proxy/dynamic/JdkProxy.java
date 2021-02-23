package com.itfeng.antic.design.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 中介
 * 动态代理
 * java提供便捷的动态代理接口 InvocationHandler，实现该接口需要重写其调用方法invoke
 *
 * JDK动态代理需要目标对象实现业务接口，代理类只需实现InvocationHandler接口。
 */
public class JdkProxy implements InvocationHandler {

    //代理者的引用
    private Object target;

    public JdkProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
        //代理的前期一些操作
        System.out.println("动态代理:我是中介,你买房开始交给我啦!");
        //通过反射调用其代理的方法
        Object invoke = method.invoke(target, args);
        System.out.println("我是中介,你买房结束啦...");
        return invoke;
    }
}

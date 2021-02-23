package com.itfeng.antic.design.proxy.dynamic;

import java.lang.reflect.Proxy;

/**
 * 动态代理
 * 动态代理需要实现InvocationHandler接口，
 * 相比静态代理而言有一个好处就是：不需要生成代理类，可扩展性强，方便后续的更改和操作。
 *
 * 由此可见：
 *  动态代理通过一个代理类来代理 N 多个被代理类，其本质是对代理者与被代理者进行解耦，使两者没有直接的耦合关系。
 *  相对而言静态代理则是能为给定接口下的实现类做代理，如果接口不同那么就需要重新定义不同的代理类，最为复杂，但是静态代理更符合面向对象原则。
 */
public class TestMain {
    public static void main(String[] args) {
        //客户
        Customer customer = new Customer();
        //构造一个动态代理对象
        JdkProxy jdkProxy = new JdkProxy(customer);
        //拿到代理者身上的classloader
        ClassLoader classLoader = customer.getClass().getClassLoader();
        //​ java动态代理是利用反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用InvokeHandler来处理
        House house = (House) Proxy.newProxyInstance(classLoader, customer.getClass().getInterfaces(), jdkProxy);
        house.maifang();
    }
}

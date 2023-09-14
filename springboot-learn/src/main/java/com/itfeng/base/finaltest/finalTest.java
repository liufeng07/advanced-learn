package com.itfeng.base.finaltest;

/**
 * 验证final修饰的变量是引用不能变，还是引用的对象不能变
 * StringBuilder 线程不安全 但速度快
 * 引用初始化以后不能被改变 --- 不是指变量的值不可变，而是指向的变量地址不可变
 */
public class finalTest {

    public static void main(String[] args) {
        final StringBuilder sb = new StringBuilder("haha");
        //同一对象的hashCode值相同
        System.out.println("sb中的内容是："+sb);
        System.out.println(sb+"的哈希编码是:"+sb.hashCode());
        sb.append("我变了");
        System.out.println("sb中的内容是："+sb);
        System.out.println(sb+"的哈希编码是:"+sb.hashCode());


        System.out.println("-----------------哈希值-------------------");
        finalTest test = new finalTest();
        System.out.println(test.hashCode());
        System.out.println(Integer.toHexString(test.hashCode()));
        System.out.println(test.getClass()+"@"+Integer.toHexString(test.hashCode()));
        System.out.println(test.getClass().getName()+"@"+Integer.toHexString(test.hashCode()));
        //在API中这么定义toString()等同于 getClass().getName() + '@' + Integer.toHexString(hashCode())
        //返回值是 a string representation of the object.
        System.out.println(test.toString());
    }
    //可以看出StringBuilder中的内容变了 而所指向的哈希编码却没发生变化，在Java中每一个对象都有自己独一无二的哈希编码，根据这个编码就可以找到相关的对象，也就是说，根据这个编码你可以独一无二地确定这个对象。
    //因而使用final关键字修饰一个变量时，是指引用变量不能变，引用变量所指向的对象中的内容还是可以改变的。
    //总得来说对于一个final变量，如果是基本数据类型的变量，则其数值一旦在初始化之后便不能更改；
    //如果是引用类型的变量，则在对其初始化之后便不能再让其指向另一个对象。

}

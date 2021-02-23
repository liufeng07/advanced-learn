package com.itfeng.annotation;

/**
 *  (注解只有成员变量，没有方法)
 *
 *
 *  #@Retention 定义了该注解的生命周期 取值.SOURCE｜.CLASS｜.RUNTIME
 *  1.SOURCE 注解只在源码阶段保留，在编译器完整编译之后，它将被丢弃忽视；
 *  2.CLASS: 注解只被保留到编译进行的时候，它并不会被加载到 JVM 中；
 *  3.RUNTIME: 注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们。
 *
 *  #@Target 表明该注解用于什么地方
 *  1.ElementType.CONSTRUCTOR: 对构造方法进行注解；
 *  2.ElementType.ANNOTATION_TYPE: 对注解进行注解；
 *  3.ElementType.FIELD: 对属性、成员变量、成员对象（包括 enum 实例）进行注解；
 *  4.ElementType.LOCAL_VARIABLE: 对局部变量进行注解；
 *  5.ElementType.METHOD: 对方法进行注解；
 *  6.ElementType.PACKAGE: 对包进行注解；
 *  7.ElementType.PARAMETER: 对描述参数进行注解；
 *  8.ElementType.TYPE: 对类、接口、枚举进行注解；
 *
 */


public class TestFruitAnnotation {
    public static void main(String[] args) {
        FruitInfoUtil.getFruitInfo(Apple.class);
    }
}

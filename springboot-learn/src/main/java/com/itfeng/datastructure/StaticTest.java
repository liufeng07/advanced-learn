package com.itfeng.datastructure;

/**
 * @author liuf
 * @date 2022年01月04日 5:28 下午
 */
public class StaticTest {
    //static关键字修饰内部类
    static class InnerClass{
        InnerClass(){
            System.out.println("====== 静态内部类======");
        }
        public void InnerMethod() {
            System.out.println("===== 静态内部方法=====");
        }
    }
    public static void main(String[] args) {
        //直接通过StaticTest类名访问静态内部类InnerClass
        StaticTest staticTest = new StaticTest();
        InnerClass innerClass = new InnerClass();
        //静态内部类可以和普通类一样使用
        innerClass.InnerMethod();
    }
}
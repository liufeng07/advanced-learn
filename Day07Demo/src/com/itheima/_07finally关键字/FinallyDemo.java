package com.itheima._07finally关键字;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
    目标：finally关键字

    用在捕获处理的异常格式中的，放在最后面。
        try{
            // 可能出现异常的代码！
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            // 无论代码是出现异常还是正常执行，最终一定要执行这里的代码！！
        }
        try: 1次。
        catch：0-N次  (如果有finally那么catch可以没有!!)
        finally: 0-1次

    finally的作用: 可以在代码执行完毕以后进行资源的释放操作。
    什么是资源？资源都是实现了Closeable接口的，都自带close()关闭方法！！

 final关键字主要用在三个地方：变量、方法、类。

 对于一个final变量，如果是基本数据类型的变量，则其数值一旦在初始化之后便不能更改；如果是引用类型的变量，则在对其初始化之后便不能再让其指向另一个对象。
 当用final修饰一个类时，表明这个类不能被继承。final类中的所有成员方法都会被隐式地指定为final方法。
 使用final方法的原因有两个。第一个原因是把方法锁定，以防任何继承类修改它的含义；第二个原因是效率。在早期的Java实现版本中，会将final方法转为内嵌调用。但是如果方法过于庞大，可能看不到内嵌调用带来的任何性能提升（现在的Java版本已经不需要使用final方法进行这些优化了）。类中所有的private方法都隐式地指定为final

 */
public class FinallyDemo {
    public static void main(String[] args) {
        //chu();
        System.out.println(chu1());
    }

    public static int chu1(){
        try{
            int a = 10 / 2 ;
            return a ;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }finally {
            System.out.println("=====finally被执行");
            return 111; // 不建议在finally中写return，会覆盖前面所有的return值!
        }
    }

    public static void chu(){
        InputStream is = null;
        try{
            //System.out.println(10/0);
            is = new FileInputStream("D:/cang.png");
            System.out.println(10 / 0 );

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("==finally被执行===");
            // 回收资源。用于在代码执行完毕以后进行资源的回收操作！
            try {
                if(is!=null)is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

package com.itfeng.antic.design.template;

/**
 * 核心点：具体的实现推迟给子类进行实现
 * （1）具体细节步骤实现定义在子类中，子类定义详细处理算法是不会改变算法整体结构。
 * （2）代码复用的基本技术，在数据库设计中尤为重要。
 * （3）存在一种反向的控制结构，通过一个父类调用其子类的操作，通过子类对父类进行扩展增加新的行为，符合“开闭原则”。
 */
public class TestMain {
    public static void main(String[] args) {
        PageBuilder builder = new MyPageBuilder();
        System.out.println(builder.bulidHtml());
    }
}

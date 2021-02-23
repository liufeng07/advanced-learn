package com.itfeng.antic.design.factory.method;

/**
 * 创建抽象工厂角色 - 人类抽象工厂
 */
public abstract class AbstractHumanFactory {
    //这里采用了泛型，对createHuman的输入参数产生两层限制
    //1.必须是Class类型
    //2.必须是Human的实现类
    public abstract <T extends Human> T createHuman(Class<T> c);
}

package com.itfeng.antic.design.sington;

/**
 * 一个简单的单例（未考虑并发的情况下）
 * 单例模式定义：
 *   所以可以使用单例模式的类都有一个共性，那就是这个类没有自己的状态。换句话说，这个类无论你实例化多少次，其实都是一样的。在整个应用中，同一时刻，有且只能有一种状态
 *
 * 如何保证实例唯一
 *      1.静态实例，带有static关键字的属性在每一个类中都是唯一的。
 *      2.限制客户端随意创造实例，即私有化构造方法，此为保证单例的最重要的一步。
 *      3.给一个公共的获取实例的静态方法，注意，是静态的方法，因为这个方法是在我们未获取到实例的时候就要提供给客户端调用的，所以如果是非静态的话，那就变成一个矛盾体了，因为非静态的方法必须要拥有实例才可以调用。
 *      4.判断只有持有的静态实例为null时才调用构造方法创造一个实例，否则就直接返回。
 */
public class Singleton {
    //一个静态的实例
    private static Singleton singleton;

    //一个私有的构造方法
    private Singleton() {
    }

    public static Singleton getInstance() {
        //并发访问的时候，第一个调用getInstance方法的线程A，在判断完singleton是null的时候，线程A就进入了if块准备创造实例，
        // 但是同时另外一个线程B在线程A还未创造出实例之前，就又进行了singleton是否为null的判断，这时singleton依然为null，
        // 所以线程B也会进入if块去创造实例，这时问题就出来了，有两个线程都进入了if块去创造实例，结果就造成单例模式并非单例。
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }

    //简单做法，将整个获取实例的方法设置同步。这样当一个线程访问这个方法时，其他的线程都要处于挂起状态
//    public synchronized static Singleton getInstance() {
//        if (singleton == null) {
//            singleton = new Singleton();
//        }
//        return singleton;
//    }

}

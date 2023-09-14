package com.itfeng.datastructure;

/**
 * @author liuf
 * @date 2022年01月04日 5:03 下午
 */
public class MainTest {
    //类变量
    //如果final修饰的是类变量，只能在静态初始化块中指定初始值或者声明该类变量时指定初始值。
    private static final String aa = "null";

    //成员变量
    //如果final修饰的是成员变量，可以在非静态初始化块、声明该变量或者构造器中执行初始值。
    private final  String bb;

    public MainTest(String bb) {
        this.bb = bb;
    }
    

//    public static void main(String[] args) {
//        MainTest mainTest = new MainTest("11");
//        System.out.println(mainTest.bb);
//        //mainTest.bb = "222";
//    }

    public static void main(String[] args) {
        AA aa1 = new AA();
        AA aa2 = new AA();
        System.out.println(aa1.i);
        System.out.println(aa1.j);
        System.out.println(aa2.i);
        System.out.println(aa2.j);
    }

}

//j值两个都一样，因为是static修饰的,全局只保留一份
//i值不一样，两个对象可能产生两个不同的值，
class AA {
    public final int i = (int) (Math.random()*100);
    public static int j = (int) (Math.random()*100);
}

package com.itfeng.base;

import lombok.Data;

/**
 * 那是因为在java中，==比较的是引用是否相等，因为字符串在直接声明x=”string”的时候是声明到常量池中，所以相同的常量指向的地址是一样的；
 * 使用new String(“string”)生成的字符串是在java堆内存中，所以使用常量池的地址和堆内存的地址自然是不一样了
 */
@Data
public class User {
    private String name;
    private int age;

    public static void main(String[] args) {
        final String aaaaaa = "2"; //常量
        //aaaaaa ="3";
        User user1 = new User();
        final User user = new User();
       // user  = user1; 引用不能变
        user.setName("222222"); // 引用变量所指向的对象中的内容是可以更改的
        //final 修饰基本数据类型时，则其数值一旦初始化之后便不能更改
        final int test = 0;
        //test =1;


        String answer = "1111";
        String answer2 = new String("1111");
        String answer3 = "1111";
        System.out.println(answer == answer2);
        System.out.println(answer == answer3);
        System.out.println(answer.equals(answer2));

//        User user = new User();
//        User user2 = new User();
//        if (user == user2) {
//            System.out.println("222");
//        }else {
//            if (user.equals(user2)) {
//                System.out.println("222222222");
//            }
//        }
        //java中的String是final类，final类是共享数据的，而java中==比较的是内存地址，而final类的String相同字符串，指向的是同一个地址！
        String aa = "222";
        String bb ="222";
        System.out.println(aa == bb);
        System.out.println(aa .equals(bb));

         //foo 和 foo2 这两个变量分别指向了其他的一个对象，这是两个不同的对象，存储的内存首地址是不同的，所以foo == foo2 是false，
        // 而这两个对象的内容是相同的，
        //比较对象
        String foo = new String("foo");
        String foo2 = new String("foo");
        //比较的是
        System.out.println(foo == foo2);
        //比较的是内容
        System.out.println(foo.equals(foo2));

    }
}

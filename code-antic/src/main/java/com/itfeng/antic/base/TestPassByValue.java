package com.itfeng.antic.base;

/**
 * @author liuf
 * @date 2021年04月06日 8:51 下午
 * 验证java是值传递
 * 1.值传递是指在调用函数时将实际参数复制一份传递到函数中，这样在函数中如果对参数进行修改，将不会影响到实际参数。
 * 2.引用传递是指在调用函数时将实际参数的地址传递到函数中，那么在函数中对参数所进行的修改，将影响到实际参数。
 * 概括：值传递是传递实参副本，函数修改不会影响实参；引用传递是传递实参地址，函数修改会影响实参。
 *
 */
public class TestPassByValue {
    public static void main(String[] args) {
        String name = "Scott";
        int age = 5;
        User user = new User();
        user.setName(name);
        user.setAge(age);
        System.out.println("before change user = " + user);

        change(user, name, age);
        System.out.println("name = " + name);
        System.out.println("age = " + age);
        System.out.println("after change user = " + user);
    }

    public static void change(User user, String name, int age) {
        name = "Tom";
        age = 20;
        user.setName(name);
        user.setAge(age);
    }

    static class User {
        private String name;
        private int age;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        @Override
        public String toString() {
            return "{name : " + name + ", age : " + age + "}";
        }
    }
}

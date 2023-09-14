package com.itheima._01知识回顾;

/**
 * 目标：this关键字知识回顾。
 * <p>
 * this关键字的作用：
 * this代表了当前对象的引用。
 * this关键字可以用在实例方法和构造器中。
 * this用在方法中，谁调用这个方法，this就代表谁。
 * this用在构造器，代表了构造器正在初始化的那个对象的引用。
 * this只能类中非静态方法使用，静态方法和静态代码块绝对不会出现this(不属于类的元素)，并且this只和特定的对象关联，同一个类中不同的对象有不同的this
 *
 *
 * java中为什么在static中不能使用this关键字???
 *
 * Static方法是类方法，先于任何的实例（对象）存在。即Static方法在类加载时就已经存在了，但是对象是在创建时才在内存中生成。而this指代的是当前的对象
 *
 * 在方法中定义使用的this关键字,它的值是当前对象的引用.也就是说你只能用它来调用属于当前对象的方法或者使用this处理方法中成员变量和局部变量重名的情况.
 *
 * 而且,更为重要的是this和super都无法出现在static 修饰的方法中,static 修饰的方法是属于类的,该方法的调用者可能是一个类,而不是对象.如果使用的是类来调用而不是对象,
 *
 * 则 this就无法指向合适的对象.所以static 修饰的方法中不能使用this.
 *
 */
public class ThisDemo02 {
    private static String aaa = null;

    public static void main(String[] args) {
        Animal1 a1 = new Animal1();
        a1.setName("金毛");
        System.out.println(a1.getName());
        Animal1 a2 = new Animal1("泰迪", 3, '公');
        System.out.println(a2.getName());
    }

    public void test() {
        String aaa = "111";
        //如果使用this.a，则不会在方法（局部变量）中寻找变量a,而是直接去实例变量中去寻找
        this.aaa = "aa";
    }
}

class Animal1 {
    private String name;
    private int age;
    private char sex;

    public Animal1() {
    }

    public Animal1(String name, int age, char sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    /**
     * 获取
     *
     * @return name
     */
    public String getName() {
        return this.name;  // a1.name  this有时候可以省略不写！！
    }

    /**
     * 设置
     *
     * @param name a1.setName("金毛");
     */
    public void setName(String name) {
        // 谁调用这个方法，this就代表谁!!!
        this.name = name; //  a1.name = 金毛
    }

    /**
     * 获取
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置
     *
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 获取
     *
     * @return sex
     */
    public char getSex() {
        return sex;
    }

    /**
     * 设置
     *
     * @param sex
     */
    public void setSex(char sex) {
        this.sex = sex;
    }

}
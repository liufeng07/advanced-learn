package com.itfeng.concurrent;


/**
 * @author liuf
 * @date 2022年01月07日 4:02 下午
 */
public class TestRunnable implements Runnable {

    private int local_i;//实例变量

    public void run() {
        local_i = 4;
        System.out.println("[" + Thread.currentThread().getName()
                + "]获取local_i 的值:" + local_i);
        local_i = 10;
        System.out.println("[" + Thread.currentThread().getName()
                + "]获取local_i*2的值:" + local_i * 2);
    }

//    public void run() {
//        //这创建了多个实例，所以线程安全
//        User user = new User();
//        user.setAge(4);
//        System.out.println("[" + Thread.currentThread().getName()
//                + "]获取local_i 的值:" + user.getAge());
//        user.setAge(10);
//        System.out.println("[" + Thread.currentThread().getName()
//                + "]获取local_i*2的值:" + user.getAge() * 2);
//    }

    public static void main(String[] args) {
        TestRunnable t = new TestRunnable();
        //启动尽量多的线程才能很容易的模拟问题
        for (int i = 0; i < 1000000; i++) {
            //每个线程对在对象t中运行，模拟单例情况
            new Thread(t, "线程" + i).start();//模拟单例情况
        }
    }
    class User{
        private int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}

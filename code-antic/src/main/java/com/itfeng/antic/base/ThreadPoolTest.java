//package com.itfeng.antic.base;
//
//import com.itfeng.antic.oom.stackError.entity.user;
//
//import java.util.List;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.TimeUnit;
//
//public class ThreadPoolTest {
//    public static void main(String[] args) {
//
//        ExecutorService threadPool = new ThreadPoolExecutor(8, //corePoolSize线程池中核心线程数
//                10, //maximumPoolSize 线程池中最大线程数
//                60, //线程池中线程的最大空闲时间，超过这个时间空闲线程将被回收
//                TimeUnit.SECONDS,//时间单位
//                new ArrayBlockingQueue(500), //队列
//                new ThreadPoolExecutor.CallerRunsPolicy()); //拒绝策略
//
//        List<User> userList = Lists.newArrayList(
//                new User("苏三"),
//                new User("苏三说技术"),
//                new User("技术"));
//
//        for (User user : userList) {
//            threadPool.submit(new Work(user));
//        }
//
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(userList);
//    }
//    static class Work implements Runnable {
//        private User user;
//
//        public Work(User user) {
//            this.user = user;
//        }
//
//        @Override
//        public void run() {
//            user.setName(user.getName() + "测试");
//        }
//    }
//    static class User {
//        private String name;
//
//        public User(String name) {
//            this.name = name;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//    }
//}

package com.itfeng.antic.oom;


import com.itfeng.antic.oom.stackError.entity.count;
import com.itfeng.antic.oom.stackError.entity.user;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class HeapOOM {

   //堆内存溢出 设置-Xms(堆的最小值)，-Xmx(堆的最大值)
    @Test
   public void testHeapOOM(){
       List<user> users = new ArrayList<>();
       int i = 0;
       while (true) {
           System.out.println(i++);
           users.add(new user());
       }
   }

    //栈内存溢出 设置栈大小的方法是设置-Xss参数
   @Test
   public void testStackOOM(){
       count count = new count();
       try {
           count.setCnumer();
       } catch (Exception e) {
           System.out.println(count.cnumer);
           e.printStackTrace();
       }
   }

    //java.lang.OutOfMemoryError: PermGen space 一般是程序类加载的过多



}
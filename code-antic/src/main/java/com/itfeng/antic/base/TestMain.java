package com.itfeng.antic.base;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class TestMain {
    public static void main(String[] args) {
        HashMap<String, String> stringStringHashMap = new HashMap<>();

        String aa = "111";
        String bb = new String("111");
        System.out.println(aa.equals(bb));
        //this 代表是当前对象

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        //key 与 value 不能为空
        map.put(null,null);
        System.out.println(map.toString());
    }


    public String getString(String aa){
        return null;
    }
}

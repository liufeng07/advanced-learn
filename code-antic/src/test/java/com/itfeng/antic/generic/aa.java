package com.itfeng.antic.generic;


import java.util.Arrays;

public class aa<K,V> {
    private K data;
    private V data2;
    private String code;
    private String msg;

    public K getData() {
        return data;
    }

    public void setData(K data) {
        this.data = data;
    }

    public V getData2() {
        return data2;
    }

    public void setData2(V data2) {
        this.data2 = data2;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 泛型方法
     * @param e
     * @param <E>
     */
    public <E> void print(E e){
        System.out.println(e);
    }

    public static void main(String[] args) {

    }

}

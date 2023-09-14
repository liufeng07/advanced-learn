package com.itfeng.datastructure;

/**
 * @author liuf
 * @date 2022年01月04日 5:01 下午
 */
public class Node<T>{
    private T data; //节点的数据
    public Node next; //指向的下一个节点
    public Node(T data, Node next) {
        this.data = data;
        this.next=next;
    }

    public Node() { }
    public T getData() {
        return data;
    }
    private void setData(T data) {
        this.data = data;
    }
}

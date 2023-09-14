package com.itfeng.datastructure;

/**
 * @author liuf
 * @date 2022年01月04日 5:02 下午
 */
public class LinkList<T> {
    private Node head; //头结点
    private Node tail; //尾结点
    private int size; //链表长度

    public LinkList() {
        head = null;
        tail = null;
    }

    //获取链表长度
    public int getLength() {
        return size;
    }

    //是否含有元素
    public boolean isEmpty() {
        return size == 0;
    }

    //清空链表
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    //通过索引获取节点
    public Node getNodeByIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        Node node = head;
        for (int i = 0; i < size; i++, node = node.next) {
            if (i == index) {
                return node;
            }
        }
        return null;
    }
}

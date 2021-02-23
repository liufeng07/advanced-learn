package com.itheima._16Collection集合Remove的坑;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(4);
        //输出结果为[1, 2, 3, 3, 4]
        System.out.println(list);


        //1、普通for循环遍历List删除指定元素--错误
        for(int i=0;i<list.size();i++){
            if(list.get(i)==3) list.remove(i);
        }
        System.out.println(list);
        // 输出结果：[1, 2, 3, 4]
        // 为什么元素3只删除了一个？本以为这代码再简单不过，可还是掉入了陷阱里，上面的代码这样写的话，元素3是过滤不完的。
        // 只要list中有相邻2个相同的元素，就过滤不完。List调用remove(index)方法后，会移除index位置上的元素，
        // index之后的元素就全部依次左移，即索引依次-1要保证能操作所有的数据，需要把index-1，
        // 否则原来索引为index+1的元素就无法遍历到(因为原来索引为index+1的数据，
        // 在执行移除操作后，索引变成index了，如果没有index-1的操作，就不会遍历到该元素，而是遍历该元素的下一个元素)。


        //2、for循环遍历List删除元素时，让索引同步调整--正确！
        for(int i=0;i<list.size();i++){
            if(list.get(i)==3) list.remove(i--);
        }
        System.out.println(list);
        //输出结果：[1, 2, 4]
        //3、倒序遍历List删除元素--正确
        for(int i=list.size()-1;i>=0;i--){
            if(list.get(i)==3){
                list.remove(i);
            }
        }
        System.out.println(list);
        //输出结果：[1, 2, 4]
        //4、foreach遍历List删除元素--错误！！
        for(Integer i:list){
            if(i==3) list.remove(i);
        }
        System.out.println(list);
        //抛出异常：java.util.ConcurrentModificationException
        //foreach 写法实际上是对的 Iterable、hasNext、next方法的简写。因此从List.iterator()源码着手分析，跟踪iterator()方法，该方法返回了 Itr 迭代器对象。



    }


}

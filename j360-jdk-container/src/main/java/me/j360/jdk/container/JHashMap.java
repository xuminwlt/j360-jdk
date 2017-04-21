package me.j360.jdk.container;

/**
 * Package: me.j360.jdk.container
 * User: min_xu
 * Date: 2017/4/20 上午11:02
 * 说明：map由数组+链表的方式组合,数组通过hash算法计算kv在哪个下标,链表使用next循环对key进行再匹配查找
 * 数组的大小并不是真正的Key的大小,数组的大小根据初始化容量和加载因子有关系,可以理解是hash桶的数量
 *
 * 数组[5]        链表
 * |-----0|  --> |1| |2| |3| |4|
 * |-----1|  --> |1| |2| |3| |4|
 * |-----2|  --> |1| |2| |3| |4|
 * |-----3|  --> |1| |2| |3| |4|
 * |-----4|  --> |1| |2| |3| |4|
 */
public class JHashMap<K,V> {


    private Node<K,V>[] tables;



    private static class Node<K,V> {
        K key;
        V value;
        Node next;
        int hash;
    }




}

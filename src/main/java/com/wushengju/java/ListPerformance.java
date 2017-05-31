package com.wushengju.java;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * List性能测试类
 *
 * @author Sunny
 * @create 2017-05-19 15:30
 **/
public class ListPerformance {

    private static ArrayList<String> arrayList= new ArrayList<String>();

    private static LinkedList<String> linkedList = new LinkedList<String>();

    /**
     * 插入数据
     * @param list
     * @param count
     */
    public static void insertElements(List<String> list, int count){
        Long startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            list.add(String.valueOf(i));
        }
        Long endTime =  System.currentTimeMillis();
        System.out.println("insert elements use time: " +(endTime-startTime) + " ms");
    }

    /**
     * 删除元素
     * @param list
     * @param count
     */
    public static void removeElements(List<String> list, int count){
        Long startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            list.remove(0);
        }
        Long endTime =  System.currentTimeMillis();
        System.out.println("remove elements use time: " +(endTime-startTime) + " ms");
    }

    /**
     * 获取元素
     * @param list
     * @param count
     */
    public static void getElements(List<String> list, int count){
        Long startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            list.get(i);
        }
        Long endTime =  System.currentTimeMillis();
        System.out.println("get elements use time: " +(endTime-startTime) + " ms");
    }
    /**
     * 删除元素第二种实现
     * @param list
     * @param count
     */
    public static void removeElements2(List<String> list, int count){
        Long startTime = System.currentTimeMillis();
        for (int i = count-1; i > 0; i--) {
            list.remove(i);
        }
        Long endTime =  System.currentTimeMillis();
        System.out.println("remove elements use time: " +(endTime-startTime) + " ms");
    }
    public static void main(String[] args){
        System.out.println("arrayList test");
        insertElements(arrayList,100000);
        getElements(arrayList,100000);
        removeElements(arrayList,100000);

        System.out.println("linkedList test");
        insertElements(linkedList,100000);
        getElements(linkedList,100000);
        removeElements(linkedList,100000);


        System.out.println("arrayList test2");
        insertElements(arrayList,100000);
        getElements(arrayList,100000);
        removeElements2(arrayList,100000);

        System.out.println("linkedList test2");
        insertElements(linkedList,100000);
        getElements(linkedList,100000);
        removeElements2(linkedList,100000);
    }
}

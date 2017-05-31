package com.wushengju.java;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ArrayList进阶学习
 *
 * @author Sunny
 * @create 2017-05-09 19:04
 **/
public class ArryListProgress {

    public static void removeElements1(List<String> list, int count){
        Long startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            list.remove(0);
        }
        Long endTime =  System.currentTimeMillis();
        System.out.println("remove elements use time: " +(endTime-startTime) + " ms");
    }

    public static void removeElements2(List<String> list, int count){
        Long startTime = System.currentTimeMillis();
        for (int i = count-1; i > 0; i--) {
            list.remove(i);
        }
        Long endTime =  System.currentTimeMillis();
        System.out.println("remove elements use time: " +(endTime-startTime) + " ms");
    }

    public static void removeElements3(List<String> list, int count){
        Long startTime = System.currentTimeMillis();
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            iterator.next();
            iterator.remove();
        }
        Long endTime =  System.currentTimeMillis();
        System.out.println("remove elements use time: " +(endTime-startTime) + " ms");
    }
    public static void main(String[] args) {

        ArrayList<String> arrayList = new ArrayList<String> ();
        ArrayList<String> arrayList1 = new ArrayList<String> ();
        ArrayList<String> arrayList2 = new ArrayList<String> ();
        int count = 100000;
        for (int i = 0; i < count; i++) {
            arrayList.add(String.valueOf(i));
            arrayList1.add(String.valueOf(i));
            arrayList2.add(String.valueOf(i));
        }

        removeElements1(arrayList,count);

        removeElements2(arrayList1,count);

        removeElements3(arrayList2,count);
        /*ArrayList<String> arrayList = new ArrayList<String> ();
        arrayList.add("a");
        arrayList.add("a");
        arrayList.add("c");
        arrayList.add("a");
        arrayList.add("a");

        Iterator<String> ite = arrayList.iterator();
        while (ite.hasNext()){
            ite.next();
            ite.remove();
        }
        System.out.println(arrayList);*/
        /*for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) == "a") {
                arrayList.remove(i);
            }
        }*/

        /*for (int i = arrayList.size()-1; i >=0 ; i--) {
            if (arrayList.get(i) == "a") {
                arrayList.remove(i);
            }
        }
        System.out.println(arrayList);
        Iterator<String> ite = arrayList.iterator();
        while (ite.hasNext()){
             System.out.println(ite.next());
        }
        System.out.println(arrayList);*/
       /* for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }

        for (String str : arrayList) {
            System.out.println(str);
        }

        Iterator<String> ite = arrayList.listIterator();
        while (ite.hasNext()){
            System.out.println(ite.next());
        }*/
    }
}

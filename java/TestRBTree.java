package com.wushengju.java;

/**
 * 红黑树测试类
 *
 * @author Sunny
 * @create 2017-05-24 16:24
 **/
public class TestRBTree {
    public static void main(String[] args){
        Integer[] keys = {10,40,30,60,90,70,20,80};
        RBTree rbTree = new RBTree();
        for (int i = 0; i < keys.length ; i++) {
            rbTree.insert(keys[i]);
        }

        rbTree.remove(30);
    }
}

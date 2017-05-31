package com.wushengju.java;

import java.io.File;

/**
 * 文件操作类
 *
 * @author Sunny
 * @create 2017-05-18 15:08
 **/
public class FileOperate {

    /**
     * 获取文件的后缀名称
     * @param file
     * @return
     */
    public static String getFileSuffixName(File file){
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf("."),fileName.length());
    }

    public static void main(String[] args){
        System.out.println(getFileSuffixName(new File("as.txt")));
    }
}

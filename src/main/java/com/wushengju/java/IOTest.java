package com.wushengju.java;

import java.io.*;

/**
 * 测试
 *
 * @author Sunny
 * @create 2017-05-11 16:14
 **/
public class IOTest {
    public static void main(String[] args){
        byte[] as= {'a','b'};
        byte[] as1= {1,2};
        try {
            FileOutputStream asd =  new FileOutputStream ("D://a.txt");
            OutputStreamWriter wr = new OutputStreamWriter(asd,"UTF-8");
            for (int i = 0; i < as.length; i++) {
                wr.write (as[i]);
            }
            wr.flush ();

            FileInputStream in = new FileInputStream ("D://a.txt");
            InputStreamReader isr = new InputStreamReader (in,"UTF-8");
            String results = "";
            int tmp ;
            while((tmp = isr.read()) != -1){
                results += (char)tmp;
            }
            System.out.print (results) ;
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        }


    }
}

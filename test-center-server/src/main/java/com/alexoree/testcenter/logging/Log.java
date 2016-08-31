package com.alexoree.testcenter.logging;

/**
 * Created by alex on 8/31/16.
 */

public class Log {
    public static void i(String msg){
        System.out.println(msg);
    }
    public static void i(String msg, Exception ex){
        System.out.println(msg);
        ex.printStackTrace();
    }
}

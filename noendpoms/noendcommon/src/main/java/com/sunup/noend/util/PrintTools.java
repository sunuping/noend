package com.sunup.noend.util;

public class PrintTools {

    public static void print(Object object) {
        System.out.println(object);
    }

    public static void print(Object... objects) {
        System.out.println(StringTools.bufferAppend(objects));
    }


}

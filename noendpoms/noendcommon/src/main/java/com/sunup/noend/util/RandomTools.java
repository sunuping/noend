package com.sunup.noend.util;

import java.util.Random;
import java.util.UUID;



public class RandomTools {

    /**
     * 获取uuid  唯一随机字符
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum;
        while (true) {
            randomNum = rand.nextInt((max + 1));
            if (randomNum >= min && randomNum <= max) {
                break;
            }
        }
        return randomNum;
    }

    public static int randInt(int max) {
        Random rand = new Random();
        int randNum = rand.nextInt((max + 1));
        return randNum > 0 ? randNum : randInt(max);
    }


}

package com.sunup.noend.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author Janly
 * @Description
 * @Date : Create in 14:04 2019/12/3
 */
@Slf4j
public class ExecutorTools {
    public static final ExecutorService executorService = new ThreadPoolExecutor(100, 1000, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

//    public static void main(String[] args) {
//        ExecutorTools.executorService.submit(new Runnable() {
//            @Override
//            public void run() {
//                log.info("22");
//            }
//        });
//    }

}

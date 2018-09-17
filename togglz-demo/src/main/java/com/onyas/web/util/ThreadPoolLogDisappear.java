package com.onyas.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class ThreadPoolLogDisappear {

    private static Logger logger = LoggerFactory.getLogger(ThreadPoolLogDisappear.class);

    static ThreadPoolExecutor testThreadPool = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>());


    public static void main(String[] args) {
        Future<Integer> resultFuture = testThreadPool.submit(() -> {
            logger.info("start running");
            int a = 3 / 0;
            return a;
        });


        try {
            int result = resultFuture.get();
            System.out.println(result);
        } catch (Exception e) {
            logger.error("InterruptedException ", e);
        }
    }

}


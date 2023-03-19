package com.example.javademo;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        RunnableImpl run = new RunnableImpl();

        Thread t1 = new Thread(run);
        Thread t2 = new Thread(run);
        Thread t3 = new Thread(run);

        t1.start();
        t2.start();
        t3.start();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,10,0,
                TimeUnit.SECONDS,new LinkedBlockingDeque<>());
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}

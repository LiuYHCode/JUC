package com.at2024.tvolatile;

import java.util.concurrent.TimeUnit;

/**
 * @author lyh
 * @date 2024-08-21 22:37:59
 */
public class JMMDemo {
    // 不加 volatile 程序就会死循环！
    // 加 volatile 可以保证可见性
    private  static int num = 0;

    public static void main(String[] args) {
        new Thread(()->{
            while (num==0){
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num = 1;
        System.out.println(num);
    }
}

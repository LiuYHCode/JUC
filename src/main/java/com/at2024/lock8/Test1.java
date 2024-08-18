package com.at2024.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author lyh
 * @date 2024-08-18 16:45:24
 * 8锁，就是关于锁的8个问题
 * 1、标准情况下，两个线程先打印 发短信还是 打电话？ 1/发短信  2/打电话
 * 2、sendSms延迟4秒，两个线程先打印 发短信还是 打电话？ 1/发短信  2/打电话
 */
public class Test1 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        //A先B后，锁的是new的对象，所以A先B后
        new Thread(() -> {
            phone.sendSms();
        },"A").start();
        new Thread(() -> {
            phone.call();
        },"B").start();
    }
}

class Phone {
    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("发短信");
    }
    public synchronized void call() {
        System.out.println("打电话");
    }
}

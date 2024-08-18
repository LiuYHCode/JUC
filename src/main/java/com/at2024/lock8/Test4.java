package com.at2024.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author lyh
 * @date 2024-08-18 16:45:24
 * 7、1个静态的同步方法，1个普通的同步方法 ，一个对象，先打印 发短信？打电话？
 * 8、1个静态的同步方法，1个普通的同步方法 ，两个对象，先打印 发短信？打电话？
 */
public class Test4 {
    public static void main(String[] args) {
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();
        new Thread(() -> {
            phone1.sendSms();
        },"A").start();
        new Thread(() -> {
            phone2.call();
        },"B").start();
    }
}

class Phone4 {
    public static synchronized void sendSms() {
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

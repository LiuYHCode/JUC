package com.at2024.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author lyh
 * @date 2024-08-18 16:45:24
 * 3、 增加了一个普通方法后！先执行发短信还是Hello？ 普通方法
 * 4、 两个对象，两个同步方法， 发短信还是 打电话？ // 打电话
 */
public class Test2 {
    public static void main(String[] args) {
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();
        new Thread(() -> {
            phone1.sendSms();
        },"A").start();
        new Thread(() -> {
            phone2.hello();
        },"B").start();
    }
}

class Phone2 {
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

    // 这里没有锁！不是同步方法，不受锁的影响
    public void hello() {
        System.out.println("hello");
    }
}

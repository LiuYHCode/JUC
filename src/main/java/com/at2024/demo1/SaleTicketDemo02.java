package com.at2024.demo1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 真正的多线程开发，公司中的开发，降低耦合性
 * 线程就是一个单独的资源类，没有任何附属的操作！
 * 1、 属性、方法
 * @author lyh
 * @date 2024-08-18 14:56:08
 */
public class SaleTicketDemo02 {
    public static void main(String[] args) {
        Ticket2 ticket = new Ticket2();

        // 并发：多线程操作同一个资源类, 把资源类丢入线程
        new Thread(() -> {for (int i = 0; i < 110; i++) ticket.sale();}, "A").start();
        new Thread(() -> {for (int i = 0; i < 110; i++) ticket.sale();}, "B").start();
        new Thread(() -> {for (int i = 0; i < 110; i++) ticket.sale();}, "C").start();
    }
}

// Lock三部曲
// 1、 new ReentrantLock();
// 2、 lock.lock(); // 加锁
// 3、 finally=>  lock.unlock(); // 解锁
class Ticket2 {
    Lock lock = new ReentrantLock();

    private int number = 100;


    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "张票，剩余" + number + "张票");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}

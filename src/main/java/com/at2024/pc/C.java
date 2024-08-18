package com.at2024.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lyh
 * @date 2024-08-18 16:22:09
 * A 执行完调用B，B执行完调用C，C执行完调用A
 */
public class C {
    public static void main(String[] args) {
        Data3 data = new Data3();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        }, "C").start();
    }
}

class Data3 {
    private int num = 1; //1-a 2-b 3-c

    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void printA() {
        lock.lock();
        try {
            while (num != 1) { //使用if存在虚假唤醒，用while解决
                //等待
                condition1.await();
            }
            // 通知其他线程，我+1完毕了
            System.out.println(Thread.currentThread().getName()+"=>AAAAAA"+num);
            num = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            while (num != 2) {
                //等待
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>"+num);
            num = 3;
            // 通知其他线程，我-1完毕了
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            while (num != 3) {
                //等待
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>"+num);
            num = 1;
            // 通知其他线程，我-1完毕了
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

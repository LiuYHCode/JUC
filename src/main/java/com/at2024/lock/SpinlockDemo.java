package com.at2024.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author lyh
 * @date 2024-08-26 22:35:50
 */
public class SpinlockDemo {
    // int   0
    // Thread  null
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    // 加锁
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "==> mylock");

        // 自旋锁，AtomicReference刚开始new出来的就是null的，后面解锁又将thread进行cas成了null
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    // 解锁
    // 加锁
    public void myUnLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "==> myUnlock");
        atomicReference.compareAndSet(thread,null);
    }
}

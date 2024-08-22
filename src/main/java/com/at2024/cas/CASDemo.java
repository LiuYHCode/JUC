package com.at2024.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lyh
 * @date 2024-08-22 23:43:07
 */
public class CASDemo {
    //CAS compareAndSet：比较并交换!
    //AtomicStampedReference 注意，如果泛型是一个包装类，注意对象的引用问题

    // 正常在业务操作，这里面比较的都是一个个对象
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1,1);

    public static void main(String[] args) {
//        分析输出结果
//        线程 a 的执行:
//        a1=>1: 线程 a 获取了初始的版本号 1。
//        等待 1 秒。
//        更新值从 1 到 2，同时将版本号从 1 增加到 2。
//        a2=>2: 线程 a 再次获取版本号，此时为 2。
//        尝试将值从 2 改为 1，并且检查版本号是否还是 2。由于没有其他线程修改过，因此 compareAndSet 成功返回 true。
//        a3=>3: 版本号增加到 3。
//        线程 b 的执行:
//        b1=>1: 线程 b 获取了初始的版本号 1。
//        等待 2 秒。
//        尝试将值从 1 改为 6，并且检查版本号是否还是 1。但是在此期间，线程 a 已经将值从 1 改为了 2，并将版本号从 1 增加到了 2，所以 compareAndSet 失败返回 false。
//        b2=>3: 线程 b 获取最新的版本号，此时为 3。
//        总结
//        线程 a 先执行了更新操作，成功将值从 1 改为 2，然后再次成功将值从 2 改为 1。
//        线程 b 尝试更新值时，由于版本号不匹配导致更新失败，最终输出的值为 1，版本号为 3。


//        a1=>1
//        b1=>1
//        a2=>2
//        true
//        a3=>3
//        false
//        b2=>3

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp(); // 获得版本号
            System.out.println("a1=>"+stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Lock lock = new ReentrantLock(true);

            atomicStampedReference.compareAndSet(1, 2,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);

            System.out.println("a2=>"+atomicStampedReference.getStamp());


            System.out.println(atomicStampedReference.compareAndSet(2, 1,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));

            System.out.println("a3=>"+atomicStampedReference.getStamp());

        },"a").start();


        // 乐观锁的原理相同！
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp(); // 获得版本号
            System.out.println("b1=>"+stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicStampedReference.compareAndSet(1, 6,
                    stamp, stamp + 1));

            System.out.println("b2=>"+atomicStampedReference.getStamp());

        },"b").start();
    }
}

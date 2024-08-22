package com.at2024.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lyh
 * @date 2024-08-22 23:43:07
 */
public class CASDemo {
    //CAS compareAndSet：比较并交换!
    public static void main(String[] args) {
        //AtomicInteger类里面有很多方法，都是native，就是java调用c++，c++去操作内存
        //调用到的内存+1，就使用了cas的思想
        AtomicInteger atomicInteger = new AtomicInteger(2000);
        //期望，更新
        //public final boolean compareAndSet
        //如果我期望的值达到了，那就更新，否则，就不更新
        atomicInteger.compareAndSet(2000,2001);
        System.out.println(atomicInteger.get());

        atomicInteger.compareAndSet(2000,2002);
        System.out.println(atomicInteger.get());
    }
}

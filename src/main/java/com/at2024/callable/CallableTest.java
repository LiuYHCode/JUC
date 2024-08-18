package com.at2024.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author lyh
 * @date 2024-08-18 22:36:13
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // new Thread(new Runnable()).start();
        // new Thread(new FutureTask<V>()).start();
        // new Thread(new FutureTask<V>( Callable )).start();
        // new Thread().start(); // 怎么启动Callable
        FutureTask futureTask = new FutureTask<>(new MyThread());// 适配类
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();// 结果会被缓存，效率高

        Integer o = (Integer) futureTask.get();//这个get 方法可能会产生阻塞！把他放到最后
        System.out.println(o);
    }
}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("call()");
        return 1024;
    }
}

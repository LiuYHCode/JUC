package com.at2024.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author lyh
 * @date 2024-08-18 23:02:41
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙成功！");
        });

        for (int i = 1; i <= 7; i++) {
            int temp = i;
            new Thread(() -> {
                /*Java 的 Lambda 表达式捕获外部局部变量时只能捕获“有效”（effectively final）的变量。这意味着如果在 Lambda
                表达式中引用了一个局部变量，那么这个局部变量不能在 Lambda 表达式之外被修改。
                在你的代码中，你尝试直接在 Lambda 表达式中使用循环变量 i，但 i 在循环体外是可以被修改的，因此编译器不允许这样做。
                为了避免这个问题，你可以使用一个局部变量 temp 来保存当前循环迭代的值，这样 temp 就成为了“有效”（effectively
                final）的变量，可以在 Lambda 表达式中安全地使用。*/
                System.out.println(Thread.currentThread().getName() + "收集" + temp + "个龙珠");

                try {
                    cyclicBarrier.await(); // 等待
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}

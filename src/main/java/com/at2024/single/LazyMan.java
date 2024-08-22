package com.at2024.single;

/**
 * @author lyh
 * @date 2024-08-21 23:52:33
 * 懒汉式单例
 * 道高一尺，魔高一丈！
 */
public class LazyMan {

    //v3.0加volatile避免指令重排
    private volatile static LazyMan lazyMan;

    private LazyMan() {
        System.out.println(Thread.currentThread().getName() + "ok!");
    }

    //v2.0 双重检测锁模式的 懒汉式单例 DCL懒汉式，解决多线程情况下能拿到多个LazyMan实例
    private static LazyMan getInstance() {
        if (lazyMan == null) {
            //锁的是Class类，只有一个
            synchronized (LazyMan.class) {
                if (lazyMan == null) {
                    /**
                     * 1. 分配内存空间
                     * 2、执行构造方法，初始化对象
                     * 3、把这个对象指向这个空间
                     * 这里其实还是有风险的，new的时候要经过上面三步
                     * 123
                     * 132 A 指令重排出现问题，线程A还没有完成对象构造，初始化对象没有完成
                     *     B // 此时lazyMan还没有完成构造，线程B直接返回了lazyMan
                     */
                    lazyMan = new LazyMan();
                }

            }
        }
        return lazyMan;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                LazyMan.getInstance();
            }).start();
        }
    }

}

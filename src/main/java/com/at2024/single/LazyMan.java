package com.at2024.single;

/**
 * @author lyh
 * @date 2024-08-21 23:52:33
 * 懒汉式单例
 * 道高一尺，魔高一丈！
 */
public class LazyMan {

    private volatile static LazyMan lazyMan;

    private LazyMan() {
        System.out.println(Thread.currentThread().getName() + "ok!");
    }

    private static LazyMan getInstance() {
        if (lazyMan == null) {
            lazyMan = new LazyMan();
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

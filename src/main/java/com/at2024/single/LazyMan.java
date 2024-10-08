package com.at2024.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author lyh
 * @date 2024-08-21 23:52:33
 * 懒汉式单例
 * 道高一尺，魔高一丈！
 */
public class LazyMan {
    //v6.0 使用标志位防止单例被破坏，在构造方法使用新的判断
    private static boolean qinjiang = false;

    //v3.0加volatile避免指令重排
    private volatile static LazyMan lazyMan;

    private LazyMan() {
        //v5.0 避免getInstance一次之后，在使用反射去构造创建新对象破坏单例
        if (qinjiang == false) {
            qinjiang = true;
        } else {
            throw new RuntimeException("不要试图使用反射破坏异常");
        }
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

    public static void main(String[] args) throws Exception {
        //v4.0 通过反射破坏单例中的private，实现破坏单例
//        LazyMan instance = LazyMan.getInstance();
        //v6.0 继续破坏，反编译获取标志位进行破坏
        Field declaredField = LazyMan.class.getDeclaredField("qinjiang");
        declaredField.setAccessible(true);

        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        //v5.0 继续破坏单例，不走getInstance
        LazyMan instance = declaredConstructor.newInstance();
        declaredField.set(instance, false);

        LazyMan instance1 = declaredConstructor.newInstance();
        System.out.println(instance);
        System.out.println(instance1);
    }

}

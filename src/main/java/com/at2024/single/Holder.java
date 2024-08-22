package com.at2024.single;

/**
 * @author lyh
 * @date 2024-08-22 21:26:03
 * 匿名内部类实现获取单例，但是其实还是不安全
 */
public class Holder {

    private Holder() {

    }

    public static Holder getInstance() {
        return InnerClass.HOLDER;
    }

    public static class InnerClass{
        private static final Holder HOLDER = new Holder();
    }
}

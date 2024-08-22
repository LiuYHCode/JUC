package com.at2024.single;

/**
 * @author lyh
 * @date 2024-08-21 23:51:03
 * 饿汉式单例
 */
public class Hungry {
    // 可能会浪费空间
    private byte[] data1 = new byte[1024*1024];
    private byte[] data2 = new byte[1024*1024];
    private byte[] data3 = new byte[1024*1024];
    private byte[] data4 = new byte[1024*1024];

    //单例模式一定要写一个私有空参构造
    private Hungry(){

    }

    private final static Hungry HUNGRY = new Hungry();

    public static Hungry getInstance(){
        return HUNGRY;
    }


}

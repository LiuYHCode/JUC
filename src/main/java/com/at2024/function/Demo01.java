package com.at2024.function;

import java.util.function.Function;

/**
 * @author lyh
 * @date 2024-08-20 22:51:54
 * Function 函数型接口, 有一个输入参数，有一个输出
 * 只要是 函数型接口 可以 用 lambda表达式简化
 */
public class Demo01 {
    public static void main(String[] args) {
//        Function<String,String> function = new Function<String,String>() {
//            @Override
//            public String apply(String str) {
//                return str;
//            }
//        };
        Function function = o-> {
            return o;
        };
        System.out.println(function.apply("asd"));
    }
}

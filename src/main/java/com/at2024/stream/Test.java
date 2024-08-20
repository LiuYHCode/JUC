package com.at2024.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author lyh
 * @date 2024-08-20 23:10:06
 * 题目要求：一分钟内完成此题，只能用一行代码实现！
 * 现在有5个用户！筛选：
 * 1、ID 必须是偶数
 * 2、年龄必须大于23岁
 * 3、用户名转为大写字母
 * 4、用户名字母倒着排序
 * 5、只输出一个用户！
 */
public class Test {
    public static void main(String[] args) {
        User u1 = new User(1,"a",21);
        User u2 = new User(2,"b",22);
        User u3 = new User(3,"c",23);
        User u4 = new User(4,"d",24);
        User u5 = new User(6,"e",25);
        // 集合就是存储
        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);

        // 计算交给Stream流
        // lambda表达式、链式编程、函数式接口、Stream流式计算
        list.stream().filter(s->s.getId()%2==0)
                .filter(s->s.getAge()>23)
                .map(s->s.getName().toUpperCase())
                .sorted(String::compareTo)
                .limit(1)
                .forEach(System.out::println);
    }
}

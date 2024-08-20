package com.at2024.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lyh
 * @date 2024-08-20 23:11:34
 */

// 有参，无参构造，get、set、toString方法！
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private int age;
}

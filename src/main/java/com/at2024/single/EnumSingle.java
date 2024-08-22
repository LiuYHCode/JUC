package com.at2024.single;

import java.lang.reflect.Constructor;

/**
 * @author lyh
 * @date 2024-08-22 22:43:19
 */
public enum EnumSingle {
    INSTANCE;

    public EnumSingle getInstance() {
        return INSTANCE;
    }
}

class Test {
    public static void main(String[] args) throws Exception {
        EnumSingle instance1 = EnumSingle.INSTANCE;
        //
        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(String.class,int.class);
        declaredConstructor.setAccessible(true);
        //正常来说newInstance里面会有一段Cannot reflectively create enum objects，这里就是用的枚举的，idea骗了我们，不是空参构造
        EnumSingle instance2 = declaredConstructor.newInstance();
        System.out.println(instance1);
        System.out.println(instance2);
    }
}
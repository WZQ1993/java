package com.wangziqing.java8Demo;

/**
 * tip:类优先原则，同时继承一个父类和实现一个接口，两者有共同的方法，父类方法优先
 * Created by ziqingwang on 2016/10/25.
 */
public interface InterfaceDefaultMethodTest {
    /**
     * 带具体实现的默认方法
     * 继承该接口的同名方法会被忽略
     */
    default void defaultMethod(){
        System.out.print("默认方法");
    }

    static String staticMethod(){
        return "静态接口方法";
    }
}

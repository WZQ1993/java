package com.wangziqing.java8Demo;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ziqingwang on 2016/10/25.
 */
public class LambdaTest {
    //实例变量
    int objectValue=0;
    //静态变量
    static int staticValue=0;

    public void repeatMessages(String test , int count){
        List<String> list= Lists.newArrayList();
        //lambda表达式中
        Runnable runnable=()->{
            //捕获闭合作用域得变量值
            System.out.print(count);
            //改变实例变量
            objectValue++;
            //改变静态变量
            staticValue++;
            //list引用同一个ArrayList对象,但是这个对象是可变的。可以改变对象的值，但不能改变引用
            list.add("");
            //this调用得是对象LambdaTest的方法，而非runnable实例的方法
            this.toString();
        };
    }
}

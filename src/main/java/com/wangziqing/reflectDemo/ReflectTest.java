package com.wangziqing.reflectDemo;

import java.util.logging.Logger;

/**
 * 对象在运行过程中能否有一种机制查看自身的状态，属性和行为。
 * 并且可以调用、设置自身的方法和域
 * 每一个运行中的类，都会有一个class对象，表示这个类的类对象。
 * Created by ziqingwang on 2016/11/28.
 */
public class ReflectTest {
    public static void main(String [] args){
        ReflectTest reflectTest=new ReflectTest();
        reflectTest.getClassTest();
    }
    private void getClassTest(){
        Test test=new SubTest();
        //1.getClass()
        Class<? extends Test> c1=test.getClass();
        Logger.getGlobal().info(c1.toString());
        //2.类.Class
        Class<String> c2 = String.class;
        Logger.getGlobal().info(c2.toString());
        //3. forName()
        try{
            Class<?> c3=Class.forName("com.wangziqing.reflectDemo.ReflectTest");
            Logger.getGlobal().info(c3.toString());
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
class Test{

}
class SubTest extends Test{

}
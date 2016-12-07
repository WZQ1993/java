package com.wangziqing.genericDemo;

import com.wangziqing.commonDao.Type;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Java泛型有这么一种规律：
 * 位于声明一侧的，源码里写了什么到运行时就能看到什么；//1
 * 位于使用一侧的，源码里写什么到运行时都没了。//2
 * </p>
 * <p>
 * 继承的父类BaseDao<Type>(),父类中的变量为Class<Type> clazz
 * 属于声明一侧，源码中写了Type，运行时可以获得Type
 * </p>
 * <p>
 * 标注1的作为声明一侧
 * list只能看到T
 * map能看到String，T
 * genericMethod能获得T,U
 * 源码文本里写的是什么运行时就能得到什么；像是T、U等在运行时的实际类型是获取不到的。
 * </p>
 * Created by ziqingwang on 2016/12/6.
 */
public class TypeDaoImp<T> extends BaseDao<Type> { //1

    public List<T> list;                     // 1
    public Map<String, T> map;               // 1

    public <U> U genericMethod(Map<T, U> m) { // 1
        return null;
    }

    public static void main(String[] args) throws Exception {
        //方法内属于使用一侧，源码里写什么到运行时都没了
        //即无法得到list的String类型
        List<String> list = null;       // 2
        list = new ArrayList<String>(); // 2
        for (int i = 0; i < 10; i++) ;
    }

    public void testDeclare() throws Exception {
        //针对类声明，public class TypeDaoImp<T> extends BaseDao<Type>
        //源码中写的T，Type都能获取
        //获取T
        System.out.println(
                getClass().getTypeParameters()[0]);//T
        //获取Type
        System.out.println(
                ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);//class com.wangziqing.commonDao.Type
        //list的T
        System.out.println(
                getClass().getField("list").getType().getTypeParameters()[0]);//E
        //map的泛型信息
        System.out.println(
                getClass().getField("map").getType().getTypeParameters()[0]);//K
        System.out.println(
                getClass().getField("map").getType().getTypeParameters()[1]);//V
        //genericMethod（）的泛型信息U
        System.out.println(
                getClass().getMethod("genericMethod", Map.class).getTypeParameters()[0]);//U
        //genericMethod（）的参数类型
        System.out.println(
                getClass().getMethod("genericMethod", Map.class).getParameterTypes()[0]);//interface java.util.Map
        //genericMethod（）的参数类型的泛型信息
        System.out.println(
                getClass().getMethod("genericMethod", Map.class).getParameterTypes()[0].getTypeParameters()[0]);//K
        System.out.println(
                getClass().getMethod("genericMethod", Map.class).getParameterTypes()[0].getTypeParameters()[1]);//V
    }
}

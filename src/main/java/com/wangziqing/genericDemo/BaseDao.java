package com.wangziqing.genericDemo;

import com.wangziqing.common.dao.Type;

import java.lang.reflect.ParameterizedType;

/**
 * 要想对BaseDao<T>获取T的实际类型是不现实的，
 * 因为BaseDao.class文件里只记录了T，却没记录使用要想对BaseDao<T>时T的实际类型。
 * 通过一个非泛型类继承一个确定的泛型基类，得到确切的类型，
 * {@link com.wangziqing.genericDemo.TypeDaoImp}可以得到泛型类类型{@link Type}
 * Created by ziqingwang on 2016/12/6.
 */
public class BaseDao<T> {
    //声明一侧，源码中写了T，对于BaseDao类运行时只能看到T，而无法看到实际类型
    Class<T> clazz;
    /**
     * getClass()返回值为 当前运行时类的Class对象。
     * 不受this和super影响，而是有当前的运行类决定的。
     * 即构造函数中调用getClass()返回的是运行时类的Class对象，即子类的Class
     */
    BaseDao(){
        ParameterizedType parameterizedType=(ParameterizedType)getClass().getGenericSuperclass();
        clazz=(Class<T>) parameterizedType.getActualTypeArguments()[0];
    }
    public void printlnClass(){
        System.out.println(clazz.toString());
    }
}

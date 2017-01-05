package com.wangziqing.dynamicProxyDemo;

/**
 * 动态代理，为指定的接口，在系统运行的时候动态生成代理对象
 * 比如对于实现了service接口的不同实现类serviceImp1，serviceImp2，
 * 系统运行时刻生成不同的代理对象；
 * Created by ziqingwang on 2016/12/17.
 */
public interface Serveice {
    void service();
}

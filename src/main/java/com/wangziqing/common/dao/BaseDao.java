package com.wangziqing.common.dao;

/**
 * <p>
 *     通用数据库操作主要是需要获取类进而得到数据库结构
 *     1.通过非泛型类继承泛型基类获取泛型类型Class
 *     2.构造函数传递Class
 *      劣势：前两种每个表需要对应一个DaoImp
 *     3.执行函数传递Class,如insert(Class,Object);
 *      优势：不必每个表都对应一个DaoImp,只用通用的
 *      <p>通过缓存Class信息(表结构)，减少反射次数</p>
 * </p>
 * Created by ziqingwang on 2016/12/6.
 */
public interface BaseDao<T> {
    void save(T t);
}

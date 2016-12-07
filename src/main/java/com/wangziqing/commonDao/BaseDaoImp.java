package com.wangziqing.commonDao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

/**
 * Created by ziqingwang on 2016/12/6.
 */
public class BaseDaoImp<T> implements BaseDao<T> {
    private Class<T> clazz;
    public BaseDaoImp(){
        //通过非泛型类继承泛型基类获取泛型类型
        ParameterizedType parameterizedType=(ParameterizedType)getClass().getGenericSuperclass();
        clazz=(Class<T>) parameterizedType.getActualTypeArguments()[0];
    }
    @Override
    public void save(T t){
        try{
            //通过反射获取clazz类中的所有变量，映射表结构
            Field fields[] = clazz.getDeclaredFields();
            //通过参数t得到对象参数的值
            Object obj[] = new Object[fields.length];
            for (int i = 0; obj != null && i < fields.length; i++) {
                //属性的访问权限设为true
                fields[i].setAccessible(true);
                obj[i] = fields[i].get(t);
            }
            //拼接sql语句
        }catch (IllegalAccessException e){

        }
    }
}

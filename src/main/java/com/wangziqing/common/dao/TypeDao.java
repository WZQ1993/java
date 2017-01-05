package com.wangziqing.common.dao;

/**
 * Created by ziqingwang on 2016/12/6.
 */
public interface TypeDao extends BaseDao<Type>{
    //区别于通用dao的另外的方法
    Type selectTypeByOther(int other);
}

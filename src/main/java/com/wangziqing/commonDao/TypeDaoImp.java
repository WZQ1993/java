package com.wangziqing.commonDao;

/**
 * Created by ziqingwang on 2016/12/6.
 */
public class TypeDaoImp extends BaseDaoImp<Type> implements TypeDao{
    @Override
    public Type selectTypeByOther(int other) {
        //非通用，直接构造sql执行
        return null;
    }
}

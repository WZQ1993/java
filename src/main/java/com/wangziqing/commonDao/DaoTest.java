package com.wangziqing.commonDao;

/**
 *
 * Created by ziqingwang on 2016/12/6.
 */
public class DaoTest {
    public static void main(String[] args){
        TypeDaoImp typeDaoImp=new TypeDaoImp();
        //基础数据库操作
        typeDaoImp.save(new Type());
        //Type额外的业务逻辑
        typeDaoImp.selectTypeByOther(0);
    }
}

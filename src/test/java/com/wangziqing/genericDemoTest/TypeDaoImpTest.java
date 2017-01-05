package com.wangziqing.genericDemoTest;

import com.wangziqing.common.dao.Type;
import com.wangziqing.genericDemo.TypeDaoImp;
import org.junit.Test;

/**
 * Created by ziqingwang on 2016/12/6.
 */
public class TypeDaoImpTest {
    @Test
    public void test() throws Exception {
        TypeDaoImp<Type> imp=new TypeDaoImp();
        imp.printlnClass();
    }
}

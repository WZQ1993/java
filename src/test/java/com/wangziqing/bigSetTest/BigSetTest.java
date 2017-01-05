package com.wangziqing.bigSetTest;

import com.wangziqing.bigDataDemo.BigDataTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ziqingwang on 2016/10/25.
 */

public class BigSetTest {
    Logger logger = LoggerFactory.getLogger(BigSetTest.class);

    @Test
    public void test(){
        long start=System.currentTimeMillis();
        BigDataTest bigDataTest=new BigDataTest();
        bigDataTest.initBigSet("C:\\workbench\\idea_gitlabc仓库\\java_base\\src\\main\\resources\\data.csv");
        assert  bigDataTest.isExist(25861631);
        logger.info("运行时间：{}",System.currentTimeMillis()-start);
    }
    @Test
    public void testDefault(){
        long start=System.currentTimeMillis();
        BigDataTest bigDataTest=new BigDataTest();
        assert bigDataTest.defaultIsExist("C:\\workbench\\idea_gitlabc仓库\\java_base\\src\\main\\resources\\data.csv",25861631);
        logger.info("运行时间：{}",System.currentTimeMillis()-start);
    }



}

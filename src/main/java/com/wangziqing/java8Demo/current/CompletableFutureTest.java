package com.wangziqing.java8Demo.current;

import java.util.concurrent.FutureTask;
import java.util.logging.Logger;

/**
 * Created by ziqingwang on 2016/11/8.
 */
public class CompletableFutureTest {
    private static final Logger LOGGER=Logger.getGlobal();
    /**
     * 回顾Future使用
     */
    private void FutureTest(){
        //回返回结果的任务
        FutureTask<String> futureTask=new FutureTask<String>(()->{
            //do something time-consuming background
            Thread.sleep(1000);
            return "result data";
        });
        //使用1
        Thread getDataThread=new Thread(futureTask);
        //做一些其他的事
        //获取任务结果

    }



}

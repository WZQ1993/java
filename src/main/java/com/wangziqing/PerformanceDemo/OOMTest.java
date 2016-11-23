package com.wangziqing.PerformanceDemo;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by ziqingwang on 2016/11/22.
 */
public class OOMTest {
    private static List<OOMTest> OOM= Lists.newArrayList();
    public static void main(String[] args){
        while(true){
            try{
                Thread.sleep(100);
                OOM.add(new OOMTest());
            }catch (InterruptedException e){

            }
        }
    }
}

package com.wangziqing.PerformanceDemo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 分析线程栈信息
 * Created by ziqingwang on 2016/11/24.
 */
public class StackDemo {
    private static final ReentrantLock lock=new ReentrantLock();
    public static void main(String[] args){
        int i=0;
        while(i<5){
            new Thread(() -> {
                while(true){
                    try{
                        lock.lock();
                        Thread.sleep(5000);
                    }catch (InterruptedException e){

                    }finally{
                        lock.unlock();
                    }
                }
            }).start();
            i++;
        }
    }
}

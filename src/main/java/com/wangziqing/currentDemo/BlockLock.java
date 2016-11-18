package com.wangziqing.currentDemo;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/**
 * 该类为群里有人写的阻塞锁,修改
 * Created by ziqingwang on 2016/11/16.
 */
public class BlockLock{
    private AtomicReference<Thread> sign = new AtomicReference<>();
    private List<Thread> queue= Lists.newArrayList();
    // 获取锁
    public void lock() {
        Thread current = Thread.currentThread();
        while (!sign.compareAndSet(null, current)){ //许可默认是被占用的
            queue.add(current);
            LockSupport.park();// 获取许可
        }
    }
    // 释放锁
    public void unlock() {
        Thread current = Thread.currentThread();
        sign.compareAndSet(current, null);
        if(!queue.isEmpty()){
            LockSupport.unpark(queue.remove(0));//释放许可
        }
    }

    volatile static int sum =0;

    public static void main(String[] args) throws InterruptedException {
        BlockLock bl=new BlockLock();
        for(int i = 0; i < 100; i++){
            new Thread(()->{
                bl.lock();
                sum++;
                bl.unlock();
            }).start();
        }
    }
}
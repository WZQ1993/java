package com.wangziqing.currentDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 此类是为了测试condition的用法
 * 如果想要达到奇数和偶数线程轮流执行
 * 1.signall(),condition判断无需使用while..
 * 2.signallAll(),condition判断必须在while之内
 * await(),signall(),signallAll()都必须在lock的保护下工作
 * signall()唤醒等待队列其中一个线程去竞争锁，而signallAll()唤醒所有等待队列，最终只有一个线程能获取锁。
 * Created by ziqingwang on 2016/10/24.
 */
public class ConditionTest {
    Logger logger = LoggerFactory.getLogger(ConditionTest.class);

    private volatile static int number=0;

    private static int i=0;

    private ReentrantLock lock;
    //奇数
    private Condition odd;
    //偶数
    private Condition even;

    ConditionTest(){
        this.lock=new ReentrantLock();
        this.even=lock.newCondition();
        this.odd=lock.newCondition();
    };
    private static void start(Runnable runnable){
        new Thread(runnable).start();
    }
    private static boolean isOdd(int number){
        return (number&1)==1;
    }
    public void test(){
        //10个奇数线程
        while(i<10){
            int index = i;
            i++;
            start(()->{
                try {
                    lock.lock();
                    logger.info("Thread{}获得锁", index);
                    if (!isOdd(number)) {
                        logger.info("Thread{} 等待add条件", index);
                        odd.await();
                        logger.info("Thread {} 被唤醒", index);
                    }
                    number--;
                    logger.info("Thread{}执行后数字为{};", index,number);
                    logger.info("Thread {} 唤醒一个等待even条件的线程 ", index);
                    even.signalAll();
                }catch (InterruptedException e){
                    logger.error(e.getMessage());
                }finally {
                    lock.unlock();
                }
            });
        }
        //10个偶数线程
        while(i<20){
            int index = i;
            i++;
            start(()->{
                try {
                    lock.lock();
                    logger.info("Thread{}获得锁", index);
                    if (isOdd(number)) {
                        logger.info("Thread{}等待even条件;", index);
                        even.await();
                        logger.info("Thread {} 被唤醒", index);
                    }
                    number++;
                    logger.info("Thread{}执行后数字为{};", index,number);
                    logger.info("Thread {} 唤醒一个等待odd条件的线程 ", index);
                    odd.signalAll();
                }catch (InterruptedException e){
                    logger.error(e.getMessage());
                }finally {
                    lock.unlock();
                }
            });
        }
    }
    public static void main(String[] args){
        ConditionTest test=new ConditionTest();
        test.test();
    }
}

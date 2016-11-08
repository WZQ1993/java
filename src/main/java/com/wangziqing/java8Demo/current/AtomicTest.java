package com.wangziqing.java8Demo.current;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.StampedLock;

/**
 * Created by ziqingwang on 2016/11/7.
 */
public class AtomicTest {
    private static AtomicLong atomicLong=new AtomicLong(0);
    private static Random random=new Random(47);
    private static StampedLock stampedLock=new StampedLock();
    /**
     * Aotimic使用（自增，自减都为原子操作）
     * 线程操作：将随机值赋值给atomicLong
     */
    private void AtomicDemo(){
        for(int i=0;i<10;i++){
            new Thread(()->{
                long oldValue;
                long randomLong;
                do{
                    oldValue=atomicLong.get();
                    randomLong=random.nextLong();
                    //不断循环，直到CAS操作成功
                }while (!atomicLong.compareAndSet(oldValue,randomLong));
            }).start();
        }
    }
    /**
     * 大量操作，乐观锁需要太多次尝试，性能下降
     * LongAdder,LongAccumulator，内部维护多个累加数，
     * 不同线程更新不同的累加数，只有需要总值的时候才累加所有的累加数
     * DoubleAdder,DoubleAccumulator同上
     */
    private void longAdderTest(){
        LongAdder longAdder= new LongAdder();
        longAdder.increment();//+1
        longAdder.decrement();//-1
        longAdder.add(10L);//增加一个值（非原子操作）
        //内部多个变量a1,a2,a3......
        LongAccumulator longAccumulator=new LongAccumulator(
                //提供一个函数（非原子操作）
                (a,v)->{return a+v;},
                //初始值
                0
        );
        //每次调用，其中一个变量an->(an,10);
        longAccumulator.accumulate(10);
        //get方法会统计所有的变量，输出a1 op a2 op a3 op......
        longAccumulator.get();
    }

    /**
     * StampedLock乐观读
     */
    private long getCurrentValue(){
        //获得一个印戳
        long stamp=stampedLock.tryOptimisticRead();
        long currentValue=atomicLong.get();
        //检验印戳是否可用
        if(!stampedLock.validate(stamp)){
            //不可用，某个线程正在写,使用悲观锁操作
            stamp=stampedLock.readLock();
            currentValue=atomicLong.get();
            stampedLock.unlockRead(stamp);//释放
        }
        return currentValue;
    }

}

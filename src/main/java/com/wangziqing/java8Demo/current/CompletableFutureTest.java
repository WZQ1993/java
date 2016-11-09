package com.wangziqing.java8Demo.current;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * Created by ziqingwang on 2016/11/8.
 */
public class CompletableFutureTest {
    private static final Logger LOGGER = Logger.getGlobal();
    private ExecutorService threadExecutor = Executors.newSingleThreadExecutor();
    //返回结果的任务
    private String getData(){
        //do something time-consuming background
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            LOGGER.info("线程中断");
        }
        return "result data";
    }
    /**
     * 回顾Future使用
     */
    private void FutureTest() throws InterruptedException, ExecutionException {
        Callable<String> callable=()->getData();
        FutureTask<String> futureTask = new FutureTask<String>(callable);
        //使用1
        Thread getDataThread = new Thread(futureTask);
        //做一些其他的事
        //获取任务结果
        //如果调用还未返回结果，则阻塞线程
        LOGGER.info(futureTask.get());
        //使用2（线程池提交，返回future）
        Future<String> resultFuture = threadExecutor.submit(callable);
        LOGGER.info(resultFuture.get());
    }

    /**
     * compeletableFuture
     */
    private void compeletableFutureTest() {
        Supplier<String> stringSupplier=()->getData();
        Function<String,Integer> function=(str)->str.length();
        Consumer<Integer> consumer=(length)->LOGGER.info("长度为"+length);
        //creat
        // 静态方法supplyAsync()，接收消费者函数，一步执行返回结果
        // runAsync()，接收Runable()，返回CompletetableFunture<void>对象
        CompletableFuture<Void> resultFuture=
                CompletableFuture.supplyAsync(stringSupplier)//Step1：获取数据，返回C<String>
                .thenApply(function)//Step2:进一步处理数据，得到结果，返回C<Integer>
                .thenAccept(consumer);//Step3:处理数据，返回C<Void>
        //tip:**Async()方法都有两种版本，1，在普通的ForkJoinPool运行，2，提供自定义的线程池
        //异步操作
        //Action
        //thenApply(T->U);为结果提供一个操作，返回另一种结果
        //thenCompose(T->Completetable<U>);为结果提供一个操作，返回另一个可完成的Future,用于组合
        //thenAccept(T->Void);为结果提供一个操作,不返回结果
        //handle((T,Throwable)->U);处理结果或者错误
        //whenComplete((T,Throwable)->void);处理结果或者错误，不返回结果
        //thenRun(Runable);执行Runable对象

        /**
         * 组合可完成Future
         * firstAction; int->CompletableFuture<String>
         * secondAction; String->CompletableFuture<Integer>
         *     组合:int->CompletableFuture<Integer>
         */
        firstAction(1).thenCompose(str->secondAction(str));
        //组合方法
        //CompletableFuture<T>.thenCombine(CompletableFuture<U>,(T,U)->V);执行两个对象，完成后处理结果，返回最终结果
        //CompletableFuture<T>.thenAcceptBoth(CompletableFuture<U>,(T,U)->Void);执行两个对象，完成后处理结果，无返回
        //CompletableFuture<T>.runAfterBoth(CompletableFuture<U>,Runable);执行两个对象，完成后执行runable
        //CompletableFuture<T>.applyToEither(CompletableFuture<U>,T->U);其中一个对象执行完成，应用函数
        //CompletableFuture<T>.acceptEither(CompletableFuture<U>,T->Void);同上，无返回
        //CompletableFuture<T>.runAfterEither(CompletableFuture<U>,Runable);同上，执行runable
        //CompletableFuture.allOf(CompletableFuture<?>...);所有对象完成后结束，无返回
        //CompletableFuture.anyOf(CompletableFuture<?>...);任何一个对象完成后结束，无返回

    }
    private CompletableFuture<String> firstAction(int i){
        return CompletableFuture.supplyAsync(()->{
            int index=0;
            StringBuilder str=new StringBuilder();
            while(index<i){
                str.append(i);
                index++;
            }
            return str.toString();
        });
    }
    private CompletableFuture<Integer> secondAction(String str){
        return CompletableFuture.supplyAsync(()->str.length());
    }

}

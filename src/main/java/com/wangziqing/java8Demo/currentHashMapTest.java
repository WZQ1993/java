package com.wangziqing.java8Demo;

import com.google.common.collect.Maps;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * Created by 王梓青 on 2016/11/7.
 */
public class currentHashMapTest {
    private static final Logger LOGGER=Logger.getGlobal();
    private ConcurrentHashMap<String,Integer> map=
            new ConcurrentHashMap<>();
    public currentHashMapTest(){
        map.put("55555",5);
        map.put("666666",6);
        map.put("7777777",7);
    }
    /**
     * java 8提供了很多用于原子更新的方法
     * 函数运行时其他一些更新映射的操作可能会被阻塞
     */
    private void atomicUpdate(){
        String word="55555";
        //接受一个函数，参数为键及其关联的值，若不存在键，则值为null；
        //函数返回更新后的值；
        map.compute(word,(k,v)->{
            return null==v?1:v+1;
        });
        //存在时才更新值，参数为value
        map.computeIfPresent(word,(k,v)->v+1);
        //不存在时才计算新值，参数为key
        map.computeIfAbsent(word,(k)->1);
        //k不存在，返回初始值；
        // k存在，调用函数对初始值和旧值计算返回
        map.merge(
                word,//key
                1,//初始
                (existingValue,newValue)->existingValue+newValue
                );
    }

    /**
     * 批量数据操作，线程安全
     * 返回结果的时候除非可以确保没有其他值修改，否则应该作为近似值
     * （如何保证？锁定数据）
     */
    private void batchOperation(){
        //search()查找,reduce()聚合，foreach()遍历应用
        //以上三个方法都有针对key,value,k&v,entry四个版本
        //第一个参数为并行阀值，超过该数量则并行处理
        //search()方法查找到第一个则返回
        map.search(1,(k,v)->{
            if(k.endsWith("5")&&v>4){
                return k;
            }else{
                return null;
            }
        });
        //foreach()遍历数据，应用函数处理
        //1.对每个数据项默认应用
        map.forEach(1,(k,v)->{
            LOGGER.info(k+"`s length is"+v);
            });
        //2.先使用转换器函数，再对结果应用消费者函数
        map.forEach(1,
                (k,v)->k+"`s length is"+v,//转换器可作为一个过滤器，如果返回null，则不应用消费者函数
                (str)->LOGGER.info(str));
        //reduce()对数据执行累加操作，返回结果
        map.reduceValues(1,
                v->Integer.valueOf(v),//转换器
                Integer::sum);//聚合操作
        //reduce***ToLong/Integer/Double(),提供int，long，double原始类型的输出；
    }
}

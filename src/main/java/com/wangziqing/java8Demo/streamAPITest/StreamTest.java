package com.wangziqing.java8Demo.streamAPITest;

import com.google.common.collect.Lists;

import java.sql.Array;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by ziqingwang on 2016/10/25.
 */
public class StreamTest {

    List<String> list= Lists.newArrayList();

    /**
     * 构造Stream
     */
    private void creatStream(){
        Stream stream;
        //collection接口已实现stream方法
        stream=list.stream();

        //构造任意参数的Stream
        stream=Stream.of("ob1","ob2","ob3");

        //将一部分转换为Stream
        stream=Arrays.stream(new String[]{""},0,6);

        //创造无限的Stream
        //(参数是一个supplier接口，需要元素时从get方法取得)
        stream=Stream.generate(()->"object");
        //参数实际上是UnaryOperator接口，将之前的值作为接口方法的参数调用
        stream=Stream.iterate(0,n->{ return 2*n+1; });
    }

    /**
     * Stream方法
     */
    private void userStream(){
        Stream<Integer> stream= Stream.of(0,1,2,3,4,5,6,7,8,9,10);
        /**
         *   流转换
         *   filter()
         *   map()
         *   flatMap()
         */
        //参数实际上是一个Predicate接口对象，针对Stream值调用test函数，return boolean
        stream.filter(value->{
                    return (value.intValue()&1)==0;
        });

        //参数实际上是一个function接口对象，调用 R apply(T t);转换吼将值收集到流中返回
        stream.map(intValue->String.valueOf(intValue));

        //Function接口方法返回的是流，调用flatmap()方法会将返回的流展开成一个流返回
        stream.flatMap(intValue->{
            return Stream.of(intValue,intValue+1,intValue+2);
        });

        /**
         * 提取子流和组合流
         */
        //返回n个元素的子流
        stream.limit(2);

        //丢弃前面n个元素
        stream.skip(2);

        //连接两个流
        Stream.concat(stream,stream);

        //peek会返回与原始流相同元素的流，只不过每次获取一个元素，会默认调用coumser的方法
        stream.peek(e->{
            System.out.print("正在消费元素："+e);
        });

        /**
         * 有状态的流转换
         */
        //去除重复元素
        stream.distinct();

        //排序，使用Comparator接口
        Comparator<Integer> comparator=(a,b)->{
            if(a<b)return -1;
            else if(a>b)return +1;
            else return 0;
        };
        stream.sorted(comparator);

        //排序，元素实现comparable接口
        stream.sorted();

        /**
         * 聚合方法
         */
        //返回总数，最大，最小，需要比较器
        stream.count();
        stream.max(Integer::compareTo);
        stream.min(comparator);
        //返回首个，以及只要匹配则结束计算返回
        Predicate<Integer> isLargeThanFive= value->value>5;
        stream.filter(isLargeThanFive).findFirst();
        stream.filter(isLargeThanFive).findAny();
        //存在匹配，所有匹配，没有匹配
        stream.anyMatch(isLargeThanFive);
        stream.allMatch(isLargeThanFive);
        stream.noneMatch(isLargeThanFive);

        /**
         * 聚合操作,将元素成一个值
         */
        //参数可以认为是一个聚合操作op，reduce返回v1 op v2 op v3 ......,参数表现(v1 op v2)
        Optional<Integer> sum=stream.reduce((x,y)->x+y);
        Integer sumInteger=stream.reduce(0,(x,y)->x+y);

    }

}

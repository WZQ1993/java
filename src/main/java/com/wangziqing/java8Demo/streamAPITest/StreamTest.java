package com.wangziqing.java8Demo.streamAPITest;

import com.google.common.collect.Lists;

import java.sql.Array;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
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
     *   流转换
     *   filter()
     *   map()
     *   flatMap()
     */
    private void streamOPeration_1(){
        Stream<Integer> stream= Stream.of(0,1,2,3,4,5,6,7,8,9,10);

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
    }
    /**
     * 提取子流和组合流
     */
    private void streamOPeration_2(){
        Stream<Integer> stream= Stream.of(0,1,2,3,4,5,6,7,8,9,10);

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
    }
    /**
     * 有状态的流转换
     */
    private void streamOPeration_3(){
        Stream<Integer> stream= Stream.of(0,1,2,3,4,5,6,7,8,9,10);

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
    }
    /**
     * 流聚合（输出为一个结果）
     * 聚合方法
     */
    private void streamOPeration_4(){
        Stream<Integer> stream= Stream.of(0,1,2,3,4,5,6,7,8,9,10);
        Comparator<Integer> comparator=(a,b)->{
            if(a<b)return -1;
            else if(a>b)return +1;
            else return 0;
        };
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
         * op==BinaryOperator接口，方法（T,T）->T 类型一致
         *
         * 聚合操作,将元素成一个值
         */
        //参数可以认为是一个聚合操作op，reduce返回v1 op v2 op v3 ......,参数表现(v1 op v2)
        Optional<Integer> sum=stream.reduce((x,y)->x+y);
        //聚合操作的第二种形式，初始值 op v1 op v2 op v3 ...，返回的是起点值而不是Optional
        Integer sumInteger=stream.reduce(0,(x,y)->x+y);
        //聚合的第三种形式，对流元素的某些属性聚合，需要一个新的累加器BiFunction函数（U,T）-> U  ,结果的U会变成下一次累加的参数
        //并且并行计算的时候，需要将结果聚合起来的BinaryOperator接口函数
        //实际开发中可以先对流映射再处理
        stream.reduce(
                "",//结果类型U
                //参数1：前一次操作的结果U     参数2：这一次操作的元素T    输出：这一次累加的结果U   （U,T）-> U
                (totalStr,integerValue)-> totalStr+integerValue.toString() ,
                //并行处理，（U,U）-> U
                (totalStr1,totalStr2)-> totalStr1+"-"+totalStr2
        );
    }

    /**
     * 收集结果，把流元素收集为集合
     */
    private void streamOPeration_5(){
        Stream<Integer> stream= Stream.of(0,1,2,3,4,5,6,7,8,9,10);
        //获得流所有元素的数组，不传入构造函数得到Object[]
        stream.toArray(String[]::new);
        //收集到集合中
        //传入的单个参数分别是 （集合构造，添加单个元素，添加集合）
        stream.collect(HashSet::new,HashSet::add,HashSet::addAll);
        stream.collect(Collectors.toList());
        stream.collect(Collectors.toSet());
        //控制得到的集合类型
        stream.collect(Collectors.toCollection(HashSet::new));
        //将流收集并使用分隔符连接起来
        String result=stream.map(i->i.toString()).collect(Collectors.joining(","));
        //将流整合为一个数值（和，平均，最大，最小）
        IntSummaryStatistics summaryStatistics=
                stream.collect(Collectors.summarizingInt(i->i));
        summaryStatistics.getAverage();
        summaryStatistics.getCount();
        summaryStatistics.getMax();
        summaryStatistics.getMin();
        summaryStatistics.getSum();
        //循环流
        stream.forEach(System.out::print);
        //区别，按照流的顺序，放弃并行计算
        stream.forEachOrdered(i->i.toString());
        //以上为终止操作，如果要继续使用流，用peek(),该方法会返回原始流，但是每次获取元素会调用传入的函数。见上文
    }

}

package com.wangziqing.java8Demo.streamAPITest;

import com.google.common.collect.Lists;

import java.sql.Array;
import java.util.*;
import java.util.function.Function;
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
        stream.collect(Collectors.toList());//default==ArrayList
        stream.collect(Collectors.toSet());//default==HashSet
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

    /**
     * 将结果收集到map中
     * 默认是HashMap
     */
    private void streamOperation_6(){
        Stream<Integer> stream= Stream.of(0,1,2,3,4,5,6,7,8,9,10);
        //0.用于并发
        Map<Integer,String> map0=stream.collect(Collectors.toConcurrentMap(
                i->i,
                i->i.toString()
        ));
        //1.定义两个函数，一个产生KEY，一个产生VALUE
        Map<Integer,String> map1=stream.collect(Collectors.toMap(
                i->i,
                i->i.toString()
        ));
        //2.Function.identity()实际的元素
        Map<String,Integer> map2=stream.collect(Collectors.toMap(
                i->i.toString(),
                Function.identity()
        ));
        //3.如果存在相同的key，聚合函数接口BinaryOperator   （旧值，新值）——》处理后的值
        Map<String,Integer> map3=stream.collect(Collectors.toMap(
                i->i.toString(),
                Function.identity(),
                (existValue,newValue)->existValue+existValue
        ));
        //4.想要自定义返回结果
        Map<String,Integer> map4=stream.collect(Collectors.toMap(
                i->i.toString(),
                Function.identity(),
                (existValue,newValue)->existValue+existValue,
                TreeMap::new
        ));

    }
    /**
     * 分组
     */
    private void streamOperation_7(){
        Stream<Integer> stream= Stream.of(0,1,2,3,4,5,6,7,8,9,10);
        // Function函数接口 T->R 作为分类函数，得到相同结果的分在一组，以返回值为key，返回map<R,List<T>>;
        //下面例子返回 0->(偶数)；1->(奇数)
        Map<Integer,List<Integer>> map1=stream.collect(Collectors.groupingBy(
                i->i%2
        ));
        //分类函数是一个predicate函数接口，根据校验结果分类
        Map<Boolean,List<Integer>> map2=stream.collect(Collectors.partitioningBy(
                i->i%2==0
        ));
        //自定义收集器
        Map<Boolean,Set<Integer>> map3=stream.collect(Collectors.partitioningBy(
                i->i%2==0,Collectors.toSet()
        ));
        //返回并发map
        Map<Integer,List<Integer>> map4=stream.collect(Collectors.groupingByConcurrent(
                i->i%2
        ));
        //其他收集器（收集器决定map的值的类型）
        //Collectors.counting(),返回收集元素总数
        //Collectors.summing(Int|double|long)(将元素转化为值的函数To..Function); 对分组元素调用To..Function()并聚合起来
        //Collectors.maxBy(比较器)|minBy(比较器);生成分组元素中最大最小
        //Collectors.mapping(映射函数，聚合函数);先对分组的元素映射操作，然后再聚合结果(返回的是数值类型)

        //对分组的元素先映射然后进行一次普通的聚合操作，返回的结果可以是其他类型
        //Collectors.reducing(初始值,映射函数，聚合函数);
    }

}

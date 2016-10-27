package com.wangziqing.java8Demo;

import java.util.Random;
import java.util.function.*;

/**
 * 函数式接口
 * Created by 王梓青 on 2016/10/27.
 */
public class FunctionInteface {
    private final Random random=new Random(47);

    //Supplier<T>接口，译作供应商，提供一个获取T类型对象的T get()函数
    Supplier<Integer> supplier=
            ()->random.nextInt();

    //Consumer<T>接口，译作消费者，提供一个处理T类型对象的void accept(T)函数
    Consumer<Integer> consumer=
            integer->System.out.println("deal with "+integer);

    //Consumer<T,U>接口，译作消费者，提供一个处理T,U类型对象的void accept(T,U)函数
    BiConsumer<Integer,String> biConsumer=
            (integer, string) -> System.out.println("deal with "+integer+" and "+string);

    //Predicate<T>接口， 译作验证器，提供一个校验T类型对象的boolean test(T)函数
    Predicate<Integer> predicate=
            (integer)->integer>0;

    //To...Function接口，译作。。类型转换器，提供一个转换T类型对象为指定。。的函数
    ToIntFunction<String> toIntFunction=
            (string)->Integer.valueOf(string);
//    ToLongFunction<T>
//    ToDoubleFunction<T>

    //..Function接口，提供一个接受R类型对象转换为原始类型的函数
    IntFunction<String> intFunction=
            String::valueOf;
//    LongFunction<R>
//    DoubleFunction<R>

    //Function<T,R>接口，普通函数，提供一个接收T对象，返回R对象
    Function<Integer,String> function=
            integer -> String.valueOf(integer);

    //BiFunction<T,R,U>,提供一个接收T,R对象，返回U对象的函数
    BiFunction<Integer,Long,String> biFunction=
            (intValue,longValue)->String.valueOf(intValue+longValue);

    //一元操作，继承Function<T>接口
    UnaryOperator<Integer> unaryOperator=
            integer -> integer++;
    
    //二元操作，继承BIFunction接口
    BinaryOperator<Integer> binaryOperator=
            (integer, integer2) -> integer+integer2;

}

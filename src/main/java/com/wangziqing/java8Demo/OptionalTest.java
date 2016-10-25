package com.wangziqing.java8Demo;

import java.util.Optional;

/**
 * ifPresent如果存在则调用，否则返回空optional
 * Created by ziqingwang on 2016/10/25.
 */
public class OptionalTest {

    private static Optional<Integer> tryIsLargeThan5(Integer integer){
        return integer>5?Optional.of(integer):Optional.empty();
    }

    private static Optional<Integer> tryIsSmallerThan10(Integer integer){
        return integer<10?Optional.of(integer):Optional.empty();
    }

    //使用flatMap组合可选值函数,执行functional接口方法后不会用optional包裹返回值。
    public static Optional<Integer> tryIsBetween5And10(Integer integer){
        return Optional.of(integer)
                //参数接口的方法已经返回optional
                .flatMap(OptionalTest::tryIsLargeThan5)
                .flatMap(OptionalTest::tryIsSmallerThan10);
    }
}

package com.wangziqing.java8Demo.current;

import java.util.Arrays;

/**
 * Created by ziqingwang on 2016/11/8.
 */
public class ParallelArraysTest {
    private int[] array={1,2,3,4,5,6,7,8,9,10};
    /**
     * 并行数组操作
     */
    private void parallelArrayOpration(){
        //并行排序(可以指定comparator)
        Arrays.parallelSort(array);
        //根据元素索引计算值
        Arrays.parallelSetAll(array,i->i%10);
        //将数组的每一个元素指定为关联操作的前缀的累积
        Arrays.parallelPrefix(array,(x,y)->x*y);
        //计算（1，1*2，1*2*3，....）
    }
}

package com.wangziqing.bigDataDemo;

import java.util.BitSet;

/**
 * Created by 王梓青 on 2016/10/25.
 */
public class BitMapTest {
    private void test(){
        //创建一个一千万bit的bitSet，初始化所有位为false
        BitSet bitSet=new BitSet(10000000);
        //比哪里大数据集，并置位
        bitSet.set(999,true);

    }
}

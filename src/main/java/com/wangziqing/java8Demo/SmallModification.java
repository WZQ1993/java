package com.wangziqing.java8Demo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * java 8其他一些杂项改进
 *
 * Created by ziqingwang on 2016/11/8.
 */
public class SmallModification {
    private void test(){
        //1.字符串
        //用分隔符组合字符串，String.join(",",String...);

        //2.数字类
        //无符号数学计算(Byte,Short,Integer)->无符号int,long
        //有符号Byte：-128到127，无符号Byte：0到255
        Integer.toUnsignedLong(12);

        //处理无符号值
        //compareUnsigned(),devideUnsigned(),remainderUnsigned()
        // 无符号整数进行比较，除法，求余
        //+,-可以正确处理无符号整数，乘法需要转换为长整形相乘，调用toUnsignedLong()（因为可能会溢出）

        //Float,Double增加了isFinite()方法判断是否有限；
        //求除数和余数
        Math.floorMod(12,10);//余数 2
        Math.floorDiv(12,10);//除数 1

        //3.比较器(利用借口可以拥有具体实现的方法)
        //static method comparing(FunctionT->U)接受一个键提取器函数，先映射后对结果排序
        //              thenComparing(Function) 多级比较
        //可以指定比较函数，先映射，后对结果应用排序
        //如果键提取函数可以返回null，使用nullsFirst(),nullsLast()改造比较器，将null看成大于或者小于正常值的值
        //static method comparing(FunctionT->U,Comparator.nullsFirst(比较函数))
        //颠倒比较器
        //static method reverseOrder()，recersed()
    }

    /**
     * 文件使用
     */
    private void fileTest()throws FileNotFoundException,IOException{
        //读取文件行的流,每个String元素都为文件的一行
        Stream<String> lines= Files.lines(Paths.get("目录","文件名"));
        //按行读取其他输入
        Stream<String> lines_=new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(new File("文件路径")))).lines();

        //遍历目录项的流，不会递归处理子目录
        try(Stream<Path> entryies=Files.list(Paths.get("目录"))){
            //java7 try-with-resource会自动处理文件关闭
        }
        //遍历目录项的流，可以递归处理(可以指定深度)
        try(Stream<Path> entryies=Files.walk(Paths.get("目录"))){
            //java7 try-with-resource会自动处理文件关闭
        }
        //遍历目录项的流，使用find()方法可以在遍历的时候进行过滤
    }

    /**
     * Base64编码
     */
    private void base64Tst(){
        //编码
        Base64.getEncoder().encodeToString("字符串".getBytes());
        Base64.getUrlEncoder();
        Base64.getMimeEncoder();
        //解码
        Base64.getDecoder().decode("base64字符串");
    }

    /**
     * 1.可重复注解
     * 2.java8之前，注解用在声明上，现在可以用在类型上，如List<@注解 String>
     * 3.方法参数映射 Parameter可以读取被注解的方法参数的名称
     */

    /**
     * 1.NULL检查
     * Objects,isNull(),nonNull()
     * 2.命名捕获组（java7）
     * (?<group1>//d+)(?<group2>a-z),可以在start,end,group中使用组名group1,group2
     */
}

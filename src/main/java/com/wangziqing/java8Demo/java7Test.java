package com.wangziqing.java8Demo;

import java.io.*;
import java.nio.file.*;
import java.util.Objects;
import java.util.Scanner;

/**
 * 包含一些有用的java7特性
 * Created by ziqingwang on 2016/11/8.
 */
public class java7Test {
    /**
     * 1.try-with-resource语句
     * 2.忽略异常
     */
    private void exceptionTest(){
        try(Scanner in=new Scanner(Paths.get("路径"))){
            in.hasNext();
        }catch (IOException e){

        }
        /**
         * 如果处理过程产生IO异常，而调用close()方法又再次抛出异常，IIO异常会被捕获
         * 异常类的构造器有可以接受两个参数，
         * 分别用来禁用忽略异常和禁用堆栈跟踪
         * 忽略异常：当第一个异常产生后，
         *           需要执行其他清理操作（finally()||资源关闭），
         *           但是在执行过程中又产生了其他异常；
         *           可以调用ex.addSupressed(ex2)，加入忽略异常
         *           （产生多个异常）
         * 堆栈跟踪：产生异常时代码的执行链
         *           （只产生一个异常）
         */
    }

    /**
     * 文件使用
     * 1.Path路径
     */
    private void fileTest()throws IOException{
        /**
         * Path路径，一个文件路径的抽象
         */
        //creat
        Paths.get("root","dir1","file.txt");// 相对路径，/root/dir1/file.txt;
        Paths.get("/","dir1","file.txt");//    绝对路径，/为根目录，取决于操作系统
        //组合、解析路径
        Path p1=Paths.get("root","dir1");
        Path p2=Paths.get("conf.txt");
        p1.resolve(p2);//返回/root/dir1/conf.txt.产生子目录
        p1.resolveSibling("dir2");//返回/root/dir2.产生兄弟目录
        p1.toAbsolutePath();//返回指定路径的绝对路径，根据调用JVM的路径进行解析，如在/JVM下启动应用，则返回./JVM/root/dir1

        /**
         * 读取、写入文件
         */
        //read
        //all
        Files.readAllBytes(p1);//new String(byte[],charset)转换
        //byLine
        Files.readAllLines(p1);
        //write
        Files.write(p1,"内容".getBytes(), StandardOpenOption.APPEND);//追加写入

        //处理大文件的时候，转换为Stream||reader/Writer
        InputStream in=Files.newInputStream(p1);
        OutputStream out=Files.newOutputStream(p1);
        Reader reader=Files.newBufferedReader(p1);
        Writer writer=Files.newBufferedWriter(p1);

        //文件复制copy可以将Path和in||out连接起来
        Files.copy(in,p1);
        Files.copy(p1,out);

        /**
         * 创建文件和目录
         */
        //creat dir
        Files.createDirectory(p1);
        Files.createDirectories(p1);//递归创建
        //creat file
        Files.createFile(p1);
        Files.exists(p1);//判断文件是否存在


        /**
         * 复制，copy
         * 移动，move
         * 删除文件 delete() deleteIfExists()
         */

    }

    private void otherTest(){
        //eauals()中的null判断
        Integer a=1;
        Integer b=2;
        // a==null && b==null,return true;
        // a==null && b!=null,return false;
        // other a.equals(b);
        Objects.equals(a,b);

        //计算hash码
        //对象有实例变量a,b，hash重写 Objects.hash(a,b)；

        //数值比较
        //return (a-b)；如果b为负数，有溢出的风险，使用Integer.compare(a,b);
    }

}

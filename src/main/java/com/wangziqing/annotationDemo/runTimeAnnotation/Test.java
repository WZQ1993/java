package com.wangziqing.annotationDemo.runTimeAnnotation;

import java.lang.annotation.Repeatable;

/**
 * 反射AnnotatedElement接口处理注解（）
 * Created by 王梓青 on 2016/10/17.
 */
@RunTimeAnnotation(value = "运行时注解1")
@RunTimeAnnotation(value = "运行时注解2")
public class Test {
    public static void main(String[] args){
        Class<Test> clazz=Test.class;
        //返回null，因为重复注解后实际上被转换成容器注解RunTimeAnnotations
        clazz.getAnnotation(RunTimeAnnotation.class);
        //return RunTimeAnnotation[]，浏览特定的注解类型的所有注解容器，返回注解数组[]
        clazz.getAnnotationsByType(RunTimeAnnotation.class);
        clazz.getAnnotations();
        clazz.isAnnotationPresent(RunTimeAnnotation.class);
        //忽略继承的注解
        clazz.getDeclaredAnnotations();
    }
}

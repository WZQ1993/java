package com.wangziqing.annotationDemo.runTimeAnnotation;

/**
 * 反射AnnotatedElement接口处理注解（）
 * Created by 王梓青 on 2016/10/17.
 */
@RunTimeAnnotation(value = "运行时注解")
public class Test {
    public static void main(String[] args){
        Class<Test> clazz=Test.class;
        clazz.getAnnotation(RunTimeAnnotation.class);
        clazz.getAnnotations();
        clazz.isAnnotationPresent(RunTimeAnnotation.class);
        //忽略继承的注解
        clazz.getDeclaredAnnotations();
    }
}

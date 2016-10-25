package com.wangziqing.annotationDemo.compileAnnotation;

import javax.annotation.processing.*;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * 使用方法
 *  1.apt工具
 *  2.服务注册文件(META-INF/services/javax.annotation.processing.Processor)
 *    打包后，javac -cp 包.jar Test.java
 * Created by ziqingwang on 2016/10/17.
 */
@SupportedAnnotationTypes("com.wangziqing.runTimeAnnotation.compileAnnotation.Assignment")
public class AssignmentProcessor extends AbstractProcessor {
//    SupportedAnnotationTypes: 用来表明注释处理器支持哪些注释类型的注释，这里就是指我们刚才定义的PrintInject这个类
//    SupportedSourceVersion: 用来表明注释处理器支持到的JAVA版本
//    processingEnv: 注释处理工具框架将提供一个工作环境processingEnv，使其可以使用该框架提供的功能来编写新文件、报告错误消息并查找其他实用工具。
//    TypeElement: 我们所定义的全部不同注解类型，好比这里应该返回PrintInject.class
//    getElementsAnnotatedWith: 返回使用相应注解类型注解的全部元素。你写了几个这种类型的注解，Set的长度就会有多长
//    printMessage: 使用messager去直接打印不同类型的不同信息。类似于Log，这边是Kind，有NOTE，ERRPR，WARNING之类，我们一般在Android Studio控制台里面看到的各种信息，就有一些是他们打印出来的
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Messager messager = processingEnv.getMessager();
//        for (TypeElement te : annotations) {
            for (Element e : roundEnv.getElementsAnnotatedWith(Assignment.class)) {
                messager.printMessage(Diagnostic.Kind.NOTE, "Printing: " + e.toString());
            }
//        }
        return true;
    }

}

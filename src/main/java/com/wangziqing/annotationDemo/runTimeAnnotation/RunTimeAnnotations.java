package com.wangziqing.annotationDemo.runTimeAnnotation;

import java.lang.annotation.*;

/**
 * 可重复注解容器
 * Created by ziqingwang on 2016/11/8.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RunTimeAnnotations {
    RunTimeAnnotation[] value();
}

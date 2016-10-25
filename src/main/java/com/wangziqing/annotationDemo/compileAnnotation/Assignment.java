package com.wangziqing.annotationDemo.compileAnnotation;

import java.lang.annotation.*;

/**
 * Created by ziqingwang on 2016/10/17.
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Assignment {
    String value()default "defalt";
}

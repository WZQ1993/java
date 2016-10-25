package com.wangziqing.annotationDemo.runTimeAnnotation;

import java.lang.annotation.*;

/**
 * Created by 王梓青 on 2016/10/17.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RunTimeAnnotation {
    String value() default "默认值";
}

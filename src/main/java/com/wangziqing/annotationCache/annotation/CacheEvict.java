package com.wangziqing.annotationCache.annotation;

import java.lang.annotation.*;

/**
 * Created by ziqingwang on 2016/12/23.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CacheEvict {
    String value() default "";
}

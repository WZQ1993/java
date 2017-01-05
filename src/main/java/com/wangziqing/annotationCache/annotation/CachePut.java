package com.wangziqing.annotationCache.annotation;

import java.lang.annotation.*;

/**
 * 用于缓存更新
 * 确保方法被调用，同时返回值被记录到缓存中
 * 其他同Cacheable{@link Cacheable}
 * Created by ziqingwang on 2016/12/23.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CachePut {
    String value() default "";
}

package com.wangziqing.annotationCache.model;

/**
 * 思路
 * 1.aop织入缓存逻辑
 * 2.1 取package.class.method作为key，封装cache存入
 * 2.2 取package.class.method+参数作为key，封装cache存入
 * 3.CacheManager 必须线程安全
 * Created by ziqingwang on 2016/12/23.
 */
public interface CacheManager {
    Cache getCache(String key);
    void setCache(String key,Cache cache);
}

package com.wangziqing.collectionDemo;

import com.google.common.collect.Sets;

import java.util.Optional;
import java.util.Set;

/**
 * Created by 王梓青 on 2016/10/24.
 */
public class SetOperationTest {
    /**
     * 交集
     * @param c1
     * @param c2
     * @param <T>
     * @return
     */
    private <T>Optional<Set<T>> tryGetIntersection(Set<T> c1,Set<T>c2){
        Set<T> result= Sets.newHashSet();
        result.addAll(c1);
        result.retainAll(c2);
        return Optional.of(result);
    }

    /**
     * 差集
     * @param c1
     * @param c2
     * @param <T>
     * @return
     */
    private <T>Optional<Set<T>> tryGetDifference(Set<T> c1,Set<T>c2){
        Set<T> result= Sets.newHashSet();
        result.addAll(c1);
        result.removeAll(c2);
        return Optional.of(result);
    }

    /**
     * 并集
     * @param c1
     * @param c2
     * @param <T>
     * @return
     */
    private <T>Optional<Set<T>> tryGetUnion(Set<T> c1,Set<T>c2){
        Set<T> result= Sets.newHashSet();
        result.addAll(c1);
        result.addAll(c2);
        return Optional.of(result);
    }
}

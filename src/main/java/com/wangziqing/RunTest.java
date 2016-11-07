package com.wangziqing;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by ziqingwang on 2016/10/27.
 */
public class RunTest {
    private static final String KUGOUIDS_PATTER = "^(\\d*,)+";
    private static Pattern kugouIdsPattern = Pattern.compile(KUGOUIDS_PATTER);

    public static void main(String[] args) {
        String kugouIdsStr = "123,123,123,123,123，123，";
        Matcher matcher = kugouIdsPattern.matcher(kugouIdsStr);
        int matcherLength = 0;
        //只有匹配到前面才返回true
        if (matcher.lookingAt()) {
            matcherLength = matcher.end();
        }
        if (matcherLength == kugouIdsStr.length()) {
            //整个匹配
            System.out.print("整个匹配");
        } else {
            StringBuilder msg = new StringBuilder("出错位置：");
            if (kugouIdsStr.length() - matcherLength > 10) {
                msg.append(kugouIdsStr.substring(matcherLength, matcherLength + 10));
            } else {
                msg.append(kugouIdsStr.substring(matcherLength, kugouIdsStr.length()));
            }
            System.out.print(msg.toString());
        }

    }
}


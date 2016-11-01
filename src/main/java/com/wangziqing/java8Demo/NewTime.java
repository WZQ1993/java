package com.wangziqing.java8Demo;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * java8新的日期和时间API
 * 特性
 * 1.Instant瞬间（旧的Date类）
 * 2.Duration时间段，两个瞬间的间隔
 * 3.LocalDateTime本地时间，没有时区信息
 * 4.TemporalAdjuster处理日历计算（如找到某月的第一天）
 * 5.ZoneDateTime带时区信息的时间点
 * 6.Period时段，带时区信息的时间间隔
 * 7.DateTimeFormatter格式化解析日期
 * Created by 王梓青 on 2016/11/1.
 */
public class NewTime {
    private void instantAndDurationTest(){
        //瞬间
        Instant start=Instant.now();
        Instant end=start.plusSeconds(5);
        //时间段
        Duration duration=Duration.between(start,end);
        //对Instant，Duration 加减乘除
        /**
         plus**();
         minus**();
         //只用于Duration
         multipliedBy(n);
         dividedBy(n);
         negated();×-1
         isZero();
         isNegativ();是否负值
         */
    }

    /**
     * 与Date不同，月份1到12
     */
    private void LocalDate(){
        //creat
        LocalDate localDate=LocalDate.now();
        localDate=LocalDate.of(1993,1,26);
        /**加减
         plus**();
         minus**();
         */
        //将日期的 日，月，年设置为给定的值
        localDate.withDayOfMonth(20);//天数设置为该月份的第n天
        localDate.withDayOfYear(155);//天数设置为该年份的第n天
        localDate.withMonth(2);
        localDate.withYear(1994);
        //获取年月日
        localDate.getDayOfWeek().getValue();//获得星期几(周一至周日分别为1-7)
        localDate.getDayOfMonth();//获得月份天数1-31
        localDate.getDayOfYear();//获得年份天数1-366
        localDate.getMonth();
        localDate.getMonthValue();//获取月份
        localDate.getYear();//获取年份
        //获取两个日期之间的时间段（带时区信息）
        localDate.until(LocalDate.now(), ChronoUnit.DAYS);
        //比较两个日期
        localDate.isAfter(localDate);
        localDate.isAfter(localDate);
    }
    //日期矫正器
    private void temporalAdjustersTest(){

    }
}

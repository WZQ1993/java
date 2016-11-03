package com.wangziqing.java8Demo;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
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
public class NewTimeTest {
    /**
     * 瞬间和时间段
     */
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
     * 本地日期
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
    /**
     * 日期矫正器
     * 用于将当前时间点调到需要的时间点
     * TemporalAdjusters产生矫正器，日期.with(使用矫正器)
     */
    private void temporalAdjustersTest(){
        //下一个or上一个工作日
        LocalDate.of(2016,11,1).with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
        //包括当前时间点
        LocalDate.of(2016,11,1).with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY));
        //.previus(),previousOrSame();上一个
        //某月中的第N个工作日
        LocalDate.of(2016,11,1).with(TemporalAdjusters.dayOfWeekInMonth(2,DayOfWeek.MONDAY));
        //某月中的最后一个工作日
        LocalDate.of(2016,11,1).with(TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY));
        //其他方法
//        TemporalAdjusters.firstDayOf***();
//        TemporalAdjusters.lastDayOf***();
    }

    /**
     * 本地时间
     */
    private void localTime(){
        //creat
        LocalTime time=LocalTime.now();
        time=LocalTime.of(22,22,22);//22：22：22
        //加减
        //plus***();plus(Duration);
        //minus***();minus(Duration);
        //将小时，分钟，秒设置为给定值
        //with***();
        //获得小时，分钟，秒
        //get***();
        //获得0点与当前时间相隔的秒，微秒
        time.toSecondOfDay();
        time.toNanoOfDay();
        //比较
        time.isAfter(time);
        time.isBefore(time);
    }
    //操作带时区的时间
    ZonedDateTime zonedDateTime=ZonedDateTime.now();
    //api与localDateTime相似

    /**
     * 格式化与解析
     */
    private void dateTimeFormatter(){
        //预定义的解析格式
        DateTimeFormatter.ISO_DATE_TIME.format(LocalDate.now());
        //java8提供四种风格的形式，SHORT,MEDIUM,LONG,FULL
        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
        //自定义格式 E:Wed
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("E yyyy-MM-dd HH;mm;ss");
        //使用(将当前时间格式化为字符串)
        dateTimeFormatter.format(LocalDateTime.now());
        //将格式化字符串解析为时间
        LocalDateTime localDateTime=LocalDateTime.parse(
                "1993-0126 22:22:22",
                dateTimeFormatter
                );
    }
    public static void main(String[]args){
       System.out.println(
               LocalDate.of(2016,11,1).with(TemporalAdjusters.next(DayOfWeek.TUESDAY))
       ) ;
        System.out.println(
                LocalDate.of(2016,11,1).with(TemporalAdjusters.dayOfWeekInMonth(2,DayOfWeek.MONDAY))
       ) ;
    }
}

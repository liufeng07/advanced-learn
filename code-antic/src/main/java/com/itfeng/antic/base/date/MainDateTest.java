package com.itfeng.antic.base.date;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author liuf
 * @date 2021年06月21日 8:50 上午
 */
public class MainDateTest {

    public static void main(String[] args) {

        //如何检查周期性时间例如 生日
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.of(1995, 6, 21);
        MonthDay birthday = MonthDay.of(date2.getMonth(), date2.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(date1);
        if (currentMonthDay.equals(birthday)) {
            System.out.println("是你的生日");
        } else {
            System.out.println("你的生日还没有到");
        }


        //如何表示信用卡到期这类固定日期
        //currentYearMonth.lengthOfMonth() 返回当月的天数
        YearMonth currentYearMonth = YearMonth.now();
        System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
        YearMonth creditCardExpiry = YearMonth.of(2019, Month.FEBRUARY);
        System.out.printf("Your credit card expires on %s %n", creditCardExpiry);

        //Java 8中如何使用预定义的格式化工具去解析或格式化日期
        String dayAfterTommorrow = "20180205";
        LocalDate formatted = LocalDate.parse(dayAfterTommorrow,
                DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(dayAfterTommorrow + "  格式化后的日期为:  " + formatted);

        //字符串互转日期类型
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //日期转字符串
        String str = date.format(format1);
        System.out.println("日期转换为字符串:" + str);
        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //字符串转日期
        LocalDate date3 = LocalDate.parse(str, format2);
        System.out.println("日期类型:" + date3);
    }
}

package javabase.类型.Date_;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 11861 on 2018/3/9.
 */
public class Calendar_ {
    public static void main(String[] args) throws ParseException {
        Date date1 = (convertToDate("2019-05-24 21:59:07", "dateTime"));
        Date date2 = (convertToDate("2019-05-24 22:00:00", "dateTime"));
        // 1. 两个时间相差的秒
        Double second = ((date2 != null ? date2.getTime() : 0) - (date1 != null ? date1.getTime() : 0)) / 1000.0;
        System.out.println(second);

        // 2. 一段时间内的日期列表
        Date date3 = new Date();
        System.out.print("date2: " + convertToString(date2, "dateTime"));
        System.out.print(", date3: " + convertToString(date3, "dateTime"));
        List<Date> dateList = getDateList(date2, date3);
        System.out.println("\n=====================期间的日期：========================");
        for (Date d : dateList) {
            System.out.println(convertToString(d, "dateTime"));
        }
        System.out.println("=====================查询完。========================");
    }

    /**
     * 获取随机字符串
     */
    public static String getRandomFileName() {
        String str = "ABCDEFGHIJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz23456789";
        StringBuffer fileName = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            fileName.append(str.charAt((int) Math.round(Math.random() * str.length())));
        }
        String date_string = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        return (fileName.toString() + date_string);
    }

    /**
     * date 转 String
     */
    private static String convertToString(Date date, String dateType) {
        String format = "";
        switch (dateType) {
            case "dateTime":
                format = "yyyy-MM-dd HH:mm:ss";
                break;
            case "dateT":
                format = "yyyy年MM月dd日 HH:mm:ss";
                break;
            case "date":
                format = "yyyy-MM-dd";
                break;
            default:
                return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * String 转 Date
     *
     * @param str 日期字符串
     * @throws ParseException 转换异常
     */
    private static Date convertToDate(String str, String dateType) throws ParseException {
        String format = "";
        switch (dateType) {
            case "date":
                format = "yyyy-MM-dd";
                break;
            case "time":
                format = "HH:mm:ss";
                break;
            case "hm":
                format = "HH:mm";
                break;
            case "dateTime":
                format = "yyyy-MM-dd HH:mm:ss";
                break;
            default:
                return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date dateTime = sdf.parse(str);
        return dateTime;
    }

    /**
     * 使用 Calendar 日期
     */
    private static Date calendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.MONTH, 14);
        calendar.set(Calendar.DATE, 40);
        calendar.set(Calendar.HOUR, 20);
        calendar.set(Calendar.MINUTE, 20);
        calendar.set(Calendar.SECOND, 78);
        calendar.set(Calendar.MILLISECOND, 2000);
        Date time = calendar.getTime();
        System.out.println(time);
        return time;
    }

    /**
     * 获取一段时间内的所有日期.
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return 日期列表
     */
    private static List<Date> getDateList(Date start, Date end) {
        List<Date> dateList = new ArrayList<>();
        dateList.add(start);
        Calendar calendar = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calendar.setTime(start);
        // 测试此日期是否在指定日期之后
        while (end.after(calendar.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            dateList.add(calendar.getTime());
        }
        return dateList;
    }
}

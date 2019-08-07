package com.mxm.java.learn_demo.tools.utils;

import com.mxm.java.learn_demo.tools.enums.ErrorCode;
import com.mxm.java.learn_demo.tools.exception.SystemException;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Slf4j
public class DateUtils {

    public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String Y_M_D = "yyyy-MM-dd";
    public static final String YMD = "yyyyMMdd";
    public static final String Y_M_D_H_M = "yyyy/MM/dd HH:mm";
    public static final String HHMMSS = "HH:mm:ss";
    /**
     * 获取当前时间  yyyy-MM-dd HH:mm:ss"
     * @author maxianming
     * @param
     * @return
     * @date 2018/8/10 14:32
     */
    public static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(YMDHMS);
        return sdf.format(new Date());
    }


    /**
     * 获取当前天 格式 yyyy-MM-dd
     * @author maxianming
     * @param
     * @return
     * @date 2018/8/10 14:35
     */
    public static String getCurrentDay() {
        SimpleDateFormat sdf = new SimpleDateFormat(Y_M_D);
        return sdf.format(new Date());
    }

    /**
     * 日期字符串转化为时间戳
     * @author maxianming
     * @param
     * @return
     * @date 2018/8/10 14:43
     */
    public static long toTimestamp(String dataStr, String format) {
        return  parse(dataStr, format).getTime();
    }

    /**
     * 时间戳转化为字符串
     * @author maxianming
     * @param
     * @return
     * @date 2018/8/10 14:41
     */
    public static String toDateStr(long millisecond, String formatDate) {
        Date date = new Date(millisecond);
        SimpleDateFormat format = new SimpleDateFormat(formatDate);
        return format.format(date);
    }

    /**
     * 格式
     * 传入指定格式 获取当前时间
     * @param format 日期格式
     * @return 日期
     */
    public static Date parse(String dataStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dataStr);
        } catch (Exception e) {
            log.error("parse(String dataFormat)错误：" + e);
            throw new SystemException(ErrorCode.DATE_PARSE_ERROR);
        }
    }

    /**
     * 格式
     *
     * @param date       日期
     * @param dataFormat 日期格式
     * @return 日期
     */
    public static String format(Date date, String dataFormat) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dataFormat);
            return sdf.format(date);
        } catch (Exception e) {
            log.error("format(Date date, String dataFormat)错误：" + e);
            throw new SystemException(ErrorCode.DATE_PARSE_ERROR);
        }
    }

    /**
     * 加分钟的日期
     * @param date   日期
     * @param minute 分钟
     * @return 加后的日期
     */
    public static Date getAddDate(Date date, int minute) {
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        time.add(Calendar.MINUTE, minute);
        return time.getTime();
    }



    public static String toMinute(long millisecond) {
        Date date = new Date(millisecond);
        SimpleDateFormat format = new SimpleDateFormat(Y_M_D_H_M);
        return format.format(date);
    }

    public static String toMinute(Date date) {
        SimpleDateFormat simpleDateFormat;
        String dateStr = null;
        try {
            simpleDateFormat = new SimpleDateFormat(Y_M_D_H_M);
            dateStr = simpleDateFormat.format(date);
        } catch (Exception e) {
            log.error("toMinute(Date date)错误：" + e);
            throw new SystemException(ErrorCode.DATE_PARSE_ERROR);
        }
        return dateStr;
    }

    private static Date addDay(Date date, int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }



}

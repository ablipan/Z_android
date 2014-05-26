/**
 * author :  lipan
 * filename :  DateUtils.java
 * create_time : 2014年4月23日 下午3:12:44
 */
package com.pp.utils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.util.SparseIntArray;

/**
 * @author : lipan
 * @create_time : 2014年4月23日 下午3:12:44
 * @desc : 日期工具类
 * @update_time :
 * @update_desc :
 *
 */
public class DateUtils
{

    public static final String DATE_FORMAT_1 = "HH:mm yyyy-MM-dd";
    
    public static final int MAX_DAYS_BIG_MONTH = 31; //大月天数
    public static final int MAX_DAYS_SMALL_MONTH = 30; //小月天数
    public static final int MAX_DAYS_LEAP_YEAR = 29; //闰年2月最大天数
    public static final int MAX_DAYS_NON_LEAP_YEAR = 28; //平年2月最大天数
    
    /**
     * 获得时间表达式
     * @param timestamp 时间戳
     * @param pattern  时间表达式，null 时默认为 C.DEF_MONTH_FMT
     * @param offMonth 偏移月数
     * @return
     */
    public static String getMonthStr(long timestamp, String pattern , int offMonth) {
        //默认月份格式
        if(null == pattern || "".equals(pattern))
        {
            pattern = C.DEF_MONTH_FMT;
        }
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTimeInMillis(timestamp);
        cal.add(Calendar.MONTH, offMonth);
        SimpleDateFormat formatter = new SimpleDateFormat(pattern,Locale.CHINA);
        return formatter.format(cal.getTime());
    }
    
    /**
     * 获得当前时间字符串
     * @param pattern
     * @return
     */
    public static String getDataStr(String pattern)
    {
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTimeInMillis(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat(pattern,Locale.CHINA);
        return formatter.format(cal.getTime());
    }
    
    /**
     * 判断指定日期是否为闰年
     *
     * @param date
     *            指定的日期，null时返回 false
     * @return 是否为闰年
     */
    public static boolean isLeap(Date date) {
        boolean isLeap = false;
        if (null != date) {
            Calendar cal = Calendar.getInstance(Locale.CHINA);
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))
                isLeap = true;
        }
        return isLeap;
    }
    
    /**
     * 获得各个时间的值
     * @return
     */
    public static int getCalVal(Calendar calendar ,int type)
    {
        if(type == Calendar.MONTH) //月份+1
        {
            return calendar.get(type) +1 ;
        }
        return calendar.get(type);
    }
    
    /**
     * 获得指定日期所在年的每个月的最大天数
     * @return
     */
    public static SparseIntArray getMonthMaxDays(Calendar calendar)
    {
        SparseIntArray maxDays = new SparseIntArray();
        
        Integer[] big = {1, 3, 5, 7, 8, 10, 12};
        Integer[] small = {4, 6, 9, 11};
        List<Integer> bigList = Arrays.asList(big); // 大月
        List<Integer> smallList = Arrays.asList(small); //小月
        
        if(calendar == null)
        {
            calendar = Calendar.getInstance();
        }
        
        for(int i=1 ; i<=12 ; i++)
        {
            if(bigList.contains(i))
            {
                maxDays.put(i, MAX_DAYS_BIG_MONTH);
            }else if(smallList.contains(i))
            {
                maxDays.put(i, MAX_DAYS_SMALL_MONTH);
            }else
            {
                if(isLeap(calendar.getTime()))
                {
                    maxDays.put(i, MAX_DAYS_LEAP_YEAR);
                }else
                {
                    maxDays.put(i, MAX_DAYS_NON_LEAP_YEAR);
                }
            }
        }
        return maxDays;
    }
}

package com.ssm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    // 日期转换字符串
    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format = sdf.format(date);
        return format;
    }
}

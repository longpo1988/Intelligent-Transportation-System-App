package com.example.administrator.its_gs_mvp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xww on 2018/4/20 0020.
 */

public class TimeUtil {
    public static String getYearAndTime() {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return f.format(new Date(System.currentTimeMillis()));
    }

    public static String getYear() {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return f.format(new Date(System.currentTimeMillis()));
    }

    public static String getTime() {
        SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
        return f.format(new Date(System.currentTimeMillis()));
    }

    public static String getWeek(){
        SimpleDateFormat f = new SimpleDateFormat("EEEE");
        return f.format(new Date(System.currentTimeMillis()));
    }
}

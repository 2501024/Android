package com.example.week12;

import java.util.Locale;

public class TimeUtil {
    protected static String convert(int second) {
        second /= 1000;
        int minute = second / 60;
        second %= 60;
        return String.format(Locale.KOREA, "%02d:%02d", minute, second);
    }
}
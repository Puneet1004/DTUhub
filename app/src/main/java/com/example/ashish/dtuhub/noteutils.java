package com.example.ashish.dtuhub;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class noteutils {

    public static String dateFromlong(long time) {
        DateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy 'at' hh:mm aaa", Locale.US);
        return format.format(new Date(time));
    }
}

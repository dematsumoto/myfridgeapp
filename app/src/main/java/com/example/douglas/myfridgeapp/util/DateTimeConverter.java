package com.example.douglas.myfridgeapp.util;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeConverter {
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static String addDaysToDate(String startDate, int days){
        DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_FORMAT);
        DateTime dateTime = fmt.parseDateTime(startDate);

        dateTime = dateTime.plusDays(days);

        return dateTime.toString(fmt);
    }
}

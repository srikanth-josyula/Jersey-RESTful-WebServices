package com.sample.restservices.vo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


public class DateInfo {
    private Date date;

    public static DateInfo valueOf(String dateStr) {
        if(dateStr==null){
            return null;
        }
        DateInfo dateInfo = new DateInfo();
        dateInfo.date = new Date(dateStr);
        return dateInfo;
    }

    public LocalDateTime asLocalDateTime() {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public Date asDate() {
        return date;
    }
}
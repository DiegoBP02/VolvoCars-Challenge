package com.example.demo.services.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GetDates {
    public static LocalDateTime[] getDatesArray() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime[] datesArray = new LocalDateTime[16];

        try {
            datesArray[0] = LocalDateTime.parse("2013-01-14T21:00:00", formatter);
            datesArray[1] = LocalDateTime.parse("2013-01-15T21:00:00", formatter);
            datesArray[2] = LocalDateTime.parse("2013-02-07T06:23:27", formatter);
            datesArray[3] = LocalDateTime.parse("2013-02-07T15:27:00", formatter);
            datesArray[4] = LocalDateTime.parse("2013-02-08T06:27:00", formatter);
            datesArray[5] = LocalDateTime.parse("2013-02-08T06:20:27", formatter);
            datesArray[6] = LocalDateTime.parse("2013-02-08T14:35:00", formatter);
            datesArray[7] = LocalDateTime.parse("2013-02-08T15:29:00", formatter);
            datesArray[8] = LocalDateTime.parse("2013-02-08T15:47:00", formatter);
            datesArray[9] = LocalDateTime.parse("2013-02-08T16:01:00", formatter);
            datesArray[10] = LocalDateTime.parse("2013-02-08T16:48:00", formatter);
            datesArray[11] = LocalDateTime.parse("2013-02-08T17:49:00", formatter);
            datesArray[12] = LocalDateTime.parse("2013-02-08T18:29:00", formatter);
            datesArray[13] = LocalDateTime.parse("2013-02-08T18:35:00", formatter);
            datesArray[14] = LocalDateTime.parse("2013-03-26T14:25:00", formatter);
            datesArray[15] = LocalDateTime.parse("2013-03-28T14:07:27", formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datesArray;
    }
}

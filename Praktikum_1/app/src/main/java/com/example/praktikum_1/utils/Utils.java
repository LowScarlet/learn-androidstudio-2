package com.example.praktikum_1.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Utils {
    public static long localDateTimeToUnix(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant();
        return instant.getEpochSecond();
    }

    public static LocalDateTime unixToLocalDateTime(long epochSecond) {
        Instant instant = Instant.ofEpochSecond(epochSecond);

        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}

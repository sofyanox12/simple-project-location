package com.codetalkz.projectlocator.utils;

import java.time.LocalDateTime;

public class Formatter {

    private Formatter() {}

    public static LocalDateTime toLocalDateTime(String date) {
        String[] dateParts = date.split("-");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);
        return LocalDateTime.of(year, month, day, 0, 0);
    }
}

package com.ereadly.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    private DateUtil() {}

    public static Date now() {
        return new Date();
    }

    public static Date addDays(Date date, int days) {
        if (date == null) return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    public static boolean isOverdue(Date dueDate) {
        if (dueDate == null) return false;
        return dueDate.before(new Date());
    }

    public static int calculateDaysLate(Date dueDate) {
        if (dueDate == null) return 0;

        LocalDate due;

        if (dueDate instanceof java.sql.Date) {
            due = ((java.sql.Date) dueDate).toLocalDate();
        } else {
            due = dueDate.toInstant()
                         .atZone(ZoneId.systemDefault())
                         .toLocalDate();
        }

        LocalDate today = LocalDate.now();

        if (today.isAfter(due)) {
            return (int) ChronoUnit.DAYS.between(due, today);
        }
        
        return 0;
    }
}
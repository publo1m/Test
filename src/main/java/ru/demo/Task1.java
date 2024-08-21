package ru.demo;

import java.sql.Date;
import java.util.Calendar;

public class Task1 {

    public static Date getNextDispatchDate(Date currentDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);

        Date[] submissionDates = {
                getDate(year, month, 1),
                getDate(year, month, 10),
                getDate(year, month, 20)
        };

        if (cal.get(Calendar.DAY_OF_MONTH) > 20) {
            submissionDates = new Date[] {
                    getDate(year, month + 1, 1),
                    getDate(year, month + 1, 10),
                    getDate(year, month + 1, 20)
            };
        }

        Date nextDate = findClosestDate(currentDate, submissionDates);

        return getVacCheck(nextDate);
    }

    private static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, 18, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return new Date(cal.getTimeInMillis());
    }

    private static Date findClosestDate(Date currentDate, Date[] dates) {
        Date closestDate = null;
        long minDiff = Long.MAX_VALUE;

        for (Date date : dates) {
            long diff = date.getTime() - currentDate.getTime();
            if (diff >= 0 && diff < minDiff) {
                minDiff = diff;
                closestDate = date;
            }
        }

        return closestDate;
    }


    private static Date getVacCheck(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            calendar.add(Calendar.DAY_OF_MONTH, + 2);
        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            calendar.add(Calendar.DAY_OF_MONTH, + 1);
        }

        return new Date(calendar.getTimeInMillis());
    }
}

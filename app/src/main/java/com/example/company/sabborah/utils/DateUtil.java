package com.example.company.sabborah.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public abstract class DateUtil {

    public static final int DAY_ONE = 1;

    /**
     * Date in past, represent the passed date backwarded N months, where N = lastMonths .
     *
     * @param lastMonths
     * @param date
     * @return DateRange of the last specified months.
     */
    public static Date getLastMonthsDateRange(Date date, int lastMonths, Locale locale) {

        Calendar nowCalendar = Calendar.getInstance(TimeZone.getDefault(), locale);

        nowCalendar.setTime(date);
        nowCalendar.add(Calendar.MONTH, -lastMonths);

        Date fromDate = adjustTimeToStartOfDay(nowCalendar.getTime(), locale);

        return fromDate;
    }

    /**
     * (REST)
     * Check if the tow dates (FromDate and ToDate) have differnece between each other more than specified period.
     *
     * @param fromDate
     * @param toDate
     * @param period
     * @return
     */
    public static boolean isDifferenceBiggerThanPeriod(Date fromDate, Date toDate, int period, Locale locale) {

        Calendar fromCal = Calendar.getInstance();
        fromCal.setTime(fromDate);
        Date fd = fromCal.getTime();
        fd = DateUtil.adjustTimeToStartOfDay(fd, locale);

        Calendar toCal = Calendar.getInstance();
        toCal.setTime(toDate);
        toCal.add(Calendar.MONTH, -(period));
        Date td = toCal.getTime();
        td = DateUtil.adjustTimeToStartOfDay(td, locale);

        if (fd.before(td)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Sets the time portion of the passed date to be equal to the start of the
     * day (00:00:00).
     *
     * @param date   Date object which time portion will be cleared
     * @param locale Locale object represents the locale to be used with the dates
     * @return Date object represents the same date as the passed one with the
     * time portion cleared (00:00:00)
     * @throws IllegalArgumentException if either <code>date</code> or <code>locale</code> is null
     */
    public static Date adjustTimeToStartOfDay(Date date, Locale locale) {
        if (date == null) {
            throw new IllegalArgumentException("date is null");
        }
        if (locale == null) {
            throw new IllegalArgumentException("locale is null");
        }

        Calendar nowCalendar = Calendar.getInstance(locale);
        nowCalendar.setTime(date);
        nowCalendar.set(nowCalendar.get(Calendar.YEAR), nowCalendar.get(Calendar.MONTH), nowCalendar.get(Calendar.DATE),
                0, 0, 0);
        nowCalendar.set(Calendar.HOUR_OF_DAY, 0);
        nowCalendar.set(Calendar.MILLISECOND, 0);
        return nowCalendar.getTime();
    }

    /**
     * This method will return the java.util.Date with todays date
     *
     * @return Today
     */
    public static Date getToday() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * This method will return the java.util.Date with todays date
     *
     * @return Today
     */
    public static Date getTomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getToday());
        calendar.add(Calendar.DATE, 1);

        return calendar.getTime();
    }

    /**
     * @param dateOfBirth
     * @return the years from the specified date until now. for instance if someone born in
     * 08-06-1985, and now we are in 2012, the returned value would be 27.
     */
    public static int getDateOfBirthYears(Date dateOfBirth) {

        Calendar cal = Calendar.getInstance();

        cal.setTime(dateOfBirth);
        int birthYear = cal.get(Calendar.YEAR);

        cal.setTime(getToday());
        int yearNow = cal.get(Calendar.YEAR);

        return yearNow - birthYear;
    }

    /**
     * @param date
     * @param dateFormat
     * @return
     */
    public static String getDateAsString(Date date, String dateFormat) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);
        return dateFormatter.format(date);
    }

    /**
     * @param date
     * @return
     */
    public static String getSimpleDate(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormatter.format(date);
    }

    public static String getCurrentDateAsString() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormatter.format(new Date());
    }

    public static Date getCurrentDate() {
        return getDateFromSimpleDateString(getCurrentDateAsString());
    }

    /**
     * @param date
     * @return date with format dd/MM/yyyy
     */
    public static String getSimpleDateWithoutHiphens(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy");
        return dateFormatter.format(date);
    }

    /**
     * @param tmpDate
     * @return date with format dd/MM/yyyy
     */
    public static String getSimpleDate(long tmpDate) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        Date ddd = new Date();
        ddd.setTime(tmpDate);

        /* DateFormat dateFormat = DateFormat.getInstance();

        Date date = null;
        try {
            date = dateFormat.parse(ddd);
        } catch (ParseException e) {
            return stringDate;
        }*/

        return dateFormatter.format(ddd);
    }

    /**
     * @param stringDate
     * @return date with format dd/MM/yyyy
     */
    public static Date getDateFromSimpleDateString(String stringDate) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        Date date = null;
        try {
            date = formatter.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static Date getDateFromSimpleDateString(String stringDate, String pattern) {

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);

        Date date = null;
        try {
            date = formatter.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    /**
     * @param stringDate
     * @return
     */
    public static Date convertStringToSimpleDate(String stringDate) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");


        Date date = null;
        try {
            date = dateFormatter.parse(stringDate);
        } catch (ParseException e) {
            return null;
        }

        return date;
    }

    /**
     * @param date
     * @return
     */
    public static String getSimpleTime(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mm:ss a");
        return dateFormatter.format(date);
    }

    public static String getSimpleHourMinute(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mm");
        return dateFormatter.format(date);
    }

    /**
     * @param date
     * @return
     */
    public static String getFormattedLongDate(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
        return dateFormatter.format(date);
    }

    /**
     * @param date
     * @return
     */
    public static String getSimpleDateMonthAndYear(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/yyyy");
        return dateFormatter.format(date);
    }

    /**
     * @param limitDate
     * @return true if passed date is same of today
     */
    public static boolean isToday(Date limitDate) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(getToday());
        cal2.setTime(limitDate);
        boolean sameDay =
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                        cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);

        return sameDay;
    }

    public static String getYear() {
        SimpleDateFormat yearFormatter = new SimpleDateFormat("yyyy");
        Integer year = Integer.parseInt(yearFormatter.format(getToday()));

        return year.toString();
    }


}

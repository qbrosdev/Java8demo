package com.qbros.DateTime;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by V.Ghasemi
 * on 4/15/2019.
 */
public class Test {

    public static void main(String[] args) {

        //---------------------------------------------OLD API---------------------------------------------------------
        Date aDate = new Date(2015, 12, 25, 20, 40);
        //the year count starts with offset from 1900 so (2015 will be 1900+2015)
        //the month start form zero
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD");
        simpleDateFormat.format(aDate);
        // the above date formatter will print wrong results (3916-01-25) because of bad input.
        //however these depricated methods give the right results (with some modifications on the output) This is  a shitty solution
        System.out.println(aDate.getYear());
        System.out.println(aDate.getMonth());

        //-DateFormat(SimpleDateFormat) is not thread safe, if two threads use the same DateFormatter, it might parse the date incorrectly
        // one solution might be to define DateFormatter ThreadLocal (ThreadLocal<SimpleDateFormat> safeDateFormatter)


        //---------Date Copy https://stackoverflow.com/a/7082614/3593084
        Date newDate = (Date) aDate.clone();
        Date newDate2 = new Date(aDate.getTime());

        //---------SQL date vs UTIL date
        //In sql we view date and time as two separate things, so sql date will suppress all the time related methods
        java.sql.Date sqlDate = new java.sql.Date(aDate.getTime());
        //sqlDate.getHours();// will throw exception
        java.sql.Time sqlTime = new java.sql.Time(aDate.getTime());
        //sqlTime.getYear();// will throw exception

        //-------Conversion From (util.Date, sql.Date, sql.Time, util.Calendar) can be done via passing getTime() method
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(aDate);


        //-------------------------------------OLD vs New API---------------------------------------------------
        /**
         * Poor API design (for example, months start with 1 and days start with 0)
         * Not thread safe
         * No consistency within the java.util and java.sql classes
         * No support for internationalization and time zones
         * * * * * * * The new Date and Time API solutions:
         * The improved API design clearly separates the human-readable date time and machine time.
         * The new Date and Time API makes all the classes immutable, which is suitable for the multithreaded environment.
         * There is more clarity in the API design level. The methods are clearly defined and perform the same action in all classes.
         * The API defines separate classes for Date, Time, DateTime, Timestamp, TimeZone, and so on.
         */


        //---------------------New API------------------------------------------------------------------------
        /**
         * Java 8 introduces a new date-time API under the package java.time.
         * Local − Simplified date-time API with no complexity of timezone handling.
         * Zoned − Specialized date-time API to deal with various timezones.
         */

        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime customDateTime = LocalDateTime.of(2018, Month.DECEMBER, 25, 12, 30);
        LocalDateTime alteredDateTime = currentDateTime.withDayOfMonth(10).withYear(2012);

        //We can extract LocalDate or LocalTime form the DateTime obj
        LocalDate date1 = currentDateTime.toLocalDate();
        LocalTime time1 = currentDateTime.toLocalTime();

        //LocalDate has date related methods
        //date1.getDayOfMonth(),....

        //LocalTime has time related methods
        //time1.getHour(), ....


        //for some Date and time aspects we have well defined objs
        Month month = currentDateTime.getMonth();
        int day = currentDateTime.getDayOfMonth();
        int seconds = currentDateTime.getSecond();

        //-----------Modifying Date & Time
        /**
         * Using numbers for modifying date and time is error prone, Java 8 defines enums for this (java.time.Temporal)
         */

        //add 1 week to the current date
        LocalDate nextWeek = date1.plus(1, ChronoUnit.WEEKS);
        //notice that each midification creates a new obj (becaues LocalDate, ... are immutable)
        LocalDate nextMonth = date1.plus(1, ChronoUnit.MONTHS);

        //notice for modifying Date, Time objs, we should use proper  ChronoUnit,
        //LocalTime newTime = time1.plus(1,ChronoUnit.MONTHS);// will throw UnsupportedTemporalTypeException
        LocalTime newTime = time1.plus(1,ChronoUnit.HOURS);

        //----------Difference Between two
        /**
         * Two specialized classes are introduced to deal with the time differences.
         * Period − It deals with date based amount of time.
         * Duration − It deals with time based amount of time.
         */

        Period period = Period.between(nextWeek, date1);
        System.out.println("Period: " + period);

        Duration duration = Duration.between(time1, newTime);
        System.out.println("Duration: " + duration);


        //---------Zoned time API

        ZoneId.getAvailableZoneIds();
    }
}

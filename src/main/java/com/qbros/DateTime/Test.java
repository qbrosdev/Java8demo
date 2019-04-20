package com.qbros.DateTime;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by V.Ghasemi
 * on 4/15/2019.
 */
public class Test {

    public static void main(String[] args) {

        //----------OLD API
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
        sqlDate.getHours();// will throw exception
        java.sql.Time sqlTime = new java.sql.Time(aDate.getTime());
        sqlTime.getYear();// will throw exception

        //-------Conversion From (util.Date, sql.Date, sql.Time, util.Calendar) can be done via passing getTime() method
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(aDate);


        //---------------------OLD vs New API---------------------------
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


        //---------------------New API
        /**
         * Java 8 introduces a new date-time API under the package java.time.
         * Local − Simplified date-time API with no complexity of timezone handling.
         * Zoned − Specialized date-time API to deal with various timezones.
         */




        ZoneId.getAvailableZoneIds();
    }
}

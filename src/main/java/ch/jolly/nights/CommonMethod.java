package ch.jolly.nights;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonMethod {
    public static java.sql.Timestamp convertTimestamp(String pageDate, String pageTime) {
        try {
            DateFormat formatter;
            formatter = new SimpleDateFormat("dd.MM.yyyy hh:mm");
            // you can change format of date
            Date date = formatter.parse(pageDate + " " + pageTime);

            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            System.out.println("Exception :" + e);
        }
        return new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    }

    public static java.sql.Date utilToDate(Date utilDate){
        return new java.sql.Date(utilDate.getTime());
    }

    public static java.sql.Date stringToDate(String pageDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            java.util.Date parsed = format.parse(pageDate);
            return new java.sql.Date(parsed.getTime());
        } catch (Exception ex) {
            System.out.println("stringToDate parameters are not good");
        }
        return new java.sql.Date(Calendar.getInstance().getTime().getTime());
    }

    public static String dateToString(java.sql.Date date) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

        return df.format(date);
    }

    public static String timestampToStringDate(Timestamp timestamp) {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return df.format(timestamp);
    }

    public static String timestampToStringTime(Timestamp timestamp) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        return df.format(timestamp);
    }
}

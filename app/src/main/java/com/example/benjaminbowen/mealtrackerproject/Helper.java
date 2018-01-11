package com.example.benjaminbowen.mealtrackerproject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by benjaminbowen on 09/01/2018.
 */

public class Helper {

    public static String addLeadingZero(String number){
        if(number.length()<2){number = "0"+number;}
        return number;
    }


//    public static String convertDateFormat(String yyyyMMDD){
//        DateTimeFormatter yMDFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
//        DateTimeFormatter dMYFormat = DateTimeFormatter.ofPattern("dd/MM/YYYY", Locale.ENGLISH);
//        LocalDate date = LocalDate.parse(yyyyMMDD, yMDFormat);
//        return  date.format(dMYFormat);
//    }

    public static String convertDateFormatOlder(String yyyyMMDD){
        SimpleDateFormat yMDFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {Date date = yMDFormat.parse(yyyyMMDD);
        SimpleDateFormat dMYFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dMYFormat.format(date);        }
        catch (ParseException e){e.printStackTrace();}
        return null;
    }

    public static String convertDateToString(Date date){
        SimpleDateFormat dMYFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dMYFormat.format(date);
    }

    public static String convertDatetoDBString(Date date){
        SimpleDateFormat yMDFormat = new SimpleDateFormat("yyyy-MM-dd");
        return yMDFormat.format(date);
    }

    public static Date getCurrentDate() {
        Calendar today = Calendar.getInstance();
        Date todayDate = today.getTime();
        return todayDate;
    }

}

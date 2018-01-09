package com.example.benjaminbowen.mealtrackerproject;

/**
 * Created by benjaminbowen on 09/01/2018.
 */

public class Helper {

    public static String addLeadingZero(String number){
        if(number.length()<2){number = "0"+number;}
        return number;
    }

}

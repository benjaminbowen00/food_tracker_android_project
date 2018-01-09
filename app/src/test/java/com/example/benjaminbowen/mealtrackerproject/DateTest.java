package com.example.benjaminbowen.mealtrackerproject;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by benjaminbowen on 09/01/2018.
 */

public class DateTest {

   @Test
    public void shortNumberTest(){
       assertEquals("02", Helper.addLeadingZero("2"));
   }

    @Test
    public void longNumberTest(){
        assertEquals("12", Helper.addLeadingZero("12"));
    }
}

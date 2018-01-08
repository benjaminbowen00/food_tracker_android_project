package com.example.benjaminbowen.mealtrackerproject;

/**
 * Created by benjaminbowen on 08/01/2018.
 */

public enum Meals {
    BREAKFAST("Breakfast"),
    LUNCH("Lunch"),
    DINNER("Dinner"),
    SNACK("Snack"),
    OTHER("Other");

    private String mealName;

    Meals(String meal){this.mealName = meal;}

    @Override
    public String toString(){return this.mealName;}
}

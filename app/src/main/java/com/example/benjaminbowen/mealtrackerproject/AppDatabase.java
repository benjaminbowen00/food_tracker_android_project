package com.example.benjaminbowen.mealtrackerproject;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by benjaminbowen on 08/01/2018.
 */

@Database(entities = {Food.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FoodDao foodDao();
}

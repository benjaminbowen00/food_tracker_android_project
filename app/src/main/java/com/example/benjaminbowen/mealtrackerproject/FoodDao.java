package com.example.benjaminbowen.mealtrackerproject;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by benjaminbowen on 08/01/2018.
 */

//Use data access objects to access data
@Dao
public interface FoodDao {

    @Insert
    void insertAll(Food... foods);

    @Query("SELECT * FROM foods ORDER BY date(date) DESC")
    List<Food> getAllFoods();



}

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

    @Query("SELECT * FROM foods WHERE date =:dateString")
    List<Food> findByDay(String dateString);

    @Query("SELECT * FROM foods WHERE meal =:mealString ORDER BY date DESC")
    List<Food> findByMeal(String mealString);

    @Query("SELECT * FROM foods WHERE id =:foodID")
    Food findByID(int foodID);



}

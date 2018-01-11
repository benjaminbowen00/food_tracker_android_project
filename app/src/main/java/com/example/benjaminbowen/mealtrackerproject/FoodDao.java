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

    @Query("DELETE FROM foods WHERE id =:foodID")
    void deleteByID(int foodID);

    @Query("SELECT * FROM foods WHERE food LIKE :word ORDER BY date DESC")
    List<Food> findBySearch(String word);

    @Query("DELETE FROM foods")
    void deleteAll();

    @Query("SELECT count(*) FROM foods WHERE meal = :meal AND (date BETWEEN :date1 AND :date2)")
    int getCountByMealAndDate(String meal, String date1, String date2);



}

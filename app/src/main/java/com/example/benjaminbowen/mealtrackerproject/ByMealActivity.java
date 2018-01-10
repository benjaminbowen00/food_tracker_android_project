package com.example.benjaminbowen.mealtrackerproject;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ByMealActivity extends AppCompatActivity {

    Button showFoodByMeal;
    Spinner mealSpinner;
    ListView listByMeal;
    AppDatabase db;
    TextView noFoodText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_meal);

        showFoodByMeal = findViewById(R.id.show_by_meal_button);
        mealSpinner = findViewById(R.id.meal_spinner_by_meal);
        listByMeal = findViewById(R.id.list_by_meal);
        noFoodText = findViewById(R.id.no_food_for_meal);
        final Context that = this;

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "foods")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        ArrayAdapter<Meals> mealAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Meals.values());
        mealAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mealSpinner.setAdapter(mealAdapter);


        showFoodByMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Food> foodList = db.foodDao().findByMeal(mealSpinner.getSelectedItem().toString());
                ArrayList<Food> foods = (ArrayList)foodList;

                if(foods.size() == 0){
                    listByMeal.setVisibility(View.INVISIBLE);
                    noFoodText.setVisibility((View.VISIBLE));
                    String noFoodTextString = "No food recorded";
                    noFoodText.setText(noFoodTextString);

                }

                else{
                    noFoodText.setVisibility(View.INVISIBLE);
                    listByMeal.setVisibility(View.VISIBLE);

                    FoodsMealAdapter foodsMealAdapter = new FoodsMealAdapter(that, foods);


                    listByMeal.setAdapter(foodsMealAdapter);}

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        menu.removeItem(R.id.item_by_meal);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId() == R.id.item_main_page){
            Intent intent = new Intent(this, ListAddActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.item_by_day){
            Intent intent = new Intent (this, ByDayActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.item_by_search){
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.item_add_food){
            Intent intent = new Intent(this, CreateFoodActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}

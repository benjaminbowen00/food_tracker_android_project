package com.example.benjaminbowen.mealtrackerproject;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class WeeklySummaryActivity extends AppCompatActivity {

    TextView weekSummaryText;
    TextView weekSummaryTitleText;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_summary);

        weekSummaryText = findViewById(R.id.week_summary_text);
        weekSummaryTitleText = findViewById(R.id.week_summary_title_text);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "foods")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();


        Date weekAgoDate = Helper.getDateWeekAgo();
        Date todayDate = Helper.getCurrentDate();
        String weekAgoDateString = Helper.convertDatetoDBString(weekAgoDate);
        String todayDateString = Helper.convertDatetoDBString(todayDate);

        String normalWeekAgoDateString = Helper.convertDateToString(weekAgoDate);
        String normalTodayDateString = Helper.convertDateToString(todayDate);

        String titleText = "Your summary of food eaten from "+normalWeekAgoDateString+" to "+normalTodayDateString;

        weekSummaryTitleText.setText(titleText);


        String summary = "";
        for(Meals meal : Meals.values()){
            summary += "You ate "+db.foodDao().getCountByMealAndDate(meal.toString(), weekAgoDateString,
                    todayDateString)+" "+meal.toString().toLowerCase()+"s \n";
        }


        weekSummaryText.setText(summary);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        menu.removeItem(R.id.item_week_summary);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId() == R.id.item_main_page){
            Intent intent = new Intent (this, ListAddActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.item_by_day){
            Intent intent = new Intent(this, ByDayActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.item_by_meal){
            Intent intent = new Intent(this, ByMealActivity.class);
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

        if(item.getItemId() == R.id.item_delete_all){
            Intent intent = new Intent(this, WeeklySummaryActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}

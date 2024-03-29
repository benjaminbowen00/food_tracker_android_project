package com.example.benjaminbowen.mealtrackerproject;

import android.app.DatePickerDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ByDayActivity extends AppCompatActivity {

    Button dayDateButton;
    Button showByDayButton;
    AppDatabase db;
    String dateForDB;
    DatePickerDialog datePickerDialog;
    TextView noFoodText;
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_day);

        listView = findViewById(R.id.list_by_day);
        dayDateButton = findViewById(R.id.by_day_date_button);
        showByDayButton = findViewById(R.id.by_day_button);
        noFoodText = findViewById(R.id.no_food_text);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "foods")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        Calendar newCalendar = Calendar.getInstance();

        Date todayDate = Helper.getCurrentDate();
        String button_date = Helper.convertDateToString(todayDate);
        dateForDB = Helper.convertDatetoDBString(todayDate);
        dayDateButton.setText(button_date);
        final Context that = this;

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String display_date = "Date: "+dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                dayDateButton.setText(display_date);
                dateForDB = Integer.toString(year)+"-"+Helper.addLeadingZero(Integer.toString(monthOfYear+1))+"-"+Helper.addLeadingZero(Integer.toString(dayOfMonth));

            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        showByDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Food> foodList = db.foodDao().findByDay(dateForDB);
                ArrayList<Food> foods = (ArrayList)foodList;

                if(foods.size() == 0){
                    listView.setVisibility(View.INVISIBLE);
                    noFoodText.setVisibility((View.VISIBLE));
                    String noFoodTextString = "No food recorded";
                    noFoodText.setText(noFoodTextString);

                }

                else{
                noFoodText.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                FoodsDayAdapter foodsDayAdapter = new FoodsDayAdapter(that, foods);


                listView.setAdapter(foodsDayAdapter);}

            }
        });
    }

    public void showDatePickerDialog(View v) {
        datePickerDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        menu.removeItem(R.id.item_by_day);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId() == R.id.item_main_page){
            Intent intent = new Intent (this, ListAddActivity.class);
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

        if(item.getItemId() == R.id.item_delete_all){
            Intent intent = new Intent(this, ClearAllActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.item_add_food){
            Intent intent = new Intent(this, CreateFoodActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.item_week_summary){
            Intent intent = new Intent(this, WeeklySummaryActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}

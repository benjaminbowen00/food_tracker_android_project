package com.example.benjaminbowen.mealtrackerproject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateFoodActivity extends AppCompatActivity {

    EditText foodText;
    EditText commentText;
    Button dateButton;
    Button saveButton;
    Spinner mealSpinner;
    DatePickerDialog datePickerDialog;
    AppDatabase db;
    String dateForDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_food);

        foodText = findViewById(R.id.food_text);
        commentText = findViewById(R.id.comment_text);
        dateButton = findViewById(R.id.date_button);
        saveButton = findViewById(R.id.save_button);
        mealSpinner = findViewById(R.id.meal_spinner);

        Calendar newCalendar = Calendar.getInstance();

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "foods")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();



        Date todayDate = Helper.getCurrentDate();
        String button_date = Helper.convertDateToString(todayDate);
        dateForDB = Helper.convertDatetoDBString(todayDate);
        dateButton.setText(button_date);


        ArrayAdapter<Meals> mealAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Meals.values());
        mealAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mealSpinner.setAdapter(mealAdapter);

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String display_date = "Date: "+dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                dateButton.setText(display_date);
                dateForDB = Integer.toString(year)+"-"+Helper.addLeadingZero(Integer.toString(monthOfYear+1))+"-"+Helper.addLeadingZero(Integer.toString(dayOfMonth));

            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                foodText = findViewById(R.id.food_text);

                if(foodText.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(CreateFoodActivity.this, R.string.no_food_toast, Toast.LENGTH_SHORT);
                    toast.show();
                    foodText.requestFocus();
                }
                
                else{
                Food food = new Food(dateForDB,
                        mealSpinner.getSelectedItem().toString(),
                        foodText.getText().toString(),
                        commentText.getText().toString());
                db.foodDao().insertAll(food);

                startActivity(new Intent(CreateFoodActivity.this, ListAddActivity.class));}
            }
        });


    }

    public void showDatePickerDialog(View v) {
        datePickerDialog.show();
    }
}

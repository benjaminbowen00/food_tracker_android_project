package com.example.benjaminbowen.mealtrackerproject;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class CreateFoodActivity extends AppCompatActivity {

    EditText foodText;
    EditText commentText;
    Button dateButton;
    Button saveButton;
    Spinner mealSpinner;
    DatePickerDialog datePickerDialog;

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

        Calendar currentDate = Calendar.getInstance();
        Integer current_year = currentDate.get(Calendar.YEAR);
        Integer current_month = currentDate.get(Calendar.MONTH)+1;
        Integer current_day = currentDate.get(Calendar.DATE);
        String button_date = Integer.toString(current_day)+'/'+Integer.toString(current_month)+'/'+Integer.toString(current_year);
        dateButton.setText(button_date);

        ArrayAdapter<Meals> mealAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Meals.values());

        mealAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mealSpinner.setAdapter(mealAdapter);

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String display_date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                dateButton.setText(display_date);

            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


    }

    public void showDatePickerDialog(View v) {
        datePickerDialog.show();
    }
}

package com.example.benjaminbowen.mealtrackerproject;

import android.app.DatePickerDialog;
import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ByDayActivity extends AppCompatActivity {

    Button dayDateButton;
    Button showByDayButton;
    AppDatabase db;
    String dateForDB;
    DatePickerDialog datePickerDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_day);

        dayDateButton = findViewById(R.id.by_day_date_button);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "foods")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        Calendar newCalendar = Calendar.getInstance();
        Calendar currentDate = Calendar.getInstance();
        Integer current_year = currentDate.get(Calendar.YEAR);
        Integer current_month = currentDate.get(Calendar.MONTH)+1;
        Integer current_day = currentDate.get(Calendar.DATE);
        String button_date = "Date: "+ Integer.toString(current_day)+'/'+Integer.toString(current_month)+'/'+Integer.toString(current_year);
        String month = Helper.addLeadingZero(Integer.toString(current_month));
        String day = Helper.addLeadingZero(Integer.toString(current_day));
        dateForDB = Integer.toString(current_year)+"-"+month+"-"+day;
        dayDateButton.setText(button_date);

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String display_date = "Date: "+dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                dayDateButton.setText(display_date);
                dateForDB = Integer.toString(year)+"-"+Helper.addLeadingZero(Integer.toString(monthOfYear+1))+"-"+Helper.addLeadingZero(Integer.toString(dayOfMonth));

            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

//        showByDayButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                List<Food> foodList = db.foodDao().findByDay(dateForDB);
//                ArrayList<Food> foods = (ArrayList)foodList;
//
//                FoodsDayAdapter foodsDayAdapter = new FoodsDayAdapter(ByDayActivity.this, foods);
//
//                ListView listView = findViewById(R.id.list_by_day);
//                listView.setAdapter(foodsDayAdapter);
//
//            }
//        });
    }

    public void showDatePickerDialog(View v) {
        datePickerDialog.show();
    }
}

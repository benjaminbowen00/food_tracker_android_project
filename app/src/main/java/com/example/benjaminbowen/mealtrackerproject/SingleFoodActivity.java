package com.example.benjaminbowen.mealtrackerproject;

import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SingleFoodActivity extends AppCompatActivity {

    AppDatabase db;

    TextView dateSingleText;
    TextView mealSingleText;
    TextView foodSingleText;
    TextView commentSingleText;
    Button deleteButton;
    int foodID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_food);

        dateSingleText = findViewById(R.id.date_single_view_text);
        mealSingleText = findViewById(R.id.meal_single_view_text);
        foodSingleText = findViewById(R.id.food_single_view_text);
        commentSingleText = findViewById(R.id.comment_single_view_text);
        deleteButton = findViewById(R.id.delete_button);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"foods")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();


        Intent intent = getIntent();
        foodID = intent.getIntExtra("foodID", 0);

        Food singleFood = db.foodDao().findByID(foodID);

        dateSingleText.setText(singleFood.getDate());
        mealSingleText.setText(singleFood.getMeal());
        foodSingleText.setText(singleFood.getFood());
        commentSingleText.setText(singleFood.getComment());

    }


    public void confirmDelete(View v){
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    db.foodDao().deleteByID(foodID);
                    startActivity(new Intent(SingleFoodActivity.this, ListAddActivity.class));
                    break;

                case DialogInterface.BUTTON_NEGATIVE:

                    break;
            }
        }
    };

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?").setNegativeButton("No", dialogClickListener).setPositiveButton("Yes", dialogClickListener).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        menu.removeItem(R.id.item_by_search);
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

        if(item.getItemId() == R.id.item_add_food){
            Intent intent = new Intent(this, CreateFoodActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}

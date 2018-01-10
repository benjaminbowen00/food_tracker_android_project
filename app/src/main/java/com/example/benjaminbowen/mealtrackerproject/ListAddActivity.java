package com.example.benjaminbowen.mealtrackerproject;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListAddActivity extends AppCompatActivity {

    FloatingActionButton addFab;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_add);

        addFab = findViewById(R.id.add_fab);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"foods")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListAddActivity.this, CreateFoodActivity.class));
            }
        });

        List<Food> foodList = db.foodDao().getAllFoods();

        ArrayList<Food> foods = (ArrayList)foodList;

        FoodsAdapter foodsAdapter = new FoodsAdapter(this, foods);

        ListView listView = findViewById(R.id.main_list);
        listView.setAdapter(foodsAdapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        menu.removeItem(R.id.item_main_page);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId() == R.id.item_by_day){
            Intent intent = new Intent (this, ByDayActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.item_by_meal){
            Intent intent = new Intent(this, ByMealActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void getFood(View listItem){
        int foodID = (int) listItem.getTag();

        Intent intent = new Intent(this, SingleFoodActivity.class);
        intent.putExtra("foodID", foodID);
        startActivity(intent);
    }


}

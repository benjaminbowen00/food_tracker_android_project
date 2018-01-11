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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    AppDatabase db;
    EditText searchText;
    Button searchButton;
    ListView searchListView;
    TextView noResultsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

    }

    public void getSearchResults(View v){

        searchText = findViewById(R.id.search_page_text);
        searchButton = findViewById(R.id.search_page_button);
        searchListView = findViewById(R.id.search_list);
        noResultsText = findViewById(R.id.no_food_search);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"foods")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        String searchWord = searchText.getText().toString();
        String searchDBWord = '%'+searchWord+'%';

        List<Food> foodList = db.foodDao().findBySearch(searchDBWord);

        ArrayList<Food> foods = (ArrayList)foodList;

        if(foods.size()==0){
            noResultsText.setVisibility(View.VISIBLE);
            searchListView.setVisibility((View.INVISIBLE));
            String outputText = "There are no foods recorded as "+ "'"+searchWord+"'";
            noResultsText.setText(outputText);

        }
        else {
            noResultsText.setVisibility(View.INVISIBLE);
            searchListView.setVisibility((View.VISIBLE));


            FoodsAdapter foodsAdapter = new FoodsAdapter(this, foods);


            searchListView.setAdapter(foodsAdapter);

            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);

            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

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
            Intent intent = new Intent(this, ListAddActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.item_by_day){
            Intent intent = new Intent (this, ByDayActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.item_by_meal){
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

        return super.onOptionsItemSelected(item);
    }

    public void getFood(View listItem){
        int foodID = (int) listItem.getTag();

        Intent intent = new Intent(this, SingleFoodActivity.class);
        intent.putExtra("foodID", foodID);
        startActivity(intent);
    }
}

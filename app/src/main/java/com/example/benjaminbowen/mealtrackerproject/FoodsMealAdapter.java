package com.example.benjaminbowen.mealtrackerproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by benjaminbowen on 09/01/2018.
 */

public class FoodsMealAdapter extends ArrayAdapter<Food> {

    public FoodsMealAdapter(Context context, ArrayList<Food> foods) {
        super(context, 0, foods);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.food_by_meal_item, parent, false);
        }

        Food currentFood = getItem(position);

        TextView foodListItem = listItemView.findViewById(R.id.food_by_meal_text);
        foodListItem.setText(currentFood.getFood());

        TextView dateListItem = listItemView.findViewById(R.id.date_by_meal_text);
        dateListItem.setText(currentFood.getDate());


        return listItemView;

    }
}

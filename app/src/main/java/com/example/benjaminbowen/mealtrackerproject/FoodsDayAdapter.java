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

public class FoodsDayAdapter extends ArrayAdapter<Food> {

    public FoodsDayAdapter(Context context, ArrayList<Food> foods) {
        super(context, 0, foods);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.food_by_day_item, parent, false);
        }

        Food currentFood = getItem(position);

        TextView foodListItem = listItemView.findViewById(R.id.food_by_day_text);
        foodListItem.setText(currentFood.getFood());

        TextView mealListItem = listItemView.findViewById(R.id.meal_by_day_text);
        mealListItem.setText(currentFood.getMeal());


        return listItemView;

    }



}

package com.amrdeveloper.menuapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by AmrDeveloper on 9/23/2018.
 */

public class MenuAdapter extends ArrayAdapter<Food> {

    private List<Food> menuList;

    public MenuAdapter(@NonNull Context context, List<Food> menuList) {
        super(context, 0, menuList);
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            //Inflate Layout If Native View equal null
            view = LayoutInflater.from(getContext()).inflate(R.layout.menu_list_item, parent, false);
        }

        //Get Current food from Menu List
        Food currentFood = getItem(position);

        //Initialize Views
        TextView foodTitle = view.findViewById(R.id.menuItemTitle);
        TextView foodPrice = view.findViewById(R.id.menuItemPrice);
        TextView foodDescription = view.findViewById(R.id.menuItemDescription);
        ImageView foodImage = view.findViewById(R.id.menuItemImage);

        //Bind Food On UI
        foodTitle.setText(currentFood.getTitle());
        foodPrice.setText(currentFood.getPrice());
        foodDescription.setText(currentFood.getDescription());
        foodImage.setImageResource(currentFood.getImageResouceID());

        //Return Layout
        return view;
    }
}


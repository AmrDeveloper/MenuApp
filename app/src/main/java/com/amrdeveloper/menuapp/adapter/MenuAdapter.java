package com.amrdeveloper.menuapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amrdeveloper.menuapp.R;
import com.amrdeveloper.menuapp.model.Food;
import com.amrdeveloper.menuapp.utils.ZoomAction;

import java.util.List;

/**
 * Created by AmrDeveloper on 9/23/2018.
 */

public class MenuAdapter extends ArrayAdapter<Food> {

    private List<Food> mMenuListItems;

    public MenuAdapter(@NonNull Context context, List<Food> menuList) {
        super(context, 0, menuList);
        mMenuListItems = menuList;
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
        final Food currentFood = getItem(position);

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

        //When user click on Image, The Image will be zoomed
        foodImage.setOnClickListener(v -> {
            //Make zoom when user click on ImageView Item
            ZoomAction.startImageZoom(getContext(), currentFood.getImageResouceID());
        });

        //Return Layout
        return view;
    }
}


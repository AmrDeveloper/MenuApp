package com.amrdeveloper.menuapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private ExpandableListView typesListView;
    private GridView foodGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        foodGridView = findViewById(R.id.foodGridView);
        List<Food> menu = new ArrayList<>();
        menu.add(new Food(getString(R.string.menu_item_title),getString(R.string.menu_item_price),getString(R.string.menu_item_description),R.drawable.image_2));
        menu.add(new Food(getString(R.string.menu_item_title),getString(R.string.menu_item_price),getString(R.string.menu_item_description),R.drawable.image_3));
        menu.add(new Food(getString(R.string.menu_item_title),getString(R.string.menu_item_price),getString(R.string.menu_item_description),R.drawable.image_4));
        menu.add(new Food(getString(R.string.menu_item_title),getString(R.string.menu_item_price),getString(R.string.menu_item_description),R.drawable.image_5));
        menu.add(new Food(getString(R.string.menu_item_title),getString(R.string.menu_item_price),getString(R.string.menu_item_description),R.drawable.image_6));
        menu.add(new Food(getString(R.string.menu_item_title),getString(R.string.menu_item_price),getString(R.string.menu_item_description),R.drawable.image_7));
        menu.add(new Food(getString(R.string.menu_item_title),getString(R.string.menu_item_price),getString(R.string.menu_item_description),R.drawable.image_8));

        MenuAdapter adapter = new MenuAdapter(this,menu);
        foodGridView.setAdapter(adapter);
    }
}

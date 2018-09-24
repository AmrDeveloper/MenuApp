package com.amrdeveloper.menuapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private ExpandableListView typesListView;
    private GridView foodGridView;
    private ImageView background;


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


    public void backToMainActivity(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void goToFeedBackActivity(View view){
        Intent intent = new Intent(this,FeedbackActivity.class);
        startActivity(intent);
    }
}

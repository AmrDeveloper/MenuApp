package com.amrdeveloper.menuapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private GridView foodGridView;
    private ImageView mBackgroundImg;

    private ExpandableListView menuExpListView;
    private ExpandableListAdapter mMenuListViewAdapter;

    private List<String> menuGroupHeadTitle;
    private HashMap<String, List<String>> menuGroubListItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mBackgroundImg = findViewById(R.id.menuBackgroundImg);

        foodGridView = findViewById(R.id.foodGridView);
        List<Food> menu = new ArrayList<>();
        menu.add(new Food(getString(R.string.menu_item_title), getString(R.string.menu_item_price), getString(R.string.menu_item_description), R.drawable.image_2));
        menu.add(new Food(getString(R.string.menu_item_title), getString(R.string.menu_item_price), getString(R.string.menu_item_description), R.drawable.image_3));
        menu.add(new Food(getString(R.string.menu_item_title), getString(R.string.menu_item_price), getString(R.string.menu_item_description), R.drawable.image_4));
        menu.add(new Food(getString(R.string.menu_item_title), getString(R.string.menu_item_price), getString(R.string.menu_item_description), R.drawable.image_5));
        menu.add(new Food(getString(R.string.menu_item_title), getString(R.string.menu_item_price), getString(R.string.menu_item_description), R.drawable.image_6));
        menu.add(new Food(getString(R.string.menu_item_title), getString(R.string.menu_item_price), getString(R.string.menu_item_description), R.drawable.image_7));
        menu.add(new Food(getString(R.string.menu_item_title), getString(R.string.menu_item_price), getString(R.string.menu_item_description), R.drawable.image_8));

        final MenuAdapter adapter = new MenuAdapter(this, menu);
        foodGridView.setAdapter(adapter);

        menuExpListView = findViewById(R.id.typesListView);
        initDummyData();
        mMenuListViewAdapter = new ExpandableListAdapter(this, menuGroupHeadTitle, menuGroubListItem);
        menuExpListView.setAdapter(mMenuListViewAdapter);

        menuExpListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                setMenuItemsMode();

                TextView selectedGroupHeader =  mMenuListViewAdapter.getGroupHeader(menuExpListView,groupPosition).findViewById(R.id.menuFoodGroup);
                selectedGroupHeader.setTextColor(getResources().getColor(R.color.red));
                return false;
            }
        });

        menuExpListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                setBackgroundMode();

                TextView selectedGroupHeader =  mMenuListViewAdapter.getGroupHeader(menuExpListView,groupPosition).findViewById(R.id.menuFoodGroup);
                selectedGroupHeader.setTextColor(Color.WHITE);
            }
        });
    }

    private void initDummyData() {
        menuGroupHeadTitle = new ArrayList<>();
        menuGroubListItem = new HashMap<>();

        //Menu Group Header
        menuGroupHeadTitle.add("Soup");
        menuGroupHeadTitle.add("Tempura");
        menuGroupHeadTitle.add("Appetizers");
        menuGroupHeadTitle.add("Pasta");
        menuGroupHeadTitle.add("Saled");
        menuGroupHeadTitle.add("Nigiri");
        menuGroupHeadTitle.add("Sashimi");
        menuGroupHeadTitle.add("Temaki");
        menuGroupHeadTitle.add("Maki");
        menuGroupHeadTitle.add("Bento boxes");
        menuGroupHeadTitle.add("Sharing boats");
        menuGroupHeadTitle.add("Noodels");
        menuGroupHeadTitle.add("Teriyaki shewers");

        //Maki Food
        List<String> maki = new ArrayList<>();
        maki.add("Ura maki");
        maki.add("Crispy ura maki");
        maki.add("Ura maki special");
        maki.add("Gunkan maki");
        maki.add("Hoso maki");
        maki.add("Futomaki");

        for (int i = 0; i < menuGroupHeadTitle.size(); i++) {
            menuGroubListItem.put(menuGroupHeadTitle.get(i), maki);
        }
    }

    public void backToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToFeedBackActivity(View view) {
        Intent intent = new Intent(this, FeedbackActivity.class);
        startActivity(intent);
    }

    private void setBackgroundMode() {
        mBackgroundImg.setVisibility(View.VISIBLE);
        foodGridView.setVisibility(View.GONE);
    }

    private void setMenuItemsMode() {
        mBackgroundImg.setVisibility(View.GONE);
        foodGridView.setVisibility(View.VISIBLE);
    }
}

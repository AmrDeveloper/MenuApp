package com.amrdeveloper.menuapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.amrdeveloper.menuapp.R;
import com.amrdeveloper.menuapp.adapter.ExpandableListAdapter;
import com.amrdeveloper.menuapp.adapter.MainMenuAdapter;
import com.amrdeveloper.menuapp.data.DummyData;
import com.amrdeveloper.menuapp.model.Food;

import java.util.HashMap;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private ImageView mBackgroundImg;
    private ExpandableListView foodExpList;
    private ExpandableListView menuExpListView;

    private ExpandableListAdapter mMenuListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        DummyData mDummyData = new DummyData(this);

        List<String> menuGroupHeadTitle = mDummyData.getMenuGroupHeaders();
        HashMap<String, List<String>> menuGroupListItem = mDummyData.getMenuGroupListItems(menuGroupHeadTitle);

        foodExpList = findViewById(R.id.foodExpList);

        List<String> foodListTitles = mDummyData.getMenuType();
        List<List<Food>> foodItemsList = mDummyData.foodDummyList();
        HashMap<String, List<List<Food>>> foodMenuItemsMap = mDummyData.getMenuListFood(foodItemsList);

        MainMenuAdapter adapter = new MainMenuAdapter(this, foodListTitles, foodMenuItemsMap);
        foodExpList.setAdapter(adapter);

        mMenuListViewAdapter = new ExpandableListAdapter(this, menuGroupHeadTitle, menuGroupListItem);

        menuExpListView = findViewById(R.id.typesListView);
        menuExpListView.setAdapter(mMenuListViewAdapter);
        menuExpListView.setOnGroupClickListener(mOnGroupClickListener);
        menuExpListView.setOnGroupExpandListener(mOnGroupExpandListener);
        menuExpListView.setOnGroupCollapseListener(mOnGroupCollapseListener);

        mBackgroundImg = findViewById(R.id.menuBackgroundImg);
        setBackgroundMode();
    }

    /**
     * @param view : When user click on Language ImageButton
     *             Start Mein Activity to choice language again
     */
    public void backToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * @param view : When user click on Feedback image button
     *             Go To FeedBack Activity
     */
    public void goToFeedBackActivity(View view) {
        Intent intent = new Intent(this, FeedbackActivity.class);
        startActivity(intent);
    }

    /**
     * Background mode : hide menu list and show background
     */
    private void setBackgroundMode() {
        mBackgroundImg.setVisibility(View.VISIBLE);
        foodExpList.setVisibility(View.GONE);
    }

    /**
     * Menu Mode : Hide Background ans show the menu List
     */
    private void setMenuItemsMode() {
        mBackgroundImg.setVisibility(View.GONE);
        foodExpList.setVisibility(View.VISIBLE);
    }

    /**
     * When user click on any food group title switch mode from background to menu mode
     */
    private final ExpandableListView.OnGroupClickListener mOnGroupClickListener = (parent, view, groupPosition, id) -> {
        setMenuItemsMode();
        return false;
    };

    /**
     * when user close food group list switch mode from menu to background
     */
    private final ExpandableListView.OnGroupCollapseListener mOnGroupCollapseListener = (groupPosition) -> {
        setBackgroundMode();
    };

    /**
     * when user open one food group the listener close all other groups
     */
    private final ExpandableListView.OnGroupExpandListener mOnGroupExpandListener = (groupPosition) -> {
        for (int index = 0; index < mMenuListViewAdapter.getGroupCount(); index++)
            if (index != groupPosition)
                if (menuExpListView.isGroupExpanded(index))
                    menuExpListView.collapseGroup(index);
        setMenuItemsMode();
    };
}

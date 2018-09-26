package com.amrdeveloper.menuapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;

import com.amrdeveloper.menuapp.R;
import com.amrdeveloper.menuapp.adapter.ExpandableListAdapter;
import com.amrdeveloper.menuapp.adapter.MenuAdapter;
import com.amrdeveloper.menuapp.data.DummyData;

import java.util.HashMap;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private GridView foodGridView;
    private ImageView mBackgroundImg;
    private ExpandableListView menuExpListView;

    private ExpandableListAdapter mMenuListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        DummyData mDummyData = new DummyData(this);
        List<String> menuGroupHeadTitle = mDummyData.getMenuGroupHeaders();
        HashMap<String, List<String>> menuGroupListItem = mDummyData.getMenuGroupListItems(menuGroupHeadTitle);

        final MenuAdapter adapter = new MenuAdapter(this, mDummyData.foodDummyList());
        foodGridView = findViewById(R.id.foodGridView);
        foodGridView.setAdapter(adapter);

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
     *               Start Mein Activity to choice language again
     */
    public void backToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     *
     * @param view : When user click on Feedback image button
     *               Go To FeedBack Activity
     */
    public void goToFeedBackActivity(View view) {
        Intent intent = new Intent(this, FeedbackActivity.class);
        startActivity(intent);
    }

    /**
     *
     */
    private void setBackgroundMode() {
        mBackgroundImg.setVisibility(View.VISIBLE);
        foodGridView.setVisibility(View.GONE);
    }

    /**
     *
     */
    private void setMenuItemsMode() {
        mBackgroundImg.setVisibility(View.GONE);
        foodGridView.setVisibility(View.VISIBLE);
    }

    /**
     *
     */
    private final ExpandableListView.OnGroupClickListener mOnGroupClickListener = (parent, view, groupPosition, id) -> {
        setMenuItemsMode();
        return false;
    };

    /**
     *
     */
    private final ExpandableListView.OnGroupCollapseListener mOnGroupCollapseListener = (groupPosition) -> {
        setBackgroundMode();
    };

    /**
     *
     */
    private final ExpandableListView.OnGroupExpandListener mOnGroupExpandListener = (groupPosition) -> {
        for (int index = 0; index < mMenuListViewAdapter.getGroupCount(); index++)
            if (index != groupPosition)
                if (menuExpListView.isGroupExpanded(index))
                    menuExpListView.collapseGroup(index);
        setMenuItemsMode();
    };
}

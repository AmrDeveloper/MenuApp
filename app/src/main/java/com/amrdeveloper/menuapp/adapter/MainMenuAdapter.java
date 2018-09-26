package com.amrdeveloper.menuapp.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.TextView;

import com.amrdeveloper.menuapp.R;
import com.amrdeveloper.menuapp.model.Food;

import java.util.HashMap;
import java.util.List;

/**
 * Created by AmrDeveloper on 9/26/2018.
 */

public class MainMenuAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> foodMenuGroup;
    private HashMap<String, List<List<Food>>> foodMenuListItems;

    public MainMenuAdapter(Context context, List<String> foodMenuGroup, HashMap<String, List<List<Food>>> foodMenuListItems) {
        this.context = context;
        this.foodMenuGroup = foodMenuGroup;
        this.foodMenuListItems = foodMenuListItems;
    }

    @Override
    public int getGroupCount() {
        return foodMenuGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return foodMenuListItems.get(foodMenuGroup.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return foodMenuGroup.get(groupPosition);
    }

    @Override
    public List<Food> getChild(int groupPosition, int childPosition) {
        return foodMenuListItems.get(foodMenuGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = getGroup(groupPosition).toString();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.menu_main_title, null);
        }

        final TextView menuFoodHeader = convertView.findViewById(R.id.menuMainTitle);
        menuFoodHeader.setTypeface(null, Typeface.BOLD);
        menuFoodHeader.setText(headerTitle);

        ExpandableListView mExpandableListView = (ExpandableListView) parent;
        mExpandableListView.expandGroup(groupPosition);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final List<Food> foodList = getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.menu_main_group, null);
        }

        GridView menuMainGroup = (GridView) convertView;
        final MenuAdapter adapter = new MenuAdapter(context, foodList);
        menuMainGroup.setAdapter(adapter);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

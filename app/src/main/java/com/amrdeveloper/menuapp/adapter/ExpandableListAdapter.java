package com.amrdeveloper.menuapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.amrdeveloper.menuapp.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by AmrDeveloper on 9/24/2018.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> menuListHeader;
    private HashMap<String, List<String>> menuListItem;

    public ExpandableListAdapter(Context context, List<String> menuListHeader, HashMap<String, List<String>> menuListItem) {
        this.context = context;
        this.menuListHeader = menuListHeader;
        this.menuListItem = menuListItem;
    }

    @Override
    public int getGroupCount() {
        return menuListHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return menuListItem.get(menuListHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return menuListHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return menuListItem.get(menuListHeader.get(groupPosition)).get(childPosition);
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
            convertView = inflater.inflate(R.layout.menu_groub_title, null);
        }

        final TextView menuFoodHeader = convertView.findViewById(R.id.menuFoodGroup);
        menuFoodHeader.setTypeface(null, Typeface.BOLD);
        menuFoodHeader.setText(headerTitle);

        if (isExpanded) {
            int redColorID = context.getResources().getColor(R.color.red);
            menuFoodHeader.setTextColor(redColorID);
        } else {
            menuFoodHeader.setTextColor(Color.WHITE);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.menu_group_item, null);
        }

        TextView txtListChild = convertView.findViewById(R.id.menuFoodTitle);
        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

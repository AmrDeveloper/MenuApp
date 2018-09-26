package com.amrdeveloper.menuapp.data;

import android.content.Context;

import com.amrdeveloper.menuapp.model.Food;
import com.amrdeveloper.menuapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by AmrDeveloper on 9/25/2018.
 */

public final class DummyData {

    private Context context;

    public DummyData(Context context) {
        this.context = context;
    }

    public List<Food> foodDummyList() {
        final String dummyFoodTitle = context.getResources().getString(R.string.menu_item_title);
        final String dummyFoodPrice = context.getResources().getString(R.string.menu_item_price);
        final String dummyFoodDescription = context.getResources().getString(R.string.menu_item_description);

        List<Food> foodMenuList = new ArrayList<>();
        foodMenuList.add(new Food(dummyFoodTitle, dummyFoodPrice, dummyFoodDescription, R.drawable.image_2));
        foodMenuList.add(new Food(dummyFoodTitle, dummyFoodPrice, dummyFoodDescription, R.drawable.image_3));
        foodMenuList.add(new Food(dummyFoodTitle, dummyFoodPrice, dummyFoodDescription, R.drawable.image_4));
        foodMenuList.add(new Food(dummyFoodTitle, dummyFoodPrice, dummyFoodDescription, R.drawable.image_5));
        foodMenuList.add(new Food(dummyFoodTitle, dummyFoodPrice, dummyFoodDescription, R.drawable.image_6));
        foodMenuList.add(new Food(dummyFoodTitle, dummyFoodPrice, dummyFoodDescription, R.drawable.image_7));
        foodMenuList.add(new Food(dummyFoodTitle, dummyFoodPrice, dummyFoodDescription, R.drawable.image_8));

        return foodMenuList;
    }

    public List<String> getMenuGroupHeaders() {
        List<String> menuChildItems = new ArrayList<>();

        menuChildItems.add("Soup");
        menuChildItems.add("Tempura");
        menuChildItems.add("Appetizers");
        menuChildItems.add("Pasta");
        menuChildItems.add("Saled");
        menuChildItems.add("Nigiri");
        menuChildItems.add("Sashimi");
        menuChildItems.add("Temaki");
        menuChildItems.add("Maki");
        menuChildItems.add("Bento boxes");
        menuChildItems.add("Sharing boats");
        menuChildItems.add("Noodels");
        menuChildItems.add("Teriyaki shewers");

        return menuChildItems;
    }

    public HashMap<String, List<String>> getMenuGroupListItems(List<String> menuChildItems) {
        HashMap<String, List<String>> menuGroupListItems = new HashMap<>();

        final List<String> maki = new ArrayList<>();
        maki.add("Ura maki");
        //maki.add("Crispy ura maki");
        //maki.add("Ura maki special");
        //maki.add("Gunkan maki");
        //maki.add("Hoso maki");
        //maki.add("Futomaki");

        for (int i = 0; i < menuChildItems.size(); i++) {
            menuGroupListItems.put(menuChildItems.get(i), maki);
        }
        return menuGroupListItems;
    }
}

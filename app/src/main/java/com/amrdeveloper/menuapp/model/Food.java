package com.amrdeveloper.menuapp.model;

/**
 * Created by AmrDeveloper on 9/23/2018.
 */

public class Food {

    private String title;
    private String price;
    private String description;

    private int imageResourceID;

    public Food(String title, String price, String description, int imageResourceID) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.imageResourceID = imageResourceID;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResouceID() {
        return imageResourceID;
    }
}

package com.amrdeveloper.menuapp;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by AmrDeveloper on 9/23/2018.
 */

public class ZoomAction {

    /**
     * Get current Screen width and height then make full screen zoom
     * @param context : Context of Menu item view
     * @param imgResourceId : Image Resource Id to load it from drawable
     */
    public static void startImageZoom(Context context, int imgResourceId) {
        //Zoom Image Code
        //Get This Screen Height and width
        DisplayMetrics dispaly = new DisplayMetrics();

        //Get Metrics
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            ((Activity) context).getWindowManager().getDefaultDisplay().getRealMetrics(dispaly);
        }else{
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dispaly);
        }

        //get height
        int height = dispaly.heightPixels;
        //get width
        int width = dispaly.widthPixels;
        //now make inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate layout take my layout id , id of layout that have ImageView
        View zoomLayout = inflater.inflate(R.layout.zoom_layout, (ViewGroup) ((Activity) context).findViewById(R.id.layout));

        //define ImageView from this view zoomLayout
        ImageView zoomedImage = zoomLayout.findViewById(R.id.zoomImageView);
        zoomedImage.setImageResource(imgResourceId);
        //set height of image is height from DisplayMetrics Full Screen
        zoomedImage.setMinimumHeight(height);
        //set width of image is width from DisplayMetrics Full Screen
        zoomedImage.setMinimumWidth(width);
        zoomedImage.setMaxHeight(height);
        zoomedImage.setMaxWidth(width);
        //show this sub layout in Toast
        Toast toast = new Toast(context.getApplicationContext());
        //set view
        toast.setView(zoomLayout);
        //show this toast
        toast.show();

    }
}

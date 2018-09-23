package com.amrdeveloper.menuapp;

/**
 * Created by AmrDeveloper on 9/23/2018.
 */

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

/**
 * This is a service registers a broadcast receiver to listen for screen on/off events.
 * It is a very unfortunate service that must exist because we can't register for screen on/off
 * in the manifest.
 */

public class ScreenListenerService extends Service {

    private BroadcastReceiver mScreenStateBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Intent discoveryIntent = new Intent(context, MainActivity.class);

            //Start Application When Screen Unlock
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                discoveryIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(discoveryIntent);
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mScreenStateBroadcastReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mScreenStateBroadcastReceiver);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Nothing should bind to this service
        return null;
    }
}
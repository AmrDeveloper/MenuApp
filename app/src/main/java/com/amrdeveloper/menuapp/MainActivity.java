package com.amrdeveloper.menuapp;


import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView arabLangTxt;
    private TextView engLangTxt;
    Configuration mConfiguration;

    private boolean isHidden = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startScreenListenerService();
        initializeView();
    }

    /**
     * Service to Launch application every time when screen unlock
     */
    private void startScreenListenerService() {
        Intent serviceIntent = new Intent(this, ScreenListenerService.class);
        startService(serviceIntent);
    }

    private void initializeView() {
        arabLangTxt = findViewById(R.id.arabLangTxt);
        engLangTxt = findViewById(R.id.engLangTxt);
    }

    public void menuActivityLauncher(View view) {
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    public void changeLanguageAction(View view) {
        languageVisibilityControl();
    }

    /**
     * If Languages Text is Hidden show it
     */
    private void languageVisibilityControl() {
        if (isHidden) {
            arabLangTxt.setVisibility(View.VISIBLE);
            engLangTxt.setVisibility(View.VISIBLE);
            isHidden = false;
        } else {
            arabLangTxt.setVisibility(View.INVISIBLE);
            engLangTxt.setVisibility(View.INVISIBLE);
            isHidden = true;
        }
    }

    public void changeLanguageToArabic(View view) {
        //Change Application Language to Arabic
        mConfiguration = new Configuration(getResources().getConfiguration());
        mConfiguration.locale = new Locale("ar");
        getResources().updateConfiguration(mConfiguration,getResources().getDisplayMetrics());
        reloadMain();
    }

    public void changeLanguageToEnglish(View view) {
        //Change Application Language to English
        mConfiguration = new Configuration(getResources().getConfiguration());
        mConfiguration.locale = Locale.ENGLISH;
        getResources().updateConfiguration(mConfiguration,getResources().getDisplayMetrics());
        reloadMain();
    }

    public void goToFeedBackActivity(View view) {
        Intent intent = new Intent(this, FeedbackActivity.class);
        startActivity(intent);
    }

    private void reloadMain(){
        finish();
        startActivity(getIntent());
    }
}

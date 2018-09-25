package com.amrdeveloper.menuapp.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.amrdeveloper.menuapp.R;
import com.amrdeveloper.menuapp.service.ScreenListenerService;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView arabLangTxt;
    private TextView engLangTxt;

    private boolean isHidden = true;
    private boolean isFirstLaunch = true;
    private boolean isEnglishLanguage = true;

    private static final String LAUNCHER = "launcher";
    private static final String LANGUAGE = "language";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isFirstStart();
        setContentView(R.layout.activity_main);

        startScreenListenerService();
        initializeViews();
        changeTextViewState();
    }

    private void isFirstStart() {
        Intent intent = getIntent();
        this.isFirstLaunch = intent.getBooleanExtra(LAUNCHER, true);
        this.isEnglishLanguage = intent.getBooleanExtra(LANGUAGE,true);

        if (isFirstLaunch) {
            changeRuntimeLanguage(Locale.ENGLISH);
            isFirstLaunch = false;
        }
    }

    /**
     * Service to Launch application every time when screen unlock
     */
    private void startScreenListenerService() {
        Intent serviceIntent = new Intent(this, ScreenListenerService.class);
        startService(serviceIntent);
    }

    private void initializeViews() {
        arabLangTxt = findViewById(R.id.arabLangTxt);
        engLangTxt = findViewById(R.id.engLangTxt);
    }

    private void changeTextViewState(){
        if(isEnglishLanguage){
            engLangTxt.setTextColor(getResources().getColor(R.color.red));
            arabLangTxt.setTextColor(Color.WHITE);
            arabLangTxt.setClickable(true);
            engLangTxt.setClickable(false);
        }else{
            arabLangTxt.setTextColor(getResources().getColor(R.color.red));
            engLangTxt.setTextColor(Color.WHITE);
            arabLangTxt.setClickable(false);
            engLangTxt.setClickable(true);
        }
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

    private void changeRuntimeLanguage(Locale locale) {
        Configuration mConfiguration = new Configuration(getResources().getConfiguration());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mConfiguration.setLocale(locale);
        } else {
            mConfiguration.locale = locale;
        }
        getResources().updateConfiguration(mConfiguration, getResources().getDisplayMetrics());
    }

    private void reloadMainActivity() {
        finish();
        Intent intent = getIntent();
        intent.putExtra(LAUNCHER, isFirstLaunch);
        intent.putExtra(LANGUAGE, isEnglishLanguage);
        startActivity(intent);
    }

    public void menuActivityLauncher(View view) {
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    public void changeLanguageAction(View view) {
        languageVisibilityControl();
    }

    public void changeLanguageToArabic(View view) {
        changeRuntimeLanguage(new Locale("ar"));
        isFirstLaunch = false;
        isEnglishLanguage = false;
        reloadMainActivity();
    }

    public void changeLanguageToEnglish(View view) {
        changeRuntimeLanguage(Locale.ENGLISH);
        isFirstLaunch = false;
        isEnglishLanguage = true;
        reloadMainActivity();
    }

    public void goToFeedBackActivity(View view) {
        Intent intent = new Intent(this, FeedbackActivity.class);
        startActivity(intent);
    }
}

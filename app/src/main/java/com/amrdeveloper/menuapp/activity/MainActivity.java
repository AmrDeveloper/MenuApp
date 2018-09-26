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

    /**
     * View to Show current application language
     * and convert to Arabic if user click on it
     */
    private TextView arabLangTxt;

    /**
     * View to Show current application language
     * and convert to English if user click on it
     */
    private TextView engLangTxt;

    /**
     * true if user not click on Language ImageButton
     */
    private boolean isHidden = true;

    /**
     * true only if it's first time to launch application
     * and languages not changes from default to other languages
     */
    private boolean isFirstLaunch = true;

    /**
     * boolean to show current application language
     * true if current application language is english
     * false if it's arabic
     */
    private boolean isEnglishLanguage = true;

    /**
     * Tag to save and receive Launcher state
     */
    private static final String LAUNCHER = "launcher";

    /**
     * Tag to save and receive current language state
     */
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

    /**
     * Receive values from intent and update member values
     * Make Application language is english for default if it first time to launch the application
     */
    private void isFirstStart() {
        Intent intent = getIntent();
        isFirstLaunch = intent.getBooleanExtra(LAUNCHER, true);
        isEnglishLanguage = intent.getBooleanExtra(LANGUAGE, true);

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

    /**
     * Initializing All Views in This Activity
     */
    private void initializeViews() {
        arabLangTxt = findViewById(R.id.arabLangTxt);
        engLangTxt = findViewById(R.id.engLangTxt);
    }

    /**
     * Change TextView Attributes depend on current application language
     * if current language is english Change:
     * 1 - English TextView Text color to Red
     * 2 - Arabic TextView Text Color to White
     * 3 - Make English TextView unClickable because it's english now
     * if current language is Arabic Change:
     * 1 - Arabic TextView Text color to Red
     * 2 - English TextView Text Color to White
     * 3 - Make Arabic TextView unClickable because it's Arabic now
     */
    private void changeTextViewState() {
        if (isEnglishLanguage) {
            engLangTxt.setTextColor(getResources().getColor(R.color.red));
            arabLangTxt.setTextColor(Color.WHITE);
            arabLangTxt.setClickable(true);
            engLangTxt.setClickable(false);
        } else {
            arabLangTxt.setTextColor(getResources().getColor(R.color.red));
            engLangTxt.setTextColor(Color.WHITE);
            arabLangTxt.setClickable(false);
            engLangTxt.setClickable(true);
        }
    }

    /**
     * Take Language type and convert Application current language to locale object language
     *
     * @param locale : Language Type
     */
    private void changeRuntimeLanguage(Locale locale) {
        Configuration mConfiguration = new Configuration(getResources().getConfiguration());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mConfiguration.setLocale(locale);
        } else {
            mConfiguration.locale = locale;
        }
        getResources().updateConfiguration(mConfiguration, getResources().getDisplayMetrics());
    }

    /**
     * Save Current values from MainActivity and return values after recreate activity
     * finish current activity
     * recreate MainActivity
     */
    private void reloadMainActivity() {
        finish();
        Intent intent = getIntent();
        intent.putExtra(LAUNCHER, isFirstLaunch);
        intent.putExtra(LANGUAGE, isEnglishLanguage);
        startActivity(intent);
    }

    /**
     * This method used to hide and show languages options text views
     * if current state is hidden :
     * 1 - Make all option is visible
     * 2 - convert state from hidden to unhidden
     * if current state is unhidden :
     * 1 - Make all option is invisible
     * 2 - convert state from unhidden to hidden
     *
     * @param view : Language Image Button
     */
    public void changeLanguageAction(View view) {
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

    /**
     * When user click of TextView and it's Clickable
     * 1 - Change Application language From English To Arabic
     * 2 - Make boolean is first time to launch app is false because it's not default state
     * 3 - Make boolean is English Language false because it's Arabic now
     * 4 - Save booleans current values and ReCreate This Activity
     *
     * @param view : TextView to change language to Arabic
     */
    public void changeLanguageToArabic(View view) {
        changeRuntimeLanguage(new Locale("ar"));
        isFirstLaunch = false;
        isEnglishLanguage = false;
        reloadMainActivity();
    }

    /**
     * When user click of TextView and it's Clickable
     * 1 - Change Application language From Arabic To English
     * 2 - Make boolean is first time to launch app is false because it's not default state
     * 3 - Make boolean is English Language true
     * 4 - Save booleans current values and ReCreate This Activity
     *
     * @param view : TextView to change language to English
     */
    public void changeLanguageToEnglish(View view) {
        changeRuntimeLanguage(Locale.ENGLISH);
        isFirstLaunch = false;
        isEnglishLanguage = true;
        reloadMainActivity();
    }

    /**
     * Start MenuActivity from MainActivity
     *
     * @param view : Menu ImageButton
     */
    public void menuActivityLauncher(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    /**
     * Start FeedbackActivity when user click of Feedback ImageButton
     *
     * @param view : Feedback ImageButton
     */
    public void goToFeedBackActivity(View view) {
        Intent intent = new Intent(this, FeedbackActivity.class);
        startActivity(intent);
    }
}

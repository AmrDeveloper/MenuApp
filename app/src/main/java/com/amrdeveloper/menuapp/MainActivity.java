package com.amrdeveloper.menuapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView arabLangTxt;
    private TextView engLangTxt;

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
    private void languageVisibilityControl(){
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

    public void feedBackLauncher(View view) {
        Toast.makeText(this, "FeedBack Action", Toast.LENGTH_SHORT).show();
    }
}

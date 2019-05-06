package com.zeneo.horoscope;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.zeneo.horoscope.Notifications.MyReceiver;

import java.util.Calendar;

public class SplachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);

        View view = findViewById(R.id.animated_bg);

        final Animation anim = new ScaleAnimation(
                1.15f, 1.05f, // Start and end values for the X axis scaling
                1.15f, 1.05f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
        anim.setDuration(5000);

        view.setAnimation(anim);

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        boolean isPicked = false;
        try {
            isPicked = preferences.getBoolean("isPicked",false);
        }catch (NullPointerException e){
            e.printStackTrace();
        }


        final boolean finalIsPicked = isPicked;
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                if (!finalIsPicked){
                    Intent intent = new Intent(getApplicationContext(), PickerActivity.class);
                    startActivity(intent);
                } else {
                    int zodiakNum;
                    Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                    zodiakNum = preferences.getInt("index",0);
                    mainIntent.putExtra("zodiakNum",zodiakNum);
                    startActivity(mainIntent);
                }
                finish();

            }
        }, 5000);



    }





}

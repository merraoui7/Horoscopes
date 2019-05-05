package com.zeneo.horoscope;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.zeneo.horoscope.Fragments.FeaturesFragment;
import com.zeneo.horoscope.Fragments.HoroscopesFragment;
import com.zeneo.horoscope.Fragments.LoveFragment;
import com.zeneo.horoscope.Notifications.MyReceiver;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    FragmentManager fm;
    FragmentTransaction ft;
    boolean loveNotReady = false;
    boolean horoscopeNotReady = false;
    FragmentManager fm2;
    FragmentTransaction ft2;
    BottomNavigationView navigation;

    boolean youMustUnregisterReciver = false;

    FrameLayout frame1,frame2,frame3;

    BroadcastReceiver broadcastReceiver;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    frame1.setVisibility(View.VISIBLE);
                    frame2.setVisibility(View.GONE);
                    frame3.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_horo:
                    if(!horoscopeNotReady) {
                        FragmentManager fm;
                        FragmentTransaction ft;
                        fm = getSupportFragmentManager();
                        ft = fm.beginTransaction();
                        ft.replace(R.id.horoscopesFrag,new HoroscopesFragment());
                        ft.commit();
                        horoscopeNotReady = true;
                    }
                    frame1.setVisibility(View.GONE);
                    frame2.setVisibility(View.VISIBLE);
                    frame3.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_love:
                    if(!loveNotReady){
                        fm2 = getSupportFragmentManager();
                        ft2 = fm2.beginTransaction();
                        ft2.replace(R.id.loveFrag,new LoveFragment());
                        ft2.commit();
                        loveNotReady = true;
                    }
                    frame1.setVisibility(View.GONE);
                    frame2.setVisibility(View.GONE);
                    frame3.setVisibility(View.VISIBLE);
                    return true;
            }
            return false;
        }
    };


    String zodiak [] = {
            "الحمل",
            "الثور",
            "الجوزاء",
            "السرطان",
            "الأسد",
            "العذراء",
            "الميزان",
            "العقرب",
            "القوس",
            "الجدي",
            "الدلو",
            "الحوت",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);


        int zodiaknum = getIntent().getExtras().getInt("zodiakNum");
        getSupportActionBar().setTitle("برج " + zodiak[zodiaknum]);


        frame1 = findViewById(R.id.featuresFrag);
        frame2 = findViewById(R.id.horoscopesFrag);
        frame3 = findViewById(R.id.loveFrag);



        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action.equals("finish_activity")) {
                    youMustUnregisterReciver = true;
                    finish();
                    // DO WHATEVER YOU WANT.
                }
            }
        };
        registerReceiver(broadcastReceiver, new IntentFilter("finish_activity"));

        final Animation anim = new ScaleAnimation(
                1.1f, 1f, // Start and end values for the X axis scaling
                1.05f, 1f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 1f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(200);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PickerActivity.class);
                intent.putExtra("from","main");
                startActivity(intent);
            }
        });
        toolbar.startAnimation(anim);

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.featuresFrag,new FeaturesFragment());
        ft.commit();



    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, new IntentFilter("finish_activity"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(youMustUnregisterReciver){
            unregisterReceiver(broadcastReceiver);
        }

    }

    public void goToDailyHoro(){
        navigation.setSelectedItemId(R.id.navigation_horo);
    }

}
